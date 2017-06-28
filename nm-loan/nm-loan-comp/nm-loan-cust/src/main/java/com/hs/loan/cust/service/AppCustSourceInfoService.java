package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.cust.entity.AppCustSourceInfo;
import com.hs.loan.cust.mapper.AppCustSourceInfoMapper;

/**
 * APP_客户来源信息表 业务处理
 * @author autocreate
 * @create 2017-03-18
 */
@Service
@Transactional(readOnly=true)
public class  AppCustSourceInfoService{
	@Autowired
	private AppCustSourceInfoMapper appCustSourceInfoMapper;
	
	/**
	 * 新增 APP_客户来源信息表
	 * @param appCustSourceInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustSourceInfo appCustSourceInfo){
		appCustSourceInfoMapper.insert(appCustSourceInfo);
	}

	/**
	 * 通过主键修改 APP_客户来源信息表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustSourceInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户来源信息表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustSourceInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过custNo取得 APP_客户来源信息表 对象
	 * @param custNo
	 * @return APP_客户来源信息表对象
	 */
	public AppCustSourceInfo getByCustNo(String custNo){
		return appCustSourceInfoMapper.getByCustNo(custNo);
	}

	/**
	 * 通过主键取得 APP_客户来源信息表 对象
	 * @param primaryKey 主键
	 * @return APP_客户来源信息表对象
	 */
	public AppCustSourceInfo getByPrimaryKey(String primaryKey){
		return appCustSourceInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户来源信息表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustSourceInfo> queryForList(Map<String, Object> param){
		return appCustSourceInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户来源信息表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustSourceInfo> queryForPage(Page<AppCustSourceInfo> page){
		appCustSourceInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustSourceInfo>)page.getPageParams().get(Page.KEY);
	}
}