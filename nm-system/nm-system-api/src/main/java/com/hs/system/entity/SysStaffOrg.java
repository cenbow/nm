package com.hs.system.entity;


import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 机构人员关系 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysStaffOrg implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 

  	@NotBlank(message="员工编号不能为空")
	@Size(max=40,message="员工编号超长")
  	private String staffId ; 

  	@NotBlank(message="机构号不能为空")
	@Size(max=40,message="机构号超长")
  	private String orgId ; 


    //构造函数
    public SysStaffOrg(){}

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
	public String getStaffId() {
		return staffId;
	}

	/**
	 * 设置 
	 * @param staffId
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置 
	 * @param orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}