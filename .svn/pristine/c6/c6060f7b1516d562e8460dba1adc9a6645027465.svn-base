package com.pd.wjyw.service.impl;

import java.util.*;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pd.arc.dao.impl.ArcBaseInfoDAO;
import com.pd.arc.dao.impl.ArcStatusRecordDAO;
import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcBaseInfoExample;
import com.pd.arc.model.ArcStatusRecord;
import com.pd.right.model.SuperUser;
import com.pd.system.res.ArcStatus;
import com.pd.webservice.core.WsRet;
import com.pd.webservice.sixandone.QueryVehInfo;
import com.pd.wjyw.dao.impl.YcInfoDAO;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.service.YcInfoBarcodeService;

public class YcInfoBarcodeServiceImpl implements YcInfoBarcodeService{
	/**
	 * 合并条码返回的code
	 * W0 该外检记录不存在
	 * W1 该外检记录已经合并
	 * A0 该档案记录已经合并
	 * SUCCESS 合并成功
	 */
	final static String W0 = "该系统编号不存在";
	final static String W1 = "该系统编号在查验记录表已经与其他流水号合并";
	final static String W2 = "该流水号在查验记录表已经与其他系统编号合并";
	final static String A0 = "该系统编号在档案记录表已经与其他流水号合并";
	final static String A1 = "该流水号在档案记录表已经与其他系统编号合并";
	public final static String SUCCESS = "合并成功";
	final static String SIXFAIL = "六合一连接失败";
	
	private YcInfoDAO ycInfoDAO;
	private ArcBaseInfoDAO arcBaseInfoDAO;
	private ArcStatusRecordDAO arcStatusRecordDAO;
	private JdbcTemplate jdbcTemplate1;
	
	public void setJdbcTemplate1(JdbcTemplate jdbcTemplate1) {
		this.jdbcTemplate1 = jdbcTemplate1;
	}

	public void setArcStatusRecordDAO(ArcStatusRecordDAO arcStatusRecordDAO) {
		this.arcStatusRecordDAO = arcStatusRecordDAO;
	}



