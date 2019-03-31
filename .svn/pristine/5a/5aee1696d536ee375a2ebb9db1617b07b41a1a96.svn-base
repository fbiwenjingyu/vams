package com.pd.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.base.dao.impl.CodesetDAO;
import com.pd.base.model.Codeset;
import com.pd.base.model.CodesetExample;
import com.pd.base.service.CodeSetService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: CodeSetServiceImpl 
*  代码设置业务逻辑实现类
* @author zl
* @date 2013-6-26 下午03:29:44 
*
 */
public class CodeSetServiceImpl implements CodeSetService {
	
	private CodesetDAO codeSetDAO;

	/**
	 * 代码设置添加
	 */
	@Override
	public void add(Codeset entity) throws Exception {
		codeSetDAO.insert(entity);
	}

	/**
	 * 代码设置删除
	 */
	@Override
	public int delete(String id) throws Exception {
		return codeSetDAO.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取代码对象
	 */
	@Override
	public Codeset getEntity(String id) throws Exception {
		return codeSetDAO.selectByPrimaryKey(id);
	}

	/**
	 * 对代码设置进行分页
	 */
	@Override
	public Pagination getPageByExample(int index, CodesetExample example)
			throws Exception {
		int total = codeSetDAO.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 对代码列表进行分页查询
	 */
	@Override
	public List<Codeset> query(CodesetExample example) throws Exception {
		List<Codeset> list_data = codeSetDAO.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询代码信息
	 */
	@Override
	public List<Codeset> selectByExample(CodesetExample example) {
		return codeSetDAO.selectByExample(example);
	}

	/**
	 * 修改代码信息
	 */
	@Override
	public int update(Codeset entity) throws Exception {
		return codeSetDAO.updateByPrimaryKey(entity);
	}

	public void setCodeSetDAO(CodesetDAO codeSetDAO) {
		this.codeSetDAO = codeSetDAO;
	}

	@Override
	public Map queryToMap(CodesetExample example){
		List<Codeset> list_data = codeSetDAO.selectByExample(example);
		Map map = new HashMap();
		for (Codeset entity : list_data) {
			map.put(entity.getId(), entity.getName());
		}
		return map;
	}

	@Override
	public Map queryToMapByCode(CodesetExample example){
		List<Codeset> list_data = codeSetDAO.selectByExample(example);
		Map map = new HashMap();
		for (Codeset entity : list_data) {
			map.put(entity.getCode(), entity.getName());
		}
		return map;
	}
}


