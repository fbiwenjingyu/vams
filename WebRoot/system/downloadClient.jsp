<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户端下载</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<script type="text/javascript" src="${pageContext.request.contextPath}/tjcx/dagdtz/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/ui/jquery.ui.effect-bounce.js"></script>
	
	<script type="text/javascript">
		$(function() {
			// $.post("wjcy_dytm!insertWjbh.action");
			// run the currently selected effect
			function runEffect() {
				// get effect type from
				$( "#effect" ).show("bounce", null, 500, null );
			};
	
			//callback function to bring a hidden box back
			function callback() {
				/*setTimeout(function() {
					$( "#effect:visible" ).removeAttr( "style" ).fadeOut();
					//$("#showCode").removeAttr("src");
				}, 10000 );*/
			};
	
			// set effect from select menu value
			$( "#button" ).click(function() {
				
				$("#showCode").attr("src","wjcy_dytm!write39CodeImage3.action?bs=3");
				runEffect();
				return false;
			});
	
			$( "#effect" ).hide();
		});
		
		$(window).unload( function () { 
			$("#showCode").removeAttr("src");
		} );
	</script>
	
  </head>
  
  <body>
	  	&bull; <a href="download/scanClient_v2.1.zip">扫描客户端</a>
	  	<br/><br/>
	  	<a href="download/scanClient_v2.1.zip">扫描客户端_v2.1</a>
	  	<br/><br/>

	  	&bull; <a href="download/RemoteCheck.apk">PAD外检拍照客户端</a>
	  	<br/>
	  	<br/>
	  	<span style="color: red;">&bull; 扫扫下载</span>
	  	<br/>
	  	<img alt="" width="100px" height="100px" src="download/qrcode.png">
	  	<br/><br/>
	  	
  </body>
</html>
