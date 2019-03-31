package com.pd.right.service.impl;

import java.util.List;

import com.pd.right.dao.impl.ResourcesDAO;
import com.pd.right.model.Resources;
import com.pd.right.model.ResourcesExample;
import com.pd.right.model.ResourcesExample.Criteria;
import com.pd.right.service.ResourcesService;
import com.pd.system.framework.Pagination;

/**
 * 
* @ClassName: ResourcesServiceImpl 
*  资源信息业务逻辑类
* @author zl
* @date 2013-6-28 上午09:29:54 
*
 */
public class ResourcesServiceImpl implements ResourcesService {

	private ResourcesDAO resourcesDao;
	
	/**
	 * 
	* @Title: addZeroForNum 
	*  最大左边补0
	* @param @param str
	* @param @param strLength
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zl
	* 2013-7-1
	 */
	public static String addZeroForNum(String str, int strLength) {
		if(str.equals("") || str.equals("0")){
			str="1";
		}
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);//左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	 }
	/**
	 * 资源信息添加
	 */
	@Override
	public void add(Resources entity) throws Exception {
		ResourcesExample example = new ResourcesExample();
		Criteria ca = example.createCriteria();
		ca.andUpresidEqualTo(entity.getUpresid());
		//根据上级资源查询最大的编号
		String maxRes = resourcesDao.getMaxRes(example);
		System.out.println("数据库目前最大的编号"+maxRes);
		Resources r = resourcesDao.selectByPrimaryKey(entity.getUpresid());
		short openmode = r.getOpenmode();
		String nextMode = String.valueOf(openmode  + 1);
		Integer nextRes = Integer.parseInt(maxRes)+1;
		entity.setOpenmode(Short.valueOf(nextMode));
		entity.setResid(entity.getUpresid()+addZeroForNum(nextRes.toString(), 3));
		resourcesDao.insert(entity);
	}

	/**
	 * 资源信息删除
	 */
	@Override
	public int delete(String ResourcesCode) throws Exception {
		return resourcesDao.deleteByPrimaryKey(ResourcesCode);
	}

	/**
	 * 根据代码查询资源对象
	 */
	@Override
	public Resources getEntity(String ResourcesCode) throws Exception {
		return resourcesDao.selectByPrimaryKey(ResourcesCode);
	}

	/**
	 * 分页查询资源信息
	 */
	@Override
	public Pagination getPageByExample(int index, ResourcesExample example)
			throws Exception {
		int total = resourcesDao.countByExample(example);
		Pagination page = new Pagination(total,index);
		return page;
	}

	/**
	 * 分页查询资源信息列表
	 */
	@Override
	public List<Resources> query(ResourcesExample example) throws Exception {
		List<Resources> list_data = resourcesDao.selectByExampleToPage(example);
		return list_data;
	}

	/**
	 * 根据条件查询资源信息
	 */
	@Override
	public List<Resources> selectByExample(ResourcesExample example) {
		List<Resources> list_data = resourcesDao.selectByExample(example);
		return list_data;
	}

	/**
	 * 更新资源信息
	 */
	@Override
	public int update(Resources entity) throws Exception {
		return resourcesDao.updateByPrimaryKey(entity);
	}

	public void setResourcesDao(ResourcesDAO resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

}


