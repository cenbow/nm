package com.hs.loan.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.customer.mapper.AppCustomerCallRegisterMapper;
import com.hs.loan.customer.entity.AppCustCallRegister;
import com.hs.base.entity.Page;

/**
 * APP_客户来电记录 业务处理
 * @author autocreate
 * @create 2016-07-07
 */
@Service
@Transactional(readOnly=true)
public class  AppCustomerCallRegisterService{
	@Autowired
	private AppCustomerCallRegisterMapper appCustomerCallRegisterMapper;
	
	/**
	 * 新增 APP_客户来电记录
	 * @param appCustCallRegister 新增对象
	 */
	@Transactional
	public void insert(AppCustCallRegister appCustCallRegister){
		appCustomerCallRegisterMapper.insert(appCustCallRegister);
	}

	/**
	 * 通过主键修改 APP_客户来电记录
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustomerCallRegisterMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户来电记录
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustomerCallRegisterMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户来电记录 对象
	 * @param primaryKey 主键
	 * @return APP_客户来电记录对象
	 */
	public AppCustCallRegister getByPrimaryKey(String primaryKey){
		return appCustomerCallRegisterMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户来电记录 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustCallRegister> queryForList(Map<String, Object> param){
		return appCustomerCallRegisterMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户来电记录 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustCallRegister> queryForPage(Page<AppCustCallRegister> page){
		appCustomerCallRegisterMapper.queryForList(page.getPageParams());
		return (Page<AppCustCallRegister>)page.getPageParams().get(Page.KEY);
	}
}