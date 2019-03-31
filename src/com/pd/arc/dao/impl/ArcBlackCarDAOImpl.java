package com.pd.arc.dao.impl;

import com.pd.arc.model.ArcBlackCar;
import com.pd.arc.model.ArcBlackCarExample;
import com.pd.system.framework.PageToExample;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ArcBlackCarDAOImpl extends SqlMapClientDaoSupport implements ArcBlackCarDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    public ArcBlackCarDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    public void insert(ArcBlackCar record) {
        getSqlMapClientTemplate().insert("ARC_BLACK_CAR.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    public List selectByExample(ArcBlackCarExample example) {
        List list = getSqlMapClientTemplate().queryForList("ARC_BLACK_CAR.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    public int deleteByExample(ArcBlackCarExample example) {
        int rows = getSqlMapClientTemplate().delete("ARC_BLACK_CAR.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    public int countByExample(ArcBlackCarExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("ARC_BLACK_CAR.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    public int updateByExampleSelective(ArcBlackCar record, ArcBlackCarExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ARC_BLACK_CAR.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    public int updateByExample(ArcBlackCar record, ArcBlackCarExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ARC_BLACK_CAR.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    private static class UpdateByExampleParms extends ArcBlackCarExample {
        private Object record;

        public UpdateByExampleParms(Object record, ArcBlackCarExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }

    @Override
	public List selectByExampleToPage(PageToExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"ARC_BLACK_CAR.abatorgenerated_selectByExampleToPage", example);
		return list;
	}
}