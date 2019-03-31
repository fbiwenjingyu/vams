package com.pd.right.dao.impl;

import com.pd.right.model.Dept;
import com.pd.right.model.DeptExample;
import java.util.List;

public interface DeptDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    void insert(Dept record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    int updateByPrimaryKey(Dept record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    int updateByPrimaryKeySelective(Dept record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    List selectByExample(DeptExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    Dept selectByPrimaryKey(String deptcode);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    int deleteByExample(DeptExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    int deleteByPrimaryKey(String deptcode);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    int countByExample(DeptExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    int updateByExampleSelective(Dept record, DeptExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    int updateByExample(Dept record, DeptExample example);

	List<Dept> selectByExampleToPage(DeptExample example);
}