package com.pd.right.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.right.model.Log;
import com.pd.right.model.LogExample;
import com.pd.right.service.LogService;
import com.pd.right.service.impl.LogServiceImpl;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: LogAction 
*  日志控制层
* @author zl
* @date 2013-6-28 上午08:52:17 
*
 */
public class LogAction extends ActionSupport {
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Log entity;
	private String sdate;
	private String edate;
	LogExample example = new LogExample();
	private LogService service = new LogServiceImpl();
	/**
	 * 
	* @Title: query 
	*  日志分页查询
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	public String query(){
		try {

			com.pd.right.model.LogExample.Criteria ca = example.createCriteria();

			if(sdate!=null && sdate.trim().length()>0){
				ca.andCzsjGreaterThanOrEqualTo(sdate);
			}
			if(edate!=null && edate.trim().length()>0){
				ca.andCzsjLessThanOrEqualTo(edate);
			}
			if(entity!=null){
				if(entity.getUserCode()!=null && entity.getUserCode().length()>0){
					ca.andUserCodeLike("%"+entity.getUserCode()+"%");
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			example.setOrderByClause(" CZSJ DESC");
			List<Log> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "log_list";
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Log getEntity() {
		return entity;
	}
	public void setEntity(Log entity) {
		this.entity = entity;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	
	
	
	
}


