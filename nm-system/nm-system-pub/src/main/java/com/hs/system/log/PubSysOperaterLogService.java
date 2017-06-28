package com.hs.system.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.system.entity.SysOperaterLog;
import com.hs.system.mapper.PubSysOperaterLogMapper;

/**
 * SYS_系统操作日志 业务处理
 * @author autocreate
 * @create 2015-10-10
 */
@Service
@Transactional(readOnly=true)
public class  PubSysOperaterLogService implements OperaterLogSaveApi{
	@Autowired
	private PubSysOperaterLogMapper sysOperaterLogMapper;
	
	/**
	 * 新增 SYS_系统操作日志
	 * @param sysOperaterLog
	 * @return
	 */
	@Transactional
	public void insert(SysOperaterLog sysOperaterLog){
		sysOperaterLogMapper.insert(sysOperaterLog);
	}
}