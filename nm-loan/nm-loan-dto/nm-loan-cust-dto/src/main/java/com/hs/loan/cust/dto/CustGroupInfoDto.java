package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户分群 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustGroupInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 群编号 */
//	@NotBlank (message="群编号不能为空")
//	@Size(max=40,message="群编号超长")
  	private String custGroup ; 
    
    /*** 群名称 */
	@NotBlank (message="群名称不能为空")
	@Size(max=80,message="群名称超长")
  	private String custGroupName ; 
    
    /*** 规则 */
	@NotBlank (message="群规则不能为空")
	@Size(max=40,message="群规则超长")
  	private String custCalc ; 
    
    /*** 创建人 */
//	@NotBlank (message="创建人不能为空")
	@Size(max=40,message="创建人超长")
  	private String instPerson ; 
    
    /*** 创建时间 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date instDate ; 
    
    /*** 是否有效 */
  	@NotBlank (message="是否有效不能为空")
	@Size(max=8,message="是否有效超长")
  	private String stat ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public CustGroupInfoDto(){}

    //getter和setter方法
    
    /**
	 * 获取 群编号
	 * @return String
	 */
	public String getCustGroup() {
		return custGroup;
	}

	/**
	 * 设置 群编号
	 * @param custGroup
	 */
	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
	}

    
    /**
	 * 获取 群名称
	 * @return String
	 */
	public String getCustGroupName() {
		return custGroupName;
	}

	/**
	 * 设置 群名称
	 * @param custGroupName
	 */
	public void setCustGroupName(String custGroupName) {
		this.custGroupName = custGroupName;
	}

    
    /**
	 * 获取 规则
	 * @return String
	 */
	public String getCustCalc() {
		return custCalc;
	}

	/**
	 * 设置 规则
	 * @param custCalc
	 */
	public void setCustCalc(String custCalc) {
		this.custCalc = custCalc;
	}

    
    /**
	 * 获取 创建人
	 * @return String
	 */
	public String getInstPerson() {
		return instPerson;
	}

	/**
	 * 设置 创建人
	 * @param instPerson
	 */
	public void setInstPerson(String instPerson) {
		this.instPerson = instPerson;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 是否有效
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 是否有效
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
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

}