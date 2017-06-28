package com.nm.model;

import java.io.Serializable;
import java.util.List;

public class CustContctInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 客户联系信息
	 */
	private CustContctModel custcontctmodel;
	/**
	 * 其它联系人信息
	 */
	private List<CustOtherContctInfoModel> custOtherContctInfoList;
	public CustContctModel getCustcontctmodel() {
		return custcontctmodel;
	}
	public void setCustcontctmodel(CustContctModel custcontctmodel) {
		this.custcontctmodel = custcontctmodel;
	}
	public List<CustOtherContctInfoModel> getCustOtherContctInfoList() {
		return custOtherContctInfoList;
	}
	public void setCustOtherContctInfoList(List<CustOtherContctInfoModel> custOtherContctInfoList) {
		this.custOtherContctInfoList = custOtherContctInfoList;
	}

}
