package com.pd.arc.service;

import com.pd.arc.dao.impl.ArcRescanInfoDAO;
import com.pd.arc.model.ArcRescanInfoExample;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.Pagination;

public class ArcReScanInfoService extends BaseService{
	private ArcRescanInfoDAO arcRescanInfoDAO;

	public void setArcRescanInfoDAO(ArcRescanInfoDAO arcRescanInfoDAO) {
		this.arcRescanInfoDAO = arcRescanInfoDAO;
	}
	
	/**
	 * 分页查询
	 * @param index
	 * @param example
	 * @return 
	 * @throws Exception 
	 */
	public Pagination findArcRescanInfoPageList(int index, ArcRescanInfoExample example) throws Exception {
		return super.findPageList(index, example, arcRescanInfoDAO);
		
	}

}
