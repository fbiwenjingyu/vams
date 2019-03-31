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
<title>合并条码</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>


<style type="text/css">
        tr{
           line-height: 35px;
        }
        
        .tds{
        	font-size: 18px;
        }
        .inp{
        	line-height: 35px;
        	width: 400px;
        	height: 35px;
        	vertical-align: middle;
        	font-size: 30px!important;
        	background: white;
        	border-width: 2px;
        }
</style>
     
<script type="text/javascript" >

function submitForm(){
	var valid = $("#form").validationEngine({returnIsValid: true});
	if(valid){
	  	form.submit();
	}else{
	    top.Dialog.alert('表单填写不正确');
	} 
  }
  
  
  /**自动跳转吓一条*/
 function autoXtbh(inp){
	//判断是否自动回车
	var isEnter= checkEnter(event);
	if(isEnter){
		var val=$(inp).val().trim();
		if(val != ""){
			$("input[name='lsh']").focus();
		}
	}
 }
  
 /**自动跳转吓一条*/
 function autoLsh(inp){
	//判断是否自动回车
	var isEnter= checkEnter(event);
	if(isEnter){
		var val=$(inp).val().trim();
		if(val != ""){
			$("button[name='smt']").focus().attr("disabled",true).find("span").attr("class","icon_loading");
			//显示处理
			$("#show").show();
			submitForm();
		}
	}
 }
</script>


</head>
<body   onkeydown="return onkeydownBody()">
 <form id="form" method="post" action="ycInfoBarcode!ycInfo_barCode.action" name="form"  target="frmright">
	<input type="hidden" name="url" value="<%=request.getParameter("url")%>"/>
	<table align="center">
	 		<tr>
                 <th align="center" colspan="2" style="font-size: 20px;"><br>六合一数据校验<br>（条码合并）<br></th> 
            </tr>
			<tr>
				<td class="tds">系统编号：</td>
				<td><input type="text"  name="xtdabh" value="<%=request.getParameter("xtdabh")%>" class="inp" onkeyup="autoXtbh(this)"/><span class="star">*</span><span id="msg"></span></td>
			</tr>
       		<tr>
				<td class="tds">流水号：</td>
				<td><input type="text"  name="lsh" class="inp" onkeyup="autoLsh(this)"/><span class="star">*</span><span id="msg">${msg }</span></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<span id="show" style="font-size: 18px;color: green;display: none;">正在处理，请稍后...</span><br>
				<button type="button" name="smt" onclick="submitForm('${update}')" ><span class="icon_ok">提交</span></button>
				<button type="button" name="cnl" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</body>
</html>
