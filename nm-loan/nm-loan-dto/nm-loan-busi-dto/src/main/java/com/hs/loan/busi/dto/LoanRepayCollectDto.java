package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.util.Date;

/**
 * 分期汇总信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanRepayCollectDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	 /*** 分期编码 */
  	private String loanNo ; 
    
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
    
    /*** 插入时间 */
  	private Date instDate ;

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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

	public Date getInstDate() {
		return instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	} 

}