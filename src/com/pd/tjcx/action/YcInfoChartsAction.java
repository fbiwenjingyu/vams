package com.pd.tjcx.action;

import java.util.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pd.system.framework.BaseAction;
import com.pd.system.startup.SYSLoadManager;
import com.pd.tjcx.service.ArcBaseYcInfoService;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoSuper;
import com.pd.wjyw.service.YcInfoService;


/**
 * 外检图形化统计结果
* @ClassName: YcInfoChartsAction 
* @Description: TODO
* @author zl
* @date 2014-8-5 上午11:10:24 
*
 */
public class YcInfoChartsAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass());
	private YcInfoService ycInfoService;
	private ArcBaseYcInfoService arcBaseYcInfoService;
	private YcInfoExample ycInfoExample = new YcInfoExample();
	private YcInfo ycinfo;
	private String sdate;
	private String edate;

	/**
	 * 根据部门显示外检信息的柱状统计结果
	* @Title: ycInfoByDept_colCharts 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-5
	 */
	public String ycInfoByDept_colCharts(){
		Map map = new HashMap();
		/**
		 * 根据时间统计柱状图显示结果
		 */
		if(sdate!=null && sdate.trim().length()>0){
			map.put("sdate", sdate);
		}
		if(edate!=null && edate.trim().length()>0){
			map.put("edate", edate);
		}
		
		
		List<YcInfoSuper> list = ycInfoService.getYcInfoByDept(map);
		request.setAttribute("list", list);
		request.setAttribute("deptMap", SYSLoadManager.dept_map);
		return "ycInfoByDept_colCharts";
	}
	
	/**
	 * 根据部门显示外检信息的饼状统计结果
	* @Title: ycInfoByDept_colCharts 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-5
	 */
	public String ycInfoByDept_pieCharts(){
		Map map = new HashMap();
		/**
		 * 根据时间统计柱状图显示结果
		 */
		if(sdate!=null && sdate.trim().length()>0){
			map.put("sdate", sdate);
		}
		if(edate!=null && edate.trim().length()>0){
			map.put("edate", edate);
		}
		
		
		List<YcInfoSuper> list = ycInfoService.getYcInfoByDept(map);
		request.setAttribute("list", list);
		request.setAttribute("deptMap", SYSLoadManager.dept_map);
		return "ycInfoByDept_pieCharts";
	}
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public YcInfo getYcinfo() {
		return ycinfo;
	}

	public void setYcinfo(YcInfo ycinfo) {
		this.ycinfo = ycinfo;
	}
	public void setYcInfoService(YcInfoService ycInfoService) {
		this.ycInfoService = ycInfoService;
	}

	public void setArcBaseYcInfoService(ArcBaseYcInfoService arcBaseYcInfoService) {
		this.arcBaseYcInfoService = arcBaseYcInfoService;
	}
	
}


