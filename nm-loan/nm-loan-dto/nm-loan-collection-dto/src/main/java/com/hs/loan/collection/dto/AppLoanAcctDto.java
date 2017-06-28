package com.hs.loan.collection.dto;

import java.io.Serializable;

/**
 * 贷款客户银行及商品信息
 * 
 * @author zhangxiaoqiang
 *
 */
public class AppLoanAcctDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 贷款编号 **/
	private String loanNo;
	/** 贷款金额 **/
	private String loanAMT;
	/** 首还日期 **/
	private String fstRepayDate;
	/** 月还金额 **/
	private String mthRepayAmt;
	/** 还款银行 **/
	private String openOrg;
	/** 还款账户 **/
	private String acctNo;
	/** 分期期数 **/
	private String instNum;
	/** 商品类型 **/
	private String goodsType;
	/** 商品型号 **/
	private String brand;
	/** 商品品牌 **/
	private String marques;
	/** 商品价格 **/
	private String pric;
	/**商户地址*/
	private String branchAdd;
	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getLoanAMT() {
		return loanAMT;
	}

	public void setLoanAMT(String loanAMT) {
		this.loanAMT = loanAMT;
	}

	public String getFstRepayDate() {
		return fstRepayDate;
	}

	public void setFstRepayDate(String fstRepayDate) {
		this.fstRepayDate = fstRepayDate;
	}

	public String getMthRepayAmt() {
		return mthRepayAmt;
	}

	public void setMthRepayAmt(String mthRepayAmt) {
		this.mthRepayAmt = mthRepayAmt;
	}

	public String getOpenOrg() {
		return openOrg;
	}

	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getInstNum() {
		return instNum;
	}

	public void setInstNum(String instNum) {
		this.instNum = instNum;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMarques() {
		return marques;
	}

	public void setMarques(String marques) {
		this.marques = marques;
	}

	public String getPric() {
		return pric;
	}

	public void setPric(String pric) {
		this.pric = pric;
	}

	public String getBranchAdd() {
		return branchAdd;
	}

	public void setBranchAdd(String branchAdd) {
		this.branchAdd = branchAdd;
	}

}
