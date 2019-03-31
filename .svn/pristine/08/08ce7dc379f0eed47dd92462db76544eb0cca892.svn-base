package com.pd.right.service.impl;

import java.util.List;

import com.pd.right.dao.impl.LogDAO;
import com.pd.right.model.Log;
import com.pd.right.model.LogExample;
import com.pd.right.service.LogService;
import com.pd.system.framework.DAOFactory;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: LogServiceImpl 
*  系统日志业务逻辑层
* @author zl
* @date 2013-6-25 上午10:26:56 
*
 */
public class LogServiceImpl implements LogService {
	
	private LogDAO logDao = DAOFactory.getDao("logDAO");;
	
	/**
	 * 日志信息插入
	 */
	@Override
	public void add(Log entity) throws Exception {
		logDao.insert(entity);
	}

	/**
	 * 获取日志信息分页
	 */
	@Override
	public Pagination getPageByExample(int index, LogExample example)
			throws Exception {
		int total = logDao.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 查询日志信息列表
	 */
	@Override
	public List<Log> query(LogExample example) throws Exception {
		List<Log> list_data = logDao.selectByExampleToPage(example);
		return list_data;
	}

}


