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
    <title>档案预入库</title>
    
    <style>
    	.data_div{
    		border: solid #0042ff 2px ; 
    		width: 180px; 
    		height:140px; 
    		float: left;
    		margin:1px 1px 1px 1px;
    		cursor: pointer;
    		padding: 2px 2px 2px 2px;
    		text-align: center;
    	}
    	.data_div span{
    		font-size: 15px;
    	}
    	
    	.data_div:HOVER {
			background-color: pink;
		}
		
		/**录入相同的档案，标机颜色*/
		.orange_data{
			background-color: orange!important;
		}
		.green_data{
			background-color: #b9ffc0!important;
		}
    </style>
    
    <script type="text/javascript"> 
    	/**加载项*/
    	$(function(){
    		setWindow();
    		loadHpzlMap("../");
    		loadYwlxMap("../");
    	});

    	/**页面载入-样式调整*/
    	function setWindow(){
       		//设置数据区域的高度
    		$("#data").height($(window).height()*0.6);
    		//设置整体宽度
    		var wd=$(window).width()*0.98;
    		if(wd<500){
    			$("#main").width(500);
    		}else{
    			$("#main").width(wd);
    		}
    	}
  
		
		//字母数字验证
		var cnw = new RegExp("^[A-Za-z0-9]+$");
		var cn = new RegExp("^[0-9]+$");
		
		//提示信息-红色
		function showOk(msg){
			$("#show_error").html("");
			$("#show_ok").html(msg);
		}
		//提示信息-绿色
		function showError(msg){
			$("#show_ok").html("");
			$("#show_error").html(msg);
		}
		
		var watermk = "请将光标放置于此，然后扫描档案";
		var lrtype=new Map();
		lrtype.put("xtdabh","系统编号");
		lrtype.put("dabh","档案编号");
		lrtype.put("lsh","流水号");
		/**验证档案编号*/
		// 检测类型，检测值
		function checkData(type,val){
			//验证档案编号
    		if(val=="" || val==watermk){
    			showError("请扫描或输入"+lrtype.get(type)+"！");
    			return false;
    		}else if(!cnw.test(val)){
    			showError("请录入正确的"+lrtype.get(type)+"！");
    			return false;
    		}else if(val.length>36){
    			showError("请录入正确长度的编号（"+val+"），已输入长度："+val.length+"");
    			return false;
    		}if($("div["+type+"='"+val+"']").length > 0){//录入重复
    			showError("录入了重复的档案："+val);
    			//标记档案
    			signArc(type, val);
    			locateArc(type,val);
    			return false;
    		}
			return true;
		}
		
		/**锚点跳转，定位
		*@param type 要定的位标签名称
		*@param val 要定的位标签值
		*/
		function locateArc(type,val){
//			document.getElementById(id).scrollIntoView();
			var $dd=$("div["+type+"='"+val+"']");
			if($dd.length>0){
				$dd[0].scrollIntoView();
			}
		}
		
		/**标记档案：改变数据div颜色*/
		function signArc(type,val){
			$("div["+type+"='"+val+"']").addClass("orange_data");
		}
		
		/**标记档案：改变数据div颜色*/
		function signArcByLr(type,val){
			$("div["+type+"='"+val+"']").addClass("green_data");
		}
		
		/**取消所有档案的标记*/
		function unsignAll(){
			$("div.data_div").removeClass("orange_data");
			$("div.data_div").removeClass("green_data");
		}
		
		
    //添加的div
		var ddiv="<div class='data_div' xtdabh='#D1' dabh='#D2' onclick='showMessage(this)'></div>";
		var dspan="<span>"
				  +"<span>#1</span><br />"
				  +"<span>#2</span><br />"
				  +"<hr />"
				  +"<span>#3</span><br />"
				  +"<span>（#4）</span><br />"
				  +"<span>#5</span><br />"
				  +"<span>#6</span><br />"
				  +"<span id='dbtn'><span>"
				  +"</span>";
    	/**添加的btn*/
    	var dbtn="<button type='button' onclick='removeArc(this)'><span class='icon_delete'>移除</span></button>";
    	/**创建一个数据div*/
    	function createDataDiv(arc){
    		//创建div
    		var fdiv=ddiv.replace("#D1", arc.xtdabh).replace("#D2", arc.dabh);
    		//创建span
    		var fspan=dspan.replace("#1", arc.xtdabh)
			    		   .replace("#2", arc.cwbh)
			    		   .replace("#3", arc.hphm)
			    		   .replace("#4", hpzl_map.get(arc.hpzl))
			    		   .replace("#5", ywlx_map.get(arc.ywlx))
			    		   .replace("#6", arc.dabh);
    		var $div=$(fdiv).append(fspan);
    		//创建按钮
    		var $btn=$(dbtn);
    		 	$btn.render();
//    		$div.render();
    		//添加btn
    		$div.find("#dbtn").append($btn);
    		//追加到数据区
    		$("#datd").append($div);
    		
    	}
    	
    	/**验证录入的档案是否存在*/
    	function checkDiff(type,val){
    		var $arc=$("["+type+"='"+val+"']");
    		if($arc.length>0){
    			showError("该档案已录入："+val);
    			//改变该档案颜色
    			signArc(type, val);
    			//跳转到该位置
    			locateArc(type, val);
    		}
    	}
    	
    	//===========================================================================================================
    	
    	//写入档案数量
		function selectNum(){
			var num=$("div.data_div").length;
			$("#select_num").html(num);
			return num;
		}
		//获取已选择 系统档案编号字符串，以";"分隔
		function getArcIds(){
			var rets="";
			var arcs=$("div.data_div");
			for(var i=0;i<arcs.length;i++){
			   if(i!=arcs.length-1){
    			rets+=(arcs[i].xtdabh+";");
    		   }else{
    		  	rets+=arcs[i].xtdabh;
    		   }
			}
			return rets;
		}
		
		
		//录入占用标记
		var lr_used=false;
		
		/**开始录入 
		*@param name 录入名称，例如：dabh
		*/
		function beginLR(name){
			//如果录入状态未被占用，则开始
			if(!lr_used){
				//设置开始标记
				lr_used = true;
				//取消所有标记的档案
				unsignAll();
				//将查询区域按钮禁用
				$("#scan").find("button").attr("disabled",true);
				//将目标按钮变成loading
				$("button[name='"+name+"']").find("span").attr("class","icon_loading");
				//提示
				showOk("正在处理，请稍后...");
				return true;
			}else{
				return false;
			}
		}
		/**结束录入*/  
		//icon_edit   
		//icon_add
		function endLR(name,btn_class){
			//如果处于录入状态，则可以结束，否则不作操作
			if(lr_used){
				$("#scan").find("button").removeAttr("disabled"); 
				
				$("button[name='"+name+"']").find("span").attr("class",btn_class);
				//还原占用标记
				lr_used = false;
			}
			
			return true;
		}
    	
		
		/**删除档案*/
    	function removeArc(tag){
			//阻止冒泡
    		stopBubble(event);
			//删除元素
    		$(tag).parent().parent().parent().remove();
    		//统计写入档案数量
    		selectNum();    		
    	}
		
		
		/**自动：添加档案*/
		function addArc(inp,isEnter){
			//当回车事件响应式进行操作：
			if(checkEnter(event) || isEnter){
				//开始录入，如果可以录入
				if(!beginLR(inp.name)){
					return;
				}
				//验证数据
				if(!checkData(inp.name,inp.value)){
					//结束录入状态
					endLR(inp.name, "icon_add");
					//清空输入框，还原焦点
					$("input[name='"+inp.name+"']").val("").focus();
					return;
				}
				//处理
				ajaxData($(inp), inp.name,inp.value);
			}
		}
		
    	/**手动：点击录入*/
    	function addArcBtn(btn){
    		var inp=$("input[name='"+btn.name+"']")[0];
    		addArc(inp,true);
    	}
		
    	
    	/**
    	* 处理添加的档案
    	* @param 当前输入框
    	* @param 录入类型
    	* @param 录入值
    	*/
    	function ajaxData($inp,type,arc){
    		//查询档案
    		$.post("stoins-findArc.action",{"applyIds":arc,"type":type},function(data){
    			unsignAll();
				if(data[0]=="1"){
					for(var i=0;i<data[1].length;i++){
						//创建档案
			    		createDataDiv(data[1][i]);
						//标机颜色
						signArcByLr(type, arc);
						//锚点跳转
			    		locateArc(type,arc);
					}
		    		showOk("档案："+arc+" 可入库，数量："+data[1].length+"份。");//消息提示
				}else{
					showError(data[1]+" ！");//消息提示
				}
				
				//统计写入档案数量
	    		selectNum();
	    		//清空输入，还原焦点
				$inp.val("").focus();
		   		//录入完毕...
		   		//延迟效果
				setTimeout(function(){
					endLR(type, "icon_add");
				}, 100);
    		});
    	
    	}  	
    	
    	
    	/**显示详细信息*/
    	function showMessage(div){
    		var url="swgl/stoins-showArc.action?applyIds="+div.xtdabh;
			dialog = new top.Dialog();
			dialog.URL=url;
			dialog.Width=700;
			dialog.Height=500;
			dialog.ShowButtonRow=true;
			dialog.ShowOkButton=false;
			dialog.CancelButtonText=" 关 闭 ";
			dialog.show();
    	}
    	
    	//签收后 打印数据
    	var printData ;
    	
      	/**签收*/
    	function checkin(btn){
    		//验证是否填写申请人信息
 //   		var check=$("#apply_msg").validationEngine({returnIsValid: true});
   // 		if(!check){
    //			showError("请填写正确的申请信息！");
    	//		return;
    	//	}
    		//验证是否有可入库的数据
			if(selectNum()==0){
				showError("没有录入档案！");
				alert("没有录入档案！");
				return;
			}
			
			//开始签收...
			//将按钮设置为不可用
    		beginLR(btn.name);
			
			//获取选中的档案编号字符串，以“;”分隔
			var apsids=getArcIds();
//			var apname=$("input[name='applyName']").val();
			
			$.post("stoins-applyIn.action",{"applyIds":apsids},function(data){
			    //alert(data[0]);
				//签收完成
				if(data[0]=="1"){
					printData=data[1];
					//签收完成
		    		alert("提交成功！");
		    		//设置批次号
		    		$("#batchNum").html(data[1].pch);
		    		//设置签收数量
		    		$("#signTotal").html(data[1].sl);
		    		
		    		$("#scan").slideUp(200);
		    		$("#data").slideUp(200);
		    		$("#check_in").slideUp(200);
		    		$("#finish").slideDown(200);
				}else if(data[0]=="2"){
					//显示错误原因
					showError(data[1]);
					alert(data[1]);
				}else if(data[0]=="3"){
					showError(data[1]);
					alert(data[1]);
					//标记档案
					signArc("xtdabh", data[2]);
					//锚点跳转
					locateArc("xtdabh", data[2]);
				}
				//处理完成
				endLR(btn.name, "icon_edit");
			});
    	}
    	
    	//============================================================
   
    	/**打印签收单*/
    	function printSign(){
    		if(null==printData){
    			alert("数据异常，无法打印！");
    			return;
    		}
    		var url="stoins-printRkd.action?pch="+printData.pch+"&sl="+printData.sl+"&name="+printData.sqrid+"&time="+printData.time;
    		window.open(url,'打印入库签收单','width=400,height=450');
    	}
    	
    	
    	/**继续入库*/
    	function storagein(){
    		freshTop("stoins-form.action");
    	}
    	
    </script>
    
    
