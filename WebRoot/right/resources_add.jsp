<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "resources";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资源管理添加页面</title>
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
	  
	  $.ajax({
	    	type:"POST",
	    	url:"resources!getSelect.action",
	    	//dataType:"json",
	    	success:function(data){
	    		var upresid = "${entity.upresid}";
	    		var seldata = "{\"list\":"+data+"}";
	    		$("#upresid").data("data",JSON.parse(seldata));
	    		$("#upresid").render(); 
	    		$("#upresid").setValue(upresid);
	    	}
	    });

	  
	  /**选择图标*/
	  function selectIcon(){
		  var diag = new top.Dialog();
			diag.Title = "手动关闭窗口";
			diag.URL = "resources!findIcon.action";
			diag.ShowButtonRow=true;
			diag.ShowOkButton=false;
			diag.CancelButtonText=" 关 闭 ";
			diag.show();
	  }
</script>
</head>
<body  onkeydown="return onkeydownBody()">
<form id="form" method="post" action="" name="form" target="frmright">
<input type="hidden" name="entity.resid" value="${entity.resid}" />
<input type="hidden" name="entity.openmode" value="${entity.openmode}" />
<!--<input type="hidden" name="entity.icon" value="${entity.icon}" />  -->
<c:if test="${update=='update'}"><input type="hidden" name="entity.upresid" value="${entity.upresid}" /></c:if>
<table class="tableStyle" formMode="line">
	<tr>
		<td class="ali03">资源名称：</td>
		<td><input type="text" name="entity.resname"
			value="${entity.resname}" 
			class="validate[required,length[0,50]]" /><span class="star">*</span></td>
	</tr>
	<tr>
		<td class="ali03">资源路径：</td>
		<td><input type="text" name="entity.resurl"
			value="${entity.resurl}"
			class="validate[length[0,100]]" /></td>
	</tr>
	<tr>
		<td class="ali03">上级资源：</td>
		<td><select id="upresid" <c:if test="${update=='update'}">disabled</c:if>
			name="entity.upresid" <c:if test="${entity.resid!='001'}">class="validate[required]"</c:if>  prompt="请选择">
		</select><c:if test="${entity.resid!='001'}"><span class="star">*</span></c:if></td>
	</tr>
	<tr>
		<td class="ali03">资源图片：</td>
		<td><input type="text" name="entity.icon"
			value="${entity.icon}" 
			class="validate[length[0,50]]" />
			<input type="button" value="&nbsp;查看图标路径&nbsp;" onclick="selectIcon()"/> 	
		</td>
	</tr>
	<tr>
		<td class="ali03">是否启用：</td>
		<td><input
			type="radio" id="radio-2" name="entity.enabled" value="1" <c:if test="${entity.enabled=='1'  || empty entity.enabled}"> checked </c:if>/><label
			for="radio-2" class="hand">启用</label><input type="radio" id="radio-1" name="entity.enabled"
			value="0" <c:if test="${entity.enabled=='0'}"> checked </c:if>/><label for="radio-1" class="hand">禁用</label> </td>
	</tr>
	<tr>
	<tr>
		<td class="ali03">资源描述：</td>
		<td><textarea name="entity.resdesc"
			class="validate[length[0,100]]">${entity.resdesc}</textarea></td>

	</tr>
	<tr>
		<td colspan="2">
		<button type="button" onclick="submitForm('${update}')"><span
			class="icon_ok">提交</span></button>
		<button type="button" onclick="closeWin()"><span
			class="icon_clear">取消</span></button>
		</td>
	</tr>
</table>
</form>
</body>
</html>