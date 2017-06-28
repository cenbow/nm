package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户其他联系人信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustContctOtherDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
//	@NotBlank(message="表主键不能为空") 
//	@Size( max = 40,message="表主键超长")// 长度或大小范围
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空") 
	@Size( max = 32,message="客户编号超长")// 长度或大小范围
  	private String custNo ; 
    
    /*** 联系人 */
	@NotBlank(message="联系人不能为空") 
	@Size( max = 32,message="联系人超长")// 长度或大小范围
  	private String contactName ; 
    
    /*** 关系 */
	@NotBlank (message="关系不能为空")
	@Size( max = 32,message="关系超长")// 长度或大小范围
  	private String contactRel ; 
    
    /*** 电话 */
	@NotBlank (message="电话不能为空")
	@Size( max = 32,message="电话超长")// 长度或大小范围
  	private String contactTel ; 
    
    /*** 是否知晓本次分期 */
//	@NotBlank (message="是否知晓本次分期不能为空")
	@Size( max = 8,message="是否知晓本次分期超长")// 长度或大小范围
  	private String isConKnow ; 
    
    /*** 是否有效 */
//	@NotBlank (message="是否有效不能为空")
	@Size( max = 8,message="是否有效超长")// 长度或大小范围
  	private String isValid ; 
    
    /*** 创建时间 */
//	@NotNull(message="创建时间不能为空") 
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public CustContctOtherDto(){}

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
	@JsonSerialize(using = DateTimeJsonSerializer.class) 
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
	 @JsonSerialize(using = DateTimeJsonSerializer.class) 
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