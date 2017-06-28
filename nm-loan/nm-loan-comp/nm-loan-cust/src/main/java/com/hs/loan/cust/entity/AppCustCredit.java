package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户授信额度 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustCredit implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 授信额度 */
  	private String creditLimit ; 
    
    /*** 剩余额度 */
  	private String remainLimit ; 
    
    /*** 授信额度方式 */
  	private String creditTyp ; 
    
    /*** 授信额度日期 */
  	private String creditDate ; 
    
    /*** 人工授信额度有效期 */
  	private String creditValid ; 
    
    /*** 授信额度调整用户 */
  	private String creditUser ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public AppCustCredit(){}

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
	 * 获取 授信额度
	 * @return String
	 */
	public String getCreditLimit() {
		return creditLimit;
	}

	/**
	 * 设置 授信额度
	 * @param creditLimit
	 */
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

    
    /**
	 * 获取 剩余额度
	 * @return String
	 */
	public String getRemainLimit() {
		return remainLimit;
	}

	/**
	 * 设置 剩余额度
	 * @param remainLimit
	 */
	public void setRemainLimit(String remainLimit) {
		this.remainLimit = remainLimit;
	}

    
    /**
	 * 获取 授信额度方式
	 * @return String
	 */
	public String getCreditTyp() {
		return creditTyp;
	}

	/**
	 * 设置 授信额度方式
	 * @param creditTyp
	 */
	public void setCreditTyp(String creditTyp) {
		this.creditTyp = creditTyp;
	}

    
    /**
	 * 获取 授信额度日期
	 * @return String
	 */
	public String getCreditDate() {
		return creditDate;
	}

	/**
	 * 设置 授信额度日期
	 * @param creditDate
	 */
	public void setCreditDate(String creditDate) {
		this.creditDate = creditDate;
	}

    
    /**
	 * 获取 人工授信额度有效期
	 * @return String
	 */
	public String getCreditValid() {
		return creditValid;
	}

	/**
	 * 设置 人工授信额度有效期
	 * @param creditValid
	 */
	public void setCreditValid(String creditValid) {
		this.creditValid = creditValid;
	}

    
    /**
	 * 获取 授信额度调整用户
	 * @return String
	 */
	public String getCreditUser() {
		return creditUser;
	}

	/**
	 * 设置 授信额度调整用户
	 * @param creditUser
	 */
	public void setCreditUser(String creditUser) {
		this.creditUser = creditUser;
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