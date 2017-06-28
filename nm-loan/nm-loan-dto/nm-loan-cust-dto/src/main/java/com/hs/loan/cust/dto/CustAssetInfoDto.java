package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户资产信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustAssetInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(min=1,max=40,message="表主键长度不能超过40")// 长度或大小范围
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空") 
	@Size( max = 32,message="客户编号超长")// 长度或大小范围
  	private String custNo ; 
    
    /*** 收入来源 */
	@NotBlank(message="收入来源不能为空") 
	@Size( max = 8,message="收入来源超长")// 长度或大小范围
  	private String incomeSrc ; 
    
    /*** 发薪日 */
	@NotBlank(message="发薪日不能为空") 
	@Size( max = 8,message="发薪日超长")// 长度或大小范围
  	private String incomeDay ; 
    
    /*** 收入金额 */
	@NotBlank(message="收入金额不能为空") 
	@Size( max = 8,message="收入金额超长")// 长度或大小范围
  	private String incomeAmt ; 
    
    /*** 分红日 */
	@Size( max = 8,message="分红日超长")// 长度或大小范围
  	private String divdndDay ; 
    
    /*** 平均分红金额 */
	@Size( max = 8,message="平均分红金额超长")// 长度或大小范围
  	private String divdndAmt ; 
    
    /*** 其他收入来源 */
	@Size( max = 8,message="其他收入来源超长")// 长度或大小范围
  	private String incomeOther ; 
    
    /*** 其他收入来源金额(月) */
	@Size( max = 8,message="其他收入来源金额(月)超长")// 长度或大小范围
  	private String incomeOtherAmt ; 
    
    /*** 备注 */
	@Size( max = 128,message="备注超长")// 长度或大小范围
  	private String remark ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public CustAssetInfoDto(){}

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
	 * 获取 收入来源
	 * @return String
	 */
	public String getIncomeSrc() {
		return incomeSrc;
	}

	/**
	 * 设置 收入来源
	 * @param incomeSrc
	 */
	public void setIncomeSrc(String incomeSrc) {
		this.incomeSrc = incomeSrc;
	}

    
    /**
	 * 获取 发薪日
	 * @return String
	 */
	public String getIncomeDay() {
		return incomeDay;
	}

	/**
	 * 设置 发薪日
	 * @param incomeDay
	 */
	public void setIncomeDay(String incomeDay) {
		this.incomeDay = incomeDay;
	}

    
    /**
	 * 获取 收入金额
	 * @return String
	 */
	public String getIncomeAmt() {
		return incomeAmt;
	}

	/**
	 * 设置 收入金额
	 * @param incomeAmt
	 */
	public void setIncomeAmt(String incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

    
    /**
	 * 获取 分红日
	 * @return String
	 */
	public String getDivdndDay() {
		return divdndDay;
	}

	/**
	 * 设置 分红日
	 * @param divdndDay
	 */
	public void setDivdndDay(String divdndDay) {
		this.divdndDay = divdndDay;
	}

    
    /**
	 * 获取 平均分红金额
	 * @return String
	 */
	public String getDivdndAmt() {
		return divdndAmt;
	}

	/**
	 * 设置 平均分红金额
	 * @param divdndAmt
	 */
	public void setDivdndAmt(String divdndAmt) {
		this.divdndAmt = divdndAmt;
	}

    
    /**
	 * 获取 其他收入来源
	 * @return String
	 */
	public String getIncomeOther() {
		return incomeOther;
	}

	/**
	 * 设置 其他收入来源
	 * @param incomeOther
	 */
	public void setIncomeOther(String incomeOther) {
		this.incomeOther = incomeOther;
	}

    
    /**
	 * 获取 其他收入来源金额(月)
	 * @return String
	 */
	public String getIncomeOtherAmt() {
		return incomeOtherAmt;
	}

	/**
	 * 设置 其他收入来源金额(月)
	 * @param incomeOtherAmt
	 */
	public void setIncomeOtherAmt(String incomeOtherAmt) {
		this.incomeOtherAmt = incomeOtherAmt;
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
	 * 获取 创建时间
	 * @return Date
	 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 创建时间
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 更新时间
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}