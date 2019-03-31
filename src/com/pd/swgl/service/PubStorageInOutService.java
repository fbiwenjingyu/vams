package com.pd.swgl.service;

import java.util.List;

import com.pd.swgl.dao.ArcStorageInDAO;
import com.pd.swgl.model.ArcStorageIn;
import com.pd.swgl.model.ArcStorageInExample;
import com.pd.system.framework.BaseService;
import com.pd.system.res.ArcStatus;

/**
 * 出入库公开接口
 * */
public class PubStorageInOutService extends BaseService {

	private ArcStorageInDAO arcStorageInDAO;

	/**
	 * 入库表：通过系统档案编号更新储位编号，更新待审核入库的档案
	 * */
	public void updateRedayInCwbh(String xtdabh, String newcwbh)
			throws Exception {
		ArcStorageInExample inExample = new ArcStorageInExample();
		inExample.createCriteria().andXtdabhEqualTo(xtdabh)
				.andDaztEqualTo(ArcStatus.DSH_RK);
		List<ArcStorageIn> list = arcStorageInDAO.selectByExample(inExample);
		// 更新编号
		if (list.size() == 1) {
			ArcStorageIn in = list.get(0);
			in.setCwbh(newcwbh);
			arcStorageInDAO.updateByPrimaryKeySelective(in);
		}
	}

	public void setArcStorageInDAO(ArcStorageInDAO arcStorageInDAO) {
		this.arcStorageInDAO = arcStorageInDAO;
	}

}
