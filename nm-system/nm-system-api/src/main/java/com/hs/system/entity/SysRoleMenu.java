package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色菜单关系 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysRoleMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ;
  	
  	@NotBlank(message="角色编号不能为空")
	@Size(max=40,message="角色编号超长")
  	private String roleId ; 

  	@NotBlank(message="菜单编号不能为空")
	@Size(max=40,message="菜单编号超长")
  	private String menuId ; 

	@Future(message="创建日期必须晚于当前日期")
  	private Date instDate ; 

	@Future(message="修改日期必须晚于当前日期")
  	private Date updtDate ; 


    //构造函数
    public SysRoleMenu(){}

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
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 设置 
	 * @param menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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