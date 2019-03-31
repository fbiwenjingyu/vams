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
    
    <title>修改储位信息</title>
    
  
	
	<script type="text/javascript">
	
		function flushUsed(sid){
			//不等于格长度不刷新
			if(sid.length==5 || sid.length==7){
				$.post("cwgl/stoset-flushUsed.action",{"cwbh":sid},function(data){
					if(data[0]=="1"){
						var useds=data[1];
						if(useds.length ==2){
							$("#used").html(useds[1]);
						}else{
							$("#used").html(useds[0]);
						}
						alert("更新完毕！");
					}else{
						alert(data[1]);
					}			
				});			
			}
		}
		
		//提交信息
		function smt(){
			var ckres=	$("#fTable").validationEngine({returnIsValid: true});
			if(!ckres){
				alert("请填写正确的信息！");
				return;
			}
			var cid=$("input[name='cid']").val();
			var zrl=$("input[name='zrl']").val();
			var enable=$("input:radio[name='enable']:checked").val();
			var cflx=$("input[name='cflx']").val();//简要说明
			var remark=$("textarea[name='remark']").val();
			
			//提交
			$.post("cwgl/stoset-modifySet.action",
					{"setting.cid":cid,"setting.zrl":zrl,"setting.sfky":enable,"setting.cflx":cflx,"setting.cwsm":remark},
					function(data){
					alert(data[1]);
					if(data[0]=="1"){
						location.reload(false);
//						freshTop("stoset-list.action");
						closeWin();
					}
			});
		
		}
	
	
	
	
	</script>
	<style type="text/css">
	    table,tr,td{
	        border: 1px solid gray;
	        border-collapse: collapse;
	        line-height: 26px;
	    }
	</style>
	
  </head>
  
  <body>
 <div id="scrollContent">
  <div id="tk" class="box1" style="overflow: auto;height: 400px;" >
    	<table id="fTable" class="tableStyle" formMode="line" width="100%">
    		<tr>
    			<td  align="right">&nbsp;储位编号：</td><td>${setting.cid}<input name="cid" type="hidden" value="${setting.cid}" /></td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;上级储位：</td><td>${setting.pid}</td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;储位类型：</td><td>${vs:cwlxName(setting.cwlx)}</td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;总容量：</td><td><input name="zrl" value="${setting.zrl}" maxlength="3" class="validate[required,custom[onlyNumber],length[1,3]]"  ${setting.cwlx=="4"?"":"type='hidden'"}/><c:if test='${setting.cwlx!="4"}'>${setting.zrl}</c:if></td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;已用量：</td><td id="used">${setting.syl}</td><td width="5%"><c:if test='${setting.cwlx=="4" || setting.cwlx=="5"}'><button onclick="flushUsed('${setting.cid}')">&nbsp;更新使用量&nbsp;</button></c:if></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;是否可用：</td><td><input type="radio" name="enable" ${setting.sfky=="1"?"checked='checked'":""} value="1" />可用&nbsp;<input type="radio" name="enable" ${setting.sfky!="1"?"checked='checked'":""} value="0" />不可用</td><td></td>
    		</tr>
    		<tr style="display: none;">
    			<td align="right">&nbsp;简要说明：</td><td><input type="text" name="cflx" value="${setting.cflx}" maxlength="10"/></td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;创建人：</td><td>${setting.cjrxm}（${setting.cjrid}）</td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;创建时间：</td><td><fmt:formatDate value="${setting.cjsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;修改人：</td><td>${setting.xgrxm}（${setting.xgrid}）</td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;修改时间：</td><td><fmt:formatDate value="${setting.xgsj}" pattern="yyyy-MM-dd HH:mm:ss"/></td><td></td>
    		</tr>
    		<tr>
    			<td  align="right">&nbsp;备注信息：<br>（150字以内）</td><td><textarea rows="3" name="remark">${setting.cwsm}</textarea></td><td></td>
    		</tr>
    		<tr   align="center">
    				<td colspan="3"><button onclick="smt()">确定</button><button onclick="closeWin()">关闭</button></td>
    			</tr>
    	</table>
    	</div>
   </div>
  </body>
</html>
