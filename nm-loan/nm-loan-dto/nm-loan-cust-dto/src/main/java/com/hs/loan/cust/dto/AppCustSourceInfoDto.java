package com.hs.loan.cust.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户来源信息表 对象
 * @author autocreate
 * @create 2017-03-18
 */
public class AppCustSourceInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** goodsType 商品类型 */
  	private String source ; 
    
    /*** 客户来源 */
  	private String custSource ; 
    
    /*** 是否接收低于金额 */
  	private String isWilling ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /***  */
  	private Date instDate ; 

    //构造函数
    public AppCustSourceInfoDto(){}

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
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 设置 
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCustSource() {
		return custSource;
	}

	/**
	 * 设置 
	 * @param custSource
	 */
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getIsWilling() {
		return isWilling;
	}

	/**
	 * 设置 
	 * @param isWilling
	 */
	public void setIsWilling(String isWilling) {
		this.isWilling = isWilling;
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

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}