package com.hs.loan.collection.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_委外金额明细 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOutsourceAmountDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 费用项 */
  	private String feeTyp ; 
    
    /*** 本金 */
  	private java.math.BigDecimal feeAmt ; 
    
    /*** 插入时间 */
  	private Date instDate ; 

    //构造函数
    public LoanOutsourceAmountDto(){}

    //getter和setter方法
    
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
	 * 获取 费用项
	 * @return String
	 */
	public String getFeeTyp() {
		return feeTyp;
	}

	/**
	 * 设置 费用项
	 * @param feeTyp
	 */
	public void setFeeTyp(String feeTyp) {
		this.feeTyp = feeTyp;
	}

    
    /**
	 * 获取 本金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	/**
	 * 设置 本金
	 * @param feeAmt
	 */
	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
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

}