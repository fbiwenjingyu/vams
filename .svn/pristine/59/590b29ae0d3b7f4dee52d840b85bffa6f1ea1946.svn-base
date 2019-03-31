package com.pd.system.res;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.service.PubArcBaseInfoService;
import com.pd.right.model.SuperUser;
import com.pd.right.model.Users;
import com.pd.system.framework.BaseAction;
import com.pd.system.utils.BarcodeTools;
import com.pd.system.utils.PrintBean;

public class BarcodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private PubArcBaseInfoService pubArcBaseInfoService;
	private BarcodeService barcodeService;

	private Logger logger = Logger.getLogger(this.getClass());

	private PrintBean print;
	private String code;
	private String xtdabh;
	private String param;

	private String message;

	/**
	 * 生成系统条码，打印条码
	 * 
	 * @Title: writeCodeImage
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @author zl 2014-8-4
	 */
	public String writeCodeImage() {
		if (code != null && !"".equals(code)) {
			code = code.toUpperCase();
			BufferedImage image = BarcodeTools.getCode128(code);
			try {
				ImageIO.write(image, "gif", response.getOutputStream());
			} catch (IOException e) {
				logger.error("单条码打印异常", e);
			}
		} else {
			return ERROR("标签无法打印，缺少打印条码值");
		}
		return null;
	}

	// ==================================================
	/**
	 * 打印档案标签
	 * */
	public String printArcLabel() {
		if (null == xtdabh || "".equals(xtdabh)) {
			return ERROR("参数异常", "缺少必要参数：XTDABH（系统编号），标签无法打印");
		}
		List<ArcBaseInfo> list = pubArcBaseInfoService.findByXtdabh(xtdabh);
		if (list.size() == 1) {
			print = new PrintBean(list.get(0));
		} else if (list.size() == 0) {
			return ERROR("数据异常", "未找到档案：" + xtdabh + "<br>标签无法打印");
		} else {
			return ERROR("数据异常", "存在相同的系统编号：" + xtdabh + "<br>标签无法打印");
		}
		return "printArcLabel";
	}

	// ===============================
	
	/**
	 * 快捷打印设置
	 * */
	public String fastPrintSet() {
		JSONArray array = new JSONArray();
		if (null != param) {
			//session中的user
			SuperUser user = getSessionUser();
			//数据库user
			Users duser = barcodeService.findUserByCode(user.getUserCode());
			
			//添加快捷打印
			if ("fastPrint".equals(param)) {
				if (duser.getF2() == null || "".equals(duser.getF2())) {
					user.setF2("fastPrint;");
				} else {
					//如果不存在则保存
					if (duser.getF2().indexOf("fastPrint") == -1) {
						//是否以";"结尾
						if (duser.getF2().endsWith(";")) {
							user.setF2(duser.getF2() + "fastPrint;");
						}else{//不以";"结尾，则添加
							user.setF2(duser.getF2() + ";fastPrint;");
						}
					}
				}
			} else {//取消快捷打印
				String vals[] = duser.getF2().split(";");
				String smt = "";
				for (int i = 0; i < vals.length; i++) {
					if (!"fastPrint".equals(vals[i])) {
						smt += vals[i]+";";
					}
				}
				user.setF2(smt);
				user.setFastPrint("");
			}
			try {
				barcodeService.updateUserFastPrintSet(user);
				user.setFastPrint(param.equals("") ? "" : "1");
				array.add(0, "1");
				array.add(1, "保存成功！");
			} catch (Exception e) {
				e.printStackTrace();
				array.add(0, "0");
				array.add(1, "保存失败！");
			}
		}
		return STRUTS_JSON(array);
	}
	
	
	//=======================set & get===================================
	public PrintBean getPrint() {
		return print;
	}

	public void setPrint(PrintBean print) {
		this.print = print;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPubArcBaseInfoService(
			PubArcBaseInfoService pubArcBaseInfoService) {
		this.pubArcBaseInfoService = pubArcBaseInfoService;
	}

	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public void setBarcodeService(BarcodeService barcodeService) {
		this.barcodeService = barcodeService;
	}
}
