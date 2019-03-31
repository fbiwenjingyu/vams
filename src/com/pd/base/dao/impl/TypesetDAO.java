package com.pd.base.dao.impl;

import com.pd.base.model.Typeset;
import com.pd.base.model.TypesetExample;
import java.util.List;

public interface TypesetDAO {
    /**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	void insert(Typeset record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	int updateByPrimaryKey(Typeset record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	int updateByPrimaryKeySelective(Typeset record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	List selectByExample(TypesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	Typeset selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	int deleteByExample(TypesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	int countByExample(TypesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	int updateByExampleSelective(Typeset record, TypesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TYPESET
	 * @abatorgenerated  Thu Jul 04 14:07:35 CST 2013
	 */
	int updateByExample(Typeset record, TypesetExample example);

	List<Typeset> selectByExampleToPage(TypesetExample example);
}