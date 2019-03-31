package com.pd.right.model;

import com.pd.system.startup.DBResource;


public class SuperUser extends Users {
	private String deptarea;
	private String deptname;
	private String rolename;
	private String levels;
	private String sftz;				//合并条码窗口是否跳转
	
	private String hpqysheng;//号牌区域：省
	private String hpqyshi;//号牌区域：市
	
	private String fastPrint;//快速打印标记
	private String cwglOpera;//储位管理操作记录

	public String getSftz() {
		return sftz;
	}

	public void setSftz(String sftz) {
		this.sftz = sftz;
	}

	public String getDeptarea() {
		return deptarea;
	}

	public void setDeptarea(String deptarea) {
		this.deptarea = deptarea;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getFastPrint() {
		return fastPrint;
	}

	public void setFastPrint(String fastPrint) {
		this.fastPrint = fastPrint;
	}
	
	public String getHpqysheng() {
		return hpqysheng;
	}

	public void setHpqysheng(String hpqysheng) {
		this.hpqysheng = hpqysheng;
	}

	public String getHpqyshi() {
		return hpqyshi;
	}

	public void setHpqyshi(String hpqyshi) {
		this.hpqyshi = hpqyshi;
	}

	public String getCwglOpera() {
		return cwglOpera;
	}

	public void setCwglOpera(String cwglOpera) {
		this.cwglOpera = cwglOpera;
	}

	//==========================================================
	// 载入资源
	public void loadRes() {
		if (getF2() != null && !getF2().trim().equals("")) {
			String f2 = getF2();
			String str[] = f2.split(";");
			for (int i = 0; i < str.length; i++) {
				if (str[i].indexOf("fastPrint") != -1) {
					fastPrint = "1";
				}
			}
		}
		//储位管理操作记录
		cwglOpera = getF1();
		//加载号牌区域
		this.hpqysheng=DBResource.getSysAttrName("hpqy.sheng");
		this.hpqyshi=DBResource.getSysAttrName("hpqy.shi");
	}
	

}
