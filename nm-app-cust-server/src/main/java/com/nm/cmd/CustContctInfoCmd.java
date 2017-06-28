package com.nm.cmd;

import java.io.Serializable;
import java.util.List;

/**
 * 客户联系信息
 * @author lenovo
 *
 */
public class CustContctInfoCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 客户联系信息
	 */
	private CustContctCmd custcontctcmd;
	/**
	 * 其它联系人信息
	 */
	private List<CustOtherContctInfoCmd> custOtherContctInfoList;
	public CustContctCmd getCustcontctcmd() {
		return custcontctcmd;
	}
	public void setCustcontctcmd(CustContctCmd custcontctcmd) {
		this.custcontctcmd = custcontctcmd;
	}
	public List<CustOtherContctInfoCmd> getCustOtherContctInfoList() {
		return custOtherContctInfoList;
	}
	public void setCustOtherContctInfoList(List<CustOtherContctInfoCmd> custOtherContctInfoList) {
		this.custOtherContctInfoList = custOtherContctInfoList;
	}

}
