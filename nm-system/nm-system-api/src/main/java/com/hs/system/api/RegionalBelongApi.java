package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysRegionalBelong;

/**
 * SYS_地域归属表 接口
 * @author autocreate
 * @create 2015-10-30
 */
public interface  RegionalBelongApi{

	/**
	 * 查询所有的县
	 * @return
	 */
	public List<SysRegionalBelong> queryArea(String prono) throws ServiceException,AppException;
	
	/**
	 * 查询省市县
	 * @param map
	 * @return
	 */
	public List<SysRegionalBelong> querySysRegionalBelong(Map<String,Object> map) throws ServiceException,AppException;
	
	/**
	 * 新增省市县
	 * @param belong
	 */
	public void save(SysRegionalBelong belong) throws ServiceException,AppException;
	/**
	 * 删除省市县
	 * @param belong
	 */
	public void delete(String id) throws ServiceException,AppException;
	
	
}