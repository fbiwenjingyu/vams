
<%@page import="org.apache.commons.lang.StringUtils"%>    <%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/taglib.jsp"%>
<%@page import="com.pd.system.startup.SYSLoadManager"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String syr = new String((request.getParameter("syr")).getBytes("iso-8859-1"),"utf-8");
	String hphm = request.getParameter("hphm");
	String hpzl = request.getParameter("hpzl");
	String vin = request.getParameter("vin");
	String ywlx = request.getParameter("ywlx");
	String xtdabh = request.getParameter("xtdabh");
	String storeNumber = request.getParameter("storeNumber");
%>

<html>
<head>
<title>打印条码</title>
<script type="text/javascript" >
$(function(){
		$("input[type='button']").removeAttr("disabled");
});
var printSetup = function(){
	// 打印页面设置      
	wb.execwb(8,1);      
}      
var printPreView = function(){  
	pagesetup_null();
	// 打印页面预览    　　      
	wb.execwb(7,1);  
}      
var printIt = function(url){      
	
	  pagesetup_null();
      window.print(); 
}  
var windowClose = function(){  
    window.opener=null;   
    window.open('','_self');   
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
<style type="text/css" media=print>  
.Noprint{display : none }  
</style>
</head>
<body>
	<div class="Noprint" style="position: absolute;right: 0px;top: 0px; z-index: 99;"> 
		<br>
	   	<object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object>  
	   	<br>
	  	<input type=button value="打印" disabled="disabled" onclick="printIt()"/>&nbsp;&nbsp;
	  	<br>
	    <input type=button value="预览" disabled="disabled" onclick="printPreView()"/>&nbsp;&nbsp;
	    <br>
	    <input type=button value="设置" disabled="disabled" onclick="printSetup()"/> &nbsp;&nbsp;
	    <br>
	    <input type=button value="关闭" disabled="disabled" onclick="windowClose()"/>&nbsp;&nbsp;
	</div>
	<table border="0" cellspacing="5px" align="center" width="100%" style="position:absolute;left:18px; top:22px; font-size: 12px;">
	  	 <tr>
			<td colspan="2">系统编号:<br/><img height="70" src="arcBaseInfo!write39CodeImage5.action?code=<%=xtdabh %>" /></td>
		</tr>
	    <tr>
			<td colspan="2">号牌号码：<font  style="font-size: 23px;"><%=hphm %>（<%=SYSLoadManager.hpzl_map.get(hpzl) %>）</font></td>
		</tr>
	
	    <tr>
			<td colspan="2">储位编号&nbsp;：<font  style="font-size: 23px;"><% if(StringUtils.isNotEmpty(storeNumber)){%><%=hpzl + "-" + storeNumber.substring(0,1) + "-" + storeNumber.substring(1,3) + "-" + storeNumber.substring(3,4) + "-" + storeNumber.substring(4,5) + "-" + storeNumber.substring(5,8) %> <%} %></font></td>
		</tr>
		<!-- 
		<tr>
			<td colspan="2">储位编号：<br><img src="arcBaseInfoMid!write39CodeImage.action?code=<%=hpzl+request.getParameter("storeNumber") %>" /></td>
		</tr>
		 -->
		<tr>
			<td colspan="2">车辆识别代号：<font  style="font-size: 16px;"><%=vin %></font></td>
		</tr>
		<!-- 
		<tr>
			<td colspan="2"><img src="arcBaseInfoMid!generateVinCodeImage.action?code=<%=vin %>" /></td>
		</tr>
		 -->
		<tr>
			<% 
				String cnYwlx = SYSLoadManager.ywlx_map.get(ywlx); 
				cnYwlx = cnYwlx==null?"":cnYwlx;
			%>
			<td width="45%">业务类型：<font  style="font-size: 20px;"><%=cnYwlx %></font></td>
		</tr>
		<!-- 
		<tr>
		   <td colspan="2" >六合一档案编号：<font  style="font-size: 18px;"><%=request.getParameter("bz") %></font></td>
		</tr>
		 -->	
		<tr>
			<td colspan="2">六合一流水号：<br><img height="60" src="arcBaseInfo!write39CodeImage5.action?code=<%=request.getParameter("lsh") %>" /></td>
		</tr>
		
	</table>
</body>
</html>
