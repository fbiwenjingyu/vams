package com.pd.base.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.pd.base.model.Flow;
import com.pd.base.model.FlowExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: FlowService 
*  系统流程
* @author zl
* @date 2013-6-25 下午05:16:05 
*
 */
public interface FlowService {
	/**
	 * 
	* @Title: add 
	*  系统流程添加
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	void add(Flow entity) throws Exception;
	
	/**
	 * 
	* @Title: update 
	*  系统流程更新
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int update(Flow entity) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	*  根据id删除系统流程
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
	*  根据id获取流程对象
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Flow    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Flow getEntity(String id) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  获取系统流程分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Pagination getPageByExample(int index,FlowExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  对系统流程进行查询列表显示
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Flow>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Flow> query(FlowExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询流程对象
	* @param @param example
	* @param @return    设定文件 
	* @return List<Flow>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Flow> selectByExample(FlowExample example);

	/**
	 * 
	* @Title: add 
	*  根据业务类型批量保存流程信息
	* @param @param entity
	* @param @param type    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-7-4
	 */
	void add(Flow entity, String[] type);

	Map queryToMap(FlowExample example);
	
	/**
	 * 根据业务类型获取流程步骤最大的流程信息
	 * @param ywlx
	 * @return json
	 * 
	 * @author wangwei
	 * Aug 5, 2013
	 */
	public JSONObject getEntityByYwlxId(String ywlx);
}


