package com.nm.cmd;

import java.io.Serializable;

/**
 *客户资产信息
 * @author lenovo
 *
 */
public class CustWorkInfoCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	private String custNo;
	/**
	 * 单位名称
	 */
	private String workUnit;
	/**
	 * 所属行业
	 */
	private String industry;
	/**
	 * 单位地址所在省
	 */
	private String unitProv;
	/**
	 * 所在市
	 */
	private String unitCity;
	/**
	 * 所在县
	 */
	private String unitArea;
	/**
	 * 单位详细地址
	 */
	private String unitAddr;
	/**
	 * 有无旅游从业背景
	 * @return
	 */
	private String workJob;
	/**
	 * 单位电话
	 * @return
	 */
	private String unitTel;
	/**
	 * 现单位工作时间
	 */
	private String workTime;

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

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
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
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

}
