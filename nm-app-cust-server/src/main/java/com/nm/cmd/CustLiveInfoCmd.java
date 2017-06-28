package com.nm.cmd;

import java.io.Serializable;
import java.util.Date;

public class CustLiveInfoCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 客户编号
	 */
	private String custNo;
	/**
	 * 现居住地址省
	 */
	private String liveProv;
	/**
	 * 现居住地址市
	 */
	private String liveCity;
	/**
	 * 现居住地址县
	 */
	private String liveArea;
	/**
	 * 现居住地址
	 */
	private String liveAddr;
  	private Date beginDate ;
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
	public String getLiveProv() {
		return liveProv;
	}
	public void setLiveProv(String liveProv) {
		this.liveProv = liveProv;
	}
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	public String getLiveArea() {
		return liveArea;
	}
	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}
	public String getLiveAddr() {
		return liveAddr;
	}
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

}
