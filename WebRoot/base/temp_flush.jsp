<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>刷新缓存</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<style>
	table tr td{
		text-align: center;
	}
</style>
<script language="javascript">
	
	var num=0;
	
	function flush(){
		var cks=$("input[type='checkbox']:checked");
		
		
		
		num=cks.length;//计算刷新个数
		if(num <=0){
			alert("请选择刷新项！");
			return;
		}
		
		//设置刷新按钮为不可用
		$("#btn_flush").attr("disabled",true);
		
		for(var i=0;i<cks.length;i++){
			var ck=cks[i];
			var name=ck.name;
			$("#"+name).attr("class","icon_loading").html("正在刷新...");//显示正在刷新图标
			$.post("${pageContext.request.contextPath}/cache-flushAll.action",{"type":name},function(data){
				num-=1;
				if(num==0){
					$("#btn_flush").attr("disabled",false);//刷新按钮不可用状态
				}
				if(data[0]=="1"){
					$("#"+data[1]).attr("class","icon_ok").html("刷新成功！");
				}else{
					$("#"+data[1]).attr("class","icon_delete").html("刷新失败！");
				}
			});
		}
	}
	
	function checkTest(tag){
		if(tag.checked==true&&tag.name=="all"){
			$("input[type='checkbox'][name!='all']").removeAttr("checked");
		}else{
			$("input[name='all']").removeAttr("checked");
		}
	}
</script>
</head>
<body>
<div id="scrollContent" class="table_bor">
 
	<table class="tableStyle" id="tab">
			<tr>
				<td>选择</td><td>缓存类别</td><td>状态</td>
			</tr>
			<tr>
				<td><input type="checkbox" checked="checked" name="all" onclick="checkTest(this)"/></td><td>全部缓存</td><td><span id="all" class="icon_refresh">准备刷新&nbsp;</span></td>
			</tr>
			<!-- 
			<tr>
				<td colspan="3"><hr /></td>
			</tr>
       		<tr>
				<td><input type="checkbox"  name="ywlx" onclick="checkTest(this)"/></td><td>业务类型</td><td><span id="ywlx" class="icon_refresh">准备刷新&nbsp;</span></td>
			</tr>
			<tr>
				<td><input type="checkbox"  name="hpzl" onclick="checkTest(this)"/></td><td>号牌种类</td><td><span id="hpzl" class="icon_refresh" >准备刷新&nbsp;</span></td>
			</tr>
			<tr>
				<td><input type="checkbox"  name="xzqh" onclick="checkTest(this)"/></td><td>行政区划</td><td><span id="xzqh" class="icon_refresh" >准备刷新&nbsp;</span></td>
			</tr>	
			<tr>
				<td><input type="checkbox"  name="smzz" onclick="checkTest(this)"/></td><td>扫描纸张</td><td><span id="smzz" class="icon_refresh" >准备刷新&nbsp;</span></td>
			</tr>
			<tr>
				<td><input type="checkbox"  name="xtpz" onclick="checkTest(this)"/></td><td>系统配置</td><td><span id="xtpz" class="icon_refresh" >准备刷新&nbsp;</span></td>
			</tr>
						<tr>
				<td><input type="checkbox"  name="jgxx" onclick="checkTest(this)"/></td><td>机构信息</td><td><span id="jgxx" class="icon_refresh" >准备刷新&nbsp;</span></td>
			</tr>
						<tr>
				<td><input type="checkbox"  name="dazt" onclick="checkTest(this)"/></td><td>档案状态</td><td><span id="dazt" class="icon_refresh" >准备刷新&nbsp;</span></td>
			</tr>
							<tr>
				<td><input type="checkbox"  name="fwgj" onclick="checkTest(this)"/></td><td>服务工具</td><td><span id="fwgj" class="icon_refresh" >准备刷新&nbsp;</span></td>
			</tr>
			 -->
			<tr>
				<td colspan="3">
					<button type="button"  onclick="flush()" id="btn_flush"><span class="icon_reload" id="flush">刷新</span></button>&nbsp;
					<button type="button" onclick="closeWin()"><span class="icon_no">关闭</span></button>
				</td>
			</tr>
       		 </table>

</div>
</body>
</html>