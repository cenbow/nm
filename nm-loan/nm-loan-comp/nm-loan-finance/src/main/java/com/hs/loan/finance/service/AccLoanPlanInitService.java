package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.finance.entity.AccLoanPlanInit;
import com.hs.loan.finance.mapper.AccLoanPlanInitMapper;

/**
 * ACC_还款计划（初始） 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccLoanPlanInitService{
	@Autowired
	private AccLoanPlanInitMapper accLoanPlanInitMapper;
	
	/**
	 * 新增 ACC_还款计划（初始）
	 * @param accLoanPlanInit 新增对象
	 */
	@Transactional
	public void insert(AccLoanPlanInit accLoanPlanInit){
		accLoanPlanInitMapper.insert(accLoanPlanInit);
	}

	/**
	 * 通过主键修改 ACC_还款计划（初始）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accLoanPlanInitMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款计划（初始）
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accLoanPlanInitMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_还款计划（初始） 对象
	 * @param primaryKey 主键
	 * @return ACC_还款计划（初始）对象
	 */
	public AccLoanPlanInit getByPrimaryKey(String primaryKey){
		return accLoanPlanInitMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款计划（初始） 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccLoanPlanInit> queryForList(Map<String, Object> param){
		return accLoanPlanInitMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_还款计划（初始） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccLoanPlanInit> queryForPage(Page<AccLoanPlanInit> page){
		accLoanPlanInitMapper.queryForList(page.getPageParams());
		return (Page<AccLoanPlanInit>)page.getPageParams().get(Page.KEY);
	}
	
}