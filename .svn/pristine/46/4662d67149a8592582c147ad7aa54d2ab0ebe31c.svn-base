package com.pd.wjpda.service;

import java.util.List;

import com.pd.right.model.Users;
import com.pd.wjpda.model.WjpdaVO;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoPicExample;

public interface WjpdaService {

	/**
	 * @author huhaifeng 2014-08-01
	 * 通过用户名和密码查找用户是否存在	
	 * @param name 	用户编码
	 * @param pwd	用户密码
	 * @return	查找到的users
	 */
	public Users findUsersByNameAndPwd(String userCode,String pwd);

	/**
	 * 通过系统档案编号查找外检查验信息
	 * @param xtdabh 系统档案编号
	 * @return	外检查验信息
	 */
	public YcInfo findYcInfoByXtdabh(String xtdabh);
	
	/**
	 * 通过相关条没有上传成功的信息
	 * @param wjpdaVO
	 * @return
	 */
	public List<WjpdaVO> findPicInfos(WjpdaVO wjpdaVO);
	
	/**
	 * 通过相关条件查询需要拍摄的照片
	 * @param wjpdaVO
	 * @return
	 */
	public List<WjpdaVO> getNeedTakePic(WjpdaVO wjpdaVO);
	
	/**
	 * 向外检图片中插入数据
	 */
	public void insertIntoYcInfoPic(YcInfoPic ycInfoPic);
	
	/**
	 * 通过相关条件查找YcInfoPicExample
	 * @return	
	 */
	public List<YcInfoPic> getYcInfoPicByYcInfoPicExample(YcInfoPicExample ycInfoPicExample);
	
	/**
	 * 修改外检图片
	 * @param ycInfoPic
	 */
	public void updateYcInfoPic(YcInfoPic ycInfoPic,YcInfoPicExample example);
	
	
	public List<YcInfo> getYcInfos(YcInfoExample example);
	
	/**
	 * 在CODESET表中查找NAME
	 * @param code	代码
	 * @param type 代码类型
	 * @return
	 */
	public String getValueByKeyFromCodeSet(String code,String type);
	
	/**
	 * 通过纸张代码查找纸张名称
	 * @param takeCode	纸张代码
	 * @return 纸张名称
	 */
	public String getTakeNameByTakeCode(String takeCode);
	
	/**
	 * 修改ycinfo
	 * @param ycInfo
	 * @param example
	 */
	public void updateYcInfo(YcInfo ycInfo);
	
	/**
	 * 根据系统档案编号和图片名称删除之前数据
	 * @param xtdabh 系统档案编号
	 * @param picName	图片名称
	 */
	public void deleteYcInfoPic(String xtdabh,String picName);
	
}
