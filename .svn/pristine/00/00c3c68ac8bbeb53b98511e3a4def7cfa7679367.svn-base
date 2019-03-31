<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>档案归档</title>

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
	
	//外检查看图片
	function showYcPic(aTag,index){
	    $(aTag).children("img").removeClass("out");
	    var xtdabh = $('#xtdabh').val();
		var newUrl = "arcBaseInfo!ycInfoShowBigPic.action?xtdabh="+xtdabh+"&curIndex="+index;
		top.Dialog.open({URL:newUrl,Width:1024,Height:768,Title:"查看图片"});
		//showScreenOver();
	}
	
	//档案查看图片
    function showBasePic(aTag,index){
	    $(aTag).children("img").removeClass("out");
	    var xtdabh = $('#xtdabh').val();
		var newUrl = "arcBaseInfo!arcBaseShowBigPic.action?xtdabh="+xtdabh+"&curIndex="+index;
		top.Dialog.open({URL:newUrl,Width:1024,Height:768,Title:"查看图片"});
		//showScreenOver();
	}

	function closeWin2(){
		removeScreenOver();
		top.frmright.closeDialog();
	}
	
	function removeAllOut(){
		$(".out").removeClass("out");
	}
	
	function addAllOut(){
		$($($(".picItem2").children("a")).children("img")).addClass("out");
	}
	
	//提交
	function submitForm(){
		var storeNum=$("#newStorageNumber").val();
		var hphm=$("#hphm").val();
		if(storeNum==""){
		    alert("提交错误，请选择储位！");
		    return;
	    }  
		if(hphm==""){
		    alert("号牌号码为空该档案无法进行归档！");
		    return;
	    }  
		form.submit();
		closeWin();
	}
	
	
	//获取储位
	function handMade(xz){
	   if(xz=="zd"){
		   $.ajax({
			 type: "POST",
			 url: "<%=preAction %>!getStoreNumber.action",
			 data:{hpzl:hpzl},
			 dataType:"json",
			 success: function(msg){  
				if(msg=="202"){
				    alert("获取储位失败！");
				    return ;
				}else{
				var data=msg[0];
				var showStorageNumber = data.substring(0,1)+"-"+data.substring(1,3)+"-"+data.substring(3,4)+"-"+data.substring(4,5)+"-"+data.substring(5,8);
				    $("#storageNumber").html(showStorageNumber);
				    $("#newStorageNumber").val(msg[0]);
				}
				
			 }
		   });
	   }else{
	       var url="cwgl/stoget-getStore.action";
		   top.Dialog.open({URL:url,Width:800,Height:550,Title:"选择储位"});
	   } 
	}
	
	function writeCwbh(cwbh,showCwbh){
	    $("#storageNumber").html(showCwbh);
		$("#newStorageNumber").val(cwbh);
		return true;
	}
	
	
</script>
</head>
<body>
	<c:if test="${fn:indexOf(storageNumber,'#')>=0}" >
		<script>
			top.Dialog.alert("储位异常，请联系管理员！<br/>错误代码：${storageNumber }");
			closeWin();
		</script>
	</c:if>
	
	<div class="box1" panelWidth="1300" style="overflow: auto;height: 550px">
	<table class="tableStyle" formMode="view">
		<tr><th colspan="6">综合详细信息</th></tr>
		<tr>
			<td width="8%">系统编号：</td>
			<td width="8%">${entity.xtdabh }</td>
			<td width="8%">上传时间：</td>
			<td width="8%"><fmt:formatDate value="${entityZh.cjsj }" type="both"/></td>
			<td width="8%">上传人:</td>
			<td width="8%">${user2CnName[entityZh.cjr ] }</td>	
		</tr>
		<tr>
			<td width="8%">流水号:</td>
			<td width="8%">${entity.lsh }</td>
			<td width="8%">机动车序号:</td>
			<td width="8%">${entity.xh }</td>
			<td width="8%">机动车所有人:</td>
			<td width="8%">${entity.syr }</td>
		</tr>
		<tr>	
			<td width="8%">号牌号码:</td>
			<td width="8%"><input type="hidden" id="hphm" value="${entity.hphm }"/>${entity.hphm }</td>
			<td width="8%">号牌种类:</td>
			<td width="8%">${hpzlMap[entity.hpzl] }</td>
			<td width="8%">中文品牌:</td>
			<td width="8%">${entity.clpp1 }</td>
		</tr>
		
		<tr>
			<td>车辆识别代号:</td>
			<td nowrap="nowrap">${entity.clsbdh }</td>
			<td>业务类型:</td>
			<td nowrap="nowrap">${ywlxMap[entity.ywlx] }</td>
			<td>车辆型号:</td>
			<td nowrap="nowrap">${entity.clxh }</td>
		</tr>
		<tr>	
			<td>业务办理人:</td>
			<td nowrap="nowrap">${entity.cjr }</td>
			<td>办理时间:</td>
			<td nowrap="nowrap"><fmt:formatDate value="${entity.cjsj }" type="both"/></td>
			<td>车辆类型:</td>
			<td nowrap="nowrap">${entity.cllx }</td>
		</tr>
	</table>
	
