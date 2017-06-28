package com.hs.loan.market.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_网点信息 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubBranch implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 网点编码 */
  	private String branchNo ; 
    
    /*** 网点名称 */
  	private String branchName ; 
    
    /*** 网点等级 */
  	private String branchLevel ; 
    
    /*** 网点评分 */
  	private java.math.BigDecimal branchScore ; 
    
    /*** 网点地址 */
  	private String branchAddr ; 
    
    /***  */
  	private String branchTyp ; 
    
    /*** 签约合同号 */
  	private String signContr ; 
    
    /*** 签约日期 */
  	private String signDate ; 
    
    /*** 联系人 */
  	private String contctPer ; 
    
    /*** 联系人职务 */
  	private String contctDuty ; 
    
    /*** 联系电话 */
  	private String contctTel ; 
    
    /*** 开户机构 */
  	private String openOrg ; 
    
    /*** 开户所在省 */
  	private String openProv ; 
    
    /*** 开户所在城市 */
  	private String openCity ; 
    
    /*** 开户所在区域 */
  	private String openArea ; 
    
    /***  */
  	private String branchProv ; 
    
    /***  */
  	private String branchCity ; 
    
    /***  */
  	private String branchArea ; 
    
    /*** 打款账号 */
  	private String acctNo ; 
    
    /*** 账户名称 */
  	private String acctName ; 
    
    /*** 开户行 */
  	private String bankNo ; 
    
    /*** 开户行名称 */
  	private String bankName ; 
    
    /*** 黑名单标志 */
  	private String blckListFlag ; 
    
    /*** 黑名单原因 */
  	private String blckListDesc ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 修改日期 */
  	private Date updtDate ; 
  	
  	/*** 是否启用*/
  	private String stat;
    /*商铺等级*/
  	private String shopLevel;
	/***法人姓名*/
  	private String legalPersonName;
	/***法人电话*/
  	private String legalPersonTel;
	/***法人身份证号*/
  	private String legalPersonCert;
	/***营业执照号*/
  	private String businessLicenseNo;
	/***组织机构代码号*/
  	private String organizationNo;
	/***税务登记号*/
  	private String taxNo;
	/***统一社会信用代码号*/
  	private String socialCertNo;
    //构造函数
    public PubBranch(){}

    //getter和setter方法
    
    /**
	 * 获取 网点编码
	 * @return String
	 */
	public String getBranchNo() {
		return branchNo;
	}

	public String getShopLevel() {
		return shopLevel;
	}

	public void setShopLevel(String shopLevel) {
		this.shopLevel = shopLevel;
	}

	/**
	 * 设置 网点编码
	 * @param branchNo
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

    
    /**
	 * 获取 网点名称
	 * @return String
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * 设置 网点名称
	 * @param branchName
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

    
    /**
	 * 获取 网点等级
	 * @return String
	 */
	public String getBranchLevel() {
		return branchLevel;
	}

	/**
	 * 设置 网点等级
	 * @param branchLevel
	 */
	public void setBranchLevel(String branchLevel) {
		this.branchLevel = branchLevel;
	}

    
    /**
	 * 获取 网点评分
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getBranchScore() {
		return branchScore;
	}

	/**
	 * 设置 网点评分
	 * @param branchScore
	 */
	public void setBranchScore(java.math.BigDecimal branchScore) {
		this.branchScore = branchScore;
	}

    
    /**
	 * 获取 网点地址
	 * @return String
	 */
	public String getBranchAddr() {
		return branchAddr;
	}

	/**
	 * 设置 网点地址
	 * @param branchAddr
	 */
	public void setBranchAddr(String branchAddr) {
		this.branchAddr = branchAddr;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBranchTyp() {
		return branchTyp;
	}

	/**
	 * 设置 
	 * @param branchTyp
	 */
	public void setBranchTyp(String branchTyp) {
		this.branchTyp = branchTyp;
	}

    
    /**
	 * 获取 签约合同号
	 * @return String
	 */
	public String getSignContr() {
		return signContr;
	}

	/**
	 * 设置 签约合同号
	 * @param signContr
	 */
	public void setSignContr(String signContr) {
		this.signContr = signContr;
	}

    
    /**
	 * 获取 签约日期
	 * @return String
	 */
	public String getSignDate() {
		return signDate;
	}

	/**
	 * 设置 签约日期
	 * @param signDate
	 */
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

    
    /**
	 * 获取 联系人
	 * @return String
	 */
	public String getContctPer() {
		return contctPer;
	}

	/**
	 * 设置 联系人
	 * @param contctPer
	 */
	public void setContctPer(String contctPer) {
		this.contctPer = contctPer;
	}

    
    /**
	 * 获取 联系人职务
	 * @return String
	 */
	public String getContctDuty() {
		return contctDuty;
	}

	/**
	 * 设置 联系人职务
	 * @param contctDuty
	 */
	public void setContctDuty(String contctDuty) {
		this.contctDuty = contctDuty;
	}

    
    /**
	 * 获取 联系电话
	 * @return String
	 */
	public String getContctTel() {
		return contctTel;
	}

	/**
	 * 设置 联系电话
	 * @param contctTel
	 */
	public void setContctTel(String contctTel) {
		this.contctTel = contctTel;
	}

    
    /**
	 * 获取 开户机构
	 * @return String
	 */
	public String getOpenOrg() {
		return openOrg;
	}

	/**
	 * 设置 开户机构
	 * @param openOrg
	 */
	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

    
    /**
	 * 获取 开户所在省
	 * @return String
	 */
	public String getOpenProv() {
		return openProv;
	}

	/**
	 * 设置 开户所在省
	 * @param openProv
	 */
	public void setOpenProv(String openProv) {
		this.openProv = openProv;
	}

    
    /**
	 * 获取 开户所在城市
	 * @return String
	 */
	public String getOpenCity() {
		return openCity;
	}

	/**
	 * 设置 开户所在城市
	 * @param openCity
	 */
	public void setOpenCity(String openCity) {
		this.openCity = openCity;
	}

    
    /**
	 * 获取 开户所在区域
	 * @return String
	 */
	public String getOpenArea() {
		return openArea;
	}

	/**
	 * 设置 开户所在区域
	 * @param openArea
	 */
	public void setOpenArea(String openArea) {
		this.openArea = openArea;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBranchProv() {
		return branchProv;
	}

	/**
	 * 设置 
	 * @param branchProv
	 */
	public void setBranchProv(String branchProv) {
		this.branchProv = branchProv;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBranchCity() {
		return branchCity;
	}

	/**
	 * 设置 
	 * @param branchCity
	 */
	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBranchArea() {
		return branchArea;
	}

	/**
	 * 设置 
	 * @param branchArea
	 */
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}

    
    /**
	 * 获取 打款账号
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 打款账号
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

    
    /**
	 * 获取 账户名称
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 账户名称
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

    
    /**
	 * 获取 开户行
	 * @return String
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * 设置 开户行
	 * @param bankNo
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

    
    /**
	 * 获取 开户行名称
	 * @return String
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * 设置 开户行名称
	 * @param bankName
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

    
    /**
	 * 获取 黑名单标志
	 * @return String
	 */
	public String getBlckListFlag() {
		return blckListFlag;
	}

	/**
	 * 设置 黑名单标志
	 * @param blckListFlag
	 */
	public void setBlckListFlag(String blckListFlag) {
		this.blckListFlag = blckListFlag;
	}

    
    /**
	 * 获取 黑名单原因
	 * @return String
	 */
	public String getBlckListDesc() {
		return blckListDesc;
	}

	/**
	 * 设置 黑名单原因
	 * @param blckListDesc
	 */
	public void setBlckListDesc(String blckListDesc) {
		this.blckListDesc = blckListDesc;
	}

    
    /**
	 * 获取 创建日期
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建日期
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 修改日期
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改日期
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getLegalPersonTel() {
		return legalPersonTel;
	}

	public void setLegalPersonTel(String legalPersonTel) {
		this.legalPersonTel = legalPersonTel;
	}

	public String getLegalPersonCert() {
		return legalPersonCert;
	}

	public void setLegalPersonCert(String legalPersonCert) {
		this.legalPersonCert = legalPersonCert;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getSocialCertNo() {
		return socialCertNo;
	}

	public void setSocialCertNo(String socialCertNo) {
		this.socialCertNo = socialCertNo;
	}

}