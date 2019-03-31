<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看档案图片</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>


<style>
	.boxgrid{ 
		width: 215px; 
		height: 122px; 
		margin:10px; 
		float:left; 
		background:#161613; 
		border: solid 2px #8399AF; 
		overflow: hidden; 
		position: relative; 
	}

	.out{
		filter:alpha(opacity:25);
	}
	
</style>
<script language="javascript">
	//查看图片
    function showPic(aTag,index){
	    $(aTag).children("img").removeClass("out");
	    var xtdabh = $('#xtdabh').val();
		var newUrl = "arcBaseInfo!arcBaseShowBigPic.action?xtdabh="+xtdabh+"&curIndex="+index;
		top.Dialog.open({URL:newUrl,Width:1024,Height:768,Title:"查看图片"});
	}
</script>
</head>
<body>
	<div class="box1"  style="overflow: auto;height: 550px">
	<table class="tableStyle" formMode="view">
		<tr><th colspan="14">详细信息</th></tr>
		<tr> 
		    <td width="7%">系统编号:</td>
			<td width="7%">${entity.xtdabh }</td>
			<td width="7%">流水号:</td>
			<td width="7%">${entity.lsh }</td>
			<td width="7%">机动车序号:</td>
			<td width="7%">${entity.xh }</td>
			<td width="7%">机动车所有人:</td>
			<td width="7%">${entity.syr }</td>
			
			<td width="7%">号牌号码:</td>
			<td width="7%">${entity.hphm }</td>
			<td width="7%">号牌种类:</td>
			<td width="7%">${hpzlMap[entity.hpzl] }</td>
			<td width="7%">中文品牌:</td>
			<td width="7%">${entity.clpp1 }</td>
		</tr>
		
		<tr>
			<td>储位编号:</td>
			<td nowrap="nowrap">${entity.cwbh }</td>
			<td>车辆识别代号:</td>
			<td nowrap="nowrap">${entity.clsbdh }</td>
			<td>业务类型:</td>
			<td nowrap="nowrap">${ywlxMap[entity.ywlx] }</td>
			<td>车辆型号:</td>
			<td nowrap="nowrap">${entity.clxh }</td>
			
			<td>业务办理人:</td>
			<td nowrap="nowrap">${user2CnName[entity.cjr] }</td>
			<td>办理时间:</td>
			<td nowrap="nowrap"><fmt:formatDate value="${entity.cjsj }" type="both"/></td>
			<td>车辆类型:</td>
			<td nowrap="nowrap">${entity.cllx }</td>
		</tr>
	</table>
	<fieldset   style="background-color: #FFFAFA;">
	<legend>档案页  </legend>
	<div style="height:auto;overflow: auto;">
		<c:forEach items="${picYwlxList }" var="picYwlx" varStatus="picYwlxStatus">
			<br/>
			<font color="red">${ywlxMap[fn:split(picYwlx, ',')[0]] }</font><HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=1)" width="100%" color=#987cb9 SIZE=1 >
			<c:forEach items="${picList }" var="pic" varStatus="status" >
				<c:if test="${fn:split(picYwlx, ',')[0]==pic.ywlx && fn:split(picYwlx, ',')[1]==pic.smcs}" >
				  <input type="hidden" id="picId6" name="picId6"  value="${pic.id}" style="width:20px;" />
					<div class="picItem2">
					  	<a href="#" onclick="showPic(this,'${status.index}')" > 
					    	<img src="arcBaseInfo!getBase64ToImage.action?tplj=${pic.tplj }"  id="arcpic${pic.id}" class="out"/>
						</a>
						<br/><input id="paper_${pic.id }" type="checkbox" name="picIds" value="${pic.id}" class="ck_class" style="display: none;" /><label for="paper_${pic.id }">${paperMap[pic.paperId] } </label>
					</div>
				 </c:if>	
			 </c:forEach>
		</c:forEach>
		<div class="clear"></div>
	</div>
	</fieldset>
	<input type="hidden" name="xtdabh" id="xtdabh" value="${entity.xtdabh }" />	
 		
		<!--<table formMode="line" align="center">		
				<tr>				
					<td>	
						<button type="button" onclick="closeWin()"><span class="icon_clear">关闭</span></button>
					</td>
				</tr>
		</table>
    --></div>
</body>
</html>