<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "typeset";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色业务档案页设置修改页面</title>
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
		  	form.action = "<%=path %>/<%=preAction %>!update.action";
		  	top.Dialog.confirm('您确定修改吗?',function(){
				form.submit();
            	top.Dialog.close();
            	},
            	function(){
                	top.Dialog.close();
                });
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
	  $.ajax({
	    	type:"POST",
	    	url:"codeSet!getSelect.action?entity.type=2",
	    	success:function(data){
				var typeId = "${entity.typeId}";
		  		var seldata = "{\"list\":"+data+"}";
		  		$("#typeId").data("data",JSON.parse(seldata));
		  		$("#typeId").render(); 
		  		$("#typeId").setValue(typeId);
	    	}
	    });
	    
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
		
	    //初始化选择框状态
	    function initCheckBox(){
	    	var needPaper = "${needPaper }";
	    	var optPaper = "${optPaper }";
	    	
	    	var needPaper_arr=needPaper.split(",");
			for(var i=0;i<needPaper_arr.length;i++){
				$("#optText_"+needPaper_arr[i]).css("color","gray");
				$("#opt_"+needPaper_arr[i]).attr("disabled",true);
	    	}
			var optPaper_arr=optPaper.split(",");
			for(var i=0;i<optPaper_arr.length;i++){
				$("#needText_"+optPaper_arr[i]).css("color","gray");
				$("#need_"+optPaper_arr[i]).attr("disabled",true);
	    	}
	    }
	    
	    $(function(){
	    	initCheckBox();
	    });
	    
</script> 
</head>
<body  onkeydown="return onkeydownBody()" style="height: 550px;overflow: auto;">
 <form id="form" method="post" action="" name="form"  target="frmright">
	<input type="hidden" name="entity.f1" value="${entity.f1 }"/>
	<input type="hidden" name="entity.f2" value="${entity.f2 }"/>
	<input type="hidden" name="entity.roleId" value="${entity.roleId}"/>
	<input type="hidden" name="entity.typeId" value="${entity.typeId}"/>
	<table  formMode="line">
       		<tr>
				<td  class="ali03">用户角色：<span class="star">*</span></td><td><select id="roleId" disabled class="validate[required]" prompt="请选择"><option value="">请选择</option></select></td>
			</tr>
			<tr>
				<td  class="ali03">业务类型：<span class="star">*</span></td><td><select id="typeId" disabled="disabled" class="validate[required]"  prompt="请选择"><option value="">请选择</option></select></td>
			</tr>	
			<tr>
				<td class="ali03">档案页扫描纸张：<span class="star">*</span></td><td id ="paper">
			    <table  align="left" class="nbor" style="border:0px;">
					<c:forEach var="paper" items="${paperlist}" varStatus="status">
						<c:if test="${status.index==0}">
						<tr style="border:0px;"></c:if>
						<td style="border:0px;">&nbsp;<input id="need_${paper.id}" type='checkbox' onchange="changeCheckBox(this);" value='${paper.id}' name='paper'  class='validate[required]' <c:forEach var='ee' items='${list}' ><c:if test='${ee.paperId==paper.id&&ee.f1==1}'>checked</c:if></c:forEach>/>
						&nbsp;<span id="needText_${paper.id}">${paper.paperName }</span></td>
						<c:if test="${(status.index+1)%5==0}"></tr><tr style="border:0px;"></c:if>
					</c:forEach>
				</table>
				</td>
			</tr>
			<tr>
				<td class="ali03">可选档案页扫描纸张：<span class="star">*</span></td><td id ="paper">
			    <table  align="left" class="nbor" style="border:0px;">
						<c:forEach var="paper" items="${paperlist}" varStatus="status">
							<c:if test="${status.index==0}">
							<tr  style="border:0px;"></c:if>
							<td  style="border:0px;">&nbsp;<input id="opt_${paper.id}" type='checkbox' onchange="changeCheckBox(this);" value='${paper.id}' name='optPaper' <c:forEach var='ee' items='${list}' ><c:if test='${ee.paperId==paper.id&&ee.f1==0}'>checked</c:if></c:forEach>/>
							&nbsp;<span id="optText_${paper.id}" >${paper.paperName }</span></td>
							<c:if test="${(status.index+1)%5==0}"></tr><tr style="border:0px;"></c:if>
						</c:forEach>
				</table>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
				<button type="button"  onclick="submitForm()"><span class="icon_ok">提交</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
       		 </table>
    </form>
</body>
</html>