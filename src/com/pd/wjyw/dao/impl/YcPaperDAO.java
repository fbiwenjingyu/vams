package com.pd.wjyw.dao.impl;

import com.pd.system.framework.BaseDAO;
import com.pd.wjyw.model.YcPaper;
import com.pd.wjyw.model.YcPaperExample;
import java.util.List;

public interface YcPaperDAO extends BaseDAO{

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	void insert(YcPaper record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	int updateByPrimaryKey(YcPaper record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	int updateByPrimaryKeySelective(YcPaper record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	List selectByExample(YcPaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	YcPaper selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	int deleteByExample(YcPaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	int countByExample(YcPaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	int updateByExampleSelective(YcPaper record, YcPaperExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_PAPER
	 * @abatorgenerated  Fri Aug 01 14:05:08 CST 2014
	 */
	int updateByExample(YcPaper record, YcPaperExample example);
}