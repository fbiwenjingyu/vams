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
import com.pd.swgl.dao.ArcStorageOutDAO;
import com.pd.swgl.model.ArcStorageIn;
import com.pd.swgl.model.ArcStorageInExample;
import com.pd.swgl.model.ArcStorageOut;
import com.pd.swgl.model.ArcStorageOutExample;
import com.pd.swgl.utils.SwglStatus;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.PageToExample;
import com.pd.system.framework.Pagination;
import com.pd.system.res.ArcStatus;
import com.pd.system.startup.DBResource;
import com.pd.system.utils.DateTools;
import com.pd.system.utils.StringTools;

public class StorageOutService extends BaseService {

	private ArcStorageOutDAO arcStorageOutDAO;
	private ArcStorageInDAO arcStorageInDAO;
	private PubArcBaseInfoService pubArcBaseInfoService;

	/**
	 * 通过系统档案编号查询可出库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowOutByXtdabh(String xtdabh)
			throws Exception {
		ArcStorageInExample example = new ArcStorageInExample();
		example.createCriteria().andXtdabhEqualTo(xtdabh)
				.andDaztEqualTo(ArcStatus.YRK);
		return arcStorageInDAO.selectByExample(example);
	}

	/**
	 * 通过系统档案编号查询可出库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowOutByCwbh(String cwbh) throws Exception {
		
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
		
		ArcStorageInExample example = new ArcStorageInExample();
		example.createCriteria().andCwbhEqualTo(cwbh)
				.andDaztEqualTo(ArcStatus.YRK);
		return arcStorageInDAO.selectByExample(example);
	}

	/**
	 * 通过档案编号查询可出库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowOutByDabh(String dabh) throws Exception {
		ArcStorageInExample example = new ArcStorageInExample();
		example.createCriteria().andDabhEqualTo(dabh)
				.andDaztEqualTo(ArcStatus.YRK);
		return arcStorageInDAO.selectByExample(example);
	}

	/**
	 * 通过流水号查询可出库档案
	 * 
	 * @throws Exception
	 * */
	public List<ArcBaseInfo> findAllowOutByLsh(String lsh) throws Exception {
		ArcStorageInExample example = new ArcStorageInExample();
		example.createCriteria().andLshEqualTo(lsh)
				.andDaztEqualTo(ArcStatus.YRK);
		return arcStorageInDAO.selectByExample(example);
	}

	/**
	 * 通过系统档案编号查询 档案基本表
	 * */
	public List<ArcBaseInfo> findArcByXtdabh(String xtdabh) {
		return pubArcBaseInfoService.findByXtdabh(xtdabh);
	}

