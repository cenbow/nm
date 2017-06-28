package com.hs.loan.approve.bo;

import java.io.Serializable;

import com.hs.loan.approve.entity.AppLoanFundMatch;

public class AppLoanFundMatchBo extends AppLoanFundMatch implements Serializable{
	private static final long serialVersionUID = 1L;

	
	/***合同URL */
  	private String contractUrl;

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}
  	
  	
}
