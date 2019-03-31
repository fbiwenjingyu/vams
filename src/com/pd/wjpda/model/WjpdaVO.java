package com.pd.wjpda.model;

/**
 * 封装业务外检VO
 * @author huhaifeng 2014-08-02
 *
 */
public class WjpdaVO {
	
	private String takeCode;				//纸张代码
	
	private String takeName;				//纸张名称
	
	private String picStatus;				//图片状态 S初始未审核状态       N审核不通过     Y审核通过
	
	private String xtdabh;					//系统档案编号(可以是外检编号或者其他编号统称)
	
	private String hpzl;					//号牌种类
	
	private String ywlx;					//业务类型

	public String getTakeCode() {
		return takeCode;
	}

	public void setTakeCode(String takeCode) {
		this.takeCode = takeCode;
	}

	public String getTakeName() {
		return takeName;
	}

	public void setTakeName(String takeName) {
		this.takeName = takeName;
	}

	public String getPicStatus() {
		return picStatus;
	}

	public void setPicStatus(String picStatus) {
		this.picStatus = picStatus;
	}

	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}


	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}
	
	

}
