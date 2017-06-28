package com.hs.loan.sale.entity;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 角色权限设置 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysPrivInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ;
  	
  	@NotBlank(message="业务功能编号不能为空")
	@Size(max=40,message="业务功能编号超长")
  	private String busiTypCod ; 

	@Size(max=200,message="机构路径超长")
  	private String orgPath ; 
	
	@Size(max=200,message="机构路径名称超长")
  	private String orgPathName;//机构名称

	@Size(max=32,message="机构匹配方式超长")
  	private String orgMatchTyp ; 

	@Size(max=32,message="角色编号超长")
  	private String roleNo ; 

	@Size(max=32,message="员工编号超长")
  	private String staffNo ; 

  	private String autTypCod ; 

  	private String appointOrgNo ; 
  	
  	private String appointOrgName;
  	private String appointRoleNo ; 
  	
  	private String appointRoleName;

  	private String appointStaffNo ; 
  	
  	@Size(max=40,message="菜单编号超长")
  	private String appointStaffName;

  	@Size(max=40,message="菜单编号超长")
  	private String appointOrgClass ; 

  	private Date instDate ; 

  	private Date updtDate ; 

  	/**
  	 * 权限类型 0角色类型， 1员工类型 
  	 */
  	private String privType;
  	

    //构造函数
    public SysPrivInfo(){}

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
	public String getBusiTypCod() {
		return busiTypCod;
	}

	/**
	 * 设置 
	 * @param busiTypCod
	 */
	public void setBusiTypCod(String busiTypCod) {
		this.busiTypCod = busiTypCod;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgPath() {
		return orgPath;
	}

	/**
	 * 设置 
	 * @param orgPath
	 */
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgMatchTyp() {
		return orgMatchTyp;
	}

	/**
	 * 设置 
	 * @param orgMatchTyp
	 */
	public void setOrgMatchTyp(String orgMatchTyp) {
		this.orgMatchTyp = orgMatchTyp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRoleNo() {
		return roleNo;
	}

	/**
	 * 设置 
	 * @param roleNo
	 */
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAutTypCod() {
		return autTypCod;
	}

	/**
	 * 设置 
	 * @param autTypCod
	 */
	public void setAutTypCod(String autTypCod) {
		this.autTypCod = autTypCod;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAppointOrgNo() {
		return appointOrgNo;
	}

	/**
	 * 设置 
	 * @param appointOrgNo
	 */
	public void setAppointOrgNo(String appointOrgNo) {
		this.appointOrgNo = appointOrgNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAppointRoleNo() {
		return appointRoleNo;
	}

	/**
	 * 设置 
	 * @param appointRoleNo
	 */
	public void setAppointRoleNo(String appointRoleNo) {
		this.appointRoleNo = appointRoleNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAppointStaffNo() {
		return appointStaffNo;
	}

	/**
	 * 设置 
	 * @param appointStaffNo
	 */
	public void setAppointStaffNo(String appointStaffNo) {
		this.appointStaffNo = appointStaffNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAppointOrgClass() {
		return appointOrgClass;
	}

	/**
	 * 设置 
	 * @param appointOrgClass
	 */
	public void setAppointOrgClass(String appointOrgClass) {
		this.appointOrgClass = appointOrgClass;
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

	public String getOrgPathName() {
		return orgPathName;
	}

	public void setOrgPathName(String orgPathName) {
		this.orgPathName = orgPathName;
	}

	public String getAppointOrgName() {
		return appointOrgName;
	}

	public void setAppointOrgName(String appointOrgName) {
		this.appointOrgName = appointOrgName;
	}

	public String getAppointRoleName() {
		return appointRoleName;
	}

	public void setAppointRoleName(String appointRoleName) {
		this.appointRoleName = appointRoleName;
	}

	public String getAppointStaffName() {
		return appointStaffName;
	}

	public void setAppointStaffName(String appointStaffName) {
		this.appointStaffName = appointStaffName;
	}

	public String getPrivType() {
		return privType;
	}

	public void setPrivType(String privType) {
		this.privType = privType;
	}

}