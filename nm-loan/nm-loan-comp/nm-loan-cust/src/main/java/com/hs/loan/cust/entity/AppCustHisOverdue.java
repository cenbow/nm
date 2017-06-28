package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户历史逾期情况 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustHisOverdue implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 逾期开始日期 */
  	private String overdueStart ; 
    
    /*** 逾期结束日期 */
  	private String overdueEnd ; 
    
    /*** 逾期金额 */
  	private String overdueAmt ; 
    
    /*** 滞纳金 */
  	private String overdueFine ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public AppCustHisOverdue(){}

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
	 * 获取 逾期开始日期
	 * @return String
	 */
	public String getOverdueStart() {
		return overdueStart;
	}

	/**
	 * 设置 逾期开始日期
	 * @param overdueStart
	 */
	public void setOverdueStart(String overdueStart) {
		this.overdueStart = overdueStart;
	}

    
    /**
	 * 获取 逾期结束日期
	 * @return String
	 */
	public String getOverdueEnd() {
		return overdueEnd;
	}

	/**
	 * 设置 逾期结束日期
	 * @param overdueEnd
	 */
	public void setOverdueEnd(String overdueEnd) {
		this.overdueEnd = overdueEnd;
	}

    
    /**
	 * 获取 逾期金额
	 * @return String
	 */
	public String getOverdueAmt() {
		return overdueAmt;
	}

	/**
	 * 设置 逾期金额
	 * @param overdueAmt
	 */
	public void setOverdueAmt(String overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

    
    /**
	 * 获取 滞纳金
	 * @return String
	 */
	public String getOverdueFine() {
		return overdueFine;
	}

	/**
	 * 设置 滞纳金
	 * @param overdueFine
	 */
	public void setOverdueFine(String overdueFine) {
		this.overdueFine = overdueFine;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 创建时间
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 更新时间
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}