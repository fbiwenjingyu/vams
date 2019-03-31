package com.pd.cwgl.model;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.components.If;

import com.pd.arc.model.ArcBaseInfo;
import com.pd.system.utils.DateTools;

public class ArcBaseInfoSdate {

	private String id;

	private String dazt;

	private String xh;

	private String ywlx;

	private String lsh;

	private String hpzl;

	private String hphm;

	private String clsbdh;

	private String fprq;

	private String syr;

	private String sfzmhm;

	private String zsxxdz;

	private String clpp1;

	private String clxh;

	private String cllx;

	private String csys;

	private String zzg;

	private String fdjh;

	private String bz;

	private String dalx;

	private String gdr;

	private String gdsj;

	private String dabh;

	private String cwbh;

	private String xzqh;

	private String deptcode;

	private String hdbj;

	private String xtdabh;

	private String clpp2;

	private String gdrmc;

	private String zzcmc;

	private String sfzmmc;

	private String fzjg;

	private String djrq;

	private String sqrq;

	private String cjr;

	private String cjrmc;

	private String cjsj;

	private String shr;

	private String shrmc;

	private String shsj;

	private String kfbj;

	private String bjrq;

	private String pc;

	private String sfbh;

	private String cwsj;

	private String cwyx;

	public ArcBaseInfoSdate() {
	}

	
	public ArcBaseInfoSdate(String id, String dazt, String xh, String ywlx,
			String lsh, String hpzl, String hphm, String clsbdh, String fprq,
			String syr, String sfzmhm, String zsxxdz, String clpp1,
			String clxh, String cllx, String csys, String zzg, String fdjh,
			String bz, String dalx, String gdr, String gdsj, String dabh,
			String cwbh, String xzqh, String deptcode, String hdbj,
			String xtdabh, String clpp2, String gdrmc, String zzcmc,
			String sfzmmc, String fzjg, String djrq, String sqrq, String cjr,
			String cjrmc, String cjsj, String shr, String shrmc, String shsj,
			String kfbj, String bjrq, String pc, String sfbh, String cwsj,
			String cwyx) {
		super();
		this.id = id;
		this.dazt = dazt;
		this.xh = xh;
		this.ywlx = ywlx;
		this.lsh = lsh;
		this.hpzl = hpzl;
		this.hphm = hphm;
		this.clsbdh = clsbdh;
		this.fprq = fprq;
		this.syr = syr;
		this.sfzmhm = sfzmhm;
		this.zsxxdz = zsxxdz;
		this.clpp1 = clpp1;
		this.clxh = clxh;
		this.cllx = cllx;
		this.csys = csys;
		this.zzg = zzg;
		this.fdjh = fdjh;
		this.bz = bz;
		this.dalx = dalx;
		this.gdr = gdr;
		this.gdsj = gdsj;
		this.dabh = dabh;
		this.cwbh = cwbh;
		this.xzqh = xzqh;
		this.deptcode = deptcode;
		this.hdbj = hdbj;
		this.xtdabh = xtdabh;
		this.clpp2 = clpp2;
		this.gdrmc = gdrmc;
		this.zzcmc = zzcmc;
		this.sfzmmc = sfzmmc;
		this.fzjg = fzjg;
		this.djrq = djrq;
		this.sqrq = sqrq;
		this.cjr = cjr;
		this.cjrmc = cjrmc;
		this.cjsj = cjsj;
		this.shr = shr;
		this.shrmc = shrmc;
		this.shsj = shsj;
		this.kfbj = kfbj;
		this.bjrq = bjrq;
		this.pc = pc;
		this.sfbh = sfbh;
		this.cwsj = cwsj;
		this.cwyx = cwyx;
	}



	/**
	 * 六合一数据转换bean
	 * */
	public ArcBaseInfoSdate(Map<String, String> stoMap) {
		if (null != stoMap) {
			this.xh = checkMap(stoMap.get("xh"));
			this.ywlx = checkMap(stoMap.get("ywlx"));
			this.lsh = checkMap(stoMap.get("lsh"));
			this.hpzl = checkMap(stoMap.get("hpzl"));
			this.hphm = checkMap(stoMap.get("hphm"));
			this.clsbdh = checkMap(stoMap.get("clsbdh"));
			this.fprq = checkMap(stoMap.get("fprq"));
			this.syr = checkMap(stoMap.get("syr"));
			this.sfzmmc = checkMap(stoMap.get("sfzmmc"));
			this.sfzmhm = checkMap(stoMap.get("sfzmhm"));
			this.zsxxdz = checkMap(stoMap.get("zsxxdz"));
			this.clpp1 = checkMap(stoMap.get("clpp1"));
			this.clxh = checkMap(stoMap.get("clxh"));
			this.cllx = checkMap(stoMap.get("cllx"));
			this.csys = checkMap(stoMap.get("csys"));
			this.zzg = checkMap(stoMap.get("zzg"));
			this.fdjh = checkMap(stoMap.get("fdjh"));
			this.xzqh = checkMap(stoMap.get("xzqh"));
			this.clpp2 = checkMap(stoMap.get("clpp2"));
			this.zzcmc = checkMap(stoMap.get("zzcmc"));
			this.fzjg = checkMap(stoMap.get("fzjg"));
			this.djrq = checkMap(stoMap.get("djrq"));
		}
	}
	
