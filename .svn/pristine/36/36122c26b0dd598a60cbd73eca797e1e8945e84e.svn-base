package com.pd.right.service;

import java.util.List;
import java.util.Map;

import com.pd.right.model.Dept;
import com.pd.right.model.DeptExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: DeptService 
*  机构管理业务逻辑接口
* @author zl
* @date 2013-6-28 上午08:58:12 
*
 */
public interface DeptService {
	/**
	 * 
	* @Title: add 
	*  机构添加保存
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	void add(Dept entity) throws Exception;
	/**
	 * 
	* @Title: update 
	*  机构修改保存
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	int update(Dept entity) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	*  机构删除
	* @param @param deptCode
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	int delete(String deptCode) throws Exception;
	/**
	 * 
	* @Title: getEntity 
	*  根据代码获取机构对象
	* @param @param deptCode
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Dept    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	Dept getEntity(String deptCode) throws Exception;
	/**
	 * 
	* @Title: getPageByExample 
	*  机构分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	Pagination getPageByExample(int index,DeptExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  机构分页信息查询
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Dept>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<Dept> query(DeptExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件获取机构查询列表
	* @param @param example
	* @param @return    设定文件 
	* @return List<Dept>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-28
	 */
	List<Dept> selectByExample(DeptExample example);
	
	/**
	 * 
	* @Title: queryToMap 
	*  根据id获取级别供其他相关联表进行查询显示
	* @param @param example
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	* @author zl
	* 2013-6-27
	 */
	public Map queryToMap(DeptExample example);
}


