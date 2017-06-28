package com.nm.cmd;

import java.io.Serializable;

public class LoanFeeCmd implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*** ID */
  	private String id ;
    
    /*** 分期编号 */
  	private String loanNo ;
    
    /*** 费用编号 */
  	private String feeNo ;
    
    /*** 费用名称 */
  	private String feeName ;
    
    /*** 费用项值 */
  	private String feeVal ;
    
    /*** 优先级 */
  	private String setlPrior ;
    
    /*** 是否客户选择 */
  	private String isChoose ;
  	
  	/*** 是否允许客户选择 */
  	private String isSel ;
    
    /*** 状态 */
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

	public String getFeeNo() {
		return feeNo;
	}

	public void setFeeNo(String feeNo) {
		this.feeNo = feeNo;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getFeeVal() {
		return feeVal;
	}

	public void setFeeVal(String feeVal) {
		this.feeVal = feeVal;
	}

	public String getSetlPrior() {
		return setlPrior;
	}

	public void setSetlPrior(String setlPrior) {
		this.setlPrior = setlPrior;
	}

	public String getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(String isChoose) {
		this.isChoose = isChoose;
	}

	public String getIsSel() {
		return isSel;
	}

	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	} 
  	

}
