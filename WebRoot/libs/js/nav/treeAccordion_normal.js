/**
 * 取消cookie的缓存功能
 * @modify by wzm
 * */

/***/
var zTree_Menu = null;
var setting = {
	view : {
		fontCss : getFontCss,
		showLine : false,
		selectedMulti : false,
		dblClickExpand : false
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
function initComplete() {
	//此处可以禁止cookie缓存，添加如下语句  by wzm
//	jQuery.jCookie("leftTreeNodeId", "false");

	$.fn.zTree.init($("#treeMenu"), setting, zNodes);
	zTree_Menu = $.fn.zTree.getZTreeObj("treeMenu");
//	showContent();
	
	//禁用搜索节点功能
	/*
	$("#searchKey").keydown(function(a) {
		if (a.keyCode == 13) {
			findNode();
		}
	});
	*/
}

//显示当前位置，此方法已禁用
function showContent() {
	//从cookie取出操作节点
	var d = jQuery.jCookie("leftTreeNodeId");
	if (d == false || d == "false") {
	//设置显示当前位置功能
		top.positionType = "simple";
		top.positionContent = "【"
				+ uncompile(quiLanguage.treeAccordion.positionContent1) + "】";
	} else {
		var c = zTree_Menu.getNodeByParam("id", d);
		zTree_Menu.selectNode(c);
		if (c.level === 0 || c.level === 1) {
			var b = $("#" + c.tId + "_a");
			b.click();
		}
		if (c.url) {
			top.positionType = "simple";
			if (c.getParentNode()) {
				top.positionContent = "【"
						+ uncompile(quiLanguage.treeAccordion.positionContent2)
						+ c.getParentNode().name + ">>" + c.name + "】";
			}
			top.frmright.location = c.url;
		}
	}

}

function beforeClick(e, d) {
	if (d.level === 0) {
		$("#" + e).find("a").each(function() {
			if ($(this).hasClass("curLevel0")) {
				$(this).removeClass("curLevel0");
			}
		});
		var c = $("#" + d.tId + "_a");
		c.addClass("curLevel0");
		if (d.open) {
			zTree_Menu.expandNode(d, false);
			var c = $("#" + d.tId + "_a");
			c.removeClass("curLevel0");
			c.removeClass("curSelectedNode");
		} else {
			zTree_Menu.expandAll(false);
			zTree_Menu.expandNode(d);
		}
	} else {
		if (d.level === 1) {
			$("#" + e).find("a").each(function() {
				if ($(this).hasClass("curLevel1")) {
					$(this).removeClass("curLevel1");
				}
			});
			var c = $("#" + d.tId + "_a");
			c.addClass("curLevel1");
			if (d.open) {
				zTree_Menu.expandNode(d, false);
				var c = $("#" + d.tId + "_a");
				c.removeClass("curLevel1");
				c.removeClass("curSelectedNode");
			} else {
				zTree_Menu.expandNode(d);
				var b = $("#" + d.getParentNode().tId + "_a");
				b.addClass("curLevel0");
			}
		} else {
			zTree_Menu.expandNode(d);
		}
	}
}

/**
 * 点击事件
 * */
function onClick(f, d, c) {

	if (c.level === 0) {
		if (!c.open) {
			var b = $("#" + c.tId + "_a");
			b.removeClass("curSelectedNode");
		}
	}
	
	
	if (c.url != null) {
		//显示正在加载框
		showProgressBar();
		/*
		//显示位置--
		if (c.getParentNode()) {
			top.positionContent = "【"
					+ uncompile(quiLanguage.treeAccordion.positionContent2)
					+ c.getParentNode().name + ">>" + c.name + "】";
		} else {
			top.positionContent = "【"
					+ uncompile(quiLanguage.treeAccordion.positionContent2)
					+ c.name + "】";
		}
		top.positionType = "simple";
		*/
	}

	
/*	jQuery.jCookie("leftTreeNodeId", c.id.toString());
	if (c.level === 0) {
		if (!c.open) {
			jQuery.jCookie("leftTreeNodeId", "false");
		}
	}
*/
}

function showAll() {
	zTree_Menu.expandAll(true);
}

function hideAll() {
	zTree_Menu.expandAll(false);
//	jQuery.jCookie("leftTreeNodeId", "false");
}

/*
 * 禁用搜索节点功能
function findNode() {
	var a = $.trim($("#searchKey").val());
	if (a != "") {
		getNodesByParamFuzzy("name", a);
	}
}
function getNodesByParamFuzzy(d, e, a) {
	var b = zTree_Menu.getNodesByParamFuzzy(d, e, a);
	highlightNodes(zTree_Menu, zTree_Menu.highlightNodeList, false);
	highlightNodes(zTree_Menu, b, true);
	zTree_Menu.highlightNodeList = b;
	if (null != b && b.length > 0) {
		zTree_Menu.expandAll(false);
		var c = b[0].getParentNode();
		zTree_Menu.expandNode(c, true);
		zTree_Menu.expandNode(b[0], true);
	}
}
*/

function highlightNodes(d, c, b) {
	if (null == c) {
		return
	}
	for ( var e = 0, a = c.length; e < a; e++) {
		c[e].highlight = b;
		d.updateNode(c[e]);
	}
}

function getFontCss(b, a) {
	return (!!a.highlight) ? {//红色加粗字体
		color : "#A60000",
		"font-weight" : "bold"
	} : {//黑色正常字体
		color : "#333",
		"font-weight" : "normal"
	};
}

/**
 * 返回主页，刷新
 * */
function homeHandler() {
	zTree_Menu.expandAll(false);
//	top.positionType = "none";
//	jQuery.jCookie("leftTreeNodeId", "false");
};