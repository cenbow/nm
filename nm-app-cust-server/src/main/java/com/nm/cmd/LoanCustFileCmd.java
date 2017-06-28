package com.nm.cmd;

import java.io.Serializable;

public class LoanCustFileCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	private String attTyp;
    private String mediaId;
    private String loanNo;
    private String custNo;
    private String attNo;
	public String getAttTyp() {
		return attTyp;
	}
	public void setAttTyp(String attTyp) {
		this.attTyp = attTyp;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getAttNo() {
		return attNo;
	}
	public void setAttNo(String attNo) {
		this.attNo = attNo;
	}
    
}
