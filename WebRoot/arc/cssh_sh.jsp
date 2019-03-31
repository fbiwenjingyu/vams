<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>重扫审核</title>

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
</style>
<script language="javascript">
	
	function initComplete(){
		//初始时有标题
		$('.boxgrid.caption').each(function(){
			$(".cover", this).css({
				top:"95px"
			});
		});
		
		$('.boxgrid.caption').hover(function(){
			$(".cover", this).stop().animate({top:'60px'},{queue:false,duration:160});
		}, function() {
			$(".cover", this).stop().animate({top:'95px'},{queue:false,duration:160});
		});
	}
	
	function sm_onchange(sm){
		if(sm.value == "other"){
			$("#sm_detail").css("display","");
		}else{
			$("#sm_detail").css("display","none");
		}
	}
	
	function showOldPic(ytp){
		var newUrl = "arcBaseInfo!getBase64ToImage.action?tplj="+ytp;
		top.Dialog.open({URL:newUrl,Width:1024,Height:768,Title:"查看图片"});
	}
	
	 function showPic(aTag,index){
	    $(aTag).children("img").removeClass("out");
	    var xtdabh = $('#xtdabh').val();
		var newUrl = "arcBaseInfo!arcBaseShowBigPic.action?xtdabh="+xtdabh+"&curIndex="+index;
		top.Dialog.open({URL:newUrl,Width:1024,Height:768,Title:"查看图片"});
		//showScreenOver();
	}
	
	function submitForm(){
		form.submit();
		closeWin();
	}
	
</script>
</head>
<body>
<form id="form" method="post" action="<%=preAction %>!rescanVerify.action" name="form"  target="frmright">
	<div class="box1" panelWidth="1300" style="overflow: auto;height: 550px">
	<table class="tableStyle" formMode="view">
		<tr><th colspan="6">详细信息</th></tr>
		<tr>
			<td width="15%">系统编号:</td>
			<td width="15%">${entity.xtdabh }</td>
			<td width="15%">机动车序号:</td>
			<td width="15%">${entity.xh }</td>
			<td width="15%">机动车所有人:</td>
			<td width="15%">${entity.syr }</td>
		</tr>
		<tr> 
			<td>流水号:</td>
			<td>${entity.lsh }</td>
			<td>号牌号码:</td>
			<td>${entity.hphm }</td>
			<td>号牌种类:</td>
			<td>${hpzlMap[entity.hpzl] }</td>
			
		</tr>
		<tr>
			<td>车辆识别代号:</td>
			<td>${entity.clsbdh }</td>
			<td>业务类型:</td>
			<td>${ywlxMap[entity.ywlx] }</td>
			<td>车辆型号:</td>
			<td>${entity.clxh }</td>
		</tr>
		<tr>
			<td>业务办理人:</td>
			<td>${user2CnName[entity.cjr] }</td>
			<td>办理时间:</td>
			<td><fmt:formatDate value="${entity.cjsj}" type="both"/></td>
			<td>车辆类型:</td>
			<td>${entity.cllx }</td>
		</tr>
	</table>
	
	<fieldset>
	<legend>档案页</legend>
	
	<div style="height: 350px;overflow: scroll;">
		<c:forEach items="${picList }" var="pic" varStatus="status">
			<div class="picItem2">
			  	<a href="#" onclick="showPic(this,'${status.index}')" > 
			    	<img src="arcBaseInfo!getBase64ToImage.action?tplj=${pic.tplj }" class="out"/>
				</a>
				<br/><input type="hidden"" name="picIds" value="${pic.id }" class="ck_class" /><label for="paper_${pic.id }"> ${paperMap[pic.paperId] } </label>
				<a href="#"  onclick="showOldPic('${pic.ytp}')" style="display:${picPage=='cx'&&pic.ytp!=null?'':'none' };color: red;">重扫前照片</a>
			</div>
		</c:forEach>
	</div>
	</fieldset>
 		<input type="hidden" name="xtdabh" id="xtdabh" value="${entity.xtdabh }"/>
 		<input type="hidden" name="csType" value="${csType }"/>
 		
 		
		<table class="tableStyle" formMode="line" style="display: ${picPage=='cx'?"none":'' }">
			<tr>
				<td class="ali03">审核结果：</td>
				<td>
					<input type="radio" id="sh-1" name="verifyResult" checked="checked" value="1" class="validate[required] radio" /><label for="sh-1" class="hand">通过</label>
					<input type="radio" id="sh-2" name="verifyResult" value="0" class="validate[required] radio" /><label for="sh-2" class="hand">不通过</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="submitForm()" ><span class="icon_ok">提交</span></button>
					<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
		</table>
   		
    </div>
    </form>
</body>
</html>