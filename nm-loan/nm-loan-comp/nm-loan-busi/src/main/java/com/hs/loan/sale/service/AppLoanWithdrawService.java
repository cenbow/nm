package com.hs.loan.sale.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.sale.entity.AppWithdrawInfo;
import com.hs.loan.sale.mapper.AppWithdrawInfoMapper;

/**
 * APP_提现记录信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanWithdrawService{
	@Autowired
	private AppWithdrawInfoMapper appLoanWithdrawMapper;
	
	/**
	 * 查询 提现记录 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public Page<AppWithdrawInfo> queryForList(Page<AppWithdrawInfo> page) {
		appLoanWithdrawMapper.queryForList(page.getPageParams());
		return (Page<AppWithdrawInfo>) page.getPageParams().get(Page.KEY);
	}
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		appLoanWithdrawMapper.updateByPrimaryKeySelective(map);
	}
}