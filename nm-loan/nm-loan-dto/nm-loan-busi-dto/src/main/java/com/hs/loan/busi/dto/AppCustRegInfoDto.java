package com.hs.loan.busi.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * APP_客户登录信息 对象
 * @author autocreate
 * @create 2017-03-31
 */
public class AppCustRegInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String custNo ; 
    
    /***  */
  	private String area ; 
    
    /***  */
  	@JsonSerialize(using=DateTimeJsonSerializer.class)
  	private Date loginTime ; 

    //构造函数
    public AppCustRegInfoDto(){}

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
	public String getArea() {
		return area;
	}

	/**
	 * 设置 
	 * @param area
	 */
	public void setArea(String area) {
		this.area = area;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * 设置 
	 * @param loginTime
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}