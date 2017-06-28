package com.nm.cmd;

import java.io.Serializable;

/**
 * 客户其它联系信息
 * @author lenovo
 *
 */
public class CustOtherContctInfoCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private String id;
	private String custNo;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 关系
	 */
	private String contactRel;
	/**
	 * 联系电话
	 */
	private String contactTel;
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
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

}
