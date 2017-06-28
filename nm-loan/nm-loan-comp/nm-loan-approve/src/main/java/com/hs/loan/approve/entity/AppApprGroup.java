package com.hs.loan.approve.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_审批组信息 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class AppApprGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 审批组号 */
  	private String groupNo ; 
    
    /*** 组名称 */
  	private String groupName ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /***  */
  	private Date instDate ; 
    
    /***  */
  	private Date updtDate ; 
    
    /***  */
  	private String prodTyp ;

    //审批组里面人员组所在省
	private String branchProv;

	//审批组里面人员组所在市
	private String branchCity;

	//审批组里面人员组所在区
	private String branchArea;

    //构造函数
    public AppApprGroup(){}

    //getter和setter方法
    
    /**
	 * 获取 审批组号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 审批组号
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

    
    /**
	 * 获取 组名称
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置 组名称
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    
    /**
	 * 获取 状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
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
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getProdTyp() {
		return prodTyp;
	}

	/**
	 * 设置 
	 * @param prodTyp
	 */
	public void setProdTyp(String prodTyp) {
		this.prodTyp = prodTyp;
	}

	public String getBranchProv() {
		return branchProv;
	}

	public void setBranchProv(String branchProv) {
		this.branchProv = branchProv;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchArea() {
		return branchArea;
	}

	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}
}