package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_还款流水 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccRepayFlow implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 10040101-正常/10040102-补账/10040103-冲账 */
  	private String tranType ; 
    
    /*** 100210-银联单笔代扣/100220-银联批量代扣/100230-对公/100231-对公转账/100232-支付宝/100233-现金还款 */
  	private String tranChan ; 
    
    /*** 100310-平台/100320-资方 */
  	private String tranObj ; 
    
    /*** 还款日期 */
  	private String tranDate ; 
    
    /*** 还款金额 */
  	private java.math.BigDecimal tranAmt ; 
    
    /***  */
  	private String acctNo ; 
    
    /***  */
  	private String acctName ; 
    
    /*** 100110-正常/100120-逾期/100130-委外 */
  	private String loanStat ; 
    
    /***  */
  	private String cntAcctNo ; 
    
    /***  */
  	private String cntAcctName ; 
    
    /***  */
  	private String tranOrg ; 
    
    /***  */
  	private String tranStaff ; 
    
    /*** 结算标志 */
  	private String setlFlag ; 
    
    /*** 插入时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
  	
  	private Integer repayNum;
  	

    public Integer getRepayNum() {
		return repayNum;
	}

	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

	//构造函数
    public AccRepayFlow(){}

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
	 * 获取 分期编码
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编码
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
	 * 获取 10040101-正常/10040102-补账/10040103-冲账
	 * @return String
	 */
	public String getTranType() {
		return tranType;
	}

	/**
	 * 设置 10040101-正常/10040102-补账/10040103-冲账
	 * @param tranType
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

    
    /**
	 * 获取 100210-银联单笔代扣/100220-银联批量代扣/100230-对公/100231-对公转账/100232-支付宝/100233-现金还款
	 * @return String
	 */
	public String getTranChan() {
		return tranChan;
	}

	/**
	 * 设置 100210-银联单笔代扣/100220-银联批量代扣/100230-对公/100231-对公转账/100232-支付宝/100233-现金还款
	 * @param tranChan
	 */
	public void setTranChan(String tranChan) {
		this.tranChan = tranChan;
	}

    
    /**
	 * 获取 100310-平台/100320-资方
	 * @return String
	 */
	public String getTranObj() {
		return tranObj;
	}

	/**
	 * 设置 100310-平台/100320-资方
	 * @param tranObj
	 */
	public void setTranObj(String tranObj) {
		this.tranObj = tranObj;
	}

    
    /**
	 * 获取 还款日期
	 * @return String
	 */
	public String getTranDate() {
		return tranDate;
	}

	/**
	 * 设置 还款日期
	 * @param tranDate
	 */
	public void setTranDate(String tranDate) {
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
	 * 获取 
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
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
	 * 获取 100110-正常/100120-逾期/100130-委外
	 * @return String
	 */
	public String getLoanStat() {
		return loanStat;
	}

	/**
	 * 设置 100110-正常/100120-逾期/100130-委外
	 * @param loanStat
	 */
	public void setLoanStat(String loanStat) {
		this.loanStat = loanStat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCntAcctNo() {
		return cntAcctNo;
	}

	/**
	 * 设置 
	 * @param cntAcctNo
	 */
	public void setCntAcctNo(String cntAcctNo) {
		this.cntAcctNo = cntAcctNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCntAcctName() {
		return cntAcctName;
	}

	/**
	 * 设置 
	 * @param cntAcctName
	 */
	public void setCntAcctName(String cntAcctName) {
		this.cntAcctName = cntAcctName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getTranOrg() {
		return tranOrg;
	}

	/**
	 * 设置 
	 * @param tranOrg
	 */
	public void setTranOrg(String tranOrg) {
		this.tranOrg = tranOrg;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getTranStaff() {
		return tranStaff;
	}

	/**
	 * 设置 
	 * @param tranStaff
	 */
	public void setTranStaff(String tranStaff) {
		this.tranStaff = tranStaff;
	}

    
    /**
	 * 获取 结算标志
	 * @return String
	 */
	public String getSetlFlag() {
		return setlFlag;
	}

	/**
	 * 设置 结算标志
	 * @param setlFlag
	 */
	public void setSetlFlag(String setlFlag) {
		this.setlFlag = setlFlag;
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