<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfoMid";
String str= getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base target="_self"/>
<title>外检审核页面</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
function ycInfo_picView(obj){
	var id = $('#id').val();
	//window.open ('ycInfo!ycInfo_picView.action?id='+id+'&curIndex='+obj,'newwindow','height=650px,width=980px,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
	top.Dialog.open({URL:"ycInfo!ycInfo_picView.action?id="+id+"&curIndex="+obj,Title:"照片查看",Width:"980px;",Height:"550px;",ScrollBars:"no"});
}
</script>
</head>

<body>
	<input type="hidden" id="id" name="id" value="${ycinfo.id}" />
	<div class="box1"  style="overflow: auto;width:900;height: 700px">
	<table class="tableStyle" formMode="view">
			<tr><th colspan="6">详细信息</th></tr>
			<tr>
				<td width="15%">系统档案编号：</td>
				<td width="15%">${ycinfo.xtdabh }</td>
				<td width="15%">号牌种类:</td>
				<td width="15%">${hpzlMap[ycinfo.hpzl] }</td>
				<td width="15%">号牌号码：</td>
				<td width="15%">${ycinfo.hphm }</td>
			</tr>
			<tr>
				<td>业务类型：</td>
				<td>${ywlxMap[ycinfo.ywlx] }</td>
				<td>采集时间：</td>
				<td><fmt:formatDate value="${ycinfo.cjsj }" type="both"/></td>
				<td>采集人:</td>
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
        <legend>&nbsp;&nbsp;图&nbsp;片&nbsp;&nbsp;&nbsp;</legend>
		<div style="height: 350px;overflow: scroll;">
			<c:forEach items="${ycPic_list }" var="pic" varStatus="status">
				<div class="picItem2">
					<a href="#" onclick="ycInfo_picView(${status.index})">
						<img class="out" src="<%=str %>/sys/base64ToImage!getBase64ToImagePath.action?picpath=${pic.picpath}"/>
					</a>
					<br/>
					说明：<c:if test="${pic.picStatus=='N'}"><font style="color:red;">不通过</font></c:if>
					<input type="checkbox" name="picIds" value="${pic.id}" class="ck_class" style="display: none;" />
				</div>
			</c:forEach>
		</div>
		</fieldset>
		
		<table class="tableStyle" formMode="line" >
				<tr>
					<td  width="30%"> 审核结果：<c:if test="${ycinfo.shjg=='S'}">未审核</c:if><c:if test="${ycinfo.shjg=='N'}">审核未通过</c:if><c:if test="${ycinfo.shjg=='Y'}">审核通过</c:if></td>
					<td width="50%"> 备注：${ycinfo.bz }</td>
				</tr>
				<tr><td colspan="2">
					  <button type="button" onclick="closeWin()"><span class="icon_clear">关闭</span></button>
				</td></tr>
		</table>
    </div>
</body>
</html>