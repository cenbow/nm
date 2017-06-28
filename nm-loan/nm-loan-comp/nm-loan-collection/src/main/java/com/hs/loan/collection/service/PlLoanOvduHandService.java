package com.hs.loan.collection.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.collection.entity.PlLoanOvduHand;
import com.hs.loan.collection.mapper.PlLoanOvduHandMapper;

/**
 * PL_逾期案件经办信息 业务处理
 * 
 * @author autocreate
 * @create 2016-04-13
 */
@Service
@Transactional(readOnly = true)
public class PlLoanOvduHandService {
	@Autowired
	private PlLoanOvduHandMapper plLoanOvduHandMapper;

	/**
	 * 新增 PL_逾期案件经办信息
	 * 
	 * @param plLoanOvduHand
	 *            新增对象
	 */
	@Transactional
	public void insert(PlLoanOvduHand plLoanOvduHand) {
		plLoanOvduHandMapper.insert(plLoanOvduHand);
	}

	/**
	 * 通过主键修改 PL_逾期案件经办信息
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		plLoanOvduHandMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_逾期案件经办信息
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		plLoanOvduHandMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PL_逾期案件经办信息 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return PL_逾期案件经办信息对象
	 */
	public PlLoanOvduHand getByPrimaryKey(String primaryKey) {
		return plLoanOvduHandMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_逾期案件经办信息 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOvduHand> queryForList(Map<String, Object> param) {
		return plLoanOvduHandMapper.queryForList(param);
	}

	/**
	 * 查询 PL_逾期案件经办信息 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOvduHand> queryForPage(Page<PlLoanOvduHand> page) {
		plLoanOvduHandMapper.queryForList(page.getPageParams());
		return (Page<PlLoanOvduHand>) page.getPageParams().get(Page.KEY);
	}

	public List<PlLoanOvduHand> queryLoanOvduHandForList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.queryForList(param);
	}

}