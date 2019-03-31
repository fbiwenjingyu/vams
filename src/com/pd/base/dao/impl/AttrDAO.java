package com.pd.base.dao.impl;

import com.pd.base.model.Attr;
import com.pd.base.model.AttrExample;
import java.util.List;

public interface AttrDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    void insert(Attr record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    int updateByPrimaryKey(Attr record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    int updateByPrimaryKeySelective(Attr record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    List selectByExample(AttrExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    Attr selectByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    int deleteByExample(AttrExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    int countByExample(AttrExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    int updateByExampleSelective(Attr record, AttrExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ATTR
     *
     * @abatorgenerated Tue Jun 25 16:47:15 CST 2013
     */
    int updateByExample(Attr record, AttrExample example);

	List<Attr> selectByExampleToPage(AttrExample example);
}