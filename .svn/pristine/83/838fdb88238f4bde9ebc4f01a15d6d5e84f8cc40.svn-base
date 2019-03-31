<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="vs" uri="/vams_libs" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/common/meta.jsp"%>
		<title>核对入库列表</title>
<script>
	$(function(){
//		var data=top.frmright.document.getElementById("finishData").innerHTML;
//		$("#data").html(data);
//		$("#data").css("text-align","center");
//		freshTop("swgl/checkin-query1.action");
		var dh=$(document).height();
		$("#dmain").height(dh*0.8);
	});
	
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
	
	//自动核对
	function autoEnter(tid,inp,event){
		//如果输入的是回车，则查询
		if(checkEnter(event)){
			//如果选择的输入框的name与查询的目标相同
			if(inp.name == tid){
				check(inp);
				//清空并还原焦点
				$(inp).val("").focus();
			}
		}
	}
	
	//核对 
	function check(inp){
		if(inp.value=="" || inp.value.trim()==""){
			showError("请输入核对信息！");
			return;
		}
		var tr=$("#"+inp.value).parent();
		tr.css("background-color","orange").attr("id","ckd");
		//跳转锚点
		locateA("id", inp.value);
		//如果核对成功，增加数量。
		if(tr.length==1){
			//写入父页面核对的id
			top.frmright.addCheckIds(tr.attr("daid"));
			//更新核对数量
			$("#checknum").html($("tr[id='ckd']").length);
			showOk("核对成功："+inp.value);
		}else{
			showError("核对失败，未找到："+inp.value);
		}
	}
	
	/**档案迁移*/
	function moveInfo(xtdabh,btn){
		var url="cwgl/stoget-moveTable.action?xtdabh="+xtdabh;
		var diag = new top.Dialog();
			diag.Title = xtdabh+"档案迁移";
			diag.URL = url;
			diag.Width=1000;
			diag.Height=550;
			diag.ShowButtonRow=true;
			diag.ShowOkButton=false;
			diag.CancelButtonText="&nbsp;关&nbsp;&nbsp;闭&nbsp;";
			diag.show();
	}
	
	
	//刷新迁移过的档案
	function flsuhInfo(newcwbh,xtdabh){alert($("td[xtdabh='"+xtdabh+"']")[0]);
		$("td[xtdabh='"+xtdabh+"']").html(newcwbh);
//		$("button[name='"+oldcwbh+"']").attr("name",newcwbh);
	}
	
	//打印标签
	function printInfo(xtdabh){
		var url="../sys/barcode!printArcLabel.action?xtdabh="+xtdabh;
		window.open(url,'打印条码','width=350,height=400');
	}
	
	/**打印标签*/
	function printInfo(xtdabh){
		if(null != xtdabh){
			var url="../sys/barcode!printArcLabel.action?xtdabh="+xtdabh; //转向网页的地址;
			var name="标签打印页"; //网页名称，可为空;
			var iWidth="400"; //弹出窗口的宽度;
			var iHeight="400"; //弹出窗口的高度;
			//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
			var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
			window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
		}
	}
	
</script>
	</head>
	
	<body>

	<div id="scrollContent">
	
	<table class="tableStyle" formMode="view">
		<tr>
			<td>批次号：</td><td width="30%">${pch}</td>
			<td>档案数量：</td><td width="30%"><span style="font-size: 20px;">${fn:length(pageList)}</span></td>
			<td>已核对：</td><td width="30%"><span id="checknum" style="font-size: 20px;">0</span></td>
		</tr>
		<tr>
			<td colspan="6" class="ali02" style="height: 20px;"><font id="show_ok" color="green"></font><font id="show_error" color="red"></font></td>
		</tr>
	</table>
	
	<!-- 检索条件 -->
<div >
	<input name="index" type="hidden" value="${page.index}" /><!-- 当前页input -->
	<input id="fuzzy" name="fuzzy" type="hidden" value="${fuzzy}"/> 
	<input name="cwbh" value="${cwbh}" type="hidden"/>
	<input name="stoinfo.pid" value="${cwbh}" type="hidden"/>
	<input name="hpzl" value="${hpzl}" type="hidden"/>
	<input name="stoinfo.hpzl" type="hidden" value="${hpzl}"/>
	<table id="selector">
		<tr>
			<td style="color: red;">*请输入信息，然后点击回车或者扫描档案*&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>&nbsp;系统编号：</td>
			<td><input name="xtdabh" onkeypress="autoEnter('xtdabh',this,event)" /></td>
			<td>&nbsp;&nbsp;档案编号：</td>
			<td><input name="dabh" onkeypress="autoEnter('dabh',this,event)" /></td>
			<td>&nbsp;&nbsp;流水号：</td>
			<td><input name="lsh" onkeypress="autoEnter('lsh',this,event)"/></td>
<!-- 		<td><button type="button" onclick="check()"><span class="icon_find">核对</span></button></td>   -->
			<td><button type="button" onclick="cleanSelector()"><span class="icon_clear">清空</span></button></td>
		</tr>
	</table>
</div>
	
	<div id="dmain" style="overflow: auto; " >
		<table class="tableStyle">
		<tr>
			<th width="30"><span>序号</span></th>
			<th class="ali02"><span>系统编号</span></th>
			<th class="ali02"><span>档案编号</span></th>
			<th class="ali02"><span>流水号</span></th>
			<th class="ali02"><span>储位编号</span></th>
			<th class="ali02"><span>业务类型</span></th>
			<th class="ali02"><span>号牌号码</span></th>
			<th class="ali02"><span>机动车序号</span></th>
			<th class="ali02"><span>车辆识别代码</span></th>
			<th class="ali02"><span>行政区划</span></th>
			<th class="ali02"><span>申请人</span></th>
			<th class="ali02"><span>申请时间</span></th>
			<th class="ali02"><span>操作</span></th>
		</tr>
		<tbody>
			<c:forEach var="info" items="${pageList}" varStatus="status">
			<tr class="ali02" daid="${info.xtdabh}">
				<td class="ali02">${status.index+1}</td>
				<td class="ali02" id="${info.xtdabh}" >${info.xtdabh}</td>
				<td class="ali02" id="${info.dabh}">${info.dabh}</td>
				<td class="ali02" id="${info.lsh}">${info.lsh}</td>
				<td class="ali02" xtdabh="${info.xtdabh}">${info.cwbh}</td>
				<td class="ali02">${vs:ywlxName(info.ywlx)}</td>
				<td class="ali02">${info.hphm}（${vs:hpzlName(info.hpzl)}）</td>
				<td class="ali02">${info.xh}</td>
				<td class="ali02">${info.clsbdh}</td>
				<td class="ali02">${vs:xzqhName(info.xzqh)}</td>
				<td class="ali02">${vs:userName(info.sqrid)}（${info.sqrid}）</td>
				<td class="ali02"><fmt:formatDate value="${info.sqsj}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
				<td class="ali02"><button name="${info.xtdabh}" type="button" onclick="printInfo('${info.xtdabh}',this)"><span class="icon_code">打签</span></button>
								   <button name="${info.xtdabh}" type="button" onclick="moveInfo('${info.xtdabh}',this)"><span class="icon_reply">迁移</span></button></td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
	</div>
	</body>

</html>
