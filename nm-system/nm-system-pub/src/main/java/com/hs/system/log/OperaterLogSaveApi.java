package com.hs.system.log;

import com.hs.system.entity.SysOperaterLog;

/**
 * SYS_系统操作日志 接口
 * @author autocreate
 * @create 2015-10-10
 */
public interface  OperaterLogSaveApi{

	/**
	 * 新增 SYS_系统操作日志
	 * @param sysOperaterLog
	 * @return
	 */
	public void insert(SysOperaterLog sysOperaterLog);

}