package com.hs.loan.collection.dto;

import java.io.Serializable;
import java.util.List;
/**
 * 委外案件信息
 * @author IT-009
 *
 */
public class LoanOutSourceBaseInDto implements Serializable{
	private static final long serialVersionUID = 1L;
	/**分期编号***/
	private String loanNo;
	/**客户姓名***/
	private String custName;
	/**委外单位名称***/
	private String outUnitName;
	/**是否分配***/
	private String isDistrbut;
	/**分配时间***/
	private String distrbutStart;
	/**分配时间***/
	private String distrbutEnd;
	/**是否结清***/
	private String isRepayd;
	
	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getOutUnitName() {
		return outUnitName;
	}

	public void setOutUnitName(String outUnitName) {
		this.outUnitName = outUnitName;
	}

	public String getIsDistrbut() {
		return isDistrbut;
	}

	public void setIsDistrbut(String isDistrbut) {
		this.isDistrbut = isDistrbut;
	}

	public String getDistrbutStart() {
		return distrbutStart;
	}

	public void setDistrbutStart(String distrbutStart) {
		this.distrbutStart = distrbutStart;
	}

	public String getDistrbutEnd() {
		return distrbutEnd;
	}

	public void setDistrbutEnd(String distrbutEnd) {
		this.distrbutEnd = distrbutEnd;
	}

	public String getIsRepayd() {
		return isRepayd;
	}

	public void setIsRepayd(String isRepayd) {
		this.isRepayd = isRepayd;
	}
 
}
