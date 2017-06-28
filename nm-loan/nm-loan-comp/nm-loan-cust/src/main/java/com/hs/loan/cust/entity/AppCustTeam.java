package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户与客户组的关系 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustTeam implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 组编号 */
  	private String custTeam ; 

    //构造函数
    public AppCustTeam(){}

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
	 * 获取 组编号
	 * @return String
	 */
	public String getCustTeam() {
		return custTeam;
	}

	/**
	 * 设置 组编号
	 * @param custTeam
	 */
	public void setCustTeam(String custTeam) {
		this.custTeam = custTeam;
	}

}