package com.hs.loan.prod.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public class ProdFeeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Valid
	private List<PubProdFeeDto> prodFeeDtos;//包含所有的期数费用值
	@Valid
	private List<PubRepayFeeConfDto> feeDtos;//费用项

	public List<PubProdFeeDto> getProdFeeDtos() {
		return prodFeeDtos;
	}

	public void setProdFeeDtos(List<PubProdFeeDto> prodFeeDtos) {
		this.prodFeeDtos = prodFeeDtos;
	}

	public List<PubRepayFeeConfDto> getFeeDtos() {
		return feeDtos;
	}

	public void setFeeDtos(List<PubRepayFeeConfDto> feeDtos) {
		this.feeDtos = feeDtos;
	}

	 
}
