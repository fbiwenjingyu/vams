package com.pd.wjpda.service.impl;

import java.util.List;

import com.pd.base.dao.impl.CodesetDAO;
import com.pd.base.model.Codeset;
import com.pd.base.model.CodesetExample;
import com.pd.right.dao.impl.UsersDAO;
import com.pd.right.model.Users;
import com.pd.right.model.UsersExample;
import com.pd.wjpda.dao.impl.WjpdaDAO;
import com.pd.wjpda.model.WjpdaVO;
import com.pd.wjpda.service.WjpdaService;
import com.pd.wjyw.dao.impl.YcInfoDAO;
import com.pd.wjyw.dao.impl.YcInfoPicDAO;
import com.pd.wjyw.dao.impl.YcPaperDAO;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoPicExample;
import com.pd.wjyw.model.YcPaper;
import com.pd.wjyw.model.YcPaperExample;

public class WjpdaServiceImpl implements WjpdaService{

	private UsersDAO userDao;					//用户Dao
	private YcInfoDAO ycInfoDAO;				//外检查验信息Dao
	private WjpdaDAO wjpdaDAO;					//外检pdaDao
	private YcInfoPicDAO ycInfoPicDAO;			//外检图片信息
	private CodesetDAO codesetDAO;				//系统代码表
	private YcPaperDAO ycPaperDAO;				//外检查验类型表
	
	/**
	 * @author huhaifeng 2014-08-01
	 * 通过用户名和密码查找用户是否存在	
	 * @param name 	用户编码
	 * @param pwd	用户密码
	 * @return	查找到的users
	 */
	@SuppressWarnings("unchecked")
	public Users findUsersByNameAndPwd(String userCode, String pwd) {
		UsersExample usersExample = new UsersExample();
		usersExample.createCriteria().andUserCodeEqualTo(userCode).andPasswordEqualTo(pwd);
		List<Users> list = userDao.selectByExample(usersExample);
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 通过系统档案编号查找外检查验信息
	 * @param xtdabh 系统档案编号
	 * @return	外检查验信息
	 */
	@SuppressWarnings("unchecked")
	public YcInfo findYcInfoByXtdabh(String xtdabh) {
		YcInfoExample example = new YcInfoExample();
		example.createCriteria().andXtdabhEqualTo(xtdabh);
		List<YcInfo> list = ycInfoDAO.selectByExample(example);
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 通过相关条没有上传成功的信息
	 * @param wjpdaVO
	 * @return
	 */
	public List<WjpdaVO> findPicInfos(WjpdaVO wjpdaVO) {
		List<WjpdaVO> list = wjpdaDAO.findPicInfos(wjpdaVO);
		return list;
	}
	
	/**
	 * 通过相关条件查询需要拍摄的照片
	 * @param wjpdaVO
	 * @return
	 */
	public List<WjpdaVO> getNeedTakePic(WjpdaVO wjpdaVO) {
		return wjpdaDAO.getNeedTakePic(wjpdaVO);
	}
	
	/**
	 * 向外间图片中插入数据
	 */
	public void insertIntoYcInfoPic(YcInfoPic ycInfoPic) {
		ycInfoPicDAO.insert(ycInfoPic);
	}

	/**
	 * 通过相关条件查找YcInfoPicExample
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<YcInfoPic> getYcInfoPicByYcInfoPicExample(YcInfoPicExample ycInfoPicExample) {
		return ycInfoPicDAO.selectByExample(ycInfoPicExample);
	}
	
	/**
	 * 修改外检图片
	 * @param ycInfoPic
	 */
	@Override
	public void updateYcInfoPic(YcInfoPic ycInfoPic,YcInfoPicExample example) {
		ycInfoPicDAO.updateByExampleSelective(ycInfoPic, example);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<YcInfo> getYcInfos(YcInfoExample example) {
		return ycInfoDAO.selectByExample(example);
	}
	
	/**
	 * 在CODESET表中查找NAME
	 * @param code	代码
	 * @param type 代码类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getValueByKeyFromCodeSet(String code, String type) {
		if(code == null || "".equals(code)){
			return null;
		}else{
			CodesetExample example = new CodesetExample();
			example.createCriteria().andCodeEqualTo(code).andTypeEqualTo(type);
			List<Codeset> list = codesetDAO.selectByExample(example);
			if(list != null && list.size()>0){
				return list.get(0).getName();
			}else{
				return null;
			}
		}
	}

	/**
	 * 通过纸张代码查找纸张名称
	 * @param takeCode	纸张代码
	 * @return 纸张名称
	 */
	@SuppressWarnings("unchecked")
	public String getTakeNameByTakeCode(String takeCode) {
		YcPaperExample ycPaperExample = new YcPaperExample();
		ycPaperExample.createCriteria().andTakecodeEqualTo(takeCode);
		List<YcPaper> list = ycPaperDAO.selectByExample(ycPaperExample);
		if(list != null && list.size()>0){
			return list.get(0).getTakeName();
		}else {
			return null;
	
		}
	}
	
	/**
	 * 修改ycinfo
	 * @param ycInfo
	 */
	public void updateYcInfo(YcInfo ycInfo) {
		//ycInfoDAO.updateByPrimaryKeySelective(ycInfo);
		ycInfoDAO.updateByPrimaryKey(ycInfo);
	}
	
	@Override
	public void deleteYcInfoPic(String xtdabh,String picName) {
		YcInfoPicExample ycInfoPicExample = new YcInfoPicExample();
		if(xtdabh != null && !"".equals(xtdabh)){
			ycInfoPicExample.createCriteria().andXtdabhEqualTo(xtdabh);
		}
		if(picName != null && !"".equals(picName)){
			ycInfoPicExample.createCriteria().andPicNameEqualTo(picName);
		}
		ycInfoPicDAO.deleteByExample(ycInfoPicExample);
	}
	
	public UsersDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UsersDAO userDao) {
		this.userDao = userDao;
	}

	public YcInfoDAO getYcInfoDAO() {
		return ycInfoDAO;
	}

	public void setYcInfoDAO(YcInfoDAO ycInfoDAO) {
		this.ycInfoDAO = ycInfoDAO;
	}

	public WjpdaDAO getWjpdaDAO() {
		return wjpdaDAO;
	}

	public void setWjpdaDAO(WjpdaDAO wjpdaDAO) {
		this.wjpdaDAO = wjpdaDAO;
	}

	public YcInfoPicDAO getYcInfoPicDAO() {
		return ycInfoPicDAO;
	}

	public void setYcInfoPicDAO(YcInfoPicDAO ycInfoPicDAO) {
		this.ycInfoPicDAO = ycInfoPicDAO;
	}

	public CodesetDAO getCodesetDAO() {
		return codesetDAO;
	}

	public void setCodesetDAO(CodesetDAO codesetDAO) {
		this.codesetDAO = codesetDAO;
	}

	public YcPaperDAO getYcPaperDAO() {
		return ycPaperDAO;
	}

	public void setYcPaperDAO(YcPaperDAO ycPaperDAO) {
		this.ycPaperDAO = ycPaperDAO;
	}

	
}
