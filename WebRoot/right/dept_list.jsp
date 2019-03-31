<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preActoin = "dept";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>部门机构查询页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
        	function add(){
                var url="<%=path %>/right/dept_add.jsp";
        		top.Dialog.open({URL:url,Title:"机构添加",Width:600,Height:400});
           	}
            function getSelected(obj){
            	var checkValue="";
            	var checkCount=0;
            	var checkId="";
            	var checkf1="";
		    	$.each($("input[name='indexnum']:checked"),function(i,n){
		    		checkValue= n.value;
		    		checkId = $('#id'+checkValue).val();
		    		checkf1 = $('#f1'+checkValue).val();
		    		checkCount ++;
		    	});
		    	if(checkCount>1){
		    		top.Dialog.alert("只能选择一条数据进行操作!");
		    		return;
		    	}
		    	if(checkCount==0){
		    		top.Dialog.alert("请选择数据后再操作!");
		    		return;
		    	}
		    	if(obj =='update'){
		    		update(checkId);
		    	}
		    	if(obj =='del'){
			    	if(checkf1=='1'){
			    		top.Dialog.alert("该数据不能删除!");
				    }else{
		    			del(checkId);
				    }
		    	}
            }
            function update(id){    
                	 var url = "<%=path %>/<%=preActoin %>!preUpdate.action?entity.deptcode="+id;
                     top.Dialog.open({URL:url,Title:"机构修改"});
                	
           	}
           	
           	 function del(id){
           		top.Dialog.confirm('您确定删除吗?',function(){
                	top.Dialog.close();
                	window.location.href="<%=path %>/<%=preActoin %>!delete.action?entity.deptcode="+id;
                	},
                	function(){
                    	top.Dialog.close();
                    });
           	}
           	
           	function page_query(index){
           		$("input[name='index']").val(index);
           		queryForm.submit();
           	}
       </script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="机构管理查询" roller="false">
	<form action="<%=preActoin %>!query.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>机构代码：</td>
			<td><input name="entity.deptcode" type="text" value="${entity.deptcode}"/></td>
			<td>机构名称：</td>
			<td><input name="entity.deptname" type="text" value="${entity.deptname }"/></td>
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
		<a href="javascript:;" onclick="add()"><span class="icon_add">新增</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:;"  onclick="getSelected('update')"><span class="icon_edit">修改</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="getSelected('del')"><span class="icon_delete">删除</span></a>
		<div class="clear"></div>
		</div>
	</div>		
	</div>	
	<div class="clear"></div>
</div> 

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30">选择</th>

			<th width="30"><span>序号</span></th>
			<th><span>机构代码</span></th>
			<th><span>机构名称</span></th>
			<th><span>机构类型</span></th>
			<th><span>上级机构</span></th>
			<th><span>行政区划</span></th>
			<th><span>联系电话</span></th>
			<th><span>联系地址</span></th>
			<th><span>网址</span></th>
			<th><span>邮箱</span></th>
			<th><span>描述</span></th>
			<th><span>扫描类型</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center"  mode="list" useCheckbox="true" selectRowButtonOnly="false">

				<td align="center">
					<input id="ck_${status.index}" type="radio" name="indexnum"  value="${status.index }" onclick="checkOne();"/>
					<input type="hidden" id="id${status.index }" name="id" value="${entity.deptcode }"/>
					<input type="hidden" id="f1${status.index }" name="f1" value="${entity.f1}"/>
				</td>
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.deptcode}</td>
				<td align="center">&nbsp;${entity.deptname}</td>
				<td align="center">&nbsp;${entity.depttype}</td>
				<td align="center">&nbsp;${deptMap[entity.upcode]}</td>
				<td align="center">&nbsp;${codeSetMap[entity.deptarea]}</td>
				<td align="center">&nbsp;${entity.linktel}</td>
				<td align="center">&nbsp;${entity.linkadd}</td>
				<td align="center">&nbsp;${entity.linkweb}</td>
				<td align="center">&nbsp;${entity.linkmail}</td>
				<td align="center">&nbsp;${entity.deptdesc}</td>
				<td align="center">&nbsp;<c:if test="${entity.f2 == '0'}">先扫</c:if><c:if test="${entity.f2 == '1'}">后扫</c:if></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list_data)==0}">
			<tr align="center">
				<td colspan="13">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>
<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
</body>
</html>