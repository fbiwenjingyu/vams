<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${vmessage.title}</title>
	<style type="text/css">
		body div{
			text-align: center;
		}
		.show{
			position:relative;
			top: 20px;
		}
		.show .msg {
			font-size: 25px;
			font-family: 黑体;
			font-weight: bold;
			color: #000; 
		}
		.show .detail{
			font-size: 16px;
			font-family: 黑体;
			font-weight: bold;
			color: #f94100; 
			margin-top: 20px;
		}
	</style>
  </head>
  
  <body>
  	
  	<div>
  		<img alt="${vmessage.title}" src="<%=basePath%>libs/images/error/warning1.png">
  	</div>
  	
  	<div class="show">
  		<div class="msg">${vmessage.msg}</div>
  		<div class="detail">${vmessage.detailMsg}</div>
  	</div>
  	
  </body>
</html>
