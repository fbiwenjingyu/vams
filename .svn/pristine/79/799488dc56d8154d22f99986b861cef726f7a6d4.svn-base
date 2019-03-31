package com.pd.right.service;

import java.util.List;
import java.util.Map;

import com.pd.right.model.RoleRes;
import com.pd.right.model.RoleResExample;
import com.pd.right.model.RoleResources;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: RoleResService 
*  角色资源业务逻辑接口
* @author zl
* @date 2013-6-28 上午08:58:46 
*
 */
public interface RoleResService {
	/**
	 * 
	* @Title: add 
	*  角色资源添加
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	void add(RoleRes entity) throws Exception;

	/**
	 * 
	* @Title: delete 
	*  角色资源删除
	* @param @param RoleResCode
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	int delete(RoleResExample example) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  角色资源分页信息
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	Pagination getPageByExample(int index,RoleResExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  角色资源信息分页
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<RoleRes>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<RoleRes> query(RoleResExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询角色资源信息
	* @param @param example
	* @param @return    设定文件 
	* @return List<RoleRes>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<RoleRes> selectByExample(RoleResExample example);
	
	/**
	 * 
	* @Title: selectByRole 
	*  根据上级菜单查询下级菜单
	* @param @param map
	* @param @return    设定文件 
	* @return List<RoleResources>    返回类型 
	* @throws 
	* @author zl
	* 2013-7-8
	 */
	List<RoleResources> selectByRole(Map map);

	/**
	 * 查询二级菜单
	* @Title: selectByFirstRole 
	* @Description: TODO
	* @param @param map
	* @param @return    设定文件 
	* @return List<RoleResources>    返回类型 
	* @throws 
	* @author zl
	* 2013-8-6
	 */
	List<RoleResources> selectByFirstRole(Map map);
}


