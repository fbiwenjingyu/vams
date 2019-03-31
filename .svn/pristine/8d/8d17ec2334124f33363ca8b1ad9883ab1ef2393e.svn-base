package com.pd.arc.service;

import java.util.ArrayList;
import java.util.List;

import com.pd.arc.dao.impl.ArcBaseInfoDAO;
import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcBaseInfoExample.Criteria;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.Pagination;

/**
 * 档案综合查询service
 * 
 * @author braveheartwzm
 * */
public class ArcQueryService extends BaseService {

	private ArcBaseInfoDAO arcBaseInfoDAO;

	/**
	 * 分页查询储位信息列表
	 * 
	 * @throws Exception
	 * */
	public Pagination findByPage(int index, ArcBaseInfoExample example)
			throws Exception {
		// example.setOrderByClause(" cjsj desc ");
		return findPageList(index, example, arcBaseInfoDAO);
	}

	/**
	 * 通过条件查询档案
	 * */
	public List<ArcBaseInfo> findListByExample(ArcBaseInfoExample example) {
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 根据条件查询符合的车辆识别代号列表
	 * */
	public List<String> findVinListByExample(ArcBaseInfoExample example) {
		List<ArcBaseInfo> list = arcBaseInfoDAO.selectByExample(example);
		List<String> vins = new ArrayList<String>();
		// 取出车辆识别代号，返回list
		for (int i = 0; i < list.size(); i++) {
			ArcBaseInfo arc = list.get(i);
			String vin = arc.getClsbdh();
			if (null != vin && !"".equals(vin)) {
				vins.add(vin);
			}
		}
		return vins;
	}

	/**
	 * 通过车辆识别代号进行关联查询分页
	 * 
	 * @throws Exception
	 * */
	public Pagination findPageByClsbdh(int index, ArcBaseInfoExample example,
			List<String> vins) throws Exception {
		if (vins.size() != 0) {
			ArcBaseInfoExample vinExample = new ArcBaseInfoExample();
			Criteria criteria = vinExample.createCriteria().andClsbdhIn(vins);
			example.or(criteria);
		}
		return findPageList(index, example, arcBaseInfoDAO);
	}

	public void setArcBaseInfoDAO(ArcBaseInfoDAO arcBaseInfoDAO) {
		this.arcBaseInfoDAO = arcBaseInfoDAO;
	}

}
