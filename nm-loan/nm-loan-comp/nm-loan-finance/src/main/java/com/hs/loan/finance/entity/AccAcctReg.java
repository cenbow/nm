package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_账务登记 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccAcctReg implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String repayId ; 
    
    /*** 账号 */
  	private String loanNo ; 
    
    /*** 账户名称 */
  	private String repayDate ; 
    
    /*** 10040102-补账/10040103-冲账 */
  	private String acctTyp ; 
    
    /*** 余额 */
  	private java.math.BigDecimal tranChan ; 
    
    /*** 开户日期 */
  	private Date tranDate ; 
    
    /*** 可用余额 */
  	private java.math.BigDecimal tranAmt ; 
    
    /***  */
  	private String tranDesc ; 
    
    /*** 开户机构 */
  	private String tranOrg ; 
    
    /*** 账户状态 */
  	private String tranStaff ; 
    
    /***  */
  	private String setlFlag ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public AccAcctReg(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRepayId() {
		return repayId;
	}

	/**
	 * 设置 
	 * @param repayId
	 */
	public void setRepayId(String repayId) {
		this.repayId = repayId;
	}

    
    /**
	 * 获取 账号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 账号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 账户名称
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 账户名称
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 10040102-补账/10040103-冲账
	 * @return String
	 */
	public String getAcctTyp() {
		return acctTyp;
	}

	/**
	 * 设置 10040102-补账/10040103-冲账
	 * @param acctTyp
	 */
	public void setAcctTyp(String acctTyp) {
		this.acctTyp = acctTyp;
	}

    
    /**
	 * 获取 余额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTranChan() {
		return tranChan;
	}

	/**
	 * 设置 余额
	 * @param tranChan
	 */
	public void setTranChan(java.math.BigDecimal tranChan) {
		this.tranChan = tranChan;
	}

    
    /**
	 * 获取 开户日期
	 * @return Date
	 */
	public Date getTranDate() {
		return tranDate;
	}

	/**
	 * 设置 开户日期
	 * @param tranDate
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

    
    /**
	 * 获取 可用余额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTranAmt() {
		return tranAmt;
	}

	/**
	 * 设置 可用余额
	 * @param tranAmt
	 */
	public void setTranAmt(java.math.BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getTranDesc() {
		return tranDesc;
	}

	/**
	 * 设置 
	 * @param tranDesc
	 */
	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

    
    /**
	 * 获取 开户机构
	 * @return String
	 */
	public String getTranOrg() {
		return tranOrg;
	}

	/**
	 * 设置 开户机构
	 * @param tranOrg
	 */
	public void setTranOrg(String tranOrg) {
		this.tranOrg = tranOrg;
	}

    
    /**
	 * 获取 账户状态
	 * @return String
	 */
	public String getTranStaff() {
		return tranStaff;
	}

	/**
	 * 设置 账户状态
	 * @param tranStaff
	 */
	public void setTranStaff(String tranStaff) {
		this.tranStaff = tranStaff;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSetlFlag() {
		return setlFlag;
	}

	/**
	 * 设置 
	 * @param setlFlag
	 */
	public void setSetlFlag(String setlFlag) {
		this.setlFlag = setlFlag;
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