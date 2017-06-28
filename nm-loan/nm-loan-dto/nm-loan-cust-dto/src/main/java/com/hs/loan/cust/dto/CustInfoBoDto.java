package com.hs.loan.cust.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息bo DTO
 * @author zwr
 *
 */
public class CustInfoBoDto extends CustInfoDto implements Serializable{
	private static final long serialVersionUID = 9172238151167573477L;

	/*** 评分 */
 	private String score ; 
 	
 	/*** 评级 */
 	private String level ; 
 	
 	/*** 分期次数 */
 	private java.math.BigDecimal loanCnt ;
 	
 	/*** 分期开始时间 */
 	private Date startTime;
 	
 	/*** 分期结束时间 */
 	private Date endTime;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public java.math.BigDecimal getLoanCnt() {
		return loanCnt;
	}

	public void setLoanCnt(java.math.BigDecimal loanCnt) {
		this.loanCnt = loanCnt;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
 	
}
