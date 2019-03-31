<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <%@ include file="/common/meta.jsp"%>
    <title>档案信息</title>
</head>

<body>
<div id="scrollContent">
	<div class="box1" style="overflow: auto;height: 490px">
	<table class="tableStyle" formMode="view" >
		<tr><th class="ali02" colspan="2">档案信息</th></tr>
		<tr><td width="35%">系统编号：</td><td>${info.xtdabh }</td></tr>
		<tr><td>储位编号：</td><td>${info.cwbh}</td></tr>
		<tr><td>档案编号：</td><td>${info.dabh}</td></tr>
		<tr><td>业务类型：</td><td>${vs:ywlxName(info.ywlx) }</td></tr>
		<tr><td>流水号：</td><td>${info.lsh }</td></tr>
		<tr><td>号牌：</td><td>${info.hphm }（${vs:hpzlName(info.hpzl)}）</td></tr>
		<tr><td>车辆识别代号：</td><td>${info.clsbdh }</td></tr>
		<tr><td>机动车序号：</td><td>${info.xh}</td></tr> 
		<tr><td>档案状态：</td><td>${vs:daztName(info.dazt) }</td></tr>
		<tr><td>档案类型：</td><td>${info.dalx=="1"?"业务档案":""}${info.dalx=="2"?"历史档案":""}</td></tr>
		<tr><td>创建人：</td><td>${info.cjr}</td></tr>
		<tr><td>归档人：</td><td>${vs:userName(info.gdr)}</td></tr>
		<tr><td>归档时间：</td><td><fmt:formatDate value="${info.gdsj}" pattern="yyyy年MM月dd日  HH:ss:mm"/></td></tr>
		<tr><td>行政区划：</td><td>${vs:xzqhName(info.xzqh)}</td></tr>
		<tr><td>发证机关：</td><td>${info.fzjg}</td></tr>
		<tr><td>登记日期：</td><td><fmt:formatDate value="${info.djrq}" pattern="yyyy年MM月dd日"/></td></tr>
		<tr><td>发牌日期：</td><td><fmt:formatDate value="${info.fprq}" pattern="yyyy年MM月dd日"/></td></tr>
		<tr><td>机动车所有人：</td><td>${info.syr}</td></tr>
		<tr><td>身份证明号码：</td><td>${info.sfzmhm}</td></tr>
		<tr><td>住所详细地址：</td><td>${info.zsxxdz}</td></tr>
		<tr><td>中文品牌：</td><td>${info.clpp1}</td></tr>
		<tr><td>车辆型号：</td><td>${info.clxh}</td></tr>
		<tr><td>车身颜色：</td><td>${info.csys}</td></tr>
		<tr><td>制造国：</td><td>${info.zzg}</td></tr>
		<tr><td>制造厂：</td><td>${info.zzcmc}</td></tr>
		<tr><td>发动机号：</td><td>${info.fdjh}</td></tr>
		<tr><td>备注：</td><td>${info.bz}</td></tr>
	</table>	
    </div>
</div>
</body>
</html>
