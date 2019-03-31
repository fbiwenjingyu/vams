<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "paper";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>档案页设置添加修改页面</title>
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
	    	url:"seclevel!getSelect.action",
	    	success:function(data){
	    		var level = "${entity.levelId}";
	    		var seldata = "{\"list\":"+data+"}";
	    		$("#levelId").data("data",JSON.parse(seldata));
	    		$("#levelId").render(); 
	    		$("#levelId").setValue(level);
	    	}
	    });
	  $(function(){
	    	var update = "${update}";
	    	if(update ==""){
	      	$("input[name='entity.paperType']").blur(function(){
	      		var type = this.value;
	      		$.ajax({
	      			url:"paper!isExists.action",
	      			data:"entity.paperType="+type,
	      			type:"POST",
	      			dataType:"text",
	      			success:function(data){
	      				if(data==1){
	      					$("#msg").html("该档案页编号已经存在!");
	      					$("input[name='entity.paperType']").focus();
	      					$("input[name='entity.paperType']").val("");
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
	<table class="tableStyle" formMode="line">
			<tr><td class="ali03">档案页代码：</td><td><input type="text" id="paperType" name="entity.paperType" value="${entity.paperType}" class="validate[required,custom[noSpecialCaracters],length[0,100]]"  watermark="请输入英文或数字" <c:if test="${update=='update'}">readonly style='background:#E6E6E6'</c:if>/><span class="star">*</span><span id="msg"></span></td></tr>
       		<tr>
				<td  class="ali03">档案页名称：</td><td><input type="text"  name="entity.paperName" value="${entity.paperName}" class="validate[required,length[0,20]]" /><span class="star">*</span></td>
			</tr>
			<tr><td class="ali03">安全级别：</td><td><select id="levelId" prompt="请选择"  name="entity.levelId" class="validate[required]"></select><span class="star">*</span></td></tr>
			<tr><td class="ali03">档案页描述：</td><td><textarea name="entity.paperDesc" class="validate[length[0,100]]" >${entity.paperDesc}</textarea></td></tr>
			<tr><td class="ali03">排序：</td><td><input type="text" name="entity.f2" value="${entity.f2}" inputMode="numberOnly" /></td></tr>
			
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