package com.hs.loan.busi.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期费用项  对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanFeeDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 费用编号 */
  	@NotBlank(message="费用编号不能为空")
  	@Size(max=40,message="费用编号超长")
  	private String feeNo ; 
    
    /*** 费用名称 */
  	@NotBlank(message="费用名称不能为空")
  	@Size(max=80,message="费用名称超长")
  	private String feeName ; 
    
    /*** 费用项值 */
  	@NotBlank(message="费用项值不能为空")
  	@Size(max=10,message="费用项值超长")
  	private String feeVal ; 
    
    /*** 优先级 */
  	@NotBlank(message="优先级不能为空")
  	@Size(max=40,message="优先级超长")
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

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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