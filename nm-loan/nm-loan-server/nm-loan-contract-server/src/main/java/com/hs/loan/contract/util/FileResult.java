package com.hs.loan.contract.util;

import java.io.Serializable;

/**
 * SYS 2015-04-08 文件服务器返回的信息
 * @author zwr
 *
 */
public class FileResult implements Serializable {
	private static final long serialVersionUID = 1458562130714716526L;
	
	/**
	 * 返回消息
	 */
	private String msg;
	
	/**
	 * 返回的code
	 * 100 保存成功
	 * 101 保存失败
	 */
	private String code;
	
	/**
	 * 返回的信息体
	 */
	private Object body;
	
	public FileResult(){}

	public FileResult(String msg, String code, Object body) {
		this.msg = msg;
		this.code = code;
		this.body = body;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
	
	
	
}
