package com.pd.webservice.pub;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcPicInfo;
import com.pd.arc.service.ArcBaseInfoService;
import com.pd.system.framework.BaseAction;
import com.pd.webservice.pub.vo.Pic;
import com.pd.webservice.pub.vo.VehBaseInfo;
import com.pd.webservice.util.BeanUtil;
import com.pd.webservice.util.ResultUtils;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.service.YcInfoPicService;
import com.pd.wjyw.service.YcInfoService;

public class PubAction extends BaseAction{
	private ArcBaseInfoService arcBaseInfoService;
	private YcInfoPicService ycInfoPicService;
	private String hphm;
	
	public String getBaseInfoByHpnm() {
		Map<String, Object> map = new HashMap<String, Object>();
		String status = null;
		try {
			if (StringUtils.isNotEmpty(hphm)) {
				//通过号牌号码查询出业务类型为注册登记（代码为A）的档案的基本信息和外检照片
				List<ArcBaseInfo> list =  arcBaseInfoService.getArcBaseInfoByHphmAndYwlx(hphm,"A");
				if(list != null && list.size() > 0){
					status = "1";
					ArcBaseInfo arcBaseInfo = list.get(0);
					VehBaseInfo baseInfo = new VehBaseInfo();
					BeanUtil.copyProperties(arcBaseInfo,baseInfo);
					map.put("baseInfo", baseInfo);//档案基本信息
					List<YcInfoPic> picList = ycInfoPicService.getPicsByXtdabh(arcBaseInfo.getXtdabh());
					if(picList != null && picList.size() > 0){
						List<Pic> pics = BeanUtil.convert(picList);
						map.put("picList", pics);//外检照片信息
					}
				} else {
					status = "null";
				}
			} else {
				status = "0";
			}
			map.put("status", status);
			ResultUtils.toJson(response, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public String getHphm() {
		return hphm;
	}
	
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public void setArcBaseInfoService(ArcBaseInfoService arcBaseInfoService) {
		this.arcBaseInfoService = arcBaseInfoService;
	}

	public void setYcInfoPicService(YcInfoPicService ycInfoPicService) {
		this.ycInfoPicService = ycInfoPicService;
	}
	
	

}
