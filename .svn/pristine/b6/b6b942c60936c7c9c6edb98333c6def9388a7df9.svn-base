package com.pd.arc.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcBaseInfoExample.Criteria;
import com.pd.arc.service.ArcQueryService;
import com.pd.system.framework.BaseAction;
import com.pd.system.framework.QueryCriteria;
import com.pd.system.utils.DateTools;

/**
 * 档案综合查询
 * 
 * @author braveheartwzm
 * */
public class ArcQueryAction extends BaseAction {

	private static final long serialVersionUID = -4893531274282690035L;

	private Logger logger = Logger.getLogger(this.getClass());

	private ArcQueryService arcQueryService;

	private ArcBaseInfo info;

	// 开始时间
	private String sdate;
	// 结束时间
	private String edate;

	/**
	 * 综合查询列表
	 * */
	public String list() {
		try {
			ArcBaseInfoExample example = new ArcBaseInfoExample();
			Map caseMap = QueryCriteria.getCaseMap();
			// 创建人不区分大小写
			caseMap.put("cjr", QueryCriteria.NoCase);
			Criteria criteria = (Criteria) QueryCriteria
					.checkAllStringByCaseByEquals(info,
							example.createCriteria(), fuzzy, caseMap);
			//开始时间
			if (null != sdate&&!"".equals(sdate.trim())) {
//				Date sd=DateTools.toDate(sdate, DateTools.yyyy_MM_dd);
				criteria.andCjsjGreaterThanOrEqualTo(sdate);
			}
			//结束时间
			if(null != edate&&!"".equals(edate.trim())){
//				Date ed=DateTools.toDate(edate, DateTools.yyyy_MM_dd);
				criteria.andCjsjLessThanOrEqualTo(edate);
			}
			
			page = arcQueryService.findByPage(index, example);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("档案综合查询异常", e);
			return ERROR("档案综合查询异常", e.getMessage());
		}
		return "list";
	}

	/**
	 * VIN关联查询
	 * */
	public String cqVin() {
		try {
			ArcBaseInfoExample example = new ArcBaseInfoExample();
			Criteria criteria = (Criteria) QueryCriteria
					.checkAllStringByEquals(info, example.createCriteria(),
							fuzzy);
			List<String> vins = new ArrayList<String>(0);
			// 如果存在条件
			if (criteria.isValid()) {
				// 查询符合条件的车辆识别代号
				vins = arcQueryService.findVinListByExample(example);
			}
			page = arcQueryService.findPageByClsbdh(index, example, vins);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("档案关联查询异常", e);
			return ERROR("档案关联查询异常", e.getMessage());
		}
		return "cqVin";
	}

	// =============================================
	public void setArcQueryService(ArcQueryService arcQueryService) {
		this.arcQueryService = arcQueryService;
	}

	public ArcBaseInfo getInfo() {
		return info;
	}

	public void setInfo(ArcBaseInfo info) {
		this.info = info;
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

}
