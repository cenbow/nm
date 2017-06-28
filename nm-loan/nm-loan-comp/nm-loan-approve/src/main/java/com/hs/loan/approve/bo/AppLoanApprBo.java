package com.hs.loan.approve.bo;

import com.hs.loan.approve.entity.AppLoanAppr;

public class AppLoanApprBo extends AppLoanAppr
{
	private static final long serialVersionUID = 1L;
	
	private String checkResult;
	private String goodsType;

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getCheckResult()
	{
		return checkResult;
	}

	public void setCheckResult(String checkResult) 
	{
		this.checkResult = checkResult;
	}
}
