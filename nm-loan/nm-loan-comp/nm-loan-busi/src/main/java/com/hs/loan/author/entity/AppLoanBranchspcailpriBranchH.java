package com.hs.loan.author.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_商户直通车权限历史 对象
 * @author autocreate
 * @create 2016-10-27
 */
public class AppLoanBranchspcailpriBranchH implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String branchNo ; 
    
    /***  */
  	private String branchName ; 
    
    /***  */
  	private Date instDate ; 
    
    /***  */
  	private Date endDate ; 
    
    /***  */
  	private String handPerson ; 
    
    /***  */
  	private String orgNo ; 

    //构造函数
    public AppLoanBranchspcailpriBranchH(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置 
	 * @param branchNo
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * 设置 
	 * @param branchName
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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