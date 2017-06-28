package com.hs.loan.customer.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户来电记录 对象
 * @author autocreate
 * @create 2016-07-07
 */
public class AppCustCallRegister implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 来电号码 */
  	private String callNo ; 
    
    /*** 来电时间 */
  	private Date callTime ; 
    
    /*** 来电内容 */
  	private String callContent ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 
  	 /*** 来电类型 */
  	private String queryType ; 
  	
    /*** 来电说明 */
  	private String queryTemp ; 
    //构造函数
    public AppCustCallRegister(){}

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
	 * 获取 客户姓名
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户姓名
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 来电号码
	 * @return String
	 */
	public String getCallNo() {
		return callNo;
	}

	/**
	 * 设置 来电号码
	 * @param callNo
	 */
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}

    
    /**
	 * 获取 来电时间
	 * @return Date
	 */
	public Date getCallTime() {
		return callTime;
	}

	/**
	 * 设置 来电时间
	 * @param callTime
	 */
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

    
    /**
	 * 获取 来电内容
	 * @return String
	 */
	public String getCallContent() {
		return callContent;
	}

	/**
	 * 设置 来电内容
	 * @param callContent
	 */
	public void setCallContent(String callContent) {
		this.callContent = callContent;
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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryTemp() {
		return queryTemp;
	}

	public void setQueryTemp(String queryTemp) {
		this.queryTemp = queryTemp;
	}

}