package com.hs.loan.prod.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProdRepayTyp;

/**
 * PUB_产品与还款类型的关系 mapper
 * @author autocreate
 * @create 2015-10-16
 */
@MyBatisRepository
public interface  PubProdRepayTypMapper extends BaseMapper<PubProdRepayTyp>{

	void deleteByProdNo(String prodNo);
	
}