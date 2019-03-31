<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合并条码-六合一条码号与系统拆解</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <%@ include file="/common/meta.jsp"%>
     <%@ include file="/common/taglib.jsp"%>
     <script type="text/javascript" >
     $(function(){
        var url="arc/wjcy_cjtm.jsp";
        dialog = new top.Dialog();
				dialog.URL=url;
				dialog.Width=800;
				dialog.Height=450;
				dialog.ID="d3";
				dialog.CancelEvent = function(){
					dialog.close();
					removeScreenOver();
					window.location.href="<%=path%>/system/layout/open.jsp";
				};
				dialog.show();
        //top.Dialog.open({URL:"",Title:"合并条码",Width:"800px;",Height:"450px;"}); 
        
     })
     </script>
  </head>
<body>
   
</body>
</html>
