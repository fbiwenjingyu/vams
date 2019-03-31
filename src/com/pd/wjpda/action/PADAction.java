package com.pd.wjpda.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pd.right.model.Users;
import com.pd.system.startup.SYSLoadManager;
import com.pd.system.utils.DateTools;
import com.pd.wjpda.model.WjpdaVO;
import com.pd.wjpda.service.WjpdaService;
import com.pd.wjpda.util.ZipUtil;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoPicExample;

/**
 * 外检PAD业务操作控制类
 * @author huhaifeng 2014-08-01
 *
 */
public class PADAction extends ActionSupport{

	private static final long serialVersionUID = -3702919066004542574L;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	private WjpdaService wjpdaService;
	
	private File imageFile;
	private String pzrInfo;
	private String picInfoStr;
	private String selectedYwlx;
	private String selectedHpzl;
	private String selectedHphm;
	private String username;
	private String password;
	private String jkFlg;
	private String xtdabh;
	
	//PDA端登录
	public String wjPdaLogin(){
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			request.setCharacterEncoding("utf-8");
			Users users = wjpdaService.findUsersByNameAndPwd(username,password);
			if(users != null){												//用户存在
				out.print(users.getUserName()+";"+users.getDeptCode());     //输出用户名和用户部门编号
			}else{
				out.print(""); 												//用户不存在 直接输出""
			}
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		return null;
	}
	
