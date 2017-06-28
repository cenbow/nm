package com.hs.loan.collection.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_费用减免 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class AccLoanBreaksFlowDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 减免类型 */
  	private String breaksTyp ; 
    
    /*** 减免金额 */
  	private java.math.BigDecimal breaksAmt ; 
    
    /*** 是否入账 */
  	private String isPost ; 
    
    /*** 减免人员编码 */
  	private String instStaffNo ; 
    
    /*** 减免人员姓名 */
  	private String instStaffName ; 
    
    /*** 减免时间 */
  	private Date instDate ; 

    //构造函数
    public AccLoanBreaksFlowDto(){}

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
	 * 获取 账单日
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 账单日
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 减免类型
	 * @return String
	 */
	public String getBreaksTyp() {
		return breaksTyp;
	}

	/**
	 * 设置 减免类型
	 * @param breaksTyp
	 */
	public void setBreaksTyp(String breaksTyp) {
		this.breaksTyp = breaksTyp;
	}

    
    /**
	 * 获取 减免金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getBreaksAmt() {
		return breaksAmt;
	}

	/**
	 * 设置 减免金额
	 * @param breaksAmt
	 */
	public void setBreaksAmt(java.math.BigDecimal breaksAmt) {
		this.breaksAmt = breaksAmt;
	}

    
    /**
	 * 获取 是否入账
	 * @return String
	 */
	public String getIsPost() {
		return isPost;
	}

	/**
	 * 设置 是否入账
	 * @param isPost
	 */
	public void setIsPost(String isPost) {
		this.isPost = isPost;
	}

    
    /**
	 * 获取 减免人员编码
	 * @return String
	 */
	public String getInstStaffNo() {
		return instStaffNo;
	}

	/**
	 * 设置 减免人员编码
	 * @param instStaffNo
	 */
	public void setInstStaffNo(String instStaffNo) {
		this.instStaffNo = instStaffNo;
	}

    
    /**
	 * 获取 减免人员姓名
	 * @return String
	 */
	public String getInstStaffName() {
		return instStaffName;
	}

	/**
	 * 设置 减免人员姓名
	 * @param instStaffName
	 */
	public void setInstStaffName(String instStaffName) {
		this.instStaffName = instStaffName;
	}

    
    /**
	 * 获取 减免时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 减免时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}