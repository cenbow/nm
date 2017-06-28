package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustContctOther;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户其他联系人信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustContctOtherMapper extends ICustExtraInfoMapper<AppCustContctOther>{

	//根据客户号和有效时间段 获取 客户其他联系人信息
	List<AppCustContctOther> getCustContctOtherLstByDate(Map<String, Object> param);

	
}