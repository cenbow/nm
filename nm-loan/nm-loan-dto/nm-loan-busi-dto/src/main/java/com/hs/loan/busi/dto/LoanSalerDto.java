package com.hs.loan.busi.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 销售信息  对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanSalerDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** ID */
  	private String id ; 
    
    /*** 销售编号 */
  	@NotBlank(message="销售编号不能为空")
  	@Size(max=40,message="销售编号超长")
  	private String staffNo ; 
    
    /*** 销售员姓名 */
  	@NotBlank(message="销售员姓名不能为空")
  	@Size(max=80,message="销售员姓名超长")
  	private String staffName ; 
    
    /*** 销售电话 (不显示销售电话，是因为历史电话可能已经变更，页面点击获取，方便保护员工隐私和记录查看日志) */

    /*** 销售电话 */
  	private String moblNo ;
    
    /*** 区域经理编号 */
  	@NotBlank(message="区域经理编号不能为空")
  	@Size(max=40,message="区域经理编号超长")
  	private String areaMgerNo ; 
    
    /*** 区域经理姓名 */
  	@NotBlank(message="区域经理姓名不能为空")
  	@Size(max=40,message="区域经理姓名超长")
  	private String areaMgerName ; 
    
    /*** 区域经理员工号 */
  	@NotBlank(message="区域经理员工号不能为空")
  	@Size(max=40,message="区域经理员工号超长")
  	private String areaStaffNo ; 
    
    /*** 机构号 */
  	@NotBlank(message="机构号不能为空")
  	@Size(max=40,message="机构号超长")
  	private String orgNo ; 
    
    /*** 机构名称 */
  	@NotBlank(message="机构名称不能为空")
  	@Size(max=40,message="机构名称超长")
  	private String orgName ; 
    
    /*** 所属省 */
  	@NotBlank(message="所属省不能为空")
  	@Size(max=10,message="所属省超长")
  	private String provNo ; 
    
    /*** 所属市 */
  	@NotBlank(message="所属市不能为空")
  	@Size(max=10,message="所属市超长")
  	private String cityNo ; 
    
    /*** 所属县 */
  	@NotBlank(message="所属大区机构名称不能为空")
  	@Size(max=10,message="所属大区机构名称超长")
  	private String cntyNo ; 
    
    /*** 所属大区机构编号 */
  	@NotBlank(message="所属大区机构编号不能为空")
  	@Size(max=40,message="所属大区机构编号超长")
  	private String areaNo ; 
    
    /*** 所属大区机构名称 */
  	@NotBlank(message="所属大区机构名称不能为空")
  	@Size(max=40,message="所属大区机构名称超长")
  	private String areaName ; 

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

	public String getMoblNo() {
		return moblNo;
	}

	public void setMoblNo(String moblNo) {
		this.moblNo = moblNo;
	}
}