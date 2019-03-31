package com.pd.swgl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcBaseInfoExample.Criteria;
import com.pd.arc.service.PubArcBaseInfoService;
import com.pd.right.model.SuperUser;
import com.pd.swgl.dao.ArcStorageCombDAO;
import com.pd.swgl.dao.ArcStorageInDAO;
import com.pd.swgl.model.ArcStorageComb;
import com.pd.swgl.model.ArcStorageIn;
import com.pd.swgl.model.ArcStorageInExample;
import com.pd.swgl.utils.SwglStatus;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.QueryCriteria;
import com.pd.system.res.ArcStatus;
import com.pd.system.utils.StringTools;

public class StorageCombService extends BaseService {
	private ArcStorageCombDAO arcStorageCombDAO;
	private ArcStorageInDAO arcStorageInDAO;
	private PubArcBaseInfoService pubArcBaseInfoService;

	/**
	 * 查询可以合档的档案
	 * */
	public List<ArcBaseInfo> findAllowCombArc(ArcBaseInfo info) {
		if (null == info) {
			return new ArrayList<ArcBaseInfo>(0);
		}

		List<String> tj = new ArrayList<String>(2);
		tj.add(ArcStatus.YRK);// 已入库
		tj.add(ArcStatus.YLR_LSDA);// 已录入
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		Criteria criteria = (Criteria) QueryCriteria.checkAllStringByEquals(
				info, example.createCriteria(), false);
		criteria.andDaztIn(tj);

		List<ArcBaseInfo> list = pubArcBaseInfoService
				.findByArcExample(example);
		if (list.size() != 0) {
			ArcBaseInfo baseInfo = list.get(0);
			example.clear();
			example.createCriteria().andDaztIn(tj)
					.andClsbdhEqualTo(baseInfo.getClsbdh());
			return pubArcBaseInfoService.findByArcExample(example);
		} else {
			return new ArrayList<ArcBaseInfo>(0);
		}
	}

	/**
	 * 查看档案详细信息
	 * */
	public List<ArcBaseInfo> findArcByXtdabh(String xtdabh) {
		return pubArcBaseInfoService.findByXtdabh(xtdabh);
	}

	/**
	 * 档案合并
	 * */
	public void arcComb(List<String> ids, SuperUser user) throws Exception {

		ArcBaseInfo mainArc = null;
		List<ArcBaseInfo> list = new ArrayList<ArcBaseInfo>();

		// 查询出所有的档案，筛选出主副档案
		for (String id : ids) {
			List<ArcBaseInfo> arclist = pubArcBaseInfoService.findByXtdabh(id);
			for (ArcBaseInfo info : arclist) {
				// 如果是主档案
				if (info.getYwlx().equals(SwglStatus.MAIN_ARC_YWLX)) {
					mainArc = info;
				} else {
					list.add(info);
				}
			}
		}
		// 主档案为空
		if (null == mainArc) {
			throw new RuntimeException("未找到主档案");
		}
		// 合档时间
		Date date = new Date();

		// 将副档案进行合并，将副档案数据转移到 comb备份表.
		for (ArcBaseInfo info : list) {
			// 已入库档案操作入库表
			if (info.getDazt().equals(ArcStatus.YRK)) {
				// 查询入库表
				ArcStorageInExample inExample = new ArcStorageInExample();
				inExample.createCriteria().andXtdabhEqualTo(info.getXtdabh())
						.andDaztEqualTo(ArcStatus.YRK);
				List<ArcStorageIn> inlist = arcStorageInDAO
						.selectByExample(inExample);
				if (inlist.size() != 0) {
					ArcStorageIn in = inlist.get(0);
					// 插入数据到入库合档表
					ArcStorageComb comb = storageInToStorageComb(info, in,
							user, date);
					arcStorageCombDAO.insert(comb);
					// 删除入库表数据
					arcStorageInDAO.deleteByPrimaryKey(in.getRkid());
				}
			}
			// 操作档案表
			pubArcBaseInfoService.combArcBaseInfo(mainArc, info, user, date);
			//********************刷新储位**********************************
			
			
			
		}

	}

	// ======================================
	// 入库表与入库合档表转换
	private ArcStorageComb storageInToStorageComb(ArcBaseInfo info,
			ArcStorageIn in, SuperUser user, Date date) {
		ArcStorageComb comb = new ArcStorageComb();
		// 合档ID使用uuid
		comb.setHdid(StringTools.getUUID());
		comb.setRkid(in.getRkid());
		comb.setXtdabh(in.getXtdabh());
		comb.setZxtdabh(info.getXtdabh());
		comb.setDabh(in.getDabh());
		comb.setYcwbh(in.getCwbh());
		comb.setCwbh(info.getCwbh());
		comb.setPch(in.getRkpch());
		comb.setYwlx(in.getYwlx());
		comb.setLsh(in.getLsh());
		comb.setHpzl(in.getHpzl());
		comb.setHphm(in.getHphm());
		comb.setXh(in.getXh());
		comb.setClsbdh(in.getClsbdh());
		comb.setXzqh(in.getXzqh());
		comb.setSqrid(in.getSqrid());
		comb.setSqrxm(in.getSqrxm());
		comb.setSqsj(in.getSqsj());
		comb.setCzrid(in.getCzrid());
		comb.setCzrxm(in.getCzrxm());
		comb.setShrid(in.getShrid());
		comb.setShrxm(in.getShrxm());
		comb.setShsj(in.getShsj());
		comb.setRkrid(in.getRkrid());
		comb.setRkrxm(in.getRkrxm());
		comb.setRksj(in.getRksj());
		comb.setHdrid(user.getUserCode());
		comb.setHdrxm(user.getUserName());
		comb.setHdsj(date);
		return comb;
	}

	// ===================================================
	public void setArcStorageCombDAO(ArcStorageCombDAO arcStorageCombDAO) {
		this.arcStorageCombDAO = arcStorageCombDAO;
	}

	public void setArcStorageInDAO(ArcStorageInDAO arcStorageInDAO) {
		this.arcStorageInDAO = arcStorageInDAO;
	}

	public void setPubArcBaseInfoService(
			PubArcBaseInfoService pubArcBaseInfoService) {
		this.pubArcBaseInfoService = pubArcBaseInfoService;
	}

}
