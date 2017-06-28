package com.hs.loan.collection.entity;

import java.io.Serializable;

/**
 * 贷款客户账户和银行信息
 * 
 * @author zhangxiaoqiang
 *
 */
public class AccCustAndBankBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String custNo;
	private String custType;
	private String custName;
	private String phoneNo;
	private String certNo;
	private String acctNo;
	private String acctName;
	private String bankNo;
	private String bankName;
	private String bankStat;
	private String noAgree;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankStat() {
		return bankStat;
	}

	public void setBankStat(String bankStat) {
		this.bankStat = bankStat;
	}

	public String getNoAgree() {
		return noAgree;
	}

	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}

}
