package com.pd.swgl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.service.PubArcBaseInfoService;
import com.pd.right.model.SuperUser;
import com.pd.swgl.dao.ArcStorageInDAO;
import com.pd.swgl.model.ArcStorageIn;
import com.pd.swgl.model.ArcStorageInExample;
import com.pd.swgl.utils.SwglStatus;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.PageToExample;
import com.pd.system.framework.Pagination;
import com.pd.system.res.ArcStatus;
import com.pd.system.startup.DBResource;
import com.pd.system.utils.DateTools;
import com.pd.system.utils.StringTools;

/**
 * 入库逻辑
 * */
public class StorageInService extends BaseService {

	private ArcStorageInDAO arcStorageInDAO;
	// 业务管理公开接口
	private PubArcBaseInfoService pubArcBaseInfoService;

	/**
	 * 通过系统档案编号查询可入库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowInByXtdabh(String xtdabh)
			throws Exception {
		return pubArcBaseInfoService.findAllowInByXtdabh(xtdabh);
	}

	/**
	 * 通过系统档案编号查询可入库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowInByCwbh(String cwbh) throws Exception {

		int len = cwbh.length();

		switch (len) {
		case 8:// 标准格式：1A1A1001
			break;
		case 10:// 格式：011A1A1001
			cwbh = cwbh.substring(2, len);
			break;
		case 12:// 格式：1-A1-A-1-001
			cwbh = cwbh.replace("-", "");
			break;
		case 15:// 格式：01-1-A1-A-1-001
			cwbh = cwbh.replace("-", "").substring(2, len);
			break;
		default:// 没有找到相应的长度
			return new ArrayList<ArcBaseInfo>();
		}
		return pubArcBaseInfoService.findAllowInByCwbh(cwbh);
	}

	public static void main(String[] args) {
		System.out.println("011A1A1001".substring(2, 10));
	}

	/**
	 * 通过档案编号查询可入库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowInByDabh(String dabh) throws Exception {
		return pubArcBaseInfoService.findAllowInByDabh(dabh);
	}

	/**
	 * 通过流水号查询可入库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowInByLsh(String lsh) throws Exception {
		return pubArcBaseInfoService.findAllowInByLsh(lsh);
	}

	/**
	 * 通过系统档案编号查询 档案基本表
	 * */
	public List<ArcBaseInfo> findArcByXtdabh(String xtdabh) {
		return pubArcBaseInfoService.findByXtdabh(xtdabh);
	}

	/**
	 * 通过系统档案编号查询 入库表
	 * */
	public List<ArcStorageIn> findStorageInByXtdabh(String xtdabh) {
		ArcStorageInExample example = new ArcStorageInExample();
		example.createCriteria().andXtdabhEqualTo(xtdabh);
		return arcStorageInDAO.selectByExample(example);
	}

