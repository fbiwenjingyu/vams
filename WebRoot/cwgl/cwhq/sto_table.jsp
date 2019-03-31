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
  	<%@ include file="/common/meta_cwgl.jsp"%>
    <title>储位分配显示界面</title>
	
	<script type="text/javascript">
	
	$(function(){
		selGuiData();   
	})
	
	//当前室的id
	var currShiId;
	//当前柜的id
	var currGuiId;
	
	//查询档案柜
	function getGuis(tag){
		var shiId=$(tag).val();
		$.post("cwgl/stoget-getGuis.action",{"shiId":shiId},function(data){
			var selVal=(data[0].list)[0].value;
			$("select[name='sel_gui']").data("data",data[0]).attr("selectedValue",selVal).render();
		//	enableTooltips();
		});
	}
	
	
	//点击按钮 显示 表格
	function showGuiData(){
		$("#cw_show").fadeOut("fast",function(){
			selGuiData();
		});
	}
	
	//查询柜数据
	function selGuiData(){
			currShiId=$("select[name='sel_shi']").val();
			currGuiId=$("select[name='sel_gui']").val();
			$.post("cwgl/stoget-getGuiData.action",{"shiId":currShiId,"guiId":currGuiId},function(data){
				//生成当前位置
				$("#local").text("第"+currShiId+"室  - 第"+currGuiId+"柜");
				//生成表格
				var tab=new CwTable("cw_show",data);
					tab.show();
			});
	}
	
	</script>
	
  </head>
  
  <body>
  <!-- 列表页操作后是否刷新 -->
  <input type="hidden" name="isflush" value="0"/>
  <div id="scrollContent">
  
  <div class="box2" panelTitle="储位选择" roller="true">
    	<table align="center">
    		<tr>
    			<td>&nbsp;&nbsp;档案室：</td><td><select name="sel_shi" onchange="getGuis(this)" selectedValue="${selShiId}" data='${shiId}' ></select></td><td></td>
    			<td>&nbsp;&nbsp;档案柜：</td><td><select name="sel_gui" boxHeight="150" selectedValue="${selGuiId}" data='${guiId}' ></select></td>
    			<td><button onclick="showGuiData()"><span class="icon_ok">确&nbsp;定</span></button></td>
    		</tr>
    	</table>
   </div>
    	
   		<div align="center" class="box1"  style="margin: 0px auto; width: 100%;">
   			<!-- 储位显示表格 -->
   			<span id="local" style="font-size: 30px;font-family: 黑体;"></span>
   			<br />
	    	<table id="cw_show"  class="box1">
	    	</table>
   		</div>
   </div>
  </body>
</html>
