package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.cust.entity.AppCustInfoExte;
import com.hs.loan.cust.mapper.AppCustInfoExteMapper;

/**
 * APP_客户信息拓展表 业务处理
 * @author autocreate
 * @create 2016-06-21
 */
@Service
@Transactional(readOnly=true)
public class  AppCustInfoExteService{
	@Autowired
	private AppCustInfoExteMapper appCustInfoExteMapper;
	
	/**
	 * 新增 APP_客户信息拓展表
	 * @param appCustInfoExte 新增对象
	 */
	@Transactional
	public void insert(AppCustInfoExte appCustInfoExte){
		appCustInfoExteMapper.insert(appCustInfoExte);
	}

	/**
	 * 通过主键修改 APP_客户信息拓展表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustInfoExteMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户信息拓展表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustInfoExteMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户信息拓展表 对象
	 * @param primaryKey 主键
	 * @return APP_客户信息拓展表对象
	 */
	public AppCustInfoExte getByPrimaryKey(String primaryKey){
		return appCustInfoExteMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户信息拓展表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustInfoExte> queryForList(Map<String, Object> param){
		return appCustInfoExteMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户信息拓展表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustInfoExte> queryForPage(Page<AppCustInfoExte> page){
		appCustInfoExteMapper.queryForList(page.getPageParams());
		return (Page<AppCustInfoExte>)page.getPageParams().get(Page.KEY);
	}
}