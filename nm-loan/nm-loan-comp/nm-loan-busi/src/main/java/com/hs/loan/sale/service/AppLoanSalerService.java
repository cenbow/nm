package com.hs.loan.sale.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.sale.entity.AppLoanSaler;
import com.hs.loan.sale.mapper.AppLoanSalerMapper;

/**
 * APP_分期与销售关系 业务处理
 * @author jqiu
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanSalerService{
	@Autowired
	private AppLoanSalerMapper appLoanSalerMapper;
	
	/**
	 * 新增 APP_分期与销售关系
	 * @param appLoanSaler 新增对象
	 */
	@Transactional
	public void insert(AppLoanSaler appLoanSaler){
		appLoanSalerMapper.insert(appLoanSaler);
	}

	/**
	 * 通过主键修改 APP_分期与销售关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanSalerMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与销售关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanSalerMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期与销售关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与销售关系对象
	 */
	private AppLoanSaler getByPrimaryKey(String primaryKey){
		return appLoanSalerMapper.getByPrimaryKey(primaryKey);
	}


	/**
	 * 查询分期销售信息
	 * @param loanNo 分期编号
	 * @return AppLoanSaler 销售信息
	 */
	public AppLoanSaler getByLoanNo(String loanNo){
		return this.getByPrimaryKey(loanNo);
	}
}