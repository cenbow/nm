package com.hs.loan.finance.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * ACC_还款计划 对象(费用减免)
 * 
 * @author autocreate
 * @create 2016-02-03
 */
public class AccLoanPlanBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 客户名称 */
	private String custName;

	/** 开户行名称 */
	private String bankName;
	/** 银行账户名称 */
	private String acctName;

	/** 银行卡号 */
	private String acctNo;

	/** 身份证号 */
	private String certNo;

	/*** 还款计划ID */
	private String id;

	/*** 分期编号 */
	private String loanNo;

	/*** 应还账单日 */
	private String repayDate;

	/*** 总期数 */
	private String busiDate;

	/*** 当前期数 */
	private Integer repayNum;

	/*** 还款金额 */
	private java.math.BigDecimal repayAmt;

	/*** 本金金额 */
	private java.math.BigDecimal prinAmt;

	/*** 利息金额 */
	private java.math.BigDecimal intAmt;

	/*** 滞纳金金额 */
	private java.math.BigDecimal ovduAmt;

	/*** 服务费 */
	private java.math.BigDecimal svcAmt;

	/*** 账户管理费 */
	private java.math.BigDecimal acctAmt;

	/*** 保险费 */
	private java.math.BigDecimal insuAmt;

	/*** 手续费 */
	private java.math.BigDecimal feeAmt;

	/*** 违约金 */
	private java.math.BigDecimal punAmt;

	/*** 提前还款违约金 */
	private java.math.BigDecimal outAmt;

	/*** 灵活还款包费 */
	private java.math.BigDecimal packAmt;

	/*** 还款总额 */
	private java.math.BigDecimal totalAmt;

	/*** 提前结清 */
	private java.math.BigDecimal setlAmt;
	/*** 当前应还总额 */
	private java.math.BigDecimal dayRcvAmt;
	/*** 未还 期数*/
	private java.lang.Integer unPaylNum;
	/*** 已还期数*/
	private java.lang.Integer rePayedlNum;

	/***  */
	private String ovduFlag;

	/*** 创建时间 */
	private Date instDate;

	/*** 更新时间 */
	private Date updtDate;

	// getter和setter方法

	/**
	 * 获取 还款计划ID
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 还款计划ID
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * 获取 应还账单日
	 * 
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 应还账单日
	 * 
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	/**
	 * 获取 总期数
	 * 
	 * @return String
	 */
	public String getBusiDate() {
		return busiDate;
	}

	/**
	 * 设置 总期数
	 * 
	 * @param busiDate
	 */
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

	/**
	 * 获取 当前期数
	 * 
	 * @return Integer
	 */
	public Integer getRepayNum() {
		return repayNum;
	}

	/**
	 * 设置 当前期数
	 * 
	 * @param repayNum
	 */
	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

	/**
	 * 获取 还款金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRepayAmt() {
		return repayAmt;
	}

	/**
	 * 设置 还款金额
	 * 
	 * @param repayAmt
	 */
	public void setRepayAmt(java.math.BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	/**
	 * 获取 本金金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrinAmt() {
		return prinAmt;
	}

	/**
	 * 设置 本金金额
	 * 
	 * @param prinAmt
	 */
	public void setPrinAmt(java.math.BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	/**
	 * 获取 利息金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getIntAmt() {
		return intAmt;
	}

	/**
	 * 设置 利息金额
	 * 
	 * @param intAmt
	 */
	public void setIntAmt(java.math.BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	/**
	 * 获取 滞纳金金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOvduAmt() {
		return ovduAmt;
	}

	/**
	 * 设置 滞纳金金额
	 * 
	 * @param ovduAmt
	 */
	public void setOvduAmt(java.math.BigDecimal ovduAmt) {
		this.ovduAmt = ovduAmt;
	}

	/**
	 * 获取 服务费
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getSvcAmt() {
		return svcAmt;
	}

	/**
	 * 设置 服务费
	 * 
	 * @param svcAmt
	 */
	public void setSvcAmt(java.math.BigDecimal svcAmt) {
		this.svcAmt = svcAmt;
	}

	 

	/**
	 * 获取 保险费
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getInsuAmt() {
		return insuAmt;
	}

	/**
	 * 设置 保险费
	 * 
	 * @param insuAmt
	 */
	public void setInsuAmt(java.math.BigDecimal insuAmt) {
		this.insuAmt = insuAmt;
	}

	/**
	 * 获取 手续费
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	/**
	 * 设置 手续费
	 * 
	 * @param feeAmt
	 */
	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	/**
	 * 获取 手续费
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPunAmt() {
		return punAmt;
	}

	/**
	 * 设置 手续费
	 * 
	 * @param funAmt
	 */
	public void setPunAmt(java.math.BigDecimal punAmt) {
		this.punAmt = punAmt;
	}

	/**
	 * 获取 提前还款违约金
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOutAmt() {
		return outAmt;
	}

	/**
	 * 设置 提前还款违约金
	 * 
	 * @param outAmt
	 */
	public void setOutAmt(java.math.BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	/**
	 * 获取 灵活还款包费
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPackAmt() {
		return packAmt;
	}

	/**
	 * 设置 灵活还款包费
	 * 
	 * @param packAmt
	 */
	public void setPackAmt(java.math.BigDecimal packAmt) {
		this.packAmt = packAmt;
	}

	/**
	 * 获取
	 * 
	 * @return String
	 */
	public String getOvduFlag() {
		return ovduFlag;
	}

	/**
	 * 设置
	 * 
	 * @param ovduFlag
	 */
	public void setOvduFlag(String ovduFlag) {
		this.ovduFlag = ovduFlag;
	}

	/**
	 * 获取 创建时间
	 * 
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * 
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	/**
	 * 获取 更新时间
	 * 
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * 
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public AccLoanPlanBo() {
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public java.math.BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(java.math.BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public java.math.BigDecimal getSetlAmt() {
		return setlAmt;
	}

	public void setSetlAmt(java.math.BigDecimal setlAmt) {
		this.setlAmt = setlAmt;
	}

	public java.lang.Integer getUnPaylNum() {
		return unPaylNum;
	}

	public void setUnPaylNum(java.lang.Integer unPaylNum) {
		this.unPaylNum = unPaylNum;
	}

	public java.lang.Integer getRePayedlNum() {
		return rePayedlNum;
	}

	public void setRePayedlNum(java.lang.Integer rePayedlNum) {
		this.rePayedlNum = rePayedlNum;
	}

	public java.math.BigDecimal getDayRcvAmt() {
		return dayRcvAmt;
	}

	public void setDayRcvAmt(java.math.BigDecimal dayRcvAmt) {
		this.dayRcvAmt = dayRcvAmt;
	}

	public java.math.BigDecimal getAcctAmt() {
		return acctAmt;
	}

	public void setAcctAmt(java.math.BigDecimal acctAmt) {
		this.acctAmt = acctAmt;
	}

}