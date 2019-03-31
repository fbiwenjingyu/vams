package com.pd.swgl.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.swgl.model.ArcStorageIn;
import com.pd.swgl.model.ArcStorageInExample;
import com.pd.swgl.model.ArcStorageInExample.Criteria;
import com.pd.swgl.service.StorageInService;
import com.pd.swgl.utils.SwglQuery;
import com.pd.system.framework.BaseAction;
import com.pd.system.res.ArcStatus;
import com.pd.system.utils.DateTools;
import com.pd.system.utils.StringTools;

/**
 * 入库action
 * */
public class StorageInAction extends BaseAction {

	private static final long serialVersionUID = 6055108041977329630L;

	private Logger logger = Logger.getLogger(this.getClass());
	private StorageInService storageInService;

	private ArcBaseInfo arcinfo;
	private ArcStorageIn stoin;

	private String type;

	private String applyIds;
	private String applyName;

	// ===============
	private String pch;
	private String sl;
	private String name;
	private String time;

	/**
	 * 进入预入库表单
	 * */
	public String form() {
		return "form";
	}

	/**
	 * 查询档案是否可以预入库
	 * */
	public String findArc() {
		Object[] obj = new Object[2];
		try {
			List<ArcBaseInfo> list = new ArrayList<ArcBaseInfo>(0);
			// 按照系统档案编号查询
			if ("xtdabh".equals(type)) {
				list = storageInService.findAllowInByXtdabh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（" + applyIds + "）：未查询到可入库的档案";
				}
			} else 
			//按照档案编号
			if ("dabh".equals(type)) {
				list = storageInService.findAllowInByDabh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（档案编号：" + applyIds + "）：未查询到可入库的档案";
				}
			} else 
			//按照流水号	
			if ("lsh".equals(type)) {
				list = storageInService.findAllowInByLsh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（流水号：" + applyIds + "）：未查询到可入库的档案";
				}
			} else 
			//按照储位编号	
			if("cwbh".equals(type)){
				list = storageInService.findAllowInByCwbh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（储位编号：" + applyIds + "）：未查询到可入库的档案";
				}
			}else {
				obj[0] = "0";
				obj[1] = "该档案（" + applyIds + "）：未查询到可入库的档案";
			}
		} catch (Exception e) {
			obj[0] = "0";
			obj[1] = "查询异常，请联系管理员！";
			logger.error("查询可入库档案异常", e);
		}
		return STRUTS_JSON(obj);
	}

	/**
	 * 查询档案详细信息
	 * */
	public String showArc() {
		List<ArcBaseInfo> list = storageInService.findArcByXtdabh(applyIds);
		// 正常
		if (list.size() == 1) {
			arcinfo = list.get(0);
		} else if (list.size() > 1) {
			return ERROR("数据异常：存在重复的档案！");
		} else {
			return ERROR("未查询到该档案！");
		}

		return "showArc";
	}

	/**
	 * 预入库
	 * */
	public String applyIn() {
		Object obj[] = new Object[3];
		List<String> ids = StringTools.getIds(applyIds);
		try {
			obj = storageInService.checkApplyIn(ids, getSessionUser());
		} catch (Exception e) {
			// 档案无法录入
			if (e.getMessage().startsWith("stoins")) {
				String[] str = e.getMessage().split("-");
				obj[0] = "3";
				if (str.length == 2) {
					obj[1] = str[1];
				} else if (str.length == 3) {
					obj[1] = str[1];
					obj[2] = str[2];
				}
			} else {
				obj[0] = "2";
				obj[1] = "处理异常，操作失败，请联系管理员";
				logger.error("预入库异常", e);
			}
		}
		return STRUTS_JSON(obj);
	}

	/**
	 * 打印入库签收单
	 * */
	public String printRkd() {
		Date date = new Date(Long.parseLong(time));
		time = DateTools
				.getFormatDate(date, DateTools.nian_yue_ri_shi_fen_miao);
		return "printRkd";
	}

	/**
	 * 进入审核列表
	 * */
	public String toCheckin() {
		ArcStorageInExample example = new ArcStorageInExample();
		Criteria criteria = SwglQuery.fuzzyCheckStorageInAll(stoin,
				example.createCriteria(), fuzzy);
		// 查询待审核的档案
		criteria.andDaztEqualTo(ArcStatus.DSH_RK);
		try {
			page = storageInService.findStorageInByPage(index, example);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("入库批次审核列表分页查询异常", e);
		}

		return "toCheckin";
	}

	/**
	 * 通过批次号查询，并进入审核入库列表
	 * */
	public String checkList() {
		if (null != pch && !"".equals(pch)) {
			pageList = storageInService.findStorageInByPch(pch);
		} else {
			pageList = new ArrayList(0);
		}
		return "checkList";
	}

	/**
	 * 入库
	 * */
	public String checkin() {

		Object ret[] = new Object[3];
		try {
			// 按批次入库
			if (type.equals("1")) {
				List<ArcStorageIn> list = storageInService.storageInByPc(
						applyIds, getSessionUser());
				ret[0] = "1";
				ret[1] = "入库完成";
				ret[2] = list;
			} else
			// 按系统编号入库
			if (type.equals("2")) {
				List<String> ids = StringTools.getIds(applyIds);
				storageInService.storageInByXtdabh(ids, getSessionUser());
				ret[0] = "1";
				ret[1] = "入库完成";
			}
		} catch (Exception e) {
			logger.error("入库审核处理异常", e);
			if (e.getMessage().startsWith("stoin")) {
				String[] str = e.getMessage().split("-");
				ret[0] = "3";
				ret[1] = str[1];
				ret[2] = str[2];
			} else {
				ret[0] = "2";
				ret[1] = "入库异常，操作操作失败";
			}
		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 退回预入库档案
	 * */
	public String backin() {

		Object ret[] = new Object[3];
		try {
			// 按批次退回
			if (type.equals("1")) {
				List<ArcStorageIn> list = storageInService.backInByPc(applyIds);
				ret[0] = "1";
				ret[1] = "退回完成";
				ret[2] = list;
			} else
			// 按系统编号退回
			if (type.equals("2")) {
				List<String> ids = StringTools.getIds(applyIds);
				storageInService.backInByXtdabh(ids);
				ret[0] = "1";
				ret[1] = "退回完成";
			}
		} catch (Exception e) {
			logger.error("入库退回处理异常", e);
			if (e.getMessage().startsWith("stoin")) {
				String[] str = e.getMessage().split("-");
				ret[0] = "3";
				ret[1] = str[1];
				ret[2] = str[2];
			} else {
				ret[0] = "2";
				ret[1] = "入库退回异常，操作失败";
			}
		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 入库档案查询
	 * */
	public String inlist() {
		ArcStorageInExample example = new ArcStorageInExample();
		Criteria criteria = SwglQuery.fuzzyCheckStorageInAll(stoin,
				example.createCriteria(), fuzzy);
		List<String> rktj=new ArrayList<String>(2);
		rktj.add(ArcStatus.YRK);
		rktj.add(ArcStatus.DSH_RK);
		criteria.andDaztIn(rktj);
		try {
			page = storageInService.findStorageInByPage(index, example);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("入库档案查询异常", e);
		}
		return "inlist";
	}

	/**
	 * 查看已入库&待审核档案
	 * */
	public String showInArc() {

		List<ArcBaseInfo> list = storageInService.findArcByXtdabh(applyIds);
		List<ArcStorageIn> listin = storageInService
				.findStorageInByXtdabh(applyIds);
		// 正常
		if (list.size() == 1 && listin.size() == 1) {
			arcinfo = list.get(0);
			stoin = listin.get(0);
		} else if (list.size() == 0) {
			return ERROR("未查询到该档案！");
		} else {
			return ERROR("数据异常！");
		}

		return "showInArc";
	}

	// ===========================================================

	public void setStorageInService(StorageInService storageInService) {
		this.storageInService = storageInService;
	}

	public ArcBaseInfo getArcinfo() {
		return arcinfo;
	}

	public void setArcinfo(ArcBaseInfo arcinfo) {
		this.arcinfo = arcinfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplyIds() {
		return applyIds;
	}

	public void setApplyIds(String applyIds) {
		this.applyIds = applyIds;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArcStorageIn getStoin() {
		return stoin;
	}

	public void setStoin(ArcStorageIn stoin) {
		this.stoin = stoin;
	}

}
