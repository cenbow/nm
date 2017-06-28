package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户学校信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustStudyDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
	/*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(max=40,message="表主键超长")
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank (message="客户编号不能为空")
	@Size(max=32,message="客户编号超长")
  	private String custNo ; 
    
    /*** 学校名称 */
	@NotBlank (message="学校名称不能为空")
	@Size(max=128,message="学校名称超长")
  	private String schoolName ; 
    
    /*** 学院名称 */
	@NotBlank (message="学院名称不能为空")
	@Size(max=128,message="学院名称超长")
  	private String collegeName ; 
    
    /*** 学制(年) */
	@NotBlank (message="学制(年)不能为空")
	@Size(max=8,message="学制(年)超长")
  	private String educ ; 
    
    /*** 专业 */
	@NotBlank (message="专业不能为空")
	@Size(max=32,message="专业超长")
  	private String major ; 
    
    /*** 系 */
	@NotBlank (message="系不能为空")
	@Size(max=8,message="系超长")
  	private String tie ; 
    
    /*** 班级 */
	@NotBlank (message="班级不能为空")
	@Size(max=32,message="班级超长")
  	private String custClass ; 
    
    /*** 入校时间(年月日) */
	@NotBlank (message="入校时间(年月日)不能为空")
	@Size(max=10,message="入校时间(年月日)超长")
  	private String intoSchoolDate ; 
    
    /*** 省/直辖市 */
	@NotBlank (message="省/直辖市不能为空")
	@Size(max=32,message="省/直辖市超长")
  	private String schoolProv ; 
  	
  	private String schoolProvName;
    
    /*** 市 */
	@NotBlank (message="市不能为空")
	@Size(max=128,message="市超长")
  	private String schoolCity ; 
  	
  	private String schoolCityName;
    
    /*** 区/县 */
	@NotBlank (message="区/县不能为空")
	@Size(max=128,message="区/县超长")
  	private String schoolArea ; 
  	
  	private String schoolAreaName;
    
    /*** 详细地址 */
	@NotBlank (message="详细地址不能为空")
	@Size(max=400,message="详细地址超长")
  	private String schoolAddr ; 
    
    /*** 学校负责人 */
	@NotBlank (message="学校负责人不能为空")
	@Size(max=32,message="学校负责人超长")
  	private String schoolMan ; 
    
    /*** 学校负责人电话 */
	@NotBlank (message="学校负责人电话")
	@Size(max=32,message="学校负责人电话")
  	private String schoolTel ; 
    
    /*** 开始日期 */
  	@JsonSerialize(using = DateTimeJsonSerializer.class) 
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	@JsonSerialize(using = DateTimeJsonSerializer.class) 
  	private Date endDate ; 

    //构造函数
    public CustStudyDto(){}

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
	 * 获取 学校名称
	 * @return String
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * 设置 学校名称
	 * @param schoolName
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

    
    /**
	 * 获取 学院名称
	 * @return String
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * 设置 学院名称
	 * @param collegeName
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

    
    /**
	 * 获取 学制(年)
	 * @return String
	 */
	public String getEduc() {
		return educ;
	}

	/**
	 * 设置 学制(年)
	 * @param educ
	 */
	public void setEduc(String educ) {
		this.educ = educ;
	}

    
    /**
	 * 获取 专业
	 * @return String
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * 设置 专业
	 * @param major
	 */
	public void setMajor(String major) {
		this.major = major;
	}

    
    /**
	 * 获取 系
	 * @return String
	 */
	public String getTie() {
		return tie;
	}

	/**
	 * 设置 系
	 * @param tie
	 */
	public void setTie(String tie) {
		this.tie = tie;
	}

    
    /**
	 * 获取 班级
	 * @return String
	 */
	public String getCustClass() {
		return custClass;
	}

	/**
	 * 设置 班级
	 * @param custClass
	 */
	public void setCustClass(String custClass) {
		this.custClass = custClass;
	}

    
    /**
	 * 获取 入校时间(年月日)
	 * @return String
	 */
	public String getIntoSchoolDate() {
		return intoSchoolDate;
	}

	/**
	 * 设置 入校时间(年月日)
	 * @param intoSchoolDate
	 */
	public void setIntoSchoolDate(String intoSchoolDate) {
		this.intoSchoolDate = intoSchoolDate;
	}

    
    /**
	 * 获取 省/直辖市
	 * @return String
	 */
	public String getSchoolProv() {
		return schoolProv;
	}

	/**
	 * 设置 省/直辖市
	 * @param schoolProv
	 */
	public void setSchoolProv(String schoolProv) {
		this.schoolProv = schoolProv;
	}

    
    /**
	 * 获取 市
	 * @return String
	 */
	public String getSchoolCity() {
		return schoolCity;
	}

	/**
	 * 设置 市
	 * @param schoolCity
	 */
	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

    
    /**
	 * 获取 区/县
	 * @return String
	 */
	public String getSchoolArea() {
		return schoolArea;
	}

	/**
	 * 设置 区/县
	 * @param schoolArea
	 */
	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

    
    /**
	 * 获取 详细地址
	 * @return String
	 */
	public String getSchoolAddr() {
		return schoolAddr;
	}

	/**
	 * 设置 详细地址
	 * @param schoolAddr
	 */
	public void setSchoolAddr(String schoolAddr) {
		this.schoolAddr = schoolAddr;
	}

    
    /**
	 * 获取 学校负责人
	 * @return String
	 */
	public String getSchoolMan() {
		return schoolMan;
	}

	/**
	 * 设置 学校负责人
	 * @param schoolMan
	 */
	public void setSchoolMan(String schoolMan) {
		this.schoolMan = schoolMan;
	}

    
    /**
	 * 获取 学校负责人电话
	 * @return String
	 */
	public String getSchoolTel() {
		return schoolTel;
	}

	/**
	 * 设置 学校负责人电话
	 * @param schoolTel
	 */
	public void setSchoolTel(String schoolTel) {
		this.schoolTel = schoolTel;
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

	public String getSchoolProvName() {
		return schoolProvName;
	}

	public void setSchoolProvName(String schoolProvName) {
		this.schoolProvName = schoolProvName;
	}

	public String getSchoolCityName() {
		return schoolCityName;
	}

	public void setSchoolCityName(String schoolCityName) {
		this.schoolCityName = schoolCityName;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

}