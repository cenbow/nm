package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期与费用项关系 对象
 * @author autocreate
 * @create 2015-10-29
 */
public class AppLoanFee implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 费用编号 */
  	private String feeNo ; 
    
    /*** 费用名称 */
  	private String feeName ; 
    
    /*** 费用项值 */
  	private String feeVal ; 
    
    /*** 优先级 */
  	private String setlPrior ; 
    
    /*** 是否客户选择 */
  	private String isChoose ; 
  	
  	/*** 是否允许客户选择 */
  	private String isSel ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 新增时间 */
  	private Date instDate ; 
    
    /*** 修改时间 */
  	private Date updtDate ; 

    //构造函数
    public AppLoanFee(){}

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
	 * 获取 费用编号
	 * @return String
	 */
	public String getFeeNo() {
		return feeNo;
	}

	/**
	 * 设置 费用编号
	 * @param feeNo
	 */
	public void setFeeNo(String feeNo) {
		this.feeNo = feeNo;
	}

    
    /**
	 * 获取 费用名称
	 * @return String
	 */
	public String getFeeName() {
		return feeName;
	}

	/**
	 * 设置 费用名称
	 * @param feeName
	 */
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

    
    /**
	 * 获取 费用项值
	 * @return String
	 */
	public String getFeeVal() {
		return feeVal;
	}

	/**
	 * 设置 费用项值
	 * @param feeVal
	 */
	public void setFeeVal(String feeVal) {
		this.feeVal = feeVal;
	}

    
    /**
	 * 获取 优先级
	 * @return String
	 */
	public String getSetlPrior() {
		return setlPrior;
	}

	/**
	 * 设置 优先级
	 * @param setlPrior
	 */
	public void setSetlPrior(String setlPrior) {
		this.setlPrior = setlPrior;
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
	 * 获取 新增时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 新增时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 修改时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(String isChoose) {
		this.isChoose = isChoose;
	}

	public String getIsSel() {
		return isSel;
	}

	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}

}