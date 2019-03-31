package com.pd.right.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.service.SeclevelService;
import com.pd.right.model.ResourcesExample;
import com.pd.right.model.Role;
import com.pd.right.model.RoleExample;
import com.pd.right.model.RoleResExample;
import com.pd.right.model.SuperUser;
import com.pd.right.service.ResourcesService;
import com.pd.right.service.RoleResService;
import com.pd.right.service.RoleService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;

public class RoleAction extends ActionSupport {
	
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	
	private static final long serialVersionUID = 1L;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Role entity;
	RoleExample example = new RoleExample();
	private RoleService service;
	private SeclevelService seclevelService;
	private ResourcesService resourcesService;
	private RoleResService roleResService;
	private String[] resid;
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	public String[] getResid() {
		return resid;
	}

	public void setResid(String[] resid) {
		this.resid = resid;
	}
	public void preAdd(){
		try {
			ResourcesExample resexample = new ResourcesExample();
			resexample.setOrderByClause(" resid ");
			List list_data = resourcesService.selectByExample(resexample);
			request.setAttribute("list_data", list_data);
			request.getRequestDispatcher("right/role_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: query 
	*  查询角色列表信息
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2013-6-24
	 */
	public String query(){
		try {
			com.pd.right.model.RoleExample.Criteria ca = example.createCriteria();
			if(entity!=null){
				if(entity.getRolename()!=null && entity.getRolename().length()>0){
					ca.andRolenameLike("%"+entity.getRolename()+"%");
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			
			List<Role> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("levelMap", seclevelService.queryToMap(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "role_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  角色添加
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-24
	 */
	public void add(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			if(entity != null){
				//service.add(entity);
				service.add(entity, resid);
				msg = "添加成功";
				LogInfo.info("角色添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "用户添加角色");
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='role!query.action';</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='role!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void preUpdate(){
		try {
			if(this.entity.getRoleid() != null){
				Role entity = service.preUpdate(this.entity.getRoleid());
				ResourcesExample resexample = new ResourcesExample();
				resexample.setOrderByClause(" resid ");
				List list_data = resourcesService.selectByExample(resexample);
				RoleResExample roleResExample = new RoleResExample();
				RoleResExample.Criteria roleResCr = roleResExample.createCriteria();
				roleResCr.andRoleidEqualTo(this.entity.getRoleid());
				//获取角色原来的资源信息
				List list = roleResService.selectByExample(roleResExample);
				request.setAttribute("list_data", list_data);
				request.setAttribute("list", list);
				request.setAttribute("entity", entity);
				request.setAttribute("update", "update");
				request.getRequestDispatcher("right/role_add.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  角色修改
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
			if(entity != null) {
				//int result = service.update(entity);
				int result = service.update(entity, resid);
				if(result==1){
					msg = "修改成功";
				}else{
					msg = "修改失败";
				}
			}
			LogInfo.info("角色修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "用户修改角色");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='role!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  角色删除
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
			int result = service.delete(entity.getRoleid());
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			LogInfo.info("角色删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "用户删除角色");
			response.getWriter().write("<script>alert('"+msg+"');window.location='role!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  获取角色下拉列表信息
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		List<Role> list_data = service.selectByExample(null);
		for (Role entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getRoleid());
			data.put("key", entity.getRolename());
			data_jx.add(data);
		}
		try {
			response.getWriter().println(data_jx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setService(RoleService service) {
		this.service = service;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Role getEntity() {
		return entity;
	}

	public void setEntity(Role entity) {
		this.entity = entity;
	}

	public void setSeclevelService(SeclevelService seclevelService) {
		this.seclevelService = seclevelService;
	}
	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}
	public void setRoleResService(RoleResService roleResService) {
		this.roleResService = roleResService;
	}
	
	
	
}


