<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>外检审核窗</title>

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


	$(document).ready(function(){
       var verifyResult = "${verifyResult }";
       if(verifyResult=='Y'){
		removeAllOut()
       } 
    });

//添加图片蒙版
	function removeAllOut(){
		$(".out").removeClass("out");
	}
	
	function addAllOut(){
		$($($(".picItem2").children("a")).children("img")).addClass("out");
	}
	
	//审核结果
	function ra_onchange(ra){
		if(ra.value == "0"){
			$("#controlNext").css("display","");
			$(".c_reScan").css("display","");
			$(".ck_class").show();
			removeAllOut();
		}else{
			$("#controlNext").css("display","none");
			$(".c_reScan").css("display","none");
			$(".ck_class").hide();
			addAllOut();
		}	
	}
	
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
	
	function showPic(aTag,index){
	    $(aTag).children("img").removeClass("out");
	    var xtdabh = $('#wjbh').val();
		var newUrl = "arcBaseInfo!ycInfoShowBigPic.action?xtdabh="+xtdabh+"&curIndex="+index;
		top.Dialog.open({URL:newUrl,Width:1024,Height:768,Title:"查看图片"});
		//showScreenOver();
	}
	
	function reload(){
		window.location.reload();
	}
	
	function gb(){
	   removeScreenOver();
	   closeWin();
	}
	
	//提交审核数据
	function submitForm(){
	    var countChecked = $("input[name='picIds']:checked").size();
		var verifyRs = $("input[name='handInfoEntity.verifyResult']:checked").val();
		if(verifyRs == "0"){
			if(countChecked <= 0){
				top.Dialog.alert("请勾选需要重扫的档案页！");			
				return;
			}
		}
		
		 /*
	     *  在提交前，判断数据是否被审核，如果已被审核不能继续审核
	     */
	    var wjbh2=document.getElementById("wjbh").value;
		$.ajax({
			type: "POST",
			url: "arcBaseInfo!queryShjg.action",
			data:{wjbh2:wjbh2},
			dataType:"json",
			success: function(msg){  
				if(msg=="222"){
				     alert("该数据已被审核，请关闭信息窗口后刷新列表数据");
					  return ;
				}else{
					 insubmit();
				}
			}
		 }); 
	}
	
	function insubmit(){
	     //得到yc_info   wjbh
	     var wjbh=document.getElementById("wjbh").value;
	     //得到yc_info_pic  id
	     var id = document.getElementsByName("picIds");
	     //得到审核结果 1-通过   0-不通过
	     var tg=document.getElementById("sh-1").checked==true?"1":"0";
	     //var lsh=document.getElementById("lsh").value;
	     //备注
	     var bz=document.getElementById("bz").value;
	     
	     if(tg=="0"){
	          var t=0;
			     for(var i=0;i<id.length;i++){
					if(id[i].checked==true){
						t++;
					}
				 }
				 if(t==0){
				    top.Dialog.alert("请选择不通过的数据");
					return;
				 }
	          var f="";
	          for(var i=0;i<id.length;i++){
				if(id[i].checked==true){
					f=f+id[i].value+" ";
				}
			  } 
			  //不通过时，弹出打印窗并yc_info_pic表审核结果
			  
        	  //window.location.href="wjcy_tpyl!retuct.action?picId="+f+"&wjbh="+wjbh+"&tg="+tg+"&bz="+bz;
			  //top.frmright.window.location.href="wjcy_tpyl!viewImgs.action";
			  
        	  //var url=;
        	  
        	  window.open ("<%=preAction %>!retuct.action?picId="+f+"&wjbh="+wjbh+"&tg="+tg+"&bz="+bz, '打印回执单', 'height=400, width=520, top=100, left=200,scrollbars=yes, toolbar=no, menubar=no,  resizable=no,location=no, status=no');
        	  closeWin();
	     }
	     
	     
	     if(tg=="1"){
	     	  if($(".out").size()>0){
				 top.Dialog.alert("请查看完所有档案页！");			
				 return;	
			  }
	          var id6 = document.getElementsByName("picId6");
	          var f="";
	          for(var i=0;i<id6.length;i++){
					f=f+id6[i].value+" ";
			  } 
			  
			  window.location.href="<%=preAction %>!retuct.action?picId="+f+"&wjbh="+wjbh+"&tg="+tg+"&bz="+bz;
			  alert("提交成功");
			  top.frmright.window.location.href="arcBaseInfo!query_sh.action";
			  closeWin();
	     }
	}
	
    //打印回持单
    function plantHek(){
    var bz=document.getElementById("bzt").value;
		closeWin();
		window.open ('<%=preAction %>!getHek.action?wjbh=${entity.xtdabh }&bz='+bz, '打印回执单', 'height=400, width=520, top=100, left=200,scrollbars=yes, toolbar=no, menubar=no,  resizable=no,location=no, status=no');
    }
	
    /*
     * 条码退办
     * 删除该条码下的所有信息,
     * author ywj
     * 2014-8-6
     */
	function backOffice(){
    	top.Dialog.confirm("确认退办该条码？",function(){
			window.location.href="arcBaseInfo!backOffice.action?wjbh=${entity.xtdabh }";
			alert("提交成功");
			//top.frmright.window.location.href="wjcy_tpyl!viewImgs.action";
			top.frmright.window.location.href="arcBaseInfo!query_sh.action";
			closeWin();
    	},function(){});
	}

