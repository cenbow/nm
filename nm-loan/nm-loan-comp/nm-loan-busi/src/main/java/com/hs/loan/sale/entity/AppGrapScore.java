package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_芝麻分抓取表 对象
 * @author autocreate
 * @create 2017-03-28
 */
public class AppGrapScore implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 识别码 */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
  	
  	/*** 贷款编号*/
  	private String loanNo;
    
    /*** 抓取渠道 */
  	private String grapChan ; 
    
    /*** 抓取分数表 */
  	private Integer grapSesaSeed ; 
    
    /*** 抓取时间 */
  	private Date grapDate ; 
    
    /*** 添加时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AppGrapScore(){}

    //getter和setter方法
    
    /**
	 * 获取 识别码
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 识别码
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
	 * 获取 抓取渠道
	 * @return String
	 */
	public String getGrapChan() {
		return grapChan;
	}

	/**
	 * 设置 抓取渠道
	 * @param grapChan
	 */
	public void setGrapChan(String grapChan) {
		this.grapChan = grapChan;
	}

    
    /**
	 * 获取 抓取分数表
	 * @return Integer
	 */
	public Integer getGrapSesaSeed() {
		return grapSesaSeed;
	}

	/**
	 * 设置 抓取分数表
	 * @param grapSesaSeed
	 */
	public void setGrapSesaSeed(Integer grapSesaSeed) {
		this.grapSesaSeed = grapSesaSeed;
	}

    
    /**
	 * 获取 抓取时间
	 * @return Date
	 */
	public Date getGrapDate() {
		return grapDate;
	}

	/**
	 * 设置 抓取时间
	 * @param grapDate
	 */
	public void setGrapDate(Date grapDate) {
		this.grapDate = grapDate;
	}

    
    /**
	 * 获取 添加时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 添加时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

}