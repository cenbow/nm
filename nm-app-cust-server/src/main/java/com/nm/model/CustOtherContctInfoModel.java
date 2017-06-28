package com.nm.model;

import java.io.Serializable;

public class CustOtherContctInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 关系
	 */
	private String contactRel;
	private String contactRelName;
	/**
	 * 联系电话
	 */
	private String contactTel;
	private String custNo;

	public String getContactRelName() {
		return contactRelName;
	}

	public void setContactRelName(String contactRelName) {
		this.contactRelName = contactRelName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactRel() {
		return contactRel;
	}
	public void setContactRel(String contactRel) {
		this.contactRel = contactRel;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

}
