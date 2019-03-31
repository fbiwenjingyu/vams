package com.pd.system.framework;

import java.io.Serializable;

public class PageToExample implements Serializable{

	private static final long serialVersionUID = 3550105968252205713L;
	private String target;
	private Integer start = 0;
    private Integer limit = 0;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}


