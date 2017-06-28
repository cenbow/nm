package com.hs.loan.acct.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_费用项配置 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubRepayFeeConf implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 费用编号 */
  	private String feeNo ; 
    
    /*** 费用名称 */
  	private String feeName ; 
    
    /*** 科目号 */
  	private String subjNo ; 
    
    /*** 费用类型 */
  	private String feeTyp ; 
    
    /*** 费用规则 */
  	private String feeCalc ; 
    
    /*** 启用标志 */
  	private String validFlag ; 
    
    /*** 备注 */
  	private String feeRemark ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 
  	
  	/***  */
  	 public String feeMode; 

    //构造函数
    public PubRepayFeeConf(){}

    //getter和setter方法
    
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
	 * 获取 科目号
	 * @return String
	 */
	public String getSubjNo() {
		return subjNo;
	}

	/**
	 * 设置 科目号
	 * @param subjNo
	 */
	public void setSubjNo(String subjNo) {
		this.subjNo = subjNo;
	}

    
    /**
	 * 获取 费用类型
	 * @return String
	 */
	public String getFeeTyp() {
		return feeTyp;
	}

	/**
	 * 设置 费用类型
	 * @param feeTyp
	 */
	public void setFeeTyp(String feeTyp) {
		this.feeTyp = feeTyp;
	}

    
    /**
	 * 获取 费用规则
	 * @return String
	 */
	public String getFeeCalc() {
		return feeCalc;
	}

	/**
	 * 设置 费用规则
	 * @param feeCalc
	 */
	public void setFeeCalc(String feeCalc) {
		this.feeCalc = feeCalc;
	}

    
    /**
	 * 获取 启用标志
	 * @return String
	 */
	public String getValidFlag() {
		return validFlag;
	}

	/**
	 * 设置 启用标志
	 * @param validFlag
	 */
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getFeeRemark() {
		return feeRemark;
	}

	/**
	 * 设置 备注
	 * @param feeRemark
	 */
	public void setFeeRemark(String feeRemark) {
		this.feeRemark = feeRemark;
	}

    
    /**
	 * 获取 创建日期
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建日期
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新日期
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新日期
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getFeeMode() {
		return feeMode;
	}

	public void setFeeMode(String feeMode) {
		this.feeMode = feeMode;
	}
}