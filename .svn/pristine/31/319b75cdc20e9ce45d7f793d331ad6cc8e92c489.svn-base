<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>档案流水列表</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>

</head>
<body>

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th><span>系统编号</span></th>
			<th><span>操作人</span></th>
			<th><span>操作人名称</span></th>
			<th><span>档案状态</span></th>
			<th><span>备注</span></th>
			<th><span>操作时间</span></th>
		</tr>
		<c:forEach var="entity" items="${list}" varStatus="status">
			<tr align="center">
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.xtdabh}</td>
				<td align="center">&nbsp;${entity.czr}</td>
				<td align="center">&nbsp;${entity.czrmc}</td>
				<td align="center">&nbsp;${arcStatus[entity.statusCode] }</td>
				<td align="center">&nbsp;${entity.bz}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.czsj}" type="both" /></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list)==0}">
			<tr align="center">
				<td colspan="7">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>

</body>
</html>
