package com.pd.webservice.scan.commands;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import com.pd.webservice.util.ObjectParser;

public class QueryScanedCommand extends AbstractCommandRequest {

	private String rowNum = "";
	
	@Override
	public String request(String requestXml){
		paramMap = ObjectParser.getParam(requestXml);
		String xtdabh = paramMap.get("xtdabh");//历史档案
		String wjbh = paramMap.get("wjbh");//业务档案
		String strScaned = "";
		if(StringUtils.isNotEmpty(xtdabh)){
			strScaned = getScanedXml(xtdabh);
		}
		if(StringUtils.isNotEmpty(wjbh)){
			strScaned = getScanedXml(wjbh);
		}
		
		header = ObjectParser.getScanXMLHead("1","成功",rowNum);
		foot = ObjectParser.getScanXMLFoot();
		return header + strScaned + foot;
	}
	
	/**
	 * 查询已扫描档案列表
	 * @param xtdabh 系统档案编号
	 * @return
	 */
	public String getScanedXml(String xtdabh){
		String strScaned = "";
		try{
			String scanedSql = "select DISTINCT SMCS SCANCOUNT,YWLX FROM ARC_PIC_INFO WHERE XTDABH ='"+xtdabh+"' ";
			List<Map<String, String>> list = jdbcTemplate.queryForList(scanedSql);
			for (Map<String, String> map : list) {
				try {
					strScaned += ObjectParser.map2XMLUTF8(map, "scaned");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			rowNum = list.size() + "";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return strScaned;
	}
	
	
	/**
	 * 查询已扫描档案列表
	 * @param xtdabh 系统档案编号
	 * @return
	 */
	public String getScanedXmlOfHisArc(String xtdabh){
		String strScaned = "";
		try{
			String scanedSql = "select DISTINCT YWLX, SMCS SCANCOUNT FROM ARC_PIC_INFO WHERE XTDABH ='"+xtdabh+"' ";
			List<Map<String, String>> list = jdbcTemplate.queryForList(scanedSql);
			for (Map<String, String> map : list) {
				try {
					strScaned += ObjectParser.map2XMLUTF8(map, "scaned");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			rowNum = list.size() + "";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return strScaned;
	}
	
	
	public static void main(String[] args) {
		String str = "<?xml version='1.0' encoding='UTF-8'?><request><header><command>queryScaned</command></header><parameter><wjbh>152800201401210005</wjbh></parameter></request>";
		new QueryScanedCommand().request(str);
	}
}
