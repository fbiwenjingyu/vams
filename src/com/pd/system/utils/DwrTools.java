package com.pd.system.utils;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.ServerContextFactory;

/**
 * DWR 推送工具
 * */
public class DwrTools {

	/**
	 * 推送信息到任意页面
	 * 
	 * @param url
	 *            需要推送到的路径，如果jsp页面依赖于action产生，则需要推送到action,例如： xxxx.action
	 * @param functionName
	 *            页面上的javascript函数名
	 * @param message
	 *            需要推送到页面javascript的函数入参值
	 * @author braveheartwzm
	 * */
	public static void pushToAnyPage(String url, final String functionName,
			final Object... message) {
		Browser.withPage(ServerContextFactory.get().getContextPath() + "/"
				+ url, new Runnable() {
			public void run() {
				ScriptSessions.addFunctionCall(functionName, message);
			}
		});
	}

}
