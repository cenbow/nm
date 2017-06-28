package com.hs.loan.finance.entity;

import java.util.Date;

import java.io.Serializable;

/**
 * ACC_三方支付代扣信息 对象
 * 
 * @author autocreate
 * @create 2016-04-20
 */
public class AccPayWith implements Serializable {
	private static final long serialVersionUID = 1L;

	/*** ID */
	private String id;

	private String repayType;

	/*
	 * 还款类型 提前还款 50201001 提前结清 50201002 正常还款 50201003 失败重扣 50201004 催收扣款
	 * 50201005 催收提前还款 50201006 催收提前结清 50201007 委外扣款 50201008
	 */

	/*** 贷款编码 */
	private String loanNo;

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	/*** 开户账号 */
	private String acctNo;

	/*** 开户名 */
	private String acctName;

	/*** 开户银行 */
	private String openBank;

	/*** 证件号码 */
	private String certNo;

	/*** 交易金额 */
	private java.math.BigDecimal curRcvAmt;

	/*** 应还款金额 */
	private java.math.BigDecimal withAmt;

	public java.math.BigDecimal getWithAmt() {
		return withAmt;
	}

	public void setWithAmt(java.math.BigDecimal withAmt) {
		this.withAmt = withAmt;
	}

	/*** 开始期数 */
	private Integer bgnRepayNum;

	/*** 结束期数 */
	private Integer endRepayNum;

	/*** 状态(已处理、未处理、成功、失败、处理中) */
	private String stat;

	/*** 返回代码 */
	private String retCode;

	/*** 返回消息 */
	private String retMsg;

	/*** 备注 */
	private String remark;

	/*** 创建日期 */
	private Date instDate;

	/*** 更新日期 */
	private Date updtDate;

	/*** 所属公司 */
	private String companyType;
	
	private String noAgree;
	
	private String phoneNo;

	// 构造函数
	public AccPayWith() {
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
	 * 获取 贷款编码
	 * 
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 贷款编码
	 * 
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	/**
	 * 获取 开户账号
	 * 
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 开户账号
	 * 
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	/**
	 * 获取 开户名
	 * 
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 开户名
	 * 
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	/**
	 * 获取 开户银行
	 * 
	 * @return String
	 */
	public String getOpenBank() {
		return openBank;
	}

	/**
	 * 设置 开户银行
	 * 
	 * @param openBank
	 */
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
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

	public java.math.BigDecimal getCurRcvAmt() {
		return curRcvAmt;
	}

	public void setCurRcvAmt(java.math.BigDecimal curRcvAmt) {
		this.curRcvAmt = curRcvAmt;
	}

	/**
	 * 获取 开始期数
	 * 
	 * @return Integer
	 */
	public Integer getBgnRepayNum() {
		return bgnRepayNum;
	}

	/**
	 * 设置 开始期数
	 * 
	 * @param bgnRepayNum
	 */
	public void setBgnRepayNum(Integer bgnRepayNum) {
		this.bgnRepayNum = bgnRepayNum;
	}

	/**
	 * 获取 结束期数
	 * 
	 * @return Integer
	 */
	public Integer getEndRepayNum() {
		return endRepayNum;
	}

	/**
	 * 设置 结束期数
	 * 
	 * @param endRepayNum
	 */
	public void setEndRepayNum(Integer endRepayNum) {
		this.endRepayNum = endRepayNum;
	}

	/**
	 * 获取 状态(已处理、未处理、成功、失败、处理中)
	 * 
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 状态(已处理、未处理、成功、失败、处理中)
	 * 
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * 获取 返回代码
	 * 
	 * @return String
	 */
	public String getRetCode() {
		return retCode;
	}

	/**
	 * 设置 返回代码
	 * 
	 * @param retCode
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	/**
	 * 获取 返回消息
	 * 
	 * @return String
	 */
	public String getRetMsg() {
		return retMsg;
	}

	/**
	 * 设置 返回消息
	 * 
	 * @param retMsg
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	/**
	 * 获取 备注
	 * 
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 创建日期
	 * 
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建日期
	 * 
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	/**
	 * 获取 更新日期
	 * 
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新日期
	 * 
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getNoAgree() {
		return noAgree;
	}

	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}