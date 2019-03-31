package com.pd.base.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.model.Flow;
import com.pd.base.model.FlowExample;
import com.pd.base.model.FlowExample.Criteria;
import com.pd.base.service.CodeSetService;
import com.pd.base.service.FlowService;
import com.pd.right.model.SuperUser;
import com.pd.right.service.RoleService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;

public class FlowAction extends ActionSupport {
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Flow entity;
	private FlowService service;
	FlowExample example = new FlowExample();
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	private String[] type;
	private CodeSetService codeSetService;
	private RoleService roleService ;
	/**
	 * 
	* @Title: query 
	*  流程分页查询
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public String query(){
		try {
			Criteria ca = example.createCriteria();
			if(entity!=null){
				if(entity.getFlowname()!=null && entity.getFlowname().length()>0){
					ca.andFlownameLike("%"+entity.getFlowname()+"%");
				}
				if(entity.getRoleId()!=null && entity.getRoleId().toString().length()>0){
					ca.andRoleIdEqualTo(entity.getRoleId());
				}
				if(entity.getUpflow()!=null && entity.getUpflow().length()>0){
					ca.andUpflowEqualTo(entity.getUpflow());
				}
				if(entity.getTypeId()!=null && entity.getTypeId().length()>0){
					ca.andTypeIdEqualTo(entity.getTypeId());
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			
			List<Flow> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("codeMap", codeSetService.queryToMap(null));
			request.setAttribute("flowMap", service.queryToMap(null));
			request.setAttribute("roleMap", roleService.queryToMap(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "flow_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  流程添加
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
			LogInfo.info("流程添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加流程");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='flow!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='flow!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: preUpdate 
	*  流程修改查询
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void preUpdate(){
		try {
			Flow entity = service.getEntity(this.entity.getId());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("base/flow_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取对象信息
	 */
	public void getModel(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			Flow flow = service.getEntity(this.entity.getId());
			JSONObject data = new JSONObject();
			data.put("entity", flow);
			response.getWriter().println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  流程修改
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
			LogInfo.info("流程修改", request.getLocalAddr(),"","1", Long.parseLong("1"), "", "用户修改流程");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='flow!query.action'</script>");
		} catch (Exception e) {
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
		if(entity.getFlowcode() !=null && entity.getFlowcode().length()>0){
			example.createCriteria().andFlowcodeEqualTo(entity.getFlowcode());
		}
		List<Flow> list = service.selectByExample(example);
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
	* @Title: delete 
	*  流程删除
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void delete() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		int result = 0;
		try {
			String ids = entity.getId();
			if(ids!=null && ids.length()>0){
				String[] arrIds = ids.split(",");
				for (String id : arrIds) {
					result = service.delete(id);
				}
			}
			
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			LogInfo.info("流程删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除流程");
			response.getWriter().write("<script>alert('"+msg+"');window.location='flow!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  流程下拉选项
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		List<Flow> list_data = service.selectByExample(null);
		for (Flow entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getId());
			data.put("key", entity.getFlowname());
			data_jx.add(data);
		}
		try {
			response.getWriter().println(data_jx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getFlowByYwlxId(){
		response.setContentType("text/html;charset=UTF-8");
		String ywlx = request.getParameter("ywlx");
		JSONObject json = service.getEntityByYwlxId(ywlx);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ajaxAdd(){
		response.setContentType("text/html;charset=UTF-8");
		try {
			service.add(entity);
			response.getWriter().println("1");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Flow getEntity() {
		return entity;
	}
	public void setEntity(Flow entity) {
		this.entity = entity;
	}
	public void setService(FlowService service) {
		this.service = service;
	}

	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

	public void setCodeSetService(CodeSetService codeSetService) {
		this.codeSetService = codeSetService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
}


