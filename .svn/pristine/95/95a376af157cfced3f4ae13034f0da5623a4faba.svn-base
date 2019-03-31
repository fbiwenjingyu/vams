package com.pd.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.base.dao.impl.SeclevelDAO;
import com.pd.base.model.Seclevel;
import com.pd.base.model.SeclevelExample;
import com.pd.base.service.SeclevelService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: SeclevelServiceImpl 
*  安全级别业务逻辑实现类
* @author zl
* @date 2013-6-26 下午03:56:33 
*
 */
public class SeclevelServiceImpl implements SeclevelService {
	private SeclevelDAO seclevelDAO;
	/**
	 * 安全级别添加
	 */
	@Override
	public void add(Seclevel entity) throws Exception {
		seclevelDAO.insert(entity);
	}

	/**
	 * 安全级别删除
	 */
	@Override
	public int delete(String id) throws Exception {
		return seclevelDAO.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取安全级别对象
	 */
	@Override
	public Seclevel getEntity(String id) throws Exception {
		return seclevelDAO.selectByPrimaryKey(id);
	}

	/**
	 * 查询安全级别分页
	 */
	@Override
	public Pagination getPageByExample(int index, SeclevelExample example)
			throws Exception {
		int total = seclevelDAO.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 查询安全级别列表分页信息
	 */
	@Override
	public List<Seclevel> query(SeclevelExample example) throws Exception {
		List<Seclevel> list_data = seclevelDAO.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询安全级别信息
	 */
	@Override
	public List<Seclevel> selectByExample(SeclevelExample example) {
		return seclevelDAO.selectByExample(example);
	}

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
	public Map queryToMap(SeclevelExample example){
		List<Seclevel> list_data = seclevelDAO.selectByExample(example);
		Map map = new HashMap();
		for (Seclevel entity : list_data) {
			map.put(entity.getId(), entity.getLevels());
		}
		return map;
	}
	
	/**
	 * 修改安全级别信息
	 */
	@Override
	public int update(Seclevel entity) throws Exception {
		return seclevelDAO.updateByPrimaryKey(entity);
	}

	public void setSeclevelDAO(SeclevelDAO seclevelDAO) {
		this.seclevelDAO = seclevelDAO;
	}

}


