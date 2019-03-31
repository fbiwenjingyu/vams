package com.pd.base.service;

import java.util.List;
import java.util.Map;

import com.pd.base.model.Attr;
import com.pd.base.model.AttrExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: AttrService 
*  属性设置接口
* @author zl
* @date 2013-6-25 下午05:12:27 
*
 */
public interface AttrService {
	/**
	 * 
	* @Title: add 
	*  属性设置添加
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	void add(Attr entity) throws Exception;
	/**
	 * 
	* @Title: update 
	*  属性修改
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int update(Attr entity) throws Exception;
	/**
	 * 
	* @Title: delete 
	*  属性设置删除
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int delete(String id) throws Exception;
	
	/**
	 * 
	* @Title: getEntity 
	*  根据id获取属性对象
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Attr    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Attr getEntity(String id) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  获取属性分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Pagination getPageByExample(int index,AttrExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  属性分页查询列表
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Attr>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Attr> query(AttrExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件获取属性
	* @param @param example
	* @param @return    设定文件 
	* @return List<Attr>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Attr> selectByExample(AttrExample example);
	
	
	Map queryToMap(AttrExample example);
}


