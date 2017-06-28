package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  对象
 * @author autocreate
 * @create 2016-03-22
 */
public class AccLoanInst implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 贷款编号 */
  	private String loanNo ; 
    
    /*** 账单日期 */
  	private String repayDate ; 
    
    /*** 业务日期 */
  	private String busnDate ; 
    
    /*** 还款期数 */
  	private Integer repayNum ; 
    
    /*** 本金金额 */
  	private java.math.BigDecimal rcvPrinAmt ; 
    
    /*** 本金金额 */
  	private java.math.BigDecimal actPrinAmt ; 
    
    /*** 利息金额 */
  	private java.math.BigDecimal rcvIntAmt ; 
    
    /*** 利息金额 */
  	private java.math.BigDecimal actIntAmt ; 
    
    /*** 服务费 */
  	private java.math.BigDecimal rcvSvcAmt ; 
    
    /*** 服务费 */
  	private java.math.BigDecimal actSvcAmt ; 
    
    /*** 滞纳金金额 */
  	private java.math.BigDecimal rcvOvduAmt ; 
    
    /*** 滞纳金金额 */
  	private java.math.BigDecimal actOvduAmt ; 
    
    /*** 账户管理费 */
  	private java.math.BigDecimal rcvAcctAmt ; 
    
    /*** 账户管理费 */
  	private java.math.BigDecimal actAcctAmt ; 
    
    /*** 保险费 */
  	private java.math.BigDecimal rcvInsuAmt ; 
    
    /*** 保险费 */
  	private java.math.BigDecimal actInsuAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal rcvFeeAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal actFeeAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal rcvPunAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal actPunAmt ; 
    
    /*** 提前还款违约金 */
  	private java.math.BigDecimal rcvOutAmt ; 
    
    /*** 提前还款违约金 */
  	private java.math.BigDecimal actOutAmt ; 
    
    /*** 灵活还款包费 */
  	private java.math.BigDecimal rcvPackAmt ; 
    
    /*** 灵活还款包费 */
  	private java.math.BigDecimal actPackAmt ; 
    
    /*** 应收总额 */
  	private java.math.BigDecimal rcvTotlAmt ; 
    
    /*** 实收总额 */
  	private java.math.BigDecimal actTotlAmt ; 
    
    /***  */
  	private String ovduFlag ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AccLoanInst(){}

    //getter和setter方法
    
    /**
	 * 获取 贷款编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 贷款编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 账单日期
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 账单日期
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 业务日期
	 * @return String
	 */
	public String getBusnDate() {
		return busnDate;
	}

	/**
	 * 设置 业务日期
	 * @param busnDate
	 */
	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}

    
    /**
	 * 获取 还款期数
	 * @return Integer
	 */
	public Integer getRepayNum() {
		return repayNum;
	}

	/**
	 * 设置 还款期数
	 * @param repayNum
	 */
	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

    
    /**
	 * 获取 本金金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvPrinAmt() {
		return rcvPrinAmt;
	}

	/**
	 * 设置 本金金额
	 * @param rcvPrinAmt
	 */
	public void setRcvPrinAmt(java.math.BigDecimal rcvPrinAmt) {
		this.rcvPrinAmt = rcvPrinAmt;
	}

    
    /**
	 * 获取 本金金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActPrinAmt() {
		return actPrinAmt;
	}

	/**
	 * 设置 本金金额
	 * @param actPrinAmt
	 */
	public void setActPrinAmt(java.math.BigDecimal actPrinAmt) {
		this.actPrinAmt = actPrinAmt;
	}

    
    /**
	 * 获取 利息金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvIntAmt() {
		return rcvIntAmt;
	}

	/**
	 * 设置 利息金额
	 * @param rcvIntAmt
	 */
	public void setRcvIntAmt(java.math.BigDecimal rcvIntAmt) {
		this.rcvIntAmt = rcvIntAmt;
	}

    
    /**
	 * 获取 利息金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActIntAmt() {
		return actIntAmt;
	}

	/**
	 * 设置 利息金额
	 * @param actIntAmt
	 */
	public void setActIntAmt(java.math.BigDecimal actIntAmt) {
		this.actIntAmt = actIntAmt;
	}

    
    /**
	 * 获取 服务费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvSvcAmt() {
		return rcvSvcAmt;
	}

	/**
	 * 设置 服务费
	 * @param rcvSvcAmt
	 */
	public void setRcvSvcAmt(java.math.BigDecimal rcvSvcAmt) {
		this.rcvSvcAmt = rcvSvcAmt;
	}

    
    /**
	 * 获取 服务费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActSvcAmt() {
		return actSvcAmt;
	}

	/**
	 * 设置 服务费
	 * @param actSvcAmt
	 */
	public void setActSvcAmt(java.math.BigDecimal actSvcAmt) {
		this.actSvcAmt = actSvcAmt;
	}

    
    /**
	 * 获取 滞纳金金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvOvduAmt() {
		return rcvOvduAmt;
	}

	/**
	 * 设置 滞纳金金额
	 * @param rcvOvduAmt
	 */
	public void setRcvOvduAmt(java.math.BigDecimal rcvOvduAmt) {
		this.rcvOvduAmt = rcvOvduAmt;
	}

    
    /**
	 * 获取 滞纳金金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActOvduAmt() {
		return actOvduAmt;
	}

	/**
	 * 设置 滞纳金金额
	 * @param actOvduAmt
	 */
	public void setActOvduAmt(java.math.BigDecimal actOvduAmt) {
		this.actOvduAmt = actOvduAmt;
	}

    
    /**
	 * 获取 账户管理费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvAcctAmt() {
		return rcvAcctAmt;
	}

	/**
	 * 设置 账户管理费
	 * @param rcvAcctAmt
	 */
	public void setRcvAcctAmt(java.math.BigDecimal rcvAcctAmt) {
		this.rcvAcctAmt = rcvAcctAmt;
	}

    
    /**
	 * 获取 账户管理费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActAcctAmt() {
		return actAcctAmt;
	}

	/**
	 * 设置 账户管理费
	 * @param actAcctAmt
	 */
	public void setActAcctAmt(java.math.BigDecimal actAcctAmt) {
		this.actAcctAmt = actAcctAmt;
	}

    
    /**
	 * 获取 保险费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvInsuAmt() {
		return rcvInsuAmt;
	}

	/**
	 * 设置 保险费
	 * @param rcvInsuAmt
	 */
	public void setRcvInsuAmt(java.math.BigDecimal rcvInsuAmt) {
		this.rcvInsuAmt = rcvInsuAmt;
	}

    
    /**
	 * 获取 保险费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActInsuAmt() {
		return actInsuAmt;
	}

	/**
	 * 设置 保险费
	 * @param actInsuAmt
	 */
	public void setActInsuAmt(java.math.BigDecimal actInsuAmt) {
		this.actInsuAmt = actInsuAmt;
	}

    
    /**
	 * 获取 手续费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvFeeAmt() {
		return rcvFeeAmt;
	}

	/**
	 * 设置 手续费
	 * @param rcvFeeAmt
	 */
	public void setRcvFeeAmt(java.math.BigDecimal rcvFeeAmt) {
		this.rcvFeeAmt = rcvFeeAmt;
	}

    
    /**
	 * 获取 手续费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActFeeAmt() {
		return actFeeAmt;
	}

	/**
	 * 设置 手续费
	 * @param actFeeAmt
	 */
	public void setActFeeAmt(java.math.BigDecimal actFeeAmt) {
		this.actFeeAmt = actFeeAmt;
	}

    
    /**
	 * 获取 手续费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvPunAmt() {
		return rcvPunAmt;
	}

	/**
	 * 设置 手续费
	 * @param rcvPunAmt
	 */
	public void setRcvPunAmt(java.math.BigDecimal rcvPunAmt) {
		this.rcvPunAmt = rcvPunAmt;
	}

    
    /**
	 * 获取 手续费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActPunAmt() {
		return actPunAmt;
	}

	/**
	 * 设置 手续费
	 * @param actPunAmt
	 */
	public void setActPunAmt(java.math.BigDecimal actPunAmt) {
		this.actPunAmt = actPunAmt;
	}

    
    /**
	 * 获取 提前还款违约金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvOutAmt() {
		return rcvOutAmt;
	}

	/**
	 * 设置 提前还款违约金
	 * @param rcvOutAmt
	 */
	public void setRcvOutAmt(java.math.BigDecimal rcvOutAmt) {
		this.rcvOutAmt = rcvOutAmt;
	}

    
    /**
	 * 获取 提前还款违约金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActOutAmt() {
		return actOutAmt;
	}

	/**
	 * 设置 提前还款违约金
	 * @param actOutAmt
	 */
	public void setActOutAmt(java.math.BigDecimal actOutAmt) {
		this.actOutAmt = actOutAmt;
	}

    
    /**
	 * 获取 灵活还款包费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvPackAmt() {
		return rcvPackAmt;
	}

	/**
	 * 设置 灵活还款包费
	 * @param rcvPackAmt
	 */
	public void setRcvPackAmt(java.math.BigDecimal rcvPackAmt) {
		this.rcvPackAmt = rcvPackAmt;
	}

    
    /**
	 * 获取 灵活还款包费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActPackAmt() {
		return actPackAmt;
	}

	/**
	 * 设置 灵活还款包费
	 * @param actPackAmt
	 */
	public void setActPackAmt(java.math.BigDecimal actPackAmt) {
		this.actPackAmt = actPackAmt;
	}

    
    /**
	 * 获取 应收总额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvTotlAmt() {
		return rcvTotlAmt;
	}

	/**
	 * 设置 应收总额
	 * @param rcvTotlAmt
	 */
	public void setRcvTotlAmt(java.math.BigDecimal rcvTotlAmt) {
		this.rcvTotlAmt = rcvTotlAmt;
	}

    
    /**
	 * 获取 实收总额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActTotlAmt() {
		return actTotlAmt;
	}

	/**
	 * 设置 实收总额
	 * @param actTotlAmt
	 */
	public void setActTotlAmt(java.math.BigDecimal actTotlAmt) {
		this.actTotlAmt = actTotlAmt;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOvduFlag() {
		return ovduFlag;
	}

	/**
	 * 设置 
	 * @param ovduFlag
	 */
	public void setOvduFlag(String ovduFlag) {
		this.ovduFlag = ovduFlag;
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

}