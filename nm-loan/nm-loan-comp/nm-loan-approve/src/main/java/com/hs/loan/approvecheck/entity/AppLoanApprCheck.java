package com.hs.loan.approvecheck.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期案件复核 对象
 * @author autocreate
 * @create 2016-11-26
 */
public class AppLoanApprCheck implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String loanNo ; 
    
    /***  */
  	private String custNo ; 
    
    /***  */
  	private String custName ; 
    
    /***  */
  	private String staffNo ; 
    
    /***  */
  	private String staffName ; 
    
    /***  */
  	private Integer checkCnt ; 
    
    /***  */
  	private String checkResult ; 
    
    /***  */
  	private Date begDate ; 
    
    /***  */
  	private Date endDate ; 
    
    /***  */
  	private String checkNo ; 
    
    /***  */
  	private String checkName ; 
    
    /***  */
  	private String isForceCheck ; 
    
    /***  */
  	private String groupId ; 
    
    /***  */
  	private String groupName ; 
    
    /***  */
  	private String apprTyp ; 
    
    /***  */
  	private String apprDesc ; 
    
    /***  */
  	private String checkRemark ; 
    
    /***  */
  	private String fstApprResult ; 
    
    /***  */
  	private String fstApprRemark ; 
    
    /***  */
  	private String apprId ; 
    
    /*** 初审编号 */
  	private String fstCheckNo ; 
    
    /*** 初审编号 */
  	private String fstCheckName ; 

    //构造函数
    public AppLoanApprCheck(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getCheckCnt() {
		return checkCnt;
	}

	/**
	 * 设置 
	 * @param checkCnt
	 */
	public void setCheckCnt(Integer checkCnt) {
		this.checkCnt = checkCnt;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCheckResult() {
		return checkResult;
	}

	/**
	 * 设置 
	 * @param checkResult
	 */
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getBegDate() {
		return begDate;
	}

	/**
	 * 设置 
	 * @param begDate
	 */
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCheckNo() {
		return checkNo;
	}

	/**
	 * 设置 
	 * @param checkNo
	 */
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCheckName() {
		return checkName;
	}

	/**
	 * 设置 
	 * @param checkName
	 */
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getIsForceCheck() {
		return isForceCheck;
	}

	/**
	 * 设置 
	 * @param isForceCheck
	 */
	public void setIsForceCheck(String isForceCheck) {
		this.isForceCheck = isForceCheck;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 设置 
	 * @param groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置 
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getApprTyp() {
		return apprTyp;
	}

	/**
	 * 设置 
	 * @param apprTyp
	 */
	public void setApprTyp(String apprTyp) {
		this.apprTyp = apprTyp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getApprDesc() {
		return apprDesc;
	}

	/**
	 * 设置 
	 * @param apprDesc
	 */
	public void setApprDesc(String apprDesc) {
		this.apprDesc = apprDesc;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCheckRemark() {
		return checkRemark;
	}

	/**
	 * 设置 
	 * @param checkRemark
	 */
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getFstApprResult() {
		return fstApprResult;
	}

	/**
	 * 设置 
	 * @param fstApprResult
	 */
	public void setFstApprResult(String fstApprResult) {
		this.fstApprResult = fstApprResult;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getFstApprRemark() {
		return fstApprRemark;
	}

	/**
	 * 设置 
	 * @param fstApprRemark
	 */
	public void setFstApprRemark(String fstApprRemark) {
		this.fstApprRemark = fstApprRemark;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getApprId() {
		return apprId;
	}

	/**
	 * 设置 
	 * @param apprId
	 */
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

    
    /**
	 * 获取 初审编号
	 * @return String
	 */
	public String getFstCheckNo() {
		return fstCheckNo;
	}

	/**
	 * 设置 初审编号
	 * @param fstCheckNo
	 */
	public void setFstCheckNo(String fstCheckNo) {
		this.fstCheckNo = fstCheckNo;
	}

    
    /**
	 * 获取 初审编号
	 * @return String
	 */
	public String getFstCheckName() {
		return fstCheckName;
	}

	/**
	 * 设置 初审编号
	 * @param fstCheckName
	 */
	public void setFstCheckName(String fstCheckName) {
		this.fstCheckName = fstCheckName;
	}
}