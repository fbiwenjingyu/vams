package com.pd.right.service;

import java.util.List;

import com.pd.right.model.Resources;
import com.pd.right.model.ResourcesExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: ResourcesService 
*  资源管理业务逻辑接口
* @author zl
* @date 2013-6-28 上午08:58:31 
*
 */
public interface ResourcesService {
	/**
	 * 
	* @Title: add 
	*  资源管理添加保存
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	void add(Resources entity) throws Exception;
	/**
	 * 
	* @Title: update 
	*  资源管理修改
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	int update(Resources entity) throws Exception;
	/**
	 * 
	* @Title: delete 
	*  资源管理删除
	* @param @param ResourcesCode
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	int delete(String ResourcesCode) throws Exception;
	
	/**
	 * 
	* @Title: getEntity 
	*  根据嗲吗查询资源对象
	* @param @param ResourcesCode
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Resources    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	Resources getEntity(String ResourcesCode) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  分页查询
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	Pagination getPageByExample(int index,ResourcesExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  资源信息分页查询
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Resources>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<Resources> query(ResourcesExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询资源信息
	* @param @param example
	* @param @return    设定文件 
	* @return List<Resources>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<Resources> selectByExample(ResourcesExample example);
}


