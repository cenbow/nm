package com.hs.loan.finance.bo;

import java.io.Serializable;

import com.hs.loan.finance.entity.AccLoanPlanInit;

/**
 * ACC_还款计划（初始） 对象
 * 
 * @author autocreate
 * @create 2016-02-03
 */
public class AccLoanPlanInitBo extends AccLoanPlanInit implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 客户名称 */
	private String acctName;
	/** 开户行名称 */
	private String bankName;
	/** 银行卡号 */
	private String acctNo;
	/** 客户姓名 */
	private String custName;
	/** 客户身份证号 */
	private String custNo;

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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

}
