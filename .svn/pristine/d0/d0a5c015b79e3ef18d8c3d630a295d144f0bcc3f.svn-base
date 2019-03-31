package com.pd.right.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.right.dao.impl.DeptDAO;
import com.pd.right.model.Dept;
import com.pd.right.model.DeptExample;
import com.pd.right.service.DeptService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: DeptServiceImpl 
*  机构业务逻辑实现
* @author zl
* @date 2013-6-28 上午09:11:40 
*
 */
public class DeptServiceImpl implements DeptService {

	private DeptDAO deptDao;
	
	/**
	 * 机构管理添加保存
	 */
	@Override
	public void add(Dept entity) throws Exception {
		deptDao.insert(entity);
	}

	/**
	 * 机构管理删除
	 */
	@Override
	public int delete(String deptCode) throws Exception {
		return deptDao.deleteByPrimaryKey(deptCode);
	}

	/**
	 * 根据部门代码获取部门对象
	 */
	@Override
	public Dept getEntity(String deptCode) throws Exception {
		return deptDao.selectByPrimaryKey(deptCode);
	}

	/**
	 * 对部门信息进行分页
	 */
	@Override
	public Pagination getPageByExample(int index, DeptExample example)
			throws Exception {
		int total = deptDao.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 查询部门信息分页列表
	 */
	@Override
	public List<Dept> query(DeptExample example) throws Exception {
		List<Dept> list_data = deptDao.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询部门信息
	 */
	@Override
	public List<Dept> selectByExample(DeptExample example) {
		return deptDao.selectByExample(example);
	}

	/**
	 * 修改部门信息
	 */
	@Override
	public int update(Dept entity) throws Exception {
		return deptDao.updateByPrimaryKey(entity);
	}

	public void setDeptDao(DeptDAO deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public Map queryToMap(DeptExample example) {
		List<Dept> list_data = deptDao.selectByExample(example);
		Map map = new HashMap();
		for (Dept entity : list_data) {
			map.put(entity.getDeptcode(), entity.getDeptname());
		}
		return map;
	}

}


