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
  	<%@ include file="/common/meta_cwgl.jsp"%>
    <title>插入储位显示界面（服务接口）</title>
	
<script type="text/javascript">
	
	function getCwbh(cwbh){
		
		var writeOK=false;
		var showNum=cwbh.substring(0, 1)+"-"+cwbh.substring(1, 3)+"-"+cwbh.substring(3, 4)+"-"+cwbh.substring(4, 5)+"-"+cwbh.substring(5, 8);
		
		//调用父窗口目标方法
		getParentContentWindow(function(windows){
			try {
				//将储位编号写入
				var ok=windows.writeCwbh(cwbh,showNum);
				//获取成功
				if(ok){
					top.Dialog.close();
					writeOK = true;
					alert("获取成功，请手动关闭档案柜选择窗口！");
					return writeOK;
				}
			} catch (e) {
				return false;
			}
		});
		if (!writeOK) {
			alert("储位编号已获取（"+cwbh+"），但无法写入!");
		}
	}
</script>
	
  </head>
  
  <body>
   <div id="scrollContent">
  		<table class="tableStyle" >
  			<tr>
  				<th>序号</th>
  				<th>储位编号</th>
  				<th>系统编号</th>
  				<th>档案编号</th>
  				<th>号牌号码</th>
  				<th>操作</th>
  			</tr>
  			<c:forEach var="info" varStatus="status" items="${pageList}">
  			<tr>
  			<td class="ali02">${status.index+1}</td>
  			<!-- 此储位为空 -->
  				<c:if test="${info.id != null}">
  					<td class="ali03">${info.cwbh}</td>
					<td class="ali03">${info.xtdabh}</td>
					<td class="ali03">${info.dabh}</td>
					<td class="ali03">${info.hphm}</td>
					<td class="ali02"><button type="button" onclick="getCwbh('${info.cwbh}')" disabled="disabled"><span class="icon_delete">已被占用</span></button></td>
  				</c:if>
  			<!-- 此储位不为空 -->
  				<c:if test="${info.id == null}">
  					<td class="ali03">${info.cwbh}</td>
					<td class="ali02" colspan="3">空白储位</td>
					<td class="ali02"><button type="button" onclick="getCwbh('${info.cwbh}')"><span class="icon_ok">获取储位</span></button></td>
  				</c:if>
			</tr>
			</c:forEach>
  		</table>
   </div>
  </body>
</html>
