package com.nm.model;

import java.io.Serializable;

/**
 * 医美网点
 * @author lenovo
 *
 */
public class InFirmaryInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String branchNo;
	private String branchName;
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
