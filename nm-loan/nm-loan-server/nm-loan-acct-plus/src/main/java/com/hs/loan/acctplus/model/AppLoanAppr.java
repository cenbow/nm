package com.hs.loan.acctplus.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * APP_贷款审批案件
 *
 */
@TableName(value = "app_loan_appr")
public class AppLoanAppr implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 案件ID */
	@TableId(value = "APPR_ID", type = IdType.UUID)
	private String apprId;

	/** 贷款编号 */
	@TableField(value = "LOAN_NO")
	private String loanNo;

	/** 客户编号 */
	@TableField(value = "CUST_NO")
	private String custNo;

	/** 客户名称 */
	@TableField(value = "CUST_NAME")
	private String custName;

	/** 销售编号 */
	@TableField(value = "SALE_NO")
	private String saleNo;

	/** 销售名称 */
	@TableField(value = "SALE_NAME")
	private String saleName;

	/** 进入时间 */
	@TableField(value = "INST_DATE")
	private Date instDate;

	/** 进件次数 */
	@TableField(value = "INST_NUM")
	private Integer instNum;

	/** 状态 */
	private String stat;

	/** 自动审批开始时间 */
	@TableField(value = "AUTO_START_DATE")
	private Date autoStartDate;

	/** 自动审批结束时间 */
	@TableField(value = "AUTO_END_DATE")
	private Date autoEndDate;

	/** 人工审批开始时间 */
	@TableField(value = "MANU_START_DATE")
	private Date manuStartDate;

	/** 人工审批结束时间 */
	@TableField(value = "MANU_END_DATE")
	private Date manuEndDate;

	/** 审批员编号 */
	@TableField(value = "APPR_NO")
	private String apprNo;

	/** 审批员姓名 */
	@TableField(value = "APPR_NAME")
	private String apprName;

	/** 是否强制人工审批 */
	@TableField(value = "MANU_FLAG")
	private String manuFlag;

	/** 审批组编号 */
	@TableField(value = "APPR_GROUP_NO")
	private String apprGroupNo;

	/** 审批组名称 */
	@TableField(value = "APPR_GROUP_NAME")
	private String apprGroupName;

	/** 审批说明 */
	@TableField(value = "APPR_DESC")
	private String apprDesc;

	/** 备注 */
	private String remark;

	/** 审批类型 */
	@TableField(value = "APPR_TYP")
	private String apprTyp;

	/** 案件进入时间 */
	@TableField(value = "ENTR_DATE")
	private Date entrDate;

	/**  */
	private String remark2;

	public String getApprId() {
		return this.apprId;
	}

	public void setApprId(String apprId) {
		this.apprId = apprId;
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

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSaleNo() {
		return this.saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public String getSaleName() {
		return this.saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public Date getInstDate() {
		return this.instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	public Integer getInstNum() {
		return this.instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	public String getStat() {
		return this.stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Date getAutoStartDate() {
		return this.autoStartDate;
	}

	public void setAutoStartDate(Date autoStartDate) {
		this.autoStartDate = autoStartDate;
	}

	public Date getAutoEndDate() {
		return this.autoEndDate;
	}

	public void setAutoEndDate(Date autoEndDate) {
		this.autoEndDate = autoEndDate;
	}

	public Date getManuStartDate() {
		return this.manuStartDate;
	}

	public void setManuStartDate(Date manuStartDate) {
		this.manuStartDate = manuStartDate;
	}

	public Date getManuEndDate() {
		return this.manuEndDate;
	}

	public void setManuEndDate(Date manuEndDate) {
		this.manuEndDate = manuEndDate;
	}

	public String getApprNo() {
		return this.apprNo;
	}

	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

	public String getApprName() {
		return this.apprName;
	}

	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	public String getManuFlag() {
		return this.manuFlag;
	}

	public void setManuFlag(String manuFlag) {
		this.manuFlag = manuFlag;
	}

	public String getApprGroupNo() {
		return this.apprGroupNo;
	}

	public void setApprGroupNo(String apprGroupNo) {
		this.apprGroupNo = apprGroupNo;
	}

	public String getApprGroupName() {
		return this.apprGroupName;
	}

	public void setApprGroupName(String apprGroupName) {
		this.apprGroupName = apprGroupName;
	}

	public String getApprDesc() {
		return this.apprDesc;
	}

	public void setApprDesc(String apprDesc) {
		this.apprDesc = apprDesc;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApprTyp() {
		return this.apprTyp;
	}

	public void setApprTyp(String apprTyp) {
		this.apprTyp = apprTyp;
	}

	public Date getEntrDate() {
		return this.entrDate;
	}

	public void setEntrDate(Date entrDate) {
		this.entrDate = entrDate;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	@Override
	public String toString() {
		return "AppLoanAppr [apprId=" + apprId + ", loanNo=" + loanNo
				+ ", custNo=" + custNo + ", custName=" + custName + ", saleNo="
				+ saleNo + ", saleName=" + saleName + ", instDate=" + instDate
				+ ", instNum=" + instNum + ", stat=" + stat
				+ ", autoStartDate=" + autoStartDate + ", autoEndDate="
				+ autoEndDate + ", manuStartDate=" + manuStartDate
				+ ", manuEndDate=" + manuEndDate + ", apprNo=" + apprNo
				+ ", apprName=" + apprName + ", manuFlag=" + manuFlag
				+ ", apprGroupNo=" + apprGroupNo + ", apprGroupName="
				+ apprGroupName + ", apprDesc=" + apprDesc + ", remark="
				+ remark + ", apprTyp=" + apprTyp + ", entrDate=" + entrDate
				+ ", remark2=" + remark2 + "]";
	}

}
