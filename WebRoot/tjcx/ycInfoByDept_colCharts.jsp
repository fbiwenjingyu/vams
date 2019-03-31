<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>外检数量柱状统计图查询</title>

   <%@ include file="/common/meta.jsp"%>
   <%@ include file="/common/taglib.jsp"%>
<script language="JavaScript" src="${pageContext.request.contextPath}/fusionCharts/FusionCharts.js"></script>
<script language="javascript">
	//分页
   	function page_query(){
   		var sdate=$("#sdate").val();
		var edate=$("#edate").val();
		//获取时间验证
		if(compareDate(sdate,edate)){
			queryForm.submit();
	    }
   	}
</script>
</head>
<body  style="width:100%;height:100%">
<div id="searchPanel" class="box2" panelTitle="外检数量柱状统计图查询" roller="false">
	<form action="ycInfoCharts!ycInfoByDept_colCharts.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>采集时间：</td>
			<td><input name="sdate" id="sdate" type="text" style="width:130px"  class="date" value='${sdate}'/></td>
			<td align="center">至</td>
			<td><input name="edate" id="edate" type="text" style="width:130px"  class="date" value='${edate}'/></td>
			<td><button type="button" onclick="page_query()"><span class="icon_find">查询</span></button></td>
			<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>
		</tr>
	</table>
</form>
</div>
<div id="scrollContent" class="table_bor">
<div style="width:100%;height:auto;background:#FFFFFF">
	<table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> 
		<div id="chartdiv" align="center">  </div>
      	<script type="text/javascript">
		   	var chart = new FusionCharts("fusionCharts/Column3D.swf", "ChartId", "800", "450", "0", "0");
		   
		   	var dataXml = "	<chart palette='4' decimals='0' enableSmartLabels='1' caption ='外检信息柱状统计图(审核通过的计入统计)' enableRotation='0' startingAngle='70' baseFont='Arial' baseFontSize ='12' shownames='1'>";
		   	<c:forEach items="${list}" var="ycInfoSuper">
				dataXml +="<set label='${deptMap[ycInfoSuper.deptcode]}' value='${ycInfoSuper.num}' />";
			</c:forEach>				
			
			dataXml +="</chart>";
			chart.setDataXML(dataXml);
			chart.render("chartdiv");
		</script> 
	</td>
  </tr>
</table>
</div>
</div>
</body>
</html>
