package com.hs.loan.finance.withpay.dto;

import java.io.Serializable;
import java.util.Date;

public class LoanRepayWithDto implements Serializable {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 7480589205927978877L;

	/*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 业务日期 */
  	private String busiDate ; 
    
    /*** 账务归属 */
  	private String acctFlag ; 
    
    /*** 渠道号 */
  	private String chanNo ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 扣款户名 */
  	private String acctName ; 
    
    /*** 扣款账号 */
  	private String acctNo ; 
    
    /*** 开户银行号 */
  	private String bankNo ; 
    
    /*** 开户银行 */
  	private String bankName ; 
    
    /*** 扣款状态 */
  	private String withStat ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
    /*** 还款期数 */
  	private Integer repayNum ; 
    
    /*** 应收总额 */
  	private java.math.BigDecimal totlAmt ; 
    
    /*** 当日应收金额 */
  	private java.math.BigDecimal dayShlAmt ; 
    
    /*** 当前应还金额 */
  	private java.math.BigDecimal currShlAmt ; 
    
    /*** 资方应收总额 */
  	private java.math.BigDecimal fundTotlAmt ; 
    
    /*** 当日资方应收金额 */
  	private java.math.BigDecimal fundDayShlAmt ; 
    
    /*** 当前资方应还金额 */
  	private java.math.BigDecimal fundCurrShlAmt ; 
    
    /*** 提前结清金额 */
  	private java.math.BigDecimal setlAmt ; 
    
    /*** 分期状态 */
  	private String stat ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getBusiDate() {
		return busiDate;
	}

	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

	public String getAcctFlag() {
		return acctFlag;
	}

	public void setAcctFlag(String acctFlag) {
		this.acctFlag = acctFlag;
	}

	public String getChanNo() {
		return chanNo;
	}

	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getWithStat() {
		return withStat;
	}

	public void setWithStat(String withStat) {
		this.withStat = withStat;
	}

	public Date getInstDate() {
		return instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	public Date getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public Integer getRepayNum() {
		return repayNum;
	}

	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

	public java.math.BigDecimal getTotlAmt() {
		return totlAmt;
	}

	public void setTotlAmt(java.math.BigDecimal totlAmt) {
		this.totlAmt = totlAmt;
	}

	public java.math.BigDecimal getDayShlAmt() {
		return dayShlAmt;
	}

	public void setDayShlAmt(java.math.BigDecimal dayShlAmt) {
		this.dayShlAmt = dayShlAmt;
	}

	public java.math.BigDecimal getCurrShlAmt() {
		return currShlAmt;
	}

	public void setCurrShlAmt(java.math.BigDecimal currShlAmt) {
		this.currShlAmt = currShlAmt;
	}

	public java.math.BigDecimal getFundTotlAmt() {
		return fundTotlAmt;
	}

	public void setFundTotlAmt(java.math.BigDecimal fundTotlAmt) {
		this.fundTotlAmt = fundTotlAmt;
	}

	public java.math.BigDecimal getFundDayShlAmt() {
		return fundDayShlAmt;
	}

	public void setFundDayShlAmt(java.math.BigDecimal fundDayShlAmt) {
		this.fundDayShlAmt = fundDayShlAmt;
	}

	public java.math.BigDecimal getFundCurrShlAmt() {
		return fundCurrShlAmt;
	}

	public void setFundCurrShlAmt(java.math.BigDecimal fundCurrShlAmt) {
		this.fundCurrShlAmt = fundCurrShlAmt;
	}

	public java.math.BigDecimal getSetlAmt() {
		return setlAmt;
	}

	public void setSetlAmt(java.math.BigDecimal setlAmt) {
		this.setlAmt = setlAmt;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	} 
}
