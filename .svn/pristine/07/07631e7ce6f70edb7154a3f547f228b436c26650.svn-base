<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="vs" uri="/vams_libs" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <%@ include file="/common/meta.jsp"%>
    <title>出库信息批次列表</title>
    
    <script type="text/javascript">
    	/**核对出库的档案编号列表*/
    	var storage_in_ids = null;
    
    	/**添加核对过的档案编号*/
    	function addCheckIds(id){
    		if(null == storage_in_ids){
    			storage_in_ids={};
    		}
    		storage_in_ids[id+""]=id;
    	}
    	
		/**批次核对*/
		function checkin(){
			var $ck=$("input[name='indexnum']:checked");
			if($ck.length==0){
				top.Dialog.alert("请选择数据！");
			}else if($ck.length>1){
				top.Dialog.alert("只能选择一条进行批次核对！");
			}else{
				var pch=$ck.attr("pch");
				var diag = new top.Dialog();
					diag.Title = "批次核对出库";
					diag.URL = "swgl/stoouts-checkList.action?pch="+pch;
					diag.ShowButtonRow=true;
					diag.OkButtonText=" &nbsp;入 库  ";
					diag.OKEvent = function(){
						diag.close();
						stoin_check();
					};
					diag.CancelButtonText=" 关 闭 ";
					diag.CancelEvent = function(){
						diag.close();
					};
					diag.show();
					diag.max();

			}
		}
		
	
    	
		/**单份出库*/
		function stoin(){
			var ids=checkMany();
			if(null != ids){
				top.Dialog.confirm("确定出库？",function(){
					stoin_opera("2",ids);
				});
			}
		}
		/**核对出库确定*/
		function stoin_check(){
			//遍历取出已核对的档案
			if(null != storage_in_ids){
				var ids="";
				for(var key in storage_in_ids){
	    			ids+=key+";";
	    		}
				stoin_opera("2",ids);
			}else{
				alert("出库失败，未核对任何档案！");
			}
		}
    	/**出库操作*/
    	function stoin_opera(type,ids){
				$.post("stoouts-checkout.action",{"type":type,"applyIds":ids},function(data){
					if(data[0]=="1"){
						alert(data[1]);
						//刷新当前页
						freshFrom();
					}else if (data[0]=="2"){
						alert(data[1]);
					}else if (data[0]=="3"){
						alert(data[1]+"\r\n"+data[2]);
					}
				});
    	}
    	
    	
    	/**单份档案退回*/
		function backin(){
			var ids=checkMany();
			alert(ids);
			if(null != ids){
					backin_opera("2",ids);
			}
		}
		/**批次档案退回*/
		function backinpc(){
			var $ck=$("input[name='indexnum']:checked");
			if($ck.length==0){
				top.Dialog.alert("请选择数据！");
			}else if($ck.length>1){
				top.Dialog.alert("只能选择一条进行批次核对！");
			}else{
				var pch=$ck.attr("pch");
					backin_opera("1",pch);
			}
		}
		/**退回操作*/
		function backin_opera(type,ids){
			top.Dialog.confirm("确定退回？",function(){
				$.post("stoouts-backout.action",{"type":type,"applyIds":ids},function(data){
					if(data[0]=="1"){
						alert(data[1]);
						//刷新当前页
						freshFrom();
					}else if (data[0]=="2"){
						alert(data[1]);
					}else if (data[0]=="3"){
						alert(data[1]+"\r\n"+data[2]);
					}
				});
			})
		}
    </script>
  </head>
  <body>
<!-- 标题栏、查询栏 -->
<div id="searchPanel" class="box2" panelTitle="核对出库  | 查询" roller="false">
<form action="stoouts-toCheckout.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index}" /><!-- 当前页input -->
	<input id="fuzzy" name="fuzzy" type="hidden" value="${fuzzy}"/> 
	<table id="selector">
		<tr>
			<td>批次号：</td>
			<td><input name="stoout.ckpch" type="text" value="${stoout.ckpch}"/></td>
			<td>系统编号：</td>
			<td><input name="stoout.xtdabh" type="text" value="${stoout.xtdabh}"/></td>
			<td>申请人：</td>
			<td><input name="stoout.sqrid" type="text" value="${stoout.sqrid}"/></td>
			<td>档案编号：</td>
			<td><input name="stoout.dabh" type="text" value="${stoout.dabh}"/></td>
			<td>业务类型：</td>
			<td><select prompt="请选择" name="stoout.ywlx" data='${vs:ywlxSelect()}' selectedValue="${stoout.ywlx}"></select></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>储位编号：</td>
			<td><input name="stoout.cwbh" type="text" value="${stoout.cwbh}"/></td>
			<td>流水号：</td>
			<td><input name="stoout.lsh" type="text" value="${stoout.lsh}"/></td>
			<td>号牌种类：</td>
			<td><select prompt="请选择" name="stoout.hpzl"  data='${vs:hpzlSelect()}' selectedValue="${stoout.hpzl}" ></select></td>
			<td>号牌号码：</td>
			<td><input name="stoout.hphm" type="text" value="${stoout.hphm}"/></td>
			<td>车辆识别代号</td>
			<td><input name="stoout.clsbdh" type="text" value="${stoout.clsbdh}"/></td>
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
		<a href="javascript:void(0);" onclick="checkin()"><span class="icon_layers" >批次核对出库</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:void(0);" onclick="stoin()"><span class="icon_mark">出库</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:void(0);" onclick="backinpc()"><span class="icon_reply">批次退回</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:void(0);" onclick="backin()"><span class="icon_clear">退回</span></a>
		
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
			<th></th>
			<th width="30"><span>序号</span></th>
			<th><span>批次号</span></th>
			<th><span>系统编号</span></th>
			<th><span>档案编号</span></th>
			<th><span>储位编号</span></th>
			<th><span>流水号</span></th>
			<th><span>号牌号码</span></th>
			<th><span>车辆识别代号</span></th>
			<th><span>申请人</span></th>
			<th><span>申请时间</span></th>
			<th><span>档案状态</span></th>
		</tr>
	<c:forEach var="info" items="${pageList}" varStatus="status">
			<td align="center"><input type="checkbox" name="indexnum" value="${info.xtdabh}" pch="${info.ckpch}"/></td>
			<td align="center">${status.index+1}</td>
			<td align="center">${info.ckpch}</td>
			<td align="center">${info.xtdabh}</td>
			<td align="center">${info.dabh}</td>
			<td align="center">${info.cwbh}</td>
			<td align="center">${info.lsh}</td>
			<td align="center">${info.hphm}（${vs:hpzlName(info.hpzl)}）</td>
			<td align="center">${info.clsbdh}</td>
			<td align="center">${info.sqrxm}（${info.sqrid}）</td>
			<td align="center"><fmt:formatDate value="${info.sqsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="center">${vs:daztName(info.dazt)}</td>
			<%-- 
				<fmt:formatDate value="${info.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			--%>
		</tr>
	</c:forEach>
		<c:if test="${fn:length(pageList)==0}">
			<tr align="center">
				<td colspan="12">数据库无相关数据!</td>
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
