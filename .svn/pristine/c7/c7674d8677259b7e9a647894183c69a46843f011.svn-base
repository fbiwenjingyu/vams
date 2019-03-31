package com.pd.right.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.right.dao.impl.RoleDAO;
import com.pd.right.dao.impl.RoleResDAO;
import com.pd.right.model.Role;
import com.pd.right.model.RoleExample;
import com.pd.right.model.RoleRes;
import com.pd.right.model.RoleResExample;
import com.pd.right.service.RoleService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: RoleServiceImpl 
*  角色业务实现操作类
* @author zl
* @date 2013-6-24 下午01:42:19 
*
 */
public class RoleServiceImpl implements RoleService{

	private RoleDAO roleDao;
	private RoleResDAO roleResDao;
	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * 角色信息保存
	 */
	@Override
	public void add(Role entity) throws Exception {
		roleDao.insert(entity);
	}

	/**
	 * 根据id删除角色信息
	 */
	@Override
	public int delete(Long roleid) throws Exception {
		return roleDao.deleteByPrimaryKey(roleid);
	}

	/**
	 * 根据id获取角色对象
	 */
	@Override
	public Role getEntity(Long roleid) throws Exception {
		return roleDao.selectByPrimaryKey(roleid);
	}


	/**
	 * 修改角色信息
	 */
	@Override
	public int update(Role entity) throws Exception {
		return roleDao.updateByPrimaryKey(entity);
	}

	/**
	 * 角色分页
	 */
	@Override
	public Pagination getPageByExample(int index, RoleExample example)
			throws Exception {
		int total = roleDao.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}
	
	/**
	 * 角色列表查询
	 */
	public List<Role> query(RoleExample example)
		throws Exception {
		List<Role> list_data = roleDao.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询角色信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> selectByExample(RoleExample example) {
		return roleDao.selectByExample(example);
	}

	/**
	 * 根据角色id获取角色对象
	 */
	@Override
	public Role preUpdate(Long roleid) {
		return roleDao.selectByPrimaryKey(roleid);
	}

	/**
	 * 根据id获取角色名称
	 */
	@Override
	public Map queryToMap(RoleExample example) {
		List<Role> list_data = roleDao.selectByExample(example);
		Map map = new HashMap();
		for (Role entity : list_data) {
			map.put(entity.getRoleid(), entity.getRolename());
		}
		return map;
	}

	/**
	 * 保存角色和角色资源信息列表
	 */
	@Override
	public void add(Role role, String[] resids) throws Exception {
		if(role != null){
			Long roleid = roleDao.getSeq();
			role.setRoleid(roleid);
			RoleRes record = new RoleRes();
			String resid ="";
			roleDao.insert(role);
			if(resids != null && resids.length>0){
				for(int i=0;i<resids.length;i++){
					resid = resids[i];
					record.setRoleid(roleid);
					record.setResid(resid);
					roleResDao.insert(record);
				}
			}
		}
		
	}

	/**
	 * 修改角色和角色资源信息列表
	 */
	@Override
	public int update(Role entity, String[] resids) throws Exception {
		int result = 0;
		if(entity != null){
			RoleRes record = new RoleRes();
			String resid ="";
			Long roleid = entity.getRoleid();
			RoleResExample roleresExample = new RoleResExample();
			com.pd.right.model.RoleResExample.Criteria ca = roleresExample.createCriteria();
			ca.andRoleidEqualTo(roleid);
			//删除角色原来的资源信息
			roleResDao.deleteByExample(roleresExample);
			roleDao.updateByPrimaryKeySelective(entity);
			if(resids != null && resids.length>0){
				for(int i=0;i<resids.length;i++){
					resid = resids[i];
					record.setRoleid(roleid);
					record.setResid(resid);
					
					roleResDao.insert(record);
				}
			}
			result = 1;
		}
		return result;
	}
	public void setRoleResDao(RoleResDAO roleResDao) {
		this.roleResDao = roleResDao;
	}

	
	public void add(){
//		Role record = new Role();
//		record.setRoleid(Long.parseLong("22"));
//		record.setRolename("1111");
//		
//		Role record11 = new Role();
//		record11.setRoleid(Long.parseLong("22"));
//		record11.setRolename("1111");
//		roleDao.insert(record);
//		roleDao.insert(record11);
		//RoleResDAO roleResDao1 = DAOFactory.getInstance().getDao("roleResDAO");
		//RoleDAO roleDao1 = DAOFactory.getInstance().getDao("roleDAO");
		Role record = new Role();
		record.setRoleid(Long.parseLong("22"));
		record.setRolename("测试吊顶灯");
		roleDao.deleteByPrimaryKey(Long.parseLong("22"));
		roleDao.insert(record);
		
		RoleRes res = new RoleRes();
		res.setResid("1222");
		res.setRoleid(Long.parseLong("22"));
		roleResDao.insert(res);
	}
	
	public static void main(String[] args){
		//add();
	}
	
}


