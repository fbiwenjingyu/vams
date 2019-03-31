package com.pd.base.service;

import java.util.List;

import com.pd.base.model.Typeset;
import com.pd.base.model.TypesetExample;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: TypeSetService 
*  设置业务角色纸张关系接口
* @author zl
* @date 2013-6-25 下午05:32:28 
*
 */
public interface TypeSetService {
	
	/**
	 * 
	* @Title: add 
	*  保存
	* @param @param entity
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	void add(Typeset entity) throws Exception;
	
	/**
	 * 
	* @Title: update 
	*  更新操作
	* @param @param entity
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	int update(Typeset entity) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	*  根据id及逆行那个删除
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
	*  根据id获取对象
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Typeset    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Typeset getEntity(String id) throws Exception;
	
	/**
	 * 
	* @Title: getPageByExample 
	*  分页
	* @param @param index
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	Pagination getPageByExample(int index,TypesetExample example) throws Exception;
	
	/**
	 * 
	* @Title: query 
	*  分页查询列表
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Typeset>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Typeset> query(TypesetExample example) throws Exception;
	
	/**
	 * 
	* @Title: selectByExample 
	*  根据条件查询
	* @param @param example
	* @param @return    设定文件 
	* @return List<Typeset>    返回类型 
	* @throws 
	* @author zl
	* 2013-6-25
	 */
	List<Typeset> selectByExample(TypesetExample example);

	/**
	 * 
	* @Title: add 
	*  批量保存角色业务类型纸张关系
	* @param @param entity
	* @param @param string
	* @param @param string2    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-7-4
	 */
	void add(Typeset entity, String[] paper,String optPaper[], String[] type);

	/**
	 * 
	* @Title: update 
	*  批量修改角色业务类型
	* @param @param entity
	* @param @param paper
	* @param @param type    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2013-7-12
	 */
	void update(Typeset entity, String[] paper, String[] optPaper);
}


