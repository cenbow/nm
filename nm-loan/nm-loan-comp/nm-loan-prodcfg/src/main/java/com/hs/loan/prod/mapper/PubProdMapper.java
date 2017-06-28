package com.hs.loan.prod.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProd;

/**
 * PUB_产品信息 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubProdMapper extends BaseMapper<PubProd>{

	List<PubProd> queryForBaseList(Map<String, Object> pageParams);
	
	
	List<PubProd> queryProdLisForLoanCal(Map<String,Object> map);


	List<Map<String, Object>> getPreFee(List<PubProd> list);
}