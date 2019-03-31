package com.pd.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.model.CodesetExample;
import com.pd.base.model.Paper;
import com.pd.base.model.PaperExample;
import com.pd.base.model.Typeset;
import com.pd.base.model.TypesetExample;
import com.pd.base.model.TypesetExample.Criteria;
import com.pd.base.service.CodeSetService;
import com.pd.base.service.PaperService;
import com.pd.base.service.TypeSetService;
import com.pd.right.model.SuperUser;
import com.pd.right.service.RoleService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;

public class TypesetAction extends ActionSupport {
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Typeset entity;
	private TypeSetService service;
	TypesetExample example = new TypesetExample();
	private CodeSetService codeSetService;
	private PaperService paperService;
	private RoleService roleService;
	private String[] paper;
	private String[] optPaper;
	private String[] type;
	private String typeid;
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	/**
	 * 
	* @Title: query 
	*  角色业务纸张关系查询
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
				if(entity.getRoleId()!=null && entity.getRoleId().toString().length()>0){
					ca.andRoleIdEqualTo(entity.getRoleId());
				}
				if(entity.getTypeId()!=null && entity.getTypeId().length()>0){
					ca.andTypeIdEqualTo(entity.getTypeId());
				}
				if(entity.getPaperId()!=null && entity.getPaperId().length()>0){
					ca.andPaperIdEqualTo(entity.getPaperId());
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			example.setOrderByClause("ROLE_ID,TYPE_ID");
			List<Typeset> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("codeMap", codeSetService.queryToMap(null));
			request.setAttribute("paperMap", paperService.queryToMap(null));
			request.setAttribute("roleMap", roleService.queryToMap(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "typeset_list";
	}
	
	public void addUpdate(){
		try {
			CodesetExample codeExample = new CodesetExample();
			CodesetExample.Criteria criteria = codeExample.createCriteria();
			
			PaperExample paperExample = new PaperExample();
			paperExample.setOrderByClause("paper_name");
			
			criteria.andTypeEqualTo("2");
			List codelist = codeSetService.selectByExample(codeExample);
			List paperlist = paperService.selectByExample(paperExample);
			request.setAttribute("paperlist", paperlist);
			request.setAttribute("codelist", codelist);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("base/typeset_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: add 
	*  角色业务纸张关系添加
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
			service.add(entity,paper,optPaper,type);
			msg = "添加成功";
			LogInfo.info("角色业务纸张关系添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "设置角色业务纸张关系");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='typeset!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='typeset!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: preUpdate 
	*  修改角色业务纸张关系
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void preUpdate(){
		try {
			Criteria criteria = example.createCriteria();
			
			List<Typeset> list = new ArrayList();
			Typeset typeset = service.getEntity(this.entity.getId());
			if(typeset != null){
				criteria.andRoleIdEqualTo(typeset.getRoleId());
				criteria.andTypeIdEqualTo(typeset.getTypeId());
				list = service.selectByExample(example);
			}
			
			/*
			 * 获取可扫，必扫已选择的纸张ID
			 * 作为前端初始化checkbox启用/禁用
			 */
			String needPaper = "", optPaper = "";
			PaperExample paperExample = new PaperExample();
			paperExample.setOrderByClause("paper_name");
			
			List<Paper> paperlist = paperService.selectByExample(paperExample);
			for (Paper paper : paperlist) {
				for (Typeset ts : list) {
					if("1".equals(ts.getF1())){
						if(ts.getPaperId().equals(paper.getId())){
							needPaper += "," + paper.getId();
						}
					}else if("0".equals(ts.getF1())){
						if(ts.getPaperId().equals(paper.getId())){
							optPaper += "," + paper.getId();
						}
					}
				}
			}
			
			request.setAttribute("needPaper", needPaper.replaceFirst(",", ""));
			request.setAttribute("optPaper", optPaper.replaceFirst(",", ""));
			request.setAttribute("paperlist", paperlist);
			request.setAttribute("list", list);
			request.setAttribute("entity", typeset);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("base/typeset_update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  修改角色业务纸张关系
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
			service.update(entity,paper,optPaper);
			msg = "修改成功";
			LogInfo.info("角色业务纸张关系修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "设置角色业务纸张关系");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='typeset!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "修改失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='typeset!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  删除
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
			LogInfo.info("角色业务纸张关系删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除角色业务纸张关系");
			response.getWriter().write("<script>alert('"+msg+"');window.location='typeset!query.action'</script>");
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
	public Typeset getEntity() {
		return entity;
	}
	public void setEntity(Typeset entity) {
		this.entity = entity;
	}
	public void setService(TypeSetService service) {
		this.service = service;
	}
	public String[] getPaper() {
		return paper;
	}

	public void setPaper(String[] paper) {
		this.paper = paper;
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

	public void setPaperService(PaperService paperService) {   
		this.paperService = paperService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String[] getOptPaper() {
		return optPaper;
	}

	public void setOptPaper(String[] optPaper) {
		this.optPaper = optPaper;
	}
	
	
}


