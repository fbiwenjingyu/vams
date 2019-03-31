package com.pd.wjyw.dao.impl;

import com.pd.system.framework.BaseDAO;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoSuper;

import java.util.List;
import java.util.Map;

public interface YcInfoDAO extends BaseDAO{

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	void insert(YcInfo record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	int updateByPrimaryKey(YcInfo record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	int updateByPrimaryKeySelective(YcInfo record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	List selectByExample(YcInfoExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	YcInfo selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	int deleteByExample(YcInfoExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	int countByExample(YcInfoExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	int updateByExampleSelective(YcInfo record, YcInfoExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table YC_INFO
	 * @abatorgenerated  Tue Aug 05 11:38:16 CST 2014
	 */
	int updateByExample(YcInfo record, YcInfoExample example);

	Map getXh(String code);

	/**
	 * 根据部门统计审核通过的外检数量
	* @Title: getYcInfoByDept 
	* @Description: TODO
	* @param @param map
	* @param @return    设定文件 
	* @return List<YcInfoSuper>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-5
	 */
	List<YcInfoSuper> getYcInfoByDept(Map map);
	
	/**
	 * 统计一周的外检数量
	* @Title: getYcInfoWeekCount 
	* @Description: TODO
	* @param @param map
	* @param @return    设定文件 
	* @return List<YcInfoSuper>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-15
	 */
	int getYcInfoWeekCount(Map map);

	/**
	 * 获取系统档案编号序列
	* @Title: getXtdabhSeq 
	* @Description: TODO
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-22
	 */
	String getXtdabhSeq();
}