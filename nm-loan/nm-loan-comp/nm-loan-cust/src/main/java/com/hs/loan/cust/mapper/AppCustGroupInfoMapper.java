package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustGroupInfo;
import com.hs.loan.cust.entity.AppCustInfo;

/**
 * APP_客户分群 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustGroupInfoMapper extends BaseMapper<AppCustGroupInfo>{

	//执行规则
	List<AppCustInfo> executeRule(Map<String, Object> param);
	
}