package com.hs.loan.collection.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_案件还款日志 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class PlCaseRepayLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** PL__案件ID */
  	private String plCaseId ; 
    
    /*** 案件ID */
  	private String caseId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** REPAY_KEY */
  	private String repayKey ; 
    
    /*** 交易金额 */
  	private java.math.BigDecimal txAmt ; 
    
    /*** 交易类型 */
  	private String txCate ; 
    
    /*** 交易结论 */
  	private String txResult ; 
    
    /*** 备注 */
  	private String remark2 ; 
    
    /*** 插入时间 */
  	private Date instDate ; 
    
    /*** 插入用户编号 */
  	private String instUser ; 
    
    /*** 插入用户名 */
  	private String instUserName ; 

    //构造函数
    public PlCaseRepayLog(){}

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
	 * 获取 PL__案件ID
	 * @return String
	 */
	public String getPlCaseId() {
		return plCaseId;
	}

	/**
	 * 设置 PL__案件ID
	 * @param plCaseId
	 */
	public void setPlCaseId(String plCaseId) {
		this.plCaseId = plCaseId;
	}

    
    /**
	 * 获取 案件ID
	 * @return String
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * 设置 案件ID
	 * @param caseId
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
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
	 * 获取 REPAY_KEY
	 * @return String
	 */
	public String getRepayKey() {
		return repayKey;
	}

	/**
	 * 设置 REPAY_KEY
	 * @param repayKey
	 */
	public void setRepayKey(String repayKey) {
		this.repayKey = repayKey;
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
	 * 获取 交易类型
	 * @return String
	 */
	public String getTxCate() {
		return txCate;
	}

	/**
	 * 设置 交易类型
	 * @param txCate
	 */
	public void setTxCate(String txCate) {
		this.txCate = txCate;
	}

    
    /**
	 * 获取 交易结论
	 * @return String
	 */
	public String getTxResult() {
		return txResult;
	}

	/**
	 * 设置 交易结论
	 * @param txResult
	 */
	public void setTxResult(String txResult) {
		this.txResult = txResult;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * 设置 备注
	 * @param remark2
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
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
	 * 获取 插入用户编号
	 * @return String
	 */
	public String getInstUser() {
		return instUser;
	}

	/**
	 * 设置 插入用户编号
	 * @param instUser
	 */
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

    
    /**
	 * 获取 插入用户名
	 * @return String
	 */
	public String getInstUserName() {
		return instUserName;
	}

	/**
	 * 设置 插入用户名
	 * @param instUserName
	 */
	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

}