package com.pd.system.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pd.system.utils.Vmessage;

/**
 * 超级抽象action，主要保存基础返回值信息
 * */
public abstract class AbstractBaseAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1496153403554258836L;
	
	public static final String FAIL = "fail";
	public static final String JSON="json";
	public static final String VAMS_MESSAGE="vmessage";
	
	/**
	 * 请使用方法：EXCEPTION(Object)
	 * */
	private static final String WARNING = "warning";
	/**
	 * 请使用方法：STRUTS_JSON(Object)
	 * */
	private static final String STRUTS_JSON = "struts_json";
	
	/**
	 * 覆盖原有的error返回值，请使用方法：ERROR(Object);
	 * */
	protected static final String ERROR="error";
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	// 返回的error页面的信息
	public Vmessage vmessage;

	// 返回的json的对象
	protected Object retJsonObject;

	// =================返回统一错误页==================

	/**
	 * 返回错误信息
	 * @param msg 简单信息
	 * */
	protected String ERROR(Object msg) {
		vmessage = new Vmessage("系统错误",msg.toString(),"");
		return ERROR;
	}
	
	/**
	 * 返回错误信息-详细为标题
	 * @param msg 简单信息
	 * @param detailMsg 详细信息
	 * */
	protected String ERROR(Object msg,String detailMsg) {
		vmessage = new Vmessage(msg.toString(),msg.toString(),detailMsg);
		return ERROR;
	}

	/**
	 * 返回警告信息
	 * @param msg 异常信息
	 * */
	protected String WARNING(Object msg) {
		vmessage = new Vmessage("处理异常",msg.toString(),"");
		return WARNING;
	}
	
	/**
	 * 返回警告信息-信息为标题
	 * @param msg 异常信息
	 * @param detailMsg详细信息
	 * */
	protected String WARNING(Object msg,String detailMsg) {
		vmessage = new Vmessage(msg.toString(),msg.toString(),detailMsg);
		return WARNING;
	}
	
	
	// ==========================================

	/**
	 * 返回struts2 json，前台不需要eval()转换
	 * 
	 * @param Object
	 *            要传递的Object对象或者json对象/数组
	 * */
	protected String STRUTS_JSON(Object json) {
		retJsonObject = json;
		return STRUTS_JSON;
	}

	/**
	 * 获取action上下文
	 * */
	protected static ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public Vmessage getVmessage() {
		return vmessage;
	}

	public Object getRetJsonObject() {
		return retJsonObject;
	}
}
