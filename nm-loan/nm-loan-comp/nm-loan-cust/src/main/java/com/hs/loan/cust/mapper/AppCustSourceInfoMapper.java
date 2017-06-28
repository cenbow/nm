package com.hs.loan.cust.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustSourceInfo;

/**
 * APP_客户来源信息表 mapper
 * @author autocreate
 * @create 2017-03-18
 */
@MyBatisRepository
public interface  AppCustSourceInfoMapper extends BaseMapper<AppCustSourceInfo>{

	AppCustSourceInfo getByCustNo(String custNo);
	
}