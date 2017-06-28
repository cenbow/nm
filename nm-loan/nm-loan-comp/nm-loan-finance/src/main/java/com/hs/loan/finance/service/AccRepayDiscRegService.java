package com.hs.loan.finance.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccRepayDiscRegMapper;
import com.hs.loan.finance.bo.AccRepayDiscRegBo;
import com.hs.loan.finance.entity.AccRepayDiscReg;
import com.hs.base.entity.Page;

/**
 * ACC_还款登记（费用减免） 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccRepayDiscRegService{
	@Autowired
	private AccRepayDiscRegMapper accRepayDiscRegMapper;
	
	/**
	 * 新增 ACC_还款登记（费用减免）
	 * @param accRepayDiscReg 新增对象
	 */
	@Transactional
	public void insert(AccRepayDiscReg accRepayDiscReg){
		accRepayDiscRegMapper.insert(accRepayDiscReg);
	}

	/**
	 * 通过主键修改 ACC_还款登记（费用减免）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accRepayDiscRegMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款登记（费用减免）
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accRepayDiscRegMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_还款登记（费用减免） 对象
	 * @param primaryKey 主键
	 * @return ACC_还款登记（费用减免）对象
	 */
	public AccRepayDiscReg getByPrimaryKey(String primaryKey){
		return accRepayDiscRegMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款登记（费用减免） 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccRepayDiscReg> queryForList(Map<String, Object> param){
		return accRepayDiscRegMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_还款登记（费用减免） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayDiscReg> queryForPage(Page<AccRepayDiscReg> page){
		accRepayDiscRegMapper.queryForList(page.getPageParams());
		return (Page<AccRepayDiscReg>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 ACC_还款登记（费用减免） 分页列表
	 * @param page	查询参数page(时间)
	 * @return List<T>列表
	 */
	public Page<AccRepayDiscRegBo> queryForPageByInstDate(Page<AccRepayDiscRegBo> page){
		accRepayDiscRegMapper.queryForPageByInstDate(page.getPageParams());
		return (Page<AccRepayDiscRegBo>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 获取委外案件各项金额
	 * @param loanNo
	 * @return
	 */
	public Map<String, Object> getOutMap(String loanNo) {
		return accRepayDiscRegMapper.getOutMap(loanNo);
	}

	public Integer updateOutSource(String loanNo, BigDecimal discAmt) {
		Map<String, Object> pageParams = new HashMap<>();
		pageParams.put("loanNo", loanNo);
		pageParams.put("discAmt", discAmt);
		return accRepayDiscRegMapper.updateOutSource(pageParams);
	}
	 
}