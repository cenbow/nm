package com.hs.loan.finance.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 单笔代扣页面数据VO
 * @author hmzhou
 *
 */
public class SingleRepayBo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*** 分期编号 */
	private String loanNo = "";
	/*** 身份证号码 */
	private String certNo = "";
	/*** 执行期数 */
	private Integer repayNum = 0;
	/*** 账单日 */
	private String repayDate = "";
	/*** 业务日期 */
	private String busnDate = "";
	/*** 渠道代码 */
	private String chanNo = "";
	/*** 页面来源：代扣/还款计划登记(提前结清)/催收还款/催收结清/委外 */
	private String pageSource = "";
	/*** 来源表主键 */
	private String id;
	
	/*** 创建时间 */
	private Date instDate;
	/*** 更新时间 */
	private Date updtDate;

	
	//20160316 by hwen 
	/*** 扣款状态*/ //扣款状态:未扣款-20109001/扣款中-20109002/扣款成功-20109003/扣款失败-20109004/取消-20109005/信托扣款成功-20109006/平台扣款成功-20109007
	private String  withStat;
	/*** 开户银行 */
	private String bankNo;
	private String bankName;
	/*** 扣款帐户名 */
	private String acctName;
	/*** 扣款帐号 */
	private String acctNo;
	/**应还总金额*/
	private BigDecimal totlAmt;
	/**当日应还金额*/
	private BigDecimal dayRcvAmt;
	/**当前应还金额*/
	private BigDecimal curRcvAmt;
	/**资方总额*/
	private BigDecimal fundTotlAmt;
	/**当日资方余额*/
	private BigDecimal fundDayRcvAmt;
	/**当前资方余额*/
	private BigDecimal fundCurRcvAmt;
	
	/**交易金额*/
	private BigDecimal tranAmt;
	/**交易时间*/
	private Date tranTime;
	
	 /*** 入账账号 */
  	private String inAcctNo ; 
    
    /*** 入账户名 */
  	private String inAcctName ; 
  	
  	/*** 交易方 */
  	private String tranObj ; 
  	
  	private String tranType;
  	/**
  	 * 快付通协议号
  	 */
	private String noAgree;
	/***交易渠道*/
	private String tranChan;
	
	private String phoneNo;
	
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
	public BigDecimal getTranAmt() {
		return tranAmt;
	}
	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}
	public Date getTranTime() {
		return tranTime;
	}
	public void setTranTime(Date tranTime) {
		this.tranTime = tranTime;
	}
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
	public Integer getRepayNum() {
		return repayNum;
	}
	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public String getBusiDate() {
		return busnDate;
	}
	public void setBusiDate(String busiDate) {
		this.busnDate = busiDate;
	}
	public String getChanNo() {
		return chanNo;
	}
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}
	public String getPageSource() {
		return pageSource;
	}
	public void setPageSource(String pageSource) {
		this.pageSource = pageSource;
	}
	
	public String getBusnDate() {
		return busnDate;
	}
	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getInstDate() {
		return instDate;
	}
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}
	public Date getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}
	public String getWithStat() {
		return withStat;
	}
	public void setWithStat(String withStat) {
		this.withStat = withStat;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
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
	public BigDecimal getTotlAmt() {
		return totlAmt;
	}
	public void setTotlAmt(BigDecimal totlAmt) {
		this.totlAmt = totlAmt;
	}
	public BigDecimal getDayRcvAmt() {
		return dayRcvAmt;
	}
	public void setDayRcvAmt(BigDecimal dayRcvAmt) {
		this.dayRcvAmt = dayRcvAmt;
	}
	public BigDecimal getCurRcvAmt() {
		return curRcvAmt;
	}
	public void setCurRcvAmt(BigDecimal curRcvAmt) {
		this.curRcvAmt = curRcvAmt;
	}
	public BigDecimal getFundTotlAmt() {
		return fundTotlAmt;
	}
	public void setFundTotlAmt(BigDecimal fundTotlAmt) {
		this.fundTotlAmt = fundTotlAmt;
	}
	public BigDecimal getFundDayRcvAmt() {
		return fundDayRcvAmt;
	}
	public void setFundDayRcvAmt(BigDecimal fundDayRcvAmt) {
		this.fundDayRcvAmt = fundDayRcvAmt;
	}
	public BigDecimal getFundCurRcvAmt() {
		return fundCurRcvAmt;
	}
	public void setFundCurRcvAmt(BigDecimal fundCurRcvAmt) {
		this.fundCurRcvAmt = fundCurRcvAmt;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getNoAgree() {
		return noAgree;
	}
	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}
	public String getTranChan() {
		return tranChan;
	}
	public void setTranChan(String tranChan) {
		this.tranChan = tranChan;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
}
