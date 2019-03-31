package com.pd.system.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * spring DAO 工厂类
 * 
 * @author zy
 * @modify wzm
 * */
public class DAOFactory implements ApplicationContextAware {

	// public static BeanFactory factory = new
	// ClassPathXmlApplicationContext("classpath:/applicationContext-common.xml");

	private static BeanFactory factory;

	private static JdbcTemplate jdbcTemplate;

	/** 阻止重复new对象 */
	private DAOFactory() {
	}

	/**
	 * 创建DAO对象
	 * 
	 * @param beanName
	 *            从spring文件中的取beanName
	 * @return Object DAO对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDao(String beanName) {
		return (T) factory.getBean(beanName);
	}

	/**
	 * 创建DAO对象
	 * 
	 * @param daoClass
	 *            从spring文件中的取beanName
	 * @return Object DAO对象
	 * @author BraveHeartWzm
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> daoClass) {
		return getDao(daoClass.getSimpleName());
	}
	
	/**
	 * 创建DAO对象
	 * 
	 * @param daoClass
	 *            从spring文件中的取beanName
	 * @return Object DAO对象
	 * @author BraveHeartWzm
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> serviceClass) {
		return getService(serviceClass.getSimpleName());
	}
	
	/**
	 * 创建DAO对象
	 * 
	 * @param daoClass
	 *            从spring文件中的取beanName
	 * @return Object DAO对象
	 * @author BraveHeartWzm
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(String servicename) {
		return (T) factory.getBean(servicename);
	}

	/**
	 * 创建DAO对象
	 * 
	 * @param beanName
	 *            从spring文件中的取beanName
	 * @return Object DAO对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getDirectDao(String beanName) {
		return getDao(beanName);
	}

	/**
	 * 获取JdbcTemplate
	 * */
	public static JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 获取任意jdbcTemplate
	 * */
	public static JdbcTemplate getAnyJdbcTemplate(String springId) {
		return getDao(springId);
	}

	// ======================set ===========================
	// 注入spring上下文
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		DAOFactory.factory = applicationContext;
	}

	// 注入基础jdbctemplate
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		DAOFactory.jdbcTemplate = jdbcTemplate;
	}

	
}
