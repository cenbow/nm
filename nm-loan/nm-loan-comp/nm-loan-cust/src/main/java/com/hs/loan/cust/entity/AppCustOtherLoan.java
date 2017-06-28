package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户其他分期信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustOtherLoan implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 信贷办理机构 */
  	private String openOrg ; 
    
    /*** 信贷分期金额 */
  	private String loanAmt ; 
  	
  	/*** 每月还款额 */
  	private java.math.BigDecimal mthAmt ; 
    
    /*** 信贷每月还款日 */
  	private String loanMthDay ; 
    
    /*** 信贷剩余期限(月) */
  	private String loanMonth ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 
  	
  	private Integer instNum;

    //构造函数
    public AppCustOtherLoan(){}

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
	 * 获取 信贷办理机构
	 * @return String
	 */
	public String getOpenOrg() {
		return openOrg;
	}

	/**
	 * 设置 信贷办理机构
	 * @param openOrg
	 */
	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

    
    /**
	 * 获取 信贷分期金额
	 * @return String
	 */
	public String getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 信贷分期金额
	 * @param loanAmt
	 */
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 信贷每月还款日
	 * @return String
	 */
	public String getLoanMthDay() {
		return loanMthDay;
	}

	/**
	 * 设置 信贷每月还款日
	 * @param loanMthDay
	 */
	public void setLoanMthDay(String loanMthDay) {
		this.loanMthDay = loanMthDay;
	}

    
    /**
	 * 获取 信贷剩余期限(月)
	 * @return String
	 */
	public String getLoanMonth() {
		return loanMonth;
	}

	/**
	 * 设置 信贷剩余期限(月)
	 * @param loanMonth
	 */
	public void setLoanMonth(String loanMonth) {
		this.loanMonth = loanMonth;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    /**
	 * 获取 开始日期
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public java.math.BigDecimal getMthAmt() {
		return mthAmt;
	}

	public void setMthAmt(java.math.BigDecimal mthAmt) {
		this.mthAmt = mthAmt;
	}

	public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}


}