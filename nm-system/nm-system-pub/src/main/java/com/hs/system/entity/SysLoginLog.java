package com.hs.system.entity;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * 系统登录日志 对象
 * @author autocreate
 * @create 2015-10-10
 */
public class SysLoginLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /*** 机构编号 */
  	@NotBlank(message="机构编号不能为空")
	@Size(max=40,message="机构编号超长")
  	private String orgId ; 
    
    /*** 角色编号 */
  	@NotBlank(message="角色编号不能为空")
	@Size(max=40,message="角色编号超长")
  	private String roleId ; 
    
    /*** 用户编号 */
  	@NotBlank(message="用户编号不能为空")
	@Size(max=40,message="用户编号超长")
  	private String staffId ; 
    
    /***登录时间  */
  	@Future(message="登录时间必须晚于当前时间")
  	private Date loginDate ; 
    
    /*** 登录IP */
  	@NotBlank(message="登录IP不能为空")
	@Size(max=40,message="登录IP超长")
  	private String loginIp ; 
  	
  	/***渠道  */
  	@NotBlank(message="渠道不能为空")
	@Size(max=40,message="渠道超长")
  	private String channel ;
  	
  	/*** 机构名称  显示字段  */
  	private String orgName;
  	
  	/*** 角色名称  显示字段  */
  	private String roleName;
  	
  	/*** 员工姓名  显示字段  */
  	private String staffName;

    //构造函数
    public SysLoginLog(){}

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

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置 
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
	 * @return Date
	 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 设置 
	 * @param loginDate
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 设置 
	 * @param loginIp
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

}