package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * APP_客户授信额度 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustCreditDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
//	@NotBlank(message="表主键不能为空") 
//	@Size( max = 40,message="表主键超长")// 长度或大小范围
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空") 
	@Size( max = 32,message="客户编号超长")// 长度或大小范围
  	private String custNo ; 
    
    /*** 授信额度 */
	@NotBlank(message="授信额度不能为空") 
	@Size( max = 32,message="授信额度超长")// 长度或大小范围
  	private String creditLimit ; 
    
    /*** 剩余额度 */
	@NotBlank(message="剩余额度不能为空") 
	@Size( max = 32,message="剩余额度超长")// 长度或大小范围
  	private String remainLimit ; 
    
    /*** 授信额度方式 */
	@NotBlank(message="授信额度方式不能为空") 
	@Size( max = 32,message="授信额度方式超长")// 长度或大小范围
  	private String creditTyp ; 
    
    /*** 授信额度日期 */
	@NotBlank(message="人工授信额度有效期不能为空") 
	@Size( max = 8,message="人工授信额度有效期超长")// 长度或大小范围
  	private String creditDate ; 
    
    /*** 人工授信额度有效期 */
	@NotBlank(message="人工授信额度有效期不能为空") 
	@Size( max = 8,message="人工授信额度有效期超长")// 长度或大小范围
  	private String creditValid ; 
    
    /*** 授信额度调整用户 */
  	@NotBlank(message="授信额度调整用户不能为空") 
  	@Size( max = 32,message="授信额度调整用户超长")// 长度或大小范围
  	private String creditUser ; 
    
    /*** 创建时间 */
//  	@NotNull(message="创建时间不能为空") 
  	private Date beginDate ; 
    
    /*** 更新时间 */
  	private Date endDate ; 

    //构造函数
    public CustCreditDto(){}

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
	 * 获取 授信额度
	 * @return String
	 */
	public String getCreditLimit() {
		return creditLimit;
	}

	/**
	 * 设置 授信额度
	 * @param creditLimit
	 */
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

    
    /**
	 * 获取 剩余额度
	 * @return String
	 */
	public String getRemainLimit() {
		return remainLimit;
	}

	/**
	 * 设置 剩余额度
	 * @param remainLimit
	 */
	public void setRemainLimit(String remainLimit) {
		this.remainLimit = remainLimit;
	}

    
    /**
	 * 获取 授信额度方式
	 * @return String
	 */
	public String getCreditTyp() {
		return creditTyp;
	}

	/**
	 * 设置 授信额度方式
	 * @param creditTyp
	 */
	public void setCreditTyp(String creditTyp) {
		this.creditTyp = creditTyp;
	}

    
    /**
	 * 获取 授信额度日期
	 * @return String
	 */
	public String getCreditDate() {
		return creditDate;
	}

	/**
	 * 设置 授信额度日期
	 * @param creditDate
	 */
	public void setCreditDate(String creditDate) {
		this.creditDate = creditDate;
	}

    
    /**
	 * 获取 人工授信额度有效期
	 * @return String
	 */
	public String getCreditValid() {
		return creditValid;
	}

	/**
	 * 设置 人工授信额度有效期
	 * @param creditValid
	 */
	public void setCreditValid(String creditValid) {
		this.creditValid = creditValid;
	}

    
    /**
	 * 获取 授信额度调整用户
	 * @return String
	 */
	public String getCreditUser() {
		return creditUser;
	}

	/**
	 * 设置 授信额度调整用户
	 * @param creditUser
	 */
	public void setCreditUser(String creditUser) {
		this.creditUser = creditUser;
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