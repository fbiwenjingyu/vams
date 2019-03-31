package com.pd.webservice.scan.commands;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.service.ArcBaseInfoService;
import com.pd.system.framework.DAOFactory;
import com.pd.system.startup.SYSLoadManager;
import com.pd.webservice.util.ObjectParser;
import com.pd.webservice.util.StringUtil;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.service.YcInfoService;

/**
 * 获取业务类型
 * @author ywj
 * 2014-8-1
 */
public class GetYwlxCommand extends AbstractCommandRequest {

	protected final Log log = LogFactory.getLog(getClass());
	private ArcBaseInfoService arcBaseInfoService = DAOFactory.getDao("arcBaseInfoService");
	private YcInfoService ycInfoService = DAOFactory.getDao("ycInfoService");
	@Override
	public String request(String requestXml) {
		System.out.println(SYSLoadManager.getPicStorePath());
		paramMap = ObjectParser.getParam(requestXml);
		String xtdabh = paramMap.get("xtdabh");
		if (StringUtil.isEmpty(xtdabh)) {
			//必要参数不能为空
			return "0001";
		}
		String ywlx = null;
		List<YcInfo> ycInfoList = ycInfoService.findYcInfoByXtdabh(xtdabh);
		if(ycInfoList != null && ycInfoList.size() > 0){
			YcInfo ycInfo = ycInfoList.get(0);
			ywlx = ycInfo.getYwlx();
		}
		
		if(StringUtils.isEmpty(ywlx)){
			List<ArcBaseInfo> arcBaseInfoList = arcBaseInfoService.getArcBaseInfoByXTDABH(xtdabh);
			if(arcBaseInfoList != null && arcBaseInfoList.size() >0){
				ArcBaseInfo arcBaseInfo = arcBaseInfoList.get(0);
				ywlx = arcBaseInfo.getYwlx();
			}
		}
			
		respXml.append("<ywlx>" + ywlx + "</ywlx>");
		header = ObjectParser.getScanXMLHead("1","成功","1");
		foot = ObjectParser.getScanXMLFoot();
		return header + respXml + foot;
	}
	
	public String getYwlxByXtdabhRespXML(String xtdabh){
		String ywlx = null;
		List<YcInfo> ycInfoList = ycInfoService.findYcInfoByXtdabh(xtdabh);
		if(ycInfoList != null && ycInfoList.size() > 0){
			YcInfo ycInfo = ycInfoList.get(0);
			ywlx = ycInfo.getYwlx();
		}
		
		if(StringUtils.isEmpty(ywlx)){
			List<ArcBaseInfo> arcBaseInfoList = arcBaseInfoService.getArcBaseInfoByXTDABH(xtdabh);
			if(arcBaseInfoList != null && arcBaseInfoList.size() >0){
				ArcBaseInfo arcBaseInfo = arcBaseInfoList.get(0);
				ywlx = arcBaseInfo.getYwlx();
			}
		}
		respXml.append("<ywlx>" + ywlx + "</ywlx>");
		return respXml.toString();
	}
	
	public String getYwlxByXtdabh(String xtdabh){
		String ywlx = null;
		List<YcInfo> ycInfoList = ycInfoService.findYcInfoByXtdabh(xtdabh);
		if(ycInfoList != null && ycInfoList.size() > 0){
			YcInfo ycInfo = ycInfoList.get(0);
			ywlx = ycInfo.getYwlx();
		}
		
		if(StringUtils.isEmpty(ywlx)){
			List<ArcBaseInfo> arcBaseInfoList = arcBaseInfoService.getArcBaseInfoByXTDABH(xtdabh);
			if(arcBaseInfoList != null && arcBaseInfoList.size() >0){
				ArcBaseInfo arcBaseInfo = arcBaseInfoList.get(0);
				ywlx = arcBaseInfo.getYwlx();
			}
		}
	    return ywlx;
	}
	
	public static void main(String[] args) {
		String str =  "<?xml version='1.0' encoding='UTF-8'?><request><header><commandName>getYwlx</commandName></header><parameter><xtdabh>111222<xtdabh/></parameter></request>";
		System.out.println("str=" + new GetYwlxCommand().request(str));
	}
}
