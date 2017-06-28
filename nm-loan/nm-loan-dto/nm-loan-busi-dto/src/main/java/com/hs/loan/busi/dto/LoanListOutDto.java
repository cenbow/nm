package com.hs.loan.busi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * 分期列表信息 对象
 * 
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanListOutDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/*** 分期编号 */
	private String loanNo;

	/*** 客户编号 */
	private String custNo;

	/*** 客户姓名 */
	private String custName;

	/*** 销售姓名 */
	private String salerName;

	/*** 机构名 */
	private String orgName;

	/*** 网点名 */
	private String branchName;

	/*** 分期状态 */
	private String stat;
	/*** 提前结清金额 */
	private java.math.BigDecimal setlAmt;

	/*** 首付金额 */
	private java.math.BigDecimal fstPayAmt;

	/*** 分期本金 */
	private java.math.BigDecimal loanAmt;

	/*** 分期类型 */
	private String loanTyp;

	/*** 分期期数 */
	private Integer instNum;

	/*** 首次还款日 */
	private String fstRepayDate;

	/*** 每月还款日 */
	private String mthRepayDate;

	/*** 月还款金额 */
	private java.math.BigDecimal mthRepayAmt;

	/*** 申请日期 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date applyDate;

	/*** 审批日期 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date aprvDate;

	/*** 注册日期 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date regDate;

	/*** 放款日期 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date distrDate;

	/*** 办理所在省 */
	private String applyProv;

	/*** 办理所在区/县 */
	private String applyArea;

	/*** 办理所在市 */
	private String applyCity;

	/*** 应收总额 */
	private java.math.BigDecimal recvblAmt;

	/*** 在贷余额 */
	private java.math.BigDecimal loanBal;

	/*** 应收余额 */
	private java.math.BigDecimal recvblBal;

	/*** 实收总额 */
	private java.math.BigDecimal repayAmt;

	/*** 逾期总额 */
	private java.math.BigDecimal overdueAmt;

	/*** 逾期本金 */
	private java.math.BigDecimal overduePrin;

	/*** 逾期期数 */
	private Integer overdueNum;

	/*** 还款计划开始日期 */
	private String recvblBeginDate;

	/*** 还款计划结束日期 */
	private String recvblEndDate;

	/*** 合同url */
	private String contractUrl;

	/** 客户类型 **/
	private String custType;
	/** 客户证件号码 **/
	private String certNo;
	/** 档案代码 **/
	private String fileNo;
	/*** 备注 */
	private String loanRemark;
	/*** 信托标志 */
	private String entrFlag;
	/** 附件编号 */
	private String attNo;
	/* 資金渠道 */
	private String chanNo;
	/*** 贷后状态 */
	private String afterStat;
	private String phoneNum;

	private String authorBranchNo;
	
	private String authorStaffNo;
	
	private String apprResultId;
	
	private String apprResult;
	
	private String staffNo;
	
	private String loanState;
	
	private String saleChanl;
	
	private String approveFlag;
	
	private String workUnit;
	
	private String unitTel;
	
	private String goodsType;
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getUnitTel() {
		return unitTel;
	}

	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}

	public String getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}

	public String getAttNo() {
		return attNo;
	}

	public void setAttNo(String attNo) {
		this.attNo = attNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	// getter和setter方法

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * 获取 分期编号
	 * 
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * 
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	/**
	 * 获取 客户编号
	 * 
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * 
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	/**
	 * 获取 首付金额
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFstPayAmt() {
		return fstPayAmt;
	}

	/**
	 * 设置 首付金额
	 * 
	 * @param fstPayAmt
	 */
	public void setFstPayAmt(java.math.BigDecimal fstPayAmt) {
		this.fstPayAmt = fstPayAmt;
	}

	/**
	 * 获取 分期本金
	 * 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 分期本金
	 * 
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	/**
	 * 获取 分期类型
	 * 
	 * @return String
	 */
	public String getLoanTyp() {
		return loanTyp;
	}

	/**
	 * 设置 分期类型
	 * 
	 * @param loanTyp
	 */
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}

	/**
	 * 获取 分期期数
	 * 
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 分期期数
	 * 
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	/**
	 * 获取 办理所在省
	 * 
	 * @return String
	 */
	public String getApplyProv() {
		return applyProv;
	}

	/**
	 * 设置 办理所在省
	 * 
	 * @param applyProv
	 */
	public void setApplyProv(String applyProv) {
		this.applyProv = applyProv;
	}

	/**
	 * 获取 办理所在区/县
	 * 
	 * @return String
	 */
	public String getApplyArea() {
		return applyArea;
	}

	/**
	 * 设置 办理所在区/县
	 * 
	 * @param applyArea
	 */
	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}

	/**
	 * 获取 办理所在市
	 * 
	 * @return String
	 */
	public String getApplyCity() {
		return applyCity;
	}

	/**
	 * 设置 办理所在市
	 * 
	 * @param applyCity
	 */
	public void setApplyCity(String applyCity) {
		this.applyCity = applyCity;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getFstRepayDate() {
		return fstRepayDate;
	}

	public void setFstRepayDate(String fstRepayDate) {
		this.fstRepayDate = fstRepayDate;
	}

	public java.math.BigDecimal getRecvblAmt() {
		return recvblAmt;
	}

	public void setRecvblAmt(java.math.BigDecimal recvblAmt) {
		this.recvblAmt = recvblAmt;
	}

	public java.math.BigDecimal getLoanBal() {
		return loanBal;
	}

	public void setLoanBal(java.math.BigDecimal loanBal) {
		this.loanBal = loanBal;
	}

	public java.math.BigDecimal getRecvblBal() {
		return recvblBal;
	}

	public void setRecvblBal(java.math.BigDecimal recvblBal) {
		this.recvblBal = recvblBal;
	}

	public java.math.BigDecimal getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(java.math.BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	public java.math.BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	public void setOverdueAmt(java.math.BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	public java.math.BigDecimal getOverduePrin() {
		return overduePrin;
	}

	public void setOverduePrin(java.math.BigDecimal overduePrin) {
		this.overduePrin = overduePrin;
	}

	public Integer getOverdueNum() {
		return overdueNum;
	}

	public void setOverdueNum(Integer overdueNum) {
		this.overdueNum = overdueNum;
	}

	public String getRecvblBeginDate() {
		return recvblBeginDate;
	}

	public void setRecvblBeginDate(String recvblBeginDate) {
		this.recvblBeginDate = recvblBeginDate;
	}

	public String getRecvblEndDate() {
		return recvblEndDate;
	}

	public void setRecvblEndDate(String recvblEndDate) {
		this.recvblEndDate = recvblEndDate;
	}

	public String getMthRepayDate() {
		return mthRepayDate;
	}

	public void setMthRepayDate(String mthRepayDate) {
		this.mthRepayDate = mthRepayDate;
	}

	public java.math.BigDecimal getMthRepayAmt() {
		return mthRepayAmt;
	}

	public void setMthRepayAmt(java.math.BigDecimal mthRepayAmt) {
		this.mthRepayAmt = mthRepayAmt;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getAprvDate() {
		return aprvDate;
	}

	public void setAprvDate(Date aprvDate) {
		this.aprvDate = aprvDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getDistrDate() {
		return distrDate;
	}

	public void setDistrDate(Date distrDate) {
		this.distrDate = distrDate;
	}

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getLoanRemark() {
		return loanRemark;
	}

	public void setLoanRemark(String loanRemark) {
		this.loanRemark = loanRemark;
	}

	public String getChanNo() {
		return chanNo;
	}

	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

	public java.math.BigDecimal getSetlAmt() {
		return setlAmt;
	}

	public void setSetlAmt(java.math.BigDecimal setlAmt) {
		this.setlAmt = setlAmt;
	}

	public String getAfterStat() {
		return afterStat;
	}

	public void setAfterStat(String afterStat) {
		this.afterStat = afterStat;
	}

	public String getEntrFlag() {
		return entrFlag;
	}

	public void setEntrFlag(String entrFlag) {
		this.entrFlag = entrFlag;
	}

	public String getAuthorBranchNo() {
		return authorBranchNo;
	}

	public void setAuthorBranchNo(String authorBranchNo) {
		this.authorBranchNo = authorBranchNo;
	}

	public String getAuthorStaffNo() {
		return authorStaffNo;
	}

	public void setAuthorStaffNo(String authorStaffNo) {
		this.authorStaffNo = authorStaffNo;
	}

	public String getApprResultId() {
		return apprResultId;
	}

	public void setApprResultId(String apprResultId) {
		this.apprResultId = apprResultId;
	}

	public String getApprResult() 
	{
		return apprResult;
	}

	public void setApprResult(String apprResult) 
	{
		this.apprResult = apprResult;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getLoanState() {
		return loanState;
	}

	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}


	public String getSaleChanl() {
		return saleChanl;
	}

	public void setSaleChanl(String saleChanl) {
		this.saleChanl = saleChanl;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
}