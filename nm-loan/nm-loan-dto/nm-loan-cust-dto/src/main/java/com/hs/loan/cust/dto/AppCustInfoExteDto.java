package com.hs.loan.cust.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * APP_客户信息拓展表 对象
 * 
 * @author autocreate
 * @create 2016-06-21
 */
public class AppCustInfoExteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/*** 客户编号 */
	@NotBlank(message = "custNo不能为空")
	@Size(max = 32, message = "custNo超长") // 长度或大小范围
	private String custNo;

	/*** 客户名称 */
	private String custName;

	/***  */
	private String exte1;

	/***  */
	private String exte2;

	/***  */
	private String exte3;

	/***  */
	private String exte4;

	/***  */
	private String exte5;

	/***  */
	private java.math.BigDecimal exte6;

	/***  */
	private java.math.BigDecimal exte7;

	/***  */
	private Date exte8;

	/***  */
	private Date exte9;

	/***  */
	private Date exte10;

	// 构造函数
	public AppCustInfoExteDto() {
	}

	// getter和setter方法

	/**
	 * 获取 客户编号
	 * 
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * 
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	/**
	 * 获取 客户名称
	 * 
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户名称
	 * 
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getExte1() {
		return exte1;
	}

	public void setExte1(String exte1) {
		this.exte1 = exte1;
	}

	public String getExte2() {
		return exte2;
	}

	public void setExte2(String exte2) {
		this.exte2 = exte2;
	}

	public String getExte3() {
		return exte3;
	}

	public void setExte3(String exte3) {
		this.exte3 = exte3;
	}

	public String getExte4() {
		return exte4;
	}

	public void setExte4(String exte4) {
		this.exte4 = exte4;
	}

	public String getExte5() {
		return exte5;
	}

	public void setExte5(String exte5) {
		this.exte5 = exte5;
	}

	public java.math.BigDecimal getExte6() {
		return exte6;
	}

	public void setExte6(java.math.BigDecimal exte6) {
		this.exte6 = exte6;
	}

	public java.math.BigDecimal getExte7() {
		return exte7;
	}

	public void setExte7(java.math.BigDecimal exte7) {
		this.exte7 = exte7;
	}

	public Date getExte8() {
		return exte8;
	}

	public void setExte8(Date exte8) {
		this.exte8 = exte8;
	}

	public Date getExte9() {
		return exte9;
	}

	public void setExte9(Date exte9) {
		this.exte9 = exte9;
	}

	public Date getExte10() {
		return exte10;
	}

	public void setExte10(Date exte10) {
		this.exte10 = exte10;
	}

}