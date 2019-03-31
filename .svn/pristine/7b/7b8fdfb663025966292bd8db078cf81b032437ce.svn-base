<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preAction = "arcBaseInfoCharts";
String str= getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>归档审核列表</title>
<style type="text/css">
    input[type="text"]
    {
    	width:126px;
    }
</style>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
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

   	function exportInfo(){
   		var url="<%=str%>/<%=preAction %>!arcInfo_export.action";
		$("#exportInfo").attr('target',"_blank");
		$("#exportInfo").attr('href',url);
   	}
   	function view(){
		var checkId="";
    	var checkXtdabh;
		var indexnum=$('input:radio[name="indexnum"]:checked').val();
		if(indexnum==null){
			top.Dialog.alert("请选择一条记录进行操作!");
			return false;
		}
		else{
			checkId = $('#id'+indexnum).val();
			checkXtdabh = $('#xtdabh'+indexnum).val();
		}
		
        var url="arcBaseInfo!getPicsByXtdabh.action?selectType=all&xtdabh="+checkXtdabh;
  		top.Dialog.open({URL:url,Title:"图片查看",Width:900,Height:700});
    }
    
    function viewFlow(){
		var checkId="";
    	var checkXtdabh;
		var indexnum=$('input:radio[name="indexnum"]:checked').val();
		if(indexnum==null){
			top.Dialog.alert("请选择一条记录进行操作!");
			return false;
		}
		else{
			checkId = $('#id'+indexnum).val();
			checkXtdabh = $('#xtdabh'+indexnum).val();
		}
		
        var url="arcBaseInfo!flowQuery.action?xtdabh="+checkXtdabh;
  		top.Dialog.open({URL:url,Title:"流水查询",Width:900,Height:700});
    }
</script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="查询" roller="false">
	<form action="<%=preAction %>!arcInfo_query.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>流水号：</td>
			<td><input name="arcBaseInfo.lsh" type="text" value="${arcBaseInfo.lsh }" /></td>
			<td>&nbsp;&nbsp;系统编号：</td>
			<td>
				<input name="arcBaseInfo.xtdabh" type="text" value="${arcBaseInfo.xtdabh }"/>
			</td>
			<td>号牌种类：</td>
			<td>
				<select id="hpzlId" name="arcBaseInfo.hpzl" data='${vs:hpzlSelect()}' selectedValue="${arcBaseInfo.hpzl }" prompt="请选择" ></select>
			</td>
			<td>号牌号码：</td>
			<td><input name="arcBaseInfo.hphm" type="text" value="${arcBaseInfo.hphm }"/></td>
				
		</tr>
		<tr>
			<td>业务类型：</td>
			<td>
				<select id="typeId" name="arcBaseInfo.ywlx" data='${vs:ywlxSelect()}' selectedValue="${arcBaseInfo.ywlx}" prompt="请选择" ></select>
			</td>
			<td>机动车序号：</td>
			<td><input name="arcBaseInfo.xh" type="text" value="${arcBaseInfo.xh }"/></td>
			<td>车辆识别代号：</td>
			<td><input name="arcBaseInfo.clsbdh" type="text" value="${arcBaseInfo.clsbdh }"/></td>
			<td>储位编号：</td>
			<td><input name="arcBaseInfo.cwbh" type="text" value="${arcBaseInfo.cwbh}"/></td>
		</tr>
		<tr>
			<td>机构名称：</td>
			<td><select  id="deptcode" name="arcBaseInfo.deptcode"  prompt="请选择" data='${vs:deptSelect()}' selectedValue="${arcBaseInfo.deptcode}"></select></td>
			<td>批次：</td>
			<td><input name="arcBaseInfo.pc" type="text" value="${arcBaseInfo.pc}"/></td>
			<td>审核人：</td>
			<td>
				<input name="arcBaseInfo.shrmc" type="text" value="${arcBaseInfo.shrmc}"/>
			</td>	
			<td>办理时间：</td>
			<td>
				<input id="sdate" type="text" name="sdate" class="date" value='${sdate }'/>
			</td>
			<td align="center">
			           至
			</td>
			<td>
				<input id="edate" type="text" name="edate" class="date" value='${edate }' />
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
		<a href="javascript:;" id="exportInfo" onclick="exportInfo();"><span class="icon_export">信息导出</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:;" id="exportInfo" onclick="view();"><span class="icon_view">查看</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:;" id="exportInfo" onclick="viewFlow();"><span class="icon_view">流水查询</span></a>
	</div>	
	</div>
	<div class="clear"></div>
</div> 

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr align="center">
			<th width="30">选择</th>
			<th width="30"><span>序号</span></th>

			<th><span>流水号</span></th>
			<th><span>系统编号</span></th>
			<th><span>储位编号</span></th>
			<th><span>批次</span></th>
			<th><span>机动车序号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>机构名称</span></th>
			<th><span>业务办理时间</span></th>
			<th><span>业务办理人</span></th>
			<th><span>审核人</span></th>
			<th><span>审核时间</span></th>
			<th><span>档案状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center"  ondblclick="view()" >
				
				<td align="center">
					<input type="radio" name="indexnum"  value="${status.index }"  />
					<input type="hidden" id="id${status.index }" name="entity.id"  value="${entity.id}"/>
					<input type="hidden" id="xtdabh${status.index }" name="entity.xtdabh"  value="${entity.xtdabh }"/>
				</td>
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.xtdabh} </td>
				<td align="center">&nbsp;${entity.cwbh} </td>
				<td align="center">&nbsp;${entity.pc} </td>
				<td align="center">&nbsp;${entity.xh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx] }</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl] }</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<td align="center">&nbsp;${deptMap[entity.deptcode]}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.cjsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">&nbsp;${entity.cjrmc}</td>
				<td align="center">&nbsp;${entity.shrmc}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.shsj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
