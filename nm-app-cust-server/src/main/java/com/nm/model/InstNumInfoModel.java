package com.nm.model;

import java.io.Serializable;

public class InstNumInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String instNum;
	private String fstPayTyp;
	private String fstPayVal;
	public String getInstNum() {
		return instNum;
	}
	public void setInstNum(String instNum) {
		this.instNum = instNum;
	}
	public String getFstPayTyp() {
		return fstPayTyp;
	}
	public void setFstPayTyp(String fstPayTyp) {
		this.fstPayTyp = fstPayTyp;
	}
	public String getFstPayVal() {
		return fstPayVal;
	}
	public void setFstPayVal(String fstPayVal) {
		this.fstPayVal = fstPayVal;
	}

}
