package com.pd.system.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

/**
 * 配置文件基础类
 * */
public class PropertiesConfig {

	// 是否在刷新
	private boolean isFlush = false;

	// 配置文件
	private String configFileName;
	// 配置对象
	private PropertiesConfiguration configuration;
	// 日志对象
	private Logger logger = Logger.getLogger(this.getClass());
	// 配置信息List
	private List<Map<String,String>> configList = new ArrayList<Map<String,String>>();
	// keys
	private List<String> keys = new ArrayList<String>();
	// values
	private List<String> values = new ArrayList<String>();

	/**
	 * 无参构造，请使用loadConfig载入配置
	 * */
	public PropertiesConfig() {
	}

	/**
	 * 直接载入配置文件
	 * */
	public PropertiesConfig(String configFileName) {
		this.configFileName = configFileName;
		loadConfig();
	}

	// 载入配置
	private void loadConfig() {
		try {
			configuration = new PropertiesConfiguration(configFileName);
			configuration
					.setReloadingStrategy(new FileChangedReloadingStrategy());// 文件属性改变后可以加载策略
			// configuration.setAutoSave(true);//自动保存
			logger.info("=======配置文件：" + configFileName + " 加载成功！=======");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			logger.error("=======配置文件：" + configFileName + " 加载失败！=======");
		}
		loadList();// 载入map
	}

	// 载入map配置对象
	@SuppressWarnings("unchecked")
	private void loadList() {
		isFlush = true;
		// 清除map对象信息
		configList.clear();
		Iterator<String> ite = configuration.getKeys();
		while (ite.hasNext()) {
			String key = ite.next();
			String value = configuration.getString(key);
			Map map = new HashMap();
			map.put(key, value);
			// 载入keys
			keys.add(key);
			// 载入values
			values.add(value);
			// 载入k-v映射对象
			configList.add(map);
		}
		isFlush = false;
	}

	// =====================公开方法============================

	/**
	 * 载入配置，可以替代构造函数进行载入
	 * */
	public void loadConfig(String configFileName) {
		this.configFileName = configFileName;
		loadConfig();
	}

	/**
	 * 获取当前的配置文件名称
	 * */
	public String getConfigFileName() {
		return configFileName;
	}

	/**
	 * 获取当前的配置对象
	 * */
	public PropertiesConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * 获取配置list
	 * */
	public List<Map<String,String>> getConfigList() {
		if (isFlush) {
			return new ArrayList<Map<String,String>>();
		}
		return configList;
	}

	/**
	 * 获取配置的key list
	 * */
	public List<String> getKeys() {
		if (isFlush) {
			return new ArrayList<String>();
		}
		//复制列表
		List<String> ks=new ArrayList<String>();
		for (int i = 0; i < keys.size(); i++) {
			ks.add(keys.get(i));
		}
		return ks;
	}

	/**
	 * 获取配置的value list
	 * */
	public List<String> getValues() {
		if (isFlush) {
			return new ArrayList<String>();
		}
		//复制列表
		List<String> vs=new ArrayList<String>();
		for (int i = 0; i < values.size(); i++) {
			vs.add(values.get(i));
		}
		return vs;
	}

	/**
	 * 获取配置
	 * */
	public String getConfig(String key) {
		if (isFlush) {
			return null;
		}
		return configuration.getString(key);
	}

	/*
	 * 保存配置文件
	 */
	private boolean save() {
		try {
			configuration.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 添加配置项
	 * 
	 * @param key
	 * @param val
	 * @param saveToFile
	 *            是否保存到配置文件
	 * */
	public synchronized boolean addConfig(String key, Object val,
			boolean saveToFile) {
		if (isFlush) {// 如果再刷新，则禁止添加
			return false;
		}
		boolean ret = true;
		configuration.addProperty(key, val);
		if (saveToFile) {// 是否保存到文件
			ret = save();
			flushConfig(true);// 刷新，清除缓存
		} else {
			flushConfig(false);// 刷新，不清除缓存
		}
		return ret;
	}

	/**
	 * 刷新配置文件
	 * 
	 * @param isClearTemp
	 *            是否清除缓存
	 * */
	public synchronized boolean flushConfig(boolean isClearTemp) {
		if (isClearTemp) {
			configuration.reload();// 重载，清除缓存
		}
		loadList();
		return true;
	}
	
	/**
	 * 修改一项配置
	 * */
	public synchronized boolean updateConfig(String key,Object val){
		if (isFlush) {
			return false;
		}
		configuration.setProperty(key, val);
		return save();
	}

	/**
	 * 清除一项属性
	 * */
	public boolean removeConfig(String key) {
		if (isFlush) {
			return false;
		}
		configuration.clearProperty(key);
		return save();
	}
}
