package com.hs.system.log;

import com.hs.base.entity.Page;
import com.hs.system.entity.SysLoginLog;

/**
 * 系统登录日志 接口
 * @author autocreate
 * @create 2015-10-10
 */
public interface  ISysLoginLogService{

	/**
	 * 查询 系统登录日志 分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<SysLoginLog> queryForPage(Page<SysLoginLog> page);
}