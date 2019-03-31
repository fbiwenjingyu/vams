package com.pd.arc.action;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoAndRescanInfo;
import com.pd.arc.model.ArcBaseInfoAndYcInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcBlackCar;
import com.pd.arc.model.ArcBlackCarExample;
import com.pd.arc.model.ArcPicInfo;
import com.pd.arc.model.ArcRescanInfoExample;
import com.pd.arc.model.ArcStatusRecord;
import com.pd.arc.model.ArcBaseInfoExample.Criteria;
import com.pd.arc.service.ArcBaseInfoService;
import com.pd.arc.service.ArcReScanInfoService;
import com.pd.right.model.SuperUser;
import com.pd.system.framework.BaseAction;
import com.pd.system.framework.Pagination;
import com.pd.system.res.ArcStatus;
import com.pd.system.startup.SYSLoadManager;
import com.pd.system.utils.BarcodeTools;
import com.pd.system.utils.DateTools;
import com.pd.webservice.util.Base64Image;
import com.pd.webservice.util.FileHelper;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoPicExample;
import com.pd.wjyw.service.YcInfoPicService;
import com.pd.wjyw.service.YcInfoService;

/**
 * 
 * @author ywj
 */
public class ArcBaseInfoAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(this.getClass());
	/*	Service	*/
	private ArcBaseInfoService arcBaseInfoService;
	private YcInfoPicService ycInfoPicService;
	private YcInfoService ycInfoService;
	private ArcReScanInfoService arcReScanInfoService;
	private JdbcTemplate jdbcTemplate;
	private JdbcTemplate jdbcTemplate1;
	
	private ArcBaseInfo entity;
	private Date sdate;
	private Date edate;
	
	private static final String YHB= "H";//外检状态：已合并
	private static final String WWJ= "N";//无外检
	private static final String YWJ= "Y";//有外检
	private static final String WSH = "S";//未审核
	private static final String SHWTG = "N";//审核未通过
	private static final String SHTG = "Y";//审核通过
	private static final String BUSINESS_ARC = "1";//业务档案
	private static final String HISTORY_ARC = "2";//历史档案
	private static final String POST_SCAN = "1"; //后扫
	/**
	 * 查询待审核的档案列表信息
	 * @return
	 * @author ywj
	 * 2014-8-4
	 */
	public String query_sh(){
		try {
			ArcBaseInfoExample example = new ArcBaseInfoExample();
			
			//查询条件拼接
			Criteria ca = example.createCriteria();
			if(null != entity){
				if(StringUtils.isNotEmpty(entity.getLsh())){
					ca.andLshLike("%" + entity.getLsh().trim() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getYwlx())){
					ca.andYwlxEqualTo(entity.getYwlx().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHpzl())){
					ca.andHpzlEqualTo(entity.getHpzl().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHphm())){
					ca.andHphmLike("%" + entity.getHphm().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getXh())){
					ca.andXhLike("%" + entity.getXh().trim().toUpperCase() + "%");
				}
				
				if(null != sdate){
					ca.andCjsjGreaterThanOrEqualTo(sdate);
				}
				if(null != edate){
					Calendar c = Calendar.getInstance();
					c.setTime(edate);
					c.add(Calendar.DAY_OF_MONTH, 1);
					ca.andCjsjLessThan(c.getTime());
				}
				
				if(StringUtils.isNotEmpty(entity.getShr())){
					ca.andShrLike("%" + entity.getShr().trim() + "%");
				}
				
				if(StringUtils.isNotEmpty(entity.getXtdabh())){
					ca.andXtdabhLike("%" + entity.getXtdabh().trim().toUpperCase() + "%");
				}
				
				if(StringUtils.isNotEmpty(entity.getDeptcode())){
					ca.andDeptcodeEqualTo(entity.getDeptcode().trim());
					System.out.println("------------- deptcode = " + entity.getDeptcode());
				}
				
			}
			
			//ca.andDaztEqualTo(ArcStatus.YSM);
			ca.andDaztIn(Arrays.asList(new String[]{ArcStatus.WSM,ArcStatus.YSM}));
			//ca.andDaztEqualTo(ArcStatus.WSM);
			//ca.andDalxEqualTo(BUSINESS_ARC);
			example.setOrderByClause("CJSJ DESC");
			
			page = arcBaseInfoService.findArcBaseInfoPageListOfQuerySh(index,example);
			List<ArcBaseInfoAndYcInfo> list_data = page.getPagelist();
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("sdate", sdate);
			request.setAttribute("edate", edate);
			request.setAttribute("user2CnDeptCode", SYSLoadManager.user2CnDeptCodeMap);
			request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		//根据登陆用户获取用户机构对应的扫描类型
		SuperUser user = getSessionUser();
		String deptCode = user.getDeptCode();
		String scanType = getScanTypeByDeptCode(deptCode);
		if(POST_SCAN.equals(scanType)){//后扫
			return "gdsh_list_post";
		}
		
		return "gdsh_list_pri";   //先扫
		
		
	}
	
	private String getScanTypeByDeptCode(String deptCode) {
		return arcBaseInfoService.getScanTypeByDeptCode(deptCode);
	}

	/**
	 * 查询业务档案的外检状态
	 * @author ywj
	 * 2014-8-5
	 * 206-其他错误
	 * 205-外检还未上传图片
	 * 204-外检为无外检业务
	 * 203-外检审核通过
	 * 202-外检还未审核
	 * 201-未合并
	 * 200-外检审核未通过
	 * @throws IOException 
	 * 
	 * */
	public void queryWjbhResult() throws IOException{
		String wjbh=request.getParameter("wjbh");
		String lsh=request.getParameter("lsh");
		try {
			if(StringUtils.isNotEmpty(wjbh)){
				YcInfoExample ycInfoExample = new YcInfoExample();
				ycInfoExample.createCriteria().andXtdabhEqualTo(wjbh);
				List<YcInfo> list = ycInfoService.selectByExample(ycInfoExample);
				if(list != null && list.size() > 0){
					YcInfo ycInfo = list.get(0);
					if(StringUtils.isEmpty(lsh)){//未合并
						String status = ycInfo.getStatus();
						if(!YHB.equals(status)){
							response.getWriter().println("201");//条码未合并
					    }else {
					    	response.getWriter().println("206");
					    }
				   }else{//已合并
					   String sfwj = ycInfo.getSfwj();
					   if(WWJ.equals(sfwj)){//无外检
						   response.getWriter().println("204");//外检为无外检业务
					   }else if(YWJ.equals(sfwj)){//有外检
						   YcInfoPicExample YcInfoPicExample = new YcInfoPicExample();
						   YcInfoPicExample.createCriteria().andXtdabhEqualTo(wjbh);
						   List<YcInfoPic> ycInfoPicList = ycInfoPicService.selectByExample(YcInfoPicExample );
						   if(null == ycInfoPicList || ycInfoPicList.size() <= 0){
							   response.getWriter().println("205");//外检还未上传图片
						   }else{
							   String shjg = ycInfo.getShjg();//审核结果
							   if(SHWTG.equals(shjg) ){//已审核
								   response.getWriter().println("200");//外检审核未通过
							   }else if(SHTG.equals(shjg)){
								   response.getWriter().println("203");//外检审核通过
							   }else if(WSH.equals(shjg)){
								   response.getWriter().println("202");//外检还未审核
							   }else {
								   response.getWriter().println("206");
							   }
						   }
					   }else {
						   response.getWriter().println("206");
					   }
				  }
			  }else {
				  response.getWriter().println("206");
			  }
			}else {
				response.getWriter().println("206");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("206");
		}
	}
	
	/**
	 * 查询档案的扫描状态
	 * @author ywj
	 * 2014-8-6
	 * 204-其他错误
	 * 203-档案还未审核
	 * 202-档案审核已通过
	 * 201-档案审核未通过
	 * 200-档案没有扫描
	 * @throws IOException 
	 * @throws IOException 
	 * 
	 * */
	public void queryScanResult() throws IOException{
		String wjbh=request.getParameter("wjbh");
		try {
			if(StringUtils.isNotEmpty(wjbh)){
				List<ArcBaseInfo> arcBaseInfoList = arcBaseInfoService.getArcBaseInfoByXTDABH(wjbh);
				if(arcBaseInfoList != null && arcBaseInfoList.size() > 0){
					ArcBaseInfo arcBaseInfo = arcBaseInfoList.get(0);
					List<ArcPicInfo> picList = arcBaseInfoService.getPicInfoByXTDABH(wjbh);
					if(null == picList || picList.size() <= 0){
						response.getWriter().println("200");
					}else{
						String dazt = arcBaseInfo.getDazt();
						if(ArcStatus.SHWTG_YW.equals(dazt)){
							response.getWriter().println("201");
						}else if(ArcStatus.SHTG_YW.equals(dazt)){
							response.getWriter().println("202");
						}else if(ArcStatus.YSM.equals(dazt)){
							response.getWriter().println("203");
						}else{
							response.getWriter().println("204");
						}
					}
				}else{
					response.getWriter().println("204");
				}
			}else {
				response.getWriter().println("204");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().println("204");
		}
	}
	
	/**
	 * 外检审核显示详细信息和图片列表
	 * ywj
	 * */
	public String showPic(){
		
		try {
			//得到外检编号
			String wjbh=request.getParameter("wjbh");
			String from = request.getParameter("from");
			YcInfoExample ycInfoExample = new YcInfoExample();
			ycInfoExample.createCriteria().andXtdabhEqualTo(wjbh);
			List<YcInfo> list = ycInfoService.selectByExample(ycInfoExample);
			if(list != null && list.size() > 0){
				YcInfo ycInfo = list.get(0);
				List<YcInfoPic> listPic = ycInfoPicService.getPicsByXtdabh(wjbh);
				request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
				request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
				request.setAttribute("user2CnDeptCode", SYSLoadManager.user2CnDeptCodeMap);
				request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
				request.setAttribute("yc_paper_map", SYSLoadManager.yc_paper_map);
				request.setAttribute("entity", ycInfo);
				request.setAttribute("picList", listPic);
				request.setAttribute("from", from);
				request.setAttribute("verifyResult", ycInfo.getShjg());
				session.setAttribute("listPic2", listPic);
			}
			request.setAttribute("wjbh", wjbh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wjcy_showPic";
	}
	
	/**
	 * 根据系统档案编号查询 档案基本信息、档案照片信息 
	 * @return 业务审核界面
	 * @author ywj
	 * 2014-8-6
	 */
	public String getEntityById(){
		try{
			String wjbh = request.getParameter("wjbh");
			String selectType = request.getParameter("selectType");
			String toPage = request.getParameter("toPage");
			String deptCode =  request.getParameter("deptCode");
			//获得档案基本信息
			List<ArcBaseInfo> list = arcBaseInfoService.getArcBaseInfoByXTDABH(wjbh);
			if(list != null && list.size() > 0){
				ArcBaseInfo arcBaseInfo = list.get(0);
				// 照片信息列表
				List<ArcPicInfo> picList = arcBaseInfoService.getPicInfoByXTDABH(wjbh);
				// 获得当前图片列表中共有多少种业务类型
				List<String> picYwlxList = new ArrayList<String>();
				for (ArcPicInfo pic : picList) {
					String picYwlx = pic.getYwlx();
					String picTimes = pic.getSmcs();
					if(!picYwlxList.contains(picYwlx+","+picTimes)){
						picYwlxList.add(picYwlx+","+picTimes);
					}
				}
				request.setAttribute("entity", arcBaseInfo);
				request.setAttribute("picList", picList);
				request.setAttribute("picYwlxList", picYwlxList);
				request.setAttribute("selectType", selectType);
				request.setAttribute("paperMap", SYSLoadManager.paper_map);
				//System.out.println("------------------车辆拓印膜----------" +SYSLoadManager.paper_map.get("164"));
				request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
				request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
				request.setAttribute("toPage", toPage);
				request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
				
				//查看详细
				session.setAttribute("entity2",entity);
				session.setAttribute("picList2", picList);
				
				
				
				request.setAttribute("deptCode", deptCode);//记录部门编号
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return "ywsh_sh";
	}
	
	/**
	 * 根据系统档案编号查询 档案基本信息、档案照片信息 
	 * @return 业务审核界面-综合审核
	 * @author ywj
	 * 2014-8-7
	 */
	public String getEntityByIdZh(){
		try{
			String selectType = request.getParameter("selectType");
			String toPage = request.getParameter("toPage");
			String wjbh = request.getParameter("wjbh");
			String deptCode =  request.getParameter("deptCode");
			/*** 获取档案基本信息及图片 ****/
			List<ArcBaseInfo> list = arcBaseInfoService.getArcBaseInfoByXTDABH(wjbh);
			if(list != null && list.size() > 0){
				ArcBaseInfo entity = list.get(0);
				// 照片信息列表
				List<ArcPicInfo> picList = arcBaseInfoService.getPicInfoByXTDABH(wjbh);
				// 获得当前图片列表中共有多少种业务类型
				List<String> picYwlxList = new ArrayList<String>();
				for (ArcPicInfo pic : picList) {
					String picYwlx = pic.getYwlx();
					String picTimes = pic.getSmcs();
					if(!picYwlxList.contains(picYwlx+","+picTimes)){
						picYwlxList.add(picYwlx+","+picTimes);
					}
				}
				request.setAttribute("entity", entity);
				request.setAttribute("picList", picList);
				request.setAttribute("picYwlxList", picYwlxList);
				request.setAttribute("selectType", selectType);
				request.setAttribute("paperMap", SYSLoadManager.paper_map);
				request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
				request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
				request.setAttribute("toPage", toPage);
				request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
				request.setAttribute("user2CnDeptCode", SYSLoadManager.user2CnDeptCodeMap);
				
				//查看详细
				session.setAttribute("entity2",entity);
				session.setAttribute("picList2", picList);
			 }
			
			/*** 获得外检基本信息 及图片   ****/
			 List<YcInfo> ycInfoList = ycInfoService.findYcInfoByXtdabh(wjbh);
			 if(ycInfoList != null && ycInfoList.size() > 0){
				YcInfo ycInfo = ycInfoList.get(0);
				List<YcInfoPic> picList = ycInfoPicService.getPicsByXtdabh(wjbh);
				request.setAttribute("picListZh", picList);
				request.setAttribute("entityZh", ycInfo);
				request.setAttribute("verifyResult", ycInfo.getShjg());
				request.setAttribute("bz", ycInfo.getBz());
				request.setAttribute("yc_paper_map", SYSLoadManager.yc_paper_map);
					
					//保存打印listPic
				session.setAttribute("listPicZh", picList);
				session.setAttribute("listPic2", picList); 
					 
			}	
			 
			request.setAttribute("deptCode", deptCode);//记录部门编号
			
			return "zhsh_sh";
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 对非base64图片显示提供路径
	 * ywj
	 * 2014-8-5
	 * 
	 * */
	public void getNotBase64ToImagePath(){
		String picName = request.getParameter("picName");
		String dir = request.getParameter("dir");
		byte[] data = FileHelper.read(new File(SYSLoadManager.getPicStorePath() + File.separator + dir + File.separator + picName));
		if(picName != null){
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				os.write(data);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 对非base64图片显示提供路径
	 * ywj
	 * 2014-8-5
	 * 
	 * */
	public void getNotBase64ToImagePath2(){
		String picPath = request.getParameter("picPath");
		byte[] data = FileHelper.read(new File(picPath));
		if(picPath != null){
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				os.write(data);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将base64图片转为图片
	 * @return
	 */
	public String getBase64ToImage(){
		String picFilePath = request.getParameter("tplj");		
		if(picFilePath != null){
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				os.write(Base64Image.getBase64ToByte(picFilePath));
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 判断在同一时间，外检数据是否已被审核
	 * @throws IOException 
	 * 
	 * */
	public void queryShjg() throws IOException{
		try {
			String wjbh=request.getParameter("wjbh2");
			YcInfoExample ycInfoExample = new YcInfoExample();
			ycInfoExample.createCriteria().andXtdabhEqualTo(wjbh).andShjgEqualTo(WSH);
			List<YcInfo> list = ycInfoService.selectByExample(ycInfoExample);
			if(list!=null && list.size()>0){
				//未审核
				response.getWriter().println("200");
			}else{
				//已被审核
				response.getWriter().println("222");	
			}
		} catch (Exception e) {
			response.getWriter().println("300");	
		}
	}
	
	/**
	 * 外检审核-提交
	 * @at
	 * 
	 * */
	public String retuct(){
		try {
			String picId=request.getParameter("picId");
			String wjbh =request.getParameter("wjbh");
			String tg=request.getParameter("tg");
			String bz=request.getParameter("bz");
			if(StringUtils.isNotEmpty(bz)){
				bz=new String(bz.getBytes("iso-8859-1"),"utf-8");
			}
			//获得当前时间
			String create_time=DateTools.nowTime();
			//获得审核人
			SuperUser user = (SuperUser) request.getSession().getAttribute("user");  //得到当前用户   
			String name=user.getUserCode();
			String shrmc = user.getUserName();
			String shjg = "";
			if("0".equals(tg)){
				shjg = SHWTG;
			}else  if("1".equals(tg)){
				shjg = SHTG;
			}
			String sqlYcInfo="";
			String sqlPicInfo="";
			session.setAttribute("code",wjbh);
			String pid[]=picId.split(" ");
			List<YcInfoPic> pics=new ArrayList<YcInfoPic>();
			
			ycInfoService.ycVerify(create_time,name,shrmc,shjg,bz,wjbh,pid);

			//审核不通过
			if("0".equals(tg)){
				YcInfoPicExample example = new YcInfoPicExample();
				example.createCriteria().andXtdabhEqualTo(wjbh).andIdIn(Arrays.asList(pid));
				pics = ycInfoPicService.selectByExample(example );
				request.setAttribute("pics", pics);
				request.setAttribute("yc_paper_map", SYSLoadManager.yc_paper_map);
				//获得审核人及上传人
				YcInfoExample ycInfoExample = new YcInfoExample();
				ycInfoExample.createCriteria().andXtdabhEqualTo(wjbh);
				List<YcInfo> list = ycInfoService.selectByExample(ycInfoExample);
				if(list!=null && list.size()>0){
					YcInfo ycinfo=list.get(0);
					request.setAttribute("entity",ycinfo);
				}
				request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
    	        request.setAttribute("user2JybhMap", SYSLoadManager.user2JybhMap);//警员编号
    	        request.setAttribute("bz", bz);
				return "wjcy_hcd";
			}
			
			//审核通过 
            if(tg.equals("1")){
    			return "gdsh_list";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	} 
	
	/**
	 * 档案审核
	 */
	public void verify(){
		try {
			String picId=request.getParameter("picId");
			String xtdabh =request.getParameter("xtdabh");
			String tg=request.getParameter("tg");
			//获得当前时间
			String create_time=DateTools.nowTime();
			//获得审核人
			SuperUser user = (SuperUser) request.getSession().getAttribute("user");  //得到当前用户   
			String name = user.getUserCode();
			String shrmc = user.getUserName();
			String sfcs = "";
			String dazt = "";
			if("0".equals(tg)){//审核未通过
				sfcs = "1";//重扫
				dazt = ArcStatus.SHWTG_YW;
			}else  if("1".equals(tg)){//审核通过
				sfcs = "0";//不重扫
				dazt = ArcStatus.SHTG_YW;
			}
			String pid[]=picId.split(" ");
			
			arcBaseInfoService.baseInfoVerify(create_time,name,shrmc,dazt,xtdabh,sfcs,pid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 业务管理-综合审核
	 * ywj
	 * 2014-8-7
	 * 
	 * */
	public String verifyZh(){
		try {
			//获取图片
			String wjbh=request.getParameter("wjbh");
			String w=request.getParameter("w");//外检图片id数组
			String d=request.getParameter("d");//档案图片id数组
			String bz=request.getParameter("bz");
			if(StringUtils.isNotEmpty(bz)){
				bz=new String(bz.getBytes("iso-8859-1"),"utf-8");
			}
			String verifyResult=request.getParameter("verifyResult");  ///审核结果 0 不通过  1通过
			//根据审核结果修改外检信息
			String create_time=DateTools.nowTime();//获得当前时间
			SuperUser user = (SuperUser) request.getSession().getAttribute("user");  //得到当前用户   //获得审核人
			String name=user.getUserCode();
			String shrmc = user.getUserName();
			if("0".equals(verifyResult)){ 
					//档案不通过 ,外检不做修改
					if("".equals(w) && !"".equals(d) ){ 	
						String daIds[]=d.split(" ");
						String str=arcBaseInfoService.verifyNotPassOfArcBaseInfo(wjbh,daIds,name,create_time,wjbh,shrmc);
						if("1".equals(str)){
							return "gdsh_list";
						}else{
							return "error";
						}
					}
					//外检不通过,档案不做修改
	                if("".equals(d) && !"".equals(w)){ 
					    String wjIds[]=w.split(" ");
					    String str= arcBaseInfoService.verifyNotPassOfYcInfo(wjIds, create_time,name, bz, wjbh);
					    if("1".equals(str)){
					    	List<YcInfo> list = ycInfoService.findYcInfoByXtdabh(wjbh);
					    	if(list!=null && list.size()>0){
								YcInfo ycinfo=list.get(0);
								request.setAttribute("entity",ycinfo);
							}
					    	YcInfoPicExample example = new YcInfoPicExample();
							example.createCriteria().andXtdabhEqualTo(wjbh).andIdIn(Arrays.asList(wjIds));
							List<YcInfoPic> pics  = ycInfoPicService.selectByExample(example );
							request.setAttribute("code", wjbh);
							request.setAttribute("pics", pics);
							request.setAttribute("yc_paper_map", SYSLoadManager.yc_paper_map);
							request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
			    	        request.setAttribute("user2JybhMap", SYSLoadManager.user2JybhMap);//警员编号
			    	        request.setAttribute("bz", bz);
							return "wjcy_hcdZh";
						}else{
							return "error";
						}
					}
	                //外检档案都不通过
	                if( !"".equals(d) && !"".equals(w) ){ 
						//取出不通过图片id
						String wjIds[]=w.split(" ");
						String daIds[]=d.split(" ");
						String str=arcBaseInfoService.verifyBothNotPass(wjbh,wjIds,create_time,name,daIds, wjbh, bz,shrmc);
						if("1".equals(str)){
							//不通过，打印回执单信息
							List<YcInfo> list = ycInfoService.findYcInfoByXtdabh(wjbh);
					    	if(list!=null && list.size()>0){
								YcInfo ycinfo=list.get(0);
								request.setAttribute("entity",ycinfo);
							}
					    	YcInfoPicExample example = new YcInfoPicExample();
							example.createCriteria().andXtdabhEqualTo(wjbh).andIdIn(Arrays.asList(wjIds));
							List<YcInfoPic> pics  = ycInfoPicService.selectByExample(example );
							request.setAttribute("code", wjbh);
							request.setAttribute("pics", pics);
							request.setAttribute("yc_paper_map", SYSLoadManager.yc_paper_map);
							request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
			    	        request.setAttribute("user2JybhMap", SYSLoadManager.user2JybhMap);//警员编号
			    	        request.setAttribute("bz", bz);
							return "wjcy_hcdZh";
						}else{
							return "error";
						}					
					}
	                
	                if("".equals(w) && "".equals(d) ){ 	
						String str=arcBaseInfoService.verifyNotPassOfArcBaseInfo(wjbh,new String[0],name,create_time,wjbh,shrmc);
						if("1".equals(str)){
							return "gdsh_list";
						}else{
							return "error";
						}
					}
	                
				}else if("1".equals(verifyResult)){//外检档案审核都通过
					String str=arcBaseInfoService.verifyBothPass(wjbh, create_time, name,bz,shrmc);
					if("1".equals(str)){
						return "gdsh_list";
					}else{
						return "error";
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();	
			}
			return  "error";
		}
	
	
	/**
	 * 审核外检，没有图片显示
	 * 打印回执单
	 * 
	 * */
	public String noPicVeirfy(){
		try {
			String bz=request.getParameter("bz");
			String wjbh=request.getParameter("wjbh");
			if(StringUtils.isNotEmpty(bz)){
				bz=new String(bz.getBytes("iso-8859-1"),"utf-8");
			}
			SuperUser user = (SuperUser) request.getSession().getAttribute("user");  //得到当前用户   //获得审核人
			String name=user.getUserCode();
			String sqlYcInfo = "update yc_info set shr='"+name+"' where xtdabh='"+wjbh+"' ";
			jdbcTemplate.execute(sqlYcInfo);
			
			YcInfoExample ycInfoExample = new YcInfoExample();
			ycInfoExample.createCriteria().andXtdabhEqualTo(wjbh);
			List<YcInfo> list = ycInfoService.selectByExample(ycInfoExample);
			if(list!=null && list.size()>0){
				YcInfo ycinfo=list.get(0);
				request.setAttribute("entity",ycinfo);
				YcInfoPicExample example = new YcInfoPicExample();
				example.createCriteria().andXtdabhEqualTo(wjbh).andPicStatusEqualTo(SHWTG);
				List<YcInfoPic> pics = ycInfoPicService.selectByExample(example );
				request.setAttribute("pics", pics);
				
				arcBaseInfoService.noPicVeirfy(WSH,wjbh);
			}
	        request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
	        request.setAttribute("user2JybhMap", SYSLoadManager.user2JybhMap);
			request.setAttribute("code", wjbh);
			request.setAttribute("bz", bz);
			return "wjcy_hcd";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wjcy_hcd";
	}
	
	/**
	 * 通过回持单，打印
	 * 
	 * */
	public String getHek(){
		try {
			String bzt=request.getParameter("bz");
			String bz=new String(bzt.getBytes("iso-8859-1"),"utf-8");
			String wjbh=request.getParameter("wjbh");
			YcInfoExample ycInfoExample = new YcInfoExample();
			ycInfoExample.createCriteria().andXtdabhEqualTo(wjbh);
			List<YcInfo> list = ycInfoService.selectByExample(ycInfoExample);
			if(list!=null && list.size()>0){
				YcInfo ycinfo=list.get(0);
				request.setAttribute("entity",ycinfo);
				YcInfoPicExample example = new YcInfoPicExample();
				example.createCriteria().andXtdabhEqualTo(wjbh).andPicStatusEqualTo(SHWTG);
				List<YcInfoPic> pics = ycInfoPicService.selectByExample(example );
				request.setAttribute("pics", pics);
			}
			
			String ycInfoUpdate = "update yc_info set SHJG='" + SHWTG + "' where xtdabh='"+wjbh+"'";
			jdbcTemplate.execute(ycInfoUpdate);
			
	        request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
		    request.setAttribute("user2JybhMap", SYSLoadManager.user2JybhMap);
			request.setAttribute("code", wjbh);
			request.setAttribute("bz",bz);	
			return "wjcy_hcd";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 外检条码退办
	 * 删除该条码下的所有信息
	 * 
	 * 1.删除本地图片
	 * 2.删除外检图片表数据
	 * 3.删除外检主表数据
	 * 
	 * @author ywj
	 * 2013-8-6
	 */
	public void backOffice(){
		String wjbh = request.getParameter("wjbh");
		List<YcInfoPic> pics = ycInfoPicService.getPicsByXtdabh(wjbh);
		
		ycInfoService.backOffice(pics,wjbh);
				
	}
	
	/**
	 * 档案退办
	 * 删除该档案的所有信息
	 * 1.删除本地图片
	 * 2.删除档案图片表数据
	 * 3.删除档案主表数据
	 * 
	 * @author ywj
	 * 2013-8-6
	 */
	public void arcBaseBackOffice(){
		String xtdabh = request.getParameter("xtdabh");
		List<ArcPicInfo> pics = arcBaseInfoService.getPicInfoByXTDABH(xtdabh);
		
		arcBaseInfoService.arcBaseBackOffice(pics,xtdabh);		
	}
	
	/**
	 * 综合退办
	 */
	public void backOfficeZh(){
		String xtdabh = request.getParameter("xtdabh");
		List<YcInfoPic> pics = ycInfoPicService.getPicsByXtdabh(xtdabh);	
		List<ArcPicInfo> picsArc = arcBaseInfoService.getPicInfoByXTDABH(xtdabh);
		arcBaseInfoService.backOfficeZh(pics,xtdabh,picsArc);
	}
	
	/**
	 * 重扫申请、重扫审核列表查询
	 * 
	 * @return
	 * 
	 * @author ywj
	 * 2013-8-8
	 */
	public String queryReScanList(){
		response.setContentType("text/html;charset=UTF-8");
		String toPage = request.getParameter("toPage");
		String csType = request.getParameter("csType");
		
		try {
			List list_data = null;
			
			if(StringUtils.isEmpty(csType) || "ygd".equals(csType)){
				ArcBaseInfoExample arcBaseInfoExample = new ArcBaseInfoExample();
				//查询条件拼接
				ArcBaseInfoExample.Criteria ca = arcBaseInfoExample.createCriteria();
				if(entity!=null){
					if(StringUtils.isNotEmpty(entity.getXtdabh())){
						ca.andXtdabhLike("%" + entity.getXtdabh().trim().toUpperCase() + "%");
					}
					if(StringUtils.isNotEmpty(entity.getCwbh())){
						ca.andCwbhLike("%" + entity.getCwbh().trim().toUpperCase() + "%");
					}
					if(StringUtils.isNotEmpty(entity.getLsh())){
						ca.andLshLike("%" + entity.getLsh().trim() + "%");
					}
					if(StringUtils.isNotEmpty(entity.getYwlx())){
						ca.andYwlxEqualTo(entity.getYwlx().trim());
					}
					if(StringUtils.isNotEmpty(entity.getHpzl())){
						ca.andHpzlEqualTo(entity.getHpzl().trim());
					}
					if(StringUtils.isNotEmpty(entity.getHphm())){
						ca.andHphmLike("%" + entity.getHphm().trim().toUpperCase() + "%");
					}
					if(StringUtils.isNotEmpty(entity.getXh())){
						ca.andXhLike("%" + entity.getXh().trim().toUpperCase() + "%");
					}
					if(sdate!=null){
						ca.andCjsjGreaterThanOrEqualTo(sdate);
					}
					if(edate!=null){
						Calendar c = Calendar.getInstance();
						c.setTime(edate);
						c.add(Calendar.DAY_OF_MONTH, 1);
						ca.andCjsjLessThan(c.getTime());
					}
				}
							
				if("sq".equals(toPage)){
					ca.andDaztIn(Arrays.asList(new String[]{ArcStatus.YGD,ArcStatus.YRK}));
				}else if("sh".equals(toPage)){
					ca.andDaztEqualTo(ArcStatus.ARC_CSSQ);
				}
				//分页数据
				page = arcBaseInfoService.findArcBaseInfoPageList(index,arcBaseInfoExample);	
				//列表数据 
				list_data = page.getPagelist();
			}
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("toPage", toPage);
			request.setAttribute("sdate", sdate);
			request.setAttribute("edate", edate);
			request.setAttribute("csType", csType);
			request.setAttribute("user2CnDeptCode", SYSLoadManager.user2CnDeptCodeMap);
			request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "cssq_list";
	}
	
	/**
	 * 重扫申请，重扫审核
	 * @return 重扫申请，重扫审核页面
	 * 
	 * @author ywj
	 * 2013-8-8
	 */
	public String getArcBaseInfoByXtdabh(){
		response.setContentType("text/html;charset=UTF-8");
		String picPage = request.getParameter("picPage");
		String csType = request.getParameter("csType");
		try{
			//if("ygd".equals(csType)){
				String xtdabh = entity.getXtdabh();
				//获得档案基本信息
				List<ArcBaseInfo> list = arcBaseInfoService.getArcBaseInfoByXTDABH(xtdabh);
				
				if(list!=null && list.size() > 0){
					ArcBaseInfo arcBaseInfo = list.get(0);
					//获得档案照片信息
					List<ArcPicInfo> picList = arcBaseInfoService.getPicInfoByXTDABHAndStatus(xtdabh,picPage);
					request.setAttribute("csType", csType);
					request.setAttribute("entity", arcBaseInfo);
					request.setAttribute("picList", picList);
					request.setAttribute("picPage", picPage);
					request.setAttribute("paperMap", SYSLoadManager.paper_map);
					request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
					request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
					request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
					
					//查看详细
					session.setAttribute("entity2",arcBaseInfo);
					session.setAttribute("picList2", picList);
				}
			//}	
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		if("sh".equals(picPage) || "cx".equals(picPage)){
			return "cssh_sh";
		}
		return "cssq_sq";
	}
    
	/**
	 * 重扫申请
	 */
	public void verifyRescanApply(){
	try{
		String picIds = request.getParameter("picIds");
		String xtdabh = request.getParameter("xtdabh");
		String sm = request.getParameter("sm");
		if(StringUtils.isNotEmpty(sm)){
			sm=new String(sm.getBytes("iso-8859-1"),"utf-8");
		}
		String pid[]=picIds.split(" ");
		SuperUser user = (SuperUser) session.getAttribute("user");//获取登陆用户
		
		arcBaseInfoService.verifyRescanApply(pid,xtdabh,sm,user);
		
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	}
	
	/**
	 * 重扫审核
	 */
	public void rescanVerify(){
		response.setContentType("text/html;charset=UTF-8");
		//图片保存状态
		String saveStatus = "0"; //不可修改 
		String xtdabh = request.getParameter("xtdabh");
		String[] picIds = request.getParameterValues("picIds");
		String csType = request.getParameter("csType");
		String verifyResult = request.getParameter("verifyResult");
		SuperUser user = (SuperUser) session.getAttribute("user");//获取登陆用户
		//审核结果为通过
		if(verifyResult.equals("1")){
			saveStatus = "1";
		}
		if("ygd".equals(csType)){
			arcBaseInfoService.rescanVerify(xtdabh,picIds,saveStatus,ArcStatus.ARC_CSSH,user); //saveStatus 2 可修改
		}
		try {
			response.getWriter().println("<script>top.Dialog.alert('操作成功',function(){window.location='arcBaseInfo!queryReScanList.action?toPage=sh&csType=ygd';})</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 列表显示打印条码
	 * @return 
	 * @author ywj
	 * 2014-8-8
	 */
	public String listPrintCode(){
		try {
			//查询条件拼接
			ArcBaseInfoExample arcBaseInfoExample = new ArcBaseInfoExample();
			ArcBaseInfoExample.Criteria ca = arcBaseInfoExample.createCriteria();
			if(entity!=null){
				if(StringUtils.isNotEmpty(entity.getXtdabh())){
					ca.andXtdabhLike("%" + entity.getXtdabh().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getLsh())){
					ca.andLshLike("%" + entity.getLsh().trim()+ "%");
				}
				if(StringUtils.isNotEmpty(entity.getYwlx())){
					ca.andYwlxEqualTo(entity.getYwlx().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHpzl())){
					ca.andHpzlEqualTo(entity.getHpzl().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHphm())){
					ca.andHphmLike("%" + entity.getHphm().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getXh())){
					ca.andXhLike("%" + entity.getXh().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getDabh())){
					ca.andDabhLike("%" + entity.getDabh().trim() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getCwbh())){
					ca.andCwbhLike("%" + entity.getCwbh().trim().toUpperCase() + "%");
				}
				
				if(sdate!=null){
					ca.andCjsjGreaterThanOrEqualTo(sdate);
				}
				if(edate!=null){
					Calendar c = Calendar.getInstance();
					c.setTime(edate);
					c.add(Calendar.DAY_OF_MONTH, 1);
					ca.andCjsjLessThan(c.getTime());
				}
			}
			
			//分页数据
			page = arcBaseInfoService.findArcBaseInfoPageList(index, arcBaseInfoExample);
			//列表数据 
			List<ArcBaseInfo> list_data = page.getPagelist();
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("sdate", sdate);
			request.setAttribute("edate", edate);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "printCode_list";
	
	}
	
	/**
	 * 检查该车辆是否为嫌疑号牌
	 * @throws IOException
	 */
	public void checkIsBlackCar() throws IOException{
		String clsbdh = request.getParameter("clsbdh");
		boolean isBlackCar = arcBaseInfoService.isBlackCar(clsbdh);
		if(isBlackCar){
			response.getWriter().println("200");
		}else{
			response.getWriter().println("300");
		}
	}
	
	/**
	 * 设置为嫌疑车辆
	 * 200:设置成功
	 * 201：设置失败
	 */
	public void setToSuspicion()throws IOException{
		String xtdabh = request.getParameter("xtdabh");
		try{
			response.getWriter().println("200");
			List<ArcBaseInfo> list = arcBaseInfoService.getArcBaseInfoByXTDABH(xtdabh);
			if(list != null && list.size() > 0){
				ArcBaseInfo baseInfo = list.get(0);
				arcBaseInfoService.setToSuspicion(baseInfo);
			}else{
				response.getWriter().println("201");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().println("201");
		}
	}
	/**
	 *  归档审核中 合并条码  
	 *  为业务档案时，如果没有合并条码则弹出合并条码页面
	 * @return
	 */
	public String andHb(){
		String wjtm=request.getParameter("wjtm");
		String lsh=request.getParameter("lsh");
		request.setAttribute("xtdabh",wjtm);
		request.setAttribute("lsh",lsh);
		return "wjcy_hbtm";
	}
	
	/**
	 * 条码拆解
	 * @return
	 */
	public String tmcj(){
		String msg;
		try {
		    String wjbh=request.getParameter("wjbh");
		    String lsh=request.getParameter("lsh");
		    msg=arcBaseInfoService.cjtm(wjbh,lsh);
		} catch (Exception e) {
			msg = "数据异常";
		}
		request.setAttribute("msg", msg);
		return "tmcj";
	}
	
	/**
	 * 档案归档列表查询
	 * @return 
	 * @author ywj
	 * 2014-8-11
	 */
	public String listGd(){
		try {
			//查询条件拼接
			ArcBaseInfoExample arcBaseInfoExample = new ArcBaseInfoExample();
			ArcBaseInfoExample.Criteria ca = arcBaseInfoExample.createCriteria();
			ca.andDaztEqualTo(ArcStatus.SHTG_YW);
			if(entity!=null){
				if(StringUtils.isNotEmpty(entity.getXtdabh())){
					ca.andXtdabhLike("%" + entity.getXtdabh().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getLsh())){
					ca.andLshLike("%" + entity.getLsh().trim() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getYwlx())){
					ca.andYwlxEqualTo(entity.getYwlx().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHpzl())){
					ca.andHpzlEqualTo(entity.getHpzl().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHphm())){
					ca.andHphmLike("%" + entity.getHphm().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getXh())){
					ca.andXhLike("%" + entity.getXh().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getDabh())){
					ca.andDabhLike("%" + entity.getDabh().trim() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getCwbh())){
					ca.andCwbhLike("%" + entity.getCwbh().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getShr())){
					ca.andShrLike("%" + entity.getShr().trim() + "%");
				}
				if(sdate!=null){
					ca.andCjsjGreaterThanOrEqualTo(sdate);
				}
				if(edate!=null){
					Calendar c = Calendar.getInstance();
					c.setTime(edate);
					c.add(Calendar.DAY_OF_MONTH, 1);
					ca.andCjsjLessThan(c.getTime());
				}
			}
			
			//分页数据
			page = arcBaseInfoService.findArcBaseInfoPageList(index, arcBaseInfoExample);
			//列表数据 
			List<ArcBaseInfo> list_data = page.getPagelist();
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
			request.setAttribute("sdate", sdate);
			request.setAttribute("edate", edate);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "dagd_list";
	
	}
	
	/**
	 * 档案预归档
	 * @return
	 * 
	 * @author ywj
	 * 2014-8-11
	 */
	public String preDagd(){
		response.setContentType("text/html;charset=UTF-8");
		try{
			String selectType = request.getParameter("selectType");
			String toPage = request.getParameter("toPage");
			String wjbh = request.getParameter("wjbh");
			/*** 获取档案基本信息及图片 ****/
			List<ArcBaseInfo> list = arcBaseInfoService.getArcBaseInfoByXTDABH(wjbh);
			if(list != null && list.size() > 0){
				ArcBaseInfo entity = list.get(0);
				
				boolean bFiling = arcBaseInfoService.completeArcBaseInfo(entity);
				if(!bFiling){
					response.getWriter().println("<script>alert('无法获取号牌信息，操作中止！');top.Dialog.close();</script>");
					return null;
				}
				
				// 照片信息列表
				List<ArcPicInfo> picList = arcBaseInfoService.getPicInfoByXTDABH(wjbh);
				// 获得当前图片列表中共有多少种业务类型
				List<String> picYwlxList = new ArrayList<String>();
				for (ArcPicInfo pic : picList) {
					String picYwlx = pic.getYwlx();
					String picTimes = pic.getSmcs();
					if(!picYwlxList.contains(picYwlx+","+picTimes)){
						picYwlxList.add(picYwlx+","+picTimes);
					}
				}
				request.setAttribute("entity", entity);
				request.setAttribute("picList", picList);
				request.setAttribute("picYwlxList", picYwlxList);
				request.setAttribute("selectType", selectType);
				request.setAttribute("paperMap", SYSLoadManager.paper_map);
				request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
				request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
				request.setAttribute("toPage", toPage);
				request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
				request.setAttribute("user2CnDeptCode", SYSLoadManager.user2CnDeptCodeMap);
				
				//查看详细
				session.setAttribute("entity2",entity);
				session.setAttribute("picList2", picList);
			 }
			
			/*** 获得外检基本信息 及图片   ****/
			 List<YcInfo> ycInfoList = ycInfoService.findYcInfoByXtdabh(wjbh);
			 if(ycInfoList != null && ycInfoList.size() > 0){
				YcInfo ycInfo = ycInfoList.get(0);
				List<YcInfoPic> picList = ycInfoPicService.getPicsByXtdabh(wjbh);
				request.setAttribute("picListZh", picList);
				request.setAttribute("entityZh", ycInfo);
				request.setAttribute("verifyResult", ycInfo.getShjg());
				request.setAttribute("bz", ycInfo.getBz());
				request.setAttribute("yc_paper_map", SYSLoadManager.yc_paper_map);
					
					//保存打印listPic
				session.setAttribute("listPicZh", picList);
				session.setAttribute("listPic2", picList); 
					 
			}	
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return "dagd_gd";
	}
	
	/**
	 * 档案归档
	 * @return
	 * 
	 * @author ywj
	 * 2014-8-12
	 * @throws IOException 
	 */
	public void dagd() throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		SuperUser user = (SuperUser) session.getAttribute("user");//获取登陆用户
		String arcId = request.getParameter("arcId");//档案id
		String storeNumber = request.getParameter("storageNumber");//储位编号
		ArcBaseInfo baseInfo = arcBaseInfoService.selectBaseByPrimaryKey(arcId);
		
		/*if(StringUtils.isNotEmpty(storeNumber)){
			baseInfo.setCwbh(storeNumber);
		}*/
		baseInfo.setGdr(user.getUserCode());
		baseInfo.setGdrmc(user.getUserName());
		baseInfo.setGdsj(new Date());
		baseInfo.setDazt(ArcStatus.YGD);//档案状态 :已归档
		//获取档案类型，1为业务档案2为历史档案，历史档案直接进行归档操作
		String arcType = request.getParameter("arcType");
		try {
			if(BUSINESS_ARC.equals(arcType)){//如果是业务档案
				ArcBaseInfoExample example =  new ArcBaseInfoExample();
				Criteria criteria = example.createCriteria();
				if(StringUtils.isNotEmpty(storeNumber)){
					criteria.andCwbhEqualTo(storeNumber);
				}
				List  list = arcBaseInfoService.selectByExample(example);
				if(list.size()>0){
					response.getWriter().write("<script>alert('该储位编号已经被占用，请重新选择！');top.Dialog.close();window.location.href='arcBaseInfo!listGd.action';top.Dialog.close();</script>");
				}else{
					boolean isSuccess = arcBaseInfoService.baseInfoGd(baseInfo,storeNumber,user);
					if(isSuccess){
						ArcBaseInfo arcBaseInfo = arcBaseInfoService.selectBaseByPrimaryKey(arcId);
						String openUrl="sys/barcode!printArcLabel.action?xtdabh="+arcBaseInfo.getXtdabh(); //转向网页的地址;
						response.getWriter().println("<script>top.Dialog.confirm('归档成功！<br> 是否打印条码信息?'" +
								",function(){window.open('"+openUrl+"','打印条码','width=420,height=400');window.location='arcBaseInfo!listGd.action';}," +
								"function(){window.location='arcBaseInfo!listGd.action';});</script>");
					}else{
						response.getWriter().write("<script>alert('操作失败！');top.Dialog.close();window.location.href='arcBaseInfo!listGd.action';top.Dialog.close();</script>");
					}
				}
			}else if(HISTORY_ARC.equals(arcType)){//如果是历史档案
				boolean isSuccess = arcBaseInfoService.updateBaseInfoGd(baseInfo,user);
				if(isSuccess){
					ArcBaseInfo arcBaseInfo = arcBaseInfoService.selectBaseByPrimaryKey(arcId);
					String openUrl="sys/barcode!printArcLabel.action?xtdabh="+arcBaseInfo.getXtdabh(); 
					response.getWriter().println("<script>top.Dialog.confirm('归档成功！<br> 是否打印条码信息?'" +
							",function(){window.open('"+openUrl+"','打印条码','width=350,height=400');window.location='arcBaseInfo!listGd.action';}," +
							"function(){top.Dialog.close();window.location='arcBaseInfo!listGd.action';});</script>");
				}else{
					response.getWriter().write("<script>alert('操作失败！');top.Dialog.close();window.location.href='arcBaseInfo!listGd.action';top.Dialog.close();</script>");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<script>alert('操作失败！');top.Dialog.close();window.location.href='arcBaseInfo!listGd.action';top.Dialog.close();</script>");
		}
	}
	
	/**
	 * 重扫查询
	 * @return
	 * 
	 * @author ywj
	 * 2014-8-12
	 */
	public String queryReScan(){
		String csType = request.getParameter("csType");
		try {
			ArcRescanInfoExample arcRescanExample = new ArcRescanInfoExample();
			//查询条件拼接
			com.pd.arc.model.ArcRescanInfoExample.Criteria ca = arcRescanExample.createCriteria();
			if(entity!=null){
				if(StringUtils.isNotEmpty(entity.getXtdabh())){
					ca.andXtdabhLike("%" + entity.getXtdabh().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getLsh())){
					ca.andLshLike("%" + entity.getLsh().trim() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getYwlx())){
					ca.andYwlxEqualTo(entity.getYwlx().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHpzl())){
					ca.andHpzlEqualTo(entity.getHpzl().trim());
				}
				if(StringUtils.isNotEmpty(entity.getHphm().trim())){
					ca.andHphmLike("%" + entity.getHphm().trim().toUpperCase() + "%");
				}
				if(StringUtils.isNotEmpty(entity.getXh())){
					ca.andXhLike("%" + entity.getXh().trim().toUpperCase() + "%");
				}
				if(sdate!=null){
					ca.andCreateTimeGreaterThanOrEqualTo(sdate);
				}
				if(edate!=null){
					Calendar c = Calendar.getInstance();
					c.setTime(edate);
					c.add(Calendar.DAY_OF_MONTH, 1);
					ca.andCreateTimeLessThan(c.getTime());
				}
				
			}
			if(StringUtils.isNotEmpty(csType)){
				if("ygd".equals(csType)){
					ca.andSfygdEqualTo("1");
				}else{
					ca.andSfygdEqualTo("0");
				}
			}
			
			
			//分页数据
			Pagination page = arcReScanInfoService.findArcRescanInfoPageList(index, arcRescanExample);
			//列表数据 
			List<ArcBaseInfoAndRescanInfo> list_data = page.getPagelist();
			System.out.println("----------list size= " + list_data.size() + "---------");
			
			request.setAttribute("list_data", list_data);
			request.setAttribute("page", page);
			request.setAttribute("entity", entity);
			request.setAttribute("csType", csType);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("sdate", sdate);
			request.setAttribute("edate", edate);
			request.setAttribute("user2CnDeptCode", SYSLoadManager.user2CnDeptCodeMap);
			request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return "cscx_list";
	}
	
	//生成wjbh条码
	public String write39CodeImage5(){
		String code=request.getParameter("code");
		if(code != null){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedImage image = BarcodeTools.getCode128(code);
			try {
			    
				ImageIO.write(image, "gif", baos);
				OutputStream os = response.getOutputStream();
				os.flush();  
				os.write(baos.toByteArray());
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 外检显示大图
	* @Title: ycInfoShowBgPic 
	* @Description:
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author ywj
	* 2014-8-14
	 */
	public String ycInfoShowBigPic(){
		try {
			/**
			 * 获取当前图片的index,以及当前图片的路径
			 */
			String curIndex = request.getParameter("curIndex");
			String xtdabh = request.getParameter("xtdabh");
			
			List<YcInfoPic> ycPic_list = ycInfoPicService.getPicsByXtdabh(xtdabh);
			List<YcInfoPic> newList=new ArrayList<YcInfoPic>();
			int currentIndex = Integer.parseInt(curIndex);
			int maxIndex = ycPic_list.size()-1;
			for (int i = currentIndex; i <= maxIndex; i++) {
				newList.add(ycPic_list.get(i));
			}
			for (int i = 0; i < currentIndex; i++) {
				newList.add(ycPic_list.get(i));
			}
			request.setAttribute("ycPic_list", newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ycInfo_showBigPic";
	}
	
	/**
	 * 档案显示大图
	* @Title: arcBaseShowBigPic 
	* @Description: 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author ywj
	* 2014-8-14
	 */
	public String arcBaseShowBigPic(){
		try {
			/**
			 * 获取当前图片的index,以及当前图片的路径
			 */
			String curIndex = request.getParameter("curIndex");
			String xtdabh = request.getParameter("xtdabh");
			List<ArcPicInfo> arcBasePic_list = arcBaseInfoService.getPicInfoByXTDABH(xtdabh);
			List<ArcPicInfo> newList=new ArrayList<ArcPicInfo>();
			int currentIndex = Integer.parseInt(curIndex);
			int maxIndex = arcBasePic_list.size()-1;
			for (int i = currentIndex; i <= maxIndex; i++) {
				newList.add(arcBasePic_list.get(i));
			}
			for (int i = 0; i < currentIndex; i++) {
				newList.add(arcBasePic_list.get(i));
			}
			request.setAttribute("pic_list", newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "arcBase_showBigPic";
	}
	
	/**
	 * 根据系统档案编号获取档案扫描图片
	 * @author ywj
	 * 2014-8-25
	 */
	public String getPicsByXtdabh(){
		try{
			String selectType = request.getParameter("selectType");
			String xtdabh = request.getParameter("xtdabh");
			/*** 获取档案基本信息及图片 ****/
			List<ArcBaseInfo> list = arcBaseInfoService.getArcBaseInfoByXTDABH(xtdabh);
			if(list != null && list.size() > 0){
				ArcBaseInfo entity = list.get(0);
				// 照片信息列表
				List<ArcPicInfo> picList = arcBaseInfoService.getPicInfoByXTDABH(xtdabh);
				// 获得当前图片列表中共有多少种业务类型
				List<String> picYwlxList = new ArrayList<String>();
				for (ArcPicInfo pic : picList) {
					String picYwlx = pic.getYwlx();
					String picTimes = pic.getSmcs();
					if(!picYwlxList.contains(picYwlx+","+picTimes)){
						picYwlxList.add(picYwlx+","+picTimes);
					}
				}
				request.setAttribute("entity", entity);
				request.setAttribute("picList", picList);
				request.setAttribute("picYwlxList", picYwlxList);
				request.setAttribute("selectType", selectType);
				request.setAttribute("paperMap", SYSLoadManager.paper_map);
				request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
				request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
				request.setAttribute("user2CnName", SYSLoadManager.user2CnNameMap);
				request.setAttribute("user2CnDeptCode", SYSLoadManager.user2CnDeptCodeMap);
			 }
			
			/*** 获得外检基本信息 及图片   ****/
			 List<YcInfo> ycInfoList = ycInfoService.findYcInfoByXtdabh(xtdabh);
			 if(ycInfoList != null && ycInfoList.size() > 0){
				YcInfo ycInfo = ycInfoList.get(0);
				List<YcInfoPic> picList = ycInfoPicService.getPicsByXtdabh(xtdabh);
				request.setAttribute("picListZh", picList);
				request.setAttribute("entityZh", ycInfo);
				request.setAttribute("verifyResult", ycInfo.getShjg());
				request.setAttribute("bz", ycInfo.getBz());
				request.setAttribute("yc_paper_map", SYSLoadManager.yc_paper_map);
			}	
			if("arc".equals(selectType)){
				return "showArcPic";	
			}else if("all".equals(selectType)){
				return "showAllPic";
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询嫌疑车辆
	 * @return
	 */
	public String queryBlackCar() {
		try{
			String hphm = request.getParameter("hphm");//号牌号码
			String clsbdh = request.getParameter("clsbdh");//车辆识别代号
			String xtdabh = request.getParameter("xtdabh");//系统档案编号
			String ywlx = request.getParameter("ywlx");//业务类型
			
			ArcBlackCarExample example = new ArcBlackCarExample();
			com.pd.arc.model.ArcBlackCarExample.Criteria ca = example.createCriteria();
			if(StringUtils.isNotEmpty(hphm)){
				ca.andHphmEqualTo(hphm.trim().toUpperCase());
			}
			if(StringUtils.isNotEmpty(clsbdh)){
				ca.andClsbdhEqualTo(clsbdh.trim().toUpperCase());
			}
			if(StringUtils.isNotEmpty(xtdabh)){
				ca.andXtdabhEqualTo(xtdabh.trim().toUpperCase());
			}
			if(StringUtils.isNotEmpty(ywlx)){
				ca.andYwlxEqualTo(ywlx.trim());
			}
			
			page = arcBaseInfoService.findBlackCarPageList(index,example);
			List<ArcBlackCar> list_data = page.getPagelist();
			request.setAttribute("list_data", list_data);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("page", page);
			request.setAttribute("hphm", hphm);
			request.setAttribute("clsbdh", clsbdh);
			request.setAttribute("xtdabh", xtdabh);
			request.setAttribute("ywlx", ywlx);
			//TODO
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "blackcar_list";
	}
	
	/**
	 * 嫌疑车辆解锁
	 */
	public void unlock(){
		String xtdabh = request.getParameter("xtdabh");
		arcBaseInfoService.unlock(xtdabh);
	}
	
	/**
	 * 流水查询
	 * @return
	 */
	public String flowQuery(){
		String xtdabh = request.getParameter("xtdabh");
		List<ArcStatusRecord> list = arcBaseInfoService.flowQuery(xtdabh);
		request.setAttribute("list", list);
		request.setAttribute("arcStatus", SYSLoadManager.dazt_map);
		return "flowQuery";
	}
	
	
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public void setArcBaseInfoService(ArcBaseInfoService arcBaseInfoService) {
		this.arcBaseInfoService = arcBaseInfoService;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setJdbcTemplate1(JdbcTemplate jdbcTemplate1) {
		this.jdbcTemplate1 = jdbcTemplate1;
	}
	
	public ArcBaseInfo getEntity() {
		return entity;
	}

	public void setEntity(ArcBaseInfo entity) {
		this.entity = entity;
	}

	public void setYcInfoPicService(YcInfoPicService ycInfoPicService) {
		this.ycInfoPicService = ycInfoPicService;
	}

	public void setYcInfoService(YcInfoService ycInfoService) {
		this.ycInfoService = ycInfoService;
	}

	public void setArcReScanInfoService(ArcReScanInfoService arcReScanInfoService) {
		this.arcReScanInfoService = arcReScanInfoService;
	}
	
	

}


