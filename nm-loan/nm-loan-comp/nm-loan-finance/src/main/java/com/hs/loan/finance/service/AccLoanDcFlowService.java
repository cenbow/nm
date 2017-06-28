package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccLoanDcFlowMapper;
import com.hs.loan.finance.entity.AccLoanDcFlow;
import com.hs.base.entity.Page;

/**
 * ACC_借贷流水 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccLoanDcFlowService{
	@Autowired
	private AccLoanDcFlowMapper accLoanDcFlowMapper;
	
	/**
	 * 新增 ACC_借贷流水
	 * @param accLoanDcFlow 新增对象
	 */
	@Transactional
	public void insert(AccLoanDcFlow accLoanDcFlow){
		accLoanDcFlowMapper.insert(accLoanDcFlow);
	}

	/**
	 * 通过主键修改 ACC_借贷流水
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accLoanDcFlowMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_借贷流水
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accLoanDcFlowMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_借贷流水 对象
	 * @param primaryKey 主键
	 * @return ACC_借贷流水对象
	 */
	public AccLoanDcFlow getByPrimaryKey(String primaryKey){
		return accLoanDcFlowMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_借贷流水 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccLoanDcFlow> queryForList(Map<String, Object> param){
		return accLoanDcFlowMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_借贷流水 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccLoanDcFlow> queryForPage(Page<AccLoanDcFlow> page){
		accLoanDcFlowMapper.queryForList(page.getPageParams());
		return (Page<AccLoanDcFlow>)page.getPageParams().get(Page.KEY);
	}
}