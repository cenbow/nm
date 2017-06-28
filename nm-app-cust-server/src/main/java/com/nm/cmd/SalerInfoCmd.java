package com.nm.cmd;

import java.io.Serializable;

public class SalerInfoCmd implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String salerNo;
	private String orgNo;
	private String salerName;
	public String getSalerNo() {
		return salerNo;
	}
	public void setSalerNo(String salerNo) {
		this.salerNo = salerNo;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getSalerName() {
		return salerName;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

}
