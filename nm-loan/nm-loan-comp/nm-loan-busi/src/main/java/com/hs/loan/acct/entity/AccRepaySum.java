package com.hs.loan.acct.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_应收实收金额汇总 对象
 * @author autocreate
 * @create 2015-10-30
 */
public class AccRepaySum implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 应收总额 */
  	private java.math.BigDecimal recvblAmt ; 
    
    /*** 在贷余额 */
  	private java.math.BigDecimal loanBal ; 
    
    /*** 应收余额 */
  	private java.math.BigDecimal recvblBal ; 
    
    /*** 实收总额 */
  	private java.math.BigDecimal repayAmt ; 
    
    /*** 逾期总额 */
  	private java.math.BigDecimal overdueAmt ; 
    
    /*** 逾期本金 */
  	private java.math.BigDecimal overduePrin ; 
    
    /*** 逾期期数 */
  	private Integer overdueNum ; 
    
    /*** 还款计划开始日期 */
  	private String recvblBeginDate ; 
    
    /*** 还款计划结束日期 */
  	private String recvblEndDate ; 
    
    /*** 插入时间 */
  	private Date instDate ; 

    //构造函数
    public AccRepaySum(){}

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
	 * 获取 应收总额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRecvblAmt() {
		return recvblAmt;
	}

	/**
	 * 设置 应收总额
	 * @param recvblAmt
	 */
	public void setRecvblAmt(java.math.BigDecimal recvblAmt) {
		this.recvblAmt = recvblAmt;
	}

    
    /**
	 * 获取 在贷余额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanBal() {
		return loanBal;
	}

	/**
	 * 设置 在贷余额
	 * @param loanBal
	 */
	public void setLoanBal(java.math.BigDecimal loanBal) {
		this.loanBal = loanBal;
	}

    
    /**
	 * 获取 应收余额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRecvblBal() {
		return recvblBal;
	}

	/**
	 * 设置 应收余额
	 * @param recvblBal
	 */
	public void setRecvblBal(java.math.BigDecimal recvblBal) {
		this.recvblBal = recvblBal;
	}

    
    
    public java.math.BigDecimal getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(java.math.BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	/**
	 * 获取 逾期总额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	/**
	 * 设置 逾期总额
	 * @param overdueAmt
	 */
	public void setOverdueAmt(java.math.BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

    
    /**
	 * 获取 逾期本金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOverduePrin() {
		return overduePrin;
	}

	/**
	 * 设置 逾期本金
	 * @param overduePrin
	 */
	public void setOverduePrin(java.math.BigDecimal overduePrin) {
		this.overduePrin = overduePrin;
	}

    
    /**
	 * 获取 逾期期数
	 * @return Integer
	 */
	public Integer getOverdueNum() {
		return overdueNum;
	}

	/**
	 * 设置 逾期期数
	 * @param overdueNum
	 */
	public void setOverdueNum(Integer overdueNum) {
		this.overdueNum = overdueNum;
	}

    
    /**
	 * 获取 还款计划开始日期
	 * @return String
	 */
	public String getRecvblBeginDate() {
		return recvblBeginDate;
	}

	/**
	 * 设置 还款计划开始日期
	 * @param recvblBeginDate
	 */
	public void setRecvblBeginDate(String recvblBeginDate) {
		this.recvblBeginDate = recvblBeginDate;
	}

    
    /**
	 * 获取 还款计划结束日期
	 * @return String
	 */
	public String getRecvblEndDate() {
		return recvblEndDate;
	}

	/**
	 * 设置 还款计划结束日期
	 * @param recvblEndDate
	 */
	public void setRecvblEndDate(String recvblEndDate) {
		this.recvblEndDate = recvblEndDate;
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

}