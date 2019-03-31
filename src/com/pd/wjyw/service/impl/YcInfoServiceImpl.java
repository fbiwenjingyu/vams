package com.pd.wjyw.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pd.arc.dao.impl.ArcBaseInfoDAO;
import com.pd.arc.dao.impl.ArcStatusRecordDAO;
import com.pd.arc.model.ArcBaseInfo;
import com.pd.arc.model.ArcStatusRecord;
import com.pd.right.model.Dept;
import com.pd.right.model.SuperUser;
import com.pd.system.framework.BaseService;
import com.pd.system.framework.Pagination;
import com.pd.system.res.ArcStatus;
import com.pd.system.utils.DateTools;
import com.pd.system.utils.SeqNumberUtil;
import com.pd.wjyw.dao.impl.YcInfoDAO;
import com.pd.wjyw.dao.impl.YcInfoPicDAO;
import com.pd.wjyw.model.YcInfo;
import com.pd.wjyw.model.YcInfoExample;
import com.pd.wjyw.model.YcInfoPic;
import com.pd.wjyw.model.YcInfoPicExample;
import com.pd.wjyw.model.YcInfoSuper;
import com.pd.wjyw.service.YcInfoService;

public class YcInfoServiceImpl extends BaseService implements YcInfoService {
	private YcInfoDAO ycInfoDAO ;
	private YcInfoPicDAO ycInfoPicDAO;
	private ArcStatusRecordDAO arcStatusRecordDAO;
	private ArcBaseInfoDAO arcBaseInfoDAO;
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(YcInfo ycInfo) throws Exception {
		ycInfoDAO.insert(ycInfo);
	}

	@Override
	public void delete(String id) throws Exception {
		YcInfoPicExample example = new YcInfoPicExample();
		YcInfoPicExample.Criteria ca = example.createCriteria();
		ca.andYcinfoIdEqualTo(id);
		ycInfoPicDAO.deleteByExample(example);
		ycInfoDAO.deleteByPrimaryKey(id);
	}

	@Override
	public Pagination findPageList(int index, YcInfoExample ycInfoExample)
			throws Exception {
		return super.findPageList(index, ycInfoExample, ycInfoDAO);
	}

