package com.hs.loan.acct.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.acct.entity.AppCustRegInfo;
import com.hs.loan.acct.mapper.AppCustRegInfoMapper;

/**
 * APP_客户登录信息 业务处理
 * @author autocreate
 * @create 2017-03-31
 */
@Service
@Transactional(readOnly=true)
public class  AppCustRegInfoService{
	@Autowired
	private AppCustRegInfoMapper appCustRegInfoMapper;
	
	/**
	 * 新增 APP_客户登录信息
	 * @param appCustRegInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustRegInfo appCustRegInfo){
		appCustRegInfoMapper.insert(appCustRegInfo);
	}

	/**
	 * 通过主键修改 APP_客户登录信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustRegInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户登录信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustRegInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户登录信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户登录信息对象
	 */
	public AppCustRegInfo getByPrimaryKey(String primaryKey){
		return appCustRegInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户登录信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustRegInfo> queryForList(Map<String, Object> param){
		return appCustRegInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户登录信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustRegInfo> queryForPage(Page<AppCustRegInfo> page){
		appCustRegInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustRegInfo>)page.getPageParams().get(Page.KEY);
	}
}