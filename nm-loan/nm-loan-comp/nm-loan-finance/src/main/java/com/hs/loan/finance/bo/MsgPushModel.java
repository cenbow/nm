package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 消息推送模型
 * @author zwr
 *
 */
public class MsgPushModel implements Serializable{
	private static final long serialVersionUID = -4700414160746207353L;
	
	/***  老系统的action */
	private String action;
	/*** 老系统中action中的method */
	private String method;
	/*** 消息推送地址 */
	private String webUrl;
	/*** 代扣交易号 */
	private String querySn;
	
	
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
	public String getQuerySn() {
		return querySn;
	}
	public void setQuerySn(String querySn) {
		this.querySn = querySn;
	}
	
}
