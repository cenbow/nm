package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.finance.bo.AccLoanPlanBo;
import com.hs.loan.finance.entity.AccLoanPlan;
import com.hs.loan.finance.mapper.AccLoanPlanMapper;

/**
 * ACC_还款计划 业务处理
 * 
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly = true)
public class AccLoanPlanService {
	@Autowired
	private AccLoanPlanMapper accLoanPlanMapper;

	/**
	 * 新增 ACC_还款计划
	 * 
	 * @param accLoanPlan
	 *            新增对象
	 */
	@Transactional
	public void insert(AccLoanPlan accLoanPlan) {
		accLoanPlanMapper.insert(accLoanPlan);
	}

	/**
	 * 通过主键修改 ACC_还款计划
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		accLoanPlanMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款计划
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		accLoanPlanMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 ACC_还款计划 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return ACC_还款计划对象
	 */
	public AccLoanPlan getByPrimaryKey(String primaryKey) {
		return accLoanPlanMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款计划 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<AccLoanPlan> queryForList(Map<String, Object> param) {
		return accLoanPlanMapper.queryForList(param);
	}

	/**
	 * 查询 ACC_还款计划 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccLoanPlan> queryForPage(Page<AccLoanPlan> page) {
		accLoanPlanMapper.queryForList(page.getPageParams());
		return (Page<AccLoanPlan>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 催收查询还款计划
	 * 
	 * @param planParam
	 * @return
	 */
	public List<AccLoanPlan> queryListByParam(Map<String, Object> planParam) {
		// TODO Auto-generated method stub
		return accLoanPlanMapper.queryListByParam(planParam);
	}

	/**
	 * 查询 ACC_还款计划 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccLoanPlanBo> queryListOnNo(Page<AccLoanPlanBo> page) {
		accLoanPlanMapper.queryListOnNo(page.getPageParams());
		return (Page<AccLoanPlanBo>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询 ACC_还款计划 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public AccLoanPlanBo calcEealySumAmt(String loanNo) {
		AccLoanPlanBo accLoanPlanBo = accLoanPlanMapper.calcEealySumAmt(loanNo);
		return accLoanPlanBo;
	}

	public Page<AccLoanPlanBo> queryOutSourceCaseListOnNo(Page<AccLoanPlanBo> page) {
		accLoanPlanMapper.queryOutSourceCaseListOnNo(page.getPageParams());
		return (Page<AccLoanPlanBo>) page.getPageParams().get(Page.KEY);
	}

}