package com.pd.cwgl.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcBaseInfoExample.Criteria;
import com.pd.cwgl.service.StoQueryService;
import com.pd.cwgl.utils.CwglConsts;
import com.pd.system.framework.BaseAction;
import com.pd.system.framework.QueryCriteria;

/**
 * 库房查询
 * */
public class StoQueryAction extends BaseAction {

	private Logger logger = Logger.getLogger(this.getClass());

	private StoQueryService stoQueryService;

	private ArcBaseInfo info;

	/**
	 * 查询列表
	 * */
	public String list() {
		try {
			ArcBaseInfoExample example = new ArcBaseInfoExample();
			Map caseMap = QueryCriteria.getCaseMap();
			//创建人不区分大小写
			caseMap.put("cjr", QueryCriteria.NoCase);
			Criteria criteria = (Criteria) QueryCriteria
					.checkAllStringByCaseByEquals(info,
							example.createCriteria(), fuzzy, caseMap);
			// 查询有效储位的数据，储位编号不为空
			criteria.andCwyxEqualTo(CwglConsts.ENABLE).andCwbhIsNotNull();
			page = stoQueryService.findByPage(index, example);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("库房档案查询异常", e);
			return ERROR("库房档案查询异常", e.getMessage());
		}
		return "list";
	}

	// =================================================
	public void setStoQueryService(StoQueryService stoQueryService) {
		this.stoQueryService = stoQueryService;
	}

	public ArcBaseInfo getInfo() {
		return info;
	}

	public void setInfo(ArcBaseInfo info) {
		this.info = info;
	}

}
