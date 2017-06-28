package com.hs.loan.collection.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_催收组信息 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class AppRecallGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 催收组号 */
  	private String groupNo ; 
    
    /*** 组名称 */
  	private String groupName ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 修改时间 */
  	private Date updtDate ; 

    //构造函数
    public AppRecallGroup(){}

    //getter和setter方法
    
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

    
    /**
	 * 获取 修改时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}