	/**
	 * 合并条码业务处理：查询六合一、写入收费库
	 * */
	public WsRet addHbtm(String xtdabh, String lsh,SuperUser user) {
		WsRet wsRet = new WsRet();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if(lsh != null && lsh.length()>0){
			/**
			 * 通过流水号查询在办业务信息，接口代码：01C22
			 * */
			wsRet = QueryVehInfo._01C22(lsh);
			if(wsRet.isOK()){
				list = wsRet.getDataList(); 
				/**
				 * 在办业务根据流水号查询没结果返回，则查询历史数据
				 */
				if(list.size() == 0){
					wsRet = QueryVehInfo._01C26(lsh);
					if(wsRet.isOK()){
						list = wsRet.getDataList(); 
					}else{
						wsRet.setCode(SIXFAIL);
					}
				}
			}else{
				wsRet.setCode(SIXFAIL);
			}
		}
		/**
		 * 有返回数据则从六合一获取机动车的数据
		 */
		if(list.size() > 0){
			Map<String,String> map = new HashMap<String,String>();
			map = list.get(0);
			List<ArcBaseInfo> arcBaseList = new ArrayList<ArcBaseInfo>();
			List<ArcBaseInfo> arcBaseLshList = new ArrayList<ArcBaseInfo>();
			List<YcInfo> ycInfoList = new ArrayList<YcInfo>();
			List<YcInfo> ycInfoLshList = new ArrayList<YcInfo>();
			
			/**
			 * 创建根据系统档案编号查询外检表数据对象
			 */
			YcInfoExample ycInfoExample = new YcInfoExample();
			YcInfoExample.Criteria ycInfoCa = ycInfoExample.createCriteria();
			ycInfoCa.andXtdabhEqualTo(xtdabh);
			    
			/**
			 * 创建根据流水号查询外检表数据对象
			 */
			YcInfoExample ycInfoLshExample = new YcInfoExample();
			YcInfoExample.Criteria ycInfoLshCa = ycInfoLshExample.createCriteria();
			ycInfoLshCa.andLshEqualTo(lsh);
			
			ycInfoList = ycInfoDAO.selectByExample(ycInfoExample);
			
			ycInfoLshList = ycInfoDAO.selectByExample(ycInfoLshExample);
			/**
			 * 根据系统档案编号查询档案表
			 */
			ArcBaseInfoExample arcBaseInfoExample = new ArcBaseInfoExample();
			ArcBaseInfoExample.Criteria arcBaseInfoCa = arcBaseInfoExample.createCriteria();
			arcBaseInfoCa.andXtdabhEqualTo(xtdabh);
			
			ArcBaseInfoExample arcBaseInfoLshExample = new ArcBaseInfoExample();
			ArcBaseInfoExample.Criteria arcBaseInfoLshCa = arcBaseInfoLshExample.createCriteria();
			arcBaseInfoLshCa.andLshEqualTo(lsh);
			
			arcBaseList = arcBaseInfoDAO.selectByExample(arcBaseInfoExample);
			arcBaseLshList = arcBaseInfoDAO.selectByExample(arcBaseInfoLshExample);
			
			ArcBaseInfo arcBaseInfo = new ArcBaseInfo();
			YcInfo ycInfo = new YcInfo();
			/**
			 * ycInfoList无返回记录则说明输入的系统档案编号不存在,wsRet的code返回W0
			 */
			if(ycInfoList.size() == 0){
				wsRet.setCode(W0);
			}else{
				
				/**
				 * ycInfoList有返回记录，获取对象的流水号，判断LSH是否为空，若不为空则说明已经合并,wsRet的code返回W1
				 */
				ycInfo = ycInfoList.get(0);
				if(ycInfo.getLsh() != null && ycInfo.getLsh().length()>0 ){
					//wsRet.setCode(W1);
					String msg = "该系统编号在查验记录表已经与其他流水号:" + ycInfo.getLsh()+ " 合并";
					wsRet.setCode(msg);
					
				}else{
					if(ycInfoLshList.size()>0 ){
						//wsRet.setCode(W2);
						String msg = "该流水号在查验记录表已经与其他系统编号:" + ycInfoLshList.get(0).getXtdabh()+ " 合并";
						wsRet.setCode(msg);
						
					}else{
						/**
						 * 根据流水号查询档案记录表如果大于0则已经合并过
						 */
						if(arcBaseLshList.size()>0){
							//wsRet.setCode(A1);
							String msg = "该流水号在档案记录表已经与其他系统编号:" + arcBaseLshList.get(0).getXtdabh() + " 合并";
							wsRet.setCode(msg);
						}else{
							/**
							 * arcBaseList无返回记录则说明改系统档案编号还未进行档案扫描,则插入机动车的基本信息
							 */
							if(arcBaseList.size() == 0){
								arcBaseInfo = getArcBaseInfo(map);
								arcBaseInfo.setId(UUID.randomUUID().toString());
								arcBaseInfo.setXtdabh(xtdabh);
								arcBaseInfo.setDazt(ArcStatus.WSM);
								addArcBase(arcBaseInfo);
								/**
								 * 未合并的外检记录则根据系统档案编号将数据补充到对应的外检信息里面
								 */
								ycInfo.setStatus("H");
								ycInfo.setLsh(map.get("lsh"));
								ycInfo.setHphm(map.get("hphm"));
								ycInfoDAO.updateByExampleSelective(ycInfo, ycInfoExample);
								/**
								 * 合并成功写入收费表信息
								 */
								jdbcTemplate1.execute("insert into LYZB_VEH_DALSH(LSH) values('"+ lsh + "')");
								
								//向操作记录表中写数据
								insertStatueRecord(xtdabh,null,user);
								
								wsRet.setCode(SUCCESS);
							}else{
								arcBaseInfo = arcBaseList.get(0);
								if(arcBaseInfo.getLsh() != null && arcBaseInfo.getLsh().length()>0 ){
									/**
									 *  提示该流水号已经合并
									 */
									//wsRet.setCode(A0);
									String msg = "该系统编号在档案记录表已经与其他流水号:" + arcBaseInfo.getLsh() + " 合并";
									wsRet.setCode(msg);
								}else{
									/**
									 * 查询的记录已经有流水号则说明该档案记录已经扫描过，则根据系统档案编号来补充六合一返回机动车的其他信息
									 */
									arcBaseInfo = getArcBaseInfo(map);
									updateArcBase(arcBaseInfo,arcBaseInfoExample);
									
									/**
									 * 未合并的外检记录则根据系统档案编号将数据补充到对应的外检信息里面
									 */
									ycInfo.setStatus("H");
									ycInfo.setLsh(lsh);
									ycInfo.setHphm(map.get("hphm"));
									
									/**
									 * 合并成功写入收费表信息，2014-10-22 by wzm 
									 */
									jdbcTemplate1.execute("insert into LYZB_VEH_DALSH(LSH) values('"+ lsh + "')");
									
									ycInfoDAO.updateByExampleSelective(ycInfo, ycInfoExample);
									
									//向操作记录表中写数据
									insertStatueRecord(xtdabh,arcBaseInfo.getDazt(),user);
									wsRet.setCode(SUCCESS);
								}
							}
						}
					}
				}
			}
		
	}else{
		wsRet.setCode("六合一未查询到此流水号："+lsh);
	}
		return wsRet;
	}
	

