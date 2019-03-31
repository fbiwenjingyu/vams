<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>归档审核工作量柱状统计图</title>

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
<div id="searchPanel" class="box2" panelTitle="归档审核工作量柱状统计图" roller="false">
	<form action="arcBaseInfoCharts!workList_colCharts.action" method="post" name="queryForm">
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
		<div id="chartdiv" align="center">  </div>
      	<script type="text/javascript">
		   	var chart = new FusionCharts("fusionCharts/MSColumn3D.swf", "ChartId", "900", "550", "0", "0");
		   
		   	var dataXml = "	<chart caption='归档审核工作量柱状统计图' baseFontSize ='12' palette='2' baseFont='Arial' xAxisName='' hownames='1' showvalues='0' decimals='0' >";
			dataXml += "<categories>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<category label='${work.mc}'/>";
			</c:forEach>				
			dataXml += "</categories>";
			
			dataXml += "<dataset seriesName='审核数量' >";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.shNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";
			
			dataXml += "<dataset seriesName='归档数量' >";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.gdNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";
			
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
