package com.hs.loan.acctplus.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * APP_贷款基本信息
 *
 */
@TableName(value = "app_loan_acct")
public class AppLoanAcct implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 贷款编号 */
	@TableId(value = "LOAN_NO", type = IdType.UUID)
	private String loanNo;

	/** 客户编号 */
	@TableField(value = "CUST_NO")
	private String custNo;

	/** 首付金额 */
	@TableField(value = "FST_PAY_AMT")
	private BigDecimal fstPayAmt;

	/** 利率 */
	@TableField(value = "INTER_RATE")
	private BigDecimal interRate;

	/** 贷款本金 */
	@TableField(value = "LOAN_AMT")
	private BigDecimal loanAmt;

	/** 贷款类型 */
	@TableField(value = "LOAN_TYP")
	private String loanTyp;

	/** 分期期数 */
	@TableField(value = "INST_NUM")
	private Integer instNum;

	/** 首次还款额 */
	@TableField(value = "FST_REPAY_AMT")
	private BigDecimal fstRepayAmt;

	/** 首次还款日 */
	@TableField(value = "FST_REPAY_DATE")
	private String fstRepayDate;

	/** 每月还款日 */
	@TableField(value = "MTH_REPAY_DATE")
	private String mthRepayDate;

	/** 月还款金额 */
	@TableField(value = "MTH_REPAY_AMT")
	private BigDecimal mthRepayAmt;

	/** 申请日期 */
	@TableField(value = "APPLY_DATE")
	private Date applyDate;

	/** 审批日期 */
	@TableField(value = "APRV_DATE")
	private Date aprvDate;

	/** 注册日期 */
	@TableField(value = "REG_DATE")
	private Date regDate;

	/** 放款日期 */
	@TableField(value = "DISTR_DATE")
	private Date distrDate;

	/** 文件类型 */
	@TableField(value = "FILE_NO")
	private String fileNo;

	/** 贷前状态 */
	private String stat;

	/** 贷后状态 */
	@TableField(value = "AFTER_STAT")
	private String afterStat;

	/** 五级分类 */
	@TableField(value = "FIV_CLS")
	private String fivCls;

	/** 办理所在省 */
	@TableField(value = "APPLY_PROV")
	private String applyProv;

	/** 办理所在区/县 */
	@TableField(value = "APPLY_AREA")
	private String applyArea;

	/** 办理所在市 */
	@TableField(value = "APPLY_CITY")
	private String applyCity;

	/** 客户名字 */
	@TableField(value = "CUST_NAME")
	private String custName;

	/**  */
	@TableField(value = "LOAN_REMARK")
	private String loanRemark;

	/**  */
	@TableField(value = "ENTR_FLAG")
	private String entrFlag;

	/**  */
	@TableField(value = "APPLY_ADDR")
	private String applyAddr;

	/**  */
	@TableField(value = "LOAN_USE")
	private String loanUse;

	/**  */
	@TableField(value = "CONTACT_ADDR")
	private String contactAddr;

	/**  */
	@TableField(value = "FEE_AMT")
	private BigDecimal feeAmt;

	/**  */
	@TableField(value = "SALE_CHANL")
	private String saleChanl;

	/**  */
	@TableField(value = "LOAN_STAT")
	private String loanStat;

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

	public BigDecimal getFstPayAmt() {
		return this.fstPayAmt;
	}

	public void setFstPayAmt(BigDecimal fstPayAmt) {
		this.fstPayAmt = fstPayAmt;
	}

	public BigDecimal getInterRate() {
		return this.interRate;
	}

	public void setInterRate(BigDecimal interRate) {
		this.interRate = interRate;
	}

	public BigDecimal getLoanAmt() {
		return this.loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	public String getLoanTyp() {
		return this.loanTyp;
	}

	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}

	public Integer getInstNum() {
		return this.instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	public BigDecimal getFstRepayAmt() {
		return this.fstRepayAmt;
	}

	public void setFstRepayAmt(BigDecimal fstRepayAmt) {
		this.fstRepayAmt = fstRepayAmt;
	}

	public String getFstRepayDate() {
		return this.fstRepayDate;
	}

	public void setFstRepayDate(String fstRepayDate) {
		this.fstRepayDate = fstRepayDate;
	}

	public String getMthRepayDate() {
		return this.mthRepayDate;
	}

	public void setMthRepayDate(String mthRepayDate) {
		this.mthRepayDate = mthRepayDate;
	}

	public BigDecimal getMthRepayAmt() {
		return this.mthRepayAmt;
	}

	public void setMthRepayAmt(BigDecimal mthRepayAmt) {
		this.mthRepayAmt = mthRepayAmt;
	}

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getAprvDate() {
		return this.aprvDate;
	}

	public void setAprvDate(Date aprvDate) {
		this.aprvDate = aprvDate;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getDistrDate() {
		return this.distrDate;
	}

	public void setDistrDate(Date distrDate) {
		this.distrDate = distrDate;
	}

	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getStat() {
		return this.stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getAfterStat() {
		return this.afterStat;
	}

	public void setAfterStat(String afterStat) {
		this.afterStat = afterStat;
	}

	public String getFivCls() {
		return this.fivCls;
	}

	public void setFivCls(String fivCls) {
		this.fivCls = fivCls;
	}

	public String getApplyProv() {
		return this.applyProv;
	}

	public void setApplyProv(String applyProv) {
		this.applyProv = applyProv;
	}

	public String getApplyArea() {
		return this.applyArea;
	}

	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}

	public String getApplyCity() {
		return this.applyCity;
	}

	public void setApplyCity(String applyCity) {
		this.applyCity = applyCity;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getLoanRemark() {
		return this.loanRemark;
	}

	public void setLoanRemark(String loanRemark) {
		this.loanRemark = loanRemark;
	}

	public String getEntrFlag() {
		return this.entrFlag;
	}

	public void setEntrFlag(String entrFlag) {
		this.entrFlag = entrFlag;
	}

	public String getApplyAddr() {
		return this.applyAddr;
	}

	public void setApplyAddr(String applyAddr) {
		this.applyAddr = applyAddr;
	}

	public String getLoanUse() {
		return this.loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	public String getContactAddr() {
		return this.contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public BigDecimal getFeeAmt() {
		return this.feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getSaleChanl() {
		return this.saleChanl;
	}

	public void setSaleChanl(String saleChanl) {
		this.saleChanl = saleChanl;
	}

	public String getLoanStat() {
		return this.loanStat;
	}

	public void setLoanStat(String loanStat) {
		this.loanStat = loanStat;
	}

}
