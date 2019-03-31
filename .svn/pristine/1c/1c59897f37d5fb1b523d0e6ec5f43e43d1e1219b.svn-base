<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "users";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户添加修改页面</title>
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
	    	url:"role!getSelect.action",
	    	success:function(data){
	    		var roleId = "${entity.roleId}";
	    		var seldata = "{\"list\":"+data+"}";
	    		$("#roleId").data("data",JSON.parse(seldata));
	    		$("#roleId").render(); 
	    		$("#roleId").setValue(roleId);
	    	}
	    });
	    
	  $.ajax({
	    	type:"POST",
	    	url:"dept!getSelect.action",
	    	//dataType:"json",
	    	success:function(data){
	    		var deptCode = "${entity.deptCode}";
	    		var seldata = "{\"list\":"+data+"}";
	    		$("#deptCode").data("data",JSON.parse(seldata));
	    		$("#deptCode").render(); 
	    		$("#deptCode").setValue(deptCode);
	    	}
	    });

	  $(function(){
	    	var update = "${update}";
	    	if(update ==""){
	      	$("input[name='entity.userCode']").blur(function(){
	      		var userCode = this.value;
	      		$.ajax({
	      			url:"users!isExists.action",
	      			data:"entity.userCode="+userCode,
	      			type:"POST",
	      			dataType:"text",
	      			success:function(data){
	      				if(data==1){
	      					$("#msg").html("该用户账号已经存在!");
	      					$("input[name='entity.userCode']").focus();
	      					$("input[name='entity.userCode']").val("");
	      				}else{
	      					$("#msg").html("");
		      			}
	      			}
	      		});
	      	});
  	
      }
	    });


</script>
</head>
<body  onkeydown="return onkeydownBody()">
 <form id="form" method="post" action="" name="form"  target="frmright">
	<input type="hidden" name="entity.f1" value="${entity.f1 }"/>
	<input type="hidden" name="entity.f2" value="${entity.f2 }"/>
	<table class="tableStyle" formMode="line">
       		<tr>
				<td  class="ali03">用户账号：</td><td><input type="text"  name="entity.userCode" value="${entity.userCode}" class="validate[required,length[3,20],custom[noSpecialCaracters]]]"  watermark="请输入英文或数字" <c:if test="${update=='update'}">readonly style='background:#E6E6E6'</c:if>/><span class="star">*</span><span id="msg"></span></td>
				<td  class="ali03">密码：</td><td><input type="text"  name="entity.password" class="${entity.password==null?'validate[required,length[6,12]]':'' }" value="${entity.password==null?'111111':''}" watermark="输入修改密码" /><span class="star">*</span></td>
			</tr>
       		<tr>
				<td  class="ali03">用户名称：</td><td><input type="text"  name="entity.userName" value="${entity.userName }" class="validate[required,length[0,10]]" /><span class="star">*</span></td>
				<td  class="ali03">昵称：</td><td><input type="text"  name="entity.nickName" value="${entity.nickName}" class="validate[length[0,10]]" /></td>
			</tr>
       		<tr>
				<td  class="ali03">身份证明号码：</td><td><input type="text"  name="entity.carid" value="${entity.carid}" class="validate[required,length[8,18],custom[noSpecialCaracters]]"  watermark="请输入英文或数字"/><span class="star">*</span></td>
				<td  class="ali03">性别：</td><td><input type="radio" id="radio-1" name="entity.userGender" value="1"   <c:if test="${entity.userGender=='1'  || empty entity.userGender}"> checked </c:if>/><label for="radio-1" class="hand">男</label>
				<input type="radio" id="radio-2" name="entity.userGender"  value="2"  <c:if test="${entity.userGender=='2'}"> checked </c:if>/><label for="radio-2" class="hand">女</label> </td>
			</tr>
			<tr>
				<td  class="ali03">警员编号：</td><td><input type="text"  name="entity.jybh" value="${entity.jybh }" class="validate[required,length[0,20],custom[noSpecialCaracters]]" /><span class="star">*</span></td>
				<td  class="ali03">工位号：</td><td><input type="text"  name="entity.gwh" value="${entity.gwh}" class="validate[length[0,20]]" /></td>
			</tr>
       		<tr>
				<td  class="ali03">用户机构：</td><td><select id="deptCode" prompt="请选择" name="entity.deptCode" class="validate[required]"></select><span class="star">*</span></td>
				<td  class="ali03">出生日期：</td><td><input type="text"  name="entity.bornDate" value='<fmt:formatDate value="${entity.bornDate }" pattern="yyyy-MM-dd"/>' class="date" /></td>
			</tr>
       		<tr>
				<td  class="ali03">用户角色：</td><td><select id="roleId" prompt="请选择" name="entity.roleId" class="validate[required]"></select><span class="star">*</span></td>
				<td  class="ali03">手机：</td><td><input type="text"  name="entity.mobil" value="${entity.mobil}" maxlength="20" class="validate[custom[mobilephone],length[0,15]]]" /></td>
			</tr>
       		<tr>
				<td  class="ali03">电话：</td><td><input type="text"  name="entity.telephone" value="${entity.telephone}" class="validate[custom[telephone],length[0,20]]" /></td>
				<td  class="ali03">邮箱：</td><td><input type="text"  name="entity.linkEmail" value="${entity.linkEmail}" class="validate[custom[email],length[0,30]]" /></td>
			</tr>
       		<tr>
				<td  class="ali03">地址：</td><td><input type="text"  name="entity.address" value="${entity.address}" class="validate[length[0,40]]" /></td>
				<td  class="ali03">用户状态：</td><td><input type="radio" name="entity.userstat" value="1"  <c:if test="${entity.userstat=='1'  || empty entity.userstat}"> checked </c:if>/><label for="radio-1" class="hand">启用</label>
				<input type="radio" name="entity.userstat"  value="0"/><label for="radio-2"  <c:if test="${entity.userstat=='0'}"> checked </c:if> class="hand">禁用</label> </td>
			</tr>
       		<tr>
				<td colspan="4">
				<button type="button"  onclick="submitForm('${update}')"><span class="icon_ok">提交</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</body>
</html>