<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "attr";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>属性设置添加修改页面</title>
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
<body  onkeydown="return onkeydownBody()">
 <form id="form" method="post" action="" name="form"  target="frmright">
	<input type="hidden" name="entity.id" value="${entity.id }"/>
	<input type="hidden" name="entity.f1" value="${entity.f1 }"/>
	<input type="hidden" name="entity.f2" value="${entity.f2 }"/>
	<input type="hidden" name="entity.f3" value="${entity.f3 }"/>
	<input type="hidden" name="entity.f4" value="${entity.f4}"/>
	<input type="hidden" name="entity.f5" value="${entity.f5 }"/>
	<input type="hidden" name="entity.jmw" value="${entity.jmw}"/>
	<table class="tableStyle" formMode="line">
       		<tr>
				<td  class="ali03">属性类别：</td><td><input type="text"  name="entity.attrtype" value="${entity.attrtype}" class="validate[required,length[0,100]]" /><span class="star">*</span></td>
			</tr>
			<tr>
				<td  class="ali03">属性代码：</td><td><input type="text"  name="entity.attrCode" value="${entity.attrCode}" class="validate[required,length[0,50]]" /><span class="star">*</span></td>
			</tr>
			<tr>
				<td  class="ali03">属性值：</td><td><input type="text"  name="entity.attrkey" value="${entity.attrkey}" class="validate[required,length[0,100]]" style="width: 250px;"/><span class="star">*</span></td>
			</tr>	
			<tr><td class="ali03">属性描述：</td><td><textarea name="entity.content" class="validate[length[0,100]]" >${entity.content}</textarea></td></tr>
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