package com.pd.wjyw.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pd.base.model.Codeset;
import com.pd.base.model.CodesetExample;
import com.pd.base.service.CodeSetService;
import com.pd.right.util.LogInfo;
import com.pd.system.framework.BaseAction;
import com.pd.system.startup.SYSLoadManager;
import com.pd.wjyw.model.YcPaper;
import com.pd.wjyw.model.YcPaperExample;
import com.pd.wjyw.model.YcTypePaper;
import com.pd.wjyw.model.YcTypePaperExample;
import com.pd.wjyw.service.YcPaperService;
import com.pd.wjyw.service.YcTypePaperService;

public class YcTypePaperAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(getClass());
	private YcTypePaper entity;
	private YcTypePaperService ycTypePaperService;
	private YcPaperService ycPaperService;
	private CodeSetService codeSetService;
	private YcTypePaperExample example = new YcTypePaperExample();
	private String[] takecodes;
	private String id;
	private int index =1;
	
	/**
	 * 分页查询外检设置信息
	* @Title: query 
	* @Description: TODO
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	public String query(){
		try{
			YcTypePaperExample.Criteria criteria = example.createCriteria();
			if(null != entity) {
				if(entity.getTakecode() != null && entity.getTakecode().trim().length() > 0){
					criteria.andTakecodeEqualTo(entity.getTakecode());
				}
				if(entity.getYwlx() != null && entity.getYwlx().trim().length() > 0){
					criteria.andYwlxEqualTo(entity.getYwlx());
				}
				if(entity.getHpzl() != null && entity.getHpzl().trim().length() > 0){
					criteria.andHpzlEqualTo(entity.getHpzl());
				}
			}
			page = ycTypePaperService.findPageList(index, example);
			example.setOrderByClause(" YWLX,HPZL");
			List<YcTypePaper> list = page.getPagelist();//ycTypePaperService.query(example);
			request.setAttribute("list_data", list);
			request.setAttribute("ywlxMap", SYSLoadManager.ywlx_map);
			request.setAttribute("hpzlMap", SYSLoadManager.hpzl_map);
			request.setAttribute("paperMap", ycPaperService.queryToMap(new YcPaperExample()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ycTypePaper_query";
	}
	
	public String preAdd(){
		List<YcPaper> list_paper = ycPaperService.selectByExample(null);
		CodesetExample codeExample = new CodesetExample();
		codeExample.setOrderByClause(" code ");
		List<Codeset> list_code = codeSetService.selectByExample(codeExample);
		request.setAttribute("list_paper", list_paper);
		request.setAttribute("list_code", list_code);
		return "ycTypePaper_add";
	}
	/**
	 * 添加保存外检设置信息
	* @Title: add 
	* @Description: TODO
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-2
	 */
	public void add(){
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			ycTypePaperService.add(entity,takecodes);
			msg = "添加成功";
			LogInfo.info("添加外检设置", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "用户添加加外检设置");
			response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycTypePaper!query.action';</script>");
		} catch (IOException e) {
			log.error("insert error......");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("insert error......");
			msg = "添加失败";
			try {
				response.getWriter().write("<script>top.Dialog.close();alert('"+msg+"');window.location='ycTypePaper!query.action';</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void delete() throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		String msg = "";
		try {
			int result = ycTypePaperService.delete(id);
			if(result==1){
				msg = "删除成功";
			}else{
				msg = "删除失败";
			}
			LogInfo.info("属性删除", request.getLocalAddr(),"","1", Long.parseLong("1"), getSessionUser().getUserCode(), "删除属性");
			response.getWriter().write("<script>alert('"+msg+"');window.location='ycTypePaper!query.action'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public YcTypePaper getEntity() {
		return entity;
	}
	public void setEntity(YcTypePaper entity) {
		this.entity = entity;
	}
	public void setYcTypePaperService(YcTypePaperService ycTypePaperService) {
		this.ycTypePaperService = ycTypePaperService;
	}

	public String[] getTakecodes() {
		return takecodes;
	}

	public void setTakecodes(String[] takecodes) {
		this.takecodes = takecodes;
	}

	public void setYcPaperService(YcPaperService ycPaperService) {
		this.ycPaperService = ycPaperService;
	}

	public void setCodeSetService(CodeSetService codeSetService) {
		this.codeSetService = codeSetService;
	}
	
	
	
}


