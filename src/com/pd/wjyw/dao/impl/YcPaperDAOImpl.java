package com.pd.wjyw.dao.impl;

import com.pd.system.framework.PageToExample;
import com.pd.wjyw.model.YcPaper;
import com.pd.wjyw.model.YcPaperExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class YcPaperDAOImpl extends SqlMapClientDaoSupport implements YcPaperDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public YcPaperDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public void insert(YcPaper record) {
		getSqlMapClientTemplate().insert("YC_PAPER.abatorgenerated_insert",
				record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public int updateByPrimaryKey(YcPaper record) {
		int rows = getSqlMapClientTemplate().update(
				"YC_PAPER.abatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public int updateByPrimaryKeySelective(YcPaper record) {
		int rows = getSqlMapClientTemplate().update(
				"YC_PAPER.abatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public List selectByExample(YcPaperExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"YC_PAPER.abatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public YcPaper selectByPrimaryKey(String id) {
		YcPaper key = new YcPaper();
		key.setId(id);
		YcPaper record = (YcPaper) getSqlMapClientTemplate().queryForObject(
				"YC_PAPER.abatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public int deleteByExample(YcPaperExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"YC_PAPER.abatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public int deleteByPrimaryKey(String id) {
		YcPaper key = new YcPaper();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"YC_PAPER.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public int countByExample(YcPaperExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"YC_PAPER.abatorgenerated_countByExample", example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public int updateByExampleSelective(YcPaper record, YcPaperExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"YC_PAPER.abatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	public int updateByExample(YcPaper record, YcPaperExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"YC_PAPER.abatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	private static class UpdateByExampleParms extends YcPaperExample {
		private Object record;

		public UpdateByExampleParms(Object record, YcPaperExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	/**
	 * 查询分页
	 */
	@Override
	public List selectByExampleToPage(PageToExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"YC_PAPER.abatorgenerated_selectByExampleToPage", example);
		return list;
	}
}