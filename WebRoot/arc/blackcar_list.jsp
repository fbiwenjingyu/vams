<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>重扫查询列表</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	
   	function page_query(index){
   		$("input[name='index']").val(index);
   		var sdate=$("#sdate").val();
		var edate=$("#edate").val();
		//获取时间验证
		if(compareDate(sdate,edate)){
			queryForm.submit();
	    }
   	}
   	
   	$.ajax({
	  	type:"POST",
	  	url:"codeSet!getSelectByCode.action?entity.type=2",
	  	success:function(data){
   			var seldata = "{\"list\":"+data+"}";
	  		$("#typeId").data("data",JSON.parse(seldata));
	  		$("#typeId").render(); 
		}
	});
	
	function unlock(){
		var val="";
		var count=0;
		var xtdabh="";
		$.each($("input[name='indexnum']:checked"),function(i,n){
			val= n.value;
			xtdabh = $('#xtdabh'+val).val();
			count ++;
		});
		if(count>1){
			top.Dialog.alert("只能选择一条数据进行操作!");
			return;
		}
		if(count==0){
			top.Dialog.alert("请选择数据后再操作!");
			return;
		}
		
		top.Dialog.confirm("确认解锁该车辆？",function(){
			window.location.href="arcBaseInfo!unlock.action?xtdabh=" + xtdabh;
			alert("提交成功");
			top.frmright.window.location.href="arcBaseInfo!queryBlackCar.action";
			closeWin();
    	},function(){});
	}
	
</script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="查询" roller="false">
	<form action="<%=preAction %>!queryBlackCar.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>号牌号码：</td>
			<td><input name="hphm" type="text" value="${hphm }" style="width:126px"/></td>
			<td>业务类型：</td>
			<td>
				<select id="typeId" name="ywlx" selectedValue="${ywlx}" prompt="请选择" ></select>
			</td>
			<td>车辆识别代号：</td>
			<td><input name="clsbdh" type="text" value="${clsbdh }" style="width:126px"/></td>
			<td>系统编号：</td>
			<td><input name="xtdabh" type="text" value="${xtdabh }" style="width:126px"/></td>
			<td><button type="button" onclick="page_query(1)"><span class="icon_find">查询</span></button></td>
			<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>
		</tr>
	</table>
</form>
</div>

<div id="operation" class="box_tool_min">
	<div class="center">
	<div class="right">
	    <div class="box_tool_line"></div>
		<a href="javascript:;" onclick="unlock()"><span class="icon_add">嫌疑解锁</span></a>		
	</div>	
	</div>
	<div class="clear"></div>
</div> 

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th width="30">选择</th>
			<th><span>号牌号码</span></th>
			<th><span>业务类型</span></th>
			<th><span>车辆识别代号</span></th>
			<th><span>系统编号</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center">
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">
					<input id="ck_${status.index}" type="checkbox" name="indexnum"  value="${status.index }"  id="${status.index }" onclick="checkOneIndex('${status.index}');" />
					<input type="hidden" id="xtdabh${status.index }" name="xtdabh"  value="${entity.xtdabh}"/>
				</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx] }</td>
				<td align="center">&nbsp;${entity.clsbdh}</td>
				<td align="center">&nbsp;${entity.xtdabh}</td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list_data)==0}">
			<tr align="center">
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
