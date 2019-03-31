<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="shortcut icon" href="<%=basePath%>favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>机动车档案综合管理系统</title>
<!--框架必需start-->
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/framework.js"></script>
<link href="${pageContext.request.contextPath}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${pageContext.request.contextPath}/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/nav/spliter.js"></script>
<script type="text/javascript" src="libs/js/bsFormat.js"></script>
<!--  调用样式-->
<link rel="stylesheet" type="text/css" href="<%=path %>/system/layout/skin/style.css"/>
<script type="text/javascript" src="<%=path %>/system/layout/js/layout_main.js"></script>
<!--框架必需end-->

<!--引入弹窗组件start-->
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libs/js/popup/dialog.js"></script>
<!--引入弹窗组件end-->


<script type="text/javascript">

	function show(obj){
		var a = $("#"+obj).val();  
		if(a == "1"){
			$("#"+obj).val(obj);
			$("#img"+obj).attr("src","system/layout/skin/btn_right.gif");   
			$("ul."+obj).each(function () {    
				$(this).hide();
		  	});
		}else{
			$("#"+obj).val("1");  
			$("#img"+obj).attr("src","system/layout/skin/btn_down.gif");   
			$("ul."+obj).each(function () { 
				$(this).show();
		  	});
		}
	}
	
	function showDownload(){
		top.Dialog.open({URL:'system/downloadClient.jsp',Title:'客户端下载'});
	}
	
	/**用户设置*/
	function setUserParam(){
		 top.Dialog.open({URL:"system/default/user_param_set.jsp",Title:"用户设置1",Width:"400px;",Height:"300px;"});
	}
</script>
</head>       
<body onload="initRes();">
<input id="ii" type="hidden"/>

<div id="mainFrame">
<!--头部与导航start-->
<div id="hbox">
	<div id="bs_bannercenter">
		<div id="bs_bannerleft">
			<div id="bs_bannerright">
			     <div class="bk_ul">
	                 <ul>
	                 	 <li>版本号:<font>&nbsp;&nbsp;BN - 2.1.150101</font></li>
	                 	 <li>号牌区划：${session.user.hpqysheng }${session.user.hpqyshi }</li>
						 <li><img src="system/login/img/user.gif"/>用户:<a href="javascript:void(0)" onclick="setUserParam()" title="点击设置用户参数"><font style="color:yellow;">&nbsp;&nbsp;${user.userName}（${user.userCode}）</font></a></li>
			             <li><img src="system/layout/skin/1_15.gif"/><a href="javascript:void(0)" title="点击修改密码" style="color:white" onclick="updatePwd()">修改密码</a></li>
			             <li><img src="libs/icons/disk.gif"/><a href="javascript:showDownload();" style="color: yellow">下载客户端</a></li>
			             <li>
			                <img src="system/layout/skin/1_10.png"/> 日期：
							<script>
								var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
								var now = new Date();
							    var year=now.getFullYear();
								var month=now.getMonth()+1;
								var day=now.getDate();
							    var currentime = year+"&nbsp;-&nbsp;"+month+"&nbsp;-&nbsp;"+day+"&nbsp;&nbsp;"+weekDayLabels[now.getDay()];
								document.write(currentime);
							</script>
			             </li>
			             <li><img src="libs/icons/exit.gif"/><a href="javascript:void(0)" style="color:white" onclick="exit()">退出</a></li>
		              </ul> 
	              </div>
	              <div class="top_tbar" id="NewsTop">
			          <ul>
						<c:forEach items="${list_data }" var="res">
			             <li class="topC0" ><a href="javascript:void(0)"  target="frmleft"  onclick="refreshRes('${res.resid}','${res.resname }');return false;">${res.resname }</a></li>
						</c:forEach>
			          </ul>
			          <SCRIPT type="text/javascript" src="system/layout/js/layout_main.js"></SCRIPT>
			      </div>
			</div>
		</div>
	</div>
	
</div>
<!--头部与导航end-->

<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
	<tr>
		<!--左侧区域start-->
		<td id="hideCon" class="ver01 ali01">
							<div id="lbox">
								<div id="lbox_topcenter">
								<div id="lbox_topleft">
								<div id="lbox_topright">
								</div>
								</div>
								</div>
								<div id="lbox_middlecenter">
								<div id="lbox_middleleft">
								<div id="lbox_middleright">
										<div id="bs_left">
											<div class="ywgl_top" id="res">
												<c:forEach items="${list_data }" var="res" begin="0" end="0">
												     ${res.resname }<input type="hidden" id="resid" value="${res.resid }" />
												</c:forEach>
											</div>
											<div class="ywgl_mid">
											 	<dl class="sliderbox" id="slider2"> </dl>
											</div>
					                    </div>
							            <!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
								</div>
								</div>
								</div>
								<div id="lbox_bottomcenter">
								<div id="lbox_bottomleft">
								<div id="lbox_bottomright">
									<div class="lbox_foot"></div>
								</div>
								</div>
								</div>
							</div>
		</td>
		<!--左侧区域end-->
		
		<!--分隔栏区域start-->
		<td class="spliter main_shutiao" targetId="hideCon" beforeClickTip="关&nbsp;闭" afterClickTip="打&nbsp;开" beforeClickClass="bs_leftArr" afterClickClass="bs_rightArr">
		</td>
		<!--分隔栏区域end-->
		
		<!--右侧区域start-->
		<td class="ali01 ver01"  width="100%">
							<div id="rbox">
								<div id="rbox_topcenter">
								<div id="rbox_topleft">
								<div id="rbox_topright">
								</div>
								</div>
								</div>
								<div id="rbox_middlecenter">
								<div id="rbox_middleleft">
								<div id="rbox_middleright">
									<div id="bs_right">
									      <IFRAME scrolling='no' width='100%' height='100%' frameBorder='0' id='frmright' name='frmright' src='system/layout/open.jsp'  allowTransparency='true'></IFRAME>
									</div>
								</div>
								</div>
								</div>
								<div id="rbox_bottomcenter" >
								<div id="rbox_bottomleft">
								<div id="rbox_bottomright">

								</div>
								</div>
								</div>
							</div>
		</td>
		<!--右侧区域end-->
	</tr>
</table>

<!--尾部区域start-->
<div id="fbox">
	<div id="bs_footcenter">
	<div id="bs_footleft">
	<div id="bs_footright">
		
	</div>
	</div>
	</div>
</div>
</div>
<!--尾部区域end-->

<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->


</body>
</html>
