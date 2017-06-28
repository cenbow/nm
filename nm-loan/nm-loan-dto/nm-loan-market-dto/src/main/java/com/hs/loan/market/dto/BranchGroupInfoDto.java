package com.hs.loan.market.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class BranchGroupInfoDto implements Serializable{
	private static final long serialVersionUID = -1760564380879862347L;

	
	 /*** 商户编号 */
  	private String groupNo ; 
    
    /*** 分组名称 */
  	@NotBlank(message="分组名称不能为空")
  	@Size(max=80,message="分组名称超长")
  	private String groupName ; 
    
    /*** 分组类型 */
  	@NotBlank(message="分组类型不能为空")
  	@Size(max=40,message="分组类型超长")
  	private String groupTyp ; 
    
    /*** 创建日期 */
  	@Future(message="创建日期必须晚于当前时间")
  	private Date instDate ; 
    
    /*** 修改日期 */
  	@Future(message="更新日期必须晚于当前时间")
  	private Date updtDate ; 

    //构造函数
    public BranchGroupInfoDto(){}

    //getter和setter方法
    
    /**
	 * 获取 商户编号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 商户编号
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

    
    /**
	 * 获取 商户名称
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置 商户名称
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    
    /**
	 * 获取 分组类型
	 * @return String
	 */
	public String getGroupTyp() {
		return groupTyp;
	}

	/**
	 * 设置 分组类型
	 * @param groupTyp
	 */
	public void setGroupTyp(String groupTyp) {
		this.groupTyp = groupTyp;
	}

    
    /**
	 * 获取 创建日期
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建日期
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 修改日期
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改日期
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}
	
}
