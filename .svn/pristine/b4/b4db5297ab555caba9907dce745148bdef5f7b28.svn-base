package com.pd.wjyw.service.impl;

import java.util.List;
import java.util.UUID;

import com.pd.system.framework.BaseService;
import com.pd.system.framework.Pagination;
import com.pd.wjyw.dao.impl.YcTypePaperDAO;
import com.pd.wjyw.model.YcTypePaper;
import com.pd.wjyw.model.YcTypePaperExample;
import com.pd.wjyw.service.YcTypePaperService;

public class YcTypePaperServiceImpl extends BaseService implements
		YcTypePaperService {

	private YcTypePaperDAO ycTypePaperDAO;

	public void setYcTypePaperDAO(YcTypePaperDAO ycTypePaperDAO) {
		this.ycTypePaperDAO = ycTypePaperDAO;
	}

	

	@Override
	public int delete(String id) throws Exception {
		return ycTypePaperDAO.deleteByPrimaryKey(id);
	}

	@Override
	public Pagination findPageList(int index, YcTypePaperExample example)
			throws Exception {
		return super.findPageList(index, example, ycTypePaperDAO);
	}

	@Override
	public List<YcTypePaper> query(YcTypePaperExample example) throws Exception {
		return ycTypePaperDAO.selectByExampleToPage(example);
	}

	@Override
	public List<YcTypePaper> selectByExample(YcTypePaperExample ycPaperExample) {
		return ycTypePaperDAO.selectByExample(ycPaperExample);
	}

	@Override
	public void add(YcTypePaper entity, String[] takecodes) throws Exception {
		for(String takecode : takecodes){
			entity.setId(UUID.randomUUID().toString());
			entity.setTakecode(takecode);
			ycTypePaperDAO.insert(entity);
		}
	}
	
}


