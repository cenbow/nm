package com.hs.loan.acct.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_还款计划（逾期） 对象
 * @author autocreate
 * @create 2015-10-30
 */
public class AccLoanRepayPlanOvdu implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 还款计划ID */
  	private String planId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 身份证号码 */
  	private String certNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 当前日期 */
  	private String currDate ; 
    
    /*** 期数 */
  	private Integer instNum ; 
    
    /*** 账单日 */
  	private String busnDate ; 
    
    /*** 超期天数 */
  	private Integer ovduDays ; 
    
    /*** 应还款总额 */
  	private java.math.BigDecimal shldPayAmt ; 
    
    /*** 应还本金加利息 */
  	private java.math.BigDecimal prinIntAmt ; 
    
    /*** 应还本金 */
  	private java.math.BigDecimal prinAmt ; 
    
    /*** 应还利息 */
  	private java.math.BigDecimal intAmt ; 
    
    /*** 滞纳金 */
  	private java.math.BigDecimal ovduAmt ; 
    
    /*** 服务费 */
  	private java.math.BigDecimal servAmt ; 
    
    /*** 账户管理费 */
  	private java.math.BigDecimal acctAmt ; 
    
    /*** 保险费 */
  	private java.math.BigDecimal insrAmt ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal feeAmt ; 
    
    /*** 提前结清手续费 */
  	private java.math.BigDecimal unexpFeeAmt ; 
    
    /*** 已还金额 */
  	private java.math.BigDecimal actPayAmt ; 
    
    /*** 剩余金额 */
  	private java.math.BigDecimal payBal ; 
    
    /*** 办理人 */
  	private String staffNo ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public AccLoanRepayPlanOvdu(){}

    //getter和setter方法
    
    /**
	 * 获取 ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
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
	 * 获取 身份证号码
	 * @return String
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * 设置 身份证号码
	 * @param certNo
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
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
	 * 获取 客户姓名
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户姓名
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 当前日期
	 * @return String
	 */
	public String getCurrDate() {
		return currDate;
	}

	/**
	 * 设置 当前日期
	 * @param currDate
	 */
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

    
    /**
	 * 获取 期数
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 期数
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

    
    /**
	 * 获取 账单日
	 * @return String
	 */
	public String getBusnDate() {
		return busnDate;
	}

	/**
	 * 设置 账单日
	 * @param busnDate
	 */
	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}

    
    /**
	 * 获取 超期天数
	 * @return Integer
	 */
	public Integer getOvduDays() {
		return ovduDays;
	}

	/**
	 * 设置 超期天数
	 * @param ovduDays
	 */
	public void setOvduDays(Integer ovduDays) {
		this.ovduDays = ovduDays;
	}

    
    /**
	 * 获取 应还款总额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getShldPayAmt() {
		return shldPayAmt;
	}

	/**
	 * 设置 应还款总额
	 * @param shldPayAmt
	 */
	public void setShldPayAmt(java.math.BigDecimal shldPayAmt) {
		this.shldPayAmt = shldPayAmt;
	}

    
    /**
	 * 获取 应还本金加利息
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrinIntAmt() {
		return prinIntAmt;
	}

	/**
	 * 设置 应还本金加利息
	 * @param prinIntAmt
	 */
	public void setPrinIntAmt(java.math.BigDecimal prinIntAmt) {
		this.prinIntAmt = prinIntAmt;
	}

    
    /**
	 * 获取 应还本金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrinAmt() {
		return prinAmt;
	}

	/**
	 * 设置 应还本金
	 * @param prinAmt
	 */
	public void setPrinAmt(java.math.BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

    
    /**
	 * 获取 应还利息
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getIntAmt() {
		return intAmt;
	}

	/**
	 * 设置 应还利息
	 * @param intAmt
	 */
	public void setIntAmt(java.math.BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

    
    /**
	 * 获取 滞纳金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOvduAmt() {
		return ovduAmt;
	}

	/**
	 * 设置 滞纳金
	 * @param ovduAmt
	 */
	public void setOvduAmt(java.math.BigDecimal ovduAmt) {
		this.ovduAmt = ovduAmt;
	}

    
    /**
	 * 获取 服务费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getServAmt() {
		return servAmt;
	}

	/**
	 * 设置 服务费
	 * @param servAmt
	 */
	public void setServAmt(java.math.BigDecimal servAmt) {
		this.servAmt = servAmt;
	}

    
    /**
	 * 获取 账户管理费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAcctAmt() {
		return acctAmt;
	}

	/**
	 * 设置 账户管理费
	 * @param acctAmt
	 */
	public void setAcctAmt(java.math.BigDecimal acctAmt) {
		this.acctAmt = acctAmt;
	}

    
    /**
	 * 获取 保险费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getInsrAmt() {
		return insrAmt;
	}

	/**
	 * 设置 保险费
	 * @param insrAmt
	 */
	public void setInsrAmt(java.math.BigDecimal insrAmt) {
		this.insrAmt = insrAmt;
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
	 * 获取 提前结清手续费
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getUnexpFeeAmt() {
		return unexpFeeAmt;
	}

	/**
	 * 设置 提前结清手续费
	 * @param unexpFeeAmt
	 */
	public void setUnexpFeeAmt(java.math.BigDecimal unexpFeeAmt) {
		this.unexpFeeAmt = unexpFeeAmt;
	}

    
    /**
	 * 获取 已还金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActPayAmt() {
		return actPayAmt;
	}

	/**
	 * 设置 已还金额
	 * @param actPayAmt
	 */
	public void setActPayAmt(java.math.BigDecimal actPayAmt) {
		this.actPayAmt = actPayAmt;
	}

    
    /**
	 * 获取 剩余金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPayBal() {
		return payBal;
	}

	/**
	 * 设置 剩余金额
	 * @param payBal
	 */
	public void setPayBal(java.math.BigDecimal payBal) {
		this.payBal = payBal;
	}

    
    /**
	 * 获取 办理人
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 办理人
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
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
	 * 获取 更新日期
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新日期
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}