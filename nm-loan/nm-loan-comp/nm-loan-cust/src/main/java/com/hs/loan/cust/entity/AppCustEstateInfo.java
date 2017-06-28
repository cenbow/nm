package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户房产信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustEstateInfo implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 有无按揭房产 */
  	private String estateType ; 
    
    /*** 房产所在地省/直辖市 */
  	private String estateProv ; 
    
    /*** 房产所在市 */
  	private String estateCity ; 
    
    /*** 房产所在区/县 */
  	private String estateArea ; 
    
    /*** 房产所在详细地址 */
  	private String estateAddr ; 
    
    /*** 房产购买年限 */
  	private String estateBuyYear ; 
    
    /*** 房产每月还款金额 */
  	private String estateMthAmt ; 
    
    /*** 房产每月还款日 */
  	private String estateMthDay ; 
    
    /*** 房产剩余期限(月) */
  	private String estateMonth ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public AppCustEstateInfo(){}

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