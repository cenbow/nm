package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期网点信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanBranchDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	 /*** ID */
  	private String id ; 
    
    /*** 网点编码 */
  	@NotBlank(message="网点名称不能为空")
  	@Size(max=40,message="网点名称超长")
  	private String branchNo ; 
  	
  	/*** 网点等级 */
  	@NotBlank(message="网点等级不能为空")
  	@Size(max=40,message="网点等级超长")
  	private String branchLevel;
  	
  	/*** 网点评分 */
  	@Size(max=24,message="网点评分超长")
  	private BigDecimal branchScore;
    
  	//-------------后面的信息仅作为历史数据查看（网点当前信息可能已经不一致）------------------
  	
    /*** 网点名称 */
  	@NotBlank(message="网点名称不能为空")
  	@Size(max=80,message="网点名称超长")
  	private String branchName ; 
    
    /*** 网点所在省 */
  	@Size(max=80,message="网点所在省超长")
  	private String branchProv ; 
  	
  	/*** 网点所在省名 */
  	private String branchProvName ; 
    
    /*** 网点所在城市 */
  	@Size(max=80,message="网点所在城市超长")
  	private String branchCity ; 
  	
  	/*** 网点所在城市名 */
  	private String branchCityName ; 
    
    /*** 网点所在区域 */
  	@Size(max=80,message="网点所在区域超长")
  	private String branchArea ; 
  	
  	/*** 网点区域名 */
  	private String branchAreaName ; 
    
    /*** 网点地址 */
  	@NotBlank(message="网点评分不能为空")
  	@Size(max=200,message="网点评分超长")
  	private String branchAdd ; 


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
	 * 获取 网点编码
	 * @return String
	 */
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置 网点编码
	 * @param branchNo
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

    
    /**
	 * 获取 网点名称
	 * @return String
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * 设置 网点名称
	 * @param branchName
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

    

    
    /**
	 * 获取 网点所在省
	 * @return String
	 */
	public String getBranchProv() {
		return branchProv;
	}

	/**
	 * 设置 网点所在省
	 * @param branchProv
	 */
	public void setBranchProv(String branchProv) {
		this.branchProv = branchProv;
	}

    
    /**
	 * 获取 网点所在城市
	 * @return String
	 */
	public String getBranchCity() {
		return branchCity;
	}

	/**
	 * 设置 网点所在城市
	 * @param branchCity
	 */
	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

    
    /**
	 * 获取 网点所在区域
	 * @return String
	 */
	public String getBranchArea() {
		return branchArea;
	}

	/**
	 * 设置 网点所在区域
	 * @param branchArea
	 */
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}

    
    /**
	 * 获取 网点地址
	 * @return String
	 */
	public String getBranchAdd() {
		return branchAdd;
	}

	/**
	 * 设置 网点地址
	 * @param branchAdd
	 */
	public void setBranchAdd(String branchAdd) {
		this.branchAdd = branchAdd;
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

	public String getBranchAreaName() {
		return branchAreaName;
	}

	public void setBranchAreaName(String branchAreaName) {
		this.branchAreaName = branchAreaName;
	}

	public String getBranchLevel() {
		return branchLevel;
	}

	public void setBranchLevel(String branchLevel) {
		this.branchLevel = branchLevel;
	}

	public BigDecimal getBranchScore() {
		return branchScore;
	}

	public void setBranchScore(BigDecimal branchScore) {
		this.branchScore = branchScore;
	}
}