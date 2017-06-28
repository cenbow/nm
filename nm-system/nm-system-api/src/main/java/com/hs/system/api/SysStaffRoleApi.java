package com.hs.system.api;

import java.util.List;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysStaffRole;

/**
 * 用户角色 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysStaffRoleApi{

	 /**
	  * 新增
	  * @param sysUserRole
	  * @
	  */
	public void saveStaffRoles(List<SysStaffRole> sysUserRole)  throws ServiceException,AppException;
	
	/**
	 * 移除
	 * @param primaryKey
	 * @
	 */
	public void removeStaffRoles(List<String> relIds)  throws ServiceException,AppException;
}