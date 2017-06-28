package com.hs.loan.busi.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_分期产品试算 对象
 * @author autocreate
 * @create 2015-11-04
 */
public class LoanProdCalcDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 还款日期 */
  	private String repayDate ; 
    
    /*** 分期期数 */
  	private Integer instNum ; 
    
    /*** 还款方式 */
  	private String repayKind ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /*** 分期金额 */
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 还款金额 */
  	private java.math.BigDecimal feeAmt ; 
  	/*** 手续费金额 */
  	private java.math.BigDecimal chargeAmt; 

    //构造函数
    public LoanProdCalcDto(){}

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
	 * 获取 还款日期
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 还款日期
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 分期期数
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 分期期数
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

    
    /**
	 * 获取 还款方式
	 * @return String
	 */
	public String getRepayKind() {
		return repayKind;
	}

	/**
	 * 设置 还款方式
	 * @param repayKind
	 */
	public void setRepayKind(String repayKind) {
		this.repayKind = repayKind;
	}

    
    /**
	 * 获取 产品编号
	 * @return String
	 */
	public String getProdNo() {
		return prodNo;
	}

	/**
	 * 设置 产品编号
	 * @param prodNo
	 */
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

    
    /**
	 * 获取 分期金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 分期金额
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 还款金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	/**
	 * 设置 还款金额
	 * @param feeAmt
	 */
	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public java.math.BigDecimal getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(java.math.BigDecimal chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

}