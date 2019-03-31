<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>档案业务归档统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引用公用路径Start -->
    <%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/taglib.jsp"%>
    <style type="text/css">
       .bott,.bott th,.bott tr,.bott td,.bott th{
        border:1px solid gray;
        line-height: 30px;
        border-collapse: collapse;
       
       }
       #tab table,#tab th,#tab tr,#tab td{
        border:1px solid gray;
        line-height: 30px;
        border-collapse: collapse;
        text-align: center;
       }
       font{
          color:red;
          font-size: 12px;
          font-weight: bold;
          padding-left:10px;
          margin-right:5px;
       }
       .tdOne{
          width:20%;
          text-align: right;
          font-weight: bold;
          padding-right:10px;
          background-color: #EDEDED;
       }
       .tdTwo{
          text-align:center;
          width:10%;
          
       }
      
		#zhi,#etime{
			display:none;
		}

    </style>
    <script type="text/javascript">
       function findFslq(){
           var sdate=document.getElementById("sdate").value;
	       var edate=document.getElementById("sdate").value;
		   if(compareDate(sdate,edate)){
				queryForm.submit();
			}else{
				queryForm.submit();
			}
       }

       function showPant(){
           var url="arcBaseInfoCharts!ywtjLast.action";
		   top.Dialog.open({URL:url,Width:1360,Height:600,Title:"未入库详细数据"});
       }
    </script>
  </head>
  
  <body>
<!-- 查询Start -->
	<div class="box2" panelTitle="档案业务归档统计" roller="false" >
		<form action="arcBaseInfoCharts!ywtjList.action" method="post" name="queryForm">
			<table>
				<tr>
				    <td>统计时间：</td>
					<td>
			          <input id="sdate" name="sdate"  type="text" class="date" value="${sdate}"/>
			        </td>
			        <td>至</td>
			        <td>
                       <input id="edate" name="edate"  type="text" class="date" value="${edate}"/>
                    </td>
					<td>
					  <button type="button" onclick="findFslq()" ><span class="icon_find">查询</span></button>
                    </td>
					<td>
					   <button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button>
					</td>
					<td>
					    <font style="color:red">**</font>系统默认统计昨天数据
					</td>
				</tr>
			</table>
	    </form>
	    <br/>
		<div>
			 温馨提醒：有<font style="color:red;">${count_last }</font>份档案已归档超过<font style="color:red;">一个月</font>仍未入库 &nbsp;&nbsp;&nbsp;&nbsp;<span style="color:blue;cursor:pointer;text-decoration: underline; " onclick="showPant()">点击查看&nbsp;>></span>
		</div>
	</div>
<!-- 查询End -->
<div id="scrollContent" >
	  <div class="box1" id="shw"> 
	      <div class="fslq_left">
			  <fieldset>
			   <legend>&nbsp;数据显示&nbsp;&nbsp;</legend>
			      
				   <div style="margin:10px 0px 10px 0px;">
				       <table width="100%"  class="bott">
						  <tr>
						    <td   class="tdOne">档案已审核数</td>
						    <td><font>${count_sh}</font>份</td>
						  </tr>
						  <tr>
						    <td   class="tdOne">档案已归档数</td>
						    <td><font>${count_gd}</font>份</td>
						  </tr>
						  
						  <tr>
						    <td   class="tdOne">档案已入库数</td>
						    <td><font>${count_rk}</font>份</td>
						  </tr>
						  
						  <tr>
						    <td class="tdOne">档案已归档但未入库数</td>
						    <td colspan="2" ><font>${fn:length(list_gd)}</font>份</td>
						  </tr>
						  <tr style="line-height: auto;">
						    <td class="tdOne">档案已归档，未入库数详细信息</td>
						    <td colspan="2" id="tab">
										<table width="100%" style="border:0px;">
										<tr>
											<th width="30">序号</th>
											<th>系统编号</th>
											<th>号牌号码</th>
											<th>号牌种类</th>
											<th>机动车序号</th>
											<th>档案编号</th>
											<th>档案归档人</th>
											
										</tr>
										<c:forEach var="entity" items="${list_gd}" varStatus="status">
											<tr>
											    <td align="center">${status.index+1}</td>
											    <td align="center">${entity.xtdabh}</td>
											    <td align="center">${entity.hphm }</td>
												<td align="center">&nbsp;${hpzlMap[entity.hpzl] }</td>
											    <td align="center">${entity.xh}</td>
											    <td align="center">${entity.dabh}</td>
												 <td align="center">${entity.gdrmc}</td>
											</tr>
										</c:forEach>
										
										<c:if test="${fn:length(list_data)==0}">
											<tr align="center">
												<td colspan="7">数据库无相关数据!</td>
											</tr>
										</c:if>				           
						        </table>       
						    </td>
						  </tr>
				       </table>
				   </div>
			  </fieldset>	 
		  </div> 
	  </div>



  </body>
</html>
