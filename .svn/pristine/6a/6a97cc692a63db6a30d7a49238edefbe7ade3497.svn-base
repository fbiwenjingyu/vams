package com.pd.wjyw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.pd.system.framework.BaseService;
import com.pd.system.framework.Pagination;
import com.pd.wjyw.dao.impl.*;
import com.pd.wjyw.model.YcPaper;
import com.pd.wjyw.model.YcPaperExample;
import com.pd.wjyw.service.YcPaperService;

public class YcPaperServiceImpl extends BaseService implements YcPaperService {

	private YcPaperDAO ycPaperDAO;
	@Override
	public void add(YcPaper ycPaper) throws Exception {
		ycPaper.setId(UUID.randomUUID().toString());
		ycPaperDAO.insert(ycPaper);
	}

	@Override
	public int delete(String id) throws Exception {
		return ycPaperDAO.deleteByPrimaryKey(id);
	}

	@Override
	public YcPaper getEntity(String id) throws Exception {
		return ycPaperDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<YcPaper> query(YcPaperExample example) throws Exception {
		return ycPaperDAO.selectByExampleToPage(example);
	}

	@Override
	public Map queryToMap(YcPaperExample example) {
		List<YcPaper> list_data = ycPaperDAO.selectByExample(example);
		Map map = new HashMap();
		for (YcPaper entity : list_data) {
			map.put(entity.getTakecode(), entity.getTakeName());
		}
		return map;
	}

	@Override
	public int update(YcPaper ycPaper) throws Exception {
		return ycPaperDAO.updateByPrimaryKeySelective(ycPaper);
	}

	public void setYcPaperDAO(YcPaperDAO ycPaperDAO) {   
		this.ycPaperDAO = ycPaperDAO;
	}

	@Override
	public List<YcPaper> selectByExample(YcPaperExample ycPaperExample) {
		return ycPaperDAO.selectByExample(ycPaperExample);
	}

	@Override
	public Pagination findPageList(int index, YcPaperExample ycPaperExample) throws Exception {
		return super.findPageList(index, ycPaperExample, ycPaperDAO);
	}

}


