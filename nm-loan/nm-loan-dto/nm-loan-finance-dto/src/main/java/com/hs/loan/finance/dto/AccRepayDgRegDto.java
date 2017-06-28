package com.hs.loan.finance.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * ACC_还款登记（对公） 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccRepayDgRegDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 还款账户 */
  	private String custAcct ; 
    
    /***  */
  	private String acctName ; 
    
    /*** 100231-对公转账/100232-支付宝/100233-现金还款 */
  	private String tranType ; 
  	
  	 /*** 100231-对公转账/100232-支付宝/100233-现金还款 */
  	private String tranTypeTwo ; 
  	
    /*** 还款期数 */
  	private Integer repayNum ; 
    
    /*** 应还金额 */
  	private java.math.BigDecimal totlAmt ; 
    
    /*** 还款日期 */
  	private Date tranDate ; 
    
  	 /*** 还款日期 */
  	private Date tranDateTwo; 
  	
    /*** 还款金额 */
  	private java.math.BigDecimal tranAmt ; 
  	
  	 /*** 还款金额 */
  	private java.math.BigDecimal tranAmtTwo ; 
    /*** 还款状态 */
  	private String stat ; 
    
    /*** 经办人 */
  	private String tranStaff ; 
    
    /*** 经办人姓名 */
  	private String tranOrg ; 
    
    /*** 经办备注 */
  	private String tranDesc ; 
    
  	/*** 扣款类型 */
  	private String dgType; 
  	
    /*** 创建日期 */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date instDate ; 
  	
  	/*** 还款凭证号 */
  	private String docNo; 
  	
  	/*** 还款交易流水号 */
  	private String serlNo; 
  	/*** 身份证号 */
  	private String certNo; 
  	/*** 银行卡号*/
  	private String bankNo; 
  	/*** 开户机构 */
  	private String openOrg; 
  	/***结清类型*/
  	private String setlType;

  	
    //构造函数
    public AccRepayDgRegDto(){}

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
	 * 获取 账单日
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 账单日
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 还款账户
	 * @return String
	 */
	public String getCustAcct() {
		return custAcct;
	}

	/**
	 * 设置 还款账户
	 * @param custAcct
	 */
	public void setCustAcct(String custAcct) {
		this.custAcct = custAcct;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

    
    /**
	 * 获取 100231-对公转账/100232-支付宝/100233-现金还款
	 * @return String
	 */
	public String getTranType() {
		return tranType;
	}

	/**
	 * 设置 100231-对公转账/100232-支付宝/100233-现金还款
	 * @param tranType
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
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
	 * 获取 应还金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTotlAmt() {
		return totlAmt;
	}

	/**
	 * 设置 应还金额
	 * @param totlAmt
	 */
	public void setTotlAmt(java.math.BigDecimal totlAmt) {
		this.totlAmt = totlAmt;
	}

    
    /**
	 * 获取 还款日期
	 * @return Date
	 */
	public Date getTranDate() {
		return tranDate;
	}

	/**
	 * 设置 还款日期
	 * @param tranDate
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

    
    /**
	 * 获取 还款金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTranAmt() {
		return tranAmt;
	}

	/**
	 * 设置 还款金额
	 * @param tranAmt
	 */
	public void setTranAmt(java.math.BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}

    
    /**
	 * 获取 还款状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 还款状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 经办人
	 * @return String
	 */
	public String getTranStaff() {
		return tranStaff;
	}

	/**
	 * 设置 经办人
	 * @param tranStaff
	 */
	public void setTranStaff(String tranStaff) {
		this.tranStaff = tranStaff;
	}

    
    /**
	 * 获取 经办人姓名
	 * @return String
	 */
	public String getTranOrg() {
		return tranOrg;
	}

	/**
	 * 设置 经办人姓名
	 * @param tranOrg
	 */
	public void setTranOrg(String tranOrg) {
		this.tranOrg = tranOrg;
	}

    
    /**
	 * 获取 经办备注
	 * @return String
	 */
	public String getTranDesc() {
		return tranDesc;
	}

	/**
	 * 设置 经办备注
	 * @param tranDesc
	 */
	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
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

	public String getDgType() {
		return dgType;
	}

	public void setDgType(String dgType) {
		this.dgType = dgType;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getSerlNo() {
		return serlNo;
	}

	public void setSerlNo(String serlNo) {
		this.serlNo = serlNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getOpenOrg() {
		return openOrg;
	}

	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

	public String getTranTypeTwo() {
		return tranTypeTwo;
	}

	public void setTranTypeTwo(String tranTypeTwo) {
		this.tranTypeTwo = tranTypeTwo;
	}

	public java.math.BigDecimal getTranAmtTwo() {
		return tranAmtTwo;
	}

	public void setTranAmtTwo(java.math.BigDecimal tranAmtTwo) {
		this.tranAmtTwo = tranAmtTwo;
	}

	public Date getTranDateTwo() {
		return tranDateTwo;
	}

	public void setTranDateTwo(Date tranDateTwo) {
		this.tranDateTwo = tranDateTwo;
	}

	public String getSetlType() {
		return setlType;
	}

	public void setSetlType(String setlType) {
		this.setlType = setlType;
	}

}