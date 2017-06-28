package com.hs.loan.prod.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProdStr;

/**
 * PUB_产品与网点的关系 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubProdStrMapper extends BaseMapper<PubProdStr>{

	void deleteByProdNo(String prodNo);

	void deleteBybranchNo(String branchNo);
	
}