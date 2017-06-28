package com.hs.loan.approvcheck.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;

import java.io.Serializable;

/**
 * APP_审批内部组信息 对象
 * @author autocreate
 * @create 2016-11-17
 */
public class AppApprstaffGroupDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String groupId ; 
    
    /***  */
  	private String groupName ; 
    
    /***  */
  	private String stat ; 
    
    /***  */
  	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date operateDate ; 
    
    /***  */
  	private String operateNo ; 

    //构造函数
    public AppApprstaffGroupDto(){}

    //getter和setter方法
    
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
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
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

}