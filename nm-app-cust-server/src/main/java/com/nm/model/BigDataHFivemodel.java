package com.nm.model;

import java.io.Serializable;

public class BigDataHFivemodel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String crawlerURL;
	private String errorMsg;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getCrawlerURL() {
		return crawlerURL;
	}
	public void setCrawlerURL(String crawlerURL) {
		this.crawlerURL = crawlerURL;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
