package com.hs.loan.finance.dto;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 *  对象
 * @author autocreate
 * @create 2016-03-17
 */
public class AccCapTranDtlDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 明细id  */
  	private String id ; 
    
    /*** 代款编号 */
  	private String loanNo ; 
    
    /*** 扣款期数  */
  	private String repayNum ; 
    
    /*** 发起文件名 */
  	private String logId ; 
    
    /*** 代扣表id  */
  	private String withId ; 
    
    /*** 是否批量 */
  	private String batFlag ; 
    
    /*** 扣款账号 */
  	private String acctNo ; 
    
    /*** 扣款账户名 */
  	private String acctName ; 
    
    /*** 扣款日期 */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date withDate ; 
    
    /*** 交易金额 */
  	private java.math.BigDecimal shdWithAmt ; 
    
    /*** 发起金额 */
  	private java.math.BigDecimal actWithAmt ; 
    
    /***  */
  	private String fundAcctNo ; 
    
    /***  */
  	private String fundAcctName ; 
    
    /*** 返回状态 */
  	private String retStat ; 
    
    /*** 返回结果 */
  	private String retMsg ; 
    
    /***  */
  	private String remark ; 

    //构造函数
    public AccCapTranDtlDto(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRepayNum() {
		return repayNum;
	}

	/**
	 * 设置 
	 * @param repayNum
	 */
	public void setRepayNum(String repayNum) {
		this.repayNum = repayNum;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 设置 
	 * @param logId
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getWithId() {
		return withId;
	}

	/**
	 * 设置 
	 * @param withId
	 */
	public void setWithId(String withId) {
		this.withId = withId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBatFlag() {
		return batFlag;
	}

	/**
	 * 设置 
	 * @param batFlag
	 */
	public void setBatFlag(String batFlag) {
		this.batFlag = batFlag;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getWithDate() {
		return withDate;
	}

	/**
	 * 设置 
	 * @param withDate
	 */
	public void setWithDate(Date withDate) {
		this.withDate = withDate;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getShdWithAmt() {
		return shdWithAmt;
	}

	/**
	 * 设置 
	 * @param shdWithAmt
	 */
	public void setShdWithAmt(java.math.BigDecimal shdWithAmt) {
		this.shdWithAmt = shdWithAmt;
	}

    
    /**
	 * 获取 
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActWithAmt() {
		return actWithAmt;
	}

	/**
	 * 设置 
	 * @param actWithAmt
	 */
	public void setActWithAmt(java.math.BigDecimal actWithAmt) {
		this.actWithAmt = actWithAmt;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getFundAcctNo() {
		return fundAcctNo;
	}

	/**
	 * 设置 
	 * @param fundAcctNo
	 */
	public void setFundAcctNo(String fundAcctNo) {
		this.fundAcctNo = fundAcctNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getFundAcctName() {
		return fundAcctName;
	}

	/**
	 * 设置 
	 * @param fundAcctName
	 */
	public void setFundAcctName(String fundAcctName) {
		this.fundAcctName = fundAcctName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRetStat() {
		return retStat;
	}

	/**
	 * 设置 
	 * @param retStat
	 */
	public void setRetStat(String retStat) {
		this.retStat = retStat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRetMsg() {
		return retMsg;
	}

	/**
	 * 设置 
	 * @param retMsg
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}