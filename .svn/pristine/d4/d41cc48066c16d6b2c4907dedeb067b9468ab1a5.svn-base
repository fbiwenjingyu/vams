package com.pd.base.service;

import java.util.List;
import java.util.Map;

import com.pd.base.model.Paper;
import com.pd.base.model.PaperExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: PaperService 
*  扫描纸张设置接口
* @author zl
* @date 2013-6-25 下午05:17:52 
*
 */
public interface PaperService {
	/**
	 * 
	* @Title: add 
	*  扫描纸张添加
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	void add(Paper entity) throws Exception;
	/**
	 * 
	* @Title: update 
	*  扫描纸张修改
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int update(Paper entity) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	*  根据id删除扫描纸张信息
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
	*  根据id获取纸张对象
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Paper    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Paper getEntity(String id) throws Exception;
	/**
	 * 
	* @Title: getPageByExample 
	*  获取扫描纸张分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Pagination getPageByExample(int index,PaperExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  对纸张信息进行分页列表显示
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Paper>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Paper> query(PaperExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询扫描纸张信息
	* @param @param example
	* @param @return    设定文件 
	* @return List<Paper>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Paper> selectByExample(PaperExample example);
	Map queryToMap(PaperExample example);

}


