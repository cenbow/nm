package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 银联返回
 * @author hwen
 *
 */
public class RetItemBo implements Serializable{
	private static final long serialVersionUID = -1677406052820884332L;
	/*** 记录序号  */
	private String sn;
	/*** 账号  */
	private String accountNo;
	/*** 账号名  */
	private String accountName;
	/*** 金额 单位为分*/
	private String amount;
	/*** 自定义用户号 */
	private String custUserid;
	/*** 返回码 */
	private String retCode;
	/*** 错误文本 */
	private String errMsg;
	/*** 备注 */
	private String remark;
	/*** 保留域1 */
	private String reserve1;
	/*** 保留域2 */
	private String reserve2;
	/*** 保留域3 */
	private String reserve3;
	
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCustUserid() {
		return custUserid;
	}
	public void setCustUserid(String custUserid) {
		this.custUserid = custUserid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	
}
