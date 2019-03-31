package com.pd.webservice.scan.commands;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pd.system.startup.SYSLoadManager;
import com.pd.system.utils.DiskUtil;
import com.pd.webservice.util.ObjectParser;

/**
 * 获取磁盘信息
 * 		1.剩余空间 <free>
 * 		2.总空间<total>
 * @author ywj
 * 2014-8-1
 */
public class GetDiskInfoCommand extends AbstractCommandRequest {

	protected final Log log = LogFactory.getLog(getClass());
	
	public static Map<String, String> jkxlhMap = new HashMap<String, String>(); 
	
	@Override
	public String request(String requestXml) {
		System.out.println(SYSLoadManager.getPicStorePath());
		paramMap = ObjectParser.getParam(requestXml);
		String filePath = SYSLoadManager.getPicStorePath();
		respXml.append("<free>" + DiskUtil.getFreeSpace(filePath) + "</free>");
		respXml.append("<total>" + DiskUtil.getTotalSpace(filePath) + "</total>");
		header = ObjectParser.getScanXMLHead("1","成功","1");
		foot = ObjectParser.getScanXMLFoot();
		return header + respXml + foot;
	}
	
	public static void main(String[] args) {
		String str =  "<?xml version='1.0' encoding='UTF-8'?><request><header><commandName>getDiskInfo</commandName></header><parameter></parameter></request>";
		System.out.println("str=" + new GetDiskInfoCommand().request(str));
	}
}
