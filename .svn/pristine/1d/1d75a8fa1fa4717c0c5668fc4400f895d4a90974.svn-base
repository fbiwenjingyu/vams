package com.pd.system.res;

import java.util.Date;

import com.pd.right.dao.impl.UsersDAO;
import com.pd.right.model.Users;
import com.pd.system.framework.BaseService;
import com.pd.system.utils.DateTools;
import com.pd.system.utils.StringTools;
import com.pd.wjyw.dao.impl.YcInfoDAO;

public class BarcodeService extends BaseService {

	private YcInfoDAO ycInfoDAO;
	private UsersDAO usersDAO;

	/**
	 * 获取系统档案编号
	 * */
	public String getXtdabh(String head) {
		/**
		 * 获取当前年月日
		 */
		String timetag = DateTools.getFormatDate(new Date(), "yyyyMMdd");
		/**
		 * 从数据库获取当前日期的最大序号
		 */
		//*******循环数为6位！！！**********
		String seq = StringTools.lpad(ycInfoDAO.getXtdabhSeq(), 6, "0");
		return new StringBuffer(head).append(timetag).append(seq).toString();
	}

	
	public Users findUserByCode(String usercode){
		return usersDAO.selectByPrimaryKey(usercode);
	}
	
	/**
	 * 更新快捷打印设置
	 * */
	public void updateUserFastPrintSet(Users users) throws Exception {

		usersDAO.updateByPrimaryKeySelective(users);

	}

	public void setYcInfoDAO(YcInfoDAO ycInfoDAO) {
		this.ycInfoDAO = ycInfoDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

}
