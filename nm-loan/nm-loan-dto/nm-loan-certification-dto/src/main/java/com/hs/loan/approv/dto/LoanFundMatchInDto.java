package com.hs.loan.approv.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期资金匹配 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class LoanFundMatchInDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    /*** 渠道号 */
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	private String chanName ; 

    //构造函数
    public LoanFundMatchInDto(){}

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

	public String getChanNo() {
		return chanNo;
	}

	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

	public String getChanName() {
		return chanName;
	}

	public void setChanName(String chanName) {
		this.chanName = chanName;
	}

}