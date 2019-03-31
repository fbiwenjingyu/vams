package com.pd.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.pd.base.dao.impl.CodesetDAO;
import com.pd.base.dao.impl.FlowDAO;
import com.pd.base.model.Codeset;
import com.pd.base.model.Flow;
import com.pd.base.model.FlowExample;
import com.pd.base.service.FlowService;
import com.pd.system.framework.DAOFactory;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: FlowServiceImpl 
*  流程信息业务实现逻辑
* @author zl
* @date 2013-6-26 下午03:40:59 
*
 */
public class FlowServiceImpl implements FlowService {

	private FlowDAO flowDAO;
	private CodesetDAO codeSetDAO;
	/**
	 * 流程添加
	 */
	@Override
	public void add(Flow entity) throws Exception {
		flowDAO.insert(entity);
	}

	/**
	 * 流程删除
	 */
	@Override
	public int delete(String id) throws Exception {
		return flowDAO.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取流程对象
	 */
	@Override
	public Flow getEntity(String id) throws Exception {
		return flowDAO.selectByPrimaryKey(id);
	}

	/**
	 * 获取流程分页
	 */
	@Override
	public Pagination getPageByExample(int index, FlowExample example)
			throws Exception {
		int total = flowDAO.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 对流程进行分页列表查询
	 */
	@Override
	public List<Flow> query(FlowExample example) throws Exception {
		List<Flow> list_data = flowDAO.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询流程信息
	 */
	@Override
	public List<Flow> selectByExample(FlowExample example) {
		return flowDAO.selectByExample(example);
	}

	/**
	 * 流程信息修改
	 */
	@Override
	public int update(Flow entity) throws Exception {
		return flowDAO.updateByPrimaryKey(entity);
	}

	public void setFlowDAO(FlowDAO flowDAO) {
		this.flowDAO = flowDAO;
	}

	public void setCodeSetDAO(CodesetDAO codeSetDAO) {
		this.codeSetDAO = codeSetDAO;
	}

	/**
	 * 根据业务类型批量保存流程信息
	 */
	@Override
	public void add(Flow entity, String[] type) {
		if(entity !=null){
			String typeid = "";
			String flowname = entity.getFlowname();
			if(type != null && type.length>0){
				for(int i=0;i<type.length;i++){
					typeid = type[i];
					entity.setTypeId(typeid);
					Codeset codeset = new Codeset();
					codeset = codeSetDAO.selectByPrimaryKey(typeid);
					String s = new String(codeset.getName());  
					String name = s.substring(s.lastIndexOf('|')+2);
					entity.setFlowname(flowname+"("+name+")");
					flowDAO.insert(entity);
				}
			}
		}
		
	}

	@Override
	public Map queryToMap(FlowExample example) {
		List<Flow> list_data = flowDAO.selectByExample(example);
		Map map = new HashMap();
		for (Flow entity : list_data) {
			map.put(entity.getId(), entity.getFlowname());
		}
		return map;
	}
	
	/**
	 * 根据业务类型获取流程步骤最大的流程信息
	 * @param ywlx
	 * @return json
	 * 
	 * @author wangwei
	 * Aug 5, 2013
	 */
	public JSONObject getEntityByYwlxId(String ywlx){
		if(StringUtils.isNotEmpty(ywlx)){
			Flow flow = null;
			FlowExample example = new FlowExample();
			example.createCriteria().andTypeIdEqualTo(ywlx);
			//根据流程步骤进行排序，将步骤最大的排在前面
			example.setOrderByClause("flow desc");
			List<Flow> flowList = flowDAO.selectByExample(example);
			if(flowList.size()>0){
				flow = flowList.get(0);
				if(flow!=null){
					System.out.println(JSONObject.fromObject(flow));
					return JSONObject.fromObject(flow);
				}
			}
		}
		return null;
	}
	
	@Before
	public void init(){
		flowDAO = DAOFactory.getDao("flowDAO");
	}
	
	@Test
	public void testGetEntityByYwlxId() throws Exception {
		getEntityByYwlxId("9");
	}

}


