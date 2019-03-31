package com.pd.system.framework;

import java.util.List;

public interface BaseDAO {
	
	String SELECT_EXAMPLE_TO_PAGE="selectByExampleToPage";

	/**
	 * 分页查询方法
	 * 
	 * @return
	 * */
	List selectByExampleToPage(PageToExample example);

}
