package com.pd.wjyw.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pd.right.model.SuperUser;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.BaseAction;
import com.pd.system.startup.SYSLoadManager;
import com.pd.webservice.util.StringUtil;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoPicExample;
import com.pd.wjyw.service.YcInfoPicService;
import com.pd.wjyw.service.YcInfoService;
import com.pd.wjyw.service.YcPaperService;

/**
 * 外检信息action控制器
* @ClassName: YcInfoAction 
* @Description: TODO
* @author zl
* @date 2014-8-4 上午11:32:14 
*
 */
public class YcInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass());
	private String id;
	private int index =1;
	private YcInfoPicService ycInfoPicService;
	private YcPaperService ycPaperService;
	
	private YcInfoService ycInfoService;
	private YcInfoExample ycInfoExample = new YcInfoExample();
	private YcInfo ycinfo;
	private String sdate;
	private String edate;
	private List<YcInfoPic> picList;
	
	/**
	 * 回执单打印
	* @Title: ycInfo_hzd 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-19
	 */
	public String ycInfo_hzd(){
		YcInfo ycinfo = new YcInfo();
		try {
			/**
			 * 根据id获取外检主表信息
			 */
			ycinfo = ycInfoService.getEntity(id);
			
			/**
			 * 根据id获取外检图片表信息
			 */
			YcInfoPicExample ycInfoPicExample = new YcInfoPicExample();
			YcInfoPicExample.Criteria pic_ca = ycInfoPicExample.createCriteria();
			pic_ca.andYcinfoIdEqualTo(id);
			pic_ca.andPicStatusEqualTo("N");
			List<YcInfoPic> ycPic_list = new ArrayList<YcInfoPic>();
			ycPic_list = ycInfoPicService.selectByExample(ycInfoPicExample);
			/**
			 * 将外检主表信息和图片集合传递到页面显示
			 */
			request.setAttribute("ycinfo", ycinfo);
			request.setAttribute("paperMap", ycPaperService.queryToMap(null));
			request.setAttribute("ycPic_list", ycPic_list);
		} catch (IOException e) {
			log.error("query error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("query error......");
			e.printStackTrace();
		} 
		return "ycInfo_hzd";
	}
	/**
	 * 外检信息审核操作
	* @Title: ycInfo_audit 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-8
	 */
	public void ycInfo_audit(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		String opt = request.getParameter("opt");
		try {
			/**
			 * 操作如果为退办则做删除处理
			 */
			if(opt.equals("del")){
				ycInfoService.delete(id);
			}else{
				ycinfo.setShr(getSessionUser().getUserCode());
				ycinfo.setShrmc(getSessionUser().getUserName());
				ycinfo.setShsj(new Date());
				ycInfoService.update(ycinfo, picList,getSessionUser());
			}
			msg = "审核操作成功";
			LogInfo.info("外检信息审核", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "外检信息审核");
			if(ycinfo.getShjg().equals("N")){
				String openUrl = "ycInfo!ycInfo_hzd.action?id="+id;
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.open('"+openUrl+"','打印回执单','width=600,height=450');window.location='ycInfo!ycInfo_check.action';</script>");
			}else{
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycInfo!ycInfo_check.action?ycinfo.shjg=S';</script>");
			}
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			msg = "审核操作失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycInfo!ycInfo_check.action?ycinfo.shjg=S';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}
	/**
	 * 外检信息审核页面
	* @Title: ycInfo_checkView 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-7
	 */
	public String ycInfo_checkView(){
		YcInfo ycinfo = new YcInfo();
		try {
			/**
			 * 根据id获取外检主表信息
			 */
			ycinfo = ycInfoService.getEntity(id);
			
			/**
			 * 根据id获取外检图片表信息
			 */
			YcInfoPicExample ycInfoPicExample = new YcInfoPicExample();
			YcInfoPicExample.Criteria pic_ca = ycInfoPicExample.createCriteria();
			pic_ca.andYcinfoIdEqualTo(id);
			List<YcInfoPic> ycPic_list = new ArrayList<YcInfoPic>();
			ycPic_list = ycInfoPicService.selectByExample(ycInfoPicExample);
			/**
			 * 将外检主表信息和图片集合传递到页面显示
			 */
			request.setAttribute("ycinfo", ycinfo);
			request.setAttribute("ycPic_list", ycPic_list);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ycInfo_checkView";
	}
	
	/**
	 * 外检查看信息
	* @Title: ycInfo_View 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-8
	 */
	public String ycInfo_view(){
		YcInfo ycinfo = new YcInfo();
		try {
			/**
			 * 根据id获取外检主表信息
			 */
			ycinfo = ycInfoService.getEntity(id);
			
			/**
			 * 根据id获取外检图片表信息
			 */
			YcInfoPicExample ycInfoPicExample = new YcInfoPicExample();
			YcInfoPicExample.Criteria pic_ca = ycInfoPicExample.createCriteria();
			pic_ca.andYcinfoIdEqualTo(id);
			List<YcInfoPic> ycPic_list = new ArrayList<YcInfoPic>();
			ycPic_list = ycInfoPicService.selectByExample(ycInfoPicExample);
			/**
			 * 将外检主表信息和图片集合传递到页面显示
			 */
			request.setAttribute("ycinfo", ycinfo);
			request.setAttribute("ycPic_list", ycPic_list);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ycInfo_view";
	}
	
	/**
	 * 图片查看
	* @Title: ycInfo_picView 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-11
	 */
	public String ycInfo_picView(){
		try {
			/**
			 * 获取当前图片的index,以及当前图片的路径
			 */
			String curIndex = request.getParameter("curIndex");
			String curPicPath = request.getParameter("curPicPath");
			/**
			 * 根据id获取外检图片表信息
			 */
			YcInfoPicExample ycInfoPicExample = new YcInfoPicExample();
			YcInfoPicExample.Criteria pic_ca = ycInfoPicExample.createCriteria();
			pic_ca.andYcinfoIdEqualTo(id);
			List<YcInfoPic> ycPic_list = new ArrayList<YcInfoPic>();
			ycPic_list = ycInfoPicService.selectByExample(ycInfoPicExample);
			
			List<YcInfoPic> newList=new ArrayList<YcInfoPic>();
			int currentIndex = Integer.parseInt(curIndex);
			int maxIndex = ycPic_list.size()-1;
			for (int i = currentIndex; i <= maxIndex; i++) {
				newList.add(ycPic_list.get(i));
			}
			for (int i = 0; i < currentIndex; i++) {
				newList.add(ycPic_list.get(i));
			}
			/**
			 * 将外检主表信息和图片集合传递到页面显示
			 */
			request.setAttribute("paperMap", ycPaperService.queryToMap(null));
			request.setAttribute("curIndex", curIndex);
			request.setAttribute("ycPic_list", newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ycInfo_picView";
	}
	
	/**
	 * 获取当前图片路径在list集合里面的index位置
	* @Title: getListIndex 
	* @Description: TODO
	* @param @param list
	* @param @param picPath
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2014-8-12
	 */
	private int getListIndex(List<YcInfoPic> list, String picPath){
		for (int i = 0, len = list.size(); i < len; i++) {
			if(list.get(i).getPicpath().equals(picPath)){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 打印外检查验记录表
	* @Title: wjcy_print 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-6-3
	 */
	public String wjcyd_print(){
		try {
			/**
			 * 获取外检图片信息
			 */
			YcInfoPicExample ycInfoPicExample = new  YcInfoPicExample();
			YcInfoPicExample.Criteria ca = ycInfoPicExample.createCriteria();
			ca.andYcinfoIdEqualTo(id);
			List<YcInfoPic> listPic=ycInfoPicService.selectByExample(ycInfoPicExample);
			/**
			 * 获取外检主表信息
			 */
			YcInfo ycinfo = ycInfoService.getEntity(id);
			request.setAttribute("picList", listPic);
			request.setAttribute("ycinfo", ycinfo);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wjcyd_print";
	}
	
	/**
	 * 条码查询，主要用户条码重打
	* @Title: ycInfo_codeQuery 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-6
	 */
	public String ycInfo_codeQuery(){
		try{
			YcInfoExample.Criteria criteria = ycInfoExample.createCriteria();
			/**
			 * 根据条件查询外检主表信息
			 */
			if(null != ycinfo){
				if(ycinfo.getXtdabh() != null && ycinfo.getXtdabh().trim().length() > 0){
					criteria.andXtdabhLike("%"+ycinfo.getXtdabh().toUpperCase().trim()+"%");
				}
				if(ycinfo.getHpzl() != null && ycinfo.getHpzl().trim().length() > 0){
					criteria.andHpzlEqualTo(ycinfo.getHpzl().trim());
				}
				if(ycinfo.getYwlx() != null && ycinfo.getYwlx().trim().length() > 0){
					criteria.andYwlxEqualTo(ycinfo.getYwlx().trim());
				}
				if(ycinfo.getHphm() != null && ycinfo.getHphm().trim().length() > 0){
					criteria.andHphmLike("%"+ycinfo.getHphm().toUpperCase().trim()+"%");
				}
				if(ycinfo.getLsh() != null && ycinfo.getLsh().trim().length() > 0){
					criteria.andLshLike("%"+ycinfo.getLsh().trim()+"%");
				}
				if(ycinfo.getShjg() != null && ycinfo.getShjg().trim().length() > 0){
					criteria.andShjgEqualTo(ycinfo.getShjg().trim());
				}
				if(ycinfo.getSfwj() != null && ycinfo.getSfwj().trim().length() > 0){
					criteria.andSfwjEqualTo(ycinfo.getSfwj().trim());
				}
				if(ycinfo.getStatus() != null && ycinfo.getStatus().trim().length() > 0){
					criteria.andStatusEqualTo(ycinfo.getStatus().trim());
				}
			}
			if(sdate!=null && sdate.trim().length()>0){
				criteria.andCjsjGreaterThanOrEqualTo(sdate);
			}
			if(edate!=null && edate.trim().length()>0){
				criteria.andCCjsjLessThanOrEqualTo(edate);
			}
			ycInfoExample.setOrderByClause(" XTDABH DESC");
			page = ycInfoService.findPageList(index, ycInfoExample);

			List<YcInfo> list = page.getPagelist();//ycInfoService.query(ycInfoExample);
			request.setAttribute("list_data", list);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ycInfo_codeQuery";
	}
	
	/**
	 * 外检审核
	* @Title: ycInfo_check 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-6
	 */
	public String ycInfo_check(){
		try{
			YcInfoExample.Criteria criteria = ycInfoExample.createCriteria();
			SuperUser user = getSessionUser();
			String deptcode = user.getDeptCode();

			if(!deptcode.equals("001")){
				ycinfo.setDeptcode(deptcode);
				criteria.andDeptcodeEqualTo(ycinfo.getDeptcode());
			}
			if(null != ycinfo){
				if(ycinfo.getXtdabh() != null && ycinfo.getXtdabh().trim().length() > 0){
					criteria.andXtdabhLike("%"+ycinfo.getXtdabh().toUpperCase().trim()+"%");
				}
				if(ycinfo.getHpzl() != null && ycinfo.getHpzl().trim().length() > 0){
					criteria.andHpzlEqualTo(ycinfo.getHpzl().trim());
				}
				if(ycinfo.getYwlx() != null && ycinfo.getYwlx().trim().length() > 0){
					criteria.andYwlxEqualTo(ycinfo.getYwlx().trim());
				}
				if(ycinfo.getHphm() != null && ycinfo.getHphm().trim().length() > 0){
					criteria.andHphmLike("%"+ycinfo.getHphm().toUpperCase().trim()+"%");
				}
				if(ycinfo.getLsh() != null && ycinfo.getLsh().trim().length() > 0){
					criteria.andLshLike("%"+ycinfo.getLsh().trim()+"%");
				}
				if(ycinfo.getShjg() != null && ycinfo.getShjg().trim().length() > 0){
					criteria.andShjgEqualTo(ycinfo.getShjg().trim());
				}
				if(ycinfo.getStatus() != null && ycinfo.getStatus().trim().length() > 0){
					criteria.andStatusEqualTo(ycinfo.getStatus().trim());
				}
				if(ycinfo.getDeptcode() != null && ycinfo.getDeptcode().length() > 0){
					criteria.andDeptcodeEqualTo(ycinfo.getDeptcode());
				}
			}
			/**
			 * 查询创建人不为空的记录
			 */
			criteria.andCjrIsNotNull();
			/**
			 * 审核有外检的记录
			 */
			criteria.andSfwjEqualTo("Y");
			if(sdate!=null && sdate.trim().length()>0){
				criteria.andCjsjGreaterThanOrEqualTo(sdate);
			}
			if(edate!=null && edate.trim().length()>0){
				criteria.andCCjsjLessThanOrEqualTo(edate);
			}
			ycInfoExample.setOrderByClause(" CJSJ DESC");
			page = ycInfoService.findPageList(index, ycInfoExample);
		
			List<YcInfo> list = page.getPagelist();//ycInfoService.query(ycInfoExample);
			
			request.setAttribute("list_data", list);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ycInfo_check";
	}
	
	/**
	 * 外检随机查询
	* @Title: randomQuery 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-9-24
	 */
	public String randomQuery(){
		try{
			Map map = new HashMap();
			YcInfoExample.Criteria criteria = ycInfoExample.createCriteria();
			String percent = request.getParameter("percent");
			if(!StringUtil.isEmpty(percent)){
				map.put("percent", percent);
			}else{
				map.put("percent", "10");
			}
			page = ycInfoService.getPageByExample(index, map);
			map.put("start",(index*page.getPageSize()-page.getPageSize())+1);
			map.put("limit",(page.getPageSize()+Integer.parseInt(map.get("start").toString())-1));
			List<YcInfo> list = ycInfoService.selectByRandomExampleToPage(map);

			request.setAttribute("list_data", list);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
			request.setAttribute("percent", (String)map.get("percent"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ycInfo_randomQuery";
	}
	
	/**
	 * 外检信息查询
	* @Title: query 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	public String query(){
		try{
			YcInfoExample.Criteria criteria = ycInfoExample.createCriteria();
			if(null != ycinfo){
				if(ycinfo.getXtdabh() != null && ycinfo.getXtdabh().trim().length() > 0){
					criteria.andXtdabhLike("%"+ycinfo.getXtdabh().toUpperCase().trim()+"%");
				}
				if(ycinfo.getHpzl() != null && ycinfo.getHpzl().trim().length() > 0){
					criteria.andHpzlEqualTo(ycinfo.getHpzl().trim());
				}
				if(ycinfo.getYwlx() != null && ycinfo.getYwlx().trim().length() > 0){
					criteria.andYwlxEqualTo(ycinfo.getYwlx().trim());
				}
				if(ycinfo.getHphm() != null && ycinfo.getHphm().trim().length() > 0){
					criteria.andHphmLike("%"+ycinfo.getHphm().toUpperCase().trim()+"%");
				}
				if(ycinfo.getLsh() != null && ycinfo.getLsh().trim().length() > 0){
					criteria.andLshLike("%"+ycinfo.getLsh().trim()+"%");
				}
				if(ycinfo.getShjg() != null && ycinfo.getShjg().trim().length() > 0){
					criteria.andShjgEqualTo(ycinfo.getShjg().trim());
				}
				if(ycinfo.getStatus() != null && ycinfo.getStatus().trim().length() > 0){
					criteria.andStatusEqualTo(ycinfo.getStatus().trim());
				}
				if(ycinfo.getDeptcode() != null && ycinfo.getDeptcode().length() > 0){
					criteria.andDeptcodeEqualTo(ycinfo.getDeptcode());
				}
			}
			if(sdate!=null && sdate.trim().length()>0){
				criteria.andCjsjGreaterThanOrEqualTo(sdate);
			}
			if(edate!=null && edate.trim().length()>0){
				criteria.andCCjsjLessThanOrEqualTo(edate);
			}
			/**
			 * 查询创建人不为空的记录
			 */
			criteria.andCjrIsNotNull();
			/**
			 * 审核有外检的记录
			 */
			criteria.andSfwjEqualTo("Y");
			ycInfoExample.setOrderByClause(" CJSJ DESC");
			page = ycInfoService.findPageList(index, ycInfoExample);

			List<YcInfo> list = page.getPagelist();//ycInfoService.query(ycInfoExample);
			request.setAttribute("list_data", list);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("xzqhMap", SYSLoadManager.xzqh_map);
			request.setAttribute("deptMap", SYSLoadManager.dept_map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ycInfo_query";
	}
	
	/**
	 * 打印系统条码
	* @Title: add 
	* @Description: TODO
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	public String printCode(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		/**
		 * 是否外检参数是在条码前加Y，如果不需要外检则生成条码前加N
		 */
		String sfwj = request.getParameter("sfwj");
		try {
			String xtdabh = ycInfoService.add(sfwj,getSessionUser().getDeptarea());
			request.setAttribute("xtdabh", xtdabh);
			msg = "添加成功";
			LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检条码信息");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycInfo!query.action';</script>");
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycInfo!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return "printCode";
	}
	
	public String printCodeDouble(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		/**
		 * 是否外检参数是在条码前加Y，如果不需要外检则生成条码前加N
		 */
		String sfwj = request.getParameter("sfwj");
		try {
			String xtdabh1 = ycInfoService.add(sfwj,getSessionUser().getDeptarea());
			String xtdabh2 = ycInfoService.add(sfwj,getSessionUser().getDeptarea());
			request.setAttribute("xtdabh1", xtdabh1);
			request.setAttribute("xtdabh2", xtdabh2);
			msg = "添加成功";
			LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检条码信息");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycInfo!query.action';</script>");
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycInfo!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return "printCodeDouble";
	}
	
	
	/**
	 * 获取双条码
	 * @return
	 * @throws IOException
	 */
	public String getCodeDouble() throws IOException{
		
		/**
		 * 是否外检参数是在条码前加Y，如果不需要外检则生成条码前加N
		 */
		String sfwj = request.getParameter("sfwj");
		try {
			String xtdabh1 = ycInfoService.getXtdabh(sfwj);
			String xtdabh2 = ycInfoService.getXtdabh2(sfwj);
			request.setAttribute("xtdabh1", xtdabh1);
			request.setAttribute("xtdabh2", xtdabh2);
			session.setAttribute("xtdabh1", xtdabh1);
			session.setAttribute("xtdabh2", xtdabh2);
			LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检条码信息");
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			e.printStackTrace();
		}
		return "printCodeDouble" + sfwj;
	}
	
	/**
	 * 插入双条码
	 */
	public void insertDoubleCode(){
		/**
		 * 是否外检参数是在条码前加Y，如果不需要外检则生成条码前加N
		 */
		String sfwj = request.getParameter("sfwj");
		try {
			String xtdabh1 = (String) session.getAttribute("xtdabh1");
			String xtdabh2 = (String) session.getAttribute("xtdabh2");
			List<YcInfo> findYcInfoByXtdabh1 = ycInfoService.findYcInfoByXtdabh(xtdabh1);
			List<YcInfo> findYcInfoByXtdabh2 = ycInfoService.findYcInfoByXtdabh(xtdabh2);
			if((findYcInfoByXtdabh1 != null && findYcInfoByXtdabh1.size() > 0) || (findYcInfoByXtdabh2 != null && findYcInfoByXtdabh2.size() > 0)){
				response.getWriter().println("200");
			}else{
				ycInfoService.insertCode(xtdabh1,xtdabh1.substring(0, 1),getSessionUser().getDeptarea());
				ycInfoService.insertCode(xtdabh2,xtdabh2.substring(0, 1),getSessionUser().getDeptarea());		
				LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getDeptarea(), "用户添加加外检条码信息");
				response.getWriter().println("222");
			}	
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
			try {
				response.getWriter().println("200");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			log.error("insert error......");
			try {
				response.getWriter().println("200");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量插入条码
	 */
	public void insertBatchCode(){
		String sfwj = request.getParameter("sfwj");
		try{
			
			List<String> list = (List<String>) session.getAttribute("list");
			if(isDuplicate(list)){
				response.getWriter().println("200");
			}else{
				ycInfoService.insertBatchCode(list,sfwj,getSessionUser().getDeptarea());
				response.getWriter().println("222");
			}
		}catch(Exception e){
			try {
				response.getWriter().println("200");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/*private boolean isDuplicate(List<String> list) {
		for(String xtdabh:list){
			List<YcInfo> ycList = ycInfoService.findYcInfoByXtdabh(xtdabh);
			if(ycList != null && ycList.size() > 0){
				return true;
			}
		}
		return false;
	}*/
	
	private boolean isDuplicate(List<String> list) {
		List<YcInfo> ycList = ycInfoService.findYcInfoByXtdabhList(list);
		if(ycList != null && ycList.size() > 0){
			return true;
		}
		return false;
	}
	/**
	 * 获取单条码
	 * @return
	 * @throws IOException
	 */
	public String getCodeSingle() throws IOException{
		
		/**
		 * 是否外检参数是在条码前加Y，如果不需要外检则生成条码前加N
		 */
		String sfwj = request.getParameter("sfwj");
		try {
			String xtdabh = ycInfoService.getXtdabh(sfwj);
			request.setAttribute("xtdabh", xtdabh);
			session.setAttribute("xtdabh", xtdabh);
			LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检条码信息");
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			e.printStackTrace();
		}
		return "printCodeSingle" + sfwj;
	}
	
	/**
	 * 插入单条码
	 */
	public void insertSingleCode(){
		/**
		 * 是否外检参数是在条码前加Y，如果不需要外检则生成条码前加N
		 */
		String sfwj = request.getParameter("sfwj");
		try {
			String xtdabh = (String) session.getAttribute("xtdabh");
			List<YcInfo> findYcInfoByXtdabh = ycInfoService.findYcInfoByXtdabh(xtdabh);
			if(findYcInfoByXtdabh != null && findYcInfoByXtdabh.size() > 0){
				response.getWriter().println("200");
			}else{
				ycInfoService.insertCode(xtdabh,xtdabh.substring(0, 1),getSessionUser().getDeptarea());
				LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检条码信息");
				response.getWriter().println("222");
			}	
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
			try {
				response.getWriter().println("200");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			log.error("insert error......");
			try {
				response.getWriter().println("200");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量获取条码
	 * @return
	 */
	public String getCodeBatch(){
		String sfwj = request.getParameter("sfwj");
		String defNum = request.getParameter("defNum");
		try {
			List<String>  list = ycInfoService.getXtdabhBatch(sfwj,Integer.parseInt(defNum));
			request.setAttribute("num", defNum);
			request.setAttribute("list", list);
			session.setAttribute("list", list);
			LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检条码信息");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printCodeBatch" + sfwj;
	}
	
	public void getCodeBatchAjax(){
		String sfwj = request.getParameter("sfwj");
		String defNum = request.getParameter("defNum");
		try {
			/*List<String>  list = ycInfoService.getXtdabhBatch(sfwj,Integer.parseInt(defNum));
			request.setAttribute("num", defNum);
			request.setAttribute("list", list);
			session.setAttribute("list", list);*/
			LogInfo.info("添加外检信息", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检条码信息");
			response.getWriter().println("200");
		} catch (IOException e) {
			e.printStackTrace();
			try {
				response.getWriter().println("400");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			try {
				response.getWriter().println("400");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setYcInfoPicService(YcInfoPicService ycInfoPicService) {
		this.ycInfoPicService = ycInfoPicService;
	}
	public void setYcInfoService(YcInfoService ycInfoService) {
		this.ycInfoService = ycInfoService;
	}
	public String getId() {
		return id;
	}
	public int getIndex() {
		return index;
	}

	public List<YcInfoPic> getPicList() {
		return picList;
	}
	public void setPicList(List<YcInfoPic> picList) {
		this.picList = picList;
	}
	public YcInfo getYcinfo() {
		return ycinfo;
	}

	public void setYcinfo(YcInfo ycinfo) {
		this.ycinfo = ycinfo;
	}
	public void setYcPaperService(YcPaperService ycPaperService) {
		this.ycPaperService = ycPaperService;
	}
	
}


