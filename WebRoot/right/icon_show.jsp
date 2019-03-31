<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preActoin = "users";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>显示图标</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	
function copya(a){
	var d = a.id; 
	//IE浏览器
	if(window.clipboardData){
		window.clipboardData.clearData();
		window.clipboardData.setData('text', d); 
		$("#show1").html("复制成功！请粘贴到相应位置。（快捷键Ctrl+V）");
	}else{
		$("#show2").html("复制失败！请手工复制！");
	}
	
}
</script>
</head>
<body>
<div id="scrollContent">
<br />
<font style="font-size: 20px;">点击复制到剪切板：</font><font color="green" id="show1"></font><font color="red" id="show2"></font>
<br /><br />
	<div style="height: 320px; overflow: auto;">
		<c:forEach var="ico" items="${iconlist}" varStatus="status">
			<a id="${path}${ico}" onclick="copya(this)">&nbsp;<img src="${path}${ico}" alt="${path}${ico}" /> &nbsp;${path}${ico}</a><br/>
		</c:forEach>
	</div>
</div>
</body>
</html>
