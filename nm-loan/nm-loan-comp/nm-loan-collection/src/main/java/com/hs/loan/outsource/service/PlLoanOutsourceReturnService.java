package com.hs.loan.outsource.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.outsource.mapper.PlLoanOutsourceReturnMapper;
import com.hs.loan.outsource.entity.PlLoanOutsourceReturn;
import com.hs.base.entity.Page;

/**
 * PL_委外金额收回流水 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  PlLoanOutsourceReturnService{
	@Autowired
	private PlLoanOutsourceReturnMapper plLoanOutsourceReturnMapper;
	
	/**
	 * 新增 PL_委外金额收回流水
	 * @param plLoanOutsourceReturn 新增对象
	 */
	@Transactional
	public void insert(PlLoanOutsourceReturn plLoanOutsourceReturn){
		plLoanOutsourceReturnMapper.insert(plLoanOutsourceReturn);
	}

	/**
	 * 通过主键修改 PL_委外金额收回流水
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		plLoanOutsourceReturnMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_委外金额收回流水
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		plLoanOutsourceReturnMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PL_委外金额收回流水 对象
	 * @param primaryKey 主键
	 * @return PL_委外金额收回流水对象
	 */
	public PlLoanOutsourceReturn getByPrimaryKey(String primaryKey){
		return plLoanOutsourceReturnMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_委外金额收回流水 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOutsourceReturn> queryForList(Map<String, Object> param){
		return plLoanOutsourceReturnMapper.queryForList(param);
	}
	
	/**
	 * 查询 PL_委外金额收回流水 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOutsourceReturn> queryForPage(Page<PlLoanOutsourceReturn> page){
		plLoanOutsourceReturnMapper.queryForList(page.getPageParams());
		return (Page<PlLoanOutsourceReturn>)page.getPageParams().get(Page.KEY);
	}
}