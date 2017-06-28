package com.hs.loan.approv.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;

/**
 * APP_审批组信息 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class ApprGroupDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 审批组号 */
  	private String groupNo ; 
    
    /*** 组名称 */
  	@NotBlank(message="组名称不能为空")
  	@Size(max=80,message="组名称超长")
  	private String groupName ; 
    
    /*** 状态 */
  	@NotBlank(message="状态不能为空")
  	@Size(max=8,message="状态超长")
  	private String stat ; 
    
    /***  */
  	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date instDate ; 
    
    /***  */
  	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date updtDate ; 
    
    /*** 产品类型 */
//  	@NotBlank(message="产品类型不能为空")
  	@Size(max=10,message="产品类型超长")
  	private String prodTyp ;

	//审批组里面人员组所在省
	private String branchProv;

	//审批组里面人员组所在市
	private String branchCity;

	//审批组里面人员组所在区
	private String branchArea;
	
	
	//商品类型
	private String goodsTypes;

	
	
	
	public String getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(String goodsTypes) {
		this.goodsTypes = goodsTypes;
	}

	public String getBranchProv() {
		return branchProv;
	}

	public void setBranchProv(String branchProv) {
		this.branchProv = branchProv;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchArea() {
		return branchArea;
	}

	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}

	//构造函数
    public ApprGroupDto(){}

    //getter和setter方法
    
    /**
	 * 获取 审批组号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 审批组号
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

    
    /**
	 * 获取 组名称
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置 组名称
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    
    /**
	 * 获取 状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
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
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getProdTyp() {
		return prodTyp;
	}

	/**
	 * 设置 
	 * @param prodTyp
	 */
	public void setProdTyp(String prodTyp) {
		this.prodTyp = prodTyp;
	}

}