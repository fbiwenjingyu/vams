<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <%@ include file="/common/meta.jsp"%>
    <title>档案关联查询VIN</title>
    
    <script type="text/javascript">
		
			//获取一个选择项
		function getOneCheck(show){
			var $ck= $("input[name='ck_tag']:checked");
			if($ck.length<=0){
				top.Dialog.alert("请选择档案！");
				return null;
			}else if($ck.length!=1){
				top.Dialog.alert("只能选择一项"+show+"！");
				return null;
			}else{
				return $ck.val().trim();
			}
		}
  
		/**标签打印*/
		function printInfo(show){
			var xtdabh=getOneCheck(show);
				if(null==xtdabh){
					return;
			}
			var url="sys/barcode!printArcLabel.action?xtdabh="+xtdabh; //转向网页的地址;
			var name="标签打印页"; //网页名称，可为空;
			var iWidth="420"; //弹出窗口的宽度;
			var iHeight="400"; //弹出窗口的高度;
			//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
			var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
			window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
		}
  			
		
		/**查看图片*/
		function imgInfo(show){
			var val=getOneCheck(show);
				if(null==val){
					return;
				}
			var diag = new top.Dialog();
				diag.Title = val+"扫描页信息";
				diag.URL = "arcBaseInfo!getPicsByXtdabh.action?selectType=all&xtdabh="+val; 
				diag.Width=1300;
				diag.Height=550;
				diag.ShowMaxButton=false;
				diag.ShowMinButton=false;
				diag.ShowButtonRow=true;
				diag.ShowOkButton=false;
				diag.CancelButtonText="&nbsp;关&nbsp;&nbsp;闭&nbsp;";
				diag.show();
		//		diag.max();
		}
		
		
		/**查看详细信息*/
		function detailInfo(show){
			var val=getOneCheck(show);
			if(null==val){
				return;
			}
			var diag = new top.Dialog();
			diag.Title = "档案详细信息";
			diag.URL = "cwgl/stoget-detailInfo.action?xtdabh="+val;
			diag.ShowButtonRow=true;
			diag.ShowOkButton=false;
			diag.Height=500;
			diag.CancelButtonText=" 关 闭 ";
			diag.show();			
			
		}
    </script>
  </head>
  <body>

<!-- 标题栏、查询栏 -->
<div id="searchPanel" class="box2" panelTitle="档案关联查询VIN" roller="false">
<form action="arcQuery-cqVin.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index}" /><!-- 当前页input -->
	<input id="fuzzy" name="fuzzy" type="hidden" value="${fuzzy}"/>
	<table id="selector">
		<tr>
			<td>系统编号：</td>
			<td><input name="info.xtdabh" type="text" style="width:126px" value="${info.xtdabh}"/></td>
			<td>号牌种类：</td>
			<td><select name="info.hpzl"  prompt="请选择"  style="width:126px" selectedValue="${info.hpzl}" data='${vs:hpzlSelect()}'></select></td>
			<td>号牌号码：</td>
			<td><input name="info.hphm" type="text" style="width:126px" value="${info.hphm}"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>车辆识别代号：</td>
			<td><input name="info.clsbdh" type="text" style="width:126px" value="${info.clsbdh}"/></td>
			<td>储位编号：</td>
			<td><input name="info.cwbh" type="text" style="width:126px" value="${info.cwbh}"/></td>
			<td>流水号：</td>
			<td><input name="info.lsh" type="text" style="width:126px" value="${info.lsh}"/></td>
			<td>档案编号：</td>
			<td><input name="info.dabh" type="text" style="width:126px" value="${info.dabh}"/></td>
			<td><button type="button" onclick="checkQuery(0)"><span class="icon_find">关联查询</span></button></td>
			<td><button type="button" onclick="cleanQuery()"><span class="icon_clear">清空</span></button></td>
		</tr>

	</table>
</form>
</div>
  	

<div id="operation"  class="box_tool_min">
	<div class="center">
	<div class="right">
		<div class="padding_top5 padding_left10">
		<a href="javascript:void(0);" onclick="printInfo('标签打印')"><span class="icon_code" >打印标签</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:void(0);" onclick="imgInfo('查看扫描页')"><span class="icon_img">查看扫描页</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:void(0);" onclick="detailInfo('查看详细信息')"><span class="icon_view">详细信息</span></a>
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
		<th align="center">选择</th>
			<th width="30"><span>序号</span></th>
			<th align="center">系统编号</th>
			<th align="center">储位编号</th>
			<th align="center">流水号</th>
			<th align="center">档案编号</th>
			<th align="center">业务类型</th>
			<th align="center">号牌种类</th>
			<th align="center">号牌号码</th>
			<th align="center">车辆识别代号</th>
			<th align="center">创建人</th>
			<th align="center">创建时间</th>
			<th align="center">档案状态</th>
		</tr>
	<c:forEach var="info" items="${pageList}" varStatus="status">
		<tr align="center">
			<td align="center"><input type="radio" name="ck_tag" value="${info.xtdabh}" /></td>
			<td align="center">${status.index+1}</td>
			
			<td align="center">${info.xtdabh}</td>
			<td align="center"">${info.cwbh}</td>
			<td align="center">${info.lsh}</td>
			<td align="center">${info.dabh}</td>
			<td align="center">${vs:ywlxName(info.ywlx)}</td>
			<td align="center">${vs:hpzlName(info.hpzl)}</td>
			<td align="center">${info.hphm}</td>
			<td align="center">${info.clsbdh}</td>
			<td align="center">${info.cjrmc}（${info.cjr}）</td>
			<td align="center"><fmt:formatDate value="${info.cjsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="ali02">${vs:daztName(info.dazt)}</td>
		</tr>
	</c:forEach>
		<c:if test="${fn:length(pageList)==0}">
			<tr align="center">
				<td colspan="14">数据库无相关数据!</td>
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
