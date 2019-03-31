package com.pd.base.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.model.Seclevel;
import com.pd.base.model.SeclevelExample;
import com.pd.base.service.SeclevelService;
import com.pd.right.model.SuperUser;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;

public class SeclevelAction extends ActionSupport {
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Seclevel entity;
	private SeclevelService service;
	SeclevelExample example = new SeclevelExample();
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	/**
	 * 
	* @Title: query 
	*  安全级别查询
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public String query(){
		try {
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			
			List<Seclevel> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "seclevel_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  安全级别添加
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void add(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			service.add(entity);
			msg = "添加成功";
			LogInfo.info("安全级别添加添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加安全级别");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='seclevel!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='seclevel!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: isExists 
	*  检测安全级别唯一性
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-27
	 */
	public void isExists(){
		String msg = "";
		response.setContentType("text/plain;charset=UTF-8");
		try {
			if(entity.getLevels() !=null && entity.getLevels().toString().length()>0){
				example.createCriteria().andLevelsEqualTo(entity.getLevels());
			}
			List<Seclevel> list = service.selectByExample(example);
			if(list!=null && list.size()>0){
				msg = "1";
			}else{
				msg = "0";
			}
			response.getWriter().println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: preUpdate 
	*  安全级别修改
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void preUpdate(){
		try {
			Seclevel entity = service.getEntity(this.entity.getId());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("base/seclevel_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  安全级别修改查询
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void update() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			int result = service.update(entity);
			if(result==1){
				msg = "修改成功";
			}else{
				msg = "修改失败";
			}
			LogInfo.info("角安全级别修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "修改安全级别");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='seclevel!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  安全级别删除
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void delete() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			int result = service.delete(entity.getId());
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			LogInfo.info("角色删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "用户删除角色");
			response.getWriter().write("<script>alert('"+msg+"');window.location='seclevel!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  安全级别下拉选择
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		List<Seclevel> list_data = service.selectByExample(null);
		for (Seclevel entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getId());
			data.put("key", entity.getLevels());
			data_jx.add(data);
		}
		try {
			response.getWriter().println(data_jx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Seclevel getEntity() {
		return entity;
	}
	public void setEntity(Seclevel entity) {
		this.entity = entity;
	}
	public void setService(SeclevelService service) {
		this.service = service;
	}
	
	
}


