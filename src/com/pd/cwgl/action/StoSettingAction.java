package com.pd.cwgl.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.pd.cwgl.model.StoSetting;
import com.pd.cwgl.model.StoSettingExample;
import com.pd.cwgl.model.StoSettingExample.Criteria;
import com.pd.cwgl.service.StoService;
import com.pd.cwgl.utils.CwglConsts;
import com.pd.cwgl.utils.CwglTools;
import com.pd.right.model.SuperUser;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.BaseAction;
import com.pd.system.utils.StringTools;

/**
 * 储位设置
 * */
public class StoSettingAction extends BaseAction {

	private StoService stoService;

	private Logger logger = Logger.getLogger(this.getClass());

	private StoSetting setting;

	private String shiId;
	private String cwbh;
	// ========================

	private String shiIds;
	private String guiIds;
	private String lieNum;
	private String geNum;
	private String fenNum;
	private String cflx;

	private String remark;

	// ========================

	/**
	 * 进入设置列表
	 * */
	public String list() {
		// 条件查询
		StoSettingExample example = new StoSettingExample();
		example.setOrderByClause("cid asc");

		Criteria criteria = example.createCriteria();
		if (null != setting) {
			if (null != setting.getCid() && !"".equals(setting.getCid().trim())) {
				if (fuzzy.equals("1")) {
					criteria.andCidLike(setting.getCid() + "%");
				} else {
					criteria.andCidEqualTo(setting.getCid());
				}
			}

			if (null != setting.getCwlx()
					&& !"".equals(setting.getCwlx().trim())) {
				if (fuzzy.equals("1")) {
					criteria.andCwlxLike(setting.getCwlx() + "%");
				} else {
					criteria.andCwlxEqualTo(setting.getCwlx());
				}
			}
		}

		try {
			page = stoService.findPageByExample(index, example);
			pageList = page.getPagelist();
		} catch (Exception e) {
			logger.error("查询储位设置列表异常", e);
		}

		return "list";
	}

	/**
	 * 准备添加档案室
	 * */
	public String toAddShi() {
		request.setAttribute("shi_rl", new Object[CwglConsts.STO_SHI_RL]);
		request.setAttribute("gui_rl", CwglConsts.STO_GUI_RL);
		request.setAttribute("lie_rl", CwglConsts.STO_LIE_RL);
		request.setAttribute("ge_rl", CwglConsts.STO_GE_RL);
		return "toAddShi";
	}

