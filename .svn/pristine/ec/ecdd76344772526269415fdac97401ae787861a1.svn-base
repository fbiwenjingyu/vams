package com.pd.wjyw.service.impl;

import java.util.List;

import com.pd.system.framework.BaseService;
import com.pd.wjyw.dao.impl.YcInfoPicDAO;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoPicExample;
import com.pd.wjyw.service.YcInfoPicService;

public class YcInfoPicServiceImpl extends BaseService implements
		YcInfoPicService {
	private YcInfoPicDAO ycInfoPicDAO;

	public void setYcInfoPicDAO(YcInfoPicDAO ycInfoPicDAO) {
		this.ycInfoPicDAO = ycInfoPicDAO;
	}

	@Override
	public List<YcInfoPic> selectByExample(YcInfoPicExample ycInfoPicExample) {
		return ycInfoPicDAO.selectByExample(ycInfoPicExample);
	}

	@Override
	public List<YcInfoPic> getPicsByXtdabh(String xtdabh) {
		YcInfoPicExample ycInfoPicExample = new YcInfoPicExample();
		ycInfoPicExample.createCriteria().andXtdabhEqualTo(xtdabh);
		return ycInfoPicDAO.selectByExample(ycInfoPicExample);
	}
	
}


