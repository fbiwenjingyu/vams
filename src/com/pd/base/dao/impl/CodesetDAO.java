package com.pd.base.dao.impl;

import com.pd.base.model.Codeset;
import com.pd.base.model.CodesetExample;
import java.util.List;

public interface CodesetDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	void insert(Codeset record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	int updateByPrimaryKey(Codeset record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	int updateByPrimaryKeySelective(Codeset record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	List selectByExample(CodesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	Codeset selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	int deleteByExample(CodesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	int countByExample(CodesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	int updateByExampleSelective(Codeset record, CodesetExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table CODESET
	 * @abatorgenerated  Wed Jun 26 15:31:53 CST 2013
	 */
	int updateByExample(Codeset record, CodesetExample example);

	List<Codeset> selectByExampleToPage(CodesetExample example);
}