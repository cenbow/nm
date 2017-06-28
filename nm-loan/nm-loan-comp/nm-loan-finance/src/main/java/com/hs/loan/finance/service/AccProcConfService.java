package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccProcConfMapper;
import com.hs.loan.finance.entity.AccProcConf;
import com.hs.base.entity.Page;

/**
 * ACC_交易配置 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccProcConfService{
	@Autowired
	private AccProcConfMapper accProcConfMapper;
	
	/**
	 * 新增 ACC_交易配置
	 * @param accProcConf 新增对象
	 */
	@Transactional
	public void insert(AccProcConf accProcConf){
		accProcConfMapper.insert(accProcConf);
	}

	/**
	 * 通过主键修改 ACC_交易配置
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accProcConfMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_交易配置
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accProcConfMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_交易配置 对象
	 * @param primaryKey 主键
	 * @return ACC_交易配置对象
	 */
	public AccProcConf getByPrimaryKey(String primaryKey){
		return accProcConfMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_交易配置 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccProcConf> queryForList(Map<String, Object> param){
		return accProcConfMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_交易配置 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccProcConf> queryForPage(Page<AccProcConf> page){
		accProcConfMapper.queryForList(page.getPageParams());
		return (Page<AccProcConf>)page.getPageParams().get(Page.KEY);
	}
}