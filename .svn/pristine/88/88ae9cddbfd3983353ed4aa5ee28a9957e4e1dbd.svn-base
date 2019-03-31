<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "typeset";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色业务档案页设置页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<style type="text/css">
   table,th,tr,td{
      border:1px solid #CCCCCC;
      border-collapse:collapse;
    
     
   }
   table{
     width: 100%;
   }
   .nbor{
     border:0px;
     display: block;
   }
</style>
<script language="javascript">
	  function submitForm(){
		var valid = $("#form").validationEngine({returnIsValid: true});
		if(valid){
		  	form.action = "<%=path %>/<%=preAction %>!add.action";
		  	form.submit();
		}else{
		    top.Dialog.alert('表单填写不正确');
		} 
	  }

	  //给下拉框赋值
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

	    //填充checkbox
	 /* $.ajax({
	    	type:"POST",
	    	url:"codeSet!getSelect.action?entity.type=2",
	    	dataType:"json",
	    	success:function(data){
	    		$.each(data,function(i,n){
	    			if (i == 0 || i% 6 == 0) {
	    				$("#typeId").append("<tr  style='border:0px;'>");
					}
	    			$("#typeId").append("<td  style='border:0px;'><input type='checkbox' value='"+n.value+"' name='type'  class='validate[required]'/>"+n.key+"</td>");
	    			if (i% 6== 0) {
	    				$("#typeId").append("</tr>");
					}
	    		});
	    	}
	    });*/

	    //填充纸张checkbox
	  /* $.ajax({
	    	type:"POST",
	    	url:"paper!getSelect.action",
	    	dataType:"json",
	    	success:function(data){
	    		$.each(data,function(i,n){
	    			if (i == 0 || i% 4 == 0) {
	    				$("#paper").append("<tr  style='border:0px;'>");
					}
	    			$("#paper").append("<td  style='border:0px;'><input type='checkbox' value='"+n.value+"' name='paper'  class='validate[required]'/>"+n.key+"</td>");
	    			if (i% 4== 0) {
	    				$("#paper").append("</tr>");
					}
	    		});
	    	}
	    });
	    */
	//全选和取消全选
	    function checkTypeAll(){
	    	if($('#typecheck').attr("checked") == true){ 
	    		$("input[name=type]").each(function(){
	    			$(this).attr("checked",true);
		    	});
	    	}else{
	    		$("input[name=type]").each(function(){
	    			$(this).attr("checked",false);
		    	});
		    }
		}

	  //全选和取消全选
	    function checkPapaerAll(){
	    	if($('#papercheck').attr("checked") == true){ 
	    		$("input[name=paper]").each(function(){
	    			$(this).attr("checked",true);
		    	});
	    	}else{
	    		$("input[name=paper]").each(function(){
	    			$(this).attr("checked",false);
		    	});
		    }
		}
	  
	    function changeCheckBox(ck){
			//选择的是必扫	    	
	    	if((ck.id).indexOf("need")>=0){
	    		var opt_id=(ck.id).substring((ck.id).indexOf("_"),(ck.id).length);
	    		if($("#"+ck.id).attr("checked")){
	    			$("#optText"+opt_id).css("color","gray");
	    			$("#opt"+opt_id).attr("disabled",true); 			
	    		}else{
	    			$("#optText"+opt_id).css("color","");
	    			$("#opt"+opt_id).attr("disabled",false); 	
	    		}
	    	}
	    	//选择的是可扫	    	
	    	if((ck.id).indexOf("opt")>=0){
	    		var opt_id=(ck.id).substring((ck.id).indexOf("_"),(ck.id).length);
	    		if($("#"+ck.id).attr("checked")){
	    			$("#needText"+opt_id).css("color","gray");
	    			$("#need"+opt_id).attr("disabled",true);
	    		}else{
	    			$("#needText"+opt_id).css("color","");
	    			$("#need"+opt_id).attr("disabled",false); 
	    		}
	    	}
	    }
	    
</script>
</head>
<body  onkeydown="return onkeydownBody()" style="height: 550px;overflow: auto;">
<div style="height:520px;overflow-y:scroll">
 <form id="form" method="post" action="" name="form"  target="frmright">
	<input type="hidden" name="entity.f1" value="${entity.f1 }"/>
	<input type="hidden" name="entity.f2" value="${entity.f2 }"/>
<table formMode="line">
	<tr>
		<td class="ali03">用户角色：<span class="star">*</span></td>
		<td><select id="roleId" name="entity.roleId"
			class="validate[required]" prompt="请选择">
			<option value="">请选择</option>
		</select></td>
	</tr>
	<tr>
		<td class="ali03">业务类型：<span class="star">*</span><br />
		<input type="checkbox" id="typecheck" onclick="checkTypeAll()" /></span>全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		<td>
		<table align="left" style="border: 0px;">
			<c:forEach var="code" items="${codelist}" varStatus="status">
				<c:if test="${status.index==0}">
					<tr align="left" style="border: 0px;">
				</c:if>
				<td style="border: 0px;">&nbsp;<input type='checkbox'
					value='${code.id}' name='type' class='validate[required]' />
				&nbsp;${code.name }</td>
				<c:if test="${(status.index+1)%5==0}">
					</tr>
					<tr style="border: 0px;">
				</c:if>
			</c:forEach>
		</table>
		</td>
	</tr>
	<tr>
		<td class="ali03">档案页扫描纸张：<span class="star">*</span><br />
		<!--  <input type="checkbox" id="papercheck" onclick="checkPapaerAll()" /></span>全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		</td>
		<td id="paper" align="left">
		<table align="left" style="border: 0px;">
			<c:forEach var="paper" items="${paperlist}" varStatus="status">
				<c:if test="${status.index==0}">
					<tr align="left" style="border: 0px;">
				</c:if>
				<td style="border: 0px;">&nbsp;<input id="need_${paper.id}" type='checkbox' onclick="changeCheckBox(this);"
					value='${paper.id}' name='paper' class='validate[required]' /> 
				&nbsp;<span id="needText_${paper.id}">${paper.paperName }</span></td>
				<c:if test="${(status.index+1)%5==0}">
					</tr>
					<tr style="border: 0px;">
				</c:if>
			</c:forEach>
		</table>
		</td>
	</tr>
	<tr>
		<td class="ali03">可扫档案页扫描纸张：<span class="star">*</span><br />
		<!-- <input type="checkbox" id="papercheck" onclick="checkPapaerAll()" /></span>全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		</td>
		<td id="paper" align="left">
		<table align="left" style="border: 0px;">
			<c:forEach var="paper" items="${paperlist}" varStatus="status">
				<c:if test="${status.index==0}">
					<tr align="left" style="border: 0px;">
				</c:if>
				<td style="border: 0px;">&nbsp;<input id="opt_${paper.id}" type='checkbox' onclick="changeCheckBox(this);"
					value='${paper.id}' name='optPaper' />
				&nbsp;<span id="optText_${paper.id}" >${paper.paperName }</span></td>
				<c:if test="${(status.index+1)%5==0}">
					</tr>
					<tr style="border: 0px;">
				</c:if>
			</c:forEach>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center" >
		<button type="button" onclick="submitForm()"><span
			class="icon_ok">提交</span></button>
		<button type="button" onclick="closeWin()"><span
			class="icon_clear">取消</span></button>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>