<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfoMid";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
<title>无标题文档</title>
<style type="text/css">
body{font-size:14px;font-weight:bold;font-family:宋体}
table{ border-left: 1px solid #666; border-bottom: 1px solid #666;}
td{border-right:1px solid #666;border-top: 1px solid #666;height:20px;}
</style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" >

var printSetup = function(){
　　// 打印页面设置      
　　  wb.execwb(8,1);      
}      
var printPreView = function(){  
	pagesetup_null();
　　// 打印页面预览    　　      
　　  wb.execwb(7,1);    　　      
}      
var printIt = function(url){      
　　  if (confirm('确定打印吗？')) {  
	  pagesetup_null();
      window.print(); 
　　}      
}  
var windowClose = function(){  
	top.Dialog.close(); 
}; 


var HKEY_Root,HKEY_Path,HKEY_Key; 
HKEY_Root="HKEY_CURRENT_USER"; 
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"; 
//设置网页打印的页眉页脚为空
function pagesetup_null(){
	try 
	 { 
	         var Wsh=new ActiveXObject("WScript.Shell"); 
	  HKEY_Key="header"; 
	  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); 
	  HKEY_Key="footer"; 
	  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); 
	 } 
	 catch(e){}
}
</script>
<style type="text/css" media=print>  
.Noprint{display : none }  
</style>
</head>
<body>
<p class="Noprint" align="center">  
   <object id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></object>  
  	<input type=button value="打印" onclick="printIt()"/>&nbsp;&nbsp;
    <input type=button value="预览" onclick="printPreView()"/>&nbsp;&nbsp;
    <input type=button value="设置" onclick="printSetup()"/> &nbsp;&nbsp;
    <input type=button value="关闭" onclick="windowClose();"/>&nbsp;&nbsp;<br/>  
