<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>统计档案已归档一个月仍未入库的档案详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引用公用路径Start -->
    <%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/taglib.jsp"%>
  </head>
  
  <body>
<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th><span>系统编号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>流水号</span></th>
			<th><span>归档人姓名</span></th>
			<th><span>归档时间</span></th>
		</tr>
		<c:forEach var="entity" items="${list_last}" varStatus="status">
			<tr>
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.xtdabh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx]}</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl]}</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.gdrmc}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.gdsj}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list_last)==0}">
			<tr align="center">
				<td colspan="8">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>
  </body>
</html>
