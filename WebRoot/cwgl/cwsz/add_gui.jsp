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
    
    <title>添加档案柜</title>
  
		<script type="text/javascript">
		//添加、减少档案柜
		function query_gui(type){
			//添加一个档案柜
			if(type==1){
				var add=$("<input name='gui_id'/><br id='gui_id_br'/>");
				add.render();
				$("#gui_list").append(add);
			}else{//减少一个档案柜
				$("#gui_list input[name='gui_id']:last").remove();
				$("#gui_list br#gui_id_br:last").remove();
			}
		}
		
		
		//提交
		function smt(){
			$("#fTable").validationEngine({returnIsValid: true});
			//获取提交数据
			var shiId=$("select[name='shi_id']").val();//室id
			
			var guiIds="";//柜ids
			var guiIdsObj=$("input[name='gui_id']");
			for(var i=0;i<guiIdsObj.length;i++){
				var guiIdVal=guiIdsObj[i].value;
				if(guiIdVal!=""){
				    if(guiIdVal.length!=2){
				       alert("档案柜填写错误，格式必须为字母+数字组成 如：A1");
				       return ;
				    }else{
				       var one=guiIdVal.substr(0,1);
				       var two=guiIdVal.substr(1,2);
				       if(!isNaN(one)){
				            alert("第一个必须为字母");
				            return ;
				       }
				       if(isNaN(two)){
				            alert("第二个必须为数字");
				            return ;
				       }
				    }
					guiIds+=guiIdVal.trim()+";";
				}else{
				   alert("请完成档案柜数据填写!");
				   return;
				}
			}
			
			var lieNum=$("input[name='lie_num']").val().trim();//列数
			var geNum=$("input[name='ge_num']").val().trim();//格数
			var fenNum=$("input[name='fen_num']").val().trim();//格容量
			var cflx=$("input[name='cflx']").val();//简要说明
			var remark=$("textarea[name='remark']").val().trim();
			
			if(shiId==""||guiIds==""||lieNum==""||geNum==""||fenNum==""){
				alert("请填写完整的信息！");
				return;
			}
			
			/**  信息填写验证start  *** */
			//档案列
			if(lieNum.length>2){
			    alert("档案列不能大于2位数的数字");
			    return ;
			    
			}else{
			   if(isNaN(lieNum)){
			     alert("档案列必须为不大于2位数的数字");
			     return ;
			   }
			}
			
			//档案格
			if(geNum.length>2){
			    alert("档案格不能大于2位数的数字");
			    return ;
			    
			}else{
			   if(isNaN(geNum)){
			     alert("档案格必须为不大于2位数的数字");
			     return ;
			   }
			}
			
			//容量
			if(fenNum.length>3){
			    alert("档案容量不能超过3位数");
			    return ;
			    
			}else{
			   if(isNaN(fenNum)){
			     alert("档案容量必须为数字");
			     return ;
			   }
			}

			
			//说明长度验证
			if(remark.length>100){
			    alert("说明长度必须小于100位数字");
			    return ;
			}			
			
			//请求数据
			$.post("cwgl/stoset-addGui.action",
				    {"shiId":shiId,"guiIds":guiIds,"lieNum":lieNum,"geNum":geNum,"fenNum":fenNum,"cflx":cflx,"remark":remark},
				    function(data){
				    	alert(data[1]);
					if(data[0]=="1"){
						freshAndClose("stoset-list.action");
					}
			});
			
		}
	</script>
  </head>
  
  <body>
  
  <div id="scrollContent">
  <div class="box1" style="height: 530px;overflow: auto;">
  
    	<table id="fTable" class="tableStyle" formMode="line" >
    		<tr>
    			<td>档案室：</td><td><select name="shi_id" selectedValue="" data='${shiId}'></select></td>
    		</tr>
    		<tr>
    			<td>档案柜编号（1位字母+1位数字）：</td>
    			<td>
    				<div id="gui_list">
    					<c:forEach items="${shi_rl}" var="shirl">
    						<input name='gui_id'/><br id='gui_id_br'/>
    					</c:forEach>
    				</div>
    				<button onclick="query_gui(1)">&nbsp;+添加柜&nbsp;</button><button onclick="query_gui(0)">&nbsp;-减少柜&nbsp;</button>
    			</td>
    		</tr>
    		<tr>
    			<td>档案柜列数：</td><td><input name="lie_num" value="${gui_rl}" maxlength="3" class="validate[required,custom[onlyNumber],length[1,2]]"/></td>
    		</tr>
    		<tr>
    			<td>档案列格数：</td><td><input name="ge_num" value="${lie_rl}" maxlength="3" class="validate[required,custom[onlyNumber],length[1,2]]"/></td>
    		</tr>
    		<tr>
    			<td>档案格容量：</td><td><input name="fen_num" value="${ge_rl}" maxlength="3" class="validate[required,custom[onlyNumber],length[1,3]]"/></td>
    		</tr>
    		<tr style="display: none;">
    			<td>简要说明：</td><td><input name="cflx" value="${cflx}" maxlength="10"/></td>
    		</tr>
    		<tr>
    			<td>档案室说明（150字以内）：</td><td><textarea rows="3" name="remark" class="validate[length[0,150]]"></textarea></td>
    		</tr>
    		<tfoot>
    			<tr>
    				<td colspan="2">
	    				<button onclick="smt()" >确定</button>
	    				<button onclick="closeWin()" >取消</button>
    				</td>
    			</tr>
    		</tfoot>
    	</table>
    </div>
    </div>
    	
  </body>
  
</html>