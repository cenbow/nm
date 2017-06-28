package com.hs.loan.prod.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProdGoods;

/**
 * PUB_产品与商品类型的关系 mapper
 * @author autocreate
 * @create 2015-10-20
 */
@MyBatisRepository
public interface  PubProdGoodsMapper extends BaseMapper<PubProdGoods>{

	void deleteByProdNo(String prodNo);
	
}