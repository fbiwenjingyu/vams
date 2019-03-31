/**基础扩展js包 v0.1*/

/**
 * 给String对象增加trim方法
 * */
String.prototype.trim = function() {
  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
};

/**
 * 判断以什么开头
 * */
String.prototype.startWith=function(str){
	  return new RegExp("^"+str).test(this);  
};

/**
 * 判断以什么结尾
 * */
String.prototype.endWith=function(str){
	  return new RegExp(str+"$").test(this);  
};

//=========================================================================================
  
/**判断回车事件*/
function checkEnter(ent){
	var kc = ent.charCode||ent.keyCode; 
//	alert(kc); 
    if(kc == "13") {
        return true;//是回车
    }else{
    	return false;//不是回车
    }
}

/**获取当前时间*/
function getNowFormatDate()
{
   var day = new Date();

   var Year = 0;
   var Month = 0;
   var Day = 0;
   var CurrentDate = "";
   //初始化时间   
   Year       = day.getFullYear();
   Month      = day.getMonth()+1;
   Day        = day.getDate();
   CurrentDate += Year + "-";
   
   if (Month >= 10 )
   {
    CurrentDate += Month + "-";
   }
   else
   {
    CurrentDate += "0" + Month + "-";
   }
   if (Day >= 10 )
   {
    CurrentDate += Day ;
   }
   else
   {
    CurrentDate += "0" + Day ;
   } 
   return CurrentDate;
}
/**
 * 日期比较
 */
function compareDate(sdate,edate){
	   var ddate=getNowFormatDate();
	   if(sdate!="" && edate!=""){
		   if(sdate>edate){
			   alert("开始时间不能大于结束时间");
			   return false;
		   }else{
			   
			   if(sdate>ddate || edate>ddate){
				   alert("开始时间或结束时间不能大于当天时间");
				   return false;
			   }else{
				   return true;
			   }
		   } 
	   }else{
		   //alert("rrr");
		   if(sdate>ddate || edate>ddate){
			   alert("开始时间或结束时间不能大于当天时间");
			   return false;
		   }else{
			   return true;
		   }
		  
	   }
}

/**
 * checkbox只允许选择一条记录
 * @return
 */
 function checkOne(){
            	$(':checkbox[name=indexnum]').each(function(){            
                	$(this).click(function(){                
                    	if($(this).attr('checked')){                    
                        	$(':checkbox[name=indexnum]').removeAttr('checked');                    
                        	$(this).attr('checked','checked');                
                        }            
                     });        
                });
}
/**
 * 表单重置 张玲
 * 
 * @return
 */
function resetSearch() {
	$("input[type!='hidden']").val('');
	$("#selector select").resetValue();
}

/**提交selector表单*/
function submitSelector(fuzzy){
	var $inps=$("#selector").find("input");
	var $sels=$("#selector").find("select");
	var test="";
	var sel_val="";
	for(var i=0;i<$inps.length;i++){
		var va=$inps[i].value.trim();
		if (va!="请选择" && va!="") {
			test+=va;
		}
	}
	for(var i=0;i<$sels.length;i++){
		sel_val=$sels[i].value.trim();
	}
	
	if(""==test&&""==sel_val){
		var url=$("form[name='queryForm']").attr("action");
		location.href=url+"?index=1";  
	}else{
		queryForm.submit();
	}
}



/**
 * 屏蔽页面中的 backspace 返回上一页的问题
 */
function onkeydownBody() {
	if (event.keyCode == 8) {
		if (document.activeElement.type == "text" || document.activeElement.type == "password"|| document.activeElement.type == "textarea") {
			if (document.activeElement.readOnly == false)
				return true;
		}
		return false;
	}
}


/**
 * 显示遮罩
 * @author wangwei
 * 2013-7-31
 */
