package com.hs.loan.prod.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.prod.entity.PubProdFundChan;

/**
 *  mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubProdFundChanMapper extends BaseMapper<PubProdFundChan>{


	void deletePubPrdoFunChanByprodNo(String prodNo);

	void deletePubPrdoFunChanBychanNo(String chanNo);
	
}