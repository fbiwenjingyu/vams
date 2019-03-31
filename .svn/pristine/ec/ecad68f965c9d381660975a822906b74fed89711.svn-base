package com.pd.webservice.scan;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.webservice.scan.commands.AbstractCommandRequest;
import com.pd.webservice.util.ObjectFactory;
import com.pd.webservice.util.ObjectParser;
import com.pd.webservice.util.StringUtil;

/**
 * 扫描客户端请求Action类
 * @author ywj
 *
 */
public class ScanAction extends ActionSupport {
 
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass());
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private String requestXml;
	private String respXml;
	private AbstractCommandRequest commandRequest;
	
	@Override
	public String execute() throws Exception {
		log.debug("--------------------------接收扫描客户端报文---------------------------------\n" + requestXml);
		//解决中文乱码
		response.setContentType("text/xml;charset=utf-8");
		//空串请求校验
		if (StringUtil.isEmpty(requestXml)) {
			try {
				response.getWriter().write(ObjectParser.getScanXMLFailed("空字符串请求"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		try {
			//解析报文请求的命令
			String commandName = ObjectParser.getCommand(requestXml);
			String respStr;
			if (!StringUtil.isEmpty(commandName)) {
				commandName = ScanConstant.commandMap.get(commandName);
				commandRequest = (AbstractCommandRequest) ObjectFactory.getInstance(commandName);
				respStr = commandRequest.request(requestXml);
				
				//根据报文请求返回的错误代码进行处理，返回给扫描客户端
				if (respStr.startsWith("0")) {
					String message = ScanConstant.messageMap.get(respStr);
					log.info("code=" + respStr + ", desc=" + message);
					respStr = ObjectParser.getScanXMLFailed(respStr, message);
				}
				
				respXml = StringUtil.decodeUTF8(respStr);
				response.getWriter().write(respXml);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(ObjectParser.getScanXMLFailed("其它错误"));
		}
		return null;
	}
	
	public String getRequestXml() {
		return requestXml;
	}

	public void setRequestXml(String requestXml) {
		this.requestXml = requestXml;
	}

}
