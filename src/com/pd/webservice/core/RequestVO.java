package com.pd.webservice.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

/**
 * http请求对象，提供相关参数
 * */
public final class RequestVO {

	/**
	 * 获取查询对象
	 * */
	public static RequestVO getQueryData(String jkid, Map<String, String> param) {
		return new RequestVO(jkid, WsConsts.QUERY_RUL, WsConsts.QUERY, param);
	}

	/**
	 * 获取写入对象
	 * */
	public static RequestVO getWriteData(String jkid, Map<String, String> param) {
		return new RequestVO(jkid, WsConsts.WRITE_URL, WsConsts.WRITE, param);
	}

	// =================================================

	/**
	 * 请求编码方式，默认为UTF-8
	 * */
	private String requestCharset = "UTF-8";

	/**
	 * 访问地址
	 * */
	private String url;
	/**
	 * 接口id
	 * */
	private String jkid;

	/**
	 * 接口类型
	 * */
	private String jklx;

	/**
	 * 参数
	 * */
	private List<NameValuePair> params;

	private RequestVO(String jkid, String url, String jklx,
			Map<String, String> param) {

		this.url = url;
		this.jklx = jklx;
		this.jkid = jkid;
		// 创建请求参数数组
		params = new ArrayList<NameValuePair>();
		// 添加接口id
		Iterator<String> keys = param.keySet().iterator();
		while (keys.hasNext()) {
			String pname = keys.next();
			String pvalue = param.get(pname);
			if (null != pvalue) {
				params.add(new NameValuePair(pname, pvalue));
			}
		}

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJkid() {
		return jkid;
	}

	public void setJkid(String jkid) {
		this.jkid = jkid;
	}

	public String getJklx() {
		return jklx;
	}

	public void setJklx(String jklx) {
		this.jklx = jklx;
	}

	public String getRequestCharset() {
		return requestCharset;
	}

	public void setRequestCharset(String requestCharset) {
		requestCharset = requestCharset;
	}

	/**
	 * 获取接口参数，已将接口id放入
	 * */
	public List<NameValuePair> getParams() {
		// 将接口id放入
		params.add(new NameValuePair(WsConsts.JKID_STR, jkid));
		return params;
	}

	/**
	 * 获取接口参数的数组
	 * */
	public NameValuePair[] getParamsToArray() {
		List<NameValuePair> list = getParams();
		return list.toArray(new NameValuePair[list.size()]);
	}

	/**
	 * 添加一个参数
	 * */
	public void addParam(String key, String value) {
		if (null != params) {
			params.add(new NameValuePair(key, value));
		}
	}

}