	/**
	 * 验证并预入库档案
	 * */
	public Object[] checkApplyIn(List<String> sids, SuperUser user)
			throws Exception {
		Object[] ret = new Object[3];
		// 记录时间
		Date date = new Date();
		// 批次号
		String pcid = getPcId();

		for (int i = 0; i < sids.size(); i++) {
			String sid = sids.get(i);
			List<ArcBaseInfo> arclist = pubArcBaseInfoService.findByXtdabh(sid);
			// 正常情况，只有一份档案
			if (arclist.size() == 1) {
				ArcBaseInfo arc = arclist.get(0);
				// 如果状态为已归档 & 已录入，则可以操作
				if (arc.getDazt().equals(ArcStatus.YGD)
						|| arc.getDazt().equals(ArcStatus.YLR_LSDA)) {
					ArcStorageInExample inExample = new ArcStorageInExample();
					inExample.createCriteria().andXtdabhEqualTo(sid);
					List<ArcStorageIn> inlist = arcStorageInDAO
							.selectByExample(inExample);
					// 没有入库记录才可以入库
					if (inlist.size() == 0) {
						// bean转换
						ArcStorageIn in = baseInfoToStorageInBean(arc);
						// 设置入库编号
						in.setRkid(getRkId());
						// 入库批次号
						in.setRkpch(pcid);
						// 档案状态
						in.setDazt(ArcStatus.DSH_RK);
						// 申请人
						in.setSqrid(user.getUserCode());
						in.setSqrxm(user.getUserName());
						in.setSqsj(date);
						in.setCzrid(user.getUserCode());
						in.setCzrxm(user.getUserName());
						// 未合档
						in.setSfhd(SwglStatus.WHD);
						// 插入
						arcStorageInDAO.insert(in);
						// 更新归档表
						pubArcBaseInfoService.updateDaztByXtdabh(sid,
								ArcStatus.DSH_RK);
					} else {
						// 数据异常，无法录入。
						throw new RuntimeException("stoins-数据异常，该档案已存在：" + sid
								+ "-" + sid);
					}
				} else {
					// 档案状态错误
					throw new RuntimeException("stoins-" + sid
							+ "档案状态错误，当前档案状态为："
							+ DBResource.getDaztName(arc.getDazt()) + "-" + sid);
				}
			} else if (arclist.size() == 0) {
				throw new RuntimeException("stoins-" + "未查询到档案，系统编号：" + sid);
			} else {
				throw new RuntimeException("stoins-"
						+ "数据异常，数据库中的系统档案编号重复，请联系管理员，系统档案编号：" + sid + "-" + sid);
			}
		}
		ret[0] = "1";
		Map<String, String> map = new HashMap<String, String>();
		map.put("pch", pcid);
		map.put("sl", sids.size() + "");
		map.put("sqrid", user.getUserCode());
		map.put("sqrxm", user.getUserName());
		map.put("time", date.getTime() + "");
		ret[1] = map;
		return ret;
	}

	/**
	 * 查询批次列表
	 * 
	 * @throws Exception
	 * */
	public Pagination findStorageInByPage(int index, PageToExample example)
			throws Exception {
		return findPageList(index, example, arcStorageInDAO);
	}

	/**
	 * 按照批次号查询预入库档案
	 * */
	public List<ArcStorageIn> findStorageInByPch(String pch) {
		ArcStorageInExample example = new ArcStorageInExample();
		example.createCriteria().andRkpchEqualTo(pch)
				.andDaztEqualTo(ArcStatus.DSH_RK);
		return arcStorageInDAO.selectByExample(example);
	}

	/**
	 * 按批次确认入库
	 * */
	public List<ArcStorageIn> storageInByPc(String pc, SuperUser user)
			throws Exception {
		ArcStorageInExample example = new ArcStorageInExample();
		// 查询该批次，待审核档案
		example.createCriteria().andRkpchEqualTo(pc)
				.andDaztEqualTo(ArcStatus.DSH_RK);
		List<ArcStorageIn> list = arcStorageInDAO.selectByExample(example);
		Date date = new Date();
		if (list.size() == 0) {
			throw new RuntimeException("stoin-未找到可审核的该档案-" + pc);
		}
		for (ArcStorageIn in : list) {
			in.setDazt(ArcStatus.YRK);
			in.setShrid(user.getUserCode());
			in.setShrxm(user.getUserName());
			in.setShsj(date);
			in.setRkrid(user.getUserCode());
			in.setRkrxm(user.getUserName());
			in.setRksj(date);
			// 更新入库表
			arcStorageInDAO.updateByPrimaryKeySelective(in);
			// 更新归档表
			pubArcBaseInfoService.updateRkByXtdabh(in.getXtdabh(), date, pc,user);
		}
		return list;
	}

