package com.pd.system.startup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.right.util.LogInfo;
import com.pd.system.framework.DAOFactory;
import com.pd.system.utils.Kval;

/**
 * 系统资源加载器
 */
public class SYSLoadManager extends HttpServlet {

	public static String serverPath = "";

	private static final long serialVersionUID = 7979365427916334399L;
	private static Logger log = Logger.getLogger(SYSLoadManager.class);
	private static JdbcTemplate jdbcTemplate = DAOFactory.getDao(
			"jdbcTemplate");

	// 当前操作系统
	private static String macos = "";
	// 当前存储路径
	private static String picStorePath = "";

	public static String getMacos() {
		return macos;
	}

	/**
	 * 获取当前OS下的存储路径
	 * */
	public static String getPicStorePath() {
		if (getMacos().toLowerCase().contains("windows")) {
			picStorePath = sys_attr_map.get("windows.pic.path");
		} else {
			picStorePath = sys_attr_map.get("linux.pic.path");
		}
		return picStorePath;
	}

	public static void setPicStorePath(String path) {
		picStorePath = path;
	}

	@Override
	public void init() throws ServletException {
		log.info("■■■■■■■■■■■■■■ VAMS system resource is loading ...... ■■■■■■■■■■■■■■");
		macos = System.getProperties().getProperty("os.name"); // 获取操作系统类型
		log.info("当前使用的操作系统:" + macos);

		// 载入系统配置文件
		SYSConfig.load();
		//通过调用重载方法进行加载。以免重复
		reload();
		
		user2CnDeptCode();
		user2DeptCode();
		user2Jybh();
		
		try {
			//收费数据库测试：
			Map<String, Object> map = DAOFactory.getAnyJdbcTemplate("jdbcTemplate1").queryForMap("select sysdate as DT from dual");
			log.info("收费数据库连接检测完毕："+map.get("DT"));
		} catch (Exception e) {
			log.error("收费数据库连接异常！",e);
		}
		
		
		log.info("基础数据初始化完成！");
	}

	
	/**
	 * 重载参数，刷新缓存调用
	 * */
	public static synchronized void reload() {
		init_dept();//加载部门
		init_xzqh();//加载行政区划
		init_ywlx();//加载业务类型
		init_hpzl();//加载号牌种类
		init_paper();//加载纸张类型
		init_dazt();// 初始化档案状态
		init_sys_attr();//加载系统参数
		init_cfgz();//加载存放规则
		init_user();//加载用户
		init_year();//加载年份
		user2CnName();
		init_yc_paper();// 外检纸张扫描
		init_cwlx();//储位类型
		LogInfo.loadProgram(jdbcTemplate);// 载入 日志写入表
		log.info("基础数据重载完成！");
	}

	/****************************************************************************************/

	// ---------------------------------------------行政区划---------------------------------------------------------
	public static Map<String, String> xzqh_map = new LinkedHashMap<String, String>();
	// 行政区划kev-value 对象
	public static Map<String, Kval> xzqh_kv_map = new LinkedHashMap<String, Kval>();

