<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String preActoin = "ycInfo";
String str= getServletContext().getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>打印条码</title>


<script type="text/javascript" >

var k=1;

var printSetup = function(){
	// 打印页面设置      
	wb.execwb(8,1);   
} 
     
var printPreView = function(){  
	// 打印页面预览    　　       　　  
	wb.execwb(7,1);    
}  
   
var printIt = function(url){  
　　  if (confirm('确定打印吗？')) { 
           $.ajax({
			   type: "POST",
			   url: "ycInfo!insertSingleCode.action?sfwj="+url,
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
	 top.Dialog.close();
 }
 
</script>
<style type="text/css" media=print>  
	.Noprint{
	   visibility: hidden;
	} 
	 
	
</style>

</head>
<body>
	<div style="margin-top:20px;text-align:center; width:100%;">  
	<c:choose>
			<c:when test="${!empty xtdabh}">
	   			<img src="<%=str %>/sys/barcode!writeCodeImage.action?code=${xtdabh }" /> <br/>
			</c:when>
			<c:otherwise>
				<img src="<%=str %>/sys/barcode!writeCodeImage.action?code=<%=request.getParameter("code") %>"/><br/>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="Noprint" style="margin-top:20px;text-align:center; width:100%;">  
	   <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object>
		
	  	<input type=button  value="打印" onclick="printIt('N')"/>&nbsp;&nbsp;
	    <input type=button  value="设置" onclick="printSetup()"/> &nbsp;&nbsp;
	    <input type=button  value="关闭" onclick="closeOp()"/>&nbsp;&nbsp;
	     <br/><br/>
	</div>
	
	 
	
</body>
</html>
