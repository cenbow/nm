package com.nm.cmd;

import java.io.Serializable;

/**
 * 客户学校信息
 * @author lenovo
 *
 */
public class CustStudyInfoCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	private String custNo;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 入校日期
	 */
	private String intoSchoolDate;
	/**
	 * 所在省
	 */
	private String schoolProv;
	/**
	 * 所在市
	 */
	private String schoolCity;
	/**
	 * 所在县
	 */
	private String schoolArea;
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
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
