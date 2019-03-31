package com.pd.wjyw.service;

import java.util.List;
import java.util.Map;

import com.pd.system.framework.Pagination;
import com.pd.wjyw.model.*;

/**
 * 外检纸张设置接口
* @ClassName: YcPaperService 
* @Description: 定义外检纸张设置
* @author zl
* @date 2014-8-1 上午11:38:45 
*
 */
public interface YcPaperService {
	/**
	 * 外检纸张添加
	* @Title: add 
	* @Description: TODO
	* @param @param ycPaper
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-1
	 */
	void add(YcPaper ycPaper) throws Exception;
	
	/**
	 * 外检纸张信息修改
	* @Title: update 
	* @Description: TODO
	* @param @param ycPaper
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2014-8-1
	 */
	int update(YcPaper ycPaper) throws Exception;
	
	/**
	 * 根据id删除外检纸张信息
	* @Title: delete 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2014-8-1
	 */
	int delete(String id) throws Exception;
	
	/**
	 * 根据id查询外检纸张信息
	* @Title: getEntity 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return YcPaper    返回类型 
	* @throws 
	* @author zl
	* 2014-8-1
	 */
	YcPaper getEntity(String id) throws Exception;
	
	/**
	 * 分页根据条件查询外检信息
	* @Title: query 
	* @Description: TODO
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<YcPaper>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-1
	 */
	List<YcPaper> query(YcPaperExample example) throws Exception;
	
	/**
	 * 根据条件查询外检信息对象
	* @Title: queryToMap 
	* @Description: TODO
	* @param @param example
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	* @author zl
	* 2014-8-1
	 */
	Map queryToMap(YcPaperExample example);

	/**
	 * 根据条件查询外检信息
	* @Title: selectByExample 
	* @Description: TODO
	* @param @param ycPaperExample
	* @param @return    设定文件 
	* @return List<YcPaper>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	List<YcPaper> selectByExample(YcPaperExample ycPaperExample);

	Pagination findPageList(int index, YcPaperExample ycPaperExample) throws Exception;
}


