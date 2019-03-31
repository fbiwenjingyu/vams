package com.pd.system.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	private List<String> notFilterList = com.pd.system.control.FilterConfig
			.getNotFilterList();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out
				.println("-----------------------LoginFilter-----------------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
//		HttpServletResponse servletResponse = (HttpServletResponse) response;
		// HttpSession session = servletRequest.getSession();
		// 从session里取登录信息信息(Users)servletRequest.getSession()getAttribute("user")
		// Users user = (Users) session.getAttribute("user");
		
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		String ctxPath = servletRequest.getContextPath();// 得到web应用程序的上下文路径
		// String uri = path.substring(ctxPath.length()); // 去除上下文路径，得到剩余部分的路径

		// 页面过滤，在filterConfig中配置不过滤的路径
		for (int i = 0; i < notFilterList.size(); i++) {
			String tag = notFilterList.get(i);
			if (path.indexOf(tag) != -1) {//如果存在，则不拦截
				chain.doFilter(request, response);
				return;//不再执行下边的代码：不在进行session用户判断
			}
		}

		// 判断如果没有取到用户信息,就跳转到登陆页面
		if (servletRequest.getSession().getAttribute("user") == null) {
			// 跳转到登陆页面
			((HttpServletResponse) response).sendRedirect(ctxPath + "/gologin.jsp");
		} else {
			// 已经登陆,继续此次请求
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
