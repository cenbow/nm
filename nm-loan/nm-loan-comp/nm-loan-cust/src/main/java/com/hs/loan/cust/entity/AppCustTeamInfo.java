package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户分组 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustTeamInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** fenzu编号 */
  	private String custTeam ; 
    
    /*** fenzu名称 */
  	private String custTeamName ; 
    
    /*** 规则 */
  	private String custCalc ; 
    
    /*** 创建人 */
  	private String instPerson ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 是否有效 */
  	private String stat ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public AppCustTeamInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 群编号
	 * @return String
	 */
	public String getCustTeam() {
		return custTeam;
	}

	/**
	 * 设置 群编号
	 * @param custTeam
	 */
	public void setCustTeam(String custTeam) {
		this.custTeam = custTeam;
	}

    
    /**
	 * 获取 群名称
	 * @return String
	 */
	public String getCustTeamName() {
		return custTeamName;
	}

	/**
	 * 设置 群名称
	 * @param custTeamName
	 */
	public void setCustTeamName(String custTeamName) {
		this.custTeamName = custTeamName;
	}

    
    /**
	 * 获取 规则
	 * @return String
	 */
	public String getCustCalc() {
		return custCalc;
	}

	/**
	 * 设置 规则
	 * @param custCalc
	 */
	public void setCustCalc(String custCalc) {
		this.custCalc = custCalc;
	}

    
    /**
	 * 获取 创建人
	 * @return String
	 */
	public String getInstPerson() {
		return instPerson;
	}

	/**
	 * 设置 创建人
	 * @param instPerson
	 */
	public void setInstPerson(String instPerson) {
		this.instPerson = instPerson;
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
	 * 获取 是否有效
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 是否有效
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 开始日期
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}