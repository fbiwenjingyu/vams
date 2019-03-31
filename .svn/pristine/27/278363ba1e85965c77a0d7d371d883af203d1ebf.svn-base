<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>档案导出预览页面</title>
<style type="text/css">
table,td,th,tr {
				border: 1px solid gray;
				border-collapse: collapse;
				
			}
			*{
			    font-size:12px;
			}

</style>
<script type="text/javascript" >

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
　　  if (confirm('确定打印吗？')) {  
	  pagesetup_null();
      window.print(); 
　　}      
}  
var windowClose = function(){  
	window.close(); 
}; 


var HKEY_Root,HKEY_Path,HKEY_Key; 
HKEY_Root="HKEY_CURRENT_USER"; 
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"; 
//设置网页打印的页眉页脚为空
function pagesetup_null(){
	try 
	 { 
	         var Wsh=new ActiveXObject("WScript.Shell"); 
	  HKEY_Key="header"; 
	  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); 
	  HKEY_Key="footer"; 
	  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); 
	 } 
	 catch(e){}
}
</script>
<style type="text/css" media=print>  
.Noprint{display : none }  
</style>
</head>
<body>
<p class="Noprint" align="center">  
   <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object>  
  	<input type=button value="打印" onclick="printIt()"/>&nbsp;&nbsp;
    <input type=button value="预览" onclick="printPreView()"/>&nbsp;&nbsp;
    <input type=button value="设置" onclick="printSetup()"/> &nbsp;&nbsp;
    <input type=button value="关闭" onclick="windowClose();"/>&nbsp;&nbsp;<br/>  
</p>
<br/>

	<table width="98%">
		<tr>
			<th width="30"><span>序号</span></th>
			<th><span>流水号</span></th>
			<th><span>系统编号</span></th>
			<th><span>储位编号</span></th>
			<th><span>批次</span></th>
			<th><span>机动车序号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>部门</span></th>
			<th><span>业务办理时间</span></th>
			<th><span>业务办理人</span></th>
			<th><span>审核人</span></th>
			<th><span>审核时间</span></th>
			<th><span>归档人</span></th>
			<th><span>归档时间</span></th>
			<th><span>档案状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center"  ondblclick="tr_ondbclick('${entity.xtdabh }','${entity.dalx}','${entity.lsh}')" >
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.xtdabh} </td>
				<td align="center">&nbsp;${entity.cwbh} </td>
				<td align="center">&nbsp;${entity.pc} </td>
				<td align="center">&nbsp;${entity.xh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx] }</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl] }</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${deptMap[entity.deptcode]}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.cjsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">&nbsp;${entity.cjrmc}</td>
				<td align="center">&nbsp;${entity.shrmc}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.shsj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="center">&nbsp;${entity.gdrmc}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.gdsj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="center">&nbsp;${arcStatus[entity.dazt] }</td>
		   </tr>
		</c:forEach>
	</table>

</body>
</html>
