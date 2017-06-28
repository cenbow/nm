package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 回调信息，必要属性为空则不会进行回调推送
 * @author zwr
 *
 */
public class CallBackInfo implements Serializable{
	private static final long serialVersionUID = 1831382967079800060L;
	/***  老系统的action */
	private String action;
	/*** 老系统中action中的method */
	private String method;
	/*** 消息推送地址 */
	private String webUrl;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
}
