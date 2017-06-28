package com.hs.loan.finance.withpay.dto;

/**
 * 单笔代扣结果vo
 * @author hwen
 *
 */
public class SingleDkResultDto extends BaseResult{
	private static final long serialVersionUID = -978990424591282539L;
	
	/*** 交易代码  */
	private String trxCode;
	/*** 交易流水号 ,同请求流水号  */
	private String reqSn;
	/*** 交易结果明细 */
	private RetItem retItem;
	
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
	public RetItem getRetItem() {
		return retItem;
	}
	public void setRetItem(RetItem retItem) {
		this.retItem = retItem;
	}
}
