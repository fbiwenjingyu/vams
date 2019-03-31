package com.pd.wjpda.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pd.wjpda.model.WjpdaVO;

public class WjpdaDAOImpl extends SqlMapClientDaoSupport implements WjpdaDAO {

	public WjpdaDAOImpl() {
		super();
	}

	/**
	 * 通过相关条没有上传成功的信息
	 * @param wjpdaVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WjpdaVO> findPicInfos(WjpdaVO wjpdaVO) {
		List<WjpdaVO> list = getSqlMapClientTemplate().queryForList("Wjpda.findPicInfos",wjpdaVO);
		return list;
	}

	/**
	 * 通过相关条件查询需要拍摄的照片
	 * @param wjpdaVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WjpdaVO> getNeedTakePic(WjpdaVO wjpdaVO) {
		List<WjpdaVO> list = getSqlMapClientTemplate().queryForList("Wjpda.getNeedTakePic",wjpdaVO);
		return list;
	}
	
	
	
	
}
