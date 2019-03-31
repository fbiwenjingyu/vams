<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preActoin = "ycInfo";
String str= getServletContext().getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>打印条码</title>
   <!-- 引用公用路径Start -->
   <%@ include file="/common/meta.jsp"%>
   <%@ include file="/common/taglib.jsp"%>
<script type="text/javascript" ><!--

var k=1;

var printSetup = function(){
	// 打印页面设置      
	wb.execwb(8,1);   
};
     
var printPreView = function(){ 
	pagesetup_null();
   	wb.execwb(7,1);    
   
};
   

var printIt = function(url){  
　　  if (confirm('确定打印吗？')) { 
           $.ajax({
			   type: "POST",
			   url: "ycInfo!insertBatchCode.action?sfwj="+url,
			   dataType:"json",
			   success: function(msg){  
				  if(msg=="200"){
				     alert("条码重复,请选择下一组条码!");
				     return;
				  }else{
				     window.print(); 
				     window.location.reload(false);
				  }
				  closeWin();
			   }
	       }); 
　　  }   
        	 
}  

var windowClose = function(){  
    window.opener=null;   
    window.open('','_self');   
   
}; 

var hkey_root,hkey_path,hkey_key
hkey_root="HKEY_CURRENT_USER"
hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"
//设置网页打印的页眉页脚为空
function pagesetup_null(){
    try{
	    var RegWsh = new ActiveXObject("WScript.Shell")
	    hkey_key="header" 
	    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
	    hkey_key="footer"
	    RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
    }catch(e){}
}
 function closeOp(){
  closeWin();
  window.reload();
 }
 
 
 function getNum(){
 	var num = $("#num").val();
 	window.location.href = "ycInfo!getCodeBatch.action?sfwj=N&defNum=" + num;
 	//alert(num);
 	//$.ajax({
		//	   type: "POST",
		//	   url: "ycInfo!getCodeBatchAjax.action?sfwj=Y&defNum="+num,
		//	   dataType:"json",
		//	   success: function(msg){ 
		//	         if(msg == "200") {
		//	         	window.location.href = "ycInfo!getCodeBatch.action?sfwj=Y&defNum=" + num;
		//	         }else {
		//	         	alert("获取条码失败");
		//	         }
	//			     
	//		   }
	// });
 }
 
--></script>
<style type="text/css" media=print>  
	.Noprint{
	   visibility: hidden;
	} 

</style>

</head>
<body>
<!-- 
    <div id="showImg2" style="text-align:center; margin-top: 2px;">
    	<img id="img1" src="wjcy_dytm!write39CodeImage.action?bs=3" /> 
	  	<img id="img2" src="wjcy_dytm!write39CodeDoubleImage.action?bs=3" />
	  	 <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object> 
	</div>
	<div class="Noprint" style="text-align: center;">  
	  	<input type=button value="&nbsp;&nbsp;有外检业务打印&nbsp;&nbsp;" onclick="printIt('3')"/>&nbsp;&nbsp;
	  	<input type=button value="预览" onclick="printPreView()" >
	    <input type=button value="设置" onclick="printSetup()" >  
	    <input type=button value="关闭" onclick="closeOp()" >
	    <br/><br/>
	    <div style="width: 100%;text-align: center;">
	       <a id="gbx" style="display:none;text-decoration: underline;color:blue; "  onclick="window.location.reload();">下一条码</a>
	    </div>
	</div>
	
	
  -->
    <div id="showImg2" style="width:380px;text-align:center; margin-top:5px;margin-left: -16px;">
		   <c:forEach var="xtdabh" items="${list}" varStatus="status">
		   		<img src="<%=str %>/sys/barcode!writeCodeImage.action?code=${xtdabh }"  style="width:40%;height:64px;"/>
		   </c:forEach>
	 	 <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object> 
	</div> 
	<div class="Noprint" style="text-align:center; width:400px;margin-top:-72px;margin-left: 0px;">  
	    <br/><br/>
	    <br/>
	   
	  	<input type=button value="&nbsp;&nbsp;打印条码&nbsp;&nbsp;" onclick="printIt('N')"/>&nbsp;&nbsp;
	    <input type=button  value="设置" onclick="printSetup()"/> &nbsp;&nbsp;
	    <input type=button  value="关闭" onclick="closeOp()"/>&nbsp;&nbsp;
	    <select name="num" id="num" size="3" style="width:5px;" onchange="getNum();">
	    	<option value="10" selected="selected">10条</option>
	    	<option value="20" <c:if test="${num==20 }">selected="selected"</c:if>>20条</option>
	    	<option value="50" <c:if test="${num==50 }">selected="selected"</c:if>>50条</option>
	    	<option value="100" <c:if test="${num==100 }">selected="selected"</c:if>>100条</option>
	    </select>
	    <div style="width: 100%;text-align: center;">
	       <a id="gbx" style="display:none;text-decoration: underline;color:blue; "  onclick="window.location.reload();">下一条码</a>
	    </div>
	</div>
	
</body>
</html>
