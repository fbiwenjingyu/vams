package com.pd.wjyw.dao.impl;

import com.pd.system.framework.BaseDAO;
import com.pd.wjyw.model.YcTypePaper;
import com.pd.wjyw.model.YcTypePaperExample;
import java.util.List;

public interface YcTypePaperDAO extends BaseDAO{

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	void insert(YcTypePaper record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	int updateByPrimaryKey(YcTypePaper record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	int updateByPrimaryKeySelective(YcTypePaper record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	List selectByExample(YcTypePaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	YcTypePaper selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	int deleteByExample(YcTypePaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	int countByExample(YcTypePaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	int updateByExampleSelective(YcTypePaper record, YcTypePaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_TYPE_PAPER
	 * @abatorgenerated  Tue Aug 05 10:19:49 CST 2014
	 */
	int updateByExample(YcTypePaper record, YcTypePaperExample example);
}