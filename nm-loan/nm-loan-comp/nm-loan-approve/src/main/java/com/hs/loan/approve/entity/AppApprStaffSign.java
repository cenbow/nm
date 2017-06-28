package com.hs.loan.approve.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_审批人员签到明细 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class AppApprStaffSign implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 签到ID */
  	private String id ; 
    
    /*** 审批组号 */
  	private String groupNo ; 
    
    /*** 员工号 */
  	private String staffNo ; 
    
    /*** 签到类型 */
  	private String signTyp ; 
    
    /*** 签到时间 */
  	private Date signDate ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public AppApprStaffSign(){}

    //getter和setter方法
    
    /**
	 * 获取 签到ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 签到ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
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
	 * 获取 员工号
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 员工号
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 签到类型
	 * @return String
	 */
	public String getSignTyp() {
		return signTyp;
	}

	/**
	 * 设置 签到类型
	 * @param signTyp
	 */
	public void setSignTyp(String signTyp) {
		this.signTyp = signTyp;
	}

    
    /**
	 * 获取 签到时间
	 * @return Date
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * 设置 签到时间
	 * @param signDate
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

    
    /**
	 * 获取 创建日期
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建日期
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新日期
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新日期
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}