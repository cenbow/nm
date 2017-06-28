package com.hs.loan.sale.entity;


import java.io.Serializable;
import java.util.Date;

/**
 *  对象
 * @author autocreate
 * @create 2016-08-30
 */
public class AppChanImport implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String loanNo ; 
    
    /***  */
  	private String chanNo ; 
    
    /***  */
  	private Integer biddingNum ; 
    
    /***  */
  	private Integer instNum ; 
    
    /***  */
  	private java.math.BigDecimal rat ; 
    
    /***  */
  	private java.math.BigDecimal contractAmt ; 
  	
  	private Date instDate;

    //构造函数
    public AppChanImport(){}

    //getter和setter方法
    
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
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getBiddingNum() {
		return biddingNum;
	}

	/**
	 * 设置 
	 * @param biddingNum
	 */
	public void setBiddingNum(Integer biddingNum) {
		this.biddingNum = biddingNum;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRat() {
		return rat;
	}

	/**
	 * 设置 
	 * @param rat
	 */
	public void setRat(java.math.BigDecimal rat) {
		this.rat = rat;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getContractAmt() {
		return contractAmt;
	}

	/**
	 * 设置 
	 * @param contractAmt
	 */
	public void setContractAmt(java.math.BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}

	public Date getInstDate() {
		return instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}