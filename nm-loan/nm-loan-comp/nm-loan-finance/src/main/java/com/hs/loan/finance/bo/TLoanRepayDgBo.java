package com.hs.loan.finance.bo;

import java.io.Serializable;

public class TLoanRepayDgBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tableKey;
	private String loanNo;
	private String repayDetailId;
	private String mthRepayDate;
	private Integer doRepayNum;
	private Integer planTotalNum;
	private String disRepayDate;
	private String repayTotalYh;
	private String custAccount;
	private String tranId;
	private java.util.Date tranTime;
	private String tranAmt;
	private String insertUserId;
	private String insertUserName;
	private java.util.Date insertTime;
	private String dgType;
	private String dgStatus;
	private java.util.Date recordTime;
	private String signNo;
	private String remark;
	private String voucherNo;
	private String channelType;

	private String custName;
	
	private String channelName;
	
	private String dgRepayType;
	
	
	
	
	
	public String getDgRepayType() {
		return dgRepayType;
	}
	public void setDgRepayType(String dgRepayType) {
		this.dgRepayType = dgRepayType;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getTableKey() {
		return tableKey;
	}
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getRepayDetailId() {
		return repayDetailId;
	}
	public void setRepayDetailId(String repayDetailId) {
		this.repayDetailId = repayDetailId;
	}
	public String getMthRepayDate() {
		return mthRepayDate;
	}
	public void setMthRepayDate(String mthRepayDate) {
		this.mthRepayDate = mthRepayDate;
	}
	public Integer getDoRepayNum() {
		return doRepayNum;
	}
	public void setDoRepayNum(Integer doRepayNum) {
		this.doRepayNum = doRepayNum;
	}
	public Integer getPlanTotalNum() {
		return planTotalNum;
	}
	public void setPlanTotalNum(Integer planTotalNum) {
		this.planTotalNum = planTotalNum;
	}
	public String getDisRepayDate() {
		return disRepayDate;
	}
	public void setDisRepayDate(String disRepayDate) {
		this.disRepayDate = disRepayDate;
	}
	public String getRepayTotalYh() {
		return repayTotalYh;
	}
	public void setRepayTotalYh(String repayTotalYh) {
		this.repayTotalYh = repayTotalYh;
	}
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public java.util.Date getTranTime() {
		return tranTime;
	}
	public void setTranTime(java.util.Date tranTime) {
		this.tranTime = tranTime;
	}
	public String getTranAmt() {
		return tranAmt;
	}
	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}
	public String getInsertUserId() {
		return insertUserId;
	}
	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}
	public String getInsertUserName() {
		return insertUserName;
	}
	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}
	public java.util.Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(java.util.Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getDgType() {
		return dgType;
	}
	public void setDgType(String dgType) {
		this.dgType = dgType;
	}
	public String getDgStatus() {
		return dgStatus;
	}
	public void setDgStatus(String dgStatus) {
		this.dgStatus = dgStatus;
	}
	public java.util.Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(java.util.Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getSignNo() {
		return signNo;
	}
	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return (TLoanRepayDgBo)super.clone();
	}
	
	
}