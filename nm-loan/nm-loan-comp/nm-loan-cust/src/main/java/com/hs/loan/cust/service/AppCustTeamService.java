package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustTeamMapper;
import com.hs.loan.cust.entity.AppCustTeam;
import com.hs.base.entity.Page;

/**
 * APP_客户与客户组的关系 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustTeamService{
	@Autowired
	private AppCustTeamMapper appCustTeamMapper;
	
	/**
	 * 新增 APP_客户与客户组的关系
	 * @param appCustTeam 新增对象
	 */
	@Transactional
	public void insert(AppCustTeam appCustTeam){
		appCustTeamMapper.insert(appCustTeam);
	}

	/**
	 * 通过主键修改 APP_客户与客户组的关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustTeamMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户与客户组的关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustTeamMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户与客户组的关系 对象
	 * @param primaryKey 主键
	 * @return APP_客户与客户组的关系对象
	 */
	public AppCustTeam getByPrimaryKey(String primaryKey){
		return appCustTeamMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户与客户组的关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustTeam> queryForList(Map<String, Object> param){
		return appCustTeamMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户与客户组的关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustTeam> queryForPage(Page<AppCustTeam> page){
		appCustTeamMapper.queryForList(page.getPageParams());
		return (Page<AppCustTeam>)page.getPageParams().get(Page.KEY);
	}
	
	
	
	
	
}