</script>
</head>

<body>
	
	<div class="box1" panelWidth="1300" style="overflow: auto;height: 550px">
	<table class="tableStyle" formMode="view">
			<tr><th colspan="6">详细信息</th></tr>
			<tr>
				<td width="15%">系统编号：</td>
				<td width="15%">${entity.xtdabh }</td>
				<td width="15%">号牌种类:</td>
				<td width="15%">${hpzlMap[entity.hpzl] }</td>
				<td width="15%">号牌号码：</td>
				<td width="15%">${entity.hphm }</td>
			</tr>
			<tr>
				<td>业务类型：</td>
				<td>${ywlxMap[entity.ywlx] }</td>
				<td>上传时间：</td>
				<td><fmt:formatDate value="${entity.cjsj }" type="both"/></td>
				<td>上传人:</td>
				<td>${user2CnName[entity.cjr ] }(${user2CnDeptCode[entity.cjr] }) </td> 
			</tr>
			
		</table>
		
		<fieldset>
        <legend>&nbsp;&nbsp;图&nbsp;片&nbsp;&nbsp;&nbsp;<a href="#" onclick="removeAllOut()" >全部已看</a></legend>
		<div style="height: 350px;overflow: scroll;">
			<c:forEach items="${picList }" var="pic" varStatus="status">
			    <!-- 存放yc_info_pic  ID -->
			       <input type="hidden" id="picId6" name="picId6"  value="${pic.id}" style="width:20px;" />
			    <!-- end结束 -->
			
				<div class="picItem2">
					<a href="#"  onclick="showPic(this,'${status.index}')">
						<img class="out"  src="arcBaseInfo!getNotBase64ToImagePath2.action?picPath=${pic.picpath}" id="ycpic${pic.id}"/>
					</a>
					<br/>
					说明：${yc_paper_map[pic.takecode ] }<c:if test="${pic.picStatus=='N'}"><font style="color:red;">不通过</font></c:if>
					<input type="checkbox" name="picIds" value="${pic.id}" class="ck_class" style="display: none;" />
				</div>
			</c:forEach>
		</div>
		</fieldset>
		<input type="hidden" id="wjbh"  value="${entity.xtdabh }" />
		
		<table class="tableStyle" formMode="line" style="display: ${from=='sh'?'none':'' };" >
		    <c:if test="${verifyResult=='S'}">
		     	<tr>
					<td width="45%" class="ali03">备注：</td>
					<td>
						<input type="text" id="bz" name="bz"/>
					</td>
				</tr>
	       	    <tr>
					<td  width="45%" class="ali03">审核结果：</td>
					<td>
						<input type="radio" onclick="ra_onchange(this)"  id="sh-1" name="handInfoEntity.verifyResult" value="1" class="validate[required] radio"   checked="checked"  /><label for="sh-1" class="hand">通过</label>
						<input type="radio" onclick="ra_onchange(this)"  id="sh-2" name="handInfoEntity.verifyResult" value="0" class="validate[required] radio" /><label for="sh-2" class="hand">不通过</label>
					</td>
					
				</tr>
				<tr id="controlNext" style="display: none;">
					<td>操作：</td>
					<td>打印回执单</td>
				</tr>	
				<tr><td colspan="2">
					  <button type="button" onclick="submitForm()" ><span class="icon_ok">提交</span></button>
					  <button type="button" onclick="backOffice()" ><span class="icon_ok">退办</span></button>
					  <button type="button" onclick="closeWin()"><span class="icon_clear">取消</span></button>
				</td></tr>
			</c:if>
			<c:if test="${verifyResult=='Y'}">
			    <tr>
				    <td width="50%">
						  备注：
					</td>
					<td>
						 ${entity.bz }
					</td>
				</tr>
				<tr>				
					<td colspan="2">
						<button type="button" onclick="backOffice()" ><span class="icon_ok">退办</span></button>		
						<button type="button" onclick="closeWin()"><span class="icon_clear">关闭</span></button>
					</td>
				</tr>
			</c:if>
			<c:if test="${verifyResult=='N'}">
				<tr>
					<td width="50%"> 备注：</td>
					<td> ${entity.bz }<input type="hidden" value="${entity.bz }" id="bzt" /></td>
				</tr>
				<tr><td colspan="2">
				      <button type="button" onclick="plantHek()" ><span class="icon_ok">打印</span></button>
				      <button type="button" onclick="backOffice()" ><span class="icon_ok">退办</span></button>
					  <button type="button" onclick="closeWin()"><span class="icon_clear">关闭</span></button>
				</td></tr>
			</c:if>
		</table>
    </div>
</body>
</html>