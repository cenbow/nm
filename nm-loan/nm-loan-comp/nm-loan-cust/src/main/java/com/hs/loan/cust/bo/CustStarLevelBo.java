package com.hs.loan.cust.bo;

import java.io.Serializable;

/**
 * 客户星级评估 bo
 * @author zwr
 *
 */
public class CustStarLevelBo implements Serializable {
	private static final long serialVersionUID = 8275953334687817081L;

	//星级定义
	private String[] levelDefine;
	
	//客户号
	private String custNo;
	
	//客户星级
	private String level;
	
	//可申请的分期(剩余授信额度)
	private String remainLimit;
	
	//客户评分
	private String score;
	
	//登记人
	private String handPersonName;
	
	//登记时间(yyyy-MM-dd)
	private String handDate;
	
	
	public String[] getLevelDefine() {
		return levelDefine;
	}
	public void setLevelDefine(String[] levelDefine) {
		this.levelDefine = levelDefine;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRemainLimit() {
		return remainLimit;
	}
	public void setRemainLimit(String remainLimit) {
		this.remainLimit = remainLimit;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}

	public String getHandPersonName() {
		return handPersonName;
	}
	public void setHandPersonName(String handPersonName) {
		this.handPersonName = handPersonName;
	}
	public String getHandDate() {
		return handDate;
	}
	public void setHandDate(String handDate) {
		this.handDate = handDate;
	}

	
}
