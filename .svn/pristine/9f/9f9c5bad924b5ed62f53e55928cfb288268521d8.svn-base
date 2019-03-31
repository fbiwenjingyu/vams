package com.pd.cwgl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.pd.arc.dao.impl.ArcBaseInfoDAO;
import com.pd.arc.dao.impl.ArcStatusRecordDAO;
import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcStatusRecord;
import com.pd.cwgl.dao.StoSettingDAO;
import com.pd.cwgl.model.StoSetting;
import com.pd.cwgl.model.StoSettingExample;
import com.pd.cwgl.utils.CwglConsts;
import com.pd.cwgl.utils.CwglTools;
import com.pd.cwgl.utils.StoRet;
import com.pd.right.dao.impl.UsersDAO;
import com.pd.right.model.SuperUser;
import com.pd.swgl.service.PubStorageInOutService;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.PageToExample;
import com.pd.system.framework.Pagination;
import com.pd.system.framework.VamsRTExcaption;
import com.pd.system.res.ArcStatus;
import com.pd.system.res.BarcodeService;
import com.pd.system.utils.StringTools;

public class StoService extends BaseService {

	/**
	 * 储位数量刷新锁定
	 * */
	private static final Object LOCK_FLUSH = new Object();
	/**
	 * 储位信息保存锁定
	 * */
	private static final Object LOCK_SAVE = new Object();

	private StoSettingDAO stoSettingDAO;
	private ArcBaseInfoDAO arcBaseInfoDAO;
	private UsersDAO usersDAO;
	private ArcStatusRecordDAO arcStatusRecordDAO;
	private BarcodeService barcodeService;
	private PubStorageInOutService pubStorageInOutService;

	/**
	 * 分页查询
	 * */
	public Pagination findPageByExample(int index, StoSettingExample example)
			throws Exception {
		return findPageList(index, example, stoSettingDAO);
	}

	// 长度转换字母，例如：输入3，返回ABC，输入4，返回ABCD
	private String lengthToWord(int len) {
		String ret = "";
		for (int i = 1; i <= len; i++) {
			ret += (StringTools.getUpperNumLetter(i));
		}
		return ret;
	}

	/**
	 * 分页查询档案格信息
	 * */
	public Pagination findArcPageByExample(int index, PageToExample example)
			throws Exception {
		return findPageList(index, example, arcBaseInfoDAO);
	}

	/**
	 * 添加档案室
	 * */
	public void addShi(SuperUser user, String shiId, List<String> guiIds,
			String lieNum, String geNum, String fenNum, String cflx,
			String remark) throws Exception {
		// 生成日期
		Date date = new Date();
		// 生成档案室
		StoSetting shiSet = new StoSetting(shiId, shiId, "0",
				CwglConsts.CWLX_SHI, guiIds.size() + "", guiIds.size() + "",
				guiIds.size() + "", user.getUserCode(), user.getUserName(),
				date, remark);
		stoSettingDAO.insert(shiSet);
		// 生成档案柜，根据档案柜ids
		for (int i = 0; i < guiIds.size(); i++) {
			String guiId = guiIds.get(i);
			StoSetting guiSet = new StoSetting(shiId + guiId, guiId, shiId,
					CwglConsts.CWLX_GUI,
					// 将列数转化成结构存入
					lengthToWord(Integer.parseInt(lieNum)), lieNum, lieNum,
					user.getUserCode(), user.getUserName(), date, remark);
			stoSettingDAO.insert(guiSet);
			// 添加档案列
			for (int j = 1; j <= Integer.parseInt(lieNum); j++) {
				// 获取列ID
				String lieId = StringTools.getUpperNumLetter(j);
				StoSetting lieSet = new StoSetting(shiId + guiId + lieId,
						lieId, shiId + guiId, CwglConsts.CWLX_LIE, "", geNum,
						geNum, user.getUserCode(), user.getUserName(), date, remark);
				stoSettingDAO.insert(lieSet);
				// 添加档案格
				for (int k = 1; k <= Integer.parseInt(geNum); k++) {
					// 获取格ID
					String geId = k + "";
					StoSetting geSet = new StoSetting(shiId + guiId + lieId
							+ geId, geId, shiId + guiId + lieId,
							CwglConsts.CWLX_GE, "", fenNum, "0",
							user.getUserCode(), user.getUserName(), date, cflx,
							remark);
					stoSettingDAO.insert(geSet);
				}
			}
		}
	}

