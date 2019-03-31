package com.pd.arc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.arc.dao.impl.ArcBaseInfoDAO;
import com.pd.arc.dao.impl.ArcCombInfoDAO;
import com.pd.arc.dao.impl.ArcPicInfoDAO;
import com.pd.arc.dao.impl.ArcStatusRecordDAO;
import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcCombInfo;
import com.pd.arc.model.ArcStatusRecord;
import com.pd.right.model.SuperUser;
import com.pd.swgl.utils.SwglStatus;
import com.pd.system.framework.BaseService;
import com.pd.system.res.ArcStatus;

/**
 * 业务管理-内部接口层
 * */
public class PubArcBaseInfoService extends BaseService {
	private ArcBaseInfoDAO arcBaseInfoDAO;
	private ArcPicInfoDAO arcPicInfoDAO;
	private ArcCombInfoDAO arcCombInfoDAO;
	private ArcStatusRecordDAO arcStatusRecordDAO;
	private JdbcTemplate jdbcTemplate;
	private JdbcTemplate jdbcTemplate1;
	private static final String SHWTG = "N";// 审核未通过

	public void setArcBaseInfoDAO(ArcBaseInfoDAO arcBaseInfoDAO) {
		this.arcBaseInfoDAO = arcBaseInfoDAO;
	}

