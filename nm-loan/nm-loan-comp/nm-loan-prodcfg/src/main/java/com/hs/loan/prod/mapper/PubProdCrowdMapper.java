package com.hs.loan.prod.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProdCrowd;

/**
 * PUB_产品与销售组关系 mapper
 * @author autocreate
 * @create 2015-10-21
 */
@MyBatisRepository
public interface  PubProdCrowdMapper extends BaseMapper<PubProdCrowd>{

	void deleteByCrowdNo(String crowdNo);
	
}