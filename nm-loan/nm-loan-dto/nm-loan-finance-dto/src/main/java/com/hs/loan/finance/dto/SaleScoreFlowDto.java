package com.hs.loan.finance.dto;

import java.io.Serializable;

public class SaleScoreFlowDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/*** 员工编号 */
	private String staffNo;
	
	/*** 员工姓名 */
	private String staffName;
	
	/*** 礼品号 */
	private String giftNo;
	
	/*** 礼品名称 */
	private String giftSubject;
	
	/*** 个人兑换次数 */
	private String tranCnt;
	
	/*** 交易日期 */
	private String tranDate;
	
	/*** 兑换分数 */
	private String tranScore;

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getGiftNo() {
		return giftNo;
	}

	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}

	public String getGiftSubject() {
		return giftSubject;
	}

	public void setGiftSubject(String giftSubject) {
		this.giftSubject = giftSubject;
	}

	public String getTranCnt() {
		return tranCnt;
	}

	public void setTranCnt(String tranCnt) {
		this.tranCnt = tranCnt;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranScore() {
		return tranScore;
	}

	public void setTranScore(String tranScore) {
		this.tranScore = tranScore;
	}
}
