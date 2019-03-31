package com.pd.system.framework;

import com.pd.system.res.Constants;

/**
 * service封装鸡肋，建议继承此类，方便扩展
 * 
 * @author BraveHeartWzm
 * */
public abstract class BaseService {

	private static final String IBATIS_COUNT_BY_EXAMPLE = "countByExample";

	/**
	 * 获取分页数据，封装调用方法，此方法将调用dao中的countByExample方法进行统计
	 * 
	 * @param index
	 *            当前页
	 * @param example
	 *            分页查询条件
	 * @param dao
	 *            实现了BaseDAO的dao对象
	 * @return 分页对象Pagination
	 * @author BraveHeartWzm
	 * */
	protected Pagination findPageList(int index, PageToExample example,
			BaseDAO dao) throws Exception {
		// 总条数
		int totalItems = (Integer) dao.getClass()
				.getMethod(IBATIS_COUNT_BY_EXAMPLE, example.getClass())
				.invoke(dao, example);
		//创建分页对象
		Pagination page = new Pagination(totalItems, Constants.PAGE_ITEM, index);
		// 设置起条数
		example.setStart(page.getPageSize() * (page.getIndex() - 1) + 1);
		// 设置结条数
		example.setLimit(page.getPageSize() * page.getIndex());
		// 设置分页数据
		page.setPagelist(dao.selectByExampleToPage(example));
		return page;
	}
}
