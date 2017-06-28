package com.hs.loan.finance.bo;

import java.io.Serializable;

public class AccLoanAcctInstDgBo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String loanNo;		//贷款编号
	private String repayDate;	//账单日
	private String busiDate;	//业务日期
	private int repayNum;		//分期期数
	private int totlAmt;		//应还总金额
	private int dayShlAmt;		//当日应还金额
	private int currShlAmt;		//当前应还金额
	private int fundTotlAmt;	//资方总额
	private int fundDayShlAmt;	//当日资方余额
	private int fundCurrShlAmt;	//当前资方余额
	private int setlAmt;		//提前结清金额	
	private String stat;		//状态（是-已锁定/否-未锁定)
	private String custNo;		//客户编号		
	private String custName;	//客户名称		
	private String certType;	//证件类型
	private String certNo;		//证件号码
	private String custAccount;	//客户账户号
	private String custAccountName;//客户账户户名
	private String custOpenOrg;	//客户账户开户行编码
	private String channel;		//渠道

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
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public String getBusiDate() {
		return busiDate;
	}
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}
	public int getRepayNum() {
		return repayNum;
	}
	public void setRepayNum(int repayNum) {
		this.repayNum = repayNum;
	}
	public int getTotlAmt() {
		return totlAmt;
	}
	public void setTotlAmt(int totlAmt) {
		this.totlAmt = totlAmt;
	}
	public int getDayShlAmt() {
		return dayShlAmt;
	}
	public void setDayShlAmt(int dayShlAmt) {
		this.dayShlAmt = dayShlAmt;
	}
	public int getCurrShlAmt() {
		return currShlAmt;
	}
	public void setCurrShlAmt(int currShlAmt) {
		this.currShlAmt = currShlAmt;
	}
	public int getFundTotlAmt() {
		return fundTotlAmt;
	}
	public void setFundTotlAmt(int fundTotlAmt) {
		this.fundTotlAmt = fundTotlAmt;
	}
	public int getFundDayShlAmt() {
		return fundDayShlAmt;
	}
	public void setFundDayShlAmt(int fundDayShlAmt) {
		this.fundDayShlAmt = fundDayShlAmt;
	}
	public int getFundCurrShlAmt() {
		return fundCurrShlAmt;
	}
	public void setFundCurrShlAmt(int fundCurrShlAmt) {
		this.fundCurrShlAmt = fundCurrShlAmt;
	}
	public int getSetlAmt() {
		return setlAmt;
	}
	public void setSetlAmt(int setlAmt) {
		this.setlAmt = setlAmt;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}
	public String getCustAccountName() {
		return custAccountName;
	}
	public void setCustAccountName(String custAccountName) {
		this.custAccountName = custAccountName;
	}
	public String getCustOpenOrg() {
		return custOpenOrg;
	}
	public void setCustOpenOrg(String custOpenOrg) {
		this.custOpenOrg = custOpenOrg;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}