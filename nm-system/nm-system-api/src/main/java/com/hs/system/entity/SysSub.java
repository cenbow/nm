package com.hs.system.entity;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 子系统信息 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysSub implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 

  	@NotBlank(message="子系统代码不能为空")
	@Size(max=40,message="子系统代码超长")
  	private String sysCod ; 

  	@NotBlank(message="子系统名称不能为空")
	@Size(max=80,message="子系统名称超长")
  	private String sysName ; 

  	@NotBlank(message="子系统IP地址不能为空")
	@Size(max=120,message="子系统IP地址超长")
  	private String sysIp ; 

  	@NotBlank(message="是否使用不能为空")
	@Size(max=10,message="是否使用超长")
  	private String isActived ; 

  	@Future(message="创建日期必须晚于当前日期")
  	private Date instDate ; 

  	@Future(message="修改日期必须晚于当前日期")
  	private Date updtDate ; 


    //构造函数
    public SysSub(){}

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
	public String getSysCod() {
		return sysCod;
	}

	/**
	 * 设置 
	 * @param sysCod
	 */
	public void setSysCod(String sysCod) {
		this.sysCod = sysCod;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * 设置 
	 * @param sysName
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSysIp() {
		return sysIp;
	}

	/**
	 * 设置 
	 * @param sysIp
	 */
	public void setSysIp(String sysIp) {
		this.sysIp = sysIp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getIsActived() {
		return isActived;
	}

	/**
	 * 设置 
	 * @param isActived
	 */
	public void setIsActived(String isActived) {
		this.isActived = isActived;
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

}