package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustCreditMapper;
import com.hs.loan.cust.entity.AppCustCredit;
import com.hs.base.entity.Page;

/**
 * APP_客户授信额度 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustCreditService{
	@Autowired
	private AppCustCreditMapper appCustCreditMapper;
	
	/**
	 * 新增 APP_客户授信额度
	 * @param appCustCredit 新增对象
	 */
	@Transactional
	public void insert(AppCustCredit appCustCredit){
		appCustCreditMapper.insert(appCustCredit);
	}

	/**
	 * 通过主键修改 APP_客户授信额度
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustCreditMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户授信额度
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustCreditMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户授信额度 对象
	 * @param primaryKey 主键
	 * @return APP_客户授信额度对象
	 */
	public AppCustCredit getByPrimaryKey(String primaryKey){
		return appCustCreditMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户授信额度 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustCredit> queryForList(Map<String, Object> param){
		return appCustCreditMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户授信额度 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustCredit> queryForPage(Page<AppCustCredit> page){
		appCustCreditMapper.queryForList(page.getPageParams());
		return (Page<AppCustCredit>)page.getPageParams().get(Page.KEY);
	}
}