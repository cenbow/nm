package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustLoanTotalMapper;
import com.hs.loan.cust.entity.AppCustLoanTotal;
import com.hs.base.entity.Page;

/**
 * APP_客户分期信息汇总 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustLoanTotalService{
	@Autowired
	private AppCustLoanTotalMapper appCustLoanTotalMapper;
	
	/**
	 * 新增 APP_客户分期信息汇总
	 * @param appCustLoanTotal 新增对象
	 */
	@Transactional
	public void insert(AppCustLoanTotal appCustLoanTotal){
		appCustLoanTotalMapper.insert(appCustLoanTotal);
	}

	/**
	 * 通过主键修改 APP_客户分期信息汇总
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustLoanTotalMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户分期信息汇总
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustLoanTotalMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户分期信息汇总 对象
	 * @param primaryKey 主键
	 * @return APP_客户分期信息汇总对象
	 */
	public AppCustLoanTotal getByPrimaryKey(String primaryKey){
		return appCustLoanTotalMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户分期信息汇总 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustLoanTotal> queryForList(Map<String, Object> param){
		return appCustLoanTotalMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户分期信息汇总 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustLoanTotal> queryForPage(Page<AppCustLoanTotal> page){
		appCustLoanTotalMapper.queryForList(page.getPageParams());
		return (Page<AppCustLoanTotal>)page.getPageParams().get(Page.KEY);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 根据客户号获取 客户分期汇总信息
	 * @param custNo
	 * @return
	 */
	public AppCustLoanTotal getByNo(String custNo){
		return appCustLoanTotalMapper.getByNo(custNo);
	}
	
}