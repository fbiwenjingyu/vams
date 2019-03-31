package com.pd.webservice.scan.commands;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcPicInfo;
import com.pd.arc.service.ArcBaseInfoService;
import com.pd.system.framework.DAOFactory;
import com.pd.system.res.ArcStatus;
import com.pd.webservice.util.ObjectParser;
import com.pd.webservice.util.StringUtil;

/**
 * 扫描客户端接口
 * 通过外检编号查询业务档案
 * @author ywj
 * 2014-8-1
 */
public class QueryBusinessArcCommandV2 extends AbstractCommandRequest {
	protected final Log log = LogFactory.getLog(getClass());
	public JdbcTemplate jdbcTemplate = DAOFactory.getDao("jdbcTemplate");
	private ArcBaseInfoService arcBaseInfoService = DAOFactory.getDao("arcBaseInfoService");
	private static final String BUSINESS_ARC = "1";//业务档案
	private static final String HISTORY_ARC = "2";//历史档案
	private static final String QXZ = "请";//请选择下列框
	@Override
	public String request(String requestXml) {
		paramMap = ObjectParser.getParam(requestXml);
		String wjbh = paramMap.get("wjbh");//外检编号，即系统档案编号
		if(StringUtils.isNotEmpty(wjbh)){
			wjbh = wjbh.toUpperCase();
		}
		String usercode = paramMap.get("usercode");
		String roleId = paramMap.get("roleid");
		String ywlx = paramMap.get("ywlx");
		String callNum = paramMap.get("callnum");
		
		//扫描状态 0 初次扫描  1 重扫
		String scanMode = paramMap.get("scanmode");
		
		String ywlxInfo = "";//获取业务类型信息
		if(scanMode.equals("0") && (StringUtil.isEmpty(ywlx) || QXZ.equals(ywlx))){//初扫时没有选择业务类型
			GetYwlxCommand command= new GetYwlxCommand();
			ywlxInfo = command.getYwlxByXtdabhRespXML(wjbh);
			ywlx = command.getYwlxByXtdabh(wjbh);
		}
		
		
		
		if (StringUtil.isEmpty(wjbh) || StringUtil.isEmpty(roleId)) {
			//必要参数不能为空
			return "0001";
		}
		
		if(StringUtil.isEmpty(ywlx)){
			return "0509";
		}
		
		String paperInfo = "";
		String scaned = "";
		/*
		 * 初次扫描
		 */
		if(scanMode == null || "0".equals(scanMode)){
			try {		
				/*
				 * 外检编号是否存在于外检表中 
				 */
				String ckWjbh1 = "select count(*) from YC_INFO where XTDABH='"+wjbh+"' ";
				log.info("wjbhSql= " + ckWjbh1);
				int checkCount = jdbcTemplate.queryForInt(ckWjbh1);
				if (checkCount <= 0) {
					return "0502";//系统中没有该数据，请检查参数是否有误
				}
				
				// 判断数据的唯一性
				String wjbhSql = "select count(*) from ARC_BASE_INFO " +
                                "where XTDABH = '" + wjbh + "' " + "and YWLX = '" + ywlx + "' ";
				log.info("wjbhSql= " + wjbhSql);
				int countNum = jdbcTemplate.queryForInt(wjbhSql);
				if (countNum > 0) {
					//对于同一外检编号，如果已扫描上传过该业务类型 则给出提示 该业务类型已扫描
					String scanedSql = "select count(*) FROM ARC_PIC_INFO WHERE XTDABH ='"+wjbh+"' "+ "and YWLX = '" + ywlx + "' ";
					int countPic = jdbcTemplate.queryForInt(scanedSql);
					if (countPic > 0) {
						//TODO
						//return "0503";
					}
				}
				
				/**
				 * 根据外检编号获取档案
				 */
				List arcBaseInfoByXTDABH = arcBaseInfoService.getArcBaseInfoByXTDABH(wjbh);
				if(null == arcBaseInfoByXTDABH || arcBaseInfoByXTDABH.size() <= 0){
					//return "0502";//系统中没有该数据，请检查参数是否有误
				}else{
					ArcBaseInfo arcBaseInfo = (ArcBaseInfo) arcBaseInfoByXTDABH.get(0);
					String dazt = arcBaseInfo.getDazt();
					String arcType = arcBaseInfo.getDalx();
					if(!BUSINESS_ARC.equals(arcType)){
						return "0502";
					}
					if(ArcStatus.SHWTG_YW.equals(dazt) || ArcStatus.SHTG_YW.equals(dazt) || ArcStatus.YGD.equals(dazt) || ArcStatus.YSC.equals(dazt) ||ArcStatus.DSH_RK.equals(dazt) || ArcStatus.YRK.equals(dazt) || ArcStatus.DSH_CK.equals(dazt) || ArcStatus.YCK.equals(dazt) || ArcStatus.SHWTG_RK.equals(dazt) || ArcStatus.SHWTG_CK.equals(dazt) || ArcStatus.ARC_CSSQ.equals(dazt) ||ArcStatus.ARC_CSSH.equals(dazt) || ArcStatus.ARC_YCS.equals(dazt)){
						return "0505";//档案已审核，不能再扫描
					}
				/*	if(ArcStatus.YGD.equals(dazt)){//归档之后再扫描，即先办业务后扫描
						List<ArcPicInfo> picList = arcBaseInfoService.getPicInfoByXTDABH(wjbh);
						if(picList != null && picList.size() > 0){
							return "0503"; //已经扫描上传过档案，不能再次进行扫描
						}
					}*/
				}
							
				header = ObjectParser.getScanXMLHead("1","成功","1");
				foot = ObjectParser.getScanXMLFoot();
				
				//根据业务类型取要扫描的纸张
				if (!StringUtil.isEmpty(ywlx)) {
					GetArcPageCommand cmd = new GetArcPageCommand();
					paperInfo = cmd.getPaperInfo(ywlx, roleId);
					String rowNum = String.valueOf(cmd.rowNum);
					header = ObjectParser.getScanXMLHead("1","成功",rowNum);
				}
				QueryScanedCommand qScaned = new QueryScanedCommand();
				scaned = qScaned.getScanedXml(wjbh);
				
			} catch (Exception e) {
				e.printStackTrace();
				//0000报文解析错误或其它错误
				return "0000";
			}
		}else if("1".equals(scanMode)){
			/*
			 * 
			 * 重扫
			 * 
			 */
		 try {
			List arcBaseInfoByXTDABH = arcBaseInfoService.getArcBaseInfoByXTDABHAndDALX(wjbh,BUSINESS_ARC);
			if(null == arcBaseInfoByXTDABH || arcBaseInfoByXTDABH.size() <= 0){
				return "0502";//系统中没有该数据，请检查参数是否有误
			}else{
				ArcBaseInfo arcBaseInfo = (ArcBaseInfo) arcBaseInfoByXTDABH.get(0);
				String dazt = arcBaseInfo.getDazt();
				if(ArcStatus.SHWTG_YW.equals(dazt) || ArcStatus.ARC_CSSH.equals(dazt)){//归档前审核不通过才可以重扫，归档后重扫审核通过后才可以重扫
					ywlx = arcBaseInfo.getYwlx();
					if (!StringUtil.isEmpty(ywlx)) {
						GetArcPageCommand cmd = new GetArcPageCommand();
						paperInfo = cmd.getReScanPaperInfoByGdq(wjbh, ywlx, roleId);
						String rowNum = String.valueOf(cmd.rowNum);
						header = ObjectParser.getScanXMLHead("1","成功",rowNum);
						foot = ObjectParser.getScanXMLFoot();
					}
				}
			}
		   }catch(Exception e){	
			   e.printStackTrace();
			   //0000报文解析错误或其它错误
			   return "0000";
		    }
		}
		String repsXml = header + ywlxInfo + paperInfo + scaned + foot;
		return repsXml;
	}
	
}


