package com.pd.right.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pd.right.model.SuperUser;
import com.pd.right.service.UsersService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.BaseAction;
import com.pd.system.startup.SYSLoadManager;
import com.pd.tjcx.service.ArcBaseYcInfoService;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String userCode;
	private String password;
	private UsersService usersService;
	private ArcBaseYcInfoService arcBaseYcInfoService;
	private static boolean isPass = true;

	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();

		// 验证是否为空
		if ((null == userCode || "".equals(userCode.trim()))) {
			return ERROR("用户名不能为空！");
		} else if (null == password || "".equals(password.trim())) {
			return ERROR("密码不能为空！");
		}

		// 获取用户信息
		SuperUser user = usersService.getUser(userCode,
				DigestUtils.md5Hex(password));
		if (user == null) {
			request.setAttribute("msg", "alert('登录名或密码不正确!')");
			return "login";
		} else if (null != user && "0".equals(user.getUserstat() + "")) {
			request.setAttribute("msg", "alert('用户已锁定，禁止登陆!')");
			return "login";
		}

		// 记录serverPath，服务器的项目路径
		if ("".equals(SYSLoadManager.serverPath)) {
			try {
				SYSLoadManager.serverPath = "http://"
						+ InetAddress.getLocalHost().getHostAddress() + ":"
						+ request.getServerPort() + request.getContextPath()
						+ "/";
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 折线统计一周前日期外检和扫描的数量
		 */
		// List list = arcBaseYcInfoService.getWeekCount();
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("user", user);
		// ac.getSession().put("list", list);
		// 载入用户资源
		user.loadRes();

		// 记录日志信息
		LogInfo.infoXt("登录系统", request, user.getUserCode(), user.getUserName()
				+ "（" + user.getUserCode() + "）");

		Long roleId = user.getRoleId();
		if (roleId.toString().equals("75")) {
			return "admin";
		} else {
			return "main";
		}

	}

	public String loginRedirect() {
		return "main";
	}

	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 退出时清空session中的内容
		ActionContext ac = ActionContext.getContext();
		SuperUser user = (SuperUser) ac.getSession().get("user");
		try {
			LogInfo.info("退出系统", request.getLocalAddr(), "", "1",
					Long.parseLong("1"), user.getUserCode(), "退出系统");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ac.getSession().remove("user");
		// 清除当前session
		ac.getSession().clear();
		return "login";
	}

	/**
	 * 防止重复提示错误信息
	 */
	public void prepare() {
		clearErrorsAndMessages();
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public void setArcBaseYcInfoService(
			ArcBaseYcInfoService arcBaseYcInfoService) {
		this.arcBaseYcInfoService = arcBaseYcInfoService;
	}

}
