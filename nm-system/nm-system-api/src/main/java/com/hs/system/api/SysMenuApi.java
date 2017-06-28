package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysMenu;

/**
 * 菜单 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysMenuApi{

	
	
	/**
	 * 新增菜单
	 * @param sysMenu
	 * @
	 */
	public int insert(SysMenu sysMenu)  throws ServiceException,AppException;
	
	/**
	 * 根据条件查询菜单
	 * @param param
	 * @return
	 * @
	 */
	public List<SysMenu> queryForList(Map<String, Object> param)  throws ServiceException,AppException;
	
	/**
	 * 获取单个菜单信息
	 * @param primaryKey
	 * @return
	 * @
	 */
	public SysMenu getByPrimaryKey(String primaryKey)  throws ServiceException,AppException;
	
	/**
	 * 删除菜单
	 * @param primaryKey
	 * @
	 */
	public void deleteByPrimaryKey(String primaryKey)  throws ServiceException,AppException;
	
	/**
	 * 更新菜单信息
	 * @param map
	 * @
	 */
	public int updateByPrimaryKeySelective(Map<String, Object> map)  throws ServiceException,AppException;
	
	/**
	 * 保存和更新菜单信息
	 * @param sysMenu
	 * @return
	 */
	public SysMenu save(SysMenu sysMenu) throws ServiceException,AppException;
	
	/**
	 * 删除菜单，向下递归删除
	 * @param primaryKey
	 */
	public void deleteMenu(String primaryKey) throws ServiceException,AppException;
	
}