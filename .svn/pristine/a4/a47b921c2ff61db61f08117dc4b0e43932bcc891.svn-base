<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="vs" uri="/vams_libs" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>出库签收单打印页</title>
<style type="text/css" media=print>  
.Noprint{display : none }  
</style>
<script>
	
function printSetup(){
	// 打印页面设置      
	wb.execwb(8,1);      
}      
function printPreView(){  
	pagesetup_null();
	// 打印页面预览    　　      
	wb.execwb(7,1);  
}      
function printIt(url){   
	  
	  pagesetup_null();
      window.print(); 
	    
}  
function windowClose(){
    window.close();
}; 

var hkey_root,hkey_path,hkey_key
hkey_root="HKEY_CURRENT_USER"
hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"
//设置网页打印的页眉页脚为空
function pagesetup_null(){
try{
    var RegWsh = new ActiveXObject("WScript.Shell")
    hkey_key="header" 
    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
    hkey_key="footer"
    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
    }catch(e){}
}
</script>
	</head>
	<body>
		<p class="Noprint" align="center">  
	   <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object>  
	  	<input type=button value="打印" onclick="printIt()"/>&nbsp;&nbsp;
	    <input type=button value="预览" onclick="printPreView()"/>&nbsp;&nbsp;
	    <input type=button value="设置" onclick="printSetup()"/> &nbsp;&nbsp;
	    <input type=button value="关闭" onclick="windowClose()"/>&nbsp;&nbsp;<br/>  
	</p>
	<br/>
	<table border="1" class="tableStyle" formMode="line" width="100%" style="font-size: 12px">
		<tr>
			<th colspan="2">档案出库签收单</th>
		</tr>
		<tr>
			<td width="30%">批次号：</td><td align="center">${pch}</td>
		</tr>
		<tr>
			<td>档案申请数量：</td><td align="center">${sl}</td>
		</tr>
		<tr>
			<td>出库申请人：</td><td align="center">${vs:userName(name)}（${name}）</td>
		</tr>
		<tr>
			<td>申请时间：</td><td align="center">${time}</td>
		</tr>
	</table>
	<table align="right" width="100%" class="tableStyle" formMode="line" style="font-size: 12px">
		<tr>
			<td>
				签收人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	</body>
</html>



