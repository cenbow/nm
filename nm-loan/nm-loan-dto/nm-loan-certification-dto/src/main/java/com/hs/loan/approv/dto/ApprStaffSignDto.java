package com.hs.loan.approv.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_审批人员签到明细 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class ApprStaffSignDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
  
    /*** 审批组号 */
  	private String groupName ; 
    
    /*** 员工号 */
  	private String staffNo ; 
  	/*** 员工号 */
  	private String staffName ; 
    
    /*** 签到类型 */
  	private String signTyp ; 
    
    /*** 签到时间 */
  	private Date signDate ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	//构造函数
    public ApprStaffSignDto(){}

    //getter和setter方法
     

    
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

}