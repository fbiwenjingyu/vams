package com.pd.system.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionInvocation;
import com.pd.right.action.LoginAction;
import com.pd.system.framework.BaseInterceptor;

public class SessionInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 1923523949945251995L;

	private static final String TAG_SESSION = "session";

	/**
	 * 用于设置session的快捷调用名称
	 * */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		if (!(invocation.getAction() instanceof LoginAction)) {
			// 当在此之前的拦截器调用完action，将session存入request中
			HttpServletRequest request = getRequest(invocation);
			Map<String, Object> session = invocation.getInvocationContext()
					.getSession();
			request.setAttribute(TAG_SESSION, session);
		}

		// =========↑↑其他拦截器之前调用↑↑============
		String invret = invocation.invoke();// 调用下一个interceptor或者action
		// =========↓↓其他拦截器之后调用↓↓============

		return invret;
	}

}
