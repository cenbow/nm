package com.hs.loan.approv.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

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
  	@NotBlank(message="分期编号不能为空")
  	@Size(max=40,message="分期编号超长")
  	private String loanNo ; 
    
    /*** 客户编号 */
  	@NotBlank(message="客户编号不能为空")
  	@Size(max=40,message="客户编号超长")
  	private String custNo ; 
    
    /*** 客户姓名 */
  	@NotBlank(message="客户姓名不能为空")
  	@Size(max=80,message="客户姓名超长")
  	private String custName ; 
    
    /*** 进件时间 */
  	private Date instDate ; 
    
    /*** 匹配类型 */
  	@NotBlank(message="匹配类型不能为空")
  	@Size(max=10,message="匹配类型超长")
  	private String matchTyp ; 
    
    /*** 匹配人 */
  	@NotBlank(message="匹配人不能为空")
  	@Size(max=40,message="匹配人超长")
  	private String matchPsn ; 
    
    /*** 匹配人姓名 */
  	@NotBlank(message="匹配人姓名不能为空")
  	@Size(max=80,message="匹配人姓名超长")
  	private String matchName ; 
    
    /*** 匹配时间 */
  	private Date matchDate ; 
    
    /*** 匹配资方 */
  	@NotBlank(message="匹配资方不能为空")
  	@Size(max=10,message="匹配资方超长")
  	private String fundNo ; 
    
    /*** 状态 */
  	@NotBlank(message="状态不能为空")
  	@Size(max=10,message="状态超长")
  	private String stat ; 
    
    /*** 渠道号 */
  	@NotBlank(message="渠道号不能为空")
  	@Size(max=10,message="渠道号超长")
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	@NotBlank(message="渠道名称不能为空")
  	@Size(max=80,message="渠道名称超长")
  	private String chanName ; 

  	/*** 匹配结果 */
  	private String matchResult;
  	
  	/***分期金额 */
  	private String loanAmt;
  	
  	/*** 备注 */
  	private String remark;
  	
  	/***合同URL */
  	private String contractUrl;
  	
    public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}

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
	@JsonSerialize(using = DateTimeJsonSerializer.class) 
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
	@JsonSerialize(using = DateJsonSerializer.class)
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

	public String getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

}