	private void insertStatueRecord(String xtdabh, String dazt, SuperUser user) {
		ArcStatusRecord record = new ArcStatusRecord();
		record.setId(UUID.randomUUID().toString());
		record.setXtdabh(xtdabh);
		record.setCzr(user.getUserCode());
		record.setCzrmc(user.getUserName());
		record.setCzsj(new Date());
		record.setStatusCode(dazt);
		record.setBz("合并流水");
		arcStatusRecordDAO.insert(record );
		
		
	}

	public ArcBaseInfo getArcBaseInfo(Map<String,String> map){
		ArcBaseInfo arcBaseInfo =  new ArcBaseInfo();
		arcBaseInfo.setLsh(map.get("lsh"));						//流水号
		arcBaseInfo.setXh(map.get("xh"));						//序号
		arcBaseInfo.setHpzl(map.get("hpzl"));					//号牌种类
		arcBaseInfo.setHphm(map.get("hphm"));					//号牌号码
		arcBaseInfo.setClpp1(map.get("clpp1"));					//品牌1
		arcBaseInfo.setClxh(map.get("clxh"));					//车辆型号
		arcBaseInfo.setClpp2(map.get("clpp2"));					//品牌2
		arcBaseInfo.setZzg(map.get("zzg"));						//制造国
		arcBaseInfo.setZzcmc(map.get("zzcmc"));					//制造厂名称
		arcBaseInfo.setClsbdh(map.get("clsbdh"));				//车辆识别代号
		arcBaseInfo.setFdjh(map.get("fdjh"));					//发动机号
		arcBaseInfo.setCllx(map.get("cllx"));					//车辆型号
		arcBaseInfo.setCsys(map.get("csys"));					//车身颜色
		arcBaseInfo.setSfzmhm(map.get("sfzmhm"));				//身份证明号码
		arcBaseInfo.setSyr(map.get("syr"));						//所有人
		arcBaseInfo.setFzjg(map.get("fzjg"));					//发证机关
		arcBaseInfo.setDabh(map.get("dabh"));					//档案编号
		arcBaseInfo.setDalx("1");								//档案类型1业务档案2历史档案
		arcBaseInfo.setYwlx(map.get("ywlx"));					//业务类型
		arcBaseInfo.setKfbj("0");								//库房标记
		arcBaseInfo.setSfbh("1");								//是否合并
		
		return arcBaseInfo;
	}
	public void addArcBase(ArcBaseInfo arcBaseInfo){
		arcBaseInfoDAO.insert(arcBaseInfo);
	}
	
	public void updateArcBase(ArcBaseInfo arcBaseInfo,ArcBaseInfoExample example){
		arcBaseInfoDAO.updateByExampleSelective(arcBaseInfo, example);
	}
	
	public void setYcInfoDAO(YcInfoDAO ycInfoDAO) {
		this.ycInfoDAO = ycInfoDAO;
	}
	public void setArcBaseInfoDAO(ArcBaseInfoDAO arcBaseInfoDAO) {
		this.arcBaseInfoDAO = arcBaseInfoDAO;
	}

}


