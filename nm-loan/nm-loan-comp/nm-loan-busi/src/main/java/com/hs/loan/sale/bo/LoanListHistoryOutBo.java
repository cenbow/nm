package com.hs.loan.sale.bo;


import java.io.Serializable;
import java.util.Date;

/**
 * 历史申请纪录
 * @author mac
 *
 */
public class LoanListHistoryOutBo implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
  	
  	/*** 客户姓名 */
  	private String custName ; 
  	
  	/*** 申请日期 */
  	private Date applyDate ; 
  	
  	/*** 客户证件号码 */
  	private String certNo;
  	
  	/*** 分期状态 */
  	private String stat ;

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	} 
  	
  	
	
}