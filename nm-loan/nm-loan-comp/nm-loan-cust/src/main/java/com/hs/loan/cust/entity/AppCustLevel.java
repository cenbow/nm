package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户评级 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustLevel implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 评级 */
  	private String level ; 
    
    /*** 评级方式 */
  	private String levelTyp ; 
    
    /*** 评级日期 */
  	private String levelDate ; 
    
    /*** 人工评级有效期 */
  	private String levelValid ; 
    
    /*** 评级用户 */
  	private String levelUser ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public AppCustLevel(){}

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
	 * 获取 评级
	 * @return String
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 设置 评级
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

    
    /**
	 * 获取 评级方式
	 * @return String
	 */
	public String getLevelTyp() {
		return levelTyp;
	}

	/**
	 * 设置 评级方式
	 * @param levelTyp
	 */
	public void setLevelTyp(String levelTyp) {
		this.levelTyp = levelTyp;
	}

    
    /**
	 * 获取 评级日期
	 * @return String
	 */
	public String getLevelDate() {
		return levelDate;
	}

	/**
	 * 设置 评级日期
	 * @param levelDate
	 */
	public void setLevelDate(String levelDate) {
		this.levelDate = levelDate;
	}

    
    /**
	 * 获取 人工评级有效期
	 * @return String
	 */
	public String getLevelValid() {
		return levelValid;
	}

	/**
	 * 设置 人工评级有效期
	 * @param levelValid
	 */
	public void setLevelValid(String levelValid) {
		this.levelValid = levelValid;
	}

    
    /**
	 * 获取 评级用户
	 * @return String
	 */
	public String getLevelUser() {
		return levelUser;
	}

	/**
	 * 设置 评级用户
	 * @param levelUser
	 */
	public void setLevelUser(String levelUser) {
		this.levelUser = levelUser;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 创建时间
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 更新时间
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}