	/**
	 * 添加档案柜
	 * */
	public void addGui(SuperUser user, String shiId, List<String> guiIds,
			String lieNum, String geNum, String fenNum, String cflx,
			String remark) throws Exception {
		// 生成日期
		Date date = new Date();
		// 生成档案柜，根据档案柜ids
		for (int i = 0; i < guiIds.size(); i++) {
			String guiId = guiIds.get(i);
			StoSetting guiSet = new StoSetting(shiId + guiId, guiId, shiId,
					CwglConsts.CWLX_GUI,
					// 将列数转化成结构存入
					lengthToWord(Integer.parseInt(lieNum)), lieNum, lieNum,
					user.getUserCode(), user.getUserName(), date, remark);
			stoSettingDAO.insert(guiSet);
			// 添加档案列
			for (int j = 1; j <= Integer.parseInt(lieNum); j++) {
				// 获取列ID
				String lieId = StringTools.getUpperNumLetter(j);
				StoSetting lieSet = new StoSetting(shiId + guiId + lieId,
						lieId, shiId + guiId, CwglConsts.CWLX_LIE, "", geNum,
						geNum, user.getUserCode(), user.getUserName(), date, remark);
				stoSettingDAO.insert(lieSet);
				// 添加档案格
				for (int k = 1; k <= Integer.parseInt(geNum); k++) {
					// 获取格ID
					String geId = k + "";
					StoSetting geSet = new StoSetting(shiId + guiId + lieId
							+ geId, geId, shiId + guiId + lieId,
							CwglConsts.CWLX_GE, "", fenNum, "0",
							user.getUserCode(), user.getUserName(), date, cflx,
							remark);
					stoSettingDAO.insert(geSet);
				}
			}
		}

		// 查询档案室
		StoSetting shi = stoSettingDAO.selectByPrimaryKey(shiId);
		// 修改档案室容量
		String cap = (Integer.parseInt(shi.getZrl()) + guiIds.size()) + "";
		shi.setZrl(cap);
		shi.setSyl(cap);
		stoSettingDAO.updateByPrimaryKeySelective(shi);
	}

	/**
	 * 更新储位
	 * */
	public void updateSet(StoSetting set) throws Exception {
		stoSettingDAO.updateByPrimaryKeySelective(set);
		// 如果设置为不可用，则需要连带更新下级所有储位
		if (set.getSfky().equals(CwglConsts.DISABLE)) {
			StoSettingExample example = new StoSettingExample();
			example.createCriteria().andCidLike(set.getCid() + "%");
			StoSetting set1 = new StoSetting();
			set1.setSfky(CwglConsts.DISABLE);
			stoSettingDAO.updateByExampleSelective(set1, example);
		}
	}

	/**
	 * 统计使用总量
	 * 
	 * @param example
	 *            查询条件
	 * @param sumFiled
	 *            求和字段
	 * */
	public long findUsedNum(StoSettingExample example) {
		return stoSettingDAO.selectNumByExample(example, "SYL");
	}

	/**
	 * 删除该储位及下级储位储位
	 * */
	public void deleteStoAndChilds(String cwbh) throws Exception {
		StoSettingExample example = new StoSettingExample();
		example.createCriteria().andCidLike(cwbh + "%");
		stoSettingDAO.deleteByExample(example);
	}

	/**
	 * 通过储位编号查询储位信息
	 * */
	public StoSetting findSetByCid(String cwbh) {
		return stoSettingDAO.selectByPrimaryKey(cwbh);
	}

	/**
	 * 获取档案室列表
	 * */
	public List<StoSetting> getShiList() {
		StoSettingExample setExample = new StoSettingExample();
		setExample.setOrderByClause(" sid asc ");
		setExample.createCriteria().andCwlxEqualTo(CwglConsts.CWLX_SHI + "");
		return stoSettingDAO.selectByExample(setExample);
	}

	/**
	 * 刷新某一格使用量
	 * 
	 * @param sid
	 *            档案格的设置编号
	 * @return 档案格使用量
	 * */
	public int flushUsed(String geid) throws Exception {
		synchronized (LOCK_FLUSH) {
			// 使用量
			int used = 0;
			// 当传入编号为档案格时
			if (geid.length() >= 5) {
				// 切割出档案格编号
				String cwbh = CwglTools.splitGeId(geid);
				// 统计该档格的使用量
				ArcBaseInfoExample example = new ArcBaseInfoExample();
				// 查询条件为：按照上级储位编号查询有效的储位
				example.createCriteria().andCwsjEqualTo(cwbh)
						.andCwyxEqualTo(CwglConsts.ENABLE);
				used = arcBaseInfoDAO.countByExample(example);

				// 查询档案格更新容量
				StoSetting ge = stoSettingDAO.selectByPrimaryKey(cwbh);
				ge.setSyl(used + "");
				stoSettingDAO.updateByPrimaryKeySelective(ge);

			} else {
				// 储位编号格式不合法
				throw new RuntimeException(
						"储位编号编号格式不正确，必须为：“1A1A1”或者“1A1A1001”格式。");
			}
			return used;
		}
	}

