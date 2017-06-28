package com.hs.loan.prod.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.entity.PubProdGoods;
import com.hs.loan.prod.mapper.PubProdGoodsMapper;

/**
 * PUB_产品与商品类型的关系 业务处理
 * @author autocreate
 * @create 2015-10-20
 */
@Service
@Transactional(readOnly=true)
public class  PubProdGoodsService{
	@Autowired
	private PubProdGoodsMapper pubProdGoodsMapper;
	
	/**
	 * 新增 PUB_产品与商品类型的关系
	 * @param pubProdGoods 新增对象
	 */
	@Transactional
	public void insert(PubProdGoods pubProdGoods){
		pubProdGoodsMapper.insert(pubProdGoods);
	}

	/**
	 * 通过主键修改 PUB_产品与商品类型的关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdGoodsMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_产品与商品类型的关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdGoodsMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_产品与商品类型的关系 对象
	 * @param primaryKey 主键
	 * @return PUB_产品与商品类型的关系对象
	 */
	public PubProdGoods getByPrimaryKey(String primaryKey){
		return pubProdGoodsMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_产品与商品类型的关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubProdGoods> queryForList(Map<String, Object> param){
		return pubProdGoodsMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_产品与商品类型的关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubProdGoods> queryForPage(Page<PubProdGoods> page){
		pubProdGoodsMapper.queryForList(page.getPageParams());
		return (Page<PubProdGoods>)page.getPageParams().get(Page.KEY);
	}

	/**根据产品编号删除已有关系
	 * @param prodNo
	 */
	@Transactional
	public void deleteByProdNo(String prodNo) {
		pubProdGoodsMapper.deleteByProdNo(prodNo);
	}
}