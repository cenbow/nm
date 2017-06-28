package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户评分 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustScore implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 评分 */
  	private String score ; 
    
    /*** 评分方式 */
  	private String scoreTyp ; 
    
    /*** 评分日期 */
  	private String scoreDate ; 
    
    /*** 人工评分有效期 */
  	private String scoreValid ; 
    
    /*** 评分用户 */
  	private String scoreUser ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public AppCustScore(){}

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
	 * 获取 评分
	 * @return String
	 */
	public String getScore() {
		return score;
	}

	/**
	 * 设置 评分
	 * @param score
	 */
	public void setScore(String score) {
		this.score = score;
	}

    
    /**
	 * 获取 评分方式
	 * @return String
	 */
	public String getScoreTyp() {
		return scoreTyp;
	}

	/**
	 * 设置 评分方式
	 * @param scoreTyp
	 */
	public void setScoreTyp(String scoreTyp) {
		this.scoreTyp = scoreTyp;
	}

    
    /**
	 * 获取 评分日期
	 * @return String
	 */
	public String getScoreDate() {
		return scoreDate;
	}

	/**
	 * 设置 评分日期
	 * @param scoreDate
	 */
	public void setScoreDate(String scoreDate) {
		this.scoreDate = scoreDate;
	}

    
    /**
	 * 获取 人工评分有效期
	 * @return String
	 */
	public String getScoreValid() {
		return scoreValid;
	}

	/**
	 * 设置 人工评分有效期
	 * @param scoreValid
	 */
	public void setScoreValid(String scoreValid) {
		this.scoreValid = scoreValid;
	}

    
    /**
	 * 获取 评分用户
	 * @return String
	 */
	public String getScoreUser() {
		return scoreUser;
	}

	/**
	 * 设置 评分用户
	 * @param scoreUser
	 */
	public void setScoreUser(String scoreUser) {
		this.scoreUser = scoreUser;
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