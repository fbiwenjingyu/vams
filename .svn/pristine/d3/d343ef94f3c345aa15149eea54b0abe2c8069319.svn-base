package com.pd.system.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

/**
 * 登录过滤页面 加载对象
 * @author BraveHeartWzm
 * */
public class FilterConfig implements Serializable{
	
	/*
	 * 刷新标记符，如果正在刷新，则为true
	 * */
	private static boolean flushing=false;
	
	private static final long serialVersionUID = -7119354099841626030L;
	
	private static final String configFile="filterConfig.properties";
	
	private static FilterConfig filterConfig;
	private PropertiesConfiguration configuration;
	private  Logger logger = Logger.getLogger(this.getClass());
	
	private static final List<String> FILTER_LIST;
	
	static{
		FILTER_LIST=new ArrayList<String>();
		filterConfig=new FilterConfig();
	}
	
	public FilterConfig() {
		try {
			configuration=new PropertiesConfiguration(configFile);
			configuration.setReloadingStrategy(new FileChangedReloadingStrategy());//允许动态加载属性
//			configuration.setAutoSave(true);
			initConfig();//加载配置文件信息
			logger.info("========filterConfig 加载成功！========");
		} catch (Exception e) {
			logger.info("========filterConfig 加载失败！========");
			configuration=new PropertiesConfiguration();
		}
	}
	
	
	/*
	 * 初始化配置list
	 * */
	private void initConfig(){
		FILTER_LIST.clear();
		Iterator<String> ite=configuration.getKeys();
		while (ite.hasNext()) {
			String val=configuration.getString(ite.next());
			FILTER_LIST.add(val);
		}
	}
	
	/*
	 * 获取配置对象实例
	 * */
	private static FilterConfig getInstence(){
		return filterConfig;
	}
	
	/**
	 * 刷新配置
	 * @param isClearTemp 是否清除缓存中临时的未保存到配置文件的配置信息
	 * */
	public static synchronized boolean flushConfig(boolean isClearTemp){
		flushing=true;
		List<String> saveList=new ArrayList<String>(FILTER_LIST);
		try {
			if (isClearTemp) {
				getInstence().configuration.reload();//刷新，将内存中的数据清除，重新载入配置文件
			}
			FILTER_LIST.clear();
			getInstence().initConfig();
		} catch (Exception e) {
			getInstence().logger.info("========filterConfig 刷新异常："+e.getMessage()+"========");
			//还原配置
			FILTER_LIST.clear();
			FILTER_LIST.addAll(saveList);
			flushing=false;
			return false;
		}
		flushing=false;
		getInstence().logger.info("========filterConfig 刷新成功！========");
		return true;
	}
	
	/**
	 * 获取配置列表
	 * */
	public static List<String> getNotFilterList(){
		if (flushing) {
			return new ArrayList<String>();
		}
		return new ArrayList<String>(FILTER_LIST);
	}
	
	/**
	 * 获取配置
	 * */
	public static String getConfig(String key){
		if (flushing) {
			return "";
		}
		return getInstence().configuration.getString(key);
	}

	
	/**
	 * 添加配置，此方法会刷新配置，但不会清除临时配置
	 * @param key
	 * @param val
	 * @param isSaveToFile 是否保存到配置文件，如果不保存，数据将临时存在
	 * */
	public static boolean addConfig(String key,Object val,boolean isSaveToFile){
		PropertiesConfiguration configuration=getInstence().configuration;
		configuration.addProperty(key, val);
		if (isSaveToFile) {
			try {
				configuration.save();
				flushConfig(false);//清除临时数据
			} catch (ConfigurationException e) {
				return false;
			}
		}else{
			flushConfig(false);//清除临时数据
		}
		return true;
	}

}
