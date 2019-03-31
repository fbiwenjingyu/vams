package com.pd.base.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.model.Paper;
import com.pd.base.model.PaperExample;
import com.pd.base.model.PaperExample.Criteria;
import com.pd.base.service.PaperService;
import com.pd.base.service.SeclevelService;
import com.pd.right.model.SuperUser;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;
import com.pd.system.startup.SYSLoadManager;

public class PaperAction extends ActionSupport {
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Paper entity;
	private PaperService service;
	private SeclevelService seclevelService;
	PaperExample example = new PaperExample();
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	/**
	 * 
	* @Title: query 
	*  档案页查询
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
				if(entity.getPaperName()!=null && entity.getPaperName().length()>0){
					ca.andPaperNameLike("%"+entity.getPaperName()+"%");
				}
				if(entity.getPaperType()!=null && entity.getPaperType().length()>0){
					ca.andPaperTypeLike("%"+entity.getPaperType()+"%");
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			
			List<Paper> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("levelMap", seclevelService.queryToMap(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "paper_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  添加扫描档案页信息
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
			LogInfo.info("档案页添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加档案页信息");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='paper!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='paper!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: isExists 
	*  档案页代码是否重复
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-27
	 */
	public void isExists(){
		String msg = "";
		response.setContentType("text/plain;charset=UTF-8");
		if(entity.getPaperType() !=null && entity.getPaperType().length()>0){
			example.createCriteria().andPaperTypeEqualTo(entity.getPaperType());
		}
		List<Paper> list = service.selectByExample(example);
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
	*  获取修改档案页信息
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void preUpdate(){
		try {
			Paper entity = service.getEntity(this.entity.getId());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("base/paper_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  档案页信息修改
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
			SYSLoadManager.reload();
			LogInfo.info("档案页信息修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "修改档案页信息");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='paper!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  删除档案页信息
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
			LogInfo.info("档案页删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除档案页信息");
			response.getWriter().write("<script>alert('"+msg+"');window.location='paper!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  扫描档案页下拉选择
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		List<Paper> list_data = service.selectByExample(null);
		for (Paper entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("value", entity.getId());
			data.put("key", entity.getPaperName());
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
	public Paper getEntity() {
		return entity;
	}
	public void setEntity(Paper entity) {
		this.entity = entity;
	}
	public void setService(PaperService service) {
		this.service = service;
	}

	public void setSeclevelService(SeclevelService seclevelService) {
		this.seclevelService = seclevelService;
	}
	
	
}


