package com.hs.system.entity;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 角色信息 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysRole implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 
  	
  	@NotBlank(message="角色编号不能为空")
	@Size(max=40,message="角色编号超长")
  	private String roleNo ; 

  	@Size(max=40,message="关系ID超长")
  	private String relId ; 

  	@NotBlank(message="角色名称不能为空")
	@Size(max=40,message="角色名称超长")
  	private String roleName ; 
  	
  	@NotBlank(message="角色状态不能为空")
	@Size(max=10,message="角色状态超长")
  	private String roleStat ; 
  	
  	@Size(max=200,message="备注超长")
  	private String remark ; 
  	
  	@Size(max=200,message="角色所属机构超长")
  	private String roleOrg ; 

  	@Future(message="创建时间必须晚于当前时间")
  	private Date instDate ; 

  	@Future(message="修改时间必须晚于当前时间")
  	private Date updtDate ; 


    //构造函数
    public SysRole(){}

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
	public String getRelId() {
		return relId;
	}

	/**
	 * 设置 
	 * @param relId
	 */
	public void setRelId(String relId) {
		this.relId = relId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置 
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRoleStat() {
		return roleStat;
	}

	/**
	 * 设置 
	 * @param roleStat
	 */
	public void setRoleStat(String roleStat) {
		this.roleStat = roleStat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRoleOrg() {
		return roleOrg;
	}

	/**
	 * 设置 
	 * @param roleOrg
	 */
	public void setRoleOrg(String roleOrg) {
		this.roleOrg = roleOrg;
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