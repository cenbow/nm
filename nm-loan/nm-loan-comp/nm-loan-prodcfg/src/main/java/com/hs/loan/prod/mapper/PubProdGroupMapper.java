package com.hs.loan.prod.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.bo.ProdGroupBO;
import com.hs.loan.prod.entity.PubProdGroup;

/**
 * PUB_产品与销售群关系 mapper
 * @author autocreate
 * @create 2015-10-21
 */
@MyBatisRepository
public interface  PubProdGroupMapper extends BaseMapper<PubProdGroup>{

	List<ProdGroupBO>  queryForGroupCrowdList(Map<String, Object> pageParams);
	
	List<ProdGroupBO>  queryForPageGroupCrowd(Map<String, Object> pageParams);

	void deleteByGroupNo(String primaryKey);
	
}