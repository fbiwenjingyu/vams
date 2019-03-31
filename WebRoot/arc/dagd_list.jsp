<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>归档列表</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	
	var dialog;
	
	function closeDialog(){
		dialog.close();
	}
	
	//分页
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
	
	$.ajax({
	  	type:"POST",
	  	url:"codeSet!getSelectByCode.action?entity.type=3",
	  	success:function(data){
   			var seldata = "{\"list\":"+data+"}";
	  		$("#hpzlId").data("data",JSON.parse(seldata));
	  		$("#hpzlId").render(); 
		}
	});
	
	//双击审核  
	function tr_ondbclick(id,wjbh){
		showPreDagd(id,wjbh);
	}
	
	//审核
	function dagd(obj){
		var count=0,val,id,wjbh;
		$.each($("input[name='indexnum']:checked"),function(i,n){
			val= n.value;
			id = $('#id'+val).val();
			wjbh = $('#wjbh'+val).val();
			count ++;
		});
		if(!count){
			alert('请选择一条数据后再做操作！');
			return;
		}
		showPreDagd(id,wjbh);
	}
	
	function showPreDagd(id,wjbh){
		var url="<%=preAction %>!preDagd.action?selectType=1&entity.id="+id+"&toPage=gdsh&wjbh="+wjbh;
		dialog = new top.Dialog();
		dialog.URL=url;
		dialog.Width=1300;
		dialog.Height=550;
		dialog.ID="d1";
		dialog.CancelEvent = function(){
			dialog.close();
			removeScreenOver();
		};
		dialog.show();
	}
	
</script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="查询" roller="false">
	<form action="<%=preAction %>!listGd.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>流水号：</td>
			<td><input name="entity.lsh" type="text" value="${entity.lsh }" style="width:126px"/></td>
			<td>系统编号：</td>
			<td>
				<input name="entity.xtdabh" type="text" value="${entity.xtdabh }" style="width:126px"/>
			</td>
			<td>业务类型：</td>
			<td>
				<select id="typeId" name="entity.ywlx" selectedValue="${entity.ywlx }" prompt="请选择" ></select>
			</td>
			<td>审核人：</td>
			<td>
				<input name="entity.shr" type="text" value="${entity.shr }" style="width:126px"/>
			</td>
			
		</tr>
		<tr>
			<td>机动车序号：</td>
			<td><input name="entity.xh" type="text" value="${entity.xh }" style="width:126px"/></td>
			<td>号牌种类：</td>
			<td>
				<select id="hpzlId" name="entity.hpzl" selectedValue="${entity.hpzl }" prompt="请选择" ></select>
			</td>
			<td>号牌号码：</td>
			<td><input name="entity.hphm" type="text" value="${entity.hphm }" style="width:126px"/></td>
			<td>办理时间：</td>
			<td>
				<input id="sdate" type="text" name="sdate" class="date" style="width:126px" value='<fmt:formatDate value="${sdate }" pattern="yyyy-MM-dd" type="date"/>'/>
			</td>
			<td align="center">
			           至
			</td>
			<td>
				<input id="edate" type="text" name="edate" class="date" style="width:126px" value='<fmt:formatDate value="${edate }" pattern="yyyy-MM-dd" type="date"/>' />
			</td>
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
		<a href="javascript:;" onclick="dagd()"><span class="icon_pencil">归档</span></a>
	</div>	
	</div>
	<div class="clear"></div>
</div> 

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th width="30">选择</th>
			<th><span>流水号</span></th>
			<th><span>系统编号</span></th>
			<th><span>储位编号</span></th>
			<th><span>机动车序号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>行政区划</span></th>
			<th><span>业务办理时间</span></th>
			<th><span>业务办理人</span></th>
			<th><span>业务办理人姓名</span></th>
			<th><span>审核人</span></th>
			<th><span>审核人姓名</span></th>
			<th><span>审核时间</span></th>
			<th><span>档案状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center" onclick="tr_click('${status.index}')"  ondblclick="tr_ondbclick('${entity.id }','${entity.xtdabh }')" >
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">
					<input id="ck_${status.index}" type="checkbox" name="indexnum"  value="${status.index }" onclick="checkOneIndex('${status.index}');" />
					<input type="hidden" id="id${status.index }" name="entity.id"  value="${entity.id}"/>
					<input type="hidden" id="wjbh${status.index }" name="entity.xtdabh"  value="${entity.xtdabh }"/>
					<input type="hidden" id="arcType${status.index }" name="entity.dalx"  value="${entity.dalx }"/>
					<input type="hidden" id="lsh${status.index }" name="entity.lsh"  value="${entity.lsh }"/>
				</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.xtdabh} </td>
				
				<td align="center">&nbsp;${entity.cwbh} </td>
				
				<td align="center">&nbsp;${entity.xh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx] }</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl] }</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${xzqhMap[entity.xzqh] }</td>
				<td align="center">&nbsp; <fmt:formatDate value="${entity.cjsj }" type="both"/></td>
				<td align="center">&nbsp;${entity.cjr}</td>
				<td align="center">&nbsp;${user2CnName[entity.cjr]}</td>
				<td align="center">&nbsp;${entity.shr}</td>
				<td align="center">&nbsp;${user2CnName[entity.shr]}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.shsj}" type="both" /></td>
				<td align="center">&nbsp;${arcStatus[entity.dazt] }</td>
			</tr>
		 </c:forEach>
		<c:if test="${fn:length(list_data)==0}">
			<tr align="center">
				<td colspan="18">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>

<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
</body>
</html>
