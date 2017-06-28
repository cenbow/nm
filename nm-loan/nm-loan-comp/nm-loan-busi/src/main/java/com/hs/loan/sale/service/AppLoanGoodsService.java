package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.sale.mapper.AppLoanGoodsMapper;
import com.hs.loan.sale.entity.AppLoanGoods;
import com.hs.base.entity.Page;

/**
 * APP_分期与商品关系 业务处理
 * @author jqiu
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanGoodsService{
	@Autowired
	private AppLoanGoodsMapper appLoanGoodsMapper;
	/**
	 * 查询该imei通过贷款编号
	 * @param loanNo
	 * @return String
	 */
	public String selectImeiByLoanNo(String loanNo){
		return appLoanGoodsMapper.selectImeiByLoanNo(loanNo);
	}
	/**
	 * 查询该imei号是否已经存在
	 * @param imei
	 * @return int
	 */
	public Integer getImeiExists(String imei){
		return appLoanGoodsMapper.getImeiExists(imei);
	}
	/**
	 * 根据贷款编号更新imei
	 * @param loanNo
	 * @return int
	 */
	@Transactional
	public int updateImeiByLoanNo(HashMap<String,Object> map){
		return appLoanGoodsMapper.updateImeiByLoanNo(map);
	}
	/**
	 * 根据贷款编号查询imei是否为空
	 * @param loanNo
	 * @return Integer
	 */
	public Integer getImeiByLoanNo(Map<String,Object> map){
		return  appLoanGoodsMapper.getImeiByLoanNo(map);
	}
	
	/**
	 * 新增 APP_分期与商品关系
	 * @param appLoanGoods 新增对象
	 */
	@Transactional
	public void insert(AppLoanGoods appLoanGoods){
		appLoanGoodsMapper.insert(appLoanGoods);
	}

	/**
	 * 通过主键修改 APP_分期与商品关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanGoodsMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与商品关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanGoodsMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期与商品关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与商品关系对象
	 */
	public AppLoanGoods getByPrimaryKey(String primaryKey){
		return appLoanGoodsMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与商品关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanGoods> queryForList(Map<String, Object> param){
		return appLoanGoodsMapper.queryForList(param);
	}
	

	/**
	 * 查询分期商品列表
	 * @param loanNo 分期编号
	 * @return List<T>商品列表
	 */
	public List<AppLoanGoods> queryByLoanNo(String loanNo){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		return this.queryForList(param);
	}

	public void deleteByLoanNo(String loanNo) {
		appLoanGoodsMapper.deleteByLoanNo(loanNo);
		
	}
}