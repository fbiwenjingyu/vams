<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>登录机动车档案综合管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>system/login2/img/login.css">
	<script type="text/javascript">
		
		var login=false;
		function main(){
			if(login){
				return;
			}
			var userCode = document.getElementById("userCode").value;
			var password = document.getElementById("password").value;
			var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi; 
			if(patrn.test(password)){ 
				alert("密码不能含中文"); 
				return;
			}
			if(userCode==""){
				alert("用户名不能为空！");
				return ;
			}
			if(password==""){
				alert("密码不能为空！");
				return ;
			}
			form.submit();
			
			//禁用，防止反复登录
			login=true;
			document.getElementById("loginbtn").disabled=true;
			document.getElementById("userCode").disabled=true;
			document.getElementById("password").disabled=true;
			var msg=document.getElementById("msg");
			msg.innerHTML="正在登陆，请稍后...";
			msg.style.color="white";
		}

		function  subCheck(){
			if(event.keyCode==13){	
				main();
			}
		}
		
		//登录错误信息返回
		${msg}
	</script>
  </head>
  
  <body style="overflow-y: hidden" onkeydown="subCheck()" onpaste="return false" oncopy="return false" oncut="return false" onselect='document.selection.empty()' oncontextmenu='return false'>
	
  <div id="container">
	    <div class="login_bg">
	        <div class="login_nei">
		        <form action="login!login.action" name="form" method="post">
				    <table>
					  <tr>
					    <td class="login_left" align="right">用户名</td>
					    <td colspan="2"><input class="login_mid" name="userCode" type="text" id="userCode" value="admin"/></td>
					  </tr>
					  <tr>
					    <td class="login_left" align="right">密&nbsp;&nbsp;&nbsp;码</td> 
					    <td><input class="login_mid" type="password" name="password"  id="password" value="1234567"/></td>
					    <td><input class="login_button" type="button" id="loginbtn" value="登&nbsp;录" onclick="main()"/></td>
					  </tr>
					  <tr>
					     <td class="login_ts" colspan="3" id="msg">**忘记密码请联系管理员！</td>
					  </tr>
					</table>
				</form>
		    </div>
		</div>
  </div>

  &copy; CopyRight&nbsp;&nbsp;&nbsp; 2013-2023 &nbsp;&nbsp;&nbsp;武汉品度科技有限公司  <span  style="font-size:12px;">设计开发</span>
<br><br>
建议使用1366*768或者更高分辨率及 <b>IE8</b> 浏览器<a href="download/IE8.exe" style="color: #999999;text-decoration: none">【下载】</a>
</body>
</html>
