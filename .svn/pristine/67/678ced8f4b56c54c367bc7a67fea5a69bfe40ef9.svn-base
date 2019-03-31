package com.pd.webservice.scan.commands;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.system.framework.DAOFactory;
import com.pd.webservice.util.ObjectParser;
import com.pd.webservice.util.StringUtil;

/**
 * 获取档案扫描页接口
 * @author ywj
 *
 */
public class GetArcPageCommand extends AbstractCommandRequest {

	protected final Log log = LogFactory.getLog(getClass());
	public JdbcTemplate jdbcTemplate = DAOFactory.getDao("jdbcTemplate");
	//存放从数据库返回的结果对象
	public Map resultMap;
	
	@Override
	public String request(String requestXml) {
		paramMap = ObjectParser.getParam(requestXml);
		String roleId = paramMap.get("roleid");
		String ywlx = paramMap.get("ywlx");
		if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(ywlx)) {
			//必要参数不能为空
			return "0001";
		}
		String paperInfo;
		try {
			paperInfo = getPaperInfo(ywlx, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			//数据库操作失败
			return "0002";
		}
		
		header = ObjectParser.getScanXMLHead("1","成功","1");
		foot = ObjectParser.getScanXMLFoot();
		return header + paperInfo + foot;
	}
	
	@SuppressWarnings("unchecked")
	protected String getPaperInfo(String ywlx, String roleId) throws Exception {
		String sql = "select p.id as paperid, p.paper_name as papername, p.paper_desc as paperfullname, p.paper_type as papertype,t.f1 as scantype from typeset t, paper p, codeset c " +
                     "where t.paper_id = p.id and c.id = t.type_id and c.code = '" + ywlx + "' and t.role_id = '" + roleId + "' order by p.id";
		log.info(sql);
		List<Map<String, Object>> list = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql);
		rowNum = list.size();
		for (int i=0; i<list.size(); i++) {
			resultMap = list.get(i);
			respXml.append(ObjectParser.map2XMLUTF8(resultMap, "paper"));
		}
		return respXml.toString();
	}
	
	/**
	 * 获取要扫描的身份证信息
	 * @param ywlx
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	protected String getIDCardInfo(String ywlx, String roleId) throws Exception {
		String sql = "select p.id as paperid, p.paper_name as papername, p.paper_desc as paperfullname, p.paper_type as papertype,t.f1 as scantype from typeset t, paper p, codeset c " +
                     "where t.paper_id = p.id and c.id = t.type_id and c.code = '" + ywlx + "' and t.role_id = '" + roleId + "' and p.paper_name like '%身份证%' order by p.id";
		log.info(sql);
		List<Map<String, Object>> list = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql);
		rowNum = list.size();
		for (int i=0; i<list.size(); i++) {
			resultMap = list.get(i);
			respXml.append(ObjectParser.map2XMLUTF8(resultMap, "paper"));
		}
		return respXml.toString();
	}
	
	/**
	 * 获取除身份证以外的档案页
	 * @param ywlx
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	protected String getPaperPostInfo(String ywlx, String roleId) throws Exception {
		String sql = "select p.id as paperid, p.paper_name as papername, p.paper_desc as paperfullname, p.paper_type as papertype,t.f1 as scantype from typeset t, paper p, codeset c " +
                     "where t.paper_id = p.id and c.id = t.type_id and c.code = '" + ywlx + "' and t.role_id = '" + roleId + "' and p.paper_name not like '%身份证%' order by p.id";
		log.info(sql);
		List<Map<String, Object>> list = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql);
		rowNum = list.size();
		for (int i=0; i<list.size(); i++) {
			resultMap = list.get(i);
			respXml.append(ObjectParser.map2XMLUTF8(resultMap, "paper"));
		}
		return respXml.toString();
	}
	
	
	
	/**
	 * 获取没有扫描纸张列表【补扫】
	 * @param arcLsh 档案流水号
	 * @param ywlx 业务类型
	 * @param roleId 角色ID
	 * @return
	 * @throws Exception
	 * 
	 * @author wangwei
	 * Aug 26, 2013
	 */
	protected String getNotScanPaperInfo(String arcLsh,String ywlx, String roleId) throws Exception {
		String sql = "select p.id as paperid, p.paper_name as papername, p.paper_desc as paperfullname, p.paper_type as papertype, c.code,t.role_id from typeset t, paper p, codeset c " +
				"where t.paper_id=p.id and c.id=t.type_id and c.code='"+ywlx+"' and t.role_id='"+roleId+"' " +
				"and p.id not in (select paper_id from arc_pic_info_mid where arc_lsh='"+arcLsh+"') order by p.paper_name asc";
		log.info(sql);
		List<Map<String, Object>> list = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql);
		rowNum = list.size();
		for (int i=0; i<list.size(); i++) {
			resultMap = list.get(i);
			respXml.append(ObjectParser.map2XMLUTF8(resultMap, "paper"));
		}
		return respXml.toString();
	}
	
	/**
	 * 获取重扫档案扫描页
	 * @param arcLsh
	 * @param ywlx
	 * @param roleId
	 * @return
	 * @throws Exception
	 * 
	 * @author wangwei
	 * 2013-9-23
	 */
	protected String getReScanPaperInfo(String arcLsh,String ywlx, String roleId) throws Exception {
		String sql = "select p.id as paperid, p.paper_name as papername, p.paper_desc as paperfullname, p.paper_type as papertype,c.code,t.role_id from typeset t, paper p, codeset c " +
				"where t.paper_id=p.id and c.id=t.type_id and c.code='"+ywlx+"' and t.role_id='"+roleId+"' " +
				"and p.id in (select paper_id from (select arc_lsh,paper_id,save_status from arc_pic_info_mid union select arc_lsh,paper_id,save_status from arc_pic_info) where arc_lsh='"+arcLsh+"' and save_status='2' ) order by p.paper_name asc";
		log.info(sql); 
		List<Map<String, Object>> list = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql);
		rowNum = list.size();
		for (int i=0; i<list.size(); i++) {
			resultMap = list.get(i);
			respXml.append(ObjectParser.map2XMLUTF8(resultMap, "paper"));
		}
		return respXml.toString();
	}
	
	/**
	 * 重扫查询
	 * @param wjbh 
	 * @param ywlx
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public String getReScanPaperInfoByGdq(String xtdabh,String ywlx, String roleId) throws Exception{
		
		String roleCondition = " and u.role_id='"+roleId+"' ";
		String sql_roletype = "select roletype from ROLE where roleid='"+roleId+"'";
		String roleType = (String) jdbcTemplate.queryForObject(sql_roletype, String.class);
		if("1".equals(roleType)){
			roleCondition = "";
		}
		String sql = "select pic.ywlx as code,pic.paper_id as paperid,paper.paper_name as papername,paper.paper_desc as paperfullname,paper.paper_type papertype,pic.smr as pre_scan,u.role_id,pic.smcs scancount " +
				"from (select xtdabh,ywlx,paper_id,smr,smcs,sfcs from arc_pic_info) pic,paper paper,users u " +
				"where paper.id=pic.paper_id and pic.smr = u.user_code " +
				"and pic.xtdabh='"+xtdabh+"' and pic.sfcs='1'" + roleCondition;
		log.info(sql); 
		List<Map<String, Object>> list = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql);
		rowNum = list.size();
		for (int i=0; i<list.size(); i++) {
			resultMap = list.get(i);
			respXml.append(ObjectParser.map2XMLUTF8(resultMap, "paper"));
		}
		System.out.println(respXml);
		return respXml.toString();
	}
	
	public static void main(String[] args) {
		String str =  "<?xml version='1.0' encoding='UTF-8'?><request><header><command>login</command>/header><param>  <username>admin</username><password>1234567</password></param></request>";
		System.out.println(new GetArcPageCommand().request(str));
	}
}

/*
<?xml version='1.0' encoding='UTF-8'?>
<request>
<header>
  <commandName>getArcPage</commandName>
</header>
<parameter>
   <usercode>test</usercode>
   <roleid>65</roleid>
   <ywlx>QT</ywlx>
</parameter>
</request>

http://localhost:8080/vams/test/testXml.jsp
*/

