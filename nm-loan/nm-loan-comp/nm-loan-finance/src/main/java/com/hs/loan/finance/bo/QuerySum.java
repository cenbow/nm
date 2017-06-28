package com.hs.loan.finance.bo;

/**
 * 查询摘要
 * @author hwen
 *
 */
public class QuerySum {

	/*** 交易流水号  	非空*/
	private String reqSn;
	/*** 要查询的交易流水 	非空*/
	private String querySn;
	/*** 查询的备注 	可空*/
	private String queryRemark;
	
	/*** 渠道号 	可空*/
	private String chalCode;
	/*** 扣款方向 	可空*/
	private String direction;
	
	
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
	public String getChalCode() {
		return chalCode;
	}
	public void setChalCode(String chalCode) {
		this.chalCode = chalCode;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
