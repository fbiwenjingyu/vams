package com.pd.base.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.model.Codeset;
import com.pd.base.model.CodesetExample;
import com.pd.base.model.CodesetExample.Criteria;
import com.pd.base.service.CodeSetService;
import com.pd.right.model.SuperUser;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;
import com.pd.system.startup.SYSLoadManager;

public class CodeSetAction extends ActionSupport {
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Codeset entity;
	private CodeSetService service;
	CodesetExample example = new CodesetExample();
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	/**
	 * 
	* @Title: query 
	*  系统代码分页查询
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
				if(entity.getCode()!=null && entity.getCode().length()>0){
					ca.andCodeLike("%"+entity.getCode()+"%");
				}
				if(entity.getName()!=null && entity.getName().length()>0){
					ca.andNameLike("%"+entity.getName()+"%");
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			
			List<Codeset> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "codeSet_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  系统代码保存
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
			SYSLoadManager.reload();
			msg = "添加成功";
			LogInfo.info("系统代码添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加系统代码");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='codeSet!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='codeSet!query.action';</script>");
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
		if(entity.getCode() !=null && entity.getCode().length()>0){
			example.createCriteria().andCodeEqualTo(entity.getCode());
		}
		List<Codeset> list = service.selectByExample(example);
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
	*  系统代码修改
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void preUpdate(){
		try {
			Codeset entity = service.getEntity(this.entity.getId());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("base/codeSet_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  系统代码修改保存
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
			SYSLoadManager.reload();
			if(result==1){
				msg = "修改成功";
			}else{
				msg = "修改失败";
			}
			LogInfo.info("系统代码修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "修改系统代码");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='codeSet!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  系统代码删除
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
			SYSLoadManager.reload();
			LogInfo.info("系统代码删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除系统代码");
			response.getWriter().write("<script>alert('"+msg+"');window.location='codeSet!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  系统代码下拉框选择
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		Criteria ca = example.createCriteria();
		if(entity!=null){
			if(entity.getType()!=null && entity.getType().length()>0){
				ca.andTypeEqualTo(entity.getType());
			}
		}
		example.setOrderByClause(" code");
		JSONArray data_jx = new JSONArray();
		List<Codeset> list_data = service.selectByExample(example);
		for (Codeset entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getId());
			data.put("key", entity.getCode()+":"+entity.getName());
			data_jx.add(data);
		}
		try {
			response.getWriter().println(data_jx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  code name下拉框选择
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-7-4
	 */
	public void getSelectByCode(){
		response.setContentType("text/html;charset=UTF-8");
		Criteria ca = example.createCriteria();
		if(entity!=null){
			if(entity.getType()!=null && entity.getType().length()>0){
				ca.andTypeEqualTo(entity.getType());
			}
		}
		example.setOrderByClause(" code");
		JSONArray data_jx = new JSONArray();
		List<Codeset> list_data = service.selectByExample(example);
		for (Codeset entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getCode());
			data.put("key", entity.getCode()+":"+entity.getName());
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
	public Codeset getEntity() {
		return entity;
	}
	public void setEntity(Codeset entity) {
		this.entity = entity;
	}
	public void setService(CodeSetService service) {
		this.service = service;
	}
	
	
}