	/**
	 * 验证map是否为空
	 * */
	private String checkMap(String str){
		if (null == str) {
			return "";
		}else{
			return str;
		}
	}

	/**
	 * 转换到ArcBaseInfo
	 * */
	public ArcBaseInfo toArcBaseInfo() {
		return new ArcBaseInfo(checkStr(id), checkStr(dazt), checkStr(xh), checkStr(ywlx), checkStr(lsh), checkStr(hpzl), checkStr(hphm), checkStr(clsbdh),
				toDate(fprq), checkStr(syr), checkStr(sfzmhm), checkStr(zsxxdz), checkStr(clpp1), checkStr(clxh), checkStr(cllx), checkStr(csys),
				checkStr(zzg), checkStr(fdjh), checkStr(bz), checkStr(dalx), checkStr(gdr), toDate(gdsj), checkStr(dabh), checkStr(cwbh), checkStr(xzqh),
				checkStr(deptcode), checkStr(hdbj), checkStr(xtdabh), checkStr(clpp2), checkStr(gdrmc), checkStr(zzcmc), checkStr(sfzmmc), checkStr(fzjg),
				toDate(djrq), toDate(sqrq), checkStr(cjr), checkStr(cjrmc), toDate(cjsj), checkStr(shr),
				checkStr(shrmc), toDate(shsj), checkStr(kfbj), toDate(bjrq), checkStr(pc), checkStr(sfbh), checkStr(cwsj), checkStr(cwyx));
	}
	
	public String checkStr(String str){
		if (null != str&&"null".equals(str.toLowerCase())) {
			return null;
		}else{
			return str;
		}
	}

	/**
	 * 日期转换
	 * */
	private Date toDate(String str) {
		if (null != str && !"".equals(str.trim()) && !"null".equals(str.toLowerCase())) {
			return DateTools.toDate(str, DateTools.yyyy_MM_dd_HH_mm_ss);
		} else {
			return null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDazt() {
		return dazt;
	}

	public void setDazt(String dazt) {
		this.dazt = dazt;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getClsbdh() {
		return clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getFprq() {
		return fprq;
	}

	public void setFprq(String fprq) {
		this.fprq = fprq;
	}

	public String getSyr() {
		return syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getZsxxdz() {
		return zsxxdz;
	}

	public void setZsxxdz(String zsxxdz) {
		this.zsxxdz = zsxxdz;
	}

	public String getClpp1() {
		return clpp1;
	}

	public void setClpp1(String clpp1) {
		this.clpp1 = clpp1;
	}

	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
	}

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getCsys() {
		return csys;
	}

	public void setCsys(String csys) {
		this.csys = csys;
	}

	public String getZzg() {
		return zzg;
	}

	public void setZzg(String zzg) {
		this.zzg = zzg;
	}

	public String getFdjh() {
		return fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDalx() {
		return dalx;
	}

	public void setDalx(String dalx) {
		this.dalx = dalx;
	}

	public String getGdr() {
		return gdr;
	}

	public void setGdr(String gdr) {
		this.gdr = gdr;
	}

	public String getGdsj() {
		return gdsj;
	}

	public void setGdsj(String gdsj) {
		this.gdsj = gdsj;
	}

	public String getDabh() {
		return dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getCwbh() {
		return cwbh;
	}

	public void setCwbh(String cwbh) {
		this.cwbh = cwbh;
	}

	public String getXzqh() {
		return xzqh;
	}

	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getHdbj() {
		return hdbj;
	}

	public void setHdbj(String hdbj) {
		this.hdbj = hdbj;
	}

	public String getXtdabh() {
		return xtdabh;
	}

	public void setXtdabh(String xtdabh) {
		this.xtdabh = xtdabh;
	}

	public String getClpp2() {
		return clpp2;
	}

	public void setClpp2(String clpp2) {
		this.clpp2 = clpp2;
	}

	public String getGdrmc() {
		return gdrmc;
	}

	public void setGdrmc(String gdrmc) {
		this.gdrmc = gdrmc;
	}

	public String getZzcmc() {
		return zzcmc;
	}

	public void setZzcmc(String zzcmc) {
		this.zzcmc = zzcmc;
	}

	public String getSfzmmc() {
		return sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getFzjg() {
		return fzjg;
	}

	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}

	public String getDjrq() {
		return djrq;
	}

	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}

	public String getSqrq() {
		return sqrq;
	}

	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjrmc() {
		return cjrmc;
	}

	public void setCjrmc(String cjrmc) {
		this.cjrmc = cjrmc;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShrmc() {
		return shrmc;
	}

	public void setShrmc(String shrmc) {
		this.shrmc = shrmc;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getKfbj() {
		return kfbj;
	}

	public void setKfbj(String kfbj) {
		this.kfbj = kfbj;
	}

	public String getBjrq() {
		return bjrq;
	}

	public void setBjrq(String bjrq) {
		this.bjrq = bjrq;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getSfbh() {
		return sfbh;
	}

	public void setSfbh(String sfbh) {
		this.sfbh = sfbh;
	}

	public String getCwsj() {
		return cwsj;
	}

	public void setCwsj(String cwsj) {
		this.cwsj = cwsj;
	}

	public String getCwyx() {
		return cwyx;
	}

	public void setCwyx(String cwyx) {
		this.cwyx = cwyx;
	}

}