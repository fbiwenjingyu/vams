package com.pd.wjyw.service;

import java.util.List;
import java.util.Map;

import com.pd.right.model.Dept;
import com.pd.right.model.SuperUser;
import com.pd.system.framework.Pagination;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoSuper;

/**
 * 外检信息主表接口
* @ClassName: YcInfoService 
* @Description: TODO
* @author zl
* @date 2014-8-4 上午10:40:01 
*
 */
public interface YcInfoService {
	/**
	 * 
	* @Title: update 
	* @Description: TODO
	* @param @param YcInfo
	* @param @param list
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-8
	 */
	void update(YcInfo ycInfo,List<YcInfoPic> list,SuperUser user) throws Exception;
	/**
	 * 外检写入信息
	* @Title: add 
	* @Description: TODO
	* @param @param YcInfo
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	void add(YcInfo record) throws Exception;
	
	/**
	 * 根据id删除外检记录信息
	* @Title: delete 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	void delete(String id) throws Exception;
	
	/**
	 * 根据条件分页查询信息
	* @Title: query 
	* @Description: TODO
	* @param @param example
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<YcInfo>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	List<YcInfo> query(YcInfoExample example) throws Exception;
	
	/**
	 * 根据id查询外检信息主表数据
	* @Title: getEntity 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return YcInfo    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	YcInfo getEntity(String id) throws Exception;
	
	/**
	 * 根据条件查询外检信息
	* @Title: selectByExample 
	* @Description: TODO
	* @param @param ycInfoExample
	* @param @return    设定文件 
	* @return List<YcInfo>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	List<YcInfo> selectByExample(YcInfoExample ycInfoExample);
	
	/**
	 * 分页查询外检信息
	* @Title: findPageList 
	* @Description: TODO
	* @param @param index
	* @param @param ycInfoExample
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Pagination    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */
	Pagination findPageList(int index, YcInfoExample ycInfoExample) throws Exception;

	/**
	 * 打印条码进行插入
	* @Title: add 
	* @Description: TODO
	* @param @param ycinfo
	* @param @param sfwj    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author zl
	* 2014-8-4
	 */

	String add(String sfwj, String xzqh);

	
	/**
	 * 根据部门统计审核通过的外检数量用户数据统计
	* @Title: getYcInfoByDept 
	* @Description: TODO
	* @param @param map
	* @param @return    设定文件 
	* @return List<YcInfoSuper>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-5
	 */
	List<YcInfoSuper> getYcInfoByDept(Map map);

	/**
	 * 根据系统档案编号查询外检信息
	* @Title: findYcInfoByXtdabh 
	* @Description: TODO
	* @param @param xtdah
	* @param @return    设定文件 
	* @return List<YcInfo>    返回类型 
	* @throws 
	* @author zl
	* 2014-8-7
	 */
	List<YcInfo> findYcInfoByXtdabh(String xtdah);
	
	/**
	 * 外检审核
	 * @param createTime
	 * @param name
	 * @param shjg
	 * @param bz
	 * @param wjbh
	 * @param pid
	 */
	void ycVerify(String createTime, String name,String shrmc, String shjg, String bz,
			String wjbh, String[] pid);
	
	/**
	 * 外检条码退办
	 * @param pics
	 * @param wjbh
	 */
	void backOffice(List<YcInfoPic> pics, String wjbh);
	Pagination getPageByExample(int index, Map map);
	List<YcInfo> selectByRandomExampleToPage(Map map);
	String getXtdabh(String sfwj);
	String getXtdabh2(String sfwj);
	
	/**
	 * 插入打印过的条码
	 * @param xtdabh 系统编号
	 * @param sfwj 是否外检，Y、N
	 * @param xzqh 行政区划
	 * */
	void insertCode(String xtdabh, String sfwj, String xzqh);
	/**
	 * 批量获取条码
	 * @param sfwj     是否外检，Y、N
	 * @param num      条码数
	 * @return
	 */
	List<String> getXtdabhBatch(String sfwj, int num);
	List<YcInfo> findYcInfoByXtdabhList(List<String> list);
	void insertBatchCode(List<String> list, String sfwj, String xzqh);

}


