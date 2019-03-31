<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "ycPaper";
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
	  
	  $(function(){
	    	var update = "${update}";
	    	if(update ==""){
	      	$("input[name='ycPaper.takecode']").blur(function(){
	      		var code = this.value;
	      		$.ajax({
	      			url:"ycPaper!isExists.action",
	      			data:"ycPaper.takecode="+code,
	      			type:"POST",
	      			dataType:"text",
	      			success:function(data){
	      				if(data==1){
	      					$("#msg").html("该外检纸张代码已经存在!");
	      					$("input[name='ycPaper.takecode']").focus();
	      					$("input[name='ycPaper.takecode']").val("");
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
<body   onkeydown="return onkeydownBody()">
 <form id="form" method="post" action="" name="form"  target="frmright">
<input type="hidden"  name="ycPaper.id" value="${ycPaper.id}" />
	<table class="tableStyle" formMode="line">
       		<tr>
				<td  class="ali03">纸张代码：</td><td><input type="text"  name="ycPaper.takecode" value="${ycPaper.takecode}" class="validate[required,length[0,10]]" <c:if test="${update=='update'}">readonly style='background:#E6E6E6'</c:if>/><span class="star">*</span><span id="msg"></span></td>
			</tr>
       		<tr>
				<td  class="ali03">纸张名称：</td><td><input type="text"  name="ycPaper.takeName" value="${ycPaper.takeName}" class="validate[required,length[0,25]]" /><span class="star">*</span></td>
			</tr>
       		<tr>
				<td  class="ali03">纸张类型：</td><td><input type="text"  name="ycPaper.takeType" value="${ycPaper.takeType}" class="validate[length[0,25]]" /></td>
			</tr>
			<tr>
				<td  class="ali03">纸张说明：</td><td><input type="text"  name="ycPaper.description" value="${ycPaper.description}"  class="validate[length[0,50]]" /></td>
			</tr>
			<tr>
				<td  class="ali03">备注：</td><td><textarea name="ycPaper.bz" class="validate[length[0,100]]" >${ycPaper.bz}</textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<button type="button"  onclick="submitForm('${update}')"><span class="icon_ok">提交</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</body>
</html>