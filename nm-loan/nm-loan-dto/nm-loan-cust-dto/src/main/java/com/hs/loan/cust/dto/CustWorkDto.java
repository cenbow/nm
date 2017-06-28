package com.hs.loan.cust.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * APP_客户工作信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustWorkDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*** ID */
//	@NotBlank(message="表主键不能为空")  // 不允许为空
//	@Size( max = 40,message="表主键超长")// 长度或大小范围
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空") 
	@Size( max = 32,message="客户编号超长")// 长度或大小范围
  	private String custNo ; 
    
    /*** 单位名称 */
	@NotBlank(message="单位名称 不能为空") 
	@Size( max = 128,message="单位名称超长")// 长度或大小范围
  	private String workUnit ; 
    
    /*** 所属行业 */
	@NotBlank(message="所属行业不能为空") 
	@Size( max = 32,message="所属行业超长")// 长度或大小范围
  	private String industry ; 
    
    /*** 单位规模 */
	@NotBlank(message="单位规模不能为空") 
	@Size( max = 32,message="单位规模超长")// 长度或大小范围
  	private String unitScale ; 
    
    /*** 单位性质 */
	@NotBlank(message="单位性质不能为空") 
	@Size( max = 8,message="单位性质超长")// 长度或大小范围
  	private String unitType ; 
    
    /*** 单位电话 */
	@Size( max = 32,message="单位电话超长")// 长度或大小范围
  	private String unitTel ; 
    
    /*** 任职部门 */
	@NotBlank(message="任职部门不能为空") 
	@Size( max = 64,message="任职部门超长")// 长度或大小范围
  	private String workDept ; 
    
    /*** 职位 */
	@NotBlank(message="职位不能为空") 
	@Size( max = 32,message="职位超长")// 长度或大小范围
  	private String workJob ; 
    
    /*** 现单位工作时间（月） */
	@NotBlank(message="现单位工作时间不能为空")  
	@Size( max = 32,message="现单位工作时间超长")// 长度或大小范围
  	private String workTime ; 
    
    /*** 省/直辖市 */
	@NotBlank(message="省/直辖市不能为空") 
	@Size( max = 32,message="省/直辖市超长")// 长度或大小范围
  	private String unitProv ; 
  	
  	private String unitProvName;
    
    /*** 市 */
	@NotBlank(message="市不能为空") 
	@Size( max = 128,message="市超长")// 长度或大小范围
  	private String unitCity ; 
  	
  	private String unitCityName;
    
    /*** 区/县 */
	@NotBlank (message="区/县不能为空") 
	@Size( max = 128,message="区/县超长")// 长度或大小范围
  	private String unitArea ; 
  	
  	private String unitAreaName;
    
    /*** 详细地址 */
	@NotBlank(message="详细地址不能为空") 
	@Size( max = 400,message="详细地址不能超长")// 长度或大小范围
  	private String unitAddr ; 
    
    /*** 单位联系人 */
	//@NotBlank(message="单位联系人不能为空") 
	@Size(max = 32,message="单位联系人超长")// 长度或大小范围
  	private String linkMan ; 
    
    /*** 单位联系人电话 */
	//@NotBlank(message="单位联系人电话不能为空") 
	@Size(max = 32,message="单位联系人电话超长")// 长度或大小范围
  	private String linkTel ; 
    
    /*** 开始日期 */
//	@NotNull (message="开始日期不能为空")
  	private Date beginDate ; 
    
    /*** 结束日期 */
//	@NotNull (message="结束日期不能为空")
  	private Date endDate ;
	//职业
	private String job;
	
	private String isTourismLabor;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	//构造函数
    public CustWorkDto(){}

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
	@JsonSerialize(using = DateTimeJsonSerializer.class)
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
	@JsonSerialize(using = DateTimeJsonSerializer.class)
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

	public String getUnitProvName() {
		return unitProvName;
	}

	public void setUnitProvName(String unitProvName) {
		this.unitProvName = unitProvName;
	}

	public String getUnitCityName() {
		return unitCityName;
	}

	public void setUnitCityName(String unitCityName) {
		this.unitCityName = unitCityName;
	}

	public String getUnitAreaName() {
		return unitAreaName;
	}

	public void setUnitAreaName(String unitAreaName) {
		this.unitAreaName = unitAreaName;
	}

	public String getIsTourismLabor() {
		return isTourismLabor;
	}

	public void setIsTourismLabor(String isTourismLabor) {
		this.isTourismLabor = isTourismLabor;
	}
	
}