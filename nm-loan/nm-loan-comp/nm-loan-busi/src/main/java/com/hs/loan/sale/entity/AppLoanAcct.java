package com.hs.loan.sale.entity;


import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期基本信息 对象
 * @author autocreate
 * @create 2015-10-27
 */
public class AppLoanAcct implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
  	/*** 客户名*/
  	private String custName; 
    
    /*** 首付金额 */
  	private java.math.BigDecimal fstPayAmt ; 
    
    /*** 利率 */
  	private java.math.BigDecimal interRate ; 
    
    /*** 分期本金 */
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 分期类型 */
  	private String loanTyp ; 
    
    /*** 分期期数 */
  	private Integer instNum ; 
    
    /*** 首次还款额 */
  	private java.math.BigDecimal fstRepayAmt ; 
    
    /*** 首次还款日 */
  	private String fstRepayDate ; 
    
    /*** 每月还款日 */
  	private String mthRepayDate ; 
    
    /*** 月还款金额 */
  	private java.math.BigDecimal mthRepayAmt ; 
    
    /*** 申请日期 */
  	private Date applyDate ; 
    
    /*** 审批日期 */
  	private Date aprvDate ; 
    
    /*** 注册日期 */
  	private Date regDate ; 
    
    /*** 放款日期 */
  	private Date distrDate ; 
    
    /*** 文件类型 */
  	private String fileNo ; 
    
    /*** 分期状态 */
  	private String stat ; 
    
    /*** 贷后状态 */
  	private String afterStat ; 
    
    /*** 五级分类 */
  	private String fivCls ; 
    
    /*** 办理所在省 */
  	private String applyProv ; 
    
    /*** 办理所在区/县 */
  	private String applyArea ; 
    
    /*** 办理所在市 */
  	private String applyCity ; 
  	
  	/*** 备注*/
  	private String loanRemark; 
  	/*** 信托标志*/
  	private String entrFlag;
	private String applyAddr;
	//手续费
	private BigDecimal feeRat;
	//通讯地址
    private String contactAddr;
    
	//贷款渠道
  	private String saleChanl;
  	
	/** 放款状态 */
	private String loanState;

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public BigDecimal getFeeRat() {
		return feeRat;
	}

	public void setFeeRat(BigDecimal feeRat) {
		this.feeRat = feeRat;
	}

	public String getApplyAddr() {
		return applyAddr;
	}

	public void setApplyAddr(String applyAddr) {
		this.applyAddr = applyAddr;
	}

	//构造函数
    public AppLoanAcct(){}

    //getter和setter方法
    
    /**
	 * 获取 分期编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 客户编号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 首付金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFstPayAmt() {
		return fstPayAmt;
	}

	/**
	 * 设置 首付金额
	 * @param fstPayAmt
	 */
	public void setFstPayAmt(java.math.BigDecimal fstPayAmt) {
		this.fstPayAmt = fstPayAmt;
	}

    
    /**
	 * 获取 利率
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getInterRate() {
		return interRate;
	}

	/**
	 * 设置 利率
	 * @param interRate
	 */
	public void setInterRate(java.math.BigDecimal interRate) {
		this.interRate = interRate;
	}

    
    /**
	 * 获取 分期本金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 分期本金
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 分期类型
	 * @return String
	 */
	public String getLoanTyp() {
		return loanTyp;
	}

	/**
	 * 设置 分期类型
	 * @param loanTyp
	 */
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}

    
    /**
	 * 获取 分期期数
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 分期期数
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

    
    /**
	 * 获取 首次还款额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFstRepayAmt() {
		return fstRepayAmt;
	}

	/**
	 * 设置 首次还款额
	 * @param fstRepayAmt
	 */
	public void setFstRepayAmt(java.math.BigDecimal fstRepayAmt) {
		this.fstRepayAmt = fstRepayAmt;
	}

    
    /**
	 * 获取 首次还款日
	 * @return String
	 */
	public String getFstRepayDate() {
		return fstRepayDate;
	}

	/**
	 * 设置 首次还款日
	 * @param fstRepayDate
	 */
	public void setFstRepayDate(String fstRepayDate) {
		this.fstRepayDate = fstRepayDate;
	}

    
    /**
	 * 获取 每月还款日
	 * @return String
	 */
	public String getMthRepayDate() {
		return mthRepayDate;
	}

	/**
	 * 设置 每月还款日
	 * @param mthRepayDate
	 */
	public void setMthRepayDate(String mthRepayDate) {
		this.mthRepayDate = mthRepayDate;
	}

    
    /**
	 * 获取 月还款金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getMthRepayAmt() {
		return mthRepayAmt;
	}

	/**
	 * 设置 月还款金额
	 * @param mthRepayAmt
	 */
	public void setMthRepayAmt(java.math.BigDecimal mthRepayAmt) {
		this.mthRepayAmt = mthRepayAmt;
	}

    
    /**
	 * 获取 申请日期
	 * @return Date
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * 设置 申请日期
	 * @param applyDate
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

    
    /**
	 * 获取 审批日期
	 * @return Date
	 */
	public Date getAprvDate() {
		return aprvDate;
	}

	/**
	 * 设置 审批日期
	 * @param aprvDate
	 */
	public void setAprvDate(Date aprvDate) {
		this.aprvDate = aprvDate;
	}

    
    /**
	 * 获取 注册日期
	 * @return Date
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * 设置 注册日期
	 * @param regDate
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

    
    /**
	 * 获取 放款日期
	 * @return Date
	 */
	public Date getDistrDate() {
		return distrDate;
	}

	/**
	 * 设置 放款日期
	 * @param distrDate
	 */
	public void setDistrDate(Date distrDate) {
		this.distrDate = distrDate;
	}

    
    /**
	 * 获取 文件类型
	 * @return String
	 */
	public String getFileNo() {
		return fileNo;
	}

	/**
	 * 设置 文件类型
	 * @param fileNo
	 */
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

    
    /**
	 * 获取 贷前状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 贷前状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 贷后状态
	 * @return String
	 */
	public String getAfterStat() {
		return afterStat;
	}

	/**
	 * 设置 贷后状态
	 * @param afterStat
	 */
	public void setAfterStat(String afterStat) {
		this.afterStat = afterStat;
	}

    
    /**
	 * 获取 五级分类
	 * @return String
	 */
	public String getFivCls() {
		return fivCls;
	}

	/**
	 * 设置 五级分类
	 * @param fivCls
	 */
	public void setFivCls(String fivCls) {
		this.fivCls = fivCls;
	}

    
    /**
	 * 获取 办理所在省
	 * @return String
	 */
	public String getApplyProv() {
		return applyProv;
	}

	/**
	 * 设置 办理所在省
	 * @param applyProv
	 */
	public void setApplyProv(String applyProv) {
		this.applyProv = applyProv;
	}

    
    /**
	 * 获取 办理所在区/县
	 * @return String
	 */
	public String getApplyArea() {
		return applyArea;
	}

	/**
	 * 设置 办理所在区/县
	 * @param applyArea
	 */
	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}

    
    /**
	 * 获取 办理所在市
	 * @return String
	 */
	public String getApplyCity() {
		return applyCity;
	}

	/**
	 * 设置 办理所在市
	 * @param applyCity
	 */
	public void setApplyCity(String applyCity) {
		this.applyCity = applyCity;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getLoanRemark() {
		return loanRemark;
	}

	public void setLoanRemark(String loanRemark) {
		this.loanRemark = loanRemark;
	}

	public String getEntrFlag() {
		return entrFlag;
	}

	public void setEntrFlag(String entrFlag) {
		this.entrFlag = entrFlag;
	}

	public String getSaleChanl() {
		return saleChanl;
	}

	public void setSaleChanl(String saleChanl) {
		this.saleChanl = saleChanl;
	}

	public String getLoanState() {
		return loanState;
	}

	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
}