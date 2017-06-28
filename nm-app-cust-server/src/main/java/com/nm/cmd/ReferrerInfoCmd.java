package com.nm.cmd;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐人信息
 * @author lenovo
 *
 */
public class ReferrerInfoCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键ID
	private String id;
	//推荐人姓名
	private String referrerName;
	//推荐人电话
	private String referrerPhoneNo;
	//推荐码
	private String referrerNo;
	//推荐时间
	private Date referrerDate;
	//客户编号
	private String custNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReferrerName() {
		return referrerName;
	}
	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}
	public String getReferrerPhoneNo() {
		return referrerPhoneNo;
	}
	public void setReferrerPhoneNo(String referrerPhoneNo) {
		this.referrerPhoneNo = referrerPhoneNo;
	}
	public String getReferrerNo() {
		return referrerNo;
	}
	public void setReferrerNo(String referrerNo) {
		this.referrerNo = referrerNo;
	}
	public Date getReferrerDate() {
		return referrerDate;
	}
	public void setReferrerDate(Date referrerDate) {
		this.referrerDate = referrerDate;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

}
