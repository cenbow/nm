package com.nm.model;

import java.io.Serializable;

public class CustBankAcctModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
    /*** 账户号 */
  	private String acctNo ; 
    
    /*** 账户名称 */
  	private String acctName ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 开户机构 */
  	private String openOrg ;
  	private String openOrgName;
    
    /*** 开户机构所在省 */
  	private String openProv ; 
  	
  	private String openProvName;
    
    /*** 开户机构所在市 */
  	private String openCity ; 
  	
  	private String openCityName;
	/*开户行*/
  	private String bankName;

	public String getOpenOrgName() {
		return openOrgName;
	}

	public void setOpenOrgName(String openOrgName) {
		this.openOrgName = openOrgName;
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

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
