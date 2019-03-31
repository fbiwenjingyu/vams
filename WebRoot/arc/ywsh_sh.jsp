<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>业务审核、归档审核审核页面</title>

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
		opacity:0.25;
		filter:alpha(opacity:25);
	}
	
</style>
<script language="javascript">

    
	
	var ttt = "1";
	
	function initComplete(){
	}	
	
	/*
	function sm_onchange(sm){
		if(sm.value == "other"){
			$("#sm_detail").css("display","");
		}else{
			$("#sm_detail").css("display","none");
		}
	}*/
	
	function ra_onchange(ra){
		if(ra.value == "0"){
			//document.getElementById("cwh").style.display="none";
			$(".c_reScan").css("display","");
			$(".ck_class").show();
			removeAllOut();
		}else{
			//document.getElementById("cwh").style.display="block";
			$(".c_reScan").css("display","none");
			$(".ck_class").hide();
			addAllOut();
		}	
	}
	
	//查看图片
    function showPic(aTag,index){
	    $(aTag).children("img").removeClass("out");
	    var xtdabh = $('#xtdabh').val();
		var newUrl = "arcBaseInfo!arcBaseShowBigPic.action?xtdabh="+xtdabh+"&curIndex="+index;
		top.Dialog.open({URL:newUrl,Width:1024,Height:768,Title:"查看图片"});
		//showScreenOver();
	}
	
	//提交审核数据
	function submitForm(){
	    var countChecked = $("input[name='picIds']:checked").size();
		var verifyRs = $("input[name='handInfoEntity.verifyResult']:checked").val();
		if(verifyRs == "0"){
			if(countChecked <= 0){
				//top.Dialog.alert("请勾选需要重扫的档案页！");			
				//return;
			}
		}
		insubmit();
	}
	
	function insubmit(){
	     //得到   xtdabh
	     var xtdabh=document.getElementById("xtdabh").value;
	     //得到arc_pic_info  id
	     var id = document.getElementsByName("picIds");
	     //得到审核结果 1-通过   0-不通过
	     var tg=document.getElementById("sh-1").checked==true?"1":"0";
	     
	     　   var deptCode = document.getElementById("deptCode").value;
	     
         //alert(deptCode);
         
	     if(tg=="0"){//不通过
	          var t=0;
			     for(var i=0;i<id.length;i++){
					if(id[i].checked==true){
						t++;
					}
				 }
				// if(t==0){
				  //  top.Dialog.alert("请选择不通过的数据");
				//	return;
				// }
	          var f="";
	          for(var i=0;i<id.length;i++){
				if(id[i].checked==true){
					f=f+id[i].value+" ";
				}
			  } 
			  
			  window.location.href="<%=preAction %>!verify.action?picId="+f+"&xtdabh="+xtdabh+"&tg="+tg;
			  alert("提交成功");
			  //freshAndClose("arcBaseInfo!query_sh.action");
			  top.frmright.window.location.href="arcBaseInfo!query_sh.action?entity.deptcode=" + deptCode;
			  closeWin();
	     }
	     
	     
	     if(tg=="1"){//审核通过
	    	  if($(".out").size()>0){
				 top.Dialog.alert("请查看完所有档案页！");			
				 return;	
			  }
	          var id6 = document.getElementsByName("picId6");
	          var f="";
	          for(var i=0;i<id6.length;i++){
					f=f+id6[i].value+" ";
			  } 
			  
			  window.location.href="<%=preAction %>!verify.action?picId="+f+"&xtdabh="+xtdabh+"&tg="+tg;
			  alert("提交成功");
			  top.frmright.window.location.href="arcBaseInfo!query_sh.action?entity.deptcode=" + deptCode;
			  closeWin();
	     }
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
	
	
	//退办
	function backOffice(){
	    var arcType=document.getElementById("arcType").value;
	    if(arcType=="2"){
	      alert("退办失败，该档案为历史档案！");
	      return ;
	    }
    	top.Dialog.confirm("确认退办该条码？",function(){
			window.location.href="arcBaseInfo!arcBaseBackOffice.action?xtdabh=${entity.xtdabh }";
			alert("提交成功");
			top.frmright.window.location.href="arcBaseInfo!query_sh.action";
			closeWin();
    	},function(){});
	}
</script>
</head>
<body>
	<div class="box1" panelWidth="1300" style="overflow: auto;height: 550px">
	<table class="tableStyle" formMode="view">
		<tr><th colspan="12">详细信息</th></tr>
		<tr>
			<td width="8%">系统编号:</td>
			<td width="8%">${entity.xtdabh }</td>
			<td width="8%">机动车序号:</td>
			<td width="8%">${entity.xh }</td>
			<td width="8%">机动车所有人:</td>
			<td width="8%">${entity.syr }</td>
			
			<td width="8%">号牌号码:</td>
			<td width="8%">${entity.hphm }</td>
			<td width="8%">号牌种类:</td>
			<td width="8%">${hpzlMap[entity.hpzl] }</td>
			<td width="8%">流水号:</td>
			<td width="8%">${entity.lsh }</td>
		</tr>
		
		<tr>
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
	<legend>档案页  &nbsp; <a href="#" onclick="removeAllOut()" >全部已看 </a></legend>
	<div style="height:auto;overflow: auto;">
		<c:forEach items="${picYwlxList }" var="picYwlx" varStatus="picYwlxStatus">
			<br/>
			<font color="red">${ywlxMap[fn:split(picYwlx, ',')[0]] }</font><HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=1)" width="100%" color=#987cb9 SIZE=1 >
			<c:forEach items="${picList }" var="pic" varStatus="status" >
				<c:if test="${fn:split(picYwlx, ',')[0]==pic.ywlx && fn:split(picYwlx, ',')[1]==pic.smcs}" >
				  <input type="hidden" id="picId6" name="picId6"  value="${pic.id}" style="width:20px;" />
					<div class="picItem2">
					  	<a href="#" onclick="showPic(this,'${status.index}')" > 
					    	<img src="arcBaseInfo!getBase64ToImage.action?tplj=${pic.tplj }" id="arcpic${pic.id}" class="out"/>
						</a>
						<br/><input id="paper_${pic.id }" type="checkbox" name="picIds" value="${pic.id}" class="ck_class" style="display: none;" /><label for="paper_${pic.id }"> ${paperMap[pic.paperId] } </label>
					</div>
				 </c:if>	
			 </c:forEach>
		</c:forEach>
		<div class="clear"></div>
	</div>
	</fieldset>
	
 		<input type="hidden" name="xtdabh" id="xtdabh" value="${entity.xtdabh }" />	
 		<input type="hidden" id="arcType" value="${entity.dalx }" />
 		<input type="hidden" id="deptCode" value="${deptCode}" />
		<table class="tableStyle" formMode="line">
       		<tr>
				<td class="ali03" width="48%">审核结果：</td>
				<td>
					<input type="radio" onclick="ra_onchange(this)"  checked="checked" id="sh-1" name="handInfoEntity.verifyResult" value="1" class="validate[required] radio" /><label for="sh-1" class="hand">通过</label>
					<input type="radio" onclick="ra_onchange(this)"  id="sh-2" name="handInfoEntity.verifyResult" value="0" class="validate[required] radio" /><label for="sh-2" class="hand">不通过</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="submitForm()" ><span class="icon_ok">提交</span></button>
					<button type="button" onclick="backOffice()" ><span class="icon_ok">退办</span></button>
					<button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td>
			</tr>
		</table>
    </div>
</body>
</html>