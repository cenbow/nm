package com.hs.system.dto;

import com.hs.system.entity.SysRole;

public class SysStaffRoleBO extends SysRole{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rid;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}
	
}
