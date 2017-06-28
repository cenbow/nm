package com.hs.loan.acct.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.acct.entity.AccLoanRepayPlanOvdu;
import com.hs.loan.acct.mapper.AccLoanRepayPlanOvduMapper;

/**
 * ACC_还款计划（逾期） 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  AccLoanRepayPlanOvduService{
	@Autowired
	private AccLoanRepayPlanOvduMapper accLoanRepayPlanOvduMapper;
	
	/**
	 * 新增 ACC_还款计划（逾期）
	 * @param accLoanRepayPlanOvdu 新增对象
	 */
	@Transactional
	public void insert(AccLoanRepayPlanOvdu accLoanRepayPlanOvdu){
		accLoanRepayPlanOvduMapper.insert(accLoanRepayPlanOvdu);
	}

	/**
	 * 通过主键修改 ACC_还款计划（逾期）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accLoanRepayPlanOvduMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款计划（逾期）
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accLoanRepayPlanOvduMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_还款计划（逾期） 对象
	 * @param primaryKey 主键
	 * @return ACC_还款计划（逾期）对象
	 */
	private AccLoanRepayPlanOvdu getByPrimaryKey(String primaryKey){
		return accLoanRepayPlanOvduMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款计划（逾期） 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AccLoanRepayPlanOvdu> queryForList(Map<String, Object> param){
		return accLoanRepayPlanOvduMapper.queryForList(param);
	}
	

	/**
	 * 通过分期编号查询还款计划（逾期） 列表
	 * @param loanNo 分期编号
	 * @return List<AccLoanRepayPlanOvdu>
	 */
	public List<AccLoanRepayPlanOvdu> queryByLoanNo(String loanNo){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		List<AccLoanRepayPlanOvdu> list = this.queryForList(param);
		return list;
	}
}