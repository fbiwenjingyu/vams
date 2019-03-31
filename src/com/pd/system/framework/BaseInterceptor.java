package com.pd.system.framework;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pd.system.utils.Vmessage;

/**
 * Interceptor封装鸡肋，Interceptor建议继承此类，方便扩展
 * 
 * @author BraveHeartWzm
 * */
public abstract class BaseInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -6758741563909467544L;

	/**
	 * 获取request
	 * */
	public HttpServletRequest getRequest(ActionInvocation invocation) {
		return (HttpServletRequest) invocation.getInvocationContext().get(
				StrutsStatics.HTTP_REQUEST);
	}

	/**
	 * 返回异常页面
	 * */
	public String ERROR(ActionInvocation invocation, String title, Object msg,
			String detailMsg) {
		Vmessage vmessage = new Vmessage(title, msg, detailMsg);
		getRequest(invocation).setAttribute(BaseAction.VAMS_MESSAGE, vmessage);
		return Action.ERROR;
	}

}
