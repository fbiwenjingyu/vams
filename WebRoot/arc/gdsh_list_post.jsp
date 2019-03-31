<%@ page language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String preAction = "arcBaseInfo";
String str= getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>归档审核列表</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/taglib.jsp"%>
<script language="javascript"><!--
	
	var dialog;
	
	function closeDialog(){
		dialog.close();
	}
	
	//分页
   	function page_query(index){
   		$("input[name='index']").val(index);
   		var sdate=$("#sdate").val();
		var edate=$("#edate").val();
		//获取时间验证
		if(compareDate(sdate,edate)){
			queryForm.submit();
	    }
   	}
   	
   	$.ajax({
	  	type:"POST",
	  	url:"codeSet!getSelectByCode.action?entity.type=2",
	  	success:function(data){
   			var seldata = "{\"list\":"+data+"}";
	  		$("#typeId").data("data",JSON.parse(seldata));
	  		$("#typeId").render();
		}
	});
	
	$.ajax({
	  	type:"POST",
	  	url:"codeSet!getSelectByCode.action?entity.type=3",
	  	success:function(data){
   			var seldata = "{\"list\":"+data+"}";
	  		$("#hpzlId").data("data",JSON.parse(seldata));
	  		$("#hpzlId").render(); 
		}
	});
	
	
	
	//双击审核  
	function tr_ondbclick(wjbh,arcType,lsh,clsbdh){
	 	$.ajax({
			type: "POST",
			url: "<%=preAction %>!checkIsBlackCar.action",
			data:{clsbdh:clsbdh},
			dataType:"json",
			success: function(msg){ 
				if(msg=="200"){
					top.Dialog.alert("该车辆为嫌疑车牌，无法审核!");
					return;
				}else if(msg=="300"){
			//arc_type 1:业务档案  2：历史档案    ----历史档案可以直接审核
	   	    if(arcType=="1"){
		        
			    //查询外检是否合并、是否审核通过
				$.ajax({
					type: "POST",
					url: "<%=preAction %>!queryWjbhResult.action",
					data:{wjbh:wjbh,lsh:lsh},
					dataType:"json",
					success: function(msg){ 
					
						if(msg=="201"){
						               top.Dialog.confirm("系统编号和流水号未合并，现在合并？",function(){
						               // var url="<%=preAction %>!andHb.action?wjtm="+wjbh+"&lsh="+lsh;
						                var url="<%=str %>/wjyw/ycInfo_barCode.jsp?url=arc&xtdabh="+wjbh;
						                top.Dialog.open({URL:url,Title:"合并条码",Width:800,Height:450});
								        //dialog = new top.Dialog();
										//		dialog.URL=url;
										//		dialog.Width=800;
										//		dialog.Height=450;
										//		dialog.ID="d3";
										//		dialog.CancelEvent = function(){
										//			dialog.close();
										//			removeScreenOver();
										//			window.location.href="arcBaseInfo!query_sh.action";
										//		};
										//		dialog.show();
						                    });	
							  return ;
						}else if(msg=="200"){
						      top.Dialog.alert("外检审核不通过，不能档案审核");
							  return ;
					    }else if(msg=="202"){
						      top.Dialog.alert("外检未被审核，不能档案审核");
							  return ;
						}else if(msg=="205"){
						      top.Dialog.alert("外检未上传图片，不能档案审核");
							  return ;
						}else if(msg=="206"){
							     top.Dialog.alert("数据异常");
								 return ;
					    }else if(msg=="203" || msg=="204"){
								$.ajax({
									type: "POST",
									url: "<%=preAction %>!queryScanResult.action",
									data:{wjbh:wjbh},
									dataType:"json",
									success: function(msg){ 
										//if(msg=="200"){
						       			//	top.Dialog.alert("该档案没有扫描，请进行扫描再审核!");   
						       			//	return ;     
										//}else 
										if(msg=="201"){
						     			 	top.Dialog.alert("档案已审核，不能再次审核");
							  				return ;
					    				}else if(msg=="202"){
						      				top.Dialog.alert("档案已审核，不能再次审核");
							  				return ;
										}else if(msg=="204"){
						      				top.Dialog.alert("数据异常");
							  				return ;
										}else if(msg=="200" || msg=="203"){ //档案还未审核
						    				var url="<%=preAction %>!getEntityById.action?selectType=1&wjbh="+wjbh+"&toPage=gdsh";
											dialog = new top.Dialog();
											dialog.URL=url;
											dialog.Width=1300;
											dialog.Height=550;
											dialog.ID="d1";
											dialog.CancelEvent = function(){
												dialog.close();
												removeScreenOver();
								  			    };
							    			dialog.show(); 
						           		}
									}
			  					  }); 
						}
					}
			  }); 
			  
	    }else if(arcType=="2"){
	        //历史档案直接审核
	               $.ajax({
					type: "POST",
					url: "<%=preAction %>!queryScanResult.action",
					data:{wjbh:wjbh},
					dataType:"json",
					success: function(msg){ 
						//if(msg=="200"){
						 //      top.Dialog.alert("该档案没有扫描，请进行扫描再审核!");   
						//       return ;     
						//}else 
						if(msg=="201"){
						      top.Dialog.alert("档案已审核，不能再次审核");
							  return ;
					    }else if(msg=="202"){
						      top.Dialog.alert("档案已审核，不能再次审核");
							  return ;
						}else if(msg=="204"){
						      top.Dialog.alert("数据异常");
							  return ;
						}else if(msg=="200" || msg=="203"){ //档案还未审核
						    	var url="<%=preAction %>!getEntityById.action?selectType=1&wjbh="+wjbh+"&toPage=gdsh";
								dialog = new top.Dialog();
								dialog.URL=url;
								dialog.Width=1300;
								dialog.Height=550;
								dialog.ID="d1";
								dialog.CancelEvent = function(){
									dialog.close();
									removeScreenOver();
								   };
							    dialog.show(); 
						}
					}
			  });
	    }else{
	        top.Dialog.alert("数据异常");
			return ;
	    }
				}
			}
		});
	       
	
	}
	
	//审核
	function getSelected(obj){
	    //得到参数   wjbh ,arc_type
	    var val="";
		var count=0;
		var id="";
		var verifyResul="";
		var wjbh="";
		var clsbdh="";
		var arcType="";
		var lsh="";
		var deptCode =  $("#deptcode").val();
		
		$.each($("input[name='indexnum']:checked"),function(i,n){
			val= n.value;
			id = $('#id'+val).val();
			verifyResul = $('#verifyResul'+val).val();
			wjbh = $('#wjbh'+val).val();
			clsbdh = $('#clsbdh'+val).val();
			lsh = $('#lsh'+val).val();
			arcType = $('#arcType'+val).val();
			count ++;
		});
		
		if(count==0){
			top.Dialog.alert("请选择数据后再操作!");
			return;
		}
		
		$.ajax({
				type: "POST",
				url: "<%=preAction %>!checkIsBlackCar.action",
				data:{clsbdh:clsbdh},
				dataType:"json",
				success: function(msg){ 
					if(msg=="200"){
						 top.Dialog.alert("该车辆为嫌疑车牌，无法审核!");
						 return;
					}else if(msg=="300"){
							    //arc_type 1:业务档案  2：历史档案    ----历史档案可以直接审核
	    		if(arcType=="1"){
		        
			    //查询外检是否合并、是否审核通过
				$.ajax({
					type: "POST",
					url: "<%=preAction %>!queryWjbhResult.action",
					data:{wjbh:wjbh,lsh:lsh},
					dataType:"json",
					success: function(msg){ 
					
						if(msg=="201"){
						               top.Dialog.confirm("系统编号和流水号未合并，现在合并？",function(){
						                var url="<%=str %>/wjyw/ycInfo_barCode.jsp?url=arc&xtdabh="+wjbh;
						                top.Dialog.open({URL:url,Title:"合并条码",Width:800,Height:450});
						               // var url="<%=preAction %>!andHb.action?wjtm="+wjbh+"&lsh="+lsh;
								       // dialog = new top.Dialog();
										//		dialog.URL=url;
										//		dialog.Width=800;
										//		dialog.Height=450;
										//		dialog.ID="d3";
										//		dialog.CancelEvent = function(){
										//			dialog.close();
										//			removeScreenOver();
										//			window.location.href="arcBaseInfo!query_sh.action";
										//		};
										//		dialog.show();
						                    });	
							  return ;
						}else if(msg=="200"){
						      top.Dialog.alert("外检审核不通过，不能档案审核");
							  return ;
					    }else if(msg=="202"){
						      top.Dialog.alert("外检未被审核，不能档案审核");
							  return ;
						}else if(msg=="205"){
						      top.Dialog.alert("外检未上传图片，不能档案审核");
							  return ;
						}else if(msg=="206"){
							     top.Dialog.alert("数据异常");
								 return ;
					    }else if(msg=="203" || msg=="204"){
							if(obj =='verify'){
								$.ajax({
									type: "POST",
									url: "<%=preAction %>!queryScanResult.action",
									data:{wjbh:wjbh},
									dataType:"json",
									success: function(msg){ 
										//if(msg=="200"){
						       			//	top.Dialog.alert("该档案没有扫描，请进行扫描再审核!");   
						       			//	return ;     
										//}else 
										if(msg=="201"){
						     			 	top.Dialog.alert("档案已审核，不能再次审核");
							  				return ;
					    				}else if(msg=="202"){
						      				top.Dialog.alert("档案已审核，不能再次审核");
							  				return ;
										}else if(msg=="204"){
						      				top.Dialog.alert("数据异常");
							  				return ;
										}else if(msg=="200" || msg=="203"){ //档案还未审核
						    				var url="<%=preAction %>!getEntityById.action?selectType=1&wjbh="+wjbh+"&toPage=gdsh&deptCode=" + deptCode;
											dialog = new top.Dialog();
											dialog.URL=url;
											dialog.Width=1300;
											dialog.Height=550;
											dialog.ID="d1";
											dialog.CancelEvent = function(){
												dialog.close();
												removeScreenOver();
								  			    };
							    			dialog.show(); 
						           		}
									}
			  					  }); 
								}	
						}
					}
			  }); 
			  
	    }else if(arcType=="2"){
	         //历史档案直接审核
           if(obj =='verify'){
             $.ajax({
					type: "POST",
					url: "<%=preAction %>!queryScanResult.action",
					data:{wjbh:wjbh},
					dataType:"json",
					success: function(msg){ 
						if(msg=="200"){
						       //top.Dialog.alert("该档案没有扫描，请进行扫描再审核!");   
						      // return ;     
						}else if(msg=="201"){
						      top.Dialog.alert("档案已审核，不能再次审核");
							  return ;
					    }else if(msg=="202"){
						      top.Dialog.alert("档案已审核，不能再次审核");
							  return ;
						}else if(msg=="204"){
						      top.Dialog.alert("数据异常");
							  return ;
						}else if(msg=="203"){ //档案还未审核
						    	var url="<%=preAction %>!getEntityById.action?selectType=1&wjbh="+wjbh+"&toPage=gdsh";
								dialog = new top.Dialog();
								dialog.URL=url;
								dialog.Width=1300;
								dialog.Height=550;
								dialog.ID="d1";
								dialog.CancelEvent = function(){
									dialog.close();
									removeScreenOver();
								   };
							    dialog.show(); 
						}
					}
			  }); 
			 }				
	    }else{
	    	 top.Dialog.alert("数据异常");
			 return ;
	    }	
					}
			   }	
		});   	
		
  
	}
	
	//外检审核
	function pencil(){
	        //获取参数
	        //得到参数   wjbh ,arc_type
		    var val="";
			var count=0;
			var id="";
			var verifyResul="";
			var wjbh="";
			var clsbdh="";
			var arcType="";
			var lsh=""
			$.each($("input[name='indexnum']:checked"),function(i,n){
				val= n.value;
				id = $('#id'+val).val();
				verifyResul = $('#verifyResul'+val).val();
				wjbh = $('#wjbh'+val).val();
				clsbdh = $('#clsbdh'+val).val();
				lsh = $('#lsh'+val).val();
				arcType = $('#arcType'+val).val();
				count ++;
			});
			
			if(count==0){
				top.Dialog.alert("请选择数据后再操作!");
				return;
			}
			
			$.ajax({
				type: "POST",
				url: "<%=preAction %>!checkIsBlackCar.action",
				data:{clsbdh:clsbdh},
				dataType:"json",
				success: function(msg){ 
					if(msg=="200"){
						 top.Dialog.alert("该车辆为嫌疑车牌，无法审核!");
						 return;
					}else if(msg=="300"){
					   	      //1 业务档案  2历史档案
	      			if(arcType=="1"){
			           
	                    $.ajax({
						type: "POST",
						url: "<%=preAction %>!queryWjbhResult.action",
						data:{wjbh:wjbh,lsh:lsh},
						dataType:"json",
						success: function(msg){  
							if(msg=="201"){
							      //top.Dialog.alert("外检条码未合并,不能外检审核");
				                top.Dialog.confirm("系统编号和流水号未合并，现在合并？",function(){
				                var url="<%=str %>/wjyw/ycInfo_barCode.jsp?url=arc&xtdabh="+wjbh;
						        top.Dialog.open({URL:url,Title:"合并条码",Width:800,Height:450});
				                //var url="<%=preAction %>!andHb.action?wjtm="+wjbh+"&lsh="+lsh;
						       // dialog = new top.Dialog();
								//		dialog.URL=url;
								//		dialog.Width=800;
								//		dialog.Height=450;
								////		dialog.ID="d3";
								//		dialog.CancelEvent = function(){
								//			dialog.close();
								//			removeScreenOver();
								//			window.location.href="arcBaseInfo!query_sh.action";
								///		};
								//		dialog.show();
				                    });	
								  return ;
							}else if(msg=="204"){
							      top.Dialog.confirm("该档案没有外检业务,可以直接档案审核",function(){
						             getSelected("verify");
						          });
								  return ;
						    }else if(msg=="205"){
							     top.Dialog.alert("外检未上传图片，不能外检审核");
								  return ;
						    }else if(msg=="200"){
							     top.Dialog.alert("外检已审核，不能再次审核");
								 return ;
						    }else if(msg=="203"){
							     top.Dialog.alert("外检已审核，不能再次审核");
								 return ;
						    }else if(msg=="206"){
							     top.Dialog.alert("数据异常");
								 return ;
						    }else if(msg=="202"){
						        //弹窗
						        var url="<%=preAction %>!showPic.action?wjbh="+wjbh
								dialog = new top.Dialog();
								dialog.URL=url;
								dialog.Width=1300;
								dialog.Height=550;
								dialog.ID="w1";
								dialog.CancelEvent = function(){
									dialog.close();
									removeScreenOver();
								};
								dialog.show();
						    }
					  }
			    });
		 }else if(arcType=="2"){
		     top.Dialog.confirm("该档案为历史档案，可以 直接档案审核？",function(){
			     getSelected("verify");
			 });
			 return;
		 }else{
		 	top.Dialog.alert("数据异常");
			return ;	
		 }
					}
				}
			});
					
					

	}
	
	//综合审核
	function view(){
		    /**
		      1-业务档案  2-历史档案
		                    档案信息、外检信息审核界面
		    */
		    var val="";
			var count=0;
			var id="";
			var verifyResul="";
			var wjbh="";
			var clsbdh="";
			var lsh="";
			var arcType="";
			var deptCode =  $("#deptcode").val();
			$.each($("input[name='indexnum']:checked"),function(i,n){
				val= n.value;
				id = $('#id'+val).val();
				verifyResul = $('#verifyResul'+val).val();
				wjbh = $('#wjbh'+val).val();
				clsbdh = $('#clsbdh'+val).val();
				lsh = $('#lsh'+val).val();
				arcType = $('#arcType'+val).val();
				count ++;
			});
			
			if(count==0){
				top.Dialog.alert("请选择数据后再操作!");
				return;
			}
			
			$.ajax({
				type: "POST",
				url: "<%=preAction %>!checkIsBlackCar.action",
				data:{clsbdh:clsbdh},
				dataType:"json",
				success: function(msg){ 
					if(msg=="200"){
						 top.Dialog.alert("该车辆为嫌疑车牌，无法审核!");
						 return;
					}else if(msg=="300"){
		  //1 业务档案  2历史档案
	      if(arcType=="1"){
	           
						        
			    //查询外检是否合并、是否审核通过
				$.ajax({
					type: "POST",
					url: "<%=preAction %>!queryWjbhResult.action",
					data:{wjbh:wjbh,lsh:lsh},
					dataType:"json",
					success: function(msg){ 
					
						if(msg=="201"){
						               top.Dialog.confirm("外检条码或流水号未合并，现在合并？",function(){
						                var url="<%=str %>/wjyw/ycInfo_barCode.jsp?url=arc&xtdabh="+wjbh;
						                top.Dialog.open({URL:url,Title:"合并条码",Width:800,Height:450});
						                //var url="<%=preAction %>!andHb.action?wjtm="+wjbh+"&lsh="+lsh;
								        //dialog = new top.Dialog();
										//		dialog.URL=url;
										//		dialog.Width=800;
										//		dialog.Height=450;
										//		dialog.ID="d3";
										//		dialog.CancelEvent = function(){
										//			dialog.close();
										//			removeScreenOver();
										//			window.location.href="arcBaseInfo!query_sh.action";
										//		};
										//		dialog.show();
						                    });	
							  return ;
						}else if(msg=="200"){
						      top.Dialog.alert("外检审核不通过，不能综合审核");
							  return ;
					    }else if(msg=="205"){
						      top.Dialog.alert("外检未上传图片，不能综合审核");
							  return ;
						}else if(msg=="206"){
							     top.Dialog.alert("数据异常");
								 return ;
					    }else if(msg=="202" || msg=="203" || msg=="204"){
								$.ajax({
									type: "POST",
									url: "<%=preAction %>!queryScanResult.action",
									data:{wjbh:wjbh},
									dataType:"json",
									success: function(msg){ 
										//if(msg=="200"){
						       			//	top.Dialog.alert("该档案没有扫描，请进行扫描再审核!");   
						       			//	return ;     
										//}else 
										if(msg=="201"){
						     			 	top.Dialog.alert("档案已审核，不能再次审核");
							  				return ;
					    				}else if(msg=="202"){
						      				top.Dialog.alert("档案已审核，不能再次审核");
							  				return ;
										}else if(msg=="204"){
						      				top.Dialog.alert("数据异常");
							  				return ;
										}else if(msg=="200" || msg=="203"){ //档案还未审核
						    				var url="<%=preAction %>!getEntityByIdZh.action?selectType=1&wjbh="+wjbh+"&toPage=gdsh&deptCode=" + deptCode;
											dialog = new top.Dialog();
											dialog.URL=url;
											dialog.Width=1300;
											dialog.Height=550;
											dialog.ID="d1";
											dialog.CancelEvent = function(){
												dialog.close();
												removeScreenOver();
								  			    };
							    			dialog.show(); 
						           		}
									}
			  					  }); 
						}
					}
			  }); 
	      }else{
			    top.Dialog.confirm("该档案为历史档案，可以 直接档案审核？",function(){
			         getSelected("verify");
			    });
				return;
	      }
					}
				}
			});
				
	}
	
	
	function suspicion(){
	        //获取参数
	        //得到参数   wjbh ,arc_type
		    var val="";
			var count=0;
			var id="";
			var verifyResul="";
			var wjbh="";
			var clsbdh="";
			var arcType="";
			var lsh=""
			$.each($("input[name='indexnum']:checked"),function(i,n){
				val= n.value;
				id = $('#id'+val).val();
				wjbh = $('#wjbh'+val).val();
				clsbdh = $('#clsbdh'+val).val();
				arcType = $('#arcType'+val).val();
				count ++;
			});
			
			if(count==0){
				top.Dialog.alert("请选择数据后再操作!");
				return;
			}
			
			$.ajax({
				type: "POST",
				url: "<%=preAction %>!checkIsBlackCar.action",
				data:{clsbdh:clsbdh},
				dataType:"json",
				success: function(msg){ 
					if(msg=="200"){
						 top.Dialog.alert("该车辆已设置为嫌疑车辆!");
						 return;
					}else if(msg=="300"){	
					      top.Dialog.confirm("设置该车辆为嫌疑车辆？",function(){
			                 	$.ajax({
									type: "POST",
									url: "<%=preAction %>!setToSuspicion.action",
									data:{xtdabh:wjbh},
									dataType:"json",
									success: function(msg){ 
										if(msg=="200"){
						       				top.Dialog.alert("设置成功!");   
						       				return ;     
										}else if(msg=="201"){
						     			 	top.Dialog.alert("设置失败!");
							  				return ;
					    				}
									}
			  					  });
			               });
					}       
				}   
				              
			  });
	     }
	     
	     function viewPic(){
	     	var xtdabh = "";
	     	var count=0;
	     	$.each($("input[name='indexnum']:checked"),function(i,n){
				val= n.value;
				xtdabh = $('#wjbh'+val).val();
				count ++;
			});
			
			if(count==0){
				top.Dialog.alert("请选择数据后再操作!");
				return;
			}
	     	var diag = new top.Dialog();
				diag.Title = val+"扫描页信息";
				diag.URL = "arcBaseInfo!getPicsByXtdabh.action?selectType=all&xtdabh="+xtdabh; 
				diag.Width=1300;
				diag.Height=550;
				diag.ShowMaxButton=false;
				diag.ShowMinButton=false;
				diag.ShowButtonRow=true;
				diag.ShowOkButton=false;
				diag.CancelButtonText="&nbsp;关&nbsp;&nbsp;闭&nbsp;";
				diag.show();
	     }
	     
	     function tmcj(){
	     	var xtdabh = "";
	     	var lsh = "";
	     	var count=0;
	     	$.each($("input[name='indexnum']:checked"),function(i,n){
				val= n.value;
				xtdabh = $('#wjbh'+val).val();
				lsh = $('#lsh'+val).val();
				count ++;
			});
			
			if(count==0){
				top.Dialog.alert("请选择数据后再操作!");
				return;
			}
			
			var url="arc/wjcy_cjtm.jsp?xtdabh=" + xtdabh + "&lsh=" + lsh;
       			dialog = new top.Dialog();
					dialog.URL=url;
					dialog.Width=800;
					dialog.Height=450;
					dialog.ID="d3";
					dialog.CancelEvent = function(){
						dialog.close();
						removeScreenOver();
						window.location.href="<%=path%>/system/layout/open.jsp";
					};
					dialog.show();
	     }
	     
	     function withBarCode(){
	     	var xtdabh = "";
	     	var lsh = "";
	     	var count=0;
	     	$.each($("input[name='indexnum']:checked"),function(i,n){
				val= n.value;
				xtdabh = $('#wjbh'+val).val();
				lsh = $('#lsh'+val).val();
				count ++;
			});
			
			if(count==0){
				top.Dialog.alert("请选择数据后再操作!");
				return;
			}
			
			if(lsh== "" || lsh.length == 0){
			
			}else{
				top.Dialog.alert("该记录已经合并过!");
				return;
			}
			
			
			var url="<%=str %>/wjyw/ycInfo_barCode.jsp?xtdabh="+xtdabh;
		  	top.Dialog.open({URL:url,Title:"数据查验",Width:800,Height:450});
	     }
	     
	     function printCode(){
	     	var xtdabh = "";
	     	var count=0;
	     	$.each($("input[name='indexnum']:checked"),function(i,n){
				val= n.value;
				xtdabh = $('#wjbh'+val).val();
				count ++;
			});
			
			if(count==0){
				top.Dialog.alert("请选择数据后再操作!");
				return;
			}
			top.Dialog.open({URL:"<%=str %>/wjyw/printCode.jsp?code="+xtdabh,ID:"checkView",Title:"打印条码",Width:"400px;",Height:"200px;"});
	     }
	     
	
