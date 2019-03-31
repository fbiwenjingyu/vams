<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "role";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色添加修改</title>
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
	 
	  function getCheck(obj){
		//设置checkbox的下级checkbox的状态
		  setChildCheckBox(obj);
		//设置checkbox的上级checkbox的状态
		  setParentCheckBox(obj);
	  }

	  //设置checkbox的下级checkbox的状态
	  function setChildCheckBox(entity){
		var status = entity.checked;
		//获取下级checkbox
		var childList = findChildCheckBox(entity);
		 //判断是否有下级
	    if(childList.length > 0){
	        //遍历下级checkbox，并设置状态
	        for(var i = 0; i < childList.length; i++){
	            childList[i].checked = status;
	            //设置childList[i]的下级checkbox的状态
	            setChildCheckBox(childList[i]);
	        }
	    }
	  }
	  //获取checkbox的下级checkbox信息
	  function findChildCheckBox(entity){
		var entityVal = $(entity).val();
		//存放下级checkbox的数组
	  	var chkArray = new Array();
	  	$("input[type=checkbox]").each(function(){
			var child = $(this).val();	//获取当前值
			var childFa = $(this).attr("dispText");	//获取上级的值
			if(childFa == entityVal){
				chkArray.push(this);
			}
		});
		return chkArray;
	  }

	  
	//设置checkbox的上级checkbox的状态
	  function setParentCheckBox(entity){
		var dispText = $(entity).attr("dispText");
		$("input[type=checkbox][value='" + dispText + "']").attr("checked", true);////当前资源的父资源选择
		var up = $("input[type=checkbox][value='" + dispText + "']").attr("dispText");
	    //获取entity的上级checkbox
		$("input[type=checkbox][value='" + up + "']").attr("checked", true);////当前资源的父资源选择
	    
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

	  
	  window.onload = function(){ //页面所有元素加载完毕
		  var cobj=document.getElementById("table1").rows;
		  for (i=4;i< cobj.length ;i++) {
		  	(i%2==0)?(cobj[i].style.background = "#EFEFEF"):(cobj[i].style.background = "#FFFFFF");
		  }
		
	  }

	    
	 


</script>
</head>
<body  onkeydown="return onkeydownBody()">
<div style="height:550px;overflow:auto">
 <form id="form" method="post" action="" name="form"  target="frmright">
	<input type="hidden" name="entity.roleid" value="${entity.roleid }"/>
	<input type="hidden" name="entity.f1" value="${entity.f1 }"/>
	<table class="tableStyle" formMode="line" id="table1">
       		<tr>
				<td  class="ali03">角色名称：</td><td><input type="text"  name="entity.rolename" value="${entity.rolename }" maxlength="20" class="validate[required,custom[chinese]]]" /><span class="star">*</span></td>
			</tr>
			<tr><td class="ali03">安全级别：</td><td><select id="levelId" prompt="请选择" name="entity.levelId" class="validate[required]" ></select><span class="star">*</span></td></tr>	
			<tr><td class="ali03">角色描述：</td><td><textarea name="entity.roledesc" class="validate[length[0,100]]" >${entity.roledesc }</textarea></td></tr>
			<tr><td class="ali03">扫描类型：</td><td><input name="entity.roletype" type="radio" value="0" checked="checked" ${entity.roletype=='0'?'checked':'' } />普通扫描 &nbsp;&nbsp;&nbsp;&nbsp; 
			<input name="entity.roletype" type="radio" value="1" ${entity.roletype=='1'?'checked':'' } />超级扫描 </td></tr>
			<tr><td class="ali03">合并窗口是否跳转：</td><td><input name="entity.f2" type="radio" value="0" checked="checked" ${entity.f2=='0'?'checked':'' } />否 &nbsp;&nbsp;&nbsp;&nbsp; 
			<input name="entity.f2" type="radio" value="1" ${entity.f2=='1'?'checked':'' } />是 </td></tr>
			<tr align="left">
			        <td align="left"><strong>资源配置</strong></td><td align="left">&nbsp;</td>
			    </tr>
				<c:forEach var="entity" items="${list_data}" varStatus="status" >
					    <c:if test="${entity.openmode=='2'}">
							<tr align='center'  onmousemove="javascript:this.bgColor='#F5FAF1';" onmouseout="javascript:this.bgColor='#FFFFFF';">
								<td align="left" width="100">
									<input type="checkbox" name="resid" value="${entity.resid }" id="${entity.resid}"  dispText="${ entity.upresid}" <c:forEach var="ee" items="${list}" ><c:if test="${ee.resid==entity.resid}">checked</c:if></c:forEach> onclick="getCheck(this)"/><strong>${entity.resname}</strong>
								</td>
								<td align="left">
						</c:if>
						<c:if test="${entity.openmode=='3' || entity.openmode=='4'}">
							<input type="checkbox" name="resid" value="${entity.resid }"  tt="${entity.openmode }" <c:forEach var="ee" items="${list}" ><c:if test="${ee.resid==entity.resid}">checked</c:if></c:forEach>  onclick="getCheck(this)" dispText="${ entity.upresid}"/>${entity.resname}
							<c:if test="${fn:length(entity.resname)<14}" >
								<c:forEach begin="1" end="${14-fn:length(entity.resname)}" >&emsp;</c:forEach>
							</c:if> 
						</c:if>	
					</c:forEach>
					</td>
					<tr>
				
       		 </table>
				<div align="center">
				<button type="button"  onclick="submitForm('${update}')"><span class="icon_ok">提交</span></button>
				<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</div>
    </form>
</div>
</body>
</html>