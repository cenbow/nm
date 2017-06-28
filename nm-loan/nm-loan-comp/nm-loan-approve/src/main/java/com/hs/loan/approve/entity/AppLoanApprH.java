package com.hs.loan.approve.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期审批信息 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class AppLoanApprH implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 审批案件ID */
  	private String apprId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户名称 */
  	private String custName ; 
    
    /*** 销售编号 */
  	private String saleNo ; 
    
    /*** 销售名称 */
  	private String saleName ; 
    
    /*** 进入时间 */
  	private Date instDate ; 
    
    /*** 进件次数 */
  	private Integer instNum ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 自动审批开始时间 */
  	private Date autoStartDate ; 
    
    /*** 自动审批结束时间 */
  	private Date autoEndDate ; 
    
    /*** 人工审批开始时间 */
  	private Date manuStartDate ; 
    
    /*** 人工审批结束时间 */
  	private Date manuEndDate ; 
    
    /*** 审批员编号 */
  	private String apprNo ; 
    
    /*** 审批员姓名 */
  	private String apprName ; 
    
    /*** 是否强制人工审批 */
  	private String manuFlag ; 
    
    /*** 审批组编号 */
  	private String apprGroupNo ; 
    
    /*** 审批组名称 */
  	private String apprGroupName ; 
    
    /*** 审批说明 */
  	private String apprDesc ; 
    
    /*** 备注 */
  	private String remark ; 

    //构造函数
    public AppLoanApprH(){}

    //getter和setter方法
    
    /**
	 * 获取 审批案件ID
	 * @return String
	 */
	public String getApprId() {
		return apprId;
	}

	/**
	 * 设置 审批案件ID
	 * @param apprId
	 */
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

    
    /**
	 * 获取 分期编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
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
	 * 获取 销售编号
	 * @return String
	 */
	public String getSaleNo() {
		return saleNo;
	}

	/**
	 * 设置 销售编号
	 * @param saleNo
	 */
	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

    
    /**
	 * 获取 销售名称
	 * @return String
	 */
	public String getSaleName() {
		return saleName;
	}

	/**
	 * 设置 销售名称
	 * @param saleName
	 */
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

    
    /**
	 * 获取 进入时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 进入时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 进件次数
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 进件次数
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

    
    /**
	 * 获取 状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 自动审批开始时间
	 * @return Date
	 */
	public Date getAutoStartDate() {
		return autoStartDate;
	}

	/**
	 * 设置 自动审批开始时间
	 * @param autoStartDate
	 */
	public void setAutoStartDate(Date autoStartDate) {
		this.autoStartDate = autoStartDate;
	}

    
    /**
	 * 获取 自动审批结束时间
	 * @return Date
	 */
	public Date getAutoEndDate() {
		return autoEndDate;
	}

	/**
	 * 设置 自动审批结束时间
	 * @param autoEndDate
	 */
	public void setAutoEndDate(Date autoEndDate) {
		this.autoEndDate = autoEndDate;
	}

    
    /**
	 * 获取 人工审批开始时间
	 * @return Date
	 */
	public Date getManuStartDate() {
		return manuStartDate;
	}

	/**
	 * 设置 人工审批开始时间
	 * @param manuStartDate
	 */
	public void setManuStartDate(Date manuStartDate) {
		this.manuStartDate = manuStartDate;
	}

    
    /**
	 * 获取 人工审批结束时间
	 * @return Date
	 */
	public Date getManuEndDate() {
		return manuEndDate;
	}

	/**
	 * 设置 人工审批结束时间
	 * @param manuEndDate
	 */
	public void setManuEndDate(Date manuEndDate) {
		this.manuEndDate = manuEndDate;
	}

    
    /**
	 * 获取 审批员编号
	 * @return String
	 */
	public String getApprNo() {
		return apprNo;
	}

	/**
	 * 设置 审批员编号
	 * @param apprNo
	 */
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

    
    /**
	 * 获取 审批员姓名
	 * @return String
	 */
	public String getApprName() {
		return apprName;
	}

	/**
	 * 设置 审批员姓名
	 * @param apprName
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

    
    /**
	 * 获取 是否强制人工审批
	 * @return String
	 */
	public String getManuFlag() {
		return manuFlag;
	}

	/**
	 * 设置 是否强制人工审批
	 * @param manuFlag
	 */
	public void setManuFlag(String manuFlag) {
		this.manuFlag = manuFlag;
	}

    
    /**
	 * 获取 审批组编号
	 * @return String
	 */
	public String getApprGroupNo() {
		return apprGroupNo;
	}

	/**
	 * 设置 审批组编号
	 * @param apprGroupNo
	 */
	public void setApprGroupNo(String apprGroupNo) {
		this.apprGroupNo = apprGroupNo;
	}

    
    /**
	 * 获取 审批组名称
	 * @return String
	 */
	public String getApprGroupName() {
		return apprGroupName;
	}

	/**
	 * 设置 审批组名称
	 * @param apprGroupName
	 */
	public void setApprGroupName(String apprGroupName) {
		this.apprGroupName = apprGroupName;
	}

    
    /**
	 * 获取 审批说明
	 * @return String
	 */
	public String getApprDesc() {
		return apprDesc;
	}

	/**
	 * 设置 审批说明
	 * @param apprDesc
	 */
	public void setApprDesc(String apprDesc) {
		this.apprDesc = apprDesc;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}