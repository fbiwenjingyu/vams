<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html>
  <head>
    	
  	<%@ include file="/common/meta.jsp"%>
    <title>档案格列表</title>
   
	<script type="text/javascript">
		//提示父页面刷新
		function flushTop(){
			$(top.frmright.document).find("input[name='isflush']").val("1");
		}
	
		$(function(){
			$("#he_tiao").bind("actived",function(e,i){
				nextHe(i+1);
			});
		})
		//查询下一盒
		function nextHe(index){
			var heid = ""+index;
			if(heid.length == 1){
				heid = "0"+new String(heid);
			}else{
				heid = new String(heid);
			}
			var geid=$("input[name='cwbh']").val().substring(0,5);
			location.href="stoget-gotoGe.action?cwbh="+geid+heid;
		}
		
		
		
		//获取一个选择项
		function getOneCheck(show){
			var $ck= $("input[name='ck_tag']:checked");
			if($ck.length<=0){
				top.Dialog.alert("请选择档案！");
				return null;
			}else if($ck.length!=1){
				top.Dialog.alert("只能选择一项"+show+"！");
				return null;
			}else{
				return $ck.val().trim();
			}
		}
		//获取多个选择值，以;分割
		function getManyCheck(){
			var $ck= $("input[name='ck_tag']:checked");
			if($ck.length<=0){
				top.Dialog.alert("请选择档案！");
				return null;
			}
			var retVal="";
			for(var i=0;i<$ck.length;i++){
				var oc=$ck[i];
				retVal+=oc.value.trim()+";";
			}
			return retVal;
		}
		
		//==============================================================
		/**查看详细信息*/
		function detailInfo(show){
			var val=getOneCheck(show);
			if(null==val){
				return;
			}
			var diag = new top.Dialog();
			diag.Title = "档案详细信息";
			diag.URL = "cwgl/stoget-detailInfo.action?xtdabh="+val;
			diag.ShowButtonRow=true;
			diag.ShowOkButton=false;
			diag.Height=500;
			diag.CancelButtonText=" 关 闭 ";
			diag.show();			
			
		}
		
		/**查看图片*/
		function imgInfo(show){
			var val=getOneCheck(show);
			if(null==val){
				return;
			}
			var diag = new top.Dialog();
				diag.Title = val+"扫描页信息"; 
				diag.URL = "arcBaseInfo!getPicsByXtdabh.action?selectType=all&xtdabh="+val; 
				diag.Width=1300;
				diag.Height=550;
				diag.ShowMaxButton=false;
				diag.ShowMinButton=false;
				diag.ShowButtonRow=true;
				diag.ShowOkButton=false;
				diag.CancelButtonText="&nbsp;关&nbsp;&nbsp;闭&nbsp;";
				diag.show();
		}
		
		
		/**修改信息*/
		function modifyInfo(show){
			var val=getOneCheck(show);
			if(null==val){
				return;
			}
			var diag = new top.Dialog();
			diag.Title = "修改档案信息";
			diag.URL = "cwgl/stoget-toModifyInfo.action?xtdabh="+val;
		//	diag.ShowButtonRow=true;
	//		diag.ShowOkButton=false;
			diag.Height=550;
	//		diag.CancelButtonText=" 关 闭 ";
			diag.show();	
		}
		
	/**删除信息*/
	function deleteInfo() {
		var val=getManyCheck();
		if(null==val){
				return;
		}
		top.Dialog.confirm("您确定要删除已选择的档案吗？",
				//确定
				function(){
					$.post("stoget-delArc.action", {"xtdabh" : val}, function(data) {
						alert(data[1]);
						if (data[0] == "1") {
							location.reload(false);
							freshTop("stoget-gotoGe.action?cwbh=${cwbh}");
						}
					});
				},
				//取消
				function(){});
	}
	
	/**标签打印*/
	function printInfo(show){
		var xtdabh=getOneCheck(show);
			if(null==xtdabh){
				return;
		}
		var url="../sys/barcode!printArcLabel.action?xtdabh="+xtdabh; //转向网页的地址;
		var name="标签打印页"; //网页名称，可为空;
		var iWidth="420"; //弹出窗口的宽度;
		var iHeight="400"; //弹出窗口的高度;
		//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
		var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
		window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
	}
	
	/**档案迁移*/
	function moveInfo(show){
		var val=getOneCheck(show);
		if(null==val){
			return;
		}
		var url="cwgl/stoget-moveTable.action?xtdabh="+val;
		var diag = new top.Dialog();
			diag.Title = val+"档案迁移";
			diag.URL = url;
			diag.Width=1000;
			diag.Height=550;
			diag.ShowButtonRow=true;
			diag.ShowOkButton=false;
			diag.CancelButtonText="&nbsp;关&nbsp;&nbsp;闭&nbsp;";
			diag.show();
			//提示父页面刷新
			flushTop();
	}
	
	
	//清空后的处理
	function cleanCall(){
		$("input[name='info.cwbh']").val("${cwbh}");
	}
</script>

