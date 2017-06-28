package com.hs.loan.finance.bo;


/**
 * 批量代扣结果vo
 * 
 * @author hejian
 *
 */
public class BatchDkResultBo extends BaseResultBo{
	private static final long serialVersionUID = 4556418148174112516L;
	
	/*** 交易代码 */
	private String trxCode;
	/*** 交易流水号 */
	private String reqSn;
	
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

}
