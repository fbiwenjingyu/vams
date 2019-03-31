package com.pd.webservice.core;

import com.pd.system.startup.DBResource;

/**
 * 请求六合一接口常量类
 * 
 */
public final class WsConsts {

	/**
	 * 加载参数
	 * */
	static {
		// 连接超时，如果存在配置参数，则默认
		String conn_tt = DBResource
				.getSysAttrName(("fetcher.connection_timeout"));
		if (null != conn_tt) {
			CONN_TIMEOUT_TIME = Integer.parseInt(conn_tt);
		} else {
			CONN_TIMEOUT_TIME = 30000;
		}

		// 请求超时，如果存在配置参数，则默认
		String sock_tt = DBResource.getSysAttrName("fetcher.socket_timeout");
		if (null != sock_tt) {
			SOCK_TIMEOUT_TIME = Integer.parseInt(sock_tt);
		} else {
			SOCK_TIMEOUT_TIME = 30000;
		}

	}

	/**
	 * 调用ws成功返回参数
	 * */
	public static final int INVOKE_WS_OK = 100;

	public static final String JKID_STR = "jkid";

	/**
	 * 六合一接口服务中间件地址
	 * */
	public static String TMRI_WS_URL = DBResource.getSysAttrName("tmri_ws_url");

	/**
	 * 标准查询接口 方法地址
	 * */
	public static String QUERY_RUL = TMRI_WS_URL + "query.action";
	/**
	 * 标准写入接口 方法低值
	 * */
	public static String WRITE_URL = TMRI_WS_URL + "write.action";

	/**
	 * 查询
	 * */
	public static final String QUERY = "query";
	/**
	 * 写入
	 * */
	public static final String WRITE = "write";

	/**
	 * http连接超时时间
	 * */
	public static int CONN_TIMEOUT_TIME;
	/**
	 * http请求超时时间
	 * */
	public static int SOCK_TIMEOUT_TIME;

	/**
	 * http request 请求头部参数
	 * */
	public static class REQ_HEADER {

	}

}
