package com.pd.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.base.dao.impl.PaperDAO;
import com.pd.base.model.Paper;
import com.pd.base.model.PaperExample;
import com.pd.base.service.PaperService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: PaperServiceImpl 
*  纸张设置业务逻辑实现类
* @author zl
* @date 2013-6-26 下午03:45:21 
*
 */
public class PaperServiceImpl implements PaperService {

	private PaperDAO paperDAO;
	/**
	 * 保存纸张设置信息
	 */
	@Override
	public void add(Paper entity) throws Exception {
		paperDAO.insert(entity);
	}

	/**
	 * 删除纸张设置信息
	 */
	@Override
	public int delete(String id) throws Exception {
		return paperDAO.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取纸张信息对象
	 */
	@Override
	public Paper getEntity(String id) throws Exception {
		return paperDAO.selectByPrimaryKey(id);
	}

	/**
	 * 获取纸张信息分页
	 */
	@Override
	public Pagination getPageByExample(int index, PaperExample example)
			throws Exception {
		int total = paperDAO.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 查询纸张列表分页信息
	 */
	@Override
	public List<Paper> query(PaperExample example) throws Exception {
		List<Paper> list_data = paperDAO.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询纸张信息
	 */
	@Override
	public List<Paper> selectByExample(PaperExample example) {
		//根据扫描档案页名排序
		return paperDAO.selectByExample(example);
	}

	/**
	 * 修改纸张信息
	 */
	@Override
	public int update(Paper entity) throws Exception {
		return paperDAO.updateByPrimaryKey(entity);
	}

	public void setPaperDAO(PaperDAO paperDAO) {
		this.paperDAO = paperDAO;
	}

	/**
	 * 根据id获取纸张名称
	 */
	@Override
	public Map queryToMap(PaperExample example) {
		List<Paper> list_data = paperDAO.selectByExample(example);
		Map map = new HashMap();
		for (Paper entity : list_data) {
			map.put(entity.getId(), entity.getPaperName());
		}
		return map;
	}

}


