package com.hs.loan.finance.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单笔代扣 供催收 提前结清使用
 * @author hmzhou
 *
 */
public class SingleOtherBusiDto  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*** 分期编号 */
	private String loanNo = "";
	/*** 身份证号码 */
	private String certNo = "";
	/*** 渠道代码 */
	private String chanNo = "";
	/*** 来源表主键 */
	private String id;
	
	/*** 开户银行 */
	private String bankNo;
	private String bankName;
	/*** 扣款帐户名 */
	private String acctName;
	/*** 扣款帐号 */
	private String acctNo;
	/**代扣总金额*/
	private BigDecimal transAmtTotal;
	/***扣款渠道*/
	private String tranType ="";
	/*** 备注 */
	private String remark;
	/*** 保留域1 */
	private String reserve1;
	/*** 保留域2 */
	private String reserve2;
	/*** 保留域3 */
	private String reserve3;
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getChanNo() {
		return chanNo;
	}
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public BigDecimal getTransAmtTotal() {
		return transAmtTotal;
	}
	public void setTransAmtTotal(BigDecimal transAmtTotal) {
		this.transAmtTotal = transAmtTotal;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	
}
