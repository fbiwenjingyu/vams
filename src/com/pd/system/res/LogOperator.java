package com.pd.system.res;

import java.util.Date;

import com.pd.right.model.Log;
import com.pd.right.service.LogService;
import com.pd.system.framework.DAOFactory;
import com.pd.system.utils.StringTools;

/**
 * 系统日志操作保存
 * */
public class LogOperator {
	
	/**
	 * 获取日志service
	 * */
	private static LogService logService = DAOFactory.getService(LogService.class);
	
	
	/**
	 * 记录系统操作日志，通用方法
	 * 
	 * @param cz
	 *            操作说明，例如：登录
	 * @param czip
	 *            操作IP地址，有多IP情况
	 * @param jmw
	 *            加密位
	 * @param logtype
	 *            日志类别 ：1系统日志 、2业务日志
	 * @param program
	 *            模块代码
	 * @param usercode
	 *            用户code
	 * @param xxsm
	 *            详细说明（500字以内） 比如登录扫描等都要详细
	 * */
	public static void saveLog(String cz, String czip, String jmw, String logtype,
			Long program, String usercode, String xxsm) throws Exception{
		Log log = new Log();
		log.setId(StringTools.getUUID());
		log.setCz(cz);
		log.setCzip(czip);
		log.setCzsj(new Date());
		log.setJmw(jmw);
		log.setLogtype(logtype);
		log.setProgram(program);
		log.setUserCode(usercode);
		log.setXxsm(xxsm);
		logService.add(log);
	}
	
}
