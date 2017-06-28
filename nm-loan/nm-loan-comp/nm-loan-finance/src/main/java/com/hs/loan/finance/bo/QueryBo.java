package com.hs.loan.finance.bo;


/**
 * 查询交易vo
 * @author hejian
 *
 */
public class QueryBo {
	/*** 商户信息 */
	private MerInfo merInfo;
	/*** 查询摘要 */
	private QuerySum querySum;
	/*** 推送消息给客户端的必要信息，为空则不会推送*/
	private CallBackInfo callBackInfo;
	
	public MerInfo getMerInfo() {
		return merInfo;
	}
	public void setMerInfo(MerInfo merInfo) {
		this.merInfo = merInfo;
	}
	public QuerySum getQuerySum() {
		return querySum;
	}
	public void setQuerySum(QuerySum querySum) {
		this.querySum = querySum;
	}
	public CallBackInfo getCallBackInfo() {
		return callBackInfo;
	}
	public void setCallBackInfo(CallBackInfo callBackInfo) {
		this.callBackInfo = callBackInfo;
	}
	
}
