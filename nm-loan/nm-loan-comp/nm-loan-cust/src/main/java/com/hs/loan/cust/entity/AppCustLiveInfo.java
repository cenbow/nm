package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户居住信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustLiveInfo implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
    
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 居住地址是否同户籍 */
  	private String isRegLive ; 
    
    /*** 现地址居住时间 */
  	private String liveTimeSpan ; 
    
    /*** 居住地省/直辖市 */
  	private String liveProv ; 
    
    /*** 居住市 */
  	private String liveCity ; 
    
    /*** 居住区/县 */
  	private String liveArea ; 
    
    /*** 居住详细地址 */
  	private String liveAddr ; 
    
    /*** 房产所有权 */
  	private String liveBuildType ; 
    
    /*** 月租金 */
  	private java.math.BigDecimal mthAmt ; 
    
    /*** 合同到期日 */
  	private String contrEndDate ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public AppCustLiveInfo(){}

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
	 * 获取 居住地址是否同户籍
	 * @return String
	 */
	public String getIsRegLive() {
		return isRegLive;
	}

	/**
	 * 设置 居住地址是否同户籍
	 * @param isRegLive
	 */
	public void setIsRegLive(String isRegLive) {
		this.isRegLive = isRegLive;
	}

    
    /**
	 * 获取 现地址居住时间
	 * @return String
	 */
	public String getLiveTimeSpan() {
		return liveTimeSpan;
	}

	/**
	 * 设置 现地址居住时间
	 * @param liveTimeSpan
	 */
	public void setLiveTimeSpan(String liveTimeSpan) {
		this.liveTimeSpan = liveTimeSpan;
	}

    
    /**
	 * 获取 居住地省/直辖市
	 * @return String
	 */
	public String getLiveProv() {
		return liveProv;
	}

	/**
	 * 设置 居住地省/直辖市
	 * @param liveProv
	 */
	public void setLiveProv(String liveProv) {
		this.liveProv = liveProv;
	}

    
    /**
	 * 获取 居住市
	 * @return String
	 */
	public String getLiveCity() {
		return liveCity;
	}

	/**
	 * 设置 居住市
	 * @param liveCity
	 */
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

    
    /**
	 * 获取 居住区/县
	 * @return String
	 */
	public String getLiveArea() {
		return liveArea;
	}

	/**
	 * 设置 居住区/县
	 * @param liveArea
	 */
	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}

    
    /**
	 * 获取 居住详细地址
	 * @return String
	 */
	public String getLiveAddr() {
		return liveAddr;
	}

	/**
	 * 设置 居住详细地址
	 * @param liveAddr
	 */
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
	}

    
    /**
	 * 获取 房产所有权
	 * @return String
	 */
	public String getLiveBuildType() {
		return liveBuildType;
	}

	/**
	 * 设置 房产所有权
	 * @param liveBuildType
	 */
	public void setLiveBuildType(String liveBuildType) {
		this.liveBuildType = liveBuildType;
	}

    
    /**
	 * 获取 月租金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getMthAmt() {
		return mthAmt;
	}

	/**
	 * 设置 月租金
	 * @param mthAmt
	 */
	public void setMthAmt(java.math.BigDecimal mthAmt) {
		this.mthAmt = mthAmt;
	}

    
    /**
	 * 获取 合同到期日
	 * @return String
	 */
	public String getContrEndDate() {
		return contrEndDate;
	}

	/**
	 * 设置 合同到期日
	 * @param contrEndDate
	 */
	public void setContrEndDate(String contrEndDate) {
		this.contrEndDate = contrEndDate;
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