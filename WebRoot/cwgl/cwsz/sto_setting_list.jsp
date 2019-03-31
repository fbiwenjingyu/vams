<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	    
	 <%@ include file="/common/taglib.jsp"%>
  	<%@ include file="/common/meta.jsp"%>
	
	
    <title>储位设置列表</title>
	
	<script type="text/javascript">
		function openwin(url,tit,h,w){
			var diag = new top.Dialog();
			diag.Title = tit;
			diag.URL = url;
			if(!h){
				diag.Height=540;
			}else{
				diag.Height=h;
			}
			if(w){
				diag.Width=w;
			}
			return diag;
		}
	
	
		//添加档案室
		function addShi(){
			openwin("cwgl/stoset-toAddShi.action","添加档案室").show();
		}	
		//添加档案柜
		function addGui(){
			openwin("cwgl/stoset-toAddGui.action","添加档案柜").show();
		}
	
		//修改储位
		function modify(cid){
			var diag = openwin("cwgl/stoset-toModifySet.action?cwbh="+cid,"修改储位（"+cid+"）",410);
			diag.CancelEvent = function(){
				location.reload(false); 
				diag.close();
			}; 
			diag.show();
		}
		//删除储位
		function del(cid,ctype){
			if(confirm("您确定要删除此储位"+ctype+"（"+cid+"）及其下级储位吗？")){
				$.post("stoset-delSet.action",{"cwbh":cid},function(data){
							alert(data[1]);
							if(data[0]=="1"){
								location.href="stoset-list.action";
							}
				});
			}
		}
	
	
		//刷新所有档案格使用量
		function flushAll(btn){
			$(btn).attr("disabled",true).find("span").attr("class","icon_loading");
			$.post("cwgl/stoset-flushAllGe.action",{},function(data){
				alert(data[1]);
				if(data[0]=="1"){
					location.href="stoset-list.action";
				}
				$(btn).removeAttr("disabled").find("span").attr("class","icon_reload");
			});
		}
	</script>
	
  </head>
  
  <body>
  
  
<div id="searchPanel" class="box2" panelTitle="储位设置 | 查询 " roller="false">
<form action="stoset-list.action" name="queryForm" method="post">
	<input name="index" value="${page.index}" type="hidden"/>
	<input id="fuzzy" name="fuzzy" type="hidden" value="${fuzzy}"/> <!-- 模糊查询 -->
	<table id="selector">
		<tr>
			<td>储位设置编号：</td>
			<td><input name="setting.cid" type="text" value="${setting.cid}"/></td>
			<td>储位类型：</td>
			<td><select name="setting.cwlx" prompt="请选择" selectedValue="${setting.cwlx}"  data='${vs:cwlxSelect()}' /></td>
			<td><button type="button" onclick="checkQuery(0)"><span class="icon_find">精确查询</span></button></td>
			<td><button type="button" onclick="checkQuery(1)"><span class="icon_find">下级储位查询</span></button></td>
			<td><button type="button" onclick="cleanQuery()"><span class="icon_clear">清空</span></button></td>
		</tr>		
	</table>
</form>
</div>
	
	<!-- 操作栏 -->
<div id="operation"  class="box_tool_min">
	<div class="center">
	<div class="right">
		<div class="padding_top5 padding_left10">
			<a href="javascript:void(0);" onclick="addShi()"><span class="icon_add" >添加档案室</span></a>
			<div class="box_tool_line"></div>
			<a href="javascript:void(0);" onclick="addGui()"><span class="icon_layers">添加档案柜</span></a>
			<div class="box_tool_line"></div>
			<a href="javascript:void(0);" onclick="flushAll(this)" style="float: right;"><span class="icon_reload" >刷新所有档案格使用量</span></a>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
</div>

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list">
		<tr>
			<th>序号</th>
			<th>储位设置编号</th>
			<th>储位类型</th>
			<th>总容量</th>
			<th>已使用量</th>
			<th>是否可用</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>修改人</th>
			<th>修改时间</th>
			<th>说明</th>
			<th>操作</th>
		</tr>
		<c:forEach var="sets" varStatus="status" items="${pageList}">
		<tr>
			<td align="center">${status.index+1}</td>
			<td align="center">${sets.cid}</td>
			<td align="center">${vs:cwlxName(sets.cwlx)}</td>
			<td align="center">${sets.zrl}</td>
			<td align="center">${sets.syl}</td>
			<td ${sets.sfky=="1"?"style='background-color: #88ff88;'":"style='background-color: #ff7b7b;'"} align="center">${sets.sfky=="1"?"可用":"不可用"}</td>
			<td align="center">${sets.cjrxm}（${sets.cjrid}）</td>
			<td align="center"><fmt:formatDate value="${sets.cjsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="center">${sets.xgrxm}（${sets.xgrid}）</td>
			<td align="center"><fmt:formatDate value="${sets.xgsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="center">${sets.cwsm}</td>
			<td align="center">
				<button onclick="modify('${sets.cid}')" style="width: 40px;">修改</button>
				<c:if test='${sets.cwlx=="1" || sets.cwlx=="2"}'>
				<button onclick="del('${sets.cid}','${vs:cwlxName(sets.cwlx)}')" style="width: 40px;">删除</button>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(pageList)==0}">
		<tr>
			<td colspan="15" align="center">数据库没有相关数据</td>
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
