<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "codeSet";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>代码管理添加修改页面</title>
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
	  $(function(){
	    	var update = "${update}";
	    	if(update ==""){
	      	$("input[name='entity.code']").blur(function(){
	      		var code = this.value;
	      		$.ajax({
	      			url:"codeSet!isExists.action",
	      			data:"entity.code="+code,
	      			type:"POST",
	      			dataType:"text",
	      			success:function(data){
	      				if(data==1){
	      					$("#msg").html("该代码编号已经存在!");
	      					$("input[name='entity.code']").focus();
	      					$("input[name='entity.code']").val("");
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
<body onkeydown="return onkeydownBody()">
 <form id="form" method="post" action="" name="form"  target="frmright">
	<input type="hidden" name="entity.id" value="${entity.id }"/>
	<input type="hidden" name="entity.f1" value="${entity.f1 }"/>
	<input type="hidden" name="entity.f2" value="${entity.f2 }"/>
	<input type="hidden" name="entity.f3" value="${entity.f3 }"/>
	<input type="hidden" name="entity.f4" value="${entity.f4}"/>
	<input type="hidden" name="entity.f5" value="${entity.f5 }"/>
	<table class="tableStyle" formMode="line">
			<tr>
				<td  class="ali03">代码值：</td><td><input type="text"  name="entity.code" value="${entity.code }"  <c:if test="${update=='update'}">readonly style='background:#E6E6E6'</c:if> class="validate[required,custom[noSpecialCaracters],length[0,20]]"  watermark="请输入英文或数字"/><span class="star">*</span><span id="msg"></span></td>
			</tr>
       		<tr>
				<td  class="ali03">代码名称：</td><td><input type="text"  name="entity.name" value="${entity.name }"  class="validate[required,length[0,20]]" /><span class="star">*</span></td>
			</tr>
			<tr>
				<td class="ali03">代码类别：</td>
				<td><select id="type" name="entity.type"
					class="validate[required]" style="width: 120px">
					<option value="1" <c:if test="${entity.type=='1'}">selected</c:if>>行政区划</option>
					<option value="2" <c:if test="${entity.type=='2'}">selected</c:if>>机动车业务类型</option>
					<option value="3" <c:if test="${entity.type=='3'}">selected</c:if>>号牌种类</option>
				</select><span class="star">*</span></td>
			</tr>
			<tr><td class="ali03">代码说明：</td><td><textarea name="entity.content" class="validate[length[0,100]]" >${entity.content }</textarea></td></tr>
			<tr>
				<td colspan="2">
				<button type="button"  onclick="submitForm('${update}')"><span class="icon_ok">提交</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</body>
</html>