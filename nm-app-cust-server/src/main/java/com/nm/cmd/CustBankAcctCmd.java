package com.nm.cmd;

import java.io.Serializable;

public class CustBankAcctCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String loanNo;
	private String custNo;
	/*** 账户号 */
  	private String acctNo ;
    
    /*** 账户名称 */
  	private String acctName ;
    
    /*** 开户机构 */
  	private String openOrg ;
    
    /*** 开户机构所在省 */
  	private String openProv ;
  	
  	private String openProvName;
    
    /*** 开户机构所在市 */
  	private String openCity ;
  	
  	private String openCityName;
	/*开户行*/
  	private String bankName;

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

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getOpenOrg() {
		return openOrg;
	}

	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

	public String getOpenProv() {
		return openProv;
	}

	public void setOpenProv(String openProv) {
		this.openProv = openProv;
	}

	public String getOpenProvName() {
		return openProvName;
	}

	public void setOpenProvName(String openProvName) {
		this.openProvName = openProvName;
	}

	public String getOpenCity() {
		return openCity;
	}

	public void setOpenCity(String openCity) {
		this.openCity = openCity;
	}

	public String getOpenCityName() {
		return openCityName;
	}

	public void setOpenCityName(String openCityName) {
		this.openCityName = openCityName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
