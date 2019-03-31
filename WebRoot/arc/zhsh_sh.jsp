
<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>综合审核页</title>

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
	
	var ttt = "1";
	
	function initComplete(){
	}	
	
	function ra_onchange(ra){
	    if(ra.value == "0"){
			//document.getElementById("cwh").style.display="none";
			$(".c_reScan").css("display","");
			$(".ck_class").show();
			form.action = "<%=preAction %>!applyRescan.action";
			removeAllOut();
			
		}else{
			
			$(".c_reScan").css("display","none");
			$(".ck_class").hide();
			form.action = "<%=preAction %>!verify.action";
			addAllOut();
			//document.getElementById("cwh").style.display="block";
		}
	
			
	}
	
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
	    //verifyRs 0-不通过  1-通过
	    var verifyRs = $("input[name='handInfoEntity.verifyResult']:checked").val();
	   
	    //获取外检
		var wjbh=document.getElementById("wjbh").value;
		var bz=""
		
		var deptCode = document.getElementById("deptCode").value;
		 
		if(${entityZh.shjg=='S' }){
		   bz=document.getElementById("bz").value;
		}
		
	    var wj = $("input[name='picwj']:checked").size();
	    var da = $("input[name='picda']:checked").size();
	    
	    if(verifyRs == "0"){
			//if(wj <= 0){
			//	if(da <= 0){
					//top.Dialog.alert("请勾选不通过的选项！");		
					//return;
			//    }   
			//}
			//if(da <= 0){
			    //if(wj <= 0){
					//top.Dialog.alert("请勾选不通过的选项！");		
					//return;
				//}
			//}
			
			
			//得到不通过的勾选图片
			var picwj = document.getElementsByName("picwj");
			var picda = document.getElementsByName("picda");
			var w="";var d="";
			for(var i=0;i<picwj.length;i++){
					if(picwj[i].checked==true){
					   w=w+picwj[i].value+" ";
					}
			}
			for(var i=0;i<picda.length;i++){
					if(picda[i].checked==true){
					   d=d+picda[i].value+" ";
					}
			}

		    if(w=="" && d!=""){  //档案不通过
			    //传出值
				window.location.href="<%=preAction %>!verifyZh.action?wjbh="+wjbh+"&w="+w+"&d="+d+"&verifyResult="+verifyRs+"&bz="+bz;
				alert("提交成功");
				top.frmright.window.location.href="arcBaseInfo!query_sh.action?entity.deptcode=" + deptCode;	
				closeWin();
			} 
		    if(d=="" && w!=""){ //外检不通过
			    //传出值
				var url="<%=preAction %>!verifyZh.action?wjbh="+wjbh+"&w="+w+"&d="+d+"&verifyResult="+verifyRs+"&bz="+bz;
	        	window.open (url, '打印回执单', 'height=400, width=520, top=100, left=200,scrollbars=yes, toolbar=no, menubar=no,  resizable=no,location=no, status=no'); 
	       	    alert("提交成功");
	        	top.frmright.window.location.href="arcBaseInfo!query_sh.action?entity.deptcode=" + deptCode;
			  	closeWin();
	        	
		    }
		    if(w!="" && d!=""){  //档案外检都不通过 ，外检打印回执单
		        var url="<%=preAction %>!verifyZh.action?wjbh="+wjbh+"&w="+w+"&d="+d+"&verifyResult="+verifyRs+"&bz="+bz;
				window.open (url, '打印回执单', 'height=400, width=520, top=100, left=200,scrollbars=yes, toolbar=no, menubar=no,  resizable=no,location=no, status=no');
				//传出值
				alert("提交成功");
		        top.frmright.window.location.href="arcBaseInfo!query_sh.action?entity.deptcode=" + deptCode;
			    closeWin();
		    }
		    if(w=="" && d==""){
		    	window.location.href="<%=preAction %>!verifyZh.action?wjbh="+wjbh+"&verifyResult="+verifyRs+"&bz="+bz;
			  	alert("提交成功");
			  	top.frmright.window.location.href="arcBaseInfo!query_sh.action?entity.deptcode=" + deptCode;	
			 	closeWin();	  
		    }
		}

        if(verifyRs=="1"){  
       		  if($(".out").size()>0){
				 top.Dialog.alert("请查看完所有档案页！");			
				 return;	
			  }
		    //审核通过
	           
			  window.location.href="<%=preAction %>!verifyZh.action?wjbh="+wjbh+"&verifyResult="+verifyRs+"&bz="+bz;
			  alert("提交成功");
			  top.frmright.window.location.href="arcBaseInfo!query_sh.action?entity.deptcode=" + deptCode;	
			  closeWin();	  
		}
		 
	}
	
	 /*
     * 条码退办
     * 删除该条码下的所有信息，包括收费表信息
     * author wangwei
     * 2013-12-18 14:17:02
     */
	function backOffice(){
    	top.Dialog.confirm("确认退办该条码？",function(){
			window.location.href="arcBaseInfo!backOfficeZh.action?xtdabh=${entityZh.xtdabh }";
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
		<tr><th colspan="6">综合详细信息</th></tr>
		<tr>
			<td width="8%">系统编号：</td>
			<td width="8%">${entityZh.xtdabh }</td>
			<td width="8%">上传时间：</td>
			<td width="8%"><fmt:formatDate value="${entityZh.cjsj }" type="both"/></td>
			<td width="8%">上传人:</td>
			<td width="8%">${user2CnName[entityZh.cjr ] }(${user2CnDeptCode[entity.cjr] })</td>	
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
			<td width="8%">${entity.hphm }</td>
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
			<td nowrap="nowrap">${user2CnName[entity.cjr] }</td>
			<td>办理时间:</td>
			<td nowrap="nowrap"><fmt:formatDate value="${entity.cjsj }" type="both"/></td>
			<td>车辆类型:</td>
			<td nowrap="nowrap">${entity.cllx }</td>
		</tr>
	</table>
	
<form id="form" method="post" action="<%=preAction %>!verifyZh.action" name="form" target="frmright">
<!-- 外检图片start -->
   <fieldset  style="background-color: #FFFAFA;">
	   <legend>外检图片 &nbsp; <a href="#" onclick="removeAllOut()" >全部已看</a></legend>
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
 		<input type="hidden" id="wjbh"  value="${entityZh.xtdabh }" />
 		
 		<input type="hidden" id="xtdabh"  value="${entity.xtdabh }" />
 		
 		<!-- 档案信息  -->
 		<input type="hidden" id="dawjbh" value="${entity.lsh }"/>
 		
 		<input type="hidden" id="deptCode" value="${deptCode}" />


		<table class="tableStyle" formMode="line">
			<c:if test="${entityZh.shjg=='S' }">
				<tr >
					   <td width="48%">外检备注：</td>
					   <td><input type="text" id="bz"  value=""/> </td>
				</tr>
			</c:if>
       		<tr>
				<td class="ali03" width="48%">审核结果：</td>
				<td >
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
</form>
</div>
</body>
</html>