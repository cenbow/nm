package com.nm.model;

import java.io.Serializable;

/**
 * 客户学校信息
 * @author lenovo
 *
 */
public class CustStudyInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 入校日期
	 */
	private String intoSchoolDate;
	/**
	 * 所在省
	 */
	private String schoolProv;
	private String schoolProvName;
	/**
	 * 所在市
	 */
	private String schoolCity;
	private String schoolCityName;
	/**
	 * 所在县
	 */
	private String schoolArea;
	private String schoolAreaName;

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

	/**
	 * 详细地址
	 */

	private String schoolAddr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getIntoSchoolDate() {
		return intoSchoolDate;
	}
	public void setIntoSchoolDate(String intoSchoolDate) {
		this.intoSchoolDate = intoSchoolDate;
	}
	public String getSchoolProv() {
		return schoolProv;
	}
	public void setSchoolProv(String schoolProv) {
		this.schoolProv = schoolProv;
	}
	public String getSchoolCity() {
		return schoolCity;
	}
	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}
	public String getSchoolArea() {
		return schoolArea;
	}
	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}
	public String getSchoolAddr() {
		return schoolAddr;
	}
	public void setSchoolAddr(String schoolAddr) {
		this.schoolAddr = schoolAddr;
	}

}
