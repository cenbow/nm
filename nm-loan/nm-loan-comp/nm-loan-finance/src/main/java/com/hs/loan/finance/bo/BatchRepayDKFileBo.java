package com.hs.loan.finance.bo;

import java.math.BigDecimal;

/**
 * 批量代扣回盘 文件类型VO
 * 
 * @author
 *
 */
public class BatchRepayDKFileBo {
	/** 代扣表Id */
	private String dkId;
	/** 交易金额 */
	private BigDecimal amount;
	/** 返回码 */
	private String retCode;
	/** 返回信息 */
	private String retMsg;
	/** 渠道代码 */
	private String chalCode;
	/** 交易方：资方/平台 */
	private String direction;

	private String loanNo;

	private BigDecimal withAmt;
	private String repayType;

	private Integer bgnRepayNum;
	private Integer endRepayNum;

	private String repayDate;
	private String repayNum;
	private String acctNo;

	private String acctName;

	private String bankNo;

	/*** 入账账号 */
	private String inAcctNo;

	/*** 入账户名 */
	private String inAcctName;

	/*** 交易方 */
	private String tranObj;

	public String getInAcctNo() {
		return inAcctNo;
	}

	public void setInAcctNo(String inAcctNo) {
		this.inAcctNo = inAcctNo;
	}

	public String getInAcctName() {
		return inAcctName;
	}

	public void setInAcctName(String inAcctName) {
		this.inAcctName = inAcctName;
	}

	public String getTranObj() {
		return tranObj;
	}

	public void setTranObj(String tranObj) {
		this.tranObj = tranObj;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Integer getBgnRepayNum() {
		return bgnRepayNum;
	}

	public void setBgnRepayNum(Integer bgnRepayNum) {
		this.bgnRepayNum = bgnRepayNum;
	}

	public Integer getEndRepayNum() {
		return endRepayNum;
	}

	public void setEndRepayNum(Integer endRepayNum) {
		this.endRepayNum = endRepayNum;
	}

	public String getDkId() {
		return dkId;
	}

	public void setDkId(String dkId) {
		this.dkId = dkId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getWithAmt() {
		return withAmt;
	}

	public void setWithAmt(BigDecimal withAmt) {
		this.withAmt = withAmt;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
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

	public String getRepayNum() {
		return repayNum;
	}

	public void setRepayNum(String repayNum) {
		this.repayNum = repayNum;
	}

}
