/**
 * 基于json的map实现js<br>
 * 
 * 接口（方法）： <br>
 * size() 获取MAP元素个数 <br>
 * isEmpty() 判断MAP是否为空<br>
 * clear() 删除MAP所有元素<br>
 * put(key, value) 向MAP中增加元素<br>
 * remove(key)删除指定KEY的元素，成功返回True，失败返回False<br>
 * get(key) 获取指定KEY的元素值VALUE，失败返回NULL<br>
 * containsKey(key) 判断MAP中是否含有指定KEY的元素 <br>
 * values() 获取MAP中所有VALUE的数组（ARRAY） <br>
 * keys() 获取MAP中所有KEY的数组（ARRAY）<br>
 * toJSON() * 附加方法：将map转换成JSON对象
 * 
 * 例子： <br>
 * var map = new Map(); <br>
 * map.put("key", "value"); <br>
 * var val = map.get("key")<br>
 * 
 * @author BraveHeartWzm
 */
function Map() {
	// map内部存储对象
	var jm = {};

	/**
	 * 获取map的长度
	 */
	this.size = function() {
		return Object.keys(jm).length;
	};

	/**
	 * 判断map是否为空
	 */
	this.isEmpty = function() {
		for ( var eo in jm) {
			return false;
		}
		return true;
	};

	/**
	 * 清除map所有元素
	 */
	this.clear = function() {
		jm = {};
	};

	/**
	 * 将元素存入map，重复的key将被新的替换
	 */
	this.put = function(key, value) {
		jm[key + ""] = value;
		return value;
	};

	/**
	 * 将map放入map，重复的key将被新的替换
	 */
	this.putAll = function(jmap) {
		var jsm=jmap.toJSON();
		for ( var ao in jsm) {
			jm[ao + ""] = jsm[ao];
		}
	};

	/**
	 * 移除一个元素
	 */
	this.remove = function(key) {
		var rov = jm[key + ""];
		delete jm[key + ""];
		return rov;
	};

	/**
	 * 获取一个元素
	 */
	this.get = function(key) {
		var val = jm[key+""];
		// 如果未定义或者为空，则返回空
		if (null == val || undefined == val) {
			return null;
		}
		return val;
	};

	/**
	 * 判断是否含有某个key
	 */
	this.containsKey = function(key) {
		return (key + "") in jm;
	};

	/**
	 * 获取map中的所有key的数组
	 */
	this.keys = function() {
		return Object.keys(jm);
	};

	/**
	 * 获取map中的所有value的数组
	 */
	this.values = function() {
		var vs = new Array();
		for ( var om in jm) {
			vs.push(jm[om]);
		}
		return vs;
	};

	/**
	 * 附加方法：将map对象转换成json
	 */
	this.toJSON = function() {
		return jm;
	};
	
	/**
	 * 附加方法：将JSON放入map，重复的key将被新的替换
	 */
	this.putJSONAll = function(json) {
		for ( var ao in json) {
			jm[ao + ""] = json[ao];
		}
	};

}
