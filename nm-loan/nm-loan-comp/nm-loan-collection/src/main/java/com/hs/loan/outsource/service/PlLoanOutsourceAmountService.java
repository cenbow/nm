package com.hs.loan.outsource.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.outsource.mapper.PlLoanOutsourceAmountMapper;
import com.hs.loan.outsource.entity.PlLoanOutsourceAmount;
import com.hs.base.entity.Page;

/**
 * PL_委外金额明细 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  PlLoanOutsourceAmountService{
	@Autowired
	private PlLoanOutsourceAmountMapper plLoanOutsourceAmountMapper;
	
	/**
	 * 新增 PL_委外金额明细
	 * @param plLoanOutsourceAmount 新增对象
	 */
	@Transactional
	public void insert(PlLoanOutsourceAmount plLoanOutsourceAmount){
		plLoanOutsourceAmountMapper.insert(plLoanOutsourceAmount);
	}

	/**
	 * 通过主键修改 PL_委外金额明细
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		plLoanOutsourceAmountMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_委外金额明细
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		plLoanOutsourceAmountMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PL_委外金额明细 对象
	 * @param primaryKey 主键
	 * @return PL_委外金额明细对象
	 */
	public PlLoanOutsourceAmount getByPrimaryKey(String primaryKey){
		return plLoanOutsourceAmountMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_委外金额明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOutsourceAmount> queryForList(Map<String, Object> param){
		return plLoanOutsourceAmountMapper.queryForList(param);
	}
	/**
	 * 查询 PL_委外金额明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOutsourceAmount> queryOutSourceFeeList(Map<String, Object> param){
		return plLoanOutsourceAmountMapper.queryOutSourceFeeList(param);
	}
	
	/**
	 * 查询 PL_委外金额明细 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOutsourceAmount> queryForPage(Page<PlLoanOutsourceAmount> page){
		plLoanOutsourceAmountMapper.queryForList(page.getPageParams());
		return (Page<PlLoanOutsourceAmount>)page.getPageParams().get(Page.KEY);
	}
}