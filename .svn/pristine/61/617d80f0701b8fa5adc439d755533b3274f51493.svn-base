<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <%@ include file="/common/meta.jsp"%>
    <title>档案合并</title>
    
    <script type="text/javascript">

    	
	    /**显示详细信息*/
		function showMessage(){
			var ids=checkOne();
			if(null != ids){
	    		var url="swgl/stocomb-showArc.action?xtdabh="+ids;
				dialog = new top.Dialog();
				dialog.URL=url;
				dialog.Width=700;
				dialog.Height=500;
				dialog.ShowButtonRow=true;
				dialog.ShowOkButton=false;
				dialog.CancelButtonText=" 关 闭 ";
				dialog.show();
			}
		}
    
    
   	 	function combArc(){
	   	 	var ids=checkMany();
			if(null != ids){
				//验证是否含有主档案
				var ckids=ids.split(";");
				for(var i=0;i<ckids.length;i++){
					var ywlx=$("input[xtdabh='"+ckids[i]+"']").attr("ywlx");
					if(ywlx=="A"){
						//档案合并发送请求
						top.Dialog.confirm("您确定要合并档案吗？",function(){
							$.post("swgl/stocomb-comb.action", {"applyIds":ids}, function(data){
								if(data[0]=="0"){//合并失败
									alert(data[1]);
								}else{
									alert(data[1]);
									//刷新
									freshFrom();
								}
							});
						});
						return;
					}
				}
				top.Dialog.alert("请勾选主档案！");
	    	}
   	 	}
    </script>
  </head>
  	
  <body>
<!-- 标题栏、查询栏 -->  	
<div id="searchPanel" class="box2" panelTitle="档案合并   | 查询" roller="false">
			<form action="stocomb-find.action" method="post" name="queryForm">
				<input name="index" type="hidden" value="${page.index}" /><!-- 当前页input -->
				<table id="selector">
					<tr>						
						<td>系统编号：</td>
						<td><input name="arcinfo.xtdabh" type="text" style="width:126px" value="${arcinfo.xtdabh}"/></td>						
						<td>档案编号：</td>
						<td><input name="arcinfo.dabh" type="text" style="width:126px" value="${arcinfo.dabh}"/></td>
						<td>流水号：</td>
						<td><input name="arcinfo.lsh" type="text" style="width:126px" value="${arcinfo.lsh}"/></td>
						<td></td>
						<td></td>
					</tr>
					<tr>						
						<td>号牌种类：</td>
						<td><select prompt="请选择" name="arcinfo.hpzl" style="width:126px" data='${vs:hpzlSelect()}' selectedValue="${arcinfo.hpzl}" ></select></td>
						<td>号牌号码：</td>
						<td><input name="arcinfo.hphm" type="text" style="width:126px" value="${arcinfo.hphm}"/></td>
						<td>车辆识别代号</td>
						<td><input name="arcinfo.clsbdh" type="text" style="width:126px" value="${arcinfo.clsbdh}"/></td>
						<td><button type="button" onclick="checkQuery(0)"><span class="icon_find">查询</span></button></td>
						<td><button type="button" onclick="cleanQuery()"><span class="icon_clear">清空</span></button></td>
					</tr>
				</table>
			</form>
</div>

<!-- 操作栏 -->
<div id="operation" class="box_tool_min">
	<div class="center">
	<div class="right">
		<div class="padding_top5 padding_left10">
		<a href="javascript:void(0);" onclick="showMessage()"><span class="icon_view" >查看详情</span></a>
		<div class="box_tool_line"></div>
		<a href="javascript:void(0);" onclick="combArc()"><span class="icon_layers">档案合并</span></a>
		<div class="clear"></div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
</div>

<!-- 数据列表框 -->
<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th></th>
			<th width="30"><span>序号</span></th>
			<th align="center"><span>系统编号</span></th>
			<th align="center"><span>储位编号</span></th>
			<th align="center"><span>档案编号</span></th>
			<th align="center"><span>流水号</span></th>
			<th align="center"><span>号牌种类</span></th>
			<th align="center"><span>号牌号码</span></th>
			<th align="center"><span>业务类型</span></th>
			<th align="center"><span>机动车序号</span></th>
			<th align="center"><span>车辆识别代码</span></th>
			<th align="center"><span>档案状态</span></th>
			<th align="center"><span>入库时间</span></th>
		</tr>
		
		<c:forEach var="info" items="${pageList}" varStatus="status">
		<tr align="center">
			<td align="center"><input type="checkbox" name="indexnum" value="${info.xtdabh}" xtdabh="${info.xtdabh}" ywlx="${info.ywlx}"/></td>
			<td align="center" ${info.ywlx=="A"?"bgcolor='orange'":""}>${status.index+1}</td><!-- 如果为注册登记，则表示为主档案，标记为橙色 -->
			<td align="center">${info.xtdabh}</td>
			<td align="center">${info.cwbh}</td>
			<td align="center">${info.dabh}</td>
			<td align="center">${info.lsh}</td>
			<td align="center">${vs:hpzlName(info.hpzl)}</td>
			<td align="center">${info.hphm}</td>
			<td align="center">${vs:ywlxName(info.ywlx)}</td>
			<td align="center">${info.xh}</td>
			<td align="center">${info.clsbdh}</td>
			<td align="center">${vs:daztName(info.dazt)}</td>
			<td align="center"><fmt:formatDate value="${info.bjrq}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		</c:forEach>
		
		<!-- 无数据 -->
		<c:if test="${fn:length(pageList)==0}">
			<tr class="ali02" >
				<td rowspan="2" colspan="13" bgcolor="#88ff88">请查询要合并的档案！&nbsp;&nbsp;&nbsp;*提示：橙色标记的为主档案</td>
			</tr>
		</c:if>
		
	</table>
</div>
  	
  </body>
</html>