--></script>
</head>
<body>

<div id="searchPanel" class="box2" panelTitle="查询" roller="false">
	<form action="<%=preAction %>!query_sh.action" method="post" name="queryForm">
	<input name="index" type="hidden" value="${page.index }" />
	<table>
		<tr>
			<td>流水号：</td>
			<td><input name="entity.lsh" type="text" value="${entity.lsh }" style="width:126px"/></td>
			<td>&nbsp;&nbsp;系统编号：</td>
			<td>
				<input name="entity.xtdabh" type="text" value="${entity.xtdabh }" style="width:126px"/>
			</td>
			<td>业务类型：</td>
			<td>
				<select id="typeId" name="entity.ywlx" selectedValue="${entity.ywlx }" prompt="请选择" ></select>
			</td>
			<td>审核人：</td>
			<td>
				<input name="entity.shr" type="text" value="${entity.shr }" style="width:126px"/>
			</td>	
			<td>机构名称：</td>
			<td>
				<select  id="deptcode" name="entity.deptcode"  prompt="请选择" data='${vs:deptSelect()}' selectedValue="${entity.deptcode}"></select>
			</td>
				
		</tr>
		<tr>
			<td>机动车序号：</td>
			<td><input name="entity.xh" type="text" value="${entity.xh }" style="width:126px"/></td>
			<td>号牌种类：</td>
			<td>
				<select id="hpzlId" name="entity.hpzl" selectedValue="${entity.hpzl }" prompt="请选择" ></select>
			</td>
			<td>号牌号码：</td>
			<td><input name="entity.hphm" type="text" value="${entity.hphm }" style="width:126px"/></td>
			<td>办理时间：</td>
			<td>
				<input id="sdate" type="text" name="sdate" class="date"  style="width:126px" value='<fmt:formatDate value="${sdate }" pattern="yyyy-MM-dd" type="date"/>'/>
			</td>
			<td align="center">
			           至
			</td>
			<td>
				<input id="edate" type="text" name="edate" class="date"  style="width:126px" value='<fmt:formatDate value="${edate }" pattern="yyyy-MM-dd" type="date"/>' />
			</td>
			<td><button type="button" onclick="page_query(1)"><span class="icon_find">查询</span></button></td>
			<td><button type="button" onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>
		</tr>
	</table>
