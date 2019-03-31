package com.pd.webservice.sixandone;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pd.webservice.core.HttpExecuter;
import com.pd.webservice.core.WsRet;

/**
 * 机动车信息读取接口
 */
public final class QueryVehInfo {

	public static final Logger logger = Logger.getLogger(QueryVehInfo.class);

	/**
	 * 通过号牌信息查询六合一历史数据，接口代码：01C21
	 * */
	public static WsRet _01C21(String hpzl, String hphm) {
		if (null == hpzl || null == hphm) {
			return null;
		}

		// 封装参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("hpzl", hpzl);
		param.put("hphm", hphm);

		return HttpExecuter.fastInvokeQuery("01C21", param, logger);
	}

	/**
	 * 通过流水号查询在办业务信息，接口代码：01C22
	 * */
	public static WsRet _01C22(String lsh) {
		if (null == lsh) {
			return null;
		}
		// 封装参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("lsh", lsh);

		return HttpExecuter.fastInvokeQuery("01C22", param, logger);
	}

	/**
	 * 通过流水号查询在历史务信息，接口代码：01C26
	 * */
	public static WsRet _01C26(String lsh) {
		if (null == lsh) {
			return null;
		}
		// 封装参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("lsh", lsh);

		return HttpExecuter.fastInvokeQuery("01C26", param, logger);
	}

	// ===============================================================
	public static void main(String[] args) {
		System.out.println(_01C22("11111111"));
	}

}
