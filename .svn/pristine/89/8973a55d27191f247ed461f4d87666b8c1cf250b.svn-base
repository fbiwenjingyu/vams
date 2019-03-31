package com.pd.webservice.scan.commands;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.system.framework.DAOFactory;
import com.pd.system.res.ArcStatus;
import com.pd.webservice.util.ObjectParser;

/**
 * 查询重扫的档案页列表
 * @author ywj
 * 2014-8-1
 */
public class QueryReScanPageListCommandV2 extends AbstractCommandRequest {
	
	protected final Log log = LogFactory.getLog(getClass());
	public JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate();
	private Map map;
	
	@Override
	public String request(String requestXml) {
		paramMap = ObjectParser.getParam(requestXml);
		String usercode = paramMap.get("usercode");
		String roleId = paramMap.get("roleid");
		
		String roleCondition = " and t2.smr='"+usercode+"' ";
		String sql_roletype = "select roletype from ROLE where roleid='"+roleId+"'";
		String roleType = (String) jdbcTemplate.queryForObject(sql_roletype, String.class);
		if("1".equals(roleType)){
			roleCondition = "";
		}
		
		String sql1 = "select t.xtdabh as wjbh,t.cwbh as cwbh,t.ywlx,t.dalx from arc_base_info t,(" +
				"select distinct t2.xtdabh from arc_rescan_info t1,arc_pic_info t2 " +
				"where t1.xtdabh = t2.xtdabh and t1.sfycs='0' and t1.arc_status='"+ ArcStatus.ARC_CSSH  + "' " + roleCondition+" ) tt " +
				"where t.xtdabh=tt.xtdabh";
		
		List<Map<String, String>> list1 = jdbcTemplate.queryForList(sql1);
		rowNum = list1.size();
		for (int i=0; i<list1.size(); i++) {
			Map<String,String> resultMap = list1.get(i);
			try {
				respXml.append(ObjectParser.map2XMLUTF8(resultMap, "veh"));
			} catch (Exception e) {
				e.printStackTrace();
				return "0000";
			}
		}
		header = ObjectParser.getScanXMLHead("1","成功", rowNum + "");
		foot = ObjectParser.getScanXMLFoot();
		return header + respXml + foot;
	}
	
	public static void main(String[] args) {
		String str =  "<?xml version='1.0' encoding='UTF-8'?><request><header><commandName>queryReScanPageList</commandName></header><parameter><usercode>slg</usercode><roleid>58</roleid></parameter></request>";
		System.out.println("str=" + new QueryReScanPageListCommandV2().request(str));
	}
}


