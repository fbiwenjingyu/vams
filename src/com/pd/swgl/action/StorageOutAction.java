package com.pd.swgl.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.swgl.model.ArcStorageIn;
import com.pd.swgl.model.ArcStorageOut;
import com.pd.swgl.model.ArcStorageOutExample;
import com.pd.swgl.model.ArcStorageOutExample.Criteria;
import com.pd.swgl.service.StorageOutService;
import com.pd.swgl.utils.SwglQuery;
import com.pd.system.framework.BaseAction;
import com.pd.system.res.ArcStatus;
import com.pd.system.utils.DateTools;
import com.pd.system.utils.StringTools;

public class StorageOutAction extends BaseAction {

	private StorageOutService storageOutService;
	private Logger logger = Logger.getLogger(this.getClass());

	private ArcStorageOut stoout;
	private ArcBaseInfo arcinfo;
	private ArcStorageIn storageIn;

	private String type;

	private String applyIds;
	private String applyName;

	// ===============
	private String pch;
	private String sl;
	private String name;
	private String time;

	/**
	 * 进入预出库表单
	 * */
	public String form() {
		return "form";
	}

	/**
	 * 查询档案是否可以预出库
	 * */
	public String findArc() {
		Object[] obj = new Object[2];
		try {
			List<ArcBaseInfo> list = new ArrayList<ArcBaseInfo>(0);
			// 按照系统档案编号查询
			if ("xtdabh".equals(type)) {
				list = storageOutService.findAllowOutByXtdabh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（" + applyIds + "）：未查询到可出库的档案";
				}
			} else if ("dabh".equals(type)) {
				list = storageOutService.findAllowOutByDabh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（" + applyIds + "）：未查询到可出库的档案";
				}
			} else if ("lsh".equals(type)) {
				list = storageOutService.findAllowOutByLsh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（" + applyIds + "）：未查询到可出库的档案";
				}
			} else
			// 按照储位编号
			if ("cwbh".equals(type)) {
				list = storageOutService.findAllowOutByCwbh(applyIds);
				if (list.size() != 0) {
					obj[0] = "1";
					obj[1] = list;
				} else {
					obj[0] = "0";
					obj[1] = "该档案（储位编号：" + applyIds + "）：未查询到可出库的档案";
				}
			} else {
				obj[0] = "0";
				obj[1] = "该档案（" + applyIds + "）：未查询到可出库的档案";
			}
		} catch (Exception e) {
			obj[0] = "0";
			obj[1] = "查询异常，请联系管理员！";
			logger.error("查询可出库档案异常", e);
		}
		return STRUTS_JSON(obj);
	}

	/**
	 * 查询档案详细信息
	 * */
	public String showArc() {
		List<ArcBaseInfo> list = storageOutService.findArcByXtdabh(applyIds);
		List<ArcStorageIn> inlist = storageOutService
				.findStorageInByXtdabh(applyIds);
		// 正常
		if (list.size() == 1 && inlist.size() == 1) {
			arcinfo = list.get(0);
			storageIn = inlist.get(0);
		} else if (list.size() > 1) {
			return ERROR("数据异常：存在重复的档案！");
		} else {
			return ERROR("未查询到该档案！");
		}

		return "showArc";
	}

	/**
	 * 预出库
	 * */
	public String applyOut() {
		Object obj[] = new Object[3];
		List<String> ids = StringTools.getIds(applyIds);
		try {
			obj = storageOutService.checkApplyOut(ids, getSessionUser());
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
				logger.error("预出库异常", e);
			}
		}
		return STRUTS_JSON(obj);
	}

	/**
	 * 打印入库签收单
	 * */
	public String printCkd() {
		Date date = new Date(Long.parseLong(time));
		time = DateTools
				.getFormatDate(date, DateTools.nian_yue_ri_shi_fen_miao);
		return "printCkd";
	}

	/**
	 * 进入审核列表
	 * */
	public String toCheckout() {
		ArcStorageOutExample example = new ArcStorageOutExample();
		Criteria criteria = SwglQuery.fuzzyCheckStorageOutAll(stoout,
				example.createCriteria(), fuzzy);
		// 查询待审核的档案
		criteria.andDaztEqualTo(ArcStatus.DSH_CK);
		try {
			page = storageOutService.findStorageOutByPage(index, example);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("出库批次审核列表分页查询异常", e);
			return ERROR("出库批次审核列表分页查询异常", e.getMessage());
		}

		return "toCheckout";
	}

	/**
	 * 通过批次号查询，并进入审核出库列表
	 * */
	public String checkList() {
		if (null != pch && !"".equals(pch)) {
			pageList = storageOutService.findStorageOutByPch(pch);
		} else {
			pageList = new ArrayList(0);
		}
		return "checkList";
	}

	/**
	 * 出库
	 * */
	public String checkout() {

		Object ret[] = new Object[3];
		try {
			// 按批次入库
			if (type.equals("1")) {
				List<ArcStorageOut> list = storageOutService.storageOutByPc(
						applyIds, getSessionUser());
				ret[0] = "1";
				ret[1] = "出库完成";
				ret[2] = list;
			} else
			// 按系统编号入库
			if (type.equals("2")) {
				List<String> ids = StringTools.getIds(applyIds);
				storageOutService.storageOutByXtdabh(ids, getSessionUser());
				ret[0] = "1";
				ret[1] = "出库完成";
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
	 * 退回预出库档案
	 * */
	public String backout() {

		Object ret[] = new Object[3];
		try {
			// 按批次退回
			if (type.equals("1")) {
				List<ArcStorageOut> list = storageOutService
						.backOutByPc(applyIds);
				ret[0] = "1";
				ret[1] = "退回完成";
				ret[2] = list;
			} else
			// 按系统编号退回
			if (type.equals("2")) {
				List<String> ids = StringTools.getIds(applyIds);
				storageOutService.backOutByXtdabh(ids);
				ret[0] = "1";
				ret[1] = "退回完成";
			}
		} catch (Exception e) {
			logger.error("出库退回处理异常", e);
			if (e.getMessage().startsWith("stoin")) {
				String[] str = e.getMessage().split("-");
				ret[0] = "3";
				ret[1] = str[1];
				ret[2] = str[2];
			} else {
				ret[0] = "2";
				ret[1] = "出库退回异常，操作失败";
			}
		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 出库档案查询
	 * */
	public String outlist() {
		ArcStorageOutExample example = new ArcStorageOutExample();
		Criteria criteria = SwglQuery.fuzzyCheckStorageOutAll(stoout,
				example.createCriteria(), fuzzy);
		List<String> cxtj = new ArrayList<String>(2);
		cxtj.add(ArcStatus.DSH_CK);
		cxtj.add(ArcStatus.YCK);
		criteria.andDaztIn(cxtj);
		try {
			page = storageOutService.findStorageOutByPage(index, example);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("出库档案查询异常", e);
		}
		return "outlist";
	}

	/**
	 * 查看已出库&待审核档案
	 * */
	public String showOutArc() {

		List<ArcBaseInfo> list = storageOutService.findArcByXtdabh(applyIds);
		List<ArcStorageOut> listout = storageOutService
				.findStorageOutByXtdabh(applyIds);
		// 正常
		if (list.size() == 1 && listout.size() == 1) {
			arcinfo = list.get(0);
			stoout = listout.get(0);
		} else if (list.size() == 0) {
			return ERROR("未查询到该档案！");
		} else {
			return ERROR("数据异常！");
		}

		return "showOutArc";
	}

	// ==============================================

	public void setStorageOutService(StorageOutService storageOutService) {
		this.storageOutService = storageOutService;
	}

	public ArcStorageOut getStoout() {
		return stoout;
	}

	public void setStoout(ArcStorageOut stoout) {
		this.stoout = stoout;
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

	public ArcBaseInfo getArcinfo() {
		return arcinfo;
	}

	public void setArcinfo(ArcBaseInfo arcinfo) {
		this.arcinfo = arcinfo;
	}

	public ArcStorageIn getStorageIn() {
		return storageIn;
	}

	public void setStorageIn(ArcStorageIn storageIn) {
		this.storageIn = storageIn;
	}

}
