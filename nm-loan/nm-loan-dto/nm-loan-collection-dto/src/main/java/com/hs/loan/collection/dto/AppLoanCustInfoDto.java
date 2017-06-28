package com.hs.loan.collection.dto;

import java.io.Serializable;

/**
 * 客户联系人信息
 * 
 * @author zhangxiaoqiang
 *
 */
public class AppLoanCustInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 客户编号 **/
	private String custNo;
	/** 电话号码 **/
	private String custText;
	/** 姓名 **/
	private String contactName;
	/** 备注 **/
	private String remark;
	/** 状态 **/
	private String stat;
	/** 与客户关系 **/
	private String contactRel;
	/** 最近拨打时间 **/
	private String callDate;
	/** 最近结果 **/
	private String handRestCode;
	

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustText() {
		return custText;
	}

	public void setCustText(String custText) {
		this.custText = custText;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getContactRel() {
		return contactRel;
	}

	public void setContactRel(String contactRel) {
		this.contactRel = contactRel;
	}

	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public String getHandRestCode() {
		return handRestCode;
	}

	public void setHandRestCode(String handRestCode) {
		this.handRestCode = handRestCode;
	}

}
