package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_借贷流水 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccLoanDcFlow implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 流水编号 */
  	private String flowId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 科目编号 */
  	private String subjNo ; 
    
    /*** 账务日期 */
  	private String busiDate ; 
    
    /*** 历史账务日期 */
  	private String hisBusiDate ; 
    
    /*** 交易日期 */
  	private String txDate ; 
    
    /*** 交易金额 */
  	private java.math.BigDecimal txAmt ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 灵活还款包日期 */
  	private Date agiliDate ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 修改时间 */
  	private Date updtDate ; 

    //构造函数
    public AccLoanDcFlow(){}

    //getter和setter方法
    
    /**
	 * 获取 流水编号
	 * @return String
	 */
	public String getFlowId() {
		return flowId;
	}

	/**
	 * 设置 流水编号
	 * @param flowId
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
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
	 * 获取 科目编号
	 * @return String
	 */
	public String getSubjNo() {
		return subjNo;
	}

	/**
	 * 设置 科目编号
	 * @param subjNo
	 */
	public void setSubjNo(String subjNo) {
		this.subjNo = subjNo;
	}

    
    /**
	 * 获取 账务日期
	 * @return String
	 */
	public String getBusiDate() {
		return busiDate;
	}

	/**
	 * 设置 账务日期
	 * @param busiDate
	 */
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

    
    /**
	 * 获取 历史账务日期
	 * @return String
	 */
	public String getHisBusiDate() {
		return hisBusiDate;
	}

	/**
	 * 设置 历史账务日期
	 * @param hisBusiDate
	 */
	public void setHisBusiDate(String hisBusiDate) {
		this.hisBusiDate = hisBusiDate;
	}

    
    /**
	 * 获取 交易日期
	 * @return String
	 */
	public String getTxDate() {
		return txDate;
	}

	/**
	 * 设置 交易日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

    
    /**
	 * 获取 交易金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTxAmt() {
		return txAmt;
	}

	/**
	 * 设置 交易金额
	 * @param txAmt
	 */
	public void setTxAmt(java.math.BigDecimal txAmt) {
		this.txAmt = txAmt;
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
	 * 获取 灵活还款包日期
	 * @return Date
	 */
	public Date getAgiliDate() {
		return agiliDate;
	}

	/**
	 * 设置 灵活还款包日期
	 * @param agiliDate
	 */
	public void setAgiliDate(Date agiliDate) {
		this.agiliDate = agiliDate;
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

}