package com.hs.loan.cust.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_客户来源信息表 对象
 * @author autocreate
 * @create 2017-03-18
 */
public class AppCustSourceInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String custNo ; 
    
    /***  */
  	private String source ; 
    
    /***  */
  	private String custSource ; 
    
    /***  */
  	private String isWilling ; 
    
    /***  */
  	private String remark ; 
    
    /***  */
  	private Date instDate ; 

    //构造函数
    public AppCustSourceInfo(){}

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