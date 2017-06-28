package com.nm.model;

import java.io.Serializable;

/**
 * 医美销售信息
 * @author lenovo
 *
 */
public class SalerInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String salerNo;
	private String salerName;
	private String orgNo;
	private String phone;
	private String logoAddr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalerNo() {
		return salerNo;
	}
	public void setSalerNo(String salerNo) {
		this.salerNo = salerNo;
	}
	public String getSalerName() {
		return salerName;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLogoAddr() {
		return logoAddr;
	}
	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}

}
