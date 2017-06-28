package com.hs.system.api;

import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.dto.SysRoleStaffBO;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;

/**
 * 角色信息 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysRoleApi{

	/**
	 * 增
	 * @param sysRole
	 * @
	 */
	public void insert(SysRole sysRole)  throws ServiceException,AppException;
	
	/**
	 * 删除
	 * @param primaryKey
	 * @
	 */
	public void deleteByPrimaryKey(String primaryKey)   throws ServiceException,AppException;
	
	/**
	 * 改
	 * @param map
	 * @
	 */
	public void updateByPrimaryKeySelective(Map<String, Object> map)   throws ServiceException,AppException;
	/**
	 * 查 分页
	 * @param page
	 * @return
	 */
	public Page<SysRole> queryForPage(Page<SysRole> page) throws ServiceException,AppException;

	/**
	 *根据客户号查询 已有角色
	 * @param page
	 * @return 
	 */
	public Page<SysRoleStaffBO> queryRolesExistStaff(Page<SysRoleStaffBO> page) throws ServiceException,AppException;
	/**queryCustNotExistRoles
	 *根据客户号查询 和未分配角色
	 * @param page
	 * @return 
	 */
	public Page<SysStaff> queryRolesNotExistStaff(Page<SysStaff> page) throws ServiceException,AppException;
										
	/**
	 * 保存角色信息
	 * @param sysRole
	 * @return
	 */
	public SysRole save(SysRole sysRole) throws ServiceException,AppException;
}