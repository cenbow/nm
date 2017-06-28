package com.hs.loan.sale.mapper;

import java.util.HashMap;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanGoods;

/**
 * APP_分期与商品关系 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppLoanGoodsMapper extends BaseMapper<AppLoanGoods>{
	/**
	 * 查询该imei通过贷款编号
	 * @param loanNo
	 * @return String
	 */
	public String selectImeiByLoanNo(String loanNo);
	/**
	 * 查询该imei号是否已经存在
	 * @param imei
	 * @return Integer
	 */
	public Integer getImeiExists(String imei);
	/**
	 * 根据贷款编号更新imei
	 * @param loanNo
	 * @return int
	 */
	public int updateImeiByLoanNo(HashMap<String,Object> map);
	/**
	 * 根据贷款编号查询imei是否为空
	 * @param loanNo
	 * @return Integer
	 */
    public Integer getImeiByLoanNo(Map<String,Object> map);
	void deleteByLoanNo(String loanNo);
	
}