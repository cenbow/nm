package com.nm.cmd;

import java.io.Serializable;

public class CustInfoCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 客户编号
	 */
    private String custNo;
    /**
     * 客户姓名
     */
    private String custName;
    /**
     * 身份证号码
     */
    private String certNo;
    /**
     * 客户类型
     */
    private String custType;
    /**
     *性别
     */
    private String sex;
    /**
     * 客户手机号码
     */
    private String phoneNo;
    /**
     * 证件有效期
     */
    private String certValidDate;
    /**
     * 民族
     */
    private String ethnic;
    /**
     * 发证机关
     */
    private String certIssuOrg;
    /**
     * 户籍类型
     */
    private String regType;
    /**
     * 证件类型
     */
    private String certType;
    /**
     * 婚姻状况
     */
    private String marriage;
    /**
     * 教育程度
     */
    private String educ;
    /**
     * 户籍地址所在省
     */
    private String regProv;
    /**
     * 户籍地址所在市
     */
    private String regCity;
    /**
     * 户籍地址所在县
     */
    private String regArea;
    /**
     * 户籍详细地址
     */
    private String regAddr;
    /**
     * 通讯地址所在省
     */
    private String contactProv;
    /**
     * 通讯地址所在市
     */
    private String contactCity;
    /**
     * 通讯地址所在县
     */
    private String contactArea;
    /**
     * 通讯详细地址
     */
    private String communicateAddr;
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
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getEduc() {
		return educ;
	}
	public void setEduc(String educ) {
		this.educ = educ;
	}
	public String getRegProv() {
		return regProv;
	}
	public void setRegProv(String regProv) {
		this.regProv = regProv;
	}
	public String getRegCity() {
		return regCity;
	}
	public void setRegCity(String regCity) {
		this.regCity = regCity;
	}
	public String getRegArea() {
		return regArea;
	}
	public void setRegArea(String regArea) {
		this.regArea = regArea;
	}
	public String getRegAddr() {
		return regAddr;
	}
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
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
	public String getCommunicateAddr() {
		return communicateAddr;
	}
	public void setCommunicateAddr(String communicateAddr) {
		this.communicateAddr = communicateAddr;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCertValidDate() {
		return certValidDate;
	}
	public void setCertValidDate(String certValidDate) {
		this.certValidDate = certValidDate;
	}
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
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
}
