package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustCallRegisterMapper;
import com.hs.loan.cust.entity.AppCustCallRegister;
import com.hs.base.entity.Page;

/**
 * APP_客户来电记录 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustCallRegisterService{
	@Autowired
	private AppCustCallRegisterMapper appCustCallRegisterMapper;
	
	/**
	 * 新增 APP_客户来电记录
	 * @param appCustCallRegister 新增对象
	 */
	@Transactional
	public void insert(AppCustCallRegister appCustCallRegister){
		appCustCallRegisterMapper.insert(appCustCallRegister);
	}

	/**
	 * 通过主键修改 APP_客户来电记录
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustCallRegisterMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户来电记录
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustCallRegisterMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户来电记录 对象
	 * @param primaryKey 主键
	 * @return APP_客户来电记录对象
	 */
	public AppCustCallRegister getByPrimaryKey(String primaryKey){
		return appCustCallRegisterMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户来电记录 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustCallRegister> queryForList(Map<String, Object> param){
		return appCustCallRegisterMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户来电记录 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustCallRegister> queryForPage(Page<AppCustCallRegister> page){
		appCustCallRegisterMapper.queryForList(page.getPageParams());
		return (Page<AppCustCallRegister>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 分页查询 客户来电记录 按时间倒序排序
	 * 必须的参数 custNo
	 * @param page
	 * @return
	 */
	public Page<AppCustCallRegister> queryCustCallRegister(Page<AppCustCallRegister> page){
		return queryForPage(page);
	}
	
	
	
	
}