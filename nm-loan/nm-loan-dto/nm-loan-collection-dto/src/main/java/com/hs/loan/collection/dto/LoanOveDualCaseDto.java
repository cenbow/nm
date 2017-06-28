package com.hs.loan.collection.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanOveDualCaseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	/*** 案件ID */
	private String caseId;

	/*** 分期编号 */
	private String loanNo;

	/*** 证件号码 */
	private String certNo;

	/*** 客户编号 */
	private String custNo;

	/*** 客户名称 */
	private String custName;

	/*** 业务日期 */
	private String busnDate;
	
	/*** 还款日期 */
  	private String repayDate ; 

	/*** 逾期开始期数 */
	private Integer bgnNum;

	/*** 逾期结束期数 */
	private Integer endNum;

	/*** 逾期金额 */
	private java.math.BigDecimal ovduLoanAmt;

	/*** 本息金额 */
	private java.math.BigDecimal prinIntAmt;

	/*** 逾期本金 */
	private BigDecimal prinAmt;

	/*** 逾期利息 */
	private BigDecimal interAmt;

	/*** 滞纳金合计 */
	private BigDecimal overdueAmt;

	/*** 服务费合计 */
	private java.math.BigDecimal serviceAmt;

	/*** 账户管理费合计 */
	private java.math.BigDecimal accountMrgAmt;

	/*** 保险合计 */
	private java.math.BigDecimal insuranceAmt;

	/*** 随心包费用合计 */
	private java.math.BigDecimal packAmt;

	/*** 已还金额 */
	private java.math.BigDecimal paidAmt;

	/*** 剩余本金 */
	private BigDecimal remainPrin;

	/*** 剩余利息 */
	private BigDecimal remainInt;

	/*** 剩余服务费 */
	private BigDecimal remainSer;

	/*** 剩余账户管理费 */
	private BigDecimal remainAcc;

	/*** 剩余保险费 */
	private BigDecimal remainIns;

	/*** 剩余金额 */
	private java.math.BigDecimal remnAmt;

	/*** 剩余滞纳金 */
	private BigDecimal remainOver;

	/*** 处理状态 */
	private String dealStat;

	/** 产品及客户信息 **/
	private AppLoanAcctDto appLoanAcctDto;

	/**
	 * 获取 案件ID
	 * 
	 * @return String
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * 设置 案件ID
	 * 
	 * @param caseId
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	/**
	 * 获取 处理状态
	 * 
	 * @return String
	 */
	public String getDealStat() {
		return dealStat;
	}

	/**
	 * 设置 处理状态
	 * 
	 * @param dealStat
	 */
	public void setDealStat(String dealStat) {
		this.dealStat = dealStat;
	}

	/**
	 * 获取 分期编号
	 * 
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * 
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	/**
	 * 获取 证件号码
	 * 
	 * @return String
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * 设置 证件号码
	 * 
	 * @param certNo
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	/**
	 * 获取 客户编号
	 * 
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * 
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	/**
	 * 获取 客户名称
	 * 
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户名称
	 * 
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * 获取 业务日期
	 * 
	 * @return String
	 */
	public String getBusnDate() {
		return busnDate;
	}

	/**
	 * 设置 业务日期
	 * 
	 * @param busnDate
	 */
	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}

	/**
	 * 获取 逾期开始期数
	 * 
	 * @return Integer
	 */
	public Integer getBgnNum() {
		return bgnNum;
	}

	/**
	 * 设置 逾期开始期数
	 * 
	 * @param bgnNum
	 */
	public void setBgnNum(Integer bgnNum) {
		this.bgnNum = bgnNum;
	}

	/**
	 * 获取 逾期结束期数
	 * 
	 * @return Integer
	 */
	public Integer getEndNum() {
		return endNum;
	}

	/**
	 * 设置 逾期结束期数
	 * 
	 * @param endNum
	 */
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	/**
	 * 获取 逾期金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOvduLoanAmt() {
		return ovduLoanAmt;
	}

	/**
	 * 设置 逾期金额
	 * 
	 * @param ovduLoanAmt
	 */
	public void setOvduLoanAmt(java.math.BigDecimal ovduLoanAmt) {
		this.ovduLoanAmt = ovduLoanAmt;
	}

	/**
	 * 获取 本息金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrinIntAmt() {
		return prinIntAmt;
	}

	/**
	 * 设置 本息金额
	 * 
	 * @param prinIntAmt
	 */
	public void setPrinIntAmt(java.math.BigDecimal prinIntAmt) {
		this.prinIntAmt = prinIntAmt;
	}

	/**
	 * 获取 已还金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPaidAmt() {
		return paidAmt;
	}

	/**
	 * 设置 已还金额
	 * 
	 * @param paidAmt
	 */
	public void setPaidAmt(java.math.BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	/**
	 * 获取 剩余金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRemnAmt() {
		return remnAmt;
	}

	/**
	 * 设置 剩余金额
	 * 
	 * @param remnAmt
	 */
	public void setRemnAmt(java.math.BigDecimal remnAmt) {
		this.remnAmt = remnAmt;
	}

	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public BigDecimal getInterAmt() {
		return interAmt;
	}

	public void setInterAmt(BigDecimal interAmt) {
		this.interAmt = interAmt;
	}

	public BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	public void setOverdueAmt(BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	public java.math.BigDecimal getServiceAmt() {
		return serviceAmt;
	}

	public void setServiceAmt(java.math.BigDecimal serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

	public java.math.BigDecimal getAccountMrgAmt() {
		return accountMrgAmt;
	}

	/**
	 * 账户管理费
	 * 
	 * @param accountMrgAmt
	 */
	public void setAccountMrgAmt(java.math.BigDecimal accountMrgAmt) {
		this.accountMrgAmt = accountMrgAmt;
	}

	public java.math.BigDecimal getInsuranceAmt() {
		return insuranceAmt;
	}

	public void setInsuranceAmt(java.math.BigDecimal insuranceAmt) {
		this.insuranceAmt = insuranceAmt;
	}

	public java.math.BigDecimal getPackAmt() {
		return packAmt;
	}

	public void setPackAmt(java.math.BigDecimal packAmt) {
		this.packAmt = packAmt;
	}

	public BigDecimal getRemainPrin() {
		return remainPrin;
	}

	public void setRemainPrin(BigDecimal remainPrin) {
		this.remainPrin = remainPrin;
	}

	public BigDecimal getRemainInt() {
		return remainInt;
	}

	public void setRemainInt(BigDecimal remainInt) {
		this.remainInt = remainInt;
	}

	public BigDecimal getRemainSer() {
		return remainSer;
	}

	public void setRemainSer(BigDecimal remainSer) {
		this.remainSer = remainSer;
	}

	public BigDecimal getRemainAcc() {
		return remainAcc;
	}

	public void setRemainAcc(BigDecimal remainAcc) {
		this.remainAcc = remainAcc;
	}

	public BigDecimal getRemainIns() {
		return remainIns;
	}

	public void setRemainIns(BigDecimal remainIns) {
		this.remainIns = remainIns;
	}

	public BigDecimal getRemainOver() {
		return remainOver;
	}

	public void setRemainOver(BigDecimal remainOver) {
		this.remainOver = remainOver;
	}

	public AppLoanAcctDto getAppLoanAcctDto() {
		return appLoanAcctDto;
	}

	public void setAppLoanAcctDto(AppLoanAcctDto appLoanAcctDto) {
		this.appLoanAcctDto = appLoanAcctDto;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

}
