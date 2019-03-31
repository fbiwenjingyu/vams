<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String preActoin = "ycInfo";
String str= getServletContext().getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>条码拆解</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>

<script type="text/javascript" >

function submitForm(){
	var valid = $("#form").validationEngine({returnIsValid: true});
	if(valid){
	  	form.submit();
	}else{
	    top.Dialog.alert('表单填写不正确');
	} 
  }
</script>


</head>
<body   onkeydown="return onkeydownBody()">
 <form id="form" method="post" action="ycInfoBarcode!ycInfo_barCode.action" name="form"  target="frmright">
	<table class="tableStyle" formMode="line">
			<tr>
				<td  class="ali03">系统编号：</td><td><input type="text"  name="xtdabh" value="" class="validate[required,length[0,13]]" /><span class="star">*</span><span id="msg"></span></td>
			</tr>
       		<tr>
				<td  class="ali03">流水号：</td><td><input type="text"  name="lsh" class="validate[required,length[0,13]]" /><span class="star">*</span><span id="msg">${msg }</span></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<button type="button"  onclick="submitForm('${update}')"><span class="icon_ok">提交</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</body>
</html>
