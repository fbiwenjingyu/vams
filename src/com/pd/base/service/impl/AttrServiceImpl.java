package com.pd.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.base.dao.impl.AttrDAO;
import com.pd.base.model.Attr;
import com.pd.base.model.AttrExample;
import com.pd.base.service.AttrService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: AttrServiceImpl 
*  属性设置业务实现
* @author zl
* @date 2013-6-26 下午03:12:27 
*
 */
public class AttrServiceImpl implements AttrService {
	
	private AttrDAO attrDAO;

	/**
	 * 属性设置添加
	 */
	@Override
	public void add(Attr entity) throws Exception {
		attrDAO.insert(entity);
	}

	/**
	 * 属性设置删除
	 */
	@Override
	public int delete(String id) throws Exception {
		return attrDAO.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取属性对象
	 */
	@Override
	public Attr getEntity(String id) throws Exception {
		return attrDAO.selectByPrimaryKey(id);
	}

	/**
	 * 获取属性分页
	 */
	@Override
	public Pagination getPageByExample(int index, AttrExample example)
			throws Exception {
		int total = attrDAO.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 查询属性信息分页列表
	 */
	@Override
	public List<Attr> query(AttrExample example) throws Exception {
		List<Attr> list_data = attrDAO.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询属性分页信息
	 */
	@Override
	public List<Attr> selectByExample(AttrExample example) {
		return attrDAO.selectByExample(example);
	}

	/**
	 * 修改属性信息
	 */
	@Override
	public int update(Attr entity) throws Exception {
		return attrDAO.updateByPrimaryKey(entity);
	}

	public void setAttrDAO(AttrDAO attrDAO) {
		this.attrDAO = attrDAO;
	}

	/**
	 * 根据id获取对象
	 */
	@Override
	public Map queryToMap(AttrExample example) {
		List<Attr> list_data = attrDAO.selectByExample(example);
		Map map = new HashMap();
		for (Attr entity : list_data) {
			map.put(entity.getId(), entity.getAttrkey());
		}
		return map;
	}
	
}


