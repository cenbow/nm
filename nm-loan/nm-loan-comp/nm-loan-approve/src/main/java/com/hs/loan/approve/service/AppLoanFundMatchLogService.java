package com.hs.loan.approve.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.approve.mapper.AppLoanFundMatchLogMapper;
import com.hs.loan.approve.entity.AppLoanFundMatchLog;
import com.hs.base.entity.Page;

/**
 * APP_分期资金匹配记录 业务处理
 * @author autocreate
 * @create 2015-11-23
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanFundMatchLogService{
	@Autowired
	private AppLoanFundMatchLogMapper appLoanFundMatchLogMapper;
	
	/**
	 * 新增 APP_分期资金匹配记录
	 * @param appLoanFundMatchLog 新增对象
	 */
	@Transactional
	public void insert(AppLoanFundMatchLog appLoanFundMatchLog){
		appLoanFundMatchLogMapper.insert(appLoanFundMatchLog);
	}

	/**
	 * 通过主键修改 APP_分期资金匹配记录
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanFundMatchLogMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期资金匹配记录
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanFundMatchLogMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期资金匹配记录 对象
	 * @param primaryKey 主键
	 * @return APP_分期资金匹配记录对象
	 */
	public AppLoanFundMatchLog getByPrimaryKey(String primaryKey){
		return appLoanFundMatchLogMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期资金匹配记录 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanFundMatchLog> queryForList(Map<String, Object> param){
		return appLoanFundMatchLogMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期资金匹配记录 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanFundMatchLog> queryForPage(Page<AppLoanFundMatchLog> page){
		appLoanFundMatchLogMapper.queryForList(page.getPageParams());
		return (Page<AppLoanFundMatchLog>)page.getPageParams().get(Page.KEY);
	}

	
	
}