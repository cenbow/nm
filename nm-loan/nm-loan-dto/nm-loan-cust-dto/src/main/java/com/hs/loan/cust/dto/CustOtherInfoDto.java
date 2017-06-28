package com.hs.loan.cust.dto;

import java.util.Date;

public class CustOtherInfoDto {
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 个人收入 */
  	private java.math.BigDecimal income ; 
    
    /*** 家庭收入 */
  	private java.math.BigDecimal famIncome ; 
    
    /*** 受教育程度 */
  	private String edu ; 
    
    /*** 是否办理过类似分期业务 */
  	private String isLoaned ; 
    
    /*** 办理过的话月还款额 */
  	private java.math.BigDecimal mthRepay ; 
    
    /*** 是否有效 */
  	private String isValid ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public CustOtherInfoDto(){}

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
	 * 获取 个人收入
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getIncome() {
		return income;
	}

	/**
	 * 设置 个人收入
	 * @param income
	 */
	public void setIncome(java.math.BigDecimal income) {
		this.income = income;
	}

    
    /**
	 * 获取 家庭收入
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFamIncome() {
		return famIncome;
	}

	/**
	 * 设置 家庭收入
	 * @param famIncome
	 */
	public void setFamIncome(java.math.BigDecimal famIncome) {
		this.famIncome = famIncome;
	}

    
    /**
	 * 获取 受教育程度
	 * @return String
	 */
	public String getEdu() {
		return edu;
	}

	/**
	 * 设置 受教育程度
	 * @param edu
	 */
	public void setEdu(String edu) {
		this.edu = edu;
	}

    
    /**
	 * 获取 是否办理过类似分期业务
	 * @return String
	 */
	public String getIsLoaned() {
		return isLoaned;
	}

	/**
	 * 设置 是否办理过类似分期业务
	 * @param isLoaned
	 */
	public void setIsLoaned(String isLoaned) {
		this.isLoaned = isLoaned;
	}

    
    /**
	 * 获取 办理过的话月还款额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getMthRepay() {
		return mthRepay;
	}

	/**
	 * 设置 办理过的话月还款额
	 * @param mthRepay
	 */
	public void setMthRepay(java.math.BigDecimal mthRepay) {
		this.mthRepay = mthRepay;
	}

    
    /**
	 * 获取 是否有效
	 * @return String
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * 设置 是否有效
	 * @param isValid
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
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