	public void setArcPicInfoDAO(ArcPicInfoDAO arcPicInfoDAO) {
		this.arcPicInfoDAO = arcPicInfoDAO;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setJdbcTemplate1(JdbcTemplate jdbcTemplate1) {
		this.jdbcTemplate1 = jdbcTemplate1;
	}

	public void setArcStatusRecordDAO(ArcStatusRecordDAO arcStatusRecordDAO) {
		this.arcStatusRecordDAO = arcStatusRecordDAO;
	}

	public void setArcCombInfoDAO(ArcCombInfoDAO arcCombInfoDAO) {
		this.arcCombInfoDAO = arcCombInfoDAO;
	}

	/**
	 * 根据系统档案编号获取档案
	 * 
	 * @param XTDABH
	 *            系统档案编号
	 * @return
	 */
	public List getArcBaseInfoByXTDABH(String XTDABH) {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		example.createCriteria().andXtdabhEqualTo(XTDABH);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 根据系统档案编号修改档案
	 * 
	 * @param arcBaseInfo
	 */
	public void updateArcBaseInfoByXTDABH(ArcBaseInfo arcBaseInfo) {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		example.createCriteria().andXtdabhEqualTo(arcBaseInfo.getXtdabh());
		arcBaseInfoDAO.updateByExampleSelective(arcBaseInfo, example);
	}

	/**
	 * 通过系统档案编号查询档案
	 * */
	public List<ArcBaseInfo> findByXtdabh(String xtdabh) {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		example.createCriteria().andXtdabhEqualTo(xtdabh);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 通过系统档案编号查询 已归档，已录入的档案。
	 * */
	public List<ArcBaseInfo> findAllowInByXtdabh(String xtdabh)
			throws Exception {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		// 查询已归档，已录入的档案
		List<String> tj = new ArrayList<String>(2);
		tj.add(ArcStatus.YGD);
		tj.add(ArcStatus.YLR_LSDA);
		example.createCriteria().andXtdabhEqualTo(xtdabh).andDaztIn(tj);
		return arcBaseInfoDAO.selectByExample(example);
	}
	
	/**
	 * 通过系统档案编号查询 已归档，已录入的档案。
	 * */
	public List<ArcBaseInfo> findAllowInByCwbh(String cwbh)
			throws Exception {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		// 查询已归档，已录入的档案
		List<String> tj = new ArrayList<String>(2);
		tj.add(ArcStatus.YGD);
		tj.add(ArcStatus.YLR_LSDA);
		example.createCriteria().andCwbhEqualTo(cwbh).andCwyxEqualTo("1").andDaztIn(tj);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 通过档案编号查询已归档档案
	 * */
	public List<ArcBaseInfo> findAllowInByDabh(String dabh) throws Exception {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		// 查询已归档，已录入的档案
		List<String> tj = new ArrayList<String>(2);
		tj.add(ArcStatus.YGD);
		tj.add(ArcStatus.YLR_LSDA);
		example.createCriteria().andDabhEqualTo(dabh).andDaztIn(tj);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 通过流水号查询已归档档案
	 * */
	public List<ArcBaseInfo> findAllowInByLsh(String lsh) throws Exception {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		// 查询已归档，已录入的档案
		List<String> tj = new ArrayList<String>(2);
		tj.add(ArcStatus.YGD);
		tj.add(ArcStatus.YLR_LSDA);
		example.createCriteria().andLshEqualTo(lsh).andDaztIn(tj);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 更新档案状态，通过系统编号
	 * */
	public void updateDaztByXtdabh(String xtdabh, String dazt) throws Exception {
		ArcBaseInfo info = new ArcBaseInfo();
		info.setXtdabh(xtdabh);
		info.setDazt(dazt);
		// 更新
		ArcBaseInfoExample infoExample = new ArcBaseInfoExample();
		infoExample.createCriteria().andXtdabhEqualTo(xtdabh);
		arcBaseInfoDAO.updateByExampleSelective(info, infoExample);
	}

	/**
	 * 档案入库，数据变更
	 * 
	 * @param xtdabh
	 *            系统编号
	 * @param date
	 *            日期
	 * @param pch
	 *            批次号
	 * */
	public void updateRkByXtdabh(String xtdabh, Date date, String pch,SuperUser user)
			throws Exception {
		ArcBaseInfo info = new ArcBaseInfo();
		info.setXtdabh(xtdabh);
		// 已入库状态
		info.setDazt(ArcStatus.YRK);
		// 已入库标记
		info.setKfbj(SwglStatus.TAG_YRK);
		// 入库日期
		info.setBjrq(date);
		// 批次号
		info.setPc(pch);
		// 更新
		ArcBaseInfoExample infoExample = new ArcBaseInfoExample();
		infoExample.createCriteria().andXtdabhEqualTo(xtdabh);
		arcBaseInfoDAO.updateByExampleSelective(info, infoExample);
		
		insertStatueRecord(info,user,"rk");
	}

	private void insertStatueRecord(ArcBaseInfo info, SuperUser user,String type) {
		ArcStatusRecord record = new ArcStatusRecord();
		record.setId(UUID.randomUUID().toString());
		record.setXtdabh(info.getXtdabh());
		record.setCzr(user.getUserCode());
		record.setCzrmc(user.getUserName());
		record.setCzsj(new Date());
		record.setStatusCode(info.getDazt());
		String bz = "";
		if("rk".equals(type)){
			bz = "档案入库";
		}else if("ck".equals(type)){
			bz = "档案出库";
		}
		
		record.setBz(bz);
		arcStatusRecordDAO.insert(record );
		
	}

	/**
	 * 档案出库，数据变更
	 * 
	 * @param xtdabh
	 *            系统编号
	 * @param date
	 *            日期
	 * @param pch
	 *            批次号
	 * */
	public void updateCkByXtdabh(String xtdabh, Date date, String pch,SuperUser user)
			throws Exception {
		ArcBaseInfo info = new ArcBaseInfo();
		info.setXtdabh(xtdabh);
		// 已入库状态
		info.setDazt(ArcStatus.YCK);
		// 已入库标记
		info.setKfbj(SwglStatus.TAG_YCK);
		// 出库日期
		info.setBjrq(date);
		// 批次号
		info.setPc(pch);
		//将储位编号设置为无效
		info.setCwyx("0");
		// 更新
		ArcBaseInfoExample infoExample = new ArcBaseInfoExample();
		infoExample.createCriteria().andXtdabhEqualTo(xtdabh);
		arcBaseInfoDAO.updateByExampleSelective(info, infoExample);
		
		insertStatueRecord(info,user,"ck");
	}

	
	/**
	 * 条件查询
	 * */
	public List<ArcBaseInfo> findByArcExample(ArcBaseInfoExample example){
		return arcBaseInfoDAO.selectByExample(example);
	}
	
	/**
	 * 将档案放入档案合并备份表
	 * */
	public void combArcBaseInfo(ArcBaseInfo mainArc,ArcBaseInfo baseInfo,SuperUser user,Date date)throws Exception{
		ArcCombInfo combInfo = new ArcCombInfo(baseInfo, mainArc, user, date);
		//出入合档表
		arcCombInfoDAO.insert(combInfo);
		//删除归档表
		arcBaseInfoDAO.deleteByPrimaryKey(baseInfo.getId());
	}
	//=========================================================
}
