package com.hs.loan.pub.hand.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期经办登记 对象
 * @author autocreate
 * @create 2015-10-27
 */
public class AppLoanHand implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
  	/*** 客户名 */
  	private String custName; 
    
    /*** 类型 */
  	private String typ ; 
    
    /*** 处理日期 */
  	private Date handDate ; 
    
    /*** 处理人编号 */
  	private String handPersonNo ; 
  	
  	/*** 处理人名 */
  	private String handPersonName ; 
    
    /*** 说明 */
  	private String remark ; 
    
    /*** 处理内容 */
  	private String handDetail ; 
    
    /*** 插入时间 */
  	private Date instDate ; 

  	/***客户标识*/
  	private String custIdentifier;
  	
    //构造函数
    public AppLoanHand(){}

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
	 * 获取 类型
	 * @return String
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * 设置 类型
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

    
    /**
	 * 获取 处理日期
	 * @return Date
	 */
	public Date getHandDate() {
		return handDate;
	}

	/**
	 * 设置 处理日期
	 * @param handDate
	 */
	public void setHandDate(Date handDate) {
		this.handDate = handDate;
	}

    
    public String getHandPersonNo() {
		return handPersonNo;
	}

	public void setHandPersonNo(String handPersonNo) {
		this.handPersonNo = handPersonNo;
	}

	public String getHandPersonName() {
		return handPersonName;
	}

	public void setHandPersonName(String handPersonName) {
		this.handPersonName = handPersonName;
	}

	/**
	 * 获取 说明
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 说明
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    /**
	 * 获取 处理内容
	 * @return String
	 */
	public String getHandDetail() {
		return handDetail;
	}

	/**
	 * 设置 处理内容
	 * @param handDetail
	 */
	public void setHandDetail(String handDetail) {
		this.handDetail = handDetail;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustIdentifier() {
		return custIdentifier;
	}

	public void setCustIdentifier(String custIdentifier) {
		this.custIdentifier = custIdentifier;
	}

}