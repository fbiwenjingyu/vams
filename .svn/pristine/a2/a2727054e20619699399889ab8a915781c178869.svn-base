package com.pd.cwgl.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.pd.cwgl.model.StoSetting;
import com.pd.system.utils.UITools;
import com.pd.system.utils.UITools.KVData;

public class CwglTools {

	/**
	 * 储位转换Qui select标签数据节点
	 * */
	public static String stoSettingToQUISelect(List<StoSetting> list,
			String type) {
		int len = list.size();
		List<KVData> kvlist = new ArrayList<KVData>(len);
		for (StoSetting setting : list) {
			kvlist.add(new KVData("第" + setting.getSid() + type, setting
					.getSid()));
		}
		return JSONObject.fromObject(UITools.getQUISelectDataToObject(kvlist))
				.toString();
	}

	/**
	 * 储位转换Qui select标签数据节点
	 * */
	public static String stoSettingToQUISelectWithBz(List<StoSetting> list,
			String type) {
		int len = list.size();
		List<KVData> kvlist = new ArrayList<KVData>(len);
		for (StoSetting setting : list) {
			String bz = setting.getCwsm();
			if (null == bz || "".equals(bz)) {
				bz = "";
			} else {
				bz = "（" + bz + "）";
			}
			kvlist.add(new KVData("第" + setting.getSid() + type + bz, setting
					.getSid()));
		}
		return JSONObject.fromObject(UITools.getQUISelectDataToObject(kvlist))
				.toString();
	}

	/**
	 * 切割档案格储位编号
	 * */
	public static String splitGeId(String cwbh) {
		return cwbh.substring(0, 5);
	}

}
