package com.hs.loan.cust.dto;

import java.io.Serializable;

import javax.validation.Valid;

public class CustBaseInfoDto implements Serializable{
	private static final long serialVersionUID = -6047112201795016474L;

	/*** 客户基本信息 */
	@Valid
	private CustInfoDto custInfoDto;
	
	/*** 客户现联系信息*/
	//private CustContctInfoDto custContctInfoDto;
	
	/*** 客户现居住地址 */
	@Valid
	private CustLiveInfoDto custLiveInfoDto;
	/***客户来源信息*/
	private AppCustSourceInfoDto appCustSourceInfoDto;
	
	public CustInfoDto getCustInfoDto() {
		return custInfoDto;
	}
	public void setCustInfoDto(CustInfoDto custInfoDto) {
		this.custInfoDto = custInfoDto;
	}
	public CustLiveInfoDto getCustLiveInfoDto() {
		return custLiveInfoDto;
	}
	public void setCustLiveInfoDto(CustLiveInfoDto custLiveInfoDto) {
		this.custLiveInfoDto = custLiveInfoDto;
	}
//	public CustContctInfoDto getCustContctInfoDto() {
//		return custContctInfoDto;
//	}
//	public void setCustContctInfoDto(CustContctInfoDto custContctInfoDto) {
//		this.custContctInfoDto = custContctInfoDto;
//	}
	public AppCustSourceInfoDto getAppCustSourceInfoDto() {
		return appCustSourceInfoDto;
	}
	public void setAppCustSourceInfoDto(AppCustSourceInfoDto appCustSourceInfoDto) {
		this.appCustSourceInfoDto = appCustSourceInfoDto;
	}

	
}
