package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  对象
 * @author autocreate
 * @create 2016-09-26
 */
public class AppChanFstdateImport implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String loanNo ; 
    
    /*** 首次还款日期 */
  	private String fstDate ; 
    
    /*** 插入时间 */
  	private Date instDate ; 

    //构造函数
    public AppChanFstdateImport(){}

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
	 * 获取 首次还款日期
	 * @return String
	 */
	public String getFstDate() {
		return fstDate;
	}

	/**
	 * 设置 首次还款日期
	 * @param fstDate
	 */
	public void setFstDate(String fstDate) {
		this.fstDate = fstDate;
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