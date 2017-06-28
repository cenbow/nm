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
public class SysSalescoreInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 员工号 */
  	private String staffNo ; 
    
    /*** 区域经理员工号 */
  	private String mgrAreaNo ; 
    
    /*** 大区经理员工号 */
  	private String mgrRegionNo ; 
    
    /*** 员工名称 */
  	private String staffName ; 
    
    /*** 所属机构 */
  	private String belgOrgNo ; 
    
    /*** 所属机构名 */
  	private String belgOrgName ; 
    
    /*** 所属区域标志 */
  	private String areaFlag ; 
    
    /*** 目标金额(区域经理专用) */
  	private java.math.BigDecimal targetAmt ; 
    
    /*** 目标总金额(大区经理专用) */
  	private java.math.BigDecimal allTargetAmt ; 
    
    /*** 角色 */
  	private String roleId ; 
    
    /*** 创建日期 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date createDate ; 
    
    /*** 离职日期 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date leaveDate ; 
    
    /*** 统计月份 */
  	private String statMth ; 
    
    /***  */
  	private java.math.BigDecimal ovduRat ; 
    
    /***  */
  	private java.math.BigDecimal score ; 

    //构造函数
    public SysSalescoreInfoDto(){}

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
	 * 获取 员工号
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 员工号
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 区域经理员工号
	 * @return String
	 */
	public String getMgrAreaNo() {
		return mgrAreaNo;
	}

	/**
	 * 设置 区域经理员工号
	 * @param mgrAreaNo
	 */
	public void setMgrAreaNo(String mgrAreaNo) {
		this.mgrAreaNo = mgrAreaNo;
	}

    
    /**
	 * 获取 大区经理员工号
	 * @return String
	 */
	public String getMgrRegionNo() {
		return mgrRegionNo;
	}

	/**
	 * 设置 大区经理员工号
	 * @param mgrRegionNo
	 */
	public void setMgrRegionNo(String mgrRegionNo) {
		this.mgrRegionNo = mgrRegionNo;
	}

    
    /**
	 * 获取 员工名称
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 员工名称
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 所属机构
	 * @return String
	 */
	public String getBelgOrgNo() {
		return belgOrgNo;
	}

	/**
	 * 设置 所属机构
	 * @param belgOrgNo
	 */
	public void setBelgOrgNo(String belgOrgNo) {
		this.belgOrgNo = belgOrgNo;
	}

    
    /**
	 * 获取 所属机构名
	 * @return String
	 */
	public String getBelgOrgName() {
		return belgOrgName;
	}

	/**
	 * 设置 所属机构名
	 * @param belgOrgName
	 */
	public void setBelgOrgName(String belgOrgName) {
		this.belgOrgName = belgOrgName;
	}

    
    /**
	 * 获取 所属区域标志
	 * @return String
	 */
	public String getAreaFlag() {
		return areaFlag;
	}

	/**
	 * 设置 所属区域标志
	 * @param areaFlag
	 */
	public void setAreaFlag(String areaFlag) {
		this.areaFlag = areaFlag;
	}

    
    /**
	 * 获取 目标金额(区域经理专用)
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTargetAmt() {
		return targetAmt;
	}

	/**
	 * 设置 目标金额(区域经理专用)
	 * @param targetAmt
	 */
	public void setTargetAmt(java.math.BigDecimal targetAmt) {
		this.targetAmt = targetAmt;
	}

    
    /**
	 * 获取 目标总金额(大区经理专用)
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getAllTargetAmt() {
		return allTargetAmt;
	}

	/**
	 * 设置 目标总金额(大区经理专用)
	 * @param allTargetAmt
	 */
	public void setAllTargetAmt(java.math.BigDecimal allTargetAmt) {
		this.allTargetAmt = allTargetAmt;
	}

    
    /**
	 * 获取 角色
	 * @return String
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置 角色
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

    
    /**
	 * 获取 创建日期
	 * @return Date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置 创建日期
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    
    /**
	 * 获取 离职日期
	 * @return Date
	 */
	public Date getLeaveDate() {
		return leaveDate;
	}

	/**
	 * 设置 离职日期
	 * @param leaveDate
	 */
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

    
    /**
	 * 获取 统计月份
	 * @return String
	 */
	public String getStatMth() {
		return statMth;
	}

	/**
	 * 设置 统计月份
	 * @param statMth
	 */
	public void setStatMth(String statMth) {
		this.statMth = statMth;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOvduRat() {
		return ovduRat;
	}

	/**
	 * 设置 
	 * @param ovduRat
	 */
	public void setOvduRat(java.math.BigDecimal ovduRat) {
		this.ovduRat = ovduRat;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getScore() {
		return score;
	}

	/**
	 * 设置 
	 * @param score
	 */
	public void setScore(java.math.BigDecimal score) {
		this.score = score;
	}

}