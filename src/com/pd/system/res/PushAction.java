package com.pd.system.res;

import java.util.List;

import com.pd.system.framework.BaseAction;
import com.pd.system.utils.DwrTools;
import com.pd.system.utils.StringTools;

/**
 * 信息推送action，专门负责信息推送
 * */
public class PushAction extends BaseAction {

	private String url;
	private String func;
	private String msg;

	/**
	 * 推送到任意页面，任意javascript函数内
	 * */
	public void call() {
		if (null != null && !"".equals(url)) {
			List<String> msgs = StringTools.getIds(msg);
			DwrTools.pushToAnyPage(url, func, msgs.toArray(new String[msgs.size()]));
		}
	}

	// ===================================
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
