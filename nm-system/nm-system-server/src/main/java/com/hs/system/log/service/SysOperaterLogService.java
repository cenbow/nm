package com.hs.system.log.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.system.api.SysOperaterLogApi;
import com.hs.system.entity.SysOperaterLog;
import com.hs.system.log.OperaterLogSaveApi;
import com.hs.system.log.mapper.SysOperaterLogMapper;

/**
 * SYS_系统操作日志 业务处理
 * @author autocreate
 * @create 2015-10-10
 */
@Service
@Transactional(readOnly=true)
public class  SysOperaterLogService implements SysOperaterLogApi{
	@Autowired
	private SysOperaterLogMapper sysOperaterLogMapper;
	
	/**
	 * 查询 SYS_系统操作日志 分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<SysOperaterLog> queryForPage(Page<SysOperaterLog> page){
		List<SysOperaterLog> list = sysOperaterLogMapper.queryForList(page.getPageParams());
		return (Page<SysOperaterLog>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 通过主键取得 SYS_系统操作日志 对象
	 * @param primaryKey
	 * @return
	 */
	public SysOperaterLog getByPrimaryKey(String primaryKey){
		return sysOperaterLogMapper.getByPrimaryKey(primaryKey);
	}
}