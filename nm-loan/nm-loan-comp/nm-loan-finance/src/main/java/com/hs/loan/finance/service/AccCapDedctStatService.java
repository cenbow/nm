package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccCapDedctStatMapper;
import com.hs.loan.finance.entity.AccCapDedctStat;
import com.hs.base.entity.Page;

/**
 * ACC_银联代扣统计 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccCapDedctStatService{
	@Autowired
	private AccCapDedctStatMapper accCapDedctStatMapper;
	
	/**
	 * 新增 ACC_银联代扣统计
	 * @param accCapDedctStat 新增对象
	 */
	@Transactional
	public void insert(AccCapDedctStat accCapDedctStat){
		accCapDedctStatMapper.insert(accCapDedctStat);
	}

	/**
	 * 通过主键修改 ACC_银联代扣统计
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accCapDedctStatMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_银联代扣统计
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accCapDedctStatMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_银联代扣统计 对象
	 * @param primaryKey 主键
	 * @return ACC_银联代扣统计对象
	 */
	public AccCapDedctStat getByPrimaryKey(String primaryKey){
		return accCapDedctStatMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_银联代扣统计 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapDedctStat> queryForList(Map<String, Object> param){
		return accCapDedctStatMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_银联代扣统计 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapDedctStat> queryForPage(Page<AccCapDedctStat> page){
		accCapDedctStatMapper.queryForList(page.getPageParams());
		return (Page<AccCapDedctStat>)page.getPageParams().get(Page.KEY);
	}
}