package com.hs.loan.finance.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * 对象
 * 
 * @author autocreate
 * @create 2016-10-11
 */
public class AccSalescoreFlowDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/*** ID */
	private String id;

	/*** 员工号 */
	private String staffNo;

	/*** 兑换的礼品号 */
	private String giftNo;

	/*** 交易日期 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date tranDate;

	/*** 消耗积分 */
	private java.math.BigDecimal tranScore;
	/*** 兑换数量 */
	private Integer tranCnt;
	/*** 礼品名称*/
	private String giftSubject;

	// 构造函数
	public AccSalescoreFlowDto() {
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
	 * 获取 交易日期
	 * 
	 * @return String
	 */
	public Date getTranDate() {
		return tranDate;
	}

	/**
	 * 设置 交易日期
	 * 
	 * @param tranDate
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public java.math.BigDecimal getTranScore() {
		return tranScore;
	}

	public void setTranScore(java.math.BigDecimal tranScore) {
		this.tranScore = tranScore;
	}

	public Integer getTranCnt() {
		return tranCnt;
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