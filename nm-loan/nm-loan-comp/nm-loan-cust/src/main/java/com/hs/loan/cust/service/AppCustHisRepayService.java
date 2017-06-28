package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustHisRepayMapper;
import com.hs.loan.cust.entity.AppCustHisRepay;
import com.hs.base.entity.Page;

/**
 * APP_客户历史还款情况 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustHisRepayService{
	@Autowired
	private AppCustHisRepayMapper appCustHisRepayMapper;
	
	/**
	 * 新增 APP_客户历史还款情况
	 * @param appCustHisRepay 新增对象
	 */
	@Transactional
	public void insert(AppCustHisRepay appCustHisRepay){
		appCustHisRepayMapper.insert(appCustHisRepay);
	}

	/**
	 * 通过主键修改 APP_客户历史还款情况
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustHisRepayMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户历史还款情况
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustHisRepayMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户历史还款情况 对象
	 * @param primaryKey 主键
	 * @return APP_客户历史还款情况对象
	 */
	public AppCustHisRepay getByPrimaryKey(String primaryKey){
		return appCustHisRepayMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户历史还款情况 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustHisRepay> queryForList(Map<String, Object> param){
		return appCustHisRepayMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户历史还款情况 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustHisRepay> queryForPage(Page<AppCustHisRepay> page){
		appCustHisRepayMapper.queryForList(page.getPageParams());
		return (Page<AppCustHisRepay>)page.getPageParams().get(Page.KEY);
	}
}