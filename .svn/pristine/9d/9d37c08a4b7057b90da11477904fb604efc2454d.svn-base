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
			   url: "ycInfo!insertDoubleCode.action?sfwj="+url,
			   dataType:"json",
			   success: function(msg){  
				  if(msg=="200"){
				     alert("该条码已被打印,请选择下一条码!");
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
           <img src="<%=str %>/sys/barcode!writeCodeImage.action?code=${xtdabh1 }"  style="width:40%;height:64px;"/>
		   <img src="<%=str %>/sys/barcode!writeCodeImage.action?code=${xtdabh2 }"  style="width:40%;height:64px;" />
	 	 <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object> 
	</div> 
	<div class="Noprint" style="text-align:center; width:380px;margin-top:-72px;margin-left: -19px;">  
	      <img  src="<%=str %>/sys/barcode!writeCodeImage.action?code=${xtdabh1 }"  style="width:40%;height:64px; " /> 
	       <img  src="<%=str %>/sys/barcode!writeCodeImage.action?code=${xtdabh2 }"  style="width:40%;height:64px;" />
	    <br/>
	  	<input type=button value="&nbsp;&nbsp;打印条码&nbsp;&nbsp;" onclick="printIt('Y')"/>&nbsp;&nbsp;
	    <input type=button  value="设置" onclick="printSetup()"/> &nbsp;&nbsp;
	    <input type=button  value="关闭" onclick="closeOp()"/>&nbsp;&nbsp;
	    <div style="width: 100%;text-align: center;">
	       <a id="gbx" style="display:none;text-decoration: underline;color:blue; "  onclick="window.location.reload();">下一条码</a>
	    </div>
	</div>
	
</body>
</html>
