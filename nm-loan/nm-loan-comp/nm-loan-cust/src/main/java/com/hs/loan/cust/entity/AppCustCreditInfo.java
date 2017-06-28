package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户信用卡信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustCreditInfo implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
    
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 发卡行 */
  	private String issuCardBank ; 
    
    /*** 卡号 */
  	private String cardNum ; 
    
    /*** 额度 */
  	private String limitAmt ; 
    
    /*** 信用卡每月还款日 */
  	private String creditMthDay ; 
    
    /*** 平均消费金额 */
  	private String avgAmt ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public AppCustCreditInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * 获取 发卡行
	 * @return String
	 */
	public String getIssuCardBank() {
		return issuCardBank;
	}

	/**
	 * 设置 发卡行
	 * @param issuCardBank
	 */
	public void setIssuCardBank(String issuCardBank) {
		this.issuCardBank = issuCardBank;
	}

    
    /**
	 * 获取 卡号
	 * @return String
	 */
	public String getCardNum() {
		return cardNum;
	}

	/**
	 * 设置 卡号
	 * @param cardNum
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

    
    /**
	 * 获取 额度
	 * @return String
	 */
	public String getLimitAmt() {
		return limitAmt;
	}

	/**
	 * 设置 额度
	 * @param limitAmt
	 */
	public void setLimitAmt(String limitAmt) {
		this.limitAmt = limitAmt;
	}

    
    /**
	 * 获取 信用卡每月还款日
	 * @return String
	 */
	public String getCreditMthDay() {
		return creditMthDay;
	}

	/**
	 * 设置 信用卡每月还款日
	 * @param creditMthDay
	 */
	public void setCreditMthDay(String creditMthDay) {
		this.creditMthDay = creditMthDay;
	}

    
    /**
	 * 获取 平均消费金额
	 * @return String
	 */
	public String getAvgAmt() {
		return avgAmt;
	}

	/**
	 * 设置 平均消费金额
	 * @param avgAmt
	 */
	public void setAvgAmt(String avgAmt) {
		this.avgAmt = avgAmt;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    /**
	 * 获取 开始日期
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


}