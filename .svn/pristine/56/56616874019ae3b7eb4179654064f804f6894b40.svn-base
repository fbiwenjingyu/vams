package com.pd.right.service.impl;

import java.util.List;
import java.util.Map;

import com.pd.right.dao.impl.RoleResDAO;
import com.pd.right.model.RoleRes;
import com.pd.right.model.RoleResExample;
import com.pd.right.model.RoleResources;
import com.pd.right.service.RoleResService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: RoleResServiceImpl 
*  角色资源业务逻辑实现类
* @author zl
* @date 2013-6-28 上午09:32:01 
*
 */
public class RoleResServiceImpl implements RoleResService {

	private RoleResDAO roleResDao;
	/**
	 * 角色资源添加
	 */
	@Override
	public void add(RoleRes entity) throws Exception {
		roleResDao.insert(entity);
	}

	/**
	 * 角色资源删除
	 */
	@Override
	public int delete(RoleResExample example) throws Exception {
		return roleResDao.deleteByExample(example);
	}
	/**
	 * 查询角色资源分页信息
	 */
	@Override
	public Pagination getPageByExample(int index, RoleResExample example)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 角色资源分页查询
	 */
	@Override
	public List<RoleRes> query(RoleResExample example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据条件查询角色资源信息
	 */
	@Override
	public List<RoleRes> selectByExample(RoleResExample example) {
		return roleResDao.selectByExample(example);
	}

	public void setRoleResDao(RoleResDAO roleResDao) {
		this.roleResDao = roleResDao;
	}

	@Override
	public List<RoleResources> selectByRole(Map map) {
		return roleResDao.selectByRole(map);
	}

	@Override
	public List<RoleResources> selectByFirstRole(Map map) {
		return roleResDao.selectByFirstRole(map);
	}

}


