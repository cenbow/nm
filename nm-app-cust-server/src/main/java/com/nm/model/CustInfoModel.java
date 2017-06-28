package com.nm.model;

import java.io.Serializable;
import java.util.Date;

public class CustInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
private String custNo ; 
    
    /*** 客户类型 */
  	private String custType ; 
    
    /*** 姓名 */
  	private String custName ; 
    
    /*** 性别 */
  	private String sex ; 
    
    /*** 民族 */
  	private String ethnic ;
  	private String ethnicName;
    
    /*** 手机号码 */
  	private String phoneNo ; 
    
    /*** 婚姻状况 */
  	private String marriage ;
	private String marriageName ;
    
    /*** 证件类型 */
  	private String certType ; 
    
    /*** 证件号码 */
  	private String certNo ; 
    
    /*** 证件有效期 */
  	private String certValidDate ; 
    
    /*** 证件发证机关 */
  	private String certIssuOrg ; 
    
    /*** 户籍类型 */
  	private String regType ; 
    
    /*** 户籍所在省/直辖市 */
  	private String regProv ; 
  	
  	/*** 省/直辖市 名称*/
  	private String regProvName;
    
    /*** 户籍市 */
  	private String regCity ; 

  	/*** 市 名称*/
  	private String regCityName;
  	
    /*** 户籍区县 */
  	private String regArea ; 
  	
  	/*** 区县 名称 */
  	private String regAreaName;
    
  	/*** 户籍详细地址*/
  	private String regAddr;
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 登记人 */
  	private String instPerson ; 
    
    /*** 登记时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
  	
  	/*** 最后一次申请分期时间 */
  	private Date lastApplyDate;
  	
  	/*** 受教育程度 */
  	private String educ;
  	private String educName;
	private String contactProv;
	private String contactCity;
	private String contactArea;
	private String contactProvName;
	private String contactCityName;
	private String contactAreaName;
	private String communicateAddr;

	public String getMarriageName() {
		return marriageName;
	}

	public void setMarriageName(String marriageName) {
		this.marriageName = marriageName;
	}

	public String getEthnicName() {
		return ethnicName;
	}

	public void setEthnicName(String ethnicName) {
		this.ethnicName = ethnicName;
	}

	public String getEducName() {
		return educName;
	}

	public void setEducName(String educName) {
		this.educName = educName;
	}

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
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public String getRegProv() {
		return regProv;
	}
	public void setRegProv(String regProv) {
		this.regProv = regProv;
	}
	public String getRegProvName() {
		return regProvName;
	}
	public void setRegProvName(String regProvName) {
		this.regProvName = regProvName;
	}
	public String getRegCity() {
		return regCity;
	}
	public void setRegCity(String regCity) {
		this.regCity = regCity;
	}
	public String getRegCityName() {
		return regCityName;
	}
	public void setRegCityName(String regCityName) {
		this.regCityName = regCityName;
	}
	public String getRegArea() {
		return regArea;
	}
	public void setRegArea(String regArea) {
		this.regArea = regArea;
	}
	public String getRegAreaName() {
		return regAreaName;
	}
	public void setRegAreaName(String regAreaName) {
		this.regAreaName = regAreaName;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInstPerson() {
		return instPerson;
	}
	public void setInstPerson(String instPerson) {
		this.instPerson = instPerson;
	}
	public Date getInstDate() {
		return instDate;
	}
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}
	public Date getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}
	public Date getLastApplyDate() {
		return lastApplyDate;
	}
	public void setLastApplyDate(Date lastApplyDate) {
		this.lastApplyDate = lastApplyDate;
	}
	public String getEduc() {
		return educ;
	}
	public void setEduc(String educ) {
		this.educ = educ;
	}
	public String getContactProv() {
		return contactProv;
	}
	public void setContactProv(String contactProv) {
		this.contactProv = contactProv;
	}
	public String getContactCity() {
		return contactCity;
	}
	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}
	public String getContactArea() {
		return contactArea;
	}
	public void setContactArea(String contactArea) {
		this.contactArea = contactArea;
	}
	public String getContactProvName() {
		return contactProvName;
	}
	public void setContactProvName(String contactProvName) {
		this.contactProvName = contactProvName;
	}
	public String getContactCityName() {
		return contactCityName;
	}
	public void setContactCityName(String contactCityName) {
		this.contactCityName = contactCityName;
	}
	public String getContactAreaName() {
		return contactAreaName;
	}
	public void setContactAreaName(String contactAreaName) {
		this.contactAreaName = contactAreaName;
	}
	public String getCommunicateAddr() {
		return communicateAddr;
	}
	public void setCommunicateAddr(String communicateAddr) {
		this.communicateAddr = communicateAddr;
	}
	
}
