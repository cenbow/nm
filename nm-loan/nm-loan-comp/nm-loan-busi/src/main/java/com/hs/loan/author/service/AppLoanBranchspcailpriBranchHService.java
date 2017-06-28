package com.hs.loan.author.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.author.mapper.AppLoanBranchspcailpriBranchHMapper;
import com.hs.loan.author.entity.AppLoanBranchspcailpriBranchH;
import com.hs.base.entity.Page;

/**
 * APP_商户直通车权限历史 业务处理
 * @author autocreate
 * @create 2016-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanBranchspcailpriBranchHService{
	@Autowired
	private AppLoanBranchspcailpriBranchHMapper appLoanBranchspcailpriBranchHMapper;
	
	/**
	 * 新增 APP_商户直通车权限历史
	 * @param appLoanBranchspcailpriBranchH 新增对象
	 */
	@Transactional
	public void insert(AppLoanBranchspcailpriBranchH appLoanBranchspcailpriBranchH){
		appLoanBranchspcailpriBranchHMapper.insert(appLoanBranchspcailpriBranchH);
	}

	/**
	 * 通过主键修改 APP_商户直通车权限历史
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanBranchspcailpriBranchHMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_商户直通车权限历史
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanBranchspcailpriBranchHMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_商户直通车权限历史 对象
	 * @param primaryKey 主键
	 * @return APP_商户直通车权限历史对象
	 */
	public AppLoanBranchspcailpriBranchH getByPrimaryKey(String primaryKey){
		return appLoanBranchspcailpriBranchHMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_商户直通车权限历史 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanBranchspcailpriBranchH> queryForList(Map<String, Object> param){
		return appLoanBranchspcailpriBranchHMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_商户直通车权限历史 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanBranchspcailpriBranchH> queryForPage(Page<AppLoanBranchspcailpriBranchH> page){
		appLoanBranchspcailpriBranchHMapper.queryForList(page.getPageParams());
		return (Page<AppLoanBranchspcailpriBranchH>)page.getPageParams().get(Page.KEY);
	}
}