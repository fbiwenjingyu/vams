package com.pd.webservice.scan.commands;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.system.framework.DAOFactory;
import com.pd.system.res.ArcStatus;
import com.pd.webservice.util.ObjectParser;
import com.pd.webservice.util.StringUtil;

/**
 * 统计工作任务
 * @author ywj
 * 2014-8-1
 */
public class CountTaskCommand extends AbstractCommandRequest {
    
	protected final Log log = LogFactory.getLog(getClass());
	//public JdbcTemplate jdbcTemplate = DAOFactory.getInstance().getDao("jdbcTemplate");
	public JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate();
	public static Map<String, String> jkxlhMap = new HashMap<String, String>(); 
	
	//存放从数据库返回的结果对象
	public Map resultMap;
	
	@Override
	public String request(String requestXml) {
		paramMap = ObjectParser.getParam(requestXml);
		String roleid = paramMap.get("roleid");
		String usercode = paramMap.get("usercode");
		
		if (StringUtil.isEmpty(roleid) || StringUtil.isEmpty(usercode)) {
			//必要参数不能为空
			return "0001";
		}
		respXml.append(getReScanCount(usercode,roleid));
		header = ObjectParser.getScanXMLHead("1","成功","1");
		foot = ObjectParser.getScanXMLFoot();
		return header + respXml + foot;
	}
	
	/**
	 * 重扫数统计
	 * @param usercode 
	 * 			用户名
	 * @param roleid 
	 * 			用户角色
	 * @return 
	 * 		number of reScan
	 * 
	 * @author wangwei
	 * 2013-12-12
	 */
	public String getReScanCount(String usercode,String roleid){
		String xml = "";
		String roleCondition = " and t2.smr='"+usercode+"' ";
		String sql_roletype = "select roletype from ROLE where roleid='"+roleid+"'";
		String roleType = (String) jdbcTemplate.queryForObject(sql_roletype, String.class);
		if("1".equals(roleType)){
			roleCondition = "";
		}
		
		String sql = "select count(*) count from arc_base_info t,(" +
				"select distinct t2.xtdabh from arc_rescan_info t1,arc_pic_info t2 " +
				"where t1.xtdabh = t2.xtdabh  and t1.sfycs='0' and t1.arc_status='"+ ArcStatus.ARC_CSSH  + "' " +roleCondition+" ) tt where t.xtdabh=tt.xtdabh ";
		log.info(sql);
		try {
			resultMap = jdbcTemplate.queryForMap(sql);
			xml = ObjectParser.map2XMLUTF8(resultMap, "rescan");
		} catch (Exception e) {
			e.printStackTrace();
			//数据库操作失败
			return "0100";
		}
		return xml;
	}
}

