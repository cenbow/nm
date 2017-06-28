package com.hs.loan.approvcheck.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;

import java.io.Serializable;

/**
 * APP_审批内部组人员信息 对象
 * @author autocreate
 * @create 2016-11-17
 */
public class AppApprstaffGroupdetalDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String id;
    /***  */
  	private String groupId ; 
    
    /***  */
  	private String groupName ; 
    
    /***  */
  	private String staffNo ; 
    
    /***  */
  	private String staffName ; 
    
    /***  */
  	private String staffLevl ; 
    
    /***  */
  	private String operateNo ; 
    
    /***  */
  	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date operateDate ; 

    //构造函数
    public AppApprstaffGroupdetalDto(){}

    //getter和setter方法
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    /**
	 * 获取 
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 设置 
	 * @param groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置 
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    
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
	 * @return String
	 */
	public String getStaffLevl() {
		return staffLevl;
	}

	/**
	 * 设置 
	 * @param staffLevl
	 */
	public void setStaffLevl(String staffLevl) {
		this.staffLevl = staffLevl;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOperateNo() {
		return operateNo;
	}

	/**
	 * 设置 
	 * @param operateNo
	 */
	public void setOperateNo(String operateNo) {
		this.operateNo = operateNo;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getOperateDate() {
		return operateDate;
	}

	/**
	 * 设置 
	 * @param operateDate
	 */
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

}