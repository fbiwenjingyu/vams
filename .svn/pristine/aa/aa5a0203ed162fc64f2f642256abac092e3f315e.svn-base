package com.pd.system.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 字符串工具类
 * */
public class StringTools {

	/**
	 * 0~F的char数组，数组长度为16
	 * */
	public static final char[] HASH_16 = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 0~9,a~z,A~F的char数组，数组长度62
	 * */
	public static final char[] HASH_ALL = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	private StringTools() {
	}

	public static boolean isNotEmpty(String str) {
		if (null != str && str.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取16进制hash值
	 * 
	 * @param length
	 *            hash值长度
	 * */
	public static String getHash16(int length) {
		String val = "";
		for (int i = 0; i < length; i++) {
			val += HASH_16[(int) (Math.random() * 16)];
		}
		return val;
	}

	/**
	 * 获取hash值，全字段
	 * 
	 * @param length
	 *            hash值长度
	 * */
	public static String getHashAll(int length) {
		String val = "";
		for (int i = 0; i < length; i++) {
			val += HASH_ALL[(int) (Math.random() * 62)];
		}
		return val;
	}

	/**
	 * 截取字符串并去除空格
	 * 
	 * @param str
	 *            需要截取的字符串
	 * @param split
	 *            分隔符
	 * @return 字符串数组
	 * */
	public static String[] splitString(String str, String split) {
		String arr[] = str.split(split);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}

	/**
	 * 将id截取并去除空格，舍弃值为空的数组元素，分隔符为分号即：";"
	 * 
	 * @param str
	 *            需要截取的字符串
	 * @return 字符串数组
	 * */
	public static List<String> getIds(String str) {
		if (null == str || "".equals(str.trim())) {
			return new ArrayList<String>(0);
		} else {
			String arr[] = StringTools.splitString(str, ";");
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < arr.length; i++) {
				if (null != arr[i] && !"".equals(arr[i].trim())) {
					list.add(arr[i]);
				}
			}
			return list;
		}
	}

	/**
	 * 将id截取并去除空格，舍弃值为空的数组元素，分隔符为分号即：";"
	 * 
	 * @param str
	 *            需要截取的字符串
	 * @return 字符串数组
	 * */
	public static String[] getIdsToArray(String str) {
		String arr[] = StringTools.splitString(str, ";");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			if (null != arr[i] && !"".equals(arr[i].trim())) {
				list.add(arr[i]);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 去掉重复的值<br>
	 * 实现原理：return new ArrayList<T>(new HashSet<T>(list));
	 * */
	public static <T> List<T> getDifferentString(List<T> list) {
		return new ArrayList<T>(new HashSet<T>(list));
	}

	/**
	 * 去掉字符串最后的0 如：152800 >> 1528
	 * 
	 * @param str
	 * @return
	 * 
	 * @author wangwei 2013-7-9
	 */
	public static String removeStrLastZero(String str) {
		if (!str.substring(str.length() - 1).equals("0")) {
			return str;
		} else {
			return removeStrLastZero(str.substring(0, str.length() - 1));
		}
	}

	/**
	 * 左填充字符串
	 * 
	 * @param res
	 *            需要被填充的字符串
	 * @param length
	 *            填充后的字符串长度，如果长度比输入的字符串短，则将原字符串从左向右截取length长度
	 * @param pad
	 *            填充字符串，默认为空格即" "
	 * @author BraveHeartWzm
	 * */
	public static String lpad(String res, int length, String pad) {
		String ret = "";
		if (length < res.length()) {// 如果小于原长度，则截取新长度
			ret = res.substring(0, length);
		} else if (length == res.length()) {// 如果相等，则直接返回
			ret = res;
		} else {// 否则大于，则进行补位操作
			int needlen = length - res.length();// 计算需要补多少位
			if (null == pad || "".equals(pad)) {// 如果为空或者null，直接用空格即" "补位
				for (int i = 0; i < needlen; i++) {
					ret += " ";
				}
				ret = ret + res;
			} else {// 如果不为空则用给出的参数进行补位
				int roll = needlen / pad.length();// 计算出需要完成循环补位字符串的循环次数
				String tail = pad.substring(0, needlen % pad.length());// 计算出需要补位的余数位的长度
				for (int i = 0; i < roll; i++) {
					ret += pad;
				}
				ret = ret + tail + res;
			}
		}
		return ret;
	}

	/**
	 * 右填充字符串
	 * 
	 * @param res
	 *            需要被填充的字符串
	 * @param length
	 *            填充后的字符串长度，如果长度比输入的字符串短，则将原字符串从左向右截取length长度
	 * @param pad
	 *            填充字符串，默认为空格即" "
	 * @author BraveHeartWzm
	 * */
	public static String rpad(String res, int length, String pad) {
		String ret = "";
		if (length < res.length()) {// 如果小于原长度，则截取新长度
			ret = res.substring(0, length);
		} else if (length == res.length()) {// 如果相等，则直接返回
			ret = res;
		} else {// 否则大于，则进行补位操作
			int needlen = length - res.length();// 计算需要补多少位
			if (null == pad || "".equals(pad)) {// 如果为空或者null，直接用空格即" "补位
				for (int i = 0; i < needlen; i++) {
					ret += " ";
				}
				ret = res + ret;
			} else {// 如果不为空则用给出的参数进行补位
				int roll = needlen / pad.length();// 计算出需要完成循环补位字符串的循环次数
				String tail = pad.substring(0, needlen % pad.length());// 计算出需要补位的余数位的长度
				for (int i = 0; i < roll; i++) {
					ret += pad;
				}
				ret = res + ret + tail;
			}
		}
		return ret;
	}

	/**
	 * 检测字符串 是 null或者空值
	 * */
	public static boolean isNullOrBlank(String str) {
		return null == str || "".equals(str);
	}

	/**
	 * 检测字符串是 null并且空值
	 * */
	public static boolean isNullAndBlank(String str) {
		return null == str && "".equals(str);
	}

	/**
	 * 检测字符串 不是 null或者值不为空
	 * */
	public static boolean isNotNullOrBlank(String str) {
		return null != str || !"".equals(str);
	}

	/**
	 * 检测字符串 不是 null并且值也不为空
	 * */
	public static boolean isNotNullAndBlank(String str) {
		return null != str && !"".equals(str);
	}

	/**
	 * 比较两个字符串（数字型字符串）a <= b吗？
	 * */
	public static boolean lessThenAndEqualsTo(String a, String b) {
		return Integer.parseInt(a) <= Integer.parseInt(b);
	}

	/**
	 * 比较两个字符串（数字型字符串）a >= b吗？
	 * */
	public static boolean greaterThenAndEqualsTo(String a, String b) {
		return Integer.parseInt(a) >= Integer.parseInt(b);
	}

	/**
	 * 比较两个字符串（数字型字符串）a > b吗？
	 * */
	public static boolean greaterThen(String a, String b) {
		return Integer.parseInt(a) > Integer.parseInt(b);
	}

	/**
	 * 去除上下文路径
	 * */
	public static String getRequestActionUri(HttpServletRequest request) {
		String ret = "";
		try {
			String path = request.getRequestURI();// 获取全路径（没有ip和端口号）
			String ctxPath = request.getContextPath();// 得到web应用程序的上下文路径
			ret = path.substring(ctxPath.length());
		} catch (Exception e) {
			e.printStackTrace();
			ret = "";
		}
		return ret;// 去除上下文路径
	}

	/**
	 * 获取大写字母编号，例如ABCDE...YZ，最小值为1，最大值为26，如果超出范围，则返回null
	 * */
	public static String getUpperNumLetter(int index) {
		if (index < 1 || index > 26) {
			return null;
		}
		// 开始于64，A=65
		int tag = 64 + index;
		return ((char) tag) + "";
	}

	/**
	 * 生成UUID序列号
	 * */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成时间ID
	 * 
	 * @param tailLength
	 *            尾端长度
	 * */
	public static String getDateUid(int tailLength) {
		return DateTools.getCurrentFormatDate(DateTools.yyyyMMddHHmmss)
				+ getHashAll(tailLength);
	}

	/**
	 * 获取DigestUtils.md5Hex的md5
	 * */
	public static String getMd5Value(String tag) {
		return DigestUtils.md5Hex(tag);
	}

	/**
	 * 获取储位打印编号，不包含号牌种类
	 * */
	public static String getPrintCwbh(String cwbh) {
		if (null != cwbh && !"".equals(cwbh.trim())) {
			return cwbh.substring(0, 1) + "-" + cwbh.substring(1, 3) + "-"
					+ cwbh.substring(3, 4) + "-" + cwbh.substring(4, 5) + "-"
					+ cwbh.substring(5, 8);
		}
		return cwbh;
	}
	
	/**
	 * 将null字符串转换成空格字符串,不为null则不转换
	 * @param src
	 * @return
	 */
	public static String nullToBlank(String src){
		return src == null?"":src;
	}
}
