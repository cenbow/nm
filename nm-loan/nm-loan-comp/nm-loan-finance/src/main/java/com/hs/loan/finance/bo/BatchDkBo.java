package com.hs.loan.finance.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 批量代扣vo
 * @author hejian
 *
 */
public class BatchDkBo implements Serializable {
	private static final long serialVersionUID = -6232753128495091521L;
	
	/*** 商户信息 */
	private MerInfo merInfo;
	/*** 交易摘要 */
	private TransSum transSum;
	/*** 交易明细列表 */
	private List<TransItem> transItemLst = new ArrayList<TransItem>();
	/*** 推送消息给客户端的必要信息，为空则不会推送*/
	private CallBackInfo callBackInfo;
	
	public MerInfo getMerInfo() {
		return merInfo;
	}
	public void setMerInfo(MerInfo merInfo) {
		this.merInfo = merInfo;
	}
	public TransSum getTransSum() {
		return transSum;
	}
	public void setTransSum(TransSum transSum) {
		this.transSum = transSum;
	}
	public List<TransItem> getTransItemLst() {
		return transItemLst;
	}
	public void setTransItemLst(List<TransItem> transItemLst) {
		this.transItemLst = transItemLst;
	}
	public CallBackInfo getCallBackInfo() {
		return callBackInfo;
	}
	public void setCallBackInfo(CallBackInfo callBackInfo) {
		this.callBackInfo = callBackInfo;
	}
	
}
