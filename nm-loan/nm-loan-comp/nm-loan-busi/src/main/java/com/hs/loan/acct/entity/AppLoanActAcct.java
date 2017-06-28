package com.hs.loan.acct.entity;


import java.io.Serializable;

/**
 * APP_贷款实际打款情况 对象
 * @author autocreate
 * @create 2017-03-21
 */
public class AppLoanActAcct implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String loanNo ; 
    
    /***  */
  	private java.math.BigDecimal loanAmt ; 
    
    /***  */
  	private java.math.BigDecimal actAmt ; 

    //构造函数
    public AppLoanActAcct(){}

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
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActAmt() {
		return actAmt;
	}

	/**
	 * 设置 
	 * @param actAmt
	 */
	public void setActAmt(java.math.BigDecimal actAmt) {
		this.actAmt = actAmt;
	}

}