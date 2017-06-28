package com.hs.loan.prod.entity;

import java.io.Serializable;

/**
 * PUB_产品与费用项关系 对象
 * 
 * @author autocreate
 * @create 2015-10-15
 */
public class PubProdFee implements Serializable {
	private static final long serialVersionUID = 1L;

	/*** ID */
	private String id;

	/*** 费用编号 */
	private String feeNo;

	/*** 产品编号 */
	private String prodNo;

	/*** 费用名称 */
	private String feeName;

	/*** 费用项值 */
	private String feeVal;

	/*** 是否客户选择 */
	private String isSel;

	/*** 结算优先级 */
	private Integer setlPrior;
	/*** 期数 */
	private Integer instNum;
	/*** 首付比例类型 */
	private String fstPayTyp;

	/*** 首付比例 */
	private java.math.BigDecimal fstPayVal;

	// 构造函数
	public PubProdFee() {
	}

	// getter和setter方法

	/**
	 * 获取 ID
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 ID
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 费用编号
	 * 
	 * @return String
	 */
	public String getFeeNo() {
		return feeNo;
	}

	/**
	 * 设置 费用编号
	 * 
	 * @param feeNo
	 */
	public void setFeeNo(String feeNo) {
		this.feeNo = feeNo;
	}

	/**
	 * 获取 产品编号
	 * 
	 * @return String
	 */
	public String getProdNo() {
		return prodNo;
	}

	/**
	 * 设置 产品编号
	 * 
	 * @param prodNo
	 */
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	/**
	 * 获取 费用名称
	 * 
	 * @return String
	 */
	public String getFeeName() {
		return feeName;
	}

	/**
	 * 设置 费用名称
	 * 
	 * @param feeName
	 */
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	/**
	 * 获取 费用项值
	 * 
	 * @return String
	 */
	public String getFeeVal() {
		return feeVal;
	}

	/**
	 * 设置 费用项值
	 * 
	 * @param feeVal
	 */
	public void setFeeVal(String feeVal) {
		this.feeVal = feeVal;
	}

	/**
	 * 获取 是否客户选择
	 * 
	 * @return String
	 */
	public String getIsSel() {
		return isSel;
	}

	/**
	 * 设置 是否客户选择
	 * 
	 * @param isSel
	 */
	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}

	/**
	 * 获取 结算优先级
	 * 
	 * @return Integer
	 */
	public Integer getSetlPrior() {
		return setlPrior;
	}

	/**
	 * 设置 结算优先级
	 * 
	 * @param setlPrior
	 */
	public void setSetlPrior(Integer setlPrior) {
		this.setlPrior = setlPrior;
	}

	public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	public String getFstPayTyp() {
		return fstPayTyp;
	}

	public void setFstPayTyp(String fstPayTyp) {
		this.fstPayTyp = fstPayTyp;
	}

	public java.math.BigDecimal getFstPayVal() {
		return fstPayVal;
	}

	public void setFstPayVal(java.math.BigDecimal fstPayVal) {
		this.fstPayVal = fstPayVal;
	}

}