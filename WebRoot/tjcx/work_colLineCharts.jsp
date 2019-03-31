<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>扫描归档审核入库年度统计图</title>

   <%@ include file="/common/meta.jsp"%>
   <%@ include file="/common/taglib.jsp"%>
<script language="JavaScript" src="${pageContext.request.contextPath}/fusionCharts/FusionCharts.js"></script>

</head>
<body  style="width:100%;height:100%">
<div id="searchPanel" class="box2" panelTitle="年度统计图" roller="false">
	<form action="arcBaseInfoCharts!work_colLineCharts.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr><td>年份：</td>
			<td><select name="year" id="year" type="text" style="width:130px" data='${vs:yearSelect()}'  selectedValue='${year}'></select></td>
			<td><button type="button" onclick="page_query(1)"><span class="icon_find">查询</span></button></td>
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
		<div id="chartdiv" align="center"></div>	
      	<script type="text/javascript">
		   	var chart = new FusionCharts("fusionCharts/MSColumnLine3D.swf", "ChartId", "900", "550", "0", "0");
		   
		   	var dataXml = "	<chart caption='${year}年度统计图'  palette='1' baseFontSize ='12' showValues='0' yAxisValuesPadding='10'>";
			dataXml += "<categories>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<category label='${work.mc}'/>";
			</c:forEach>				
			dataXml += "</categories>";
			
			dataXml += "<dataset seriesName='扫描数量' color='ff3366'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.smNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";

			dataXml += "<dataset seriesName='审核数量' color='6699ff'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.shNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";
			
			dataXml += "<dataset seriesName='归档数量' color='66cc99'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.gdNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";

			dataXml += "<dataset seriesName='入库数量' color='ffcc66'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.rkNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";


			dataXml += "<dataset seriesName='扫描数量'  renderAs='Line' color='990033'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.smNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";

			dataXml += "<dataset seriesName='审核数量'  renderAs='Line' color='003399'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.shNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";
			
			dataXml += "<dataset seriesName='归档数量' renderAs='Line' color='006633'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.gdNum}'/>";
			</c:forEach>				
			dataXml += "</dataset>";

			dataXml += "<dataset seriesName='入库数量' renderAs='Line' color='996600'>";
		   	<c:forEach items="${list}" var="work">
				dataXml +="<set value='${work.rkNum}'/>";
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
