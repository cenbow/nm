package com.hs.loan.approve.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.approve.mapper.AppApprGroupGoodsMapper;
import com.hs.loan.approve.entity.AppApprGroupGoods;
import com.hs.base.entity.Page;

/**
 * APP_审批组与商品类型关系 业务处理
 * @author autocreate
 * @create 2017-01-13
 */
@Service
@Transactional(readOnly=true)
public class  AppApprGroupGoodsService{
	@Autowired
	private AppApprGroupGoodsMapper appApprGroupGoodsMapper;
	
	/**
	 * 新增 APP_审批组与商品类型关系
	 * @param appApprGroupGoods 新增对象
	 */
	@Transactional
	public void insert(AppApprGroupGoods appApprGroupGoods){
		appApprGroupGoodsMapper.insert(appApprGroupGoods);
	}

	/**
	 * 通过主键修改 APP_审批组与商品类型关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appApprGroupGoodsMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_审批组与商品类型关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appApprGroupGoodsMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键删除 APP_审批组与商品类型关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByGroupNo(String groupNo){
		appApprGroupGoodsMapper.deleteByGroupNo(groupNo);
	}

	/**
	 * 通过主键取得 APP_审批组与商品类型关系 对象
	 * @param primaryKey 主键
	 * @return APP_审批组与商品类型关系对象
	 */
	public AppApprGroupGoods getByPrimaryKey(String primaryKey){
		return appApprGroupGoodsMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_审批组与商品类型关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppApprGroupGoods> queryForList(Map<String, Object> param){
		return appApprGroupGoodsMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_审批组与商品类型关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprGroupGoods> queryForPage(Page<AppApprGroupGoods> page){
		appApprGroupGoodsMapper.queryForList(page.getPageParams());
		return (Page<AppApprGroupGoods>)page.getPageParams().get(Page.KEY);
	}
}