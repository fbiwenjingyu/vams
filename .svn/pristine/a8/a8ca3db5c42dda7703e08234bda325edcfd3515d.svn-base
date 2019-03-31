package com.pd.swgl.utils;

import com.pd.swgl.model.ArcStorageIn;
import com.pd.swgl.model.ArcStorageInExample.Criteria;
import com.pd.swgl.model.ArcStorageOut;
import com.pd.system.framework.QueryCriteria;

public class SwglQuery{


	/**
	 * 【库存表】模糊与精确条件过滤
	 * 
	 * @param storageIn
	 *            条件值
	 * @param criteria
	 *            查询对象
	 * @param fuzzy
	 *            是否模糊查询（0不模糊1模糊）
	 * */
	public static com.pd.swgl.model.ArcStorageInExample.Criteria fuzzyCheckStorageInAll(
			ArcStorageIn storageIn,
			com.pd.swgl.model.ArcStorageInExample.Criteria criteria, String fuzzy) {

		// 如果查询条件的值为空，直接返回
		if (null == storageIn) {
			return criteria;
		}
		return (Criteria) QueryCriteria.checkAllStringByEquals(storageIn, criteria, QueryCriteria.checkFuzzy(fuzzy));
	}

	

	/**
	 * 【库存表】模糊与精确条件过滤
	 * 
	 * @param storageIn
	 *            条件值
	 * @param criteria
	 *            查询对象
	 * @param fuzzy
	 *            是否模糊查询（0不模糊1模糊）
	 * */
	public static com.pd.swgl.model.ArcStorageOutExample.Criteria fuzzyCheckStorageOutAll(
			ArcStorageOut storageOut,
			com.pd.swgl.model.ArcStorageOutExample.Criteria criteria, String fuzzy) {

		// 如果查询条件的值为空，直接返回
		if (null == storageOut) {
			return criteria;
		}
		return (com.pd.swgl.model.ArcStorageOutExample.Criteria) QueryCriteria.checkAllStringByEquals(storageOut, criteria, QueryCriteria.checkFuzzy(fuzzy));
	}

}
