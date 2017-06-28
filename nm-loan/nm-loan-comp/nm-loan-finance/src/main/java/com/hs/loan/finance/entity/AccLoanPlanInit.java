package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_还款计划（初始） 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccLoanPlanInit implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 还款计划ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 应还账单日 */
  	private String repayDate ; 
    
    /*** 总期数 */
  	private String busiDate ; 
    
    /*** 当前期数 */
  	private Integer repayNum ; 
    
    /*** 还款金额 */
  	private java.math.BigDecimal repayAmt ; 
    
    /*** 本金金额 */
  	private java.math.BigDecimal prinAmt ; 
    
    /*** 利息金额 */
  	private java.math.BigDecimal intAmt ; 
    
    /*** 滞纳金金额 */
  	private java.math.BigDecimal ovduAmt ; 
    
    /*** 服务费 */
  	private java.math.BigDecimal svcAmt ; 
    
    /*** 账户管理费 */
  	private java.math.BigDecimal mngAmt ; 
    
    /*** 保险费 */
  	private java.math.BigDecimal insuAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal feeAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal punAmt ; 
    
    /*** 提前还款违约金 */
  	private java.math.BigDecimal outAmt ; 
    
    /*** 灵活还款包费 */
  	private java.math.BigDecimal packAmt ; 
    
    /***  */
  	private String ovduFlag ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AccLoanPlanInit(){}

    //getter和setter方法
    
    /**
	 * 获取 还款计划ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 还款计划ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
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
	 * 获取 应还账单日
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 应还账单日
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 总期数
	 * @return String
	 */
	public String getBusiDate() {
		return busiDate;
	}

	/**
	 * 设置 总期数
	 * @param busiDate
	 */
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

    
    /**
	 * 获取 当前期数
	 * @return Integer
	 */
	public Integer getRepayNum() {
		return repayNum;
	}

	/**
	 * 设置 当前期数
	 * @param repayNum
	 */
	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

    
    /**
	 * 获取 还款金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRepayAmt() {
		return repayAmt;
	}

	/**
	 * 设置 还款金额
	 * @param repayAmt
	 */
	public void setRepayAmt(java.math.BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

    
    /**
	 * 获取 本金金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrinAmt() {
		return prinAmt;
	}

	/**
	 * 设置 本金金额
	 * @param prinAmt
	 */
	public void setPrinAmt(java.math.BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

    
    /**
	 * 获取 利息金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getIntAmt() {
		return intAmt;
	}

	/**
	 * 设置 利息金额
	 * @param intAmt
	 */
	public void setIntAmt(java.math.BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

    
    /**
	 * 获取 滞纳金金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOvduAmt() {
		return ovduAmt;
	}

	/**
	 * 设置 滞纳金金额
	 * @param ovduAmt
	 */
	public void setOvduAmt(java.math.BigDecimal ovduAmt) {
		this.ovduAmt = ovduAmt;
	}

    
    /**
	 * 获取 服务费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getSvcAmt() {
		return svcAmt;
	}

	/**
	 * 设置 服务费
	 * @param svcAmt
	 */
	public void setSvcAmt(java.math.BigDecimal svcAmt) {
		this.svcAmt = svcAmt;
	}

    
    /**
	 * 获取 账户管理费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getMngAmt() {
		return mngAmt;
	}

	/**
	 * 设置 账户管理费
	 * @param mngAmt
	 */
	public void setMngAmt(java.math.BigDecimal mngAmt) {
		this.mngAmt = mngAmt;
	}

    
    /**
	 * 获取 保险费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getInsuAmt() {
		return insuAmt;
	}

	/**
	 * 设置 保险费
	 * @param insuAmt
	 */
	public void setInsuAmt(java.math.BigDecimal insuAmt) {
		this.insuAmt = insuAmt;
	}

    
    /**
	 * 获取 手续费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	/**
	 * 设置 手续费
	 * @param feeAmt
	 */
	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

    
    /**
	 * 获取 手续费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPunAmt() {
		return punAmt;
	}

	/**
	 * 设置 手续费
	 * @param funAmt
	 */
	public void setPunAmt(java.math.BigDecimal punAmt) {
		this.punAmt = punAmt;
	}

    
    /**
	 * 获取 提前还款违约金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOutAmt() {
		return outAmt;
	}

	/**
	 * 设置 提前还款违约金
	 * @param outAmt
	 */
	public void setOutAmt(java.math.BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

    
    /**
	 * 获取 灵活还款包费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPackAmt() {
		return packAmt;
	}

	/**
	 * 设置 灵活还款包费
	 * @param packAmt
	 */
	public void setPackAmt(java.math.BigDecimal packAmt) {
		this.packAmt = packAmt;
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