package com.hs.loan.busi.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期客户基本信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanCustBaseInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户类型 */
  	@NotBlank(message="客户类型不能为空")
  	@Size(max=8,message="客户类型超长")
  	private String custType ; 
  	
  	/*** 客户类型名 */
  	private String custTypeName ; 
    
    /*** 姓名 */
  	@NotBlank(message="姓名不能为空")
  	@Size(max=40,message="姓名超长")
  	private String custName ; 
    
    /*** 性别 */
  	@NotBlank(message="性别不能为空")
  	@Size(max=8,message="性别超长")
  	private String sex ; 
    
    /*** 民族 */
  	@NotBlank(message="民族不能为空")
  	@Size(max=8,message="民族超长")
  	private String ethnic ; 
    
    /*** 手机号码 */
  	@NotBlank(message="手机号码不能为空")
  	@Size(max=32,message="手机号码超长")
  	private String phoneNo ; 
    
    /*** 婚姻状况 */
  	@NotBlank(message="婚姻状况不能为空")
  	@Size(max=8,message="婚姻状况超长")
  	private String marriage ; 
    
    /*** 证件类型 */
  	@NotBlank(message="证件类型不能为空")
  	@Size(max=8,message="证件类型超长")
  	private String certType ; 
  	
  	/*** 证件类型名称 */
  	private String certTypeName ; 
    
    /*** 证件号码 */
  	@NotBlank(message="证件号码不能为空")
  	@Size(max=40,message="证件号码超长")
  	private String certNo ; 
    
    /*** 证件有效期 */
  	@NotBlank(message="证件有效期不能为空")
  	@Size(max=8,message="证件有效期超长")
  	private String certValidDate ; 
    
    /*** 证件发证机关 */
  	@NotBlank(message="证件发证机关不能为空")
  	@Size(max=128,message="证件发证机关超长")
  	private String certIssuOrg ;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertValidDate() {
		return certValidDate;
	}

	public void setCertValidDate(String certValidDate) {
		this.certValidDate = certValidDate;
	}

	public String getCertIssuOrg() {
		return certIssuOrg;
	}

	public void setCertIssuOrg(String certIssuOrg) {
		this.certIssuOrg = certIssuOrg;
	}

	public String getCertTypeName() {
		return certTypeName;
	}

	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}

	public String getCustTypeName() {
		return custTypeName;
	}

	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	} 
	
}