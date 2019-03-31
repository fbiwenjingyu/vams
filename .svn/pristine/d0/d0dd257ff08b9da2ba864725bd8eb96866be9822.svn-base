package com.pd.system.utils;

/**
 * 生成序号帮助类
* @ClassName: SeqNumberUtil 
* @Description: TODO
* @author zl
* @date 2014-8-4 下午02:04:32 
*
 */
public class SeqNumberUtil {
	
	/**
	 * 生成左侧补零的序号
	* @Title: addZeroForNum 
	* @Description: TODO
	* @param @param str
	* @param @param strLength
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	public static String addZeroForNum(String str, int strLength) {
		if(null==str || str.equals("") ){
			str="1";
		}
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);//左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	 }
	
	public static void main(String[] arg){
		String str = addZeroForNum("",14);
		System.out.println("------"+str);
	}
}


