package com.pd.right.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pd.right.model.Role;
import com.pd.right.model.RoleExample;

public class RoleDAOImpl extends SqlMapClientDaoSupport implements RoleDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public RoleDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public void insert(Role record) {
        getSqlMapClientTemplate().insert("ROLE.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public int updateByPrimaryKey(Role record) {
        int rows = getSqlMapClientTemplate().update("ROLE.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public int updateByPrimaryKeySelective(Role record) {
        int rows = getSqlMapClientTemplate().update("ROLE.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public List selectByExample(RoleExample example) {
        List list = getSqlMapClientTemplate().queryForList("ROLE.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public Role selectByPrimaryKey(Long roleid) {
        Role key = new Role();
        key.setRoleid(roleid);
        Role record = (Role) getSqlMapClientTemplate().queryForObject("ROLE.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public int deleteByExample(RoleExample example) {
        int rows = getSqlMapClientTemplate().delete("ROLE.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public int deleteByPrimaryKey(Long roleid) {
        Role key = new Role();
        key.setRoleid(roleid);
        int rows = getSqlMapClientTemplate().delete("ROLE.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public int countByExample(RoleExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("ROLE.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public int updateByExampleSelective(Role record, RoleExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ROLE.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    public int updateByExample(Role record, RoleExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ROLE.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table ROLE
     *
     * @abatorgenerated Mon Jun 24 13:28:44 CST 2013
     */
    private static class UpdateByExampleParms extends RoleExample {
        private Object record;

        public UpdateByExampleParms(Object record, RoleExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List<Role> selectByExampleToPage(RoleExample example) {
		 List<Role> list = getSqlMapClientTemplate().queryForList("ROLE.abatorgenerated_selectByExampleToPage", example);
	     return list;
	}

	@Override
	public Long getSeq() {
		return  (Long) getSqlMapClientTemplate().queryForObject("ROLE.getSeq", null);
	}
}