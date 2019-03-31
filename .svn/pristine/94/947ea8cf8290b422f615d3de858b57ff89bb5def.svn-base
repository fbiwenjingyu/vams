package com.pd.webservice.scan.commands;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.system.framework.DAOFactory;
import com.pd.webservice.util.MD5Util;
import com.pd.webservice.util.ObjectParser;
import com.pd.webservice.util.StringUtil;

/**
 * 登录接口
 * @author ywj
 *
 */
public class LoginCommand extends AbstractCommandRequest {

	protected final Log log = LogFactory.getLog(getClass());
	public JdbcTemplate jdbcTemplate = DAOFactory.getDao("jdbcTemplate");
	
	public static Map<String, String> jkxlhMap = new HashMap<String, String>(); 
	
	//存放从数据库返回的结果对象
	public Map resultMap;
	
	@Override
	public String request(String requestXml) {
		paramMap = ObjectParser.getParam(requestXml);
		String userCode = paramMap.get("usercode");
		String password = paramMap.get("password");
		if (StringUtil.isEmpty(userCode) || StringUtil.isEmpty(password)) {
			//必要参数不能为空
			return "0001";
		}
		try {
			password = MD5Util.encodeMD5Hex(password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String sql = "select u.USER_CODE as usercode, u.PASSWORD as password, u.USER_NAME as username, u.ROLE_ID as roleid, u.GWH as gwh,u.USERSTAT as userstat,d.DEPTNAME as deptname, d.F2 as scantype " +
                     "from users u left join dept d on u.dept_code = d.deptcode where u.user_code = '" + userCode + "' and u.password = '" + password + "'";
		log.info(sql);
		try {
			String jkxlh = MD5Util.encodeMD5Hex(userCode + password).substring(0, 16);
			jkxlhMap.put(userCode, jkxlh);

			//从数据库中检查用户名密码是否正确
			resultMap = jdbcTemplate.queryForMap(sql);
			//接口序列号 安全校验(用户名+密码)组合成md5码，取前16位
			resultMap.put("jkxlh", jkxlh);
			respXml.append(ObjectParser.map2XMLUTF8(resultMap, "user"));
		} catch (Exception e) {
			e.printStackTrace();
			//数据库操作失败
			return "0100";
		}
		header = ObjectParser.getScanXMLHead("1","成功","1");
		foot = ObjectParser.getScanXMLFoot();
		return header + respXml + foot;
	}
}


