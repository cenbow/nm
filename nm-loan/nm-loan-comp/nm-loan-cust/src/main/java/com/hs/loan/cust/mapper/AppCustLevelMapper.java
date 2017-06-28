package com.hs.loan.cust.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.bo.CustStarLevelBo;
import com.hs.loan.cust.entity.AppCustLevel;

/**
 * APP_客户评级 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustLevelMapper extends BaseMapper<AppCustLevel>{

	//获取星级评估信息
	CustStarLevelBo getStarEvaluate(String custNo);
	
}