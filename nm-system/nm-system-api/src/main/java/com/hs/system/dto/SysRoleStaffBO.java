package com.hs.system.dto;


import java.util.Date;

import com.hs.system.entity.SysStaff;

import java.io.Serializable;

/**
 * 员工信息 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysRoleStaffBO extends SysStaff{
	private static final long serialVersionUID = 1L;
	
   private String rid;
   
   private String roleNo;

	public String getRoleNo() {
		return roleNo;
	}
	
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
	
	public String getRid() {
		return rid;
	}
	
	public void setRid(String rid) {
		this.rid = rid;
}
   
   
}