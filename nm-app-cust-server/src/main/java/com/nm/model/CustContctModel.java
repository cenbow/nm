package com.nm.model;

import java.io.Serializable;

/**
 * 客户联系信息
 * @author lenovo
 *
 */
public class CustContctModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String custNo;
	private String qq;
	private String imei;
	private String phoneNo;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

}
