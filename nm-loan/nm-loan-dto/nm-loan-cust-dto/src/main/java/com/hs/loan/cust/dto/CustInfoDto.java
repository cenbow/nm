package com.hs.loan.cust.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息dto
 * @author zwr
 *
 */
public class CustInfoDto implements Serializable {
	private static final long serialVersionUID = 1636142589280796227L;

	/*** 客户编号 */
//	@NotBlank (message="客户编号不能为空")
//	@Size(max=32,message="客户编号超长")
  	private String custNo ; 
    
    /*** 客户类型 */
	@NotBlank (message="客户类型不能为空")
	@Size(max=8,message="客户类型超长")
  	private String custType ; 
    
    /*** 姓名 */
	@NotBlank (message="姓名不能为空")
	@Size(max=40,message="姓名超长")
  	private String custName ; 
    
    /*** 性别 */
	@NotBlank (message="性别 不能为空")
	@Size(max=8,message="性别 超长")
  	private String sex ; 
    
    /*** 民族 */
//	@NotBlank (message="民族不能为空")
	@Size(max=8,message="民族超长")
  	private String ethnic ; 
    
    /*** 手机号码 */
	@NotBlank (message="手机号码不能为空")
	@Size(max=32,message="手机号码超长")
  	private String phoneNo ; 
    
    /*** 婚姻状况 */
//	@NotBlank (message="婚姻状况不能为空")
	@Size(max=8,message="婚姻状况超长")
  	private String marriage ; 
    
    /*** 证件类型 */
	@NotBlank (message="证件类型不能为空")
	@Size(max=8,message="证件类型超长")
  	private String certType ; 
    
    /*** 证件号码 */
	@NotBlank (message="证件号码不能为空")
	@Size(max=40,message="证件号码超长")
  	private String certNo ; 
    
    /*** 证件有效期 */
	@NotBlank (message="证件有效期不能为空")
	@Size(max=10,message="证件有效期超长")
  	private String certValidDate ; 
    
    /*** 证件发证机关 */
	@NotBlank (message="证件发证机关 不能为空")
	@Size(max=128,message="证件发证机关 超长")
  	private String certIssuOrg ; 
    
    /*** 户籍类型 */
//	@NotBlank (message="户籍类型不能为空")
	@Size(max=8,message="户籍类型超长")
  	private String regType ; 
    
    /*** 户籍所在省/直辖市 */
	@NotBlank (message="户籍所在省/直辖市不能为空")
	@Size(max=128,message="户籍所在省/直辖市超长")
  	private String regProv ; 
  	
  	/*** 省/直辖市 名称*/
  	private String regProvName;
    
    /*** 户籍市 */
	@NotBlank (message="户籍市不能为空")
	@Size(max=128,message="户籍市超长")
  	private String regCity ; 

  	/*** 市 名称*/
  	private String regCityName;
  	
    /*** 户籍区县 */
	@NotBlank (message="户籍区县不能为空")
	@Size(max=128,message="户籍区县超长")
  	private String regArea ; 
  	
  	/*** 区县 名称 */
  	private String regAreaName;
    
  	/*** 户籍详细地址*/
	@NotBlank (message="户籍详细地址不能为空")
	@Size(max=400,message="户籍详细地址超长")
  	private String regAddr;
    
    /*** 备注 */
	@Size( max = 128,message="备注超长")
  	private String remark ; 
    
    /*** 登记人 */
  	//@NotBlank (message="登记人不能为空")
	@Size(max=32,message="登记人超长")
  	private String instPerson ; 
    
    /*** 登记时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
  	
  	/*** 最后一次申请分期时间 */
  	private Date lastApplyDate;
  	
  	/*** 受教育程度 */
  	private String educ;
	private String contactProv;
	private String contactCity;
	private String contactArea;
	private String contactProvName;
	private String contactCityName;
	private String contactAreaName;
	private String communicateAddr;
	
	/***客户来源信息*/
	private AppCustSourceInfoDto appCustSourceInfoDto;

	public String getCommunicateAddr() {
		return communicateAddr;
	}

