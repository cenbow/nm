package com.hs.loan.acct.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.acct.entity.PubRepayFeeConf;

/**
 * PUB_费用项配置 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubRepayFeeConfMapper extends BaseMapper<PubRepayFeeConf>{

	//查询在指定产品中未与此产品关联的费用项配置
	List<PubRepayFeeConf> queryProdUnUsedFeeConf(Map<String, Object> pageParams);
	//查询在指定产品中与此产品关联的费用项配置
	List<PubRepayFeeConf> queryProdUsedFeeConf(Map<String, Object> param);
	
}