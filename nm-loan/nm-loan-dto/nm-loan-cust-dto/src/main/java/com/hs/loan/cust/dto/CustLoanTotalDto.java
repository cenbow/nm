package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * APP_客户分期信息汇总 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustLoanTotalDto implements Serializable{
	private static final long serialVersionUID = 3573249090715941962L;

	/*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(max=40,message="表主键超长")
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank (message="客户编号不能为空")
	@Size(max=32,message="客户编号超长")
  	private String custNo ; 
    
    /*** 分期次数 */
	@NotNull (message="分期次数不能为空")
	@Size(max=12,message="分期次数超长")
  	private java.math.BigDecimal loanCnt ; 
    
    /*** 分期总金额 */
	@NotNull (message="分期总金额 不能为空")
	@Size(max=12,message="分期总金额 超长")
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 在贷余额总额 */
	@NotNull (message="在贷余额总额不能为空")
	@Size(max=12,message="在贷余额总额超长")
  	private java.math.BigDecimal loanBal ; 
    
    /*** 最晚到期日期 */
	@NotBlank (message="最晚到期日期不能为空")
	@Size(max=8,message="最晚到期日期超长")
  	private String lastDate ; 
    
    /*** 创建时间 */
//	@NotNull (message="创建时间不能为空")
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 


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
	 * 获取 分期次数
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanCnt() {
		return loanCnt;
	}

	/**
	 * 设置 分期次数
	 * @param loanCnt
	 */
	public void setLoanCnt(java.math.BigDecimal loanCnt) {
		this.loanCnt = loanCnt;
	}

    
    /**
	 * 获取 分期总金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 分期总金额
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 在贷余额总额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanBal() {
		return loanBal;
	}

	/**
	 * 设置 在贷余额总额
	 * @param loanBal
	 */
	public void setLoanBal(java.math.BigDecimal loanBal) {
		this.loanBal = loanBal;
	}

    
    /**
	 * 获取 最晚到期日期
	 * @return String
	 */
	public String getLastDate() {
		return lastDate;
	}

	/**
	 * 设置 最晚到期日期
	 * @param lastDate
	 */
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
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