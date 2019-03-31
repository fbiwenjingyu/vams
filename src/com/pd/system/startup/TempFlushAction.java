package com.pd.system.startup;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.pd.system.framework.BaseAction;

/**
 * 缓存刷新action，提供缓存的分类刷新，全部刷新
 * */
public class TempFlushAction extends BaseAction {

	private static final long serialVersionUID = -8795671042454743963L;
	
	/**
	 * 刷新所有缓存
	 * */
	public static final String FLUSH_ALL="all";
	
	/**
	 * 刷新业务类型
	 * */
	public static final String FLUSH_YWLX="ywlx";
	/**
	 * 刷新号牌种类
	 * */
	public static final String FLUSH_HPZL="hpzl";
	/**
	 * 刷新行政区划
	 * */
	public static final String FLUSH_XZQH="xzqh";
	/**
	 * 刷新扫描纸张
	 * */
	public static final String FLUSH_SMZZ="smzz";
	/**
	 * 刷新系统配置
	 * */
	public static final String FLUSH_XTPZ="xtpz";
	/**
	 * 刷新机构信息（部门）
	 * */
	public static final String FLUSH_JGXX="jgxx";
	/**
	 * 刷新档案状态
	 * */
	public static final String FLUSH_DAZT="dazt";
	/**
	 * 刷新服务类工具
	 * */
	public static final String FLUSH_FWGJ="fwgj";
	
	
	/**
	 * 刷新类别
	 * */
	private String type;
	
	private HttpServletResponse response=ServletActionContext.getResponse();
	
	private void writeMsg(String str){
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 刷新缓存
	 * */
	public void flush(){
		if (null==type||"".equals(type)) {
			writeMsg("['0','']");
		}
		boolean ret=flush(type);
		if (ret) {
			writeMsg("['1','"+type+"']");
		}else{
			writeMsg("['0','"+type+"']");
		}
		
	}
	
	/**
	 * 提供为内部刷新的方法
	 * */
	public static boolean flush(String type){
		if (type.equals(FLUSH_ALL)) {//刷新所有
			SYSLoadManager.reload();
			return true;
		}else if (type.equals(FLUSH_YWLX)) {//刷新业务类型
			return SYSLoadManager.init_ywlx();
		}else if(type.equals(FLUSH_HPZL)){//刷新号牌种类
			return SYSLoadManager.init_hpzl();
		}else if (type.equals(FLUSH_XZQH)) {//刷新行政区划
			return SYSLoadManager.init_xzqh();
		}else if(type.equals(FLUSH_SMZZ)){//刷新扫描纸张
			return SYSLoadManager.init_paper();
		}else if(type.equals(FLUSH_XTPZ)){//刷新系统配置
			return SYSLoadManager.init_sys_attr();
		}else if(type.equals(FLUSH_JGXX)){//刷新机构信息（部门）
			return SYSLoadManager.init_dept();
		}else if(type.equals(FLUSH_DAZT)){//刷新档案状态
			return SYSLoadManager.init_dazt();
		}
		return false;
	}
	
	//=====================================================
	public void setType(String type) {
		this.type = type;
	}

}
