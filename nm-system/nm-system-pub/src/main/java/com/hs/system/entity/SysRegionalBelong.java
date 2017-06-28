package com.hs.system.entity;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * SYS_地域归属表 对象
 * @author autocreate
 * @create 2015-10-30
 */
public class SysRegionalBelong implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 省编码 */
  	@NotBlank(message="角色状态不能为空")
	@Size(max=40,message="角色状态超长")
  	private String provNo ; 
    
    /*** 省名称 */
  	@NotBlank(message="角色状态不能为空")
	@Size(max=128,message="角色状态超长")
  	private String provName ; 
    
    /*** 市编码 */
	@Size(max=40,message="市编码超长")
  	private String cityNo ; 
    
    /*** 市名称 */
	@Size(max=128,message="市名称超长")
  	private String cityName ; 
    
    /*** 县编码 */
	@Size(max=40,message="县编码超长")
  	private String areaNo ; 
    
    /*** 县名称 */
	@Size(max=128,message="县名称超长")
  	private String areaName ; 

    //构造函数
    public SysRegionalBelong(){}

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
	 * 获取 省编码
	 * @return String
	 */
	public String getProvNo() {
		return provNo;
	}

	/**
	 * 设置 省编码
	 * @param provNo
	 */
	public void setProvNo(String provNo) {
		this.provNo = provNo;
	}

    
    /**
	 * 获取 省名称
	 * @return String
	 */
	public String getProvName() {
		return provName;
	}

	/**
	 * 设置 省名称
	 * @param provName
	 */
	public void setProvName(String provName) {
		this.provName = provName;
	}

     
    
    /**
	 * 获取 市编码
	 * @return String
	 */
	public String getCityNo() {
		return cityNo;
	}

	/**
	 * 设置 市编码
	 * @param cityNo
	 */
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

    
    /**
	 * 获取 市名称
	 * @return String
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 设置 市名称
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
    /**
	 * 获取 县编码
	 * @return String
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * 设置 县编码
	 * @param areaNo
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

    
    /**
	 * 获取 县名称
	 * @return String
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 设置 县名称
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}