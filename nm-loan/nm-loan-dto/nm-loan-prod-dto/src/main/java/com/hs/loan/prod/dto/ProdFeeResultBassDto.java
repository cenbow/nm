package com.hs.loan.prod.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ProdFeeResultBassDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
    /*** ID */
  	private String id ; 
    
    /*** 费用编号 */
//  	@NotBlank(message="不能为空")
//  	@Size(max=0,message="超长")
  	private String feeNo ; 
    
    /*** 产品编号 */
//  	@NotBlank(message="不能为空")
//  	@Size(max=0,message="超长")
  	private String prodNo ; 
    
    /*** 费用费率 */
//  	@NotNull(message="不能为空")
//  	@Size(max=0,message="超长")
  	private java.math.BigDecimal feeRat ; 
    
    /*** 费用名称 */
//  	@NotBlank(message="不能为空")
//  	@Size(max=0,message="超长")
  	private String feeName ; 
    
    /*** 结算优先级 */
//  	@NotBlank(message="不能为空")
//  	@Size(max=0,message="超长")
  	private Integer setlPrior ; 
    
    /*** 分期金额 */
//  	@NotNull(message="不能为空")
//  	@Size(max=0,message="超长")
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 期数 */
//  	@NotNull(message="不能为空")
//  	@Size(max=0,message="超长")
  	private Integer instNum ; 
    
    /*** 费用金额 */
//  	@NotNull(message="不能为空")
//  	@Size(max=0,message="超长")
  	private java.math.BigDecimal feeAmt ;

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

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public java.math.BigDecimal getFeeRat() {
		return feeRat;
	}

	public void setFeeRat(java.math.BigDecimal feeRat) {
		this.feeRat = feeRat;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public Integer getSetlPrior() {
		return setlPrior;
	}

	public void setSetlPrior(Integer setlPrior) {
		this.setlPrior = setlPrior;
	}

	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	} 
}
