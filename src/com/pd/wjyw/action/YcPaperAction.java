package com.pd.wjyw.action;

import java.io.IOException;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pd.base.model.Attr;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.BaseAction;
import com.pd.system.startup.SYSLoadManager;
import com.pd.wjyw.model.YcPaper;
import com.pd.wjyw.model.YcPaperExample;
import com.pd.wjyw.service.YcPaperService;

/**
 * 外检纸张设置控制类
* @ClassName: YcPaperAction 
* @Description: TODO
* @author zl
* @date 2014-8-1 下午03:41:03 
*
 */
public class YcPaperAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass());
	/**
	 * 外检纸张对象
	 */
	private YcPaper ycPaper;	
	/**
	 * 外检主键id
	 */
	private String id;
	private YcPaperExample ycPaperExample = new YcPaperExample();
	private YcPaperService ycPaperService;
	private int index = 1;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 分页查询外检纸张列表信息
	* @Title: query 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-1
	 */
	public String query(){
		try{
			YcPaperExample.Criteria criteria = ycPaperExample.createCriteria();
			if(null != ycPaper) {
				if(ycPaper.getTakecode() != null && ycPaper.getTakecode().trim().length() > 0){
					criteria.andTakecodeEqualTo(ycPaper.getTakecode());
				}
				if(ycPaper.getTakeName() != null && ycPaper.getTakeName().trim().length() > 0){
					criteria.andTakeNameLike("%"+ycPaper.getTakeName()+"%");
				}
			}
			page = ycPaperService.findPageList(index, ycPaperExample);
			List<YcPaper> list = page.getPagelist();//ycPaperService.query(ycPaperExample);
			request.setAttribute("list_data", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ycPaper_query";
	}
	
	public void add(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			ycPaperService.add(ycPaper);
			msg = "添加成功";
			LogInfo.info("添加外检纸张", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检纸张");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycPaper!query.action';</script>");
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycPaper!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 检测纸张代码唯一性
	* @Title: isExists 
	* @Description: TODO
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	public void isExists(){
		String msg = "";
		response.setContentType("text/plain;charset=UTF-8");
		if(ycPaper.getTakecode() !=null && ycPaper.getTakecode().length()>0){
			ycPaperExample.createCriteria().andTakecodeEqualTo(ycPaper.getTakecode());
		}
		List<YcPaper> list = ycPaperService.selectByExample(ycPaperExample);
		if(list!=null && list.size()>0){
			msg = "1";
		}else{
			msg = "0";
		}
		try {
			response.getWriter().println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改页面获取外检纸张信息
	* @Title: preUpdate 
	* @Description: TODO
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	public void preUpdate(){
		try {
			YcPaper ycPaper = ycPaperService.getEntity(id);
			request.setAttribute("ycPaper", ycPaper);
			request.setAttribute("update", "update");
			request.getRequestDispatcher("wjyw/ycPaper_add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 修改外检纸张信息后保存到数据库
	* @Title: update 
	* @Description: TODO
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	public void update() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			int result = ycPaperService.update(ycPaper);
			if(result==1){       
				msg = "修改成功";
			}else{
				msg = "修改失败";
			}
			LogInfo.info("属性修改", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户修改属性");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycPaper!query.action'</script>");
		} catch (Exception e) {
			log.error("update error......");
			e.printStackTrace();
		}
		
	}
	

	/**
	 * 根据id删除外检纸张信息
	* @Title: delete 
	* @Description: TODO
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	public void delete() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			int result = ycPaperService.delete(id);
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			LogInfo.info("属性删除", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "删除属性");
			response.getWriter().write("<script>alert('"+msg+"');window.location='ycPaper!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 外检纸张下拉框选项
	* @Title: getSelect 
	* @Description: TODO
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	public void getSelect(){
		response.setContentType("text/html;charset=UTF-8");
		JSONArray data_jx = new JSONArray();
		Map ycPaperMap = new HashMap();
		ycPaperMap  = ycPaperService.queryToMap(null);
		data_jx.add(ycPaperMap);
		try {
			response.getWriter().println(data_jx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public YcPaper getYcPaper() {
		return ycPaper;
	}
	public void setYcPaper(YcPaper ycPaper) {
		this.ycPaper = ycPaper;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public void setYcPaperService(YcPaperService ycPaperService) {
		this.ycPaperService = ycPaperService;
	}
	
}


