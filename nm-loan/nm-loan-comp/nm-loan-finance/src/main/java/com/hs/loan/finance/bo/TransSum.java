package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 交易摘要
 * @author zwr
 *
 */
public class TransSum implements Serializable{
	
	private static final long serialVersionUID = -6899642821101317472L;
	
	/*** 交易流水号 非空*/
	private String reqSn;
	/*** 交易总记录数 单笔代扣可以为空，其它非空*/
	private String totalItem;
	/*** 交易总金额 非空*/
	private String totalSum;
	
	/*** 渠道号 可空*/
	private String chalCode;
	/*** 扣款方向 可空*/
	private String direction;
	
	
	public String getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(String totalItem) {
		this.totalItem = totalItem;
	}
	public String getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(String totalSum) {
		this.totalSum = totalSum;
	}
	public String getReqSn() {
		return reqSn;
	}
	public void setReqSn(String reqSn) {
		this.reqSn = reqSn;
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