	/*
	 * init行政区划
	 */
	synchronized static boolean init_xzqh() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select code,name from CODESET where type='1' order by id";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String code = map.get("code").toString();
				String name = map.get("name").toString();
				nmap.put(code, name);
				kvmap.put(code, new Kval(code, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		xzqh_map.clear();
		xzqh_map.putAll(nmap);
		xzqh_kv_map.clear();
		xzqh_kv_map.putAll(kvmap);
		return true;
	}

	// ----------------------------------------------业务类型-------------------------------------------------------------------
	// 业务类型Map
	public static Map<String, String> ywlx_map = new LinkedHashMap<String, String>();
	// 业务类型Map
	public static Map<String, Kval> ywlx_kv_map = new LinkedHashMap<String, Kval>();

	/*
	 * init业务类型
	 */
	synchronized static boolean init_ywlx() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select code,name from CODESET where type='2'  order by code";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String code = map.get("code").toString();
				String name = map.get("name").toString();
				nmap.put(code, name);
				kvmap.put(code, new Kval(code, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		ywlx_map.clear();
		ywlx_map.putAll(nmap);
		ywlx_kv_map.clear();
		ywlx_kv_map.putAll(kvmap);
		return true;
	}
	
	// ---------------------------------------------年份-----------------------------------------------------
	public static Map<String, Kval> year_map = new LinkedHashMap<String, Kval>();
	synchronized static boolean init_year() {
		// 刷新后的数据
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select code,name from CODESET where type='year'  order by code";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String code = map.get("code").toString();
				String name = map.get("name").toString();
				kvmap.put(code, new Kval(code,name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		year_map.clear();
		year_map.putAll(kvmap);
		return true;
	}
	
	// ---------------------------------------------号牌种类-----------------------------------------------------
	// 号牌种类Map
	public static Map<String, String> hpzl_map = new LinkedHashMap<String, String>();
	public static Map<String, Kval> hpzl_kv_map = new LinkedHashMap<String, Kval>();

	/*
	 * 号牌种类Map初始化
	 */
	synchronized static boolean init_hpzl() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select code,name from CODESET where type='3' order by id";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String code = map.get("code").toString();
				String name = map.get("name").toString();
				nmap.put(code, name);
				kvmap.put(code, new Kval(code, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		hpzl_map.clear();
		hpzl_map.putAll(nmap);
		hpzl_kv_map.clear();
		hpzl_kv_map.putAll(kvmap);
		return true;
	}

	// --------------------------------------------部门-----------------------------------------------------
	// 机构信息Map
	public static Map<String, String> dept_map = new LinkedHashMap<String, String>();
	public static Map<String, Kval> dept_kv_map = new LinkedHashMap<String, Kval>();

	/*
	 * 初始化机构信息
	 */
	synchronized static boolean init_dept() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select deptcode,deptname from dept";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String deptcode = map.get("deptcode").toString();
				String deptname = map.get("deptname").toString();
				nmap.put(deptcode, deptname);
				kvmap.put(deptcode, new Kval(deptcode, deptname));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		dept_map.clear();
		dept_map.putAll(nmap);
		dept_kv_map.clear();
		dept_kv_map.putAll(kvmap);
		return true;
	}

	// ------------------------------------------档案状态-----------------------------------------------------------
	// 档案状态
	public static Map<String, String> dazt_map = new LinkedHashMap<String, String>();
	static Map<String, Kval> dazt_kv_map = new LinkedHashMap<String, Kval>();
	/* 通过档案状态识别代码来获取档案状态id */
	static Map<String, String> dazt_code_id_map = new LinkedHashMap<String, String>();

	/*
	 * 初始化档案状态map
	 */
	synchronized static boolean init_dazt() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		// 刷新后的数据
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		Map<String, String> cimap = new LinkedHashMap<String, String>();
		try {
			String sql = "select * from arc_state";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String id = map.get("ID").toString();// 状态id
				String name = map.get("STATUS_NAME").toString();// 状态名称
				String code = map.get("STATUS_CODE").toString();// 状态
				nmap.put(id, name);
				kvmap.put(id, new Kval(id, name));
				cimap.put(code, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		dazt_map.clear();
		dazt_map.putAll(nmap);
		dazt_kv_map.clear();
		dazt_kv_map.putAll(kvmap);
		dazt_code_id_map.clear();
		dazt_code_id_map.putAll(cimap);
		return true;
	}

	// --------------------------------------------扫描纸张--------------------------------------------------------
	// 扫描纸张Map
	public static Map<String, String> paper_map = new HashMap<String, String>();
	static Map<String, Kval> paper_kv_map = new HashMap<String, Kval>();

	synchronized static boolean init_paper() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select id,paper_name from paper";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String id = map.get("id").toString();
				String name = map.get("paper_name").toString();
				nmap.put(id, name);
				kvmap.put(id, new Kval(id, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		paper_map.clear();
		paper_map.putAll(nmap);
		paper_kv_map.clear();
		paper_kv_map.putAll(kvmap);
		return true;
	}

	// --------------------------------------------扫描纸张--------------------------------------------------------
	// 外检扫描纸张Map
	public static Map<String, String> yc_paper_map = new HashMap<String, String>();
	static Map<String, Kval> yc_paper_kv_map = new HashMap<String, Kval>();

	synchronized static boolean init_yc_paper() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select TAKECODE,TAKE_NAME from YC_PAPER";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String id = map.get("TAKECODE").toString();
				String name = map.get("TAKE_NAME").toString();
				nmap.put(id, name);
				kvmap.put(id, new Kval(id, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		yc_paper_map.clear();
		yc_paper_map.putAll(nmap);
		yc_paper_kv_map.clear();
		yc_paper_kv_map.putAll(kvmap);
		return true;
	}

	// ----------------------------------------------x系统参数-------------------------------------------------------

	// 系统配置参数Map
	public static Map<String, String> sys_attr_map = new LinkedHashMap<String, String>();
	public static Map<String, Kval> sys_attr_kv_map = new LinkedHashMap<String, Kval>();

	/*
	 * 读取系统设置默认参数
	 */
	synchronized static boolean init_sys_attr() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select attr_code,attrkey from attr";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String code = map.get("ATTR_CODE").toString();
				String key = map.get("ATTRKEY").toString();
				nmap.put(code, key);
				kvmap.put(code, new Kval(code, key));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		sys_attr_map.clear();
		sys_attr_map.putAll(nmap);
		sys_attr_kv_map.clear();
		sys_attr_kv_map.putAll(kvmap);
		return true;
	}
	
	
	// ----------------------------------------------用户信息-------------------------------------------------------

	// 系统配置参数Map
	public static Map<String, String> user_map = new LinkedHashMap<String, String>();
	public static Map<String, Kval> user_kv_map = new LinkedHashMap<String, Kval>();
    
	 
	/*
	 * 读取系统设置默认参数
	 */
	public synchronized static boolean init_user() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		Map<String, Kval> kvmap = new LinkedHashMap<String, Kval>();
		try {
			String sql = "select user_code,user_name from users";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String code = map.get("USER_CODE").toString();
				String key = map.get("USER_NAME").toString();
				nmap.put(code, key);
				kvmap.put(code, new Kval(code, key));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		user_map.clear();
		user_map.putAll(nmap);
		user_kv_map.clear();
		user_kv_map.putAll(kvmap);
		return true;
	}
	
	// ----------------------------------------------存放规则---------------------------------------------------
	// 档案存放规则
	public static Map<String, String> cfgzMap = new LinkedHashMap<String, String>();

	/**
	 * 实物档案 存放规则
	 * 
	 * @author wzm
	 */
	synchronized static boolean init_cfgz() {
		// 刷新后的数据
		Map<String, String> nmap = new LinkedHashMap<String, String>();
		try {
			String sql = "select code,name from CODESET where type='4' order by id";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String code = map.get("code").toString();
				String name = map.get("name").toString();
				nmap.put(code, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		cfgzMap.clear();
		cfgzMap.putAll(nmap);
		return true;
	}

	public static Map<String, String> user2CnNameMap = new HashMap<String, String>();

	private static boolean user2CnName() {
		Map<String, String> tempMap = new HashMap<String, String>();
		try {
			String sql = "select user_code,user_name from USERS";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				tempMap.put(map.get("USER_CODE") + "", map.get("USER_NAME")
						+ "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		user2CnNameMap.clear();
		user2CnNameMap.putAll(tempMap);
		return true;
	}

	/**
	 * 警员编号
	 */
	public static Map<String, String> user2JybhMap = new HashMap<String, String>();

	public synchronized static boolean user2Jybh() {
		Map<String, String> tempMap = new HashMap<String, String>();
		try {
			String sql = "select user_code,SIGN_TYPE from USERS";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				tempMap.put(map.get("USER_CODE") + "", map.get("SIGN_TYPE")
						+ "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		user2JybhMap.clear();
		user2JybhMap.putAll(tempMap);
		return true;
	}

	// -------------------------------------------------------------------------------------------------------------

	// 根据流程和业务类型得到流程名称MAP
	// public static Map<String, String> flownameByFlowYwlxMap = new
	// LinkedHashMap<String, String>();// 反向的map，即key与value对换

	// 系统用户信息统计监听
	// public final static Map<String, ListeneUser> LISTENE_USER_MAP = new
	// HashMap<String, ListeneUser>();

	/**
	 * 根据流程和业务类型得到流程名称MAP eg:{1,QT=其它历史档案扫描, 2,A=注册登记制证岗扫描, 1,A=注册登记受理岗扫描,
	 * 3,A=注册登记审核岗扫描}
	 * 
	 * @return Map<String, String>
	 * 
	 * @author wangwei 2013-7-31
	 * 
	 *         public Map<String, String> flownameByFlowYwlx() { FlowDAO flowDAO
	 *         = DAOFactory.getInstance().getDao("flowDAO"); Map<String, String>
	 *         ywlxIdToCode = StartupServlet.ywlxIdToCode; List<Flow> list =
	 *         flowDAO.selectByExample(null); for (Flow flow : list) {
	 *         flownameByFlowYwlxMap.put( flow.getFlow() + "," +
	 *         ywlxIdToCode.get(flow.getTypeId()), flow.getFlowname()); } return
	 *         flownameByFlowYwlxMap; }
	 */

	public static Map<String, String> user2CnDeptCodeMap = new HashMap<String, String>();
	
	/**
	 * 由用户名得到中文所属机构
	 * 
	 * @return
	 * 
	 * @author WangWei 2014-2-17
	 */

	private static boolean user2CnDeptCode() {
		Map<String, String> tempMap = new HashMap<String, String>();
		try {
			String sql = "select t1.user_code,t2.deptname from users t1,dept t2 where t1.dept_code = t2.deptcode";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				tempMap.put(map.get("USER_CODE").toString(), map
						.get("DEPTNAME").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		user2CnDeptCodeMap.clear();
		user2CnDeptCodeMap.putAll(tempMap);
		return true;
	}
	
	public static Map<String, String> user2DeptCodeMap = new HashMap<String, String>();
	private static boolean user2DeptCode() {
		Map<String, String> tempMap = new HashMap<String, String>();
		try {
			String sql = "select USER_CODE,DEPT_CODE from USERS";
			List list = jdbcTemplate.queryForList(sql);
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				tempMap.put(map.get("USER_CODE") + "", map.get("DEPT_CODE")
						+ "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		user2DeptCodeMap.clear();
		user2DeptCodeMap.putAll(tempMap);
		return true;
	}
	
	//----------------------------------------------------------------------------------------
	// 系统配置参数Map
	public static Map<String, String> cwlx_map = new LinkedHashMap<String, String>();
	public static Map<String, Kval> cwlx_kv_map = new LinkedHashMap<String, Kval>();
	/**
	 * 储位类型
	 * */
	private static boolean init_cwlx(){
		cwlx_map.clear();
		cwlx_kv_map.clear();
		
		cwlx_map.put("1", "室");
		cwlx_map.put("2", "柜");
		cwlx_map.put("3", "列");
		cwlx_map.put("4", "格");
		cwlx_map.put("5", "盒");
		
		cwlx_kv_map.put("1", new Kval("1", "室"));
		cwlx_kv_map.put("2", new Kval("2", "柜"));
		cwlx_kv_map.put("3", new Kval("3", "列"));
		cwlx_kv_map.put("4", new Kval("4", "格"));
		cwlx_kv_map.put("5", new Kval("5", "盒"));
		
		return true;
	}
	
	
	

}
