package com.hs.loan.outsource.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.util.Date;

import java.io.Serializable;

/**
 * PL_委外减免表 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class PlLoanOutsourceDerate implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 减免类型 */
  	private String derateTyp ; 
    
    /*** 减免金额 */
  	private java.math.BigDecimal derateAmt ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 插入用户编码 */
  	private String instUserNo ; 
    
    /*** 插入用户名称 */
  	private String instUserName ; 
    
    /*** 创建时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date instDate ; 

    //构造函数
    public PlLoanOutsourceDerate(){}

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
	 * 获取 减免类型
	 * @return String
	 */
	public String getDerateTyp() {
		return derateTyp;
	}

	/**
	 * 设置 减免类型
	 * @param derateTyp
	 */
	public void setDerateTyp(String derateTyp) {
		this.derateTyp = derateTyp;
	}

    
    /**
	 * 获取 减免金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getDerateAmt() {
		return derateAmt;
	}

	/**
	 * 设置 减免金额
	 * @param derateAmt
	 */
	public void setDerateAmt(java.math.BigDecimal derateAmt) {
		this.derateAmt = derateAmt;
	}

    
    /**
	 * 获取 状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 插入用户编码
	 * @return String
	 */
	public String getInstUserNo() {
		return instUserNo;
	}

	/**
	 * 设置 插入用户编码
	 * @param instUserNo
	 */
	public void setInstUserNo(String instUserNo) {
		this.instUserNo = instUserNo;
	}

    
    /**
	 * 获取 插入用户名称
	 * @return String
	 */
	public String getInstUserName() {
		return instUserName;
	}

	/**
	 * 设置 插入用户名称
	 * @param instUserName
	 */
	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}