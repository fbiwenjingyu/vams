<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preActoin = "log";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>日志查询页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
</head>
<script language="javascript">

function page_query(index){
		$("input[name='index']").val(index);
		var sdate = $('#sdate').val();
		var edate = $('#edate').val();
		if(sdate!="" || edate!=""){
			if(compareDate(sdate,edate)){
				queryForm.submit();
			};
		}else{
			queryForm.submit();
		}
		
	}
</script>
<body>

<div id="searchPanel" class="box2" panelTitle="日志查询" roller="false">
	<form action="<%=preActoin %>!query.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>用户账号：</td>
			<td><input name="entity.userCode" type="text" value="${entity.userCode }"/></td>
			<td>操作开始时间：</td>
			<td><input name="sdate" id="sdate" type="text" class="date" value='${sdate}'/></td>
			<td>操作结束时间：</td>
			<td><input name="edate" id="edate" type="text" class="date" value='${edate}'/></td>
			<td><button type="button" onclick="page_query(1)"><span class="icon_find">查询</span></button></td>
			<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>
		</tr>
	</table>
</form>
</div>

<div id="scrollContent" >
<table class="tableStyle"  mode="list" useCheckbox="true" selectRowButtonOnly="false">
	<tr>
		<th width="30"><span>序号</span></th>
		<th><span>操作人</span></th>
		<th><span>操作说明</span></th>
		<th><span>操作ip</span></th>
		<th><span>操作时间</span></th>
		<th><span>详细说明</span></th>
	</tr>
	<c:forEach var="entity" items="${list_data}" varStatus="status">
		<tr align="center" bgcolor="#FFFFFF"
			onmouseover="this.bgColor='#F5FAF1'"
			onmouseout="this.bgColor='#FFFFFF'">
			<td align="center">&nbsp;${status.index+1}</td>
			<td align="center">&nbsp;${entity.userCode}</td>
			<td align="center">&nbsp;${entity.cz}</td>
			<td align="center">&nbsp;${entity.czip}</td>
			<td align="center">&nbsp;<fmt:formatDate value="${entity.czsj}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			<td align="center">&nbsp;${entity.xxsm}</td>
		</tr>
	</c:forEach>
	<c:if test="${fn:length(list_data)==0}">
		<tr>
			<td colspan="6">数据库无相关数据!</td>
		</tr>
	</c:if>
</table>
</div>
<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
</body>
</html>
