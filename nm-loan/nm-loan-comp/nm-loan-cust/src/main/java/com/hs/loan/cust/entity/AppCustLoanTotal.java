package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户分期信息汇总 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustLoanTotal implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 分期次数 */
  	private java.math.BigDecimal loanCnt ; 
    
    /*** 分期总金额 */
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 在贷余额总额 */
  	private java.math.BigDecimal loanBal ; 
    
    /*** 最晚到期日期 */
  	private String lastDate ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public AppCustLoanTotal(){}

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
	 * 获取 分期次数
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanCnt() {
		return loanCnt;
	}

	/**
	 * 设置 分期次数
	 * @param loanCnt
	 */
	public void setLoanCnt(java.math.BigDecimal loanCnt) {
		this.loanCnt = loanCnt;
	}

    
    /**
	 * 获取 分期总金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 分期总金额
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 在贷余额总额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanBal() {
		return loanBal;
	}

	/**
	 * 设置 在贷余额总额
	 * @param loanBal
	 */
	public void setLoanBal(java.math.BigDecimal loanBal) {
		this.loanBal = loanBal;
	}

    
    /**
	 * 获取 最晚到期日期
	 * @return String
	 */
	public String getLastDate() {
		return lastDate;
	}

	/**
	 * 设置 最晚到期日期
	 * @param lastDate
	 */
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 创建时间
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 更新时间
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}