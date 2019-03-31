<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "flow";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程管理添加修改页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	  function submitForm(update){
		var valid = $("#form").validationEngine({returnIsValid: true});
		if(valid){
			if(update == "update"){
		  		form.action = "<%=path %>/<%=preAction %>!update.action";
		  	}else{
		  		form.action = "<%=path %>/<%=preAction %>!add.action";
		  	}
		  	form.submit();
		}else{
		    top.Dialog.alert('表单填写不正确');
		} 
	  }
	  
	function refreshFlow(obj){
		$.ajax({
	    	type:"POST",
	    	url:"flow!getModel.action?entity.id="+$(obj).val(),
	    	dataType:"json",
	    	success:function(json){
	    		var flow = parseInt(json.entity.flow)+1;
	    		$("#flow").val(flow);
	    	}
	    });
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
	    	url:"flow!getSelect.action",
	    	success:function(data){
	    		var upflow = "${entity.upflow}";
	    		var seldata = "{\"list\":"+data+"}";
	    		$("#upflow").data("data",JSON.parse(seldata));
	    		$("#upflow").render(); 
	    		$("#upflow").setValue(upflow);
	    	}
	    });
	    

	  //全选和取消全选
	    function checkAll(){
	    	if($('#check').attr("checked") == true){ 
	    		$("input[name=type]").each(function(){
	    			$(this).attr("checked",true);
		    	});
	    	}else{
	    		$("input[name=type]").each(function(){
	    			$(this).attr("checked",false);
		    	});
		    }
		}
		
	//业务类型下拉列表	
	$.ajax({
	  	type:"POST",
	  	url:"codeSet!getSelect.action?entity.type=2",
	  	success:function(data){
   			var seldata = "{\"list\":"+data+"}";
	  		$("#typeId").data("data",JSON.parse(seldata));
	  		$("#typeId").render(); 
		}
	});
	
	//
	$(function(){
		$("#typeId").change(function(){
			typeId_change();
		});
		
		$("#roleId").change(function(){
			typeId_change();
		});
	});
	
	function typeId_change(){
		var ywlx=$("#typeId").val();
		if($("#roleId").val()==""||$("#typeId").val()==""){
			$("#flowName").val("");
  			$("#cnUpFlow").html("");
  			$("#upflow").val("");
  			$("#flow").html("");
  			$("input[name='entity.flow']").val("");
			return;
		}
		$.ajax({
		  	type:"POST",
		  	dataType:"json",
		  	url:"flow!getFlowByYwlxId.action?ywlx="+ywlx,
		  	success:function(data){
		  		if(data){
		  			var cnType=$("#typeId").find("option:selected").text();
		  			$("#flowName").val(cnType.substring((cnType.indexOf(":")+1),cnType.length).trim()+"("+$("#roleId").find("option:selected").text()+")");
		  			$("#cnUpFlow").html(data.flowname);
		  			$("#upflow").val(data.id);
		  			$("#flow").html(parseInt(data.flow)+1);
		  			$("input[name='entity.flow']").val(parseInt(data.flow)+1);
		  		}else{
		  			var cnType=$("#typeId").find("option:selected").text();
		  			$("#flowName").val(cnType.substring((cnType.indexOf(":")+1),cnType.length).trim()+"("+$("#roleId").find("option:selected").text()+")");
		  			$("#cnUpFlow").html("");
		  			$("#upflow").val("");
		  			$("#flow").html(1);
		  			$("input[name='entity.flow']").val(1);
		  		}
			}
		});
	}
	
	//继续添加流程
	function continueAdd(){
		if($("#roleId").val()==""||$("#typeId").val()==""){
			top.Dialog.alert("用户角色和业务类型为必选项!");
			return;
		}
		$.ajax({
		  	type:"POST",
		  	dataType:"text",
		  	data:$("#form").serialize(),
		  	url:"flow!ajaxAdd.action?1=1",
		  	success:function(data){
		  		if(data.trim()=="1"){
		  			typeId_change();
		  			$("#roleId").setValue("");
		  		}else{
		  			top.Dialog.alert("系统出现异常，请与管理员联系!");
		  		}
			}
		});
	}
	
</script>
</head>


<body  onkeydown="return onkeydownBody()">
<div style="height:400px;overflow:auto">
 <form id="form" method="post" action="" name="form"  target="frmright">
	<table class="tableStyle" formMode="line">
			<tr>
				<td  class="ali03">用户角色：</td><td><select id="roleId" prompt="请选择" name="entity.roleId" class="validate[required]" style="width:122px"></select><span class="star">*</span></td>
				<td  class="ali03">业务类型：</td><td><select id="typeId" name="entity.typeId" prompt="请选择" class="validate[required]" ></select><span class="star">*</span></td>
			</tr>
			<tr>
				<td  class="ali03">流程名称：</td><td colspan="3"><input id="flowName"  style="width: 430px" type="text" name="entity.flowname" value="${entity.flowname}" /></td>
			</tr>
			<tr>
				<td  class="ali03">上一步骤：</td><td><input id="upflow" type="hidden" name="entity.upflow"  /><div id="cnUpFlow" ></div> </td>
				<td  class="ali03">流程步骤：</td><td><input type="hidden" name="entity.flow" value="${entity.flow}" class="validate[required,custom[onlyNumber],length[0,2]]" /><div id="flow"></div></td>
			</tr>
			
			<tr>
				<td colspan="4">
					<button type="button"  onclick="continueAdd()"><span class="icon_ok">继续</span></button>
					<button type="button"  onclick="submitForm('${update}')"><span class="icon_ok">完成</span></button>
					<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</div>
</body>
</html>