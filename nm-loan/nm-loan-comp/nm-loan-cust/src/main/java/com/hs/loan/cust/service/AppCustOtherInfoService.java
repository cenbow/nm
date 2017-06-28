package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustOtherInfoMapper;
import com.hs.loan.cust.entity.AppCustOtherInfo;
import com.hs.base.entity.Page;

/**
 * APP_客户其他信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustOtherInfoService{
	@Autowired
	private AppCustOtherInfoMapper appCustOtherInfoMapper;
	
	/**
	 * 新增 APP_客户其他信息
	 * @param appCustOtherInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustOtherInfo appCustOtherInfo){
		appCustOtherInfoMapper.insert(appCustOtherInfo);
	}

	/**
	 * 通过主键修改 APP_客户其他信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustOtherInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户其他信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustOtherInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户其他信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户其他信息对象
	 */
	public AppCustOtherInfo getByPrimaryKey(String primaryKey){
		return appCustOtherInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户其他信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustOtherInfo> queryForList(Map<String, Object> param){
		return appCustOtherInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户其他信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustOtherInfo> queryForPage(Page<AppCustOtherInfo> page){
		appCustOtherInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustOtherInfo>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	
	
}