</form>
</div>

<div id="operation" class="box_tool_min">
	<div class="center">
	<div class="right">
	    <div class="box_tool_line"></div>
		<a href="javascript:;" onclick="pencil()"><span class="icon_pencil">外检审核</span></a>
		
	    <div class="box_tool_line"></div>
		<a href="javascript:;" onclick="getSelected('verify')"><span class="icon_add">档案审核</span></a>
		
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="view()"><span class="icon_view">综合审核</span></a>
		
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="suspicion()"><span class="icon_item">嫌疑设置</span></a>
		
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="viewPic()"><span class="icon_view">查看照片</span></a>
		
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="withBarCode()"><span class="icon_item">数据验证(条码合并)</span></a>
		
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="tmcj()"><span class="icon_item">条码拆解</span></a>
		
		<div class="box_tool_line"></div>
		<a href="javascript:;" onclick="printCode()"><span class="icon_print">打印条码</span></a>
	</div>	
	</div>
	<div class="clear"></div>
</div> 

<div id="scrollContent" class="table_bor">
	<table class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="30"><span>序号</span></th>
			<th width="30">选择</th>
			<th><span>流水号</span></th>
			<th><span>系统编号</span></th>
			<th><span>储位编号</span></th>
			<th><span>机动车序号</span></th>
			<th><span>业务类型</span></th>
			<th><span>号牌种类</span></th>
			<th><span>号牌号码</span></th>
			<th><span>机构名称</span></th>
			<th><span>业务办理时间</span></th>
			<th><span>业务办理人</span></th>
			<th><span>审核人</span></th>
			<th><span>外检上传人</span></th>
			<th><span>审核时间</span></th>
			<th><span>档案状态</span></th>
		</tr>
		<c:forEach var="entity" items="${list_data}" varStatus="status">
			<tr align="center" onclick="tr_click('${status.index}')"  ondblclick="tr_ondbclick('${entity.xtdabh }','${entity.dalx}','${entity.lsh}','${entity.clsbdh}')" >
				<td align="center">&nbsp;${status.index+1}</td>
				<td align="center">
					<input id="ck_${status.index}" type="checkbox" name="indexnum"  value="${status.index }" onclick="checkOneIndex('${status.index}');" />
					<input type="hidden" id="id${status.index }" name="entity.id"  value="${entity.id}"/>
					<input type="hidden" id="verifyResul${status.index }" name="entity.verifyResult"  value="${entity.yc_shjg}"/>
					<input type="hidden" id="wjbh${status.index }" name="entity.verifyResult"  value="${entity.xtdabh }"/>
					<input type="hidden" id="arcType${status.index }" name="entity.verifyResult"  value="${entity.dalx }"/>
					<input type="hidden" id="lsh${status.index }" name="entity.verifyResult"  value="${entity.lsh }"/>
					<input type="hidden" id="clsbdh${status.index }" name="clsbdh"  value="${entity.clsbdh }"/>
				</td>
				<td align="center">&nbsp;${entity.lsh}</td>
				<td align="center">&nbsp;${entity.xtdabh} </td>
				<td align="center">&nbsp;${entity.cwbh} </td>
				<td align="center">&nbsp;${entity.xh}</td>
				<td align="center">&nbsp;${ywlxMap[entity.ywlx] }</td>
				<td align="center">&nbsp;${hpzlMap[entity.hpzl] }</td>
				<td align="center">&nbsp;${entity.hphm}</td>
				<!--<td align="center">&nbsp;${user2CnDeptCode[entity.cjr] }</td>-->
				<td align="center">&nbsp;${deptMap[entity.deptcode]}</td>
				<td align="center">&nbsp; <fmt:formatDate value="${entity.cjsj}" type="both"/></td>
				<td align="center">&nbsp;${user2CnName[entity.cjr] }</td>
				<td align="center">&nbsp;${user2CnName[entity.shr]}</td>
				<td align="center">&nbsp;${user2CnName[entity.yc_cjr]}</td>
				<td align="center">&nbsp;<fmt:formatDate value="${entity.shsj}" type="both" /></td>
				<td align="center">&nbsp;${arcStatus[entity.dazt] }</td>
		   </tr>
		</c:forEach>
		<c:if test="${fn:length(list_data)==0}">
			<tr align="center">
				<td colspan="18">数据库无相关数据!</td>
			</tr>
		</c:if>
	</table>
</div>

<div class="page_bottom">
	<span>${page.pageDisplay}</span>
</div>
</body>
</html>
