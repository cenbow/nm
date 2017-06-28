package com.hs.loan.acct.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_产品试算 对象
 * @author autocreate
 * @create 2015-10-21
 */
public class PubProdFeeCalc implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 费用编号 */
  	private String feeNo ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /*** 费用费率 */
  	private java.math.BigDecimal feeRat ; 
    
    /*** 费用名称 */
  	private String feeName ; 
    
    /*** 结算优先级 */
  	private Integer setlPrior ; 
    
    /*** 分期金额 */
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 期数 */
  	private Integer instNum ; 
    
    /*** 费用金额 */
  	private java.math.BigDecimal feeAmt ; 

    //构造函数
    public PubProdFeeCalc(){}

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
	 * 获取 费用编号
	 * @return String
	 */
	public String getFeeNo() {
		return feeNo;
	}

	/**
	 * 设置 费用编号
	 * @param feeNo
	 */
	public void setFeeNo(String feeNo) {
		this.feeNo = feeNo;
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
	 * 获取 费用费率
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFeeRat() {
		return feeRat;
	}

	/**
	 * 设置 费用费率
	 * @param feeRat
	 */
	public void setFeeRat(java.math.BigDecimal feeRat) {
		this.feeRat = feeRat;
	}

    
    /**
	 * 获取 费用名称
	 * @return String
	 */
	public String getFeeName() {
		return feeName;
	}

	/**
	 * 设置 费用名称
	 * @param feeName
	 */
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

    
    /**
	 * 获取 结算优先级
	 * @return Integer
	 */
	public Integer getSetlPrior() {
		return setlPrior;
	}

	/**
	 * 设置 结算优先级
	 * @param setlPrior
	 */
	public void setSetlPrior(Integer setlPrior) {
		this.setlPrior = setlPrior;
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
	 * 获取 期数
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 期数
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

    
    /**
	 * 获取 费用金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	/**
	 * 设置 费用金额
	 * @param feeAmt
	 */
	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

}