package com.pd.webservice.util;

import java.lang.reflect.Constructor;

/**
 * Class loading utility.
 * 
 * @author zy
 * @version 0.1
 */
public class ObjectFactory {
	
	/**
	 * Instantiate a {@link Class} instance for given {@linkplain className}.
	 * @param className
	 * @return
	 */
	public static Object getInstance(String className) {
		Object instance = null;
		try {
			Class<?> clazz = Class.forName(className, true, ObjectFactory.class.getClassLoader());
			instance = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * Instantiate a {@link Class} instance for given parameters:
	 * {@linkplain clazz} and {@linkplain parameters}.
	 * @param clazz
	 * @param parameters
	 * @return
	 */
	public static Object getInstance(Class<?> clazz, Object... parameters) {
		Object instance = null;
		try {
			Constructor<?>[] constructors = clazz.getConstructors();
			for(Constructor<?> c : constructors) {
				if(c.getParameterTypes().length==parameters.length) {
					instance = c.newInstance(parameters);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * Instantiate a {@link Class} instance for given parameters:
	 * {@linkplain className} and {@linkplain parameters}.
	 * @param className
	 * @param parameters
	 * @return
	 */
	public static Object getInstance(String className, Object... parameters) {
		Object instance = null;
		try {
			Class<?> clazz = Class.forName(className, true, ObjectFactory.class.getClassLoader());
			instance = getInstance(clazz, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
}
