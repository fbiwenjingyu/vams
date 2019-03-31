<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preActoin = "users";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表查询页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
        	function add(){
                var url="<%=path %>/right/users_add.jsp";
        		top.Dialog.open({URL:url,Title:"用户添加"});
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
		    		checkCount++;
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
			    	if(checkf1=='1' || checkId=='admin'){
				    	top.Dialog.alert("该数据不能删除!");
				    }else{
		    			del(checkId);
				    }
		    	}
            }

           
            function update(id){    
               	 var url = "<%=path %>/<%=preActoin %>!preUpdate.action?entity.userCode="+id;
                 top.Dialog.open({URL:url,Title:"用户修改"});
                	
           	}
           	
           	 function del(id){
           		top.Dialog.confirm('您确定删除吗?',function(){
                	top.Dialog.close();
                	window.location.href="<%=path %>/<%=preActoin %>!delete.action?entity.userCode="+id;
                	},
                	function(){
                    	top.Dialog.close();
                    });
           	}
           	
           	function page_query(index){
           		$("input[name='index']").val(index);
           		queryForm.submit();
           	}

            $.ajax({
    	    	type:"POST",
    	    	url:"role!getSelect.action",
    	    	success:function(data){
    	    		var roleId = "${entity.roleId}";
    	    		var seldata = "{\"list\":"+data+"}";
    	    		$("#roleId").data("data",JSON.parse(seldata));
    	    		$("#roleId").render(); 
    	    		$("#roleId").setValue(roleId);
    	    	}
    	    });
    	    
    	  $.ajax({
    	    	type:"POST",
    	    	url:"dept!getSelect.action",
    	    	//dataType:"json",
    	    	success:function(data){
    	    		var deptCode = "${entity.deptCode}";
    	    		var seldata = "{\"list\":"+data+"}";
    	    		$("#deptCode").data("data",JSON.parse(seldata));
    	    		$("#deptCode").render(); 
    	    		$("#deptCode").setValue(deptCode);
    	    	}
    	    });
       </script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="用户管理查询" roller="false">
	<form action="<%=preActoin %>!query.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>用户账号：</td>
			<td><input name="entity.userCode" type="text" value="${entity.userCode}"/></td>
			<td>用户名称：</td>
			<td><input name="entity.userName" type="text" value="${entity.userName }"/></td>
			<td>用户机构：</td>
			<td><select name="entity.deptCode" id="deptCode" prompt="请选择" ></select></td>
			<td>用户角色：</td>
			<td><select name="entity.roleId" id="roleId" prompt="请选择" ></select></td>
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
	<table class="tableStyle"  mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>

			<th width="30">选择</th>
			<th width="30"><span>序号</span></th>
			<th><span>用户账号</span></th>
			<th><span>用户名称</span></th>
			<th><span>昵称</span></th>
			<th><span>性别</span></th>
			<th><span>出生日期</span></th>
			<th><span>身份证明号码</span></th>
			<th><span>用户机构</span></th>
			<th><span>警员编号</span></th>
			<th><span>工位号</span></th>
			<th><span>用户角色</span></th>
			<th><span>手机</span></th>
			<th><span>电话</span></th>
			<th><span>邮箱</span></th>
			<th><span>用户状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center">

				<td align="center">
					<input id="ck_${status.index}" type="radio" name="indexnum"  value="${status.index }" onclick="checkOne();"/>
					<input type="hidden" id="id${status.index }" name="id" value="${entity.userCode}"/>
					<input type="hidden" id="f1${status.index }" type="text" name="f1" value="${entity.f1}"/>
				</td>
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.userCode}</td>
				<td align="center">&nbsp;${entity.userName}</td>
				<td align="center">&nbsp;${entity.nickName}</td>
				<td align="center">&nbsp;<c:if test="${entity.userGender=='2'}">女</c:if><c:if test="${entity.userGender=='1'}">男</c:if></td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.bornDate }" pattern="yyyy-MM-dd"/></td>
				<td align="center">&nbsp;${entity.carid}</td>
				<td align="center">&nbsp;${deptMap[entity.deptCode]}</td>
				<td align="center">&nbsp;${entity.jybh}</td>
				<td align="center">&nbsp;${entity.gwh}</td>
				<td align="center">&nbsp;${roleMap[entity.roleId]}</td>
				<td align="center">&nbsp;${entity.telephone}</td>
				<td align="center">&nbsp;${entity.mobil}</td>
				<td align="center">&nbsp;${entity.linkEmail}</td>
				<td align="center">&nbsp;<c:if test="${entity.userstat=='0'}">禁用</c:if><c:if test="${entity.userstat=='1'}">启用</c:if></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list_data)==0}">
			<tr align="center">
				<td colspan="14">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>
<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
</body>
</html>
