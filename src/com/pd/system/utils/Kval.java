package com.pd.system.utils;

import java.io.Serializable;

/**
 * 键值对对象
 * */
public class Kval implements Serializable {
	
	private static final long serialVersionUID = -6117685934577046416L;
	private String key;
	private Object value;
	
	private Object val1;
	private Object val2;
	
	public Kval() {
	}
	
	public Kval(String key,Object value) {
		this.key=key;
		this.value=value;
	}

	public Kval(String key, Object value, Object val1, Object val2) {
		super();
		this.key = key;
		this.value = value;
		this.val1 = val1;
		this.val2 = val2;
	}

	public Object getVal1() {
		return val1;
	}

	public void setVal1(Object val1) {
		this.val1 = val1;
	}

	public Object getVal2() {
		return val2;
	}

	public void setVal2(Object val2) {
		this.val2 = val2;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
