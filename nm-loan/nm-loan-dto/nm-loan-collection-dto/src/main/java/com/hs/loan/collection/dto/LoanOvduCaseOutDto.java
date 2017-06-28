package com.hs.loan.collection.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * PL_逾期案件 对象
 * 
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOvduCaseOutDto extends LoanOveDualCaseBaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/*** 处理状态 */
	private String dealStat;

	/*** 处理结果 */
	private String dealResult;

	/*** 处理人 */
	private String dealUser;

	/*** 处理人姓名 */
	private String dealUserName;

	/*** 处理机构 */
	private String dealOrg;

	/*** 处理机构名称 */
	private String dealOrgName;

	/*** 处理时间 */
	@JsonSerialize(using=DateTimeJsonSerializer.class)
	private Date dealTime;

	/*** 新增人 */
	private String instUser;

	/*** 新增时间 */
	@JsonSerialize(using=DateTimeJsonSerializer.class)
	private Date instDate;

	/*** 更新时间 */
	@JsonSerialize(using=DateTimeJsonSerializer.class)
	private Date updtDate;
	/** 商户所在省名称 ****/
	private String branchProvName;
	/** 商户所在市名称 ****/
	private String branchCityName;
	/** 渠道 ****/
	private String chanleNo;
	/** 渠道名称 ****/
	private String chanleName;

	/** 委外标记 ****/
	private String outFlag;
	/*** 逾期本金 */
	private String ovduPrin;

	/*** 逾期利息 */
	private String ovduInt;

	/*** 剩余本金 */
	private String remainPrin;

	/*** 剩余利息 */
	private String remainInt;

	// 构造函数
	public LoanOvduCaseOutDto() {
	}

	// getter和setter方法

	/**
	 * 获取 处理状态
	 * 
	 * @return String
	 */
	public String getDealStat() {
		return dealStat;
	}

	/**
	 * 设置 处理状态
	 * 
	 * @param dealStat
	 */
	public void setDealStat(String dealStat) {
		super.setDealStat(dealStat);
		this.dealStat = dealStat;
	}

	/**
	 * 获取 处理结果
	 * 
	 * @return String
	 */
	public String getDealResult() {
		return dealResult;
	}

	/**
	 * 设置 处理结果
	 * 
	 * @param dealResult
	 */
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	/**
	 * 获取 处理人
	 * 
	 * @return String
	 */
	public String getDealUser() {
		return dealUser;
	}

	/**
	 * 设置 处理人
	 * 
	 * @param dealUser
	 */
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	/**
	 * 获取 处理人姓名
	 * 
	 * @return String
	 */
	public String getDealUserName() {
		return dealUserName;
	}

	/**
	 * 设置 处理人姓名
	 * 
	 * @param dealUserName
	 */
	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	/**
	 * 获取 处理机构
	 * 
	 * @return String
	 */
	public String getDealOrg() {
		return dealOrg;
	}

	/**
	 * 设置 处理机构
	 * 
	 * @param dealOrg
	 */
	public void setDealOrg(String dealOrg) {
		this.dealOrg = dealOrg;
	}

	/**
	 * 获取 处理机构名称
	 * 
	 * @return String
	 */
	public String getDealOrgName() {
		return dealOrgName;
	}

	/**
	 * 设置 处理机构名称
	 * 
	 * @param dealOrgName
	 */
	public void setDealOrgName(String dealOrgName) {
		this.dealOrgName = dealOrgName;
	}

	/**
	 * 获取 处理时间
	 * 
	 * @return Date
	 */
	public Date getDealTime() {
		return dealTime;
	}

	/**
	 * 设置 处理时间
	 * 
	 * @param dealTime
	 */
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	/**
	 * 获取 新增人
	 * 
	 * @return String
	 */
	public String getInstUser() {
		return instUser;
	}

	/**
	 * 设置 新增人
	 * 
	 * @param instUser
	 */
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	/**
	 * 获取 新增时间
	 * 
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 新增时间
	 * 
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	/**
	 * 获取 更新时间
	 * 
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * 
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getBranchProvName() {
		return branchProvName;
	}

	public void setBranchProvName(String branchProvName) {
		this.branchProvName = branchProvName;
	}

	public String getBranchCityName() {
		return branchCityName;
	}

	public void setBranchCityName(String branchCityName) {
		this.branchCityName = branchCityName;
	}

	public String getChanleNo() {
		return chanleNo;
	}

	public void setChanleNo(String chanleNo) {
		this.chanleNo = chanleNo;
	}

	public String getChanleName() {
		return chanleName;
	}

	public void setChanleName(String chanleName) {
		this.chanleName = chanleName;
	}

	public String getOutFlag() {
		return outFlag;
	}

	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}

	public String getOvduPrin() {
		return ovduPrin;
	}

	public void setOvduPrin(String ovduPrin) {
		this.ovduPrin = ovduPrin;
	}

	public String getOvduInt() {
		return ovduInt;
	}

	public void setOvduInt(String ovduInt) {
		this.ovduInt = ovduInt;
	}

	public String getRemainPrin() {
		return remainPrin;
	}

	public void setRemainPrin(String remainPrin) {
		this.remainPrin = remainPrin;
	}

	public String getRemainInt() {
		return remainInt;
	}

	public void setRemainInt(String remainInt) {
		this.remainInt = remainInt;
	}

}