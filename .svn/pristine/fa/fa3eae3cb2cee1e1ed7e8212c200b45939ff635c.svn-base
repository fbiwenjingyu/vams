<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String str= getServletContext().getContextPath();
%>

<!DOCTYPE HTML >
<html>
  <head>
   <title>查看图片 </title>

  <link rel="stylesheet" type="text/css" href="<%=path %>/libs/picView.css" />
<script src="<%=path %>/libs/jquery-latest.js"></script>
<script src="<%=path %>/libs/rotate.js"></script>
<script type="text/javascript" language="javascript">
   

    //向右旋转
    function rotateRight(angle) {
        var curid = $("#curid").val();
        var id = "ys"+curid;
      rotate(id, angle == undefined ? 90 : angle);
    }
    //向左旋转
    function rotateLeft(angle) {
    	 var curid = $("#curid").val();
         var id = "ys"+curid;
         
      rotate(id, angle == undefined ? -90 : -angle);
    }
  </script>
</head>

<body style="text-align:center;">

<div >
<input type="hidden" name="curid" id="curid" value="0"/>
 <a href="javascript:void(0)" style="font-weight: bold; font-size: 16px; text-decoration: none;" 
      onclick="rotateLeft(90);"><img src="<%=str %>/libs/icons/png_28/icons_left.png"/></a>
<a href="javascript:void(0)" style="font-weight: bold; text-decoration: none; font-size: 16px;"
      onclick="rotateRight(90);"><img src="<%=str %>/libs/icons/png_28/icons_right.png"/></a> 
   <br>   </div>
<div id="box-lanrenzhijia" >
	<div class="bigPic">
		<a class="btnleft" href="javascript:;"></a><a class="btnright" href="javascript:;"></a>
		<div class="info">
			<table>
			  <tbody>
			  <tr>
				 <c:forEach 	var="pic" items="${ycPic_list}" varStatus="status">
					<td align="center"><input type="hidden" value="${pic.id}" id="cur${status.index}"/>
	 				<div align="center"><img align="center" id="ys${status.index}" height="818" border="0" onmousewheel="return bigimg(this)" width="818" src='<%=str %>/sys/base64ToImage!getBase64ToImagePath.action?picpath=${pic.picpath}'  /></div></td>
	    		</c:forEach>
			</tr>
			  </tbody>
			</table>
		</div>
	</div>
<script type=text/javascript>
		var curid = 0;

		$(".bigPic a.btnleft").click(function(){
			
			curid--;
			if(curid<0){
				alert("已经是第一张了！");
				curid=0;
			}else{
				$(".bigPic .info").animate({"scrollLeft":curid*818});
				var $ifr=$(top.document).find("iframe[id!='frmright']");
				for(var i=0;i<$ifr.length;i++){//循环取出页面上的iframe，查找含有storageNumber的iframe
					var ifr=$($ifr[i].contentWindow.document);
					var curPicpath = $("#cur"+curid).val();
					ifr.find('#pic'+curPicpath).removeClass("out");		
				}
			}
			$("#curid").val(curid);
		})
		$(".bigPic a.btnright").click(function(){
			curid++;
			if(curid>$(".bigPic .info tr").find("td").size()-1){
				alert("已经是最后一张了！");
				curid=$(".bigPic .info tr").find("td").size()-1;   
			}else{
				$(".bigPic .info").animate({"scrollLeft":curid*818});
				var $ifr=$(top.document).find("iframe[id!='frmright']");
				for(var i=0;i<$ifr.length;i++){//循环取出页面上的iframe，查找含有storageNumber的iframe
					var ifr=$($ifr[i].contentWindow.document);
					var curPicpath = $("#cur"+curid).val();
					ifr.find('#pic'+curPicpath).removeClass("out");		
				}
			}
			$("#curid").val(curid);
		})
		
		function bigimg(i)
{
	var zoom = parseInt(i.style.zoom,10)||100;
	zoom += event.wheelDelta / 12;
	if(zoom > 0 )
	i.style.zoom=zoom+'%';
	return false;
}
	</script>
</div>
</body>
</html>

