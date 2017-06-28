package com.hs.loan.acct.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.acct.entity.AccLoanRepayPlan;
import com.hs.loan.acct.mapper.AccLoanRepayPlanMapper;

/**
 * ACC_还款计划 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  AccLoanRepayPlanService{
	@Autowired
	private AccLoanRepayPlanMapper accLoanRepayPlanMapper;
	
	/**
	 * 新增 ACC_还款计划
	 * @param accLoanRepayPlan 新增对象
	 */
	@Transactional
	public void insert(AccLoanRepayPlan accLoanRepayPlan){
		accLoanRepayPlanMapper.insert(accLoanRepayPlan);
	}

	/**
	 * 通过主键修改 ACC_还款计划
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accLoanRepayPlanMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款计划
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accLoanRepayPlanMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_还款计划 对象
	 * @param primaryKey 主键
	 * @return ACC_还款计划对象
	 */
	private AccLoanRepayPlan getByPrimaryKey(String primaryKey){
		return accLoanRepayPlanMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款计划 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AccLoanRepayPlan> queryForList(Map<String, Object> param){
		return accLoanRepayPlanMapper.queryForList(param);
	}
	

	/**
	 * 通过分期编号查询还款计划 列表
	 * @param loanNo 分期编号
	 * @return List<AccLoanRepayPlan>
	 */
	public List<AccLoanRepayPlan> queryByLoanNo(String loanNo){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		List<AccLoanRepayPlan> list = this.queryForList(param);
		return list;
	}
}