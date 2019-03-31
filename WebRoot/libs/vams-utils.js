/**工具js包 v0.1*/
/**严格转换JSON*/
function toJSON(str){
	return $.parseJSON(str);
}

/**字符串eval*/
function toEval(str){ 
	return eval("("+str+")");
}

/**用函数转换JSON*/
function toFunctionVal(str){
	return new Function("return "+str);
}




//====================================================================================
//载入业务类型
var ywlx_map=new Map();
//载入号牌种类
var hpzl_map=new Map();

/**
 * 加载号牌种类map
 * */
function loadHpzlMap(path){
	var phf="res-json-getHpzlList.action";
	if(null != path && ""!= path){
		phf=path+phf;
	}
	$.post(phf,{},function(data){
		if(null != data){
			for(var i=0;i<data.length;i++){
				hpzl_map.put(data[i].key, data[i].value)
			}
		}
	});
}
/**
 * 加载业务类型map
 * */
function loadYwlxMap(path){
	var phf="res-json-getYwlxList.action";
	if(null != path && ""!= path){
		phf=path+phf;
	}
	$.post(phf,{},function(data){
		if(null != data){
			for(var i=0;i<data.length;i++){
				ywlx_map.put(data[i].key, data[i].value)
			}
		}
	});
}
