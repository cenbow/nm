package com.hs.loan.busi.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期产品信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanProdDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** ID */
  	private String id ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /*** 产品名称 */
  	@NotBlank(message="产品名称不能为空")
  	@Size(max=80,message="产品名称超长")
  	private String prodName ; 
    
    /*** 产品类型 */
  	@NotBlank(message="产品类型不能为空")
  	@Size(max=10,message="产品类型超长")
  	private String prodTyp ; 
  	
  	/*** 产品类型名称 */
  	private String prodTypName ; 
    
    /*** 还款方式 */
  	@NotBlank(message="还款方式不能为空")
  	@Size(max=32,message="还款方式超长")
  	private String repayKind ; 
  	
  	/*** 还款方式名称 */
  	private String repayKindName ; 
    
    /*** 利率 */
  	@NotBlank(message="产品利率不能为空")
  	@Size(max=12,message="产品利率超长")
  	private java.math.BigDecimal rat ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdTyp() {
		return prodTyp;
	}

	public void setProdTyp(String prodTyp) {
		this.prodTyp = prodTyp;
	}

	public String getProdTypName() {
		return prodTypName;
	}

	public void setProdTypName(String prodTypName) {
		this.prodTypName = prodTypName;
	}

	public String getRepayKind() {
		return repayKind;
	}

	public void setRepayKind(String repayKind) {
		this.repayKind = repayKind;
	}

	public String getRepayKindName() {
		return repayKindName;
	}

	public void setRepayKindName(String repayKindName) {
		this.repayKindName = repayKindName;
	}

	public java.math.BigDecimal getRat() {
		return rat;
	}

	public void setRat(java.math.BigDecimal rat) {
		this.rat = rat;
	} 
  	
}