package com.hs.loan.acct.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.acct.mapper.AppLoanActAcctMapper;
import com.hs.loan.acct.entity.AppLoanActAcct;
import com.hs.base.entity.Page;

/**
 * APP_贷款实际打款情况 业务处理
 * @author autocreate
 * @create 2017-03-21
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanActAcctService{
	@Autowired
	private AppLoanActAcctMapper appLoanActAcctMapper;
	
	/**
	 * 新增 APP_贷款实际打款情况
	 * @param appLoanActAcct 新增对象
	 */
	@Transactional
	public void insert(AppLoanActAcct appLoanActAcct){
		appLoanActAcctMapper.insert(appLoanActAcct);
	}

	/**
	 * 通过主键修改 APP_贷款实际打款情况
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanActAcctMapper.updateByPrimaryKeySelective(map);
	}
	
	/**
	 * 通过LoanNo修改 APP_贷款实际打款情况
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByLoanNo(Map<String, Object> map){
		appLoanActAcctMapper.updateByLoanNo(map);
	}


	/**
	 * 通过主键删除 APP_贷款实际打款情况
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanActAcctMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_贷款实际打款情况 对象
	 * @param primaryKey 主键
	 * @return APP_贷款实际打款情况对象
	 */
	public AppLoanActAcct getByPrimaryKey(String primaryKey){
		return appLoanActAcctMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_贷款实际打款情况 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanActAcct> queryForList(Map<String, Object> param){
		return appLoanActAcctMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_贷款实际打款情况 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanActAcct> queryForPage(Page<AppLoanActAcct> page){
		appLoanActAcctMapper.queryForList(page.getPageParams());
		return (Page<AppLoanActAcct>)page.getPageParams().get(Page.KEY);
	}
}