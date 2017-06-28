package com.hs.loan.acct.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_还款计划 对象
 * @author autocreate
 * @create 2015-10-30
 */
public class AccLoanRepayPlan implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 还款计划ID */
  	private String planId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 应还账单日 */
  	private String repayDate ; 
    
    /*** 总期数 */
  	private Integer num ; 
    
    /*** 当前期数 */
  	private Integer repayNum ; 
    
    /*** 还款金额 */
  	private java.math.BigDecimal repayAmt ; 
    
    /*** 提前结清金额 */
  	private java.math.BigDecimal advAmt ; 
    
    /*** 本金加利息 */
  	private java.math.BigDecimal prinInterAmt ; 
    
    /*** 本金金额 */
  	private java.math.BigDecimal prinAmt ; 
    
    /*** 利息金额 */
  	private java.math.BigDecimal interAmt ; 
    
    /*** 滞纳金金额 */
  	private java.math.BigDecimal overdueAmt ; 
    
    /*** 服务费 */
  	private java.math.BigDecimal serviceAmt ; 
    
    /*** 账户管理费 */
  	private java.math.BigDecimal acctMngAmt ; 
    
    /*** 保险费 */
  	private java.math.BigDecimal insuAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal feeAmt ; 
    
    /*** 提前还款违约金 */
  	private java.math.BigDecimal unexpPayAmt ; 
    
    /*** 灵活还款包费 */
  	private java.math.BigDecimal agilityAmt ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AccLoanRepayPlan(){}

    //getter和setter方法
    
    /**
	 * 获取 还款计划ID
	 * @return String
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * 设置 还款计划ID
	 * @param planId
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
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
	 * @return Integer
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * 设置 总期数
	 * @param num
	 */
	public void setNum(Integer num) {
		this.num = num;
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
	 * 获取 提前结清金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAdvAmt() {
		return advAmt;
	}

	/**
	 * 设置 提前结清金额
	 * @param advAmt
	 */
	public void setAdvAmt(java.math.BigDecimal advAmt) {
		this.advAmt = advAmt;
	}

    
    /**
	 * 获取 本金加利息
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrinInterAmt() {
		return prinInterAmt;
	}

	/**
	 * 设置 本金加利息
	 * @param prinInterAmt
	 */
	public void setPrinInterAmt(java.math.BigDecimal prinInterAmt) {
		this.prinInterAmt = prinInterAmt;
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
	public java.math.BigDecimal getInterAmt() {
		return interAmt;
	}

	/**
	 * 设置 利息金额
	 * @param interAmt
	 */
	public void setInterAmt(java.math.BigDecimal interAmt) {
		this.interAmt = interAmt;
	}

    
    /**
	 * 获取 滞纳金金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	/**
	 * 设置 滞纳金金额
	 * @param overdueAmt
	 */
	public void setOverdueAmt(java.math.BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

    
    /**
	 * 获取 服务费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getServiceAmt() {
		return serviceAmt;
	}

	/**
	 * 设置 服务费
	 * @param serviceAmt
	 */
	public void setServiceAmt(java.math.BigDecimal serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

    
    /**
	 * 获取 账户管理费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAcctMngAmt() {
		return acctMngAmt;
	}

	/**
	 * 设置 账户管理费
	 * @param acctMngAmt
	 */
	public void setAcctMngAmt(java.math.BigDecimal acctMngAmt) {
		this.acctMngAmt = acctMngAmt;
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
	 * 获取 提前还款违约金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getUnexpPayAmt() {
		return unexpPayAmt;
	}

	/**
	 * 设置 提前还款违约金
	 * @param unexpPayAmt
	 */
	public void setUnexpPayAmt(java.math.BigDecimal unexpPayAmt) {
		this.unexpPayAmt = unexpPayAmt;
	}

    
    /**
	 * 获取 灵活还款包费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAgilityAmt() {
		return agilityAmt;
	}

	/**
	 * 设置 灵活还款包费
	 * @param agilityAmt
	 */
	public void setAgilityAmt(java.math.BigDecimal agilityAmt) {
		this.agilityAmt = agilityAmt;
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