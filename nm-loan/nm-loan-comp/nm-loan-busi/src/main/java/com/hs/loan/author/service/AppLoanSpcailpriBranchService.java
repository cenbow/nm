package com.hs.loan.author.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.author.entity.AppLoanSpcailpriBranch;
import com.hs.loan.author.mapper.AppLoanSpcailpriBranchMapper;

/**
 * APP_商户直通车权限 业务处理
 * @author autocreate
 * @create 2016-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanSpcailpriBranchService{
	@Autowired
	private AppLoanSpcailpriBranchMapper appLoanSpcailpriBranchMapper;
	
	/**
	 * 新增 APP_商户直通车权限
	 * @param appLoanSpcailpriBranch 新增对象
	 */
	@Transactional
	public void insert(AppLoanSpcailpriBranch appLoanSpcailpriBranch){
		appLoanSpcailpriBranchMapper.insert(appLoanSpcailpriBranch);
	}

	/**
	 * 通过主键修改 APP_商户直通车权限
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanSpcailpriBranchMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_商户直通车权限
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanSpcailpriBranchMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_商户直通车权限 对象
	 * @param primaryKey 主键
	 * @return APP_商户直通车权限对象
	 */
	public AppLoanSpcailpriBranch getByPrimaryKey(String primaryKey){
		return appLoanSpcailpriBranchMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_商户直通车权限 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanSpcailpriBranch> queryForList(Map<String, Object> param){
		return appLoanSpcailpriBranchMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_商户直通车权限 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanSpcailpriBranch> queryForPage(Page<AppLoanSpcailpriBranch> page){
		appLoanSpcailpriBranchMapper.queryForList(page.getPageParams());
		return (Page<AppLoanSpcailpriBranch>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询不在商户直通车的商户
	 * @param param
	 * @return
	 */
	public Page<AppLoanSpcailpriBranch> queryNotSpcailpriBranchForPage(Page<AppLoanSpcailpriBranch> page)
	{
		 appLoanSpcailpriBranchMapper.queryNotSpcailpriBranchList(page.getPageParams());
		 return (Page<AppLoanSpcailpriBranch>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 批量新增 APP_商户直通车权限
	 * @param appLoanSpcailpriBranch 新增对象
	 */
	@Transactional
	public void addbatchInsert(List<AppLoanSpcailpriBranch> list)
	{
		appLoanSpcailpriBranchMapper.batchInsert(list);
	}
}