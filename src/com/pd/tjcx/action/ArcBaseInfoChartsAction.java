package com.pd.tjcx.action;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;

import com.lowagie.text.pdf.AcroFields;
import com.pd.arc.model.*;
import com.pd.arc.model.ArcBaseInfoExample.Criteria;
import com.pd.arc.service.ArcBaseInfoService;
import com.pd.system.framework.BaseAction;
import com.pd.system.res.ArcStatus;
import com.pd.system.startup.SYSLoadManager;
import com.pd.system.utils.DateTools;
import com.pd.system.utils.StringTools;

public class ArcBaseInfoChartsAction extends BaseAction {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(this.getClass());
	private ArcBaseInfoService arcBaseInfoService;
	
	
	private String sdate;
	private String edate;
	private ArcBaseInfo arcBaseInfo;
	
	/**
	 * 档案查询
	* @Title: arcInfo_query 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-14
	 */
	public String arcInfo_query(){
		try {
			ArcBaseInfoExample example = new ArcBaseInfoExample();
			
			//查询条件拼接
			Criteria ca = example.createCriteria();
			if(null != arcBaseInfo){
				if(StringTools.isNotEmpty(arcBaseInfo.getLsh())){
					ca.andLshLike("%"+arcBaseInfo.getLsh().trim()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getYwlx())){
					ca.andYwlxEqualTo(arcBaseInfo.getYwlx().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getDeptcode())){
					ca.andDeptcodeEqualTo(arcBaseInfo.getDeptcode().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getHpzl())){
					ca.andHpzlEqualTo(arcBaseInfo.getHpzl().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getHphm())){
					ca.andHphmLike("%"+arcBaseInfo.getHphm().trim().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getClsbdh().trim())){
					ca.andClsbdhLike("%"+arcBaseInfo.getClsbdh().trim().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getXtdabh().trim())){
					ca.andXtdabhLike("%"+arcBaseInfo.getXtdabh().trim().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getDazt())){
					ca.andDaztEqualTo(arcBaseInfo.getDazt().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getCwbh().trim())){
					ca.andCwbhLike("%"+arcBaseInfo.getCwbh().trim().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getXh().trim())){
					ca.andXhLike("%"+arcBaseInfo.getXh()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getShrmc())){
					ca.andShrmcLike("%"+arcBaseInfo.getShrmc().trim()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getPc())){
					ca.andPcLike("%"+arcBaseInfo.getPc().trim()+"%");
				}
			}
			if(!StringUtils.isNotEmpty(sdate) && !StringUtils.isNotEmpty(edate) ){
				//Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
				//edate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				//calendar.add(Calendar.MONTH, -1);    //得到前一个月
				//sdate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			}
			if(StringUtils.isNotEmpty(sdate)){
				ca.andCjsjGreaterThanOrEqualTo(sdate);
			}if(StringUtils.isNotEmpty(edate)){
				ca.andCjsjLessThanOrEqualTo(edate);
			}
			example.setOrderByClause("CJSJ DESC");
			
			page = arcBaseInfoService.findArcBaseInfoPageList(index,example);
			List<ArcBaseInfoAndYcInfo> list_data = page.getPagelist();
			getSession().setAttribute("arcBaseInfo", arcBaseInfo);
			request.setAttribute("list_data", list_data);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return "arcInfo_query";
	}
	
	/**
	 * 档案打印导出
	* @Title: arcInfo_export 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-14
	 */
	public String arcInfo_export(){
		try {
			ArcBaseInfoExample example = new ArcBaseInfoExample();
			ArcBaseInfo arcBaseInfo = (ArcBaseInfo) getSession().getAttribute("arcBaseInfo");
			//查询条件拼接
			Criteria ca = example.createCriteria();
			if(null != arcBaseInfo){
				if(StringTools.isNotEmpty(arcBaseInfo.getLsh())){
					ca.andLshLike("%"+arcBaseInfo.getLsh().trim()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getYwlx())){
					ca.andYwlxEqualTo(arcBaseInfo.getYwlx().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getDeptcode())){
					ca.andDeptcodeEqualTo(arcBaseInfo.getDeptcode().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getHpzl())){
					ca.andHpzlEqualTo(arcBaseInfo.getHpzl().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getHphm())){
					ca.andHphmLike("%"+arcBaseInfo.getHphm().trim().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getClsbdh().trim())){
					ca.andClsbdhLike("%"+arcBaseInfo.getClsbdh().trim().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getXtdabh().trim())){
					ca.andXtdabhLike("%"+arcBaseInfo.getXtdabh().trim().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getDazt())){
					ca.andDaztEqualTo(arcBaseInfo.getDazt().trim());
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getCwbh().trim())){
					ca.andCwbhLike("%"+arcBaseInfo.getCwbh().toUpperCase()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getXh().trim())){
					ca.andXhLike("%"+arcBaseInfo.getXh()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getShrmc())){
					ca.andShrmcLike("%"+arcBaseInfo.getShrmc().trim()+"%");
				}
				if(StringTools.isNotEmpty(arcBaseInfo.getPc())){
					ca.andPcLike("%"+arcBaseInfo.getPc().trim()+"%");
				}
			}
			if(!StringUtils.isNotEmpty(sdate) && !StringUtils.isNotEmpty(edate) ){
				Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
				edate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				calendar.add(Calendar.MONTH, -1);    //得到前一个月
				sdate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			}
			if(StringUtils.isNotEmpty(sdate)){
				ca.andCjsjGreaterThanOrEqualTo(sdate);
			}if(StringUtils.isNotEmpty(edate)){
				ca.andCjsjLessThanOrEqualTo(edate);
			}
			example.setOrderByClause("CJSJ DESC");

			List<ArcBaseInfo> list_data = arcBaseInfoService.selectByExample(example);
			getSession().setAttribute("arcBaseInfo", arcBaseInfo);
			request.setAttribute("list_data", list_data);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return "arcInfo_export";
	}
	
	/**
	 * 柱状线型统计扫描，审核归档入库的月档案数量
	* @Title: work_colLineCharts 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-14
	 */
	public String work_colLineCharts(){
		try{
			Map map = new HashMap();
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year = request.getParameter("year");
			/**
			 * 年份为空则查询当前年份
			 */
			if(null == year || year.length()==0){
				year = df.format(new Date());	
			}
			map.put("year", year);
			List<ArcBaseInfoSuper> list = arcBaseInfoService.getArcNum(map);
			request.setAttribute("year",year);
			request.setAttribute("list", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return  "work_colLineCharts";
	}
	/**
	 * 线型统计审核归档工作量
	 */
	public String workList_LineCharts(){
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
		List<ArcBaseInfoSuper> list = arcBaseInfoService.getArcCheckNum(map);
		request.setAttribute("list", list);
		return "workList_LineCharts";
	}
	
	/**
	 *柱状图统计审核归档工作量
	 */
	public String workList_colCharts(){
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
		List<ArcBaseInfoSuper> list = arcBaseInfoService.getArcCheckNum(map);
		request.setAttribute("list", list);
		return "workList_colCharts";
	}
	
	/**
	 *饼状图统计审核归档工作量
	 */
	public String workList_pieCharts(){
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
		List<ArcBaseInfoSuper> list = arcBaseInfoService.getArcCheckNum(map);
		request.setAttribute("list", list);
		return "workList_pieCharts";
	}
	
	/**
	 * 业务统计
	* @Title: ywtjList 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-11
	 */
	public String ywtjList(){
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		ArcBaseInfoExample.Criteria criteria = example.createCriteria();
        
		if(sdate==null && edate == null){
			Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
	        calendar.add(Calendar.DATE, -1);    //得到前一天
	        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			sdate = dayBefore;
			edate = dayBefore;
		}
		
		/**
		 * 查询一个月已归档未入库的档案数量
		 */
		criteria.andDaztEqualTo(ArcStatus.YGD);
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.MONTH, -1);    //得到前一个月
		String lastMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        criteria.andShsjLessThanOrEqualTo(lastMonth);
		Integer count_last = arcBaseInfoService.countByExample(example);
		request.setAttribute("count_last", count_last);
		example.clear();
		
		/**
		 * 查询审核通过的记录审核通过但是没有归档入库,档案状态不在已扫描 ，审核未通过（业务），已录入（历史档案）
		 */
		criteria = example.createCriteria();
		List listsh = new ArrayList();
		listsh.add(ArcStatus.YSM);
		listsh.add(ArcStatus.SHWTG_YW);
		listsh.add(ArcStatus.YLR_LSDA);
		criteria.andDaztNotIn(listsh);
		if(sdate!=null && sdate.trim().length()>0){
			criteria.andShsjGreaterThanOrEqualTo(sdate);
		}
		if(edate!=null && edate.trim().length()>0){
			criteria.andShsjLessThanOrEqualTo(edate);
		}
		Integer count_sh = arcBaseInfoService.countByExample(example);
		request.setAttribute("count_sh", count_sh);
		example.clear();
		
		/**
		 * 查询已归档的档案数量,档案状态不在已扫描 ，审核未通过（业务），审核通过（业务）,已录入（历史档案）
		 */
		criteria = example.createCriteria();
		List list = new ArrayList();
		list.add(ArcStatus.YSM);
		list.add(ArcStatus.SHWTG_YW);
		list.add(ArcStatus.SHTG_YW);
		list.add(ArcStatus.YLR_LSDA);
		criteria.andDaztNotIn(list);
		if(sdate!=null && sdate.trim().length()>0){
			criteria.andGdsjGreaterThanOrEqualTo(sdate);
		}
		if(edate!=null && edate.trim().length()>0){
			criteria.andGdsjLessThanOrEqualTo(edate);
		}
		Integer count_gd = arcBaseInfoService.countByExample(example);
		request.setAttribute("count_gd", count_gd);
		example.clear();
		
		/**
		 * 查询已归档的档案未入库的档案记录
		 */
		criteria = example.createCriteria();
		criteria.andDaztEqualTo(ArcStatus.YGD);
		if(sdate!=null && sdate.trim().length()>0){
			criteria.andGdsjGreaterThanOrEqualTo(sdate);
		}
		if(edate!=null && edate.trim().length()>0){
			criteria.andGdsjLessThanOrEqualTo(edate);
		}
		List<ArcBaseInfo> list_gd = arcBaseInfoService.selectByExample(example);
		request.setAttribute("list_gd", list_gd);
		example.clear();
		
		/**
		 * 查询已入库的数量
		 */
		criteria = example.createCriteria();
		criteria.andDaztEqualTo(ArcStatus.YRK);
		if(sdate!=null && sdate.trim().length()>0){
			criteria.andBjrqGreaterThanOrEqualTo(sdate);
		}
		if(edate!=null && edate.trim().length()>0){
			criteria.andBjrqLessThanOrEqualTo(edate);
		}
		Integer count_rk = arcBaseInfoService.countByExample(example);
		request.setAttribute("count_rk", count_rk);
		example.clear();
		
		request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
		return "ywtjList";
	}
	
	/**
	 * 统计档案已归档一个月仍未入库的档案详细信息
	* @Title: ywtjLast 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-11
	 */
	public String ywtjLast(){
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		ArcBaseInfoExample.Criteria criteria = example.createCriteria();
		/**
		 * 查询一个月已归档未入库的档案数量
		 */
		criteria.andDaztEqualTo(ArcStatus.YGD);
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		calendar.add(Calendar.MONTH, -1);    //得到前一个月
		edate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        criteria.andShsjLessThanOrEqualTo(edate);
		List list_last = arcBaseInfoService.selectByExample(example);
		request.setAttribute("list_last", list_last);
		request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
		return "ywtjLast";
	}
	public void setArcBaseInfoService(ArcBaseInfoService arcBaseInfoService) {
		this.arcBaseInfoService = arcBaseInfoService;
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

	public ArcBaseInfo getArcBaseInfo() {
		return arcBaseInfo;
	}

	public void setArcBaseInfo(ArcBaseInfo arcBaseInfo) {
		this.arcBaseInfo = arcBaseInfo;
	}
	
}


