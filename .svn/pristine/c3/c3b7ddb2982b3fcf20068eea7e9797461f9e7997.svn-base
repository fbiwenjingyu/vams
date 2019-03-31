package com.pd.webservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class ObjectParser {

	protected static final Log log = LogFactory.getLog(ObjectParser.class);
	
	/**
	 * 请求六合一接口的报文头信息
	 * @return
	 */
	public static String getXMLFileHead() {
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<root>\n";
	}

	/**
	 * 请求六合一接口的报文尾信息
	 * @return
	 */
	public static String getXMLFileFoot() {
		return "</root>";
	}

	/**
	 * 返回扫描客户端的报文头信息
	 * @param code 1:成功  0:失败
	 * @param desc 描述说明
	 * @param rownum 返回的记录个数
	 * @return
	 */
	public static String getScanXMLHead(String code, String desc, String rownum) {
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<response>\n" + 
		        "<header>\n" +
		        "<code>" + code + "</code>\n" +
		        "<describe>" + desc + "</describe>\n" +
		        "<rownum>" + rownum + "</rownum>\n" +
		        "</header>\n" + 
		        "<result>";
	}
	
	/**
	 * 返回扫描客户端的报文尾信息
	 * @return
	 */
	public static String getScanXMLFoot() {
		return "</result>\n</response>";
	}
	
	/**
	 * 返回扫描客户端的失败的报文信息
	 * @param cause 失败原因
	 * @author BraveHeartWzm
	 * @return
	 */
	public static String getScanXMLFailed(String message) {
		return getScanXMLHead("0", message, "0") + getScanXMLFoot();
	}
	
	/**
	 * 返回扫描客户端的失败的报文信息
	 * @param cause 错误代码和失败原因
	 * @author BraveHeartWzm
	 * @return
	 */
	public static String getScanXMLFailed(String code, String message) {
		return getScanXMLHead(code, message, "0") + getScanXMLFoot();
	}
	
	/**
	 * 获取扫描客户端请求命令标识
	 * @param xml
	 * @return
	 */
	public static String getCommand(String xml) {
		Document doc = Jsoup.parse(xml.toString());
		Elements elements = doc.getElementsByTag("header");
		Element element = elements.first();
		if (null == element) {
			return null;
		} else {
			String command = element.getElementsByTag("commandName").text();
			return command;
		}
	}
	
	/**
	 * 获取扫描客户端请求参数
	 * @param xml
	 * @return
	 */
	public static Map<String, String> getParam(String respXml) {
		int start = respXml.indexOf("<parameter>");
		int end = respXml.indexOf("</parameter>") + "</parameter>".length();
		String paramXml = respXml.substring(start, end);
		return html2Map(paramXml,"parameter");
	}
	
	/**
	 * 将html报文中指定结点名的第一个结点下所有元素转化成Map对象，key为元素名称，value为元素值
	 * @param xml
	 * @param nodeName
	 * @return
	 */
	public static Map<String, String> html2Map(String html, String nodeName) {
		Map<String, String> result = new HashMap<String, String>();
		Document doc = Jsoup.parse(html.toString());
//		System.out.println(doc.html());
		Elements elements = doc.getElementsByTag(nodeName);
		Element element = elements.first();
		if (null == element) {
			return null;
		} else {
			Elements eleSubs = element.children();
			for (int j = 0; j < eleSubs.size(); j++) {
				Element node = eleSubs.get(j);
				String key = node.nodeName();
				String value = node.text();
//				log.info("key=" + key + " ,value=" + value);
				result.put(key, value);
			}
		}
		return result;
	}
	
	/**
	 * 将html报文中指定结点名的所有结点及这些节点下所有元素转化成Map对象，key为元素名称，value为元素值
	 * @param xml
	 * @param nodeName
	 * @return
	 */
	public static List<Map<String, String>> html2List(String html, String nodeName) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Document doc = Jsoup.parse(html.toString());
//		System.out.println(doc.html());
		Elements elements = doc.getElementsByTag(nodeName);
		for (int i=0; i<elements.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			Element element = elements.get(i);
			if (null == element) {
				continue;
			} else {
				Elements eleSubs = element.children();
				for (int j = 0; j < eleSubs.size(); j++) {
					Element node = eleSubs.get(j);
					String key = node.nodeName();
					String value = node.text();
//					System.out.println("key=" + key + " ,value=" + value);
					map.put(key, value);
				}
				result.add(map);
			}
		}
		return result;
	}
	
	/**
	 * 将Map对象封装成xml文档
	 * 
	 * @param bean
	 * @param itemName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String map2XMLUTF8(Map p, String itemName) throws Exception {
		StringBuffer sb = new StringBuffer();
		Set s = p.keySet();
		Iterator iter = s.iterator();
		sb.append("<");
		sb.append(itemName);
		sb.append(">");

		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = p.get(key);
			key = key.toString().toLowerCase();
//			System.out.println("key=" + key);
			sb.append("\n <");
			sb.append(key);
			sb.append(">");
			if (value == null || value.toString().equals("")) {
				sb.append("");
			} else {
				value = value.toString();
				value = StringUtil.encodeUTF8(value.toString());
				sb.append(value);
			}
			sb.append("</");
			sb.append(key);
			sb.append(">");
		}
		sb.append("\n</");
		sb.append(itemName);
		sb.append(">\n");
		return sb.toString();
	}
	
	/**
	 * 将数据对象封装成xml文档
	 * 
	 * @param bean
	 * @param itemName
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String bean2XMLUTF8(Object bean, String itemName,	String itemId) throws Exception {
		StringBuffer sb = new StringBuffer();
		Map p = BeanUtils.describe(bean);
		Set s = p.keySet();
		Iterator iter = s.iterator();
		sb.append("<");
		sb.append(itemName);
		if (itemId == null || itemId.equals("")) {
			sb.append(">");
		} else {
			sb.append(" id=\"");
			sb.append(itemId);
			sb.append("\">");
		}

		while (iter.hasNext()) {
			Object key = iter.next();
			if (key.equals("class")) {
			} else {
				Object value = p.get(key);
//				System.out.println("key=" + key);
				sb.append("\n <");
				sb.append(key);
				sb.append(">");
				if (value == null || value.toString().equals("")) {
					sb.append("");
				} else {
					value = StringUtil.encodeUTF8(value.toString());
					sb.append(value);
				}
				sb.append("</");
				sb.append(key);
				sb.append(">");
			}
		}
		sb.append("\n</");
		sb.append(itemName);
		sb.append(">\n");
		return sb.toString();
	}

	
	public static void main(String[] args) throws Exception {
//		String str =  "<?xml version='1.0' encoding='GBK'?><request><header><commandName>login</commandName><describe>成功</describe><rownum>0</rownum></header><parameter> <username>admin</username><password>1234567</password></parameter></request>";
		String str =  "<parameter> <username>admin</username><password>1234567</password></parameter>";
		System.out.println(getCommand(str));
		Map<String, String> map = html2Map(str, "parameter");
		Map<String, String> map1 = getParam(str);
//		System.out.println(map.get("username"));
		
		System.out.println("-------------------------------------------------------------------");
//		System.out.println("-------------------------------------------------------------------");
//		Map<String, String> result = new HashMap<String, String>();
//		result.put("hmbh", "123");
//		result.put("hphm", "LHH616");
//		System.out.println(getScanXMLFileHead("1","成功","1"));
//		System.out.println(map2XMLUTF8(result,"veh"));
//		System.out.println(getScanXMLFileFoot());
//		
//		System.out.println("-------------------------------------------------------------------");
//		ResponseVO vo = new ResponseVO();
//		vo.setUri("http://www.baidu.com");
//		vo.setContentType("我是xml");
//		System.out.println(bean2XMLUTF8(vo, "Res", "2"));
	}
}


