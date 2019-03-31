package com.pd.system.res;

import java.util.Date;

import com.pd.arc.model.ArcStatusRecord;
import com.pd.arc.service.ArcStatusRecordService;
import com.pd.system.framework.DAOFactory;
import com.pd.system.utils.StringTools;

/**
 * 档案状态操作记录
 * */
public class ArcStatusRecordOperator {

	private static ArcStatusRecordService arcStatusRecordService = DAOFactory
			.getService(ArcStatusRecordService.class);

	/**
	 * 记录档案状态操作记录
	 * @param xtdabh 系统档案编号
	 * @param czrid 操作人id
	 * @param czrmc 操作人姓名
	 * @param czsj 操作时间
	 * @param dazt 档案状态code
	 * @param bz 备注
	 * */
	public static void recordArcStatus(String xtdabh, String czrid,
			String czrmc, Date czsj, String dazt, String bz) throws Exception {
		
		ArcStatusRecord record = new ArcStatusRecord();
		record.setId(StringTools.getUUID());
		record.setXtdabh(xtdabh);
		record.setCzr(czrid);
		record.setCzrmc(czrmc);
		record.setCzsj(czsj);
		record.setStatusCode(dazt);
		record.setBz(bz);
		arcStatusRecordService.saveArcStatusRecord(record);
	}

}
