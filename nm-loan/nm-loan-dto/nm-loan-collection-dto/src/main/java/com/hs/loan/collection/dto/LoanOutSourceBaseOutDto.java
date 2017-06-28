package com.hs.loan.collection.dto;

import java.io.Serializable;
import java.util.List;
/**
 * 委外案件信息
 * @author IT-009
 *
 */
public class LoanOutSourceBaseOutDto implements Serializable{
	private static final long serialVersionUID = 1L;
 
	private  LoanOutsourceDto loanOutsourceDto;

	public LoanOutsourceDto getLoanOutsourceDto() {
		return loanOutsourceDto;
	}

	public void setLoanOutsourceDto(LoanOutsourceDto loanOutsourceDto) {
		this.loanOutsourceDto = loanOutsourceDto;
	}	
	
}
