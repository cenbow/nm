package com.hs.loan.collection.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 逾期期数信息
 * 
 * @author zhangxiaoqiang
 *
 */
public class LoanOverduePeriodDetileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** id */
	private String planid;
	
	/*** 逾期期数 */
	private String repayNum;

	/*** 还款日期 */
	private String repayDate;

	/*** 逾期天数 */
	private String overdueNum;

	/*** 逾期金额 */
	private BigDecimal repayAmt;
	/**
	 * 滞纳金
	 */
	private BigDecimal overdueAmt;

	public LoanOverduePeriodDetileDto() {

	}

	public String getRepayNum() {
		return repayNum;
	}

	public void setRepayNum(String repayNum) {
		this.repayNum = repayNum;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getOverdueNum() {
		return overdueNum;
	}

	public void setOverdueNum(String overdueNum) {
		this.overdueNum = overdueNum;
	}

	public BigDecimal getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	public BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	public void setOverdueAmt(BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	 

}
