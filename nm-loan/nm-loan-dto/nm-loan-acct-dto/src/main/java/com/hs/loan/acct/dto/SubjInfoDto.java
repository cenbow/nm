package com.hs.loan.acct.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class SubjInfoDto implements Serializable{
	private static final long serialVersionUID = -1679662057739035550L;

	/*** 科目编号 */
  	private String subjId ; 
    
    /*** 科目名称 */
  	@NotBlank(message="科目名称不能为空")
  	@Size(max=100,message="科目名称超长")
  	private String subjName ; 
    
    /*** 科目层级 */
  	@NotNull(message="科目层级不能为空")
  	@Size(max=8,message="科目层级超长")
  	private java.math.BigDecimal subjLevel ; 
    
    /*** 上级科目编号 */
  	@Size(max=40,message="上级科目编号超长")
  	private String parSubjId ; 
    
    /*** 借贷方向 */
  	@NotBlank(message="借贷方向不能为空")
  	@Size(max=8,message="借贷方向超长")
  	private String subjDirection ; 
    
    /*** 是否在用 */
  	@NotBlank(message="是否在用不能为空")
  	@Size(max=8,message="是否在用超长")
  	private String isUse ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 修改时间 */
  	private Date updtDate ; 

    //构造函数
    public SubjInfoDto(){}

    //getter和setter方法
    
    /**
	 * 获取 科目编号
	 * @return String
	 */
	public String getSubjId() {
		return subjId;
	}

	/**
	 * 设置 科目编号
	 * @param subjId
	 */
	public void setSubjId(String subjId) {
		this.subjId = subjId;
	}

    
    /**
	 * 获取 科目名称
	 * @return String
	 */
	public String getSubjName() {
		return subjName;
	}

	/**
	 * 设置 科目名称
	 * @param subjName
	 */
	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}

    
    /**
	 * 获取 科目层级
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getSubjLevel() {
		return subjLevel;
	}

	/**
	 * 设置 科目层级
	 * @param subjLevel
	 */
	public void setSubjLevel(java.math.BigDecimal subjLevel) {
		this.subjLevel = subjLevel;
	}

    
    /**
	 * 获取 上级科目编号
	 * @return String
	 */
	public String getParSubjId() {
		return parSubjId;
	}

	/**
	 * 设置 上级科目编号
	 * @param parSubjId
	 */
	public void setParSubjId(String parSubjId) {
		this.parSubjId = parSubjId;
	}

    
    /**
	 * 获取 借贷方向
	 * @return String
	 */
	public String getSubjDirection() {
		return subjDirection;
	}

	/**
	 * 设置 借贷方向
	 * @param subjDirection
	 */
	public void setSubjDirection(String subjDirection) {
		this.subjDirection = subjDirection;
	}

    
    /**
	 * 获取 是否在用
	 * @return String
	 */
	public String getIsUse() {
		return isUse;
	}

	/**
	 * 设置 是否在用
	 * @param isUse
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 修改时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	
	
}
