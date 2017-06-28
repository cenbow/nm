package com.hs.system.log;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.system.entity.SysLoginLog;
import com.hs.system.mapper.PubSysLoginLogMapper;

/**
 * 系统登录日志 业务处理
 * @author autocreate
 * @create 2015-10-10
 */
@Service
@Transactional(readOnly=true)
public class  PubSysLoginLogService implements ISysLoginLogService{
	@Autowired
	private PubSysLoginLogMapper sysLoginLogMapper;
	
	/**
	 * 新增 系统登录日志
	 * @param sysLoginLog
	 * @return
	 */
	@Transactional
	public void insert(SysLoginLog sysLoginLog){
		sysLoginLogMapper.insert(sysLoginLog);
	}

	/**
	 * 通过主键修改 系统登录日志
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysLoginLogMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 系统登录日志
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysLoginLogMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 系统登录日志 对象
	 * @param primaryKey
	 * @return
	 */
	public SysLoginLog getByPrimaryKey(String primaryKey){
		return sysLoginLogMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 系统登录日志 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysLoginLog> queryForList(Map<String, Object> param){
		return sysLoginLogMapper.queryForList(param);
	}
	
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