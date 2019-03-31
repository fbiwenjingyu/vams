package com.pd.base.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.base.model.Attr;
import com.pd.base.model.AttrExample;
import com.pd.base.model.AttrExample.Criteria;
import com.pd.base.service.AttrService;
import com.pd.right.model.SuperUser;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;
import com.pd.system.startup.SYSLoadManager;

/**
 * URL:attr!xxx.action
 * @author wangwei
 * 2013-9-17
 */
public class AttrAction extends ActionSupport {
	protected final Log log = LogFactory.getLog(getClass());
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Attr entity;
	private AttrService service;
	AttrExample example = new AttrExample();
	SuperUser user = (SuperUser) request.getSession().getAttribute("user");
	/**
	 * 
	* @Title: query 
	*  属性分页查询
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
				if(entity.getAttrCode()!=null && entity.getAttrCode().length()>0){
					ca.andAttrCodeLike("%"+entity.getAttrCode()+"%");
				}
				if(entity.getAttrkey()!=null && entity.getAttrkey().length()>0){
					ca.andAttrkeyLike("%"+entity.getAttrkey()+"%");
				}
				if(entity.getAttrtype()!=null && entity.getAttrtype().length()>0){
					ca.andAttrtypeLike("%"+entity.getAttrtype()+"%");
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			example.setOrderByClause(" attrtype ");
			List<Attr> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "attr_list";
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
		if(entity.getAttrCode() !=null && entity.getAttrCode().length()>0){
			example.createCriteria().andAttrCodeEqualTo(entity.getAttrCode());
		}
		List<Attr> list = service.selectByExample(example);
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
	* @Title: add 
	*  系统属性添加操作
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
			LogInfo.info("系统属性添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "用户添加系统属性");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='attr!query.action';</script>");
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='attr!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: preUpdate 
	*  属性修改查询
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void preUpdate(){
		try {
			Attr entity = service.getEntity(this.entity.getId());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("base/attr_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  属性修改保存
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
				SYSLoadManager.reload();
			}else{
				msg = "修改失败";
			}
			LogInfo.info("属性修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "用户修改属性");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='attr!query.action'</script>");
		} catch (Exception e) {
			log.error("update error......");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	* @Title: delete 
	*  根据id删除属性信息
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
			LogInfo.info("属性删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除属性");
			response.getWriter().write("<script>alert('"+msg+"');window.location='attr!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  属性下拉框选项
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-26
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		List<Attr> list_data = service.selectByExample(null);
		for (Attr entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("attrCode", entity.getAttrCode());
			data.put("attrKey", entity.getAttrkey());
			data_jx.add(data);
		}
		try {
			response.getWriter().println(data_jx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传客户端文件 
	 * 
	 * 
	 * @author wangwei
	 * 2013-9-17
	 */
	private File file1;
	private String file1ContentType;
	private String file1FileName;
	
	public void uploadClient(){
		response.setContentType("text/html;charset=UTF-8");
		
		File saveFile = new File(request.getRealPath("/download")+ File.separator + file1FileName);
		if (!saveFile.getParentFile().exists())
			saveFile.getParentFile().mkdirs();
		try {
			// 复制文件
			FileUtils.copyFile(file1, saveFile);
			response.getWriter().println("<script>alert('上传成功!');window.location.href='uploadClient.jsp';</script>");
		} catch (IOException e) {
			e.printStackTrace();
			try {
				response.getWriter().println("<script>alert('上传失败，请与管理员联系!');window.location.href='uploadClient.jsp';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}

	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Attr getEntity() {
		return entity;
	}
	public void setEntity(Attr entity) {
		this.entity = entity;
	}
	public void setService(AttrService service) {
		this.service = service;
	}
	
	
}


