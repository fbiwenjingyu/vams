<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>拆解</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <%@ include file="/common/meta.jsp"%>
     <%@ include file="/common/taglib.jsp"%>
     <style type="text/css">
        tr{
           line-height: 35px;
        }
     </style>
     <script type="text/javascript">
        $(function(){
            document.getElementById("wjbh").focus();
        })
        
         //拆解
         function update(){
             //取值
             var wjbh=document.getElementById("wjbh").value;
             var lsh=document.getElementById("lsh").value;
             //去空格
             wjbh = trim(wjbh);
             lsh = trim(lsh);
             
             //验证
             if(wjbh==""){
                 top.Dialog.alert("系统编号不能为空");
	             showScreenOver();
                return ;
             }
             if(lsh==""){
                top.Dialog.alert("六合一条码号不能为空");
	            showScreenOver();
	           return ;
             }
             
            // if(isNaN(wjbh)){   
            //     top.Dialog.alert("外检编号必须是数字类型");
           //      showScreenOver();
 	       //    return ;
          //    }
             
             if(isNaN(lsh)){   
                top.Dialog.alert("六合一流水号必须是数字类型");
                showScreenOver();
	           return ;
             }
             
             if(lsh.length>13){   
                top.Dialog.alert("六合一流水号长度0-13位数");
                showScreenOver();
	           return ;
             }
             
             //提交
             window.location.href="<%=preAction %>!tmcj.action?wjbh="+wjbh+"&lsh="+lsh;
         }
         
		//去左右空格
		function trim(str){ //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
     </script>
  </head>
  <body><br/><br/>
       <form action="<%=preAction %>!tmcj.action" method="post" name="queryForm">
            <table align="center">
              <tr>
                 <th align="left" colspan="3">外检流水拆解</th> 
              </tr>
              <tr>
	                 <td>系统编号：</td>
	                 <td><input type="text" name="wjbh" id="wjbh" style="width:200px;"  value="${param.xtdabh }"/> </td>
	                 <td>&nbsp;</td>
              </tr>
              <tr>
	                 <td>六合一流水号：</td>
	                 <td><input type="text" name="lsh" id="lsh" style="width:200px;" value="${param.lsh }" /></td>
	                 <td>&nbsp;</td>
              </tr>
              <tr></tr>
              <tr>
	                <td colspan="3">
	                 <!-- 
	                   <button type="button" onclick="update()" ><span class="icon_mark">修改</span></button> &nbsp;&nbsp;&nbsp;&nbsp;
	                   -->
	                   <button type="button" onclick="update()" ><span class="icon_cut">拆解</span></button> &nbsp;&nbsp;&nbsp;&nbsp;
                   	   <button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button> &nbsp;&nbsp;&nbsp;&nbsp;
                   	   <button type="button" onclick="closeWin()"><span class="icon_reload">关闭</span></button> &nbsp;&nbsp;&nbsp;&nbsp;
                   	</td>
               </tr>
          </table>
		</form>
		
		<div align="center" style="width:100%;display: block;">
		    <font style="color:red;">${msg }</font>
		</div>
  </body>
</html>
