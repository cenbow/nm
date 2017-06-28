package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户历史还款情况 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustHisRepay implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 还款金额 */
  	private String repayAmt ; 
    
    /*** 还款时间 */
  	private String repayDate ; 
    
    /*** 还款渠道 */
  	private String repayChan ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public AppCustHisRepay(){}

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
	 * 获取 客户编号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
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
	 * 获取 还款金额
	 * @return String
	 */
	public String getRepayAmt() {
		return repayAmt;
	}

	/**
	 * 设置 还款金额
	 * @param repayAmt
	 */
	public void setRepayAmt(String repayAmt) {
		this.repayAmt = repayAmt;
	}

    
    /**
	 * 获取 还款时间
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 还款时间
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 还款渠道
	 * @return String
	 */
	public String getRepayChan() {
		return repayChan;
	}

	/**
	 * 设置 还款渠道
	 * @param repayChan
	 */
	public void setRepayChan(String repayChan) {
		this.repayChan = repayChan;
	}

    
    /**
	 * 获取 开始日期
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}