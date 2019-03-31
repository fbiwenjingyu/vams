package com.pd.right.service.impl;

import java.util.List;

import com.pd.right.dao.impl.UsersDAO;
import com.pd.right.model.SuperUser;
import com.pd.right.model.Users;
import com.pd.right.model.UsersExample;
import com.pd.right.service.UsersService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: UsersServiceImpl 
*  用户信息业务逻辑实现类
* @author zl
* @date 2013-6-28 上午09:34:04 
*
 */
public class UsersServiceImpl implements UsersService {

	private UsersDAO userDao;
	/**
	 * 用户添加保存
	 */
	@Override
	public void add(Users entity) throws Exception {
		userDao.insert(entity);
	}
	/**
	 * 用户信息删除
	 */
	@Override
	public int delete(String usersCode) throws Exception {
		return userDao.deleteByPrimaryKey(usersCode);
	}

	/**
	 * 根据用户账号查询用户对象
	 */
	@Override
	public Users getEntity(String usersCode) throws Exception {
		return userDao.selectByPrimaryKey(usersCode);
	}

	/**
	 * 用户信息分页
	 */
	@Override
	public Pagination getPageByExample(int index, UsersExample example)
			throws Exception {
		int total = userDao.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 查询用户信息分页列表
	 */
	@Override
	public List<Users> query(UsersExample example) throws Exception {
		List<Users> list_data = userDao.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询用户信息
	 */
	@Override
	public List<Users> selectByExample(UsersExample example) {
		return userDao.selectByExample(example);
	}

	/**
	 * 用户信息修改
	 */
	@Override
	public int update(Users entity) throws Exception {
		return userDao.updateByPrimaryKeySelective(entity);
	}
	public void setUserDao(UsersDAO userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 获取登录用户信息
	 */
	@Override
	public SuperUser getUser(String userCode, String password) {
		return userDao.getUser(userCode, password,"");//查询任意用户
	}

}