	/**
	 * 添加档案室
	 * */
	public String addShi() {
		Object[] ret = new Object[2];
		// 生成柜ids
		List<String> guis = StringTools.getIds(guiIds.toUpperCase());
		try {
			stoService.addShi(getSessionUser(), shiId, guis, lieNum, geNum,
					fenNum, cflx, remark);
			ret[0] = "1";
			ret[1] = "添加成功！";
		} catch (Exception e) {
			ret[0] = "2";
			// ORA-00001: 违反唯一约束条件
			if (e.toString().indexOf("ORA-00001") != -1) {
				ret[0] = "储位已存在！";
				logger.error("储位已存在，添加档案室异常", e);
			} else {
				ret[1] = "添加异常！";
				logger.error("添加档案室异常", e);
			}

		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 准备添加档案柜
	 * */
	public String toAddGui() {
		// 准备参数
		toAddShi();
		shiId = CwglTools.stoSettingToQUISelect(stoService.getShiList(), "室");
		return "toAddGui";
	}

	/**
	 * 添加档案柜
	 * */
	public String addGui() {
		JSONArray array = new JSONArray();
		// 生成柜ids
		List<String> guis = StringTools.getIds(guiIds.toUpperCase());
		try {
			stoService.addGui(getSessionUser(), shiId, guis, lieNum, geNum,
					fenNum, cflx, remark);
			array.add(0, "1");
			array.add(1, "添加成功！");
			LogInfo.infoYw("添加档案柜", request, getSessionUser().getUserCode(),
					guiIds);
		} catch (Exception e) {
			array.add(0, "2");
			// ORA-00001: 违反唯一约束条件
			if (e.toString().indexOf("ORA-00001") != -1) {
				array.add(1, "储位已存在！");
			} else {
				array.add(1, "添加异常！");
				e.printStackTrace();
			}
		}
		return STRUTS_JSON(array);
	}

	/**
	 * 准备修改储位信息
	 * */
	public String toModifySet() {
		setting = stoService.findSetByCid(cwbh);
		return "toModifySet";
	}

	/**
	 * 修改保存储位信息
	 * */
	public String modifySet() {
		Object[] ret = new Object[2];
		StoSetting set = stoService.findSetByCid(setting.getCid());
		// 判断调整容量是否可以进行
		if ("".equals(setting.getZrl().trim())
				|| (!set.getZrl().equals(setting.getZrl()) && (Integer
						.parseInt(setting.getZrl()) < Integer.parseInt(set
						.getSyl())))) {
			ret[0] = "2";
			ret[1] = "储位总容量调整超出范围！";
			return STRUTS_JSON(ret);
		}
		try {
			setting.setXgrid(getSessionUser().getUserCode());
			setting.setXgrxm(getSessionUser().getUserName());
			setting.setXgsj(new Date());
			stoService.updateSet(setting);
			ret[0] = "1";
			ret[1] = "保存完成！";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0] = "3";
			ret[1] = "储位保存异常！";
		}

		return STRUTS_JSON(ret);
	}

	/**
	 * 刷新档案格的已使用量
	 * */
	public String flushUsed() {
		Object ret[] = new Object[2];
		try {
			int used = stoService.flushUsed(cwbh);
			ret[0] = "1";
			ret[1] = "" + used;
		} catch (Exception e) {
			logger.error("刷新异常", e);
			ret[0] = "2";
			ret[1] = "刷新异常！";
		}
		return STRUTS_JSON(ret);
	}

	/**
	 * 超级方法：更新所有储位格的使用量
	 * */
	public String flushAllGe() {
		try {
			stoService.flushAllGe();
		} catch (Exception e) {
			logger.error("刷新储位格使用异常", e);
			return STRUTS_JSON(new String[] { "0", "刷新异常" });
		}
		return STRUTS_JSON(new String[] { "1", "刷新成功" });
	}

	/**
	 * 删除储位
	 * */
	public String delSet() {
		Object ret[] = new Object[2];
		// 统计使用量，如果被使用，则无法删除
		StoSettingExample setExample = new StoSettingExample();
		setExample.createCriteria().andCwlxEqualTo(CwglConsts.CWLX_GE)
				.andCidLike(cwbh + "%");// 查询储位格的使用量
		long sum = stoService.findUsedNum(setExample);
		if (sum > 0) {
			ret[0] = "2";
			ret[1] = "当前储位已存放了档案，无法删除！";
		} else {
			try {
				stoService.deleteStoAndChilds(cwbh);
				ret[0] = "1";
				ret[1] = "删除成功！";
				SuperUser user = getSessionUser();
				LogInfo.infoYw("储位管理：删除储位（" + cwbh + "）", request,
						user.getUserCode(),
						user.getUserName() + "（" + user.getUserCode()
								+ "），储位管理：删除储位（" + cwbh + "）");
			} catch (Exception e) {
				logger.error("储位删除异常", e);
				ret[0] = "1";
				ret[1] = "删除异常，请联系管理员！";
			}
		}
		return STRUTS_JSON(ret);
	}

	// ========================================================

	public StoSetting getSetting() {
		return setting;
	}

	public void setStoService(StoService stoService) {
		this.stoService = stoService;
	}

	public void setSetting(StoSetting setting) {
		this.setting = setting;
	}

	public String getShiIds() {
		return shiIds;
	}

	public void setShiIds(String shiIds) {
		this.shiIds = shiIds;
	}

	public String getGuiIds() {
		return guiIds;
	}

	public void setGuiIds(String guiIds) {
		this.guiIds = guiIds;
	}

	public String getLieNum() {
		return lieNum;
	}

	public void setLieNum(String lieNum) {
		this.lieNum = lieNum;
	}

	public String getGeNum() {
		return geNum;
	}

	public void setGeNum(String geNum) {
		this.geNum = geNum;
	}

	public String getFenNum() {
		return fenNum;
	}

	public void setFenNum(String fenNum) {
		this.fenNum = fenNum;
	}

	public String getShiId() {
		return shiId;
	}

	public void setShiId(String shiId) {
		this.shiId = shiId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCwbh() {
		return cwbh;
	}

	public void setCwbh(String cwbh) {
		this.cwbh = cwbh;
	}

	public String getCflx() {
		return cflx;
	}

	public void setCflx(String cflx) {
		this.cflx = cflx;
	}

}