</head>


<body onresize="setWindow()">
<!-- 锚点跳转 
<a name="tag_a" href="" style="display: none;"><span id="tag_click"></span></a>
-->


<div id="scrollContent">

<div id="main" class="box1"  style="width: 800px;margin: 0 auto;">
<div>

 	<!-- 申请 -->
 	<div>
	     <table id="apply_msg" class="tableStyle">
		 	<tr>
		 		<th colspan="2"><span style="font-size: 20px;font-weight: bold;">档案预入库</span></th>
		 	</tr>
			<tr>
			<!-- 
				<td width="10%" class="ali03">入库申请人：</td><td width="10%"><input name="applyName" type="text" class="validate[required,length[1,10]]"/><span class="star">*</span></td>
			-->
				<td width="10%" class="ali03">操作人：</td>
				<td width="10%">${session.user.userName}（${session.user.userCode}）</td>
			</tr>
		 </table>
	</div>

	<!-- 操作显示 -->
	<table id="scan" class="tableStyle">
		<tr>
			<td width="30%" class="ali03">扫描或输入【系统编号】：</td>
			<td width="40%" class="ali02"><input id="inp_xtdabh" type="text" name="xtdabh" style="width: 98%;" watermark="请将光标放置于此，然后扫描档案" onkeypress="addArc(this,false)"/></td>
			<td width="30%" class="ali01"><button id="load_xtdabh" type="button" name="xtdabh" onclick="addArcBtn(this)"><span class="icon_add">录入</span></button></td>
		</tr>
		<tr>
			<td width="30%" class="ali03">扫描或输入【储位编号】：</td>
			<td width="40%" class="ali02"><input id="inp_cwbh" name="cwbh" type="text" style="width: 98%;" watermark="请将光标放置于此，然后扫描档案" onkeypress="addArc(this,false)"/></td>
			<td width="30%" class="ali01"><button id="load_cwbh" type="button" name="cwbh" onclick="addArcBtn(this)"><span class="icon_add">录入</span></button></td>
		</tr>
		<tr>
			<td width="30%" class="ali03">扫描或输入【档案编号】：</td>
			<td width="40%" class="ali02"><input id="inp_dabh" name="dabh" type="text" style="width: 98%;" watermark="请将光标放置于此，然后扫描档案" onkeypress="addArc(this,false)"/></td>
			<td width="30%" class="ali01"><button id="load_dabh" type="button" name="dabh" onclick="addArcBtn(this)"><span class="icon_add">录入</span></button></td>
		</tr>
		<tr>
			<td width="30%" class="ali03">扫描或输入【流水号】：</td>
			<td width="40%" class="ali02"><input id="inp_lsh" name="lsh" type="text" style="width: 98%;" watermark="请将光标放置于此，然后扫描档案" onkeypress="addArc(this,false)"/></td>
			<td width="30%" class="ali01"><button id="load_lsh" type="button" name="lsh" onclick="addArcBtn(this)"><span class="icon_add">录入</span></button></td>
		</tr>
		<tr>
			<!-- 提示信息 -->
			<td colspan="3" class="ali02" id="show_msg"><font id="show_ok" color="green"></font><font id="show_error" color="red"></font></td>
		</tr>
	</table>
	
	
	<!-- 
	<div class="data_div" xtdabh="xxxxxxxxxx" dabh="xxxxxxx">
	<span>
			<span>系统档案编号 </span><br />
			<span>储位编号</span><br />
			<hr />
			<span>号牌号码</span> <br /> 
			<span>（小型汽车）</span><br />
			<span>初次申领</span><br />    
			<span>档案编号</span><br />
			<button type='button' onclick="removeArc(this)"><span class='icon_delete'>移除</span></button>
	</span>
	</div>
 	-->
 	<!-- 档案选择 -->
	<div id="data" style="overflow: auto;">
		<table id="show_arc" class="tableStyle">
			<tr id="datag">
				<td id="datd">
					
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 提交区域 -->
	<table id="check_in" class="tableStyle" formMode="line">
		<tr>
				<td width="50%">预入库档案数量：</td>
				<td id="select_num">0</td>
		</tr>
		<tr>
				<td colspan="2"><button name="smt" type="button" onclick="checkin(this)"><span class="icon_edit">提 交</span></button></td>
		</tr>
	</table>
	
	<!-- 打印签收单，入库 -->
	<div id="finish" style="display:none;">
		<table class="tableStyle" formMode="line">
			<tr>
				<th colspan="2">提交成功，请核对数量！</th>
			</tr>
			<tr>
				<td width="50%">批次号：</td><td id="batchNum"></td>
			</tr>
			<tr>
				<td width="50%">档案数量：</td><td id="signTotal"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="button" onclick="printSign()"><span class="icon_print">打印签收单</span></button></td>
			</tr>
		</table>
		<table id="rkr" class="tableStyle" formMode="line">
			<tr>
				<td colspan="2"></td>
			</tr>
			<tr> 
				<td colspan="2"><button type="button" onclick="storagein()"><span class="icon_reply">继续申请</span></button></td>
			</tr>
		</table>
	</div>
	
</div>


</div>
</div>
</body>
</html>
