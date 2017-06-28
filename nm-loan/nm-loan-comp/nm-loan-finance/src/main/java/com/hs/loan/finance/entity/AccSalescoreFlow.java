package com.hs.loan.finance.entity;

import java.util.Date;

import java.io.Serializable;

/**
 * 对象
 * 
 * @author autocreate
 * @create 2016-10-12
 */
public class AccSalescoreFlow implements Serializable 
{
	private static final long serialVersionUID = 1L;

	/*** ID */
	private String id;

	/*** 员工号 */
	private String staffNo;

	/*** 兑换的礼品号 */
	private String giftNo;

	/*** 交易时间 */
	private Date tranDate;

	/*** 消耗积分 */
	private java.math.BigDecimal tranScore;

	/*** 兑换数量 */
	private Integer tranCnt;
	/*** 礼品名称 */
	private String giftSubject;

	// 构造函数
	public AccSalescoreFlow() {
	}

	// getter和setter方法

	/**
	 * 获取 ID
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 ID
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 员工号
	 * 
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 员工号
	 * 
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	/**
	 * 获取 兑换的礼品号
	 * 
	 * @return String
	 */
	public String getGiftNo() {
		return giftNo;
	}

	/**
	 * 设置 兑换的礼品号
	 * 
	 * @param giftNo
	 */
	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}

	/**
	 * 获取 交易时间
	 * 
	 * @return Date
	 */
	public Date getTranDate() {
		return tranDate;
	}

	/**
	 * 设置 交易时间
	 * 
	 * @param tranDate
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public Integer getTranCnt() {
		return tranCnt;
	}

	/**
	 * 获取 消耗积分
	 * 
	 * @return Integer
	 */
	public java.math.BigDecimal getTranScore() {
		return tranScore;
	}

	/**
	 * 设置 消耗积分
	 * 
	 * @param tranScore
	 */
	public void setTranScore(java.math.BigDecimal tranScore) {
		this.tranScore = tranScore;
	}

	public void setTranCnt(Integer tranCnt) {
		this.tranCnt = tranCnt;
	}

	public String getGiftSubject() {
		return giftSubject;
	}

	public void setGiftSubject(String giftSubject) {
		this.giftSubject = giftSubject;
	}

}