package com.pd.system.res;

import com.pd.system.startup.DBResource;


/**
 * 档案状态
 * */
public final class ArcStatus {

	/**
	 * 已扫描 ，代码：1000
	 * */
	public final static String YSM = DBResource.getDaztIdByCode("YSM");
	/**
	 * 审核未通过（业务） ，代码：1010
	 * */
	public final static String SHWTG_YW = DBResource.getDaztIdByCode("SHWTG-YW");
	
	/**
	 * 审核通过（业务） ，代码：1020
	 * */
	public final static String SHTG_YW = DBResource.getDaztIdByCode("SHTG-YW");
	
	/**
	 * 已归档 ，代码：1030
	 * */
	public final static String YGD = DBResource.getDaztIdByCode("YGD");
	
	/**
	 * 已删除 ，代码：1040
	 * */
	public final static String YSC = DBResource.getDaztIdByCode("YSC");
	
	/**
	 * 待审核（入库） ，代码：1050
	 * */
	public static final String DSH_RK = DBResource.getDaztIdByCode("DSH-RK");
	/**
	 * 已入库 ，代码：1060
	 * */
	public static final String YRK = DBResource.getDaztIdByCode("YRK");
	
	/**
	 * 待审核（出库） ，代码：1070
	 * */
	public static final String DSH_CK = DBResource.getDaztIdByCode("DSH-CK");;

	/**
	 * 已出库 ，代码：1080
	 * */
	public static final String YCK = DBResource.getDaztIdByCode("YCK");
	
	/**
	 * 审核未通过（入库） ，代码：1090
	 * */
	public static final String SHWTG_RK = DBResource.getDaztIdByCode("SHWTG-RK");
	
	/**
	 * 审核未通过（出库） ，代码：1100
	 * */
	public static final String SHWTG_CK = DBResource.getDaztIdByCode("SHWTG-CK");
	
	/**
	 * 已录入（历史档案） ，代码：1110
	 * */
	public static final String YLR_LSDA = DBResource.getDaztIdByCode("YLR-LSDA");
	
	/**
	 * 档案状态 重扫申请 1120
	 */
	public final static String ARC_CSSQ = DBResource.getDaztIdByCode("ARC-CSSQ");
	
	/**
	 * 档案状态 重扫审核 1130
	 */
	public final static String ARC_CSSH = DBResource.getDaztIdByCode("ARC-CSSH");
	
	/**
	 * 档案状态 已重扫 1140
	 */
	public final static String ARC_YCS = DBResource.getDaztIdByCode("ARC-YCS");
	
	/**
	 * 档案状态 未扫描 1150
	 */
	public final static String WSM = DBResource.getDaztIdByCode("WSM");
}
