package com.hs.loan.finance.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * 对象
 * 
 * @author autocreate
 * @create 2016-09-26
 */
public class AppChanFstdateImportDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String loanNo;
	private String fstDate;

	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date instDate;

	// 构造函数
	public AppChanFstdateImportDto() {
	}
	// getter和setter方法

	 

	public String getFstDate() {
		return fstDate;
	}

	public String getLoanNo() {
		return loanNo;
	}



	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}



	public void setFstDate(String fstDate) {
		this.fstDate = fstDate;
	}

	public Date getInstDate() {
		return instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}