package com.pd.right.service;
import java.util.List;
import java.util.Map;

import com.pd.right.model.Role;
import com.pd.right.model.RoleExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: RoleService 
*  角色管理接口
* @author zl
* @date 2013-6-24 下午01:33:14 
*
 */
public interface RoleService {
	/**
	 * 
	* @Title: add 
	*  角色保存
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-24
	 */
	void add(Role entity) throws Exception;
	
	/**
	 * 
	* @Title: update 
	*  角色信息修改
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-24
	 */
	int update(Role entity) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	*  删除角色信息
	* @param @param roleid
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-24
	 */
	int delete(Long roleid) throws Exception;
	
	/**
	 * 
	* @Title: getEntity 
	*  获取对象
	* @param @param roleid
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Role    返回类型 
	* @throws 
	* @author zl
	* 2013-6-24
	 */
	Role getEntity(Long roleid) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  获取列表分页信息
	* @param  index
	* @param  example
	* @param 
	* @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @author zl
	* 2013-6-24
	 */
	Pagination getPageByExample(int index,RoleExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  获取角色列表分页信息
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Role>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-24
	 */
	List<Role> query(RoleExample example) throws Exception;

	/**
	 * 
	* @Title: selectByExample 
	*  获取角色下拉列表信息
	* @param @param object
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Role> selectByExample(RoleExample example);

	/**
	 * 
	* @Title: preUpdate 
	*  获取需要修改的角色西悉尼
	* @param @param roleid
	* @param @return    设定文件 
	* @return Role    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Role preUpdate(Long roleid);
	
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
	public Map queryToMap(RoleExample example);
	
	/**
	 * 
	* @Title: add 
	*  保存角色和角色资源信息
	* @param @param role
	* @param @param resid
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-7-11
	 */
	void add(Role role, String[] resid) throws Exception;
	
	/**
	 * 
	* @Title: update 
	*  修改角色和角色资源信息
	* @param @param role
	* @param @param resid
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-7-11
	 */
	int update(Role role, String[] resid) throws Exception;

	void add();
}


