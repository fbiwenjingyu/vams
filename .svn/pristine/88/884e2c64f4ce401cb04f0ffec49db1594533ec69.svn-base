<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<%@ include file="/common/taglib.jsp"%>
  	<%@ include file="/common/meta.jsp"%>
    
    <title>储位档案数据录入</title>


	<script type="text/javascript">
		//数据录入方式，1查询，0手工
		var lrtype;
		$(function(){
				//默认为查询输入
				lrtype=1;
		//		$("input[name='hphm']").attr("readonly","true").css("background-color","#dcdcdc");
		//		$("input[name='clsbdh']").attr("readonly","true").css("background-color","#dcdcdc");
//			$("#msg_show").attr("align","center");
//			$("button").attr("type","button");
				$("#he_tiao").bind("actived",function(e,i){
					nextHe(i+1);
				});
		});
		
		//查询下一盒
		function nextHe(index){
			var heid = ""+index;
			if(heid.length == 1){
				heid = "0"+new String(heid);
			}else{
				heid = new String(heid);
			}
			var geid=$("input[name='cwbh']").val().substring(0,5);
			location.href="stoget-stoAssign.action?cwbh="+geid+heid;
		}
		
		
	
		/**数据录入方式*/
		function lrType(tp){
			cleanMsg();
			if(tp==lrtype){
				return;
			}else{
				resetInp();//当选择变更时，清空输入框
			}
			lrtype=tp;
			
			//查询录入
			if(tp==1){
				$("input[name='cxlr']").attr("checked","checked");
				$("input[name='sglr']").removeAttr("checked");
				$("#show_xh").hide();//显示机动车序号
				$("#cxlhy").fadeIn(300);
				//隐藏输入项
				$("input[id='show_hide']").fadeOut(200);
				$("div#show_hide_span").fadeOut(300);
			}
			//手工录入
			if(tp==0){
				$("input[name='sglr']").attr("checked","checked");
				$("input[name='cxlr']").removeAttr("checked");
	//			$("#show_xh").show();//显示机动车序号
				$("#cxlhy").fadeOut(300);
				//显示输入项
	//			$("#lr_show").find("span").html("");//清空span
				$("input[id='show_hide']").fadeIn(300);
				$("div#show_hide_span").fadeIn(300);
			}
			$("span.star").html("*");
			//隐藏信息
			showPrint(false);
		}
		
		//当前的储位编号
		var current_cwbh="${cwbh}";
		
		/**清空*/
		function clean(tag){
			if(null != tag && "" != tag){
				$("input[name='"+tag+"']").val("");
				if(tag=="hphm_sel"){
					$("input[name='hphm_sel']").val("${session.user.hpqyshi}");
				}
			}else{
				$("input[name='lsh']").val("");
				$("select[name='ywlx']").val("");
				$("input[name='hphm']").val("");
				$("input[name='dabh']").val("");
				$("input[name='clsbdh']").val("");
				$("input[name='xh']").val("");
			}
		}
		
		/**回车自动查询*/
		function autoEnter(tid,inp,event){
			//验证回车
			if(checkEnter(event)){
				//如果选择的输入框的name与查询的目标相同
				if(inp.name== tid+"_sel"){
					$(inp).focus();
					checkSino(tid,document.getElementById("find_"+tid));
				}
			}
		}
		
		//=====================================数据处理================================================
		//查询状态
		var findStatus=false;
		//查询六合一是否成功？，如果成功则允许提交，否则不能提交
		var sino_ok=false;
		//六合一查询返回后转换ArcbaseinfoSdate封装json对象
		var obj_sino={};
		var print_id;
		//查询六合一接口；tt：查询类型
		function checkSino(tt,btn){
			//如果查询正在进行，则禁止再次查询
			if(findStatus){
				return;
			}
			//清除提示消息
			cleanMsg();
			var findata=$("input[name='"+tt+"_sel']").val();
			var findata1= "";
			//如果是按照号牌号码查询，则查询号牌种类字段
			if(tt == "hphm"){
				findata1=$("select[name='hpzl_sel']").val();
				//验证查询
				if(findata1 == null || findata1.trim()== ""){
					alert("请选择号牌种类！");
					$("input[name='"+tt+"_sel']").focus();
					return;
				}
				//验证号牌号码格式
				if(findata == null || findata.trim()== "" || findata.length < 6){
					alert("请输入号牌号码！");
					$("input[name='"+tt+"_sel']").focus();
					return;
				}
			}
			
			//验证查询信息
			if(findata == null || findata.trim()== ""){
				alert("请输入要查询的信息！");
				$("input[name='"+tt+"_sel']").focus();
				return;
			}
			
			//开始查询
			findStatus=true;
			//按钮禁用，变成载入中
			$("button[id^='find_']").attr("disabled","disabled");
			$(btn).attr("disabled","disabled").find("span").attr("class","icon_loading");
			//查询六合一
			$.post("cwgl/stoget-findSino.action",{"querytype":tt,"findata":findata,"findata1":findata1},function(data){
				//结束查询
				findStatus=false;
				//还原查询按钮为可用
				$(btn).removeAttr("disabled").find("span").attr("class","icon_find");
				$("button[id^='find_']").removeAttr("disabled");
				//查询结果
				if(data[0]=="1"){//成功
					//将数据保存到json对象
					obj_sino=data[1];
					$("select[name='show_hpzl']").setValue(obj_sino.hpzl);
					$("input[name='show_hphm']").val(obj_sino.hphm);
					$("input[name='show_lsh']").val(obj_sino.lsh);
					$("select[name='show_ywlx']").setValue(obj_sino.ywlx);
					$("input[name='show_dabh']").val(obj_sino.dabh);
					$("input[name='show_clsbdh']").val(obj_sino.clsbdh);
					$("input[name='show_syr']").val(obj_sino.syr);
					$("input[name='show_bz']").val(obj_sino.bz);
					sino_ok=true;
					//清除输入框
					clean(tt+"_sel");
					//提示状态
					okMsg("六合一数据查询成功！");
					//隐藏打印框
					showPrint(false);
					//将焦点跳入保存按钮
					$("button[id='saveData']").focus();
				}else{
					//查询失败
					alert(data[1]);
					//查询失败后，还原输入框焦点
					$("input[name='"+tt+"_sel']").focus();
				}
			});
			
		}
		
		//=======================
		//验证已输入的数据，并些吧输入的数据放到json中
		function checkInput(){
			//备注
			var bz=$("input[name='show_bz']").val();
				if(bz != null && bz != ""){
					obj_sino.bz=bz.trim();
				}
				
			//号牌种类
			var hpzl=$("select[name='show_hpzl']").val();
			if(hpzl == null || hpzl == ""){
				alert("请选择号牌种类");return false;
			}else{
				obj_sino.hpzl = hpzl.trim();
			}
			//号牌号码
			var hphm=$("input[name='show_hphm']").val();
			if(hphm == null || hphm == ""){
				alert("请输入号牌号码");return false;
			}else{
				obj_sino.hphm = hphm.trim();
			}
			//六合一流水号
			/*
			var lsh=$("input[name='show_lsh']").val();
			if(lsh == null || lsh == ""){
				alert("请输入流水号");return false;
			}else{
				obj_sino.lsh = lsh.trim();
			}
			*/
			//号牌种类
			var ywlx=$("select[name='show_ywlx']").val();
			if(ywlx == null || ywlx == ""){
				alert("请选择业务类型");return false;
			}else{
				obj_sino.ywlx = ywlx.trim();
			}
			/*
			//档案编号
			var dabh=$("input[name='show_dabh']").val();
			if(dabh == null || dabh == ""){
				alert("请输入档案编号");return false;
			}else{
				obj_sino.dabh = dabh.trim();
			}
			*/
			//车辆识别代号
			var clsbdh=$("input[name='show_clsbdh']").val();
			if(clsbdh == null || clsbdh == ""){
				alert("请输入车辆识别代号");return false;
			}else{
				obj_sino.clsbdh = clsbdh.trim();
			}
			
			return true;
		}
		
		
		//保存数据
		function save(){
			//清空提示信息
			cleanMsg();
			//查看是否为查询录入
			if(lrtype==1){
				//是否查询六合一接口成功？
				if(!sino_ok){
					alert("请查询数据！");
					return;
				}else{
					//验证输入框
					if(checkInput()){
						obj_sino.cwbh=$("input[name='cwbh']").val().trim();
						obj_sino.dalx="2";//历史档案
						//提交
						smt(obj_sino);
					}
				}
			}else{//手工录入
				if(checkInput()){
					obj_sino.cwbh=$("input[name='cwbh']").val().trim();
					obj_sino.dalx="2";//历史档案
					//提交
					smt(obj_sino);
				}
			}
		}
		
		/**转换成infod对象*/
		function jsonToInfod(obj){
			var infod={};
			for ( var key in obj) {
				infod["infod."+key]=obj[key];
			}
			return infod;
		}
		
		//提交信息
		function smt(obj){
			$.post("cwgl/stoget-saveStore.action",jsonToInfod(obj),function(data){
			//	alert(data[1]);
				if(data[0]=="1"){
					obj_sino=data[2];
					okMsg(data[1]);
					//清空查询结果
					$("#lr_show").find("input").val("");
	//				$("#lr_show").find("span").html("");
					//显示打印信息
					showPrint(true,obj_sino);
					//重置输入
					resetInp();
					//将焦点放入打印按钮
					$("button[id='printBtn']").focus();
				}else{
					alert(data[1]);
				}		
			});
		}
		
		/**重置输入框*/
		function resetInp(){
				//接口查询状态还原
				sino_ok=false;
				clean();//清除查询
				flushUsed();//刷新使用量
				//数据对象清空
				obj_sino={};
		}
		
		/**显示标签打印table*/
		function showPrint(ishow,obj){
			if(ishow){
				print_id = obj.xtdabh;
				//设置显示数据
				$("#print_dabh").html(obj.dabh);
				$("#print_lsh").html(obj.lsh);
				$("#print_hpzl").html(obj.hpzl);
				$("#print_hphm").html(obj.hphm);
				$("#print_ywlx").html(obj.ywlx);
				$("#print_clsbdh").html(obj.clsbdh);
				$("#print_cwbh").html(obj.cwbh);
				$("#print_xtdabh").html(obj.xtdabh);
				
				$("#print").fadeIn(300);
				clickA();
			}else{
				$("#print").fadeOut(300);
			}
		}
		
        /**标签打印*/
		function printInfo(){
				var url="../sys/barcode!printArcLabel.action?xtdabh="+print_id; //转向网页的地址;
				var name="标签打印页"; //网页名称，可为空;
				var iWidth="420"; //弹出窗口的宽度;
				var iHeight="400"; //弹出窗口的高度;
				//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
				var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
				var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
				window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
		}

     	//锚点跳转
		function clickA(){
			$("a[name='tag_a']").attr("href","#print");
			$("#tag_click").click();
		}

		//正确信息
		function okMsg(msg){
			$("#msg_error").html("");
			$("#msg_ok").html(msg);
		}
		//错误信息
		function errorMsg(msg){
			$("#msg_ok").html("");
			$("#msg_error").html(msg);
		}
		//清除提示信息！
		function cleanMsg(){
			okMsg("");
			errorMsg("");
		}
		
		//刷新档案格容量、储位编号
		function flushUsed(){
			$.post("cwgl/stoget-getNewCwbh.action",{"cwbh":"${setting.cid}"},function(data){
				var cwbh=data[0];
				var ge=data[1];
				if(Number(ge.syl) >= Number(ge.zrl)){
					$("#show_used").html("<font color='red'>"+ge.syl+"</font>");
					$("#msg_ok").html("储位使用量刷新成功！");
					$("#msg_error").html("但使用量已满，请更换储位！");
					alert("当前储位已满，请更换储位");
				}else if(cwbh[0]=="0"){
					$("#msg_error").html(cwbh[1]);
				}else{
					$("#show_used").html(data.syl);
					//将储位编号设置进去
					$("input[name='cwbh']").val(cwbh[1]);
					$("#show_cwbh_flush").html(cwbh[1]);
					okMsg("储位使用量刷新成功！");
				}
			});
		}
		
	</script>
	
  </head>
  
  <body>
  
 <div id="scrollContent" style="height: 100%;">
 <div class="box1" style="overflow: auto;height: 100%">
 	<!-- 储位编号 -->
 	<input name="cwbh" type="hidden" value="${cwbh}" />
 	
  	<input name="geId" value="${setting.cid}" type="hidden" />
  	<br />
  	<div style="font-size: 24px;" align="center">
  		当前储位：${showId}<hr /><br />	
  	</div>
  	
  	<div id="" align="center" style="font-size: 14px;">
  	档案数据录入方式：<input type="radio" name="cxlr" checked="checked" onclick="lrType(1)" /><span style="cursor: pointer;" onclick="lrType(1)">查询录入</span> 
  				&nbsp;&nbsp;
  				<input type="radio" name="sglr" onclick="lrType(0)" /><span style="cursor: pointer;" onclick="lrType(0)">手工录入</span> 
  	</div>
  	<table class="tableStyle" formMode="line">
  		<tr>
  			<td>数据录入人：</td>
  			<td>${user.userName}（${user.userCode}）</td>
  			<td>当前储位容量：</td>
  			<td style="font-weight: bold;"><span id="show_used">${setting.syl}</span>&nbsp;/&nbsp;${setting.zrl}</td>
  		</tr>
  	</table>
  	
    <table border="1" id="cxlhy" align="center" class="tableStyle" formMode="line">
    	<tr>
    		<th colspan="6">六合一信息查询</th>
    	</tr>
    	<tr>
    		<td>号牌号码：</td>
    		<td width="5%"><select name="hpzl_sel" selectedValue="" data='${vs:hpzlSelect()}' boxHeight="200"></select></td>
    		<td ><input name="hphm_sel" style="width: 100%;" onkeypress="autoEnter('hphm',this,event)" value="${session.user.hpqyshi}" maxlength="6"/></td>
    		<td width="5%"><button type="button" onclick="checkSino('hphm',this)" id="find_hphm"><span class="icon_find">查询</span></button></td>
    		<td width="5%"><button type="button" onclick="clean('hphm_sel')"><span class="icon_clear">清空</span></button></td>
    		<td width="10%"></td>
    	</tr>
   
    	<tr>
    		<td>六合一流水号：</td>
    		<td colspan="2"><input name="lsh_sel" style="width: 100%;" onkeypress="autoEnter('lsh',this,event)"/></td>
    		<td><button type="button" onclick="checkSino('lsh',this)" id="find_lsh"><span class="icon_find">查询</span></button></td>
    		<td><button type="button" onclick="clean('lsh_sel')"><span class="icon_clear">清空</span></button></td>
    		<td></td>
    	</tr>
    	<!--
    	<tr>
    		<td>档案编号：</td>
    		<td></td>
    		<td><input name="dabh_sel" style="width: 100%;" onkeypress="autoEnter('dabh',this,event)"/></td>
    		<td><button type="button" onclick="checkSino('dabh',this)" id="find_dabh"><span class="icon_find">查询</span></button></td>
    		<td><button type="button" onclick="clean('dabh_sel')"><span class="icon_clear">清空</span></button></td>
    	</tr>
    	-->
    	
    </table>
    
    <!-- 显示信息 -->
    <table align="center" class="tableStyle" formMode="line">
    	<tr>
    		<td colspan="4"><strong><font color="red" id="msg_error"></font><font color="green" id="msg_ok"></font></strong></td>
    	</tr>
    </table>
    
	<!-- 录入显示信息 -->
    <table align="center" id="lr_show" class="tableStyle" formMode="line">
    	<tr>
    		<th colspan="4">机动车档案信息查询结果简要显示</th>
    	</tr>
    	<tr>
    		<td><span class="star">*</span>号牌种类：</td><td><select name="show_hpzl" selectedValue="" data='${vs:hpzlSelect()}' boxHeight="200"></td>
    		<td><span class="star">*</span>号牌号码：</td><td><input type="text" name="show_hphm"  maxlength="6"/></td>
    	</tr>
    	<tr>
    		<td><span class="star"></span>六合一流水号：</td><td><input name="show_lsh" style="width: 100%;" maxlength="20"/></td>
    		<td><span class="star">*</span>业务类型：</td><td><select name="show_ywlx" selectedValue="" data='${vs:ywlxSelect()}' boxHeight="200"></select></td>
    	</tr>
    	<tr>
    		<td><span class="star"></span>档案编号：</td><td><input name="show_dabh" style="width: 100%;" maxlength="25"/></td>
    		<td><span class="star">*</span>车辆识别代号：</td><td><input name="show_clsbdh" style="width: 100%;" maxlength="30"/></td>
    	</tr>
    	<tr>
    		<td>所有人：</td><td><input type="text" name="show_syr" style="width: 100%;"/></td>
    		<td>备注：</td><td><input name="show_bz" style="width: 100%;"/></td>
    	</tr>
    	<tr>
    		<td>储位编号：</td>
    		<td id="show_cwbh_flush" style="font-weight: bold;">${cwbh}</td>
    		<td></td>
    		<td><button type="button" onclick="flushUsed()" >&nbsp;&nbsp;刷新储位&nbsp;&nbsp;</button></td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			<button id="saveData" type="button" onclick="save()"><span class="icon_save">保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
    		</td>
    	</tr>
    </table>
    <!-- <button onclick="cancel('cwgl/stoGet-table.action')">返回</button> -->
    
    <!-- 打印信息 -->
    <hr />
    <table id="print" style="display: none;" align="center" class="tableStyle" formMode="line">
    		<tr>
    		<th colspan="4">打印信息</th>
    	</tr>
    	<tr>
    		<td>号牌种类：</td><td id="print_hpzl"></td> 
    		<td>号牌号码：</td><td id="print_hphm"></td>
    	</tr>
    	<tr>
    		<td>档案编号：</td><td id="print_dabh"></td>
    		<td>六合一流水号：</td><td id="print_lsh"></td>
    	</tr>
    	<tr>
    		<td>业务类型：</td><td id="print_ywlx"></td>
    		<td>车辆识别代号：</td><td id="print_clsbdh"></td>
    	</tr>
    	<tr>
    		<td>储位编号：</td><td id="print_cwbh"></td>
    		<td>系统档案编号：</td><td id="print_xtdabh"></td>
    	</tr>
    	<tr>
    		<td colspan="4"><button type="button" id="printBtn" onclick="printInfo()"><span class="icon_print">打印</span></button></td>
    	</tr>
    </table>
    <a name="tag_a" href="" style="display: none;"><span id="tag_click"></span></a>
  </div>
  </div>
  </body>
</html>
