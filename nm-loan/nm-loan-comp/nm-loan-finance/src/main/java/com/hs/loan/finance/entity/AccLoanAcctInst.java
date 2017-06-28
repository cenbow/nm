package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_分期预处理 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccLoanAcctInst implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 业务日期 */
  	private String busnDate ; 
    
    /*** 还款期数 */
  	private Integer repayNum ; 
    
    /*** 应收总额 */
  	private java.math.BigDecimal totlAmt ; 
    
    /*** 当日应收金额 */
  	private java.math.BigDecimal dayRcvAmt ; 
    
    /*** 当前应还金额 */
  	private java.math.BigDecimal curRcvAmt ; 
    
    /*** 资方应收总额 */
  	private java.math.BigDecimal fundTotlAmt ; 
    
    /*** 当日资方应收金额 */
  	private java.math.BigDecimal fundDayRcvAmt ; 
    
    /*** 当前资方应还金额 */
  	private java.math.BigDecimal fundCurRcvAmt ; 
    
    /*** 提前结清金额 */
  	private java.math.BigDecimal setlAmt ; 
    
    /*** 分期状态 */
  	private String instStat ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AccLoanAcctInst(){}

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

	public String getBusnDate() {
		return busnDate;
	}

	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
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

	public java.math.BigDecimal getDayRcvAmt() {
		return dayRcvAmt;
	}

	public void setDayRcvAmt(java.math.BigDecimal dayRcvAmt) {
		this.dayRcvAmt = dayRcvAmt;
	}

	public java.math.BigDecimal getCurRcvAmt() {
		return curRcvAmt;
	}

	public void setCurRcvAmt(java.math.BigDecimal curRcvAmt) {
		this.curRcvAmt = curRcvAmt;
	}

	public java.math.BigDecimal getFundTotlAmt() {
		return fundTotlAmt;
	}

	public void setFundTotlAmt(java.math.BigDecimal fundTotlAmt) {
		this.fundTotlAmt = fundTotlAmt;
	}

	public java.math.BigDecimal getFundDayRcvAmt() {
		return fundDayRcvAmt;
	}

	public void setFundDayRcvAmt(java.math.BigDecimal fundDayRcvAmt) {
		this.fundDayRcvAmt = fundDayRcvAmt;
	}

	public java.math.BigDecimal getFundCurRcvAmt() {
		return fundCurRcvAmt;
	}

	public void setFundCurRcvAmt(java.math.BigDecimal fundCurRcvAmt) {
		this.fundCurRcvAmt = fundCurRcvAmt;
	}

	public java.math.BigDecimal getSetlAmt() {
		return setlAmt;
	}

	public void setSetlAmt(java.math.BigDecimal setlAmt) {
		this.setlAmt = setlAmt;
	}

	

	public String getInstStat() {
		return instStat;
	}

	public void setInstStat(String instStat) {
		this.instStat = instStat;
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

    //getter和setter方法
    
   

}