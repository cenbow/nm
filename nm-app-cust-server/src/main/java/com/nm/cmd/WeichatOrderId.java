package com.nm.cmd;

import java.io.Serializable;

/**
 * 微信轮训请求，验证是否完全提交
 * @author lenovo
 *
 */
public class WeichatOrderId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String orderId;
	private String errorCode;
	private String errorMsg;
	private String stat;
	private String type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
