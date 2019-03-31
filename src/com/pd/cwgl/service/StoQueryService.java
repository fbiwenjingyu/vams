package com.pd.cwgl.service;

import com.pd.arc.dao.impl.ArcBaseInfoDAO;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.cwgl.dao.StoSettingDAO;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.Pagination;

/**
 * 库房查询
 * */
public class StoQueryService extends BaseService {

	private StoSettingDAO stoSettingDAO;
	private ArcBaseInfoDAO arcBaseInfoDAO;

	/**
	 * 分页查询储位信息列表
	 * 
	 * @throws Exception
	 * */
	public Pagination findByPage(int index, ArcBaseInfoExample example)
			throws Exception {
		example.setOrderByClause(" cjsj desc");
		return findPageList(index, example, arcBaseInfoDAO);
	}

	// =====================================================
	public void setStoSettingDAO(StoSettingDAO stoSettingDAO) {
		this.stoSettingDAO = stoSettingDAO;
	}

	public void setArcBaseInfoDAO(ArcBaseInfoDAO arcBaseInfoDAO) {
		this.arcBaseInfoDAO = arcBaseInfoDAO;
	}

}
