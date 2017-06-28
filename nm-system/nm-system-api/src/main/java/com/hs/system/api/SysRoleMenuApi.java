package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.dto.SysRoleMenuBo;

/**
 * 角色菜单关系 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysRoleMenuApi{
	
	/**
	 * 查询 角色菜单 树
	 * @param param
	 * @return List<T>
	 */
	public List<SysRoleMenuBo> querySysRoleMenuList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 查询 角色关联菜单(选中)
	 * @param param
	 * @return List<T>
	 */
	public List<SysRoleMenuBo> querySysRoleMenuCheckedList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 保存角色关联菜单
	 * @param roleId
	 * @param list
	 */
	public void save(String sysId,String roleId, List<String> list) throws ServiceException,AppException;
	
	
	/**
	 * 查询改角色在子系统下菜单中的菜单，
	 */
	public List<String> querySubSysRoleHadMenu(String roleId, String sysId) throws ServiceException,AppException;
}