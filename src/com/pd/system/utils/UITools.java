package com.pd.system.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * qui工具类
 * */
public class UITools {
	
	public static class KVData{
		private String key;
		private String value;
		public KVData() {
		}
		public KVData(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * QUI的select列表数据头key
	 * */
	public static final String QUI_SELECT_LIST_TAG = "list";

	/**
	 * 获取QUI的select数据块，返回Map格式
	 * 
	 * @param list
	 *            kv键值对列表
	 * */
	public static Map<String, List<KVData>> getQUISelectDataToObject(
			List<KVData> list) {
		Map<String, List<KVData>> map = new HashMap<String, List<KVData>>();
		map.put(QUI_SELECT_LIST_TAG, list);
		return map;
	}

	/**
	 * 获取QUI的select数据块，返回string格式
	 * 
	 * @param list
	 *            kv键值对列表，*注意key对应的是select显示值，value对应的是select的id

	public static String getQUISelectDataToString(List<Kval> list) {
		return JSONObject.fromObject(getQUISelectDataToObject(list)).toString();
	}
		 * */

	/**
	 * 获取QUI的select数据块（key与value反向），返回String格式
	 * */
	public static String getQUISelectDataToString(List<Kval> list) {
		int len = list.size();
		List<KVData> kvlist = new ArrayList<KVData>(len);
		for (Kval val : list) {
			kvlist.add(new KVData(val.getValue().toString(), val.getKey()));
		}
		return JSONObject.fromObject(getQUISelectDataToObject(kvlist))
				.toString();
	}
	
	/**
	 * 获取QUI的select数据块（key与value反向），返回String格式，带ID标识符
	 * */
	public static String getQUISelectDataToStringWithID(List<Kval> list) {
		int len = list.size();
		List<KVData> kvlist = new ArrayList<KVData>(len);
		for (Kval val : list) {
			String key=val.getKey();
			String value=val.getValue().toString();
			kvlist.add(new KVData(key+"："+value, key));
		}
		return JSONObject.fromObject(getQUISelectDataToObject(kvlist))
				.toString();
	}

}
