package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccAcctRegMapper;
import com.hs.loan.finance.entity.AccAcctReg;
import com.hs.base.entity.Page;

/**
 * ACC_账务登记 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccAcctRegService{
	@Autowired
	private AccAcctRegMapper accAcctRegMapper;
	
	/**
	 * 新增 ACC_账务登记
	 * @param accAcctReg 新增对象
	 */
	@Transactional
	public void insert(AccAcctReg accAcctReg){
		accAcctRegMapper.insert(accAcctReg);
	}

	/**
	 * 通过主键修改 ACC_账务登记
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accAcctRegMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_账务登记
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accAcctRegMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_账务登记 对象
	 * @param primaryKey 主键
	 * @return ACC_账务登记对象
	 */
	public AccAcctReg getByPrimaryKey(String primaryKey){
		return accAcctRegMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_账务登记 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccAcctReg> queryForList(Map<String, Object> param){
		return accAcctRegMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_账务登记 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccAcctReg> queryForPage(Page<AccAcctReg> page){
		accAcctRegMapper.queryForList(page.getPageParams());
		return (Page<AccAcctReg>)page.getPageParams().get(Page.KEY);
	}
}