package com.hs.loan.cust.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * APP_客户银行账户信息 对象
 * @author autocreate
 * @create 2015-10-27
 */
public class AppCustBankAcct implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 账户号 */
  	private String acctNo ; 
    
    /*** 账户名称 */
  	private String acctName ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 开户机构 */
  	private String openOrg ; 
    
    /*** 开户机构所在省 */
  	private String openProv ; 
    
    /*** 开户机构所在市 */
  	private String openCity ; 
    
    /*** 是否有效 */
  	private String stat ; 
    
    /*** 操作人 */
  	private String instPerson ; 
	/***开户行名称*/
	private String bankName;
    /*** 操作时间 */
  	private Date instDate ; 

    //构造函数
    public AppCustBankAcct(){}

    //getter和setter方法
    
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
	 * 获取 客户号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}