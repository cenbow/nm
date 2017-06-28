package com.hs.loan.approve.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.approve.mapper.AppLoanApprBookMapper;
import com.hs.loan.approve.entity.AppLoanApprBook;
import com.hs.base.entity.Page;

/**
 * APP_分期审批案件批注表 业务处理
 * @author autocreate
 * @create 2015-11-24
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanApprBookService{
	@Autowired
	private AppLoanApprBookMapper appLoanApprBookMapper;
	
	/**
	 * 新增 APP_分期审批案件批注表
	 * @param appLoanApprBook 新增对象
	 */
	@Transactional
	public void insert(AppLoanApprBook appLoanApprBook){
		appLoanApprBookMapper.insert(appLoanApprBook);
	}

	/**
	 * 通过主键修改 APP_分期审批案件批注表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanApprBookMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期审批案件批注表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanApprBookMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期审批案件批注表 对象
	 * @param primaryKey 主键
	 * @return APP_分期审批案件批注表对象
	 */
	public AppLoanApprBook getByPrimaryKey(String primaryKey){
		return appLoanApprBookMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期审批案件批注表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanApprBook> queryForList(Map<String, Object> param){
		return appLoanApprBookMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期审批案件批注表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanApprBook> queryForPage(Page<AppLoanApprBook> page){
		appLoanApprBookMapper.queryForList(page.getPageParams());
		return (Page<AppLoanApprBook>)page.getPageParams().get(Page.KEY);
	}

	public void deleteByLoanNo(String loanNo) {
		appLoanApprBookMapper.deleteByLoanNo(loanNo);
	}
}