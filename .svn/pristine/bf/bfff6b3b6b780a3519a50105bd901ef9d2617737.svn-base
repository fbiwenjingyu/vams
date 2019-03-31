package com.pd.right.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.service.CodeSetService;
import com.pd.right.model.Dept;
import com.pd.right.model.DeptExample;
import com.pd.right.model.DeptExample.Criteria;
import com.pd.right.model.SuperUser;
import com.pd.right.service.DeptService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;
import com.pd.system.startup.SYSLoadManager;

public class DeptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Dept entity;
	DeptExample example = new DeptExample();
	private DeptService service;
	private CodeSetService codeSetService;
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	/**
	 * 
	* @Title: query 
	*  机构信息分页查询
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2013-6-27
	 */
	public String query(){
		try {
			Criteria ca = example.createCriteria();
			if(entity!=null){
				if(entity.getDeptcode()!=null && entity.getDeptcode().length()>0){
					ca.andDeptcodeLike("%"+entity.getDeptcode()+"%");
				}
				if(entity.getDeptname()!=null && entity.getDeptname().length()>0){
					ca.andDeptnameLike("%"+entity.getDeptname()+"%");
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			
			List<Dept> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("codeSetMap", codeSetService.queryToMapByCode(null));
			request.setAttribute("deptMap", service.queryToMap(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dept_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  机构添加
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-27
	 */
	public void add(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			service.add(entity);
			SYSLoadManager.reload();
			msg = "添加成功";
			LogInfo.info("机构添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加机构");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='dept!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='dept!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: isExists 
	*  检测代码值唯一性
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-27
	 */
	public void isExists(){
		String msg = "";
		response.setContentType("text/plain;charset=UTF-8");
		if(entity.getDeptcode() !=null && entity.getDeptcode().length()>0){
			example.createCriteria().andDeptcodeEqualTo(entity.getDeptcode());
		}
		List<Dept> list = service.selectByExample(example);
		if(list!=null && list.size()>0){
			msg = "1";
		}else{
			msg = "0";
		}
		try {
			response.getWriter().println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: preUpdate 
	*  机构修改
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	public void preUpdate(){
		try {
			Dept entity = service.getEntity(this.entity.getDeptcode());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("right/dept_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  机构修改
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
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
			SYSLoadManager.reload();
			LogInfo.info("机构修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "修改机构");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='dept!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  机构删除
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	public void delete() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		
		String msg = "";
		try {
			int result = service.delete(entity.getDeptcode());
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			SYSLoadManager.reload();
			LogInfo.info("机构删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除机构");
			response.getWriter().write("<script>alert('"+msg+"');window.location='dept!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  获取机构下拉列表信息
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		List<Dept> list_data = service.selectByExample(null);
		for (Dept entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getDeptcode());
			data.put("key", entity.getDeptname());
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
	public Dept getEntity() {
		return entity;
	}
	public void setEntity(Dept entity) {
		this.entity = entity;
	}
	public void setService(DeptService service) {
		this.service = service;
	}

	public void setCodeSetService(CodeSetService codeSetService) {
		this.codeSetService = codeSetService;
	}
	
	
}


