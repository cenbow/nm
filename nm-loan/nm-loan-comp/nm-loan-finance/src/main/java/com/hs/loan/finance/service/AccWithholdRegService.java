package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccWithholdRegMapper;
import com.hs.loan.finance.entity.AccWithholdReg;
import com.hs.base.entity.Page;

/**
 * ACC_扣款流水记录表 业务处理
 * @author autocreate
 * @create 2016-12-06
 */
@Service
@Transactional(readOnly=true)
public class  AccWithholdRegService{
	@Autowired
	private AccWithholdRegMapper accWithholdRegMapper;
	
	/**
	 * 新增 ACC_扣款流水记录表
	 * @param accWithholdReg 新增对象
	 */
	@Transactional
	public void insert(AccWithholdReg accWithholdReg){
		accWithholdRegMapper.insert(accWithholdReg);
	}
	/**
	 * 插入扣款记录
	 * @param accWithholdReg
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertAccWithholdReg(AccWithholdReg accWithholdReg){
		accWithholdRegMapper.insert(accWithholdReg);
	}
	/**
	 * 通过主键修改 ACC_扣款流水记录表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accWithholdRegMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_扣款流水记录表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accWithholdRegMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_扣款流水记录表 对象
	 * @param primaryKey 主键
	 * @return ACC_扣款流水记录表对象
	 */
	public AccWithholdReg getByPrimaryKey(String primaryKey){
		return accWithholdRegMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_扣款流水记录表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccWithholdReg> queryForList(Map<String, Object> param){
		return accWithholdRegMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_扣款流水记录表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccWithholdReg> queryForPage(Page<AccWithholdReg> page){
		accWithholdRegMapper.queryForList(page.getPageParams());
		return (Page<AccWithholdReg>)page.getPageParams().get(Page.KEY);
	}
}