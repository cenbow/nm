package com.hs.system.api;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysOperaterLog;

/**
 * SYS_系统操作日志 接口
 * @author autocreate
 * @create 2015-10-10
 */
public interface  SysOperaterLogApi{

	/**
	 * 查询 SYS_系统操作日志 分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<SysOperaterLog> queryForPage(Page<SysOperaterLog> page) throws ServiceException,AppException;
}