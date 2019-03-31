package com.pd.arc.model;

import java.io.Serializable;

public class ArcBaseInfoSuper extends ArcBaseInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer shNum;
	private Integer gdNum;
	private Integer rkNum;
	private Integer smNum;
	private String code;
	private String mc;
	private Integer wjNum;
	
	public Integer getWjNum() {
		return wjNum;
	}
	public void setWjNum(Integer wjNum) {
		this.wjNum = wjNum;
	}
	public Integer getRkNum() {
		return rkNum;
	}
	public void setRkNum(Integer rkNum) {
		this.rkNum = rkNum;
	}
	public Integer getSmNum() {
		return smNum;
	}
	public void setSmNum(Integer smNum) {
		this.smNum = smNum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getShNum() {
		return shNum;
	}
	public void setShNum(Integer shNum) {
		this.shNum = shNum;
	}
	public Integer getGdNum() {
		return gdNum;
	}
	public void setGdNum(Integer gdNum) {
		this.gdNum = gdNum;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	
}


