package com.hs.system.log.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.system.api.SysLoginLogApi;
import com.hs.system.entity.SysLoginLog;
import com.hs.system.log.mapper.SysLoginLogMapper;

/**
 * 系统登录日志 业务处理
 * @author autocreate
 * @create 2015-10-10
 */
@Service
@Transactional(readOnly=true)
public class  SysLoginLogService implements SysLoginLogApi{
	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;
	
	/**
	 * 查询 系统登录日志 分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<SysLoginLog> queryForPage(Page<SysLoginLog> page){
		List<SysLoginLog> list = sysLoginLogMapper.queryForList(page.getPageParams());
		return (Page<SysLoginLog>)page.getPageParams().get(Page.KEY);
	}
}