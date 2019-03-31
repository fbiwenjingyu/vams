package com.pd.webservice.core;

import java.util.List;
import java.util.Map;

/**
 * ws接口调用返回对象
 * */
public final class WsRet {
	/**
	 * 合并条码返回的code
	 * W0 该外检记录不存在
	 * W1 该外检记录已经合并
	 * A0 该档案记录已经合并
	 * SUCCESS 合并成功
	 */
	private String code;
	/**
	 * 是否调用成功
	 * */
	private boolean isOK;
	/**
	 * 调用返回code
	 * */
	private int wsCode;
	/**
	 * 调用结果说明
	 * */
	private String wsMsg;

	/**
	 * 调用返回数据
	 * */
	private List<Map<String, String>> dataList;

	public WsRet() {
	}

	/**
	 * 创建一个异常的返回值
	 * */
	public WsRet(String wsMsg) {
		this.isOK = false;
		this.wsCode = 0;
		this.wsMsg = wsMsg;
		this.dataList = null;
	}

	public WsRet(boolean isOK, int wsCode, String wsMsg,
			List<Map<String, String>> dataList) {
		super();
		this.isOK = isOK;
		this.wsCode = wsCode;
		this.wsMsg = wsMsg;
		this.dataList = dataList;
	}
	
	/**
	 * 获取唯一的结果，【这是一个快速方法】<br>
	 * 如果仅有一个结果则返回一个，如果有多个结果或者没有结果，则返回null
	 * */
	public Map<String, String> getSingleMap() {
		if (null != dataList && dataList.size() == 1) {
			return dataList.get(0);
		} else {
			return null;
		}
	}

	//===================================
	public int getWsCode() {
		return wsCode;
	}

	public String getWsMsg() {
		return wsMsg;
	}

	/**
	 * 获取接口数据列表
	 * */
	public List<Map<String, String>> getDataList() {
		return dataList;
	}

	public boolean isOK() {
		return isOK;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	//===================================
	@Override
	public String toString() {
		return new StringBuffer("调用结果：").append(isOK).append(" - ")
				.append(wsCode).append(" - ").append(wsMsg).toString();
	}

}
