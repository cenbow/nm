package com.hs.loan.author.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_销售直通车权限 对象
 * @author autocreate
 * @create 2016-10-27
 */
public class AppLoanSpcailpriSaler implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String staffNo ; 
    
    /***  */
  	private String staffName ; 
    
    /***  */
  	private Date instDate ; 
    
    /***  */
  	private String handPerson ; 
    
    /***  */
  	private String orgNo ; 

    //构造函数
    public AppLoanSpcailpriSaler(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getHandPerson() {
		return handPerson;
	}

	/**
	 * 设置 
	 * @param handPerson
	 */
	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

}