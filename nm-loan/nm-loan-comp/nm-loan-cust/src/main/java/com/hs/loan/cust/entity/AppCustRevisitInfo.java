package com.hs.loan.cust.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.util.Date;
import java.io.Serializable;

/**
 * APP_客户回访信息 对象
 * @author autocreate
 * @create 2016-06-21
 */
public class AppCustRevisitInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 贷款编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 回访对象 */
  	private String revisitObj ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 回访标识 */
  	private String revisitFlag ; 
    
    /*** 标识类型 */
  	private String typ ; 
    
    /*** 说明 */
  	private String revisitDesc ; 
    
    /*** 操作人员ID */
  	private String staffId ; 
    
    /*** 操作人员名称 */
  	private String staffName ; 
    
    /*** 回访时间 */
  	private Date revisitDate ;
	/*** 回访开始时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date startDate;
	/*** 回访结束时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date endDate;
	/*** 回访电话时间 */
	private String phone;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

  	private String remark;

    //构造函数
	//构造函数
    public AppCustRevisitInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 设置 ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 贷款编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 贷款编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	 * 获取 回访对象
	 * @return String
	 */
	public String getRevisitObj() {
		return revisitObj;
	}

	/**
	 * 设置 回访对象
	 * @param revisitObj
	 */
	public void setRevisitObj(String revisitObj) {
		this.revisitObj = revisitObj;
	}

    
    /**
	 * 获取 客户姓名
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户姓名
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 回访标识
	 * @return String
	 */
	public String getRevisitFlag() {
		return revisitFlag;
	}

	/**
	 * 设置 回访标识
	 * @param revisitFlag
	 */
	public void setRevisitFlag(String revisitFlag) {
		this.revisitFlag = revisitFlag;
	}

    
    /**
	 * 获取 标识类型
	 * @return String
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * 设置 标识类型
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

    
    /**
	 * 获取 说明
	 * @return String
	 */
	public String getRevisitDesc() {
		return revisitDesc;
	}

	/**
	 * 设置 说明
	 * @param revisitDesc
	 */
	public void setRevisitDesc(String revisitDesc) {
		this.revisitDesc = revisitDesc;
	}

    
    /**
	 * 获取 操作人员ID
	 * @return String
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * 设置 操作人员ID
	 * @param staffId
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

    
    /**
	 * 获取 操作人员名称
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 操作人员名称
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 回访时间
	 * @return Date
	 */
	public Date getRevisitDate() {
		return revisitDate;
	}

	/**
	 * 设置 回访时间
	 * @param revisitDate
	 */
	public void setRevisitDate(Date revisitDate) {
		this.revisitDate = revisitDate;
	}

}