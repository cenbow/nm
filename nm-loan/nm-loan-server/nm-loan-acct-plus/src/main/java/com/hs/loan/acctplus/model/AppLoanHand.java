package com.hs.loan.acctplus.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * APP_贷款经办登记
 *
 */
@TableName(value = "app_loan_hand")
public class AppLoanHand implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId(value = "ID",type = IdType.UUID)
	private String id;

	/**  */
	@TableField(value = "CUST_NAME")
	private String custName;

	/** 贷款编号 */
	@TableField(value = "LOAN_NO")
	private String loanNo;

	/** 客户编号 */
	@TableField(value = "CUST_NO")
	private String custNo;

	/** 类型 */
	@TableField(value = "TYP")
	private String typ;

	/** 处理日期 */
	@TableField(value = "HAND_DATE")
	private Date handDate;

	/** 处理人 */
	@TableField(value = "HAND_PERSON_NO")
	private String handPersonNo;

	/** 处理人名字 */
	@TableField(value = "HAND_PERSON_NAME")
	private String handPersonName;

	/** 说明 */
	@TableField(value = "REMARK")
	private String remark;

	/** 处理内容 */
	@TableField(value = "HAND_DETAIL")
	private String handDetail;

	/** 插入时间 */
	@TableField(value = "INST_DATE")
	private Date instDate;

	/**  */
	@TableField(value = "CUST_IDENTIFIER")
	private String custIdentifier;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getLoanNo() {
		return this.loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Date getHandDate() {
		return this.handDate;
	}

	public void setHandDate(Date handDate) {
		this.handDate = handDate;
	}

	public String getHandPersonNo() {
		return this.handPersonNo;
	}

	public void setHandPersonNo(String handPersonNo) {
		this.handPersonNo = handPersonNo;
	}

	public String getHandPersonName() {
		return this.handPersonName;
	}

	public void setHandPersonName(String handPersonName) {
		this.handPersonName = handPersonName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHandDetail() {
		return this.handDetail;
	}

	public void setHandDetail(String handDetail) {
		this.handDetail = handDetail;
	}

	public Date getInstDate() {
		return this.instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	public String getCustIdentifier() {
		return this.custIdentifier;
	}

	public void setCustIdentifier(String custIdentifier) {
		this.custIdentifier = custIdentifier;
	}

	@Override
	public String toString() {
		return "AppLoanHand [id=" + id + ", custName=" + custName + ", loanNo="
				+ loanNo + ", custNo=" + custNo + ", typ=" + typ
				+ ", handDate=" + handDate + ", handPersonNo=" + handPersonNo
				+ ", handPersonName=" + handPersonName + ", remark=" + remark
				+ ", handDetail=" + handDetail + ", instDate=" + instDate
				+ ", custIdentifier=" + custIdentifier + "]";
	}

}
