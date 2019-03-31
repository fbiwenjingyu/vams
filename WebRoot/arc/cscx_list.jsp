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
	
	$.ajax({
	  	type:"POST",
	  	url:"codeSet!getSelectByCode.action?entity.type=3",
	  	success:function(data){
   			var seldata = "{\"list\":"+data+"}";
	  		$("#hpzlId").data("data",JSON.parse(seldata));
	  		$("#hpzlId").render(); 
		}
	});
	
	function tr_doubleClick(id,gdzt,dazt){
		if(gdzt=="0"){
			gdzt="wgd";			
		}else{
			gdzt="ygd";		
		}
		
		var url="<%=preAction %>!getArcBaseInfoByXtdabh.action?entity.xtdabh="+id+"&picPage=cx&csType="+gdzt+"&dazt="+dazt;
		
		dialog = new top.Dialog();
		dialog.URL=url;
		dialog.Width=1300;
		dialog.Height=580;
		dialog.ID="d2";
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
	<form action="<%=preAction %>!queryReScan.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<input name="toPage" type="hidden" value="${toPage }" />
	<table>
		<tr>
			<td>流水号：</td>
			<td><input name="entity.lsh" type="text" value="${entity.lsh }" style="width:126px"/></td>
			<td>业务类型：</td>
			<td>
				<select id="typeId" name="entity.ywlx" selectedValue="${entity.ywlx }" prompt="请选择" ></select>
			</td>
			<td>机动车序号：</td>
			<td><input name="entity.xh" type="text" value="${entity.xh }" style="width:126px"/></td>
			<td>办理时间：</td>
			<td>
				<input id="sdate" type="text" name="sdate" class="date" style="width:126px" value='<fmt:formatDate value="${sdate }" pattern="yyyy-MM-dd" type="date"/>'/>
				至
				<input id="edate" type="text" name="edate" class="date" style="width:126px" value='<fmt:formatDate value="${edate }" pattern="yyyy-MM-dd" type="date"/>' />
			</td>
		</tr>
		<tr>
			<td>系统编号：</td>
			<td><input name="entity.xtdabh" type="text" value="${entity.xtdabh }" style="width:126px"/></td>
			<td>号牌种类：</td>
			<td>
				<select id="hpzlId" name="entity.hpzl" selectedValue="${entity.hpzl }" prompt="请选择" ></select>
			</td>
			<td>号牌号码：</td>
			<td><input name="entity.hphm" type="text" value="${entity.hphm }" style="width:126px"/></td>
			<td>重扫类型：</td>
			<td>
				<select id="typeId" name="csType" prompt="请选择" >
					<option value="">请选择</option>
					<option value="wgd" ${csType=='wgd'?"selected":"" } >未归档</option>
					<option value="ygd" ${csType=='ygd'?"selected":"" } >已归档</option>
				</select>
			</td>
			<td><button type="button" onclick="page_query(1)"><span class="icon_find">查询</span></button></td>
			<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>
			
		</tr>
	</table>
</form>
</div>

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th><span>流水号</span></th>
			<th><span>机动车序号</span></th>
			<th><span>储位编号</span></th>
			<th><span>系统编号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>部门</span></th>
			<th><span>业务办理时间</span></th>
			<th><span>业务办理人</span></th>
			<th><span>是否重扫</span></th>
			<th><span>归档状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center" ondblclick="tr_doubleClick('${entity.xtdabh}','${entity.sfygd }','${entity.sfycs }');" >
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.xh}</td>
				<td align="center">&nbsp;${entity.cwbh}</td>
				<td align="center">&nbsp;${entity.xtdabh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx] }</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl] }</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${user2CnDeptCode[entity.cjr] }</td>
				<td align="center">&nbsp; <fmt:formatDate value="${entity.createTime }" type="both"/></td>
				<td align="center">&nbsp;${user2CnName[entity.cjr] }</td>
				<td align="center">&nbsp;${entity.sfycs=='1'?'已重扫':'未重扫'}</td>
				<td align="center">&nbsp;${entity.sfygd=='0'?'未归档':'已归档' }</td>
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