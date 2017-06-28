package com.hs.loan.approve.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.approve.mapper.AppLoanApprTermMapper;
import com.hs.loan.approve.entity.AppLoanApprTerm;
import com.hs.base.entity.Page;

/**
 * APP_审批术语表 业务处理
 * @author autocreate
 * @create 2016-04-11
 */
@Service
@Transactional
public class  AppLoanApprTermService{
	@Autowired
	private AppLoanApprTermMapper appLoanApprTermMapper;
	
	/**
	 * 新增 APP_审批术语表
	 * @param appLoanApprTerm 新增对象
	 */
	@Transactional
	public void insert(AppLoanApprTerm appLoanApprTerm){
		appLoanApprTermMapper.insert(appLoanApprTerm);
	}

	/**
	 * 通过主键修改 APP_审批术语表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanApprTermMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_审批术语表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanApprTermMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_审批术语表 对象
	 * @param primaryKey 主键
	 * @return APP_审批术语表对象
	 */
	public AppLoanApprTerm getByPrimaryKey(String primaryKey){
		return appLoanApprTermMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_审批术语表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanApprTerm> queryForList(Map<String, Object> param){
		return appLoanApprTermMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_审批术语表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanApprTerm> queryForPage(Page<AppLoanApprTerm> page){
		appLoanApprTermMapper.queryForList(page.getPageParams());
		return (Page<AppLoanApprTerm>)page.getPageParams().get(Page.KEY);
	}
}