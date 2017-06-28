package com.hs.loan.cust.entity;


import com.hs.loan.cust.itface.ICustExtraInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * APP_客户工作信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustWork implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 单位名称 */
  	private String workUnit ; 
    
    /*** 所属行业 */
  	private String industry ; 
    
    /*** 单位规模 */
  	private String unitScale ; 
    
    /*** 单位性质 */
  	private String unitType ; 
    
    /*** 单位电话 */
  	private String unitTel ; 
    
    /*** 任职部门 */
  	private String workDept ; 
    
    /*** 职位 */
  	private String workJob ; 
    
    /*** 现单位工作时间（月） */
  	private String workTime ; 
    
    /*** 省/直辖市 */
  	private String unitProv ; 
    
    /*** 市 */
  	private String unitCity ; 
    
    /*** 区/县 */
  	private String unitArea ; 
    
    /*** 详细地址 */
  	private String unitAddr ; 
    
    /*** 单位联系人 */
  	private String linkMan ; 
    
    /*** 单位联系人电话 */
  	private String linkTel ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ;
	private String job;

	private String isTourismLabor;
	
	public String getIsTourismLabor() {
		return isTourismLabor;
	}

	public void setIsTourismLabor(String isTourismLabor) {
		this.isTourismLabor = isTourismLabor;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	//构造函数
    public AppCustWork(){}

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
	 * 获取 单位名称
	 * @return String
	 */
	public String getWorkUnit() {
		return workUnit;
	}

	/**
	 * 设置 单位名称
	 * @param workUnit
	 */
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

    
    /**
	 * 获取 所属行业
	 * @return String
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * 设置 所属行业
	 * @param industry
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

    
    /**
	 * 获取 单位规模
	 * @return String
	 */
	public String getUnitScale() {
		return unitScale;
	}

	/**
	 * 设置 单位规模
	 * @param unitScale
	 */
	public void setUnitScale(String unitScale) {
		this.unitScale = unitScale;
	}

    
    /**
	 * 获取 单位性质
	 * @return String
	 */
	public String getUnitType() {
		return unitType;
	}

	/**
	 * 设置 单位性质
	 * @param unitType
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

    
    /**
	 * 获取 单位电话
	 * @return String
	 */
	public String getUnitTel() {
		return unitTel;
	}

	/**
	 * 设置 单位电话
	 * @param unitTel
	 */
	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}

    
    /**
	 * 获取 任职部门
	 * @return String
	 */
	public String getWorkDept() {
		return workDept;
	}

	/**
	 * 设置 任职部门
	 * @param workDept
	 */
	public void setWorkDept(String workDept) {
		this.workDept = workDept;
	}

    
    /**
	 * 获取 职位
	 * @return String
	 */
	public String getWorkJob() {
		return workJob;
	}

	/**
	 * 设置 职位
	 * @param workJob
	 */
	public void setWorkJob(String workJob) {
		this.workJob = workJob;
	}

    
    /**
	 * 获取 现单位工作时间（月）
	 * @return String
	 */
	public String getWorkTime() {
		return workTime;
	}

	/**
	 * 设置 现单位工作时间（月）
	 * @param workTime
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

    
    /**
	 * 获取 省/直辖市
	 * @return String
	 */
	public String getUnitProv() {
		return unitProv;
	}

	/**
	 * 设置 省/直辖市
	 * @param unitProv
	 */
	public void setUnitProv(String unitProv) {
		this.unitProv = unitProv;
	}

    
    /**
	 * 获取 市
	 * @return String
	 */
	public String getUnitCity() {
		return unitCity;
	}

	/**
	 * 设置 市
	 * @param unitCity
	 */
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}

    
    /**
	 * 获取 区/县
	 * @return String
	 */
	public String getUnitArea() {
		return unitArea;
	}

	/**
	 * 设置 区/县
	 * @param unitArea
	 */
	public void setUnitArea(String unitArea) {
		this.unitArea = unitArea;
	}

    
    /**
	 * 获取 详细地址
	 * @return String
	 */
	public String getUnitAddr() {
		return unitAddr;
	}

	/**
	 * 设置 详细地址
	 * @param unitAddr
	 */
	public void setUnitAddr(String unitAddr) {
		this.unitAddr = unitAddr;
	}

    
    /**
	 * 获取 单位联系人
	 * @return String
	 */
	public String getLinkMan() {
		return linkMan;
	}

	/**
	 * 设置 单位联系人
	 * @param linkMan
	 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

    
    /**
	 * 获取 单位联系人电话
	 * @return String
	 */
	public String getLinkTel() {
		return linkTel;
	}

	/**
	 * 设置 单位联系人电话
	 * @param linkTel
	 */
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

    
    /**
	 * 获取 开始日期
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    

}