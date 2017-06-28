package com.hs.loan.collection.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.collection.mapper.PlCaseRepayLogMapper;
import com.hs.loan.collection.entity.PlCaseRepayLog;
import com.hs.base.entity.Page;

/**
 * PL_案件还款日志 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  PlCaseRepayLogService{
	@Autowired
	private PlCaseRepayLogMapper plCaseRepayLogMapper;
	
	/**
	 * 新增 PL_案件还款日志
	 * @param plCaseRepayLog 新增对象
	 */
	@Transactional
	public void insert(PlCaseRepayLog plCaseRepayLog){
		plCaseRepayLogMapper.insert(plCaseRepayLog);
	}

	/**
	 * 通过主键修改 PL_案件还款日志
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		plCaseRepayLogMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_案件还款日志
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		plCaseRepayLogMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PL_案件还款日志 对象
	 * @param primaryKey 主键
	 * @return PL_案件还款日志对象
	 */
	public PlCaseRepayLog getByPrimaryKey(String primaryKey){
		return plCaseRepayLogMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_案件还款日志 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlCaseRepayLog> queryForList(Map<String, Object> param){
		return plCaseRepayLogMapper.queryForList(param);
	}
	
	/**
	 * 查询 PL_案件还款日志 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlCaseRepayLog> queryForPage(Page<PlCaseRepayLog> page){
		plCaseRepayLogMapper.queryForList(page.getPageParams());
		return (Page<PlCaseRepayLog>)page.getPageParams().get(Page.KEY);
	}
}