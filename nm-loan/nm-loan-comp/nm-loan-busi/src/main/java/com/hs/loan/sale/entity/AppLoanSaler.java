package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期与销售关系 对象
 * @author autocreate
 * @create 2015-10-29
 */
public class AppLoanSaler implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 销售编号 */
  	private String staffNo ; 
    
    /*** 销售员姓名 */
  	private String staffName ; 
    
    /*** 销售电话 */
  	private String moblNo ; 
    
    /*** 区域经理编号 */
  	private String areaMgerNo ; 
    
    /*** 区域经理姓名 */
  	private String areaMgerName ; 
    
    /*** 区域经理员工号 */
  	private String areaStaffNo ; 
    
    /*** 机构号 */
  	private String orgNo ; 
    
    /*** 机构名称 */
  	private String orgName ; 
    
    /*** 所属省 */
  	private String provNo ; 
    
    /*** 所属市 */
  	private String cityNo ; 
    
    /*** 所属县 */
  	private String cntyNo ; 
    
    /*** 所属大区机构编号 */
  	private String areaNo ; 
    
    /*** 所属大区机构名称 */
  	private String areaName ; 

    //构造函数
    public AppLoanSaler(){}

    //getter和setter方法
    
    /**
	 * 获取 分期编码
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编码
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 销售编号
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 销售编号
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 销售员姓名
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 销售员姓名
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 销售电话
	 * @return String
	 */
	public String getMoblNo() {
		return moblNo;
	}

	/**
	 * 设置 销售电话
	 * @param moblNo
	 */
	public void setMoblNo(String moblNo) {
		this.moblNo = moblNo;
	}

    
    /**
	 * 获取 区域经理编号
	 * @return String
	 */
	public String getAreaMgerNo() {
		return areaMgerNo;
	}

	/**
	 * 设置 区域经理编号
	 * @param areaMgerNo
	 */
	public void setAreaMgerNo(String areaMgerNo) {
		this.areaMgerNo = areaMgerNo;
	}

    
    /**
	 * 获取 区域经理姓名
	 * @return String
	 */
	public String getAreaMgerName() {
		return areaMgerName;
	}

	/**
	 * 设置 区域经理姓名
	 * @param areaMgerName
	 */
	public void setAreaMgerName(String areaMgerName) {
		this.areaMgerName = areaMgerName;
	}

    
    /**
	 * 获取 区域经理员工号
	 * @return String
	 */
	public String getAreaStaffNo() {
		return areaStaffNo;
	}

	/**
	 * 设置 区域经理员工号
	 * @param areaStaffNo
	 */
	public void setAreaStaffNo(String areaStaffNo) {
		this.areaStaffNo = areaStaffNo;
	}

    
    /**
	 * 获取 机构号
	 * @return String
	 */
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 机构号
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

    
    /**
	 * 获取 机构名称
	 * @return String
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置 机构名称
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    
    /**
	 * 获取 所属省
	 * @return String
	 */
	public String getProvNo() {
		return provNo;
	}

	/**
	 * 设置 所属省
	 * @param provNo
	 */
	public void setProvNo(String provNo) {
		this.provNo = provNo;
	}

    
    /**
	 * 获取 所属市
	 * @return String
	 */
	public String getCityNo() {
		return cityNo;
	}

	/**
	 * 设置 所属市
	 * @param cityNo
	 */
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

    
    /**
	 * 获取 所属县
	 * @return String
	 */
	public String getCntyNo() {
		return cntyNo;
	}

	/**
	 * 设置 所属县
	 * @param cntyNo
	 */
	public void setCntyNo(String cntyNo) {
		this.cntyNo = cntyNo;
	}

    
    /**
	 * 获取 所属大区机构编号
	 * @return String
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * 设置 所属大区机构编号
	 * @param areaNo
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

    
    /**
	 * 获取 所属大区机构名称
	 * @return String
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 设置 所属大区机构名称
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}