package com.nm.cmd;

import java.io.Serializable;

/**
 * 用户风控报告参数
 * @author lenovo
 *
 */
public class UserReportInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 状态
	 */
	private String stat;
	/**
	 * 档案编号
	 */
	private String fileNo;
	/**
	 * 贷款编号
	 */
	private String loanNo;
	/**
	 * 客户编号
	 */
	private String custNo;
	/**
	 * 备注
	 */
	private String loanRemark;
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getLoanRemark() {
		return loanRemark;
	}
	public void setLoanRemark(String loanRemark) {
		this.loanRemark = loanRemark;
	}

}
