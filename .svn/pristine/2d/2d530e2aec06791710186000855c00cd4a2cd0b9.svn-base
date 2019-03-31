<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<title>综合审核回执单</title>
   <!-- 引用公用路径Start -->
   

  
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
       window.print();
       //window.location.href="wjcy_dytm!insertWjbh.action";
　　  }   
     window.close(); 
     showScreenOver();  	 
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
  closeWin();
  showScreenOver();
  
 }
</script>
<style type="text/css" media=print>  
.Noprint{
   visibility: hidden;
}  

</style>
</head>
<body style="font-size: 12px;">
<div style="text-align: center;width:495px;height:100%;display:block; margin:0px auto 0px auto;" >
    <span style="font-size: 14px;font-weight: bold;">外检回执单</span>
    <br/>
    <br/>
      <img src="arcBaseInfo!write39CodeImage5.action?code=${code }" />
     <br/><br/>
    
	<div style="text-align: left;margin-left:180px;">
	    <font style="font-weight: bold;">重扫图片有：</font>
	    <br/><br/>
	    <span>
	        
	           <c:forEach items="${pics }" var="pic"  varStatus="status">
	            <li style="line-height: 23px;list-style-type:none;">${status.index+1} &nbsp;&nbsp; ${yc_paper_map[pic.takecode ] }</li>
	           </c:forEach>
	       
	        
	    </span>
	    <br/>
	            上传人：${user2CnName[entity.cjr ] }&nbsp;&nbsp;
		上传人警号：${user2JybhMap[entity.cjr] }
	    <br/>
	             审核人：${user2CnName[entity.shr ] }&nbsp;&nbsp;
		审核人警号：${user2JybhMap[entity.shr] }
	       
	     <br/><br/>
	    理由：${bz }
    </div>
</div>
	<br/>
	<div class="Noprint" style="text-align: center;">   
	    <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object> 
	  	<input type=button value="打印" onclick="printIt()"/>&nbsp;&nbsp;
	    <input type=button value="设置" onclick="printSetup()"/> &nbsp;&nbsp;
	    <input type=button value="预览" onclick="printPreView()"/>&nbsp;&nbsp;
	    <input type=button value="关闭" onclick="window.close()"/>&nbsp;&nbsp;<br/> <br/>
	</div>
</body>
</html>
