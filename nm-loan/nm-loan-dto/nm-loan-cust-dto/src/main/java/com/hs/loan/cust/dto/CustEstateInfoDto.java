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
 * APP_客户房产信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustEstateInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
	/*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(min=1,max=40,message="表主键长度不能超过40")
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank (message="客户编号不能为空")
	@Size(max=32,message="客户编号长度不能超过32")
  	private String custNo ; 
    
    /*** 有无按揭房产 */
	@NotBlank (message="有无按揭房产不能为空")
	@Size(max=8,message="有无按揭房产超长")
  	private String estateType ; 
    
    /*** 房产所在地省/直辖市 */
	@NotBlank (message="房产所在地省不能为空")
	@Size(max=32,message="房产所在地省超长")
  	private String estateProv ; 
  	
  	 /*** 房产所在地省/直辖市 名称*/
  	private String estateProvName;
    
    /*** 房产所在市 */
	@NotBlank (message="房产所在市不能为空")
	@Size(max=128,message="房产所在市超长")
  	private String estateCity ; 
  	
  	/*** 房产所在市 名称*/
  	private String estateCityName;
    
    /*** 房产所在区/县 */
	@NotBlank (message="房产所在区/县不能为空")
	@Size(max=128,message="房产所在区/县超长")
  	private String estateArea ; 
  	
  	/*** 房产所在区/县 名称*/
  	private String estateAreaName;
    
    /*** 房产所在详细地址 */
  	@NotBlank (message="房产所在详细地址不能为空")
	@Size(max=400,message="房产所在详细地址超长")
  	private String estateAddr ; 
    
    /*** 房产购买年限 */
  	@NotBlank (message="房产购买年限不能为空")
	@Size(max=8,message="房产购买年限超长")
  	private String estateBuyYear ; 
    
    /*** 房产每月还款金额 */
  	@NotBlank (message="房产每月还款金额不能为空")
	@Size(max=8,message="房产每月还款金额超长")
  	private String estateMthAmt ; 
    
    /*** 房产每月还款日 */
  	@NotBlank (message="房产每月还款日不能为空")
	@Size(max=8,message="房产每月还款日超长")
  	private String estateMthDay ;
    
    /*** 房产剩余期限(月) */
  	@NotBlank (message="房产剩余期限(月)不能为空")
	@Size(max=8,message="房产剩余期限(月)超长")
  	private String estateMonth ; 
    
    /*** 备注 */
  	@Size(max = 128,message="备注超长")// 长度或大小范围
  	private String remark ; 
    
    /*** 开始日期 */
  	//@NotNull (message="开始日期不能为空")
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	//@NotNull (message="结束日期不能为空")
  	private Date endDate ; 

    //构造函数
    public CustEstateInfoDto(){}

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
	 * 获取 有无按揭房产
	 * @return String
	 */
	public String getEstateType() {
		return estateType;
	}

	/**
	 * 设置 有无按揭房产
	 * @param estateType
	 */
	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}

    
    /**
	 * 获取 房产所在地省/直辖市
	 * @return String
	 */
	public String getEstateProv() {
		return estateProv;
	}

	/**
	 * 设置 房产所在地省/直辖市
	 * @param estateProv
	 */
	public void setEstateProv(String estateProv) {
		this.estateProv = estateProv;
	}

    
    /**
	 * 获取 房产所在市
	 * @return String
	 */
	public String getEstateCity() {
		return estateCity;
	}

	/**
	 * 设置 房产所在市
	 * @param estateCity
	 */
	public void setEstateCity(String estateCity) {
		this.estateCity = estateCity;
	}

    
    /**
	 * 获取 房产所在区/县
	 * @return String
	 */
	public String getEstateArea() {
		return estateArea;
	}

	/**
	 * 设置 房产所在区/县
	 * @param estateArea
	 */
	public void setEstateArea(String estateArea) {
		this.estateArea = estateArea;
	}

    
    /**
	 * 获取 房产所在详细地址
	 * @return String
	 */
	public String getEstateAddr() {
		return estateAddr;
	}

	/**
	 * 设置 房产所在详细地址
	 * @param estateAddr
	 */
	public void setEstateAddr(String estateAddr) {
		this.estateAddr = estateAddr;
	}

    
    /**
	 * 获取 房产购买年限
	 * @return String
	 */
	public String getEstateBuyYear() {
		return estateBuyYear;
	}

	/**
	 * 设置 房产购买年限
	 * @param estateBuyYear
	 */
	public void setEstateBuyYear(String estateBuyYear) {
		this.estateBuyYear = estateBuyYear;
	}

    
    /**
	 * 获取 房产每月还款金额
	 * @return String
	 */
	public String getEstateMthAmt() {
		return estateMthAmt;
	}

	/**
	 * 设置 房产每月还款金额
	 * @param estateMthAmt
	 */
	public void setEstateMthAmt(String estateMthAmt) {
		this.estateMthAmt = estateMthAmt;
	}

    
    /**
	 * 获取 房产每月还款日
	 * @return String
	 */
	public String getEstateMthDay() {
		return estateMthDay;
	}

	/**
	 * 设置 房产每月还款日
	 * @param estateMthDay
	 */
	public void setEstateMthDay(String estateMthDay) {
		this.estateMthDay = estateMthDay;
	}

    
    /**
	 * 获取 房产剩余期限(月)
	 * @return String
	 */
	public String getEstateMonth() {
		return estateMonth;
	}

	/**
	 * 设置 房产剩余期限(月)
	 * @param estateMonth
	 */
	public void setEstateMonth(String estateMonth) {
		this.estateMonth = estateMonth;
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

	public String getEstateProvName() {
		return estateProvName;
	}

	public void setEstateProvName(String estateProvName) {
		this.estateProvName = estateProvName;
	}

	public String getEstateCityName() {
		return estateCityName;
	}

	public void setEstateCityName(String estateCityName) {
		this.estateCityName = estateCityName;
	}

	public String getEstateAreaName() {
		return estateAreaName;
	}

	public void setEstateAreaName(String estateAreaName) {
		this.estateAreaName = estateAreaName;
	}
    

}