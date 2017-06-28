package com.hs.loan.collection.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.util.Date;

import java.io.Serializable;

/**
 * PL_委外金额收回流水 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOutsourceReturnDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 委外单位编码 */
  	private String unitNo ; 
  	 /*** 委外单位名称*/
  	private String unitName ; 
    /*** 交易金额 */
  	private java.math.BigDecimal payAmt ; 
    
    /*** 交易类型 */
  	private String payTyp ; 
    
    /*** 委外关系ID */
  	private String relId ; 
    
    /*** 创建时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date instDate ; 

    //构造函数
    public LoanOutsourceReturnDto(){}

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
	 * 获取 委外单位编码
	 * @return String
	 */
	public String getUnitNo() {
		return unitNo;
	}

	/**
	 * 设置 委外单位编码
	 * @param unitNo
	 */
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

    
    /**
	 * 获取 交易金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPayAmt() {
		return payAmt;
	}

	/**
	 * 设置 交易金额
	 * @param payAmt
	 */
	public void setPayAmt(java.math.BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

    
    /**
	 * 获取 交易类型
	 * @return String
	 */
	public String getPayTyp() {
		return payTyp;
	}

	/**
	 * 设置 交易类型
	 * @param payTyp
	 */
	public void setPayTyp(String payTyp) {
		this.payTyp = payTyp;
	}

    
    /**
	 * 获取 委外关系ID
	 * @return String
	 */
	public String getRelId() {
		return relId;
	}

	/**
	 * 设置 委外关系ID
	 * @param relId
	 */
	public void setRelId(String relId) {
		this.relId = relId;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}