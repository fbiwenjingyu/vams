package com.pd.webservice.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/**
 * 请求六合一接口请求响应对象，提供相关结果
 * 
 */
public final class ResponseVO {

	// 中间件接口地址
	private String uri;
	// http数据类型
	private String contentType;
	// http响应代码
	private int statusCode;
	// http返回数据
	private String respDoc;
	// 返回结果编码
	private String responseCharset;


	// ws处理结果
	private int wsCode;
	// 调用结果说明
	private String wsMsg;
	// 返回的数据列表
	private List<Map<String, String>> wsList;

	public ResponseVO() {
	}

	/**
	 * 创建一个失败的返回结果
	 * */
	public ResponseVO(int wsCode, String wsMsg) {
		super();
		this.wsCode = wsCode;
		this.wsMsg = wsMsg;
	}

	/**
	 * 处理 调用成功的响应结果，将其转换成JSON
	 * */
	public ResponseVO executeResp() {
		//将字符串转换json对象
		JSONArray array = JSONArray.fromObject(respDoc);
		// 转换返回结果代码
		wsCode = Integer.parseInt(array.get(0).toString());
		// 如果等于100，则表示成功
		if (wsCode == WsConsts.INVOKE_WS_OK) {
			// 获取json返回数组的第二个元素
			Object obj = array.get(1);
			// 如果是list
			if (obj instanceof List) {
				wsList = (List<Map<String, String>>) obj;
			} else
			// 如果是map
			if (obj instanceof Map) {
				wsList = new ArrayList<Map<String, String>>(1);
				wsList.add((Map<String, String>) obj);
			}else{
				wsList = new ArrayList<Map<String, String>>(0);
			}
			wsMsg = "六合一数据调用成功";
		} else {
			wsMsg = array.get(1).toString();
		}
		return this;
	}

	/**
	 * 获取返回结果对象
	 * */
	public WsRet getRespRet() {
		// 获取调用结果
		if (wsCode == WsConsts.INVOKE_WS_OK) {
			// 调用成功
			return new WsRet(true, wsCode, wsMsg, wsList);
		} else {
			// 调用失败
			return new WsRet(false, wsCode, wsMsg, wsList);
		}
	}

	// ==========================================================
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("接口地址：");
		sb.append(getUri());
		sb.append("\nHTTP处理结果（statusCode）：");
		sb.append(getStatusCode());
		sb.append("\nHTTP数据格式（contentType）：");
		sb.append(getContentType());
		sb.append("\nWS返回结果：");
		sb.append(wsCode);
		sb.append(" - ");
		sb.append(wsMsg);
		return sb.toString();
	}

	// ============================================================

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getRespDoc() {
		return respDoc;
	}

	public void setRespDoc(String respDoc) {
		this.respDoc = respDoc;
	}

	public int getWsCode() {
		return wsCode;
	}

	public List<Map<String, String>> getWsList() {
		return wsList;
	}

	public String getWsMsg() {
		return wsMsg;
	}

	public String getResponseCharset() {
		return responseCharset;
	}

	public void setResponseCharset(String responseCharset) {
		this.responseCharset = responseCharset;
	}

}
