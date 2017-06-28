package com.hs.system.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.dto.SysPrivManagerBo;
import com.hs.system.entity.SysPrivInfo;

/**
 * 角色权限设置 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysPrivInfoApi{

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page<SysPrivInfo> queryForPage(Page<SysPrivInfo> page) throws ServiceException,AppException;
	
	/**
	 * 保存或更新
	 * @param sysPrivInfo
	 */
	public void saveOrUpdate(SysPrivInfo sysPrivInfo) throws ServiceException,AppException;

	/**
	 * 通过id批量删除privinfo
	 * @param param
	 */
	public void deletePrivInfos(List<String> param) throws ServiceException,AppException;
	
	/**
	 * 通过id获得privinfo
	 * @param primaryKey
	 * @return
	 */
	public SysPrivInfo getByPrimaryKey(String primaryKey) throws ServiceException,AppException;
	
	/**
	 * 分页查询privmanage bo，用于页面显示
	 * @param sysPrivInfo
	 * @return
	 */
	public Page<SysPrivManagerBo> queryPrivManagerBoPage(Page<SysPrivManagerBo> page) throws ServiceException,AppException;
	
}