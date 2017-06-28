package com.hs.loan.customer.dto;


import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * APP_客户来电记录 对象
 * @author autocreate
 * @create 2016-07-07
 */
public class AppCustCallRegisterDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(min=1,max=40,message="表主键长度不能超过40")
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空") 
	@Size( max = 32,message="客户编号超长")// 长度或大小范围
  	private String custNo ; 
    
    /*** 客户姓名 */
	@NotBlank(message="客户姓名不能为空") 
	@Size( max = 40,message="客户姓名超长")// 长度或大小范围
  	private String custName ; 
    
    /*** 来电号码 */
	@NotBlank(message="来电号码不能为空") 
	@Size( max = 32,message="来电号码超长")// 长度或大小范围
  	private String callNo ; 
    
    /*** 来电时间 */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	@NotNull(message="来电时间不能为空") 
  	@Past(message="来电时间必须早于当前时间")
  	private Date callTime ; 
    
    /*** 来电内容 */
  	@Size( max = 128)// 长度或大小范围
  	private String callContent ; 
    
    /*** 创建时间 */
//  	@NotNull (message="创建时间不能为空")
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ;
  	
  	 /*** 来电类型 */
  	@Size( max = 100)// 长度或大小范围
  	private String queryType ; 
  	
    /*** 来电说明 */
  	@Size( max = 400)// 长度或大小范围
  	private String queryTemp ; 

    //构造函数
    public AppCustCallRegisterDto(){}

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
	 * 获取 来电号码
	 * @return String
	 */
	public String getCallNo() {
		return callNo;
	}

	/**
	 * 设置 来电号码
	 * @param callNo
	 */
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}

    
    /**
	 * 获取 来电时间
	 * @return Date
	 */
	public Date getCallTime() {
		return callTime;
	}

	/**
	 * 设置 来电时间
	 * @param callTime
	 */
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

    
    /**
	 * 获取 来电内容
	 * @return String
	 */
	public String getCallContent() {
		return callContent;
	}

	/**
	 * 设置 来电内容
	 * @param callContent
	 */
	public void setCallContent(String callContent) {
		this.callContent = callContent;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryTemp() {
		return queryTemp;
	}

	public void setQueryTemp(String queryTemp) {
		this.queryTemp = queryTemp;
	}

}