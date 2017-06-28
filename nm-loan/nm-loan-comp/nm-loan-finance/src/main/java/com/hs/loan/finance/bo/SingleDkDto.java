package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 单笔代扣vo
 * @author hwen
 *
 */
public class SingleDkDto implements Serializable {
	private static final long serialVersionUID = -1962501616729028516L;
	
	
	/*** 商户基本信息 */
	private MerInfo merInfo;
	/*** 交易摘要 */
	private TransSum transSum;
	/*** 交易明细 */
	private TransItem transItem;
	
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
	public TransItem getTransItem() {
		return transItem;
	}
	public void setTransItem(TransItem transItem) {
		this.transItem = transItem;
	}
	
}
