package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户学校信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustStudy implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
    
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 学校名称 */
  	private String schoolName ; 
    
    /*** 学院名称 */
  	private String collegeName ; 
    
    /*** 学制(年) */
  	private String educ ; 
    
    /*** 专业 */
  	private String major ; 
    
    /*** 系 */
  	private String tie ; 
    
    /*** 班级 */
  	private String custClass ; 
    
    /*** 入校时间(年月日) */
  	private String intoSchoolDate ; 
    
    /*** 省/直辖市 */
  	private String schoolProv ; 
    
    /*** 市 */
  	private String schoolCity ; 
    
    /*** 区/县 */
  	private String schoolArea ; 
    
    /*** 详细地址 */
  	private String schoolAddr ; 
    
    /*** 学校负责人 */
  	private String schoolMan ; 
    
    /*** 学校负责人电话 */
  	private String schoolTel ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public AppCustStudy(){}

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


}