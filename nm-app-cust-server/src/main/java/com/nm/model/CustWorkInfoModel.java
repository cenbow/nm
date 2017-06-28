package com.nm.model;

import java.io.Serializable;

/**
 * 客户单位信息
 * @author lenovo
 *
 */
public class CustWorkInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 单位名称
	 */
	private String workUnit;
	/**
	 * 职位
	 */
	private String workJob;
	private String workJobName;
	private String industry;
	private String industryName;

	/**
	 * 公司电话
	 */
	private String unitTel;
	/**
	 * 工作时间(月)
	 */
	private String workTime;
	/**
	 * 任职部门
	 */
	private String workDept;
	/**
	 * 单位地址所在省
	 */
	private String unitProv;
	private String unitProvName;
	/**
	 * 所在市
	 */
	private String unitCity;
	private String unitCityName;
	/**
	 * 所在县
	 */
	private String unitArea;
	private String unitAreaName;

	public String getWorkJobName() {
		return workJobName;
	}

	public void setWorkJobName(String workJobName) {
		this.workJobName = workJobName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
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

	/**
	 * 单位详细地址
	 */

	private String unitAddr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getWorkJob() {
		return workJob;
	}
	public void setWorkJob(String workJob) {
		this.workJob = workJob;
	}
	public String getUnitTel() {
		return unitTel;
	}
	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getWorkDept() {
		return workDept;
	}
	public void setWorkDept(String workDept) {
		this.workDept = workDept;
	}
	public String getUnitProv() {
		return unitProv;
	}
	public void setUnitProv(String unitProv) {
		this.unitProv = unitProv;
	}
	public String getUnitCity() {
		return unitCity;
	}
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}
	public String getUnitArea() {
		return unitArea;
	}
	public void setUnitArea(String unitArea) {
		this.unitArea = unitArea;
	}
	public String getUnitAddr() {
		return unitAddr;
	}
	public void setUnitAddr(String unitAddr) {
		this.unitAddr = unitAddr;
	}

}
