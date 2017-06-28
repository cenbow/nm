package com.hs.loan.author.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.author.mapper.AppLoanSpcailpriSalerHMapper;
import com.hs.loan.author.entity.AppLoanSpcailpriSalerH;
import com.hs.base.entity.Page;

/**
 * APP_销售直通车权限历史 业务处理
 * @author autocreate
 * @create 2016-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanSpcailpriSalerHService{
	@Autowired
	private AppLoanSpcailpriSalerHMapper appLoanSpcailpriSalerHMapper;
	
	/**
	 * 新增 APP_销售直通车权限历史
	 * @param appLoanSpcailpriSalerH 新增对象
	 */
	@Transactional
	public void insert(AppLoanSpcailpriSalerH appLoanSpcailpriSalerH){
		appLoanSpcailpriSalerHMapper.insert(appLoanSpcailpriSalerH);
	}

	/**
	 * 通过主键修改 APP_销售直通车权限历史
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanSpcailpriSalerHMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_销售直通车权限历史
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanSpcailpriSalerHMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_销售直通车权限历史 对象
	 * @param primaryKey 主键
	 * @return APP_销售直通车权限历史对象
	 */
	public AppLoanSpcailpriSalerH getByPrimaryKey(String primaryKey){
		return appLoanSpcailpriSalerHMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_销售直通车权限历史 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanSpcailpriSalerH> queryForList(Map<String, Object> param){
		return appLoanSpcailpriSalerHMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_销售直通车权限历史 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanSpcailpriSalerH> queryForPage(Page<AppLoanSpcailpriSalerH> page){
		appLoanSpcailpriSalerHMapper.queryForList(page.getPageParams());
		return (Page<AppLoanSpcailpriSalerH>)page.getPageParams().get(Page.KEY);
	}
}