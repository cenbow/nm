package com.hs.loan.author.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.author.mapper.AppLoanSpcailpriInfoMapper;
import com.hs.loan.author.entity.AppLoanSpcailpriInfo;
import com.hs.base.entity.Page;

/**
 * APP_销售直通车信息 业务处理
 * @author autocreate
 * @create 2016-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanSpcailpriInfoService{
	@Autowired
	private AppLoanSpcailpriInfoMapper appLoanSpcailpriInfoMapper;
	
	/**
	 * 新增 APP_销售直通车信息
	 * @param appLoanSpcailpriInfo 新增对象
	 */
	@Transactional
	public void insert(AppLoanSpcailpriInfo appLoanSpcailpriInfo){
		appLoanSpcailpriInfoMapper.insert(appLoanSpcailpriInfo);
	}

	/**
	 * 通过主键修改 APP_销售直通车信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanSpcailpriInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_销售直通车信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanSpcailpriInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_销售直通车信息 对象
	 * @param primaryKey 主键
	 * @return APP_销售直通车信息对象
	 */
	public AppLoanSpcailpriInfo getByPrimaryKey(String primaryKey){
		return appLoanSpcailpriInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_销售直通车信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanSpcailpriInfo> queryForList(Map<String, Object> param){
		return appLoanSpcailpriInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_销售直通车信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanSpcailpriInfo> queryForPage(Page<AppLoanSpcailpriInfo> page){
		appLoanSpcailpriInfoMapper.queryForList(page.getPageParams());
		return (Page<AppLoanSpcailpriInfo>)page.getPageParams().get(Page.KEY);
	}
}