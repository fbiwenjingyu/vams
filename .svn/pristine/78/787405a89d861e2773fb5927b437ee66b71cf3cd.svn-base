<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<title>档案标签打印 - ${print.xtdabh}</title>
<script type="text/javascript" >
$(function(){
		$("input[type='button']").removeAttr("disabled");
		${session.user.fastPrint=="1"?"printIt();":""}
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
    
	  ${session.user.fastPrint=="1"?"window.close();":""}
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
<style type="text/css" media="print">  
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
	<table border="0" cellspacing="5px" align="center" width="100%" style="position:absolute;left:18px; top:12px; font-size: 12px;">
	  	 <tr>
			<td>系统编号：<br/>
			<c:if test="${!empty print.xtdabh}">
				<img src="${pageContext.request.contextPath}/sys/barcode!writeCodeImage.action?code=${print.xtdabh}" />
			</c:if>
			<c:if test="${empty print.xtdabh}">
				<font style="font-size: 20px;">${print.xtdabh}</font>
			</c:if>
			</td>
		</tr>
	    <tr>
			<td>号牌号码：<font style="font-size: 30px;">${print.hphm}</font><font  style="font-size: 16px;">（${vs:hpzlName(print.hpzl)}）</font></td>
		</tr>
	
	    <tr>
			<td>储位编号&nbsp;：<font  style="font-size: 25px;">${print.cwbhprint}</font></td>
		</tr>
		<tr>
			<td>业务类型：<font  style="font-size: 18px;">${vs:ywlxName(print.ywlx)}</font></td>
		</tr>
		<tr>
		 	<td>档案编号：<font  style="font-size: 16px;">${print.dabh}</font></td>
		</tr>
		<tr>
			<td >车辆识别代号：<font  style="font-size: 18px;">${print.clsbdh}</font></td>
		</tr>
		<tr>
		<td>流水号：<br>
			<c:if test="${!empty print.lsh}">
			<img src="${pageContext.request.contextPath}/sys/barcode!writeCodeImage.action?code=${print.lsh}" />
			</c:if>
			<c:if test="${empty print.lsh}">
				<font  style="font-size: 18px;">${print.lsh}</font>
			</c:if>
			</td>
		</tr>
	</table>
</body>
</html>
