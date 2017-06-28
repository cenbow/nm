package com.hs.loan.finance.bo;


/**
 * 单笔代扣结果vo
 * @author hwen
 *
 */
public class SingleDkResultBo extends BaseResultBo{
	private static final long serialVersionUID = -978990424591282539L;
	
	/*** 交易代码  */
	private String trxCode;
	/*** 交易流水号 ,同请求流水号  */
	private String reqSn;
	/*** 交易结果明细 */
	private RetItemBo retItem;
	
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
	public RetItemBo getRetItem() {
		return retItem;
	}
	public void setRetItem(RetItemBo retItem) {
		this.retItem = retItem;
	}
}
