package com.nm.model;

import com.hs.loan.prod.dto.PubProdFeeDto;

import java.io.Serializable;
import java.util.List;

public class PubProdFeeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<PubProdFeeDto> pubProdFeeList;
	private List<InstNumInfoModel> instNumInfoList;
	public List<PubProdFeeDto> getPubProdFeeList() {
		return pubProdFeeList;
	}
	public void setPubProdFeeList(List<PubProdFeeDto> pubProdFeeList) {
		this.pubProdFeeList = pubProdFeeList;
	}
	public List<InstNumInfoModel> getInstNumInfoList() {
		return instNumInfoList;
	}
	public void setInstNumInfoList(List<InstNumInfoModel> instNumInfoList) {
		this.instNumInfoList = instNumInfoList;
	}

}
