package com.pd.arc.service;

import com.pd.arc.dao.impl.ArcStatusRecordDAO;
import com.pd.arc.model.ArcStatusRecord;
import com.pd.system.framework.BaseService;
import com.pd.system.utils.StringTools;

/**
 * 档案状态记录service
 * */
public class ArcStatusRecordService extends BaseService {

	private ArcStatusRecordDAO arcStatusRecordDAO;

	
	/**
	 * 保存档案状态记录
	 * */
	public void saveArcStatusRecord(ArcStatusRecord record) throws Exception {

		if (null == record) {
			throw new RuntimeException("档案状态记录对象不能为空");
		}

		if (null == record.getId()) {
			record.setId(StringTools.getUUID());
		}
		
		arcStatusRecordDAO.insert(record);
	}

	
	//================================================
	public void setArcStatusRecordDAO(ArcStatusRecordDAO arcStatusRecordDAO) {
		this.arcStatusRecordDAO = arcStatusRecordDAO;
	}

}
