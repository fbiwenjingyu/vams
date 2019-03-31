<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>归档审核工作量饼状统计图查询</title>

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
<style type="text/css">


.div_InputModel .field
{
  float:left;
}
</style>
</head>
<body  style="width:100%;height:100%">
<div id="searchPanel" class="box2" panelTitle="归档审核工作量饼状统计" roller="false">
	<form action="arcBaseInfoCharts!workList_pieCharts.action" method="post" name="queryForm">
	<table>
		<tr>
			<td>统计时间：</td>
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
		<div class="div_InputModel">
	     <div class="field" id="chartdiv"></div><div class="field" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	     <div class="field" id="chartgd"></div>
	    </div>
	</td>
  </tr>
</table>
</div>
</div>
<script type="text/javascript">
		   	var chart = new FusionCharts("fusionCharts/Pie3D.swf", "ChartId", "500", "450", "0", "0");
		   	var dataXml = "<chart palette='2' caption='审核工作量饼状分布统计图' xAxisName=''  showBorder='1' showValues='1' enableRotation='0' bgColor='99CCFF,FFFFFF' bgAlpha='40,100' bgRatio='0,100' decimals='0' formatNumberScale='0' baseFont='Arial' baseFontSize ='12' shownames='1'>";
			<c:forEach items="${list}" var="work">
				dataXml +="<set label='${work.mc}' value='${work.shNum}' />";
			</c:forEach>				
			dataXml +="</chart>";
			chart.setDataXML(dataXml);
			chart.render("chartdiv");


			var chartgd = new FusionCharts("fusionCharts/Pie3D.swf", "ChartId", "500", "450", "0", "0");
		   	var datagdXml = "<chart palette='2' caption='归档工作量饼状分布统计图' xAxisName=''  showBorder='1' showValues='1' enableRotation='0' bgColor='99CCFF,FFFFFF' bgAlpha='40,100' bgRatio='0,100' decimals='0' formatNumberScale='0' baseFont='Arial' baseFontSize ='12' shownames='1'>";
			<c:forEach items="${list}" var="workgd">
				datagdXml +="<set label='${workgd.mc}' value='${workgd.gdNum}' />";
			</c:forEach>				
			datagdXml +="</chart>";
			chartgd.setDataXML(datagdXml);
			chartgd.render("chartgd");
		</script>
</body>
</html>