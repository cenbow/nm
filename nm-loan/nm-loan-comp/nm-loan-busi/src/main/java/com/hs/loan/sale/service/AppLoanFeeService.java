package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.sale.mapper.AppLoanFeeMapper;
import com.hs.loan.sale.entity.AppLoanFee;
import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_分期与费用项关系 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanFeeService{
	@Autowired
	private AppLoanFeeMapper appLoanFeeMapper;
	public Integer delAppLoanFee(java.util.Map map){
		return appLoanFeeMapper.delAppLoanFee(map);
	}
	/**
	 * 新增 APP_分期与费用项关系
	 * @param appLoanFee 新增对象
	 */
	@Transactional
	public void insert(AppLoanFee appLoanFee){
		appLoanFeeMapper.insert(appLoanFee);
	}

	/**
	 * 通过主键修改 APP_分期与费用项关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanFeeMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与费用项关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanFeeMapper.deleteByPrimaryKey(primaryKey);
	}


	/**
	 * 通过主键取得 APP_分期与费用项关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与费用项关系对象
	 */
	public AppLoanFee getByPrimaryKey(String primaryKey){
		return appLoanFeeMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与费用项关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanFee> queryForList(Map<String, Object> param){
		return appLoanFeeMapper.queryForList(param);
	}
	
	/**
	 * 查询当前使用分期与费用项
	 * @param loanNo 分期编号
	 * @return List<AppLoanFee>
	 */
	public List<AppLoanFee> queryEnableByLoanNo(String loanNo){
		return this.queryByLoanNo(loanNo, CommonConstant.STAT_ENABLE);
	}
	
	/**
	 * 查询历史分期与费用项
	 * @param loanNo 分期编号
	 * @return List<AppLoanFee>
	 */
	public List<AppLoanFee> queryDisableByLoanNo(String loanNo){
		return this.queryByLoanNo(loanNo, CommonConstant.STAT_DISABLE);
	}
		
		
	/**
	 * 查询分期与费用项
	 * @param loanNo 分期编号
	 * @param stat   状态
	 * @return List<AppLoanFee>
	 */
	private List<AppLoanFee> queryByLoanNo(String loanNo,String stat){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("stat", stat);
		List<AppLoanFee> list = this.queryForList(param);
		return list;
	}
	/**
	 * 查询分期可选的费用项
	 * @param loanNo 分期编号
	 * @param stat   状态
	 * @return List<AppLoanFee>
	 */
	public List<AppLoanFee> querySelByLoanNo(String loanNo){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("isSel", CommonConstant.COMMON_YES);
		param.put("stat", CommonConstant.STAT_ENABLE);
		param.put("isChoose", CommonConstant.COMMON_YES);
		List<AppLoanFee> list = this.queryForList(param);
		return list;
	}
	
	/**
	 * 删除分期与费用项
	 * @param loanNo 分期编号
	 */
	@Transactional
	public void deleteByloanNo(String loanNo) {
		appLoanFeeMapper.deleteByLoanNo(loanNo);
		
	}
}