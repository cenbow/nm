package com.hs.loan.prod.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProFeeCalResultDto implements Serializable {

	/**
	 * 产品试算
	 */
	private static final long serialVersionUID = 1L;

	
	private BigDecimal paymentAmt;//首付金额

	private BigDecimal loanAmt;//分期金额
	
	private List<ProdFeeResultBassDto> feeResultBassDtos;//每期费用

	
	public BigDecimal getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(BigDecimal paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public List<ProdFeeResultBassDto> getFeeResultBassDtos() {
		return feeResultBassDtos;
	}

	public void setFeeResultBassDtos(List<ProdFeeResultBassDto> feeResultBassDtos) {
		this.feeResultBassDtos = feeResultBassDtos;
	}

	public BigDecimal getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}
}
