package com.pd.system.res;

import com.pd.system.framework.BaseAction;
import com.pd.system.startup.SYSLoadManager;

/**
 * 缓存管理action
 * */
public class CacheManageAction extends BaseAction {
	
	
	/**
	 * 刷新全部缓存
	 * */
	public String flushAll(){
		
		SYSLoadManager.reload();
		
		return STRUTS_JSON(new String[]{"1","all"});
		
	}
	
}
