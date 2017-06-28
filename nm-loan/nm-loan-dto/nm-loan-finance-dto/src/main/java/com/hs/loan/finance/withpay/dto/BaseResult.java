package com.hs.loan.finance.withpay.dto;

import java.io.Serializable;

/**
 * 基类结果
 * @author hwen
 *
 */
public class BaseResult implements Serializable{
	private static final long serialVersionUID = -3203502650437658253L;
	
	/*** 本次请求响应状态 */
	private String retCode;
	/*** 响应的消息 */
	private String errMsg;
	
	public BaseResult() {}
	public BaseResult(String retCode, String errMsg) {
		super();
		this.retCode = retCode;
		this.errMsg = errMsg;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
