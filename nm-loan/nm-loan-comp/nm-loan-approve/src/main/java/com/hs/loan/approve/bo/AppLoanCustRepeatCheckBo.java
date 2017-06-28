/**
 * 
 */
package com.hs.loan.approve.bo;

import java.util.Date;

/**
 * 客户重复信息统计
 * @author anyine
 * @date 20160304
 */
public class AppLoanCustRepeatCheckBo {
	private String reLoanNo;//重复单号
	private String reTxt;//重复内容
	private String reName;//重复名称
	private Date applyDate;//申请时间
	private String custAccountName;//重复客户姓名
	private String aprovResult;//审批结果
	private String aprovDecision;//审批决策原因
	private String installNum;//总期数
	private String repayNum;//已还期数
	private String isoverdue;//逾期情况
	private String loanType;//渠道
	private String goodsType;//商品类型
	public String getReLoanNo() {
		return reLoanNo;
	}
	public void setReLoanNo(String reLoanNo) {
		this.reLoanNo = reLoanNo;
	}
	public String getReTxt() {
		return reTxt;
	}
	public void setReTxt(String reTxt) {
		this.reTxt = reTxt;
	}
	public String getReName() {
		return reName;
	}
	public void setReName(String reName) {
		this.reName = reName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getCustAccountName() {
		return custAccountName;
	}
	public void setCustAccountName(String custAccountName) {
		this.custAccountName = custAccountName;
	}
	public String getAprovResult() {
		return aprovResult;
	}
	public void setAprovResult(String aprovResult) {
		this.aprovResult = aprovResult;
	}
	public String getAprovDecision() {
		return aprovDecision;
	}
	public void setAprovDecision(String aprovDecision) {
		this.aprovDecision = aprovDecision;
	}
	public String getInstallNum() {
		return installNum;
	}
	public void setInstallNum(String installNum) {
		this.installNum = installNum;
	}
	public String getRepayNum() {
		return repayNum;
	}
	public void setRepayNum(String repayNum) {
		this.repayNum = repayNum;
	}
	public String getIsoverdue() {
		return isoverdue;
	}
	public void setIsoverdue(String isoverdue) {
		this.isoverdue = isoverdue;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	
}
