<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String str= getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base target="_self"/>
<title>外检审核页面</title>

<!--框架必需start-->
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/framework.js"></script>
<link href="${pageContext.request.contextPath}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/libs/skins/blue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="blue" positionTarget="positionContent"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${pageContext.request.contextPath}/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/nav/spliter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/layout_main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/page.js"></script>
<!--框架必需end-->

<!--引入弹窗组件start-->
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/popup/dialog.js"></script>
<!--引入弹窗组件end-->
<%@ include file="/common/taglib.jsp"%>
<style>

	.out{
		filter:alpha(opacity:25);
	}
</style>
<script language="javascript">
$(document).ready(function() {
	$("input:radio").change(function() {     //id 为season行内radio值变化函数
	    var shjg = $("input[name='ycinfo.shjg']:checked").val();           // 获取当前选中radio的值
	    if (shjg == "Y") {
	    	$(".fail").attr("style","display:none");
	    }
	    if (shjg == "N") {
		    $(".fail").attr("style","display:block");
	    }
	})
	}) 

	function submitForm(obj){
    	var countChecked = $("input[name='picIndex']:checked").size();
    	var shjg = $("input[name='ycinfo.shjg']:checked").val();

			if(obj == 'del'){
				top.Dialog.confirm('您确定退办该外检信息吗?',function(){
					form.action = "<%=path %>/ycInfo!ycInfo_audit.action?opt="+obj;
			  		form.submit();
                	top.Dialog.close();
                	},
                	function(){
                    	top.Dialog.close();
                    });
			}else{
				if(shjg != undefined){
				    	if (shjg == "N") {
				    		if(countChecked == 0){
								top.Dialog.alert("请勾选需要重扫的档案页！");			
								return;
							}else{
								/*
						    	*审核不通过时将重扫图片的picStatus均修改为N
						    	*/
								$.each($("input[name='picIndex']:checked"),function(i,n){
						    		var checkValue= n.value;
						    		$('#picStatus'+checkValue).val("N");
						    	});
								form.action = "<%=path %>/ycInfo!ycInfo_audit.action?opt="+obj;
						  		form.submit();
							}	
				    	}else{
				    		if($(".out").size()>0){
					    		top.Dialog.alert("请查看完所有档案页！");			
								return;	
							}
					    	/*
					    	*审核通过时将图片的picStatus均修改为Y
					    	*/
				    		$.each($("input[name='picIndex']"),function(i,n){
					    		var checkValue= n.value;
					    		$('#picStatus'+checkValue).val("Y");
					    	});
				    		
				    		form.action = "<%=path %>/ycInfo!ycInfo_audit.action?opt="+obj;
					  		form.submit();
					  	}
				}else{
				    top.Dialog.alert('请选择审核结果');
				} 
		}
        	
	}

function ycInfo_picView(obj){
	var id = $('#id').val();
	$("img").each(function(index) {
        if(obj == index){
           	var curid = this.id;
        	$('#'+curid).removeClass("out");
        }
    });
	//window.open ('ycInfo!ycInfo_picView.action?id='+id+'&curIndex='+obj,'newwindow','height=650px,width=980px,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
	top.Dialog.open({URL:"ycInfo!ycInfo_picView.action?id="+id+"&curIndex="+obj,Title:"照片查看",Width:"980px;",Height:"550px;",ScrollBars:"no"});
}


function removeAllOut(){
		$(".out").removeClass("out");
}
	
</script>
</head>

<body>
	 <form id="form" method="post" action="ycInfo!ycInfo_audit.action" name="form"  target="frmright">
	<input type="hidden" id="id" name="id" value="${ycinfo.id}" />
	<input type="hidden"  name="ycinfo.id" value="${ycinfo.id}" />
	<div class="box1"  style="overflow: auto;width:900;height: 700px">
	<table class="tableStyle" formMode="view">
			<tr><th colspan="6">详细信息</th></tr>
			<tr>
				<td width="15%">系统编号：</td>
				<td width="15%">${ycinfo.xtdabh }</td>
				<td width="15%">号牌种类:</td>
				<td width="15%">${hpzlMap[ycinfo.hpzl] }</td>
				<td width="15%">号牌号码：</td>
				<td width="15%">${ycinfo.hphm }</td>
			</tr>
			<tr>
				<td>业务类型：</td>
				<td>${ywlxMap[ycinfo.ywlx] }</td>
				<td>上传时间：</td>
				<td><fmt:formatDate value="${ycinfo.cjsj }" type="both"/></td>
				<td>上传人:</td>
				<td>${ycinfo.cjr}</td>
			</tr>
			<tr>
				<td>流水号：</td>
				<td>${ycinfo.lsh}</td>
				<td>行政区划：</td>
				<td>${hpzlMap[ycinfo.xzqh]}</td>
				<td>部门:</td>
				<td>${deptMap[ycinfo.deptcode]}</td>
			</tr>
		</table>
		
		<fieldset>
        <legend>&nbsp;&nbsp;图&nbsp;片&nbsp;&nbsp;&nbsp;<a href="#" onclick="removeAllOut()" >全部已看 </a></legend>
		<div style="height: 350px;overflow: scroll;" id="pic">
			<c:forEach items="${ycPic_list }" var="pic" varStatus="status">
				<div  class="picItem2">
					<a href="#"  id="a${status.index }">
						<input type="hidden"  name="picList[${status.index}].id" value="${pic.id}" />
						<img  onclick="ycInfo_picView(${status.index})" id="pic${pic.id}" src="<%=str %>/sys/base64ToImage!getBase64ToImagePath.action?picpath=${pic.picpath}" class="out"/>
					</a>
					<br/>
					<div id="fail${status.index}" class ="fail" style="display: none">
						<input type="checkbox"  name="picIndex" value="${status.index}"/>不通过
						<input type="hidden" id="picStatus${status.index}"  name="picList[${status.index}].picStatus" value="${pic.picStatus}"  />
					</div>
				</div>
			</c:forEach>
		</div>
		</fieldset>
		
		<table class="tableStyle" formMode="line" >
				<tr id="controlNext" style="display: none;">
					<td>操作：</td>
					<td>打印回执单</td>
				</tr>	
				<tr>
					<td  width="30%"> 审核结果：<input type="radio" name="ycinfo.shjg" value="Y" class="validate[required]" checked="checked"/>通过<input type="radio" name="ycinfo.shjg" value="N" class="validate[required]" />不通过</td>
					<td width="50%"> 备注：<input  name="bz" /></td>
				</tr>
				<tr><td colspan="2">
				      <button type="button"  onclick="submitForm('check')"><span class="icon_ok">审核</span></button>&nbsp;&nbsp;
				       
				      <button type="button"  onclick="submitForm('del')"><span class="icon_delete">退办</span></button>&nbsp;&nbsp;
				       
					  <button type="button" onclick="closeWin()"><span class="icon_clear">关闭</span></button>
				</td></tr>
		</table>
    </div>
</form>
</body>
</html>