	public void setCommunicateAddr(String communicateAddr) {
		this.communicateAddr = communicateAddr;
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

	//构造函数
    public CustInfoDto(){}

    //getter和setter方法
    
    /**
	 * 获取 客户编号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 客户类型
	 * @return String
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * 设置 客户类型
	 * @param custType
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

    
    /**
	 * 获取 姓名
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 姓名
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 性别
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置 性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

    
    /**
	 * 获取 民族
	 * @return String
	 */
	public String getEthnic() {
		return ethnic;
	}

	/**
	 * 设置 民族
	 * @param ethnic
	 */
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

    /**
	 * 获取 手机号码
	 * @return String
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 设置 手机号码
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

    
    /**
	 * 获取 婚姻状况
	 * @return String
	 */
	public String getMarriage() {
		return marriage;
	}

	/**
	 * 设置 婚姻状况
	 * @param marriage
	 */
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

    
    /**
	 * 获取 证件类型
	 * @return String
	 */
	public String getCertType() {
		return certType;
	}

	/**
	 * 设置 证件类型
	 * @param certType
	 */
	public void setCertType(String certType) {
		this.certType = certType;
	}

    
    /**
	 * 获取 证件号码
	 * @return String
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * 设置 证件号码
	 * @param certNo
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

    
    /**
	 * 获取 证件有效期
	 * @return String
	 */
	public String getCertValidDate() {
		return certValidDate;
	}

	/**
	 * 设置 证件有效期
	 * @param certValidDate
	 */
	public void setCertValidDate(String certValidDate) {
		this.certValidDate = certValidDate;
	}

    
    /**
	 * 获取 证件发证机关
	 * @return String
	 */
	public String getCertIssuOrg() {
		return certIssuOrg;
	}

	/**
	 * 设置 证件发证机关
	 * @param certIssuOrg
	 */
	public void setCertIssuOrg(String certIssuOrg) {
		this.certIssuOrg = certIssuOrg;
	}

    
    /**
	 * 获取 户籍类型
	 * @return String
	 */
	public String getRegType() {
		return regType;
	}

	/**
	 * 设置 户籍类型
	 * @param regType
	 */
	public void setRegType(String regType) {
		this.regType = regType;
	}

    
    /**
	 * 获取 户籍所在省/直辖市
	 * @return String
	 */
	public String getRegProv() {
		return regProv;
	}

	/**
	 * 设置 户籍所在省/直辖市
	 * @param regProv
	 */
	public void setRegProv(String regProv) {
		this.regProv = regProv;
	}

    
    /**
	 * 获取 户籍市
	 * @return String
	 */
	public String getRegCity() {
		return regCity;
	}

	/**
	 * 设置 户籍市
	 * @param regCity
	 */
	public void setRegCity(String regCity) {
		this.regCity = regCity;
	}

    
    /**
	 * 获取 户籍区县
	 * @return String
	 */
	public String getRegArea() {
		return regArea;
	}

	/**
	 * 设置 户籍区县
	 * @param regArea
	 */
	public void setRegArea(String regArea) {
		this.regArea = regArea;
	}

    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    /**
	 * 获取 登记人
	 * @return String
	 */
	public String getInstPerson() {
		return instPerson;
	}

	/**
	 * 设置 登记人
	 * @param instPerson
	 */
	public void setInstPerson(String instPerson) {
		this.instPerson = instPerson;
	}

    
    /**
	 * 获取 登记时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 登记时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public Date getLastApplyDate() {
		return lastApplyDate;
	}

	public void setLastApplyDate(Date lastApplyDate) {
		this.lastApplyDate = lastApplyDate;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public String getRegProvName() {
		return regProvName;
	}

	public void setRegProvName(String regProvName) {
		this.regProvName = regProvName;
	}

	public String getRegCityName() {
		return regCityName;
	}

	public void setRegCityName(String regCityName) {
		this.regCityName = regCityName;
	}

	public String getRegAreaName() {
		return regAreaName;
	}

	public void setRegAreaName(String regAreaName) {
		this.regAreaName = regAreaName;
	}

	public String getEduc() {
		return educ;
	}

	public void setEduc(String educ) {
		this.educ = educ;
	}

	public AppCustSourceInfoDto getAppCustSourceInfoDto() {
		return appCustSourceInfoDto;
	}

	public void setAppCustSourceInfoDto(AppCustSourceInfoDto appCustSourceInfoDto) {
		this.appCustSourceInfoDto = appCustSourceInfoDto;
	}
	
	
}