function showScreenOver(){
	if(top.document.getElementById("screenOver")!=null){
		return;	
	}
	var div=top.document.createElement("div"); 
	div.style.width=top.document.documentElement.scrollWidth+"px"; 
	div.style.height=top.document.documentElement.scrollHeight+"px"; 
	div.style.backgroundColor="gray"; 
	div.style.position="absolute"; 
	div.style.left=0; 
	div.style.top=0; 
	div.id="screenOver";
	div.style.zIndex=10;
	if(top.document.all) 
	div.style.filter = "alpha(opacity=30)"; 
	else div.style.opacity = .3; 
	top.document.getElementById("mainFrame").appendChild(div); 
} 

/**
 * 检测字数
 * @param inp 输入内容的对象this
 * @param span_id 要显示字数的span的id
 * @param word_length 字数上限
 */
function check_word(inp,span_id,word_length){
	var inp_val=$(inp).val().trim();
	//超过字数长度
	if(inp_val.length>word_length){
		$("#"+span_id).html(inp_val.length).css("color","red");
	}else{
		$("#"+span_id).html(inp_val.length).css("color","black");
	}
}


/**
 * 防止冒泡事件的传递，请在方法结束时调用
 */
function stopBubble(e){
   if (e && e.stopPropagation)  
       e.stopPropagation();
   else 
       window.event.cancelBubble=true ;
} 


/**锚点跳转，定位
*@param type 要定位的标签的属性名
*@param val 要定位的标签的属性值
*/
function locateA(type,val){
//	document.getElementById(id).scrollIntoView();
	var $dd=$("["+type+"='"+val+"']");
	if($dd.length>0){
		$dd[0].scrollIntoView();
	}
}

/**
 * 关闭弹出窗口
 * 
 * @return
 */
function closeWin() {
	top.Dialog.close();
}

/**
 * 刷新列表页
 * */
function freshTop(url){
	top.frmright.window.location.href=url;
}

/**
 * 关闭弹出窗口并刷新列表页
 * */
function freshAndClose(url){
	freshTop(url);
	closeWin();
}


/**
 * 关闭弹出窗口，提示信息
 * */
function freshAndCloseByInfo(url,msg){
	alert(msg);
	freshAndClose(url);
}

/**
 * 分页提交方法
 */
function page_query(index) {
	$("input[name='index']").val(index);
	queryForm.submit();
}

function tr_click(index){
	$(':checkbox[name=indexnum]').each(function(){            
    	if($(this).attr('checked')){                    
			$(':checkbox[name=indexnum]').removeAttr('checked');                    
        }            
    });
	$("#ck_"+index).attr('checked','checked');  
}

function checkOneIndex(index){
	$(':checkbox[name=indexnum]').each(function(){            
    	if($(this).attr('checked')){                    
			$(':checkbox[name=indexnum]').removeAttr('checked');                    
        }            
    });
	$("#ck_"+index).attr('checked','checked');  
}


/**
 * 在二级弹窗上获取父窗口的页面元素，需要在回调函数中进行判断是否为需要的iframe
 * */
function getParentWindow(callback){
	var $ifr=$(top.document).find("iframe[id!='frmright']");
	try {
		//当获取到iframe
		if($ifr.length!=0){
			for(var i=0;i<$ifr.length;i++){//循环取出页面上的iframe
				if(callback($($ifr[i].contentWindow.document))){
					//如果返回true将跳出循环
					break;
				};
			}
		}else{//未获取到iframe
			callback(null);
		}
	} catch (e) {
		alert("父窗口获取异常："+e.message);
	}
}

/**
 * 在二级弹窗上获取父窗口的页面元素，需要在回调函数中进行判断是否为需要的iframe
 * */
function getParentContentWindow(callback){
	var $ifr=$(top.document).find("iframe[id!='frmright']");
	try {
		//当获取到iframe
		if($ifr.length!=0){
			for(var i=0;i<$ifr.length;i++){//循环取出页面上的iframe
				if(callback($ifr[i].contentWindow)){
					break;
				}
			}
		}else{//未获取到iframe
			callback(null);
		}
	} catch (e) {
		alert("父窗口获取异常："+e.message);
	}
}
