<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "ycTypePaper";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>部门机构添加修改页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	  function submitForm(update){
		var valid = $("#form").validationEngine({returnIsValid: true});
		if(valid){
			if(update == "update"){
		  		form.action = "<%=path %>/<%=preAction %>!update.action";
		  		top.Dialog.confirm('您确定修改吗?',function(){
					form.submit();
                	top.Dialog.close();
                	},
                	function(){
                    	top.Dialog.close();
                    });
		  	}else{
		  		form.action = "<%=path %>/<%=preAction %>!add.action";
		  		form.submit();
		  	}
		  	
		}else{
		    top.Dialog.alert('表单填写不正确');
		} 
	  }

</script>
</head>
<body   onkeydown="return onkeydownBody()">
<div class="box1" panelWidth="590" panelHeight="360">

 <form id="form" method="post" action="" name="form"  target="frmright" class="formTable">
<input type="hidden"  name="ycPaper.id" value="${ycPaper.id}" />
	<table  >
       		<tr>
				<td  class="ali03">业务类型：</td><td>
				<select id="ywlx" name="entity.ywlx" class="validate[required]" >
					<option value="">请选择</option>
					<c:forEach var="code" items="${list_code}" varStatus="status">
						<c:if test="${code.type == '2'}">
							<option value="${code.code}">${code.name }</option>
						</c:if>
					</c:forEach>
				</select><span class="star">*</span>span</td>
			</tr>
       		<tr>
				<td  class="ali03">号牌种类：</td><td>
				<select id="hpzl" name="entity.hpzl" class="validate[required]" >
					<option value="">请选择</option>
					<c:forEach var="code" items="${list_code}" varStatus="status">
						<c:if test="${code.type == '3'}">
							<option value="${code.code}">${code.name }</option>
						</c:if>
					</c:forEach>
				</select><span class="star">*</span></td>
			</tr>
       		<tr>
				<td  class="ali03">纸张类型：<span class="star">*</span></td>
				<td>
					<table align="left" style="border: 0px;">
						<c:forEach var="paper" items="${list_paper}" varStatus="status">
							<c:if test="${status.index==0}">
								<tr align="left" style="border: 0px;">
							</c:if>
							<td style="border: 0px;" align="left">&nbsp;<input type='checkbox'
								value='${paper.takecode}' name='takecodes' class='validate[required]' />&nbsp;${paper.takeName }
							</td>
							<c:if test="${(status.index+1)%5==0}">
								</tr>
								<tr style="border: 0px;">
							</c:if>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<button type="button"  onclick="submitForm('${update}')"><span class="icon_ok">提交</span></button>&nbsp;
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>

</body>
</html>