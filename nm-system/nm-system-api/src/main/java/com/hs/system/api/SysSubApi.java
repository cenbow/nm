package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysSub;

/**
 * 子系统信息 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysSubApi{
	/**
	 * 新增子系统
	 * @param sysSub
	 * @
	 * @author IT-009
	 */
	public void insert(SysSub sysSub)  throws ServiceException,AppException;
	
	/**
	 * 修改子系统信息
	 * @param map
	 * @
	 */
	public void updateByPrimaryKeySelective(Map<String, Object> map)  throws ServiceException,AppException;
	
	/**
	 * 根据子系统编号删除信息
	 * @param primaryKey
	 * @
	 */
	public void deleteByPrimaryKey(String primaryKey)  throws ServiceException,AppException;
	
	/**
	 * 根据主键获取子系统信息
	 * @param primaryKey
	 * @return
	 * @
	 */
	public SysSub getByPrimaryKey(String primaryKey)  throws ServiceException,AppException;
	
	/**
	 * 查询子系统列表
	 * @param param
	 * @return
	 * @
	 */
	public List<SysSub> queryForList(Map<String, Object> param)  throws ServiceException,AppException;
	
	/**
	 * 查询子系统信息分页
	 * @param page
	 * @return
	 * @
	 */
	public Page<SysSub> queryForPage(Page<SysSub> page)  throws ServiceException,AppException;
	
	/**
	 * 新增和修改子系统
	 * @param sysSub
	 * @return
	 */
	public SysSub save(SysSub sysSub) throws ServiceException,AppException;
}