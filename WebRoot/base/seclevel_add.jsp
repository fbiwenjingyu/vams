<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "seclevel";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全级别添加修改页面</title>
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
	      	$("input[name='entity.levels']").blur(function(){
	      		var levels = this.value;
	      		$.ajax({
	      			url:"seclevel!isExists.action",
	      			data:"entity.levels="+levels,
	      			type:"POST",
	      			dataType:"text",
	      			success:function(data){
	      				if(data==1){
	      					$("#msg").html("该级别已经存在!");
	      					$("input[name='entity.levels']").focus();
	      					$("input[name='entity.levels']").val("");
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
	<input type="hidden" name="entity.bz" value="${entity.bz }"/>
	<input type="hidden" name="entity.f1" value="${entity.f1 }"/>
	<input type="hidden" name="entity.f2" value="${entity.f2 }"/>
	<input type="hidden" name="entity.f3" value="${entity.f3 }"/>
	<table class="tableStyle" formMode="line">
       		<tr>
				<td  class="ali03">安全级别：</td><td><input type="text"  name="entity.levels" value="${entity.levels}" <c:if test="${update=='update'}">readonly style='background:#E6E6E6'</c:if> class="validate[required,custom[onlyNumber],length[0,3]]" watermark="请输入数字" /><span class="star">*</span><span id="msg"></span></td>
			</tr>	
			<tr><td class="ali03">级别说明：</td><td><textarea name="entity.comments" class="validate[length[0,100]]" >${entity.comments}</textarea></td></tr>
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