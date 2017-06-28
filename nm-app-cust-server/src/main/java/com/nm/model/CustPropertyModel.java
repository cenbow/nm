package com.nm.model;

import java.io.Serializable;

/**
 * 客户资产信息
 * @author lenovo
 *
 */
public class CustPropertyModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 客户其它贷款信息
	 */
	private CustOtherLoanModel custotherloanmodel;
	/**
	 * 客户收入信息
	 */
	private CustAssetInfoModel custassetinfomodel;
	public CustOtherLoanModel getCustotherloanmodel() {
		return custotherloanmodel;
	}
	public void setCustotherloanmodel(CustOtherLoanModel custotherloanmodel) {
		this.custotherloanmodel = custotherloanmodel;
	}
	public CustAssetInfoModel getCustassetinfomodel() {
		return custassetinfomodel;
	}
	public void setCustassetinfomodel(CustAssetInfoModel custassetinfomodel) {
		this.custassetinfomodel = custassetinfomodel;
	}

}