</p>
<br/>
<p align="center" ><span style="font-size:
21.0pt;font-family:宋体">机 动 车 查 验 记 录 表</span></p>
<p><span>号牌号码（流水号或其他与车辆能对应的号码）：${ycinfo.hphm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
号牌种类：${hpzlMap[ycinfo.hpzl] }&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="8">业务类型:<span>
                <input name="" type="checkbox" value="" />注册登记
                <input name="ywlx" type="checkbox" value="" />转入
				<input name="ywlx" type="checkbox" value="" />转移登记
				<input name="ywlx" type="checkbox" value="" />变更迁出
				<input name="ywlx" type="checkbox" value="" />变更车身颜色<br/>
				<input name="ywlx" type="checkbox" value="" />核发检验合格标志<input name="ywlx" type="checkbox" value="" />更换车身或者车架
				<input name="ywlx" type="checkbox" value="" />更换发动机
				<input name="ywlx" type="checkbox" value="" />变更使用性质<input name="ywlx" type="checkbox" value="" />重新打刻VIN<br/>
				<input name="ywlx" type="checkbox" value="" />重新打刻发动机号
                <input name="ywlx" type="checkbox" value="" />更换整车
                <input name="ywlx" type="checkbox" value="" />申领登记证书
                <input name="ywlx" type="checkbox" value="" />补领登记证书
                <input name="ywlx" type="checkbox" value="" />监销
				<input name="ywlx" type="checkbox" value="" />其他
               
            </span></td>
  </tr>
  <tr>
    <td width="10%" align="center">类别</td>
    <td width="5%" align="center">序号</td>
    <td width="23%" align="center">查验项目</td>
    <td width="7%" align="center">判定</td>
    <td width="10%" align="center">类别</td>
    <td width="5%" align="center">序号</td>
    <td width="23%" align="center">查验项目</td>
    <td width="7%" align="center">判定</td>
  </tr>
  <tr>
    <td rowspan="9"><p align="center">通用</p>
    <p align="center"> 项目</p></td>
    <td height="10"><div align="center">1</div></td>
    <td>车辆识别代号</td>   
    <td>&nbsp;</td>
    <td rowspan="4"><div align="center">大中型客车、校车、危险化学品运输车</div></td>
    <td><div align="center">14</div></td>
    <td>灭火器</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"><div align="center">2</div></td>
    <td>发动机型号/号码</td>
    <td>&nbsp;</td>
    <td><div align="center">15</div></td>
    <td>行驶记录装置</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"><div align="center">3</div></td>
    <td>车辆品牌/型号</td>
    <td>&nbsp;</td>
    <td><div align="center">16</div></td>
    <td>安全出口/安全手锤</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"><div align="center">4</div></td>
    <td>车身颜色</td>
    <td>&nbsp;</td>
    <td><div align="center">17</div></td>
    <td>外部标识、文字</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"><div align="center">5</div></td>
    <td>核定载人数</td>
    <td>&nbsp;</td>
    <td rowspan="2"><div align="center">其 他</div></td>
    <td><div align="center">18</div></td>
    <td>标志灯具、警报器</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"><div align="center">6</div></td>
    <td>车辆类型</td>
    <td>&nbsp;</td>
    <td><div align="center">19</div></td>
    <td>安全技术检验合格证明</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"><div align="center">7</div></td>
    <td>号牌/车辆外观形状</td>
    <td>&nbsp;</td>
    <td colspan="4" rowspan="3">查验结论</td>
  </tr>
  <tr>
    <td height="10"><div align="center">8</div></td>
    <td>轮胎完好情况</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"><div align="center">9</div></td>
    <td>安全带、三角警告牌</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="4" align="center" style="height:10pt">
      <p>货车</p>
      <p>挂车</p>
   	</td>
    <td height="10"><div align="center">10</div></td>
    <td>轮胎完好情况</td>
    <td>&nbsp;</td>
    <td colspan="4" rowspan="2"><p>检验员：</p>
    <span style="float:right" style="">&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</span></td>
  </tr>
  <tr>
    <td height="10"  align="center">11</td>
    <td>轮胎规格</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="10"  align="center">12</td>
    <td>侧后部防护装置</td>
    <td>&nbsp;</td>
    <td rowspan="2">复检合格</td>
    <td colspan="3" rowspan="2"><p>检验员：</p>
    <span style="float:right" style="">&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</span></td>
  </tr>
  <tr>
    <td height="10"  align="center">13</td>
    <td>车身反光标示</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
  

<c:choose>
	<c:when test="${ empty picList}">
	  <td style="height:220pt" colspan="5"><p class="MsoNormal" align="center" style='margin:auto;text-align:center;
	  text-indent:-25.0pt'><font size="6">机&nbsp;动&nbsp;车&nbsp;照&nbsp;片</font><br/><span
	  style='font-family:宋体; font-size: 14.5px;'>(注册登记、转移登记、需要制作照片的变更登记、转入、监销)</span></p></td>
	    <td colspan="3" valign="top">备&nbsp;&nbsp;&nbsp;&nbsp;注:</td>
	</c:when>
	<c:otherwise>
		<td align="center" colspan="8" >
			<c:forEach var="pic" items="${picList}">
				<img height="220pt" width="200"  src="<%=path %>/sys/base64ToImage!getBase64ToImagePath.action?picpath=${pic.picpath}"/>
			</c:forEach>
		</td>
	</c:otherwise>
</c:choose>
  </tr>
  <tr>
    <td colspan="8"><p class="MsoNormal" align="center" style='text-align:center; margin-top: 10px;'><span
  style='font-size:14.0pt;font-family:宋体'><p class="MsoNormal" align="center" style='margin:auto;text-align:center;
  text-indent:-25.0pt'><font size="6">车辆识别代号(车架号)拓印膜</font><br/><span
  style='font-family:宋体; font-size: 14.5px;'>（注册登记、转移登记、转出、转入、更换车身或者车架、更换整车、<br/>申领登记证书、重新打刻VIN）</span></p></td>
  </tr>
</table>

<p >说明：1、填表时应在对应的业务类型名称上划“√”；2、对按照规定不须查验的项目，在对应的判定栏内划“—”；3、本表所列查验项目判定不合格时在对应栏划“×”，本表以外的查验项目不合格时，在备注栏内注明情况，查验结论签注为“不合格”；所有查验项目合格，查验结论签注为“合格”；4、复检合格时，查验员签字并签注日期；复检仍不合格的，不签注；5、注册登记查验时，“车身颜色、核定载人数、车辆类型”判定栏内签注查验确定的相应内容，变更颜色查验时签注车身颜色。</p>
</body>
</html>
