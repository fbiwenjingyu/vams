package com.pd.webservice.scan.commands;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.system.framework.DAOFactory;


/**
 * 客户端请求命令抽象类
 * @author ywj
 *
 */
public abstract class AbstractCommandRequest {
	
//	public JdbcTemplate jdbcTemplate;
	public JdbcTemplate jdbcTemplate = DAOFactory.getDao("jdbcTemplate");
	
	//存放客户端xml信息参数解析
	public Map<String, String> paramMap;
	
	//存放返回的结果报文头信息
	public String header;
	
	//存放返回的结果报文尾信息
	public String foot;
	
	//存放返回的结果信息数量
	public int rowNum;
	
	//存放返回的xml结果主体信息
	public StringBuffer respXml = new StringBuffer();
	
	public String request(String requestXml) throws Exception {
		return respXml.toString();
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
