<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String tag=path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>重新登录</title> 
	<script type="text/javascript">
		
      function gologin(win){
     	//如果url地址相同说明到达最顶层,否则递归调用
     	if (win.location==win.top.location) {
			win.location="${pageContext.request.contextPath}/system/login/index.jsp";
		} else {
			gologin(win.top);
		}
     }
     gologin(window);
     </script>
	
  </head>
  
  <body>
  </body>
</html>
