package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccRepayAdvanRegMapper;
import com.hs.loan.finance.entity.AccRepayAdvanReg;
import com.hs.base.entity.Page;

/**
 * ACC_还款登记（提前结清） 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccRepayAdvanRegService{
	@Autowired
	private AccRepayAdvanRegMapper accRepayAdvanRegMapper;
	
	/**
	 * 新增 ACC_还款登记（提前结清）
	 * @param accRepayAdvanReg 新增对象
	 */
	@Transactional
	public void insert(AccRepayAdvanReg accRepayAdvanReg){
		accRepayAdvanRegMapper.insert(accRepayAdvanReg);
	}

	/**
	 * 通过主键修改 ACC_还款登记（提前结清）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accRepayAdvanRegMapper.updateByPrimaryKeySelective(map);
	}
	/**
	 * 通过贷款编号，开始期数状态修改 ACC_还款登记（提前结清）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateBySelective(Map<String, Object> map){
		accRepayAdvanRegMapper.updateBySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款登记（提前结清）
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accRepayAdvanRegMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_还款登记（提前结清） 对象
	 * @param primaryKey 主键
	 * @return ACC_还款登记（提前结清）对象
	 */
	public AccRepayAdvanReg getByPrimaryKey(String primaryKey){
		return accRepayAdvanRegMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款登记（提前结清） 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccRepayAdvanReg> queryForList(Map<String, Object> param){
		return accRepayAdvanRegMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_还款登记（提前结清） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayAdvanReg> queryForPage(Page<AccRepayAdvanReg> page){
		accRepayAdvanRegMapper.queryForList(page.getPageParams());
		return (Page<AccRepayAdvanReg>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 提前结清更新预期案件表状态
	 * @param loanNo
	 */
	@Transactional
	public void updateOveLoanStat(String loanNo) {
		accRepayAdvanRegMapper.updateOveLoanStat(loanNo);
		
	}
	/**
	 * 获取分期信息
	 * @param loanNo
	 */
	public Map<String, Object> getAppLoanAcct(String loanNo) {
		return accRepayAdvanRegMapper.getAppLoanAcct(loanNo);
	}
}