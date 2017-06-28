package com.hs.loan.prod.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProdOrg;

/**
 *  mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubProdOrgMapper extends BaseMapper<PubProdOrg>{
	void deletePubProdOrgByprodNo(String prodNo);
}