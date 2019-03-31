package com.pd.cwgl.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcBaseInfoExample.Criteria;
import com.pd.cwgl.model.ArcBaseInfoSdate;
import com.pd.cwgl.model.StoSetting;
import com.pd.cwgl.service.StoService;
import com.pd.cwgl.utils.CwglConsts;
import com.pd.cwgl.utils.CwglTools;
import com.pd.cwgl.utils.StoRet;
import com.pd.right.model.SuperUser;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.BaseAction;
import com.pd.system.framework.QueryCriteria;
import com.pd.system.res.ArcStatus;
import com.pd.system.utils.StringTools;
import com.pd.webservice.core.WsRet;
import com.pd.webservice.sixandone.QueryVehInfo;

public class StoGettingAction extends BaseAction {

	// 六合一查询数据
	private String findata;
	private String findata1;

	// 档案室
	private String shiId;
	private String selShiId;
	// 档案柜
	private String guiId;
	private String selGuiId;

	// 储位编号
	private String cwbh;
	private String hpzl;
	private String hphm;
	private String lsh;
	private String showId;
	private String xtdabh;
	private String clsbdh;
	private String querytype;
	private String sdate;

	// ==============================
	private StoSetting setting;
	private ArcBaseInfo info;
	private ArcBaseInfoSdate infod;
	private StoService stoService;

	private List<StoSetting> stosets;
	private List<ArcBaseInfo> baseinfos;

	// ==========================

	// 在session保存当前操作记录
	private void saveInSessoin(String geId) {
		String operaId = geId.substring(0, 1) + "-" + geId.substring(1, 3);
		SuperUser user = getSessionUser();
		// 如果session中的值与操作值不相同
		if (!operaId.equals(user.getF1())) {
			user.setF1(operaId);
		}
	}