	/**
	 * 刷新所有储位格
	 * */
	public void flushAllGe() throws Exception {
		// 查询所有档案格
		StoSettingExample example = new StoSettingExample();
		example.createCriteria().andCwlxEqualTo(CwglConsts.CWLX_GE);
		List<StoSetting> list = stoSettingDAO.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			StoSetting ge = list.get(i);
			synchronized (LOCK_FLUSH) {
				// 统计该档格的使用量
				ArcBaseInfoExample infoExample = new ArcBaseInfoExample();
				// 查询条件为：按照上级储位编号查询有效的储位
				infoExample.createCriteria().andCwsjEqualTo(ge.getCid())
						.andCwyxEqualTo(CwglConsts.ENABLE);
				int used = arcBaseInfoDAO.countByExample(infoExample);
				// 查询档案格更新容量
				ge.setSyl(used + "");
				stoSettingDAO.updateByPrimaryKeySelective(ge);
			}
		}
	}

	// =======================================================================================

	/**
	 * 获取某一档案室的档案柜
	 * */
	public List<StoSetting> getGuiList(String shiId) {
		StoSettingExample example = new StoSettingExample();
		example.setOrderByClause(" cid asc ");
		example.createCriteria().andCwlxEqualTo(CwglConsts.CWLX_GUI)
				.andPidEqualTo(shiId);
		return stoSettingDAO.selectByExample(example);
	}

	/**
	 * 向数据库提交用户
	 * */
	public void updateUserByOperationRecord(SuperUser user) throws Exception {
		usersDAO.updateByPrimaryKeySelective(user);
	}

	/**
	 * ▲▲▲获取档案柜数据
	 * 
	 * @param shiId
	 *            档案室简单id
	 * @param guiId
	 *            档案柜简单id
	 * @param currentCwbh
	 *            当前格的cwbh
	 * @return List<List<SwdaStorageSet>>
	 * */
	public List<List<StoSetting>> getGuiData(String shiId, String guiId,
			String currentCwbh) {
		List<List<StoSetting>> listGui = new ArrayList<List<StoSetting>>();

		StoSettingExample setExample = new StoSettingExample();
		// 1、查询出目标档案柜，取出结构（列结构）
		StoSetting tagGui = stoSettingDAO.selectByPrimaryKey(shiId + guiId);
		char[] jg = tagGui.getXsjg().toUpperCase().toCharArray();// 取出结构
		// 2、查询出列数据
		setExample.createCriteria().andPidEqualTo(shiId + guiId)
				.andCwlxEqualTo(CwglConsts.CWLX_LIE);
		List<StoSetting> listLie = stoSettingDAO.selectByExample(setExample);
		// 3、循环每列，然后根据列数据查询格数据
		for (int i = 0; i < jg.length; i++) {
			// 循环找到对应的档案列
			for (int j = 0; j < listLie.size(); j++) {
				StoSetting lie = listLie.get(j);// 依次取出列，与保持的结果作比较
				if ((jg[i] + "").equals(lie.getSid().toUpperCase())) {// 如果与实际的列id相同，则查询当前列下的所有格子
					setExample.clear();
					setExample.setOrderByClause(" sid asc ");// 排序查询
					setExample.createCriteria().andPidEqualTo(lie.getCid())
							.andCwlxEqualTo(CwglConsts.CWLX_GE);
					List<StoSetting> ges = stoSettingDAO
							.selectByExample(setExample);
					for (StoSetting stoSetting : ges) {
						// 标记 格说明
						if (null == stoSetting.getCflx()
								|| "".equals(stoSetting.getCflx())) {
							stoSetting.setCflx(CwglConsts.CFLX_SM);
						}
						// 标记当前格
						if (stoSetting.getCid().equals(currentCwbh)) {// 如果为当前格，则标记可用为2
							stoSetting.setSfky(CwglConsts.CURRENT);
						}
					}
					listGui.add(ges);
					break;
				}
			}
		}
		return listGui;
	}

	/**
	 * 根据档案格ID查询档案盒
	 * 
	 * public List<StoSetting> findHeByGeId(String geId) { StoSettingExample
	 * example = new StoSettingExample(); example.setOrderByClause(" cid asc ");
	 * example.createCriteria().andCwlxEqualTo(CwglConsts.CWLX_HE)
	 * .andPidEqualTo(geId); return stoSettingDAO.selectByExample(example); }
	 * */

	/**
	 * 根据档案格ID查询档案列表
	 * */
	public List<ArcBaseInfo> findArcByGeId(String geid) {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		// example.setOrderByClause("  asc ");
		// 查询储位编号为档案盒，并且有效的储位编号
		example.createCriteria().andCwsjEqualTo(geid)
				.andCwyxEqualTo(CwglConsts.ENABLE);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 根据档案格ID查询档案列表
	 * */
	public List<ArcBaseInfo> findArcByGeIdOrderByAsc(String geid) {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		example.setOrderByClause(" cwbh  asc ");
		// 查询储位编号为档案盒，并且有效的储位编号
		example.createCriteria().andCwsjEqualTo(geid)
				.andCwyxEqualTo(CwglConsts.ENABLE);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 通过系统档案编号查询档案
	 * */
	public List<ArcBaseInfo> findArcByXtdabh(String xtdabh) {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		example.createCriteria().andXtdabhEqualTo(xtdabh);
		return arcBaseInfoDAO.selectByExample(example);
	}

	/**
	 * 保存储位信息及档案信息，同时更新当前格de使用量
	 * 
	 * @param info
	 *            档案信息。当给现有档案添加储位时，此对象必须包含系统编号字段值
	 * @param user
	 *            用户
	 * @param type
	 *            保存类型：0：创建档案和储位编号，1：给现有档案添加储位编号。
	 * */
	public String saveStore(ArcBaseInfo info, SuperUser user, int type)
			throws Exception {
		// 插入储位锁定
		synchronized (LOCK_SAVE) {
			// 刷新使用量锁定
			synchronized (LOCK_FLUSH) {
				// 判断储位编号是否为空
				if (null == info.getCwbh() || "".equals(info.getCwbh())
						|| "null".equals(info.getCwbh())) {
					return StoRet.PARAM_NOTHING_CWBH;
				} else
				// 储位编号长度不足
				if (info.getCwbh().length() != 8) {
					return StoRet.ERROR_CWBH;
				}

				String cwbh = info.getCwbh();// 取出储位编号
				String geid = CwglTools.splitGeId(cwbh);// 取出档案格id
				// 查询档案格
				StoSetting currentGe = stoSettingDAO.selectByPrimaryKey(geid);
				// 验证使用量与总容量是否达到上限
				if (Integer.parseInt(currentGe.getSyl()) >= Integer
						.parseInt(currentGe.getZrl())) {
					return StoRet.FULL_CW;
				}
				// 验证档案格是否可用
				if (currentGe.getSfky().equals(CwglConsts.DISABLE)) {
					return StoRet.DISABLE_CW;
				}
				// 验证该储位编号是否可用
				ArcBaseInfoExample infoExample = new ArcBaseInfoExample();
				infoExample.createCriteria().andCwbhEqualTo(cwbh)
						.andCwyxEqualTo(CwglConsts.ENABLE);
				int ky = arcBaseInfoDAO.countByExample(infoExample);
				if (ky > 0) {
					return StoRet.ALREADY_IN_USE;
				}
				
				// 插入系统档案编号，保存档案信息
				if (type == 0) {
					Date date = new Date();
					// 设置ID
					info.setId(StringTools.getUUID());
					// 设置档案状态
					info.setDazt(ArcStatus.YLR_LSDA);
					// 归档人
					info.setGdr(user.getUserCode());
					// 归档时间
					info.setGdsj(date);
					// 合档标记
					info.setHdbj("0");// 未合档
					// 历史档案
					info.setDalx("2");
					// 系统档案编号
					String xtdabh=barcodeService.getXtdabh("H");
					System.out.println("储位录入："+xtdabh);//-=============测试储位编号====================
					info.setXtdabh(xtdabh);
					// 创建人
					info.setCjr(user.getUserCode());
					info.setCjrmc(user.getUserName());
					info.setCjsj(date);
					// 设置储位信息
					info.setCwsj(currentGe.getCid());// 上级储位编号即为当前格编号
					info.setCwyx("1");// 储位编号有效
					// 插入信息
					arcBaseInfoDAO.insert(info);
					
					insertStatueRecord(info,user);
				} else
				// 更新储位编号
				if (type == 1) {
					// 根据系统编号查询到要添加储位编号的档案
					String xtdabh = info.getXtdabh();
					ArcBaseInfoExample example = new ArcBaseInfoExample();
					example.createCriteria().andXtdabhEqualTo(xtdabh);
					List<ArcBaseInfo> list = arcBaseInfoDAO
							.selectByExample(example);
					// 数据正常，添加储位
					if (list.size() == 1) {
						info = list.get(0);
						info.setCwbh(cwbh);
						info.setCwsj(currentGe.getCid());// 上级储位编号即为当前格编号
						info.setCwyx("1");// 储位编号有效
						// 更新信息
						arcBaseInfoDAO.updateByPrimaryKeySelective(info);
					} else if (list.size() == 0) {
						return StoRet.NOTHING_XTDABH;
					} else {
						return StoRet.XTDABH_ERROR;
					}
				} else {
					throw new RuntimeException(
							"未选择储位编号保存类型：0-创建档案和储位编号，1-给现有档案添加储位编号。");
				}

				// 刷新使用量
				// 统计该档格的使用量，查询条件为：按照上级储位编号查询有效的储位
				infoExample.clear();
				infoExample.createCriteria().andCwsjEqualTo(geid)
						.andCwyxEqualTo(CwglConsts.ENABLE);
				int used = arcBaseInfoDAO.countByExample(infoExample);// 得到使用量
				currentGe.setSyl(used + "");// 设置使用量
				stoSettingDAO.updateByPrimaryKeySelective(currentGe);

				return StoRet.SUCCESS;
			}
		}
	}

	private void insertStatueRecord(ArcBaseInfo info, SuperUser user) {
		ArcStatusRecord record = new ArcStatusRecord();
		record.setId(UUID.randomUUID().toString());
		record.setXtdabh(info.getXtdabh());
		record.setCzr(user.getUserCode());
		record.setCzrmc(user.getUserName());
		record.setCzsj(new Date());
		record.setStatusCode(info.getDazt());
		record.setBz("历史档案录入");
		arcStatusRecordDAO.insert(record );
		
	}

	/**
	 * 通过id更新归档表
	 * */
	public void updateArcById(ArcBaseInfo info) throws Exception {
		arcBaseInfoDAO.updateByPrimaryKeySelective(info);
	}

	/**
	 * 获取某一格子内可用的储位编号
	 * */
	public String[] getUsableCwbh(String geid) {
		// 查询该格档案
		StoSetting ge = stoSettingDAO.selectByPrimaryKey(geid);
		if (null == ge) {
			return new String[] { "0", "未查询到相关的储位" };
		} else if (ge.getSfky().equals(CwglConsts.DISABLE)) {
			return new String[] { "0", "该储位已禁用" };
		} else if (ge.getSyl().equals(ge.getZrl())) {
			return new String[] { "0", "该储位已满" };
		}

		// 获取该格可用的储位序号
		Integer xh = arcBaseInfoDAO.findUsableCwxh(geid);
		if (null != xh) {
			String cwbh = ge.getCid() + StringTools.lpad(xh.toString(), 3, "0");
			return new String[] { "1", cwbh };
		} else {
			return new String[] { "0", "未查询到可用的储位编号" };
		}
	}

	/**
	 * 删除一份已录入历史档案
	 * */
	public void deleteArcCwbh(List<String> xtdabhs) throws Exception {
		ArcBaseInfoExample example = new ArcBaseInfoExample();
		for (int i = 0; i < xtdabhs.size(); i++) {
			String xtdabh = xtdabhs.get(i);
			example.clear();
			example.createCriteria().andXtdabhEqualTo(xtdabh)
					.andCwyxEqualTo(CwglConsts.ENABLE);
			List<ArcBaseInfo> list = arcBaseInfoDAO.selectByExample(example);
			if (list.size() == 1) {
				ArcBaseInfo info = list.get(0);
				// 只允许删除已录入的档案
				if (info.getDazt().equals(ArcStatus.YLR_LSDA)) {
					// 删除档案
					arcBaseInfoDAO.deleteByPrimaryKey(info.getId());
					// 刷新储位使用量
					flushUsed(info.getCwsj());
				} else {
					throw new VamsRTExcaption("该档案禁止删除：" + xtdabh);
				}
			} else if (list.size() == 0) {
				throw new VamsRTExcaption("删除异常，未找到档案：" + xtdabh);
			} else {
				throw new VamsRTExcaption("数据异常，存在相同的档案：" + xtdabh);
			}
		}

	}

	/**
	 * 储位迁移，自动模式
	 * 
	 * @param xtdabh
	 *            系统编号
	 * @param tagcw
	 *            目标格储位id
	 *            @return [1]新储位编号[2]系统编号
	 * */
	public String[] moveInfoByAuto(String xtdabh, String tagcw)
			throws Exception {
		// 查询该档案
		ArcBaseInfoExample infoExample = new ArcBaseInfoExample();
		infoExample.createCriteria().andXtdabhEqualTo(xtdabh)
				.andCwyxEqualTo(CwglConsts.ENABLE);
		List<ArcBaseInfo> infos = arcBaseInfoDAO.selectByExample(infoExample);
		// 档案数据正常
		if (infos.size() == 1) {
			// 获取到该档案
			ArcBaseInfo info = infos.get(0);
			// 取出旧储位编号的格
			String oldGeid = info.getCwsj();
			// 获取目标档案格
			StoSetting ge = findSetByCid(tagcw);
			// 获取可用的储位编号
			String cwbh[] = getUsableCwbh(ge.getCid());
			if (cwbh[0].equals("1")) {
				String newcwbh = cwbh[1];
				synchronized (LOCK_SAVE) {
					synchronized (LOCK_FLUSH) {
						info.setCwbh(newcwbh);// 写入储位编号
						info.setCwsj(ge.getCid());// 写入储位格id
						// 更新储位数据
						arcBaseInfoDAO.updateByPrimaryKeySelective(info);
						// 如果档案状态等于待审核入库，则更新入库表
						if (info.getDazt().equals(ArcStatus.DSH_RK)) {
							pubStorageInOutService.updateRedayInCwbh(xtdabh,
									newcwbh);
						}
						// 刷新2个储位格
						flushGe(new String[] { oldGeid, ge.getCid() });
					}
				}
				return new String[] { "1", newcwbh, xtdabh };
			} else {
				return cwbh;
			}
		} else if (infos.size() == 0) {
			return new String[] { "0", "未查询到档案：" + xtdabh };
		} else {
			return new String[] { "0", "数据异常，存在相同的系统编号：" + xtdabh };
		}
	}

	// =============================================

	/**
	 * 刷新储位格**此方法必须在synchronized内使用
	 * */
	private void flushGe(String[] geids) throws Exception {
		for (int i = 0; i < geids.length; i++) {
			String geid = geids[i];
			// 如果不为空
			if (null != geid && !"".equals(geid)) {
				// 查询使用量
				ArcBaseInfoExample infoExample = new ArcBaseInfoExample();
				infoExample.createCriteria().andCwsjEqualTo(geid)
						.andCwyxEqualTo(CwglConsts.ENABLE);
				int count = arcBaseInfoDAO.countByExample(infoExample);
				StoSettingExample settingExample = new StoSettingExample();
				settingExample.createCriteria().andCidEqualTo(geid);
				StoSetting setting = stoSettingDAO.selectByPrimaryKey(geid);
//				if (setting.getSyl().equals(setting.getZrl())) {
//					throw new VamsRTExcaption("储位容量已满（" + setting.getSyl()
////							+ "/" + setting.getZrl() + "）：" + setting.getCid());
//				} else {
					setting.setSyl(count + "");
					stoSettingDAO.updateByPrimaryKeySelective(setting);
//				}
			}
		}
	}

	// ===============================================

	public void setStoSettingDAO(StoSettingDAO stoSettingDAO) {
		this.stoSettingDAO = stoSettingDAO;
	}

	public void setArcBaseInfoDAO(ArcBaseInfoDAO arcBaseInfoDAO) {
		this.arcBaseInfoDAO = arcBaseInfoDAO;
	}

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public void setBarcodeService(BarcodeService barcodeService) {
		this.barcodeService = barcodeService;
	}

	public void setPubStorageInOutService(
			PubStorageInOutService pubStorageInOutService) {
		this.pubStorageInOutService = pubStorageInOutService;
	}

	public void setArcStatusRecordDAO(ArcStatusRecordDAO arcStatusRecordDAO) {
		this.arcStatusRecordDAO = arcStatusRecordDAO;
	}
	

}
