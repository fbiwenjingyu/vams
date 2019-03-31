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
 * 通过外检编号查询归档后的档案的扫描页
 * @author ywj
 * 2014-8-1
 */
public class QueryPostArcCommand extends AbstractCommandRequest {
	protected final Log log = LogFactory.getLog(getClass());
	public JdbcTemplate jdbcTemplate = DAOFactory.getDao("jdbcTemplate");
	private ArcBaseInfoService arcBaseInfoService = DAOFactory.getDao("arcBaseInfoService");
	private static final String BUSINESS_ARC = "1";//业务档案
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
		
		//扫描状态 5 初次扫描 6 重扫
		String scanMode = paramMap.get("scanmode");
		
		String ywlxInfo = "";//获取业务类型信息
		if(scanMode.equals("5") && (StringUtil.isEmpty(ywlx) || QXZ.equals(ywlx))){//初扫时没有选择业务类型
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
		/*
		 * 初次扫描
		 */
		if(scanMode == null || "5".equals(scanMode)){
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
				
	
				// 判断是否已经扫描过除身份证以外的档案页
				String scanedSql = "select count(*) from arc_pic_info t where t.paper_id in (select p.id from paper p where p.paper_name not like '%身份证%') and t.xtdabh ='"
						+ wjbh + "' ";
				int countPic = jdbcTemplate.queryForInt(scanedSql);
				if (countPic > 0) {
					return "0503"; //您已经扫描上传过档案，不能再次进行扫描
				}
				
				
				/**
				 * 根据外检编号获取档案
				 */
				List arcBaseInfoByXTDABH = arcBaseInfoService.getArcBaseInfoByXTDABH(wjbh);
				if(null == arcBaseInfoByXTDABH || arcBaseInfoByXTDABH.size() <= 0){
					return "0502";//系统中没有该数据，请检查参数是否有误
				}else{
					ArcBaseInfo arcBaseInfo = (ArcBaseInfo) arcBaseInfoByXTDABH.get(0);
					String dazt = arcBaseInfo.getDazt();
					String arcType = arcBaseInfo.getDalx();
					if(!BUSINESS_ARC.equals(arcType)){
						return "0502";
					}
					if(!ArcStatus.YGD.equals(dazt)){
						return "0510";//请归档后再扫描
					}
				}
							
				header = ObjectParser.getScanXMLHead("1","成功","1");
				foot = ObjectParser.getScanXMLFoot();
				
				//根据业务类型取要扫描的纸张
				if (!StringUtil.isEmpty(ywlx)) {
					GetArcPageCommand cmd = new GetArcPageCommand();
					//paperInfo = cmd.getPaperInfo(ywlx, roleId);
					paperInfo = cmd.getPaperPostInfo(ywlx, roleId);
					String rowNum = String.valueOf(cmd.rowNum);
					header = ObjectParser.getScanXMLHead("1","成功",rowNum);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				//0000报文解析错误或其它错误
				return "0000";
			}
		}
		String repsXml = header + ywlxInfo + paperInfo  + foot;
		return repsXml;
	}
	
}


