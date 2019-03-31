package com.pd.wjyw.service;

import java.util.List;

import com.pd.system.framework.Pagination;
import com.pd.wjyw.model.YcTypePaper;
import com.pd.wjyw.model.YcTypePaperExample;

public interface YcTypePaperService {
	/**
	 * 添加外检号牌种类业务类型需要拍照的纸张信息
	 * @param takecodes 
	* @Title: add 
	* @Description: TODO
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	void add(YcTypePaper entity, String[] takecodes) throws Exception;
	
	/**
	 * 删除外检设置信息
	* @Title: delete 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	int delete(String id) throws Exception;
	
	/**
	 * 根据条件查询外检设置信息
	* @Title: selectByExample 
	* @Description: TODO
	* @param @param ycPaperExample
	* @param @return    设定文件 
	* @return List<YcTypePaper>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	List<YcTypePaper> selectByExample(YcTypePaperExample ycPaperExample);
	
	/**
	 * 分页根据条件查询外检设置信息
	* @Title: query 
	* @Description: TODO
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<YcTypePaper>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	List<YcTypePaper> query(YcTypePaperExample example) throws Exception;
	
	/**
	 * 获取分页信息记录
	* @Title: findPageList 
	* @Description: TODO
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	Pagination findPageList(int index, YcTypePaperExample example) throws Exception;
}


