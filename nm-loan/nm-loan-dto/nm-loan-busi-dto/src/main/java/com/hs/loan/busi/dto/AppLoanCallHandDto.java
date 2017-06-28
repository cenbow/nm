package com.hs.loan.busi.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * APP_经办历史记录表 对象
 * @author autocreate
 * @create 2017-05-02
 */
public class AppLoanCallHandDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String orderId ; 
    
    /***  */
  	private String handPerson ; 
    
    /***  */
  	private String handNo ; 
    
    /***  */
  	private String handType ; 
    
    /***  */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date startDate ; 
    
    /***  */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date endDate ; 
    
    /***  */
  	private String remark ; 

    //构造函数
    public AppLoanCallHandDto(){}

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
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置 
	 * @param loanNo
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getHandPerson() {
		return handPerson;
	}

	/**
	 * 设置 
	 * @param handPerson
	 */
	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getHandNo() {
		return handNo;
	}

	/**
	 * 设置 
	 * @param handNo
	 */
	public void setHandNo(String handNo) {
		this.handNo = handNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getHandType() {
		return handType;
	}

	/**
	 * 设置 
	 * @param handType
	 */
	public void setHandType(String handType) {
		this.handType = handType;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置 
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

}