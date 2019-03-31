package com.pd.swgl.dao;

import com.pd.swgl.model.ArcStorageComb;
import com.pd.swgl.model.ArcStorageCombExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ArcStorageCombDAOImpl extends SqlMapClientDaoSupport implements ArcStorageCombDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public ArcStorageCombDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public void insert(ArcStorageComb record) {
		getSqlMapClientTemplate().insert(
				"ARC_STORAGE_COMB.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public int updateByPrimaryKey(ArcStorageComb record) {
		int rows = getSqlMapClientTemplate().update(
				"ARC_STORAGE_COMB.abatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public int updateByPrimaryKeySelective(ArcStorageComb record) {
		int rows = getSqlMapClientTemplate().update(
				"ARC_STORAGE_COMB.abatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public List selectByExample(ArcStorageCombExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"ARC_STORAGE_COMB.abatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public ArcStorageComb selectByPrimaryKey(String hdid) {
		ArcStorageComb key = new ArcStorageComb();
		key.setHdid(hdid);
		ArcStorageComb record = (ArcStorageComb) getSqlMapClientTemplate()
				.queryForObject(
						"ARC_STORAGE_COMB.abatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public int deleteByExample(ArcStorageCombExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"ARC_STORAGE_COMB.abatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public int deleteByPrimaryKey(String hdid) {
		ArcStorageComb key = new ArcStorageComb();
		key.setHdid(hdid);
		int rows = getSqlMapClientTemplate().delete(
				"ARC_STORAGE_COMB.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public int countByExample(ArcStorageCombExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ARC_STORAGE_COMB.abatorgenerated_countByExample", example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public int updateByExampleSelective(ArcStorageComb record,
			ArcStorageCombExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"ARC_STORAGE_COMB.abatorgenerated_updateByExampleSelective",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	public int updateByExample(ArcStorageComb record,
			ArcStorageCombExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"ARC_STORAGE_COMB.abatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table ARC_STORAGE_COMB
	 * @abatorgenerated  Fri Aug 15 16:53:01 CST 2014
	 */
	private static class UpdateByExampleParms extends ArcStorageCombExample {
		private Object record;

		public UpdateByExampleParms(Object record, ArcStorageCombExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}