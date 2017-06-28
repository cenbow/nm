package com.nm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustLiveInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 月租金
	 */
	private BigDecimal mthAmt;
	/**
	 * 现居住地址省
	 */
	private String liveProv;
	private String liveProvName;
	/**
	 * 现居住地址市
	 */
	private String liveCity;
	private String liveCityName;
	/**
	 * 现居住地址县
	 */
	private String liveArea;
	private String liveAreaName;
	/**
	 * 现居住地址
	 */
	private String liveAddr;
	private String liveBuildType ;

	public String getLiveProvName() {
		return liveProvName;
	}

	public void setLiveProvName(String liveProvName) {
		this.liveProvName = liveProvName;
	}

	public String getLiveCityName() {
		return liveCityName;
	}

	public void setLiveCityName(String liveCityName) {
		this.liveCityName = liveCityName;
	}

	public String getLiveAreaName() {
		return liveAreaName;
	}

	public void setLiveAreaName(String liveAreaName) {
		this.liveAreaName = liveAreaName;
	}

	public BigDecimal getMthAmt() {
		return mthAmt;
	}
	public void setMthAmt(BigDecimal mthAmt) {
		this.mthAmt = mthAmt;
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
	public String getLiveBuildType() {
		return liveBuildType;
	}
	public void setLiveBuildType(String liveBuildType) {
		this.liveBuildType = liveBuildType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
