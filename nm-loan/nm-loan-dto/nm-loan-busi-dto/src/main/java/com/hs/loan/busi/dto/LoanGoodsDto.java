package com.hs.loan.busi.dto;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期商品信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanGoodsDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	 /*** ID */
  	private String id ; 
    
  	/*** 分期编号 */
  	@NotBlank(message="分期编号不能为空")
  	@Size(max=40,message="分期编号超长")
  	private String loanNo; 
  	
    /*** 商品类型 */
  	@NotBlank(message="商品类型不能为空")
  	@Size(max=40,message="商品类型超长")
  	private String goodsType ; 
  	
  	/*** 商品类型名*/
  	private String goodsTypeName ; 
    
    /*** 商品品牌 */
  	@NotBlank(message="商品品牌不能为空")
  	@Size(max=64,message="商品品牌超长")
  	private String brand ; 
    
    /*** 商品型号 */
  	@NotBlank(message="商品型号不能为空")
  	@Size(max=64,message="商品型号超长")
  	private String marques ; 
    
    /*** 价格 */
  	@NotNull(message="价格不能为空")
  	@Size(max=24,message="价格超长")
  	private java.math.BigDecimal pric ;

  	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMarques() {
		return marques;
	}

	public void setMarques(String marques) {
		this.marques = marques;
	}

	public java.math.BigDecimal getPric() {
		return pric;
	}

	public void setPric(java.math.BigDecimal pric) {
		this.pric = pric;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	} 
  	
}