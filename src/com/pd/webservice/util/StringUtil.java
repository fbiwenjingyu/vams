package com.pd.webservice.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created on 2012-8-24 
 * Original Author: zy 
 * Description: Common string helper
 * class
 * 
 * Changes ------- $Log$
 * 
 */
public class StringUtil {

    private static Log log = LogFactory.getLog(StringUtil.class);

    /**
     * Judge whether a string is null or not
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
	if (null == str || "".equals(str) || str.length() == 0
		|| str.trim().length() == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    public static JsonConfig getJsonConfig() {
	JsonConfig config = new JsonConfig();
	config.setIgnoreDefaultExcludes(false);
	config.registerJsonValueProcessor(java.util.Date.class,
		new JsonDateValueProcessor("yyyy-MM-dd  HH:mm:ss"));
	// 如果在json中发现java.util.Date类型的数据就用DateJsonValueProcessor类去处理数据
	config.registerJsonValueProcessor(java.sql.Timestamp.class,
		new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
	config.registerJsonValueProcessor(java.sql.Date.class,
		new JsonDateValueProcessor("yyyy-MM-dd"));
	return config;
    }

    /**  
     * 传入对象  
     * @param obj 前提obj存在set get方法  
     * @return返回对象存在的属性值  
     * @throws Exception  
     */  
    public static String ObjectParesToString(Object obj) throws Exception{   
        Class userClass = Class.forName(obj.toString().split("@")[0]);//加载类   
        Field[] fields = userClass.getDeclaredFields();//获得对象方法集合   
        String fdname=null;   
        Method metd = null;   
            for (Field field : fields) {// 遍历该数组   
                 fdname = field.getName();// 得到字段名，   
                 metd = userClass.getMethod("get" + change(fdname), null);// 根据字段名找到对应的get方法，null表示无参数   
                   Object name = metd.invoke(obj, null);// 调用该字段的get方法   
                     if(name!=null){   
                        System.out.println(name);   
                     }   
             }   
            return null;   
    }   
    
    /**  
     * @param src  源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null  
     */  
    public static String change(String src) {   
        if (src != null) {   
            StringBuffer sb = new StringBuffer(src);   
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            return sb.toString();   
        } else {   
            return null;   
        }   
    }  
    
    /**
     * 将中文字符转换为utf-8格式
     * @param xmlDoc
     * @return
     */
    public static String encodeUTF8(String xmlDoc) {
	String str = "";
	try {
	    str = URLEncoder.encode(xmlDoc, "utf-8");
	    return str;
	} catch (Exception e) {
	    str = e.toString();
	}
	return str;
    }
    
    /**
     * 将utf-8格式字符转换为中文字符
     * @param str
     * @return
     */
    public static String decodeUTF8(String str) {
	String xmlDoc = "";
	try {
	    xmlDoc = URLDecoder.decode(str, "utf-8");
	} catch (Exception e) {
	    xmlDoc = e.toString();
	}
	return xmlDoc;
    }

    public static String createZycId(){
    	Random r = new Random();
    	int rInt = r.nextInt(999);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
    	return "999"+sdf.format(new Date()) + rInt;
    }
}
