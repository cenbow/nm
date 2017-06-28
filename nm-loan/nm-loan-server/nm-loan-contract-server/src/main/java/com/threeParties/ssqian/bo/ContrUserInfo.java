package com.threeParties.ssqian.bo;

/**
 * 合同签约接收用户信息
 * @author jqiu
 */
public class ContrUserInfo {
	/**
	 * 用户邮箱(可选，邮箱可以为空，当前邮箱为空时，系统将用所提供手机号码)
	 */
	private String email;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 手机
	 */
	private String mobile;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
