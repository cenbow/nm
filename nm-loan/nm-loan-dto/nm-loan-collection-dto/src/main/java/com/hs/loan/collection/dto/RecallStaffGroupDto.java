package com.hs.loan.collection.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.util.Date;

import java.io.Serializable;

/**
 * PL_催收人员与组关联信息 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class RecallStaffGroupDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 人员编号 */
  	private String staffNo ; 
    
    /*** 人员姓名 */
  	private String staffName ; 
    
    /*** 催收组号 */
  	private String groupNo ; 
    
    /*** 组名称 */
  	private String groupName ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 最近签到时间 */
  	private Date updtDate ; 
    
    /*** 创建时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date instDate ; 

    //构造函数
    public RecallStaffGroupDto(){}

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
	 * 获取 人员编号
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 人员编号
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 人员姓名
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 人员姓名
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 催收组号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 催收组号
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
	 * 获取 最近签到时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 最近签到时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}