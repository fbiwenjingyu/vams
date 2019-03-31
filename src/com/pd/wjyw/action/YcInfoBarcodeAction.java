package com.pd.wjyw.action;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pd.system.framework.BaseAction;
import com.pd.webservice.core.WsRet;
import com.pd.wjyw.service.YcInfoBarcodeService;
import com.pd.wjyw.service.impl.YcInfoBarcodeServiceImpl;



/**
 * 条码合并操作
* @ClassName: YcInfoBarcodeAction 
* @Description: TODO
* @author zl
* @date 2014-8-7 下午02:05:18 
*
 */
public class YcInfoBarcodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass());
	private YcInfoBarcodeService ycInfoBarcodeService;

	private String xtdabh;
	private String lsh;
	
	/**
	 * 合并条码
	 * */
	public void ycInfo_barCode(){
		response.setContentType("text/html;charset=UTF-8");
		WsRet wsRet = new WsRet();
		String msg = "";
		String url = "";
		try{
			wsRet = ycInfoBarcodeService.addHbtm(xtdabh, lsh,getSessionUser());
			String urlStr = request.getParameter("url");
			if(wsRet.getWsCode()== 0){
				msg= "数据接口连接失败（六合一），请联系管理员！";
			}else{
				msg = wsRet.getCode();
			}
			request.setAttribute("msg", msg);
			if(urlStr.equals("P")){
				url = "ycInfo_preBarcode.jsp";
			}else if(urlStr.equals("arc")){
				url = "arcBaseInfo!query_sh.action";
			}else{
				url = "ycInfo!ycInfo_check.action?ycinfo.shjg=S";
			}
			if(msg.equals(YcInfoBarcodeServiceImpl.SUCCESS)){//条码合并成功后是否跳转到档案审核页面
				String scriptCode = "<script>top.Dialog.close();top.Dialog.confirm('合并成功！<br> 是否继续审核档案?'" +
								",function(){window.location='arcBaseInfo!query_sh.action?entity.xtdabh=" + xtdabh + "';}," +
							    "function(){});</script>";
				response.getWriter().write(scriptCode);
			}else{
				if(url.equals("ycInfo_preBarcode.jsp")){
					response.getWriter().write("<script>top.Dialog.close();top.Dialog.alert('"+msg+"');</script>");
				}else{
					response.getWriter().write("<script>top.Dialog.close();top.Dialog.alert('"+msg+"');window.location='"+url+"';</script>");
				}	
			}
			
		}catch (Exception e) {
			log.info("系统档案编号为"+xtdabh+"流水号为"+lsh+"合并条码失败----------------"+new Date());
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycInfo!ycInfo_check.action?ycinfo.shjg=S';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		//return "ycInfo_barCode";
	}
	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public void setYcInfoBarcodeService(YcInfoBarcodeService ycInfoBarcodeService) {
		this.ycInfoBarcodeService = ycInfoBarcodeService;
	}

	
	
}


