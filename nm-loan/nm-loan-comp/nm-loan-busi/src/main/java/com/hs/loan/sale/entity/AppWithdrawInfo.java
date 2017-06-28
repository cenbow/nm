package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_提现记录 对象
 * @author autocreate
 * @create 2016-06-01
 */
public class AppWithdrawInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 流水编号 */
  	private String id ; 
    
    /*** 贷款编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 提现金额 */
  	private java.math.BigDecimal withdrawAmt ; 
    
    /*** 提现状态 */
  	private String withdrawStat ; 
    
    /*** 插入时间 */
  	private Date instDate ; 
    
    /*** 修改时间 */
  	private Date updtDate ; 
    
    /*** 银行账号 */
  	private String acctNo ; 
    
    /*** 户名 */
  	private String acctName ; 
    
    /*** 银行 */
  	private String openBank ; 
    
    /*** 手续费 */
  	private java.math.BigDecimal feeAmt ; 

    //构造函数
    public AppWithdrawInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 流水编号
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 流水编号
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
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
	 * 获取 提现金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getWithdrawAmt() {
		return withdrawAmt;
	}

	/**
	 * 设置 提现金额
	 * @param withdrawAmt
	 */
	public void setWithdrawAmt(java.math.BigDecimal withdrawAmt) {
		this.withdrawAmt = withdrawAmt;
	}

    
    /**
	 * 获取 提现状态
	 * @return String
	 */
	public String getWithdrawStat() {
		return withdrawStat;
	}

	/**
	 * 设置 提现状态
	 * @param withdrawStat
	 */
	public void setWithdrawStat(String withdrawStat) {
		this.withdrawStat = withdrawStat;
	}

    
    /**
	 * 获取 插入时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 插入时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 修改时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

    
    /**
	 * 获取 银行账号
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 银行账号
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

    
    /**
	 * 获取 户名
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 户名
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

    
    /**
	 * 获取 银行
	 * @return String
	 */
	public String getOpenBank() {
		return openBank;
	}

	/**
	 * 设置 银行
	 * @param openBank
	 */
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
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

}