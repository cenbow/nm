package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_资金渠道信息 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubFundChanInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 渠道编号 */
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	private String chanName ; 
    
    /*** 商户ID */
  	private String chinapayMerid ; 
    
    /*** 银联密钥 */
  	private String chinapayMerkey ; 
    
    /*** 银联账户名 */
  	private String chinapayUsername ; 
    
    /*** 银联账户密码 */
  	private String chinapayPw ; 
    
    /*** 公司名称 */
  	private String compOrg ; 
    
    /*** 公司帐号 */
  	private String compAcc ; 
    
    /*** 开户行 */
  	private String openOrg ; 
    
    /*** 资金利率 */
  	private java.math.BigDecimal capRat ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
  	
  	/*** 是否前置验证*/
  	private String checkFlag; 

    //构造函数
    public PubFundChanInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 渠道编号
	 * @return String
	 */
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 渠道编号
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

    
    /**
	 * 获取 渠道名称
	 * @return String
	 */
	public String getChanName() {
		return chanName;
	}

	/**
	 * 设置 渠道名称
	 * @param chanName
	 */
	public void setChanName(String chanName) {
		this.chanName = chanName;
	}

    
    /**
	 * 获取 商户ID
	 * @return String
	 */
	public String getChinapayMerid() {
		return chinapayMerid;
	}

	/**
	 * 设置 商户ID
	 * @param chinapayMerid
	 */
	public void setChinapayMerid(String chinapayMerid) {
		this.chinapayMerid = chinapayMerid;
	}

    
    /**
	 * 获取 银联密钥
	 * @return String
	 */
	public String getChinapayMerkey() {
		return chinapayMerkey;
	}

	/**
	 * 设置 银联密钥
	 * @param chinapayMerkey
	 */
	public void setChinapayMerkey(String chinapayMerkey) {
		this.chinapayMerkey = chinapayMerkey;
	}

    
    /**
	 * 获取 银联账户名
	 * @return String
	 */
	public String getChinapayUsername() {
		return chinapayUsername;
	}

	/**
	 * 设置 银联账户名
	 * @param chinapayUsername
	 */
	public void setChinapayUsername(String chinapayUsername) {
		this.chinapayUsername = chinapayUsername;
	}

    
    /**
	 * 获取 银联账户密码
	 * @return String
	 */
	public String getChinapayPw() {
		return chinapayPw;
	}

	/**
	 * 设置 银联账户密码
	 * @param chinapayPw
	 */
	public void setChinapayPw(String chinapayPw) {
		this.chinapayPw = chinapayPw;
	}

    
    /**
	 * 获取 公司名称
	 * @return String
	 */
	public String getCompOrg() {
		return compOrg;
	}

	/**
	 * 设置 公司名称
	 * @param compOrg
	 */
	public void setCompOrg(String compOrg) {
		this.compOrg = compOrg;
	}

    
    /**
	 * 获取 公司帐号
	 * @return String
	 */
	public String getCompAcc() {
		return compAcc;
	}

	/**
	 * 设置 公司帐号
	 * @param compAcc
	 */
	public void setCompAcc(String compAcc) {
		this.compAcc = compAcc;
	}

    
    /**
	 * 获取 开户行
	 * @return String
	 */
	public String getOpenOrg() {
		return openOrg;
	}

	/**
	 * 设置 开户行
	 * @param openOrg
	 */
	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

    
    /**
	 * 获取 资金利率
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getCapRat() {
		return capRat;
	}

	/**
	 * 设置 资金利率
	 * @param capRat
	 */
	public void setCapRat(java.math.BigDecimal capRat) {
		this.capRat = capRat;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

}