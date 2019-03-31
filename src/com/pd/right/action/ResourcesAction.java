package com.pd.right.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.right.model.Resources;
import com.pd.right.model.ResourcesExample;
import com.pd.right.model.ResourcesExample.Criteria;
import com.pd.right.model.SuperUser;
import com.pd.right.service.ResourcesService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.BaseAction;
import com.pd.system.framework.Pagination;
import com.pd.system.startup.SYSConfig;

public class ResourcesAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Resources entity;
	ResourcesExample example = new ResourcesExample();
	private ResourcesService service;
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	/**
	 * 
	* @Title: getRoleRes 
	*  登录用户为管理员查询所有的菜单
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2013-7-3
	 */
	public String getRoleRes(){
		try{
			Criteria ca = example.createCriteria();
			ca.andUpresidEqualTo("001");
			
			example.setOrderByClause(" resid ");
			List<Resources> list_data = service.selectByExample(example);
			request.setAttribute("list_data", list_data);

		}catch(Exception e){
			e.printStackTrace();
		}
		return "main";
	}
	/**
	 * 
	* @Title: query 
	*  资源信息分页查询
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
				if(entity.getResname()!=null && entity.getResname().length()>0){
					ca.andResnameLike("%"+entity.getResname()+"%");
				}
				if(entity.getResid()!=null && entity.getResid().length()>0){
					ca.andResidLike("%"+entity.getResid()+"%");
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			example.setOrderByClause(" resid ");
			List<Resources> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "resources_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  资源添加
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
			msg = "添加成功";
			LogInfo.info("资源添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加资源");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='resources!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='resources!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void preUpdate(){
		try {
			Resources entity = service.getEntity(this.entity.getResid());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("right/resources_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 查询资源图标
	 * */
	public String findIcon(){
		//获取本地项目路径
		String damspath=getRealPath();
		//获取图标配置路径
		String iconpath=SYSConfig.getMenuIconPath();
		String path=damspath+iconpath;
		//获取图标文件夹
		File file=new File(path);
		//获取图标列表
		File[] icons=file.listFiles();
		List<String> iconlist=new ArrayList<String>(icons.length);
		for (int i = 0; i < icons.length; i++) {
			iconlist.add(icons[i].getName());
		}
		//将图标名称列表和相对路径放入request
		request.setAttribute("iconlist", iconlist);
		request.setAttribute("path", iconpath);
		
		return "icon";
	}
	
	/**
	 * 
	* @Title: update 
	*  资源修改
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
			LogInfo.info("资源修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "修改资源");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='resources!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  资源删除
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
			int result = service.delete(entity.getResid());
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			LogInfo.info("资源删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除资源");
			response.getWriter().write("<script>alert('"+msg+"');window.location='resources!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  获取资源下拉列表信息
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		example.setOrderByClause(" resid ");
		List<Resources> list_data = service.selectByExample(example);
		for (Resources entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getResid());
			data.put("key", entity.getResname());
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
	public Resources getEntity() {
		return entity;
	}
	public void setEntity(Resources entity) {
		this.entity = entity;
	}
	public void setService(ResourcesService service) {
		this.service = service;
	}
	
	
}


