<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="vs" uri="/vams_libs" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/common/meta.jsp"%>
    <title>入库档案查询</title>
    
    <script type="text/javascript">
		
		//=============================
		/**打印标签*/
		function printcode(){
			var ids=checkOne();
			if(null != ids){
				var url="../sys/barcode!printArcLabel.action?xtdabh="+ids; //转向网页的地址;
				var name="标签打印页"; //网页名称，可为空;
				var iWidth="400"; //弹出窗口的宽度;
				var iHeight="400"; //弹出窗口的高度;
				//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
				var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
				var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
				window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
			}
		}
		
		/**显示详细信息*/
    	function showMessage(){
    		var ids=checkOne();
			if(null != ids){
	    		var url="swgl/stoins-showInArc.action?applyIds="+ids;
				dialog = new top.Dialog();
				dialog.URL=url;
				dialog.Width=700;
				dialog.Height=500;
				dialog.ShowButtonRow=true;
				dialog.ShowOkButton=false;
				dialog.CancelButtonText=" 关 闭 ";
				dialog.show();
			}
    	}
    </script>
  </head>
  <body>
<!-- 标题栏、查询栏 -->
<div id="searchPanel" class="box2" panelTitle="入库档案  | 查询" roller="false">
<form action="stoins-inlist.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index}" /><!-- 当前页input -->
	<input id="fuzzy" name="fuzzy" type="hidden" value="${fuzzy}"/> 
	<table id="selector">
		<tr>
			<td>批次号：</td>
			<td><input name="stoin.rkpch" type="text" style="width:126px" value="${stoin.rkpch}"/></td>
			<td>系统编号：</td>
			<td><input name="stoin.xtdabh" type="text" style="width:126px" value="${stoin.xtdabh}"/></td>
			<td>申请人：</td>
			<td><input name="stoin.sqrid" type="text" style="width:126px" value="${stoin.sqrid}"/></td>
			<td>档案编号：</td>
			<td><input name="stoin.dabh" type="text" style="width:126px" value="${stoin.dabh}"/></td>
			<td>业务类型：</td>
			<td><select prompt="请选择" name="stoin.ywlx" data='${vs:ywlxSelect()}' selectedValue="${stoin.ywlx}"></select></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>储位编号：</td>
			<td><input name="stoin.cwbh" type="text" style="width:126px"  value="${stoin.cwbh}"/></td>
			<td>流水号：</td>
			<td><input name="stoin.lsh" type="text" style="width:126px" value="${stoin.lsh}"/></td>
			<td>号牌种类：</td>
			<td><select prompt="请选择" name="stoin.hpzl" data='${vs:hpzlSelect()}' selectedValue="${stoin.hpzl}" ></select></td>
			<td>号牌号码：</td>
			<td><input name="stoin.hphm" type="text" style="width:126px" value="${stoin.hphm}"/></td>
			<td>车辆识别代号</td>
			<td><input name="stoin.clsbdh" type="text" style="width:126px" value="${stoin.clsbdh}"/></td>
			<td><button type="button" onclick="checkQuery(0)"><span class="icon_find">精确查询</span></button></td>
			<td><button type="button" onclick="checkQuery(1)"><span class="icon_find2">模糊查询</span></button></td>
			<td><button type="button" onclick="cleanQuery()"><span class="icon_clear">清空</span></button></td>
		</tr>

	</table>
</form>
</div>
  	
<!-- 操作栏 -->
<div id="operation" class="box_tool_min">
	<div class="center">
	<div class="right">
		<div class="padding_top5 padding_left10">
		<a href="javascript:void(0);" onclick="showMessage()"><span class="icon_view" >查看详情</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:void(0);" onclick="printcode()"><span class="icon_code">打印标签</span></a>
		<div class="clear"></div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
</div>

<!-- 数据列表框 -->
<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th width="30" class="ali02"><!-- <input type="checkbox" name="selall" onclick="checkAll(this)" title="点击全选"/>  --></th>
			<th><span>系统编号</span></th>
			<th><span>档案编号</span></th>
			<th><span>储位编号</span></th>
			<th><span>流水号</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>车辆识别代号</span></th>
			<th><span>业务类型</span></th>
			<th><span>批次号</span></th>
			<th><span>入库申请人</span></th>
			<th><span>申请时间</span></th>
			<th><span>入库审核人</span></th>
			<th><span>入库时间</span></th>
			<th><span>档案状态</span></th>
		</tr>
	<c:forEach var="info" items="${pageList}" varStatus="status">
			<td class="ali02"><input type="radio" name="indexnum" value="${info.xtdabh}" pch="${info.rkpch}"/></td>
			<td class="ali02">${status.index+1}</td>
			<td class="ali02">${info.xtdabh}</td>
			<td class="ali02">${info.dabh}</td>
			<td class="ali02">${info.cwbh}</td>
			<td class="ali02">${info.lsh}</td>
			<td class="ali02">${vs:hpzlName(info.hpzl)}</td>
			<td class="ali02">${info.hphm}</td>
			<td class="ali02">${info.clsbdh}</td>
			<td class="ali02">${vs:ywlxName(info.ywlx)}</td>
			<td class="ali02">${info.rkpch}</td>
			<td class="ali02">${info.sqrxm}（${info.sqrid}）</td>
			<td class="ali02"><fmt:formatDate value="${info.sqsj}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
			<td class="ali02">${info.shrxm}（${info.shrid}）</td>
			<td class="ali02"><fmt:formatDate value="${info.rksj}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
			<td class="ali02">${vs:daztName(info.dazt)}</td>
		</tr>
	</c:forEach>
		<c:if test="${fn:length(pageList)==0}">
			<tr class="ali02">
				<td colspan="16">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>  

<!-- 翻页栏 -->
<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
  	
  </body>
</html>
