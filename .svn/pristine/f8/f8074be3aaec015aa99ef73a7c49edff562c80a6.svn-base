/**
 * 存放类型map
 * */
var stype_map = null;
  
function loadStypeMapByCallBack(mapActionUrl,callback){
	try {
		if ( null == stype_map || stype_map.size() == 0 ) {
			stype_map=new Map();
			$.post(mapActionUrl,{},function(data){
				//将json放入map
				for ( var obj in data) {
					var map=new Map();
					map.putJSONAll(data[obj+""]);
					stype_map.put(obj+"", map);
				}
				callback(stype_map);
				return stype_map;
			});
		}
	} catch (e) {
		alert("存放类型信息载入异常："+e.message);
	}
}



//===============================start CwTable===============================================
/**
 * 进入储位格，获取编号
 * */
function getCwbh(sid){
	var diag = new top.Dialog();
	diag.Title = "档案数据录入："+sid;
	diag.URL = "cwgl/stoget-stoAssign.action?cwbh="+sid;
	diag.Height=770;  
	diag.Width=900;
	diag.ShowButtonRow=true;
	diag.ShowOkButton=false;
	diag.CancelButtonText=" 关 闭 ";
	diag.CancelEvent = function(){
		window.location.reload(false);
		diag.close();
	};
	diag.show();
//	localHref("storeGet-getStore.action?cwbh="+sid+"&index=1");
}
 
/**
 * 进入档案格列表
 * */
function intoGe(sid){
	var diag = new top.Dialog();
	diag.Title = sid+"储位信息";
	diag.URL = "cwgl/stoget-gotoGe.action?cwbh="+sid;
	diag.ShowMaxButton=false;
	diag.ShowMinButton=false;
	diag.ShowButtonRow=true;
	diag.ShowOkButton=false;
	diag.CancelButtonText="&nbsp;关&nbsp;&nbsp;闭&nbsp;";
	diag.CancelEvent = function(){
		var isflush=$("input[name='isflush']").val()
		if (isflush == "1") {
			window.location.reload(false);
		}
		diag.close();
	};
	diag.show();
	diag.max();
//	top.Dialog.open({URL:"cwgl/stoGet-gotoGe.action?cwbh="+sid,Width:"100",Height:"100",Title:sid+"储位信息"});
//	localHref("storeGet-gotoGe.action?cwbh="+sid);
}

/**
 * 获取编号（格）-归档审核
 * 需要获取储位编号的页面 创建 function writeCwbh(cwbh,showCwbh)
 * */
function getCwbhToPage(cwbh,stype){
	$.ajax({
		async:true,
		url:"cwgl/stoget-getCwbhByGe.action",
		dataType:"json",
		data:{"cwbh":cwbh},
		success:function(data){
			if(data[0]== "1"){
				var writeOK=false;
				var showNum=data[1].substring(0, 1)+"-"+data[1].substring(1, 3)+"-"+data[1].substring(3, 4)+"-"+data[1].substring(4, 5)+"-"+data[1].substring(5, 8);
				
				//调用父窗口目标方法
				getParentContentWindow(function(windows){
					try {
						//将储位编号写入
						var ok=windows.writeCwbh(data[1],showNum);
						//获取成功
						if(ok){
							top.Dialog.close();
							writeOK = true;
							return writeOK;
						}
					} catch (e) {
						return false;
					}
				});
				if (!writeOK) {
					alert("储位编号已获取（"+data[1]+"），但无法写入!");
				}
			//	top.document.getElementById("_DialogFrame_d1").contentWindow.document.getElementById('newStorageNumber').value=data[0];
			//	top.document.getElementById("_DialogFrame_d1").contentWindow.document.getElementById('storageNumber').innerHTML=showNum;
			}else if(data[0]=="0"){
				alert(data[1]);
			}else{
				alert("储位编号获取异常！");
			}
		}
	});
}

/**
 * 获取编号-插入编号（格）-归档审核
 * 需要获取储位编号的页面 创建 function writeCwbh(cwbh,showCwbh)
 * */
function getCwbhByInsert(cwbh,stype){
	var diag = new top.Dialog();
	diag.Title = cwbh+"插入储位";
	diag.URL = "cwgl/stoget-intoInsertCwbh.action?cwbh="+cwbh;
	diag.Width=600;
	diag.Height=300;
	diag.ShowMaxButton=false;
	diag.ShowMinButton=false;
	diag.ShowButtonRow=true;
	diag.ShowOkButton=false;
	diag.CancelButtonText="&nbsp;关&nbsp;&nbsp;闭&nbsp;";
	diag.show();
}

