package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户信用卡信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustCreditInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
	/*** ID */
//	@NotBlank(message="表语键不能为空") 
//	@Size( max = 40,message="表语键超长")// 长度或大小范围
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空") 
	@Size( max = 32,message="客户编号超长")// 长度或大小范围
  	private String custNo ; 
    
    /*** 发卡行 */
	@NotBlank(message="发卡行不能为空") 
	@Size( max = 8,message="发卡行超长")// 长度或大小范围
  	private String issuCardBank ; 
    
    /*** 卡号 */
	@NotBlank(message="卡号不能为空") 
	@Size( max = 40,message="卡号超长")// 长度或大小范围
  	private String cardNum ; 
    
    /*** 额度 */
	@NotBlank(message="额度不能为空") 
	@Size( max = 8,message="额度超长")// 长度或大小范围
  	private String limitAmt ; 
    
    /*** 信用卡每月还款日 */
	@NotBlank(message=" 信用卡每月还款日不能为空") 
	@Size( max = 8,message="信用卡每月还款日超长")// 长度或大小范围
  	private String creditMthDay ; 
    
    /*** 平均消费金额 */
	@NotBlank(message="平均消费金额不能为空") 
	@Size( max = 8,message="平均消费金额超长")// 长度或大小范围
  	private String avgAmt ; 
    
    /*** 备注 */
	@Size( max = 128,message="备注超长")// 长度或大小范围
  	private String remark ; 
    
    /*** 开始日期 */
  	@NotNull (message="开始日期不能为空")
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	@NotNull (message="结束日期不能为空")
  	private Date endDate ; 

    //构造函数
    public CustCreditInfoDto(){}

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
	 @JsonSerialize(using = DateTimeJsonSerializer.class) 
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
	 @JsonSerialize(using = DateTimeJsonSerializer.class) 
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