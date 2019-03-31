package com.pd.right.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pd.right.model.RoleRes;
import com.pd.right.model.RoleResExample;
import com.pd.right.model.RoleResources;

public class RoleResDAOImpl extends SqlMapClientDaoSupport implements RoleResDAO {

    /**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	public RoleResDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	public void insert(RoleRes record) {
		getSqlMapClientTemplate().insert("ROLE_RES.abatorgenerated_insert",
				record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	public List selectByExample(RoleResExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"ROLE_RES.abatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	public int deleteByExample(RoleResExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"ROLE_RES.abatorgenerated_deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	public int countByExample(RoleResExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ROLE_RES.abatorgenerated_countByExample", example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	public int updateByExampleSelective(RoleRes record, RoleResExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"ROLE_RES.abatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	public int updateByExample(RoleRes record, RoleResExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"ROLE_RES.abatorgenerated_updateByExample", parms);
		return rows;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table ROLE_RES
	 * @abatorgenerated  Thu Jul 04 09:08:40 CST 2013
	 */
	private static class UpdateByExampleParms extends RoleResExample {
		private Object record;

		public UpdateByExampleParms(Object record, RoleResExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
	public List<RoleResources> selectByRole(Map map) {
		return getSqlMapClientTemplate().queryForList("ROLE_RES.getResByRole", map);
	}
	
	@Override
	public List<RoleResources> selectByFirstRole(Map map) {
		return getSqlMapClientTemplate().queryForList("ROLE_RES.getFirstResByRole", map);
	}
}