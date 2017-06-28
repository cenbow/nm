package com.hs.loan.acct.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.acct.entity.PubRepayFirstConf;

/**
 * PUB_首次还款日规则 mapper
 * @author autocreate
 * @create 2015-10-29
 */
@MyBatisRepository
public interface  PubRepayFirstConfMapper extends BaseMapper<PubRepayFirstConf>{
	
	public void getFirstRepayDate(Map<String,String> map);
	
}