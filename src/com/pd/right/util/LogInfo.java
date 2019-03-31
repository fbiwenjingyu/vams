package com.pd.right.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.right.model.Log;
import com.pd.right.service.LogService;
import com.pd.right.service.impl.LogServiceImpl;
import com.pd.system.utils.StringTools;

/**
 * 日志记录 类
 * 
 * @author zl
 * @motify wzm
 * */
public class LogInfo {
	private static LogService logService = new LogServiceImpl();

	// 代码map
	private static final Map<String, Long> PROGRAM_MAP = new HashMap<String, Long>();

	private static final String regStr = ".action";

	private static void saveLog(Log record) throws Exception {
		logService.add(record);
	}

	/**
	 * 载入 代码表
	 * */
	public static void loadProgram(JdbcTemplate template) {
		String sql = "select RESID,RESURL from RESOURCES";
		List<Object> list = template.queryForList(sql);
		for (Object obj : list) {
			Map<String, String> pro = (Map<String, String>) obj;
			String key = pro.get("RESURL");
			if (null != key && !"".equals(key)) {
				key = key.split(regStr)[0];
				String val = pro.get("RESID");
				PROGRAM_MAP.put(key, Long.parseLong(val));
			}
		}
	}

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
	public static void info(String cz, String czip, String jmw, String logtype,
			Long program, String usercode, String xxsm) throws Exception {

		Log record = new Log();
		record.setId(UUID.randomUUID().toString());
		record.setCz(cz);
		record.setCzip(czip);
		record.setCzsj(new Date());
		record.setJmw(jmw);
		record.setLogtype(logtype);
		record.setProgram(program);
		record.setUserCode(usercode);
		record.setXxsm(xxsm);
		saveLog(record);// 保存日志
	}

	/**
	 * 这是一个快捷的日志记录方法，记录业务日志，此方法建议在Action中调用
	 * 
	 * @param opera
	 *            操作说明，例如：登录
	 * @param request
	 *            http请求对象
	 * @param usercode
	 *            用户code
	 * @param xxsm
	 *            详细说明（500字以内） 比如登录扫描等都要详细
	 * */
	public static void infoYw(String opera, HttpServletRequest request,
			String usercode, String xxsm) {
		try {
			String uri = StringTools.getRequestActionUri(request).split(regStr)[0];// 获取除去ip地址和端口后url地址，然后根据.action进行切割
			Long program = PROGRAM_MAP.get(uri);
			// 调用日志方法
			info(opera, request.getRemoteAddr(), "", "2", program, usercode,
					xxsm);// 只记录业务日志
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这是一个快捷的日志记录方法，记录系统日志，此方法建议在Action中调用
	 * 
	 * @param opera
	 *            操作说明，例如：登录
	 * @param request
	 *            http请求对象
	 * @param usercode
	 *            用户code
	 * @param xxsm
	 *            详细说明（500字以内） 比如登录扫描等都要详细
	 * */
	public static void infoXt(String opera, HttpServletRequest request,
			String usercode, String xxsm) {
		try {
			String uri = StringTools.getRequestActionUri(request).split(regStr)[0];// 获取除去ip地址和端口后url地址，然后根据.action进行切割
			Long program = PROGRAM_MAP.get(uri);
			// 调用日志方法
			info(opera, request.getRemoteAddr(), "", "1", program, usercode,
					xxsm);// 只记录业务日志
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInfo() throws Exception {
		LogInfo.info("角色添加", "", "", "1", Long.parseLong("1"), "用户", "用户添加角色");

	}

}
