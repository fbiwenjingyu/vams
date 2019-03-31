<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>条码打印</title>

<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/taglib.jsp" %>
<script language="javascript">
	var dialog;
	
	var id="",xtdabh,arcNumber,storeNumber,syr,hphm,hpzl,ywlx,clsbdh,bz,lsh;
	
	function closeDialog(){
		dialog.close();
	}
	function getSelected(obj){
		var val="";
		var count=0;
		$.each($("input[name='indexnum']:checked"),function(i,n){
			val= n.value;
			id=$('#id'+val).val();
			xtdabh=$('#xtdabh'+val).val();
			arcNumber=$('#arcNumber'+val).val();
			storeNumber=$('#storeNumber'+val).val();
			syr=$('#syr'+val).val();
			hphm=$('#hphm'+val).val();
			hpzl=$('#hpzl'+val).val();
			ywlx=$('#ywlx'+val).val();
			clsbdh=$('#clsbdh'+val).val();

			bz=$('#bz'+val).val();
			lsh=$('#lsh'+val).val();
			count ++;
		});
		//if(count>1){
		//	top.Dialog.alert("只能选择一条数据进行操作!");
		//	return;
		//}
		if(count==0){
			top.Dialog.alert("请选择数据后再操作!");
			return false;
		}
		return true;
	}
	
   	function page_query(index){
   		$("input[name='index']").val(index);
   		queryForm.submit();
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
	
	$.ajax({
	  	type:"POST",
	  	url:"codeSet!getSelectByCode.action?entity.type=3",
	  	success:function(data){
   			var seldata = "{\"list\":"+data+"}";
	  		$("#hpzlId").data("data",JSON.parse(seldata));
	  		$("#hpzlId").render(); 
		}
	});
	
	function printCode(){
		if(getSelected()){
			//var openUrl = "arc/printCode.jsp?arcNumber="+arcNumber+"&xtdabh="+xtdabh+"&storeNumber="+storeNumber+"&syr="+syr+"&hphm="+hphm+"&ywlx="+ywlx+"&vin="+clsbdh+"&hpzl="+hpzl+"&bz="+bz+"&lsh="+lsh;
			var openUrl="sys/barcode!printArcLabel.action?xtdabh="+xtdabh; //转向网页的地址;
			window.open(openUrl,'打印条码','width=420,height=400');
		}
	}
	
</script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="查询" roller="false">
	<form action="<%=preAction %>!listPrintCode.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>系统编号：</td>
			<td><input name="entity.xtdabh" type="text" value="${entity.xtdabh }" style="width:126px"/></td>
			<td>六合一档案编号：</td>
			<td><input name="entity.dabh" type="text" value="${entity.dabh }" style="width:126px"/></td>
			<td>储位编号：</td>
			<td><input name="entity.cwbh" type="text" value="${entity.cwbh }" style="width:126px"/></td>
			<td>流水号：</td>
			<td><input name="entity.lsh" type="text" value="${entity.lsh }" style="width:126px"/></td>
		
		</tr>
		<tr>
			<td>业务类型：</td>
			<td>
				<select id="typeId" name="entity.ywlx" selectedValue="${entity.ywlx }" prompt="请选择" ></select>
			</td>
			<td>机动车序号：</td>
			<td><input name="entity.xh" type="text" value="${entity.xh }" style="width:126px"/></td>
			<td>号牌种类：</td>
			<td>
				<select id="hpzlId" name="entity.hpzl" selectedValue="${entity.hpzl }" prompt="请选择" ></select>
			</td>
			<td>号牌号码：</td>
			<td><input name="entity.hphm" type="text" value="${entity.hphm }" style="width:126px"/></td>
			<td><button type="submit" ><span class="icon_find">查询</span></button></td>
			<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>
		</tr>
	</table>
</form>
</div>

<div id="operation" class="box_tool_min">
	<div class="center">
	<div class="right">
		<div class="padding_top5 padding_left10">
		<a href="javascript:;" onclick="printCode()"><span class="icon_add">打印条码</span></a>
		<div class="clear"></div>
		</div>
	</div>		
	</div>	
	<div class="clear"></div>
</div> 

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th width="30">选择</th>
			<th><span>系统编号</span></th>
			<th><span>流水号</span></th>
			<th><span>机动车序号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>行政区划</span></th>
			<th><span>档案编号</span></th>
			<th><span>储位编号</span></th>
			<th><span>档案状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center" onclick="tr_click('${status.index}')">
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">
					<input id="ck_${status.index}" type="checkbox" name="indexnum" value="${status.index }" id="${status.index }"  onclick="checkOneIndex('${status.index}');"/>
					<input type="hidden" id="id${status.index }" value="${status.index}"/>
					<input type="hidden" id="xtdabh${status.index }" value="${entity.xtdabh }"/>
					<input type="hidden" id="arcNumber${status.index }" value="${entity.dabh }"/>
					<input type="hidden" id="storeNumber${status.index }" value="${entity.cwbh }"/>
					<input type="hidden" id="syr${status.index }" value="${entity.syr }"/>
					<input type="hidden" id="hphm${status.index }" value="${entity.hphm }"/>
					<input type="hidden" id="hpzl${status.index }" value="${entity.hpzl }"/>
					<input type="hidden" id="ywlx${status.index }" value="${entity.ywlx }"/>
					<input type="hidden" id="clsbdh${status.index }" value="${entity.clsbdh }"/>
					
					<input type="hidden" id="bz${status.index }" value="${entity.bz }"/>
					<input type="hidden" id="lsh${status.index }" value="${entity.lsh }"/>
				</td>
				<td align="center">&nbsp;${entity.xtdabh}</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.xh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx] }</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl] }</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${xzqhMap[entity.xzqh] }</td>
				<td align="center">&nbsp;${entity.dabh }</td>
				<td align="center">&nbsp;${entity.cwbh}</td>
				<td align="center">&nbsp;${arcStatus[entity.dazt] }</td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list_data)==0}">
			<tr align="center">
				<td colspan="16">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>

<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
</body>
</html>
