package com.hs.loan.cust.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户联系信息和客户其他联系人信息dto
 * @author zwr
 *
 */
public class CustContctPersonDto implements Serializable{
	private static final long serialVersionUID = 893178258044240026L;

	//客户联系信息
	private CustContctInfoDto custContctInfoDto;
	//客户其他联系人信息
	private List<CustContctOtherDto> custContctOtherDtoLst = new ArrayList<>(0);
	
	public CustContctInfoDto getCustContctInfoDto() {
		return custContctInfoDto;
	}
	public void setCustContctInfoDto(CustContctInfoDto custContctInfoDto) {
		this.custContctInfoDto = custContctInfoDto;
	}
	public List<CustContctOtherDto> getCustContctOtherDtoLst() {
		return custContctOtherDtoLst;
	}
	public void setCustContctOtherDtoLst(
			List<CustContctOtherDto> custContctOtherDtoLst) {
		this.custContctOtherDtoLst = custContctOtherDtoLst;
	}
	
}
