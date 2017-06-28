package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustScoreMapper;
import com.hs.loan.cust.entity.AppCustScore;
import com.hs.base.entity.Page;

/**
 * APP_客户评分 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustScoreService{
	@Autowired
	private AppCustScoreMapper appCustScoreMapper;
	
	/**
	 * 新增 APP_客户评分
	 * @param appCustScore 新增对象
	 */
	@Transactional
	public void insert(AppCustScore appCustScore){
		appCustScoreMapper.insert(appCustScore);
	}

	/**
	 * 通过主键修改 APP_客户评分
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustScoreMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户评分
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustScoreMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户评分 对象
	 * @param primaryKey 主键
	 * @return APP_客户评分对象
	 */
	public AppCustScore getByPrimaryKey(String primaryKey){
		return appCustScoreMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户评分 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustScore> queryForList(Map<String, Object> param){
		return appCustScoreMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户评分 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustScore> queryForPage(Page<AppCustScore> page){
		appCustScoreMapper.queryForList(page.getPageParams());
		return (Page<AppCustScore>)page.getPageParams().get(Page.KEY);
	}
}