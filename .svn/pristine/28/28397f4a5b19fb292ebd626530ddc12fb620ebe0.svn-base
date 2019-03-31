<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看图片</title>
    <%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
    <script language="javascript">
        function verifyBtg(){
             var wjbh=document.getElementById("wjbh").value;
             var bzsm=document.getElementById("bzsm").value;
             closeWin();
             
             var url="<%=preAction %>!noPicVeirfy.action?wjbh="+wjbh+"&bz="+bzsm;
			 window.open (url, '打印回执单', 'height=400, width=520, top=100, left=200,scrollbars=yes, toolbar=no, menubar=no,  resizable=no,location=no, status=no');
        }
    </script>
  </head>
  
  <body style="text-align: center;">
  <div style="text-align: left;padding-left:43%">
    <br/><br/><h4>没有图片！</h4>
    <br/><br/>
    外检编号：<font style="color:red;font-size: 14px;">${wjbh }</font>
    <input id="wjbh" type="hidden" value="${wjbh }"  style="width: 200px;"/><br/>
    审核说明：
    <input type="text" id="bzsm" value=""  id="bzsm" style="width: 200px;"/><br/>
    审核结果：
    <input type="radio" name="field＿name" checked value="'value" /> 不通过
    <br/><br/>
    <input type="button" value="&nbsp;&nbsp;打印回执单&nbsp;&nbsp;" onclick="verifyBtg()"/>
    </div>
  </body>
</html>
