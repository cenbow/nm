package com.nm.cmd;

import java.io.Serializable;

/**
 * 我的还款信息
 * @author zkj
 *
 */
public class RepayCmd implements Serializable{

	private static final long serialVersionUID = -319261359785150199L;
	private String loanNo ; /*贷款id*/
	private String custNo ; /*客户id*/
	private String loanAmt ; /*贷款金额*/
	private int instNum ; /* 期数：单位:月*/
	private String mthRepayAmt ; /*月还款*/
	private String repayDate ; /*还款日期*/
	private int curInstNum ; /* 当前期数：单位：月*/
	private int repayLoanDays ; /*还款剩余天数：负数表示逾期 */
	private String ovduAmt;/*逾期金额*/
	private String message;/*提示信息*/
	private String stateCode;/*错误码*/
	private String submitDate;
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOvduAmt() {
		return ovduAmt;
	}
	public void setOvduAmt(String ovduAmt) {
		this.ovduAmt = ovduAmt;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public int getInstNum() {
		return instNum;
	}
	public void setInstNum(int instNum) {
		this.instNum = instNum;
	}
	public String getMthRepayAmt() {
		return mthRepayAmt;
	}
	public void setMthRepayAmt(String mthRepayAmt) {
		this.mthRepayAmt = mthRepayAmt;
	}
	public int getCurInstNum() {
		return curInstNum;
	}
	public void setCurInstNum(int curInstNum) {
		this.curInstNum = curInstNum;
	}
	public int getRepayLoanDays() {
		return repayLoanDays;
	}
	public void setRepayLoanDays(int repayLoanDays) {
		this.repayLoanDays = repayLoanDays;
	}
	
}