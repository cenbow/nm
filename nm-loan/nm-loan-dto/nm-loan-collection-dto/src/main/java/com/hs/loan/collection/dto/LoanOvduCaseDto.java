package com.hs.loan.collection.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * PL_逾期案件 对象
 * 
 * @author zhangxiaoqiang
 *
 */
public class LoanOvduCaseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*** 案件ID */
	private String id;

	/*** 分期编号 */
	private String loanNo;

	/*** 客户名称 */
	private String custName;

	/*** 证件号码 */
	private String certNo;

	/** 商户所在省名称 ****/
	private String branchProvName;

	/** 商户所在市名称 ****/
	private String branchCityName;

	/*** 逾期开始期数 */
	private Integer bgnNum;

	/*** 逾期结束期数 */
	private Integer endNum;

	/*** 逾期开始日期 */
	private String bgnDate;

	/*** 逾期结束日期 */
	private String endDate;

	/*** 逾期阶段(逾期等级) */
	private Integer ovduLev;

	/*** 逾期金额 */
	private java.math.BigDecimal repayAmt;

	/*** 滞纳金 */
	private java.math.BigDecimal ovduAmt;

	/*** 处理人 */
	private String staffNo;

	/*** 处理人姓名 */
	private String staffName;

	/*** 处理状态 */
	private String dealStat;

	/*** 处理时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date dealTime;

	/** 委外标记 ****/
	private String outFlag;
	
	/** 渠道 ****/
	private String chanleNo;
	
	/** 渠道名称 ****/
	private String chanleName;
	/** 催收备注 **/
	private String dealDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getBranchProvName() {
		return branchProvName;
	}

	public void setBranchProvName(String branchProvName) {
		this.branchProvName = branchProvName;
	}

	public String getBranchCityName() {
		return branchCityName;
	}

	public void setBranchCityName(String branchCityName) {
		this.branchCityName = branchCityName;
	}

	public Integer getBgnNum() {
		return bgnNum;
	}

	public void setBgnNum(Integer bgnNum) {
		this.bgnNum = bgnNum;
	}

	public Integer getEndNum() {
		return endNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	public String getBgnDate() {
		return bgnDate;
	}

	public void setBgnDate(String bgnDate) {
		this.bgnDate = bgnDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getOvduLev() {
		return ovduLev;
	}

	public void setOvduLev(Integer ovduLev) {
		this.ovduLev = ovduLev;
	}

	public java.math.BigDecimal getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(java.math.BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	public java.math.BigDecimal getOvduAmt() {
		return ovduAmt;
	}

	public void setOvduAmt(java.math.BigDecimal ovduAmt) {
		this.ovduAmt = ovduAmt;
	}
 
	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDealStat() {
		return dealStat;
	}

	public void setDealStat(String dealStat) {
		this.dealStat = dealStat;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getOutFlag() {
		return outFlag;
	}

	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}

	public String getChanleNo() {
		return chanleNo;
	}

	public void setChanleNo(String chanleNo) {
		this.chanleNo = chanleNo;
	}

	public String getChanleName() {
		return chanleName;
	}

	public void setChanleName(String chanleName) {
		this.chanleName = chanleName;
	}

	public String getDealDesc() {
		return dealDesc;
	}

	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

}
