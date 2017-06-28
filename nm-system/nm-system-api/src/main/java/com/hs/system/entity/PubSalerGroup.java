package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PUB_销售组信息 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubSalerGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 销售组编号 */
//	@NotBlank(message="销售组编号不能为空")
	@Size(max=40,message="销售组编号超长")
  	private String groupNo ; 
    
    /*** 销售组名称 */
	@NotBlank(message="销售组名称不能为空")
	@Size(max=80,message="销售组名称超长")
  	private String groupName ; 
    
    /*** 状态 */
	@NotBlank(message="状态不能为空")
	@Size(max=10,message="状态超长")
  	private String stat ; 
    
    /*** 创建时间 */
//	@NotNull(message="创建时间不能为空")
	@Future(message="创建时间必须晚于当前时间")
  	private Date instDate ; 
    
    /*** 更新时间 */
	@Future(message="更新时间必须晚于当前时间")
  	private Date updtDate ; 

    //构造函数
    public PubSalerGroup(){}

    //getter和setter方法
    
    /**
	 * 获取 销售组编号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 销售组编号
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

    
    /**
	 * 获取 销售组名称
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置 销售组名称
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
    
    public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * 获取 创建时间
	 * @return Date
	 */
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
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}