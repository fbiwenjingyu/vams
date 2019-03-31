package com.pd.system.startup;

import com.pd.system.utils.PropertiesConfig;

/**
 * 系统配置文件配置
 * */
public final class SYSConfig {

	/** 载入系统配置文件 */
	private static PropertiesConfig sysConfig = new PropertiesConfig(
			"system_config.properties");

	/**
	 * 只能同包可以被实例化，载入当前的配置
	 * */
	SYSConfig() {
		loadFilterList();
	}

	/**
	 * 系统参数载入，载入相关参数
	 * */
	public static void load() {
	}

	/**
	 * 载入不拦截列表参数
	 */
	private static void loadFilterList() {
	}

	// ==================公开方法=========================
	/**
	 * 获取每页条数
	 * */
	public static int getPageItem() {
		int pi=20;
		try {
			String pageitem=sysConfig.getConfig("page_item");
			if (null != pageitem) {
				pi= Integer.parseInt(pageitem);
			}
		} catch (Exception e) {
		}
		return pi;
	}

	/**
	 * 获取菜单图标路径
	 * */
	public static String getMenuIconPath() {
		return sysConfig.getConfig("menu_icon_path");
	}

	/**
	 * 全局异常拦截前缀
	 * */
	public static String getGlobalExceptionInterceptPrefix() {
		String str= sysConfig.getConfig("global_exception_intercept_prefix");
		if (null == str) {
			return "com";
		}else{
			return str;
		}
	}

	// -------------------------------------------------------

	/**
	 * 获取系统配置
	 * */
	public static String getSystemConfig(String key) {
		return sysConfig.getConfig(key);
	}

	public static void main(String[] args) {
		// System.out.println(getDefaultLoginPage());
		String fprq = "2014-1-20 11:11:11.0";
		System.out.println(fprq.substring(0, fprq.length() - 2));
	}

}