<form id="form" method="post" action="<%=preAction %>!dagd.action" name="form" target="frmright">
<!-- 外检图片start -->
   <fieldset  style="background-color: #FFFAFA;">
	   <legend>外检图片 &nbsp; <!--<a href="#" onclick="removeAllOut()" >全部已看</a>--></legend>
		<div style="height:auto;overflow: auto;">
			<c:forEach items="${picListZh }" var="pic" varStatus="status" >
			    <div class="picItem2">
				  	<a href="#" onclick="showYcPic(this,'${status.index}')">
						<img class="out" src="arcBaseInfo!getNotBase64ToImagePath2.action?picPath=${pic.picpath}" id="ycpic${pic.id}"/>
					</a>
					<br/>
					<c:if test="${entityZh.shjg=='Y'}"><font style="color:red;">通过-</font></c:if>
					<c:if test="${entityZh.shjg!='Y'}">
					<input  id="paper_${pic.id }"  type="checkbox" name="picwj" value="${pic.id }" class="ck_class" style="display:none;" /> 
					</c:if>
					<label for="paper_${pic.id }">${yc_paper_map[pic.takecode ] }  </label>
				</div>
			</c:forEach>
			<div class="clear"></div>
		</div>
	</fieldset>
<!-- 外检图片end -->	
<br/>
<!-- 档案图片start -->
<fieldset style="background-color: #FFFAFA;">
	<legend>档案图片  &nbsp; </legend>
	<div style="height:auto;overflow: auto;">
	   <c:forEach items="${picYwlxList }" var="picYwlx" varStatus="picYwlxStatus">
			<br/>
			<font color="red">${ywlxMap[fn:split(picYwlx, ',')[0]] }</font><HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=1)" width="100%" color=#987cb9 SIZE=1 >
			<c:forEach items="${picList }" var="pic" varStatus="status" >
				<c:if test="${fn:split(picYwlx, ',')[0]==pic.ywlx && fn:split(picYwlx, ',')[1]==pic.smcs}" >
					<div class="picItem2">
					  	<a href="#" onclick="showBasePic(this,'${status.index}')" >
					    	<img src="arcBaseInfo!getBase64ToImage.action?tplj=${pic.tplj }" class="out" id="arcpic${pic.id}"/>
						</a>
						<br/><input id="paper_${pic.id }"  type="checkbox" name="picda" value="${pic.id }" class="ck_class" style="display: none;" />
						<label for="paper_${pic.id }">${paperMap[pic.paperId] } </label>
					</div>
				 </c:if>	
			</c:forEach>
		</c:forEach>
		<div class="clear"></div>
	</div>
</fieldset>
<!-- 档案图片end -->
 		<!-- 外检信息 -->
 		<input type="hidden" id="xtdabh"  name="xtdabh" value="${entityZh.xtdabh }" />
 		<!-- 档案信息  -->
 		<input type="hidden" id="arcId" name="arcId" value="${entity.id }"/>
 		<input type="hidden" id="xtdabh" name="xtdabh" value="${entity.xtdabh }"/>
 		<input name="arcType" type="hidden" value="${entity.dalx}" />
 		
		<table class="tableStyle" formMode="line">
			<c:if test="${entity.dalx!=2 }">
			<tr id="cwh" style="none" >
				<td class="ali03" width="48%">储位编号：</td>
				<td >
					<button type="button" onclick="handMade('sd')">&nbsp;&nbsp;获取储位编号&nbsp;&nbsp;</button>
					<!--<button type="button" onclick="handMade('zd')">&nbsp;&nbsp;自动获取&nbsp;&nbsp;</button>

					--><input name="storageNumber" type="hidden" id="newStorageNumber" />
					<span id="storageNumber">${storageNumber } </span>
				</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="2">
					<button type="button" onclick="submitForm()" ><span class="icon_ok">归档</span></button>
				</td>
			</tr>
		</table>
</form>
</div>
</body>
</html>