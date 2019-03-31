package com.pd.base.service.impl;

import java.util.List;

import com.pd.base.dao.impl.TypesetDAO;
import com.pd.base.model.Typeset;
import com.pd.base.model.TypesetExample;
import com.pd.base.model.TypesetExample.Criteria;
import com.pd.base.service.TypeSetService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: TypeSetServiceImpl 
*  角色纸张业务设置
* @author zl
* @date 2013-6-26 下午03:58:37 
*
 */
public class TypeSetServiceImpl implements TypeSetService {

	private TypesetDAO typeSetDAO;
	/**
	 * 添加角色纸张业务设置
	 */
	@Override
	public void add(Typeset entity) throws Exception {
		typeSetDAO.insert(entity);
	}

	/**
	 * 删除角色纸张业务
	 */
	@Override
	public int delete(String id) throws Exception {
		return typeSetDAO.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取对象
	 */
	@Override
	public Typeset getEntity(String id) throws Exception {
		return typeSetDAO.selectByPrimaryKey(id);
	}

	/**
	 * 获取分页信息
	 */
	@Override
	public Pagination getPageByExample(int index, TypesetExample example)
			throws Exception {
		int total = typeSetDAO.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 查询
	 */
	@Override
	public List<Typeset> query(TypesetExample example) throws Exception {
		List<Typeset> list_data = typeSetDAO.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询记录集
	 */
	@Override
	public List<Typeset> selectByExample(TypesetExample example) {
		return typeSetDAO.selectByExample(example);
	}

	/**
	 * 修改
	 */
	@Override
	public int update(Typeset entity) throws Exception {
		return typeSetDAO.updateByPrimaryKey(entity);
	}

	public void setTypeSetDAO(TypesetDAO typeSetDAO) {
		this.typeSetDAO = typeSetDAO;
	}

	/**
	 * 批量保存角色业务类型纸张关系
	 */
	@Override
	public void add(Typeset entity, String[] paper,String optPaper[], String[] type) {
		if(entity !=null){
			String typeid = "";
			String papaerid = "";
			if(type != null && type.length>0){
				for(int i=0;i<type.length;i++){
					typeid = type[i];
					entity.setTypeId(typeid);
					for(int a=0;a<paper.length;a++){
						papaerid = paper[a];
						entity.setPaperId(papaerid);
						entity.setF1("1");
						TypesetExample example = new TypesetExample();
						Criteria ca = example.createCriteria();
						ca.andPaperIdEqualTo(papaerid);
						ca.andTypeIdEqualTo(typeid);
						ca.andRoleIdEqualTo(entity.getRoleId());
						List<Typeset> list = typeSetDAO.selectByExample(example);
						if(list != null && list.size()>0){
							for(Typeset e : list){
								if(equal(e,entity)){
									continue;
								}else{
									typeSetDAO.insert(entity);
								}
							}
						}else{
							typeSetDAO.insert(entity);
						}
					}
					
					if(optPaper!=null && optPaper.length>0){
						for(int a=0;a<optPaper.length;a++){
							papaerid = optPaper[a];
							entity.setPaperId(papaerid);
							entity.setF1("0");
							TypesetExample example = new TypesetExample();
							Criteria ca = example.createCriteria();
							ca.andPaperIdEqualTo(papaerid);
							ca.andTypeIdEqualTo(typeid);
							ca.andRoleIdEqualTo(entity.getRoleId());
							List<Typeset> list = typeSetDAO.selectByExample(example);
							if(list != null && list.size()>0){
								for(Typeset e : list){
									if(equal(e,entity)){
										continue;
									}else{
										typeSetDAO.insert(entity);
									}
								}
							}else{
								typeSetDAO.insert(entity);
							}
						}
					}
				}
			}
		}
	}

	public  boolean equal(Typeset model1,Typeset model2){
		if(model1.getPaperId().equals(model2.getPaperId()) && model1.getRoleId().equals(model2.getRoleId()) && model1.getTypeId().equals(model2.getTypeId())){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public void update(Typeset entity, String[] paper, String[] optPaper) {
		if(entity !=null){
			String papaerid = "";
			TypesetExample example = new TypesetExample();
			Criteria criteria = example.createCriteria();
			//修改时根据角色id和业务类型id删除原来的角色业务对应的纸张信息
			criteria.andRoleIdEqualTo(entity.getRoleId());
			criteria.andTypeIdEqualTo(entity.getTypeId());
			typeSetDAO.deleteByExample(example);
			//必扫档案页
			if(paper != null && paper.length>0){
				for(int a=0;a<paper.length;a++){
					papaerid = paper[a];
					entity.setPaperId(papaerid);
					entity.setF1("1");
					typeSetDAO.insert(entity);
				}
			}
			
			//可扫档案页
			if(optPaper != null &&optPaper.length>0){
				for(int a=0;a<optPaper.length;a++){
					papaerid = optPaper[a];
					entity.setPaperId(papaerid);
					entity.setF1("0");
					typeSetDAO.insert(entity);
				}
			}
		}
	}

}