	/**
	 * 通过系统档案编号查询 出库表
	 * */
	public List<ArcStorageOut> findStorageOutByXtdabh(String xtdabh) {
		ArcStorageOutExample example = new ArcStorageOutExample();
		example.createCriteria().andXtdabhEqualTo(xtdabh);
		return arcStorageOutDAO.selectByExample(example);
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
	 * 验证并预出库档案
	 * */
	public Object[] checkApplyOut(List<String> sids, SuperUser user)
			throws Exception {
		Object[] ret = new Object[3];
		// 记录时间
		Date date = new Date();
		// 批次号
		String pcid = getPcId();

		for (int i = 0; i < sids.size(); i++) {
			String sid = sids.get(i);
			// 查询入库表
			ArcStorageInExample inExample = new ArcStorageInExample();
			inExample.createCriteria().andXtdabhEqualTo(sid)
					.andDaztEqualTo(ArcStatus.YRK);
			List<ArcStorageIn> inlist = arcStorageInDAO
					.selectByExample(inExample);
			// 正常情况，只有一份档案
			if (inlist.size() == 1) {
				ArcStorageIn in = inlist.get(0);
				ArcStorageOutExample outExample = new ArcStorageOutExample();
				outExample.createCriteria().andXtdabhEqualTo(sid);
				List<ArcStorageOut> outlist = arcStorageOutDAO
						.selectByExample(outExample);
				// 没有入库记录才可以入库
				if (outlist.size() == 0) {
					// bean转换
					ArcStorageOut out = storageInToStorageOutBean(in);
					// 设置入库编号
					out.setCkid(getCkId());
					// 入库批次号
					out.setCkpch(pcid);
					// 档案状态
					out.setDazt(ArcStatus.DSH_CK);
					// 申请人
					out.setSqrid(user.getUserCode());
					out.setSqrxm(user.getUserName());
					out.setSqsj(date);
					out.setCzrid(user.getUserCode());
					out.setCzrxm(user.getUserName());
					// 是否合档
					out.setSfhd(in.getSfhd());
					// 插入
					arcStorageOutDAO.insert(out);
					// 更新入库表，将其状态改为出库待审核。
					in.setDazt(ArcStatus.DSH_CK);
					arcStorageInDAO.updateByPrimaryKeySelective(in);
					// 更新归档表
					pubArcBaseInfoService.updateDaztByXtdabh(sid,
							ArcStatus.DSH_CK);
				} else {
					// 数据异常，无法录入。
					throw new RuntimeException("stoins-数据异常，该档案已存在：" + sid
							+ "-" + sid);
				}
			} else if (inlist.size() == 0) {
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
	 * 查询出库批次列表
	 * 
	 * @throws Exception
	 * */
	public Pagination findStorageOutByPage(int index, PageToExample example)
			throws Exception {
		return findPageList(index, example, arcStorageOutDAO);
	}

	/**
	 * 按照批次号查询预出库档案
	 * */
	public List<ArcStorageOut> findStorageOutByPch(String pch) {
		ArcStorageOutExample example = new ArcStorageOutExample();
		example.createCriteria().andCkpchEqualTo(pch)
				.andDaztEqualTo(ArcStatus.DSH_CK);
		return arcStorageOutDAO.selectByExample(example);
	}

	/**
	 * 按批次确认出
	 * */
	public List<ArcStorageOut> storageOutByPc(String pc, SuperUser user)
			throws Exception {
		ArcStorageOutExample example = new ArcStorageOutExample();
		// 查询该批次，待审核档案
		example.createCriteria().andCkpchEqualTo(pc)
				.andDaztEqualTo(ArcStatus.DSH_CK);
		List<ArcStorageOut> list = arcStorageOutDAO.selectByExample(example);
		Date date = new Date();
		if (list.size() == 0) {
			throw new RuntimeException("stoin-未找到可审核的该档案-" + pc);
		}
		for (ArcStorageOut out : list) {
			executeStorageOut(out, user, date);
		}
		return list;
	}

	/**
	 * 按档案编号出库
	 * */
	public void storageOutByXtdabh(List<String> ids, SuperUser user)
			throws Exception {
		Date date = new Date();
		for (String xtdabh : ids) {
			ArcStorageOutExample example = new ArcStorageOutExample();
			// 查询系统档案编号，待审核档案
			example.createCriteria().andXtdabhEqualTo(xtdabh)
					.andDaztEqualTo(ArcStatus.DSH_CK);
			List<ArcStorageOut> list = arcStorageOutDAO
					.selectByExample(example);
			if (list.size() == 1) {
				ArcStorageOut out = list.get(0);
				// 处理出库
				executeStorageOut(out, user, date);
			} else if (list.size() == 0) {
				throw new RuntimeException("stoin-未找到可审核的该档案-" + xtdabh);
			} else {
				throw new RuntimeException("stoin-数据异常，查询到相同的系统档案编号-" + xtdabh);
			}
		}

	}

	// 处理出库
	private void executeStorageOut(ArcStorageOut out, SuperUser user, Date date)
			throws Exception {
		out.setDazt(ArcStatus.YCK);
		out.setShrid(user.getUserCode());
		out.setShrxm(user.getUserName());
		out.setShsj(date);
		out.setCkrid(user.getUserCode());
		out.setCkrxm(user.getUserName());
		out.setCksj(date);
		// 更新出库表
		arcStorageOutDAO.updateByPrimaryKeySelective(out);
		// 删除入库表信息
		ArcStorageInExample inExample = new ArcStorageInExample();
		inExample.createCriteria().andXtdabhEqualTo(out.getXtdabh());
		arcStorageInDAO.deleteByExample(inExample);
		// 更新归档表
		pubArcBaseInfoService.updateCkByXtdabh(out.getXtdabh(), date,
				out.getCkpch(),user);
		// *******************刷新储位************************
		out.getCwbh();

	}

	/**
	 * 按批次退回预出库档案
	 * */
	public List<ArcStorageOut> backOutByPc(String pc) throws Exception {
		ArcStorageOutExample example = new ArcStorageOutExample();
		// 查询该批次，待审核档案
		example.createCriteria().andCkpchEqualTo(pc)
				.andDaztEqualTo(ArcStatus.DSH_CK);
		List<ArcStorageOut> list = arcStorageOutDAO.selectByExample(example);
		for (ArcStorageOut out : list) {
			// 删除出库表
			arcStorageOutDAO.deleteByPrimaryKey(out.getCkid());
			// 更新入库表，修改回已入库状态
			ArcStorageInExample inExample = new ArcStorageInExample();
			inExample.createCriteria().andXtdabhEqualTo(out.getXtdabh());
			ArcStorageIn in = new ArcStorageIn();
			in.setDazt(ArcStatus.YRK);
			arcStorageInDAO.updateByExampleSelective(in, inExample);
			// 更新归档表
			pubArcBaseInfoService.updateDaztByXtdabh(out.getXtdabh(),
					ArcStatus.YRK);
		}
		return list;
	}

	/**
	 * 按系统档案编号退回预出库档案
	 * */
	public void backOutByXtdabh(List<String> ids) throws Exception {
		for (String xtdabh : ids) {
			ArcStorageOutExample example = new ArcStorageOutExample();
			// 查询系统档案编号，待审核档案
			example.createCriteria().andXtdabhEqualTo(xtdabh)
					.andDaztEqualTo(ArcStatus.DSH_CK);
			List<ArcStorageOut> list = arcStorageOutDAO
					.selectByExample(example);
			if (list.size() == 0) {
				throw new RuntimeException("stoin-未找到可退回的该档案-" + xtdabh);
			} else {
				for (ArcStorageOut out : list) {
					// 删除出库表
					arcStorageOutDAO.deleteByPrimaryKey(out.getCkid());
					// 更新入库表，修改回已入库状态
					ArcStorageInExample inExample = new ArcStorageInExample();
					inExample.createCriteria()
							.andXtdabhEqualTo(out.getXtdabh());
					ArcStorageIn in = new ArcStorageIn();
					in.setDazt(ArcStatus.YRK);
					arcStorageInDAO.updateByExampleSelective(in, inExample);
					// 更新归档表
					pubArcBaseInfoService.updateDaztByXtdabh(out.getXtdabh(),
							ArcStatus.YRK);
				}
			}
		}

	}

	// ==============================================
	private ArcStorageOut storageInToStorageOutBean(ArcStorageIn in) {
		ArcStorageOut out = new ArcStorageOut();
		out.setXtdabh(in.getXtdabh());
		out.setCwbh(in.getCwbh());
		out.setDabh(in.getDabh());
		out.setYwlx(in.getYwlx());
		out.setLsh(in.getLsh());
		out.setHpzl(in.getHpzl());
		out.setHphm(in.getHphm());
		out.setXh(in.getXh());
		out.setClsbdh(in.getClsbdh());
		out.setXzqh(in.getXzqh());
		return out;
	}

	/**
	 * 获取入库ID
	 * */
	private String getCkId() throws Exception {
		String seq = arcStorageOutDAO.getCKSeq();
		return DateTools.getDateSeqId1("O", StringTools.lpad(seq, 6, "0"));
	}

	/**
	 * 获取入库批次ID
	 * */
	private String getPcId() throws Exception {
		String seq = arcStorageOutDAO.getCKPCSeq();
		return DateTools.getDateSeqId2(null, StringTools.lpad(seq, 5, "0"));
	}

	// =====================================================
	public void setArcStorageOutDAO(ArcStorageOutDAO arcStorageOutDAO) {
		this.arcStorageOutDAO = arcStorageOutDAO;
	}

	public void setArcStorageInDAO(ArcStorageInDAO arcStorageInDAO) {
		this.arcStorageInDAO = arcStorageInDAO;
	}

	public void setPubArcBaseInfoService(
			PubArcBaseInfoService pubArcBaseInfoService) {
		this.pubArcBaseInfoService = pubArcBaseInfoService;
	}

}
