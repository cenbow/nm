package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_分录流水 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccAcctJnl implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 交易流水号 */
  	private String seqNo ; 
    
    /*** 分录序号 */
  	private Integer serlNo ; 
    
    /*** 交易日期 */
  	private Date txDate ; 
    
    /*** 账号 */
  	private String acctNo ; 
    
    /*** 账户名称 */
  	private String acctName ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 科目号 */
  	private String subjNo ; 
    
    /*** 借贷标志 */
  	private String drCrFlag ; 
    
    /*** 交易码 */
  	private String txCod ; 
    
    /*** 摘要 */
  	private String txDesc ; 
    
    /*** 交易金额 */
  	private java.math.BigDecimal txAmt ; 
    
    /*** 余额 */
  	private java.math.BigDecimal bal ; 
    
    /*** 对方账号 */
  	private String cntAcctNo ; 
    
    /*** 对方户名 */
  	private String cntAcctName ; 
    
    /*** 交易状态 */
  	private String txStat ; 
    
    /*** 批量标志 */
  	private String batFlag ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public AccAcctJnl(){}

    //getter和setter方法
    
    /**
	 * 获取 交易流水号
	 * @return String
	 */
	public String getSeqNo() {
		return seqNo;
	}

	/**
	 * 设置 交易流水号
	 * @param seqNo
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

    
    /**
	 * 获取 分录序号
	 * @return Integer
	 */
	public Integer getSerlNo() {
		return serlNo;
	}

	/**
	 * 设置 分录序号
	 * @param serlNo
	 */
	public void setSerlNo(Integer serlNo) {
		this.serlNo = serlNo;
	}

    
    /**
	 * 获取 交易日期
	 * @return Date
	 */
	public Date getTxDate() {
		return txDate;
	}

	/**
	 * 设置 交易日期
	 * @param txDate
	 */
	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}

    
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
	 * 获取 借贷标志
	 * @return String
	 */
	public String getDrCrFlag() {
		return drCrFlag;
	}

	/**
	 * 设置 借贷标志
	 * @param drCrFlag
	 */
	public void setDrCrFlag(String drCrFlag) {
		this.drCrFlag = drCrFlag;
	}

    
    /**
	 * 获取 交易码
	 * @return String
	 */
	public String getTxCod() {
		return txCod;
	}

	/**
	 * 设置 交易码
	 * @param txCod
	 */
	public void setTxCod(String txCod) {
		this.txCod = txCod;
	}

    
    /**
	 * 获取 摘要
	 * @return String
	 */
	public String getTxDesc() {
		return txDesc;
	}

	/**
	 * 设置 摘要
	 * @param txDesc
	 */
	public void setTxDesc(String txDesc) {
		this.txDesc = txDesc;
	}

    
    /**
	 * 获取 交易金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTxAmt() {
		return txAmt;
	}

	/**
	 * 设置 交易金额
	 * @param txAmt
	 */
	public void setTxAmt(java.math.BigDecimal txAmt) {
		this.txAmt = txAmt;
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
	 * 获取 对方账号
	 * @return String
	 */
	public String getCntAcctNo() {
		return cntAcctNo;
	}

	/**
	 * 设置 对方账号
	 * @param cntAcctNo
	 */
	public void setCntAcctNo(String cntAcctNo) {
		this.cntAcctNo = cntAcctNo;
	}

    
    /**
	 * 获取 对方户名
	 * @return String
	 */
	public String getCntAcctName() {
		return cntAcctName;
	}

	/**
	 * 设置 对方户名
	 * @param cntAcctName
	 */
	public void setCntAcctName(String cntAcctName) {
		this.cntAcctName = cntAcctName;
	}

    
    /**
	 * 获取 交易状态
	 * @return String
	 */
	public String getTxStat() {
		return txStat;
	}

	/**
	 * 设置 交易状态
	 * @param txStat
	 */
	public void setTxStat(String txStat) {
		this.txStat = txStat;
	}

    
    /**
	 * 获取 批量标志
	 * @return String
	 */
	public String getBatFlag() {
		return batFlag;
	}

	/**
	 * 设置 批量标志
	 * @param batFlag
	 */
	public void setBatFlag(String batFlag) {
		this.batFlag = batFlag;
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