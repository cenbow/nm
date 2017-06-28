package com.hs.loan.sale.bo;


import com.hs.loan.sale.entity.AppLoanAcct;

import java.io.Serializable;
import java.util.Date;

/**
 * 分期列表信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanListOutBo extends AppLoanAcct implements Serializable{
	private static final long serialVersionUID = 1L;
    
  	
  	/*** 销售姓名 */
  	private String salerName ; 
  	
  	/*** 机构名 */
  	private String orgName ; 
  	
  	/*** 网点名 */
  	private String branchName ;
  	
  	/*** 提前结清金额 */
  	private java.math.BigDecimal setlAmt ; 
  	
  	/*** 应收总额 */
  	private java.math.BigDecimal recvblAmt ; 
    
    /*** 在贷余额 */
  	private java.math.BigDecimal loanBal ; 
    
    /*** 应收余额 */
  	private java.math.BigDecimal recvblBal ; 
    
    /*** 实收总额 */
  	private java.math.BigDecimal repayAmt ; 
    
    /*** 逾期总额 */
  	private java.math.BigDecimal overdueAmt ; 
    
    /*** 逾期本金 */
  	private java.math.BigDecimal overduePrin ; 
    
    /*** 逾期期数 */
  	private Integer overdueNum ; 
    
    /*** 还款计划开始日期 */
  	private String recvblBeginDate ; 
    
    /*** 还款计划结束日期 */
  	private String recvblEndDate ; 
  	
  	/**合同URL**/
  	private String contractUrl; 
  	
  	/**客户类型**/
  	private String custType; 
  	/**客户证件号码**/
  	private String certNo; 
  	
  	private String attNo;

  	private String chanNo;
  	/***贷后状态*/
  	private String afterStat;

	private String phoneNum;

	private String authorBranchNo;
	
	private String authorStaffNo;
	
	private String apprResultId;
	
	private String apprResult;
	
	private String staffNo;
	
	private String saleChanl;
	private Date regDate;
	private String goodsType;
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
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

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
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

	public String getApprResult() {
		return apprResult;
	}

	public void setApprResult(String apprResult) {
		this.apprResult = apprResult;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	 
	public String getSaleChanl() {
		return saleChanl;
	}

	public void setSaleChanl(String saleChanl) {
		this.saleChanl = saleChanl;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
}