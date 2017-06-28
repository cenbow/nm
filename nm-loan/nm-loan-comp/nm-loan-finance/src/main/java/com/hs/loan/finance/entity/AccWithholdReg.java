package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_扣款流水记录表 对象
 * @author autocreate
 * @create 2016-12-07
 */
public class AccWithholdReg implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String loanNo ; 
    
    /***  */
  	private String tranTyp ; 
    
    /***  */
  	private java.math.BigDecimal tranAmt ; 
    
    /***  */
  	private String repayDate ; 
    
    /***  */
  	private java.math.BigDecimal rcvAmt ; 
    
    /***  */
  	private String bankNo ; 
    
    /***  */
  	private String acctNum ; 
    
    /***  */
  	private String acctName ; 
    
    /***  */
  	private Integer bgnNum ; 
    
    /***  */
  	private Integer endNum ; 
    
    /***  */
  	private String withStat ; 
    
    /***  */
  	private String settleStat ; 
    
    /***  */
  	private Date tranBgnDate ; 
    
    /***  */
  	private Date tranEndDate ; 
    
    /***  */
  	private String txsn ; 
    
    /***  */
  	private String staffNo ; 
    
    /***  */
  	private String staffName ; 
    
    /***  */
  	private String orgNo ; 
  	
  	
  	private String tranChan;

    //构造函数
    public AccWithholdReg(){}

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
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getTranTyp() {
		return tranTyp;
	}

	/**
	 * 设置 
	 * @param tranTyp
	 */
	public void setTranTyp(String tranTyp) {
		this.tranTyp = tranTyp;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTranAmt() {
		return tranAmt;
	}

	/**
	 * 设置 
	 * @param tranAmt
	 */
	public void setTranAmt(java.math.BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRcvAmt() {
		return rcvAmt;
	}

	/**
	 * 设置 
	 * @param rcvAmt
	 */
	public void setRcvAmt(java.math.BigDecimal rcvAmt) {
		this.rcvAmt = rcvAmt;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * 设置 
	 * @param bankNo
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAcctNum() {
		return acctNum;
	}

	/**
	 * 设置 
	 * @param acctNum
	 */
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
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
	 * 获取 
	 * @return Integer
	 */
	public Integer getBgnNum() {
		return bgnNum;
	}

	/**
	 * 设置 
	 * @param bgnNum
	 */
	public void setBgnNum(Integer bgnNum) {
		this.bgnNum = bgnNum;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getEndNum() {
		return endNum;
	}

	/**
	 * 设置 
	 * @param endNum
	 */
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getWithStat() {
		return withStat;
	}

	/**
	 * 设置 
	 * @param withStat
	 */
	public void setWithStat(String withStat) {
		this.withStat = withStat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSettleStat() {
		return settleStat;
	}

	/**
	 * 设置 
	 * @param settleStat
	 */
	public void setSettleStat(String settleStat) {
		this.settleStat = settleStat;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getTranBgnDate() {
		return tranBgnDate;
	}

	/**
	 * 设置 
	 * @param tranBgnDate
	 */
	public void setTranBgnDate(Date tranBgnDate) {
		this.tranBgnDate = tranBgnDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getTranEndDate() {
		return tranEndDate;
	}

	/**
	 * 设置 
	 * @param tranEndDate
	 */
	public void setTranEndDate(Date tranEndDate) {
		this.tranEndDate = tranEndDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getTxsn() {
		return txsn;
	}

	/**
	 * 设置 
	 * @param txsn
	 */
	public void setTxsn(String txsn) {
		this.txsn = txsn;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getTranChan() {
		return tranChan;
	}

	public void setTranChan(String tranChan) {
		this.tranChan = tranChan;
	}

}