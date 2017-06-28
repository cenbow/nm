package com.hs.loan.approv.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期资金匹配 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class LoanFundMatchDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String matchId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 进件时间 */
  	private Date instDate ; 
    
    /*** 匹配类型 */
  	private String matchTyp ; 
    
    /*** 匹配人 */
  	private String matchPsn ; 
    
    /*** 匹配人姓名 */
  	private String matchName ; 
    
    /*** 匹配时间 */
  	private Date matchDate ; 
    
    /*** 匹配资方 */
  	private String fundNo ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 渠道号 */
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	private String chanName ; 

    //构造函数
    public LoanFundMatchDto(){}

    //getter和setter方法
    
    /**
	 * 获取 ID
	 * @return String
	 */
	public String getMatchId() {
		return matchId;
	}

	/**
	 * 设置 ID
	 * @param matchId
	 */
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

    
    /**
	 * 获取 分期编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 客户编号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 客户姓名
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户姓名
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 进件时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 进件时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 匹配类型
	 * @return String
	 */
	public String getMatchTyp() {
		return matchTyp;
	}

	/**
	 * 设置 匹配类型
	 * @param matchTyp
	 */
	public void setMatchTyp(String matchTyp) {
		this.matchTyp = matchTyp;
	}

    
    /**
	 * 获取 匹配人
	 * @return String
	 */
	public String getMatchPsn() {
		return matchPsn;
	}

	/**
	 * 设置 匹配人
	 * @param matchPsn
	 */
	public void setMatchPsn(String matchPsn) {
		this.matchPsn = matchPsn;
	}

    
    /**
	 * 获取 匹配人姓名
	 * @return String
	 */
	public String getMatchName() {
		return matchName;
	}

	/**
	 * 设置 匹配人姓名
	 * @param matchName
	 */
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

    
    /**
	 * 获取 匹配时间
	 * @return Date
	 */
	public Date getMatchDate() {
		return matchDate;
	}

	/**
	 * 设置 匹配时间
	 * @param matchDate
	 */
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

    
    /**
	 * 获取 匹配资方
	 * @return String
	 */
	public String getFundNo() {
		return fundNo;
	}

	/**
	 * 设置 匹配资方
	 * @param fundNo
	 */
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}

    
    /**
	 * 获取 状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 渠道号
	 * @return String
	 */
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 渠道号
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

    
    /**
	 * 获取 渠道名称
	 * @return String
	 */
	public String getChanName() {
		return chanName;
	}

	/**
	 * 设置 渠道名称
	 * @param chanName
	 */
	public void setChanName(String chanName) {
		this.chanName = chanName;
	}

}