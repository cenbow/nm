package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustHisOverdueMapper;
import com.hs.loan.cust.entity.AppCustHisOverdue;
import com.hs.base.entity.Page;

/**
 * APP_客户历史逾期情况 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustHisOverdueService{
	@Autowired
	private AppCustHisOverdueMapper appCustHisOverdueMapper;
	
	/**
	 * 新增 APP_客户历史逾期情况
	 * @param appCustHisOverdue 新增对象
	 */
	@Transactional
	public void insert(AppCustHisOverdue appCustHisOverdue){
		appCustHisOverdueMapper.insert(appCustHisOverdue);
	}

	/**
	 * 通过主键修改 APP_客户历史逾期情况
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustHisOverdueMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户历史逾期情况
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustHisOverdueMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户历史逾期情况 对象
	 * @param primaryKey 主键
	 * @return APP_客户历史逾期情况对象
	 */
	public AppCustHisOverdue getByPrimaryKey(String primaryKey){
		return appCustHisOverdueMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户历史逾期情况 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustHisOverdue> queryForList(Map<String, Object> param){
		return appCustHisOverdueMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户历史逾期情况 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustHisOverdue> queryForPage(Page<AppCustHisOverdue> page){
		appCustHisOverdueMapper.queryForList(page.getPageParams());
		return (Page<AppCustHisOverdue>)page.getPageParams().get(Page.KEY);
	}
}