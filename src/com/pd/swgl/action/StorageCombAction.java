package com.pd.swgl.action;

import java.util.List;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.swgl.service.StorageCombService;
import com.pd.system.framework.BaseAction;
import com.pd.system.utils.StringTools;

public class StorageCombAction extends BaseAction {
	
	
	private StorageCombService storageCombService;
	
	private ArcBaseInfo arcinfo;
	private String xtdabh;
	
	private String applyIds;
	
	/**
	 * 进入档案合并列表
	 * */
	public String list(){
		return "list";
	}
	
	/**
	 * 查询可以合档的关联档案
	 * */
	public String find(){
		pageList = storageCombService.findAllowCombArc(arcinfo);
		return "list";
	}
	
	/**
	 * 查看档案
	 * */
	public String showArc(){
		List<ArcBaseInfo> list= storageCombService.findArcByXtdabh(xtdabh);
		if (list.size()==1) {
			arcinfo=list.get(0);
		}else if(list.size()==0){
			return ERROR("未查询到档案");
		}else{
			return ERROR("数据异常，查询到相同的系统编号");
		}
		return "showArc";
	}
	
	/**
	 * 档案合并
	 * */
	public String comb(){
		Object[] ret =new Object[2];
		List<String> ids=StringTools.getIds(applyIds);
		if (ids.size()!=0) {
			try {
				storageCombService.arcComb(ids,getSessionUser());
				ret[0]="1";
				ret[1]="合并成功！";
			} catch (Exception e) {
				logger.error("合档异常",e);
				ret[0]="0";
				ret[1]="档案合并处理异常";
			}
		}else{
			ret[0]="0";
			ret[1]="缺少必要参数：系统档案编号";
		}
		return STRUTS_JSON(ret);
	}
	
	
	
	
	//=======================================

	public void setStorageCombService(StorageCombService storageCombService) {
		this.storageCombService = storageCombService;
	}

	public ArcBaseInfo getArcinfo() {
		return arcinfo;
	}

	public void setArcinfo(ArcBaseInfo arcinfo) {
		this.arcinfo = arcinfo;
	}

	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}

	public String getApplyIds() {
		return applyIds;
	}

	public void setApplyIds(String applyIds) {
		this.applyIds = applyIds;
	}
	
}
