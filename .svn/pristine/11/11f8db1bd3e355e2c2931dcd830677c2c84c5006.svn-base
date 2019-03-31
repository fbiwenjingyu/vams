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
    <title>入库档案</title>
</head>

<body>
<div id="scrollContent">
	<div class="box1" style="overflow: auto;height: 490px">
	<table class="tableStyle" formMode="view" >
		<tr><th colspan="2">入库信息</th></tr>
		<tr><td width="35%">系统编号：</td><td>${stoin.xtdabh }</td></tr>
		<tr><td>入库批次号：</td><td>${stoin.rkpch }</td></tr>
		<tr><td>储位编号：</td><td>${stoin.cwbh}</td></tr>
		<tr><td>档案编号：</td><td>${stoin.dabh}</td></tr>
		<tr><td>业务类型：</td><td>${vs:ywlxName(stoin.ywlx) }</td></tr>
		<tr><td>流水号：</td><td>${stoin.lsh }</td></tr>
		<tr><td>号牌：</td><td>${stoin.hphm }（${vs:hpzlName(stoin.hpzl)}）</td></tr>
		<tr><td>车辆识别代号：</td><td>${stoin.clsbdh }</td></tr>
		<tr><td>机动车序号：</td><td>${stoin.xh}</td></tr> 
		<tr><td>档案状态：</td><td>${vs:daztName(stoin.dazt) }</td></tr>
		<tr><td>申请人：</td><td>${stoin.sqrxm}（${stoin.sqrid}）</td></tr>
		<tr><td>申请时间：</td><td><fmt:formatDate value="${stoin.sqsj}" type="both" /></td></tr>
		<tr><td>审核人：</td><td>${stoin.shrxm}（${stoin.shrid}）</td></tr>
		<tr><td>入库时间：</td><td><fmt:formatDate value="${stoin.rksj}" type="both" /></td></tr>
		<tr><th class="ali02" colspan="2">档案信息</th></tr>
		<tr><td>档案类型：</td><td>${arcinfo.dalx=="1"?"业务档案":""}${arcinfo.dalx=="2"?"历史档案":""}</td></tr>
		<tr><td>归档人：</td><td>${vs:userName(arcinfo.gdr)}</td></tr>
		<tr><td>归档时间：</td><td><fmt:formatDate value="${arcinfo.gdsj}" pattern="yyyy年MM月dd日  HH:ss:mm"/></td></tr>
		<tr><td>创建人：</td><td>${vs:userName(arcinfo.cjr)}</td></tr>
		<tr><td>行政区划：</td><td>${vs:xzqhName(arcinfo.xzqh)}</td></tr>
		<tr><td>发证机关：</td><td>${arcinfo.fzjg}</td></tr>
		<tr><td>登记日期：</td><td><fmt:formatDate value="${arcinfo.djrq}" pattern="yyyy年MM月dd日"/></td></tr>
		<tr><td>发牌日期：</td><td><fmt:formatDate value="${arcinfo.fprq}" pattern="yyyy年MM月dd日"/></td></tr>
		<tr><td>机动车所有人：</td><td>${arcinfo.syr}</td></tr>
		<tr><td>身份证明号码：</td><td>${arcinfo.sfzmhm}</td></tr>
		<tr><td>住所详细地址：</td><td>${arcinfo.zsxxdz}</td></tr>
		<tr><td>中文品牌：</td><td>${arcinfo.clpp1}</td></tr>
		<tr><td>车辆型号：</td><td>${arcinfo.clxh}</td></tr>
		<tr><td>车身颜色：</td><td>${arcinfo.csys}</td></tr>
		<tr><td>制造国：</td><td>${arcinfo.zzg}</td></tr>
		<tr><td>制造厂：</td><td>${arcinfo.zzcmc}</td></tr>
		<tr><td>发动机号：</td><td>${arcinfo.fdjh}</td></tr>
		<tr><td>备注：</td><td>${arcinfo.bz}</td></tr>
	</table>	
    </div>
</div>
</body>
</html>
