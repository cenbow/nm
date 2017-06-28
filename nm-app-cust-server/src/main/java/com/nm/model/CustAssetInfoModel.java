package com.nm.model;

import java.io.Serializable;

/**
 * 客户资产信息
 * @author lenovo
 *
 */
public class CustAssetInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 收入来源
	 */
	private String incomeSrc;
	/**
	 * 发薪日
	 */
	private String incomeDay;
	/**
	 * 收入金额
	 */
	private String incomeAmt;
	/**
	 * 其它收入
	 */
	private String incomeOtherAmt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIncomeSrc() {
		return incomeSrc;
	}
	public void setIncomeSrc(String incomeSrc) {
		this.incomeSrc = incomeSrc;
	}
	public String getIncomeDay() {
		return incomeDay;
	}
	public void setIncomeDay(String incomeDay) {
		this.incomeDay = incomeDay;
	}
	public String getIncomeAmt() {
		return incomeAmt;
	}
	public void setIncomeAmt(String incomeAmt) {
		this.incomeAmt = incomeAmt;
	}
	public String getIncomeOtherAmt() {
		return incomeOtherAmt;
	}
	public void setIncomeOtherAmt(String incomeOtherAmt) {
		this.incomeOtherAmt = incomeOtherAmt;
	}

}
