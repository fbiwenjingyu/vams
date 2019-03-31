/**
 * 分页、基本UI控制
 */

//================================分页页码，查询框、页脚、表格样式调整JS：BEGIN=========================================
/**
 * 滚动区域适应滚动
 * */
var auto_scroll_content=true;
/**
 * 设置列表页滚动框大小
 * */
// 分页页脚高度
var page_bottom;
// 顶端查询框高度
var query_top;
//操作区域高度
var operation_heigh;
/**
 * 计算列表区域高度，框架默认方法名为customHeightSet(contentHeight)
 * */
function customHeightSet(contentHeight,contentWidth) {
	if(auto_scroll_content){
		// 分页页脚高度
		page_bottom = $(".page_bottom").height() == null ? 0
				: $(".page_bottom").height();
		// 顶端查询框高度
		query_top = $("#searchPanel").height() == null ? 0 : $("#searchPanel").height();
		//操作区域高度
		operation_heigh = $("#operation").height() == null ? 0 : $("#operation").height();
		//中间滚动列表将上下区域高度减掉，最后减掉一个修正值
		var sch=contentHeight - page_bottom - query_top - operation_heigh - 8;
		$("#scrollContent").height(sch);
	}
}

/**
 * 带有查询框的滚动自适应
 */
function initComplete(){
    $("#searchPanel").bind("stateChange",function(e,state){
       if(state=="hide"){
    	   query_top=$("#searchPanel").height()==null?0:$("#searchPanel").height();
       }
       else if(state=="show"){
    	   query_top=$("#searchPanel").height()==null?0:$("#searchPanel").height();
       }
       triggerCustomHeightSet();
   });
   
}

//==================================
/**
 * 分页提交方法
 */
function page_query(index) {
	$("input[name='index']").val(index);
	$("form[name='queryForm']").submit();
}

//===================================分页页码，查询框、页脚、表格样式调整JS：END==========================================================

/**
 * 关闭本页面
 * */
function closeWin(){
	top.Dialog.close();
}
/**
 * 刷新父页面
 * */
function freshTop(url){
	top.frmright.window.location.href=url;
}

/**
 * 关闭窗口并刷新父页面
 * */
function closeAndFresh(url){
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

/**刷新当前页*/
function freshFrom(){
	$("form[name='queryForm']").submit();
}

/**
 * 刷新URL
 * */
function freshUrl(){
	location.href = location.href.replace(/#/g,'');
}

/**
 * 刷新reload
 * */
function freshReload(){
	location.reload(false);
}

//================================表单操作===========================================

/**检测提交表单*/
function checkForm($form){
	return $form.validationEngine({returnIsValid: true});  
}

/**清空查询*/
function cleanQuery(){
	$("form[name='queryForm']")[0].reset();
	$("form[name='queryForm']").find("input").val("");
	$("form[name='queryForm']").find("select").attr("selectedValue","").resetValue();
}

/**提交查询*/
function checkQuery(fuzzy){
	var $inps=$("#selector").find("input");
	var test="";
	for(var i=0;i<$inps.length;i++){
		test+=$inps[i].value;
	}
	if(""==test.trim()|| "请选择"==test.trim()){//查询条件为空    			
		var url=$("form[name='queryForm']").attr("action");
		location.href=url;
	}else{
		$("#fuzzy").val(fuzzy);
		$("form[name='queryForm']").submit();
	}
}

/**
 * 获取已选择的数据：多个，用";" 进行分割
 * */
function checkMany(){
	var $ck=$("input[name='indexnum']:checked");
	if($ck.length==0){
		top.Dialog.alert("请选择数据！");
		return null;
	}else{
		var retVal = "";
		for(var i=0;i<$ck.length;i++){
			var oc=$ck[i];
			retVal+=oc.value.trim()+";";
		}
		return retVal;
	}
}
/**
 * 获取已选择的数据：一个
 */
function checkOne(){
	var $ck=$("input[name='indexnum']:checked");
	if($ck.length==0){
		top.Dialog.alert("请选择数据！");
		return null;
	}else if($ck.length>1){
		top.Dialog.alert("只能选择一条数据进行操作！");
		return null;
	}else{
		return $ck.val().trim();
	}
}