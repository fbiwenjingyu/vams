package com.pd.right.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.right.model.Resources;
import com.pd.right.model.ResourcesExample;
import com.pd.right.model.RoleRes;
import com.pd.right.model.RoleResExample;
import com.pd.right.model.RoleResources;
import com.pd.right.model.SuperUser;
import com.pd.right.service.ResourcesService;
import com.pd.right.service.RoleResService;

public class RoleResAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private RoleRes entity;
	RoleResExample example = new RoleResExample();
	private RoleResService service;
	private ResourcesService resourcesService;
	private String upresid;
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	public String getRoleRes(){
		try{
			SuperUser user = (SuperUser) request.getSession().getAttribute("user");
			Long roleId = user.getRoleId();
			Map map = new HashMap();
			if(roleId.toString().equals("75")){
				map.put("upresid", "001");
			}
			if(roleId!=null && !roleId.toString().equals("75")){
				map.put("roleId", roleId);
				map.put("upresid", "001");
			}
			List<RoleResources> list_data = service.selectByFirstRole(map);
			request.setAttribute("list_data", list_data);

		}catch(Exception e){
			e.printStackTrace();
		}
		return "main";
	}
	
	public String getRes(){
		response.setContentType("application/json; charset=UTF-8"); 
		try {
			try {
				SuperUser user = (SuperUser) request.getSession().getAttribute("user");
				Long roleId = user.getRoleId();
				Map map = new HashMap();
				ResourcesExample resexample = new ResourcesExample();
				com.pd.right.model.ResourcesExample.Criteria ca = resexample.createCriteria();
				JSONArray data_jx = new JSONArray();
				
				/**
				 * 当前登录用户为管理员则从资源表里面获取下级资源
				 */
				if(roleId.toString().equals("75")){
					if(upresid!=null && upresid.length()>0){
						ca.andUpresidLike(upresid+"%");
					}else{
						ca.andUpresidEqualTo("001");
					}
					resexample.setOrderByClause(" resid");
					List<Resources> list_data = resourcesService.selectByExample(resexample);
					for (Resources entity : list_data) {
						JSONObject data = new JSONObject();
						data.put("resname", entity.getResname());
						data.put("openmode", entity.getOpenmode());
						data.put("upresid", entity.getUpresid());
						data.put("resurl", entity.getResurl());
						data.put("ebabled", entity.getEnabled());
						data.put("resid", entity.getResid());
						data.put("icon", entity.getIcon());
						data_jx.add(data);
					}
				}
				
				/**
				 * 当前登录用户为其他类型用户通过角色关联角色资源表查询获取下级资源
				 */
				if(roleId!=null && !roleId.toString().equals("75")){
					map.put("roleId", roleId);
					map.put("upresid", upresid);
					List<RoleResources> list_data = service.selectByRole(map);
					for (RoleResources entity : list_data) {
						JSONObject data = new JSONObject();
						data.put("roleid", entity.getRoleid());
						data.put("resname", entity.getResname());
						data.put("openmode", entity.getOpenmode());
						data.put("upresid", entity.getUpresid());
						data.put("resurl", entity.getResurl());
						data.put("ebabled", entity.getEnabled());
						data.put("resid", entity.getResid());
						data.put("icon", entity.getIcon());
						data_jx.add(data);
					}
				}

				response.getWriter().println(data_jx);
				
			} catch (JSONException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}
//	/**
//	 * 
//	* @Title: query 
//	*  角色资源关系信息分页查询
//	* @param @return    设定文件 
//	* @return String    返回类型 
//	* @throws 
//	* @author zl
//	* 2013-6-27
//	 */
//	public String query(){
//		try {
//			Criteria ca = example.createCriteria();
//			if(entity!=null){
//				if(entity.getRoleId()!=null && entity.getRoleId().toString().length()>0){
//					ca.andRoleIdEqualTo(entity.getRoleId());
//				}
//			}
//			Pagination page = service.getPageByExample(index, example);
//			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
//			example.setLimit(page.getPageSize()+example.getStart()-1);
//			
//			List<RoleRes> list_data = service.query(example);
//			
//			request.setAttribute("list_data", list_data);
//			request.setAttribute("page", page);
//			request.setAttribute("entity", entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "role_list";
//	}
//	
//	/**
//	 * 
//	* @Title: add 
//	*  角色资源关系添加
//	* @param     设定文件 
//	* @return void    返回类型 
//	* @throws 
//	* @author zl
//	* 2013-6-27
//	 */
//	public void add(){
//		response.setContentType("text/html;charset=UTF-8");
//		String msg = "";
//		try {
//			service.add(entity);
//			msg = "添加成功";
//			LogInfo.info("角色资源关系添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加角色资源关系");
//			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='role!query.action';</script>");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			msg = "添加失败";
//			try {
//				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='role!query.action';</script>");
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 
//	* @Title: delete 
//	*  角色资源关系删除
//	* @param @throws Exception    设定文件 
//	* @return void    返回类型 
//	* @throws 
//	* @author zl
//	* 2013-6-25
//	 
//	public void delete() throws Exception{
//		response.setContentType("text/html;charset=UTF-8");
//		
//		String msg = "";
//		try {
//			int result = service.delete(this.entity.getRoleId().toString());
//			if(result==1){
//				msg = "删除成功";
//			}else{
//				msg = "删除失败";
//			}
//			LogInfo.info("角色资源关系删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除角色资源关系");
//			response.getWriter().write("<script>alert('"+msg+"');window.location='RoleRes!query.action'</script>");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}*/
	

	public int getIndex() {
		return index;
	}
	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public RoleRes getEntity() {
		return entity;
	}
	public void setEntity(RoleRes entity) {
		this.entity = entity;
	}
	public void setService(RoleResService service) {
		this.service = service;
	}

	public String getUpresid() {
		return upresid;
	}

	public void setUpresid(String upresid) {
		this.upresid = upresid;
	}
	
	
}


