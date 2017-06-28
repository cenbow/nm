package com.hs.loan.sale.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.sale.mapper.AppGrapScoreMapper;
import com.hs.loan.sale.entity.AppGrapScore;
import com.hs.base.entity.Page;

/**
 * APP_芝麻分抓取表 业务处理
 * @author autocreate
 * @create 2017-03-28
 */
@Service
@Transactional(readOnly=true)
public class  AppGrapScoreService{
	@Autowired
	private AppGrapScoreMapper appGrapScoreMapper;
	
	/**
	 * 新增 APP_芝麻分抓取表
	 * @param appGrapScore 新增对象
	 */
	@Transactional
	public void insert(AppGrapScore appGrapScore){
		appGrapScoreMapper.insert(appGrapScore);
	}

	/**
	 * 通过主键修改 APP_芝麻分抓取表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appGrapScoreMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_芝麻分抓取表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appGrapScoreMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过CustNo取得 APP_芝麻分抓取表 对象
	 * @param CustNo
	 * @return APP_芝麻分抓取表对象
	 */
	public AppGrapScore getByCustNo(Map<String, Object> param){
		return appGrapScoreMapper.getByCustNo(param);
	}


	/**
	 * 通过主键取得 APP_芝麻分抓取表 对象
	 * @param primaryKey 主键
	 * @return APP_芝麻分抓取表对象
	 */
	public AppGrapScore getByPrimaryKey(String primaryKey){
		return appGrapScoreMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_芝麻分抓取表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppGrapScore> queryForList(Map<String, Object> param){
		return appGrapScoreMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_芝麻分抓取表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppGrapScore> queryForPage(Page<AppGrapScore> page){
		appGrapScoreMapper.queryForList(page.getPageParams());
		return (Page<AppGrapScore>)page.getPageParams().get(Page.KEY);
	}
}