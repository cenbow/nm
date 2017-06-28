package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustContctInfo;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户联系信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustContctInfoMapper extends ICustExtraInfoMapper<AppCustContctInfo>{

	//通过客户号获取客户当前使用的联系信息
	AppCustContctInfo getCustCurrentContactInfoByNo(String custNo);

	
}