package com.hs.loan.market.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.market.entity.PubSalerBranch;

/**
 * PUB_销售网点关联信息 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubSalerBranchMapper extends BaseMapper<PubSalerBranch>{

	//移除网点与销售的关系
	void rmvBrhSalerRel(Map<String, Object> param);
	
}