	@Override
	public YcInfo getEntity(String id) throws Exception {
		return ycInfoDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<YcInfo> query(YcInfoExample example) throws Exception {
		return ycInfoDAO.selectByExampleToPage(example);
	}

	@Override
	public List<YcInfo> selectByExample(YcInfoExample ycInfoExample) {
		return ycInfoDAO.selectByExample(ycInfoExample);
	}
	

	public void setYcInfoDAO(YcInfoDAO ycInfoDAO) {
		this.ycInfoDAO = ycInfoDAO;
	}

	public void setYcInfoPicDAO(YcInfoPicDAO ycInfoPicDAO) {
		this.ycInfoPicDAO = ycInfoPicDAO;
	}
    
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public void setArcStatusRecordDAO(ArcStatusRecordDAO arcStatusRecordDAO) {
		this.arcStatusRecordDAO = arcStatusRecordDAO;
	}
	

	public void setArcBaseInfoDAO(ArcBaseInfoDAO arcBaseInfoDAO) {
		this.arcBaseInfoDAO = arcBaseInfoDAO;
	}

	@Override
	public String add(String sfwj,String xzqh) {
		/**
		 * 获取当前年月日
		 */
		String code = DateTools.getFormatDate(new Date(),"yyyyMMdd");
		Map map = new HashMap();
		/**
		 * 从数据库获取当前日期的最大序号
		 */
		map = ycInfoDAO.getXh(code);
		BigDecimal maxBh = (BigDecimal) map.get("XTDABH");
		String maxXh = "";

		if (maxBh == null)
			maxXh = "0";
		else
			maxXh = String.valueOf(maxBh).substring(9);
		maxXh = String.valueOf(Integer.parseInt(maxXh) + 1);
		String xh = SeqNumberUtil.addZeroForNum(maxXh, 5);
		String xtdabh = sfwj + code +xh;
		YcInfo ycinfo = new YcInfo();
		ycinfo.setId(UUID.randomUUID().toString());
		ycinfo.setXtdabh(xtdabh);
		ycinfo.setSfwj(sfwj);
		//ycinfo.setShjg("S");
		ycinfo.setXzqh(xzqh);
		ycInfoDAO.insert(ycinfo);
		insertArcBaseInfo(xtdabh);
		return xtdabh;
		
	}
	
	private void insertArcBaseInfo(String xtdabh) {
		ArcBaseInfo entity = new ArcBaseInfo();
		entity.setId(UUID.randomUUID().toString());
		entity.setXtdabh(xtdabh);
		entity.setCjsj(new Date());
		entity.setDalx("1");
		entity.setDazt(ArcStatus.WSM);
		arcBaseInfoDAO.insert(entity );
		
	}

	@Override
	public String getXtdabh(String sfwj) {
		/**
		 * 获取当前年月日
		 */
		String code = DateTools.getFormatDate(new Date(),"yyyyMMdd");
		Map map = new HashMap();
		/**
		 * 从数据库获取当前日期的最大序号
		 */
		map = ycInfoDAO.getXh(code);
		BigDecimal maxBh = (BigDecimal) map.get("XTDABH");
		String maxXh = "";

		if (maxBh == null)
			maxXh = "0";
		else
			maxXh = String.valueOf(maxBh).substring(9);
		maxXh = String.valueOf(Integer.parseInt(maxXh) + 1);
		String xh = SeqNumberUtil.addZeroForNum(maxXh, 5);
		String xtdabh = sfwj + code +xh;
		return xtdabh;
		
	}
	
	@Override
	public String getXtdabh2(String sfwj) {
		/**
		 * 获取当前年月日
		 */
		String code = DateTools.getFormatDate(new Date(),"yyyyMMdd");
		Map map = new HashMap();
		/**
		 * 从数据库获取当前日期的最大序号
		 */
		map = ycInfoDAO.getXh(code);
		BigDecimal maxBh = (BigDecimal) map.get("XTDABH");
		String maxXh = "";

		if (maxBh == null)
			maxXh = "0";
		else
			maxXh = String.valueOf(maxBh).substring(9);
		maxXh = String.valueOf(Integer.parseInt(maxXh) + 2);
		String xh = SeqNumberUtil.addZeroForNum(maxXh, 5);
		String xtdabh = sfwj + code +xh;
		return xtdabh;
		
	}
	
	private String getXtdabh(String sfwj,int index) {
		/**
		 * 获取当前年月日
		 */
		String code = DateTools.getFormatDate(new Date(),"yyyyMMdd");
		Map map = new HashMap();
		/**
		 * 从数据库获取当前日期的最大序号
		 */
		map = ycInfoDAO.getXh(code);
		BigDecimal maxBh = (BigDecimal) map.get("XTDABH");
		String maxXh = "";

		if (maxBh == null)
			maxXh = "0";
		else
			maxXh = String.valueOf(maxBh).substring(9);
		maxXh = String.valueOf(Integer.parseInt(maxXh) + index);
		String xh = SeqNumberUtil.addZeroForNum(maxXh, 5);
		String xtdabh = sfwj + code +xh;
		return xtdabh;
		
	}
	
	private List<String> getXtdabhList(String sfwj,int num){
		List<String> list = new ArrayList<String>();
		String code = DateTools.getFormatDate(new Date(),"yyyyMMdd");
		Map map = new HashMap();
		/**
		 * 从数据库获取当前日期的最大序号
		 */
		map = ycInfoDAO.getXh(code);
		BigDecimal maxBh = (BigDecimal) map.get("XTDABH");
		String maxXh = "";

		if (maxBh == null)
			maxXh = "0";
		else
			maxXh = String.valueOf(maxBh).substring(9);
	    for(int i=1;i<=num;i++){
	    	String maxXhOfNum = String.valueOf(Integer.parseInt(maxXh) + i);
	    	String xh = SeqNumberUtil.addZeroForNum(maxXhOfNum, 5);
	    	String xtdabh = sfwj + code +xh;
	    	list.add(xtdabh);
	    }
	    return list;
	}
	
	@Override
	public void insertCode(String xtdabh,String sfwj,String xzqh){
		YcInfo ycinfo = new YcInfo();
		ycinfo.setId(UUID.randomUUID().toString());
		ycinfo.setXtdabh(xtdabh);
		ycinfo.setSfwj(sfwj);
		//ycinfo.setShjg("S");
		ycinfo.setXzqh(xzqh);
		ycInfoDAO.insert(ycinfo);
		insertArcBaseInfo(xtdabh);
	}


	@Override
	public List<YcInfoSuper> getYcInfoByDept(Map map) {
		List<YcInfoSuper> list = new ArrayList<YcInfoSuper>();
		list = ycInfoDAO.getYcInfoByDept(map);
		return list;
	}

	@Override
	public List<YcInfo> findYcInfoByXtdabh(String xtdabh) {
		YcInfoExample ycInfoExample = new YcInfoExample();
		ycInfoExample.createCriteria().andXtdabhEqualTo(xtdabh);
		return ycInfoDAO.selectByExample(ycInfoExample);
	}

	@Override
	public void update(YcInfo record,List<YcInfoPic> list,SuperUser user) throws Exception {
		if(list != null && list.size() >0){
			for(YcInfoPic ycInfoPic : list){
				ycInfoPicDAO.updateByPrimaryKeySelective(ycInfoPic);
			}
		}
		ycInfoDAO.updateByPrimaryKeySelective(record);
		
		YcInfo ycInfo = ycInfoDAO.selectByPrimaryKey(record.getId());
		
		insertStatusRecord(ycInfo,user);
		
	}
   
	private void insertStatusRecord(YcInfo ycInfo,SuperUser user) {
		ArcStatusRecord record = new ArcStatusRecord();
		record.setId(UUID.randomUUID().toString());
		record.setXtdabh(ycInfo.getXtdabh());
		record.setCzr(user.getUserCode());
		record.setCzrmc(user.getUserName());
		record.setCzsj(new Date());
		record.setStatusCode(null);
		record.setBz("外检审核");
		arcStatusRecordDAO.insert(record );
		
	}

	@Override
	public void ycVerify(String createTime, String name,String shrmc, String shjg,
			String bz, String wjbh, String[] pid) {
		String sqlYcInfo="update yc_info set shsj=to_date('"+createTime+"','YYYY-MM-DD HH24:MI:SS'),shr='"+name+"',shjg='"+shjg+"',bz='"+bz+"',shrmc='" + shrmc + "' where xtdabh='"+wjbh+"'";
		jdbcTemplate.execute(sqlYcInfo);
		for(int i=0;i<pid.length;i++){
			String sqlPicInfo="update yc_info_pic set pic_status='"+shjg+"' where id='"+pid[i]+"'";
			jdbcTemplate.execute(sqlPicInfo);
		}
		
	}

	@Override
	public void backOffice(List<YcInfoPic> pics, String wjbh) {
		for(YcInfoPic pic : pics){
			String path = pic.getPicpath();
			File file = new File(path);
			file.delete();
		}
		
		//2.删除外检图片表数据
		String sqlDel = "delete from YC_INFO_PIC where xtdabh='"+wjbh+"'";
		jdbcTemplate.execute(sqlDel);
        
		//3.修改外检主表数据
		String sqlUpdate = "update yc_info set ywlx='',hphm='',hpzl='',cjr='',cjrmc='',xzqh='',bz='',lsh='',shr='',shsj='',shjg='S'" + " where xtdabh='"+wjbh+"' ";
		jdbcTemplate.execute(sqlUpdate);
		
	}

	@Override
	public Pagination getPageByExample(int index, Map map) {
		Integer total = 0;
		String sql = "select count(*) as Total from YC_INFO SAMPLE("+map.get("percent")+") seed (10) where " +
				"(CJR is not null and SFWJ = 'Y') ";
		List list = jdbcTemplate.queryForList(sql);
		if(list != null && list.size() > 0){
				Iterator it = list.iterator();
				while(it.hasNext()){
					Map map1 = (Map) it.next();
					total = Integer.valueOf(map1.get("Total").toString());
				}
			
		}  
		Pagination page = new Pagination(total,index);
		return page;
	}

	@Override
	public List<YcInfo> selectByRandomExampleToPage(Map object) {
		String sql = "select * from (select t.*, ROWNUM as row_num from (" +
				" select ID, XTDABH, YWLX, HPHM, HPZL, CJR, CJRMC, CJSJ, XZQH, BZ, STATUS, " +
				"SHR, SHRMC, SHSJ, SHJG,LSH, SFWJ, DEPTCODE from YC_INFO " +
				" SAMPLE("+object.get("percent")+") seed (10) where (CJR is not null and SFWJ = 'Y') order by cjsj desc" +
				" )t where ROWNUM <= "+object.get("limit")+") where row_num >= "+object.get("start")+"";
			List list = jdbcTemplate.queryForList(sql);
			List<YcInfo> list_data = new ArrayList<YcInfo>();
			if(list != null && list.size() > 0){
				Iterator it = list.iterator();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
				while(it.hasNext()){
					Map map = (Map) it.next();
					YcInfo yc = new YcInfo();
					yc.setId(map.get("ID")+"");
					yc.setXtdabh(map.get("XTDABH")+"");
					yc.setYwlx(map.get("YWLX")+"");
					yc.setHphm(map.get("HPHM")==null?"":map.get("HPHM") + "");
					yc.setHpzl(map.get("HPZL")+"");
					yc.setShrmc(map.get("SHRMC")+"");
					yc.setLsh(map.get("LSH")+"");
					try {
						if(map.get("SHSJ")!=null){
							yc.setShsj(sdf.parse((map.get("SHSJ")+"")));
						}
						if(map.get("CJSJ")!=null){
							yc.setCjsj(sdf.parse((map.get("CJSJ")+"")));
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					yc.setCjrmc(map.get("CJRMC")+"");
					yc.setStatus(map.get("STATUS")+"");
					yc.setShjg(map.get("SHJG")+"");
					yc.setDeptcode(map.get("DEPTCODE")+"");
					list_data.add(yc);
				}
			}  
			return list_data;
	}

	@Override
	public List<String> getXtdabhBatch(String sfwj, int num) {
		/*List<String> list = new ArrayList<String>();
	    for(int i = 1;i <= num;i++){
	    	String xtdabh = getXtdabh(sfwj, i);
	    	list.add(xtdabh);
	    }
		return list;*/
		return getXtdabhList(sfwj,num);
	}

	@Override
	public List<YcInfo> findYcInfoByXtdabhList(List<String> list) {
		YcInfoExample ycInfoExample = new YcInfoExample();
		ycInfoExample.createCriteria().andXtdabhIn(list);
		return ycInfoDAO.selectByExample(ycInfoExample);
	}

	@Override
	public void insertBatchCode(List<String> list, String sfwj, String xzqh) {
		for(String xtdabh : list){
			YcInfo ycinfo = new YcInfo();
			ycinfo.setId(UUID.randomUUID().toString());
			ycinfo.setXtdabh(xtdabh);
			ycinfo.setSfwj(sfwj);
			//ycinfo.setShjg("S");
			ycinfo.setXzqh(xzqh);
			ycInfoDAO.insert(ycinfo);
			insertArcBaseInfo(xtdabh);
		}
		
		
	}
	
}


