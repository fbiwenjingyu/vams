<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "users";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>当前用户设置</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	  function submitForm(){
	  }
	  
	  /**快捷打印设置保存*/
	  function saveFastPrint(){
		  var req;
		  var fastPrint=$("input[name='fast_print']:checked").val();
		  if(fastPrint&&fastPrint.trim()=="1"){
			  req={"param":"fastPrint"};
		  }else{
			  req={"param":""};
		  }
		  $.post("${pageContext.request.contextPath}/sys/barcode-fastPrintSet.action",req,function(data){
			  alert(data[1]);
			  if(data[0]=="1"){
				  closeWin();
			  }
		  });
	  }
	  
	
	  /**保存*/
	  function saveParam(){
		  saveFastPrint();//保存快捷打印设置
	  }
	  
</script>
</head>
<body  onkeydown="return onkeydownBody()">
	<table class="tableStyle" formMode="line">
			<tr>
				<td  class="ali03"><input type="checkbox" value="1" name="fast_print"  ${sessionScope.user.fastPrint=="1"?"checked='checked'":""}/></td><td>快捷标签打印</td>
			</tr>
			<tr>
				<td  class="ali03"></td><td></td>
			</tr>	
			<tr>
				<td colspan="2">
				<button type="button"  onclick="saveParam()"><span class="icon_ok">保存</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
   </table>
</body>
</html>