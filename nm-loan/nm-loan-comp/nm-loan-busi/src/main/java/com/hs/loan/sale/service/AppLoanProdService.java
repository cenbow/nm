package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.commons.constants.CommonConstant;
import com.hs.loan.sale.entity.AppLoanProd;
import com.hs.loan.sale.mapper.AppLoanProdMapper;

/**
 * APP_分期与产品关系 业务处理
 * @author jqiu
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanProdService{
	@Autowired
	private AppLoanProdMapper appLoanProdMapper;
	public Map<String,Object> getPreFee(Map map){
		return appLoanProdMapper.getPreFee(map);
	}
	/**
	 * 新增 APP_分期与产品关系
	 * @param appLoanProd 新增对象
	 */
	@Transactional
	public void insert(AppLoanProd appLoanProd){
		appLoanProdMapper.insert(appLoanProd);
	}

	/**
	 * 通过主键修改 APP_分期与产品关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanProdMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与产品关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanProdMapper.deleteByPrimaryKey(primaryKey);
	}
	/**
	 * 通过主键删除 APP_分期与产品关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByLoanNo(String loanNo){
		appLoanProdMapper.deleteByLoanNo(loanNo);
	}

	

	/**
	 * 通过主键取得 APP_分期与产品关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与产品关系对象
	 */
	private AppLoanProd getByPrimaryKey(String primaryKey){
		return appLoanProdMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与产品关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanProd> queryForList(Map<String, Object> param){
		return appLoanProdMapper.queryForList(param);
	}
	

	
	/**
	 * 查询当前分期产品信息
	 * @param loanNo 分期编号
	 * @return AppLoanProd
	 */
	public AppLoanProd getEnableAcctByLoanNo(String loanNo){
		return this.getAcctByLoanNo(loanNo, CommonConstant.STAT_ENABLE);
	}
	
	/**
	 * 查询历史分期产品信息
	 * @param loanNo 分期编号
	 * @return AppLoanProd
	 */
	public AppLoanProd getDisableAcctByLoanNo(String loanNo){
		return this.getAcctByLoanNo(loanNo, CommonConstant.STAT_DISABLE);
	}
		
		
	/**
	 * 查询分期产品信息
	 * @param loanNo 分期编号
	 * @param stat   状态
	 * @return AppLoanProd
	 */
	private AppLoanProd getAcctByLoanNo(String loanNo,String stat){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("prodStat", stat);
		List<AppLoanProd> list = this.queryForList(param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}