package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 基类结果
 * @author hwen
 *
 */
public class BaseResultBo implements Serializable{
	private static final long serialVersionUID = -3203502650437658253L;
	
	/*** 本次请求响应状态 */
	private String retCode;
	/*** 响应的消息 */
	private String errMsg;
	
	public BaseResultBo() {}
	public BaseResultBo(String retCode, String errMsg) {
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
