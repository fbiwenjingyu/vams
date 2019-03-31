var parentTopHeight;
var parentBottomHeight;
var parentTopHeight_left;
var parentBottomHeight_left;
var parentTopHeight_middle;
var parentBottomHeight_middle;
var fixHeight;
var skinName;
var themeColor = "blue";
var broswerFlag;
var broswerVersion;
var fontSize = 12;
var fontFamily = "宋体";
var prePath = "../../";
var exitVtab = 0;
var vtabIdx = 0;
var hasIframe = 0;
var parentScrollHeight;
var boxWhiteBg = false;
var splitMode = false;
var positionTarget = "";
var box4Custom = false;
var scrollerX = false;
var autoGetSkin = true;
var autoFormat = true;
var autoRender = true;
var boxIe6Flag = 0;
var boxIe7Flag = 0;
var isHeadFixMode = 0;
var headFixExcude = 0;
var headFixExcude2 = 0;
var cResizeTimer = null;
var depth = 500;
var currentMouseX;
var currentMouseY;
$(function() {
	$.ajaxSetup({
		cache : false
	});
	$("body").bind("click", function() {
		try {
			top.iframeClickHandler()
		} catch (e) {
		}
		try {
			parent.iframeClickHandler()
		} catch (e) {
		}
	});
	var c = navigator.userAgent, rMsie = /(msie\s|trident.*rv:)([\w.]+)/, rFirefox = /(firefox)\/([\w.]+)/, rOpera = /(opera).+version\/([\w.]+)/, rChrome = /(chrome)\/([\w.]+)/, rSafari = /version\/([\w.]+).*(safari)/;
	var d;
	var f;
	var g = c.toLowerCase();
	function uaMatch(a) {
		var b = rMsie.exec(a);
		if (b != null) {
			return {
				browser : "IE",
				version : b[2] || "0"
			}
		}
		var b = rFirefox.exec(a);
		if (b != null) {
			return {
				browser : b[1] || "",
				version : b[2] || "0"
			}
		}
		var b = rOpera.exec(a);
		if (b != null) {
			return {
				browser : b[1] || "",
				version : b[2] || "0"
			}
		}
		var b = rChrome.exec(a);
		if (b != null) {
			return {
				browser : b[1] || "",
				version : b[2] || "0"
			}
		}
		var b = rSafari.exec(a);
		if (b != null) {
			return {
				browser : b[2] || "",
				version : b[1] || "0"
			}
		}
		if (b != null) {
			return {
				browser : "",
				version : "0"
			}
		}
	}
	var h = uaMatch(c.toLowerCase());
	if (h.browser && eval(uncompile("%u02DB%D7%D2%CD%C8%8FQ"))) {
		d = h.browser;
		f = h.version
	}
	if (d == "IE") {
		if (f == "6.0") {
			broswerFlag = "IE6"
		} else {
			if (f == "7.0") {
				broswerFlag = "IE7"
			} else {
				if (f == "8.0") {
					broswerFlag = "IE8"
				} else {
					if (f == "9.0") {
						broswerFlag = "IE9"
					} else {
						if (f == "10.0") {
							broswerFlag = "IE10"
						} else {
							if (f == "11.0") {
								broswerFlag = "IE11"
							}
						}
					}
				}
			}
		}
	} else {
		broswerFlag = d
	}
	broswerVersion = Number(f.split(".")[0]);
	var i;
	if ($("#skin").attr("prePath") != null) {
		prePath = $("#skin").attr("prePath")
	}
	if ($("#skin").attr("splitMode") == true
			|| $("#skin").attr("splitMode") == "true") {
		splitMode = true
	} else {
		try {
			var o = top.document.getElementById("theme")
		} catch (e) {
			if ($("body").attr("leftFrame") != "true") {
				alert(uncompile(quiLanguage.jsError.splitModeMessage))
			}
			return
		}
		var j = $(window.top.document.getElementById("theme"));
		var k = $(window.top.document.getElementById("skin"));
		if (j.attr("autoGetSkin") == false || j.attr("autoGetSkin") == "false") {
			autoGetSkin = false
		}
		if (j.attr("autoFormat") == false || j.attr("autoFormat") == "false") {
			autoFormat = false
		}
		if (j.attr("autoRender") == false || j.attr("autoRender") == "false") {
			autoRender = false
		}
		if ($("#skin").attr("autoRender") == false
				|| $("#skin").attr("autoRender") == "false") {
			autoRender = false
		}
		if (j.attr("box1WhiteBg") == true || j.attr("box1WhiteBg") == "true") {
			boxWhiteBg = true
		}
		if (j.attr("box4Custom") == true || j.attr("box4Custom") == "true") {
			box4Custom = true
		}
		if (j.attr("scrollerX") == true || j.attr("scrollerX") == "true") {
			scrollerX = true
		}
		if (j.attr("positionTarget") != null) {
			positionTarget = j.attr("positionTarget")
		}
		if (j.attr("href") == null) {
			skinName = "system/layout/skin/";
			themeColor = "blue"
		} else {
			skinName = k.attr("skinPath");
			themeColor = j.attr("themeColor")
		}
	}
	if (autoGetSkin == true && splitMode == false) {
		if (j.attr("debug") == "true" || j.attr("debug") == true) {
			if (broswerFlag == "IE6" || broswerFlag == "IE7") {
				if (j.attr("href") == "") {
				} else {
					$
							.ajax({
								url : prePath + "libs/skins/" + themeColor
										+ "/style.css",
								error : function() {
									if ($("body").attr("leftFrame") == "true"
											|| $("body").attr("leftFrame") == true) {
										alert(uncompile(quiLanguage.jsError.pathMessage0)
												+ prePath
												+ "libs/skins/"
												+ themeColor
												+ "/style.css"
												+ uncompile(quiLanguage.jsError.pathMessage2))
									} else {
										alert(uncompile(quiLanguage.jsError.pathMessage1)
												+ prePath
												+ "libs/skins/"
												+ themeColor
												+ "/style.css"
												+ uncompile(quiLanguage.jsError.pathMessage2))
									}
								},
								success : function() {
									if ($("body").attr("leftFrame") == "true"
											|| $("body").attr("leftFrame") == true) {
										alert(uncompile(quiLanguage.jsError.pathMessage3))
									} else {
										alert(uncompile(quiLanguage.jsError.pathMessage4))
									}
									$
											.ajax({
												url : prePath + skinName
														+ "style.css",
												error : function() {
													if ($("body").attr(
															"leftFrame") == "true"
															|| $("body")
																	.attr(
																			"leftFrame") == true) {
														alert(uncompile(quiLanguage.jsError.pathMessage0)
																+ prePath
																+ skinName
																+ "style.css"
																+ uncompile(quiLanguage.jsError.pathMessage5))
													} else {
														alert(uncompile(quiLanguage.jsError.pathMessage1)
																+ prePath
																+ skinName
																+ "style.css"
																+ uncompile(quiLanguage.jsError.pathMessage5))
													}
												},
												success : function() {
													if ($("body").attr(
															"leftFrame") == "true"
															|| $("body")
																	.attr(
																			"leftFrame") == true) {
														alert(uncompile(quiLanguage.jsError.pathMessage6))
													} else {
														alert(uncompile(quiLanguage.jsError.pathMessage6))
													}
												}
											})
								}
							})
				}
			} else {
				if (j.attr("href") == null) {
				} else {
					$
							.ajax({
								url : prePath + "libs/skins/" + themeColor
										+ "/style.css",
								error : function() {
									if ($("body").attr("leftFrame") == "true"
											|| $("body").attr("leftFrame") == true) {
										alert(uncompile(quiLanguage.jsError.pathMessage0)
												+ prePath
												+ "libs/skins/"
												+ themeColor
												+ "/style.css"
												+ uncompile(quiLanguage.jsError.pathMessage2))
									} else {
										alert(uncompile(quiLanguage.jsError.pathMessage1)
												+ prePath
												+ "libs/skins/"
												+ themeColor
												+ "/style.css"
												+ uncompile(quiLanguage.jsError.pathMessage2))
									}
								},
								success : function() {
									if ($("body").attr("leftFrame") == "true"
											|| $("body").attr("leftFrame") == true) {
										alert(uncompile(quiLanguage.jsError.pathMessage3))
									} else {
										alert(uncompile(quiLanguage.jsError.pathMessage4))
									}
									$
											.ajax({
												url : prePath + skinName
														+ "style.css",
												error : function() {
													if ($("body").attr(
															"leftFrame") == "true"
															|| $("body")
																	.attr(
																			"leftFrame") == true) {
														alert(uncompile(quiLanguage.jsError.pathMessage0)
																+ prePath
																+ skinName
																+ "style.css"
																+ uncompile(quiLanguage.jsError.pathMessage5))
													} else {
														alert(uncompile(quiLanguage.jsError.pathMessage1)
																+ prePath
																+ skinName
																+ "style.css"
																+ uncompile(quiLanguage.jsError.pathMessage5))
													}
												},
												success : function() {
													if ($("body").attr(
															"leftFrame") == "true"
															|| $("body")
																	.attr(
																			"leftFrame") == true) {
														alert(uncompile(quiLanguage.jsError.pathMessage6))
													} else {
														alert(uncompile(quiLanguage.jsError.pathMessage6))
													}
												}
											})
								}
							})
				}
			}
		}
		$("#skin").attr("href",
				prePath + "libs/skins/" + themeColor + "/style.css");
		$("#customSkin").attr("href", prePath + skinName + "style.css")
	}
	enableTooltips();
	try {
		if (splitMode == false) {
			fontSize = top.getFontSize();
			fontFamily = top.getFontFamily()
		}
	} catch (e) {
	}
	if (fontSize != 12) {
		$("body").css({
			fontSize : fontSize + "px"
		});
		if ($("table:[class=tableStyle]").length > 0) {
			$("table:[class=tableStyle]").css({
				fontSize : fontSize + "px"
			})
		}
		if ($("pre").length > 0) {
			$("pre").css({
				fontSize : fontSize + "px"
			})
		}
	}
	if (fontFamily != "宋体") {
		$("body").css({
			fontFamily : fontFamily
		})
	}
	if (autoRender == true) {
		$("div").each(
				function() {
					if ($(this).hasClass("box1")
							|| $(this).attr("boxType") == "box1") {
						$(this).box1Render()
					} else {
						if ($(this).hasClass("box2")
								|| $(this).attr("boxType") == "box2") {
							$(this).box2Render()
						} else {
							if ($(this).hasClass("box4")) {
								$(this).box4Render()
							}
						}
					}
				})
	}
	if ($("body").attr("leftFrame") == "true") {
		$("body").addClass("contentStyleLeft")
	} else {
		$("body").addClass("contentStyle")
	}
	if (scrollerX == true) {
		if ($("#skin").attr("scrollerX") == "false"
				|| $("#skin").attr("scrollerX") == false) {
			scrollerX = false
		}
	} else {
		if ($("#skin").attr("scrollerX") == "true"
				|| $("#skin").attr("scrollerX") == true) {
			scrollerX = true
		}
	}
	if (broswerFlag == "IE6") {
		$("html").css({
			width : "100%"
		})
	}
	if (scrollerX == false) {
		if (broswerFlag == "IE7") {
			$("body").css({
				overflowX : "hidden"
			})
		} else {
			$("html").css({
				overflowX : "hidden"
			})
		}
	}
	if ($("#skin").attr("scrollerY") == "false"
			|| $("#skin").attr("scrollerY") == false) {
		$("html").css({
			overflowY : "hidden"
		})
	}
	triggerCustomHeightSet();
	if (!qflag()) {
		return
	}
	if (cResizeTimer) {
		clearTimeout(cResizeTimer)
	}
	cResizeTimer = setTimeout("triggerCustomHeightSet()", 500);
	window.onresize = function() {
		if (cResizeTimer) {
			clearTimeout(cResizeTimer)
		}
		cResizeTimer = setTimeout("triggerCustomHeightSet()", 100)
	};
	if (autoRender == true) {
		$("div,input,textarea,button,select,form,table,a,img,span")
				.each(
						function() {
							if ($(this).hasClass("box1")
									|| $(this).hasClass("box2")
									|| $(this).hasClass("box3")
									|| $(this).hasClass("box4")
									|| $(this).attr("boxType") == "box1"
									|| $(this).attr("boxType") == "box2"
									|| $(this).attr("keepDefaultStyle") == "true"
									|| $(this).attr("keepDefaultStyle") == true
									|| $(this).attr("fillType")) {
								if ($(this).hasClass("imgPreview")) {
									$(this).render()
								}
							} else {
								$(this).render();
								if ($(this).attr("title")) {
									if ($(this).parents(".selectbox-tree").length > 0
											|| $(this).parents(
													".dbSelectionMode").length > 0) {
									} else {
										addTooltip($(this)[0])
									}
								}
							}
						});
		$(".spliter").each(function() {
			try {
				if ($(this).is("td")) {
					$(this).spliterRender()
				}
			} catch (e) {
				alert(uncompile(quiLanguage.jsError.spliter))
			}
		})
	}
	closeProgress();
	if (!splitMode) {
		if (parent.positionType) {
			if (parent.positionType != "none" && parent.positionContent != "") {
				if (positionTarget == "") {
					if (parent.positionType == "normal") {
						createPosition(parent.positionContent, "normal")
					} else {
						createPosition(parent.positionContent, "simple")
					}
				} else {
					top.createPosition(positionTarget, parent.positionContent)
				}
			}
		}
	}
	_initComplete()
});
function cusTreeTableLoadLater(b, a) {
	$.ajax({
		url : a,
		error : function() {
			try {
				top.Dialog.alert("数据加载失败，请检查dataPath是否正确")
			} catch (c) {
				alert("数据加载失败，请检查dataPath是否正确")
			}
		},
		success : function(d) {
			var c = b.parents("tr").next().find("table").parents("td");
			c.html("");
			var e = $(d);
			e.appendTo(c);
			e.tableRender();
			b.removeClass("img_loading");
			b.addClass("img_remove2");
			b.attr("title", "点击收缩");
			b.parents("tr").next().show()
		}
	})
}
function triggerCustomHeightSet() {
	var b = document.documentElement.clientHeight;
	var a = document.documentElement.clientWidth;
	_customHeightSet(b, a)
}
function _customHeightSet(c, a) {
	try {
		customHeightSet(c, a)
	} catch (b) {
	}
}
function changeFont(a) {
	$("body").css({
		fontSize : a + "px"
	});
	if ($("table:[class=tableStyle]").length > 0) {
		$("table:[class=tableStyle]").css({
			fontSize : a + "px"
		})
	}
	if ($("pre").length > 0) {
		$("pre").css({
			fontSize : a + "px"
		})
	}
	if ($("iframe").length > 0) {
		for ( var b = 0; b < $("iframe").length; b++) {
			document.getElementsByTagName("iframe")[b].contentWindow
					.changeFont(a)
		}
	}
	fontSize = a
}
function changeFontFamily(b) {
	$("body").css({
		fontFamily : b
	});
	if ($("iframe").length > 0) {
		for ( var a = 0; a < $("iframe").length; a++) {
			document.getElementsByTagName("iframe")[a].contentWindow
					.changeFontFamily(b)
		}
	}
	fontFamily = b
}
(function(a) {
	a.fn.render = function() {
		if (a(this).hasClass("spliter")) {
			try {
				a(this).spliterRender()
			} catch (b) {
				alert(uncompile(quiLanguage.jsError.spliter))
			}
		}
		if (a(this).is("input")) {
			if (a(this).attr("type") == "text") {
				if (a(this).hasClass("color")) {
					a(this).textInputStyleRender();
					try {
						a(this).attr("trueType", "color");
						a(this).colorRender()
					} catch (b) {
						alert(uncompile(quiLanguage.jsError.color))
					}
				} else {
					if (a(this).hasClass("date")) {
						a(this).attr("trueType", "date");
						a(this).dateRender()
					} else {
						if (a(this).hasClass("dateIcon")) {
							a(this).attr("trueType", "date");
							a(this).textInputStyleRender()
						} else {
							if (a(this).hasClass("keypad")) {
								a(this).textInputStyleRender();
								try {
									a(this).attr("trueType", "keypad");
									a(this).keypadRender()
								} catch (b) {
									alert(uncompile(quiLanguage.jsError.keypad))
								}
							} else {
								if (a(this).hasClass("stepper")) {
									a(this).textInputStyleRender();
									try {
										a(this).attr("trueType", "stepper");
										a(this).stepperRender()
									} catch (b) {
										alert(uncompile(quiLanguage.jsError.stepper))
									}
								} else {
									a(this).attr("trueType", "textinput");
									a(this).textinputRender()
								}
							}
						}
					}
				}
			} else {
				if (a(this).attr("type") == "button"
						|| a(this).attr("type") == "submit"
						|| a(this).attr("type") == "reset") {
					a(this).buttonInputRender()
				} else {
					if (a(this).attr("type") == "file") {
						a(this).attr("trueType", "file");
						a(this).fileRender()
					} else {
						if (a(this).attr("type") == "password") {
							a(this).attr("trueType", "password");
							a(this).passInputRender();
							if (a(this).hasClass("keypad")) {
								a(this).textInputStyleRender();
								try {
									a(this).attr("trueType", "keypad");
									a(this).keypadRender()
								} catch (b) {
									alert(uncompile(quiLanguage.jsError.keypad))
								}
							}
						} else {
							if (a(this).attr("type") == "radio") {
								a(this).attr("trueType", "radio")
							} else {
								if (a(this).attr("type") == "checkbox") {
									a(this).attr("trueType", "checkbox")
								} else {
									if (a(this).attr("type") == "hidden") {
										a(this).attr("trueType", "hidden")
									}
								}
							}
						}
					}
				}
			}
		} else {
			if (a(this).is("button")) {
				a(this).buttonRender()
			} else {
				if (a(this).is("textarea")) {
					a(this).attr("trueType", "textarea");
					a(this).textareaRender()
				} else {
					if (a(this).is("select")) {
						a(this).attr("trueType", "select");
						a(this).prev(".mainCon").attr("trueType", "q_select");
						a(this).selectRender()
					} else {
						if (a(this).is("table")) {
							if (a(this).hasClass("tableStyle")) {
								a(this).tableRender()
							} else {
								if (a(this).hasClass("treeTable")) {
									try {
										a(this).treeTableRender()
									} catch (b) {
										alert(uncompile(quiLanguage.jsError.treeTable))
									}
								} else {
									if (a(this).hasClass("detailTable")) {
										try {
											a(this).addClass("tableStyle");
											a(this).tableRender();
											a(this).detailTableRender()
										} catch (b) {
											alert(uncompile(quiLanguage.jsError.detailTable))
										}
									}
								}
							}
						} else {
							if (a(this).is("a")) {
								if (a(this).hasClass("imgPreview")) {
									try {
										a(this).imagePreviewRender()
									} catch (b) {
										alert(uncompile(quiLanguage.jsError.imgPreview))
									}
								} else {
									if (a(this).hasClass("imgZoom")) {
										try {
											a(this).imgZoomRender()
										} catch (b) {
											alert(uncompile(quiLanguage.jsError.imgZoom))
										}
									}
								}
							} else {
								if (a(this).is("img")) {
									if (a(this).hasClass("imgFrame")) {
										try {
											a(this).imgFrameRender()
										} catch (b) {
											alert(uncompile(quiLanguage.jsError.imgFrame))
										}
									} else {
										if (a(this).hasClass("imgFade")) {
											try {
												a(this).imgFadeRender()
											} catch (b) {
												alert(uncompile(quiLanguage.jsError.imgFade))
											}
										}
									}
								} else {
									if (a(this).is("div")) {
										if (a(this).hasClass("box1")
												|| a(this).attr("boxType") == "box1") {
											a(this).box1Render()
										} else {
											if (a(this).hasClass("box2")
													|| a(this).attr("boxType") == "box2") {
												a(this).box2Render()
											} else {
												if (a(this).hasClass("box4")) {
													a(this).box4Render()
												} else {
													if (a(this).hasClass(
															"floatPanel")) {
														try {
															a(this)
																	.floatPanelRender()
														} catch (b) {
															alert(uncompile(quiLanguage.jsError.floatPanel))
														}
													} else {
														if (a(this).hasClass(
																"selectTree")) {
															try {
																a(this)
																		.attr(
																				"trueType",
																				"selectTree");
																a(this)
																		.selectTreeRender()
															} catch (b) {
																alert(uncompile(quiLanguage.jsError.selectTree))
															}
														} else {
															if (a(this)
																	.hasClass(
																			"selectCustom")) {
																try {
																	a(this)
																			.attr(
																					"trueType",
																					"selectCustom");
																	a(this)
																			.selectCustomRender()
																} catch (b) {
																	alert(uncompile(quiLanguage.jsError.selectCustom))
																}
															} else {
																if (a(this)
																		.hasClass(
																				"suggestion")) {
																	try {
																		a(this)
																				.attr(
																						"trueType",
																						"suggestion");
																		a(this)
																				.suggestionRender()
																	} catch (b) {
																		alert(uncompile(quiLanguage.jsError.suggestion))
																	}
																} else {
																	if (a(this)
																			.hasClass(
																					"filter")) {
																		try {
																			a(
																					this)
																					.attr(
																							"trueType",
																							"filter");
																			a(
																					this)
																					.filterRender()
																		} catch (b) {
																			alert(uncompile(quiLanguage.jsError.filter))
																		}
																	} else {
																		if (a(
																				this)
																				.hasClass(
																						"lister")) {
																			try {
																				a(
																						this)
																						.attr(
																								"trueType",
																								"lister");
																				a(
																						this)
																						.listerRender()
																			} catch (b) {
																				alert(uncompile(quiLanguage.jsError.lister))
																			}
																		} else {
																			if (a(
																					this)
																					.hasClass(
																							"listerTree")) {
																				try {
																					a(
																							this)
																							.attr(
																									"trueType",
																									"listerTree");
																					a(
																							this)
																							.listerTreeRender()
																				} catch (b) {
																					alert(uncompile(quiLanguage.jsError.listerTree))
																				}
																			} else {
																				if (a(
																						this)
																						.hasClass(
																								"rating")) {
																					try {
																						a(
																								this)
																								.attr(
																										"trueType",
																										"rating");
																						a(
																								this)
																								.ratingRender()
																					} catch (b) {
																						alert(uncompile(quiLanguage.jsError.rating))
																					}
																				} else {
																					if (a(
																							this)
																							.hasClass(
																									"popupMenu")) {
																						a(
																								this)
																								.popupMenuRender()
																					} else {
																						if (a(
																								this)
																								.hasClass(
																										"basicTab")) {
																							try {
																								a(
																										this)
																										.basicTabRender()
																							} catch (b) {
																								alert(uncompile(quiLanguage.jsError.basicTab))
																							}
																						} else {
																							if (a(
																									this)
																									.hasClass(
																											"basicTabModern")) {
																								a(
																										this)
																										.basicTabModernRender()
																							} else {
																								if (a(
																										this)
																										.hasClass(
																												"basicTabProcess")) {
																									try {
																										a(
																												this)
																												.basicTabProcessRender()
																									} catch (b) {
																										alert(uncompile(quiLanguage.jsError.basicTabProcess))
																									}
																								} else {
																									if (a(
																											this)
																											.hasClass(
																													"verticalTab")) {
																										try {
																											a(
																													this)
																													.verticalTabRender()
																										} catch (b) {
																											alert(uncompile(quiLanguage.jsError.verticalTab))
																										}
																									} else {
																										if (a(
																												this)
																												.hasClass(
																														"singleNav")) {
																											a(
																													this)
																													.singleNavRender()
																										} else {
																											if (a(
																													this)
																													.hasClass(
																															"singleNavMin")) {
																												a(
																														this)
																														.singleNavMinRender()
																											} else {
																												if (a(
																														this)
																														.hasClass(
																																"accordition")) {
																													try {
																														a(
																																this)
																																.accorditionRender()
																													} catch (b) {
																														alert(uncompile(quiLanguage.jsError.accordion))
																													}
																												} else {
																													if (a(
																															this)
																															.hasClass(
																																	"navIcon")) {
																														a(
																																this)
																																.hover(
																																		function() {
																																			a(
																																					this)
																																					.addClass(
																																							"navIcon_hover")
																																		},
																																		function() {
																																			a(
																																					this)
																																					.removeClass(
																																							"navIcon_hover")
																																		})
																													} else {
																														if (a(
																																this)
																																.hasClass(
																																		"navIconSmall")) {
																															a(
																																	this)
																																	.hover(
																																			function() {
																																				a(
																																						this)
																																						.addClass(
																																								"navIconSmall_hover")
																																			},
																																			function() {
																																				a(
																																						this)
																																						.removeClass(
																																								"navIconSmall_hover")
																																			})
																														} else {
																															if (a(
																																	this)
																																	.hasClass(
																																			"pageNumber")) {
																																try {
																																	a(
																																			this)
																																			.pageNumberRender()
																																} catch (b) {
																																	alert(uncompile(quiLanguage.jsError.pageNumber))
																																}
																															} else {
																																if (a(
																																		this)
																																		.hasClass(
																																				"pageArrow")) {
																																	try {
																																		a(
																																				this)
																																				.pageArrowRender()
																																	} catch (b) {
																																		alert(uncompile(quiLanguage.jsError.pageArrow))
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									} else {
										if (a(this).is("span")) {
											if (a(this).hasClass("img_light")) {
												a(this).addClass("hand");
												a(this)
														.hover(
																function() {
																	a(this)
																			.removeClass(
																					"img_light");
																	a(this)
																			.addClass(
																					"img_lightOn")
																},
																function() {
																	a(this)
																			.addClass(
																					"img_light");
																	a(this)
																			.removeClass(
																					"img_lightOn")
																})
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	};
	a.fn.setValue = function(c, d) {
		var b = a(this);
		if (b.attr("trueType") == "select") {
			b.attr("selectedValue", c);
			b.render()
		} else {
			if (b.attr("trueType") == "selectTree") {
				b.attr("selectedValue", c);
				b.render()
			} else {
				if (b.attr("trueType") == "selectCustom") {
					b.selectCustomSetValue(c, d)
				} else {
					if (b.attr("trueType") == "suggestion") {
						b.suggestionSetValue(c, d)
					} else {
						if (b.attr("trueType") == "lister") {
							b.listerSetValue(c)
						} else {
							if (b.attr("trueType") == "listerTree") {
								b.listerTreeSetValue(c)
							} else {
								if (b.attr("trueType") == "filter") {
									b.attr("selectedValue", c);
									b.render()
								}
							}
						}
					}
				}
			}
		}
	};
	a.fn.resetValue = function() {
		var b = a(this);
		if (b.attr("trueType") == "select") {
			b.render()
		} else {
			if (b.attr("trueType") == "selectTree") {
				b.render()
			} else {
				if (b.attr("trueType") == "lister") {
					b.render()
				} else {
					if (b.attr("trueType") == "listerTree") {
						b.render()
					} else {
						if (b.attr("trueType") == "filter") {
							b.render()
						}
					}
				}
			}
		}
	};
	a.fn.addItem = function(c) {
		var b = a(this);
		if (b.attr("trueType") == "select") {
			b.selectAddItem(c)
		} else {
			if (b.attr("trueType") == "selectTree") {
				b.selectTreeAddItem(c)
			} else {
				if (b.attr("trueType") == "lister") {
					b.listerAddItem(c)
				} else {
					if (b.attr("trueType") == "listerTree") {
						b.listerTreeAddItem(c)
					}
				}
			}
		}
	};
	a.fn.removeItem = function(c) {
		var b = a(this);
		if (b.attr("trueType") == "select") {
			b.selectRemoveItem(c)
		} else {
			if (b.attr("trueType") == "selectTree") {
				b.selectTreeRemoveItem(c)
			} else {
				if (b.attr("trueType") == "lister") {
					b.listerRemoveItem(c)
				} else {
					if (b.attr("trueType") == "listerTree") {
						b.listerTreeRemoveItem(c)
					}
				}
			}
		}
	};
	a.fn.selectValue = function(c) {
		var b = a(this);
		if (b.attr("trueType") == "lister") {
			b.listerSelectValue(c)
		} else {
			if (b.attr("trueType") == "listerTree") {
				b.listerTreeSelectValue(c)
			}
		}
	};
	a.fn.unSelectValue = function(c) {
		var b = a(this);
		if (b.attr("trueType") == "lister") {
			b.listerUnSelectValue(c)
		} else {
			if (b.attr("trueType") == "listerTree") {
				b.listerTreeUnSelectValue(c)
			}
		}
	};
	a.fn.box1Render = function() {
		var b;
		if (a(this).find(".boxContent").length > 0) {
		} else {
			b = a(this).html();
			a(this).empty();
			if (a(this).attr("whiteBg") == "true"
					|| a(this).attr("whiteBg") == true || boxWhiteBg == true) {
				if (a(this).hasClass("box1")) {
					a(this).addClass("box1_white")
				}
			}
			a(
					"<div class='box_topcenter'><div class='box_topleft'><div class='box_topright'></div></div></div>")
					.appendTo(a(this));
			a(
					"<div class='box_middlecenter'><div class='box_middleleft'><div class='box_middleright'><div class='boxContent'></div></div></div></div>")
					.appendTo(a(this));
			a(
					"<div class='box_bottomcenter'><div class='box_bottomleft'><div class='box_bottomright'></div></div></div>")
					.appendTo(a(this));
			a(this).find(".boxContent").html(b)
		}
		a(this).box1Build()
	};
	a.fn.box1Build = function() {
		if (a(this).attr("panelWidth") != null) {
			var d = a(this).attr("panelWidth");
			var c = d.substr(d.length - 1, 1);
			if (c == "%") {
				a(this).width(d)
			} else {
				var e = Number(a(this).attr("panelWidth"));
				a(this).width(e)
			}
		}
		if (a(this).attr("panelHeight") != null) {
			var b;
			if (a(this).attr("whiteBg") == "true"
					|| a(this).attr("whiteBg") == true) {
				b = Number(a(this).attr("panelHeight"))
						- a(this).find(".box1_topcenter2").outerHeight()
						- a(this).find(".box1_bottomcenter2").outerHeight()
			} else {
				b = Number(a(this).attr("panelHeight"))
						- a(this).find(".box1_topcenter").outerHeight()
						- a(this).find(".box1_bottomcenter").outerHeight()
			}
			a(this).find(".boxContent").height(b)
		}
		if (a(this).attr("overflow") == "true"
				|| a(this).attr("overflow") == true) {
			a(this).find(".boxContent").css({
				overflow : "auto"
			})
		} else {
			if (a(this).attr("overflow") == "false"
					|| a(this).attr("overflow") == false) {
				a(this).find(".boxContent").css({
					overflow : "hidden"
				})
			} else {
				a(this).find(".boxContent").css({
					overflow : "visible"
				})
			}
		}
		if (a(this).attr("position") == "center") {
			a(this).addClass("center")
		} else {
			a(this).removeClass("center")
		}
	};
	a.fn.box2Close = function() {
		var b = a(this).box2GetState();
		if (!b) {
			return
		}
		a(this).find(".ss").click()
	};
	a.fn.box2Open = function() {
		var b = a(this).box2GetState();
		if (b) {
			return
		}
		a(this).find(".ss").click()
	};
	a.fn.box2ChangeState = function() {
		a(this).find(".ss").click()
	};
	a.fn.box2GetState = function() {
		var b;
		if (a(this).find(".boxContent")[0].style.display == "none") {
			b = false
		} else {
			b = true
		}
		return b
	};
	a.fn.box2Render = function() {
		var b;
		if (a(this).find(".boxContent").length > 0) {
		} else {
			b = a(this).html();
			a(this).empty();
			a(
					"<div class='box_topcenter' id='box_topcenter'><div class='box_topleft'><div class='box_topright'><div class='title'><span></span></div><div class='boxSubTitle'></div><div class='status'><span class='ss'><a></a></span></div><div class='clear'></div></div></div></div>")
					.appendTo(a(this));
			a(
					"<div class='box_middlecenter'><div class='box_middleleft'><div class='box_middleright'><div class='boxContent'></div></div></div></div>")
					.appendTo(a(this));
			a(
					"<div class='box_bottomcenter' id='box_bottomcenter'><div class='box_bottomleft'><div class='box_bottomright'></div></div></div>")
					.appendTo(a(this));
			a(this).find(".boxContent").html(b)
		}
		a(this).box2Build()
	};
	a.fn.box2Build = function() {
		var f = a(this);
		var r = f.find(".title");
		var o = r.find("span");
		if (f.attr("boxType") == null) {
			f.attr("boxType", "box2")
		}
		if (f.attr("panelTitle") != null) {
			o.text(f.attr("panelTitle"))
		}
		if (f.attr("iconClass") != null) {
			o.addClass(f.attr("iconClass"));
			o.css({
				backgroundPosition : "0 50%"
			});
			o.parent().addClass("title_icon")
		} else {
			if (f.attr("iconSrc") != null) {
				o.css({
					backgroundImage : "url(" + a(this).attr("iconSrc") + ")",
					backgroundRepeat : "no-repeat",
					backgroundPosition : "0 50%",
					display : "block"
				});
				o.parent().addClass("title_icon")
			}
		}
		if (f.attr("panelSubTitle") != null) {
			a(this).find(".boxSubTitle").text(f.attr("panelSubTitle"))
		}
		if (f.attr("panelSubTitleColor") != null) {
			a(this).find(".boxSubTitle").css({
				color : f.attr("panelSubTitleColor")
			})
		}
		if (a(this).attr("panelWidth") != null) {
			var c = a(this).attr("panelWidth");
			var n = c.substr(c.length - 1, 1);
			if (n == "%") {
				a(this).width(c)
			} else {
				var b = Number(a(this).attr("panelWidth"));
				a(this).width(b)
			}
		}
		if (a(this).attr("panelHeight") != null) {
			var p = Number(a(this).attr("panelHeight"))
					- a(this).find("#box2_topcenter").outerHeight()
					- a(this).find("#box2_bottomcenter").outerHeight();
			a(this).find(".boxContent").height(p)
		}
		if (a(this).attr("overflow") == "true"
				|| a(this).attr("overflow") == true) {
			a(this).find(".boxContent").css({
				overflow : "auto"
			})
		} else {
			if (a(this).attr("overflow") == "false"
					|| a(this).attr("overflow") == false) {
				a(this).find(".boxContent").css({
					overflow : "hidden"
				})
			} else {
				a(this).find(".boxContent").css({
					overflow : "visible"
				})
			}
		}
		var e = "true";
		if (a(this).attr("showStatus") != null) {
			e = a(this).attr("showStatus")
		}
		var m = "javascript:;";
		if (a(this).attr("panelUrl") != null) {
			m = a(this).attr("panelUrl")
		}
		var l = "_self";
		if (a(this).attr("panelTarget") != null) {
			l = a(this).attr("panelTarget")
		}
		var d = uncompile(quiLanguage.box2.collapseText);
		if (a(this).attr("statusText") != null) {
			d = a(this).attr("statusText")
		}
		var s = uncompile(quiLanguage.box2.expendText);
		if (a(this).attr("afterStatusText") != null) {
			s = a(this).attr("afterStatusText")
		}
		var h = a(this).find(".ss");
		h.unbind("click");
		var q;
		var t = "visibleChange";
		if (a(this).attr("statusType") != null) {
			t = a(this).attr("statusType")
		}
		var g = "open";
		if (a(this).attr("startState") != null) {
			g = a(this).attr("startState")
		}
		if (t == "visibleChange" && e == "true" && g == "open") {
			h.text(d);
			h.toggle(function() {
				var u = a(this).parents('div[boxType="box2"]').find(
						".boxContent");
				q = u.height();
				if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
					u.fadeOut(300, function() {
						f.trigger("stateChange", "hide")
					})
				} else {
					u.hide(300, function() {
						f.trigger("stateChange", "hide")
					})
				}
				a(this).text(s)
			},
					function() {
						var u = a(this).parents('div[boxType="box2"]').find(
								".boxContent");
						u.height(q);
						if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
							u.fadeIn(300, function() {
								f.trigger("stateChange", "show")
							})
						} else {
							u.show(300, function() {
								f.trigger("stateChange", "show")
							})
						}
						if (a(this).parents('div[boxType="box2"]').attr(
								"panelHeight") == null) {
							setTimeout(function() {
								u.css({
									height : "auto"
								})
							}, 500)
						}
						a(this).text(d)
					})
		} else {
			if (t == "visibleChange" && e == "true" && g == "close") {
				h.text(d);
				var i = a(this).find(".boxContent");
				q = i.height();
				i.hide();
				h.toggle(function() {
					var u = a(this).parents('div[boxType="box2"]').find(
							".boxContent");
					u.height(q);
					if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
						u.fadeIn(300, function() {
							f.trigger("stateChange", "show")
						})
					} else {
						u.show(300, function() {
							f.trigger("stateChange", "show")
						})
					}
					if (a(this).parents('div[boxType="box2"]').attr(
							"panelHeight") == null) {
						setTimeout(function() {
							u.css({
								height : "auto"
							})
						}, 500)
					}
					a(this).text(s)
				}, function() {
					if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
						i.fadeOut(300, function() {
							f.trigger("stateChange", "hide")
						})
					} else {
						i.hide(300, function() {
							f.trigger("stateChange", "hide")
						})
					}
					a(this).text(d)
				})
			} else {
				if (t == "link" && e == "true"
						&& a(this).attr("statusText") != null) {
					h.find("a").attr("href", m);
					h.find("a").attr("target", l);
					h.find("a").text(d)
				} else {
					if (e == "true" && a(this).attr("statusText") != null) {
						h.text(d);
						h.css({
							cursor : "auto"
						})
					} else {
						h.hide()
					}
				}
			}
		}
	};
	a.fn.box4Render = function() {
		var b;
		if (a(this).find(".boxContent").length > 0) {
		} else {
			b = a(this).html();
			a(this).empty();
			if (box4Custom) {
				a(
						"<div class='box4_topcenter_notitle2' id='box4_notitle'><div class='box4_topleft_notitle2'><div class='box4_topright_notitle2'></div></div></div>")
						.appendTo(a(this));
				a(
						"<div class='box4_topcenter2' id='box4_hastitle'><div class='box4_topleft2'><div class='box4_topright2'><div class='title'></div></div></div></div>")
						.appendTo(a(this));
				a(
						"<div class='box4_middlecenter2'><div class='box4_middleleft2'><div class='box4_middleright2'><div class='boxContent'></div></div></div></div>")
						.appendTo(a(this));
				a(
						"<div class='box4_bottomcenter2' id='box4_bottomcenter'><div class='box4_bottomleft2'><div class='box4_bottomright2'></div></div></div>")
						.appendTo(a(this))
			} else {
				a(
						"<div class='box4_topcenter_notitle' id='box4_notitle'><div class='box4_topleft_notitle'><div class='box4_topright_notitle'></div></div></div>")
						.appendTo(a(this));
				a(
						"<div class='box4_topcenter' id='box4_hastitle'><div class='box4_topleft'><div class='box4_topright'><div class='title'></div></div></div></div>")
						.appendTo(a(this));
				a(
						"<div class='box4_middlecenter'><div class='box4_middleleft'><div class='box4_middleright'><div class='boxContent'></div></div></div></div>")
						.appendTo(a(this));
				a(
						"<div class='box4_bottomcenter' id='box4_bottomcenter'><div class='box4_bottomleft'><div class='box4_bottomright'></div></div></div>")
						.appendTo(a(this))
			}
			a(this).find(".boxContent").html(b)
		}
		a(this).box4Build()
	};
	a.fn.box4Build = function() {
		if (a(this).attr("panelTitle") != null) {
			a(this).find(".title").text(a(this).attr("panelTitle"))
		}
		var f = a(this).find("#box4_notitle");
		var b = a(this).find("#box4_hastitle");
		f.hide();
		b.hide();
		if (a(this).attr("noTitle") == "true"
				|| a(this).attr("noTitle") == true) {
			f.show()
		} else {
			b.show()
		}
		if (a(this).attr("panelWidth") != null) {
			var g = a(this).attr("panelWidth");
			var d = g.substr(g.length - 1, 1);
			if (d == "%") {
				a(this).width(g)
			} else {
				var h = Number(a(this).attr("panelWidth"));
				a(this).width(h)
			}
		}
		if (a(this).attr("panelHeight") != null) {
			a(this).find(".box4_topcenter").height(27);
			a(this).find(".box4_bottomcenter").height(5);
			var c;
			if (a(this).attr("noTitle") == "true"
					|| a(this).attr("noTitle") == true) {
				c = Number(a(this).attr("panelHeight"))
						- a(this).find("#box4_notitle").outerHeight()
						- a(this).find("#box4_bottomcenter").outerHeight()
			} else {
				c = Number(a(this).attr("panelHeight"))
						- a(this).find("#box4_hastitle").outerHeight()
						- a(this).find("#box4_bottomcenter").outerHeight()
			}
			a(this).find(".boxContent").height(c)
		}
		if (a(this).attr("overflow") == "true"
				|| a(this).attr("overflow") == true) {
			a(this).find(".boxContent").css({
				overflow : "auto"
			})
		} else {
			if (a(this).attr("overflow") == "false"
					|| a(this).attr("overflow") == false) {
				a(this).find(".boxContent").css({
					overflow : "hidden"
				})
			} else {
				a(this).find(".boxContent").css({
					overflow : "visible"
				})
			}
		}
		var e = a(this);
		e.find("li a").unbind("click");
		e.find("li a").each(function(l) {
			a(this).click(function() {
				e.find("li a").removeClass("current");
				a(this).addClass("current")
			})
		})
	};
	a.fn.textinputRender = function() {
		if (a(this).attr("inputMode")) {
			var e = a(this).attr("inputMode");
			if (e == "numberOnly") {
				var d = a(this)[0];
				var c = function() {
					d.value = d.value.replace(/\D/g, "");
					if (!validateInput(d.value, "^(0|[1-9][0-9]*)$")) {
						d.value = d.value.substring(1)
					}
				};
				a(this)[0].onkeyup = function() {
					c()
				};
				a(this)[0].onafterpaste = function() {
					c()
				}
			} else {
				if (e == "positiveDecimal") {
					var d = a(this)[0];
					var c = function() {
						d.value = d.value.replace(/[^0-9\.]/g, "");
						if (!validateInput(d.value,
								"^(([1-9]+)|([1-9]+).{1}|([0-9]+.{1}[0-9]+))$")) {
							d.value = d.value.substring(0, d.value.length - 1)
						}
					};
					a(this)[0].onkeyup = function() {
						c()
					};
					a(this)[0].onafterpaste = function() {
						c()
					}
				}
			}
		}
		a(this).bind("keydown", function(g) {
			var f = g.keyCode || g.which || g.charCode;
			if (f == 13) {
				a(this).trigger("enter")
			}
		});
		if (a(this).attr("class") == "keypad") {
			return
		}
		a(this).addClass("textinput");
		a(this).css("fontFamily", fontFamily);
		a(this).css("fontSize", fontSize);
		var b = null;
		a(this).hover(function() {
			if (b != a(this)[0]) {
				a(this).removeClass("textinput");
				a(this).addClass("textinput_hover")
			}
		}, function() {
			if (b != a(this)[0]) {
				a(this).removeClass("textinput_hover");
				a(this).addClass("textinput")
			}
		});
		a(this).focus(function() {
			b = a(this)[0];
			a(this).removeClass("textinput");
			a(this).removeClass("textinput_hover");
			a(this).addClass("textinput_click")
		});
		a(this).blur(function() {
			b = null;
			a(this).removeClass("textinput_click");
			a(this).addClass("textinput")
		});
		if (a(this).attr("clearable") == "true") {
			a(this).clearableTextField()
		}
		if (a(this).attr("maxNum") != null) {
			a(this).maxlength({
				maxCharacters : parseInt(a(this).attr("maxNum"))
			})
		}
		if (a(this).attr("watermark") != null) {
			a(this).watermark("watermark", a(this).attr("watermark"))
		}
	};
	a.fn.fileRender = function() {
		var i = 184;
		var g = 60;
		var l = 0;
		if (!splitMode) {
			var c = a(window.top.document.getElementById("theme"));
			if (c.attr("defaultFileInputWidth") != null) {
				l = Number(c.attr("defaultFileInputWidth"))
			}
			if (c.attr("fileBtnWidth") != null) {
				g = Number(c.attr("fileBtnWidth"))
			}
		} else {
			if (a(this).attr("fileBtnWidth") != null) {
				g = Number(a(this).attr("fileBtnWidth"))
			}
		}
		if (a(this).attr("fileWidth")) {
			i = Number(a(this).attr("fileWidth"))
		} else {
			if (l != 0) {
				i = l
			}
		}
		a(this).addClass("fileComponent");
		a(this).wrap('<div class="file-container"></div>');
		var f = a('<table cellspacing="0" cellpadding="0" style="border-style:none;position:absolute;z-index:10;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"><input type="text" class="textinput"/></td><td class="ali01" style="border-style:none;;padding:0;margin:0;"><input type="button" class="fileBtn" value="" /></td></tr></table>');
		var h = a(this).parent();
		h.wrap('<div class="file-container-main"></div>');
		var b = h.parent();
		h.prepend(f);
		var d = f.find("input[type=text]");
		var e = f.find(".fileBtn");
		e.width(g);
		d.width(i - g - 2);
		if (a.browser.msie) {
			a(this).width(i)
		}
		h.width(i + 5);
		b.width(i + 5);
		a(this).css({
			position : "absolute",
			"z-index" : 20,
			"font-size" : "118px",
			opacity : "0",
			left : "0px",
			top : "-30px"
		});
		a(this).change(
				function() {
					var m = "";
					if (broswerFlag == "IE6" || broswerFlag == "IE7"
							|| broswerFlag == "IE8" || broswerFlag == "IE9") {
						a(this)[0].select();
						m = document.selection.createRange().text
					} else {
						if (broswerFlag == "firefox") {
							m = a(this).val()
						} else {
							var n = a(this).val().toString().split("\\");
							m = n[n.length - 1]
						}
					}
					var p = a(this).parent().find("input[type=text]");
					p.val(m);
					if (a(this).attr("showInfo") != "false") {
						try {
							a(this).attr("title", m);
							addTooltip(a(this)[0])
						} catch (o) {
						}
					} else {
						if (a(this).attr("showTitle") != "false") {
							try {
								a(this).attr("title", m)
							} catch (o) {
							}
						}
					}
					a(this).css({
						"font-size" : "118px"
					});
					a(this).blur()
				})
	};
	a.fn.textInputStyleRender = function() {
		a(this).css("fontFamily", fontFamily);
		a(this).css("fontSize", fontSize);
		var b = null;
		if (a(this).attr("inputMode")) {
			var c = a(this).attr("inputMode");
			if (c == "numberOnly") {
				a(this)[0].onkeyup = function() {
					a(this)[0].value = a(this)[0].value.replace(/\D/g, "")
				};
				a(this)[0].onafterpaste = function() {
					a(this)[0].value = a(this)[0].value.replace(/\D/g, "")
				}
			}
		}
		a(this).hover(function() {
			if (b != a(this)[0]) {
				a(this).addClass("date_hover")
			}
		}, function() {
			if (b != a(this)[0]) {
				a(this).removeClass("date_hover")
			}
		});
		a(this).focus(function() {
			b = a(this)[0];
			a(this).removeClass("date_hover");
			a(this).addClass("date_click")
		});
		a(this).blur(function() {
			b = null;
			a(this).removeClass("date_click")
		})
	};
	a.fn.passInputRender = function() {
		var b = null;
		a(this).addClass("textinput");
		if (a(this).attr("inputMode")) {
			var c = a(this).attr("inputMode");
			if (c == "numberOnly") {
				a(this)[0].onkeyup = function() {
					a(this)[0].value = a(this)[0].value.replace(/\D/g, "")
				};
				a(this)[0].onafterpaste = function() {
					a(this)[0].value = a(this)[0].value.replace(/\D/g, "")
				}
			}
		}
		a(this).bind("keydown", function(f) {
			var d = f.keyCode || f.which || f.charCode;
			if (d == 13) {
				a(this).trigger("enter")
			}
		});
		a(this).hover(function() {
			if (b != a(this)[0]) {
				a(this).removeClass("textinput");
				a(this).addClass("textinput_hover")
			}
		}, function() {
			if (b != a(this)[0]) {
				a(this).removeClass("textinput_hover");
				a(this).addClass("textinput")
			}
		});
		a(this).focus(function() {
			b = a(this)[0];
			a(this).removeClass("textinput");
			a(this).removeClass("textinput_hover");
			a(this).addClass("textinput_click")
		});
		a(this).blur(function() {
			b = null;
			a(this).removeClass("textinput_click");
			a(this).addClass("textinput")
		});
		if (a(this).attr("clearable") == "true") {
			a(this).clearableTextField()
		}
		if (a(this).attr("maxNum") != null) {
			a(this).maxlength({
				maxCharacters : parseInt(a(this).attr("maxNum"))
			})
		}
		if (a(this).attr("checkStrength") == "true") {
			a(this).password_strength()
		}
		if (a(this).attr("checkCaps") !== "false") {
			a(this).caps(function(d) {
				if (d) {
					a(this).tip({
						showCloseBtn : true,
						content : uncompile(quiLanguage.jsError.capslock),
						width : 160
					})
				} else {
				}
			})
		}
	};
	a.fn.textareaRender = function() {
		var b = null;
		a(this).addClass("textarea");
		a(this).css("fontFamily", fontFamily);
		a(this).css("fontSize", fontSize);
		if (a(this).attr("maxNum") != null) {
			a(this).maxlength({
				maxCharacters : parseInt(a(this).attr("maxNum"))
			})
		}
		if (a(this).attr("resize") == "true") {
			a(this).TextAreaResizer()
		}
		if (a(this).attr("autoHeight") == "true") {
			a(this).css({
				height : "auto"
			});
			a(this).attr("rows", 5);
			a(this).autoGrow()
		}
		if (a(this).attr("watermark") != null) {
			a(this).watermark("watermark", a(this).attr("watermark"))
		}
		a(this).hover(function() {
			if (b != a(this)[0]) {
				a(this).removeClass("textarea");
				a(this).addClass("textarea_hover")
			}
		}, function() {
			if (b != a(this)[0]) {
				a(this).removeClass("textarea_hover");
				a(this).addClass("textarea")
			}
		});
		a(this).focus(function() {
			b = a(this)[0];
			a(this).removeClass("textarea");
			a(this).removeClass("textarea_hover");
			a(this).addClass("textarea_click")
		});
		a(this).blur(function() {
			b = null;
			a(this).removeClass("textarea_click");
			a(this).addClass("textarea")
		})
	};
	a.fn.buttonInputRender = function() {
		if (!a(this).attr("class")) {
			a(this).addClass("button")
		}
		if (broswerFlag == "IE7") {
			a(this).css("minWidth", "auto")
		}
		a(this).css("fontFamily", fontFamily);
		a(this).css("fontSize", fontSize);
		var c = _getStrLength(a(this).val());
		if (a(this).attr("useMinWidth") == "false"
				|| a(this).attr("useMinWidth") == false) {
		} else {
			if (c < 5) {
			}
		}
		a(this).hover(function() {
			a(this).addClass("button_hover")
		}, function() {
			a(this).removeClass("button_hover")
		});
		if (a(this).attr("toggle") == "true" || a(this).attr("toggle") == true) {
			var d = a("<input type='hidden'/>");
			if (a(this).attr("name") != null) {
				d.attr("name", a(this).attr("name"))
			}
			a(this).after(d);
			var b = 0;
			if (a(this).attr("relValue") == "1") {
				b = 1
			}
			a(this).attr("relValue", b);
			d.attr("relValue", b);
			if (b == 0) {
				a(this).toggle(function() {
					a(this).addClass("toggle");
					a(this).attr("relValue", 1);
					d.attr("relValue", 1)
				}, function() {
					a(this).removeClass("toggle");
					a(this).attr("relValue", 0);
					d.attr("relValue", 0)
				})
			} else {
				a(this).addClass("toggle");
				a(this).toggle(function() {
					a(this).removeClass("toggle");
					a(this).attr("relValue", 0);
					d.attr("relValue", 0)
				}, function() {
					a(this).addClass("toggle");
					a(this).attr("relValue", 1);
					d.attr("relValue", 1)
				})
			}
		}
	};
	a.fn.buttonRender = function() {
		if (!a(this).attr("class")) {
			a(this).addClass("button")
		}
		if (broswerFlag == "IE7") {
			a(this).css("minWidth", "auto")
		}
		if (a(this).attr("useMinWidth") == "false"
				|| a(this).attr("useMinWidth") == false) {
		} else {
			var c = _getStrLength(a(this).text());
			var b = 0;
			var d = 50;
			b = _getStrLength(a(this).filter(":has(span)").find("span").text());
			if (b != 0) {
				d = 20 + 7 * b + 10
			}
			if (broswerFlag == "firefox" || broswerFlag == "opera"
					|| broswerFlag == "safari") {
				a(this).filter(":has(span)").css({
					paddingLeft : "5px"
				})
			} else {
				a(this).filter(":has(span)").css({
					paddingLeft : "5px"
				})
			}
			if (c < 5) {
			}
		}
		a(this).filter(":has(span)").find("span").css({
			cursor : "default",
			fontFamily : fontFamily,
			fontSize : fontSize
		});
		a(this).hover(function() {
			a(this).addClass("button_hover")
		}, function() {
			a(this).removeClass("button_hover")
		})
	};
	a.fn.dateRender = function() {
		var d = null;
		a(this).css("fontFamily", fontFamily);
		a(this).css("fontSize", fontSize);
		var c = "yyyy-MM-dd";
		if (a(this).attr("dateFmt") != null) {
			c = a(this).attr("dateFmt")
		}
		var b = false;
		if (a(this).attr("doubleCal") == true
				|| a(this).attr("doubleCal") == "true") {
			b = true
		}
		a(this).hover(function() {
			if (d != a(this)[0]) {
				a(this).addClass("date_hover")
			}
		}, function() {
			if (d != a(this)[0]) {
				a(this).removeClass("date_hover")
			}
		});
		a(this).focus(function() {
			try {
				WdatePicker({
					skin : themeColor,
					isShowClear : true,
					dateFmt : c,
					doubleCalendar : b,
					onpicked : function(e) {
						a(this).blur()
					}
				})
			} catch (f) {
				alert(uncompile(quiLanguage.jsError.WdatePicker))
			}
			d = a(this)[0];
			a(this).removeClass("date_hover");
			a(this).addClass("date_click")
		});
		a(this).blur(function() {
			d = null;
			a(this).removeClass("date_click")
		})
	};
	a.fn.popupMenuRender = function() {
		a(this).hover(function() {
			a(this).find(".popupMenu_con").show()
		}, function() {
			a(this).find(".popupMenu_con").hide()
		})
	};
	a.fn.singleNavRender = function() {
		var b = a(this);
		b.find(">div span").each(function() {
			a(this).click(function() {
				b.find(">div").removeClass("current");
				a(this).parent("div").addClass("current")
			});
			a(this).hover(function() {
				a(this).animate({
					paddingLeft : "40px"
				}, "fast")
			}, function() {
				a(this).animate({
					paddingLeft : "20px"
				})
			})
		})
	};
	a.fn.singleNavMinRender = function() {
		var b = a(this);
		b.find(">div span").each(function() {
			a(this).click(function() {
				b.find(">div").removeClass("current");
				a(this).parent("div").addClass("current")
			});
			a(this).hover(function() {
				a(this).animate({
					paddingLeft : "15px"
				}, "fast")
			}, function() {
				a(this).animate({
					paddingLeft : "5px"
				})
			})
		})
	};
	a.fn.tableRender = function() {
		return this
				.each(function() {
					if (a(this).attr("mode") == "list") {
						if (a(this).attr("thTrueWidth") == "true"
								|| a(this).attr("thTrueWidth") == true) {
							a("#scrollContent").css({
								overflowX : "auto"
							});
							var b = 0;
							a(this).find("tr").eq(0).find("th").each(
									function() {
										var g = Number(a(this)
												.attr("trueWidth"));
										b = b + g;
										a(this).width(g)
									});
							a(this).width(b)
						} else {
							if (a(this).attr("tdTrueWidth") == "true"
									|| a(this).attr("tdTrueWidth") == true) {
								a("#scrollContent").css({
									overflowX : "auto"
								});
								var e = 0;
								a(this).find("tr").eq(0).find("td").each(
										function() {
											var g = Number(a(this).attr(
													"trueWidth"));
											e = e + g;
											a(this).width(g)
										});
								a(this).width(e)
							}
						}
						if (a(this).attr("fixedCellHeight") == "true"
								|| a(this).attr("fixedCellHeight") == true) {
						} else {
							a(this).addClass("tableStyleWordWrap")
						}
						if (a(this).find("tr").eq(1).find("td").eq(0).find(
								'input[type="checkbox"]').length == 1) {
							if (a(this).attr("useCheckBox") != "false") {
								a(this).attr("useCheckBox", "true")
							}
							if (a(this).attr("useMultColor") != "false") {
								a(this).attr("useMultColor", "true")
							}
						}
						if (a(this).find("tr").eq(1).find("td").eq(0).find(
								'input[type="radio"]').length == 1) {
							if (a(this).attr("useRadio") != "false") {
								a(this).attr("useRadio", "true")
							}
						}
						if (a(this).attr("useColor") != "false") {
							a(this).find("tr:even").addClass("odd")
						}
						if (a(this).attr("useHover") != "false") {
							a(this).find("tr").hover(function() {
								a(this).addClass("highlight")
							}, function() {
								a(this).removeClass("highlight")
							})
						}
						if (a(this).attr("sortMode") == "true") {
							a(this).find("th").filter(":has(span)").hover(
									function() {
										a(this).removeClass("th");
										a(this).addClass("th_over")
									}, function() {
										a(this).removeClass("th_over");
										a(this).addClass("th")
									});
							a(this).find("th span").addClass("sort_off");
							a(this).find("th").click(function() {
							})
						}
						if (a(this).attr("useClick") != "false") {
							a(this).attr("useClick", "true")
						}
						if (a(this).attr("useClick") == "true"
								&& a(this).attr("useMultColor") == "true") {
							a(this).attr("useClick", "false")
						}
						if (a(this).attr("useRadio") != "true") {
							a(this).attr("useRadio", "false")
						}
						if (a(this).attr("useCheckBox") != "true") {
							a(this).attr("useCheckBox", "false")
						}
						if (a(this).attr("useClick") != "false") {
							if (a(this).attr("useRadio") == "false") {
								a(this).find("tr").click(function() {
									a(this).siblings().removeClass("selected");
									a(this).addClass("selected")
								})
							} else {
								a(this).find('input[type="radio"]:checked')
										.parents("tr").addClass("selected");
								a(this).find("tr").click(
										function() {
											a(this).siblings().removeClass(
													"selected");
											a(this).addClass("selected");
											a(this).find('input[type="radio"]')
													.attr("checked", "checked")
										})
							}
						}
						if (a(this).attr("useMultColor") == "true") {
							if (a(this).attr("useCheckBox") == "false") {
								a(this).find("tr").click(function() {
									a(this).toggleClass("selected")
								})
							} else {
								a(this).find('input[type="checkbox"]:checked')
										.parents("tr").addClass("selected");
								if (a(this).find("th").length > 0) {
									var c = a("<img src="
											+ prePath
											+ 'libs/icons/checkAllOff.gif title="'
											+ uncompile(quiLanguage.table.selectAllBtnText)
											+ '" class="hand"></span>');
									a(this).find("th").eq(0).addClass("ali02")
											.html("").append(c);
									if (a(this).attr("headFixMode") == "true") {
										c
												.toggle(
														function() {
															a(
																	"table:[class=tableStyle]")
																	.find("tr")
																	.each(
																			function() {
																				a(
																						this)
																						.addClass(
																								"selected");
																				a(
																						this)
																						.find(
																								'input[type="checkbox"]')
																						.attr(
																								"checked",
																								"checked")
																			});
															a(this)
																	.attr(
																			"src",
																			prePath
																					+ "libs/icons/checkAllOn.gif");
															a(this)
																	.attr(
																			"title",
																			uncompile(quiLanguage.table.deSelectAllBtnText))
														},
														function() {
															a(
																	"table:[class=tableStyle]")
																	.find("tr")
																	.each(
																			function() {
																				if (a(
																						this)
																						.hasClass(
																								"selected")) {
																					a(
																							this)
																							.removeClass(
																									"selected");
																					a(
																							this)
																							.find(
																									'input[type="checkbox"]')
																							.removeAttr(
																									"checked")
																				}
																			});
															a(this)
																	.attr(
																			"src",
																			prePath
																					+ "libs/icons/checkAllOff.gif");
															a(this)
																	.attr(
																			"title",
																			uncompile(quiLanguage.table.selectAllBtnText))
														})
									} else {
										c
												.toggle(
														function() {
															a(this)
																	.parents(
																			"table")
																	.find("tr")
																	.each(
																			function() {
																				a(
																						this)
																						.addClass(
																								"selected");
																				a(
																						this)
																						.find(
																								'input[type="checkbox"]')
																						.attr(
																								"checked",
																								"checked")
																			});
															a(this)
																	.attr(
																			"src",
																			prePath
																					+ "libs/icons/checkAllOn.gif");
															a(this)
																	.attr(
																			"title",
																			uncompile(quiLanguage.table.deSelectAllBtnText))
														},
														function() {
															a(this)
																	.parents(
																			"table")
																	.find("tr")
																	.each(
																			function() {
																				if (a(
																						this)
																						.hasClass(
																								"selected")) {
																					a(
																							this)
																							.removeClass(
																									"selected");
																					a(
																							this)
																							.find(
																									'input[type="checkbox"]')
																							.removeAttr(
																									"checked")
																				}
																			});
															a(this)
																	.attr(
																			"src",
																			prePath
																					+ "libs/icons/checkAllOff.gif");
															a(this)
																	.attr(
																			"title",
																			uncompile(quiLanguage.table.selectAllBtnText))
														})
									}
								}
								if (a(this).attr("selectRowButtonOnly") == false
										|| a(this).attr("selectRowButtonOnly") == "false") {
									a(this)
											.find("tr:has(td)")
											.each(
													function() {
														a(this)
																.find("td")
																.eq(0)
																.addClass(
																		"ali02");
														a(this).unbind("click");
														a(this)
																.bind(
																		"click",
																		function() {
																			if (a(
																					this)
																					.hasClass(
																							"selected")) {
																				a(
																						this)
																						.removeClass(
																								"selected");
																				a(
																						this)
																						.find(
																								"td")
																						.eq(
																								0)
																						.find(
																								'input[type="checkbox"]')
																						.attr(
																								"checked",
																								false)
																			} else {
																				a(
																						this)
																						.addClass(
																								"selected");
																				a(
																						this)
																						.find(
																								"td")
																						.eq(
																								0)
																						.find(
																								'input[type="checkbox"]')
																						.attr(
																								"checked",
																								true)
																			}
																		})
													})
								} else {
									a(this)
											.find("tr:has(td)")
											.find('input[type="checkbox"]')
											.each(
													function() {
														a(this)
																.parents("td")
																.addClass(
																		"ali02");
														a(this).unbind("click");
														a(this)
																.bind(
																		"click",
																		function() {
																			if (a(
																					this)
																					.parents(
																							"tr")
																					.hasClass(
																							"selected")) {
																				a(
																						this)
																						.parents(
																								"tr")
																						.removeClass(
																								"selected")
																			} else {
																				a(
																						this)
																						.parents(
																								"tr")
																						.addClass(
																								"selected")
																			}
																		})
													})
								}
							}
						}
					}
					if (a(this).attr("formMode") == "line") {
						a(this).attr("useColor", "false");
						a(this).attr("useHover", "false");
						a(this).attr("useClick", "false");
						a(this).find("th").css({
							fontWeight : "bold",
							"text-align" : "center"
						});
						a(this).find("tr").find("td:even").css("text-align",
								"right");
						if (a(this).attr("footer") != null) {
							if (a(this).attr("footer") == "left") {
								a(this).find("tr:last").find("td").css(
										"text-align", "left")
							} else {
								if (a(this).attr("footer") == "right") {
									a(this).find("tr:last").find("td").css(
											"text-align", "right")
								} else {
									if (a(this).attr("footer") == "center") {
										a(this).find("tr:last").find("td").css(
												"text-align", "center")
									} else {
										if (a(this).attr("footer") == "normal") {
											a(this).find("tr:last").find(
													"td:even").css(
													"text-align", "right")
										}
									}
								}
							}
						} else {
							var f = a(this).find("tr:last").find("td").eq(0)
									.attr("colspan");
							if (f) {
								if (f.toString() != "1") {
									a(this).find("tr:last").find("td").css(
											"text-align", "center")
								}
							}
						}
						a(this).find("td").css({
							paddingTop : "3px",
							paddingBottom : "3px"
						})
					} else {
						if (a(this).attr("formMode") == "transparent") {
							a(this).attr("useColor", "false");
							a(this).attr("useHover", "false");
							a(this).attr("useClick", "false");
							a(this).find("th").css({
								fontWeight : "bold",
								"text-align" : "center"
							});
							a(this).css({
								border : "none",
								backgroundColor : "transparent"
							});
							a(this).find("tr").css({
								border : "none",
								backgroundColor : "transparent"
							});
							a(this).find("tr").find("td:even").css(
									"text-align", "right");
							if (a(this).attr("footer") != null) {
								if (a(this).attr("footer") == "left") {
									a(this).find("tr:last").find("td").css(
											"text-align", "left")
								} else {
									if (a(this).attr("footer") == "right") {
										a(this).find("tr:last").find("td").css(
												"text-align", "right")
									} else {
										if (a(this).attr("footer") == "center") {
											a(this)
													.find("tr:last")
													.find("td")
													.css("text-align", "center")
										} else {
											if (a(this).attr("footer") == "normal") {
												a(this).find("tr:last").find(
														"td:even").css(
														"text-align", "right")
											}
										}
									}
								}
							} else {
								var d = a(this).find("tr:last").find("td")
										.eq(0).attr("colspan");
								if (d) {
									if (d.toString() != "1") {
										a(this).find("tr:last").find("td").css(
												"text-align", "center")
									}
								}
							}
							a(this).find("td").css({
								paddingTop : "3px",
								paddingBottom : "3px",
								border : "none"
							})
						} else {
							if (a(this).attr("formMode") == "view") {
								a(this).attr("useColor", "false");
								a(this).attr("useHover", "false");
								a(this).attr("useClick", "false");
								a(this).find("th").css({
									fontWeight : "bold",
									"text-align" : "center"
								});
								a(this).find("tr").find("td:even").addClass(
										"viewModeEven");
								if (a(this).attr("footer") != null) {
									if (a(this).attr("footer") == "left") {
										a(this).find("tr:last").find("td").css(
												{
													textAlign : "left",
													backgroundColor : "#ffffff"
												})
									} else {
										if (a(this).attr("footer") == "right") {
											a(this)
													.find("tr:last")
													.find("td")
													.css(
															{
																textAlign : "right",
																backgroundColor : "#ffffff"
															})
										} else {
											if (a(this).attr("footer") == "center") {
												a(this).find("tr:last").find(
														"td").css({
													textAlign : "center",
													backgroundColor : "#ffffff"
												})
											} else {
												if (a(this).attr("footer") == "normal") {
													a(this)
															.find("tr:last")
															.find("td:even")
															.css(
																	{
																		textAlign : "right",
																		backgroundColor : "#ffffff"
																	})
												}
											}
										}
									}
								} else {
									var f = a(this).find("tr:last").find("td")
											.eq(0).attr("colspan");
									if (f) {
										if (f.toString() != "1") {
											a(this)
													.find("tr:last")
													.find("td")
													.css(
															{
																textAlign : "center",
																backgroundColor : "#ffffff"
															})
										}
									}
								}
								a(this).find("td").css({
									paddingTop : "6px",
									paddingBottom : "6px"
								})
							}
						}
					}
					a(this).find("th").addClass("th")
				})
	}
})(jQuery);
function getPosition(c, d) {
	var a = -1;
	for ( var b = 0; b < d.length; b++) {
		if (c == d[b]) {
			a = b;
			break
		}
	}
	return a
}
jQuery.fn.extend({
	selectRender : function() {
		return this.each(function() {
			if ($(this).prev("div").hasClass("mainCon")) {
				$(this).prev("div").remove()
			}
			new jQuery.SelectBox(this)
		})
	},
	selectAddItem : function(a) {
		this.each(function() {
			var c = $(this).data("data");
			var d = "list";
			if ($(this).attr("dataRoot")) {
				d = $(this).attr("dataRoot")
			}
			var b;
			if (c[d]) {
				b = c[d]
			} else {
				b = c
			}
			b.push(a);
			$(this).data("data", c);
			$(this).prev(".mainCon").remove();
			new jQuery.SelectBox(this)
		})
	},
	selectRemoveItem : function(a) {
		this.each(function() {
			var c = $(this).data("data");
			var d = -1;
			var e = "list";
			if ($(this).attr("dataRoot")) {
				e = $(this).attr("dataRoot")
			}
			var b;
			if (c[e]) {
				b = c[e]
			} else {
				b = c
			}
			$.each(b, function(f, g) {
				if (g[valueField].toString() == a) {
					d = f
				}
			});
			if (d != -1) {
				b.splice(d, 1)
			}
			$(this).data("data", c);
			$(this).prev(".mainCon").remove();
			new jQuery.SelectBox(this)
		})
	}
});
if (!window.console) {
	var console = {
		log : function(a) {
		}
	}
}
var elm_id = 1;
jQuery.SelectBox = function(Y) {
	var Z = {};
	Z.inputClass = Z.inputClass || "selectbox";
	Z.containerClass = Z.containerClass || "selectbox-wrapper";
	Z.hoverClass = Z.hoverClass || "current";
	Z.currentClass = Z.selectedClass || "selected";
	Z.debug = Z.debug || false;
	elm_id++;
	var ba = "0_input";
	var bb = "0_button";
	var bc = 0;
	var bd = false;
	var be = 0;
	var bf = $(Y);
	var bg = setupContainer(Z);
	var bh = setupMainCon();
	var bi = setupInput(Z);
	var bj = false;
	var bk = false;
	var bl = 1;
	var bm;
	var bn;
	var bo = 0;
	var bp = 0;
	var bq = 24;
	var br = 24;
	var bs = 26;
	var bt = 0;
	var bu = 0;
	var bv = true;
	var bw = "value";
	var bx = "key";
	if (bf.attr("valueField")) {
		bw = bf.attr("valueField")
	}
	if (bf.attr("labelField")) {
		bx = bf.attr("labelField")
	}
	if (bf.attr("selItemHeight")) {
		bs = bf.attr("selItemHeight")
	}
	if (bf.attr("boxAutoScroll") == "false"
			|| bf.attr("boxAutoScroll") == false) {
		bv = false
	}
	if (!splitMode) {
		var by = $(window.top.document.getElementById("theme"));
		if (by.attr("selInputHeight") != null) {
			bq = Number(by.attr("selInputHeight"))
		}
		if (by.attr("selButtonWidth") != null) {
			br = Number(by.attr("selButtonWidth"))
		}
		if (by.attr("defaultSelWidth") != null) {
			bt = Number(by.attr("defaultSelWidth"))
		}
		if (by.attr("defaultSelItemHeight") != null) {
			bs = Number(by.attr("defaultSelItemHeight"))
		}
	} else {
		if (bf.attr("selInputHeight") != null) {
			bq = Number(bf.attr("selInputHeight"))
		}
		if (bf.attr("selButtonWidth") != null) {
			br = Number(bf.attr("selButtonWidth"))
		}
	}
	if (window.navigator.userAgent.indexOf("Windows") > -1) {
		bo = 1
	}
	bn = bf.width();
	if (bn == "0") {
		bn = 116
	}
	var bz;
	bz = $("<input type='button' value=' ' class='selBtn'/>");
	bz.attr("id", elm_id + "_button");
	var bA = $("<div class='loader'></div>");
	bA.text(quiLangage.select.loadingMessage);
	if (bf.attr("colNum") != null) {
		bl = parseInt(bf.attr("colNum"))
	}
	if (bf.attr("colWidth") != null) {
		bm = Number(bf.attr("colWidth"))
	} else {
		bm = 100
	}
	var bB = 97;
	if (bf.attr("selWidth") != null) {
		bB = Number(bf.attr("selWidth")) - br
	} else {
		if (bt != 0) {
			bB = bt - br
		}
	}
	bi.width(bB);
	bi.css("fontFamily", fontFamily);
	bi.css("fontSize", fontSize);
	bf.hide().before(bh);
	var bC = $('<table cellspacing="0" cellpadding="0" style="border-style:none;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"></td><td class="ali01" style="border-style:none;;padding:0;margin:0;"></td></tr></table>');
	bC.find("td").eq(0).append(bi);
	bC.find("td").eq(1).append(bz);
	bh.append(bC);
	bh.append(bg);
	bh.append(bA);
	bA.hide();
	if (bf.attr("disabled") == "disabled" || bf.attr("disabled") == "true"
			|| bf.attr("disabled") == true) {
		bz.attr("disabled", true);
		bz.addClass("selBtn_disabled");
		bi.addClass("selectbox_disabled")
	}
	bf.data("scrollY", 0);
	init();
	if (bf.attr("editable") != null) {
		if (bf.attr("editable") == "true") {
			bk = true
		} else {
			bk = false
		}
	}
	if (!bk) {
		bi.css({
			cursor : "pointer"
		});
		bi.click(function(a) {
			ba = $(a.target).attr("id");
			setHeight();
			if (bg.attr("hasfocus") == 0) {
				showMe()
			} else {
				hideMe()
			}
		}).keydown(function(a) {
			switch (a.keyCode) {
			case 38:
				a.preventDefault();
				moveSelect(-1);
				break;
			case 40:
				a.preventDefault();
				moveSelect(1);
				break;
			case 13:
				a.preventDefault();
				$("li." + Z.hoverClass).trigger("click");
				break;
			case 27:
				hideMe();
				break
			}
		})
	} else {
		bi.css({
			cursor : "text"
		});
		bi.change(function() {
			bf.attr("editValue", $(this).val())
		})
	}
	bz.click(function(a) {
		bb = $(a.target).attr("id");
		setHeight();
		if (bg.attr("hasfocus") == 0) {
			showMe()
		} else {
			hideMe()
		}
	}).keydown(function(a) {
		switch (a.keyCode) {
		case 38:
			a.preventDefault();
			moveSelect(-1);
			break;
		case 40:
			a.preventDefault();
			moveSelect(1);
			break;
		case 13:
			a.preventDefault();
			$("li." + Z.hoverClass).trigger("click");
			break;
		case 27:
			hideMe();
			break
		}
	});
	function setHeight() {
		bf.blur();
		var a;
		var b = bg.find("li").length;
		if (bl == 1) {
			a = b * bs
		} else {
			if (b % bl == 0) {
				a = b * bs / bl
			} else {
				a = (b - b % bl) * bs / bl + bs
			}
		}
		bg.height(a);
		var c = 200;
		c = window.document.documentElement.clientHeight
				- (bh.offset().top - $(window).scrollTop()) - 30;
		var d;
		if (!bf.attr("boxWidth")) {
			d = bg.width()
		}
		bg.css({
			overflowY : "auto",
			overflowX : "hidden"
		});
		if (bl != 1) {
			bg.width((bm + 6) * bl)
		} else {
			if (!bf.attr("boxWidth")) {
				bg.width(d)
			} else {
				bg.width(Number(bf.attr("boxWidth")) + 1)
			}
		}
		var e = 0;
		if (bf.attr("boxHeight")) {
			e = Number(bf.attr("boxHeight"))
		}
		if (e != 0) {
			bg.height(e);
			if (bf.attr("openDirection") == "top") {
				bg.css({
					top : -e
				})
			} else {
				if (bf.attr("openDirection") == "bottom") {
					bg.css({
						top : bq
					})
				} else {
					if (c < e) {
						if (bh.offset().top > e) {
							bg.css({
								top : -e
							})
						} else {
							if (c < 100 && bh.offset().top > c
									&& bh.offset().top > 100) {
								bg.css({
									top : -e
								})
							} else {
								bg.css({
									top : bq
								})
							}
						}
					} else {
						bg.css({
							top : bq
						})
					}
				}
			}
			if (bv == true) {
				if (bf.data("scrollY") + bs > e) {
					bg.animate({
						scrollTop : bf.data("scrollY")
					}, 600)
				}
			}
		} else {
			if (bf.attr("openDirection") == "top") {
				if (bh.offset().top > a) {
					bg.css({
						top : -a
					})
				} else {
					bg.height(bh.offset().top);
					bg.css({
						top : -bh.offset().top
					})
				}
			} else {
				if (bf.attr("openDirection") == "bottom") {
					if (c < a) {
						bg.css({
							top : bq
						});
						bg.height(c)
					} else {
						bg.css({
							top : bq
						})
					}
				} else {
					if (c < a) {
						if (bh.offset().top > a) {
							bg.css({
								top : -a
							})
						} else {
							if (c < 100 && bh.offset().top > c
									&& bh.offset().top > 100) {
								bg.height(bh.offset().top);
								bg.css({
									top : -bh.offset().top
								})
							} else {
								bg.css({
									top : bq
								});
								bg.height(c)
							}
						}
					} else {
						bg.css({
							top : bq
						})
					}
				}
			}
			if (bv == true) {
				if (bf.data("scrollY") + bs > bg.height()) {
					bg.animate({
						scrollTop : bf.data("scrollY")
					}, 600)
				}
			}
		}
		if (!bf.attr("boxWidth")) {
			if (bg.width() < bB + br) {
				bg.width(bB + br)
			}
		}
	}
	function hideMe() {
		bg.attr("hasfocus", 0);
		bg.hide();
		$("body").unbind("mousedown", onBodyDown)
	}
	function showMe() {
		bg.attr("hasfocus", 1);
		depth++;
		bh.css({
			zIndex : depth
		});
		bg.show();
		$("body").bind("mousedown", onBodyDown)
	}
	function onBodyDown(a) {
		if ($(a.target).attr("id") == ba || $(a.target).attr("id") == bb
				|| $(a.target).parent().attr("class") == "selectbox-wrapper"
				|| $(a.target).attr("class") == "selectbox-wrapper"
				|| $(a.target).parents(".selectbox-wrapper").length > 0) {
		} else {
			hideMe()
		}
	}
	function init() {
		bg.append(getSelectOptions(bi.attr("id"))).hide();
		var a = bi.css("width")
	}
	function setupMainCon() {
		var a = $("<div></div>");
		a.addClass("mainCon");
		if (bf.attr("selAlign") == "right") {
			a.css("float", "right")
		}
		return a
	}
	function setupContainer(a) {
		var b = $("<div></div>");
		b.attr("id", elm_id + "_container");
		b.addClass(a.containerClass);
		b.css({});
		b.attr("hasfocus", 0);
		return b
	}
	function setupInput(a) {
		var b = document.createElement("input");
		var c = $(b);
		c.attr("id", elm_id + "_input");
		c.attr("type", "text");
		c.addClass(a.inputClass);
		if (broswerFlag == "IE8") {
			c.addClass("selectboxFont")
		}
		c.attr("autocomplete", "off");
		var d = false;
		if (bf.attr("editable") != null) {
			if (bf.attr("editable") == "true") {
				d = true
			} else {
				d = false
			}
		}
		if (!d) {
			if (broswerFlag == "firefox") {
				c.attr("contenteditable", false)
			} else {
				c.attr("readonly", "readonly")
			}
		} else {
			c.attr("readonly", false)
		}
		c.attr("tabIndex", bf.attr("tabindex"));
		if (bf.attr("disabled") == "disabled" || bf.attr("disabled") == "true"
				|| bf.attr("disabled") == true) {
			c.attr("disabled", true);
			c.addClass("inputDisabled")
		}
		return c
	}
	function moveSelect(a) {
		var b = $("li", bg);
		if (!b || b.length == 0) {
			return false
		}
		bc += a;
		if (bc < 0) {
			bc = b.size()
		} else {
			if (bc > b.size()) {
				bc = 0
			}
		}
		scroll(b, bc);
		b.removeClass(Z.hoverClass);
		$(b[bc]).addClass(Z.hoverClass)
	}
	function scroll(a, b) {
		var c = $(a[b]).get(0);
		var a = bg.get(0);
		if (c.offsetTop + c.offsetHeight > a.scrollTop + a.clientHeight) {
			a.scrollTop = c.offsetTop + c.offsetHeight - a.clientHeight
		} else {
			if (c.offsetTop < a.scrollTop) {
				a.scrollTop = c.offsetTop
			}
		}
	}
	function setCurrent() {
		var a = $("li." + Z.currentClass, bg).get(0);
		var b = (a.id).split("_");
		var c = b[0].length + b[1].length + 2;
		var d = a.id;
		var e = d.substr(c, d.length);
		bf.val(e);
		bf.attr("relText", $(a).text());
		bf.attr("relValue", e);
		var d = $(a).html().trim();
		bi.val(d);
		if (bk == true) {
			bf.attr("editValue", bi.val())
		}
		bf.focus();
		return true
	}
	function getCurrentSelected() {
		return bf.val()
	}
	function getCurrentValue() {
		return bi.val()
	}
	function getSelectOptions(i) {
		var j = new Array();
		var k = document.createElement("ul");
		var l = [];
		var m = 0;
		var n;
		if (bf.attr("childId") != null) {
			n = true
		}
		var o;
		if (bf.attr("editable") != null) {
			if (bf.attr("editable") == "true") {
				o = true
			} else {
				o = false
			}
		}
		var p = false;
		var q = bf.attr("url");
		var r = bf.attr("data");
		var s = bf.data("data");
		if (q != null || r != null || s != null || bf.attr("dataType") == "xml"
				|| bf.find("option").length == 0) {
			p = true
		}
		if (p == true) {
			var t = "list";
			if (bf.attr("dataRoot")) {
				t = bf.attr("dataRoot")
			}
			var u = bf.attr("params");
			var v;
			if (u) {
				try {
					v = JSON.parse(u)
				} catch (e) {
					v = "";
					alert(quiLangage.select.paramErrorMessage)
				}
			} else {
				v = ""
			}
			if (s) {
				createOptions(s, i, bl, bm, o, n, k, t)
			} else {
				if (r) {
					var w;
					try {
						w = JSON.parse(r)
					} catch (e) {
						w = "";
						alert(quiLangage.select.dataErrorMessage)
					}
					bf.data("data", w);
					createOptions(w, i, bl, bm, o, n, k, t)
				} else {
					if (q && bf.attr("dataType") == "xml") {
						$.ajax({
							url : bf.attr("url"),
							data : v,
							error : function() {
								alert(quiLangage.select.urlErrorMessage)
							},
							success : function(a) {
								createOptions_xml(a, i, bl, bm, o, n, k, t)
							}
						})
					} else {
						if (q) {
							var x = "json";
							if (bf.attr("dataType")) {
								x = bf.attr("dataType")
							}
							$.ajax({
								url : bf.attr("url"),
								dataType : x,
								data : v,
								error : function() {
									alert(quiLangage.select.urlErrorMessage)
								},
								success : function(a) {
									var b;
									if (x == "text") {
										b = eval("(" + a + ")")
									} else {
										b = a
									}
									bf.data("data", b);
									createOptions(b, i, bl, bm, o, n, k, t)
								}
							})
						} else {
							if (bf.attr("dataType") == "xml") {
								createOptions_xml(null, i, bl, bm, o, n, k, t)
							} else {
								if (bf.find("option").length == 0) {
									bf.data("data", {
										list : []
									});
									createOptions(null, i, bl, bm, o, n, k, t)
								}
							}
						}
					}
				}
			}
		} else {
			bf.find("option").each(
					function(d) {
						l.push($(this)[0]);
						var f = document.createElement("li");
						f.setAttribute("id", i + "_" + $(this).val());
						f.innerHTML = $(this).html();
						if ($(this).is(":selected")) {
							if (o == true) {
								bi.val($(this).html());
								$(f).addClass(Z.currentClass)
							} else {
								var g = $(this).html().trim();
								bi.val(g);
								$(f).addClass(Z.currentClass)
							}
						}
						if (bl != 1) {
							$(f).addClass("li_left");
							if (bm != null) {
								$(f).width(bm)
							} else {
								var h = Number(bn);
								$(f).width(h)
							}
						}
						k.appendChild(f);
						$(f).mouseover(function(a) {
							be = 1;
							if (Z.debug) {
								console.log("over on : " + this.id)
							}
							jQuery(a.target, bg).addClass(Z.hoverClass)
						}).mouseout(function(a) {
							be = -1;
							if (Z.debug) {
								console.log("out on : " + this.id)
							}
							jQuery(a.target, bg).removeClass(Z.hoverClass)
						}).click(
								function(a) {
									var b = $("li." + Z.hoverClass, bg).get(0);
									if (Z.debug) {
										console.log("click on :" + this.id)
									}
									var c = $(this).attr("id").split("_");
									$(
											"#" + c[0] + "_container li."
													+ Z.currentClass)
											.removeClass(Z.currentClass);
									$(this).addClass(Z.currentClass);
									setCurrent();
									bf.get(0).blur();
									hideMe();
									try {
										bf.trigger("change")
									} catch (e) {
									}
									bi.removeClass("tipColor");
									bf.data("scrollY", d * bs);
									if (n) {
										ajaxLoad(bf, bf.val())
									}
								});
						if (bf.attr("editValue") != null) {
							bi.val(bf.attr("editValue"))
						}
					})
		}
		bf.find("optgroup")
				.each(
						function() {
							var a = getPosition($(this).children("option")
									.eq(0)[0], l);
							var b = $(this).attr("label");
							$(k).find("li").eq(a + m).before(
									"<li class='group'>" + b + "</li>");
							m++
						});
		return k
	}
	function createOptions_xml(h, i, j, k, l, m, n, o, p) {
		var q = quiLangage.select.promptMessage;
		if (bf.attr("prompt") != null) {
			if (bf.attr("prompt") == "") {
				q = quiLangage.select.promptMessage
			} else {
				q = bf.attr("prompt")
			}
		}
		var r = -1;
		var s = "";
		if (bf.attr("selectedIdx")) {
			r = Number(bf.attr("selectedIdx"))
		}
		if (bf.attr("selectedValue")) {
			s = bf.attr("selectedValue")
		}
		bf.attr("length", 10);
		if (bf.attr("prompt") != null) {
			var t = document.createElement("li");
			t.setAttribute("id", i + "_");
			t.innerHTML = q;
			if (r == -1 && s == "") {
				$(t).addClass(Z.currentClass);
				bi.val(t.innerHTML)
			}
			n.appendChild(t);
			bf[0].options.length = 0;
			bf[0].options[bf[0].options.length] = new Option(q, "");
			if (j != 1) {
				$(t).addClass("li_left");
				if (k != null) {
					$(t).width(k)
				} else {
					var u = Number(bn);
					$(t).width(u)
				}
			}
			$(t).mouseover(function(a) {
				be = 1;
				if (Z.debug) {
					console.log("over on : " + this.id)
				}
				jQuery(a.target, bg).addClass(Z.hoverClass)
			}).mouseout(function(a) {
				be = -1;
				if (Z.debug) {
					console.log("out on : " + this.id)
				}
				jQuery(a.target, bg).removeClass(Z.hoverClass)
			}).click(
					function(a) {
						var b = $("li." + Z.hoverClass, bg).get(0);
						if (Z.debug) {
							console.log("click on :" + this.id)
						}
						var c = $(this).attr("id").split("_");
						$("#" + c[0] + "_container li." + Z.currentClass)
								.removeClass(Z.currentClass);
						$(this).addClass(Z.currentClass);
						setCurrent();
						bf.get(0).blur();
						hideMe();
						bf.trigger("change");
						bi.removeClass("tipColor");
						bf.data("scrollY", 0);
						if (m) {
							ajaxLoad(bf, bf.data("selectedNode"))
						}
					})
		}
		if (bf.attr("prompt") == null) {
			if (r == -1 && s == "") {
				r = 0
			}
		}
		var v = 0;
		$.each($(h).find(o), function(d, e) {
			var f = document.createElement("li");
			f.setAttribute("id", i + "_" + $(e).attr(bw));
			f.innerHTML = $(e).attr(bx);
			$(f).data("itemData", e);
			bf[0].options[bf[0].options.length] = new Option($(e).attr(bx),
					$(e).attr(bw));
			if (r == d) {
				if (l == true) {
					$(f).addClass(Z.currentClass);
					bi.val(f.innerHTML);
					bf.val($(e).attr(bx));
					bf.attr("relText", $(e).attr(bx));
					bf.attr("editValue", $(e).attr(bx))
				} else {
					$(f).addClass(Z.currentClass);
					bi.val(f.innerHTML.trim());
					bf.val($(e).attr(bw));
					bf.attr("relText", $(e).attr(bx));
					bf.attr("relValue", $(e).attr(bw))
				}
				bf.data("selectedNode", e);
				if (bf.attr("prompt") == null) {
					bf.data("scrollY", d * bs)
				} else {
					bf.data("scrollY", (d + 1) * bs)
				}
				if (m) {
					ajaxLoad(bf, e)
				}
				v = 1
			} else {
				if (s != "") {
					if (s == $(e).attr(bw).toString()) {
						if (l == true) {
							$(f).addClass(Z.currentClass);
							bi.val(f.innerHTML);
							bf.val($(e).attr(bw));
							bf.attr("relText", $(e).attr(bx));
							bf.attr("editValue", $(e).attr(bx))
						} else {
							$(f).addClass(Z.currentClass);
							bi.val(f.innerHTML.trim());
							bf.val($(e).attr(bw));
							bf.attr("relText", $(e).attr(bx));
							bf.attr("relValue", $(e).attr(bw))
						}
						bf.data("selectedNode", e);
						if (bf.attr("prompt") == null) {
							bf.data("scrollY", d * bs)
						} else {
							bf.data("scrollY", (d + 1) * bs)
						}
						if (m) {
							ajaxLoad(bf, e)
						}
						v = 1
					}
				}
			}
			if (j != 1) {
				$(f).addClass("li_left");
				if (k != null) {
					$(f).width(k)
				} else {
					var g = Number(bn);
					$(f).width(g)
				}
			}
			$(f).mouseover(function(a) {
				be = 1;
				if (Z.debug) {
					console.log("over on : " + this.id)
				}
				jQuery(a.target, bg).addClass(Z.hoverClass)
			}).mouseout(function(a) {
				be = -1;
				if (Z.debug) {
					console.log("out on : " + this.id)
				}
				jQuery(a.target, bg).removeClass(Z.hoverClass)
			}).click(
					function(a) {
						var b = $("li." + Z.hoverClass, bg).get(0);
						if (Z.debug) {
							console.log("click on :" + this.id)
						}
						var c = $(this).attr("id").split("_");
						$("#" + c[0] + "_container li." + Z.currentClass)
								.removeClass(Z.currentClass);
						$(this).addClass(Z.currentClass);
						setCurrent();
						bf.data("selectedNode", $(this).data("itemData"));
						bf.get(0).blur();
						hideMe();
						bf.trigger("change");
						bi.removeClass("tipColor");
						if (bf.attr("prompt") == null) {
							bf.data("scrollY", d * bs)
						} else {
							bf.data("scrollY", (d + 1) * bs)
						}
						if (m) {
							ajaxLoad(bf, bf.data("selectedNode"))
						}
					});
			n.appendChild(f);
			if (bf.attr("editValue") != null) {
				bi.val(bf.attr("editValue"))
			}
		});
		if (v == 0) {
			if (bf.attr("prompt")) {
				$(n).find("li").eq(0).addClass(Z.currentClass);
				bi.val(bf.attr("prompt"))
			}
		}
		bf.attr("finished", "true");
		bf.trigger("ajaxInit")
	}
	function createOptions(h, i, j, k, l, m, n, o, p) {
		var q = quiLangage.select.promptMessage;
		if (bf.attr("prompt") != null) {
			if (bf.attr("prompt") == "") {
				q = quiLangage.select.promptMessage
			} else {
				q = bf.attr("prompt")
			}
		}
		var r = -1;
		var s = "";
		if (bf.attr("selectedIdx")) {
			r = Number(bf.attr("selectedIdx"))
		}
		if (bf.attr("selectedValue")) {
			s = bf.attr("selectedValue")
		}
		bf.attr("length", 10);
		if (bf.attr("prompt") != null) {
			var t = document.createElement("li");
			t.setAttribute("id", i + "_");
			t.innerHTML = q;
			if (r == -1 && s == "") {
				$(t).addClass(Z.currentClass);
				bi.val(t.innerHTML)
			}
			n.appendChild(t);
			bf[0].options.length = 0;
			bf[0].options[bf[0].options.length] = new Option(q, "");
			if (j != 1) {
				$(t).addClass("li_left");
				if (k != null) {
					$(t).width(k)
				} else {
					var u = Number(bn);
					$(t).width(u)
				}
			}
			$(t).mouseover(function(a) {
				be = 1;
				if (Z.debug) {
					console.log("over on : " + this.id)
				}
				jQuery(a.target, bg).addClass(Z.hoverClass)
			}).mouseout(function(a) {
				be = -1;
				if (Z.debug) {
					console.log("out on : " + this.id)
				}
				jQuery(a.target, bg).removeClass(Z.hoverClass)
			}).click(
					function(a) {
						var b = $("li." + Z.hoverClass, bg).get(0);
						if (Z.debug) {
							console.log("click on :" + this.id)
						}
						var c = $(this).attr("id").split("_");
						$("#" + c[0] + "_container li." + Z.currentClass)
								.removeClass(Z.currentClass);
						$(this).addClass(Z.currentClass);
						setCurrent();
						bf.get(0).blur();
						hideMe();
						bf.trigger("change");
						bi.removeClass("tipColor");
						bf.data("scrollY", 0);
						if (m) {
							ajaxLoad(bf, bf.val())
						}
					})
		}
		if (bf.attr("prompt") == null) {
			if (r == -1 && s == "") {
				r = 0
			}
		}
		var v = 0;
		if (h) {
			var w;
			if (h[o]) {
				w = h[o]
			} else {
				w = h
			}
			$.each(w, function(d, e) {
				var f = document.createElement("li");
				f.setAttribute("id", i + "_" + e[bw]);
				f.innerHTML = e[bx];
				$(f).data("itemData", e);
				bf[0].options[bf[0].options.length] = new Option(e[bx], e[bw]);
				if (r == d) {
					if (l == true) {
						$(f).addClass(Z.currentClass);
						bi.val(f.innerHTML);
						bf.val(e[bw]);
						bf.attr("relText", e[bx]);
						bf.attr("editValue", e[bx])
					} else {
						$(f).addClass(Z.currentClass);
						bi.val(f.innerHTML.trim());
						bf.val(e[bw]);
						bf.attr("relText", e[bx]);
						bf.attr("relValue", e[bw])
					}
					bf.data("selectedNode", e);
					if (bf.attr("prompt") == null) {
						bf.data("scrollY", d * bs)
					} else {
						bf.data("scrollY", (d + 1) * bs)
					}
					if (m) {
						ajaxLoad(bf, e[bw])
					}
					v = 1
				} else {
					if (s != "") {
						if (s == e[bw].toString()) {
							if (l == true) {
								$(f).addClass(Z.currentClass);
								bi.val(f.innerHTML);
								bf.val(e[bw]);
								bf.attr("relText", e[bx]);
								bf.attr("editValue", e[bx])
							} else {
								$(f).addClass(Z.currentClass);
								bi.val(f.innerHTML.trim());
								bf.val(e[bw]);
								bf.attr("relText", e[bx]);
								bf.attr("relValue", e[bw])
							}
							bf.data("selectedNode", e);
							if (bf.attr("prompt") == null) {
								bf.data("scrollY", d * bs)
							} else {
								bf.data("scrollY", (d + 1) * bs)
							}
							if (m) {
								ajaxLoad(bf, e[bw])
							}
							v = 1
						}
					}
				}
				if (j != 1) {
					$(f).addClass("li_left");
					if (k != null) {
						$(f).width(k)
					} else {
						var g = Number(bn);
						$(f).width(g)
					}
				}
				$(f).mouseover(function(a) {
					be = 1;
					if (Z.debug) {
						console.log("over on : " + this.id)
					}
					jQuery(a.target, bg).addClass(Z.hoverClass)
				}).mouseout(function(a) {
					be = -1;
					if (Z.debug) {
						console.log("out on : " + this.id)
					}
					jQuery(a.target, bg).removeClass(Z.hoverClass)
				}).click(
						function(a) {
							var b = $("li." + Z.hoverClass, bg).get(0);
							if (Z.debug) {
								console.log("click on :" + this.id)
							}
							var c = $(this).attr("id").split("_");
							$("#" + c[0] + "_container li." + Z.currentClass)
									.removeClass(Z.currentClass);
							$(this).addClass(Z.currentClass);
							setCurrent();
							bf.data("selectedNode", $(this).data("itemData"));
							bf.get(0).blur();
							hideMe();
							bf.trigger("change");
							bi.removeClass("tipColor");
							if (bf.attr("prompt") == null) {
								bf.data("scrollY", d * bs)
							} else {
								bf.data("scrollY", (d + 1) * bs)
							}
							if (m) {
								ajaxLoad(bf, bf.val())
							}
						});
				n.appendChild(f);
				if (bf.attr("editValue") != null) {
					bi.val(bf.attr("editValue"))
				}
			})
		}
		if (v == 0) {
			if (bf.attr("prompt")) {
				$(n).find("li").eq(0).addClass(Z.currentClass);
				bi.val(bf.attr("prompt"))
			}
		}
		bf.attr("finished", "true");
		bf.trigger("ajaxInit")
	}
	function ajaxLoad(a, b) {
		var c = a.attr("childId");
		var d = $("#" + c).prev().find("div[class=loader]");
		d.show();
		window.setTimeout(function() {
			loadLater(a, b)
		}, 200)
	}
	function loadLater(C, D) {
		var E;
		if (C.attr("childDataType") == "xml") {
			var F = C.attr("childId");
			var G = $("#" + F).prev().find("div[class=loader]");
			G.hide();
			var H = $("#" + F).prev().find("ul");
			var I = $("#" + F).prev().find(">div").attr("id").split("_")[0];
			var J = $("#" + F).prev().find("input:text");
			var K = $("#" + F)[0];
			var L = $("#" + F);
			H.html("");
			K.options.length = 0;
			$("#" + F).data("scrollY", 0);
			var M = "list";
			if ($("#" + F).attr("dataRoot")) {
				M = $("#" + F).attr("dataRoot")
			}
			var N = "key";
			if ($("#" + F).attr("labelField")) {
				N = $("#" + F).attr("labelField")
			}
			var O = "value";
			if ($("#" + F).attr("valueField")) {
				O = $("#" + F).attr("valueField")
			}
			if ($("#" + F).attr("prompt")) {
				var P = document.createElement("li");
				var Q = $("#" + F).attr("prompt");
				$(P).text(Q);
				$(P).attr("relValue", "");
				H.append($(P));
				K.options[K.options.length] = new Option(Q, "");
				$(P).mouseover(function(a) {
					jQuery(a.target).addClass(Z.hoverClass)
				});
				$(P).mouseout(function(a) {
					jQuery(a.target).removeClass(Z.hoverClass)
				});
				$(P).mousedown(
						function(a) {
							$("#" + I + "_container li." + Z.currentClass)
									.removeClass(Z.currentClass);
							$(this).addClass(Z.currentClass);
							$("#" + F).attr("relText", $(this).text());
							$("#" + F).attr("relValue",
									$(this).attr("relValue"));
							$("#" + F).val($(this).attr("relValue"));
							J.val($(this).html());
							$("#" + F).prev().find(">div").hide();
							$("#" + F).prev().find(">div").attr("hasfocus", 0);
							$("#" + F).focus();
							$("#" + F).data("scrollY", 0);
							if ($("#" + F).attr("onchange") != null) {
							}
							try {
								$("#" + F).trigger("change")
							} catch (e) {
							}
						})
			}
			var R = -1;
			var S = "";
			var T;
			var U;
			if (L.attr("childId") != null) {
				U = true
			}
			if (L.attr("selectedIdx")) {
				R = Number(L.attr("selectedIdx"))
			}
			if (L.attr("selectedValue")) {
				S = L.attr("selectedValue")
			}
			if (L.attr("editable") != null) {
				if (L.attr("editable") == "true") {
					T = true
				} else {
					T = false
				}
			}
			var V = 0;
			$.each($(D).find(M), function(b, c) {
				var d = $(c).attr(N);
				var f = $(c).attr(O);
				var g = document.createElement("li");
				$(g).text(d);
				$(g).attr("relValue", f);
				$(g).data("itemData", c);
				H.append($(g));
				K.options[K.options.length] = new Option(d, f);
				if (R == b) {
					if (T == true) {
						$(g).addClass(Z.currentClass);
						J.val(g.innerHTML);
						L.val($(c).attr(O));
						L.attr("relText", $(c).attr(N));
						L.attr("editValue", $(c).attr(N))
					} else {
						$(g).addClass(Z.currentClass);
						J.val(g.innerHTML.trim());
						L.val($(c).attr(O));
						L.attr("relText", $(c).attr(N));
						L.attr("relValue", $(c).attr(O))
					}
					L.data("selectedNode", c);
					if (L.attr("prompt") == null) {
						L.data("scrollY", b * bs)
					} else {
						L.data("scrollY", (b + 1) * bs)
					}
					if (U) {
						ajaxLoad(L, c)
					}
					V = 1
				} else {
					if (S != "") {
						if (S == $(c).attr(O).toString()) {
							if (T == true) {
								$(g).addClass(Z.currentClass);
								J.val(g.innerHTML);
								L.val($(c).attr(O));
								L.attr("relText", $(c).attr(N));
								L.attr("editValue", $(c).attr(N))
							} else {
								$(g).addClass(Z.currentClass);
								J.val(g.innerHTML.trim());
								L.val($(c).attr(O));
								L.attr("relText", $(c).attr(N));
								L.attr("relValue", $(c).attr(O))
							}
							L.data("selectedNode", c);
							if (L.attr("prompt") == null) {
								L.data("scrollY", b * bs)
							} else {
								L.data("scrollY", (b + 1) * bs)
							}
							if (U) {
								ajaxLoad(L, c)
							}
							V = 1
						}
					}
				}
				$(g).mouseover(function(a) {
					jQuery(a.target).addClass(Z.hoverClass)
				});
				$(g).mouseout(function(a) {
					jQuery(a.target).removeClass(Z.hoverClass)
				});
				$(g).mousedown(
						function(a) {
							$("#" + I + "_container li." + Z.currentClass)
									.removeClass(Z.currentClass);
							$(this).addClass(Z.currentClass);
							$("#" + F).attr("relText", $(this).text());
							$("#" + F).attr("relValue",
									$(this).attr("relValue"));
							$("#" + F).data("selectedNode",
									$(this).data("itemData"));
							$("#" + F).val($(this).attr("relValue"));
							J.val($(this).html());
							$("#" + F).prev().find(">div").hide();
							$("#" + F).prev().find(">div").attr("hasfocus", 0);
							$("#" + F).focus();
							if ($("#" + F).attr("prompt") == null) {
								$("#" + F).data("scrollY", b * bs)
							} else {
								$("#" + F).data("scrollY", (b + 1) * bs)
							}
							if ($("#" + F).attr("onchange") != null) {
							}
							try {
								$("#" + F).trigger("change")
							} catch (e) {
							}
							if (U) {
								ajaxLoad($("#" + F), $("#" + F).data(
										"selectedNode"))
							}
						})
			});
			if ($(D).find(M).length == 0) {
				var W = document.createElement("li");
				$(W).text(quiLangage.select.ldError);
				H.append($(W))
			}
			if (R == -1 && S == "") {
				var X = H.find("li").eq(0);
				J.val(X.text());
				X.addClass(Z.currentClass);
				$("#" + F).val(X.attr("relValue"));
				$("#" + F).attr("relValue", X.attr("relValue"));
				$("#" + F).attr("relText", X.text());
				$("#" + F).data("selectedNode", X.data("itemData"));
				$("#" + F).data("scrollY", 0)
			}
			if (V == 0) {
				if ($("#" + F).attr("prompt")) {
					H.find("li").eq(0).addClass(Z.currentClass);
					J.val($("#" + F).attr("prompt"))
				}
			}
			$("#" + F).trigger("ajaxInit")
		} else {
			if (C.attr("childDataType") == null) {
				E = C.attr("childDataPath") + D
			} else {
				if (C.attr("childActionType") == "local") {
					E = C.attr("childDataPath") + D + "."
							+ C.attr("childDataType")
				} else {
					E = C.attr("childDataPath") + D
				}
			}
			$
					.getJSON(
							E,
							function(h) {
								var i = C.attr("childId");
								var j = $("#" + i).prev().find(
										"div[class=loader]");
								j.hide();
								var k = $("#" + i).prev().find("ul");
								var l = $("#" + i).prev().find(">div").attr(
										"id").split("_")[0];
								var m = $("#" + i).prev().find("input:text");
								var n = $("#" + i)[0];
								var o = $("#" + i);
								k.html("");
								n.options.length = 0;
								var p = "list";
								if ($("#" + i).attr("dataRoot")) {
									p = $("#" + i).attr("dataRoot")
								}
								var q = "key";
								if ($("#" + i).attr("labelField")) {
									q = $("#" + i).attr("labelField")
								}
								var r = "value";
								if ($("#" + i).attr("valueField")) {
									r = $("#" + i).attr("valueField")
								}
								if ($("#" + i).attr("prompt")) {
									var s = document.createElement("li");
									var t = $("#" + i).attr("prompt");
									$(s).text(t);
									$(s).attr("relValue", "");
									k.append($(s));
									n.options[n.options.length] = new Option(t,
											"");
									$(s).mouseover(function(a) {
										jQuery(a.target).addClass(Z.hoverClass)
									});
									$(s).mouseout(
											function(a) {
												jQuery(a.target).removeClass(
														Z.hoverClass)
											});
									$(s)
											.mousedown(
													function(a) {
														$(
																"#"
																		+ l
																		+ "_container li."
																		+ Z.currentClass)
																.removeClass(
																		Z.currentClass);
														$(this).addClass(
																Z.currentClass);
														$("#" + i).attr(
																"relText",
																$(this).text());
														$("#" + i)
																.attr(
																		"relValue",
																		$(this)
																				.attr(
																						"relValue"));
														$("#" + i)
																.val(
																		$(this)
																				.attr(
																						"relValue"));
														m.val($(this).html());
														$("#" + i).prev().find(
																">div").hide();
														$("#" + i).prev().find(
																">div").attr(
																"hasfocus", 0);
														$("#" + i).focus();
														$("#" + i).data(
																"scrollY", 0);
														if ($("#" + i).attr(
																"onchange") != null) {
														}
														try {
															$("#" + i).trigger(
																	"change")
														} catch (e) {
														}
													})
								}
								var u = -1;
								var v = "";
								var w;
								var x;
								if (o.attr("childId") != null) {
									x = true
								}
								if (o.attr("selectedIdx")) {
									u = Number(o.attr("selectedIdx"))
								}
								if (o.attr("selectedValue")) {
									v = o.attr("selectedValue")
								}
								if (o.attr("editable") != null) {
									if (o.attr("editable") == "true") {
										w = true
									} else {
										w = false
									}
								}
								var y = 0;
								var z;
								if (h[p]) {
									z = h[p]
								} else {
									z = h
								}
								$
										.each(
												z,
												function(b, c) {
													var d = c[q];
													var f = c[r];
													var g = document
															.createElement("li");
													$(g).text(d);
													$(g).attr("relValue", f);
													$(g).data("itemData", c);
													k.append($(g));
													n.options[n.options.length] = new Option(
															d, f);
													if (u == b) {
														if (w == true) {
															$(g)
																	.addClass(
																			Z.currentClass);
															m.val(g.innerHTML);
															o.val(c[r]);
															o.attr("relText",
																	c[q]);
															o.attr("editValue",
																	c[q])
														} else {
															$(g)
																	.addClass(
																			Z.currentClass);
															m.val(g.innerHTML
																	.trim());
															o.val(c[r]);
															o.attr("relText",
																	c[q]);
															o.attr("relValue",
																	c[r])
														}
														o.data("selectedNode",
																c);
														if (o.attr("prompt") == null) {
															o.data("scrollY", b
																	* bs)
														} else {
															o
																	.data(
																			"scrollY",
																			(b + 1)
																					* bs)
														}
														if (U) {
															ajaxLoad(o, c[r])
														}
														y = 1
													} else {
														if (v != "") {
															if (v == c[r]
																	.toString()) {
																if (w == true) {
																	$(g)
																			.addClass(
																					Z.currentClass);
																	m
																			.val(g.innerHTML);
																	o.val(c[r]);
																	o
																			.attr(
																					"relText",
																					c[q]);
																	o
																			.attr(
																					"editValue",
																					c[q])
																} else {
																	$(g)
																			.addClass(
																					Z.currentClass);
																	m
																			.val(g.innerHTML
																					.trim());
																	o.val(c[r]);
																	o
																			.attr(
																					"relText",
																					c[q]);
																	o
																			.attr(
																					"relValue",
																					c[r])
																}
																o
																		.data(
																				"selectedNode",
																				c);
																if (o
																		.attr("prompt") == null) {
																	o
																			.data(
																					"scrollY",
																					b
																							* bs)
																} else {
																	o
																			.data(
																					"scrollY",
																					(b + 1)
																							* bs)
																}
																if (x) {
																	ajaxLoad(
																			o,
																			c[r])
																}
																y = 1
															}
														}
													}
													$(g)
															.mouseover(
																	function(a) {
																		jQuery(
																				a.target)
																				.addClass(
																						Z.hoverClass)
																	});
													$(g)
															.mouseout(
																	function(a) {
																		jQuery(
																				a.target)
																				.removeClass(
																						Z.hoverClass)
																	});
													$(g)
															.mousedown(
																	function(a) {
																		$(
																				"#"
																						+ l
																						+ "_container li."
																						+ Z.currentClass)
																				.removeClass(
																						Z.currentClass);
																		$(this)
																				.addClass(
																						Z.currentClass);
																		$(
																				"#"
																						+ i)
																				.attr(
																						"relText",
																						$(
																								this)
																								.text());
																		$(
																				"#"
																						+ i)
																				.attr(
																						"relValue",
																						$(
																								this)
																								.attr(
																										"relValue"));
																		$(
																				"#"
																						+ i)
																				.data(
																						"selectedNode",
																						$(
																								this)
																								.data(
																										"itemData"));
																		$(
																				"#"
																						+ i)
																				.val(
																						$(
																								this)
																								.attr(
																										"relValue"));
																		m
																				.val($(
																						this)
																						.html());
																		$(
																				"#"
																						+ i)
																				.prev()
																				.find(
																						">div")
																				.hide();
																		$(
																				"#"
																						+ i)
																				.prev()
																				.find(
																						">div")
																				.attr(
																						"hasfocus",
																						0);
																		$(
																				"#"
																						+ i)
																				.focus();
																		if ($(
																				"#"
																						+ i)
																				.attr(
																						"onchange") != null) {
																		}
																		try {
																			$(
																					"#"
																							+ i)
																					.trigger(
																							"change")
																		} catch (e) {
																		}
																		if (o
																				.attr("prompt") == null) {
																			$(
																					"#"
																							+ i)
																					.data(
																							"scrollY",
																							b
																									* bs)
																		} else {
																			$(
																					"#"
																							+ i)
																					.data(
																							"scrollY",
																							(b + 1)
																									* bs)
																		}
																		if (x) {
																			ajaxLoad(
																					$("#"
																							+ i),
																					$(
																							"#"
																									+ i)
																							.val())
																		}
																	})
												});
								if (h.length == 0) {
									var A = document.createElement("li");
									$(A).text(quiLangage.select.noItemMessage);
									k.append($(A))
								}
								if (u == -1 && v == "") {
									var B = k.find("li").eq(0);
									m.val(B.text());
									B.addClass(Z.currentClass);
									$("#" + i).val(B.attr("relValue"));
									$("#" + i).attr("relValue",
											B.attr("relValue"));
									$("#" + i).attr("relText", B.text());
									$("#" + i).data("selectedNode",
											B.data("itemData"));
									$("#" + i).data("scrollY", 0)
								}
								$("#" + i).trigger("ajaxInit");
								if (y == 0) {
									if ($("#" + i).attr("prompt")) {
										k.find("li").eq(0).addClass(
												Z.currentClass);
										m.val($("#" + i).attr("prompt"))
									}
								}
							})
		}
	}
};
var tipDirection = "down";
function enableTooltips(e) {
	var b, a, c, d;
	if (!document.getElementById || !document.getElementsByTagName) {
		return
	}
	AddCss();
	d = document.createElement("span");
	d.id = "btc";
	d.setAttribute("id", "btc");
	d.style.position = "absolute";
	d.style.zIndex = 9999;
	$("body").append($(d))
}
function _getStrLength(c) {
	var b;
	var a;
	for (b = 0, a = 0; b < c.length; b++) {
		if (c.charCodeAt(b) < 128) {
			a++
		} else {
			a = a + 2
		}
	}
	return a
}
function addTooltip(f) {
	var g, d, a, e, c;
	d = f.getAttribute("title");
	if (d == " ") {
		f.removeAttribute("title");
		f.onmouseover = null;
		f.onmouseout = null;
		f.onmousemove = null;
		return
	}
	if (d != null && d.length != 0) {
		f.removeAttribute("title");
		if (_getStrLength(d) > 37 || _getStrLength(d) == 37) {
			g = CreateEl("span", "tooltip")
		} else {
			if (_getStrLength(d) > 10 && _getStrLength(d) < 37) {
				g = CreateEl("span", "tooltip_mid")
			} else {
				g = CreateEl("span", "tooltip_min")
			}
		}
		e = CreateEl("span", "top");
		$(e).html(d);
		g.appendChild(e);
		a = CreateEl("b", "bottom");
		g.appendChild(a);
		setOpacity(g);
		f.tooltip = g;
		f.onmouseover = showTooltip;
		f.onmouseout = hideTooltip;
		f.onmousemove = Locate2
	}
}
function hideTip(a) {
	var b = document.getElementById("btc");
	if (b.childNodes.length > 0) {
		b.removeChild(b.firstChild)
	}
}
function showTooltip(a) {
	document.getElementById("btc").appendChild(this.tooltip);
	Locate(a)
}
function hideTooltip() {
	var a = document.getElementById("btc");
	if (a.childNodes.length > 0) {
		a.removeChild(a.firstChild)
	}
}
function setOpacity(a) {
	a.style.filter = "alpha(opacity:95)";
	a.style.KHTMLOpacity = "0.95";
	a.style.MozOpacity = "0.95";
	a.style.opacity = "0.95"
}
function CreateEl(b, d) {
	var a = document.createElement(b);
	a.className = d;
	a.style.display = "block";
	return (a)
}
function AddCss() {
}
function Locate(g) {
	var a = 0, i = 0;
	if (g == null) {
		g = window.event
	}
	if (g.pageX || g.pageY) {
		a = g.pageX;
		i = g.pageY
	} else {
		if (g.clientX || g.clientY) {
			if (document.documentElement.scrollTop) {
				a = g.clientX + document.documentElement.scrollLeft;
				i = g.clientY + document.documentElement.scrollTop
			} else {
				a = g.clientX + document.body.scrollLeft;
				i = g.clientY + document.body.scrollTop
			}
		}
	}
	var h = window.document.documentElement.clientWidth;
	var c = window.document.documentElement.clientHeight;
	var b = $("#btc").width();
	var f = $("#btc").height();
	var d = $("#btc >span")[0].className;
	if (h - b < a - 20) {
		document.getElementById("btc").style.left = (h - b) + "px";
		if (d == "tooltip") {
			$("#btc >span")[0].className = "tooltip_s"
		} else {
			if (d == "tooltip_min") {
				$("#btc >span")[0].className = "tooltip_min_s"
			} else {
				if (d == "tooltip_mid") {
					$("#btc >span")[0].className = "tooltip_mid_s"
				}
			}
		}
	} else {
		document.getElementById("btc").style.left = (a - 20) + "px"
	}
	if ($(window).scrollTop() + c - f < i) {
		document.getElementById("btc").style.top = (i - f - 10) + "px";
		if (d == "tooltip") {
			$("#btc >span")[0].className = "tooltip_r"
		} else {
			if (d == "tooltip_min") {
				$("#btc >span")[0].className = "tooltip_min_r"
			} else {
				if (d == "tooltip_mid") {
					$("#btc >span")[0].className = "tooltip_mid_r"
				}
			}
		}
		tipDirection = "up"
	} else {
		document.getElementById("btc").style.top = (i + 10) + "px";
		if (d == "tooltip_r") {
			$("#btc >span")[0].className = "tooltip"
		} else {
			if (d == "tooltip_min_r") {
				$("#btc >span")[0].className = "tooltip_min"
			} else {
				if (d == "tooltip_mid_r") {
					$("#btc >span")[0].className = "tooltip_mid"
				}
			}
		}
		tipDirection = "down"
	}
}
function Locate2(f) {
	var a = 0, h = 0;
	if (f == null) {
		f = window.event
	}
	if (f.pageX || f.pageY) {
		a = f.pageX;
		h = f.pageY
	} else {
		if (f.clientX || f.clientY) {
			if (document.documentElement.scrollTop) {
				a = f.clientX + document.documentElement.scrollLeft;
				h = f.clientY + document.documentElement.scrollTop
			} else {
				a = f.clientX + document.body.scrollLeft;
				h = f.clientY + document.body.scrollTop
			}
		}
	}
	var g = window.document.documentElement.clientWidth;
	var c = window.document.documentElement.clientHeight;
	var b = $("#btc").width();
	var d = $("#btc").height();
	if (g - b < a - 20) {
		document.getElementById("btc").style.left = (g - b) + "px"
	} else {
		document.getElementById("btc").style.left = (a - 20) + "px"
	}
	if (tipDirection == "up") {
		document.getElementById("btc").style.top = (h - d - 10) + "px"
	} else {
		document.getElementById("btc").style.top = (h + 10) + "px"
	}
}
(function(c) {
	var h, i;
	var d = 0;
	var a = 32;
	var e;
	c.fn.TextAreaResizer = function() {
		return this
				.each(function() {
					h = c(this).addClass("processed"), i = null;
					c(this)
							.wrap(
									'<div class="resizable-textarea"><span></span></div>')
							.parent()
							.append(
									c('<div class="grippie"></div>').bind(
											"mousedown", {
												el : this
											}, b))
							.wrap(
									'<table cellspacing="0" cellpadding="0" style="border-style:none;"><tr><td class="ali01" style="border-style:none;padding:0;margin:0;"></td></tr></table>');
					var m = c("div.grippie", c(this).parent())
				})
	};
	function b(m) {
		h = c(m.data.el);
		h.blur();
		d = l(m).y;
		i = h.height() - d;
		h.css("opacity", 0.25);
		c(document).mousemove(g).mouseup(f);
		return false
	}
	function g(o) {
		var m = l(o).y;
		var n = i + m;
		if (d >= (m)) {
			n -= 5
		}
		d = m;
		n = Math.max(a, n);
		h.height(n + "px");
		if (n < a) {
			f(o)
		}
		return false
	}
	function f(m) {
		c(document).unbind("mousemove", g).unbind("mouseup", f);
		h.css("opacity", 1);
		h.focus();
		h = null;
		i = null;
		d = 0
	}
	function l(m) {
		return {
			x : m.clientX + document.documentElement.scrollLeft,
			y : m.clientY + document.documentElement.scrollTop
		}
	}
})(jQuery);
(function(a) {
	a.fn.watermark = function(b, c) {
		return this.each(function() {
			var e = a(this), d;
			e.focus(function() {
				d && !(d = 0) && e.removeClass(b).data("w", 0).val("")
			}).blur(function() {
				!e.val() && (d = 1) && e.addClass(b).data("w", 1).val(c)
			}).closest("form").submit(function() {
				d && e.val("")
			});
			e.blur()
		})
	};
	a.fn.removeWatermark = function() {
		return this.each(function() {
			a(this).data("w") && a(this).val("")
		})
	}
})(jQuery);
if (jQuery) {
	(function(a) {
		a.cursorMessageData = {};
		a(window).ready(function(b) {
			if (a("#cursorMessageDiv").length == 0) {
				a("body").append('<div id="cursorMessageDiv">&nbsp;</div>');
				a("#cursorMessageDiv").hide()
			}
			a("body").mousemove(function(c) {
				a.cursorMessageData.mouseX = c.pageX;
				a.cursorMessageData.mouseY = c.pageY;
				currentMouseX = c.pageX;
				currentMouseY = c.pageY;
				if (a.cursorMessageData.options != undefined) {
					a._showCursorMessage()
				}
			})
		});
		a
				.extend({
					cursorMessage : function(c, b) {
						if (b == undefined) {
							b = {}
						}
						if (b.offsetX == undefined) {
							b.offsetX = 5
						}
						if (b.offsetY == undefined) {
							b.offsetY = 5
						}
						if (b.hideTimeout == undefined) {
							b.hideTimeout = 3000
						}
						a("#cursorMessageDiv").html(c).show();
						if (jQuery.cursorMessageData.hideTimeoutId != undefined) {
							clearTimeout(jQuery.cursorMessageData.hideTimeoutId)
						}
						if (b.hideTimeout > 0) {
							jQuery.cursorMessageData.hideTimeoutId = setTimeout(
									a.hideCursorMessage, b.hideTimeout)
						}
						jQuery.cursorMessageData.options = b;
						a._showCursorMessage()
					},
					hideCursorMessage : function() {
						a("#cursorMessageDiv").hide()
					},
					_showCursorMessage : function() {
						a("#cursorMessageDiv")
								.css(
										{
											top : (a.cursorMessageData.mouseY + a.cursorMessageData.options.offsetY)
													+ "px",
											left : (a.cursorMessageData.mouseX + a.cursorMessageData.options.offsetX)
										})
					}
				})
	})(jQuery)
}
jQuery.fn.caps = function(a) {
	return this.keypress(function(f) {
		var b = f.which ? f.which : (f.keyCode ? f.keyCode : -1);
		var d = f.shiftKey ? f.shiftKey : (f.modifiers ? !!(f.modifiers & 4)
				: false);
		var g = ((b >= 65 && b <= 90) && !d) || ((b >= 97 && b <= 122) && d);
		a.call(this, g)
	})
};
function iframeHeight(b) {
	var a = document.getElementById(b);
	a.style.height = a.contentWindow.document.body.scrollHeight + "px"
}
(function(d) {
	d.fn.clearableTextField = function() {
		if (d(this).length > 0) {
			d(this).bind("keyup change paste cut", e);
			for ( var f = 0; f < d(this).length; f++) {
				c(d(d(this)[f]))
			}
		}
	};
	function e() {
		c(d(this))
	}
	function c(f) {
		if (f.val().length > 0) {
			b(f)
		} else {
			a(f)
		}
	}
	function b(i) {
		if (!i.next().hasClass("text_clear_button")) {
			i.after("<div class='text_clear_button'></div>");
			var f = i.next();
			var g = f.outerHeight(), m = f.outerHeight();
			i.css("padding-right", parseInt(i.css("padding-right")) + g + 1);
			i.width(i.width() - g - 1);
			var o = i.position();
			var l = {};
			l.left = o.left + i.outerWidth(false) - (g + 2);
			var n = Math.round((i.outerHeight(true) - m) / 2);
			l.top = o.top + d("#scrollContent").scrollTop() + n;
			f.css(l);
			f.click(function() {
				i.val("");
				c(i)
			})
		}
	}
	function a(h) {
		var f = h.next();
		if (f.hasClass("text_clear_button")) {
			f.remove();
			var g = f.width();
			h.css("padding-right", parseInt(h.css("padding-right")) - g - 1);
			h.width(h.width() + g + 1)
		}
	}
})(jQuery);
(function(a) {
	a.fn.maxlength = function(b) {
		var c = jQuery.extend({
			events : [],
			maxCharacters : 10,
			status : true,
			statusClass : "maxNum",
			statusText : uncompile(quiLanguage.maxlength.statusText),
			notificationClass : "notification",
			showAlert : false,
			alertText : uncompile(quiLanguage.maxlength.alertText),
			slider : true
		}, b);
		a.merge(c.events, [ "keyup" ]);
		return this.each(function() {
			var g = a(this);
			var l = a(this).val().length;
			function d() {
				var m = c.maxCharacters - l;
				if (m < 0) {
					m = 0
				}
				g.next("div").html(c.statusText + " :" + m)
			}
			function e() {
				var m = true;
				if (l >= c.maxCharacters) {
					m = false;
					g.addClass(c.notificationClass);
					g.val(g.val().substr(0, c.maxCharacters));
					i()
				} else {
					if (g.hasClass(c.notificationClass)) {
						g.removeClass(c.notificationClass)
					}
				}
				if (c.status) {
					d()
				}
			}
			function i() {
				if (c.showAlert) {
					alert(c.alertText)
				}
			}
			function f() {
				var m = false;
				if (g.is("textarea")) {
					m = true
				} else {
					if (g.filter("input[type=text]")) {
						m = true
					} else {
						if (g.filter("input[type=password]")) {
							m = true
						}
					}
				}
				return m
			}
			if (!f()) {
				return false
			}
			a.each(c.events, function(m, o) {
				g.bind(o, function(n) {
					l = g.val().length;
					e()
				})
			});
			if (c.status) {
				g.after(a("<div/>").addClass(c.statusClass).html("-"));
				d()
			}
			if (!c.status) {
				var h = g.next("div." + c.statusClass);
				if (h) {
					h.remove()
				}
			}
			if (c.slider) {
				g.next().hide();
				g.focus(function() {
					g.next().slideDown("fast")
				});
				g.blur(function() {
					g.next().slideUp("fast")
				})
			}
		})
	}
})(jQuery);
var colsDefault = 0;
var rowsDefault = 5;
function setDefaultValues(a) {
	colsDefault = a.cols;
	rowsDefault = $(a).attr("rows")
}
function bindEvents(a) {
	a.onkeyup = function() {
		grow(a)
	}
}
function grow(d) {
	var c = 0;
	var a = d.value.split("\n");
	for ( var b = a.length - 1; b >= 0; --b) {
		c += Math.floor((a[b].length / colsDefault) + 1)
	}
	if (c >= rowsDefault) {
		d.rows = c + 1
	} else {
		d.rows = rowsDefault
	}
}
jQuery.fn.autoGrow = function() {
	return this.each(function() {
		setDefaultValues(this);
		bindEvents(this)
	})
};
(function(b) {
	var a = new function() {
		this.countRegexp = function(d, e) {
			var c = d.match(e);
			return c ? c.length : 0
		};
		this.getStrength = function(i, e) {
			var c = i.length;
			if (c < e) {
				return 0
			}
			var g = this.countRegexp(i, /\d/g), l = this.countRegexp(i,
					/[a-z]/g), f = this.countRegexp(i, /[A-Z]/g), d = c - g - l
					- f;
			if (g == c || l == c || f == c || d == c) {
				return 1
			}
			var h = 0;
			if (g) {
				h += 2
			}
			if (l) {
				h += f ? 4 : 3
			}
			if (f) {
				h += l ? 4 : 3
			}
			if (d) {
				h += 5
			}
			if (c > 10) {
				h += 1
			}
			return h
		};
		this.getStrengthLevel = function(e, c) {
			var d = this.getStrength(e, c);
			switch (true) {
			case (d <= 0):
				return 1;
				break;
			case (d > 0 && d <= 4):
				return 2;
				break;
			case (d > 4 && d <= 8):
				return 3;
				break;
			case (d > 8 && d <= 12):
				return 4;
				break;
			case (d > 12):
				return 5;
				break
			}
			return 1
		}
	};
	b.fn.password_strength = function(c) {
		var d = b.extend({
			container : null,
			minLength : 6,
			texts : {
				1 : uncompile(quiLanguage.passStrength.text1),
				2 : uncompile(quiLanguage.passStrength.text2),
				3 : uncompile(quiLanguage.passStrength.text3),
				4 : uncompile(quiLanguage.passStrength.text4),
				5 : uncompile(quiLanguage.passStrength.text5)
			}
		}, c);
		return this.each(function() {
			if (d.container) {
				var e = b(d.container)
			} else {
				var e = b("<span/>").attr("class", "password_strength");
				b(this).after(e)
			}
			b(this).keyup(
					function() {
						var g = b(this).val();
						if (g.length > 0) {
							var h = a.getStrengthLevel(g, d.minLength);
							var f = "password_strength_" + h;
							if (!e.hasClass(f) && h in d.texts) {
								e.text(d.texts[h]).attr("class",
										"password_strength " + f)
							}
						} else {
							e.text("").attr("class", "password_strength")
						}
					})
		})
	};
	b.fn.password_strength2 = function(c) {
		var d = a.getStrengthLevel(c, 6);
		return d
	}
})(jQuery);
jQuery.jCookie = function(i, b, n, l) {
	if (!navigator.cookieEnabled) {
		return false
	}
	var l = l || {};
	if (typeof (arguments[0]) !== "string" && arguments.length === 1) {
		l = arguments[0];
		i = l.name;
		b = l.value;
		n = l.expires
	}
	i = encodeURI(i);
	if (b && (typeof (b) !== "number" && typeof (b) !== "string" && b !== null)) {
		return false
	}
	var e = l.path ? "; path=" + l.path : "";
	var f = l.domain ? "; domain=" + l.domain : "";
	var d = l.secure ? "; secure" : "";
	var g = "";
	if (b || (b === null && arguments.length == 2)) {
		n = (n === null || (b === null && arguments.length == 2)) ? -1 : n;
		if (typeof (n) === "number" && n != "session" && n !== undefined) {
			var m = new Date();
			m.setTime(m.getTime() + (n * 24 * 60 * 60 * 1000));
			g = [ "; expires=", m.toGMTString() ].join("")
		}
		document.cookie = [ i, "=", encodeURI(b), g, f, e, d ].join("");
		return true
	}
	if (!b && typeof (arguments[0]) === "string" && arguments.length == 1
			&& document.cookie && document.cookie.length) {
		var a = document.cookie.split(";");
		var h = a.length;
		while (h--) {
			var c = a[h].split("=");
			if (jQuery.trim(c[0]) === i) {
				return decodeURI(c[1])
			}
		}
	}
	return false
};
function showProgressBar(e, b) {
	var c = uncompile(quiLanguage.progressBar.title);
	if (e) {
		c = e
	}
	var a = "simple";
	if (b) {
		if (b == "normal") {
			a = b
		}
	}
	if (a == "simple") {
		top.progressFlag = 2;
		top.showSimpleProgress(c, 0, true, "#ffffff")
	} else {
		top.progressFlag = 1;
		var d = new top.Dialog();
		d.Width = 360;
		d.Height = 70;
		d.Title = c;
		d.InvokeElementId = "progress";
		d.show()
	}
}
function closeProgress() {
	try {
		if (top.progressFlag == 1) {
			top.Dialog.close();
			top.progressFlag = 0
		} else {
			if (top.progressFlag == 2) {
				top.hideSimpleProgress();
				top.progressFlag = 0
			}
		}
	} catch (a) {
	}
}
function _initComplete() {
	try {
		initComplete()
	} catch (a) {
	}
}
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "")
};
(function(a) {
	a.fn.mask = function(d, c, e, b) {
		a(this).each(function() {
			if (e == null) {
				e = true
			}
			var f = "#cccccc";
			if (b) {
				f = b
			}
			if (c !== undefined && c > 0 && c != null) {
				var g = a(this);
				g.data("_mask_timeout", setTimeout(function() {
					a.maskElement(g, d, e, f)
				}, c))
			} else {
				a.maskElement(a(this), d, e, f)
			}
		})
	};
	a.fn.unmask = function() {
		a(this).each(function() {
			a.unmaskElement(a(this))
		})
	};
	a.fn.isMasked = function() {
		return this.hasClass("masked")
	};
	a.maskElement = function(e, d, g, c) {
		if (e.data("_mask_timeout") !== undefined) {
			clearTimeout(e.data("_mask_timeout"));
			e.removeData("_mask_timeout")
		}
		if (e.isMasked()) {
			a.unmaskElement(e)
		}
		if (e.css("position") == "static") {
			e.addClass("masked-relative")
		}
		e.addClass("masked");
		var f = a('<div class="loadmask"></div>');
		f.css({
			backgroundColor : c
		});
		if (navigator.userAgent.toLowerCase().indexOf("msie") > -1) {
			f.height(e.height() + parseInt(e.css("padding-top"))
					+ parseInt(e.css("padding-bottom")));
			f.width(e.width() + parseInt(e.css("padding-left"))
					+ parseInt(e.css("padding-right")))
		}
		if (navigator.userAgent.toLowerCase().indexOf("msie 6") > -1) {
			e.find("select").addClass("masked-hidden")
		}
		e.append(f);
		f.show();
		if (d !== undefined && d != null) {
			var b = a('<div class="loadmask-msg" style="display:none;"></div>');
			if (g) {
				b.append('<div class="mask_lading">' + d + "</div>")
			} else {
				b.append('<div  class="normal">' + d + "</div>")
			}
			e.append(b);
			b.css("top", Math.round(e.height()
					/ 2
					- (b.height() - parseInt(b.css("padding-top")) - parseInt(b
							.css("padding-bottom"))) / 2)
					+ "px");
			b.css("left", Math.round(e.width()
					/ 2
					- (b.width() - parseInt(b.css("padding-left")) - parseInt(b
							.css("padding-right"))) / 2)
					+ "px");
			b.show()
		}
	};
	a.unmaskElement = function(b) {
		if (b.data("_mask_timeout") !== undefined) {
			clearTimeout(b.data("_mask_timeout"));
			b.removeData("_mask_timeout")
		}
		b.find(".loadmask-msg,.loadmask").remove();
		b.removeClass("masked");
		b.removeClass("masked-relative");
		b.find("select").removeClass("masked-hidden")
	}
})(jQuery);
var JSON;
if (!JSON) {
	JSON = {}
}
(function() {
	function f(n) {
		return n < 10 ? "0" + n : n
	}
	if (typeof Date.prototype.toJSON !== "function") {
		Date.prototype.toJSON = function(a) {
			return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-"
					+ f(this.getUTCMonth() + 1) + "-" + f(this.getUTCDate())
					+ "T" + f(this.getUTCHours()) + ":"
					+ f(this.getUTCMinutes()) + ":" + f(this.getUTCSeconds())
					+ "Z" : null
		};
		String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function(
				a) {
			return this.valueOf()
		}
	}
	var e = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, gap, indent, meta = {
		"\b" : "\\b",
		"\t" : "\\t",
		"\n" : "\\n",
		"\f" : "\\f",
		"\r" : "\\r",
		'"' : '\\"',
		"\\" : "\\\\"
	}, rep;
	function quote(b) {
		escapable.lastIndex = 0;
		return escapable.test(b) ? '"'
				+ b.replace(escapable, function(a) {
					var c = meta[a];
					return typeof c === "string" ? c : "\\u"
							+ ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
				}) + '"' : '"' + b + '"'
	}
	function str(a, b) {
		var i, k, v, length, mind = gap, partial, value = b[a];
		if (value && typeof value === "object"
				&& typeof value.toJSON === "function") {
			value = value.toJSON(a)
		}
		if (typeof rep === "function") {
			value = rep.call(b, a, value)
		}
		switch (typeof value) {
		case "string":
			return quote(value);
		case "number":
			return isFinite(value) ? String(value) : "null";
		case "boolean":
		case "null":
			return String(value);
		case "object":
			if (!value) {
				return "null"
			}
			gap += indent;
			partial = [];
			if (Object.prototype.toString.apply(value) === "[object Array]") {
				length = value.length;
				for (i = 0; i < length; i += 1) {
					partial[i] = str(i, value) || "null"
				}
				v = partial.length === 0 ? "[]" : gap ? "[\n" + gap
						+ partial.join(",\n" + gap) + "\n" + mind + "]" : "["
						+ partial.join(",") + "]";
				gap = mind;
				return v
			}
			if (rep && typeof rep === "object") {
				length = rep.length;
				for (i = 0; i < length; i += 1) {
					if (typeof rep[i] === "string") {
						k = rep[i];
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ": " : ":") + v)
						}
					}
				}
			} else {
				for (k in value) {
					if (Object.prototype.hasOwnProperty.call(value, k)) {
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ": " : ":") + v)
						}
					}
				}
			}
			v = partial.length === 0 ? "{}" : gap ? "{\n" + gap
					+ partial.join(",\n" + gap) + "\n" + mind + "}" : "{"
					+ partial.join(",") + "}";
			gap = mind;
			return v
		}
	}
	if (typeof JSON.stringify !== "function") {
		JSON.stringify = function(a, b, c) {
			var i;
			gap = "";
			indent = "";
			if (typeof c === "number") {
				for (i = 0; i < c; i += 1) {
					indent += " "
				}
			} else {
				if (typeof c === "string") {
					indent = c
				}
			}
			rep = b;
			if (b && typeof b !== "function"
					&& (typeof b !== "object" || typeof b.length !== "number")) {
				throw new Error("JSON.stringify")
			}
			return str("", {
				"" : a
			})
		}
	}
	if (typeof JSON.parse !== "function") {
		JSON.parse = function(c, d) {
			function walk(a, b) {
				if (value && typeof value === "object") {
					for (k in value) {
						if (Object.prototype.hasOwnProperty.call(value, k)) {
							v = walk(value, k);
							if (v !== undefined) {
								value[k] = v
							} else {
								delete value[k]
							}
						}
					}
				}
				return d.call(a, b, value)
			}
			c = String(c);
			e.lastIndex = 0;
			if (e.test(c)) {
				c = c.replace(e, function(a) {
					return "\\u"
							+ ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
				})
			}
			if (/^[\],:{}\s]*$/
					.test(c
							.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@")
							.replace(
									/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
									"]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) {
				j = eval("(" + c + ")");
				return typeof d === "function" ? walk({
					"" : j
				}, "") : j
			}
			throw new SyntaxError("JSON.parse")
		}
	}
}());
function validateInput(c, b) {
	var a = new RegExp(b);
	return a.test(c)
}
function createPosition(c, a) {
	var b;
	if (a == "normal") {
		b = $('<div class="position"><div class="center"><div class="left"><div class="right"><span></span></div></div></div></div>')
	} else {
		if (a == "simple") {
			b = $('<div class="positionSimple"><span></span></div>')
		}
	}
	b.find("span").append(c);
	$("body").prepend(b)
}
$.fn.createBoxItem = function(d, f) {
	var c = $(this);
	var g = c.parents(".box4");
	c.empty();
	var e;
	var a = "double";
	if (d.type) {
		if (d.type == "single") {
			a = "single"
		}
	}
	if (a == "single") {
		g.attr("noTitle", "false");
		g.attr("panelTitle", d.title);
		g.box4Build();
		var b = $("<ul></ul>");
		c.append(b);
		$.each(d.list, function(h, m) {
			var l = $('<li><a><span class="text_slice"></span></a></li>');
			if (m.link != "") {
				var i = l.find("a");
				i.attr("href", m.link);
				i.attr("target", f)
			}
			l.find(".text_slice").text(m.name);
			b.append(l)
		})
	} else {
		g.attr("noTitle", "true");
		g.box4Build();
		$.each(d.list, function(h, i) {
			if (h == 0) {
				e = i.link
			}
			if (i.type == "parent") {
				var m = $('<div class="subtitle"></div>');
				var l;
				if (i.link != "") {
					l = $('<a><div class="subtitle_con"></div></a>');
					l.attr("href", i.link);
					l.attr("target", f)
				} else {
					l = $('<div class="subtitle_con"></div>')
				}
				m.append(l);
				m.find(".subtitle_con").text(i.name);
				m.attr("id", "boxitem_" + i.id);
				c.append(m);
				c.append("<ul></ul>")
			}
		});
		$.each(d.list, function(h, n) {
			if (n.type == "child") {
				var l = $('<li><a><span class="text_slice"></span></a></li>');
				if (n.link != "") {
					var m = l.find("a");
					m.attr("href", n.link);
					m.attr("target", f)
				}
				l.find(".text_slice").text(n.name);
				var i = n.pid;
				$("#boxitem_" + i).next("ul").append(l)
			}
		});
		c.find(".subtitle a").each(function() {
			$(this).unbind("click");
			$(this).click(function() {
				c.find("li a").removeClass("current")
			})
		})
	}
	c
			.find("li a")
			.each(
					function(h) {
						$(this).unbind("click");
						$(this)
								.click(
										function() {
											c.find("li a").removeClass(
													"current");
											$(this).addClass("current");
											if ($(this).attr("href") != null) {
												showProgressBar();
												if (a == "single") {
													if (d.title) {
														top.positionContent = "【"
																+ uncompile(quiLanguage.position.title)
																+ d.title
																+ ">>"
																+ $(this)
																		.text()
																+ "】"
													} else {
														top.positionContent = "【"
																+ uncompile(quiLanguage.position.title)
																+ $(this)
																		.text()
																+ "】"
													}
												} else {
													if (d.title) {
														top.positionContent = "【"
																+ uncompile(quiLanguage.position.title)
																+ d.title
																+ ">>"
																+ $(this)
																		.parents(
																				"ul")
																		.prev(
																				".subtitle")
																		.eq(0)
																		.text()
																+ ">>"
																+ $(this)
																		.text()
																+ "】"
													} else {
														top.positionContent = "【"
																+ uncompile(quiLanguage.position.title)
																+ $(this)
																		.parents(
																				"ul")
																		.prev(
																				".subtitle")
																		.eq(0)
																		.text()
																+ ">>"
																+ $(this)
																		.text()
																+ "】"
													}
												}
												top.positionType = "simple"
											}
										})
					});
	$("#" + f).attr("src", e)
};
function showCodePage(c, a) {
	var b = new top.Dialog();
	b.Title = a;
	b.Modal = false;
	b.ID = "code1";
	b.URL = c;
	b.ShowMaxButton = true;
	b.ShowMinButton = true;
	b.Width = 900;
	b.Height = 540;
	b.MaxEvent = function() {
		b.innerFrame.contentWindow.changeCodeHeight($(
				top.document.getElementById("_DialogBGDiv")).height() - 55)
	};
	b.DecreaseEvent = function() {
		b.innerFrame.contentWindow.changeCodeHeight(530)
	};
	b.show()
}
(function($) {
	Function.prototype.quiExtend = function(a, b) {
		if (typeof a != "function") {
			return this
		}
		this.base = a.prototype;
		this.base.constructor = a;
		var f = function() {
		};
		f.prototype = a.prototype;
		this.prototype = new f();
		this.prototype.constructor = this;
		if (b) {
			$.extend(this.prototype, b)
		}
	};
	Function.prototype.quiDefer = function(o, a, b) {
		var c = this;
		return setTimeout(function() {
			c.apply(o, b || [])
		}, a)
	};
	window.qui = $.quiui = {
		version : "QUI3.1",
		managerCount : 0,
		managers : {},
		managerIdPrev : "quiui",
		error : {
			managerIsExist : uncompile(quiLanguage.jsError.idInfo)
		},
		getId : function(a) {
			a = a || this.managerIdPrev;
			var b = a + (1000 + this.managerCount);
			this.managerCount++;
			return b
		},
		add : function(a) {
			if (arguments.length == 2) {
				var m = arguments[1];
				m.id = m.id || m.options.id || arguments[0].id;
				this.addManager(m);
				return
			}
			if (!a.id) {
				a.id = this.getId(a.__idPrev())
			}
			if (this.managers[a.id]) {
				throw new Error(this.error.managerIsExist)
			}
			this.managers[a.id] = a
		},
		remove : function(a) {
			if (typeof a == "string" || typeof a == "number") {
				delete $.quiui.managers[a]
			} else {
				if (typeof a == "object" && a instanceof $.quiui.core.Component) {
					delete $.quiui.managers[a.id]
				}
			}
		},
		get : function(a, b) {
			b = b || "quiuiid";
			if (typeof a == "string" || typeof a == "number") {
				return $.quiui.managers[a]
			} else {
				if (typeof a == "object" && a.length) {
					if (!a[0][b] && !$(a[0]).attr(b)) {
						return null
					}
					return $.quiui.managers[a[0][b] || $(a[0]).attr(b)]
				}
			}
			return null
		},
		find : function(a) {
			var b = [];
			for ( var c in this.managers) {
				var d = this.managers[c];
				if (a instanceof Function) {
					if (d instanceof a) {
						b.push(d)
					}
				} else {
					if (a instanceof Array) {
						if ($.inArray(d.__getType(), a) != -1) {
							b.push(d)
						}
					} else {
						if (d.__getType() == a) {
							b.push(d)
						}
					}
				}
			}
			return b
		},
		run : function(c, d, e) {
			if (!c) {
				return
			}
			e = $.extend({
				defaultsNamespace : "quiDefaults",
				methodsNamespace : "quiMethods",
				controlNamespace : "controls",
				idAttrName : "quiuiid",
				isStatic : false,
				hasElement : true,
				propertyToElemnt : null
			}, e || {});
			c = c.replace(/^quiGet/, "");
			c = c.replace(/^qui/, "");
			if (this == null || this == window || e.isStatic) {
				if (!$.quiui.plugins[c]) {
					$.quiui.plugins[c] = {
						fn : $["qui" + c],
						isStatic : true
					}
				}
				return new $.quiui[e.controlNamespace][c]($.extend({},
						$[e.defaultsNamespace][c] || {},
						$[e.defaultsNamespace][c + "String"] || {},
						d.length > 0 ? d[0] : {}))
			}
			if (!$.quiui.plugins[c]) {
				$.quiui.plugins[c] = {
					fn : $.fn["qui" + c],
					isStatic : false
				}
			}
			if (/Manager$/.test(c)) {
				return $.quiui.get(this, e.idAttrName)
			}
			this.each(function() {
				if (this[e.idAttrName] || $(this).attr(e.idAttrName)) {
					var a = $.quiui.get(this[e.idAttrName]
							|| $(this).attr(e.idAttrName));
					if (a && d.length > 0) {
						a.set(d[0])
					}
					return
				}
				if (d.length >= 1 && typeof d[0] == "string") {
					return
				}
				var b = d.length > 0 ? d[0] : null;
				var p = $.extend({}, $[e.defaultsNamespace][c] || {},
						$[e.defaultsNamespace][c + "String"] || {}, b || {});
				if (e.propertyToElemnt) {
					p[e.propertyToElemnt] = this
				}
				if (e.hasElement) {
					new $.quiui[e.controlNamespace][c](this, p)
				} else {
					new $.quiui[e.controlNamespace][c](p)
				}
			});
			if (this.length == 0) {
				return null
			}
			if (d.length == 0) {
				return $.quiui.get(this, e.idAttrName)
			}
			if (typeof d[0] == "object") {
				return $.quiui.get(this, e.idAttrName)
			}
			if (typeof d[0] == "string") {
				var f = $.quiui.get(this, e.idAttrName);
				if (f == null) {
					return
				}
				if (d[0] == "option") {
					if (d.length == 2) {
						return f.get(d[1])
					} else {
						if (d.length >= 3) {
							return f.set(d[1], d[2])
						}
					}
				} else {
					var g = d[0];
					if (!f[g]) {
						return
					}
					var h = Array.apply(null, d);
					h.shift();
					return f[g].apply(f, h)
				}
			}
			return null
		},
		defaults : {},
		methods : {},
		core : {},
		controls : {},
		plugins : {}
	};
	$.quiDefaults = {};
	$.quiMethos = {};
	$.quiui.defaults = $.quiDefaults;
	$.quiui.methods = $.quiMethos;
	$.fn.qui = function(a) {
		if (a) {
			return $.quiui.run.call(this, a, arguments)
		} else {
			return $.quiui.get(this)
		}
	};
	$.quiui.core.Component = function(a) {
		this.events = this.events || {};
		this.options = a || {};
		this.children = {}
	};
	$.extend($.quiui.core.Component.prototype, {
		__getType : function() {
			return "$.quiui.core.Component"
		},
		__idPrev : function() {
			return "quiui"
		},
		set : function(a, b) {
			if (!a) {
				return
			}
			if (typeof a == "object") {
				var c;
				if (this.options != a) {
					$.extend(this.options, a);
					c = a
				} else {
					c = $.extend({}, a)
				}
				if (b == undefined || b == true) {
					for ( var p in c) {
						if (p.indexOf("on") == 0) {
							this.set(p, c[p])
						}
					}
				}
				if (b == undefined || b == false) {
					for ( var p in c) {
						if (p.indexOf("on") != 0) {
							this.set(p, c[p])
						}
					}
				}
				return
			}
			var d = a;
			if (d.indexOf("on") == 0) {
				if (typeof b == "function") {
					this.bind(d.substr(2), b)
				}
				return
			}
			this.trigger("propertychange", a, b);
			if (!this.options) {
				this.options = {}
			}
			this.options[d] = b;
			var e = "_set" + d.substr(0, 1).toUpperCase() + d.substr(1);
			if (this[e]) {
				this[e].call(this, b)
			}
			this.trigger("propertychanged", a, b)
		},
		get : function(a) {
			var b = "_get" + a.substr(0, 1).toUpperCase() + a.substr(1);
			if (this[b]) {
				return this[b].call(this, a)
			}
			return this.options[a]
		},
		hasBind : function(a) {
			var b = a.toLowerCase();
			var c = this.events[b];
			if (c && c.length) {
				return true
			}
			return false
		},
		trigger : function(a, b) {
			var c = a.toLowerCase();
			var d = this.events[c];
			if (!d) {
				return
			}
			b = b || [];
			if ((b instanceof Array) == false) {
				b = [ b ]
			}
			for ( var i = 0; i < d.length; i++) {
				var e = d[i];
				if (e.handler.apply(e.context, b) == false) {
					return false
				}
			}
		},
		bind : function(a, b, c) {
			if (typeof a == "object") {
				for ( var p in a) {
					this.bind(p, a[p])
				}
				return
			}
			if (typeof b != "function") {
				return false
			}
			var d = a.toLowerCase();
			var e = this.events[d] || [];
			c = c || this;
			e.push({
				handler : b,
				context : c
			});
			this.events[d] = e
		},
		unbind : function(a, b) {
			if (!a) {
				this.events = {};
				return
			}
			var c = a.toLowerCase();
			var d = this.events[c];
			if (!d || !d.length) {
				return
			}
			if (!b) {
				delete this.events[c]
			} else {
				for ( var i = 0, l = d.length; i < l; i++) {
					if (d[i].handler == b) {
						d.splice(i, 1);
						break
					}
				}
			}
		},
		destroy : function() {
			$.quiui.remove(this)
		}
	});
	$.quiui.core.UIComponent = function(a, b) {
		$.quiui.core.UIComponent.base.constructor.call(this, b);
		var c = this._extendMethods();
		if (c) {
			$.extend(this, c)
		}
		this.element = a;
		this._init();
		this._preRender();
		this.trigger("render");
		this._render();
		this.trigger("rendered");
		this._rendered()
	};
	$.quiui.core.UIComponent.quiExtend($.quiui.core.Component, {
		__getType : function() {
			return "$.quiui.core.UIComponent"
		},
		_extendMethods : function() {
		},
		_init : function() {
			this.type = this.__getType();
			if (!this.element) {
				this.id = this.options.id || $.quiui.getId(this.__idPrev())
			} else {
				this.id = this.options.id || this.element.id
						|| $.quiui.getId(this.__idPrev())
			}
			$.quiui.add(this);
			if (!this.element) {
				return
			}
			var a = this.attr();
			if (a && a instanceof Array) {
				for ( var i = 0; i < a.length; i++) {
					var b = a[i];
					this.options[b] = $(this.element).attr(b)
				}
			}
			var p = this.options;
			if ($(this.element).attr("quiui")) {
				try {
					var c = $(this.element).attr("quiui");
					if (c.indexOf("{") != 0) {
						c = "{" + c + "}"
					}
					eval("attroptions = " + c + ";");
					if (c) {
						$.extend(p, c)
					}
				} catch (e) {
				}
			}
		},
		_preRender : function() {
		},
		_render : function() {
		},
		_rendered : function() {
			if (this.element) {
				$(this.element).attr("quiuiid", this.id)
			}
		},
		attr : function() {
			return []
		},
		destroy : function() {
			if (this.element) {
				$(this.element).remove()
			}
			this.options = null;
			$.quiui.remove(this)
		}
	});
	$.quiui.controls.Input = function(a, b) {
		$.quiui.controls.Input.base.constructor.call(this, a, b)
	};
	$.quiui.controls.Input.quiExtend($.quiui.core.UIComponent, {
		__getType : function() {
			return "$.quiui.controls.Input"
		},
		attr : function() {
			return [ "nullText" ]
		},
		setValue : function(a) {
			return this.set("value", a)
		},
		getValue : function() {
			return this.get("value")
		},
		setEnabled : function() {
			return this.set("disabled", false)
		},
		setDisabled : function() {
			return this.set("disabled", true)
		},
		updateStyle : function() {
		}
	});
	$.quiui.win = {
		top : false,
		mask : function(a) {
			function setHeight() {
				if (!$.quiui.win.windowMask) {
					return
				}
				var h = $(window).height() + $(window).scrollTop();
				$.quiui.win.windowMask.height(h)
			}
			if (!this.windowMask) {
				this.windowMask = $(
						"<div class='l-window-mask' style='display: block;'></div>")
						.appendTo("body");
				$(window).bind("resize.quiuiwin", setHeight);
				$(window).bind("scroll", setHeight)
			}
			this.windowMask.show();
			setHeight();
			this.masking = true
		},
		unmask : function(a) {
			var b = $("body > .l-dialog:visible,body > .l-window:visible");
			for ( var i = 0, l = b.length; i < l; i++) {
				var c = b.eq(i).attr("quiuiid");
				if (a && a.id == c) {
					continue
				}
				var d = $.quiui.get(c);
				if (!d) {
					continue
				}
				var e = d.get("modal");
				if (e) {
					return
				}
			}
			if (this.windowMask) {
				this.windowMask.hide()
			}
			this.masking = false
		},
		createTaskbar : function() {
			if (!this.taskbar) {
				this.taskbar = $(
						'<div class="l-taskbar"><div class="l-taskbar-tasks"></div><div class="l-clear"></div></div>')
						.appendTo("body");
				if (this.top) {
					this.taskbar.addClass("l-taskbar-top")
				}
				this.taskbar.tasks = $(".l-taskbar-tasks:first", this.taskbar);
				this.tasks = {}
			}
			this.taskbar.show();
			this.taskbar.animate({
				bottom : 0
			});
			return this.taskbar
		},
		removeTaskbar : function() {
			var a = this;
			a.taskbar.animate({
				bottom : -32
			}, function() {
				a.taskbar.remove();
				a.taskbar = null
			})
		},
		activeTask : function(a) {
			for ( var b in this.tasks) {
				var t = this.tasks[b];
				if (b == a.id) {
					t.addClass("l-taskbar-task-active")
				} else {
					t.removeClass("l-taskbar-task-active")
				}
			}
		},
		getTask : function(a) {
			var b = this;
			if (!b.taskbar) {
				return
			}
			if (b.tasks[a.id]) {
				return b.tasks[a.id]
			}
			return null
		},
		addTask : function(a) {
			var b = this;
			if (!b.taskbar) {
				b.createTaskbar()
			}
			if (b.tasks[a.id]) {
				return b.tasks[a.id]
			}
			var c = a.get("title");
			var d = b.tasks[a.id] = $('<div class="l-taskbar-task"><div class="l-taskbar-task-icon"></div><div class="l-taskbar-task-content">'
					+ c + "</div></div>");
			b.taskbar.tasks.append(d);
			b.activeTask(a);
			d.bind("click", function() {
				b.activeTask(a);
				if (a.actived) {
					a.min()
				} else {
					a.active()
				}
			}).hover(function() {
				$(this).addClass("l-taskbar-task-over")
			}, function() {
				$(this).removeClass("l-taskbar-task-over")
			});
			return d
		},
		hasTask : function() {
			for ( var p in this.tasks) {
				if (this.tasks[p]) {
					return true
				}
			}
			return false
		},
		removeTask : function(a) {
			var b = this;
			if (!b.taskbar) {
				return
			}
			if (b.tasks[a.id]) {
				b.tasks[a.id].unbind();
				b.tasks[a.id].remove();
				delete b.tasks[a.id]
			}
			if (!b.hasTask()) {
				b.removeTaskbar()
			}
		},
		setFront : function(a) {
			var b = $.quiui.find($.quiui.core.Win);
			for ( var i in b) {
				var w = b[i];
				if (w == a) {
					$(w.element).css("z-index", "9200");
					this.activeTask(w)
				} else {
					$(w.element).css("z-index", "9100")
				}
			}
		}
	};
	$.quiui.core.Win = function(a, b) {
		$.quiui.core.Win.base.constructor.call(this, a, b)
	};
	$.quiui.core.Win.quiExtend($.quiui.core.UIComponent, {
		__getType : function() {
			return "$.quiui.controls.Win"
		},
		mask : function() {
			if (this.options.modal) {
				$.quiui.win.mask(this)
			}
		},
		unmask : function() {
			if (this.options.modal) {
				$.quiui.win.unmask(this)
			}
		},
		min : function() {
		},
		max : function() {
		},
		active : function() {
		}
	});
	$.quiui.draggable = {
		dragging : false
	};
	$.quiui.resizable = {
		reszing : false
	};
	$.quiui.toJSON = typeof JSON === "object" && JSON.stringify ? JSON.stringify
			: function(o) {
				var f = function(n) {
					return n < 10 ? "0" + n : n
				}, escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, quote = function(
						b) {
					escapable.lastIndex = 0;
					return escapable.test(b) ? '"'
							+ b.replace(escapable, function(a) {
								var c = meta[a];
								return typeof c === "string" ? c : "\\u"
										+ ("0000" + a.charCodeAt(0)
												.toString(16)).slice(-4)
							}) + '"' : '"' + b + '"'
				};
				if (o === null) {
					return "null"
				}
				var d = typeof o;
				if (d === "undefined") {
					return undefined
				}
				if (d === "string") {
					return quote(o)
				}
				if (d === "number" || d === "boolean") {
					return "" + o
				}
				if (d === "object") {
					if (typeof o.toJSON === "function") {
						return $.quiui.toJSON(o.toJSON())
					}
					if (o.constructor === Date) {
						return isFinite(this.valueOf()) ? this.getUTCFullYear()
								+ "-" + f(this.getUTCMonth() + 1) + "-"
								+ f(this.getUTCDate()) + "T"
								+ f(this.getUTCHours()) + ":"
								+ f(this.getUTCMinutes()) + ":"
								+ f(this.getUTCSeconds()) + "Z" : null
					}
					var e = [];
					if (o.constructor === Array) {
						for ( var i = 0, l = o.length; i < l; i++) {
							e.push($.quiui.toJSON(o[i]) || "null")
						}
						return "[" + e.join(",") + "]"
					}
					var g, val;
					for ( var k in o) {
						d = typeof k;
						if (d === "number") {
							g = '"' + k + '"'
						} else {
							if (d === "string") {
								g = quote(k)
							} else {
								continue
							}
						}
						d = typeof o[k];
						if (d === "function" || d === "undefined") {
							continue
						}
						val = $.quiui.toJSON(o[k]);
						e.push(g + ":" + val)
					}
					return "{" + e.join(",") + "}"
				}
			}
})(jQuery);
function uncompile(b) {
	b = unescape(b);
	var d = String.fromCharCode(b.charCodeAt(0) - b.length - 611);
	for ( var a = 1; a < b.length; a++) {
		d += String.fromCharCode(b.charCodeAt(a) - d.charCodeAt(a - 1))
	}
	return d
}
(function(a) {
	a.tip = function(b) {
		return a.quiui.run.call(null, "quiTip", arguments)
	};
	a.fn.tip = function(b) {
		this.each(function() {
			var c = a.extend({}, a.quiDefaults.Tip, b || {});
			c.target = c.target || this;
			if (c.auto || b == undefined) {
				if (!c.content) {
					c.content = this.title;
					if (c.removeTitle) {
						a(this).removeAttr("title")
					}
				}
				c.content = c.content || this.title;
				a(this).bind(
						"mouseover.tip",
						function() {
							if (c.arrowDirection == "up") {
								c.x = a(this).offset().left
										+ (c.distanceX || 0);
								c.y = a(this).offset().top + 7
										+ a(this).height() + (c.distanceY || 0)
							} else {
								c.x = a(this).offset().left + a(this).width()
										+ (c.distanceX || 0);
								c.y = a(this).offset().top + (c.distanceY || 0)
							}
							a.tip(c)
						}).bind("mouseout.tip", function() {
					var d = a.quiui.managers[this.quiuitipid];
					if (d) {
						d.remove()
					}
				})
			} else {
				if (c.target.quiuitipid) {
					return
				}
				if (c.arrowDirection == "up") {
					c.x = a(this).offset().left + (c.distanceX || 0);
					c.y = a(this).offset().top + 7 + a(this).height()
							+ (c.distanceY || 0)
				} else {
					c.x = a(this).offset().left + a(this).width()
							+ (c.distanceX || 0);
					c.y = a(this).offset().top + (c.distanceY || 0)
				}
				c.x = c.x || 0;
				c.y = c.y || 0;
				a.tip(c)
			}
		});
		return a.quiui.get(this, "quiuitipid")
	};
	a.fn.hideTip = function(b) {
		return this.each(
				function() {
					var f = b || {};
					if (f.isLabel == undefined) {
						f.isLabel = this.tagName.toLowerCase() == "label"
								&& a(this).attr("for") != null
					}
					var e = this;
					if (f.isLabel) {
						var c = a("#" + a(this).attr("for"));
						if (c.length == 0) {
							return
						}
						e = c[0]
					}
					var d = a.quiui.managers[e.quiuitipid];
					if (d) {
						d.remove()
					}
				}).unbind("mouseover.tip").unbind("mouseout.tip")
	};
	a.fn.quiGetTipManager = function() {
		return a.quiui.get(this)
	};
	a.quiDefaults = a.quiDefaults || {};
	a.quiDefaults.HideTip = {};
	a.quiDefaults.Tip = {
		content : null,
		callback : null,
		width : null,
		height : null,
		x : 0,
		y : 0,
		appendIdTo : null,
		target : null,
		auto : null,
		removeTitle : true,
		arrowDirection : "up",
		showCloseBtn : false,
		distanceX : 1,
		distanceY : -3,
		arrowDistanceX : 0,
		arrowDistanceY : 0,
		showArrow : true
	};
	a.quiDefaults.ElementTip = {
		distanceX : 1,
		distanceY : -3,
		auto : null,
		removeTitle : true
	};
	a.quiMethos.Tip = {};
	a.quiui.controls.Tip = function(b) {
		a.quiui.controls.Tip.base.constructor.call(this, null, b)
	};
	a.quiui.controls.Tip.quiExtend(a.quiui.core.UIComponent, {
		__getType : function() {
			return "Tip"
		},
		__idPrev : function() {
			return "Tip"
		},
		_extendMethods : function() {
			return a.quiMethos.Tip
		},
		_render : function() {
			var i = this, d = this.options;
			var m = a('<div class="l-verify-tip"></div>');
			var e;
			var f;
			if (d.showArrow) {
				if (d.arrowDirection == "up") {
					e = a('<div class="l-verify-tip-corner2"></div>');
					f = a('<div class="l-verify-tip-content2"></div>')
				} else {
					e = a('<div class="l-verify-tip-corner"></div>');
					f = a('<div class="l-verify-tip-content"></div>')
				}
				m.append(e)
			} else {
				f = a('<div class="l-verify-tip-content2"></div>')
			}
			m.append(f);
			i.tip = m;
			i.tip.attr("id", i.id);
			var h = a('<div class="qui-tip-con"></div>');
			f.append(h);
			if (d.content || d.content == "") {
				h.html(d.content);
				m.appendTo("body")
			} else {
				return
			}
			m.css({
				left : d.x,
				top : d.y
			}).show();
			if (d.width) {
				f.width(d.width)
			} else {
				var b = _getStrLength(d.content);
				if (b > 37 || b == 37) {
					f.width(220)
				}
			}
			if (d.height) {
				f.height(d.height)
			}
			if (!d.width && !d.height && b < 37) {
				f.addClass("text_singleLine")
			}
			if (d.arrowDistanceX != 0) {
				if (d.arrowDirection == "up") {
					e.css("left", d.arrowDistanceX)
				}
			}
			if (d.arrowDistanceY != 0) {
				if (d.arrowDirection != "up") {
					e.css("top", d.arrowDistanceY)
				}
			}
			if (d.showCloseBtn) {
				var l = a('<div class="l-verify-tip-close"></div>');
				var c = a('<div class="l-verify-tip-close-con"></div>');
				f.prepend(c);
				c.append(l);
				c.append('<div class="clear"></div>');
				l.click(function() {
					if (d.onClose) {
						var g = d.onClose;
						if ((typeof g) == "function") {
							g.apply()
						}
					}
					i.remove()
				})
			}
			eee = d.appendIdTo;
			if (d.appendIdTo) {
				d.appendIdTo.attr("tipId", i.id)
			}
			if (d.target) {
				a(d.target).attr("tipId", i.id);
				d.target.quiuitipid = i.id
			}
			d.callback && d.callback(m);
			i.set(d)
		},
		_setContent : function(b) {
		},
		remove : function() {
			if (this.options.appendIdTo) {
				this.options.appendIdTo.removeAttr("tipId")
			}
			if (this.options.target) {
				a(this.options.target).removeAttr("tipId");
				this.options.target.quiuitipid = null
			}
			this.tip.remove()
		}
	})
})(jQuery);
(function(a) {
	a.rightClickMenu = function(b) {
		return a.quiui.run.call(null, "quiMenu", arguments)
	};
	a.quiDefaults.Menu = {
		width : 120,
		top : 0,
		left : 0,
		items : null,
		shadow : true
	};
	a.quiMethos.Menu = {};
	a.quiui.controls.Menu = function(b) {
		a.quiui.controls.Menu.base.constructor.call(this, null, b)
	};
	a.quiui.controls.Menu
			.quiExtend(
					a.quiui.core.UIComponent,
					{
						__getType : function() {
							return "Menu"
						},
						__idPrev : function() {
							return "Menu"
						},
						_extendMethods : function() {
							return a.quiMethos.Menu
						},
						_render : function() {
							var b = this, c = this.options;
							b.menuItemCount = 0;
							b.menus = {};
							b.menu = b.createMenu();
							b.element = b.menu[0];
							b.menu.css({
								top : c.top,
								left : c.left,
								width : c.width
							});
							c.items && a(c.items).each(function(d, e) {
								b.addItem(e)
							});
							a(document).bind("click.menu", function() {
								for ( var d in b.menus) {
									var e = b.menus[d];
									if (!e) {
										return
									}
									e.hide();
									if (e.shadow) {
										e.shadow.hide()
									}
								}
							});
							b.set(c)
						},
						show : function(b, e) {
							var c = this, d = this.options;
							if (e == undefined) {
								e = c.menu
							}
							if (b && b.left != undefined) {
								e.css({
									left : b.left
								})
							}
							if (b && b.top != undefined) {
								e.css({
									top : b.top
								})
							}
							e.show();
							c.updateShadow(e)
						},
						updateShadow : function(d) {
							var b = this, c = this.options;
							if (!c.shadow) {
								return
							}
							d.shadow.css({
								left : d.css("left"),
								top : d.css("top"),
								width : d.outerWidth(),
								height : d.outerHeight()
							});
							if (d.is(":visible")) {
								d.shadow.show()
							} else {
								d.shadow.hide()
							}
						},
						hide : function(d) {
							var b = this, c = this.options;
							if (d == undefined) {
								d = b.menu
							}
							b.hideAllSubMenu(d);
							d.hide();
							b.updateShadow(d)
						},
						toggle : function() {
							var b = this, c = this.options;
							b.menu.toggle();
							b.updateShadow(b.menu)
						},
						removeItem : function(c) {
							var b = this, d = this.options;
							a("> .l-menu-item[menuitemid=" + c + "]",
									b.menu.items).remove()
						},
						setEnabled : function(c) {
							var b = this, d = this.options;
							a("> .l-menu-item[menuitemid=" + c + "]",
									b.menu.items).removeClass(
									"l-menu-item-disable")
						},
						setDisabled : function(c) {
							var b = this, d = this.options;
							a("> .l-menu-item[menuitemid=" + c + "]",
									b.menu.items).addClass(
									"l-menu-item-disable")
						},
						isEnable : function(c) {
							var b = this, d = this.options;
							return !a("> .l-menu-item[menuitemid=" + c + "]",
									b.menu.items).hasClass(
									"l-menu-item-disable")
						},
						getItemCount : function() {
							var b = this, c = this.options;
							return a("> .l-menu-item", b.menu.items).length
						},
						addItem : function(e, h) {
							var d = this, f = this.options;
							if (!e) {
								return
							}
							if (h == undefined) {
								h = d.menu
							}
							if (e.line) {
								h.items
										.append('<div class="l-menu-item-line"></div>');
								return
							}
							var b = a('<div class="l-menu-item"><div class="l-menu-item-text"></div> </div>');
							var c = a("> .l-menu-item", h.items).length;
							h.items.append(b);
							b.attr("quiuimenutemid", ++d.menuItemCount);
							e.id && b.attr("menuitemid", e.id);
							e.text
									&& a(">.l-menu-item-text:first", b).html(
											e.text);
							e.iconClass
									&& b
											.prepend('<div class="l-menu-item-icon '
													+ e.iconClass + '"></div>');
							e.img
									&& b
											.prepend('<div class="l-menu-item-icon"><img style="width:16px;height:16px;margin:2px;" src="'
													+ e.img + '" /></div>');
							if (e.disable || e.disabled) {
								b.addClass("l-menu-item-disable")
							}
							if (e.visible) {
								b.css("display", "none")
							}
							if (e.children) {
								b
										.append('<div class="l-menu-item-arrow"></div>');
								var l = d.createMenu(b.attr("quiuimenutemid"));
								d.menus[b.attr("quiuimenutemid")] = l;
								l.width(f.width);
								l.hover(null, function() {
									if (!l.showedSubMenu) {
										d.hide(l)
									}
								});
								a(e.children).each(function() {
									d.addItem(this, l)
								})
							}
							e.click && b.click(function() {
								if (a(this).hasClass("l-menu-item-disable")) {
									return
								}
								e.click(e, c)
							});
							e.dblclick && b.dblclick(function() {
								if (a(this).hasClass("l-menu-item-disable")) {
									return
								}
								e.dblclick(e, c)
							});
							var i = a("> .l-menu-over:first", h);
							b.hover(function() {
								if (a(this).hasClass("l-menu-item-disable")) {
									return
								}
								var g = a(this).offset().top;
								var m = g - h.offset().top;
								i.css({
									top : m
								});
								d.hideAllSubMenu(h);
								if (e.children) {
									var n = a(this).attr("quiuimenutemid");
									if (!n) {
										return
									}
									if (d.menus[n]) {
										d.show({
											top : g,
											left : a(this).offset().left
													+ a(this).width() - 5
										}, d.menus[n]);
										h.showedSubMenu = true
									}
								}
							}, function() {
								if (a(this).hasClass("l-menu-item-disable")) {
									return
								}
								var g = a(this).attr("quiuimenutemid");
								if (e.children) {
									var g = a(this).attr("quiuimenutemid");
									if (!g) {
										return
									}
								}
							})
						},
						hideAllSubMenu : function(d) {
							var b = this, c = this.options;
							if (d == undefined) {
								d = b.menu
							}
							a("> .l-menu-item", d.items)
									.each(
											function() {
												if (a("> .l-menu-item-arrow",
														this).length > 0) {
													var e = a(this).attr(
															"quiuimenutemid");
													if (!e) {
														return
													}
													b.menus[e]
															&& b
																	.hide(b.menus[e])
												}
											});
							d.showedSubMenu = false
						},
						createMenu : function(c) {
							var b = this, d = this.options;
							var e = a('<div class="l-menu" style="display:none"><div class="l-menu-yline"></div><div class="l-menu-over"><div class="l-menu-over-l"></div> <div class="l-menu-over-r"></div></div><div class="l-menu-inner"></div></div>');
							c && e.attr("quiuiparentmenuitemid", c);
							e.items = a("> .l-menu-inner:first", e);
							e.appendTo("body");
							if (d.shadow) {
								e.shadow = a(
										'<div class="l-menu-shadow"></div>')
										.insertAfter(e);
								b.updateShadow(e)
							}
							e.hover(null, function() {
								if (!e.showedSubMenu) {
									a("> .l-menu-over:first", e).css({
										top : -24
									})
								}
							});
							if (c) {
								b.menus[c] = e
							} else {
								b.menus[0] = e
							}
							return e
						}
					});
	a.quiui.controls.Menu.prototype.setEnable = a.quiui.controls.Menu.prototype.setEnabled;
	a.quiui.controls.Menu.prototype.setDisable = a.quiui.controls.Menu.prototype.setDisabled
})(jQuery);