package com.hs.system.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hs.system.entity.SysOperaterLog;

/**
 * 系统操作日志线程类
 * @author Administrator
 *
 */
public class SysOperaterLogThread extends Thread {
	
	private static Logger logger = LoggerFactory.getLogger(SysOperaterLogThread.class);
	
	private SysOperaterLog sysOperaterLog = null;
	private OperaterLogSaveApi service = null;
	
	public SysOperaterLogThread(SysOperaterLog sysOperaterLog,OperaterLogSaveApi service){
		this.sysOperaterLog = sysOperaterLog;
		this.service = service;
	}

	public void run() {
		try {
			if(null != sysOperaterLog){
				service.insert(sysOperaterLog);
			}
		} catch (Exception e) {
			logger.error("操作日志写入错误",e);
		}
	}
	
}
