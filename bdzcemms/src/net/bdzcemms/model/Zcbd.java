package net.bdzcemms.model;

import java.io.Serializable;

public class Zcbd implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lydwh;  //使用单位号
	private String lydwm;  //使用单位名
	private String zcbh;   //设备编号
	private String zcflh;  //分类号
	private String zcmc;   //设备名称
	private String ppxh;   //品牌型号
	private String gg;     //规格
	private Double je;    //金额
	private Integer bdsl;  //变动数量
	private String cj;     //厂家
	private String ggrq;   //购置日期
	private String xz;     //现状
	private String syfx;   //使用方向
	private String fph;    //发票号
	private String ghs;    //供货商
	private String cfdbh;  //存放地编号
	private String cfdmc;  //存放地名称
	private String syrbh;  //使用人编号
	private String syr;    //使用人
	private String jsr;    //经手人
	private String shzt;   //审核状态
	private String jzr;    //记账人
	private String rzrq;   //入账日期*[start-date, end-date]
	private String bz;     //备注
	private String djh;    //单据号
    private String srrbh;   //输入人编号
	private String srr;    //输入人
	private String srrq;   //输入日期
	private String bdrq;   //变动日期
	private String bdyy;   //变动原因
	private String zrdwh;  //转入单位号
    private String zrdwm;         //转入单位名
	private Double bddj;   //变动单价
	private String sqrbh;  //申请人编号
	private String sqr;    //申请人
    
    public String getLydwh() {
		return lydwh;
	}
    public void setLydwh(String lydwh) {
    	this.lydwh = lydwh;
    }
    
    public String getLydwm() {
    	return lydwm;
    }
    
    public void setLydwm(String lydwm) {
    	this.lydwm = lydwm;
    }
    
    public String getZcbh() {
		return zcbh;
	}
    public void setZcbh(String zcbh) {
    	this.zcbh = zcbh;
    }
    
    public String getZcflh() {
		return zcflh;
	}
    public void setZcflh(String zcflh) {
    	this.zcflh = zcflh;
    }
    public String getZcmc() {
		return zcmc;
	}
    public void setZcmc(String zcmc) {
    	this.zcmc = zcmc;
    }
    
    public String getPpxh() {
		return ppxh;
	}
    public void setPpxh(String ppxh) {
    	this.ppxh = ppxh;
    }
    
    public String getGg() {
		return gg;
	}
    public void setGg(String gg) {
    	this.gg = gg;
    }
    
    public Double getJe() {
		return je;
	}
    public void setJe(Double je) {
    	this.je = je;
    }
    
    public Integer getBdsl() {
    	return bdsl;
    }
    
    public void setBdsl(Integer bdsl) {
    	this.bdsl = bdsl;
    }
    
    public String getCj() {
		return cj;
	}
    public void setCj(String cj) {
    	this.cj = cj;
    }
    
    public String getGgrq() {
		return ggrq;
	}
    public void setGgrq(String ggrq) {
    	this.ggrq = ggrq;
    }
    
    public String getXz() {
		return xz;
	}
    public void setXz(String xz) {
    	this.xz= xz;
    }
    
    public String getSyfx() {
		return syfx;
	}
    public void setSyfx(String syfx) {
    	this.syfx = syfx;
    }
    
    public String getFph() {
		return fph;
	}
    public void setFph(String fph) {
    	this.fph = fph;
    }
    
    public String getGhs() {
		return ghs;
	}
    public void setGhs(String ghs) {
    	this.ghs = ghs;
    }
    
    public String getCfdbh() {
		return cfdbh;
	}
    public void setCfdbh(String cfdbh) {
    	this.cfdbh = cfdbh;
    }
    
    public String getCfdmc() {
		return cfdmc;
	}
    public void setCfdmc(String cfdmc) {
    	this.cfdmc = cfdmc;
    }
    
    public String getSyrbh() {
		return syrbh;
	}
    public void setSyrbh(String syrbh) {
    	this.syrbh = syrbh;
    }
    
    public String getSyr() {
		return syr;
	}
    public void setSyr(String syr) {
    	this.syr = syr;
    }
    
    public String getJsr() {
		return jsr;
	}
    public void setJsr(String jsr) {
    	this.jsr = jsr;
    }
    
    public String getShzt() {
		return shzt;
	}
    public void setShzt(String shzt) {
    	this.shzt = shzt;
    }
    
    public String getJzr() {
		return jzr;
	}
    public void setJzr(String jzr) {
    	this.jzr = jzr;
    }
    
    public String getRzrq() {
		return rzrq;
	}
    public void setRzrq(String rzrq) {
    	this.rzrq = rzrq;
    }
    
    public String getBz() {
		return bz;
	}
    public void setBz(String bz) {
    	this.bz = bz;
    }
    
    public String getDjh() {
		return djh;
	}
    public void setDjh(String djh) {
    	this.djh = djh;
    }
    
    public String getSrrbh() {
    	return srrbh;
    }
    
    public void setSrrbh(String srrbh) {
    	this.srrbh = srrbh;
    }
    
    public String getSrr() {
    	return srr;
    }
    
    public void setSrr(String srr) {
    	this.srr = srr;
    }
    
    public String getSrrq() {
		return srrq;
	}
    public void setSrrq(String srrq) {
    	this.srrq = srrq;
    }
    
    public String getBdrq() {
		return bdrq;
	}
    public void setBdrq(String bdrq) {
    	this.bdrq = bdrq;
    }
    
    public String getBdyy() {
		return bdyy;
	}
    public void setBdyy(String bdyy) {
    	this.bdyy = bdyy;
    }
    
    public String getZrdwh() {
		return zrdwh;
	}
    public void setZrdwh(String zrdwh) {
    	this.zrdwh = zrdwh;
    }
    
    public String getZrdwm() {
    	return zrdwm;
    }
    
    public void setZrdwm(String zrdwm) {
    	this.zrdwm = zrdwm;
    }
    
    public Double getBddj() {
		return bddj;
	}
    public void setBddj(Double bddj) {
    	this.bddj = bddj;
    }
    
    public String getSqrbh() {
		return sqrbh;
	}
    public void setSqrbh(String sqrbh) {
    	this. sqrbh= sqrbh;
    }
    
    public String getSqr() {
		return sqr;
	}
    public void setSqr(String sqr) {
    	this.sqr = sqr;
    }
}
