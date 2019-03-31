<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String str= getServletContext().getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>无外检双条码打印</title>
   <!-- 引用公用路径Start -->
   <%@ include file="/common/meta.jsp"%>
   <%@ include file="/common/taglib.jsp"%>
<script type="text/javascript" >
     $(function(){
       top.Dialog.open({URL:"ycInfo!getCodeDouble.action?sfwj=N",Title:"打印条码",Width:"400px;",Height:"200px;"}); 
        window.location.href="<%=str %>/system/layout/bg.jsp";
     })
</script>
</head>
<body >

</body>
</html>
