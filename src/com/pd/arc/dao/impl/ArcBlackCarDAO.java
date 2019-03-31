package com.pd.arc.dao.impl;

import com.pd.arc.model.ArcBlackCar;
import com.pd.arc.model.ArcBlackCarExample;
import com.pd.system.framework.BaseDAO;

import java.util.List;

public interface ArcBlackCarDAO extends BaseDAO{
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    void insert(ArcBlackCar record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    List selectByExample(ArcBlackCarExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    int deleteByExample(ArcBlackCarExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    int countByExample(ArcBlackCarExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    int updateByExampleSelective(ArcBlackCar record, ArcBlackCarExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_BLACK_CAR
     *
     * @abatorgenerated Mon Aug 11 11:30:45 CST 2014
     */
    int updateByExample(ArcBlackCar record, ArcBlackCarExample example);
}