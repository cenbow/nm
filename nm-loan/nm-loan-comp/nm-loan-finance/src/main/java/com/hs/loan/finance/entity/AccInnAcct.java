package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_内部账户 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccInnAcct implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 账号 */
  	private String acctNo ; 
    
    /*** 账户名称 */
  	private String acctName ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 账户类型 */
  	private String acctTyp ; 
    
    /*** 开户机构 */
  	private String openOrg ; 
    
    /*** 开户日期 */
  	private Date openDate ; 
    
    /*** 科目号 */
  	private String subjNo ; 
    
    /*** 余额 */
  	private java.math.BigDecimal bal ; 
    
    /*** 可用余额 */
  	private java.math.BigDecimal avalBal ; 
    
    /*** 冻结金额 */
  	private java.math.BigDecimal frzAmt ; 
    
    /*** 账户状态 */
  	private String acctStat ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public AccInnAcct(){}

    //getter和setter方法
    
    /**
	 * 获取 账号
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 账号
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
	 * 获取 客户号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 账户类型
	 * @return String
	 */
	public String getAcctTyp() {
		return acctTyp;
	}

	/**
	 * 设置 账户类型
	 * @param acctTyp
	 */
	public void setAcctTyp(String acctTyp) {
		this.acctTyp = acctTyp;
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
	 * 获取 开户日期
	 * @return Date
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * 设置 开户日期
	 * @param openDate
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

    
    /**
	 * 获取 科目号
	 * @return String
	 */
	public String getSubjNo() {
		return subjNo;
	}

	/**
	 * 设置 科目号
	 * @param subjNo
	 */
	public void setSubjNo(String subjNo) {
		this.subjNo = subjNo;
	}

    
    /**
	 * 获取 余额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getBal() {
		return bal;
	}

	/**
	 * 设置 余额
	 * @param bal
	 */
	public void setBal(java.math.BigDecimal bal) {
		this.bal = bal;
	}

    
    /**
	 * 获取 可用余额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAvalBal() {
		return avalBal;
	}

	/**
	 * 设置 可用余额
	 * @param avalBal
	 */
	public void setAvalBal(java.math.BigDecimal avalBal) {
		this.avalBal = avalBal;
	}

    
    /**
	 * 获取 冻结金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFrzAmt() {
		return frzAmt;
	}

	/**
	 * 设置 冻结金额
	 * @param frzAmt
	 */
	public void setFrzAmt(java.math.BigDecimal frzAmt) {
		this.frzAmt = frzAmt;
	}

    
    /**
	 * 获取 账户状态
	 * @return String
	 */
	public String getAcctStat() {
		return acctStat;
	}

	/**
	 * 设置 账户状态
	 * @param acctStat
	 */
	public void setAcctStat(String acctStat) {
		this.acctStat = acctStat;
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