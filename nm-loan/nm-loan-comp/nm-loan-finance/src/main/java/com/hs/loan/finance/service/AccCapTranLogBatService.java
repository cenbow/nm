package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccCapTranLogBatMapper;
import com.hs.loan.finance.entity.AccCapTranLogBat;
import com.hs.base.entity.Page;

/**
 * ACC_银联交易日志（批量） 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccCapTranLogBatService{
	@Autowired
	private AccCapTranLogBatMapper accCapTranLogBatMapper;
	
	/**
	 * 新增 ACC_银联交易日志（批量）
	 * @param accCapTranLogBat 新增对象
	 */
	@Transactional
	public void insert(AccCapTranLogBat accCapTranLogBat){
		accCapTranLogBatMapper.insert(accCapTranLogBat);
	}

	/**
	 * 通过主键修改 ACC_银联交易日志（批量）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accCapTranLogBatMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_银联交易日志（批量）
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accCapTranLogBatMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_银联交易日志（批量） 对象
	 * @param primaryKey 主键
	 * @return ACC_银联交易日志（批量）对象
	 */
	public AccCapTranLogBat getByPrimaryKey(String primaryKey){
		return accCapTranLogBatMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_银联交易日志（批量） 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapTranLogBat> queryForList(Map<String, Object> param){
		return accCapTranLogBatMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_银联交易日志（批量） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapTranLogBat> queryForPage(Page<AccCapTranLogBat> page){
		Map<String, Object> param = page.getPageParams();
		param.put("chanNo", param.get("exportTxtType"));
		accCapTranLogBatMapper.queryForList(param);
		return (Page<AccCapTranLogBat>)page.getPageParams().get(Page.KEY);
	}
}