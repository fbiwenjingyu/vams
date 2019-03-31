<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preAction = "attr";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>客户端上传</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript">
	
	function upload(type){
		if(type=="1" ){
			if($("#file1").val()){
				if(confirm("上传该会替换原来IE，确实上传？")){
					form_upload.submit();				
				}	
			}else{
				alert("请先选择要替换的IE!");
				return;
			}
		}
		
		if(type=="2"){
			if($("#file2").val()){
				if(confirm("上传该会替换原来扫描客户端，确实上传？")){
					form_upload.submit();				
				}	
			}else{
				alert("请先选择要替换的扫描客户端!");
				return;
			}
		}
		
		if(type=="3"){
			if($("#file3").val()){
				if(confirm("上传该会替换原来外检查验客户端，确实上传？")){
					form_upload.submit();				
				}	
			}else{
				alert("请先选择要替换的外检查验客户端!");
				return;
			}
		}
	}

</script>
</head>
<body>

<form action="<%=preAction %>!uploadClient.action" enctype="multipart/form-data" method="post" name="form_upload">
	<table>
		<tr>
			<td>IE:</td>
			<td>
				<input type="file" name="file1" id="file1" /> 
			</td>
			<td>
				<button type="button" onclick="upload('1')">上传</button>
			</td>
		</tr>
		<tr>
			<td>扫描客户端:</td>
			<td>
				<input type="file" name="file1" id="file2"  /> 
			</td>
			<td>
				<button type="button" onclick="upload('2')">上传</button>
			</td>
		</tr>
		<tr>
			<td>外检客户端:</td>
			<td>
				<input type="file" name="file1" id="file3"  /> 
			</td>
			<td>
				<button type="button" onclick="upload('3')">上传</button>
			</td>
		</tr>
	</table>
</form>

</body>
</html>
