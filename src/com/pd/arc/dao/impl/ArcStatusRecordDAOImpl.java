package com.pd.arc.dao.impl;

import com.pd.arc.model.ArcStatusRecord;
import com.pd.arc.model.ArcStatusRecordExample;
import com.pd.system.framework.PageToExample;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ArcStatusRecordDAOImpl extends SqlMapClientDaoSupport implements ArcStatusRecordDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public ArcStatusRecordDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public void insert(ArcStatusRecord record) {
        getSqlMapClientTemplate().insert("ARC_STATUS_RECORD.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public int updateByPrimaryKey(ArcStatusRecord record) {
        int rows = getSqlMapClientTemplate().update("ARC_STATUS_RECORD.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public int updateByPrimaryKeySelective(ArcStatusRecord record) {
        int rows = getSqlMapClientTemplate().update("ARC_STATUS_RECORD.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public List selectByExample(ArcStatusRecordExample example) {
        List list = getSqlMapClientTemplate().queryForList("ARC_STATUS_RECORD.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public ArcStatusRecord selectByPrimaryKey(String id) {
        ArcStatusRecord key = new ArcStatusRecord();
        key.setId(id);
        ArcStatusRecord record = (ArcStatusRecord) getSqlMapClientTemplate().queryForObject("ARC_STATUS_RECORD.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public int deleteByExample(ArcStatusRecordExample example) {
        int rows = getSqlMapClientTemplate().delete("ARC_STATUS_RECORD.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public int deleteByPrimaryKey(String id) {
        ArcStatusRecord key = new ArcStatusRecord();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("ARC_STATUS_RECORD.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public int countByExample(ArcStatusRecordExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("ARC_STATUS_RECORD.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public int updateByExampleSelective(ArcStatusRecord record, ArcStatusRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ARC_STATUS_RECORD.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public int updateByExample(ArcStatusRecord record, ArcStatusRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ARC_STATUS_RECORD.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    private static class UpdateByExampleParms extends ArcStatusRecordExample {
        private Object record;

        public UpdateByExampleParms(Object record, ArcStatusRecordExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

	@Override
	public List selectByExampleToPage(PageToExample example) {
		// TODO Auto-generated method stub
		return null;
	}
}