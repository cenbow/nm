package com.nm.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客户其它贷款信息
 * @author lenovo
 *
 */
public class CustOtherLoanModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 贷款办理机构
	 */
	private String openOrg;
	/**
	 * 贷款金额
	 */
	private String loanAmt;
	/**
	 * 每月还款日
	 */
	private String loanMthDay;
	/**
	 * 每月还款金额
	 */
	private BigDecimal mthAmt;
	/**
	 * 剩余期数
	 * @return
	 */
	private String loanMonth;
	/**
	 * 贷款总期数
	 * @return
	 */
	private Integer instNum;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpenOrg() {
		return openOrg;
	}
	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanMthDay() {
		return loanMthDay;
	}
	public void setLoanMthDay(String loanMthDay) {
		this.loanMthDay = loanMthDay;
	}
	public BigDecimal getMthAmt() {
		return mthAmt;
	}
	public void setMthAmt(BigDecimal mthAmt) {
		this.mthAmt = mthAmt;
	}
	public String getLoanMonth() {
		return loanMonth;
	}
	public void setLoanMonth(String loanMonth) {
		this.loanMonth = loanMonth;
	}
	public Integer getInstNum() {
		return instNum;
	}
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

}
