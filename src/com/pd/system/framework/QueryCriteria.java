package com.pd.system.framework;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 查询条件封装
 * */
public class QueryCriteria {

	/**
	 * 定义为大写
	 * */
	public static final int UpperCase = 0x1;
	/**
	 * 定义为小写
	 * */
	public static final int LowerCase = 0x2;
	/**
	 * 不区分大小写
	 * */
	public static final int NoCase = 0x3;

	/**
	 * 获取大小写区分条件map
	 * */
	public static Map<String, Integer> getCaseMap() {
		return new HashMap<String, Integer>();
	}

	private static Logger logger = Logger.getLogger(QueryCriteria.class);

	/**
	 * 检测是否需要模糊查询
	 * */
	public static boolean checkFuzzy(String fuzzy) {
		// 如果为空或则精确查询
		if (null == fuzzy || "".equals(fuzzy)) {
			return false;
		} else {
			// 是否模糊查询
			return "1".equals(fuzzy.trim());
		}
	}

	/**
	 * 验证所有条件，仅用于相等（模糊）连接，只对字符串有效
	 * 
	 * @param bean
	 *            参数实例bean对象
	 * @param criteria
	 *            查询条件对象
	 * */
	public static Object checkAllStringByEquals(Object bean, Object criteria,
			String fuzzy) {
		return checkAllStringByEquals(bean, criteria, checkFuzzy(fuzzy));
	}

	/**
	 * 验证所有条件，仅用于相等（模糊）连接，只对字符串有效，全部转换为大写
	 * 
	 * @param bean
	 *            参数实例bean对象
	 * @param criteria
	 *            查询条件对象
	 * */
	public static Object checkAllStringByCaseByEquals(Object bean,
			Object criteria, String fuzzy, Map<String, Integer> caseMap) {
		return checkAllStringByCaseByEquals(bean, criteria, checkFuzzy(fuzzy),
				caseMap);
	}

	/**
	 * 验证所有条件，仅用于相等（模糊）连接，只对字符串有效，全部转换为大写
	 * 
	 * @param bean
	 *            参数实例bean对象
	 * @param criteria
	 *            查询条件对象
	 * */
	public static Object checkAllStringByEquals(Object bean, Object criteria,
			boolean fuzzy) {
		if (null == bean) {
			return criteria;
		}
		try {
			Class beanClass = bean.getClass();
			Class criteriaClass = criteria.getClass();
			// 获取该类的所有有效属性
			Field[] fields = beanClass.getDeclaredFields();
			// 循环获取属性
			for (Field field : fields) {
				// 属性名
				String fieldName = field.getName();
				// 调用get方法获取值
				Object getVal = new PropertyDescriptor(fieldName, beanClass)
						.getReadMethod().invoke(bean);
				// null判断，如果为字符串实例，那么一定不等于null，null不是字符实例，所以这里就不在判断null
				if (getVal instanceof CharSequence) {
					// 如果不为空
					if (!"".equals(getVal.toString().trim())) {
						// 获取属性名
						String name = fieldName.replaceFirst(
								fieldName.substring(0, 1),
								fieldName.substring(0, 1).toUpperCase());
						// 模糊查询
						if (fuzzy) {
							String fuzzyMethodName = "and" + name + "Like";
							criteriaClass.getDeclaredMethod(fuzzyMethodName,
									String.class).invoke(
									criteria,
									"%"
											+ getVal.toString().trim()
													.toUpperCase() + "%");
						} else {
							String fuzzyMethodName = "and" + name + "EqualTo";
							criteriaClass.getDeclaredMethod(fuzzyMethodName,
									String.class).invoke(criteria,
									getVal.toString().trim().toUpperCase());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("验证条件方法调用异常", e);
			return criteria;
		}
		return criteria;
	}

	/**
	 * 验证所有条件，仅用于相等（模糊）连接，只对字符串有效，可以选择大小写转换
	 * 
	 * @param bean
	 *            参数实例bean对象
	 * @param criteria
	 *            查询条件对象
	 * */
	public static Object checkAllStringByCaseByEquals(Object bean,
			Object criteria, boolean fuzzy, Map<String, Integer> caseMap) {
		if (null == bean) {
			return criteria;
		}
		try {
			Class beanClass = bean.getClass();
			Class criteriaClass = criteria.getClass();
			// 获取该类的所有有效属性
			Field[] fields = beanClass.getDeclaredFields();
			// 循环获取属性
			for (Field field : fields) {
				// 属性名
				String fieldName = field.getName();
				// 调用get方法获取值
				Object getVal = new PropertyDescriptor(fieldName, beanClass)
						.getReadMethod().invoke(bean);
				// null判断，如果为字符串实例，那么一定不等于null，null不是字符实例，所以这里就不在判断null
				if (getVal instanceof CharSequence) {
					// 如果不为空
					if (!"".equals(getVal.toString().trim())) {
						// 获取属性名
						String name = fieldName.replaceFirst(
								fieldName.substring(0, 1),
								fieldName.substring(0, 1).toUpperCase());
						// 获取属性值
						String val = getVal.toString().trim();
						//大小写转换字段
						Integer caseVal=caseMap.get(fieldName);
						if (null != caseVal) {
							if (LowerCase == caseVal) {
								val = val.toLowerCase();
							} else if (NoCase == caseVal) {
								//不转换大小写
							}else{
								val = val.toUpperCase();//转换大写
							}
						}else{
							val = val.toUpperCase();//转换大写
						}
						// 模糊查询
						if (fuzzy) {
							String fuzzyMethodName = "and" + name + "Like";
							criteriaClass.getDeclaredMethod(fuzzyMethodName,
									String.class).invoke(criteria,
									"%" + val + "%");
						} else {
							String fuzzyMethodName = "and" + name + "EqualTo";
							criteriaClass.getDeclaredMethod(fuzzyMethodName,
									String.class).invoke(criteria, val);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("验证条件方法调用异常", e);
			return criteria;
		}
		return criteria;
	}

	// =====================================

}