	/**
	 * 按档案编号入库
	 * */
	public void storageInByXtdabh(List<String> ids, SuperUser user)
			throws Exception {
		Date date = new Date();
		for (String xtdabh : ids) {
			ArcStorageInExample example = new ArcStorageInExample();
			// 查询系统档案编号，待审核档案
			example.createCriteria().andXtdabhEqualTo(xtdabh)
					.andDaztEqualTo(ArcStatus.DSH_RK);
			List<ArcStorageIn> list = arcStorageInDAO.selectByExample(example);
			if (list.size() == 1) {
				ArcStorageIn in = list.get(0);
				in.setDazt(ArcStatus.YRK);
				in.setShrid(user.getUserCode());
				in.setShrxm(user.getUserName());
				in.setShsj(date);
				in.setRkrid(user.getUserCode());
				in.setRkrxm(user.getUserName());
				in.setRksj(date);
				// 更新入库表
				arcStorageInDAO.updateByPrimaryKeySelective(in);
				// 更新归档表
				pubArcBaseInfoService.updateRkByXtdabh(in.getXtdabh(), date,
						in.getRkpch(),user);
			} else if (list.size() == 0) {
				throw new RuntimeException("stoin-未找到可审核的该档案-" + xtdabh);
			} else {
				throw new RuntimeException("stoin-数据异常，查询到相同的系统档案编号-" + xtdabh);
			}
		}

	}

	/**
	 * 按批次退回档案
	 * */
	public List<ArcStorageIn> backInByPc(String pc) throws Exception {
		ArcStorageInExample example = new ArcStorageInExample();
		// 查询该批次，待审核档案
		example.createCriteria().andRkpchEqualTo(pc)
				.andDaztEqualTo(ArcStatus.DSH_RK);
		List<ArcStorageIn> list = arcStorageInDAO.selectByExample(example);
		for (ArcStorageIn in : list) {
			// 删除入库表
			arcStorageInDAO.deleteByPrimaryKey(in.getRkid());
			// 更新归档表
			pubArcBaseInfoService.updateDaztByXtdabh(in.getXtdabh(),
					ArcStatus.YGD);
		}
		return list;
	}

	/**
	 * 按档案编号退回档案
	 * */
	public void backInByXtdabh(List<String> ids) throws Exception {
		for (String xtdabh : ids) {
			ArcStorageInExample example = new ArcStorageInExample();
			// 查询系统档案编号，待审核档案
			example.createCriteria().andXtdabhEqualTo(xtdabh)
					.andDaztEqualTo(ArcStatus.DSH_RK);
			List<ArcStorageIn> list = arcStorageInDAO.selectByExample(example);
			if (list.size() == 0) {
				throw new RuntimeException("stoin-未找到可退回的该档案-" + xtdabh);
			} else {
				for (ArcStorageIn in : list) {
					// 删除入库表
					arcStorageInDAO.deleteByPrimaryKey(in.getRkid());
					// 更新归档表
					pubArcBaseInfoService.updateDaztByXtdabh(in.getXtdabh(),
							ArcStatus.YGD);
				}
			}
		}

	}

	// ==============================================
	private ArcStorageIn baseInfoToStorageInBean(ArcBaseInfo info) {
		ArcStorageIn in = new ArcStorageIn();
		in.setXtdabh(info.getXtdabh());
		in.setCwbh(info.getCwbh());
		in.setDabh(info.getDabh());
		in.setYwlx(info.getYwlx());
		in.setLsh(info.getLsh());
		in.setHpzl(info.getHpzl());
		in.setHphm(info.getHphm());
		in.setXh(info.getXh());
		in.setClsbdh(info.getClsbdh());
		in.setXzqh(info.getXzqh());
		return in;
	}

	/**
	 * 获取入库ID
	 * */
	private String getRkId() throws Exception {
		String seq = arcStorageInDAO.getRKSeq();
		return DateTools.getDateSeqId1("I", StringTools.lpad(seq, 6, "0"));
	}

	/**
	 * 获取入库批次ID
	 * */
	private String getPcId() throws Exception {
		String seq = arcStorageInDAO.getRKPCSeq();
		return DateTools.getDateSeqId2(null, StringTools.lpad(seq, 5, "0"));
	}

	// ======================set & get==============================

	public void setArcStorageInDAO(ArcStorageInDAO arcStorageInDAO) {
		this.arcStorageInDAO = arcStorageInDAO;
	}

	public void setPubArcBaseInfoService(
			PubArcBaseInfoService pubArcBaseInfoService) {
		this.pubArcBaseInfoService = pubArcBaseInfoService;
	}

}
