package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_销售直通车信息 对象
 * @author autocreate
 * @create 2016-10-27
 */
public class AppLoanSpcailpriInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String loanNo ; 
    
    /***  */
  	private String custName ; 
    
    /***  */
  	private String orgNo ; 
    
    /***  */
  	private String orgName ; 
    
    /***  */
  	private String staffNo ; 
    
    /***  */
  	private String staffName ; 
    
    /***  */
  	private String apprNo ; 
    
    /***  */
  	private String apprName ; 
    
    /***  */
  	private String apprResult ; 
    
    /***  */
  	private String apprRemark ; 
    
    /***  */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date applyDate ; 
    
    /***  */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date apprDate ; 

    //构造函数
    public AppLoanSpcailpriInfoDto(){}

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
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置 
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	 * @return String
	 */
	public String getApprNo() {
		return apprNo;
	}

	/**
	 * 设置 
	 * @param apprNo
	 */
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getApprName() {
		return apprName;
	}

	/**
	 * 设置 
	 * @param apprName
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getApprResult() {
		return apprResult;
	}

	/**
	 * 设置 
	 * @param apprResult
	 */
	public void setApprResult(String apprResult) {
		this.apprResult = apprResult;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getApprRemark() {
		return apprRemark;
	}

	/**
	 * 设置 
	 * @param apprRemark
	 */
	public void setApprRemark(String apprRemark) {
		this.apprRemark = apprRemark;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * 设置 
	 * @param applyDate
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getApprDate() {
		return apprDate;
	}

	/**
	 * 设置 
	 * @param apprDate
	 */
	public void setApprDate(Date apprDate) {
		this.apprDate = apprDate;
	}

}