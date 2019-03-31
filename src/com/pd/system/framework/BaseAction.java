package com.pd.system.framework;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.pd.right.model.SuperUser;
import com.pd.system.res.Constants;

/**
 * action封装鸡肋，action建议继承此类，方便【业务】扩展
 * 
 * @author BraveHeartWzm
 * */
public abstract class BaseAction extends AbstractBaseAction implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -7782390905683067564L;

	protected Logger logger = Logger.getLogger(this.getClass());

	private static final String WARNING = "warning";

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	// 分页页码，默认为1
	protected int index = 1;
	// 分页对象
	protected Pagination page;
	// 分页数据
	protected List<?> plist;
	// 分页数据
	protected List<?> pageList;
	// 模糊查询
	protected String fuzzy;
	// ids用;分割
	protected String ids;
	
	protected String startdate;
	protected String enddate;

	// 返回的json的对象
	protected Object retJsonObject;
	public static final String STRUTS_JSON = "struts_json";

	// 返回的error页面的信息
	public String error;
	public static final String FAIL = "fail";

	/**
	 * 获取session中的user
	 * */
	protected SuperUser getSessionUser() {
		return (SuperUser) session.getAttribute(Constants.USER_TAG);
	}

	/**
	 * 将user存入session
	 * */
	protected void setSessionUser(SuperUser user) {
		session.setAttribute(Constants.USER_TAG, user);
	}

	/**
	 * 向页面打印字符串，打印编码为UTF-8
	 * 
	 * @throws IOException
	 * */
	protected void responseWrite(String str) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str);
		} catch (IOException e) {
			logger.error("BaseAction：页面输出字符串异常，字符串内容：" + str, e);
		}
	}
	
	/**
	 * 向页面打印javascript，打印编码为UTF-8
	 * 
	 * @throws IOException
	 * */
	protected void responseWriteJS(String str) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<script>"+str+"</script>");
		} catch (IOException e) {
			logger.error("BaseAction：页面输出字符串异常，字符串内容：" + str, e);
		}
	}

	/**
	 * 编码转换
	 * 
	 * @param res
	 *            需要转码的字符串
	 * @param fromCode
	 *            字符串原始编码
	 * @param toCode
	 *            需要转换到的编码
	 * @return 转换后的字符串
	 * */
	protected String codeConversion(String res, String fromCode, String toCode) {
		try {
			return new String(res.getBytes(fromCode), toCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 获取项目本地绝对路径文件夹
	 * */
	protected String getRealPath() {
		return session.getServletContext().getRealPath("/");
	}

	/**
	 * 开始小时分秒变成00:00:00
	 * */
	protected Date startDateTo00(Date date) {
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	/**
	 * 结束小时分秒变成23:59:59
	 * */
	protected Date endDateTo24(Date date) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	}

	// ============set & get===============
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List getPlist() {
		return plist;
	}

	public void setPlist(List plist) {
		this.plist = plist;
	}

	public Pagination getPage() {
		return page;
	}

	public String getFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}

	public List<?> getPageList() {
		return pageList;
	}

	public void setPageList(List<?> pageList) {
		this.pageList = pageList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获取action上下文
	 * */
	protected static ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	/**
	 * 返回struts2 json，前台不需要eval()转换
	 * 
	 * @param jsonObject
	 *            要传递的Object对象或者json对象/数组
	 * */
	protected String STRUTS_JSON(Object json) {
		retJsonObject = json;
		return STRUTS_JSON;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Object getRetJsonObject() {
		return retJsonObject;
	}

	public void setRetJsonObject(Object retJsonObject) {
		this.retJsonObject = retJsonObject;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


}
