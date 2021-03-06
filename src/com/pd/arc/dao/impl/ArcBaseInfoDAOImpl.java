package com.pd.arc.dao.impl;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcBaseInfoSuper;
import com.pd.system.framework.PageToExample;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ArcBaseInfoDAOImpl extends SqlMapClientDaoSupport implements
		ArcBaseInfoDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public ArcBaseInfoDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public void insert(ArcBaseInfo record) {
		getSqlMapClientTemplate().insert(
				"ARC_BASE_INFO.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public int updateByPrimaryKey(ArcBaseInfo record) {
		int rows = getSqlMapClientTemplate().update(
				"ARC_BASE_INFO.abatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public int updateByPrimaryKeySelective(ArcBaseInfo record) {
		int rows = getSqlMapClientTemplate().update(
				"ARC_BASE_INFO.abatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public List selectByExample(ArcBaseInfoExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.abatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public ArcBaseInfo selectByPrimaryKey(String id) {
		ArcBaseInfo key = new ArcBaseInfo();
		key.setId(id);
		ArcBaseInfo record = (ArcBaseInfo) getSqlMapClientTemplate()
				.queryForObject(
						"ARC_BASE_INFO.abatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public int deleteByExample(ArcBaseInfoExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"ARC_BASE_INFO.abatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public int deleteByPrimaryKey(String id) {
		ArcBaseInfo key = new ArcBaseInfo();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"ARC_BASE_INFO.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public int countByExample(ArcBaseInfoExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ARC_BASE_INFO.abatorgenerated_countByExample", example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public int updateByExampleSelective(ArcBaseInfo record,
			ArcBaseInfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate()
				.update("ARC_BASE_INFO.abatorgenerated_updateByExampleSelective",
						parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds
	 * to the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	public int updateByExample(ArcBaseInfo record, ArcBaseInfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"ARC_BASE_INFO.abatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to
	 * the database table ARC_BASE_INFO
	 * 
	 * @abatorgenerated Fri Aug 01 13:50:26 CST 2014
	 */
	private static class UpdateByExampleParms extends ArcBaseInfoExample {
		private Object record;

		public UpdateByExampleParms(Object record, ArcBaseInfoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
	public List<ArcBaseInfoSuper> getArcCheckNum(Map map) {
		List<ArcBaseInfoSuper> list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.getArcCheckNum", map);
		return list;
	}

	@Override
	public List selectByExampleToPage(PageToExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.abatorgenerated_selectByExampleToPage", example);
		return list;
	}
	
	@Override
	public List selectByExampleToPageOfQuerySh(ArcBaseInfoExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.abatorgenerated_selectByExampleToPageOfQuerySh", example);
		return list;
	}
	
	@Override
	public List<ArcBaseInfoSuper> getGdArcNum(Map map) {
		List<ArcBaseInfoSuper> list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.getGdArcNum", map);
		return list;
	}

	@Override
	public List<ArcBaseInfoSuper> getSmArcNum(Map map) {
		List<ArcBaseInfoSuper> list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.getSmArcNum", map);
		return list;
	}
	
	@Override
	public List<ArcBaseInfoSuper> getShArcNum(Map map) {
		List<ArcBaseInfoSuper> list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.getShArcNum", map);
		return list;
	}
	
	@Override
	public List<ArcBaseInfoSuper> getRkArcNum(Map map) {
		List<ArcBaseInfoSuper> list = getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.getRkArcNum", map);
		return list;
	}
	
	@Override
	public int getWeekArcNum(Map map) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ARC_BASE_INFO.getWeekArcNum", map);
		return count.intValue();
	}

	@Override
	public List<ArcBaseInfo> findMaxCwbhArcByGeid(String geid) {
		return getSqlMapClientTemplate().queryForList(
				"ARC_BASE_INFO.select_max_cwbh_arc_by_geid", geid);
	}

	@Override
	public Integer findUsableCwxh(String geid) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ARC_BASE_INFO.get_usable_cwxh", geid);
	}
}