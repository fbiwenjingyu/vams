<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "users";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>密码修改页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	  function submitForm(update){
		var valid = $("#form").validationEngine({returnIsValid: true});
		var oldpwd = $('#oldpwd').val();
		var password = $('#pwd').val();
		var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi; 
		if(oldpwd!="" && oldpwd==password){
			alert("新密码和旧密码一样");
			$("input[name='entity.password']").focus();
			$("input[name='entity.password']").val("");
			$("#comfirmpwd").val("");
			return;
		}
		if(patrn.test(password)){ 
			alert("密码不能含中文"); 
			return;
		}
		if(valid){
		  	form.submit();
		}else{
		    top.Dialog.alert('表单填写不正确');
		} 
	  }
	  $(function(){
	      	$("input[name='oldpwd']").blur(function(){
	      		var oldpwd = this.value;
	      		var userCode = $('#userCode').val();
	      		$.ajax({
	      			url:"users!oldPwd.action",
	      			data:"oldPwd="+oldpwd+"&entity.userCode="+userCode,
	      			type:"POST",
	      			dataType:"text",
	      			success:function(data){
	      				if(data==0){
	      					$("#msg").html("当前密码输入不正确!");
	      					$("input[name='oldPwd']").focus();
	      					$("input[name='oldPwd']").val("");
	      				}else{
	      					$("#msg").html("");
		      			}
	      			}
	      		});
	      	});
	    });


</script>
</head>
<body  onkeydown="return onkeydownBody()">
 <form id="form" method="post" action="<%=path %>/<%=preAction %>!updatePwd.action" name="form"  target="frmright">
	<table class="tableStyle" formMode="line">
       		<tr>
				<td  class="ali03" width="30%">用户账号：</td><td><input type="text" id="userCode" name="entity.userCode" value="${user.userCode}" readonly style='background:#E6E6E6' class="validate[required,length[0,100]]" /><span class="star">*</span></td>
			</tr>
			<tr>
				<td  class="ali03">当前密码：</td><td><input type="password"  id="oldpwd" name="oldpwd" class="validate[required,length[6,12]]" onpaste="return false" /><span class="star">*</span><span id="msg"></span></td>
			</tr>
			<tr> 
				<td  class="ali03">新密码：</td><td><input type="password" name="entity.password"  id="pwd" class="validate[required,length[6,12]]" onpaste="return false" /><span class="star">*</span></td>
			</tr>
			<tr>
				<td  class="ali03">密码确认：</td><td><input type="password"  id="comfirmpwd" class="validate[required,confirm[pwd],length[6,12]]" onpaste="return false" /><span class="star">*</span></td>
			</tr>	
			<tr>
				<td colspan="2">
				<button type="button"  onclick="submitForm()"><span class="icon_ok">提交</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</body>
</html>