package com.hs.system.dto;


import java.io.Serializable;

import com.hs.system.entity.SysMenu;

/**
 * 角色菜单关系 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysRoleMenuBo extends SysMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String roleId ; 
  	
  	private String isChecked;

    //构造函数
    public SysRoleMenuBo(){}

    //getter和setter方法

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

}