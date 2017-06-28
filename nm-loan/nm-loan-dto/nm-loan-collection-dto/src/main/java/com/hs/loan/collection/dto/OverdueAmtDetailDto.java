package com.hs.loan.collection.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_逾期费用项明细 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class OverdueAmtDetailDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /***  */
  	private String caseId ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 费用类型 */
  	private String feeType ; 
    
    /*** 费用类型名称 */
  	private String feeName ; 
    
    /*** 逾期金额 */
  	private java.math.BigDecimal overAmt ; 
    
    /*** 剩余金额 */
  	private java.math.BigDecimal residueAmt ; 
    
    /*** 插入时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public OverdueAmtDetailDto(){}

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
	 * 获取 
	 * @return String
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * 设置 
	 * @param caseId
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
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
	 * 获取 费用类型
	 * @return String
	 */
	public String getFeeType() {
		return feeType;
	}

	/**
	 * 设置 费用类型
	 * @param feeType
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

    
    /**
	 * 获取 费用类型名称
	 * @return String
	 */
	public String getFeeName() {
		return feeName;
	}

	/**
	 * 设置 费用类型名称
	 * @param feeName
	 */
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

    
    /**
	 * 获取 逾期金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOverAmt() {
		return overAmt;
	}

	/**
	 * 设置 逾期金额
	 * @param overAmt
	 */
	public void setOverAmt(java.math.BigDecimal overAmt) {
		this.overAmt = overAmt;
	}

    
    /**
	 * 获取 剩余金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getResidueAmt() {
		return residueAmt;
	}

	/**
	 * 设置 剩余金额
	 * @param residueAmt
	 */
	public void setResidueAmt(java.math.BigDecimal residueAmt) {
		this.residueAmt = residueAmt;
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