package com.hs.loan.busi.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;

import java.io.Serializable;

/**
 * APP_销售直通车权限历史 对象
 * @author autocreate
 * @create 2016-10-27
 */
public class AppLoanSpcailpriSalerHDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String staffNo ; 
    
    /***  */
  	private String staffName ; 
    
    /***  */
	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date instDate ; 
    
    /***  */
	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date endDate ; 
    
    /***  */
  	private String handPerson ; 
    
    /***  */
  	private String orgNo ; 

    //构造函数
    public AppLoanSpcailpriSalerHDto(){}

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
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
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
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

}