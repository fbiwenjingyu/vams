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
    <title>修改档案信息</title>
    
    <style>
    	.long{
    		width: 240px;
    	}
    	.short{
    		
    	}
    </style>
    
  	<script type="text/javascript">
  		
  		//保存信息
  		function save(){
  			//基础信息
  			var cwbh=$("input[name='cwbh']").val().trim();//储位编号
  			var dabh=$("input[name='dabh']").val().trim();//档案编号
  			var lsh=$("input[name='lsh']").val().trim();//流水号
  			var ywlx=$("select[name='ywlx']").val().trim();//业务类型
  	//		var hpzl=$("input[name='hpzl']").val();//号牌种类
  			var hphm=$("input[name='hphm']").val().trim();//号牌号码
  			var clsbdh=$("input[name='clsbdh']").val().trim();//车辆识别代号
  			var remark=$("textarea[name='remark']").val().trim();//基础备注
  			var snid=$("input[name='snid']").val();//关联序列号
  			
			//高级信息
			var wjbh=$("input[name='wjbh']").val();//外检编号
			var xh=$("input[name='xh']").val();//机动车序号
			var arcType=$("input[name='arcType']").val();//档案类型
			var deptArea=$("select[name='deptArea']").val();//行政区划
			var fprq=$("input[name='fprq']").val();//发牌日期
			var fdjh=$("input[name='fdjh']").val();//发动机号
			var syr=$("input[name='syr']").val();//机动车所有人
			var sfzmhm=$("input[name='sfzmhm']").val();//身份证明号码
			var zsxxdz=$("input[name='zsxxdz']").val();//住所详细地址
			var clpp1=$("input[name='clpp1']").val();//车辆品牌
			var clxh=$("input[name='clxh']").val();//车辆型号
			var cllx=$("input[name='cllx']").val();//车辆类型
			var csys=$("input[name='csys']").val();//车身颜色
			var zzg=$("input[name='zzg']").val();//制造国
			var bz=$("textarea[name='bz']").val();//备注
			
  			$.post("storage/storeGet-saveInfo.action",
  				{//info信息
  				 "info.cwbh":cwbh,
  				 "info.snid":snid,
  				 "info.dabh":dabh,
  				 "info.lsh":lsh,
  				 "info.ywlx":ywlx,
  				 "info.hphm":hphm,
  				 "info.clsbdh":clsbdh,
  				 "info.remark":remark,
  				 //arcinfo信息
  				 "arcinfo.snid":snid,
  				 "arcinfo.cwbh":cwbh,
  				 "arcinfo.lsh":lsh,
  				 "arcinfo.ywlx":ywlx,
  				 "arcinfo.xh":xh,
  				 "arcinfo.dabh":dabh,
  				 "arcinfo.hphm":hphm,
  				 "arcinfo.clsbdh":clsbdh,
  				 "arcinfo.fprq":fprq,
  				 "arcinfo.syr":syr,
  				 "arcinfo.sfzmhm":sfzmhm,
  				 "arcinfo.zsxxdz":zsxxdz,
  				 "arcinfo.clpp1":clpp1,
  				 "arcinfo.clxh":clxh,
  				 "arcinfo.cllx":cllx,
  				 "arcinfo.csys":csys,
  				 "arcinfo.zzg":zzg,
  				 "arcinfo.fdjh":fdjh,
  				 "arcinfo.wjbh":wjbh,
  				 "arcinfo.bz":bz,
  				 "arcinfo.arcType":arcType,
  				 "arcinfo.deptArea":deptArea
  				 },
  				function(data){
					alert(data[1]);
					if(data[0]=="1"){
						top.document.getElementById("_DialogFrame_0").contentWindow.queryForm.submit();
						closeWin();
					}
  				});
  		}
  		
  		//点击修改更多信息
  		function showDetil(btn){
  			//未显示
  			if(btn.name=="btn_d0"){
  				$(btn).attr("name","btn_d1").text("点击关闭详细信息");
  				$("#dtab").show();
  			}else 
  			//已显示
  			if(btn.name=="btn_d1"){
  				$(btn).attr("name","btn_d0").text("点击修改详细信息");
  				$("#dtab").hide();
  			}
  		}
  	</script>
  </head>
  
  <body>
   <div id="scrollContent" > 
  	<div class="box1" style="height: 540px;overflow: auto;">
  	<form action="cwgl/stoget-saveInfo.action" method="post">
  	<!-- 主键 -->
  	<input type="hidden" name="info.id" value="${info.id}"/>
  	
   	<table class="tableStyle" formMode="line">
   		<tr><th colspan="2">修改档案信息</th></tr>
   		<tr>
   			<td width="30%">储位编号：</td><td>${info.cwbh}</td>
   		</tr>
		<tr><td width="35%">系统编号：</td><td>${info.xtdabh }</td></tr>
		<tr><td>档案编号：</td><td><input name="info.dabh" value="${info.dabh}"/></td></tr>
		<tr><td>业务类型：</td><td><select name="info.ywlx" boxHeight="150" selectedValue="${info.ywlx}" data='${vs:ywlxSelect()}'></select></tr>
		<tr><td>流水号：</td><td>${info.lsh }</td></tr>
		<tr><td>号牌：</td><td>${info.hphm }（${vs:hpzlName(info.hpzl)}）</td></tr>
		<tr><td>车辆识别代号：</td><td>${info.clsbdh }</td></tr>
		<tr><td>机动车序号：</td><td>${info.xh}</td></tr> 
		<tr><td>档案状态：</td><td>${vs:daztName(info.dazt) }</td></tr>
		<tr><td>档案类型：</td><td>${info.dalx=="1"?"业务档案":""}${info.dalx=="2"?"历史档案":""}</td></tr>
		<tr><td>创建人：</td><td>${info.cjrmc}（${info.cjr}）</td></tr>
		<tr><td>创建时间：</td><td><fmt:formatDate value="${info.cjsj}" pattern="yyyy年MM月dd日  HH:ss:mm"/></td></tr>
		<tr><td>归档人：</td><td>${vs:userName(info.gdr)}</td></tr>
		<tr><td>归档时间：</td><td><fmt:formatDate value="${info.gdsj}" pattern="yyyy年MM月dd日  HH:ss:mm"/></td></tr>
		<tr><td>行政区划：</td><td><select name="info.xzqh" boxHeight="150" selectValue="${info.xzqh }" data='${vs:xzqhSelect()}'></select></td></tr>
		<tr><td>发证机关：</td><td><input name="info.fzjg" value="${info.fzjg}"/></td></tr>
		<tr><td>登记日期：</td><td><fmt:formatDate value="${info.djrq}" pattern="yyyy年MM月dd日"/></td></tr>
		<tr><td>发牌日期：</td><td><fmt:formatDate value="${info.fprq}" pattern="yyyy年MM月dd日"/></td></tr>
		<tr><td>机动车所有人：</td><td><input name="info.syr" value="${info.syr}"/></td></tr>
		<tr><td>身份证明号码：</td><td><input name="info.sfzmhm" value="${info.sfzmhm }"/></td></tr>
		<tr><td>住所详细地址：</td><td><input name="info.zsxxdz" value="${info.zsxxdz}"/></td></tr>
		<tr><td>中文品牌：</td><td><input name="info.clpp1" value="${info.clpp1}"/></td></tr>
		<tr><td>车辆型号：</td><td><input name="info.clxh" value="${info.clxh}"/></td></tr>
		<tr><td>车身颜色：</td><td><input name="info.csys" value="${info.csys}"/></td></tr>
		<tr><td>制造国：</td><td><input name="info.zzg" value="${info.zzg}"/></td></tr>
		<tr><td>制造厂：</td><td><input name="info.zzcmc" value="${info.zzcmc}"/></td></tr>
		<tr><td>发动机号：</td><td><input name="info.fdjh" value="${info.fdjh}"/></td></tr>
		<tr><td>备注：</td><td><input name="info.bz" value="${info.bz}"/></td></tr>
   	</table>
   	<table class="tableStyle" formMode="line">
   		<tr><td colspan="2"><button type="submit" onclick="save()">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
   							<button onclick="closeWin()" style="right: 0px;">取消</button>
		</td></tr>
   	</table>
   	</form>
   	
   	</div>
   	</div>
  </body>
</html>
