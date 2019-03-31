package com.pd.system.control;

import com.opensymphony.xwork2.ActionInvocation;
import com.pd.system.framework.BaseInterceptor;
import com.pd.system.startup.SYSConfig;

/**
 * 全局异常监测拦截器
 * */
public class GlobalExceptionInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 2954202398795436409L;

	private boolean showError = true;

	/**
	 * 全局检测拦截字符串
	 * */
	private static final String geip = SYSConfig.getGlobalExceptionInterceptPrefix();

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		if (!showError) {
			return invocation.invoke();
		} else {
			String inval = null;
			try {
				inval = invocation.invoke();
			} catch (Exception e) {
				e.printStackTrace();

				String exceptionVal = "<br>原因：<br>" + e.toString()
						+ "<br><br>详细说明：<br>";
				StackTraceElement element[] = e.getStackTrace();
				for (int i = 0; i < element.length; i++) {
					String ele = element[i].toString();
					if (ele.indexOf(geip) != -1) {
						exceptionVal += ele + "<br>";
					}
				}
				return ERROR(invocation, "处理异常", "处理异常！", exceptionVal);
			}
			return inval;

		}

	}

}
