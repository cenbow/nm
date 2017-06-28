package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期银行账户信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanBankAcctDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** ID */
  	private String id ; 
    
    /*** 开户机构 */
  	@NotBlank(message="开户机构不能为空")
  	@Size(max=40,message="开户机构有效超长")
  	private String openOrg ; 
  	
  	/*** 开户机构名 */
  	private String openOrgName ; 
    
    /*** 开户机构所在省 */
  	@NotBlank(message="开户机构所在省不能为空")
  	@Size(max=128,message="开户机构所在省有效超长")
  	private String openProv ; 
  	
  	/*** 开户机构所在省名称 */
  	private String openProvName ; 
    
    /*** 开户机构所在市 */
  	@NotBlank(message="开户机构所在市不能为空")
  	@Size(max=128,message="开户机构所在市有效超长")
  	private String openCity ; 
  	
  	/*** 开户机构所在市名称 */
  	private String openCityName ; 
    
    /*** 账户名称 */
  	@NotBlank(message="账户名称不能为空")
  	@Size(max=80,message="账户名称有效超长")
  	private String acctName ; 
    
    /*** 账户号 */
  	@NotBlank(message="账户号不能为空")
  	@Size(max=40,message="账户号有效超长")
  	private String acctNo ; 
    
    /*** 是否有效 */
  	@NotBlank(message="是否有效不能为空")
  	@Size(max=8,message="是否有效超长")
  	private String stat ; 
    
    /*** 操作人 */
  	@NotBlank(message="操作人不能为空")
  	@Size(max=40,message="操作人超长")
  	private String instPerson ; 
    
    /*** 操作时间 */
  	@Future(message="操作时间必须晚于当前时间")
  	private Date instDate ; 
  	
  	private String bankName;
  	
  	/**
	 * 获取 ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 开户机构
	 * @return String
	 */
	public String getOpenOrg() {
		return openOrg;
	}

	/**
	 * 设置 开户机构
	 * @param openOrg
	 */
	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

    
    /**
	 * 获取 开户机构所在省
	 * @return String
	 */
	public String getOpenProv() {
		return openProv;
	}

	/**
	 * 设置 开户机构所在省
	 * @param openProv
	 */
	public void setOpenProv(String openProv) {
		this.openProv = openProv;
	}

    
    /**
	 * 获取 开户机构所在市
	 * @return String
	 */
	public String getOpenCity() {
		return openCity;
	}

	/**
	 * 设置 开户机构所在市
	 * @param openCity
	 */
	public void setOpenCity(String openCity) {
		this.openCity = openCity;
	}

    
    /**
	 * 获取 账户名称
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 账户名称
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

    
    /**
	 * 获取 账户号
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 账户号
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

    
    /**
	 * 获取 是否有效
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 是否有效
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 操作人
	 * @return String
	 */
	public String getInstPerson() {
		return instPerson;
	}

	/**
	 * 设置 操作人
	 * @param instPerson
	 */
	public void setInstPerson(String instPerson) {
		this.instPerson = instPerson;
	}

    
    /**
	 * 获取 操作时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 操作时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	public String getOpenOrgName() {
		return openOrgName;
	}

	public void setOpenOrgName(String openOrgName) {
		this.openOrgName = openOrgName;
	}

	public String getOpenProvName() {
		return openProvName;
	}

	public void setOpenProvName(String openProvName) {
		this.openProvName = openProvName;
	}

	public String getOpenCityName() {
		return openCityName;
	}

	public void setOpenCityName(String openCityName) {
		this.openCityName = openCityName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}