	// 数据库和session都保存当前的操作记录
	private void saveInSessionAndDb(String geId) {
		String operaId = geId.substring(0, 1) + "-" + geId.substring(1, 3);
		SuperUser user = getSessionUser();
		// 如果数据库中的值中的值与操作值不相同
		if (!operaId.equals(user.getCwglOpera())) {
			user.setF1(operaId);
			try {
				stoService.updateUserByOperationRecord(user);
				user.setCwglOpera(operaId);// 保存临时操作状态
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取用户的缓存操作
	 * 
	 * @return string[0]=室，string[1]=柜
	 * */
	private String[] getSessionOperation() {
		String operareoced = getSessionUser().getF1();
		// 如果用户未做过任何操作。则返回null
		if (null == operareoced || "".equals(operareoced)) {
			return null;
		} else {
			return operareoced.split("-");
		}
	}

	/**
	 * 进入储位获取界面
	 * */
	public String table() {
		String[] orecord = getSessionOperation();
		shiId = "";
		guiId = "";
		// 如果用户未操作做过任何储位
		if (null == orecord) {
			// 获取第一档案室信息
			List<StoSetting> shis = stoService.getShiList();
			if (shis.size() == 0) {
				return WARNING("请创建储位！");
			}
			for (int i = 0; i < shis.size(); i++) {
				String shiid = shis.get(i).getSid();
				if (i == 0) {
					selShiId = shiid;
					break;
				}
			}
			shiId = CwglTools.stoSettingToQUISelectWithBz(shis, "室");
			// 获取第一柜信息
			List<StoSetting> guis = stoService.getGuiList(shis.get(0).getSid());
			for (int i = 0; i < guis.size(); i++) {
				String guiid = guis.get(i).getSid();
				if (i == 0) {
					selGuiId = guiid;
					break;
				}
			}
			guiId = CwglTools.stoSettingToQUISelectWithBz(guis, "柜");
		} else {
			// 获取档案室信息
			List<StoSetting> shis = stoService.getShiList();
			if (shis.size() == 0) {
				return WARNING("请创建储位！");
			}
			for (int i = 0; i < shis.size(); i++) {
				String shiid = shis.get(i).getSid();
				if (shiid.equals(orecord[0])) {// 如果用户操作的室与查询出来的档案室相同，则标记
					selShiId = shiid;
					break;
				}
			}
			shiId = CwglTools.stoSettingToQUISelectWithBz(shis, "室");
			// 获取档案柜信息
			List<StoSetting> guis = stoService.getGuiList(orecord[0]);
			for (int i = 0; i < guis.size(); i++) {
				String guiid = guis.get(i).getSid();
				if (guiid.equals(orecord[1])) {
					selGuiId = guiid;
					break;
				}
			}
			guiId = CwglTools.stoSettingToQUISelectWithBz(guis, "柜");
		}
		return "table";
	}

	/**
	 * 获取档案柜列表
	 * */
	public String getGuis() {
		JSONArray array = new JSONArray();
		String guiData = "";
		List<StoSetting> list = stoService.getGuiList(shiId);
		guiData = CwglTools.stoSettingToQUISelectWithBz(list, "柜");
		array.add(0, guiData);
		return STRUTS_JSON(array);
	}

	/**
	 * ▲▲▲获取档案柜数据，用于生成前台显示界面。核心算法
	 * */
	public String getGuiData() {
		List<List<StoSetting>> list = stoService.getGuiData(shiId, guiId, null);
		// 保存用户操作记录
		saveInSessoin(shiId + guiId);// 保存操作记录到session
		return STRUTS_JSON(list);
	}

	/**
	 * 进入档案格查看档案信息列表
	 * */
	public String gotoGe() {
		if (null == cwbh || "".equals(cwbh.trim())) {
			return WARNING("参数异常，未找到储位编号！");
		} else if (cwbh.length() < 5) {
			return WARNING("参数异常，储位编号格式不正确：" + cwbh);
		}
		// 截取档案格id
		String geId = CwglTools.splitGeId(cwbh);
		// 如果为默认储位编号则清空
		if (null != info) {
			if (null != info.getCwbh() && info.getCwbh().length() <= 5) {
				info.setCwbh(null);
			}
		}
		// 查询
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		example.setOrderByClause(" cwbh asc ");
		Criteria criteria = (Criteria) QueryCriteria.checkAllStringByEquals(
				info, example.createCriteria(), fuzzy);
		// 查询上级储位为当前档案格的档案，并且为有效储位的档案
		criteria.andCwsjEqualTo(geId).andCwyxEqualTo(CwglConsts.ENABLE);
		try {
			page = stoService.findArcPageByExample(index, example);
			pageList = page.getPagelist();

			// 获取已录入档案状态，用于判断是否可以删除
			request.setAttribute("allowdel", ArcStatus.YLR_LSDA);
		} catch (Exception e) {
			logger.error("查询档案格数据异常：" + cwbh, e);
		}
		return "gotoGe";
	}

	/**
	 * 查看档案详细信息
	 * */
	public String detailInfo() {
		List<ArcBaseInfo> list = stoService.findArcByXtdabh(xtdabh);
		if (list.size() == 1) {
			info = list.get(0);
		} else if (list.size() == 0) {
			return ERROR("未找到档案：" + xtdabh + "（系统编号）");
		} else {
			return ERROR("数据异常，存在系统编号相同的档案：" + xtdabh + "（系统编号）");
		}
		return "detailInfo";
	}

	/**
	 * 通过储位编号获取档案设置表
	 * */
	public String getCwbhSet() {
		return STRUTS_JSON(stoService.findSetByCid(cwbh));
	}

	/**
	 * 根据档案格ID获取可用的最新的储位编号和该格信息
	 * */
	public String getNewCwbh() {
		Object ret[] = new Object[3];
		String geid = CwglTools.splitGeId(cwbh);
		ret[0] = stoService.getUsableCwbh(geid);
		ret[1] = stoService.findSetByCid(geid);
		return STRUTS_JSON(ret);
	}

	/**
	 * 进入储位分配页面
	 * */
	public String stoAssign() {
		// 验证储位编号
		if (null == cwbh || "".equals(cwbh.trim())) {
			return ERROR("参数异常，未选择储位编号！");
		} else
		// 储位编号不正确
		if (cwbh.length() < 5) {
			return ERROR("参数异常，储位编号格式不正确：" + cwbh);
		}

		// 获取格id
		String geId = cwbh.substring(0, 5);
		// 查询格信息
		setting = stoService.findSetByCid(geId);

		// 储位已满
		if (setting.getSyl().equals(setting.getZrl())) {
			return ERROR("储位已满，请选择其他储位！", "使用量：" + setting.getSyl() + "，总容量："
					+ setting.getZrl());
		} else
		// 验证储位是否可用
		if (setting.getSfky().equals(CwglConsts.DISABLE)) {
			return ERROR("该储位已禁止存放档案！", "使用量：" + setting.getSyl() + "，总容量："
					+ setting.getZrl());
		}
		// 查询当前储位信息，并生成最新的储位编号
		String[] res = stoService.getUsableCwbh(geId);
		if (res[0].equals("1")) {
			cwbh = res[1];
		} else {
			// 获取异常
			return ERROR(res[1]);
		}

		// 切割储位编号
		showId = cwbh.substring(0, 1) + "室" + cwbh.substring(1, 3) + "柜"
				+ cwbh.substring(3, 4) + "列" + cwbh.substring(4, 5) + "格";

		return "stoAssign";
	}

	/**
	 * 查询六合一接口
	 * */
	public String findSino() {
		Object obj[] = new Object[2];

		// 按照身份证明号查询
		if ("hphm".equals(querytype)) {
			// 通过号牌查询六合一
			WsRet wsRet = QueryVehInfo._01C21(findata1, findata);
			if (null == wsRet) {
				obj[0] = "0";
				obj[1] = "请输入参数";
			}
			// 查询成功
			if (wsRet.isOK() && wsRet.getDataList().size() > 0) {
				// 数据查询成功
				Map<String, String> map = wsRet.getDataList().get(0);
				// 设置数据
				infod = new ArcBaseInfoSdate(map);
				infod.setHphm(findata.toUpperCase());
				infod.setHpzl(findata1);
				obj[0] = "1";
				obj[1] = infod;
			} else {// 查询失败
				obj[0] = "0";
				obj[1] = "六合一查询失败：" + wsRet.getWsCode() + " - "
						+ wsRet.getWsMsg();
			}
		} else if ("lsh".equals(querytype)) {
			// 通过流水号查询六合一数据
			WsRet wsRet = QueryVehInfo._01C26(findata);
			if (null == wsRet) {
				obj[0] = "0";
				obj[1] = "请输入参数";
			}
			// 查询成功
			if (wsRet.isOK() && wsRet.getDataList().size() > 0) {
				// 数据查询成功
				Map<String, String> map = wsRet.getDataList().get(0);
				// 设置数据
				infod = new ArcBaseInfoSdate(map);
				infod.setLsh(findata);
				obj[0] = "1";
				obj[1] = infod;
			} else {// 查询失败
				obj[0] = "0";
				obj[1] = "六合一查询失败：" + wsRet.getWsCode() + " - "
						+ wsRet.getWsMsg();
			}
		}
		return STRUTS_JSON(obj);
	}

	/**
	 * 保存储位信息（插入储位编号）
	 * */
	public String saveStore() {
		Object obj[] = new Object[3];
		String cwbh1 = infod.getCwbh();
		try {
			// 如果储位编号为空，则无法保存
			if ("".equals(cwbh1)) {
				obj[0] = "2";
				obj[1] = "未找到储位编号，数据未保存！";
				return STRUTS_JSON(obj);
			}
			// 保存，插入信息
			ArcBaseInfo baseInfo = infod.toArcBaseInfo();
			String ret = stoService.saveStore(baseInfo, getSessionUser(), 0);
			if (ret.equals(StoRet.PARAM_NOTHING_CWBH)) {
				obj[0] = "2";
				obj[1] = "未找到储位编号，数据未保存！";
			} else if (ret.equals(StoRet.FULL_CW)) {
				obj[0] = "2";
				obj[1] = "当前储位已满，请更换储位，信息未保存！";
			} else if (ret.equals(StoRet.ERROR_CWBH)) {
				obj[0] = "2";
				obj[1] = "储位编号异常，请刷新当前页！";
			} else if (ret.equals(StoRet.SUCCESS)) {
				obj[0] = "1";
				obj[1] = "保存成功！";
				obj[2] = baseInfo.toArcBaseInfoSdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj[0] = "2";
			obj[1] = "处理异常，请刷新储位编号或联系管理员！";
		}
		// 保存操作记录
		saveInSessionAndDb(cwbh1);
		return STRUTS_JSON(obj);
	}

	/**
	 * 准备修改档案信息
	 * */
	public String toModifyInfo() {
		List<ArcBaseInfo> list = stoService.findArcByXtdabh(xtdabh);
		if (list.size() == 0) {
			return ERROR("数据异常", "未查询到相关档案：" + xtdabh + "（系统编号）");
		} else if (list.size() > 1) {
			return ERROR("数据异常", "查询到相同的系统编号：" + xtdabh + "（系统编号）");
		} else {
			info = list.get(0);
		}
		return "toModifyInfo";
	}

	/**
	 * 保存档案信息
	 * */
	public void saveInfo() {
		Object ret[] = new Object[2];
		try {
			stoService.updateArcById(info);
			responseWriteJS("alert('保存成功！');top.frmright.window.location.reload(false);top.Dialog.close();");
		} catch (Exception e) {
			logger.error("档案储位信息保存异常", e);
			responseWriteJS("alert('保存异常！');history.go(-1)");
		}
	}

	// ========================接口公开界面=========================

	/**
	 * 进入储位获取界面
	 * */
	public String getStore() {
		// 取出缓存中的操作
		String[] orecord = getSessionOperation();
		// 获取档案室信息列表
		List<StoSetting> shis = stoService.getShiList();
		shiId = CwglTools.stoSettingToQUISelectWithBz(shis, "室");

		// 如果档案室为空
		if (shis.size() == 0) {
			return WARNING("未查询到任何档案室，请创建！");
		}
		// 对应的档案柜列表
		List<StoSetting> guis = new ArrayList<StoSetting>(0);
		// 如果用户做过操作未做过任何操作
		if (null != orecord) {
			for (int i = 0; i < shis.size(); i++) {
				String shiid = shis.get(i).getSid();
				if (shiid.equals(orecord[0])) {// 如果用户操作的室与查询出来的档案室相同，则标记
					selShiId = shiid;
					// 获取该室的档案柜列表
					guis = stoService.getGuiList(shiid);
					guiId = CwglTools.stoSettingToQUISelectWithBz(guis, "柜");
					break;
				}
			}
			for (int i = 0; i < guis.size(); i++) {
				String guiid = guis.get(i).getSid();
				if (guiid.equals(orecord[1])) {// 如果用户操作的柜与查询出来的档案柜相同，则标记
					selGuiId = guiid;
					break;
				}
			}
		} else {// 如果等于空，则说明未产生过操作
			// 取出第一个档案室
			guis = stoService.getGuiList(shis.get(0).getSid());
			guiId = CwglTools.stoSettingToQUISelectWithBz(guis, "柜");
			selShiId = "";
			selGuiId = "";
		}
		return "getStore";
	}

	/**
	 * 通过格自动分配储位编号
	 * */
	public String getCwbhByGe() {
		Object ret[] = null;
		if (null == cwbh || "".equals(cwbh.trim())) {
			ret = new String[] { "0", "参数不能为空（格编号）" };
		} else if (cwbh.length() < 5) {
			ret = new String[] { "0", "参数格式不合法：" + cwbh };
		} else {
			String geid = CwglTools.splitGeId(cwbh);
			ret = stoService.getUsableCwbh(geid);
		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 删除储位信息
	 * */
	public String delArc() {
		Object ret[] = new Object[2];
		try {
			List<String> ids = StringTools.getIds(xtdabh);
			stoService.deleteArcCwbh(ids);
			ret[0] = "1";
			ret[1] = "删除成功";
			SuperUser user = getSessionUser();
			LogInfo.infoYw("储位管理：删除档案", request, user.getUserCode(),
					user.getUserName() + "（" + user.getUserCode() + "）");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ret[0] = "0";
			ret[1] = e.getMessage();
		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 准备迁移档案迁移
	 * */
	public String moveTable() {
		// 查询当前储位
		// StoSetting ge = stoService.findSetByCid(cwbh);

		// 获取档案室信息列表
		List<StoSetting> shis = stoService.getShiList();
		shiId = CwglTools.stoSettingToQUISelectWithBz(shis, "室");

		// 获取第一个档案柜列表
		List<StoSetting> guis = stoService.getGuiList(shis.get(0).getSid());
		guiId = CwglTools.stoSettingToQUISelectWithBz(guis, "柜");

		return "moveTable";
	}

	/**
	 * ▲▲▲获取档案柜数据，用于生成前台显示界面。核心算法，表现必须简单！
	 * */
	public String getMoveTable() {
		List<List<StoSetting>> list = new ArrayList<List<StoSetting>>();
		// 查询档案
		List<ArcBaseInfo> arcs = stoService.findArcByXtdabh(xtdabh);
		if (arcs.size() == 1) {
			ArcBaseInfo info = arcs.get(0);
			// 有效储位
			if (info.getCwyx().equals(CwglConsts.ENABLE)) {
				list = stoService.getGuiData(shiId, guiId, info.getCwsj());
			}
		}
		return STRUTS_JSON(list);
	}

	/**
	 * 迁移档案
	 **/
	public String moveArc() {
		Object ret[] = new Object[3];
		try {
			ret = stoService.moveInfoByAuto(xtdabh, cwbh);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ret[0] = "0";
			ret[1] = "处理异常：" + e.getMessage();
		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 进入插入储位编号界面
	 * */
	public String intoInsertCwbh() {
		// 验证储位编号
		if (null == cwbh || "".equals(cwbh.trim())) {
			return ERROR("参数异常，未选择储位编号！");
		} else
		// 储位编号不正确
		if (cwbh.length() < 5) {
			return ERROR("参数异常，储位编号格式不正确：" + cwbh);
		}
		String geid = CwglTools.splitGeId(cwbh);
		// 查询档案格信息
		StoSetting ge = stoService.findSetByCid(geid);

		// 查询该档案格的所有已录入的储位档案，必须经过储位编号顺序排序
		List<ArcBaseInfo> list = stoService.findArcByGeIdOrderByAsc(geid);

		// 用已有数据填充储位数据空格
		pageList = Arrays.asList(fillCwbhBlank(list, ge));

		return "intoInsertCwbh";
	}

	// =============================
	// 储位编号数据对象数组填充
	private ArcBaseInfo[] fillCwbhBlank(List<ArcBaseInfo> list, StoSetting ge) {
		// 创建一个对应的储位列表
		ArcBaseInfo[] taglist = new ArcBaseInfo[Integer.parseInt(ge.getZrl())];
		// 储位数据计数器
		int idx = 0;
		// 已有数据长度
		int length = list.size();
		for (int i = 0; i < taglist.length; i++) {
			if (idx < length) {
				ArcBaseInfo info = list.get(idx);
				String csn = info.getCwbh().substring(5, 8);// 截取储位序号
				// 如果储位序号与储位列表序号相同。就放入，然后计数器+1
				if (Integer.parseInt(csn) - 1 == i) {
					taglist[i] = info;
					idx++;
					continue;// 不在进行空白填充
				}
			}
			// 除以上情况外。当数据列表已经获取完毕，则填充空白储位对象
			ArcBaseInfo info = new ArcBaseInfo();
			// 设置储位编号
			info.setCwbh(ge.getCid() + StringTools.lpad((i + 1) + "", 3, "0"));
			taglist[i] = info;

		}
		return taglist;
	}

	// ============================================
	public void setStoService(StoService stoService) {
		this.stoService = stoService;
	}

	public String getFindata() {
		return findata;
	}

	public void setFindata(String findata) {
		this.findata = findata;
	}

	public String getShiId() {
		return shiId;
	}

	public void setShiId(String shiId) {
		this.shiId = shiId;
	}

	public String getSelShiId() {
		return selShiId;
	}

	public void setSelShiId(String selShiId) {
		this.selShiId = selShiId;
	}

	public String getGuiId() {
		return guiId;
	}

	public void setGuiId(String guiId) {
		this.guiId = guiId;
	}

	public String getSelGuiId() {
		return selGuiId;
	}

	public void setSelGuiId(String selGuiId) {
		this.selGuiId = selGuiId;
	}

	public String getCwbh() {
		return cwbh;
	}

	public void setCwbh(String cwbh) {
		this.cwbh = cwbh;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public String getClsbdh() {
		return clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public StoSetting getSetting() {
		return setting;
	}

	public void setSetting(StoSetting setting) {
		this.setting = setting;
	}

	public ArcBaseInfo getInfo() {
		return info;
	}

	public void setInfo(ArcBaseInfo info) {
		this.info = info;
	}

	public List<StoSetting> getStosets() {
		return stosets;
	}

	public void setStosets(List<StoSetting> stosets) {
		this.stosets = stosets;
	}

	public List<ArcBaseInfo> getBaseinfos() {
		return baseinfos;
	}

	public void setBaseinfos(List<ArcBaseInfo> baseinfos) {
		this.baseinfos = baseinfos;
	}

	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getFindata1() {
		return findata1;
	}

	public void setFindata1(String findata1) {
		this.findata1 = findata1;
	}

	public ArcBaseInfoSdate getInfod() {
		return infod;
	}

	public void setInfod(ArcBaseInfoSdate infod) {
		this.infod = infod;
	}

}