/**
 * 档案迁移
 * */
function moveTo(tagtoId,xtdabh,tag_btn){
	//设置当前按钮不可用
	$(tag_btn).attr("disabled",true);
	$.post("cwgl/stoget-moveArc.action", {
		"cwbh":tagtoId, "xtdabh":xtdabh
	}, function(data) {
		//设置当前按钮可用
		$(tag_btn).removeAttr("disabled");
		if (data[0] == "1") { 
			alert("迁移成功！");
			try {
				//刷新第一级弹出框的列表-储位管理部分
				top.document.getElementById("_DialogFrame_0fk").contentWindow.queryForm.submit();
			} catch (e) {
			} 
			
			try {
				//刷新第一级弹出框的列表-实物管理部分
				top.document.getElementById("_DialogFrame_0fk").contentWindow.flsuhInfo(data[1],data[2]);
			} catch (e) {
			}
			closeWin();  
		}else{
			alert(data[1]);
		}
	});
}

/**
 * 储位动态表格生成对象
 * @param tableId 储位显示的table的id
 * @param jsonData 储位数据（json数组），结构为List<List<SwdaStorageSet>>
 */
function CwTable(tableId,jsonData){
	//储位显示的table的ID
	var tabId=tableId;
	//要现实的数据
	var tdata=jsonData;
	//一行数据
	var tr="<tr>#data</tr>";
	var td="<td>#data</td>";
	//储位格显示div
	var data_div="<div class=\"div_ge\">"+
				 "<span>#stype</span><br>"+
				 "<span class=\"ge_cwbh_show\">#cwbh</span><br>"+
				 "<span class=\"ge_cwbh_used\">#used</span><br>"+
				 "#btn</div>";
	//储位格显示div红色
	var data_div_red="<div class=\"div_ge_red\">"+
					 "<span>#stype</span><br>"+
					 "<span class=\"ge_cwbh_show\">#cwbh</span><br>"+
					 "<span class=\"ge_cwbh_used\">#used</span><br>"+
					 "#btn</div>";
	//储位格显示div黄色
	var data_div_yellow="<div class=\"div_ge_yellow\">"+
					 "<span>#stype</span><br>"+
					 "<span class=\"ge_cwbh_show\">#cwbh</span><br>"+
					 "<span class=\"ge_cwbh_used\">#used</span><br>"+
					 "#btn</div>";
	
	//可以使用的按钮
	var btn="<button type='button' class='btn_ge button' onclick=\"intoGe('#goto')\" ><span class=\"icon_view\">查看</span></button><button type='button' class='btn_ge button' onclick=\"getCwbh('#get')\" ><span class=\"icon_add\">录入</span></button>";
	var btn_get="<button type='button' class='btn_ge_get button' onclick=\"getCwbhToPage('#get','#stype')\" ><span class='icon_ok'>获取储位</span></button>";
	var btn_get_insert="<br><button type='button' class='btn_ge_get button' onclick=\"getCwbhByInsert('#get','#stype')\" ><span class='icon_folder_doc'>插入储位</span></button>";
	var btn_move="<button type='button' class='btn_ge_get button' onclick=\"moveTo('#tagto','#xtdabh',this)\" ><span class='icon_reply'>迁移</span></button>";
	//无法使用的按钮
	var unbtn="<button type='button' class='btn_ge button' onclick=\"intoGe('#goto')\" ><span class=\"icon_view\">查看</span></button><button type='button' class='btn_ge button' disabled=\"disabled\" ><span class=\"icon_add\">录入</span></button>";
	var unbtn_get="<button type='button' class='btn_ge_get button' disabled=\"disabled\" ><span class='icon_delete'>获取储位编号</span></button>";
	var unbtn_get_insert="<br><button type='button' class='btn_ge_get button' disabled=\"disabled\" ><span class='icon_folder_doc'>插入储位</span></button>";
	var unbtn_move="<button type='button' class='btn_ge_get button' disabled=\"disabled\" ><span class='icon_delete'>迁移</span></button>";
	//最终要显示的数据
	var show_Data="";
	
	/**
	 * 转换显示类型
	 * */
	function getStype(map,stypename,stype){
		var stypeMap=map.get(stypename);
		if(stypeMap){
			return stypeMap.get(stype);
		}else{
			return "<font color='red'>类型错误</font>";
		}
	}
	
	/**
	 * 储位表格显示
	 * @param stypeMap 存放规则map对象
	 * */
	this.show=function(){
		try{
			//根据列数循环
			for(var i=0;i<tdata.length;i++){
				var lie=tdata[i];
				var geHtmls="";
				//根据格数循环取出
				for(var j=0;j<lie.length;j++){
					var ge=lie[j];
					var new_ge_div=new String(data_div);
					if (ge.sfky!="0") {//如果当前格可用
						new_ge_div=new_ge_div.replace("#btn", btn);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						if (ge.syl==ge.zrl) {//满格标记
							new_ge_div=new_ge_div.replace("#used", "<font color='red'>"+ge.syl+"&nbsp;/&nbsp;"+ge.zrl+"（已满）</font>");//替换使用量
						}else{
							new_ge_div=new_ge_div.replace("#used", "<font color='green'>"+ge.syl+"&nbsp;/&nbsp;"+ge.zrl+"</font>");//替换使用量
						}
						new_ge_div=new_ge_div.replace("#goto", ge.cid);//替换查看按钮id
						new_ge_div=new_ge_div.replace("#get", ge.cid);//替换获取按钮id
					}else{
						new_ge_div=new String(data_div_red);
						new_ge_div=new_ge_div.replace("#btn", unbtn);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						new_ge_div=new_ge_div.replace("#used", "<font color='red'>不可用</font>");//替换使用量
						new_ge_div=new_ge_div.replace("#goto", ge.cid);//替换查看按钮id，虽然不可用但是可以查看
					}
					geHtmls+=new_ge_div;//将格内的DIV合并
				}
				//嵌套td
				show_Data+=new String(td).replace("#data", geHtmls);
			}
			//清除原html表格代码
			$("#"+tableId).html("");
			
			//html代码处理完毕以后进行写入
			//嵌套tr
			var $trh=$(new String(tr).replace("#data", show_Data));
			$("#"+tableId).html($trh);
			
			$("button.button").render();
			
			//显示表格
			$("#"+tableId).fadeIn("fast");
		} catch (e) {
			alert("数据异常！（"+e.message+"）");
		}
	};
	
	/**
	 * 打印储位显示。根据存放规则筛选--获取储位编号
	 * @param stypeMap 存放规则map对象
	 * */
	this.showGet=function(){
		try {
			//根据列数循环
			for(var i=0;i<tdata.length;i++){
				var lie=tdata[i];
				var geHtmls="";
				//根据格数循环取出
				for(var j=0;j<lie.length;j++){
					var ge=lie[j];
					var new_ge_div=new String(data_div);
					if(Number(ge.syl)==Number(ge.zrl)){//档案格已满
						new_ge_div=new String(data_div_red);
						new_ge_div=new_ge_div.replace("#btn", unbtn_get+unbtn_get_insert);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						new_ge_div=new_ge_div.replace("#used", "<font color='red'>已满（"+ge.syl+"/"+ge.zrl+"）</font>");//替换使用量
					}else if (ge.sfky!="0") {//可用
						new_ge_div=new_ge_div.replace("#btn", btn_get+btn_get_insert);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						if (ge.syl==ge.zrl) {//满格标记
							new_ge_div=new_ge_div.replace("#used", "<font color='red'>"+ge.syl+"&nbsp;/&nbsp;"+ge.zrl+"</font>");//替换使用量
						}else{
							new_ge_div=new_ge_div.replace("#used", "<font color='green'>"+ge.syl+"&nbsp;/&nbsp;"+ge.zrl+"</font>");//替换使用量
						}
			//			new_ge_div=new_ge_div.replace("#goto", ge.cid);//替换查看按钮id
						new_ge_div=new_ge_div.replace("#get", ge.cid).replace("#get", ge.cid);//替换获取按钮id
						new_ge_div=new_ge_div.replace("#stype", ge.cflx).replace("#stype", ge.cflx);//替换获取按钮存放规则
					}else {
						new_ge_div=new String(data_div_red);
						new_ge_div=new_ge_div.replace("#btn", unbtn_get+unbtn_get_insert);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						new_ge_div=new_ge_div.replace("#used", "<font color='red'>不可用</font>");//替换使用量
					}
					geHtmls+=new_ge_div;//将格内的DIV合并
				}
				//嵌套td
				show_Data+=new String(td).replace("#data", geHtmls);
			}
			//清除原html表格代码
			$("#"+tableId).html("");
			
			//html代码处理完毕以后进行写入
			//嵌套tr
			var $trh=$(new String(tr).replace("#data", show_Data));
			$("#"+tableId).html($trh);
			//载入btn样式
			$("button.button").render();
			
			//显示表格
			$("#"+tableId).fadeIn("fast");
		} catch (e) {
			alert("数据异常！（"+e.message+"）");
		}
	};
	
	/**
	 * 打印要迁移的储位。根据存放规则筛选，更具当前格筛选
	 * @param ids 当前格
	 * @param stypeMap 存放规则map对象
	 * */
	this.showMove=function(ids,stypeMap){
		try {
			//根据列数循环
			for(var i=0;i<tdata.length;i++){
				var lie=tdata[i];
				var geHtmls="";
				//根据格数循环取出
				for(var j=0;j<lie.length;j++){
					var ge=lie[j];
					var new_ge_div=new String(data_div);
					if(ge.sfky=="2"){//当前格不可用
						new_ge_div=new String(data_div_yellow);
						new_ge_div=new_ge_div.replace("#btn", unbtn_move);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						new_ge_div=new_ge_div.replace("#used", "<font color='red'>当前储位</font>");//替换使用量
					}else if(Number(ge.syl)==Number(ge.zrl)){//档案格已满
						new_ge_div=new String(data_div_red);
						new_ge_div=new_ge_div.replace("#btn", unbtn_move);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						new_ge_div=new_ge_div.replace("#used", "<font color='red'>已满（"+ge.syl+"/"+ge.zrl+"）</font>");//替换使用量
					}else if (ge.sfky == "1") {//如果当前格可用
						new_ge_div=new_ge_div.replace("#btn", btn_move);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						if (ge.syl==ge.zrl) {//满格标记
							new_ge_div=new_ge_div.replace("#used", "<font color='red'>"+ge.syl+"&nbsp;/&nbsp;"+ge.zrl+"</font>");//替换使用量
						}else{
							new_ge_div=new_ge_div.replace("#used", "<font color='green'>"+ge.syl+"&nbsp;/&nbsp;"+ge.zrl+"</font>");//替换使用量
						}
			//			new_ge_div=new_ge_div.replace("#goto", ge.cid);//替换查看按钮id
			//			new_ge_div=new_ge_div.replace("#get", ge.cid);//替换获取按钮id
						new_ge_div=new_ge_div.replace("#tagto", ge.cid);//替换获取按钮要选择的储位编号
						new_ge_div=new_ge_div.replace("#xtdabh", ids);//替换获取按钮要选择的系统编号
					}else {
						new_ge_div=new String(data_div_red);
						new_ge_div=new_ge_div.replace("#btn", unbtn_move);//替换按钮
						var shuoming=ge.cwsm;
						shuoming=(shuoming==""||shuoming==null||shuoming=="null")?"未设定说明":shuoming;
						new_ge_div=new_ge_div.replace("#stype", shuoming);//替换存放规则
						new_ge_div=new_ge_div.replace("#cwbh", CwTable.formatSid(ge.cid));//替换储位编号
						new_ge_div=new_ge_div.replace("#used", "<font color='red'>不可用</font>");//替换使用量
					}
					geHtmls+=new_ge_div;//将格内的DIV合并
				}
				//嵌套td
				show_Data+=new String(td).replace("#data", geHtmls);
			}
			//清除原html表格代码
			$("#"+tableId).html("");
			
			//html代码处理完毕以后进行写入
			//嵌套tr
			var $trh=$(new String(tr).replace("#data", show_Data));
			$("#"+tableId).html($trh);
			//载入btn样式
			$("button.button").render();
			
			//显示表格
			$("#"+tableId).fadeIn("fast");
		} catch (e) {
			alert("数据异常！（"+e.message+"）");
		}
	};

}

/**
 * 格式化储位编号，格式化效果为：1A1-A-1
 * */
CwTable.formatSid=function(sid){
	if(sid.length==5){
		var id1=sid.substring(0, 3);
		var id2=sid.substring(3, 4);
		var id3=sid.substring(4, 5);
		return id1+"-"+id2+"-"+id3;
	}else{
		return sid;
	}
};
//================================end CwTable================================================




