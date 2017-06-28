package com.hs.loan.prod.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * PUB_资金渠道信息 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubFundChanInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 渠道编号 */
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	@NotBlank(message="渠道名称不能为空")
  	@Size(max=64,message="渠道名称超长")
  	private String chanName ; 
    
    /*** 商户ID */
  	@NotBlank(message="商户ID不能为空")
  	@Size(max=32,message="商户ID超长")
  	private String chinapayMerid ; 
    
    /*** 银联密钥 */
  	@NotBlank(message="银联密钥不能为空")
  	@Size(max=32,message="银联密钥超长")
  	private String chinapayMerkey ; 
    
    /*** 银联账户名 */
  	@NotBlank(message="银联账户名不能为空")
  	@Size(max=64,message="银联账户名超长")
  	private String chinapayUsername ; 
    
    /*** 银联账户密码 */
  	@NotBlank(message="银联账户密码不能为空")
  	@Size(max=32,message="银联账户密码超长")
  	private String chinapayPw ; 
    
    /*** 公司名称 */
  	@NotBlank(message="公司名称不能为空")
  	@Size(max=128,message="公司名称超长")
  	private String compOrg ; 
    
    /*** 公司帐号 */
  	@NotBlank(message="公司帐号不能为空")
  	@Size(max=32,message="公司帐号超长")
  	private String compAcc ; 
    
    /*** 开户行 */
  	@NotBlank(message="开户行不能为空")
  	@Size(max=128,message="开户行超长")
  	private String openOrg ; 
    
    /*** 资金利率 */
  	@NotNull(message="资金利率不能为空")
  	@Range(max=99999,message="资金利率超长")
  	private java.math.BigDecimal capRat ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

	/*** 是否前置验证*/
  	@NotNull(message="是否前置请验证不能为空")
  	private String checkFlag; 
  	
    //构造函数
    public PubFundChanInfoDto(){}

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