	//通过系统编号查找信息
	public String getInfoByXTDABH(){
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		String dabh = request.getParameter("xtdabh");
		if(jkFlg != null && "1".equals(jkFlg)){								//此情况代表的是外检转发
			dabh = xtdabh;
		}
		try {
			out = response.getWriter();
			if(dabh != null){
				dabh = dabh.toUpperCase();
				YcInfo ycInfo = wjpdaService.findYcInfoByXtdabh(dabh);
				if(ycInfo != null){																			//外检编号存在
					if(ycInfo.getShjg() == null || "".equals(ycInfo.getShjg())){							//第一次扫描
						response.setContentType("text/html");
						out.println("<?xml version='1.0' encoding='UTF-8' standalone='no'?>");
						out.println("<ycInfos>");
						out.println("<hphm>" + ycInfo.getHphm() + "</hphm>");	
						WjpdaVO wjpdaVO = new WjpdaVO();
						wjpdaVO.setPicStatus("N");
						wjpdaVO.setXtdabh(dabh);	
						//查找没有上传成功的图片信息
						List<WjpdaVO> wjpdaVOList = wjpdaService.findPicInfos(wjpdaVO);
						if(wjpdaVOList != null && wjpdaVOList.size()>0){
							for(WjpdaVO w : wjpdaVOList){
								out.println("<wjpdaVO>");
								out.println("<takeCode>" + w.getTakeCode() + "</takeCode>");				//纸张代码
								out.println("<takeName>" + w.getTakeName() + "</takeName>");				//纸张名称
								out.println("</wjpdaVO>");
							}
						}
						out.println("</ycInfos>");
					}else if("N".equals(ycInfo.getShjg())){													//审核不通过
						YcInfoPicExample example = new YcInfoPicExample();
						example.createCriteria().andXtdabhEqualTo(dabh).andPicStatusEqualTo("N");			
						List<YcInfoPic> listYcInfoPics = wjpdaService.getYcInfoPicByYcInfoPicExample(example);
						if(listYcInfoPics == null || listYcInfoPics.size()>0){								//此系统编号有没有通过的审核的图片
							response.setContentType("text/html");
							out.println("<?xml version='1.0' encoding='UTF-8' standalone='no'?>");
							out.println("<ycInfos>");
							out.println("<ycInfo>");
							out.println("<ywlx>" + ycInfo.getYwlx() + "</ywlx>");								//业务类型
							out.println("<hphm>" + ycInfo.getHphm() + "</hphm>");								//号牌号码
							out.println("<hpzl>" + ycInfo.getHpzl() + "</hpzl>");								//号牌种类
							out.println("<status>" + ycInfo.getStatus() + "</status>");							//状态
							out.println("<sjjg>" + ycInfo.getShjg() + "</sjjg>");								//审核结果
							out.println("</ycInfo>");
							WjpdaVO wjpdaVO = new WjpdaVO();
							wjpdaVO.setPicStatus("N");
							wjpdaVO.setXtdabh(dabh);
							//查找没有上传成功的图片信息
							List<WjpdaVO> wjpdaVOList = wjpdaService.findPicInfos(wjpdaVO);
							if(wjpdaVOList != null && wjpdaVOList.size()>0){
								for(WjpdaVO w : wjpdaVOList){
									out.println("<wjpdaVO>");
									out.println("<takeCode>" + w.getTakeCode() + "</takeCode>");				//纸张代码
									out.println("<takeName>" + w.getTakeName() + "</takeName>");				//纸张名称
									out.println("</wjpdaVO>");
								}
							}
							out.println("</ycInfos>");
							out.flush();
							out.close();
						}else{																				//此系统编号没有没通过的审核的图片
							out.print("2"); 
							out.close();
							out.flush();
						}
					}else if("S".equals(ycInfo.getShjg()) || "Y".equals(ycInfo.getShjg())){					//为审核状态或者是审核已通过
						out.print("2"); 
						out.close();
						out.flush();
					}
				}else{																						//外检编号不存在			
					out.print("0"); 
					out.close();
					out.flush();
				}
				out.close();
				out.flush();
			}else{
				out.print("0"); 
				out.close();
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
	
	//通过选中的业务类型和好牌种类分析需要拍那些相片
	public String getNeedTakePic(){
		String hpzl = request.getParameter("hpzl");
		String ywlx = request.getParameter("ywlx");
		String wjbh = request.getParameter("wjbh");
		PrintWriter out = null;
		if(jkFlg != null && "1".equals(jkFlg)){
			wjbh = xtdabh;
			hpzl = selectedHpzl;
			ywlx = selectedYwlx;
		}
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.println("<?xml version='1.0' encoding='UTF-8' standalone='no'?>");
			out.println("<ycInfos>");
			if(wjbh != null && !"".equals(wjbh)){
				wjbh = wjbh.toUpperCase();
				WjpdaVO wjpdaVO = new WjpdaVO();			//通过选中的业务类型和好牌种类查找需要拍那些相片										
				wjpdaVO.setHpzl(hpzl);
				wjpdaVO.setYwlx(ywlx);
				List<WjpdaVO> wjpdaVOList = wjpdaService.getNeedTakePic(wjpdaVO);
				if(wjpdaVOList != null && wjpdaVOList.size()>0){
					for(WjpdaVO w : wjpdaVOList){
						out.println("<wjpdaVO>");
						out.println("<takeCode>" + w.getTakeCode() + "</takeCode>");				//纸张代码
						out.println("<takeName>" + w.getTakeName() + "</takeName>");				//纸张名称
						out.println("</wjpdaVO>");
					}
				}
				out.println("</ycInfos>");
				out.flush();
				out.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		return null;
	}
	
	public String picUpToSrever() throws UnsupportedEncodingException{
		try {
			PrintWriter out = null;
			//根据os获取图片路径
			String path =  SYSLoadManager.getPicStorePath();
			String yyMM = DateTools.getYM();
			path = path + yyMM;
			File yyMMFile = new File(path);
			if(! yyMMFile.exists()){
				yyMMFile.mkdirs();
			}
			ZipUtil.unZipFiles(imageFile, path);					//将上传上来的图片解压到服务器存放图片的目录下
			YcInfoPic ycInfoPic = setYcInfoPicDate();
			YcInfo ycInfo = setYcInfoDate();
			picUpServiceDel(ycInfoPic, ycInfo, path);
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print("t");
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String picUpLoadAgain(){
		try {
			String wjbh = getXtdabhMethod();
			YcInfo ycInfo = wjpdaService.findYcInfoByXtdabh(wjbh);
			if(ycInfo != null && !"Y".equals(ycInfo.getShjg())){		//yc_info中存在这个数据
				//wjpdaService.deleteYcInfoPic(wjbh,null); 				//删除yc_info_pic中之前的旧数据
				PrintWriter out = null;
				String path = SYSLoadManager.getPicStorePath();
				String yyMM = DateTools.getYM();
				path = path + yyMM;
				File yyMMFile = new File(path);
				if(! yyMMFile.exists()){
					yyMMFile.mkdirs();
				}
				ZipUtil.unZipFiles(imageFile, path);					//将上传上来的图片解压到服务器存放图片的目录下
				
				YcInfoPic ycInfoPic = setYcInfoPicDate();
				ycInfo = setYcInfoDate();
				picUpServiceDel(ycInfoPic, ycInfo, path);
				response.setCharacterEncoding("utf-8");
				out = response.getWriter();
				out.print("t");
				out.close();
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public YcInfo setYcInfoDate(){
		YcInfo ycInfo = new YcInfo();
		if(pzrInfo != null && !"".equals(pzrInfo)){
			String pzr[] = pzrInfo.split(";");
			ycInfo.setCjr(pzr.length > 0 ? pzr[0] : "");		//创建人
			ycInfo.setCjrmc(pzr.length > 1 ? pzr[1] : "");		//创建人名称
			ycInfo.setDeptcode(pzr.length > 2 ? pzr[2] : "");	//部门编码
		}
		Date date = new Date();
		ycInfo.setCjsj(date);									//创建时间
		ycInfo.setYwlx(selectedYwlx);			//业务类型
		ycInfo.setHpzl(selectedHpzl);			//好牌种类
		ycInfo.setHphm(selectedHphm);			//号牌号码
		ycInfo.setSfwj("Y");					//是否外检
		ycInfo.setShr(null);					//审核人
		ycInfo.setShsj(null);					//审核时间
		ycInfo.setShrmc(null);					//审核人姓名
		return ycInfo;
	}
	
	public YcInfoPic setYcInfoPicDate(){
		YcInfoPic ycInfoPic = new YcInfoPic();
		if(pzrInfo != null && !"".equals(pzrInfo)){
			String pzr[] = pzrInfo.split(";");
			ycInfoPic.setPzr(pzr.length > 0 ? pzr[0] : "");		//拍照人
			ycInfoPic.setPzrmc(pzr.length > 1 ? pzr[1] : "");	//拍照人名称
		}
		ycInfoPic.setPicStatus("S");			//图片状态 S初始未审核状态N审核不通过Y审核通过
		ycInfoPic.setAddress("");				//照片拍摄地址
		ycInfoPic.setPosition("");				//坐标
		Date date = new Date();
		ycInfoPic.setPzsj(date);				//拍照时间
		
		return ycInfoPic;
	}
	
	public void picUpServiceDel(YcInfoPic ycInfoPic, YcInfo ycInfo, String path){
		if(picInfoStr != null && !"".equals(picInfoStr)){
			String str[] = picInfoStr.split(",");
			String dabh = "";
			YcInfoExample ycInfoExample = new YcInfoExample();
			for(int i= 0; i<str.length; i++){
				String picName = str[i];
				File file = new File(path + File.separator + picName);
				if(file.exists()){
					String info[] = picName.split("_");
					dabh = info[0].toUpperCase();
					ycInfo.setXtdabh(dabh);
					if(ycInfo.getId() == null || "".equals(ycInfo.getId())){
						ycInfoExample.createCriteria().andXtdabhEqualTo(dabh);
						List<YcInfo> listYcInfos = wjpdaService.getYcInfos(ycInfoExample);
						if(listYcInfos != null && listYcInfos.size()>0){
							ycInfo.setId(listYcInfos.get(0).getId());
							ycInfoPic.setYcinfoId(listYcInfos.get(0).getId());
						}
					}
					String takecode = info[1].replace(".jpg", "");
					YcInfoPicExample example = new YcInfoPicExample();
					example.createCriteria().andXtdabhEqualTo(dabh).andTakecodeEqualTo(takecode).andPicStatusEqualTo("N");
					ycInfoPic.setXtdabh(dabh);								//系统档案编号(可以是外检编号或者其他编号统称)
					ycInfoPic.setTakecode(takecode);						//拍摄种类ID
					ycInfoPic.setPicpath(path + File.separator + picName);	//图片存放路径
					ycInfoPic.setMik(path);									//图片存入文件夹目录
					ycInfoPic.setPicName(picName);							//图片名称
					List<YcInfoPic> listYcInfoPics = wjpdaService.getYcInfoPicByYcInfoPicExample(example);
					if(listYcInfoPics != null && listYcInfoPics.size()>0){	//之前存在数据 说明是补照
						wjpdaService.updateYcInfoPic(ycInfoPic, example);
					}else{
						ycInfoPic.setId(UUID.randomUUID().toString());
						wjpdaService.insertIntoYcInfoPic(ycInfoPic);
					}
				}
			}
			YcInfo yc = wjpdaService.findYcInfoByXtdabh(dabh);
			if(yc != null){
				if(yc.getShjg() == null || "".equals(yc.getShjg())){
					ycInfo.setStatus("S");				//这种情况是初次外检   就默认是S
				}else{
					ycInfo.setLsh(yc.getLsh());
					ycInfo.setStatus(yc.getStatus());
				}
				ycInfo.setXzqh(yc.getXzqh());
				ycInfo.setHphm(yc.getHphm());
			}else{
				ycInfo.setStatus("S");					//这种情况是初次外检   就默认是S
			}
			ycInfo.setShjg("S");						//这种情况是初次外检   就默认是S
			wjpdaService.updateYcInfo(ycInfo);
		}
	}
	
	
	public String getXtdabhMethod(){
		String xtdahb = "";
		if(picInfoStr != null && !"".equals(picInfoStr)){
			String str[] = picInfoStr.split(",");
			if(str.length>0){
				xtdahb = str[0].split("_")[0].toUpperCase();
			}
		}
		return xtdahb;
	}
	
	
	
	public WjpdaService getWjpdaService() {
		return wjpdaService;
	}

	public void setWjpdaService(WjpdaService wjpdaService) {
		this.wjpdaService = wjpdaService;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getPzrInfo() {
		return pzrInfo;
	}

	public void setPzrInfo(String pzrInfo) {
		this.pzrInfo = pzrInfo;
	}

	public String getPicInfoStr() {
		return picInfoStr;
	}

	public void setPicInfoStr(String picInfoStr) {
		this.picInfoStr = picInfoStr;
	}

	public String getSelectedYwlx() {
		return selectedYwlx;
	}

	public void setSelectedYwlx(String selectedYwlx) {
		this.selectedYwlx = selectedYwlx;
	}

	public String getSelectedHpzl() {
		return selectedHpzl;
	}

	public void setSelectedHpzl(String selectedHpzl) {
		this.selectedHpzl = selectedHpzl;
	}

	public String getSelectedHphm() {
		return selectedHphm;
	}

	public void setSelectedHphm(String selectedHphm) {
		this.selectedHphm = selectedHphm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJkFlg() {
		return jkFlg;
	}

	public void setJkFlg(String jkFlg) {
		this.jkFlg = jkFlg;
	}

	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}
	
}
