package com.hs.loan.approve.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.entity.AppApprGroupGoods;

/**
 * APP_审批组与商品类型关系 mapper
 * @author autocreate
 * @create 2017-01-13
 */
@MyBatisRepository
public interface  AppApprGroupGoodsMapper extends BaseMapper<AppApprGroupGoods>{

	void deleteByGroupNo(String groupNo);
	
}