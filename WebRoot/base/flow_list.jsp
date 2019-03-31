<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preActoin = "flow";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程管理查询列表页面</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
        	function add(){
                var url="<%=path %>/base/flow_add.jsp";
        		top.Dialog.open({URL:url,Title:"流程添加",Width:600,Height:400});
           	}
            function getSelected(obj){
            	var checkValue="";
            	var checkCount=0;
            	var checkId="";
            	var checkf1="";
		    	$.each($("input[name='indexnum']:checked"),function(i,n){
		    		checkValue= n.value;
		    		checkId = checkId + "," + $('#id'+checkValue).val();
		    		checkf1 = $('#f1'+checkValue).val();
		    		checkCount ++;
		    	});
		    	if(checkCount>1 && obj!='del'){
		    		top.Dialog.alert("只能选择一条数据进行操作!");
		    		return;
		    	}
		    	if(checkCount==0){
		    		top.Dialog.alert("请选择数据后再操作!");
		    		return;
		    	}
		    	if(obj =='del'){
			    	if(checkf1=='1'){
				    	top.Dialog.alert("该数据不能删除!");
				    }else{
				    	checkId=checkId.replace(",","");
		    			del(checkId);
				    }
		    	}
            }
           	
           	 function del(id){
           		top.Dialog.confirm('您确定删除吗?',function(){
                	top.Dialog.close();
                	window.location.href="<%=path %>/<%=preActoin %>!delete.action?entity.id="+id;
                	},
                	function(){
                    	top.Dialog.close();
                    });
           	}
           	
           	function page_query(index){
           		$("input[name='index']").val(index);
           		queryForm.submit();
           	}

			//填充角色下拉框
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

           	//业务类型下拉框填充
           	$.ajax({
    	    	type:"POST",
    	    	url:"codeSet!getSelect.action?entity.type=2",
    	    	success:function(data){
           			var typeId = "${entity.typeId}";
           			var seldata = "{\"list\":"+data+"}";
    	    		$("#typeId").data("data",JSON.parse(seldata));
    	    		$("#typeId").render(); 
    	    		$("#typeId").setValue(typeId);
    	    	}
    	    });
           	
          //全选
        	function checkAll(tag){
        		var inps=$("input[name=indexnum]");
        		if(tag.checked){
        			idsArr=[];
        			for(var i=0;i<inps.length;i++){
        				idsArr[i]=inps[i].value;
        			}
        			inps.attr("checked","checked");
        		}else{
        			idsArr=[];
        			inps.removeAttr("checked");
        		}
        	}
       </script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="流程设置查询" roller="false">
	<form action="<%=preActoin %>!query.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>流程名称：</td>
			<td><input name="entity.flowname" type="text" value="${entity.flowname }"/></td>
			<td>角色名称：</td>
			<td><select id="roleId" prompt="请选择" name="entity.roleId" class="validate[required]" ></select></td>
			<td>业务名称：</td>
			<td><select id="typeId" prompt="请选择" name="entity.typeId" class="validate[required]" ></select></td>
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
			<th><span>流程名称</span></th>
			<th><span>角色名称</span></th>
			<th><span>业务名称</span></th>
			<th><span>上一流程</span></th>
			<th><span>流程步骤</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center"  >
				
				<td align="center">
					<input id="ck_${status.index}" type="radio" name="indexnum"  value="${status.index }" />
					<input type="hidden" id="id${status.index }" name="id" value="${entity.id }"/>
					<input type="hidden" id="f1${status.index }" name="f1" value="${entity.f1}"/>
				</td>
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">&nbsp;${entity.flowname}</td>
				<td align="center">&nbsp;${roleMap[entity.roleId]}</td>
				<td align="center">&nbsp;${codeMap[entity.typeId]}</td>
				<td align="center">&nbsp;${flowMap[entity.upflow]}</td>
				<td align="center">&nbsp;${entity.flow}</td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list_data)==0}">
			<tr align="center">
				<td colspan="7">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>
<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
</body>
</html>
