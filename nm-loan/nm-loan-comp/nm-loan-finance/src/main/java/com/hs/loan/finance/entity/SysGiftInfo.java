package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  对象
 * @author autocreate
 * @create 2016-10-26
 */
public class SysGiftInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 兑换的礼品号 */
  	private String giftNo ; 
    
    /*** 礼品名称 */
  	private String giftSubject ; 
    
    /*** 礼品简介 */
  	private String giftRemark ; 
    
    /*** 所需积分 */
  	private Integer valueScore ; 
    
    /*** 所剩数量 */
  	private Integer remainNum ; 
    
    /*** 个人兑换次数 */
  	private Integer selfCnt ; 
    
    /*** 图片路径 */
  	private String potPath ; 
    
    /*** 开始时间 */
  	private Date bgnDate ; 
    
    /*** 结束时间 */
  	private Date endDate ; 

    //构造函数
    public SysGiftInfo(){}

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
	 * 获取 兑换的礼品号
	 * @return String
	 */
	public String getGiftNo() {
		return giftNo;
	}

	/**
	 * 设置 兑换的礼品号
	 * @param giftNo
	 */
	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}

    
    /**
	 * 获取 礼品名称
	 * @return String
	 */
	public String getGiftSubject() {
		return giftSubject;
	}

	/**
	 * 设置 礼品名称
	 * @param giftSubject
	 */
	public void setGiftSubject(String giftSubject) {
		this.giftSubject = giftSubject;
	}

    
    /**
	 * 获取 礼品简介
	 * @return String
	 */
	public String getGiftRemark() {
		return giftRemark;
	}

	/**
	 * 设置 礼品简介
	 * @param giftRemark
	 */
	public void setGiftRemark(String giftRemark) {
		this.giftRemark = giftRemark;
	}

    
    /**
	 * 获取 所需积分
	 * @return Integer
	 */
	public Integer getValueScore() {
		return valueScore;
	}

	/**
	 * 设置 所需积分
	 * @param valueScore
	 */
	public void setValueScore(Integer valueScore) {
		this.valueScore = valueScore;
	}

    
    /**
	 * 获取 所剩数量
	 * @return Integer
	 */
	public Integer getRemainNum() {
		return remainNum;
	}

	/**
	 * 设置 所剩数量
	 * @param remainNum
	 */
	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

    
    /**
	 * 获取 个人兑换次数
	 * @return Integer
	 */
	public Integer getSelfCnt() {
		return selfCnt;
	}

	/**
	 * 设置 个人兑换次数
	 * @param selfCnt
	 */
	public void setSelfCnt(Integer selfCnt) {
		this.selfCnt = selfCnt;
	}

    
    /**
	 * 获取 图片路径
	 * @return String
	 */
	public String getPotPath() {
		return potPath;
	}

	/**
	 * 设置 图片路径
	 * @param potPath
	 */
	public void setPotPath(String potPath) {
		this.potPath = potPath;
	}

    
    /**
	 * 获取 开始时间
	 * @return Date
	 */
	public Date getBgnDate() {
		return bgnDate;
	}

	/**
	 * 设置 开始时间
	 * @param bgnDate
	 */
	public void setBgnDate(Date bgnDate) {
		this.bgnDate = bgnDate;
	}

    
    /**
	 * 获取 结束时间
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束时间
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}