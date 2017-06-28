package com.hs.loan.prod.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProdArea;

/**
 *  mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubProdAreaMapper extends BaseMapper<PubProdArea>{

	void deleteProdAreaByBusi(Map<String, Object> map);
	
}