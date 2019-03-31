package com.pd.tjcx.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.pd.arc.dao.impl.ArcBaseInfoDAO;
import com.pd.arc.model.ArcBaseInfoSuper;
import com.pd.wjyw.dao.impl.YcInfoDAO;

public class ArcBaseYcInfoService {
	private ArcBaseInfoDAO arcBaseDAO;
	private YcInfoDAO ycInfoDAO;
	public List getWeekCount(){
		String sdate = "";
		Map map = new HashMap();
		
		List list = new ArrayList();
		
		/**
		 *  得到前一周日期
		 */
		for(int i=1;i<8;i++){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -i);   
		    sdate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		    map.put("sdate", sdate);
		    int arcNum = arcBaseDAO.getWeekArcNum(map);
			int ycNum = ycInfoDAO.getYcInfoWeekCount(map);
			ArcBaseInfoSuper entity = new ArcBaseInfoSuper();
			entity.setSmNum(arcNum);
			entity.setWjNum(ycNum);
			entity.setMc(sdate);
			list.add(entity);
			map.clear();
		}

		return list;
	}
	public void setArcBaseDAO(ArcBaseInfoDAO arcBaseDAO) {
		this.arcBaseDAO = arcBaseDAO;
	}
	public void setYcInfoDAO(YcInfoDAO ycInfoDAO) {
		this.ycInfoDAO = ycInfoDAO;
	}
	
}


