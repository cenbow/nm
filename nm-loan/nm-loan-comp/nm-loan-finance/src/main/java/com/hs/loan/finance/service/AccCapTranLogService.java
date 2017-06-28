package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccCapTranLogMapper;
import com.hs.loan.finance.entity.AccCapTranLog;
import com.hs.base.entity.Page;

/**
 * ACC_银联交易日志（单笔） 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccCapTranLogService{
	@Autowired
	private AccCapTranLogMapper accCapTranLogMapper;
	
	/**
	 * 新增 ACC_银联交易日志（单笔）
	 * @param accCapTranLog 新增对象
	 */
	@Transactional
	public void insert(AccCapTranLog accCapTranLog){
		accCapTranLogMapper.insert(accCapTranLog);
	}

	/**
	 * 通过主键修改 ACC_银联交易日志（单笔）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accCapTranLogMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_银联交易日志（单笔）
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accCapTranLogMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_银联交易日志（单笔） 对象
	 * @param primaryKey 主键
	 * @return ACC_银联交易日志（单笔）对象
	 */
	public AccCapTranLog getByPrimaryKey(String primaryKey){
		return accCapTranLogMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_银联交易日志（单笔） 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapTranLog> queryForList(Map<String, Object> param){
		return accCapTranLogMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_银联交易日志（单笔） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapTranLog> queryForPage(Page<AccCapTranLog> page){
		accCapTranLogMapper.queryForList(page.getPageParams());
		return (Page<AccCapTranLog>)page.getPageParams().get(Page.KEY);
	}
}