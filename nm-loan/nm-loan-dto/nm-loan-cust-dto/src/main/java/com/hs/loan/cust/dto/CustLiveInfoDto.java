package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户居住信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustLiveInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
	/*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(max=40,message="表主键超长")
  	private String id ; 
    
    /*** 客户编号 */
//	@NotBlank (message="客户编号不能为空")
//	@Size(max=32,message="客户编号超长")
  	private String custNo ; 
    
    /*** 居住地址是否同户籍 */
	@Size(max=8,message="居住地址是否同户籍超长")
  	private String isRegLive ; 
    
    /*** 现地址居住时间 */
//	@NotBlank (message="现地址居住时间不能为空")
	@Size(max=32,message="现地址居住时间超长")
  	private String liveTimeSpan ; 
    
    /*** 居住地省/直辖市 */
	@NotBlank (message="居住地省/直辖市不能为空")
	@Size(max=32,message="居住地省/直辖市超长")
  	private String liveProv ;
  	
  	/*** 省/直辖市 名称 */
  	private String liveProvName;
  	
    /*** 居住市 */
  	@NotBlank (message="居住市不能为空")
	@Size(max=128,message="居住市超长")
  	private String liveCity ; 

  	/*** 市 名称 */
  	private String liveCityName;
  	
    /*** 居住区/县 */
  	@NotBlank (message="居住区/县不能为空")
	@Size(max=128,message="居住区/县超长")
  	private String liveArea ; 
  	
  	/*** 区/县 名称*/
  	private String liveAreaName;
    
    /*** 居住详细地址 */
  	@NotBlank (message="居住详细地址不能为空")
	@Size(max=400,message="居住详细地址超长")
  	private String liveAddr ; 
    
    /*** 房产所有权 */
//  	@NotBlank (message="居住类型不能为空")
	@Size(max=8,message="居住类型超长")
  	private String liveBuildType ; 
    
    /*** 月租金 */
//  	@NotNull (message="月租金不能为空")
	@Range(max=99999999,message="月租金超长")
  	private java.math.BigDecimal mthAmt ; 
    
    /*** 合同到期日 */
//  	@NotBlank (message="合同到期日不能为空")
	@Size(max=10,message="合同到期日超长")
  	private String contrEndDate ; 
    
    /*** 开始日期 */
  	//@NotNull (message="开始日期不能为空")
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	//@NotNull (message="结束日期不能为空")
  	private Date endDate ; 

    //构造函数
    public CustLiveInfoDto(){}

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
	 * 获取 居住地址是否同户籍
	 * @return String
	 */
	public String getIsRegLive() {
		return isRegLive;
	}

	/**
	 * 设置 居住地址是否同户籍
	 * @param isRegLive
	 */
	public void setIsRegLive(String isRegLive) {
		this.isRegLive = isRegLive;
	}

    
    /**
	 * 获取 现地址居住时间
	 * @return String
	 */
	public String getLiveTimeSpan() {
		return liveTimeSpan;
	}

	/**
	 * 设置 现地址居住时间
	 * @param liveTimeSpan
	 */
	public void setLiveTimeSpan(String liveTimeSpan) {
		this.liveTimeSpan = liveTimeSpan;
	}

    
    /**
	 * 获取 居住地省/直辖市
	 * @return String
	 */
	public String getLiveProv() {
		return liveProv;
	}

	/**
	 * 设置 居住地省/直辖市
	 * @param liveProv
	 */
	public void setLiveProv(String liveProv) {
		this.liveProv = liveProv;
	}

    
    /**
	 * 获取 居住市
	 * @return String
	 */
	public String getLiveCity() {
		return liveCity;
	}

	/**
	 * 设置 居住市
	 * @param liveCity
	 */
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

    
    /**
	 * 获取 居住区/县
	 * @return String
	 */
	public String getLiveArea() {
		return liveArea;
	}

	/**
	 * 设置 居住区/县
	 * @param liveArea
	 */
	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}

    
    /**
	 * 获取 居住详细地址
	 * @return String
	 */
	public String getLiveAddr() {
		return liveAddr;
	}

	/**
	 * 设置 居住详细地址
	 * @param liveAddr
	 */
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
	}

    
    /**
	 * 获取 房产所有权
	 * @return String
	 */
	public String getLiveBuildType() {
		return liveBuildType;
	}

	/**
	 * 设置 房产所有权
	 * @param liveBuildType
	 */
	public void setLiveBuildType(String liveBuildType) {
		this.liveBuildType = liveBuildType;
	}

    
    /**
	 * 获取 月租金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getMthAmt() {
		return mthAmt;
	}

	/**
	 * 设置 月租金
	 * @param mthAmt
	 */
	public void setMthAmt(java.math.BigDecimal mthAmt) {
		this.mthAmt = mthAmt;
	}

    
    /**
	 * 获取 合同到期日
	 * @return String
	 */
	public String getContrEndDate() {
		return contrEndDate;
	}

	/**
	 * 设置 合同到期日
	 * @param contrEndDate
	 */
	public void setContrEndDate(String contrEndDate) {
		this.contrEndDate = contrEndDate;
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

	public String getLiveProvName() {
		return liveProvName;
	}

	public void setLiveProvName(String liveProvName) {
		this.liveProvName = liveProvName;
	}

	public String getLiveCityName() {
		return liveCityName;
	}

	public void setLiveCityName(String liveCityName) {
		this.liveCityName = liveCityName;
	}

	public String getLiveAreaName() {
		return liveAreaName;
	}

	public void setLiveAreaName(String liveAreaName) {
		this.liveAreaName = liveAreaName;
	}

	
	
}