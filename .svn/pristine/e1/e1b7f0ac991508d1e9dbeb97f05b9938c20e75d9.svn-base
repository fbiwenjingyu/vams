package com.pd.cwgl.service;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.right.model.SuperUser;
import com.pd.system.framework.BaseService;

public class PubStoService extends BaseService {

	private StoService stoService;

	
	/**
	 * 针对业务档案添加储位编号
	 * 
	 * @param xtdabh
	 *            需要添加储位编号的系统档案编号
	 * @param user
	 *            当前操作人对象
	 * */
	public String addCwbh(String xtdabh,String cwbh, SuperUser user) throws Exception {
		if (null == xtdabh || "".equals(xtdabh.trim())) {
			throw new RuntimeException("添加储位编号异常：系统档案编号不能为空");
		}
		ArcBaseInfo info = new ArcBaseInfo();
		info.setXtdabh(xtdabh);
		info.setCwbh(cwbh);
		// 添加储位编号
		return stoService.saveStore(info, user, 1);
	}
	
	

	// ==========================================
	public void setStoService(StoService stoService) {
		this.stoService = stoService;
	}

}
