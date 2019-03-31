package com.pd.system.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期与时间工具类<br>
 * <br>
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ <br>
 * 描述 <br>
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━<br>
 * G Era 标志符 Text AD <br>
 * y 年 Year 1996; 96 <br>
 * M 年中的月份 Month July; Jul; 07 <br>
 * w 年中的周数 Number 27 <br>
 * W 月份中的周数 Number 2 <br>
 * D 年中的天数 Number 189 <br>
 * d 月份中的天数 Number 10 <br>
 * F 月份中的星期 Number 2 <br>
 * E 星期中的天数 Text Tuesday; Tue <br>
 * a Am/pm 标记 Text PM <br>
 * H 一天中的小时数（0-23） Number 0 <br>
 * k 一天中的小时数（1-24） Number 24 <br>
 * K am/pm 中的小时数（0-11） Number 0 <br>
 * h am/pm 中的小时数（1-12） Number 12 <br>
 * m 小时中的分钟数 Number 30 <br>
 * s 分钟中的秒数 Number 55 <br>
 * S 毫秒数 Number 978 <br>
 * z 时区 General time zone Pacific Standard Time; PST; GMT-08:00 <br>
 * Z 时区 RFC 822 time zone -0800 <br>
 * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━<br>
 * */
public class DateTools {

	/**
	 * 格式化类型：年月日（yyyy-MM-dd）
	 * */
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	/**
	 * 格式化类型：xxxx年xx月xx日
	 * */
	public static final String $yyyy_MM_dd = "yyyy年MM月dd日";
	/**
	 * 格式化类型：年月日（yyyyMMdd）
	 * */
	public static final String yyyyMMdd = "yyyyMMdd";
	/**
	 * 格式化类型：年月日时分秒（yyyy-MM-dd HH:mm:ss）
	 * */
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 格式化类型：年月日时分秒（yyyyMMddHHmmss）
	 * */
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	/**
	 * 转换类型：yyyy年MM月dd日  HH:mm:ss
	 * */
	public static final String nian_yue_ri_shi_fen_miao="yyyy年MM月dd日  HH:mm:ss";

	private DateTools() {
	}

	/**
	 * 获取格式化日期字符串
	 * 
	 * @param date
	 *            日期对象
	 * @param type
	 *            格式化方式，参考：DateTools的静态常量
	 * @author BraveHeartWzm
	 * 
	 * */
	public static String getFormatDate(Date date, String type) {
		String ret = null;
		if (null == date) {
			return null;
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
			ret = simpleDateFormat.format(date);
			simpleDateFormat = null;
		}
		return ret;
	}

	/**
	 * 获取当前的格式化日期字符串
	 * 
	 * @param type
	 *            格式化方式，参考：DateTools的静态常量
	 * @author BraveHeartWzm
	 * */
	public static String getCurrentFormatDate(String type) {
		return getFormatDate(new Date(), type);
	}

	/**
	 * 字符串转换日期
	 * */
	public static Date toDate(String dateStr, String type) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("日期转换异常");
		}
		return null;
	}

	/**
	 * 获取当前时间 年月 yyyyMM
	 * 
	 * @return 如：201312
	 * @author wangwei 2013-12-31
	 */
	public static String getYM() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String YM = sdf.format(new Date());
		return YM;
	}

	/**
	 * 获取当前时间的年月日
	 * 
	 * @return
	 */
	public static String getYMD() {
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		String YMD = sdf.format(new Date());
		return YMD;
	}

	/**
	 * 获取当前时间 年月日时分秒 yyyyMMddHHmmss
	 * 
	 * @return
	 * 
	 * @author WangWei 2014-1-8
	 */
	public static String getYmdhms() {
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmss);
		String YM = sdf.format(new Date());
		return YM;
	}

	public static String date2Ym(String strDate) {
		DateFormat gmtFormat = new SimpleDateFormat("yyyyMM");
		TimeZone cstTime = TimeZone.getTimeZone("CST");
		gmtFormat.setTimeZone(cstTime);
		return gmtFormat.format(new Date(strDate));
	}

	// 当前时间
	public static String nowTime() {

		Date d = new Date();
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 转换格式
		return sdf.format(d).toString();

	}

	/**
	 * 获取时间序列ID 日期格式：yyyyMMddHHmmss
	 * */
	public static String getDateSeqId1(String head, String seq) {
		StringBuffer buffer = new StringBuffer();
		if (null != head && !"".equals(head)) {
			buffer.append(head);
		}
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		buffer.append(sdf.format(new Date()).toString());
		buffer.append(seq);
		return buffer.toString();
	}

	/**
	 * 获取时间序列ID 日期格式：yyMMddHHmmss
	 * */
	public static String getDateSeqId2(String head, String seq) {
		StringBuffer buffer = new StringBuffer();
		if (null != head && !"".equals(head)) {
			buffer.append(head);
		}
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		buffer.append(sdf.format(new Date()).toString());
		buffer.append(seq);
		return buffer.toString();
	}

	/**
	 * 获取时间序列ID 日期格式：yyyyMMdd
	 * */
	public static String getDateSeqId3(String head, String seq) {
		StringBuffer buffer = new StringBuffer();
		if (null != head && !"".equals(head)) {
			buffer.append(head);
		}
		// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		buffer.append(sdf.format(new Date()).toString());
		buffer.append(seq);
		return buffer.toString();
	}

	// ===================================================

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String str = sdf.format(new Date());
		System.out.println(str);
	}

}
