<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preActoin = "ycInfo";
String str= getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>外检信息查询</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">

			function printCode(){
            	var checkId="";
            	var checkXtdabh="";
            	var checkValue=$('input:radio[name="indexnum"]:checked').val();
            	if(checkValue==null){
					top.Dialog.alert("请选择一条记录进行操作!");
					return ;
				}else{
	            	checkId = $('#id'+checkValue).val();
		    		checkXtdabh = $('#xtdabh'+checkValue).val();
			    	top.Dialog.open({URL:"<%=str %>/wjyw/printCode.jsp?code="+checkXtdabh,Title:"打印条码",Width:"400px;",Height:"200px;"});
				} 
            }

			function page_query(index){
				$("input[name='index']").val(index);
				var sdate = $('#sdate').val();
				var edate = $('#edate').val();
				if(sdate!="" || edate!=""){
					if(compareDate(sdate,edate)){
						queryForm.submit();
					};
				}else{
					queryForm.submit();
				}
			}

			function ycInfo_view(){
				var checkId="";
				var indexnum=$('input:radio[name="indexnum"]:checked').val();
				if(indexnum==null){
					top.Dialog.alert("请选择一条记录进行操作!");
					return false;
				}
				else{
					checkId = $('#id'+indexnum).val();
				}

		        var url="ycInfo!ycInfo_view.action?id="+checkId;
		  		top.Dialog.open({URL:url,Title:"外检信息查看",Width:900,Height:700});
		    }
       </script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="外检信息查询" roller="false">
	<form action="<%=preActoin %>!query.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>		
			<td>系统编号：</td>
			<td><input id="xtdabh" name="ycinfo.xtdabh" style="width:126px"  value="${ycinfo.xtdabh }"/></td>
			<td>流水号：</td>
			<td><input id="lsh" name="ycinfo.lsh" style="width:126px"  value="${ycinfo.lsh }"/></td>
			<td>号牌种类：</td>
			<td><select id="hpzl" name="ycinfo.hpzl"  prompt="请选择" data='${vs:hpzlSelect()}' selectedValue="${ycinfo.hpzl }"></select></td>
			<td>号牌号码：</td>
			<td><input id="lsh" name="ycinfo.hphm" style="width:126px"  value="${ycinfo.hphm}"/></td>
			</tr>
			<tr>
			<td>机构名称：</td>
			<td><select  id="deptcode" name="ycinfo.deptcode"  prompt="请选择" data='${vs:deptSelect()}' selectedValue="${ycinfo.deptcode}"></select></td>
			<td>业务类型：</td>
			<td><select  id="ywlx" name="ycinfo.ywlx"  prompt="请选择" data='${vs:ywlxSelect()}' selectedValue="${ycinfo.ywlx}"></select></td>
			<td>审核状态：</td>
			<td><select  id="status" name="ycinfo.shjg" >
					<option value="" >全部</option>
					<option value="S"  <c:if test="${ycinfo.shjg=='S'}">selected</c:if>>未审核</option>
					<option value="Y" <c:if test="${ycinfo.shjg=='Y'}">selected</c:if>>已审核</option>
					<option value="N" <c:if test="${ycinfo.shjg=='N'}">selected</c:if>>审核不通过</option>
				</select></td>
			<td>采集时间：</td>
			<td><input name="sdate" id="sdate" type="text" style="width:126px"  class="date" value='${sdate}'/></td>
			<td align="center">至</td>
			<td><input name="edate" id="edate" type="text" style="width:126px"  class="date" value='${edate}'/></td>
			<td><button type="button" onclick="page_query(1)"><span class="icon_find">查询</span></button></td>
			<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>
		</tr>
	</table>
</form>
</div>

<div id="operation" class="box_tool_min">
	<div class="center">
	<div class="right">
		<div class="padding_top5 padding_left10">
		<a href="javascript:;" onclick="ycInfo_view()"><span class="icon_view">查看照片</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:;"  onclick="printCode()"><span class="icon_print">打印条码</span></a>
		</div>
	</div>		
	</div>	
	<div class="clear"></div>
</div> 

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr align="center">
			<th width="30">选择</th>
			<th width="30"><span>序号</span></th>
			<th><span>系统编号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>流水号</span></th>
			<th><span>采集人姓名</span></th>
			<th><span>采集时间</span></th>
			<th><span>机构名称</span></th>
			<th><span>审核人姓名</span></th>
			<th><span>审核时间</span></th>
			<th><span>审核结果</span></th>
			<th><span>状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr ondblclick="ycInfo_view()">

				<td align="center">
					<input id="ck_${status.index}" type="radio" name="indexnum"  value="${status.index }" />
					<input type="hidden" id="id${status.index }" name="id" value="${entity.id }"/>
					<input type="hidden" id="xtdabh${status.index }" value="${entity.xtdabh}"/>
				</td>
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.xtdabh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx]}</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl]}</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.cjrmc}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.cjsj}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td align="center">&nbsp;${deptMap[entity.deptcode]}</td>
				<td align="center">&nbsp;${entity.shrmc}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.shsj}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td align="center">&nbsp;<c:if test="${entity.shjg=='S'}">未审核</c:if><c:if test="${entity.shjg=='N'}">审核未通过</c:if><c:if test="${entity.shjg=='Y'}">审核通过</c:if></td>
				<td align="center">&nbsp;<c:if test="${entity.status=='S'}">未合并</c:if><c:if test="${entity.status=='H'}">已合并</c:if><c:if test="${entity.status=='C'}">已拆解</c:if></td>
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
