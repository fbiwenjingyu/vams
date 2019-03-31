package com.pd.base.service;

import java.util.List;
import java.util.Map;

import com.pd.base.model.Codeset;
import com.pd.base.model.CodesetExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: CodeSetService 
*  系统代码设置接口
* @author zl
* @date 2013-6-25 下午05:14:11 
*
 */
public interface CodeSetService {
	/**
	 * 
	* @Title: add 
	*  代码添加操作
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	void add(Codeset entity) throws Exception;
	/**
	 * 
	* @Title: update 
	*  代码修改操作
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int update(Codeset entity) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	*  根据id删除代码
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int delete(String id) throws Exception;
	
	/**
	 * 
	* @Title: getEntity 
	*  根据id获取代码对象
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Codeset    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Codeset getEntity(String id) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  对代码列表进行分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Pagination getPageByExample(int index,CodesetExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  获取代码分页列表信息
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Codeset>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Codeset> query(CodesetExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询代码信息
	* @param @param example
	* @param @return    设定文件 
	* @return List<Codeset>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Codeset> selectByExample(CodesetExample example);
	
	/**
	 * 
	* @Title: queryToMap 
	*  获取map里面对应的值
	* @param @param example
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	public Map queryToMap(CodesetExample example);
	
	/**
	 * 
	* @Title: queryToMapByCode 
	*  根据code获取name
	* @param @param example
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	* @author zl
	* 2013-7-4
	 */
	Map queryToMapByCode(CodesetExample example);
}


