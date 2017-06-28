package com.hs.loan.collection.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_逾期案件 对象
 * @author autocreate
 * @create 2016-03-23
 */
public class PlLoanOvduCase implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 案件编码 */
  	private String id ; 
    
    /*** 贷款编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户名称 */
  	private String custName ; 
    
    /*** 证件号码*/
  	private String certNo ; 
    
    /*** 业务日期 */
  	private String busnDate ; 
    
    /*** 业务日期 */
  	private String repayDate ; 
    
    /***  */
  	private Integer repayNum ; 
    
    /*** 逾期阶段 */
  	private Integer ovduLev ; 
    
    /*** 逾期开始期数 */
  	private Integer bgnNum ; 
    
    /*** 逾期结束期数 */
  	private Integer endNum ; 
    
    /*** 逾期开始日期 */
  	private String bgnDate ; 
    
    /*** 逾期结束日期 */
  	private String endDate ; 
    
    /***  */
  	private Integer ovduDays ; 
    
    /*** 处理状态 */
  	private String dealStat ; 
    
    /*** 处理时间 */
  	private Date dealTime ; 
    
    /*** 处理人 */
  	private String staffNo ; 
    
    /*** 处理人姓名 */
  	private String staffName ; 
    
    /*** 处理机构 */
  	private String orgNo ; 
    
    /*** 处理机构名称 */
  	private String orgName ; 
    
    /*** 处理结果 */
  	private String dealDesc ; 
    
    /*** 委外标记 */
  	private String outFlag ; 
    
    /*** 新增时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
	
	/** 扣款状态 */
	private String withStat;

    //构造函数
    public PlLoanOvduCase(){}

    //getter和setter方法
    
    /**
	 * 获取 案件编码
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 案件编码
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 贷款编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 贷款编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	 * 获取 客户名称
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户名称
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * 设置 
	 * @param certNo
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

    
    /**
	 * 获取 业务日期
	 * @return String
	 */
	public String getBusnDate() {
		return busnDate;
	}

	/**
	 * 设置 业务日期
	 * @param busnDate
	 */
	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}

    
    /**
	 * 获取 业务日期
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 业务日期
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getRepayNum() {
		return repayNum;
	}

	/**
	 * 设置 
	 * @param repayNum
	 */
	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

    
    /**
	 * 获取 逾期阶段
	 * @return Integer
	 */
	public Integer getOvduLev() {
		return ovduLev;
	}

	/**
	 * 设置 逾期阶段
	 * @param ovduLev
	 */
	public void setOvduLev(Integer ovduLev) {
		this.ovduLev = ovduLev;
	}

    
    /**
	 * 获取 逾期开始期数
	 * @return Integer
	 */
	public Integer getBgnNum() {
		return bgnNum;
	}

	/**
	 * 设置 逾期开始期数
	 * @param bgnNum
	 */
	public void setBgnNum(Integer bgnNum) {
		this.bgnNum = bgnNum;
	}

    
    /**
	 * 获取 逾期结束期数
	 * @return Integer
	 */
	public Integer getEndNum() {
		return endNum;
	}

	/**
	 * 设置 逾期结束期数
	 * @param endNum
	 */
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

    
    /**
	 * 获取 逾期开始日期
	 * @return String
	 */
	public String getBgnDate() {
		return bgnDate;
	}

	/**
	 * 设置 逾期开始日期
	 * @param bgnDate
	 */
	public void setBgnDate(String bgnDate) {
		this.bgnDate = bgnDate;
	}

    
    /**
	 * 获取 逾期结束日期
	 * @return String
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 设置 逾期结束日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getOvduDays() {
		return ovduDays;
	}

	/**
	 * 设置 
	 * @param ovduDays
	 */
	public void setOvduDays(Integer ovduDays) {
		this.ovduDays = ovduDays;
	}

    
    /**
	 * 获取 处理状态
	 * @return String
	 */
	public String getDealStat() {
		return dealStat;
	}

	/**
	 * 设置 处理状态
	 * @param dealStat
	 */
	public void setDealStat(String dealStat) {
		this.dealStat = dealStat;
	}

    
    /**
	 * 获取 处理时间
	 * @return Date
	 */
	public Date getDealTime() {
		return dealTime;
	}

	/**
	 * 设置 处理时间
	 * @param dealTime
	 */
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

    
    /**
	 * 获取 处理人
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 处理人
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 处理人姓名
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 处理人姓名
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 处理机构
	 * @return String
	 */
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 处理机构
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

    
    /**
	 * 获取 处理机构名称
	 * @return String
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置 处理机构名称
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    
    /**
	 * 获取 处理结果
	 * @return String
	 */
	public String getDealDesc() {
		return dealDesc;
	}

	/**
	 * 设置 处理结果
	 * @param dealDesc
	 */
	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

    
    /**
	 * 获取 委外标记
	 * @return String
	 */
	public String getOutFlag() {
		return outFlag;
	}

	/**
	 * 设置 委外标记
	 * @param outFlag
	 */
	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}

    
    /**
	 * 获取 新增时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 新增时间
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

	public String getWithStat() {
		return withStat;
	}

	public void setWithStat(String withStat) {
		this.withStat = withStat;
	}

}