package com.pd.right.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.right.model.SuperUser;
import com.pd.right.model.Users;
import com.pd.right.model.UsersExample;
import com.pd.right.model.UsersExample.Criteria;
import com.pd.right.service.DeptService;
import com.pd.right.service.RoleService;
import com.pd.right.service.UsersService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.Pagination;
import com.pd.system.startup.SYSLoadManager;

public class UsersAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private int index = 1;
	private Users entity;
	UsersExample example = new UsersExample();
	private UsersService service;
	private DeptService deptService;
	private RoleService roleService;
	HttpSession session = request.getSession();
	Users user = (Users) session.getAttribute("user");
	
	/**
	 * 密码修改验证旧密码是否正确
	* @Title: oldPwd 
	* @Description: TODO
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-3-4
	 */
	public void oldPwd(){
		String msg = "0";
		response.setContentType("text/plain;charset=UTF-8");
		String oldPwd = request.getParameter("oldPwd");
		try {
			SuperUser user = service.getUser(entity.getUserCode(), DigestUtils.md5Hex(oldPwd));
			if(null!=user){
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
	* @Title: query 
	*  用户信息分页查询
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
				if(entity.getUserCode()!=null && entity.getUserCode().length()>0){
					ca.andUserCodeLike("%"+entity.getUserCode()+"%");
				}
				if(entity.getUserName()!=null && entity.getUserName().length()>0){
					ca.andUserNameLike("%"+entity.getUserName()+"%");
				}
				if(entity.getDeptCode()!=null && entity.getDeptCode().length()>0){
					ca.andDeptCodeEqualTo(entity.getDeptCode());
				}
				if(entity.getRoleId()!=null && entity.getRoleId().toString().length()>0){
					ca.andRoleIdEqualTo(entity.getRoleId());
				}
			}
			Pagination page = service.getPageByExample(index, example);
			example.setStart((index*page.getPageSize()-page.getPageSize())+1);
			example.setLimit(page.getPageSize()+example.getStart()-1);
			
			List<Users> list_data = service.query(example);
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("deptMap", deptService.queryToMap(null));
			request.setAttribute("roleMap", roleService.queryToMap(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "users_list";
	}
	
	/**
	 * 
	* @Title: add 
	*  用户添加
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
			entity.setCreateDate(new Date());
			entity.setPassword(DigestUtils.md5Hex(entity.getPassword()));
			service.add(entity);
			msg = "添加成功";
			//刷新用户缓存
			SYSLoadManager.init_user();
			//刷新警员编号
			SYSLoadManager.user2Jybh();
			
			LogInfo.info("用户添加", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "添加用户");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='users!query.action';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='users!query.action';</script>");
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
		if(entity.getUserCode() !=null && entity.getUserCode().length()>0){
			example.createCriteria().andUserCodeEqualTo(entity.getUserCode());
		}
		List<Users> list = service.selectByExample(example);
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
	*  用户修改获取对象
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	public void preUpdate(){
		try {
			Users entity = service.getEntity(this.entity.getUserCode());
			request.setAttribute("entity", entity);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("right/users_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: update 
	*  用户修改
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
			if(entity.getPassword()==null || entity.getPassword().length()==0 || "输入修改密码".equals(entity.getPassword())){
				entity.setPassword(null);
			}else{
				entity.setPassword(DigestUtils.md5Hex(entity.getPassword()));
			}
			int result = service.update(entity);
			if(result==1){
				msg = "修改成功";
			}else{
				msg = "修改失败";
			}
			LogInfo.info("用户修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "修改用户");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='users!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: updatePwd 
	*  密码修改返回登录页面
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-7-4
	 */
	public void updatePwd() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			entity.setPassword(DigestUtils.md5Hex(entity.getPassword()));
			int result = service.update(entity);
			if(result==1){
				msg = "修改成功,请重新登录";
				LogInfo.info("密码修改", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "修改密码");
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.parent.location='index.jsp'</script>");
			}else{
				msg = "修改失败";
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');</script>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: delete 
	*  用户删除
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
			int result = service.delete(entity.getUserCode());
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			LogInfo.info("用户删除", request.getLocalAddr(),"","1", Long.parseLong("1"), user.getUserCode(), "删除用户");
			response.getWriter().write("<script>alert('"+msg+"');window.location='users!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getSelect 
	*  获取用户下拉列表信息
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		List<Users> list_data = service.selectByExample(null);
		for (Users entity : list_data) {
			JSONObject data = new JSONObject();
			data.put("userCode", entity.getUserCode());
			data.put("userName", entity.getUserName());
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
	public Users getEntity() {
		return entity;
	}
	public void setEntity(Users entity) {
		this.entity = entity;
	}
	public void setService(UsersService service) {
		this.service = service;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
}


