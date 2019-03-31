package com.pd.right.service;

import java.util.List;

import com.pd.right.model.SuperUser;
import com.pd.right.model.Users;
import com.pd.right.model.UsersExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: UsersService 
*  用户管理业务逻辑接口
* @author zl
* @date 2013-6-28 上午08:59:00 
*
 */
public interface UsersService {
	/**
	 * 
	* @Title: add 
	*  用户添加保存
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	void add(Users entity) throws Exception;
	/**
	 * 
	* @Title: update 
	*  用户修改保存
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	int update(Users entity) throws Exception;
	/**
	 * 
	* @Title: delete 
	*  用户删除
	* @param @param UsersCode
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	int delete(String UsersCode) throws Exception;
	/**
	 * 
	* @Title: getEntity 
	*  根据用户账号获取用户对象
	* @param @param UsersCode
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Users    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	Users getEntity(String UsersCode) throws Exception;
	/**
	 * 
	* @Title: getPageByExample 
	*  用户信息分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	Pagination getPageByExample(int index,UsersExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  用户信息分页查询
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Users>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<Users> query(UsersExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询用户信息
	* @param @param example
	* @param @return    设定文件 
	* @return List<Users>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<Users> selectByExample(UsersExample example);
	
	/**
	 * 
	* @Title: getUser 
	*  获取登录用户信息
	* @param @param userCode
	* @param @param password
	* @param @return    设定文件 
	* @return SuperUser    返回类型 
	* @throws 
	* @author zl
	* 2013-7-11
	 */
	SuperUser getUser(String userCode,String password);
}


