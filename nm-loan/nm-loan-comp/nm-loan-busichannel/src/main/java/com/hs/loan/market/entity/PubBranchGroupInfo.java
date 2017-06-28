package com.hs.loan.market.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_网点分组信息 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubBranchGroupInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 商户编号 */
  	private String groupNo ; 
    
    /*** 商户名称 */
  	private String groupName ; 
    
    /*** 分组类型 */
  	private String groupTyp ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 修改日期 */
  	private Date updtDate ; 

    //构造函数
    public PubBranchGroupInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 商户编号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 商户编号
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

    
    /**
	 * 获取 商户名称
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置 商户名称
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    
    /**
	 * 获取 分组类型
	 * @return String
	 */
	public String getGroupTyp() {
		return groupTyp;
	}

	/**
	 * 设置 分组类型
	 * @param groupTyp
	 */
	public void setGroupTyp(String groupTyp) {
		this.groupTyp = groupTyp;
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
	 * 获取 修改日期
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改日期
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}