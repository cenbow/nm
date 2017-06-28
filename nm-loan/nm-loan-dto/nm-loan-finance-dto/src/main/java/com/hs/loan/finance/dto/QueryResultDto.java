package com.hs.loan.finance.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class QueryResultDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*** 交易代码 */
	private String trxCode;
	/*** 交易流水号 */
	private String reqSn;
	/*** 要查询的交易流水 */
	private String querySn;
	/*** 查询备注 */
	private String queryRemark;
	/*** 查询结果明细 */
	private List<RetItemDto> retItemLst = new ArrayList<RetItemDto>();
	
	
	public String getTrxCode() {
		return trxCode;
	}
	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}
	public String getReqSn() {
		return reqSn;
	}
	public void setReqSn(String reqSn) {
		this.reqSn = reqSn;
	}
	public String getQuerySn() {
		return querySn;
	}
	public void setQuerySn(String querySn) {
		this.querySn = querySn;
	}
	public String getQueryRemark() {
		return queryRemark;
	}
	public void setQueryRemark(String queryRemark) {
		this.queryRemark = queryRemark;
	}
	public List<RetItemDto> getRetItemLst() {
		return retItemLst;
	}
	public void setRetItemLst(List<RetItemDto> retItemLst) {
		this.retItemLst = retItemLst;
	}
	


}
