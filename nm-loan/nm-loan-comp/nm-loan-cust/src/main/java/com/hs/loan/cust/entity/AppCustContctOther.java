package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户其他联系人信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustContctOther implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 联系人 */
  	private String contactName ; 
    
    /*** 关系 */
  	private String contactRel ; 
    
    /*** 电话 */
  	private String contactTel ; 
    
    /*** 是否知晓本次分期 */
  	private String isConKnow ; 
    
    /*** 是否有效 */
  	private String isValid ; 
    
    /*** 创建时间 */
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public AppCustContctOther(){}

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
	 * 获取 联系人
	 * @return String
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * 设置 联系人
	 * @param contactName
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

    
    /**
	 * 获取 关系
	 * @return String
	 */
	public String getContactRel() {
		return contactRel;
	}

	/**
	 * 设置 关系
	 * @param contactRel
	 */
	public void setContactRel(String contactRel) {
		this.contactRel = contactRel;
	}

    
    /**
	 * 获取 电话
	 * @return String
	 */
	public String getContactTel() {
		return contactTel;
	}

	/**
	 * 设置 电话
	 * @param contactTel
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

    
    /**
	 * 获取 是否知晓本次分期
	 * @return String
	 */
	public String getIsConKnow() {
		return isConKnow;
	}

	/**
	 * 设置 是否知晓本次分期
	 * @param isConKnow
	 */
	public void setIsConKnow(String isConKnow) {
		this.isConKnow = isConKnow;
	}

    
    /**
	 * 获取 是否有效
	 * @return String
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * 设置 是否有效
	 * @param isValid
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
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

}