</head>
  
  
<body>
<input name="cwbh" value="${cwbh}" type="hidden"/>
<!-- 查询  -->
<div id="searchPanel">
<!-- 传递格编号 -->
<form action="stoget-gotoGe.action?cwbh=${cwbh}" method="post" name="queryForm">
	<input id="fuzzy" name="fuzzy" type="hidden" value="${fuzzy}"/> 
	<input name="index" value="${page.index}" type="hidden"/>
	<table id="selector">
		<tr>
			<td>&nbsp;&nbsp;储位编号：</td>
			<td><input name="info.cwbh" type="text" value="${info.cwbh}" style="width: 80px;" maxlength="8"/></td>
			<td>&nbsp;系统编号：</td>
			<td><input name="info.xtdabh" type="text" value="${info.xtdabh}" style="width: 120px;" maxlength="20"/></td>
			<td>&nbsp;流水号：</td>
			<td><input name="info.lsh" type="text" value="${info.lsh}" style="width: 120px;" /></td>
			<td>&nbsp;档案编号：</td>
			<td><input name="info.dabh" type="text" value="${info.dabh}" style="width: 120px;" /></td>
			<td>&nbsp;号牌号码：</td>
			<td><input name="info.hphm" type="text" value="${info.hphm}" style="width: 100px;" maxlength="6"/></td>
			<td>&nbsp;车辆识别代号：</td>
			<td><input name="info.clsbdh" type="text" value="${info.clsbdh}" style="width: 180px;" maxlength="6"/></td>
			<td><button type="button" onclick="checkQuery(0)"><span class="icon_find">精确查询</span></button></td>
			<td><button type="button" onclick="checkQuery(1)"><span class="icon_find2">模糊查询</span></button></td>
			<td><button type="button" onclick="cleanQuery();cleanCall()"><span class="icon_clear">清空</span></button></td>
		</tr>
	</table>
</form>
</div>
	
<!-- 操作栏 -->
<div id="operation" class="box_tool_min">
	<div class="center">
	<div class="right">
		<div class="padding_top5 padding_left10">
			<a href="javascript:void(0);" onclick="detailInfo('查看详细信息')"><span class="icon_view">详细信息</span></a>
			<div class="box_tool_line"></div>
			<a href="javascript:void(0);" onclick="imgInfo('查看图片')"><span class="icon_img">查看图片</span></a>
			<div class="box_tool_line"></div>
			<a href="javascript:void(0);" onclick="printInfo('打印标签')"><span class="icon_code">标签打印</span></a>
			<div class="box_tool_line"></div>
			<a href="javascript:void(0);" onclick="modifyInfo('修改信息')"><span class="icon_edit">修改</span></a>
	 		<div class="box_tool_line"></div>
			<a href="javascript:void(0);" onclick="deleteInfo()"><span class="icon_delete">删除（可多选）</span></a>
			<div class="box_tool_line"></div>
			<a href="javascript:void(0);" onclick="moveInfo('档案迁移')"><span class="icon_reply">档案迁移</span></a>
		</div>
	</div>
	</div>
	<div class="clear"></div>
</div>
	
<div id="scrollContent" class="table_bor">
  	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
	<tr>
		<th></th> 
		<th></th>
		<th>储位编号</th>
		<th>系统编号</th>
		<th>流水号</th>
		<th>档案编号</th>
		<th>业务类型</th>
		<th>号牌种类</th>
		<th>号牌号码</th>
		<th>车辆识别代号</th>
		<th>档案类型</th>
		<th>档案状态</th>
		<th>创建人</th>	
		<th>创建时间</th>
		<th>允许删除</th>
	</tr>
	<c:forEach var="info" varStatus="status" items="${pageList}">
	<tr>
		<td class="ali02"><input type="checkbox" name="ck_tag" value="${info.xtdabh}"/></td>
		<td class="ali02">${status.index+1}</td>
		<td class="ali02">${info.cwbh}</td>
		<td class="ali02">${info.xtdabh}</td>
		<td class="ali02">${info.lsh}</td>
		<td class="ali02">${info.dabh}</td>
		<td class="ali02">${vs:ywlxName(info.ywlx)}</td>
		<td class="ali02">${vs:hpzlName(info.hpzl)}</td>
		<td class="ali02">${info.hphm}</td>
		<td class="ali02">${info.clsbdh}</td>
		<td class="ali02">${info.dalx=="1"?"业务档案":""}${info.dalx=="2"?"历史档案":""}</td>
		<td class="ali02">${vs:daztName(info.dazt)}</td>
		<td class="ali02">${info.cjrmc}（${info.cjr}）</td>
		<td class="ali02"><fmt:formatDate value="${info.cjsj}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
		<td class="ali02"><span class='${info.dazt == allowdel?"icon_ok":"icon_no"}'>&nbsp;</span></td>
	</tr>
	</c:forEach>
	<c:if test="${fn:length(pageList)==0}">
	<tr>
		<td colspan="15" align="center">数据库没有相关数据</td>
	</tr>
	</c:if>
</table>
</div>
	
<!-- 翻页栏 -->
<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
	
</body>
</html>
