package com.pd.system.utils;

import com.pd.arc.model.ArcBaseInfo;

/**
 * 标签打印bean
 * */
public class PrintBean {
	// 打印条码值
	private String code;
	// 系统档案编号
	private String xtdabh;
	// 号牌号码
	private String hphm;
	// 号牌种类
	private String hpzl;
	// 储位编号
	private String cwbh;
	// 储位打印编号
	private String cwbhprint;
	// 车辆识别代号
	private String clsbdh;
	// 业务类型
	private String ywlx;
	// 档案编号
	private String dabh;
	// 流水号
	private String lsh;

	public PrintBean() {
	}

	public PrintBean(ArcBaseInfo info) {
		this.xtdabh = info.getXtdabh();
		this.hphm = info.getHphm();
		this.hpzl = info.getHpzl();
		this.cwbh = info.getCwbh();
		this.cwbhprint = StringTools.getPrintCwbh(info.getCwbh());
		this.clsbdh = info.getClsbdh();
		this.ywlx = info.getYwlx();
		this.dabh = info.getDabh();
		this.lsh = info.getLsh();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getCwbh() {
		return cwbh;
	}

	public void setCwbh(String cwbh) {
		this.cwbh = cwbh;
	}

	public String getClsbdh() {
		return clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getDabh() {
		return dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getCwbhprint() {
		return cwbhprint;
	}

	public void setCwbhprint(String cwbhprint) {
		this.cwbhprint = cwbhprint;
	}

}
