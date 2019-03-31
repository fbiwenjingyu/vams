package com.pd.base.service;

import java.util.List;
import java.util.Map;

import com.pd.base.model.Seclevel;
import com.pd.base.model.SeclevelExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: SeclevelService 
*  安全级别接口
* @author zl
* @date 2013-6-25 下午05:30:31 
*
 */
public interface SeclevelService {
	/**
	 * 
	* @Title: add 
	*  安全级别添加
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	void add(Seclevel entity) throws Exception;
	
	/**
	 * 
	* @Title: update 
	*  修改安全级别
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int update(Seclevel entity) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	*  根据id删除安全级别信息
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
	*  根据id获取安全级别对象
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Seclevel    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Seclevel getEntity(String id) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  对安全级别进行分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Pagination getPageByExample(int index,SeclevelExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  安全级别信息列表查询
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Seclevel>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Seclevel> query(SeclevelExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询安全级别信息
	* @param @param example
	* @param @return    设定文件 
	* @return List<Seclevel>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Seclevel> selectByExample(SeclevelExample example);
	
	/**
	 * 
	* @Title: queryToMap 
	*  根据id获取级别供其他相关联表进行查询显示
	* @param @param example
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	* @author zl
	* 2013-6-27
	 */
	public Map queryToMap(SeclevelExample example);
}


