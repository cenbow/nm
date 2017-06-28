package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期与网点关系 对象
 * @author autocreate
 * @create 2015-10-27
 */
public class AppLoanBranch implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 网点编码 */
  	private String branchNo ; 
    
    /*** 网点名称 */
  	private String branchName ; 
    
    /*** 联系人 */
  	private String contctPer ; 
    
    /*** 联系人职务 */
  	private String contctDuty ; 
    
    /*** 联系电话 */
  	private String contctTel ; 
    
    /*** 网点所在省 */
  	private String branchProv ; 
    
    /*** 网点所在城市 */
  	private String branchCity ; 
    
    /*** 网点所在区域 */
  	private String branchArea ; 
    
    /*** 网点地址 */
  	private String branchAdd ;

	/*** 挂单商户名称 */
	private String marName;

	private String marContctPerson;

	private String marContctTel;

	public String getMarContctPerson() {
		return marContctPerson;
	}

	public void setMarContctPerson(String marContctPerson) {
		this.marContctPerson = marContctPerson;
	}

	public String getMarContctTel() {
		return marContctTel;
	}

	public void setMarContctTel(String marContctTel) {
		this.marContctTel = marContctTel;
	}

	//构造函数
    public AppLoanBranch(){}

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
	 * 获取 分期编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 网点编码
	 * @return String
	 */
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置 网点编码
	 * @param branchNo
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

    
    /**
	 * 获取 网点名称
	 * @return String
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * 设置 网点名称
	 * @param branchName
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

    
    /**
	 * 获取 联系人
	 * @return String
	 */
	public String getContctPer() {
		return contctPer;
	}

	/**
	 * 设置 联系人
	 * @param contctPer
	 */
	public void setContctPer(String contctPer) {
		this.contctPer = contctPer;
	}

    
    /**
	 * 获取 联系人职务
	 * @return String
	 */
	public String getContctDuty() {
		return contctDuty;
	}

	/**
	 * 设置 联系人职务
	 * @param contctDuty
	 */
	public void setContctDuty(String contctDuty) {
		this.contctDuty = contctDuty;
	}

    
    /**
	 * 获取 联系电话
	 * @return String
	 */
	public String getContctTel() {
		return contctTel;
	}

	/**
	 * 设置 联系电话
	 * @param contctTel
	 */
	public void setContctTel(String contctTel) {
		this.contctTel = contctTel;
	}

    
    /**
	 * 获取 网点所在省
	 * @return String
	 */
	public String getBranchProv() {
		return branchProv;
	}

	/**
	 * 设置 网点所在省
	 * @param branchProv
	 */
	public void setBranchProv(String branchProv) {
		this.branchProv = branchProv;
	}

    
    /**
	 * 获取 网点所在城市
	 * @return String
	 */
	public String getBranchCity() {
		return branchCity;
	}

	/**
	 * 设置 网点所在城市
	 * @param branchCity
	 */
	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

    
    /**
	 * 获取 网点所在区域
	 * @return String
	 */
	public String getBranchArea() {
		return branchArea;
	}

	/**
	 * 设置 网点所在区域
	 * @param branchArea
	 */
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}

    
    /**
	 * 获取 网点地址
	 * @return String
	 */
	public String getBranchAdd() {
		return branchAdd;
	}

	/**
	 * 设置 网点地址
	 * @param branchAdd
	 */
	public void setBranchAdd(String branchAdd) {
		this.branchAdd = branchAdd;
	}
	/**
	 * 设置 挂单商户
	 */
	public String getMarName() {
		return marName;
	}
	/**
	 * 设置 挂单商户
	 * @param marName
	 */
	public void setMarName(String marName) {
		this.marName = marName;
	}
}